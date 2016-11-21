<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/10/23
  Time: 21:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="css/default.css"/>
</head>
<body background="img/welcome-bg2.png">
<div class="welcome">
    <div class="welcom-text" >欢迎登陆就业管理中心！ </div>
</div>
<div>

    <span>今天参加面试的学生有&nbsp;<a href="/inter/findByDay" target=main>${dayInter}</a></span>&nbsp;人。
    <br>
    <span>近一周企业发布的招聘信息有&nbsp;<a href="/recruit/findByWeek" target=main>${weekRecruit}</a></span>&nbsp;条。
    <br>
    <span>近一周发布招聘信息的企业有&nbsp;<a href="/company/findComByWeek" target=main>${weekRecruitCom}</a></span>&nbsp;家。
    <br>
</div>

<div class="table-slipline"></div>

</body>
</html>
