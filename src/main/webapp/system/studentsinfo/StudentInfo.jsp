<%--
  Created by IntelliJ IDEA.
  User: w
  Date: 2016/10/26
  Time: 16:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
    <script type="text/javascript" src="../../js/showele.js" ></script>
    <link rel="stylesheet" type="text/css" href="../../css/default.css"/>
    <link rel="stylesheet" href="../../css/icon.css" />
    <script src="../../js/jquery.min.js" type="text/javascript" charset="utf-8"></script>

<script type="text/javascript">
    function  showStuUpdate(){
        document.getElementById("StuUpdate").style.display="block";
        document.getElementById("zhezhaobg").style.display="block";
    }
    function  hideStuUpdate(){
        document.getElementById("StuUpdate").style.display="none";
        document.getElementById("zhezhaobg").style.display="none";
    }
    function hideUpload(){
        document.getElementById("showupload-div").style.display="none";
        document.getElementById("zhezhaobg").style.display="none";
    }
    function updateStudent(sid) {
        $.ajax({
            type: "GET",
            url: "/student/updateStudentPro?sid="+sid,
            dataType:"text",
            success: function(data){
                var json = eval("("+data+")"); // data的值是json字符串，这行把字符串转成object
                var json = JSON.parse( data );
                showStuUpdate();
                $("#sid").attr("value",json.sid);
                $("#sname").attr("value",json.sname);
                $("#sgrade").attr("value",json.sgrade);
                $("#sclass").attr("value",json.sclass);
                $("#sphone").attr("value",json.sphone);
                $("#semail").attr("value",json.semail);
                $("#sstate option").removeAttr("selected"); //移除属性selected
                if(json.sstate==0){
                    $("#sstate0").attr("selected","selected");
                }else if(json.sstate==2){
                    $("#sstate1").attr("selected","selected");
                }else if(json.sstate==3) {
                    $("#sstate2").attr("selected", "selected");
                }
            },
            error: function(XMLHttpRequest, textStatus, errorThrown){
                alert(XMLHttpRequest.status);
                alert(XMLHttpRequest.readyState);
                alert(textStatus);
            }
        });
    }
    function delStudent(sid){
        var result = confirm('您确定要删除该条记录吗！');
        if(result){
            window.location.href="/student/delStudent?sid="+sid;
            //alert(${ResMsg});
        }else{
            alert('不删除！');
        }
    }
    var msg="${ResMsg}";
    if(msg!=""){
        alert(msg);
    }
    function  onclickload() {
        var sstate = document.getElementById("sstate").value;
        if(sstate==2){
            document.getElementById("addcause").style.display ="block";
        }else {
            document.getElementById("addcause").style.display ="none";
        }
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
                    <span>学生信息</span><div class="left-arrow"></div>
                    </div><br />
                <div class="Big-title">
                    <div class="littil-title">
                        学生信息
                    </div>
                    <div class="search-box">
                        <button class="mybutton" type="button" onclick="JavaScript :history.back(-1)">
                            返回上一页
                        </button>
                    </div>
                </div>
            </div>
        </div>
        <div>
            <!--这是一条记录开始-->
            <table  class="pure-table pure-table-bordered" style="text-align: left;">
                <tr>
                    <td>姓名：</td>
                    <td>${student.sname}</td>
                    <td>年级：</td>
                    <td>${student.sgrade}</td>
                    <td>性别：</td>
                    <td>
                        <c:if test="${!(student.ssex)}">
                            男
                        </c:if>
                        <c:if test="${student.ssex}">
                            女
                        </c:if>
                    </td>
                    <td rowspan="5">
                        <button class="mybutton" type="button" onclick="updateStudent(${student.sid});" >编辑</button>
                    </td>
                </tr>
                <tr>
                    <td>学号：</td>
                    <td>${student.sno}</td>
                    <td>班级：</td>
                    <td>${student.spro}${student.sclass}班</td>
                    <td>出生年份：</td>
                    <td>${student.sbirth}</td>
                </tr>
                <tr>
                    <td>联系方式：</td>
                    <td>${student.sphone}</td>
                    <td>邮箱：</td>
                    <td>${student.semail}</td>
                    <td>身份证号：</td>
                    <td>${student.scode}</td>

                </tr>
                <tr>
                    <td>是否已就业：</td>
                    <td>
                       <c:if test="${isemp}">
                            是
                       </c:if>
                        <c:if test="${!isemp}">
                            否
                        </c:if>
                    </td>
                    <td>就业企业：</td>
                    <td>
                        <c:if test="${isemp}">
                            <a href="/company/findByCompCid?cid=${student.cid}">
                                 ${student.cname}
                            </a>
                        </c:if>
                        <c:if test="${!isemp}">
                            无
                        </c:if>
                    </td>
                    <td>岗位：</td>
                    <td>
                        <c:if test="${isemp}">
                             ${student.jname}
                        </c:if>
                        <c:if test="${!isemp}">
                             无
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <td>参加的面试：</td>
                    <td colspan="2">
                        ${InterTimes}次 &nbsp;
                        <button class="mybutton" type="button" onclick="location='/student/findInterBySid?sid=${student.sid}'"  >查看详情</button>
                    </td>
                    <td>学生详细信息：</td>
                    <td colspan="2">
                        <button class="mybutton" type="button" onclick="location='/grade/findStudentDetail?sid=${student.sid}'"  >查看详情</button>
                    </td>
                </tr>
            </table>
            <div class="table-slipline"></div>
            <!--这是一条记录结束-->

        </div>
    </div>
    <div class="button-footer">
        <div class="left-button-footer">
            <button class="mybutton" type="button" onclick="alert('弹出下载框')"> <span>导出数据</span></button>

        </div>
    </div>
</div>
<div id="showupload-div">
    <div class="tab-close">
        <button class="mybutton" type="button" onclick="hideUpload()">关闭</button>
    </div>
    <iframe src="../tools/UploadExcel.html" width="810px" height="340px" frameborder="0"></iframe>
</div>

<div id="StuUpdate">
    <div class="tab-close">
        <button class="mybutton" type="button" onclick="hideStuUpdate()">取消</button>
    </div>
    <p>修改学生信息：</p>
    <form action="/student/updateStudent" method="post">
        <table  class="pure-table pure-table-bordered">
            <input type="hidden" name="sid" id="sid" >
            <tr>
                <td>姓名：</td>
                <td>
                    <input type="text" name="sname" id="sname" disabled="disabled" />
                </td>
            </tr>
            <tr>
                <td>联系电话：</td>
                <td>
                    <input type="text" name="sphone" id="sphone" />
                </td>
            </tr>
            <tr>
                <td>邮箱：</td>
                <td>
                    <input type="text" name="semail" id="semail" />
                </td>
            </tr>
            <tr>
                <td>状态：</td>
                <td>
                    <select name="sstate" id="sstate" onchange="onclickload()">
                        <option id="sstate0" value="0">正常</option>
                        <option id="sstate1" value="2">留级</option>
                        <option id="sstate2" value="3">休学</option>
                    </select>
                </td>
            </tr>
        </table>
        <div id="addcause">
            <table class="pure-table pure-table-bordered" style="margin-top: 0px;">
                <tr>
                    <td>年级：</td>
                    <td>
                        <input type="text" name="sgrade" id="sgrade" />
                    </td>
                </tr>
                <tr>
                    <td>班级：</td>
                    <td>
                        <input type="text" name="sclass" id="sclass" />
                    </td>
                </tr>
            </table>
        </div>
        <table class="pure-table pure-table-bordered" style="margin-top: 0px;">
            <tr>
                <td colspan="2" style="text-align: center;">
                    <button class="mybutton" type="button" style="width: 200px; " onclick="this.form.submit()">保存</button>
                </td>
            </tr>
        </table>
    </form>
</div>
<div id="zhezhaobg"></div>
</body>
</html>

