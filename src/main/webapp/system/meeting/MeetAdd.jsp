<%--
  Created by IntelliJ IDEA.
  User: w
  Date: 2016/11/1
  Time: 10:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" type="text/css" href="../../css/default.css"/>
    <script src="../../js/showele.js" type="text/javascript" charset="utf-8"></script>
    <link rel="stylesheet" href="../../css/icon.css" />
    <script type="text/javascript" src="../../js/showele.js" ></script>
    <script type="text/javascript" src="../../js/Date.js" ></script>
    <script src="../../js/addtime.js" type="text/javascript"></script>
    <script type="text/javascript" src="../../js/upload.js" ></script>
    <script src="../../js/jquery.min.js" type="text/javascript" charset="utf-8"></script>

    <script type="text/javascript">
        function findcity() {
            var myselect = document.getElementById("aprovince");
            var index = myselect.selectedIndex;
            var aprovince = myselect.options[index].value;
            //alert(aprovince);
            $.ajax({
                type: "POST",
                url: "/area/findcity",
                //contentType: "application/json; charset=utf-8",
                data:"aprovince="+aprovince,
                dataType:"text",
                success: function(data){
                    //alert(data);
                    var json = eval("("+data+")"); // data的值是json字符串，这行把字符串转成object
                    var json = JSON.parse( data );
                    //alert( json[0].acity );
                    var city = $("#city");
                    var str = '';
                    //清空以前的option
                    $("#city").find("option").remove();
                    for(var o in json) {
                        str += '<option value="'+json[o].aid+'">'+json[o].acity+'</option>';
                    }
                    city.append(str);
                },
                error: function(XMLHttpRequest, textStatus, errorThrown){
                    alert(XMLHttpRequest.status);
                    alert(XMLHttpRequest.readyState);
                    alert(textStatus);
                }
            });
        }

    </script>
</head>
<body onload="addtime()">
<div class="table-box">
    <div class="table-content">
        <div class="table-head">
            <div class="table-address">
                <div style="float: left;">
                    <span>信息管理</span><div class="left-arrow"></div>
                    <span>面试信息</span><div class="left-arrow"></div>
                    <span>添加面试信息</span>
                </div><br />
                <br />
                <div class="Big-title">
                    <div class="littil-title">
                        添加招聘信息
                    </div>
                    <div class="search-box">
                        <button class="mybutton" type="button" onclick="ShowUpload()" > <span>导入数据</span></button>
                        <button class="mybutton" type="button"  onclick="alert('弹出下载页')"> <span>下载Excel模板</span></button>
                    </div>
                </div>
            </div>
        </div>
        <div>
            <!--这是一条记录开始-->
            <form action="/recruit/addRecruit" method="post">
                <table  class="pure-table pure-table-bordered" style="text-align:left">
                    <input type="hidden" name="rid" value="${recruit.rid }">
                    <tr>
                        <td>企业名称：</td>
                        <td>
                            <select name="cid">
                                <c:forEach items="${companyList}" var="company">
                                    <option value="${company.cid }">${company.cname}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>工作城市：</td>
                        <td>
                            <select name="aprovince" id="aprovince" onchange="javascript:findcity();">
                                <option selected="selected"></option>
                                <c:forEach items="${areaList}" var="area">
                                    <option value="${area.aprovince}">${area.aprovince}</option>
                                </c:forEach>
                            </select>
                            <select id="city" name="aid">
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>竞聘岗位：</td>
                        <td>
                            <select name="jid">
                                <c:forEach items="${jobList}" var="job">
                                    <option value="${job.jid }">${job.jname}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>月薪：</td>
                        <td>
                            <input type="text" name="rsalary"/>RMB
                        </td>
                    </tr>
                    <tr>
                        <td >性别要求：</td>
                        <td >
                            <input type="radio" name="rsex" value="0">男
                            <input type="radio" name="rsex" value="1">女
                        </td>
                    </tr>
                    <tr>
                        <td >招聘人数：</td>
                        <td ><input type="text" name="rnum"/>人</td>
                    </tr>

                    <tr>
                        <td>截止时间：</td>
                        <td >
                            <input type="text" id="add_date" name="rend" onclick="choose_date_czw('add_date');"/>
                        </td>
                    </tr>
                    <tr>
                        <td>详情:</td>
                        <td>
                            <textarea name="rinfo" cols="100" rows="4"></textarea>
                        </td>
                    </tr>

                    <tr style="text-align: center;">
                        <td colspan="2">
                            <button class="mybutton" type="button" onclick="this.form.submit()">保存</button>
                        </td>
                    </tr>
                </table>
            </form>
            <div class="table-slipline"></div>
            <!--这是一条记录结束-->


        </div>
    </div>
    <div class="button-footer">

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
