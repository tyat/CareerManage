<%--
  Created by IntelliJ IDEA.
  User: w
  Date: 2016/11/1
  Time: 10:39
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
                    <span>信息管理</span><div class="left-arrow"></div><span>面试管理</span>
                </div> <br />
                <div class="Big-title">
                    <div class="littil-title">
                        面试参与人员
                    </div>
                    <div class="search-box">
                        <form action="/student/query" method="post">
                            <select name="type">
                                <option value="0">按学生姓名</option>
                                <option value="1">按年级</option>
                                <option value="2">按专业</option>
                            </select>
                            <input type="text" name="searchtext"  value="请输入……"/>
                            <button class="mybutton" type="button" onclick="this.form.submit()"> <span>搜索</span> </button>
                            <button class="mybutton" type="button" onclick="JavaScript :history.back(-1)">
                                返回上一页
                            </button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <!--这是标题栏结束-->
        <div>
            <!--这是表格开始-->
            <table  class="pure-table pure-table-bordered">
                <tr>
                    <td>姓名</td>
                    <td>联系方式</td>
                    <td>性别</td>
                    <td>年级</td>
                    <td>班级</td>
                    <td>出生年月</td>
                    <td>面试结果</td>
                    <td  colspan="2">
                        <button class="mybutton" style="width: 100px;" type="button" onclick="ShowAddStu()">添加</button>
                    </td>
                </tr>
                <!--这是一条记录开始-->
                <tr>
                    <td><a href="../studentsinfo/StudentInfo.html">王新</a></td>
                    <td>15953102060</td>
                    <td>女</td>
                    <td>2013</td>
                    <td>计算机4</td>
                    <td>1994-08</td>
                    <td>面试成功（已就业）</td>
                    <td>
                        <button class="mybutton" type="button" onclick="ShowMeetResult()">编辑</button>
                    </td>
                    <td>
                        <button class="mybutton" type="button" onclick="AreYouSour()">删除</button>
                    </td>
                </tr>
                <!--这是一条记录结束-->
                <!--这是一条记录开始-->
                <tr>
                    <td><a href="../studentsinfo/StudentInfo.html">张丽</a></td>
                    <td>15953102332</td>
                    <td>女</td>
                    <td>2013</td>
                    <td>计算机4</td>
                    <td>1993-08</td>
                    <td>面试成功（已就业）</td>
                    <td>
                        <button class="mybutton" type="button" onclick="ShowMeetResult()">编辑</button>
                    </td>
                    <td>
                        <button class="mybutton" type="button" onclick="AreYouSour()">删除</button>
                    </td>
                </tr>
                <!--这是一条记录结束-->
            </table>
            <div class="table-slipline"></div>
            <!--这是表格结束-->
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

    <div id="showAddstu-div">
        <div class="tab-close">
            <button class="mybutton" type="button" onclick="HideAddStu()">取消</button>
        </div>
        <div class="search-boxcenter">
            <div class="center-box">
                <input type="text" name="searchtext"  value="" placeholder="请输入学号进行搜索...."/>
                <button class="mybutton" type="button" onclick="ShowSearchResult()"> <span>搜索</span> </button>
            </div>
        </div><br />
        <hr />
        <div id="search-result">
            <table  class="pure-table pure-table-bordered">
                <tr>
                    <td>姓名</td>
                    <td>学号</td>
                    <td>班级</td>
                    <td>年级</td>
                    <td>性别</td>
                    <td></td>
                </tr>
                <tr>
                    <td>王新</td>
                    <td>201303204423</td>
                    <td>计算机4</td>
                    <td>2013</td>
                    <td>女</td>
                    <td>
                        <button class="mybutton" type="button" style="width: 200px;">选择</button>
                    </td>
                </tr>
            </table>
        </div>
    </div>
    <div id="showMeetResult">
        <div class="tab-close">
            <button class="mybutton" type="button" onclick="HideMeetResult()">取消</button>
        </div>
        <div class="MeetResult">
            <p>修改面试结果：</p>
            <table  class="pure-table pure-table-bordered">
                <tr>
                    <td>姓名：</td>
                    <td>王新</td>
                </tr>
                <tr>
                    <td>面试结果：</td>
                    <td>
                        <select>
                            <option>准备面试</option>
                            <option>面试成功（已就业）</option>
                            <option>面试成功（未就业）</option>
                            <option>面试未通过</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <button class="mybutton" type="button" style="width: 200px;">保存</button>
                    </td>
                </tr>
            </table>
        </div>
    </div>
    <div id="zhezhaobg"></div>

</body>
</html>

