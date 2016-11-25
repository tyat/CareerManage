<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/11/3
  Time: 8:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"  %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="../../js/showele.js" ></script>
    <script src="../../js/showele.js" type="text/javascript" charset="utf-8"></script>
    <script type="text/javascript" src="../../js/Cityjs/jquery.js" ></script>
    <link rel="stylesheet" type="text/css" href="../../css/default.css"/>
    <link rel="stylesheet" href="../../css/icon.css" />
    <script type="text/javascript" src="../../js/Date3.js" ></script>
    <script language="JavaScript">
        function snoajax(){
            var sno = document.getElementById("sno").value;
            // alert(sno);
            $.ajax({
                url:'/unemp/selectStuBySno',
                type:'GET',
                data:{'key':sno},
                contentType:'application/json',
                dataType:'json',
                success:function (data) {
                    //alert(data);
                    if (data=="null"){
                        alert("该生基本信息不存在，请先添加！");
                        document.getElementById("mydiv1").style.display ="none";
                        document.getElementById("mydiv2").style.display ="";
                    }else if(data=="inemp"){
                        alert("该生以就业，不能添加！");
                    }else if (data=="notnull"){
                        alert("该生已存在未就业生信息表，不能添加！");
                    }else{
                        var stu = data.split(",");
                        document.getElementById("sid").value=stu[0];
                        document.getElementById("sname").value=stu[2];
                        $("#sname").html(stu[2]);
                        // alert(stu[3]);
                        if(stu[3]=="true"){
                            document.getElementById("ssex").value="女";
                        }else if(stu[3]=="false"){
                            document.getElementById("ssex").value="男";
                        }
                        document.getElementById("spro").value=stu[4];
                        document.getElementById("sgrade").value=stu[5];
                        document.getElementById("sclass").value=stu[6];
                    }
                },
                error:function (msg) {
                    alert("检测错误！");
                }
            });
        } </script>
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
            document.getElementById("mydiv1").style.display ="";
            document.getElementById("mydiv2").style.display ="none";
        }
    </script>

</head>
<body  onload="startload()">
<div class="table-box">
    <div class="table-content">
        <div class="table-head">
            <div class="table-address">
                <div style="float: left;">
                    <span>信息管理</span>
                    <div class="left-arrow"></div>
                    <span><a href="#">非就业生信息</a></span>
                    <div class="left-arrow"></div>
                    <span>添加非就业生</span>
                </div><br />
                <div class="Big-title">
                    <div class="littil-title">
                        添加非就业生
                    </div>
                    <div class="search-box">
                        <button class="mybutton" type="button" onclick="JavaScript :history.back(-1)">
                            返回上一页
                        </button>
                    </div>
                </div>

            </div>
        </div>
        <span style="color: red" ><h3>${info}</h3></span><br>
        <div id="allUnEmp-table">
            <form method="post" action="/unemp/addUnEmp">
                <!--准备就业的表这是一条记录开始-->
                <input  type="hidden" id="sid" name="sid" value=""/>
                <table  class="pure-table pure-table-bordered left">
                    <tr>
                        <td width="200px">学号：</td>
                        <td><input type="text" id="sno" name="sno" required="required" /><input class="mybutton" type="button" id="" value="检测" onclick="snoajax()" /> </td>
                    </tr>
                </table>
                <div id="mydiv1">
                    <table  class="pure-table pure-table-bordered left">
                        <tr>
                            <td width="200px">姓名：</td>
                            <td><input type="text"  id="sname" name="sname" required="required" disabled="disabled"  /></td>
                        </tr>
                        <tr>
                            <td>性别：</td>
                            <td>
                                <input type="text" id="ssex"  name="ssex"  required="required" disabled="disabled"/>
                            </td>
                        </tr>
                        <tr>
                            <td>专业：</td>
                            <td><input type="text" id="spro" name="spro" required="required"  disabled="disabled"/></td>
                        </tr>
                        <tr>
                            <td>年级：</td>
                            <td><input type="text" id="sgrade" name="sgrade"  required="required" disabled="disabled"/></td>
                        </tr>
                        <tr>
                            <td>班级：</td>
                            <td><input type="text" id="sclass" name="sclass"  disabled="disabled"/></td>
                        </tr>
                    </table>
                </div>
                <div id="mydiv2">
                    <table  class="pure-table pure-table-bordered" style="text-align:left">
                        <tr>
                            <td width="200px">姓名：</td>
                            <td><input type="text"  id="addsname" name="addsname"    /></td>
                        </tr>
                        <tr>
                            <td width="200px">性别：</td>
                            <td>
                                <select id="addssex" name="addssex">
                                    <option value="0" selected="selected">男</option>
                                    <option value="1" selected="selected">女</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td width="200px">出生日期：</td>
                            <td><input type="text" id="addsbirth" name="addsbirth" onclick="choose_date_czw('addsbirth')"/></td>
                        </tr>
                        <tr>
                            <td width="200px">专业：</td>
                            <td><input type="text" id="addspro" name="addspro" /></td>
                        </tr>
                        <tr>
                            <td width="200px">年级：</td>
                            <td><input type="text" id="addsgrade" name="addsgrade"  />例如2013</td>
                        </tr>
                        <tr>
                            <td width="200px">班级：</td>
                            <td>
                                <select id="addsclass" name="addsclass">
                                    <option value="1" selected="selected">1班</option>
                                    <option value="2">2班</option>
                                    <option value="3">3班</option>
                                    <option value="4">4班</option>
                                    <option value="5">5班</option>
                                </select>
                        </tr>
                        <tr>
                            <td width="200px">身份证号：</td>
                            <td >
                                <input name="addscode" id="addscode"  type="text"/>
                            </td>
                        </tr>
                        <tr>
                            <td width="200px">联系电话：</td>
                            <td >
                                <input name="addsphone" id="addsphone" value=""  maxlength="12"  type="text"   />
                            </td>
                        </tr>
                        <tr>
                            <td width="200px">邮箱：</td>
                            <td>
                                <input name="addsemail" id="addsemail"  type="text" onblur="v_email(this.id)" />
                            </td>
                        </tr>
                        <tr>
                            <td width="200px">学生备注：</td>
                            <td >
                                <textarea cols="60" rows="8" name="addsdetail" id="addsdetail">这是学生备注</textarea>

                            </td>
                        </tr>

                    </table>
                </div>
                <table  class="pure-table pure-table-bordered left">
                    <tr>
                        <td width="200px">学生动向：</td>
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
                            <td><input type="text" id="uesalary" name="uesalary" value=""  onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))"/></td>
                        </tr>
                        <tr>
                            <td>期望实习时间：</td>
                            <td><input type="text" id="uetime" name="uetime"  onclick="choose_date_czw('uetime')"/></td>
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
