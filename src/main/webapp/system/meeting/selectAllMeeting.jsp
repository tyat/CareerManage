<%--
  Created by IntelliJ IDEA.
  User: w
  Date: 2016/11/1
  Time: 10:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isELIgnored="false"%>

<html>
<head>
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" type="text/css" href="../../css/default.css"/>
    <script src="../../js/showele.js" type="text/javascript" charset="utf-8"></script>
    <link rel="stylesheet" href="../../css/icon.css" />
    <script src="../../js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
    <script type="text/javascript" src="../../js/Date.js" ></script>

    <script type="text/javascript">
        function  showRinfo(){
            document.getElementById("showRinfo").style.display="block";
            document.getElementById("zhezhaobg").style.display="block";
        }
        function  hideRinfo(){
            document.getElementById("showRinfo").style.display="none";
            document.getElementById("zhezhaobg").style.display="none";
        }
        function queryRinfo(rid) {
            $.ajax({
                type: "GET",
                url: "/recruit/queryRinfo?rid="+rid,
                dataType:"text",
                success: function(data){
                    //alert(data);
                    var json = eval("("+data+")"); // data的值是json字符串，这行把字符串转成object
                    var json = JSON.parse( data );
                    $("#rinfo").html(json.rinfo);
                    showRinfo();
                },
                error: function(XMLHttpRequest) {
                    alert(XMLHttpRequest.status);
                }
            });
        }
        function delRecruit(rid){
            var result = confirm('您确定要删除该条记录吗！');
            if(result){
                window.location.href="/recruit/delRecruit?rid="+rid;
                alert("已删除！");
            }else{
                alert('不删除！');
            }
        }

        var msg="${ResMsg}";
        if(msg!=""){
            alert(msg);
        }
        function  startload() {
            document.getElementById("mydate").style.display ="none";
        }
        function  showDateInput() {
            var type = document.getElementById("type").value;
            if(type==1){
                document.getElementById("mysearch").style.display ="none";
                document.getElementById("mydate").style.display = "block";
            }else {
                document.getElementById("mysearch").style.display ="block";
                document.getElementById("mydate").style.display = "none";
            }
        }
        function query(){
            var searchtext = document.getElementById("searchtext").value;
            var rstart = document.getElementById("rstart").value;
            if (searchtext == ""&&rstart=="") {
                alert("关键字不能为空！");
            }else{
                $("#search").submit();
            }
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
                    <span>信息管理</span><div class="left-arrow"></div>
                    <span>招聘管理</span>
                </div> <br />
                <div class="Big-title">
                    <div class="littil-title">
                        企业招聘信息  shuliang${weekrecruit}
                    </div>
                    <div class="search-box">
                        <form action="/recruit/query" method="post" name="search" id="search">
                            <table style="background: none; margin-left: 400px;">
                                <tr>
                                    <td>
                                        <select name="type"id="type" onchange="showDateInput();">
                                            <option value="0">按企业名称</option>
                                            <option value="1">按招聘时间</option>
                                        </select>
                                    </td>&nbsp;
                                    <td id="mysearch">
                                        <input type="text" name="searchtext" id="searchtext" placeholder="请输入……"/>
                                    </td>
                                    <td id="mydate">
                                        <input type="text" id="rstart" name="searchtext" onclick="choose_date_czw('rstart');"/>
                                        <input type="text" id="rend" name="date" onclick="choose_date_czw('rend');"/>
                                    </td>&nbsp;
                                    <td>
                                        <button class="mybutton" type="button" onclick="query()"> <span>搜索</span> </button>
                                    </td>
                                </tr>
                            </table>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <!--这是标题栏结束-->
        <br>
        <div>
            <c:if test="${recruitList!=null}">
            <!--这是一条记录开始-->
            <c:forEach var="recruit" items="${recruitList}">
                <table  class="pure-table pure-table-bordered left">
                    <tr>
                        <td >企业名称</td>
                        <td width="130px">竞聘岗位：</td>
                        <td>
                            <button class="mybutton" type="button" onclick="queryRinfo(${recruit.rid})">${recruit.jname}</button>
                        </td>
                        <td>工作地点：</td>
                        <td>${recruit.aprovince}${recruit.acity}</td>
                        <td rowspan="3">
                            <button class="mybutton" type="button" onclick="location='/recruit/findByRid?rid=${recruit.rid}'">编辑</button>
                        </td>
                    </tr>
                    <tr>
                        <td rowspan="4"><a href="/recruit/findByCid?cid=${recruit.cid}">${recruit.cname}</a></td>
                        <td width="130px">联系人：</td>
                        <td>${recruit.chr}</td>
                        <td>联系电话：</td>
                        <td>${recruit.cphone}</td>
                    </tr>
                    <tr>
                        <td>月薪：</td>
                        <td>${recruit.rsalary} RMB</td>
                        <td>性别要求：</td>
                        <td>
                            <c:if test="${!(recruit.rsex)}">
                                男
                            </c:if>
                            <c:if test="${recruit.rsex}">
                                女
                            </c:if>
                        </td>
                    </tr>
                    <tr>
                        <td>招聘人数：</td>
                        <td>${recruit.rnum}人</td>
                        <td>报名学生：</td>
                        <td>
                            <button class="mybutton" type="button" onclick="location='/inter/findByRid?rid=${recruit.rid}'">详情</button>
                        </td>
                        <td rowspan="3">
                            <button class="mybutton" type="button" onclick="delRecruit(${recruit.rid})">删除</button>
                        </td>
                    </tr>
                    <tr>
                        <td>发布时间：</td>
                        <td><fmt:formatDate value="${recruit.rstart}" pattern="yyyy-MM-dd"/></td>
                        <td>截止时间：</td>
                        <td><fmt:formatDate value="${recruit.rend}" pattern="yyyy-MM-dd"/></td>
                    </tr>
                </table>
                <br>
            </c:forEach>
            <div class="table-slipline"></div>
            <!--这是一条记录结束-->
            </c:if>
            <c:if test="${recruitList==null}">
                暂时没有招聘信息
            </c:if>
        </div>
        <div class="button-footer">
            <div class="right-button-footer">
                <%--<div id="Page">
                    <a href="#">«</a><span>1</span><a href="#">2</a><a href="#">3</a><a href="#">4</a><a href="#">5</a><a href="#">6</a><a href="#">»</a>
                </div>--%>
            </div>
            <div class="left-button-footer">
                <button type="submit" class="mybutton" value="Submit" onclick="window.open('/recruit/outputRecruit')">导出数据</button>
            </div>
        </div>
    </div>

    <div id="showRinfo">
        <div class="tab-close">
            <button class="mybutton" type="button" onclick="hideRinfo()">取消</button>
        </div>
        <div class="Rinfo">
            <p>招聘详情：</p>
            <div id="rinfo" style="width: 600px; background-color: white; margin-left: 100px;">

            </div>

        <%--<button class="mybutton" type="button" style="width: 200px;" onclick="hideRinfo()">确定</button>--%>
        </div>
    </div>
    <div id="zhezhaobg"></div>

</div>

</body>
</html>