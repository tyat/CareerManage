<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: TianYu
  Date: 2016/11/12
  Time: 10:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="../../css/default.css"/>
    <script src="../../js/showele.js" type="text/javascript" charset="utf-8"></script>
    <link rel="stylesheet" href="../../css/icon.css" />
    <script type="text/javascript" src="../../js/valid.js" ></script>
    <script type="text/javascript" src="../../js/Cityjs/jquery.js" ></script>
    <script type="text/javascript">

        function area(){
            document.getElementById("area").submit();
        }

        function student(){
            document.getElementById("student").submit();
        }

        function grade(){
            document.getElementById("grade").submit();
        }

        function company(){
            document.getElementById("company").submit();
        }

        function empstu(){
            document.getElementById("empstu").submit();
        }

        function unempstu(){
            document.getElementById("unempstu").submit();
        }
    </script>
</head>
<body onload="init()">
<div class="table-box">
    <span style="color: red" ><h3>${file}</h3></span><br>
    <div class="table-content">
        <!--这是标题栏-->
        <div class="table-head">
            <div class="table-address">
                <div style="float: left;">
                    <span>管理中心</span><div class="left-arrow"></div><span>数据初始化</span>
                </div> <br />
                <div class="Big-title">
                    <div class="littil-title">
                        Excel初始数据导入
                    </div>
                </div>
            </div>
        </div>
        <!--这是标题栏结束-->
        <div>
            <!--这是表格开始-->
            <table  class="pure-table pure-table-bordered left">
                <tr>
                    <td>地区初始化</td>
                    <td>
                        <button type="submit" class="mybutton" value="Submit" onclick="window.open('/upload/Area(ok).xls')">下载模板</button>
                        </td>
                    <td>
                       <form action="/area/inputArea" method="post" enctype="multipart/form-data" id="area">
                          <input type="file" name="file" />
                          <button type="submit" class="mybutton" value="Submit" onclick="area()">开始导入</button>
                       </form>
                    </td>
                </tr>
                <tr>
                    <td>学生初始化</td>
                    <td>
                        <button type="submit" class="mybutton" value="Submit" onclick="window.open('/upload/Student(ok).xls')">下载模板</button>
                    </td>
                    <td>
                        <form action="/area/inputStudent" method="post" enctype="multipart/form-data" id="student">
                            <input type="file" name="file" />
                            <button type="submit" class="mybutton" value="Submit" onclick="student()">开始导入</button>
                        </form>
                    </td>
                </tr>
                <tr>
                    <td>公司初始化</td>
                    <td>
                        <button type="submit" class="mybutton" value="Submit" onclick="window.open('/upload/Company(ok).xls')">下载模板</button>
                    </td>
                    <td>
                        <form action="/area/inputCompany" method="post" enctype="multipart/form-data" id="company">
                            <input type="file" name="file" />
                            <button type="submit" class="mybutton" value="Submit" onclick="company()">开始导入</button>
                        </form>
                    </td>
                </tr>
                <tr>
                    <td>学生成绩导入</td>
                    <td>
                        <button type="submit" class="mybutton" value="Submit" onclick="window.open('/upload/Grade(ok).xls')">下载模板</button>
                    </td>
                    <td>
                        <form action="/area/inputGrade" method="post" enctype="multipart/form-data" id="grade">
                            <input type="file" name="file" />
                            <button type="submit" class="mybutton" value="Submit" onclick="student()">开始导入</button>
                        </form>
                    </td>
                </tr>
                <tr>
                    <td>已就业学生导入</td>
                    <td>
                        <button type="submit" class="mybutton" value="Submit" onclick="window.open('/upload/Emp(ok).xls')">下载模板</button>
                    </td>
                    <td>
                        <form action="/area/inputEmp" method="post" enctype="multipart/form-data" id="empstu">
                            <input type="file" name="file" />
                            <button type="submit" class="mybutton" value="Submit" onclick="empstu()">开始导入</button>
                        </form>
                    </td>
                </tr>
                <tr>
                    <td>未就业学生导入</td>
                    <td>
                        <button type="submit" class="mybutton" value="Submit" onclick="window.open('/upload/Unemp(ok).xls')">下载模板</button>
                    </td>
                    <td>
                        <form action="/area/inputUnemp" method="post" enctype="multipart/form-data" id="unempstu">
                            <input type="file" name="file" />
                            <button type="submit" class="mybutton" value="Submit" onclick="unempstu()">开始导入</button>
                        </form>
                    </td>
                </tr>
            </table>
            <div class="table-slipline"></div>
            <!--这是表格结束-->
        </div>
        <div id="zhezhaobg2"></div>
    </div>
</div>
<script type="text/javascript">
function init() {
<c:if test="${!empty file}">
    alert(${file});
</c:if>
alert("File"+${file});
}
</script>
</body>
</html>
