<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/11/3
  Time: 8:28
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
    <script language="JavaScript">
        function  ceshi() {
            var did = document.getElementById("did").value;
            if(did=="2"||did=="5"){
                //alert(did);
                document.getElementById("thisdiv1").style.display ="none";
                document.getElementById("thisdiv2").style.display ="";
            }else {
                document.getElementById("thisdiv1").style.display ="";
                document.getElementById("thisdiv2").style.display ="none";
            }
        }
        function  startload() {
            // alert("测试----------");
            document.getElementById("thisdiv1").style.display ="";
            document.getElementById("thisdiv2").style.display ="none";
        }
    </script>
</head>
<body onload="startload()">
<div class="table-box">
    <div class="table-content">
        <div class="table-head">
            <div class="table-address">
                <div style="float: left;">
                    <span>信息管理</span>
                    <div class="left-arrow"></div>
                    <span><a href="#">非就业生信息</a></span>
                    <div class="left-arrow"></div>
                    <span>修改非就业生</span>
                </div><br />
                <div class="Big-title">
                    <div class="littil-title">
                        修改非就业生
                    </div>
                    <div class="search-box">
                        <button class="mybutton" type="button" onclick="JavaScript :history.back(-1)">
                            返回上一页
                        </button>
                    </div>
                </div>
            </div>
        </div>
        <div id="allUnEmp-table">
            <form method="get" action="/unemp/updateUnEmp">
                <!--准备就业的表这是一条记录开始-->
                <input  type="hidden" id="sid" name="sid" value="${cmStudent.sid}"/>
                <table  class="pure-table pure-table-bordered left">
                    <tr>
                        <td width="200px">姓名：</td>
                        <td><input type="text"  id="sname" name="sname" value="${cmStudent.sname}" required="required" disabled="disabled"  /></td>
                    </tr>
                    <tr>
                        <td>性别：</td>
                        <td>
                           <c:if test="${cmStudent.ssex==true}"><input id="ssex" name="ssex" value="女"/></c:if>
                            <c:if test="${cmStudent.ssex==false}"><input id="ssex" name="ssex" value="男"/></c:if>
                        </td>
                    </tr>
                    <tr>
                        <td>专业：</td>
                        <td><input type="text" id="spro" name="spro" value="${cmStudent.spro}" required="required"  disabled="disabled"/></td>
                    </tr>
                    <tr>
                        <td>年级：</td>
                        <td><input type="text" id="sgrade" name="sgrade" value="${cmStudent.sgrade}" required="required" disabled="disabled"/></td>
                    </tr>
                    <tr>
                        <td>班级：</td>
                        <td><input type="text" id="sclass" name="sclass" value="${cmStudent.sclass}" disabled="disabled"/></td>
                    </tr>

                    <tr>
                        <td>学生动向：</td>
                        <td>
                            <select id="did" name="did" onchange="ceshi()">
                                <c:forEach items="${allDirection}" var="s" varStatus="stu">
                                    <option id="dids" value="${s.did}">${s.dname}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>

                </table>

                <div id="thisdiv1" name="thisdiv1">

                    <table  class="pure-table pure-table-bordered left">
                        <tr>
                            <td width="200px" >期望岗位：</td>
                            <td>
                                <select id="jid" name="jid">
                                    <c:forEach items="${allJob}" var="s" varStatus="stu">
                                        <option id="jids" value="${s.jid}">${s.jname}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr >
                            <td width="200px">期望月薪：</td>
                            <td><input type="text" id="uesalary" name="uesalary" value=""/></td>
                        </tr>
                        <tr>
                            <td>期望实习时间：</td>
                            <td><input type="text" id="uetime" name="uetime" value="0000-00-00"  onclick="choose_date_czw('uetime')"/></td>
                        </tr>
                    </table>
                </div>
                <div id="thisdiv2"  name="thisdiv2">
                    <table  class="pure-table pure-table-bordered left">
                        <tr>
                            <td width="200px">期望院校：</td>
                            <td><input type="text" id="ueschool" name="ueschool" value=""/></td>
                        </tr>
                        <tr>
                            <td width="200px">期望专业：</td>
                            <td><input type="text" id="uemajor" name="uemajor" value=""/></td>
                        </tr>
                        <tr>
                            <td>结果：</td>
                            <td>
                                <select id="uesuccess" name="uesuccess">
                                    <option  value="0">暂无</option>
                                    <option value="1">成功</option>
                                    <option value="2">失败</option>
                                </select>
                            </td>

                        </tr>

                    </table>
                </div>
                <table class="pure-table pure-table-bordered left">
                    <tr >
                        <td colspan="2" style="text-align: center;">
                            <input class="mybutton" type="submit" >
                        </td>
                    </tr>
                </table>
            </form>

            <!--准备就业的表这是一条记录结束-->
            <div class="table-slipline"></div>
            <!--准备就业的表这是一条记录结束-->

        </div>

    </div>
    <div class="button-footer">

        <div class="right-button-footer">
            <div id="Page">
                <a href="#">«</a><span>1</span><a href="#">2</a><a href="#">3</a><a href="#">4</a><a href="#">5</a><a href="#">6</a><a href="#">»</a>
            </div>
        </div>
        <div class="left-button-footer">
            <button class="mybutton" type="button" onclick="">导出数据</button>
            <!--<button class="icon-filein" type="button" onclick="ShowDetailTip()"> <span>导入数据</span></button>
            <button class="icon-down" type="button" onclick="ShowDetailTip()"> <span>下载Excel模板</span></button>-->
        </div>
    </div>
</div>

<!--这是查看备注详情功能div-->
<div id="show-detail-tip" class="show-detail-tip">
    <button id="little-close" type="button" onclick="HideDetailTip()"></button>
    <div class="tip-box">
        <div id="icon-tipdetail"></div>
        <div class="tip-header">
            <label  > 备注详情：王新</label>
        </div>
        <div class="tip-content">
            <form action="" name="tip-detadil">
					<textarea cols="80" readonly="readonly">
						这是cascdscszvcvdvvvvvvvvvvvvvvvvvvvvvv
					</textarea><br />
                <!--<input type="button" class="icon-submit" value="编辑" />
                <input type="submit" class="icon-submit" value="保存" />-->
</form>

    </div>
    </div>
</div>
<!--这是查看备注详情功能div-->
<div id="zhezhaobg"></div>
</body>
</html>
