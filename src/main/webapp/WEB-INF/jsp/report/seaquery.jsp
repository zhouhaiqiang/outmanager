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
  
<script src="../localjs/report/seaquery.js"></script>

</head>


<body>
    <div class="panel-body" style="padding-bottom:0px;width:95%;font-size:12px">
        <div class="panel panel-default">
            <div class="panel-heading">查询条件（季报）</div>
            <div class="panel-body">
                <form id="formSearch" class="form-horizontal">
                    <div class="form-group" style="margin-top:10px">

                      <label class="control-label col-sm-1">报表名称</label>
						<div class="col-sm-3">
                        <select id="query_name" class="form-control" data-live-search="true" title="选择...">
                         	 <option value="">选择...</option>
      					 	<option>03紧密型业务外包人员增减变动情况统计表</option>
							<option>05公司主动辞退人员情况统计表</option>
							<option>07省本部人员流动情况统计表</option>
							<option>08地市本部人员流动情况统计表</option>
							<option>09岗位占比变化情况统计表</option>
							<option>10人员学历结构情况统计表</option>
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
		      			<div class="input-group date form_date col-md-2" data-date="" data-date-format="yyyy-mm-dd">
		                    <input class="form-control" size="16" type="text" value="" id="query_date" name="query_date" >
							<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
		                </div>                      
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