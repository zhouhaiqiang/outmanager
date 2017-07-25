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
  
<script src="../localjs/report/yearquery.js"></script>

</head>


<body>
    <div class="panel-body" style="padding-bottom:0px;width:95%;font-size:12px">
        <div class="panel panel-default">
            <div class="panel-heading">查询条件（年报）</div>
            <div class="panel-body">
                <form id="formSearch" class="form-horizontal">
                    <div class="form-group" style="margin-top:10px">

                      <label class="control-label col-sm-1">报表名称</label>
						<div class="col-sm-3">
                        <select id="query_name" class="form-control" data-live-search="true" title="选择...">
                         	<option value="">选择...</option>
      					 	<option>1-1、全口径人员总量统计</option>
							<option>1-3、省级公司本部情况统计表</option>
							<option>1-4、地市公司本部情况统计表</option>
							<option>2-1、人员分类(一)</option>
							<option>2-2、人员分类(二)</option>
							<option>3-4、紧密型业务外包人员岗位分类</option>
							<option>3-5、各岗序列人员占比变动情况</option>
							<option>3-14、紧密型业务外包人员关键职责分类</option>
							<option>4-3、紧密型业务外包人员职位层级变动分析</option>
							<option>5-3、紧密型业务外包人员增减变动</option>
							<option>7、人员素质结构优化分析</option>	
                        </select>                    
                       	</div>
                       	
                       	
                     <label class="control-label col-sm-1">组织名称</label>
						<div class="col-sm-2">
                        <select id="query_unit" class="form-control" data-live-search="true" title="选择...">
                         	
                        <option value="">选择...</option> 	
						<option>山西省分公司</option>
						<option>山西省分公司本部</option>
						<option>太原市分公司</option>
						<option>晋中市分公司</option>
						<option>大同市分公司</option>
						<option>朔州市分公司</option>
						<option>忻州市分公司</option>
						<option>阳泉市分公司</option>
						<option>长治市分公司</option>
						<option>晋城市分公司</option>
						<option>吕梁市分公司</option>
						<option>临汾市分公司</option>
						<option>运城市分公司</option>		
                        </select>                    
                       	</div>  	 
                       	
                      <label class="control-label col-sm-1">查询日期<span style="color:red">*</span></label>
                      <div class="col-sm-2">
		      			<select id="query_date" class="form-control" data-live-search="false" title="选择...">
		      			</select>                      
                      </div>                        	
                       	                     
                                           
                        <!--------------------- 查询按钮区域---------------------- -->                   
						<div class="form-group" style="margin-left:5px;margin-top:10px">
                        <div class="col-sm-1">
                            <button type="button" style="margin-left:50px" id="btn_query" class="btn btn-danger">查询</button>
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