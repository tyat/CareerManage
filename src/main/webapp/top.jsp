<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/10/23
  Time: 21:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="css/default.css"/>
    <link rel="stylesheet" type="text/css" href="css/icon.css"/>
    <script type="text/javascript" src="js/icon.js" ></script>
    <script type="text/javascript" src="js/showele.js" ></script>
</head>
<body >

<div class="logo">
    <a href="main.html" target=main><img src="img/title.png" alt="回到主页" /></a>
</div>
<div class="loginpic">
    <button id="user_icon" type="button"  value="login_user"><span>&nbsp;&nbsp;&nbsp;当前用户：${cmUser.uname}</span></button>
    <a id="loginout_icon"  href="/user/quit" target="_self;index" > <span style="font-size: 10px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;退出</span> &nbsp;&nbsp;&nbsp;&nbsp;</a>
    <%--<button id="loginout_icon" type="button" value="logout" onclick="location='/user/quit',target='index'"> <span>退出</span> </button>--%>
</div>
<div class="indexline"></div>
</body>
</html>
