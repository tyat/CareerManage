<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/10/30
  Time: 13:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"  %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="../../js/Cityjs/jquery.js" ></script>
    <link rel="stylesheet" type="text/css" href="../../css/default.css"/>
    <script src="../../js/showele.js" type="text/javascript" charset="utf-8"></script>
    <link rel="stylesheet" href="../../css/icon.css" />
    <script type="text/javascript" src="../../js/showele.js" ></script>
    <script type="text/javascript" src="../../js/Date3.js" ></script>
    <script src="../../js/addtime.js" type="text/javascript"></script>
    <script type="text/javascript" src="../../js/upload.js" ></script>
    <script language="javascript">
        function snoajax(){
            var sno = document.getElementById("sno").value;
            $.ajax({
                url : '/student/selectStudentBySno',
                type : 'GET',
                data :{'key':sno},
                contentType : 'application/json',
                dataType : 'json',
                success : function(data) {
                    if (data=="null"){
                        alert("该生基本信息不存在，请先添加！");
                        document.getElementById("mydiv1").style.display ="none";
                        document.getElementById("mydiv2").style.display ="";
                    }else{
                        var stu = data.split(",");
                        document.getElementById("sid").value=stu[0];
                        document.getElementById("sname").value=stu[2];
                        $("#sname").html(stu[2]);
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
                error : function(msg) {
                    alert(msg);
                }
            });
        }
        function provinceajax(){
            var aprovince = document.getElementById("aprovince").value;
            var city = document.getElementById("city");
            $("#city").html("");
            $.ajax({
                url : '/area/selectCity',
                type : 'GET',
                data :{'key':aprovince},
                contentType :'application/json',
                dataType :'json',
                success : function(data) {
                    //请求成功
                    var citys = data.split(",");
                    citys.length = citys.length-1;
                    for (var i = 0; i < citys.length; i++) {
                        var aid = citys[i].split(":");
                        city.options.add(new Option(aid[1],aid[0]));
                    }
                },
                error : function(msg) {
                    alert(msg);
                }
            });
        }
        function Jprovinceajax(){
            var aprovince = document.getElementById("Japrovince").value;
            var city = document.getElementById("Jcity");
            $("#Jcity").html("");
            $.ajax({
                url : '/area/selectCity',
                type : 'GET',
                data :{'key':aprovince},
                contentType :'application/json',
                dataType :'json',
                success : function(data) {
                    //请求成功
                    var citys = data.split(",");
                    citys.length = citys.length-1;
                    for (var i = 0; i < citys.length; i++) {
                        var aid = citys[i].split(":");
                        //alert(aid[0]+"."+aid[1]);
                        city.options.add(new Option(aid[1],aid[0]));
                    }
                },
                error : function(msg) {
                    alert(msg);
                }
            });
        }
        function Iprovinceajax(){
            var aprovince = document.getElementById("Iaprovince").value;
            var city = document.getElementById("Icity");
            $("#Icity").html("");
            $.ajax({
                url : '/area/selectCity',
                type : 'GET',
                data :{'key':aprovince},
                contentType :'application/json',
                dataType :'json',
                success : function(data) {
                    //请求成功
                    var citys = data.split(",");
                    citys.length = citys.length-1;
                    for (var i = 0; i < citys.length; i++) {
                        var aid = citys[i].split(":");
                        //alert(aid[0]+"."+aid[1]);
                        city.options.add(new Option(aid[1],aid[0]));
                    }
                },
                error : function(msg) {
                    alert(msg);
                }
            });
        }
        function  startload() {
            // alert("测试----------");
            document.getElementById("mydiv1").style.display ="";
            document.getElementById("mydiv2").style.display ="none";
        }
    </script>
</head>
<body onload="addtime();startload()">
<div class="table-box">
    <div class="table-content">
        <div class="table-head">
            <div class="table-address">
                <div style="float: left;">
                    <span>信息管理</span><div class="left-arrow"></div>
                    <span>就业生信息</span><div class="left-arrow"></div>
                    <span>添加就业生信息</span>
                </div><br />
                <br />
                <div class="Big-title">
                    <div class="littil-title">
                        添加就业生信息
                    </div>
                    <div class="search-box">
                        <button class="mybutton" type="button" onclick="JavaScript :history.back(-1)">
                            取消并返回上一页
                        </button>
                    </div>
                </div>
            </div>
        </div>
        <div>
            <span style="color: red" ><h3>${info}</h3></span><br>
            <!--这是一条记录开始-->
            <form action="/emp/addEmp" method="post">
                <table  class="pure-table pure-table-bordered" style="text-align:left">
                    <tr>
                        <td width="200px">学号：</td>
                        <td><input type="text" id="sno" name="sno" required="required" /><input class="mybutton" type="button" value="检测" onclick="snoajax()" /> </td>
                    </tr>
                </table>
                <div id="mydiv1">
                    <table  class="pure-table pure-table-bordered" style="text-align:left">
                        <input type="hidden" id="sid" name="sid" />

                        <tr>
                            <td width="200px">姓名：</td>
                            <td><input type="text"  id="sname" name="sname" required="required" disabled="disabled"  /></td>
                        </tr>
                        <tr>
                            <td width="200px">性别：</td>
                            <td>
                                <input type="text" id="ssex"  name="ssex"  required="required" disabled="disabled"/>
                            </td>
                        </tr>
                        <tr>
                            <td width="200px">专业：</td>
                            <td><input type="text" id="spro" name="spro" required="required"  disabled="disabled"/></td>
                        </tr>
                        <tr>
                            <td width="200px">年级：</td>
                            <td><input type="text" id="sgrade" name="sgrade"  disabled="disabled"/></td>
                        </tr>
                        <tr>
                            <td width="200px">班级：</td>
                            <td><input type="text" id="sclass" name="sclass" disabled="disabled" /></td>
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
                <table  class="pure-table pure-table-bordered" style="text-align:left">
                    <tr>
                        <td width="200px">就业企业：</td>
                        <td >
                            <input type="text" id="cname" name="cname"/>
                        </td>
                    </tr>
                    <tr>
                        <td width="200px">联系人：</td>
                        <td >
                            <input type="text" id="chr" name="chr"/>
                        </td>
                    </tr>
                    <tr>
                        <td width="200px">联系电话：</td>
                        <td>
                            <input name="cphone" id="cphone" value=""  maxlength="12" required="required"   onblur=""  type="text"   />
                        </td>
                    </tr>
                    <tr>
                        <td width="200px">详细地址：</td>
                        <td>
                            <select  name="aprovince" id="aprovince" onchange="provinceajax()" >
                                <option value="0" selected="selected"> 省份 </option>
                                <c:forEach items="${allAreaList}" var="s" varStatus="stu">
                                    <option id="aprovinces" value="${s.aprovince}">${s.aprovince}</option>
                                </c:forEach>

                            </select>
                            <select name="city" id="city"  >
                                <option value="0" selected="selected"> 城市 </option>
                            </select>
                            <input  name="caddress" type="text" id="caddress" onfocus="javascript:if(this.value=='请在此处填写详细地址.....')this.value='';" required="required" placeholder="请在此处填写详细地址....."/>
                        </td>
                    </tr>
                    <tr>
                        <td width="200px">邮箱：</td>
                        <td>
                            <input name="cemail" id="cemail" type="text" value="" onblur="v_email(this.id)" required="required"/>
                        </td>
                    </tr>
                    <tr>
                        <td>实习时间：</td>
                        <td width="200px">
                            <input type="text" id="etime" required="required" name="etime" onclick="choose_date_czw('etime')"/>
                        </td>
                    </tr>
                    <tr>
                        <td>面试时间：</td>
                        <td>
                            <input type="text" required="required" id="itime"  name="itime"  onclick="choose_date_czw('itime');"/>
                            <select id="add_time" name="add_time"></select> <select id="add_time2" name="add_time2"></select>
                        </td>
                    <tr>
                    <tr>
                        <td width="200px">面试地址：</td>
                        <td>
                            <select  name="Iaprovince" id="Iaprovince" onchange="Iprovinceajax()" >
                                <option value="0" selected="selected"> 省份 </option>
                                <c:forEach items="${allAreaList}" var="s" varStatus="stu">
                                    <option id="aprovinces" value="${s.aprovince}">${s.aprovince}</option>
                                </c:forEach>
                            </select>
                            <select name="Icity" id="Icity"  >
                                <option value="0" selected="selected"> 城市 </option>
                            </select>
                            <input  name="iaddress" type="text" id="iaddress" onfocus="javascript:if(this.value=='请在此处填写详细地址.....')this.value='';" required="required" placeholder="请在此处填写详细地址....."/>
                        </td>
                    </tr>
                    <tr>
                        <td width="200px">面试方式：</td>
                        <td >
                            <input type="text" id="itype" name="itype"/>
                        </td>
                    <tr>

                    <tr>
                        <td width="200px">岗位:</td>
                        <td>
                            <select id="job" name="job">
                                <c:forEach items="${allJob}"  var="s" varStatus="stu">
                                    <option id="jids" value="${s.jid}">${s.jname}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>

                    <tr>
                        <td width="200px">工作地址：</td>
                        <td>
                            <select  name="Japrovince" id="Japrovince" onchange="Jprovinceajax()" >
                                <option value="0" selected="selected"> 省份 </option>
                                <c:forEach items="${allAreaList}" var="s" varStatus="stu">
                                    <option id="aprovinces" value="${s.aprovince}">${s.aprovince}</option>
                                </c:forEach>
                            </select>
                            <select name="Jcity" id="Jcity"  >
                                <option value="0" selected=“selected“> 城市 </option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td width="200px">月薪：</td>
                        <td>
                            <input type="text" name="rsalary" id="rsalary"  onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))"/>
                        </td>
                    </tr>

                    <tr>
                        <td width="200px">是否网签：</td>
                        <td>
                            <select id="ewq" name="ewq">
                                <option value="1" selected="selected">是</option>
                                <option value="0">否</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td width="200px">备注:</td>
                        <td>
                            <textarea cols="100" rows="4" id="einfo" name="einfo">这是就业生备注详情......</textarea>
                        </td>
                    </tr>
                    <tr>
                        <td width="200px">企业简介：</td>
                        <td>
                            <textarea cols="60" rows="8" id="cinfo" name="cinfo">这是企业简介</textarea>
                        </td>
                    </tr>
                    <tr>
                        <td width="200px">企业备注：</td>
                        <td>
                            <textarea cols="60" rows="8" name="cmark" id="cmark">这是企业备注</textarea>
                        </td>
                    </tr>
                    <tr style="text-align: center;">
                        <td colspan="2">
                            <input class="mybutton" type="submit" value="保存"/>
                        </td>
                    </tr>
                </table>
            </form>
            <div class="table-slipline"></div>
            <!--这是一条记录结束-->


        </div>
    </div>
    <div class="button-footer">

        <!--<div class="left-button-footer">
            <button class="mybutton" type="button"  > <span>导入数据</span></button>
            <button class="mybutton" type="button"  > <span>下载Excel模板</span></button>
        </div>-->
    </div>
</div>
<div id="showupload-div">
    <div class="tab-close">
        <button class="mybutton" type="button" onclick="HideUpload()">关闭</button>
    </div>
    <iframe src="../tools/UploadExcel.html" width="810px" height="340px" frameborder="0"></iframe>
</div>
<div id="zhezhaobg"></div>

</body>
</html>
