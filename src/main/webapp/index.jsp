<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
    String mess = (String)request.getSession().getAttribute("message1");  // HASH值
    if (mess == null) mess = "";
    String pr_mess = (String)request.getSession().getAttribute("pr_mess");  // 密文(签名)
    if (pr_mess == null) pr_mess = "";
%>

<!DOCTYPE html>
<html>

<head>
    <title>RSA数字签名系统</title>
    <link href="css/index.css" rel="stylesheet" type="text/css" />
</head>

<body>
    <form action="one" method="post" class="box">
        <h1 class="font">基于RSA的数字签名设计与实现</h1>
        Message&nbsp;:&nbsp;<input type="text" name="message1" placeholder="请输入原始消息" class="input">
        <br>
        <br>
        HASH值&nbsp;:&nbsp;&nbsp;<input type="text" class="input" value=<%=mess%>>
        <br>
        <br>
        &nbsp;密&nbsp;&nbsp;&nbsp;&nbsp;文&nbsp;&nbsp;:&nbsp;&nbsp;<input type="text" class="input" value=<%=pr_mess%>>
        <br>
        <br>
        <input type="submit" value="获得SHA-512散列值及私钥签名" class="input-botton">
        <br>
        <br>
        <a href="verify.jsp" class="lianjie">进行签名验证</a>
    </form>
</body>

</html>