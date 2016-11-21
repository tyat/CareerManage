<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/10/23
  Time: 21:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.*" isELIgnored="false" %>
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
        <table class="pure-table pure-table-bordered left">
            <tr>
                <td width="200px">近一个月就业学生数量：</td>
                <td>
                    <button class="mybutton" type="button" onclick="location='/unemp/findAllUnemp?page=1'">${EmpCountByMonth}</button>
                </td>
            </tr>
            <tr>
                <td width="200px">当前已就业学生数量：</td>
                <td>
                    <button class="mybutton" type="button" onclick="location='/unemp/findAllUnemp?page=1'">${empCount}</button>
                </td>
            </tr>
            <tr>
                <td width="200px">当前未就业学生数量：</td>
                <td>
                    <button class="mybutton" type="button" onclick="location='/unemp/findAllUnemp?page=1'">${unempCount}</button>
                </td>
            </tr>
        </table>
        <div class="table-slipline"></div>
    </div>
</body>
</html>
