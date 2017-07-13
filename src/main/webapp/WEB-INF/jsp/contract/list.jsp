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
  
<script src="../localjs/contract/list.js"></script>

</head>


<body>
    <div class="panel-body" style="padding-bottom:0px;width:95%;font-size:12px">
        <div class="panel panel-default">
            <div class="panel-heading">查询条件</div>
            <div class="panel-body">
                <form id="formSearch" class="form-horizontal">
                    <div class="form-group" style="margin-top:15px">
                        <label class="control-label col-sm-1" for="txt_search_departmentname">类型</label>
                        <div class="col-sm-2">
                           
                            <select id="query_type" class="form-control" data-live-search="true" title="选择...">
                         <option value=''>选择...</option>
                        <option>紧密型</option>
                        <option>规范型</option>
      
                        </select>
                        </div>
                        
                        
                        <label class="control-label col-sm-1" for="txt_search_statu">签署主体</label>
                        <div class="col-sm-2">
                            <input type="text" class="form-control" id="query_unit">
                        </div>
                        
                     <label class="control-label col-sm-1" for="txt_search_statu">合同称名</label>
                        <div class="col-sm-2">                         
                            <input type="text" class="form-control" id="query_name">
                        </div>      
                                           
                        <!--------------------- 查询按钮区域---------------------- -->                   
                        <div class="col-sm-1" style="text-align:left;">
                            <button type="button" style="margin-left:50px" id="btn_query1" class="btn btn-danger">重置</button>
                            
                        </div>
                        <div class="col-sm-1" style="text-align:left;">

                            <button type="button" style="margin-left:50px" id="btn_query" class="btn btn-danger">查询</button>
                        </div>
                        <!--------------------- 查询按钮区域---------------------- -->
                        
                                     
                        
                        
                    </div>
                </form>
            </div>
        </div> 
        
        
    
              

        <div id="toolbar" class="btn-group">
        
        <shiro:hasAnyRoles name="系统管理员,外包合同信息维护岗">
            <button id="btn_add" type="button" class="btn btn-default">
                <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
            </button>
            <button id="btn_edit" type="button" class="btn btn-default">
                <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
            </button>
            <button id="btn_delete" type="button" class="btn btn-default">
                <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
            </button>
            
            
        </shiro:hasAnyRoles>
            
            <button id="btn_export" type="button" class="btn btn-default">
                <span class="glyphicon glyphicon-export" aria-hidden="true"></span>导出
            </button>            
            
            
        </div>
        <table id="tb_data"></table>
               
        
    </div>

    <!-- add or update modal -start-->
   <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" >  
       <div class="modal-dialog" role="document" style="width:800px;">  
           <div class="modal-content">  
               <div class="modal-header">  
                   <button type="button" class="close" data-dismiss="modal" aria-label="Close">  
                       <span aria-hidden="true">×</span>  
                   </button>  
                   <h4 class="modal-title" id="myModalLabel">合同信息</h4>  
               </div>  
               <div class="modal-body"> 
               <form id="editform">  
               
               <!-- id -->
               <input type="hidden" id="id" name="id">

               <table class="table table-bordered">
               
                 <tbody>
                   <tr>
                     <td class="active"> 
                        <label  class="control-label">合同类型<span style="color:red">*</span></label>
                        
                     </td>
                    <td>                         

                        
                        <select id="conType" class="form-control" data-live-search="true" title="选择..." name="conType">         
                        <option>紧密型</option>
                        <option>规范型</option>
      
                        </select>
                        
                     </td>
                    <td class="active"> 
                        <label  class="control-label">合同名称<span style="color:red">*</span></label>
                    </td>
                    <td> 
                       <div class="form-group">
                        <input type="text" class="form-control" id="name" name="name" >
                        </div>
                        
                    </td>
                    
                   </tr>
                   
                   <tr>
                     <td class="active"> 
                        <label  class="control-label">合同编号<span style="color:red">*</span></label>
                    </td>
                    <td>    
                       <div class="form-group">
                        <textarea rows="2" cols="20" class="form-control" id="conCode" name="conCode" ></textarea>                                    
                        </div>
                     </td>
                    <td class="active"> 
                        <label  class="control-label">业务范围<span style="color:red">*</span></label>
                    </td>
                    <td>                         
 						 <div class="form-group">
                        <textarea rows="2" cols="20" class="form-control" id="yewu" name="yewu" ></textarea> 
                        </div>
                    </td>
                    
                   </tr>                   
                   
                   <tr>                    
                    <td class="active"> 
                        <label  class="control-label">签署主体<span style="color:red">*</span></label>
                    </td>
                    <td>      
                      <div class="form-group">                   
                        <input type="text" class="form-control" id="unit" name="unit" value='山西省分公司' >
                       </div>
                    </td>
                    
                    <td class="active"> 
                        <label  class="control-label">外包公司<span style="color:red">*</span></label>
                    </td>
                    <td>      
                      <div class="form-group">   
                      
                      <select class="selectpicker form-control" data-live-search="true" name="companyid" id="companyid">  
					  </select>
                      
                        <!--               
                        <input type="text" class="form-control" id="companyid" name="companyid" value='' >
                         -->
                       </div>
                    </td>                   
                  </tr>                   
                   <tr>
                     <td class="active"> 
                        <label  class="control-label">开始时间<span style="color:red">*</span></label>
                    </td>
                    <td>    
                                        
		      			<div class="input-group date form_date col-md-8" data-date="" data-date-format="yyyy-mm-dd">
		                    <input class="form-control" size="16" type="text" value="" id="startdate" name="startdate" readonly>
							<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
		                </div>                     </td>
                     
                    <td class="active"> 
                        <label  class="control-label">结束时间<span style="color:red">*</span></label>
                    </td>
                    <td>    
                                        
		      			<div class="input-group date form_date col-md-8" data-date="" data-date-format="yyyy-mm-dd">
		                    <input class="form-control" size="16" type="text" value="" id="enddate" name="enddate" readonly>
							<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
		                </div> 
		              
                     </td>
                    
                   </tr>
                   
                   
                  <tr>
                     <td class="active"> 
                        <label  class="control-label">合同期限</label>
                    </td>
                    <td>          
                     <div class="form-group">               
                        <input type="text" class="form-control" id="qixian" name="qixian">
                     </div>
                     </td>
                     
                    <td class="active"> 
                        <label  class="control-label">合同金额</label>
                    </td>
                    <td>          
                     <div class="form-group">               
                        <input type="text" class="form-control" id="jine" name="jine">
                     </div>
                     </td>
                     
                    </tr>
                   
                            
                                
                  <tr>
                     <td class="active"> 
                        <label  class="control-label">合同文本</label>
                    </td>
                    <td colspan="3">                         
                        <input type="file" class="file" data-show-preview="false" id="atts" name="atts">
                     </td>
                     
                     
                   </tr>          
                                
                  <tr>
                    <td class="active"> 
                        <label  class="control-label">补充协议</label>
                    </td>
                    <td colspan="3">                  
                        <input type="file" class="file" data-show-preview="false" id="buchong" name="buchong">
                    </td>
                    
                   </tr>             
                   
                 </tbody>
               </table>
                    
                    
                </form> 
                   
                   
                   
               </div>  
               <div class="modal-footer">  
                   <button type="button" class="btn btn-danger" data-dismiss="modal">关闭</button>  
                   <button type="button" class="btn btn-danger" onclick="Ok_btn();">保存</button>  
               </div>  
           </div>  
       </div>  
   </div>  
   <!-- add or update modal -end--> 
    
    

</body>
</html>