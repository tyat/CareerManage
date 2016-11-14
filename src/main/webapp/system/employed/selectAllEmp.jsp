<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 2016/11/2
  Time: 15:01
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
    <link rel="stylesheet" type="text/css" href="../../css/bootstrap.css"/>
    <script src="../../js/showele.js" type="text/javascript" charset="utf-8"></script>
    <link rel="stylesheet" href="../../css/icon.css" />
    <script type="text/javascript" src="../../js/showele.js" ></script>
    <script type="text/javascript" src="../../js/upload.js" ></script>

</head>
<body onload="Activeli()">
<div class="table-box">
    <div class="table-content">
        <div class="table-head">
            <div class="table-address">
                <div style="float: left;">
                    <span>信息管理</span><div class="left-arrow"></div>
                    <span>就业生信息</span><div class="left-arrow"></div>
                </div><br />
                <div class="Big-title">
                    <div class="littil-title">
                        就业生信息
                    </div>
                    <div class="search-box">
                        <form action="/emp/findByEmp">
                        <select id="searchType" name="searchType" style="width: 80px;">
                            <option value="sname">按姓名</option>
                            <option value="jname">按岗位</option>
                            <option value="sgrade">按年级</option>
                            <option value="cname">按企业</option>
                        </select>
                        <input type="text" id="searchtext" name="searchtext"  value="输入字符"/>
                        <button class="mybutton" type="button" onclick="this.form.submit()"> <span>搜索</span> </button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <div class="table-bar">
            <ul>
                <li class="active-li" onclick="ShowAllEmpStu()"> 全部岗位 </li>
                <li onclick="ShowKaifaEmpStu()"> 开发岗 </li>
                <li onclick="ShowFeikaifaEmpStu()"> 非开发岗 </li>
            </ul>
        </div>

        <div id="kaifastu">
            <c:forEach varStatus="i" var="emplist" items="${empList}">
            <c:if test="${emplist.jtype == true}">
            <!--这是一条记录开始-->
            <table  class="pure-table pure-table-bordered left">
                <tr>
                    <td rowspan="6" width="70px"><a href="../studentsinfo/StudentInfo.html">${emplist.sname}</a></td>
                    <td width="80px">班级：</td>
                    <td><a href="EmpSearch.html">${emplist.spro}${emplist.sclass}班</a></td>
                    <td width="60px">年级：</td>
                    <td width="120px"><a href="EmpSearch.html">${emplist.sgrade}级</a></td>
                    <td width="60px">性别：</td>
                    <td><c:if test="${emplist.ssex==false}">
                        <a>男</a>
                        </c:if>
                        <c:if test="${emplist.ssex==true}">
                            <a>女</a>
                        </c:if>
                    </td>
                    <td rowspan="3">
                        <button class="mybutton" type="button" onclick="location='EmpUpdate.html'">编辑</button>
                    </td>
                </tr>
                <tr>
                    <td >就业企业：</td>
                    <td colspan="5"><button class="mybutton" type="button" onclick="ShowCompByCid(${emplist.cid})">${emplist.cname}</button></td>
                </tr>
                <tr>
                    <td>岗位:</td>
                    <td colspan="5">
                        <div class="gangwei-box">
                            <a href="#">@${emplist.jname}</a>&nbsp;
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>实习日期：</td>
                    <td colspan="2">${emplist.etime}</td>
                    <td>实习补贴：</td>
                    <td colspan="2">${emplist.esalary}元/月</td>
                    <td rowspan="4">
                        <button class="mybutton" type="button" onclick="AreYouSouremp(${emplist.eid})">删除</button>
                    </td>
                </tr>
                <tr>
                    <td>离职日期：</td>
                    <td colspan="2">${emplist.eleave}</td>
                    <td>离职原因：</td>
                    <td colspan="2">${emplist.ereason}</td>
                </tr>
                <tr>
                    <td>推荐人：</td>
                    <td colspan="2">${emplist.uname}</td>
                    <td >是否网签：</td>
                    <td colspan="2">
                        <c:if test="${emplist.ewq==false}">
                            <a>否</a>
                        </c:if>
                        <c:if test="${emplist.ewq==true}">
                            <a>是</a>
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <td>备注:</td>
                    <td colspan="5">${emplist.einfo}</td>
                </tr>
            </table>
            <div class="table-slipline"></div>
            <!--这是一条记录结束-->
            </c:if>
            </c:forEach>
        </div>
        <div id="feikaifastu">
            <c:forEach varStatus="i" var="emplist" items="${empList}">
            <c:if test="${emplist.jtype == false}">
            <!--这是一条记录开始-->
            <table  class="pure-table pure-table-bordered left">
                <tr>
                    <td rowspan="6" width="70px"><a href="../studentsinfo/StudentInfo.html">${emplist.sname}</a></td>
                    <td width="80px">班级：</td>
                    <td><a href="EmpSearch.html">${emplist.spro}${emplist.sclass}班</a></td>
                    <td width="60px">年级：</td>
                    <td width="120px"><a href="EmpSearch.html">${emplist.sgrade}级</a></td>
                    <td width="60px">性别：</td>
                    <td>
                        <c:if test="${emplist.ssex==false}">
                            <a>男</a>
                        </c:if>
                        <c:if test="${emplist.ssex==true}">
                            <a>女</a>
                        </c:if>
                    </td>
                    <td rowspan="3">
                        <button class="mybutton" type="button" onclick="location='EmpUpdate.html'">编辑</button>
                    </td>
                </tr>
                <tr>
                    <td >就业企业：</td>
                    <td colspan="5"><button class="mybutton" type="button" onclick="ShowCompByCid(${emplist.cid})">${emplist.cname}</button></td>
                </tr>
                <tr>
                    <td>岗位:</td>
                    <td colspan="5">
                        <div class="gangwei-box">
                            <a href="#">${emplist.jname}</a>&nbsp;
                        </div>
                    </td>

                </tr>
                <tr>
                    <td>实习日期：</td>
                    <td colspan="2">${emplist.etime}</td>
                    <td>实习补贴：</td>
                    <td colspan="2">${emplist.esalary}元/月</td>
                    <td rowspan="4">
                        <button class="mybutton" type="button" onclick="AreYouSouremp(${emplist.eid})">删除</button>
                    </td>
                </tr>
                <tr>
                    <td>离职日期：</td>
                    <td colspan="2">${emplist.eleave}</td>
                    <td>离职原因：</td>
                    <td colspan="2">${emplist.ereason}</td>
                </tr>
                <tr>
                    <td>推荐人：</td>
                    <td colspan="2">${emplist.uname}</td>
                    <td >是否网签：</td>
                    <td colspan="2">
                        <c:if test="${emplist.ewq==false}">
                            <a>否</a>
                        </c:if>
                        <c:if test="${emplist.ewq==true}">
                            <a>是</a>
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <td>备注:</td>
                    <td colspan="5">${emplist.einfo}</td>
                </tr>
            </table>
            <div class="table-slipline"></div>
            <!--这是一条记录结束-->
            </c:if>
            </c:forEach>
        </div>
    </div>

    <div>
        <div class="pagination pagination-centered">
            <ul>
                ${pageCode }
            </ul>
        </div>
        <div class="left-button-footer">
            <button class="mybutton" type="button" onclick="alert('弹出下载框')"> <span>导出数据</span></button>
            <button class="mybutton" type="button" onclick="ShowUpload()"> <span>导入数据</span></button>
            <button class="mybutton" type="button" onclick="alert('弹出下载框')"> <span>下载Excel模板</span></button>
        </div>
    </div>
</div>


</body>
</html>

