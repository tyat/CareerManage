<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 2016/11/3
  Time: 9:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"  %>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" type="text/css" href="../../css/default.css"/>
    <link rel="stylesheet" type="text/css" href="../../css/bootstrap.css"/>
    <script src="../../js/showele.js" type="text/javascript" charset="utf-8"></script>
    <link rel="stylesheet" href="../../css/icon.css" />
    <script type="text/javascript" src="../../js/showele.js" ></script>

</head>
<body onload="Activeli()">
<div class="table-box">
    <div class="table-content">
        <div class="table-head">
            <div class="table-address">
                <div style="float: left;">
                    <span>信息管理</span><div class="left-arrow"></div>
                    <span>未就业生信息</span><div class="left-arrow"></div>
                </div><br />
                <div class="Big-title">
                    <div class="littil-title">
                        近一个月期望就业学生
                    </div>
                </div>
            </div>
        </div>
        <div id="allUnemp">
            <c:forEach varStatus="i" var="list" items="${UnempList}">
                    <!--这是一条记录开始-->
                    <table  class="pure-table pure-table-bordered left">
                        <tr>
                            <td rowspan="4">
                               ${list.sname}
                            </td>
                            <td width="100px">班级：</td>
                            <td width="100px">
                               ${list.spro}${list.sclass}班
                            </td>
                            <td width="50px">年级：</td>
                            <td>
                                    ${list.sgrade}级
                            </td>
                            <td width="50px">性别：</td>
                            <td> <c:if test="${list.ssex==false}">
                                男
                            </c:if>
                                <c:if test="${list.ssex==true}">
                                    女
                                </c:if>
                            </td>
                        </tr>
                        <tr>
                            <td>期望岗位：</td>
                            <td colspan="5">${list.jname}</td>
                        </tr>
                        <tr>
                            <td>期望月薪:</td>
                            <td colspan="5">${list.uesalary}元/月</td>
                        </tr>
                        <tr>
                            <td>期望实习时间:</td>
                            <td colspan="5">${list.uetime}</td>
                        </tr>
                    </table>
                    <div class="table-slipline"></div>
                    <!--这是一条记录结束-->
            </c:forEach>
        </div>
    </div>
</div>

</body>
</html>
