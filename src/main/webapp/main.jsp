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
                <td>
                    <button class="mybutton" type="button" onclick="location='/unemp/findAllCount'">就业生、未就业生分布</button>
                </td>
                <td>
                    <button class="mybutton" type="button" onclick="location='/unemp/findSumNotEmp'">未就业生情况分布</button>
                </td>
                <td>
                    <button class="mybutton" type="button" onclick="location='/emp/findEmpCountByType'">就业生情况分布</button>
                </td>
            </tr>
        </table>
        <table  class="pure-table pure-table-bordered CompInfo1">
            <tr>
                <td rowspan="2">就业生月增量</td>
                <td>日期</td>
                <c:forEach items="${empIncrease}" var="emp">
                    <td> ${emp.thismonth}
                            <%--<fmt:formatDate value="${emp.thismonth}" pattern="yyyy-MM-dd"/> --%>
                    </td>
                </c:forEach>
            </tr>
            <tr>
                <td>增量</td>
                <c:forEach items="${empIncrease}" var="emp">
                    <td>${emp.data}</td>
                </c:forEach>
            </tr>
        </table>
        <table class="pure-table pure-table-bordered left">
            <tr>
                <td>近一个月准备就业的人数</td>
                <td>近一个月就业学生数量</td>
                <td>当前已就业学生数量</td>
                <td>当前未就业学生数量</td>
            </tr>
            <tr>
                <td>
                    <button class="mybutton" type="button" onclick="location='/unemp/findAllUnempMonth'">${unempmonth}</button>
                </td>
                <td>
                    <button class="mybutton" type="button" onclick="location='/unemp/findAllUnemp?page=1'">${EmpCountByMonth}</button>
                </td>
                <td>
                    <button class="mybutton" type="button" onclick="location='/unemp/findAllUnemp?page=1'">${empCount}</button>
                </td>
                <td>
                    <button class="mybutton" type="button" onclick="location='/unemp/findAllUnemp?page=1'">${unempCount}</button>
                </td>
            </tr>
        </table>
        <div class="table-slipline"></div>
    </div>
</body>
</html>
