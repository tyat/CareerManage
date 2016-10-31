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
    <link rel="stylesheet" type="text/css" href="../../css/default.css"/>
    <script src="../../js/showele.js" type="text/javascript" charset="utf-8"></script>
    <script src="../../js/valid.js" type="text/javascript" charset="utf-8"></script>
    <link rel="stylesheet" href="../../css/icon.css" />
    <script type="text/javascript" src="../../js/showele.js" ></script>
    <script type="text/javascript" src="../../js/Date.js" ></script>
    <script type="text/javascript">
        function snoajax(){
            var aprovince = document.getElementById("xuehao").value;
            var city = document.getElementById("city");
            $("#city").html("");
            $.ajax({
                url : 'selectCity',
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
    </script>
</head>
<body onload="Activeli()">
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
                        <button class="mybutton" type="button" onclick="ShowUpload()" > <span>导入数据</span></button>
                        <button class="mybutton" type="button"  > <span>下载Excel模板</span></button>
                    </div>
                </div>
            </div>
        </div>
        <div>
            ${cinfo}
            <!--这是一条记录开始-->
            <form id="empadd" name="empadd" action="/EmpAdd" method="get">
                <table  class="pure-table pure-table-bordered" style="text-align:left">
                    <tr>
                        <td>学号</td>
                        <td><input type="text" id="xuehao" name="xuehao"  value="" required="required" onblur="v_xuehao(this.id)" maxlength="12" onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" />   <button class="mybutton" type="button" onclick="snoajax()">检测重复</button></td>

                    </tr>
                    <tr>
                        <td>姓名</td>
                        <td><input type="text" value=" " id="" /></td>
                    </tr>

                    <tr>
                        <td>班级：</td>
                        <td><input type="text" value=" " /></td>
                    </tr>
                    <tr>
                        <td>年级：</td>
                        <td><input type="text" value=" " /></td>
                    </tr>
                    <tr>
                        <td>性别：</td>
                        <td>
                            <select>
                                <option>男</option>
                                <option>女</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td >就业企业：</td>
                        <td ><input type="text" value=" "/></td>

                    </tr>

                    <tr>
                        <td>岗位:</td>
                        <td>
                            <select>
                                <option>web前端</option>
                                <option>软件测试</option>
                                <option>Java开发</option>
                            </select>
                        </td>

                    </tr>
                    <tr>
                        <td>实习时间：</td>
                        <td>
                            <input type="text" id="add_date" onclick="choose_date_czw('add_date')"/>
                        </td>
                    </tr>
                    <tr>
                        <td>离职时间：</td>
                        <td>
                            <input type="text" id="add_date2" onclick="choose_date_czw('add_date2')"/>
                        </td>
                    </tr>
                    <tr>
                        <td>离职原因：</td>
                        <td>
                            <input type="text" id="ereason" name="ereason" onclick=""/>
                        </td>
                    </tr>
                    <tr>
                        <td>推荐人：</td>
                        <td>
                            <select>
                                <option>毛老师</option>
                                <option>张老师</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>是否网签：</td>
                        <td>
                            <select>
                                <option>是</option>
                                <option>否</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>备注:</td>
                        <td>
                            <textarea cols="100" rows="4">这是备注详情......</textarea>
                        </td>
                    </tr>
                    <tr style="text-align: center;">
                        <td colspan="2">
                            <button class="mybutton" type="button" onclick="alert('保存成功！')">保存</button>
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