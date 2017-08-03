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
  
<script src="../localjs/user/list.js"></script>

<!-- 引入标签文件 -->
<%@include file="../common/tag.jsp" %>


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
                        
                        <label class="control-label col-sm-1" for="txt_search_statu">外包公司</label>
                        <div class="col-sm-2">
                           
                            <select class="form-control selectpicker show-tick" data-live-search="true" name="query_companyid" id="query_companyid" title="请选择 ...">                           	                          
					        </select>
                            
                        </div>
                        
                        <label class="control-label col-sm-1" for="txt_search_statu">人员姓名</label>
                        <div class="col-sm-2">                         
                            <input type="text" class="form-control" id="query_name">
                        </div>  
                        
                       
                         
                                               
                       <label class="control-label col-sm-1" for="txt_search_statu">合同编号</label>

                       <div class="col-sm-2">
                           
                            <select class="form-control selectpicker show-tick" data-live-search="true" name="query_concode" id="query_concode" title="请选择 ...">
                            	                          
					        </select>
                            
                        </div>

                        
                         </div>
                         <div class="form-group" style="margin-top:15px">                      
                        
                        <label class="control-label col-sm-1" for="txt_search_statu">人员编号</label>
                        <div class="col-sm-2">
                            <input type="text" class="form-control" id="query_code">
                        </div>
                        
                        <label class="control-label col-sm-1" for="txt_search_statu">查询日期</label>
                        <div class="col-sm-3">                         
                           
	 		      			<div class="input-group date form_date col-md-9" data-date="" data-date-format="yyyy-mm-dd">
			                    <input class="form-control" size="16" type="text" value="" id="query_date" name="query_date" readonly>
								<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
			                </div>                            
                            
                            
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
        
        <shiro:hasAnyRoles name="系统管理员,外包人员信息维护岗">	
            <button id="btn_add" type="button" class="btn btn-default">
                <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
            </button>
            <!--  人员的修改不在这里处理
            <button id="btn_edit" type="button" class="btn btn-default">
                <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
            </button>
             -->
            <button id="btn_delete" type="button" class="btn btn-default">
                <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
            </button>            
        </shiro:hasAnyRoles>
            
            <button id="btn_export" type="button" class="btn btn-default">
                <span class="glyphicon glyphicon-export" aria-hidden="true"></span>导出
            </button> 
            
             <select class="btn btn-default" name="datatype" id="datatype" title="请选择导出的数据...">	
             
             	<option value="">--请选择导出信息--</option>	
				<option>基本信息</option>			
				<option>教育信息</option>
				<option>职业技能</option>
				<option>专业技术</option> 
				<option>劳动关系</option>
				<option>解除外包关系</option>  
			</select> 
            
   
        </div>
        <table id="tb_data"></table>
               
        
    </div>
    <!--------------------- 列表区域---------------------- -->
    
    

    <!-- add or update modal -start-->
   <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" >  
       <div class="modal-dialog" role="document" style="width:800px;">  
           <div class="modal-content">  
               <div class="modal-header">  
                   <button type="button" class="close" data-dismiss="modal" aria-label="Close">  
                       <span aria-hidden="true">×</span>  
                   </button>  
                   <h4 class="modal-title" id="myModalLabel">人员基本信息</h4>  
               </div>  
               <div class="modal-body"> 
               <form id="editform">  
               
               <!-- id -->
               <input type="hidden" id="id" name="id">

               <table class="table table-bordered">
               
                 <tbody>
                   <tr>
                     <td> 
                        <label  class="control-label">所属组织名称<span style="color:red">*</span></label>
                        
                     </td>
                    <td>                         
                      
                       <input type="text" class="form-control" id="unit" name="unit" readonly onclick="showMenu1(); return false;">
                        <div id="menuContent1" class="menuContent" style="display:none;z-index:9;position: fixed; left:1px;top:1px;">
							<ul id="treeDemo1" class="ztree"></ul>
					    </div>
                        
                     </td>
                    <td> 
                        <label  class="control-label">从事联通业务开始时间<span style="color:red">*</span></label>
                    </td>
                    <td> 
                     
                       	<div class="input-group date form_date col-md-9" data-date="" data-date-format="yyyy-mm-dd">
		                    <input class="form-control" size="16" type="text" value="" id="inunicomdate" name="inunicomdate" readonly>
							<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
		                </div>                     

		             </td>

                    
                   </tr>
                   
                   <tr>
                     <td> 
                        <label  class="control-label">所属公司<span style="color:red">*</span></label>
                    </td>
                    <td>    
               
                      <select class="form-control selectpicker show-tick" data-live-search="true" name="companyid" id="companyid" title="请选择 ...">                      
					  </select>                                       
                     </td>
                    <td> 
                        <label  class="control-label">所属合同编号<span style="color:red">*</span></label>
                    </td>
                    <td>                         
                
                      <select class="form-control selectpicker show-tick" data-live-search="true" name=concode id="concode" title="请选择 ...">                      
					  </select>                         

                    </td>
                    
                   </tr>                   
                   
                   <tr>                    
                    <td> 
                        <label  class="control-label">姓名<span style="color:red">*</span></label>
                    </td>
                    <td>      
                      <div class="form-group">                   
                        <input type="text" class="form-control" id="name" name="name" value='' >
                       </div>
                    </td>
                    
                    <td> 
                        <label  class="control-label">省份证<span style="color:red">*</span></label>
                    </td>
                    <td>      
                      <input type="text" class="form-control" id="idnumber" name="idnumber" value='' >
                    </td>                   
                  </tr>                   
                   <tr>
                     <td> 
                        <label  class="control-label">性别<span style="color:red">*</span></label>
                    </td>
                    <td>    
                                        
		       		 <select class="form-control selectpicker show-tick"  name="sex" id="sex" title="请选择 ...">
                            	     
                         <option>男性</option> 
                         <option>女性 </option>
                                        
					  </select>                 
		            </td>
                     
                    <td> 
                        <label  class="control-label">人员类型<span style="color:red">*</span></label>
                    </td>
                    <td>    
                      <select class="form-control selectpicker show-tick"  name="contype" id="contype" title="请选择 ...">
                            	     
                         <option>紧密型 </option> 
                         
                         <!-- 
                         <option>协议型</option>
                          -->
                                         
					  </select>
		              
                     </td>
                    
                   </tr>
                   
                   
                  <tr>
                     <td> 
                        <label  class="control-label">出生日期</label>
                    </td>
                    <td>          
                       <div class="input-group date form_date col-md-9" data-date="" data-date-format="yyyy-mm-dd">
		                    <input class="form-control" size="16" type="text" value="" id="birthday" name="birthday" readonly>
							<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
		                </div> 
                     </td>

                     
                    <td> 
                        <label  class="control-label">国籍</label>
                    </td>
                    <td>          
         		      <select class="form-control selectpicker show-tick" data-live-search="true" name="nationality" id="nationality" title="请选择 ...">                      
					  </select>   
                     </td>
                     
                    </tr>
                   
                            
                                
                  <tr>
                     <td> 
                        <label  class="control-label">民族</label>
                    </td>
                    <td >                         
         		      <select class="form-control selectpicker show-tick" data-live-search="true" name="mingz" id="mingz" title="请选择 ...">                      
					  </select>                          
                     </td>
              
                    <td> 
                        <label  class="control-label">户口类型</label>
                    </td>
                    <td >                  

         		      <select class="form-control selectpicker show-tick" data-live-search="true" name="hukoutype" id="hukoutype" title="请选择 ...">                      
					  </select>                        
                       
                    </td>
                    
                   </tr>
                   
                  <tr>
                     <td> 
                        <label  class="control-label">户口所在地</label>
                    </td>
                    <td >                         
                       <input type="text" class="form-control" id="hukouaddress" name="hukouaddress">
                     </td>
              
                    <td> 
                        <label  class="control-label">是否移除国外</label>
                    </td>
                    <td >                  
                                             
                       <select class="form-control selectpicker show-tick"  name="isout" id="isout" title="请选择 ...">
                            	     
                         <option>是 </option>                        
                         <option>否</option> 
                                         
					   </select>
                    </td>
                    
                   </tr>
                         
                         
                         
                   <tr>
                     <td> 
                        <label  class="control-label">政治面貌</label>
                    </td>
                    <td >                         
                         <select class="form-control selectpicker show-tick" data-live-search="true" name="zhengzhi" id="zhengzhi" title="请选择 ...">						     
					     </select>
                    </td>
              
                    <td> 
                        <label  class="control-label">电子邮件</label>
                    </td>
                    <td>                  
                       <input type="text" class="form-control" id="mail" name="mail">
                    </td>
                    
                   </tr> 
                   
                   
                   
                   <tr>
                     <td> 
                        <label  class="control-label">进外包单位日期</label>
                    </td>
                    <td >                         
                         <div class="input-group date form_date col-md-9" data-date="" data-date-format="yyyy-mm-dd">
		                    <input class="form-control" size="16" type="text" value="" id="inworkdate" name="inworkdate" readonly>
							<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
		                </div>
                     </td>

                    <td> 
                        <label  class="control-label">从事外包业务类型</label>
                    </td>
                    <td>                  
                       <select class="form-control selectpicker show-tick"  name="ywtype" id="ywtype" title="请选择 ...">
					   </select>
                    </td>
                    
                   </tr>                    
                   
                   
                  <tr>
                     <td> 
                        <label  class="control-label">从事联通服务途径</label>
                    </td>
                    <td >                         
						<select class="form-control selectpicker show-tick"  name="ywtj" id="ywtj" title="请选择 ...">
		
					   </select>                       
                       
                       
                     </td>
                     
                     
                     
              
                    <td> 
                        <label  class="control-label">原人员编号</label>
                    </td>
                    <td >                  
                       <input type="text" class="form-control" id="oldcode" name="oldcode">
                    </td>
                    
                   </tr>
                   
                   
                              
                    <tr>
                     <td> 
                        <label  class="control-label">纳税地</label>
                    </td>
                    <td >                         
                         <select class="form-control selectpicker show-tick" data-live-search="true" name="nsaddress" id="nsaddress" title="请选择 ...">						     
					     </select>
                     </td>
              
                    <td> 
                        <label  class="control-label">社保缴纳地</label>
                    </td>
                    <td >                  
                         <select class="form-control selectpicker show-tick" data-live-search="true" name="sbaddress" id="sbaddress" title="请选择 ...">						     
					     </select>
                    </td>
                    
                   </tr>
                   
                   
                   
                    <tr>
                     <td> 
                        <label  class="control-label">岗位序列</label>
                    </td>
                    <td >                         
                         <select class="form-control selectpicker show-tick" data-live-search="true" name="gwnumber" id="gwnumber" onchange="chggwnumber(this.value);" title="请选择 ...">						     
					     </select>
                     </td>
              
                    <td> 
                        <label  class="control-label">岗位分类</label>
                    </td>
                    <td >                  
                         <select class="form-control selectpicker show-tick" data-live-search="true" name="gwtype" id="gwtype" title="请选择 ...">						     
					     </select>
                    </td>
                    
                   </tr>
                   
                   
                     <tr>
                     <td> 
                        <label  class="control-label">参考岗级</label>
                    </td>
                    <td >                         
                          <select class="form-control selectpicker show-tick" data-live-search="true" name="gwdj" id="gwdj" title="请选择 ...">						     
					     </select>                      
                       
                     </td>
              
                    <td> 
                        <label  class="control-label">考核信息</label>
                    </td>
                    <td >                  
                       <input type="text" class="form-control" id="kaohei" name="kaohei">
                    </td>
                    
                   </tr>
                     
                     <tr>
                     <td> 
                        <label  class="control-label">基层单元负责人</label>
                    </td>
                    <td >                         
                         <select class="form-control selectpicker show-tick"  name="fzr" id="fzr" title="请选择 ...">                          	     
                         <option>是 </option>                        
                         <option>否</option> 
                                         
					   </select>
                     </td>
              
                    <td> 
                        <label  class="control-label">核算到最小单元</label>
                    </td>
                    <td >                  
                         <select class="form-control selectpicker show-tick"  name="iszuixin" id="iszuixin" title="请选择 ...">
                            	     
                         <option>是 </option>                        
                         <option>否</option> 
                                         
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