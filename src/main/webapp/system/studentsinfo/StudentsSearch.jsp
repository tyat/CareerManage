<%--
  Created by IntelliJ IDEA.
  User: w
  Date: 2016/10/26
  Time: 16:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" type="text/css" href="../../css/default.css"/>
    <script src="../../js/showele.js" type="text/javascript" charset="utf-8"></script>
    <link rel="stylesheet" href="../../css/icon.css" />
    <script type="text/javascript" src="../../js/showele.js" ></script>
    <script type="text/javascript">
        function postwith(url, param) {
            var myForm = document.createElement("form");
            myForm.method = "post";
            myForm.action = url;
            for ( var k in param){
                var myInput = document.createElement("input");
                myInput.setAttribute("name", k);
                myInput.setAttribute("value", param[k]);
                myForm.appendChild(myInput);
            }
            document.body.appendChild(myForm);
            myForm.submit();
            document.body.removeChild(myForm);
        }
    </script>
</head>
<body onload="Activeli()">
<div class="table-box">
    <div class="table-content">
        <div class="table-head">
            <div class="table-address">
                <div style="float: left;">
                    <span>信息管理</span>
                    <div class="left-arrow"></div>
                    <span><a href="selecteAllC.html">学生列表</a></span>
                    <div class="left-arrow"></div>
                    <span>搜索结果</span>
                </div><br />
                <div class="Big-title">
                    <div class="littil-title">
                        学生列表
                    </div>
                    <div class="search-box">
                        <form action="/student/query" method="post">
                            <select name="type">
                                <option value="0">按年级</option>
                                <option value="1">按专业</option>
                                <option value="2">按姓名</option>
                            </select>
                            <input type="text" name="searchtext"  placeholder="请输入……"/>
                            <button class="mybutton" type="button" onclick="this.form.submit()"> <span>搜索</span> </button>
                            <button class="mybutton" type="button" onclick="JavaScript :history.back(-1)">
                                返回上一页
                            </button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <div>
            <c:if test="${studentList!=null}">
                <!--这是表格开始-->
                <table  class="pure-table pure-table-bordered" style="text-align: left;">
                    <tr>
                        <td>姓名</td>
                        <td>性别</td>
                        <td>年级</td>
                        <td>班级</td>
                    </tr>
                    <c:forEach var="student" items="${studentList}">
                    <!--这是一条记录开始-->
                    <tr>
                        <td>
                            <a href="/student/findBySid?sid=${student.sid}">${student.sname}</a>
                        </td>
                        <td>
                            <c:if test="${!(student.ssex)}">
                                男
                            </c:if>
                            <c:if test="${student.ssex}">
                                女
                            </c:if>
                        </td>
                        <td><a href="/student/findBySgrade?sgrade=${student.sgrade}">${student.sgrade}级</a></td>
                        <td><a href="javascript:postwith('/student/findBySclass',{'spro':'${student.spro}','sclass':'${student.sclass}'})">${student.spro}${student.sclass}班</a></td>
                    </tr>
                    <!--这是一条记录结束-->
                    </c:forEach>

                </table>
                <div class="table-slipline"></div>
                <!--这是表格结束-->
            </c:if>
            <c:if test="${studentList==null}">
                此处没有学生数据
            </c:if>
        </div>
    </div>
    <div class="button-footer">

        <div class="right-button-footer">
            <div id="Page">
                <a href="#">«</a>
                <span>1</span>
                <a href="#">2</a>
                <a href="#">3</a>
                <a href="#">4</a>
                <a href="#">5</a>
                <a href="#">6</a>
                <a href="#">»</a>
            </div>
        </div>
        <div class="left-button-footer">
            <button class="mybutton" type="button" onclick="alert('弹出下载框！')"> <span>导出数据</span></button>
        </div>
    </div>
</div>
</body>
</html>
