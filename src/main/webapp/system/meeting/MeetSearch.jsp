<%--
  Created by IntelliJ IDEA.
  User: w
  Date: 2016/11/1
  Time: 10:40
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
</head>
<body>
<div class="table-box">
    <div class="table-content">
        <!--这是标题栏-->
        <div class="table-head">
            <div class="table-address">
                <div style="float: left;">
                    <span>信息管理</span><div class="left-arrow"></div>
                    <span>面试管理</span><div class="left-arrow"></div>
                    <span>搜索结果</span>
                </div> <br />
                <div class="Big-title">
                    <div class="littil-title">
                        面试信息
                    </div>
                    <div class="search-box">
                        <select>
                            <option value="1">按企业名称</option>
                            <option value="2">按面试时间</option>
                        </select>
                        <input type="text" name="searchtext"  value="输入字符"/>
                        <button class="mybutton" type="button" onclick="alert('搜索成功');location='CompSearch.html'"> <span>搜索</span> </button>
                        <button class="mybutton" type="button" onclick="JavaScript :history.back(-1)">
                            返回上一页
                        </button>
                    </div>
                </div>
            </div>
        </div>
        <!--这是标题栏结束-->
        <div>
            <!--这是一条记录开始-->
            <table  class="pure-table pure-table-bordered left">
                <tr>
                    <td >企业名称</td>
                    <td width="130px">竞聘岗位：</td>
                    <td>@java开发</td>
                    <td width="130px">联系人：</td>
                    <td>孟梦</td>
                    <td rowspan="3">
                        <button class="mybutton" type="button" onclick="location='MeetUpdate.html'">编辑</button>
                    </td>
                </tr>
                <tr>
                    <td rowspan="5"><a href="../company/CompSearch.html">中兴济南公司</a></td>
                    <td>面试时间：</td>
                    <td>2016-11-15 13时30分</td>
                    <td>联系电话：</td>
                    <td>58877668</td>
                </tr>
                <tr>
                    <td>月薪：</td>
                    <td>2000 RMB</td>
                    <td>性别要求：</td>
                    <td>男</td>
                </tr>
                <tr>
                    <td>面试方式：</td>
                    <td>面试、笔试、电话面试</td>
                    <td>报名学生：</td>
                    <td>
                        <button class="mybutton" type="button" onclick="location='/inter/findByRid?rid=1'">1人</button>
                    </td>
                    <td rowspan="3">
                        <button class="mybutton" type="button" onclick="AreYouSour()">删除</button>
                    </td>
                </tr>
                <tr>
                    <td>面试地点：</td>
                    <td>济南xxx3号楼1楼101室</td>
                    <td>招聘人数：</td>
                    <td>2人</td>
                </tr>
                <tr>
                    <td>发布时间：</td>
                    <td>2016-10-10</td>
                    <td>截止时间：</td>
                    <td>2016-12-12</td>
                </tr>
            </table>
            <div class="table-slipline"></div>
            <!--这是一条记录结束-->
        </div>
        <div class="button-footer">
            <div class="right-button-footer">
                <div id="Page">
                    <a href="#">«</a><span>1</span><a href="#">2</a><a href="#">3</a><a href="#">4</a><a href="#">5</a><a href="#">6</a><a href="#">»</a>
                </div>
            </div>
            <div class="left-button-footer">
                <button class="mybutton" type="button" onclick="alert('弹出保存对话框')"> <span>批量导出数据</span></button>
            </div>
        </div>
    </div>



</body>
</html>
