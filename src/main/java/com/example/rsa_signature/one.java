package com.example.rsa_signature;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import RSA.GenerateKey;
import RSA.SignatureData;
import SHA.SHA512;


@WebServlet(name = "one", value = "/one")
public class one extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");


        String message1 = request.getParameter("message1");  // 原始message
        request.getSession().setAttribute("message", message1);
        SHA512 sha_512 = new SHA512();
        byte[] bits =  message1.getBytes();
        bits = sha_512.setPad(bits);
        String info = sha_512.generate_hash(bits);  // SHA-512散列后结果
        request.getSession().setAttribute("message1", info);  // 此时存入到session中的message1是散列后结果

        try {
            // 第一步，产生密钥对
            System.out.println("第一步，产生密钥对");
            GenerateKey genkeypair = new GenerateKey();
            String pubkeyvalue = genkeypair.getPubKey();
            String prikeyvalue = genkeypair.getPriKey();
            request.getSession().setAttribute("pubkeyvalue", pubkeyvalue);

            // 第二步，利用私钥对消息进行加密
            System.out.println("第二步，利用私钥对消息进行加密");
            String signedmsg = SignatureData.generateSignedMessage(info,
                    prikeyvalue);  // 用私钥签名后的消息
            request.getSession().setAttribute("pr_mess", signedmsg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("index.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}