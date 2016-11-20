<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/10/23
  Time: 22:01
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
    <script type="text/javascript" src="../../js/Cityjs/AtaiJs-1.2.js" ></script>
    <script type="text/javascript" src="../../js/Cityjs/jquery.js" ></script>
    <script type="text/javascript" src="../../js/Cityjs/chengshi.js" ></script>
    <script type="text/javascript" src="../../js/valid.js" ></script>
    <script language="javascript">
        function provinceajax(){
            var aprovince = document.getElementById("aprovince").value;
            var city = document.getElementById("city");
            $("#city").html("");
            $.ajax({
                url : 'selectCity',
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
        } </script>
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
                    <span>添加企业信息</span>
                </div><br />
                <div class="Big-title">
                    <div class="littil-title">
                        添加企业信息
                    </div>
                    <div class="search-box">
                        <%--<button class="mybutton" type="button" onclick="ShowUpload()"> 批量导入数据</button>
                        <button class="mybutton" type="button" onclick="alert('弹出文件下载框')">下载Excel模板</button>--%>
                    </div>
                    <span style="color: red" ><h3>${info}</h3></span><br>
                </div>
            </div>

        </div>
        <!--这是标题栏结束-->
        <div>
            <!--这是表格开始-->
            <form method="get" action="/company/addCompany">
                <table  class="pure-table pure-table-bordered CompInfo1" style="text-align: left;">
                    <tr>
                        <td>企业名称</td>
                        <td colspan="4">
                            <input name="cname" value="" id="cname"  type="text" required="required"/>
                        </td>
                    </tr>
                    <tr>

                        <td>联系人：</td>
                        <td colspan="4">
                            <input name="chr" id="chr" value="" type="text" required="required"/>
                        </td>

                    </tr>
                    <tr>
                        <td>联系电话：</td>
                        <td colspan="4">
                            <input name="cphone" id="cphone" value=""  maxlength="12" required="required"   onblur=""  type="text"   />
                        </td>
                    </tr>
                    <!--<tr>
                        <td>在岗学生人数：</td>
                        <td colspan="4">
                            <input name="" value="0人" type="text"  readonly="readonly"/>
                        </td>
                    </tr>-->
                    <tr>
                        <td>详细地址：</td>
                        <td colspan="4">
                            <select  name="aprovince" id="aprovince" onchange="provinceajax()" >
                                <option value="0" selected="selected"> 省份 </option>
                                <c:forEach items="${allAreaList}" var="s" varStatus="stu">
                                    <option id="aprovinces" value="${s.aprovince}">${s.aprovince}</option>
                                </c:forEach>

                            </select>
                            <select name="city" id="city"  >
                                <option value="0" selected=“selected“> 城市 </option>
                            </select>
                            <input  name="caddress" type="text" id="caddress" onfocus="javascript:if(this.value=='请在此处填写详细地址.....')this.value='';" required="required" placeholder="请在此处填写详细地址....."/>
                        </td>
                    </tr>
                    <tr>
                        <td>邮箱：</td>
                        <td colspan="4">
                            <input name="cemail" id="cemail" type="text" value="" onblur="v_email(this.id)" required="required"/>
                        </td>
                    </tr>
                    <tr>
                        <td>简介：</td>
                        <td colspan="4">
                            <textarea cols="60" rows="8" id="cinfo" name="cinfo">这是企业简介</textarea>
                        </td>
                    </tr>
                    <tr>
                        <td>备注：</td>
                        <td colspan="4">
                            <textarea cols="60" rows="8" name="cmark" id="cmark">这是企业备注</textarea>
                        </td>
                    </tr>
                    <tr>

                        <td colspan="5" style="text-align: center;">
                            <input type="submit" value="保存" name="" class="mybutton"  />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <button class="mybutton" type="button" onclick="JavaScript :history.back(-1)" >取消</button>
                        </td>
                    </tr>
                </table>
            </form>
            <div class="table-slipline"></div>
            <!--这是表格结束-->
            <script type="text/javascript" language ="javascript"><!--
            //BindProvince();//只初始化省份
            BindCity("山东");//初始化
            // --></script>
        </div>
    </div>
    <div id="showtabs-div">
        <div class="tab-close">
            <button class="mybutton" type="button" onclick="HideTabs()">关闭</button>
        </div>
        <iframe src="../tools/GangWeiTabs.html" width="810px" height="340px" frameborder="0"></iframe>
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
