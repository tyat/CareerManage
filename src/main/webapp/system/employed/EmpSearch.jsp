<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 2016/11/2
  Time: 15:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="com.*" isELIgnored="false" %>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" type="text/css" href="../../css/default.css"/>
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
                            <select id="searchType" name="searchType" style="width:120px;height: 30px;">
                                <option value="sname">按姓名</option>
                                <option value="jname">按岗位</option>
                                <option value="cname">按企业</option>
                                <option value="sgrade">按年级</option>>
                            </select>
                            <input type="text" id="searchtext" name="searchtext" style="width:120px;height: 30px;" onfocus="javascript:if(this.value=='请输入字符...')this.value='';" required="required" placeholder="请输入字符..."/>
                            <button class="mybutton" type="button" onclick="this.form.submit()"> <span>搜索</span> </button>
                            <button class="mybutton" type="button" onclick="JavaScript :history.back(-1)">
                                返回上一页
                            </button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <div class="table-bar">
            <ul><c:if test="${list.jtype == true}">
                <li onclick="ShowKaifaEmpStu()"> 开发岗 </li></c:if>
                <c:if test="${list.jtype == false}">
                    <li onclick="ShowFeikaifaEmpStu()"> 非开发岗 </li></c:if>
            </ul>
        </div>
        <c:forEach varStatus="i" var="list" items="${listdata}">
            <c:if test="${list.jtype == true}">
                <div id="kaifastu">
                    <!--这是一条记录开始-->
                    <table  class="pure-table pure-table-bordered left">
                        <tr>
                            <td rowspan="6" width="70px"><button class="mybutton" type="button" onclick="location='/grade/findStudentDetail?sid=${list.sid}'">${list.sname}</button></td>
                            <td width="80px">班级：</td>
                            <td><a onclick="ShowEmpStuBySclass(${list.sgrade},${list.sclass})">${list.spro}${list.sclass}班</a></td>
                            <td width="60px">年级：</td>
                            <td width="120px">${list.sgrade}级</td>
                            <td width="60px">性别：</td>
                            <td>
                                <c:if test="${list.ssex==false}">
                                    男
                                </c:if>
                                <c:if test="${list.ssex==true}">
                                    女
                                </c:if>
                            </td>
                            <td rowspan="3">
                                <button class="mybutton" type="button" onclick="location='/emp/forUpdateEmp?sid=${list.sid}'">编辑</button>
                            </td>
                        </tr>
                        <tr>
                            <td >就业企业：</td>
                            <td colspan="5"><button class="mybutton" type="button" onclick="findEmpStuByCid(${list.cid})">${list.cname}</button></td>
                        </tr>
                        <tr>
                            <td>岗位:</td>
                            <td colspan="5">
                                <button class="mybutton" type="button" onclick="ShowEmpStuByJname(${list.jid})">@${list.jname}</button>
                            </td>
                        </tr>
                        <tr>
                            <td>实习日期：</td>
                            <td colspan="2">${fn:substring(list.etime,0,10)}</td>
                            <td>实习补贴：</td>
                            <td colspan="2">${list.esalary}元/月</td>
                            <td rowspan="4">
                                <button class="mybutton" type="button" onclick="AreYouSouremp(${list.eid})">删除</button>
                            </td>
                        </tr>
                        <tr>
                            <td>推荐人：</td>
                            <td colspan="2">${list.urname}</td>
                            <td >是否网签：</td>
                            <td colspan="2">
                                <c:if test="${list.ewq==false}">
                                    否
                                </c:if>
                                <c:if test="${list.ewq==true}">
                                    是
                                </c:if>
                            </td>
                        </tr>
                        <tr>
                            <td>备注:</td>
                            <td colspan="5">${list.einfo}</td>
                        </tr>
                    </table>
                    <div class="table-slipline"></div>
                    <!--这是一条记录结束-->
                </div>
            </c:if>
            <c:if test="${list.jtype == false}">
                <div id="feikaifastu">
                    <!--这是一条记录开始-->
                    <table  class="pure-table pure-table-bordered left">
                        <tr>
                            <td rowspan="6" width="70px"><button class="mybutton" type="button" onclick="location='/grade/findStudetDetail?sid=${list.sid}'">${list.sname}</button></td>
                            <td width="80px">班级：</td>
                            <td><a onclick="ShowEmpStuBySclass(${list.sgrade},${list.sclass})">${list.spro}${list.sclass}班</a></td>
                            <td width="60px">年级：</td>
                            <td width="120px">${list.sgrade}级</td>
                            <td width="60px">性别：</td>
                            <td>
                                <c:if test="${list.ssex==false}">
                                    男
                                </c:if>
                                <c:if test="${list.ssex==true}">
                                    女
                                </c:if>
                            </td>
                            <td rowspan="3">
                                <button class="mybutton" type="button" onclick="location='/emp/forUpdateEmp?sid=${list.sid}'">编辑</button>
                            </td>
                        </tr>
                        <tr>
                            <td >就业企业：</td>
                            <td colspan="5"><button class="mybutton" type="button" onclick="ShowCompByCid(${list.cid})">${list.cname}</button></td>
                        </tr>
                        <tr>
                            <td>岗位:</td>
                            <td colspan="5">
                                <div class="gangwei-box">
                                    <a href="#">${list.jname}</a>&nbsp;
                                </div>
                            </td>

                        </tr>
                        <tr>
                            <td>实习日期：</td>
                            <td colspan="2">${fn:substring(list.etime,0,10)}</td>
                            <td>实习补贴：</td>
                            <td colspan="2">${list.esalary}元/月</td>
                            <td rowspan="4">
                                <button class="mybutton" type="button" onclick="AreYouSouremp(${list.eid})">删除</button>
                            </td>
                        </tr>
                        <tr>
                            <td>推荐人：</td>
                            <td colspan="2">${list.urname}</td>
                            <td >是否网签：</td>
                            <td colspan="2">
                                <c:if test="${list.ewq==false}">
                                    否
                                </c:if>
                                <c:if test="${list.ewq==true}">
                                    是
                                </c:if>
                            </td>
                        </tr>
                        <tr>
                            <td>备注:</td>
                            <td colspan="5">${list.einfo}</td>
                        </tr>
                    </table>
                    <div class="table-slipline"></div>
                    <!--这是一条记录结束-->
                </div>
            </c:if>
        </c:forEach>
    </div>

    <div class="button-footer">

        <div class="right-button-footer">
            <div id="Page">

            </div>
        </div>
        <div class="left-button-footer">
            <%--<button class="mybutton" type="button" onclick="alert('弹出下载框')"> <span>导出数据</span></button>--%>
        </div>
    </div>
</div>


</body>
</html>

