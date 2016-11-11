<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/11/10
  Time: 19:22
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
    <script  language="javascript">
        function yanzheng() {
            var newpwd=document.getElementById("newpwd").value;
            var repwd=document.getElementById("repwd").value;
            var oldpwd=document.getElementById("oldpwd").value;
            if(newpwd!=repwd){
                alert("密码和重复密码不一致！");
                document.getElementById("newpwd").value="";
                document.getElementById("repwd").value="";
            }else if(newpwd==oldpwd){
                alert("新密码和旧密码不能一致！");
                document.getElementById("newpwd").value="";
                document.getElementById("repwd").value="";

            }
        }
        function cheshi(){
            var uname = document.getElementById("uname").value;

            $.ajax({
                url : '/user/selectUser',
                type : 'GET',
                data :{'key':uname},
                contentType :'application/json',
                dataType :'json',
                success : function(data) {
                    if(data=="notnull"){
                        alert("该用户名已被使用！")
                        document.getElementById("uname").value="";
                    }
                },
                error : function(msg) {
                }
            });
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
                    <span>管理中心</span><div class="left-arrow"></div><span>管理员个人中心</span>
                </div> <br />
                <div class="Big-title">
                    <div class="littil-title">
                        个人中心
                    </div>
                </div>
            </div>
        </div>
        <!--这是标题栏结束-->
        <div>
            <!--这是表格开始-->
            <span style="color: red" ><h3>${info}</h3></span><br>
            <button type="button" class="mybutton" onclick="ShowAdminUpdate()">修改信息</button>&nbsp; &nbsp;&nbsp;&nbsp;<button type="button" class="mybutton" onclick="ShowAdminPwd()">修改密码</button><br/>
            <table  class="pure-table pure-table-bordered left">
                <tr>
                    <td width="100px">用户名:</td>
                    <td>${cmUser.uname}</td>
                </tr>
                <tr>
                    <td>真实姓名:</td>
                    <td>${cmUser.urname}</td>
                </tr>
                <tr>
                    <td>邮箱:</td>
                    <td>${cmUser.uemail}</td>
                </tr>
                <tr>
                    <td>联系方式:</td>
                    <td>${cmUser.uphone}</td>
                </tr>
                <tr>
                    <td>权限:</td>
                    <c:if test="${cmUser.urank==0}"> <td>超级管理员</td></c:if>
                    <c:if test="${cmUser.urank==1}"> <td>任课教师</td></c:if>
                    <c:if test="${cmUser.urank==2}"> <td>辅导员</td></c:if>
                    <c:if test="${cmUser.urank==3}"> <td>学生管理员</td></c:if>
                    <c:if test="${cmUser.urank==4}"> <td>禁用</td></c:if>
                </tr>
            </table>
            <div class="table-slipline"></div>
            <!--这是表格结束-->
        </div>
        <div id="AdminPwd">
                <button class="mybutton" type="button" onclick="HideAdminPwd()">取消</button>
            <p>修改密码</p>
            <form  action="/user/updateUpwd" method="get" id="updateUpwd" name="updateUpwd">
                <table  class="pure-table pure-table-bordered left">
                   <input type="hidden" id="uid" name="uid" value="${cmUser.uid}"/>
                    <tr>
                        <td>旧密码：</td>
                        <td>
                            <input type="password" value="" name="oldpwd" id="oldpwd"  required="required"/>
                        </td>
                    </tr>
                    <tr>
                        <td>新密码：</td>
                        <td>
                            <input type="password" value="" name="newpwd" id="newpwd" required="required"/>
                        </td>
                    </tr>
                    <tr>
                        <td>重复密码：</td>
                        <td>
                            <input type="password" value="" name="repwd" id="repwd" required="required" onblur="yanzheng()"/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" style="text-align: center;">
                            <input value="保存" class="mybutton" type="submit" style="width: 100px;"/>
                            <button class="mybutton" type="button"  style="width: 100px;" onclick="HideAdminPwd()">取消</button>
                        </td>
                    </tr>
                </table>
            </form>
        </div>

        <div id="zhezhaobg"></div>
        <div id="AdminUpdate">
        <p>修改个人信息</p>
        <form  action="/user/updateUser" method="get">
            <input type="hidden" id="uid1" name="uid1" value="${cmUser.uid}">
            <table  class="pure-table pure-table-bordered left">
                <tr>
                    <td>用户名：</td>
                    <td>
                        <input type="text" id="uname" value="${cmUser.uname}" disabled="disabled" name="uname" required="required" onblur="cheshi()"/>
                    </td>
                </tr>
                <tr>
                    <td>真实姓名：</td>
                    <td>
                        <input type="text"  name="urname" placeholder="" value="${cmUser.urname}"  id="urname"value="" required="required"  />
                    </td>
                </tr>
                <tr>
                    <td>邮箱：</td>
                    <td>
                        <input type="text" name="uemail" id="uemail" value="${cmUser.uemail}"  required="required"  onblur="v_email(this.id)"/>
                    </td>
                </tr>
                <tr>
                    <td>电话：</td>
                    <td>
                        <input type="text" name="uphone" id="uphone" value="${cmUser.uphone}"  required="required" />
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
    </div>

</body>
</html>
