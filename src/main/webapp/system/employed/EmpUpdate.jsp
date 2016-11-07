<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/10/31
  Time: 20:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"  %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="../../css/default.css"/>
    <script src="../../js/showele.js" type="text/javascript" charset="utf-8"></script>
    <link rel="stylesheet" href="../../css/icon.css" />
    <script type="text/javascript" src="../../js/showele.js" ></script>
    <script type="text/javascript" src="../../js/Date.js" ></script>
</head>
<body onload="Activeli()">
<div class="table-box">
    <div class="table-content">
        <div class="table-head">
            <div class="table-address">
                <div style="float: left;">
                    <span>信息管理</span><div class="left-arrow"></div>
                    <span>就业生信息</span><div class="left-arrow"></div>
                    <span>修改就业生信息</span>
                </div><br />
                <br />
                <div class="Big-title">
                    <div class="littil-title">
                        修改就业生信息
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
            <span style="color: red" ><h3>${info}</h3></span><br>
            <!--这是一条记录开始-->
            <form action="/emp/updateEmp" method="get">
                <input type="hidden" id="sid" name="sid" value="${findStuBySid.sid}">
                <table  class="pure-table pure-table-bordered" style="text-align:left">
                    <tr>
                        <td>姓名</td>
                        <td><input type="text" value="${findStuBySid.sname}" disabled="disabled"/></td>
                    </tr>
                    <tr>
                        <td>专业：</td>
                        <td><input type="text" value="${findStuBySid.spro}" disabled="disabled"/></td>
                    </tr>
                    <tr>
                        <td>年级：</td>
                        <td><input type="text" value="${findStuBySid.sgrade}" disabled="disabled"/></td>
                    </tr>
                    <tr>
                        <td>班级：</td>
                        <td><input type="text" value="${findStuBySid.sclass}" disabled="disabled"/></td>
                    </tr>
                    <tr>
                        <td>性别：</td>
                        <td>
                        <c:if test="${findStuBySid.ssex==true}"><input type="text" value="男" disabled="disabled"/></c:if>
                        <c:if test="${findStuBySid.ssex==false}"><input type="text" value="女" disabled="disabled"/></c:if>
                        </td>
                    </tr>
                    <tr>
                        <td >就业企业：</td>
                        <td >
                          <input type="text" id="cid" name="cid" value="${findCompanyBySid.cname}"  disabled="disabled"/>
                        </td>

                    </tr>

                    <tr>
                        <td>岗位:</td>
                        <td>
                         <input type="text" id="jid" name="jid" value="${findBySid.jname}"  disabled="disabled"/>
                        </td>
                    <tr>
                        <td>薪资：</td>
                        <td>
                            <input type="text" id="esalary" name="esalary" value="${findEmpBySid.esalary}" />
                        </td>
                    </tr>
                    </tr>
                    <tr>
                        <td>实习时间：</td>
                        <td>
                            <input value="${findEmpBySid.etime}"   required="required" type="text" id="etime" name="etime" onclick="choose_date_czw('etime')"/>
                        </td>
                    </tr>
                    <tr>
                        <td>是否离职：</td>
                        <td>
                            <select id="estate" name="estate">
                                <option id="estates" value="0" selected="selected">在岗</option>
                                <option id="estates1" value="1">离职</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>离职时间：</td>
                        <td>
                            <input value="${findEmpBySid.etime}" required="required" type="text" id="eleave" name="eleave" onclick="choose_date_czw('eleave')"/>
                        </td>
                    </tr>
                    <tr>
                        <td>离职原因:</td>
                        <td>
                            <textarea cols="100" rows="4"  id="ereason" name="ereason">${findEmpBySid.ereason}</textarea>
                        </td>
                    </tr>
                    <tr>
                        <td>推荐人：</td>
                        <td>
                            <select id="user" name="user">
                                <c:forEach items="${allUser}" var="s" varStatus="stu">
                                    <option id="uids" value="${s.uid}">${s.urname}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>是否网签：</td>
                        <td>
                            <select id="ewq" name="ewq">
                                <option id="ewq1" value="0" selected="selected">是</option>
                                <option id="ewq2" value="1">否</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>备注:</td>
                        <td>
                            <textarea cols="100" rows="4" id="einfo" name="einfo">${findEmpBySid.einfo}</textarea>
                        </td>
                    </tr>
                    <tr style="text-align: center;">
                        <td colspan="2">
                            <input class="mybutton" type="submit" />
                        </td>
                    </tr>
                </table>
            </form>
            <div class="table-slipline"></div>
            <!--这是一条记录结束-->
        </div>
    </div>
</div>
</body>
</html>