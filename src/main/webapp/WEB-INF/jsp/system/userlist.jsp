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

<!-- 引入验证控件 -->
<%@include file="../common/validator.jsp" %>

<!-- 引入日期控件 -->
<%@include file="../common/datepicker.jsp" %>

<!-- 引入下拉控件 -->
<%@include file="../common/selectpicker.jsp" %>

<!-- 引入zTree控件 -->
<%@include file="../common/ztree.jsp" %>

<!--  页面私有js --> 
<script src="../localjs/common.js"></script>
  
<script src="../localjs/system/userlist.js"></script>


</head>

<body>
    <div class="panel-body" style="padding-bottom:0px;width:95%;font-size:12px">
        <div class="panel panel-default">
            <div class="panel-heading">查询条件</div>
            <div class="panel-body">
                <form id="formSearch" class="form-horizontal">
                
                    <!--------------------- 查询条件区域---------------------- -->
                    <div class="form-group" style="margin-top:15px">
                        <label class="control-label col-sm-1" for="txt_search_statu">组织名称</label>
                        <div class="col-sm-2">
                            <input type="text" class="form-control" id="query_unit" readonly onclick="showMenu(); return false;">
                         
                            <div id="menuContent" class="menuContent" style="display:none;z-index:9;position: fixed;left:100px;top:100px">
								<ul id="treeDemo" class="ztree"></ul>
							</div>
                        </div>                        
                        
                                           
                        
                        <label class="control-label col-sm-1" for="txt_search_statu">人员编号</label>
                        <div class="col-sm-2">
                            <input type="text" class="form-control" id="query_code">
                        </div>
                      
                   
                        <!--------------------- 查询按钮区域---------------------- -->                   
                        <div class="col-sm-1" style="text-align:left;">
                            <button type="button" style="margin-left:50px" id="btn_reset" class="btn btn-danger">重置</button>
                            
                        </div>
                        <div class="col-sm-1" style="text-align:left;">

                            <button type="button" style="margin-left:50px" id="btn_query" class="btn btn-danger">查询</button>
                        </div>
                        <!--------------------- 查询按钮区域---------------------- -->
                                     
                        
                        
                    </div>
                    <!--------------------- 查询条件区域---------------------- -->
                </form>
            </div>
        </div> 
        
        
    
              
		<!--------------------- 列表区域---------------------- -->
        <div id="toolbar" class="btn-group">
                       
            <button id="btn_export" type="button" class="btn btn-default">
                <span class="glyphicon glyphicon-export" aria-hidden="true"></span>导出
            </button> 
        </div>
        <table id="tb_data"></table>
               
        
    </div>
    <!--------------------- 列表区域---------------------- -->
    
    

    <!-- list -start-->
   <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" >  
       <div class="modal-dialog" role="document" style="width:800px;">  
           <div class="modal-content">  
               <div class="modal-header">  
                   <button type="button" class="close" data-dismiss="modal" aria-label="Close">  
                       <span aria-hidden="true">×</span>  
                   </button>  
                   <h4 class="modal-title" id="myModalLabel">职责列表</h4>  
               </div>  
               <div class="modal-body"> 
              
               
               <!-- userid -->
               <input type="hidden" id="uid" name="uid">

               <div class="tab-pane" id="pan2">
            	<!---------------------职责列表---------------------- -->
            			
			        <div id="toolbar2" class="btn-group">
			            <button id="btn_add" type="button" class="btn btn-default">
			                <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
			            </button>
			            
			            <button id="btn_edit" type="button" class="btn btn-default" >
			                <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
			            </button>
			  
			            <button id="btn_delete" type="button" class="btn btn-default">
			                <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
			            </button>

			        </div>
			        <table id="tb_data2"></table>
  	
            	<!---------------------职责列表---------------------- -->
            </div>                   
          
                   
                   
                   
             </div>  
    
           </div>  
       </div>  
   </div>  
   <!-- list modal -end--> 
   
   
   
    <!-- add or update modal -start-->
   <div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" >  
       <div class="modal-dialog" role="document" style="width:460px;">  
           <div class="modal-content">  
               <div class="modal-header">  
                   <button type="button" class="close" data-dismiss="modal" aria-label="Close">  
                       <span aria-hidden="true">×</span>  
                   </button>  
                   <h4 class="modal-title" id="editModalLabel">职责编辑</h4>  
               </div>  
               <div class="modal-body"> 
               <form id="editform">  
               
               <!-- userid 编辑的用户id -->
               <input type="hidden" id="userid" name="userid">
               
               <!-- map id -->
               <input type="hidden" id="id" name="id">

               <table class="table table-bordered">
               
                 <tbody>
 
                    <tr>
                     <td class="active"> 
                        <label class="control-label">职责名<span style="color:red">*</span></label>
                    </td>
                    <td>                  
                        <select class="form-control selectpicker show-tick" data-live-search="true"  name="remark" id="duty" title="请选择 ...">                          	                          
					   </select>
                       <input type="hidden" class="form-control" id="dutyid" name="dutyid" >        
                     </td>

                   </tr> 
 
 
             
                    <tr>
                     <td class="active"> 
                        <label class="control-label">组织名称<span style="color:red">*</span></label>
                    </td>
                    <td>                  
                        <input type="text" class="form-control" id="unit" name="unit" readonly onclick="showMenu1(); return false;">
                        <div id="menuContent1" class="menuContent" style="display:none;z-index:9;position: fixed; left:1px;top:1px;">
							<ul id="treeDemo1" class="ztree"></ul>
					    </div>
                        <input type="hidden" class="form-control" id="unitid" name="unitid" >      
                     </td>

                   </tr>
                   
                   <tr>
                     <td class="active"> 
                        <label  class="control-label">管理本组织<span style="color:red">*</span></label>
                    </td>
                    <td>    
                    	<select class="form-control selectpicker show-tick"  name="selforg" id="selforg" title="请选择 ...">                          	     
                        
					   </select>
                     </td>
                     
                    </tr>
                   
                    <tr>                    
                     
                    <td class="active"> 
                        <label  class="control-label">管理子组织<span style="color:red">*</span></label>
                    </td>
                    <td>                         
 						 <select class="form-control selectpicker show-tick"  name="suborg" id="suborg" title="请选择 ...">                          	     
                        
					   </select>
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