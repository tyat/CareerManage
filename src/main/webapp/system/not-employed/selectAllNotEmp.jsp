<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 2016/11/3
  Time: 9:44
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
                    <span>未就业生信息</span><div class="left-arrow"></div>
                    <span>2013级</span>
                </div><br />
                <div class="Big-title">
                    <div class="littil-title">
                        未就业生信息
                    </div>
                    <div class="search-box">
                        <form action="/unemp/findByUnEmp">
                        <select id="searchType" name="searchType" style="width: 80px;">
                            <option value="sclass">按班级</option>
                            <option value="sname">按姓名</option>
                            <option value="jname">按岗位</option>
                            <option value="uesalary">按期望薪资</option>
                        </select>
                        <input type="text" name="searchtext"  value="输入字符"/>
                        <button class="mybutton" type="button" onclick="this.form.submit()"> <span>搜索</span> </button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <div class="table-bar not-emp">
            <ul>
                <li class="active-li" onclick="ShowAllUnEmp()"> 全部动向 </li>
                <li onclick="ShowZhunBei()">准备就业</li>
                <li onclick="ShowKaoYan()">其他</li>
            </ul>
        </div>
        <c:forEach varStatus="i" var="list" items="${UnempList}">
        <div id="allUnEmp-table">
            <!--准备就业的表这是一条记录开始-->
            <table  class="pure-table pure-table-bordered left">
                <tr>
                    <td rowspan="5"><a href="../studentsinfo/StudentsSearch.html">${list.sname}</a></td>
                    <td width="100px">班级：</td>
                    <td width="100px">
                        <a href="NotEmpSearch.html">${list.spro}${list.sclass}班</a>
                    </td>
                    <td width="50px">年级：</td>
                    <td>
                        <a href="NotEmpSearch.html">${list.sgrade}级</a>
                    </td>
                    <td width="50px">性别：</td>
                    <td> <c:if test="${list.ssex==false}">
                        <a>男</a>
                    </c:if>
                        <c:if test="${list.ssex==true}">
                            <a>女</a>
                        </c:if>
                    </td>
                    <td>操作</td>
                </tr>
                <tr>
                    <td>学生动向：</td>
                    <td colspan="5">${list.dname}</td>
                    <td rowspan="4">
                        <button class="mybutton" type="button" onclick="location='/direction/selectAllDirection2?sid=${list.sid}'">编辑</button>
                        <br>
                        <br>
                        <button class="mybutton" type="button" onclick="AreYouSourUnemp(${list.ueid})">删除</button>
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
            <!--准备就业的表这是一条记录结束-->
        </div>
        <div id="ZhunBei-table">
            <!--这是一条记录开始-->
            <table  class="pure-table pure-table-bordered left">
                <tr>
                    <td rowspan="4">
                        <a href="../studentsinfo/StudentsSearch.html">${list.sname}</a>
                    </td>
                    <td width="100px">班级：</td>
                    <td width="100px">
                        <a href="NotEmpSearch.html">${list.spro}${list.sclass}班</a>
                    </td>
                    <td width="50px">年级：</td>
                    <td>
                        <a href="NotEmpSearch.html">${list.sgrade}级</a>
                    </td>
                    <td width="50px">性别：</td>
                    <td> <c:if test="${list.ssex==false}">
                        <a>男</a>
                    </c:if>
                        <c:if test="${list.ssex==true}">
                            <a>女</a>
                        </c:if>
                    </td>
                    <td>操作</td>
                </tr>
                <tr>
                    <td>期望岗位：</td>
                    <td colspan="5">${list.jname}</td>
                    <td rowspan="3">
                        <button class="mybutton" type="button" onclick="location='/direction/selectAllDirection2?sid=${list.sid}'">编辑</button>
                        <br>
                        <br>
                        <button class="mybutton" type="button" onclick="AreYouSourUnemp(${list.ueid})">删除</button>
                    </td>
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
        </div>
        <div id="KaoYan-table">
            <!--这是一条记录开始-->
            <table  class="pure-table pure-table-bordered left">
                <tr>
                    <td rowspan="4">${list.sname}</td>
                    <td width="100px">班级：</td>
                    <td width="100px">${list.spro}${list.sclass}班</td>
                    <td width="50px">年级：</td>
                    <td>${list.sgrade}级</td>
                    <td width="50px">性别：</td>
                    <td> <c:if test="${list.ssex==false}">
                        <a>男</a>
                    </c:if>
                        <c:if test="${list.ssex==true}">
                            <a>女</a>
                        </c:if>
                    </td>
                    <td>操作</td>
                </tr>
                <tr>
                    <td>期望院校：</td>
                    <td colspan="5">${list.ueschool}</td>
                    <td rowspan="3">
                        <button class="mybutton" type="button" onclick="location='/direction/selectAllDirection2?sid=${list.sid}'">编辑</button>
                        <br>
                        <br>
                        <button class="mybutton" type="button" onclick="AreYouSourUnemp(${list.ueid})">删除</button>
                    </td>
                </tr>
                <tr>
                    <td>期望专业:</td>
                    <td colspan="5">${list.uemajor}</td>
                </tr>
                <tr>
                    <td>考研结果:</td>
                    <td colspan="5">${list.uesuccess}</td>
                </tr>
            </table>
            <div class="table-slipline"></div>
            <!--这是一条记录结束-->
        </div>
    </c:forEach>
    </div>
    <div class="button-footer">
        <div class="right-button-footer">
            <div id="Page">
                <a href="#"></a><span>1</span><a href="#">2</a><a href="#">3</a><a href="#">4</a><a href="#">5</a><a href="#">6</a><a href="#">»</a>
            </div>
        </div>
        <div class="left-button-footer">
            <button type="submit" class="mybutton" value="Submit" onclick="window.open('/unemp/outputUnemp')">导出数据</button>
        </div>
    </div>
</div>

</body>
</html>
