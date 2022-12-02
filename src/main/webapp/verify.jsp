<%--
  Created by IntelliJ IDEA.
  User: hua'wei
  Date: 2022/11/13
  Time: 18:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
  String mess = (String)request.getSession().getAttribute("message");  // 原始消息
  if (mess == null) mess = "";
  String pr_mess = (String)request.getSession().getAttribute("pr_mess");  // 密文(签名)
  if (pr_mess == null) pr_mess = "";
  String pubkeyvalue = (String)request.getSession().getAttribute("pubkeyvalue");  // 公钥
  if (pubkeyvalue == null) pubkeyvalue = "";
  String mess1 = (String)request.getSession().getAttribute("message1");  //散列原始消息后的值
  if (mess1 == null) mess1 = "";
  String result = (String)request.getSession().getAttribute("result");  // 结果对比
  if (result == null) result = "";
  String pu_mess = (String)request.getSession().getAttribute("pu_mess");  // 公钥解签后的值
  if (pu_mess == null) pu_mess = "";
%>
<html>

<head>
  <title>RSA数字签名系统</title>
  <link href="css/verify.css" rel="stylesheet" type="text/css" />
</head>

<body>
  <div class="box">
    <form action="two" method="post">
      <h1>签名验证</h1>
      <div class="up">
      <h3>验证者已获取到签名、公钥、Message</h3>
      Message&nbsp;:&nbsp;<input type="text" name="message2" class="input" value=<%=mess%>>
      <br>
      <br>
      &nbsp;签&nbsp;&nbsp;&nbsp;&nbsp;名&nbsp;&nbsp;:&nbsp;&nbsp;<input type="text" name="signature" class="input" value=<%=pr_mess%>>
      <br>
      <br>
      &nbsp;公&nbsp;&nbsp;&nbsp;&nbsp;钥&nbsp;&nbsp;:&nbsp;&nbsp;<input type="text" name="pu" class="input" value=<%=pubkeyvalue%>>
      <br>
      <br>
      <input type="submit" class="input-botton" value="提交数据">
      </div>
    </form>

    <form action="three" method="post">
      <div class="down">
      <h3>使用SHA-512散列Message</h3>
      HASH II(使用SHA-512散列M后)：<br><input type="text" class="input" value=<%=mess1%>>
      <br>
      <br>
      <h3>比较HASH I & HASH II</h3>
      <input type="text" class="input" value=<%=result%>>
      <br>
      <br>
      <input type="submit" value="对比结果" class="input-botton">
      </div>
    </form>
  </div>
</body>

</html>
