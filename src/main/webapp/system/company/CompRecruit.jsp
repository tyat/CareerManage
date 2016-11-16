<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 2016/11/13
  Time: 15:08
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
                    <span>信息管理</span><div class="left-arrow"></div>
                    <span>企业管理</span><div class="left-arrow"></div>
                    <span>岗位招聘信息</span>
                </div> <br />
                <div class="Big-title">
                    <div class="littil-title">
                        岗位招聘信息
                    </div>
                    <div class="search-box">
                        <form action="/recruit/query" method="post">
                            <select name="type">
                                <option value="0">按企业名称</option>
                                <%--<option value="1">按面试时间</option>--%>
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
        <!--这是标题栏结束-->
        <div>
            <c:if test="${recruitList!=null}">
                <!--这是一条记录开始-->
                <c:forEach var="recruit" items="${recruitList}">
                    <table  class="pure-table pure-table-bordered left">
                        <tr>
                            <td >企业名称</td>
                            <td width="130px">竞聘岗位：</td>
                            <td>${recruit.jname}</td>
                            <td>工作地点：</td>
                            <td>${recruit.aprovince}${recruit.acity}</td>
                            <td rowspan="3">
                                <button class="mybutton" type="button" onclick="location='/recruit/findByRid?rid=${recruit.rid}'">编辑</button>
                            </td>
                        </tr>
                        <tr>
                            <td rowspan="4"><a href="../company/CompSearch.html">${recruit.cname}</a></td>
                            <td width="130px">联系人：</td>
                            <td>${recruit.chr}</td>
                            <td>联系电话：</td>
                            <td>${recruit.cphone}</td>
                        </tr>
                        <tr>
                            <td>月薪：</td>
                            <td>${recruit.rsalary} RMB</td>
                            <td>性别要求：</td>
                            <td>
                                <c:if test="${!(recruit.rsex)}">
                                    男
                                </c:if>
                                <c:if test="${recruit.rsex}">
                                    女
                                </c:if>
                            </td>
                        </tr>
                        <tr>
                            <td>招聘人数：</td>
                            <td>${recruit.rnum}人</td>
                            <td>报名学生：</td>
                            <td>
                                <button class="mybutton" type="button" onclick="location='/inter/findByRid?rid=${recruit.rid}'">人</button>
                            </td>
                            <td rowspan="3">
                                <button class="mybutton" type="button" onclick="AreYouSour()">删除</button>
                            </td>
                        </tr>
                        <tr>
                            <td>发布时间：</td>
                            <td>${recruit.rstart}</td>
                            <td>截止时间：</td>
                            <td>${recruit.rend}</td>
                        </tr>
                    </table>
                    <br>
                </c:forEach>
                <div class="table-slipline"></div>
                <!--这是一条记录结束-->
            </c:if>
            <c:if test="${recruitList==null}">
                暂时没有匹配的招聘信息
            </c:if>
        </div>
        <div class="button-footer">
            <div class="left-button-footer">
                <button class="mybutton" type="button" onclick="alert('弹出保存对话框')"> <span>导出数据</span></button>
            </div>
        </div>
    </div>


</div>
</body>
</html>

