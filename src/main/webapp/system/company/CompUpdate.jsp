<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/10/29
  Time: 12:02
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
    <script language="javascript">
        function provinceajax(){
            var aprovince = document.getElementById("aprovince").value;
            var city = document.getElementById("city");
            $("#city").html("");
            $.ajax({
                url : '/area/selectCity',
                type : 'GET',
                data :{'key':aprovince},
                contentType : 'application/json',
                dataType : 'json',
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
        function startload() {

        }
    </script>
</head>
<body onload="startload()">
<div class="table-box">
    <div class="table-content">
        <!--这是标题栏-->
        <div class="table-head">
            <div class="table-address">
                <div style="float: left;">
                    <span>信息管理</span>
                    <div class="left-arrow"></div>
                    <span><a href="selecteAllC.html">企业管理</a></span>
                    <div class="left-arrow"></div>
                    <span>修改企业信息</span>
                </div><br />
                <div class="Big-title">
                    <div class="littil-title">
                        修改企业信息
                    </div>
                    <div class="search-box">
                        <button class="mybutton" type="button" onclick="JavaScript :history.back(-1)">
                            返回上一页
                        </button>
                    </div>
                </div>
            </div>
        </div>
        <!--这是标题栏结束-->
        <div>
            <span style="color: red" ><h3>${info}</h3></span><br>
            <!--这是表格开始-->
            <form action="/company/updateCompany" method="get">
                <input type="hidden" id="cid" name="cid" value="${findCompByCid.cid}">
                <table  class="pure-table pure-table-bordered CompInfo1" style="text-align: left;">
                    <tr>
                        <td>企业名称</td>
                        <td colspan="4">
                            <input name="cname" id="cname"  value="${findCompByCid.cname}" type="text" required="required"/>
                        </td>
                    </tr>
                    <tr>

                        <td>联系人：</td>
                        <td colspan="4">
                            <input name="chr" id="chr" value="${findCompByCid.chr}" type="text" required="required"/>
                        </td>

                    </tr>
                    <tr>
                        <td>联系电话：</td>
                        <td colspan="4">
                            <input name="cphone" id="cphone" value="${findCompByCid.cphone}" type="text" required="required" />
                        </td>
                    </tr>
                    <tr>
                        <td>详细地址：</td>
                        <td colspan="4">
                            <select  name="aprovince" id="aprovince" onchange="provinceajax()">
                                <%--<option id="aprovinces" value="${findCompByCid.cmAreaByAid.aprovince}">${findCompByCid.cmAreaByAid.aprovince}</option>--%>
                                <c:forEach items="${allAreaList}" var="s" varStatus="stu">
                                    <option id="aprovinces" value="${s.aprovince}" <c:if test="${s.aprovince==findCompByCid.cmAreaByAid.aprovince}"> selected="selected"</c:if>>${s.aprovince}</option>
                                </c:forEach>
                            </select>
                            <select name="city" id="city"  >
                                <c:forEach items="${findCityByApro}" var="s" varStatus="stu">
                                    <option id="aprovinces" value="${s.aid}" <c:if test="${s.aid==findCompByCid.cmAreaByAid.aid}"> selected="selected"</c:if>>${s.acity}</option>
                                </c:forEach>
                            </select>
                            <input  name="caddress" id="caddress" value="${findCompByCid.caddress}"  onfocus="javascript:if(this.value=='请在此处填写详细地址.....')this.value='';" />
                        </td>
                    </tr>
                    <tr>
                        <td>邮箱：</td>
                        <td colspan="4">
                            <input name="cemail" type="text"   id="cemail"   value="${findCompByCid.cemail}"/>
                        </td>
                    </tr>
                    <tr>
                        <td>简介：</td>
                        <td colspan="4">
                            <textarea cols="60" rows="8" id="cinfo" name="cinfo">${findCompByCid.cinfo}</textarea>
                        </td>
                    </tr>
                    <tr>
                        <td>备注：</td>
                        <td colspan="4">
                            <textarea cols="60" rows="8" id="cmark" name="cmark">${findCompByCid.cmark}</textarea>
                        </td>
                    </tr>
                    <c:if test="${info==null}">
                        <tr>
                            <td colspan="5" width="50%" style="text-align: center;">
                                <input type="submit"   class="mybutton"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <button class="mybutton" type="button" onclick="JavaScript :history.back(-1)" >取消</button>
                            </td>
                        </tr>
                    </c:if>
                </table>
            </form>
            <div class="table-slipline"></div>
            <!--这是表格结束-->
            <script type="text/javascript" language ="javascript"><!--
            //BindProvince();//只初始化省份
            BindCity("山东省");//初始化
            // --></script>
        </div>
    </div>
    <div id="showtabs-div">
        <div class="tab-close">
            <button class="mybutton" type="button" onclick="HideTabs()">关闭</button>
        </div>
        <iframe src="../tools/GangWeiTabs.html" width="810px" height="340px" frameborder="0"></iframe>
    </div>
    <div id="zhezhaobg"></div></div>
</body>
</html>
