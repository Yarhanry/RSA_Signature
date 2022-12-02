package com.example.rsa_signature;

import RSA.BitByte;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.*;
import java.security.spec.X509EncodedKeySpec;



@WebServlet(name = "three", value = "/three")
public class three extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");


        String info = (String)request.getSession().getAttribute("message1");  // 获取修改后散列值
        String pr_mess = (String)request.getSession().getAttribute("pr_mess");  // 获取修改后签名
        String pubkeyvalue = (String)request.getSession().getAttribute("pubkeyvalue");  // 获取公钥


        try {
            // 第三步，利用公钥对签名后的消息进行验证
            System.out.println("第三步，利用公钥对签名后的消息进行验证");
            X509EncodedKeySpec bobPubKeySpec = new X509EncodedKeySpec(
                    BitByte.hexStrToBytes(pubkeyvalue));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey pubKey = keyFactory.generatePublic(bobPubKeySpec);

            byte[] signed = BitByte.hexStrToBytes(pr_mess);
            Signature signetcheck = Signature.getInstance("SHA512withRSA");
            signetcheck.initVerify(pubKey);
            signetcheck.update(info.getBytes());

            if (signetcheck.verify(signed)) {   // 进行验证
                request.getSession().setAttribute("result", "恭喜正确是本人！");
                response.sendRedirect("verify.jsp");
            } else {
                request.getSession().setAttribute("result", "很遗憾被篡改了！");
                response.sendRedirect("verify.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}