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
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    <title></title>
    <link rel="stylesheet" type="text/css" href="../../css/default.css"/>
    <script src="../../js/showele.js" type="text/javascript" charset="utf-8"></script>
    <link rel="stylesheet" href="../../css/icon.css" />
    <script type="text/javascript" src="../../js/showele.js" ></script>
    <script type="text/javascript" src="../../js/Date.js" ></script>
    <script src="../../js/addtime.js" type="text/javascript"></script>
    <script src="../../js/jquery.min.js" type="text/javascript" charset="utf-8"></script>

    <script type="text/javascript">
        function findcity() {
            var myselect = document.getElementById("aprovince");
            var index = myselect.selectedIndex;
            var aprovince = myselect.options[index].value;
            $.ajax({
                type: "POST",
                url: "/area/findcity",
                data:"aprovince="+aprovince,
                dataType:"text",
                success: function(data){
                    var json = eval("("+data+")"); // data的值是json字符串，这行把字符串转成object
                    var json = JSON.parse( data );
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
                    <span>添加面试学生</span>
                </div><br />
                <br />
                <div class="Big-title">
                    <div class="littil-title">
                        添加面试学生
                    </div>
                    <div class="search-box">
                        <form action="/inter/findUnempBySname" method="post" name="search" id="search">
                            <input type="hidden" name="rid" value="${rid}">
                            <input type="text" name="sname" id="sname" placeholder="请输入学生姓名进行搜索...."/>
                            <button class="mybutton" type="button" onclick="query()"> <span>搜索</span> </button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <div>
            <form action="/inter/addInter" method="post">
            <c:if test="${unempList!=null}">
            <!--这是一条记录开始-->
                <table  class="pure-table pure-table-bordered">
                    <tr>
                        <td></td>
                        <td>姓名</td>
                        <td>性别</td>
                        <td>年级</td>
                        <td>班级</td>
                    </tr>
                    <c:forEach var="unemp" items="${unempList}">
                        <tr>
                            <td><input type="radio" name="sid" id="sid" value="${unemp.sid}" required="required"/></td>
                            <td id="fsname">${unemp.sname}</td>
                            <td id="fsex">
                                <c:if test="${!(unemp.ssex)}">
                                    男
                                </c:if>
                                <c:if test="${unemp.ssex}">
                                    女
                                </c:if>
                            </td>
                            <td id="fsgrade">${unemp.sgrade}</td>
                            <td id="fsprosclass">${unemp.spro}${unemp.sclass}班</td>
                        </tr>
                    </c:forEach>
                </table>

            <div class="table-slipline"></div>
            <!--这是一条记录结束-->
            </c:if>
            <c:if test="${unempList==null}">
                暂时没有未就业生信息
            </c:if>


                <table  class="pure-table pure-table-bordered" style="text-align:left">
                    <input type="hidden" name="rid" value="${rid}">
                    <input type="hidden" name="sid">
                    <tr>
                        <td>面试时间：</td>
                        <td >
                            <input type="text" id="add_date" name="itime" onclick="choose_date_czw('add_date');" required="required"/>
                        </td>
                    </tr>
                    <tr>
                        <td>面试城市：</td>
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
                        <td>面试地点：</td>
                        <td>
                            <input type="text" name="iaddress" required="required"/>
                        </td>
                    </tr>
                    <tr>；
                        <td >面试方式：</td>
                        <td ><input type="text" name="itype" required="required"/></td>
                    </tr>
                    <tr style="text-align: center;">
                        <td colspan="2">
                            <input type="submit" value="保存" name="" class="mybutton"  />
                        </td>
                    </tr>
                </table>
            </form>
            <div class="table-slipline"></div>


        </div>
    </div>
    <div class="button-footer">

    </div>
</div>

<script>
    function query(){
        var sname = document.getElementById("sname").value;
        if (sname == "") {
            alert("关键字不能为空！");
        }else{
            $("#search").submit();
        }
    }
</script>

</body>
</html>
