<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 2016/11/17
  Time: 20:51
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
</head>
<body>
<div class="table-box">
    <div class="table-content">
        <!--这是标题栏-->
        <div class="table-head">
            <div class="table-address">
                <div style="float: left;">
                    <span>信息管理</span><div class="left-arrow"></div>
                    <span><a href="selecteAllC.html">企业管理</a></span><div class="left-arrow"></div>
                    <span>在岗学生名单</span>
                </div> <br />
                <div class="Big-title">
                    <div class="littil-title">
                        在岗学生名单
                    </div>
                    <div class="search-box">
                        <form action="/company/findByType">
                            <select id="searchType" name="searchType" style="width:120px;height: 30px;">
                                <option value="sname">按姓名</option>
                                <option value="jname">按岗位</option>
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
        <!--这是标题栏结束-->
        <div>
            <table  class="pure-table pure-table-bordered CompInfo1">
                <tr>
                    <td>姓名</td>
                    <td>年级</td>
                    <td>班级</td>
                    <td>性别</td>
                    <td>岗位</td>
                    <td>联系电话</td>
                    <td>入职日期</td>
                </tr>
                <c:forEach var="dataList" items="${dataList}" varStatus="i">
                <!--这是一条记录开始-->
                <tr>
                    <td>
                        <a href="../studentsinfo/StudentInfo.html">
                                ${dataList.sname}</a>
                    </td>
                    <td>
                        <a href="ThisCompEmpSearch.html">
                                ${dataList.sgrade}</a>
                    </td>
                    <td>
                        <a href="ThisCompEmpSearch.html">
                                ${dataList.spro}${dataList.sclass}班</a>
                    </td>
                    <td><c:if test="${dataList.ssex==false}">
                        <a>男</a>
                    </c:if>
                        <c:if test="${dataList.ssex==true}">
                            <a>女</a>
                        </c:if>
                    </td>
                    <td>
                        <a href="ThisCompEmpSearch.html'">${dataList.jname}</a>&nbsp;
                    </td>
                    <td>${dataList.sphone}</td>
                    <td>${fn:substring(dataList.etime,0,10)}</td>
                </tr>
                </c:forEach>
            </table>
            <div class="table-slipline"></div>
        </div>
        <div class="button-footer">

            <div class="right-button-footer">
                <div id="Page">
                </div>
            </div>
            <div class="left-button-footer">
                <button type="submit" class="mybutton" value="Submit" onclick="window.open('/company/outputComStu?cid=${dataList.get(0).cid}')">导出数据</button>
            </div>
        </div>
    </div>
    </div>
</body>
</html>

