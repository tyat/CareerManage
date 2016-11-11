<%--
  Created by IntelliJ IDEA.
  User: w
  Date: 2016/11/11
  Time: 9:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" type="text/css" href="../../css/default.css"/>
    <script src="../../js/showele.js" type="text/javascript" charset="utf-8"></script>
    <link rel="stylesheet" href="../../css/icon.css" />
    <script type="text/javascript" src="../../js/showele.js" ></script>

</head>
<body onload="Activeli()">
<div class="table-box">
    <div class="table-content">
        <div class="table-head">
            <div class="table-address">
                <div style="float: left;">
                    <span>信息管理</span><div class="left-arrow"></div>
                    <span>批量导入学生信息</span>
                </div><br />
                <div class="Big-title">
                    <div class="littil-title">
                        批量导入学生信息
                    </div>
                    <div class="search-box">
                        <button class="mybutton" type="button" onclick="alert('弹出下载框！')"> <span>下载Excel模板</span></button>
                    </div>
                </div>
            </div>
        </div>
        <div>
            <!--这是表格开始-->
            <table  class="pure-table pure-table-bordered" style="text-align: left;">
                <tr>
                    <td>姓名</td>
                    <td>性别</td>
                    <td>年级</td>
                    <td>班级</td>
                </tr>
                <!--这是一条记录开始-->
                <!--<tr>
                    <td>
                        <a href="StudentInfo.html">王新</a>
                    </td>
                    <td>女</td>
                    <td><a href="StudentsSearch.html">2013</a></td>
                    <td><a href="StudentsSearch.html">计算机4</a></td>
                </tr>-->
                <!--这是一条记录结束-->
                <tr>
                    <td colspan="4" style="text-align: center;">
                        <button class="mybutton" type="button" onclick="ShowUpload()" style="width: 300px;"> <span>导入数据</span></button>
                    </td>
                </tr>
            </table>
            <div class="table-slipline"></div>
            <!--这是表格结束-->

        </div>
    </div>
    <div class="button-footer">

        <div class="right-button-footer">
            <div id="Page">
                <a href="#">«</a>
                <span>1</span>
                <a href="#">2</a>
                <a href="#">3</a>
                <a href="#">4</a>
                <a href="#">5</a>
                <a href="#">6</a>
                <a href="#">»</a>
            </div>
        </div>

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

