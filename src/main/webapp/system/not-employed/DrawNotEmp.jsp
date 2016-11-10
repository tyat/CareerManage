<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/11/6
  Time: 17:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"  %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="../../css/default.css"/>
    <link rel="stylesheet" type="text/css" href="../../css/icon.css"/>
    <script type="text/javascript" src="../../js/canvarsDraw.js" ></script>

    <script>
        window.onload = function() {
            var canvas = document.getElementById("pie_canvas");
            var seriesData = [
                {name:"准备就业", value:${index0}, color:"RGBA(255,0,0,1)"},
                {name:"考公务员", value:${index1}, color:"RGBA(255,255,0,1)"},
                {name:"考研", value:${index2}, color:"RGBA(0,0,255,1)"},
                {name:"考事业编", value:${index3}, color:"RGBA(255,0,255,1)"},
                {name:"教师编", value:${index4}, color:"RGBA(0,255,255,1)"},
                {name:"保研", value:${index5}, color:"RGBA(0,127,255,1)"}]
            var config = {
                width : 475,
                height: 400,
                series: seriesData,
                canvas: canvas,
                unit: "kg",
                title:"2013级学生非就业情况分布",
                tooltips : {
                    enable : true
                },
                animation :{
                    enable: true
                },
                legend : {
                    enable : true
                },
                text : {
                    enable: true
                },
            };
            pieChart.initSettings(config);
            pieChart.render();
        }
    </script>
</head>
<body>
<div class="table-content">
    <div class="table-head">
        <div class="table-address">
            <span>统计分析</span><i style="color: #317EB4;">>></i><span>未就业生</span>
        </div>
    </div>
    <div class="big-canvar-box">
        <div class="canvars-box">
            <div id="my_container" style="width:95%; height:95%;">
                <canvas id="pie_canvas"></canvas>
            </div>
        </div>
        <div class="canvars-box">
            <div id="my_container2" style="width:95%; height:95%;">
                <canvas id="pie_canvas2"></canvas>
            </div>
        </div>
        <div class="canvars-box">
            <div id="my_container3" style="width:95%; height:95%;">
                <canvas id="pie_canvas3"></canvas>
            </div>
        </div>
        <div class="canvars-box">
            <div id="my_container4" style="width:95%; height:95%;">
                <canvas id="pie_canvas4"></canvas>
            </div>
        </div>
    </div>
</div>

</body>
</html>
