<%--
  Created by IntelliJ IDEA.
  User: w
  Date: 2016/11/1
  Time: 10:37
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
    <script src="../../js/jquery.min.js" type="text/javascript" charset="utf-8"></script>

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
                    alert(data);
                    var json = eval("("+data+")"); // data的值是json字符串，这行把字符串转成object
                    var json = JSON.parse( data );
                    showMeetResult();
                    $("#iid").attr("value",json.iid);
                    $("#sname").attr("value",json.sname);
                    if(json.isuccess==0){
                        $("#isuccess0").attr("selected","selected");
                    }else if(json.isuccess==1){
                        $("#isuccess1").attr("selected","selected");
                    }else if(json.isuccess==2){
                        $("#isuccess2").attr("selected","selected");
                    }else{
                        $("#isuccess3").attr("selected","selected");
                    }
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
<body>
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
                        面试信息
                    </div>
                    <div class="search-box">
                        <form action="/inter/query2" method="post">
                            <select name="type">
                                <option value="0">按学生姓名</option>
                                <option value="1">按学生学号</option>
                                <option value="2">按面试企业名称</option>
                                <%--<option value="3">按面试时间</option>--%>
                            </select>
                            <input type="text" name="searchtext"  placeholder="请输入……"/>
                            <button class="mybutton" type="button" onclick="this.form.submit()"> <span>搜索</span> </button>
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
                        <td>面试结果</td>
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
                            <td>${inter.itime}</td>
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
                <div id="Page">
                    <a href="#">«</a><span>1</span><a href="#">2</a><a href="#">3</a><a href="#">4</a><a href="#">5</a><a href="#">6</a><a href="#">»</a>
                </div>
            </div>
            <div class="left-button-footer">
                <button class="mybutton" type="button" onclick="alert('弹出保存对话框')"> <span>批量导出数据</span></button>
                <button class="mybutton" type="button" onclick="alert('弹出文件上传框')"> <span>批量导入数据</span></button>
                <button class="mybutton" type="button" onclick="ShowDetailTip()"> <span>下载Excel模板</span></button>
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
                    <input type="hidden" name="iid" id="iid">
                    <tr>
                        <td>姓名：</td>
                        <td><input type="text" name="sname" id="sname" disabled="disabled"/></td>
                    </tr>
                    <tr>
                        <td>面试结果：</td>
                        <td>
                            <select name="isuccess" id="isuccess">
                                <option id="isuccess0" value="0">准备面试</option>
                                <option id="isuccess1" value="1">面试成功（已就业）</option>
                                <option id="isuccess2" value="2">面试成功（未就业）</option>
                                <option id="isuccess3" value="3">面试未通过</option>
                            </select>
                        </td>
                    </tr>
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