<%--
  Created by IntelliJ IDEA.
  User: w
  Date: 2016/11/1
  Time: 10:41
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
    <script type="text/javascript" src="../../js/Date.js" ></script>
    <script src="../../js/addtime.js" type="text/javascript"></script>
    <script type="text/javascript" src="../../js/upload.js" ></script>
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
                        添加面试信息
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
            <form>
                <table  class="pure-table pure-table-bordered" style="text-align:left">
                    <tr>
                        <td>企业名称：</td>
                        <td>
                            <select style=" width:175px;">
                                <option></option>
                                <option>济南中兴通讯</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>联系人:</td>
                        <td><input type="text" value="孟梦"/></td>

                    </tr>
                    <tr>
                        <td>联系电话：</td>
                        <td>
                            <input type="text" value=" "/>
                        </td>
                    </tr>
                    <tr>
                        <td>竞聘岗位：</td>
                        <td>
                            <select>
                                <option>@web前端</option>
                                <option>@java开发</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>月薪：</td>
                        <td>
                            <input type="text" value=" " />
                        </td>
                    </tr>
                    <tr>
                        <td >性别要求：</td>
                        <td >
                            <input type="radio" name="sex" value="0">男
                            <input type="radio" name="sex" value="1">女
                        </td>
                    </tr>
                    <tr>
                        <td >招聘人数：</td>
                        <td ><input type="text" value=" "/></td>
                    </tr>

                    <tr>
                        <td>截止时间：</td>
                        <td >
                            <input type="text" id="add_date" onclick="choose_date_czw('add_date');"/>
                        </td>
                    </tr>
                    <tr>
                        <td>面试地点：</td>
                        <td>
                            <input type="text" value=" " />
                        </td>
                    </tr>
                    <tr>
                        <td >面试方式：</td>
                        <td ><input type="text" value=" "/></td>
                    </tr>
                    <tr>
                        <td>面试时间：</td>
                        <td >
                            <input type="text" id="add_date" onclick="choose_date_czw('add_date');"/>
                            <select id="add_time"></select> <select id="add_time2"></select>
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
