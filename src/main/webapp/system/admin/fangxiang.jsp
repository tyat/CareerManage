<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 2016/11/5
  Time: 13:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.List" %>
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
                    <span>管理中心</span><div class="left-arrow"></div><span>未就业方向管理</span>
                </div> <br />
                <div class="Big-title">
                    <div class="littil-title">
                        未就业方向
                    </div>
                    <div class="search-box">
                        <button type="button" class="mybutton" onclick="ShowFXAdd() ">添加</button>
                    </div>
                </div>
            </div>
        </div>
        <!--这是标题栏结束-->
        <div>

            <!--这是表格开始-->
            <table  class="pure-table pure-table-bordered left">
                <tr>
                    <td width="200px">未就业方向</td>
                    <td>操作</td>
                </tr>
                <c:forEach varStatus="i" var="DirList" items="${DirList}">
                <tr>
                    <td>${DirList.dname}</td>
                    <td>
                        <button type="button" class="mybutton" onclick="AreYouSourDir(${DirList.did})">删除</button>
                    </td>
                </tr>
                </c:forEach>
            </table>
            <div class="table-slipline"></div>
            <!--这是表格结束-->
        </div>
        <div id="FangXiangAdd">
            <div class="tab-close">
                <button class="mybutton" type="button" onclick="HideFXAdd()">取消</button>
            </div>
            <p>添加未就业方向</p>
            <form action="/direction/addDirection">
            <table  class="pure-table pure-table-bordered left">
                <tr>
                    <td>未就业方向：</td>
                    <td>
                        <input id="direction" type="text" name="direction" required="required" />
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

