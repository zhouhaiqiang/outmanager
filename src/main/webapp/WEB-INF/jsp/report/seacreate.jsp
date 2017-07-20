<html>

<head>
<%@ page language="Java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!--  样式冲突，先不在公共jsp中使用，放到具体的页面上去 -->
<link rel="stylesheet" href="../static/bootstrap/css/bootstrap.min.css">    

<!-- 引入头文件 -->
<%@include file="../common/head.jsp" %>

<!-- 引入bootstrap-table控件 -->
<%@include file="../common/bootstrap-table.jsp" %>

<!-- 引入验证控件 -->
<%@include file="../common/validator.jsp" %>

<!-- 引入日期控件 -->
<%@include file="../common/datepicker.jsp" %>

<!-- 引入下拉控件 -->
<%@include file="../common/selectpicker.jsp" %>

<!-- 引入zTree控件 -->
<%@include file="../common/ztree.jsp" %>

<!-- 引入标签文件 -->
<%@include file="../common/tag.jsp" %>

<!--  页面私有js --> 
<script src="../localjs/common.js"></script>
  
<script src="../localjs/report/seacreate.js"></script>

</head>


<body>
    <div class="panel-body" style="padding-bottom:0px;width:95%;font-size:12px">
        <div class="panel panel-default">
            <div class="panel-heading">数据抽取条件（季报）</div>
            <div class="panel-body">
                <form id="formSearch" class="form-horizontal">
                    <div class="form-group" style="margin-top:10px">
    
                        <label class="control-label col-sm-1">组织称名</label>
						<div class="col-sm-2">
                           <input type="text" class="form-control" id="query_unit" readonly value="山西省分公司">                       
                       	</div>
                        
                         <label class="control-label col-sm-1">查询日期</label>
		      			<div class="input-group date form_date col-md-2" data-date="" data-date-format="yyyy-mm-dd">
		                    <input class="form-control" size="16" type="text" value="" id="query_date" name="query_date" >
							<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
		                </div>                      
                      </div>                      
                        <!--------------------- 查询按钮区域---------------------- -->                   
						<div class="form-group" style="margin-left:5px;margin-top:10px">
                        <div class="col-sm-1">
                            <button type="button" style="margin-left:50px" id="btn_query" class="btn btn-danger">生成报表</button>
                        </div>
                        <!--------------------- 查询按钮区域---------------------- -->

                    </div>
                </form>
            </div>
        </div>          
        <table id="tb_data"></table>                       
    </div>
</body>
</html>