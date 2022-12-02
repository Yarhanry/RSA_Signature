package com.example.rsa_signature;


import SHA.SHA512;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "two", value = "/two")
public class two extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");


        String message2 = request.getParameter("message2");
        String signature = request.getParameter("signature");
        request.getSession().setAttribute("message", message2);
        SHA512 sha_512 = new SHA512();
        byte[] bits =  message2.getBytes();
        bits = sha_512.setPad(bits);
        String info = sha_512.generate_hash(bits);  // 将更改的message2进行SHA-512散列后结果
        request.getSession().setAttribute("message1", info);
        request.getSession().setAttribute("pr_mess", signature);
        response.sendRedirect("verify.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}