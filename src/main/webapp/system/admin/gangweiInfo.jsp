<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 2016/11/8
  Time: 21:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.*" isELIgnored="false" %>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" type="text/css" href="../../css/default.css"/>
    <script src="../../js/showele.js" type="text/javascript" charset="utf-8"></script>
    <link rel="stylesheet" href="../../css/icon.css" />
</head>
<body>
<div class="table-box">
    <div class="table-content">
        <!--这是标题栏-->
        <div class="table-head">
            <div class="table-address">
                <div style="float: left;">
                    <span>信息管理</span><div class="left-arrow"></div><span>企业信息</span>
                    <div class="left-arrow"></div>
                    <span>岗位信息</span>
                </div> <br />
                <div class="Big-title">
                    <div class="littil-title">
                        岗位标签
                    </div>
                    <div class="search-box">
                        <button type="button" class="mybutton" onclick="ShowGwAdd()">添加标签</button>
                    </div>
                </div>
            </div>
        </div>
        <!--这是标题栏结束-->
        <div>
            <!--这是表格开始-->
            <c:forEach varStatus="i" var="dataList" items="${dataList}">
            <table  class="pure-table pure-table-bordered left">
                <!--<c:if test="${dataList.jtype == true}">
                <tr>
                    <td width="90px">开发岗：</td>
                    <td><c:forEach varStatus="i" var="job" items="${jobList}">@${job.jname}&nbsp;&nbsp;</c:forEach></td>
                </tr>
                </c:if>
                <c:if test="${dataList.jtype == false}">
                <tr>
                    <td>非开发岗：</td>
                    <td><c:forEach varStatus="i" var="job" items="${jobList}">@${job.jname}&nbsp;&nbsp;</c:forEach></td>
                </tr>
                </c:if>-->
                <c:if test="${dataList.jtype == true}">
                <tr>
                    <td width="90px">开发岗：</td>
                    <td>@${dataList.jname}&nbsp;&nbsp;</td>
                </tr>
                </c:if>
                <c:if test="${dataList.jtype == false}">
                <tr>
                    <td width="90px">非开发岗：</td>
                    <td>@${dataList.jname}&nbsp;&nbsp;</td>
                </tr>
                </c:if>
            </table>
            </c:forEach>
            <div class="table-slipline"></div>
            <!--这是表格结束-->
        </div>
        <div id="GangWeiAdd">
            <div class="tab-close">
                <button class="mybutton" type="button" onclick="HideGwAdd()">取消</button>
            </div>
            <p>添加岗位</p>
            <form action="/job/addJob">
            <table  class="pure-table pure-table-bordered left">
                <tr>
                    <td>方向：</td>
                    <td>
                        <select id="jtype" name="jtype" style="width: 80px;">
                            <option value="1">开发岗</option>
                            <option value="0">非开发岗</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>岗位：</td>
                    <td>
                        <input id="jname" type="text" name="jname" required="required" />
                    </td>
                </tr>
                <tr>
                    <td>备注：</td>
                    <td>
                        <input id="jinfo" type="text" name="jinfo" required="required">
                    </td>
                </tr>
                <tr>
                    <td colspan="2" style="text-align: center;">
                        <button class="mybutton" type="submit" style="width: 200px;">保存</button>
                    </td>
                </tr>
            </table>
            </form>
        </div>
        <div id="zhezhaobg"></div>
    </div>
</div>
</body>
</html>

