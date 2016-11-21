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
    <span>就业生月增量：</span>
<table  class="pure-table pure-table-bordered CompInfo1">
    <tr>
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
</div>

<div class="table-slipline"></div>

</body>
</html>
