<!DOCTYPE html>
<html lang="zh-CN">

<head>
<%@ page language="Java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!--  样式冲突，先不在公共jsp中使用，放到具体的页面上去 -->
<link rel="stylesheet" href="../static/bootstrap/css/bootstrap.min.css">    

<!-- 引入头文件 -->
<%@include file="../common/head.jsp" %>

<!-- 引入bootstrap-table控件 -->
<%@include file="../common/bootstrap-table.jsp" %>


<!--  页面私有js --> 
<script src="../localjs/common.js"></script>
  
<script src="../localjs/report/${jsfile}.js"></script>

</head>


<body>
    <div class="panel-body" style="padding-bottom:0px;width:100%;font-size:9px">  
    <input type='hidden' id='id' value='${report.id}'>
    <input type='hidden' id='name' value='${report.name}'> 
    <input type='hidden' id='unit' value='${report.unit}'>
    <input type='hidden' id='repdate' value='${repdate}'>  

    <table id="tb_data"></table>                       
    </div>
</body>
</html>