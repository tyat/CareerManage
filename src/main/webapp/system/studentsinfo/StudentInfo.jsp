<%--
  Created by IntelliJ IDEA.
  User: w
  Date: 2016/10/26
  Time: 16:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
    <script type="text/javascript" src="../../js/showele.js" ></script>
    <link rel="stylesheet" type="text/css" href="../../css/default.css"/>
    <link rel="stylesheet" href="../../css/icon.css" />


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
            <table  class="pure-table pure-table-bordered" style="text-align: left;">
                <tr>
                    <td>姓名：</td>
                    <td>${student.sname}</td>
                    <td>年级：</td>
                    <td>${student.sgrade}</td>
                    <td>性别：</td>
                    <td>
                        <c:if test="${!(student.ssex)}">
                            男
                        </c:if>
                        <c:if test="${student.ssex}">
                            女
                        </c:if>
                    </td>
                    <td rowspan="3">
                        <button class="mybutton" type="button" onclick="ShowStuUpdate()" >编辑</button>
                    </td>
                </tr>
                <tr>
                    <td>学号：</td>
                    <td>${student.sno}</td>
                    <td>班级：</td>
                    <td>${student.spro}${student.sclass}班</td>
                    <td>出生年份：</td>
                    <td>${student.sbirth}</td>
                </tr>
                <tr>
                    <td>联系方式：</td>
                    <td>${student.sphone}</td>
                    <td>邮箱：</td>
                    <td>${student.semail}</td>
                    <td>身份证号：</td>
                    <td>${student.scode}</td>

                </tr>
                <tr>
                    <td>是否已就业：</td>
                    <td>
                       <c:if test="${isemp}">
                            是
                       </c:if>
                        <c:if test="${!isemp}">
                            否
                        </c:if>
                    </td>
                    <td>就业企业：</td>
                    <td>
                    <a href="../company/CompInfo2.html">
                        <c:if test="${isemp}">
                            ${student.cname}
                        </c:if>
                        <c:if test="${!isemp}">
                            无
                        </c:if>
                    </a>
                    </td>
                    <td>岗位：</td>
                    <td>
                    <a href="#">
                        <c:if test="${isemp}">
                             ${student.jname}
                        </c:if>
                        <c:if test="${!isemp}">
                             无
                        </c:if>
                    </a>
                    </td>
                    <td rowspan="2">
                        <button class="mybutton" type="button" onclick="AreYouSour(${student.sid})"  >删除</button>
                    </td>
                </tr>
                <tr>
                    <td>参加的面试：</td>
                    <td colspan="2">
                        <button class="mybutton" type="button" onclick="location='../meeting/ThisStudentMeets.html'"  >查看详情</button>
                    </td>
                    <td>学生详细信息：</td>
                    <td colspan="2">
                        <button class="mybutton" type="button" onclick="location='/grade/findStudentDetail?sid=${student.sid}'"  >查看详情</button>
                    </td>
                </tr>
            </table>
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
    <form action="/student/updateStudent" method="get">
        <table  class="pure-table pure-table-bordered left">
            <tr>
                <td>姓名：</td>
                <td>
                    <input type="text" value="${student.sname}" disabled="disabled" />
                </td>
            </tr>
            <tr>
                <td>联系电话：</td>
                <td>
                    <input type="text" value="${student.sphone}" />
                </td>
            </tr>
            <tr>
                <td>邮箱：</td>
                <td>
                    <input type="text" value="${student.semail}" />
                </td>
            </tr>
            <tr>
                <td colspan="2" style="text-align: center;">
                    <button class="mybutton" type="button" style="width: 200px; " onclick="this.form.submit()">保存</button>
                </td>
            </tr>
        </table>
    </form>
</div>
<div id="zhezhaobg"></div>
</body>
</html>

