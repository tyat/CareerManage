<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 2016/11/15
  Time: 15:29
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
    <script type="text/javascript" src="../../js/showele.js" ></script>

</head>
<body onload="Activeli()">
<div class="table-box">
    <div class="table-content">
        <div class="table-head">
            <div class="table-address">
                <div style="float: left;">
                    <span>信息管理</span><div class="left-arrow"></div>
                    <span>学生信息</span><div class="left-arrow"></div>
                    <span>2013级</span></div><br />
                <div class="Big-title">
                    <div class="littil-title">
                        学生信息
                    </div>
                    <div class="search-box">
                        <button class="mybutton" type="button" onclick="JavaScript :history.back(-1)">
                            返回上一页
                        </button>
                    </div>
                </div>
            </div>
        </div>
        <div>
            <!--这是一条记录开始-->
            <c:forEach varStatus="i" items="${dataList}" var="dataList" >
            <table  class="pure-table pure-table-bordered" style="text-align: left;">
                <tr>
                    <td>姓名：</td>
                    <td>${dataList.sname}</td>
                    <td>年级：</td>
                    <td>${dataList.sgrade}级</td>
                    <td>性别：</td>
                    <td><c:if test="${dataList.ssex==false}">
                        <a>男</a>
                    </c:if>
                        <c:if test="${dataList.ssex==true}">
                            <a>女</a>
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <td>学号：</td>
                    <td>${dataList.sno}</td>
                    <td>专业：</td>
                    <td>${dataList.spro}</td>
                    <td>班级：</td>
                    <td>${dataList.sclass}班</td>
                </tr>
                <tr>
                    <td>联系方式：</td>
                    <td>${dataList.sphone}</td>
                    <td>邮箱：</td>
                    <td>${dataList.semail}</td>
                    <td>身份证号：</td>
                    <td>${dataList.scode}</td>

                </tr>
                <tr>
                    <td>能力认定：</td>
                    <td>${dataList.smark}星</td>
                    <td>就业企业：</td>
                    <td>${dataList.cname}</td>
                    <td>岗位：</td>
                    <td>${dataList.jname}</td>
                </tr>
            </table>
            </c:forEach>
            <div class="table-slipline"></div>
            <!--这是一条记录结束-->

        </div>
    </div>
    <div class="button-footer">
        <div class="left-button-footer">
            <button class="mybutton" type="button" onclick="alert('弹出下载框')"> <span>导出数据</span></button>

        </div>
    </div>
</div>
<div id="showupload-div">
    <div class="tab-close">
        <button class="mybutton" type="button" onclick="HideUpload()">关闭</button>
    </div>
    <iframe src="../tools/UploadExcel.html" width="810px" height="340px" frameborder="0"></iframe>
</div>

<div id="StuUpdate">
    <div class="tab-close">
        <button class="mybutton" type="button" onclick="HideStuUpdate()">取消</button>
    </div>
    <p>修改学生信息：</p>
    <table  class="pure-table pure-table-bordered left">
        <tr>
            <td>姓名：</td>
            <td>
                <input type="text" value="王新" disabled="disabled" />
            </td>
        </tr>
        <tr>
            <td>联系电话：</td>
            <td>
                <input type="text" value="15953102060" />
            </td>
        </tr>
        <tr>
            <td>邮箱：</td>
            <td>
                <input type="text" value="810826473@qq.com" />
            </td>
        </tr>
        <tr>
            <td colspan="2" style="text-align: center;">
                <button class="mybutton" type="button" style="width: 200px;">保存</button>
            </td>
        </tr>
    </table>
</div>
<div id="zhezhaobg"></div>
</body>
</html>
