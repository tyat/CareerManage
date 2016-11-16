<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/11/10
  Time: 16:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="../../css/default.css"/>
    <script src="../../js/showele.js" type="text/javascript" charset="utf-8"></script>
    <link rel="stylesheet" href="../../css/icon.css" />
    <script type="text/javascript" src="../../js/valid.js" ></script>
    <script type="text/javascript" src="../../js/Cityjs/jquery.js" ></script>
    <script language="javascript">
        function ceshi(){
            var uname = document.getElementById("uname").value;

            $.ajax({
                url : '/user/selectUser',
                type : 'GET',
                data :{'key':uname},
                contentType :'application/json',
                dataType :'json',
                success : function(data) {
                    if(data=="notnull"){
                        document.getElementById("uname").value="";
                        alert("该用户名已被使用！")
                    }
                },
                error : function(msg) {
                }
            });
        }
        function insertValue(v1,v2) {
            // alert("这是一个测试----------"+v1+v2);
            document.getElementById("uid1").value=v1;
            document.getElementById("urname1").value=v2;
        }
    </script>
</head>
<body>
<div class="table-box">
    <div class="table-content">
        <!--这是标题栏-->
        <div class="table-head">
            <div class="table-address">
                <div style="float: left;">
                    <span>管理中心</span><div class="left-arrow"></div><span>管理员信息管理</span>
                </div> <br />
                <div class="Big-title">
                    <div class="littil-title">
                        管理员信息
                    </div>
                    <c:if test="${cmUser.urank==0}">
                        <div class="search-box">
                            <button type="button" class="mybutton" onclick="ShowAdminAdd()">添加管理员</button>
                        </div>
                    </c:if>
                </div>
            </div>
        </div>
        <!--这是标题栏结束-->
        <div>
            <!--这是表格开始-->
            <table  class="pure-table pure-table-bordered CompInfo1">

                <tr>
                    <td>用户名</td>
                    <td>真实姓名</td>
                    <td>电话</td>
                    <td>邮箱</td>
                    <td>权限</td>
                    <c:if test="${cmUser.urank==0}">
                        <td>操作</td>
                    </c:if>
                </tr>
                <c:forEach items="${allUsers}" varStatus="stu" var="s">

                    <tr>
                        <td>${s.uname}</td>
                        <td>${s.urname}</td>
                        <td>${s.uphone}</td>
                        <td>${s.uemail}</td>
                        <c:if test="${s.urank==0}"> <td>超级管理员</td></c:if>
                        <c:if test="${s.urank==1}"> <td>任课教师</td></c:if>
                        <c:if test="${s.urank==2}"> <td>辅导员</td></c:if>
                        <c:if test="${s.urank==3}"> <td>学生管理员</td></c:if>
                        <c:if test="${s.urank==4}"> <td>禁用</td></c:if>
                        <c:if test="${cmUser.urank==0}">
                            <td>
                                <input type="button" class="mybutton" onclick="ShowAdminUpdate();insertValue(${s.uid},'${s.urname}')" value="修改权限"/>
                            </td>
                        </c:if>
                    </tr>
                </c:forEach>
            </table>
            <div class="table-slipline"></div>
            <!--这是表格结束-->
        </div>
        <div id="AdminUpdate">
            <div class="tab-close">
                <button class="mybutton" type="button" onclick="HideAdminUpdate()">取消</button>
            </div>
            <p>修改管理员权限：</p>
            <form action="/user/updateUrank" method="get">
                <table  class="pure-table pure-table-bordered left">
                    <input type="hidden" id="uid1" name="uid1"/>
                    <tr>
                        <td>真实姓名</td>
                        <td><input type="text" id="urname1" name="urname1" disabled="disabled" /></td>
                    </tr>
                    <tr>
                        <td>权限：</td>
                        <td>
                            <select id="urank1" name="urank1">
                                <option value="0">超级管理员</option>
                                <option value="1">任课教师</option>
                                <option value="2">辅导员</option>
                                <option value="3">学生管理员</option>
                                <option value="4">禁用</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" style="text-align: center;">
                            <input class="mybutton" type="submit" style="width: 100px;"/>
                            <button class="mybutton"  style="width:100px;" onclick="HideAdminUpdate()">取消</button>
                        </td>
                    </tr>

                </table>
            </form>


        </div>
        <div id="zhezhaobg2"></div>
        <div id="AdminAdd">
            <div class="tab-close">
                <button class="mybutton" type="button" onclick="HideAdminAdd()">取消</button>
            </div>
            <p>添加管理员</p>
            <form  action="/user/addUser" method="get">
                <table  class="pure-table pure-table-bordered left">
                    <tr>
                        <td>用户名：</td>
                        <td>
                            <input type="text" id="uname" value="" name="uname" required="required" onblur="ceshi()"/>
                        </td>
                    </tr>
                    <tr>
                        <td>真实姓名：</td>
                        <td>
                            <input type="text"  name="urname" placeholder=""  id="urname"value="" required="required"  />
                        </td>
                    </tr>
                    <tr>
                        <td>密码：</td>
                        <td>
                            <input type="password" name="upwd" id="upwd" value=""  required="required"  />
                        </td>
                    </tr>
                    <tr>
                        <td>邮箱：</td>
                        <td>
                            <input type="text" name="uemail" id="uemail" value=""  required="required"  onblur="v_email(this.id)"/>
                        </td>
                    </tr>
                    <tr>
                        <td>电话：</td>
                        <td>
                            <input type="text" name="uphone" id="uphone" value=""  required="required" />
                        </td>
                    </tr>
                    <tr>
                        <td>权限：</td>
                        <td>
                            <select id="urank" name="urank">
                                <option value="0">超级管理员</option>
                                <option value="1">任课教师</option>
                                <option value="2">辅导员</option>
                                <option value="3">学生管理员</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" style="text-align: center;">
                            <input class="mybutton" type="submit" style="width: 200px;"/>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
        <div id="zhezhaobg"></div>
    </div>
</div>
</body>
</html>
