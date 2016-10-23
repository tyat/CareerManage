<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/10/23
  Time: 17:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
    <link rel="stylesheet" type="text/css" href="css/default.css"/>
    <script type="text/javascript" src="js/valid.js" ></script>
</head>
<style>
    body{
        background: url(img/welcome-bg2.png);
    }
</style>
<body>
<div class="logo2">
    <img src="img/title.png" />
</div>
<div class="login-bg">
    <div class="login-form-box">
        <form  action="user/login" onsubmit="return  v_login()">
            <div class="form-box">
                <input type="text" value="" name="uname" id="uname" placeholder="请输入用户名..." onkeyup="value=value.replace(/[\W]/g,'') "  onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" required="required" /><br />
                <input type="password" value="" name="upwd" id="upwd" placeholder="请输入密码..."  required="required"  /><br />
                <span><input type="checkbox" value="1"  checked="checked"/>记住密码</span>
                <input type="submit" value="登录" name="" class="mybutton"  /><br />
            </div>
        </form>
    </div>
</div>
</body>
</html>
