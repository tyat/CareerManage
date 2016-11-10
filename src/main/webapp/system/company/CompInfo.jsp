<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 2016/11/8
  Time: 9:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.*" isELIgnored="false" %>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" type="text/css" href="../../css/default.css"/>
    <link rel="stylesheet" href="../../css/icon.css" />
    <script type="text/javascript" src="../../js/showele.js" ></script>
</head>
<body>
<div class="table-box">
    <c:forEach varStatus="i" var="lsit" items="${dateList}" >
    <div class="table-content">
        <!--这是标题栏-->
        <div class="table-head">
            <div class="table-address">
                <div style="float: left;">
                    <span>信息管理</span><div class="left-arrow"></div>
                    <span><a href="selecteAllC.html">企业管理</a></span><div class="left-arrow"></div>
                    <span>企业详细信息</span>
                </div><br />
                <div class="Big-title">
                    <div class="littil-title">
                        企业详细信息
                    </div>
                    <div class="search-box">
                        <button class="mybutton" type="button" onclick="JavaScript :history.back(-1)">
                            返回上一页
                        </button>
                    </div>
                </div>
            </div>
        </div>
        <!--这是标题栏结束-->
        <div>
            <!--这是表格开始-->

            <table  class="pure-table pure-table-bordered CompInfo1" style="text-align: left;">
                <tr>
                    <td>企业名称</td>
                    <td width="130px">岗位标签：</td>
                    <td colspan="3">这是放岗位标签的</td>
                </tr>
                <tr>
                    <td rowspan="7">${lsit.cname}</td>
                    <td>联系人：</td>
                    <td colspan="3">${lsit.chr}</td>

                </tr>
                <tr>
                    <td>联系电话：</td>
                    <td colspan="3">${lsit.cphone}</td>
                </tr>
                <tr>
                    <td>在岗学生人数：</td>
                    <td colspan="3">
                        <button class="mybutton" type="button" onclick="location='ThisCompEmp.html'" title="查看">2人</button>
                    </td>
                </tr>
                <tr>
                    <td>详细地址：</td>
                    <td colspan="3">${lsit.caddress}</td>
                </tr>
                <tr>
                    <td>邮箱：</td>
                    <td colspan="3">${lsit.cemail}</td>
                </tr>
                <tr>
                    <td>简介：</td>
                    <td colspan="3"><button class="mybutton" type="button" onclick="ShowDetailInfo()">查看简介</button>
                    </td>
                </tr>
                <tr>
                    <td>备注：</td>
                    <td colspan="3"><button class="mybutton" type="button" onclick="ShowDetailTip()">查看备注</button>
                    </td>
                </tr>

            </table>

            <div class="table-slipline"></div>
            <!--这是表格结束-->
        </div>
        <div class="button-footer">

            <div class="left-button-footer">
                <button class="mybutton" type="button" onclick="alert('弹出下载框！')"> 导出数据</button>
                <button class="mybutton" type="button" onclick="location='/company/findCompByCid?cid=${lsit.cid}'">修改</button>
            </div>
        </div>
    </div></c:forEach>

    <!---------------------------------------------------------------------------->

    <!--这是查看备注详情功能div-->
    <div id="show-detail-tip" class="show-detail-tip">
        <div class="tab-close">
            <button class="mybutton" type="button" onclick="HideDetailTip()">关闭</button>
        </div>
        <div class="tip-box">
        <c:forEach varStatus="i" var="lsit" items="${dateList}" >
            <div class="tip-header">
                <div id="icon-tipdetail"></div>
                <p>备注详情：中兴济南公司 </p>
            </div>
            <div class="tip-content">
                <form>
                    <textarea id="beizhu" name=""  cols="80" rows="5"  disabled="disabled">${lsit.cmark}</textarea><br />
                    <div class="buttonbox">
                        <input type="button" value="修改" class="mybutton" onclick="beginBeizhu()"/>
                        <input type="submit" value="保存" class="mybutton" />
                    </div>
                </form>
            </div>
            </c:forEach>
        </div>
    </div>
    <!--这是查看备注详情功能div-->
    <!--这是查看简介详情功能div-->
    <div id="show-detail-info" class="show-detail-info">
        <div class="tab-close">
            <button class="mybutton" type="button" onclick="HideDetailInfo()">关闭</button>
        </div>
        <div class="tip-box">
        <c:forEach varStatus="i" var="lsit" items="${dateList}" >
            <div class="tip-header">
                <div id="icon-tipdetail"></div>
                <p>简介详情：中兴济南公司 </p>
            </div>
            <div class="tip-content">
                <form action="" name="tip-detadil">
					<textarea id="jianjie" name="" cols="80" rows="5" disabled="disabled">${lsit.cinfo}
					</textarea><br /><br /><br />
                    <input type="button" value="修改" class="mybutton" onclick="BeginJianjie()"/>
                    <input type="submit" class="mybutton" value="保存" />
                </form>
            </div>
            </c:forEach>
        </div>
    </div>
    <!--这是查看简介详情功能div-->

    <div id="zhezhaobg"></div>
</div>
</body>
</html>

