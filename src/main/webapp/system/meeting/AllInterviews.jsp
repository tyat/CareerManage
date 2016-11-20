<%--
  Created by IntelliJ IDEA.
  User: w
  Date: 2016/11/1
  Time: 10:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        function  showMeetResult(){
            document.getElementById("showMeetResult").style.display="block";
            document.getElementById("zhezhaobg").style.display="block";
        }
        function  hideMeetResult(){
            document.getElementById("showMeetResult").style.display="none";
            document.getElementById("zhezhaobg").style.display="none";
        }
        function delInter(iid){
            var result = confirm('您确定要删除该条记录吗！');
            if(result){
                window.location.href="/inter/delInter2?iid="+iid;
            }else{
                alert('不删除！');
            }
        }
        function updateInter(iid) {
            $.ajax({
                type: "GET",
                url: "/inter/findResObjByIid?iid="+iid,
                dataType:"text",
                success: function(data){
                    var json = eval("("+data+")"); // data的值是json字符串，这行把字符串转成object
                    var json = JSON.parse( data );
                    $("#iid").attr("value",json.iid);
                    $("#sname").attr("value",json.sname);
                    $("#sid").attr("value",json.sid);
                    $("#isuccess option").removeAttr("selected"); //移除属性selected
                    $("#addcause").attr("display","null");
                    $("#mydiv").attr("display","null");
                    if(json.isuccess==0){
                        $("#isuccess0").attr("selected","selected");
                    }else if(json.isuccess==1){
                        $("#isuccess1").attr("selected","selected");
                    }else if(json.isuccess==2){
                        $("#isuccess2").attr("selected","selected");
                    }else{
                        $("#isuccess3").attr("selected","selected");
                    }
                    showMeetResult();
                },
                error: function(XMLHttpRequest, textStatus, errorThrown){
                    alert(XMLHttpRequest.status);
                    alert(XMLHttpRequest.readyState);
                    alert(textStatus);
                }
            });
        }
        function  onclickload() {
            var isuccess = document.getElementById("isuccess").value;
            if(isuccess==1){
                document.getElementById("mydiv").style.display ="";
                document.getElementById("addcause").style.display ="none";
            }else if(isuccess==2){
                document.getElementById("mydiv").style.display ="none";
                document.getElementById("addcause").style.display ="block";
            }else {
                document.getElementById("mydiv").style.display ="none";
                document.getElementById("addcause").style.display ="none";
            }
        }
        function  startload() {
            document.getElementById("mydiv").style.display ="none";
            document.getElementById("mydate").style.display ="none";
        }
        function  showDateInput() {
            var type = document.getElementById("type").value;
            if(type==3){
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
                    <span>信息管理</span><div class="left-arrow"></div><span>面试管理</span>
                </div> <br />
                <div class="Big-title">
                    <div class="littil-title">
                        面试信息shuliang${dayinter}
                    </div>
                    <div class="search-box">
                        <form action="/inter/query2" method="post" name="search" id="search">
                            <input type="hidden" name="rid" value="${interList.get(0).rid }">
                            <table style="background: none; margin-left: 400px;">
                                <tr>
                                    <td>
                                        <select name="type"id="type" onchange="showDateInput();">
                                            <option value="0">按学生姓名</option>
                                            <option value="1">按学生学号</option>
                                            <option value="2">按面试企业名称</option>
                                            <option value="3">按面试时间</option>
                                        </select>
                                    </td>
                                    <td id="mysearch">
                                        <input type="text" name="searchtext" id="searchtext" placeholder="请输入……"/>
                                    </td>
                                    <td id="mydate">
                                        <input type="text" id="rstart" name="searchtext" style="width: 80px;" onclick="choose_date_czw('rstart');"/>
                                        <input type="text" id="rend" name="date" style="width: 80px;" onclick="choose_date_czw('rend');"/>
                                    </td>
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
        <div>
            <c:if test="${interList!=null}">
                <!--这是表格开始-->
                <table  class="pure-table pure-table-bordered">
                    <tr>
                        <td>姓名</td>
                        <td>学号</td>
                        <td>面试企业</td>
                        <td>竞聘岗位</td>
                        <td>面试时间</td>
                        <td>面试城市</td>
                        <td>面试地点</td>
                        <td>面试方式</td>
                        <td>面试状态</td>
                        <td  colspan="2">
                            操作
                        </td>
                    </tr>

                    <c:forEach var="inter" items="${interList}">
                        <input type="hidden" name="rid" value="${inter.rid }">
                        <!--这是一条记录开始-->
                        <tr>
                            <td><a href="/student/findBySid?sid=${inter.sid}">${inter.sname}</a></td>
                            <td>${inter.sno}</td>
                            <td><a href="/company/findByCompCid?cid=${inter.cid}">${inter.cname}</a></td>
                            <td>${inter.jname}</td>
                            <td><fmt:formatDate value="${inter.itime}" pattern="yyyy-MM-dd"/></td>
                            <td>${inter.aprovince}${inter.acity}</td>
                            <td>${inter.iaddress}</td>
                            <td>${inter.itype}</td>
                            <td>
                                <c:if test="${inter.isuccess==0}">
                                    准备面试
                                </c:if>
                                <c:if test="${inter.isuccess==1}">
                                    面试成功（已就业）
                                </c:if>
                                <c:if test="${inter.isuccess==2}">
                                    面试成功（未就业）
                                </c:if>
                                <c:if test="${inter.isuccess==3}">
                                    面试未通过
                                </c:if>
                            </td>
                            <td>
                                <button id="editInter" class="mybutton" type="button" onclick="updateInter(${inter.iid});">编辑</button>
                            </td>
                            <td>
                                <button class="mybutton" type="button" onclick="delInter(${inter.iid})">删除</button>
                            </td>
                        </tr>
                        <!--这是一条记录结束-->
                    </c:forEach>
                </table>
                <div class="table-slipline"></div>
                <!--这是表格结束-->
            </c:if>
            <c:if test="${interList==null}">
                暂时没有面试记录
            </c:if>
        </div>
        <div class="button-footer">
            <div class="right-button-footer">
                <%--<div id="Page">
                    <a href="#">«</a><span>1</span><a href="#">2</a><a href="#">3</a><a href="#">4</a><a href="#">5</a><a href="#">6</a><a href="#">»</a>
                </div>--%>
            </div>
            <div class="left-button-footer">
                <button type="submit" class="mybutton" value="Submit" onclick="window.open('/inter/outputInter')">导出数据</button>
            </div>
        </div>
    </div>

    <div id="showMeetResult">
        <div class="tab-close">
            <button class="mybutton" type="button" onclick="hideMeetResult()">取消</button>
        </div>
        <div class="MeetResult">
            <p>修改面试结果：</p>
            <form action="/inter/updateInter2" method="post">
                <table class="pure-table pure-table-bordered">
                    <input type="hidden" name="iid"id="iid">
                    <tr>
                        <td>姓名：</td>
                        <td><input type="text" name="sname" id="sname" disabled="disabled"/></td>
                    </tr>
                    <tr>
                        <td>面试结果：</td>
                        <td>
                            <select name="isuccess" id="isuccess" onchange="onclickload()">
                                <option id="isuccess0" value="0">准备面试</option>
                                <option id="isuccess1" value="1">面试成功（已就业）</option>
                                <option id="isuccess2" value="2">面试成功（未就业）</option>
                                <option id="isuccess3" value="3">面试未通过</option>
                            </select>
                        </td>
                    </tr>
                </table>
                <div id="addcause">
                    <table class="pure-table pure-table-bordered">
                        <tr>
                            <td>未就业原因:</td>
                            <td><input type="text" name="isuccleave" id="isuccleave" /></td>
                        </tr>
                    </table>
                </div>
                <div id="mydiv">
                    <table class="pure-table pure-table-bordered">
                        <input type="hidden" name="sid" id="sid"/>
                        <tr>
                            <td>薪资：</td>
                            <td>
                                <input type="text" id="esalary" name="esalary" value="${findEmpBySid.esalary}"  onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" />
                            </td>
                        </tr>
                        <tr>
                            <td>实习时间：</td>
                            <td>
                                <input   required="required" type="text" id="etime" name="etime" onclick="choose_date_czw('etime')"/>
                            </td>
                        </tr>
                        <tr>
                            <td>是否网签：</td>
                            <td>
                                <select id="ewq" name="ewq">
                                    <option id="ewq1" value="1" selected="selected">是</option>
                                    <option id="ewq2" value="0">否</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td>推荐人：</td>
                            <td>
                                <select id="uid" name="uid">
                                    <c:forEach items="${userList}" var="s" varStatus="stu">
                                        <c:if test="${s.uid!=0}">
                                            <option id="uids" value="${s.uid}">${s.urname}</option>
                                        </c:if>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td>备注:</td>
                            <td>
                                <textarea cols="19" rows="4" id="einfo" name="einfo">这里是备注信息...</textarea>
                            </td>
                        </tr>
                    </table>
                </div>
                <table class="pure-table pure-table-bordered">
                    <tr>
                        <td colspan="2">
                            <button class="mybutton" type="button" style="width: 200px;" onclick="this.form.submit()">保存</button>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>

</div>
</body>
</html>