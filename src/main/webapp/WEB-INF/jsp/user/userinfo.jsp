<html>

<head>
<%@ page language="Java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>

<!-- 引入头文件 -->
<%@include file="../common/head.jsp" %>

<!-- 引入bootstrap-table控件 -->
<%@include file="../common/bootstrap-table.jsp" %>

<!-- 引入验证控件 -->
<%@include file="../common/validator.jsp" %>


<!--  样式冲突，先不在公共jsp中使用，放到具体的页面上去 -->
<link rel="stylesheet" href="../static/bootstrap/css/bootstrap.min.css">


<!-- 引入日期控件 -->
<%@include file="../common/datepicker.jsp" %>

<!-- 引入下拉控件 -->
<%@include file="../common/selectpicker.jsp" %>

<!-- 引入zTree控件 -->
<%@include file="../common/ztree.jsp" %>

<!-- 标签 -->
<%@include file="../common/tag.jsp" %>

<!--  页面私有js --> 
<script src="../localjs/common.js"></script>
  
<script src="../localjs/user/userinfo.js"></script>

</head>

<body>
    <!-- id -->
    <input type="hidden" id="uid" name="uid" value="${user.id}">      
    <div class="panel-body" style="padding-bottom:0px;width:95%;font-size:12px">
        <div class="panel panel-default">
            <div class="panel-heading">基本信息</div>

               <table class="table table-bordered">              
                 <tbody>
                   <tr class="active">
                     <td > 
                        <label  class="control-label">姓名</label>                      
                     </td>
                    <td>                         
                      ${user.name}
                     
                     </td>
                    <td> 
                        <label  class="control-label">人员编号</label>
                    </td>
                    <td>                      
                     ${user.code}
		             </td>
                     <td> 
                        <label  class="control-label">公司名称</label>                      
                     </td>
                    <td>                         
                      ${user.companyid}
                     
                     </td>
                   		             
                     <td> 
                        <label  class="control-label">合同编号</label>                      
                     </td>
                    <td>                         
                      ${user.concode}
                     
                     </td>
  
                   	</tr>                   
                  </tbody>
                </table>

				</div>                  
   

    
		<!--------------------- 列表区域---------------------- -->
        <ul class="nav nav-tabs">
            <li class="active"><a href="#pan1" data-toggle="tab">基本信息</a></li>
            <li><a href="#pan2" data-toggle="tab">分配信息</a></li>
            <li><a href="#pan3" data-toggle="tab">教育信息</a></li>
            <li><a href="#pan4" data-toggle="tab">职业技能信息</a></li>
            <li><a href="#pan5" data-toggle="tab">专业技术资格</a></li>
            <li><a href="#pan6" data-toggle="tab">劳动关系</a></li>
            <li><a href="#pan7" data-toggle="tab">解除外包关系</a></li>
        </ul>
        <!-- 面板 -->
        <div class="tab-content">
            <div class="tab-pane active" id="pan1">           
             	
               <!---------------------基本信息---------------------- -->	
               <table class="table table-bordered">              
                 <tbody>
                   <tr>
                     <td class="active"> 
                        <label  class="control-label">省份证</label>                      
                     </td>
                    <td>                         
                      ${user.idnumber}
                     
                     </td>
                    <td class="active"> 
                        <label  class="control-label">人员类型</label>
                    </td>
                    <td>                      
                     ${user.contype}
		             </td>
                     </tr> 
                   	
                   	 <tr>   		             
		             
                     <td class="active"> 
                        <label  class="control-label">人员编号</label>                      
                     </td>
                 
                     
                    <td>                         
                      ${user.code}
                     
                     </td>
                    <td class="active"> 
                        <label  class="control-label">性别</label>
                    </td>
                    <td>                      
                     ${user.sex}
		             </td>
		                                  
                   	</tr> 
                   	
                   	 <tr>
                     <td class="active"> 
                        <label  class="control-label">出生年月</label>                      
                     </td>
                    <td>                         
                      ${user.birthday}
                     
                     </td>
                    <td class="active"> 
                        <label  class="control-label">国籍</label>
                    </td>
                    
                 
                       
                    <td>                      
                     ${user.nationality}
		             </td>
                     </tr> 
                   	
                   	 <tr>   		             
                     <td class="active"> 
                        <label  class="control-label">名族</label>                      
                     </td>
                    <td>                         
                      ${user.mingz}
                     
                     </td>
                    <td class="active"> 
                        <label  class="control-label">户口类型</label>
                    </td>
                    <td>                      
                     ${user.hukoutype}
		             </td>
		                                  
                   	</tr> 
                   	
                   	 <tr>
                     <td class="active"> 
                        <label  class="control-label">户口所在地</label>                      
                     </td>
                    <td>                         
                      ${user.hukouaddress}
                     
                     </td>
                    <td class="active"> 
                        <label  class="control-label">政治面貌</label>
                    </td>
                    <td>                      
                     ${user.zhengzhi}
		             </td>
		             
                     </tr> 
                   	
                   	 <tr>                    
   		             
                     <td class="active"> 
                        <label  class="control-label">是否移除国外</label>                      
                     </td>
                    <td>                         
                      ${user.isout}
                     
                     </td>
                    <td class="active"> 
                        <label  class="control-label">电子邮件</label>
                    </td>
                    <td>                      
                     ${user.mail}
		             </td>
		                                  
                   	</tr>                    	
                   	
                  	 <tr>
                     <td class="active"> 
                        <label  class="control-label">进外包单位日期</label>                      
                     </td>
                     
                    <td>                         
                     	<fmt:formatDate value="${user.inworkdate}" pattern="yyyy-MM-dd"/>
                     </td>
                    <td class="active"> 
                        <label  class="control-label">从事外包业务类型</label>
                    </td>
                    <td>                      
                     ${user.ywtype}
		             </td>
		             
                     </tr> 
                   	
                   	 <tr>                    
   		             
                     <td class="active"> 
                        <label  class="control-label">从事联通服务日期</label>                      
                     </td>
                    <td>                         

                      <fmt:formatDate value="${user.inunicomdate}" pattern="yyyy-MM-dd"/>
                     
                     </td>
                    <td class="active"> 
                        <label  class="control-label">从事联通服务途径</label>
                    </td>
                    <td>                      
                     ${user.ywtj}
		             </td>
		                                  
                   	</tr>                    	
                   	                  
                  </tbody>
                </table>             	
             	<!---------------------基本信息---------------------- -->
            </div>
            <div class="tab-pane" id="pan2">
            	<!---------------------分配信息---------------------- -->
            			
			        <div id="toolbar2" class="btn-group">
			            <button id="btn_add" type="button" class="btn btn-default" onclick="addact(2);">
			                <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
			            </button>
			            
			            <button id="btn_edit" type="button" class="btn btn-default" onclick="editact(2);">
			                <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
			            </button>
			  
			            <button id="btn_delete" type="button" class="btn btn-default" onclick="delact(2,'/outmanager/userinfo/fenpei_del','/outmanager/userinfo/fenpei_list_json?uid=${user.id}');">
			                <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
			            </button>

			        </div>
			        <table id="tb_data2"></table>
  	
            	<!---------------------分配信息---------------------- -->
            </div>
            
            
            <div class="tab-pane" id="pan3">
            	<!---------------------教育信息---------------------- -->
            	
					<div id="toolbar3" class="btn-group">
			            <button id="btn_add" type="button" class="btn btn-default" onclick="addact(3);">
			                <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
			            </button>
			            
			            <button id="btn_edit" type="button" class="btn btn-default" onclick="editact(3);">
			                <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
			            </button>
			  
			            <button id="btn_delete" type="button" class="btn btn-default" onclick="delact(3,'/outmanager/userinfo/jiaoyu_del','/outmanager/userinfo/jiaoyu_list_json?uid=${user.id}');">
			                <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
			            </button>

			        </div>
			        <table id="tb_data3"></table>          	
            	
            	
            	<!---------------------教育信息---------------------- -->
            </div>
            
            
            <div class="tab-pane" id="pan4">           
             	<!---------------------技能信息---------------------- -->
             	
			        <div id="toolbar4" class="btn-group">
			            <button id="btn_add" type="button" class="btn btn-default" onclick="addact(4);">
			                <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
			            </button>
			            
			            <button id="btn_edit" type="button" class="btn btn-default" onclick="editact(4);">
			                <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
			            </button>
			  
			            <button id="btn_delete" type="button" class="btn btn-default" onclick="delact(4,'/outmanager/userinfo/jineng_del','/outmanager/userinfo/jineng_list_json?uid=${user.id}');">
			                <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
			            </button>

			        </div>
			        <table id="tb_data4"></table>             	
             	
             	<!--------------------技能信息---------------------- -->
            </div>            
            <div class="tab-pane" id="pan5">           
             	<!---------------------专业技术信息---------------------- -->
             	
 			        <div id="toolbar5" class="btn-group">
			            <button id="btn_add" type="button" class="btn btn-default" onclick="addact(5);">
			                <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
			            </button>
			            
			            <button id="btn_edit" type="button" class="btn btn-default" onclick="editact(5);">
			                <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
			            </button>
			  
			            <button id="btn_delete" type="button" class="btn btn-default" onclick="delact(5,'/outmanager/userinfo/zhiye_del','/outmanager/userinfo/zhiye_list_json?uid=${user.id}');">
			                <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
			            </button>

			        </div>
			        <table id="tb_data5"></table>            	
             	
             	<!--------------------专业技术信息---------------------- -->
            </div>
            
            <div class="tab-pane" id="pan6">  
            	<!---------------------劳动信息---------------------- -->
            	
 			        <div id="toolbar6" class="btn-group">
			            <button id="btn_add" type="button" class="btn btn-default" onclick="addact(6);">
			                <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
			            </button>
			            
			            <button id="btn_edit" type="button" class="btn btn-default" onclick="editact(6);">
			                <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
			            </button>
			  
			            <button id="btn_delete" type="button" class="btn btn-default" onclick="delact(6,'/outmanager/userinfo/laodong_del','/outmanager/userinfo/laodong_list_json?uid=${user.id}');">
			                <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
			            </button>

			        </div>
			        <table id="tb_data6"></table>           	
            	
            	<!---------------------劳动信息---------------------- -->         
             	
            </div>
            
            <div class="tab-pane" id="pan7">           
             	<!---------------------解除关系信息---------------------- -->
             	
             		<div id="toolbar7" class="btn-group">
			            <button id="btn_add" type="button" class="btn btn-default" onclick="addact(7);">
			                <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
			            </button>
			            
			            <button id="btn_edit" type="button" class="btn btn-default" onclick="editact(7);">
			                <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
			            </button>
			  
			            <button id="btn_delete" type="button" class="btn btn-default" onclick="delact(7,'/outmanager/userinfo/jiechu_del','/outmanager/userinfo/jiechu_list_json?uid=${user.id}');">
			                <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
			            </button>

			        </div>
			        <table id="tb_data7"></table>
             	<!---------------------解除关系信息---------------------- -->
            </div>                        
                        
        </div>        
      </div>
    <!--------------------- 列表区域---------------------- -->
    
    
   <!---------------------对话窗口2-----------------------start---->
   <div class="modal fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" >  
       <div class="modal-dialog" role="document" style="width:800px;">  
           <div class="modal-content">  
               <div class="modal-header">  
                   <button type="button" class="close" data-dismiss="modal" aria-label="Close">  
                       <span aria-hidden="true">×</span>  
                   </button>  
                   <h4 class="modal-title" id="myModalLabel">分配信息</h4>  
               </div>  
               <div class="modal-body"> 
               <form id="editform2">  
               
               <!-- id -->
               <input type="hidden" id="id_2" name="id">
               <!-- 用户id -->
               <input type="hidden" id="userid" name="userid" value="${user.id}">

               <table class="table table-bordered">
               
                 <tbody>
                  <tr>
                     <td class="active"> 
                        <label  class="control-label">服务部门<span style="color:red">*</span></label>
                    </td>
                    <td> 
         
                       <input type="text" class="form-control" id="fwdept_2" name="fwdept" readonly onclick="showMenu2(); return false;">
                        <div id="menuContent2" class="menuContent" style="display:none;z-index:9;position: fixed; left:1px;top:1px;">
							<ul id="treeDemo2" class="ztree"></ul>
					    </div>
				                                       
          		      	            
                     </td>
                    <td class="active"> 
                        <label  class="control-label">公司名称<span style="color:red">*</span></label>
                    </td>
                    <td>                         
 						
          		      <select class="form-control selectpicker show-tick" data-live-search="true"  id="company_2" name="company" title="请选择 ...">                      
					  </select> 					  						
                    </td>
                    
                   </tr>    
                   
                   <tr>
                     <td class="active"> 
                        <label  class="control-label">合同编号<span style="color:red">*</span></label>
                    </td>
                    <td colspan="3"> 
         
          		      <select class="form-control selectpicker show-tick" data-live-search="true"  id="connumber_2" name="connumber" title="请选择 ...">                      
					  </select>  
				                                       
          		      	            
                     </td>
                     
                    </tr>    
                   
                   <tr>                    
                     
                    <td class="active"> 
                        <label  class="control-label">开始日期<span style="color:red">*</span></label>
                    </td>
                    <td>                         
 						
          		        <div class="input-group date form_date col-md-9" data-date="" data-date-format="yyyy-mm-dd">
		                    <input class="form-control" size="16" type="text" value="" id="startdate_2" name="startdate" readonly>
							<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
		                </div>  
					  						
                    </td>                    
                    <td class="active"> 
                        <label  class="control-label">结束日期<span style="color:red">*</span></label>
                    </td>
                    <td>                         
 						
          		        <div class="input-group date form_date col-md-9" data-date="" data-date-format="yyyy-mm-dd">
		                    <input class="form-control" size="16" type="text" value="" id="enddate_2" name="enddate" readonly>
							<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
		                </div>  
					  						
                    </td>
                    
                   </tr>                                
                 
                                 
                 
                 
                    <tr>
                     <td class="active"> 
                        <label  class="control-label">岗位序列</label>
                    </td>
                    <td> 
         
          		      <select class="form-control selectpicker show-tick" data-live-search="true"  id="gw_2" name="gw" onchange="chggwnumber(this.value);" title="请选择 ...">                      
					  </select> 
				                                       
          		      	            
                     </td>
                    <td class="active"> 
                        <label  class="control-label">岗位分类</label>
                    </td>
                    <td>                         
 						
          		      <select class="form-control selectpicker show-tick" data-live-search="true"  id="gwtype_2" name="gwtype" title="请选择 ...">                      
					  </select> 
					  						
                    </td>
                   </tr>                
                                                       
                  
                   <tr>
                     <td class="active"> 
                        <label  class="control-label">岗位等级</label>
                    </td>
                    <td> 
         
          		      <select class="form-control selectpicker show-tick" data-live-search="true"  id="gwjb_2" name="gwjb" title="请选择 ...">                      
					  </select> 
				                                       
          		      	            
                     </td>
                    <td class="active"> 
                        <label  class="control-label">考核信息</label>
                    </td>
                    <td>                         
 						<input  class="form-control" id="kaohe_2" name="kaohe" >
          		    	
                    </td>
                   </tr>  
                   
                                    
                  
                   <tr>
                     <td class="active"> 
                        <label  class="control-label">备用1</label>
                    </td>
                    <td> 
         
          		       <input  class="form-control" id="qt1_2" name="qt1" >
				                                       
          		      	            
                     </td>
                    <td class="active"> 
                        <label  class="control-label">备用2</label>
                    </td>
                    <td>                         
 						
					   <input  class="form-control" id="qt2_2" name="qt2" >
					  						
                    </td>
                   </tr>  
                                    
                      <tr>
                     <td class="active"> 
                        <label  class="control-label">基层单元负责人</label>
                    </td>
                    <td >                         
                         <select class="form-control selectpicker show-tick" id="fzr_2"  name="fzr"  title="请选择 ...">                          	                           
					   </select>
                     </td>
              
                    <td class="active"> 
                        <label  class="control-label">核算到最小单元</label>
                    </td>
                    <td >                  
                         <select class="form-control selectpicker show-tick" id="iszuixiao_2" name="iszuixiao"  title="请选择 ...">
                                         
					   </select>
                    </td>                   
                   </tr>                                                                     
                  
                 </tbody>
               </table>
                    
                    
                </form> 
               </div>  
               <div class="modal-footer">  
                   <button type="button" class="btn btn-danger" data-dismiss="modal">关闭</button>  
                   <button type="button" class="btn btn-danger" onclick="Ok_btn(2,'/outmanager/userinfo/fenpei_update','/outmanager/userinfo/fenpei_list_json?uid=${user.id}');">保存</button>  
               </div>  
           </div>  
       </div>  
   </div>  
   <!---------------------对话窗口2-----------------------end---->   
    
    
   <!---------------------对话窗口3-----------------------start---->
   <div class="modal fade" id="myModal3" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" >  
       <div class="modal-dialog" role="document" style="width:800px;">  
           <div class="modal-content">  
               <div class="modal-header">  
                   <button type="button" class="close" data-dismiss="modal" aria-label="Close">  
                       <span aria-hidden="true">×</span>  
                   </button>  
                   <h4 class="modal-title" id="myModalLabel">教育信息</h4>  
               </div>  
               <div class="modal-body"> 
               <form id="editform3">  
               
               <!-- id -->
               <input type="hidden" id="id_3" name="id">
               <!-- 用户id -->
               <input type="hidden" id="userid" name="userid" value="${user.id}">

               <table class="table table-bordered">
               
                 <tbody>
                
                  <tr>
                     <td class="active"> 
                        <label  class="control-label">毕业学校<span style="color:red">*</span></label>
                    </td>
                    <td colspan="3"> 
         
          		      <input  class="form-control" id="school_3" name="school" >               
                     </td>
                    
                    
                   </tr>               
                
                   <tr>
                     <td class="active"> 
                        <label  class="control-label">入学时间<span style="color:red">*</span></label>
                    </td>
                    <td> 
         
          		        <div class="input-group date form_date col-md-9" data-date="" data-date-format="yyyy-mm-dd">
		                    <input class="form-control" size="16" type="text" value="" id="startdate_3" name="startdate" readonly>
							<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
		                </div>           		      	            
                     </td>
                    <td class="active"> 
                        <label  class="control-label">毕业时间<span style="color:red">*</span></label>
                    </td>
                    <td>                         
 						
          		        <div class="input-group date form_date col-md-9" data-date="" data-date-format="yyyy-mm-dd">
		                    <input class="form-control" size="16" type="text" value="" id="enddate_3" name="enddate" readonly>
							<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
		                </div> 					  						
                    </td>
                    
                   </tr>                
                 
                  <tr>
                     <td class="active"> 
                        <label  class="control-label">学历<span style="color:red">*</span></label>
                    </td>
                    <td> 
         
          		      <select class="form-control selectpicker show-tick" data-live-search="true"  id="xueli_3" name="xueli" title="请选择 ...">                      
					  </select>  
				                                       
          		      	            
                     </td>
                    <td class="active"> 
                        <label  class="control-label">学历证书编号</label>
                    </td>
                    <td>                         
 						
           		     <input  class="form-control" id="xuelizsnumber_3" name="xuelizsnumber" >  
					  						
                    </td>
                    
                   </tr>    
                   
                   <tr>
                     <td class="active"> 
                        <label  class="control-label">是否最高学历<span style="color:red">*</span></label>
                    </td>
                    <td> 
         
          		      <select class="form-control selectpicker show-tick" data-live-search="true"  id="ismaxxl_3" name="ismaxxl" title="请选择 ...">                      
					  </select>  
				                                       
          		      	            
                     </td>
                    <td class="active"> 
                        <label  class="control-label">学位</label>
                    </td>
                    <td>                         
 						
          		      <select class="form-control selectpicker show-tick" data-live-search="true"  id="xuewei_3" name="xuewei" title="请选择 ...">                      
					  </select>  
					  						
                    </td>
                    
                   </tr>                                
                 
                 
                   <tr>
                     <td class="active"> 
                        <label  class="control-label">第一专业</label>
                    </td>
                    <td> 
         
          		       <input  class="form-control" id="d1zy_3" name="d1zy" >  
				                                       
          		      	            
                     </td>
                    <td class="active"> 
                        <label  class="control-label">第一学位类型</label>
                    </td>
                    <td>                         
 						
          		      <input  class="form-control" id="d1xwtype_3" name="d1xwtype" >  
					  						
                    </td>
                   </tr>                  
                 
                        <tr>
                     <td class="active"> 
                        <label  class="control-label">第二学位类型</label>
                    </td>
                    <td> 
         
          		       <input  class="form-control" id="d2xwtype_3" name="d2xwtype" >  
				                                       
          		      	            
                     </td>
                    <td class="active"> 
                        <label  class="control-label">学位授予日期</label>
                    </td>
                    <td>                         
 						
          		        <div class="input-group date form_date col-md-9" data-date="" data-date-format="yyyy-mm-dd">
		                    <input class="form-control" size="16" type="text" value="" id="xwdate_3" name="xwdate" readonly>
							<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
		                </div> 
					  						
                    </td>
                   </tr>     
                               
                 
                 <tr>
                     <td class="active"> 
                        <label  class="control-label">学位授予单位</label>
                    </td>
                    <td> 
         
                      <input  class="form-control" id="xwunit_3" name="xwunit" >  
				                                       
          		      	            
                     </td>
                    <td class="active"> 
                        <label  class="control-label">学位编号</label>
                    </td>
                    <td>                         
                        <input  class="form-control" id="xwzsnumber_3" name="xwzsnumber" >  
                    </td>
                    
                   </tr>                  
                 
                 
                    <tr>
                     <td class="active"> 
                        <label  class="control-label">是否最高学位</label>
                    </td>
                    <td> 
         
          		      <select class="form-control selectpicker show-tick" data-live-search="true"  id="ismaxxw_3" name="ismaxxw" title="请选择 ...">                      
					  </select> 
				                                       
          		      	            
                     </td>
                    <td class="active"> 
                        <label  class="control-label">同等学历</label>
                    </td>
                    <td>                         
 						
          		      <select class="form-control selectpicker show-tick" data-live-search="true"  id="tdxl_3" name="tdxl" title="请选择 ...">                      
					  </select> 
					  						
                    </td>
                   </tr>                
                                                       
                  
                   <tr>
                     <td class="active"> 
                        <label  class="control-label">相当毕业</label>
                    </td>
                    <td> 
         
          		      <select class="form-control selectpicker show-tick" data-live-search="true"  id="xdby_3" name="xdby" title="请选择 ...">                      
					  </select> 
				                                       
          		      	            
                     </td>
                    <td class="active"> 
                        <label  class="control-label">专业类别</label>
                    </td>
                    <td>                         
 						
          		      <select class="form-control selectpicker show-tick" data-live-search="true"  id="zytype_3" name="zytype" title="请选择 ...">                      
					  </select> 
					  						
                    </td>
                   </tr>                   
                  
                   <tr>
                     <td class="active"> 
                        <label  class="control-label">专业子类别</label>
                    </td>
                    <td> 
         
          		      <select class="form-control selectpicker show-tick" data-live-search="true"  id="zysubtype_3" name="zysubtype" title="请选择 ...">                      
					  </select> 
				                                       
          		      	            
                     </td>
                    <td class="active"> 
                        <label  class="control-label">第二专业</label>
                    </td>
                    <td>                         
 						
					  <input  class="form-control" id="d2zy_3" name="d2zy" >
					  						
                    </td>
                   </tr>  
                                    
                  
                    <tr>
                     <td class="active"> 
                        <label  class="control-label">学制（年）</label>
                    </td>
                    <td> 
         
          		      <input  class="form-control" id="xuezhi_3" name="xuezhi" > 
				                                       
          		      	            
                     </td>
                    <td class="active"> 
                        <label  class="control-label">学习形式</label>
                    </td>
                    <td>                         
 						
          		      <select class="form-control selectpicker show-tick" data-live-search="true"  id="xxxs_3" name="xxxs" title="请选择 ...">                      
					  </select> 
					  						
                    </td>
                   </tr>                 
                  
                   <tr>
                     <td class="active"> 
                        <label  class="control-label">学习情况</label>
                    </td>
                    <td> 
         
          		      <input  class="form-control" id="xxqk_3" name="xxqk" > 
				                                       
          		      	            
                     </td>
                    <td class="active"> 
                        <label  class="control-label">备注</label>
                    </td>
                    <td>                         
 						
          		       <input  class="form-control" id="qt_3" name="qt" >  
					  						
                    </td>
                   </tr>                 
                    
                  
                  
                 </tbody>
               </table>
                    
                    
                </form> 
               </div>  
               <div class="modal-footer">  
                   <button type="button" class="btn btn-danger" data-dismiss="modal">关闭</button>  
                   <button type="button" class="btn btn-danger" onclick="Ok_btn(3,'/outmanager/userinfo/jiaoyu_update','/outmanager/userinfo/jiaoyu_list_json?uid=${user.id}');">保存</button>  
               </div>  
           </div>  
       </div>  
   </div>  
   <!---------------------对话窗口3-----------------------end---->   
    
    
    
   <!---------------------对话窗口4-----------------------start---->
   <div class="modal fade" id="myModal4" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" >  
       <div class="modal-dialog" role="document" style="width:800px;">  
           <div class="modal-content">  
               <div class="modal-header">  
                   <button type="button" class="close" data-dismiss="modal" aria-label="Close">  
                       <span aria-hidden="true">×</span>  
                   </button>  
                   <h4 class="modal-title" id="myModalLabel">职业技能信息</h4>  
               </div>  
               <div class="modal-body"> 
               <form id="editform4">  
               
               <!-- id -->
               <input type="hidden" id="id_4" name="id">
               <!-- 用户id -->
               <input type="hidden" id="userid" name="userid" value="${user.id}">

               <table class="table table-bordered">
               
                 <tbody>
                 
                 
                 <tr>
                     <td class="active"> 
                        <label  class="control-label">认定起始日期</label>
                    </td>
                    <td> 
         
                        <div class="input-group date form_date col-md-9" data-date="" data-date-format="yyyy-mm-dd">
		                    <input class="form-control" size="16" type="text" value="" id="startdate_4" name="startdate" readonly>
							<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
		                </div>
				                                       
          		      	            
                     </td>
                    <td class="active"> 
                        <label  class="control-label">认定终止日期</label>
                    </td>
                    <td>                         
                        <div class="input-group date form_date col-md-9" data-date="" data-date-format="yyyy-mm-dd">
		                    <input class="form-control" size="16" type="text" value="" id="enddate_4" name="enddate" readonly>
							<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
		                </div>
                    </td>
                    
                   </tr>                  
                 
                 
               
                   
                   <tr>
                     <td class="active"> 
                        <label  class="control-label">认定单位<span style="color:red">*</span></label>
                    </td>
                    <td> 
         
          		    
					   <input  class="form-control" id="rdunit_4" name="rdunit" >
				                                       
          		      	            
                     </td>
                    <td class="active"> 
                        <label  class="control-label">认定技术资格名称<span style="color:red">*</span></label>
                    </td>
                    <td>                         
 						
           		      <select class="form-control selectpicker show-tick" data-live-search="true"  id="rdname_4" name="rdname" title="请选择 ...">                      
					  </select>  
					  						
                    </td>
                    
                   </tr>
                   

                   
                   <tr>
                     <td class="active"> 
                        <label  class="control-label">认定技能等级</label>
                    </td>
                    <td> 
         
          		      <select class="form-control selectpicker show-tick" data-live-search="true"  id="rddengji_4" name="rddengji" title="请选择 ...">                      
					  </select>  
				                                       
          		      	            
                     </td>
                    <td class="active"> 
                        <label  class="control-label">是否主要认定</label>
                    </td>
                    <td>                         
 						
           		      <select class="form-control selectpicker show-tick" data-live-search="true"  id="ismain_4" name="ismain" title="请选择 ...">                      
					  </select>  
					  						
                    </td>
                    
                   </tr>
                   
                   
                  <tr>
                     <td class="active"> 
                        <label  class="control-label">其他说明</label>
                    </td>
                    <td> 
         

          		      <input  class="form-control" id="qt_4" name="qt" > 				                                       
          		      	            
                     </td>
                    <td class="active"> 
                       
                    </td>
                    <td>                         
                    
                    </td>
                    
                   </tr>                                        
                  
                 </tbody>
               </table>
                    
                    
                </form> 
               </div>  
               <div class="modal-footer">  
                   <button type="button" class="btn btn-danger" data-dismiss="modal">关闭</button>  
                   <button type="button" class="btn btn-danger" onclick="Ok_btn(4,'/outmanager/userinfo/jineng_update','/outmanager/userinfo/jineng_list_json?uid=${user.id}');">保存</button>  
               </div>  
           </div>  
       </div>  
   </div>  
   <!---------------------对话窗口4-----------------------end---->   
    
    
    
    
    
    
   <!---------------------对话窗口5-----------------------start---->
   <div class="modal fade" id="myModal5" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" >  
       <div class="modal-dialog" role="document" style="width:800px;">  
           <div class="modal-content">  
               <div class="modal-header">  
                   <button type="button" class="close" data-dismiss="modal" aria-label="Close">  
                       <span aria-hidden="true">×</span>  
                   </button>  
                   <h4 class="modal-title" id="myModalLabel">专业技术资格信息</h4>  
               </div>  
               <div class="modal-body"> 
               <form id="editform5">  
               
               <!-- id -->
               <input type="hidden" id="id_5" name="id">
               <!-- 用户id -->
               <input type="hidden" id="userid" name="userid" value="${user.id}">

               <table class="table table-bordered">
               
                 <tbody>
                 
                 
                 <tr>
                     <td class="active"> 
                        <label  class="control-label">起始日期</label>
                    </td>
                    <td> 
         
                        <div class="input-group date form_date col-md-9" data-date="" data-date-format="yyyy-mm-dd">
		                    <input class="form-control" size="16" type="text" value="" id="startdate_5" name="startdate" readonly>
							<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
		                </div>
				                                       
          		      	            
                     </td>
                    <td class="active"> 
                        <label  class="control-label">终止日期</label>
                    </td>
                    <td>                         
                        <div class="input-group date form_date col-md-9" data-date="" data-date-format="yyyy-mm-dd">
		                    <input class="form-control" size="16" type="text" value="" id="endtime_5" name="endtime" readonly>
							<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
		                </div>
                    </td>
                    
                   </tr>                  
                 
                 
               
                   
                   <tr>
                     <td class="active"> 
                        <label  class="control-label">专业技术资格序列<span style="color:red">*</span></label>
                    </td>
                    <td> 
         
          		      <select class="form-control selectpicker show-tick" data-live-search="true"  id="xulie_5" name="xulie" title="请选择 ...">                      
					  </select>  
				                                       
          		      	            
                     </td>
                    <td class="active"> 
                        <label  class="control-label">专业技术资格名称<span style="color:red">*</span></label>
                    </td>
                    <td>                         
 						
           		      <select class="form-control selectpicker show-tick" data-live-search="true"  id="name_5" name="name" title="请选择 ...">                      
					  </select>  
					  						
                    </td>
                    
                   </tr>
                   

                   
                   <tr>
                     <td class="active"> 
                        <label  class="control-label">专业分类</label>
                    </td>
                    <td> 
         
          		      <select class="form-control selectpicker show-tick" data-live-search="true"  id="zytype_5" name="zytype" title="请选择 ...">                      
					  </select>  
				                                       
          		      	            
                     </td>
                    <td class="active"> 
                        <label  class="control-label">专业子分类</label>
                    </td>
                    <td>                         
 						
           		      <select class="form-control selectpicker show-tick" data-live-search="true"  id="subtype_5" name="subtype" title="请选择 ...">                      
					  </select>  
					  						
                    </td>
                    
                   </tr>
                   
                   
                  <tr>
                     <td class="active"> 
                        <label  class="control-label">其他说明</label>
                    </td>
                    <td> 
         

          		      <input  class="form-control" id="qt_5" name="qt" > 				                                       
          		      	            
                     </td>
                    <td class="active"> 
                        <label  class="control-label">专业资质证书编号</label>
                    </td>
                    <td>                         
                    	<input  class="form-control" id="zsnumber_5" name="zsnumber" > 
                    </td>
                    
                   </tr>                                        
 
 
 
 
                 <tr>
                     <td class="active"> 
                        <label  class="control-label">资格取得日期</label>
                    </td>
                    <td> 
         
                        <div class="input-group date form_date col-md-9" data-date="" data-date-format="yyyy-mm-dd">
		                    <input class="form-control" size="16" type="text" value="" id="gotdate_5" name="gotdate" readonly>
							<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
		                </div>
				                                       
          		      	            
                     </td>
                    <td class="active"> 
                        <label  class="control-label">资格到期日</label>
                    </td>
                    <td>                         
                        <div class="input-group date form_date col-md-9" data-date="" data-date-format="yyyy-mm-dd">
		                    <input class="form-control" size="16" type="text" value="" id="outdate_5" name="outdate" readonly>
							<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
		                </div>
                    </td>
                    
                   </tr>  
 
 
                   <tr>
                     <td class="active"> 
                        <label  class="control-label">取得资格途径<span style="color:red">*</span></label>
                    </td>
                    <td> 
         
          		      <select class="form-control selectpicker show-tick" data-live-search="true"  id="gotway_5" name="gotway" title="请选择 ...">                      
					  </select>  
				                                       
          		      	            
                     </td>
                    <td class="active"> 
                        <label  class="control-label">专业技术资格等级<span style="color:red">*</span></label>
                    </td>
                    <td>                         
 						
           		      <select class="form-control selectpicker show-tick" data-live-search="true"  id="dengji_5" name="dengji" title="请选择 ...">                      
					  </select>  
					  						
                    </td>
                    
                   </tr> 
                               
               
                   <tr>
                     <td class="active"> 
                        <label  class="control-label">授予单位</label>
                    </td>
                    <td> 

					  <input  class="form-control" id="shareunit_5" name="shareunit" >  
         
                     </td>
                    <td class="active"> 
                        <label  class="control-label">是否为主要专业技术资质</label>
                    </td>
                    <td>                         
 						
           		      <select class="form-control selectpicker show-tick" data-live-search="true"  id="ismain_5" name="ismain" title="请选择 ...">                      
					  </select>  
					  						
                    </td>
                    
                   </tr>               
               
                    
                 </tbody>
               </table>
                    
                    
                </form> 
               </div>  
               <div class="modal-footer">  
                   <button type="button" class="btn btn-danger" data-dismiss="modal">关闭</button>  
                   <button type="button" class="btn btn-danger" onclick="Ok_btn(5,'/outmanager/userinfo/zhiye_update','/outmanager/userinfo/zhiye_list_json?uid=${user.id}');">保存</button>  
               </div>  
           </div>  
       </div>  
   </div>  
   <!---------------------对话窗口5-----------------------end---->    
    
    
   <!---------------------对话窗口6-----------------------start---->
   <div class="modal fade" id="myModal6" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" >  
       <div class="modal-dialog" role="document" style="width:800px;">  
           <div class="modal-content">  
               <div class="modal-header">  
                   <button type="button" class="close" data-dismiss="modal" aria-label="Close">  
                       <span aria-hidden="true">×</span>  
                   </button>  
                   <h4 class="modal-title" id="myModalLabel">劳动关系信息</h4>  
               </div>  
               <div class="modal-body"> 
               <form id="editform6">  
               
               <!-- id -->
               <input type="hidden" id="id_6" name="id">
               <!-- 用户id -->
               <input type="hidden" id="userid" name="userid" value="${user.id}">

               <table class="table table-bordered">
               
                 <tbody>
                   <tr>
                     <td class="active"> 
                        <label  class="control-label">合同类型<span style="color:red">*</span></label>
                        
                     </td>
                    <td>                         
 						<select class="form-control selectpicker show-tick" id="contype_6"  name="contype"  title="请选择 ...">
                            	     
                         	<option selected >紧密型 </option> 
                         
	                         <!-- 
	                         <option>协议型</option>
	                          -->
                                         
					  	</select>                     
                     </td>
                    <td  class="active"> 
                        <label  class="control-label">签订单位<span style="color:red">*</span></label>
                    </td>
                    <td> 
                        <input type="text" class="form-control" id="unit_6" name="unit" readonly  onclick="showMenu6(); return false;">
                        <div id="menuContent6" class="menuContent" style="display:none;z-index:9;position: fixed; left:1px;top:1px;">
							<ul id="treeDemo6" class="ztree"></ul>
					    </div>                     
                        
                        
                    </td>
                   </tr>
                   
                   <tr>
                     <td class="active"> 
                        <label  class="control-label">合同期限类型<span style="color:red">*</span></label>
                    </td>
                    <td> 
         
          		      <select class="form-control selectpicker show-tick" data-live-search="true"  id="conqxtype_6" name="conqxtype" title="请选择 ...">                      
					  </select>  
				                                       
          		      	            
                     </td>
                    <td class="active"> 
                        <label  class="control-label">合同期限<span style="color:red">*</span></label>
                    </td>
                    <td>                         
 						 <div class="form-group">
                        	<input  class="form-control" id="qixian_6" name="qixian" > 
                        </div>
                    </td>
                    
                   </tr>
                   
                   
                  <tr>
                     <td class="active"> 
                        <label  class="control-label">合同起始日期<span style="color:red">*</span></label>
                    </td>
                    <td> 
         
                        <div class="input-group date form_date col-md-9" data-date="" data-date-format="yyyy-mm-dd">
		                    <input class="form-control" size="16" type="text" value="" id="startdate_6" name="startdate" readonly>
							<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
		                </div>
				                                       
          		      	            
                     </td>
                    <td class="active"> 
                        <label  class="control-label">合同终止日期<span style="color:red">*</span></label>
                    </td>
                    <td>                         
                        <div class="input-group date form_date col-md-9" data-date="" data-date-format="yyyy-mm-dd">
		                    <input class="form-control" size="16" type="text" value="" id="enddate_6" name="enddate" readonly>
							<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
		                </div>
                    </td>
                    
                   </tr>   
                   
                   
                  <tr>
                     <td class="active"> 
                        <label  class="control-label">合同状态<span style="color:red">*</span></label>
                    </td>
                    <td> 
         

          		      <select class="form-control selectpicker show-tick" data-live-search="true"  id="constatus_6" name="constatus" title="请选择 ...">                      
					  </select>  				                                       
          		      	            
                     </td>
                    <td class="active"> 
                        <label  class="control-label">合同编号<span style="color:red">*</span></label>
                    </td>
                    <td>                         
                    	<input  class="form-control" id="connumber_6" name="connumber" > 
                    </td>
                    
                   </tr> 
                   
                   
                  <tr>
                     <td class="active"> 
                        <label  class="control-label">甲方单位<span style="color:red">*</span></label>
                    </td>
                    <td> 
         

          		      <input  class="form-control" id="fristunit_6" name="fristunit" > 				                                       
          		      	            
                     </td>
                    <td class="active"> 
                        <label  class="control-label">劳务合同编号<span style="color:red">*</span></label>
                    </td>
                    <td>                         
                    	<input  class="form-control" id="nwconnumber_6" name="nwconnumber" > 
                    </td>
                    
                   </tr>                                        
 
 
                  <tr>
                     <td class="active"> 
                        <label  class="control-label">劳务合同名称<span style="color:red">*</span></label>
                    </td>
                    <td> 
         

          		      <input  class="form-control" id="lwconname_6" name="lwconname" > 				                                       
          		      	            
                     </td>
                    <td class="active"> 
                       
                    </td>
                    <td>                         
                    	
                    </td>
                    
                   </tr>                                  
                    
                 </tbody>
               </table>
                    
                    
                </form> 
               </div>  
               <div class="modal-footer">  
                   <button type="button" class="btn btn-danger" data-dismiss="modal">关闭</button>  
                   <button type="button" class="btn btn-danger" onclick="Ok_btn(6,'/outmanager/userinfo/laodong_update','/outmanager/userinfo/laodong_list_json?uid=${user.id}');">保存</button>  
               </div>  
           </div>  
       </div>  
   </div>  
   <!---------------------对话窗口6-----------------------end---->   
    

    
   <!---------------------对话窗口7-----------------------start---->
   <div class="modal fade" id="myModal7" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" >  
       <div class="modal-dialog" role="document" style="width:800px;">  
           <div class="modal-content">  
               <div class="modal-header">  
                   <button type="button" class="close" data-dismiss="modal" aria-label="Close">  
                       <span aria-hidden="true">×</span>  
                   </button>  
                   <h4 class="modal-title" id="myModalLabel">解除关系信息</h4>  
               </div>  
               <div class="modal-body"> 
               <form id="editform7">  
               
               <!-- id -->
               <input type="hidden" id="id_7" name="id">
               <!-- 用户id -->
               <input type="hidden" id="userid" name="userid" value="${user.id}">

               <table class="table table-bordered">
               
                 <tbody>
                   <tr>
                     <td class="active"> 
                        <label  class="control-label">解除原因<span style="color:red">*</span></label>
                        
                     </td>
                    <td>                         
  
                        <input type="text" class="form-control" id="jcreason_7" name="jcreason" >                      
                     </td>
                    <td  class="active"> 
                        <label  class="control-label">解除日期<span style="color:red">*</span></label>
                    </td>
                    <td> 
          		      	<div class="input-group date form_date col-md-8" data-date="" data-date-format="yyyy-mm-dd">
		                    <input class="form-control" size="16" type="text" value="" id="jcdate_7" name="jcdate" readonly>
							<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
		                </div>                         
                        
                        
                    </td>
                   </tr>
                   
                   <tr>
                     <td class="active"> 
                        <label  class="control-label">费用终止日<span style="color:red">*</span></label>
                    </td>
                    <td>    
          		      	<div class="input-group date form_date col-md-8" data-date="" data-date-format="yyyy-mm-dd">
		                    <input class="form-control" size="16" type="text" value="" id="gzenddate_7" name="gzenddate" readonly>
							<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
		                </div>               
                     </td>
                    <td class="active"> 
                        <label  class="control-label">途径说明（新外包公司名）<span style="color:red">*</span></label>
                    </td>
                    <td>                         
 						 <div class="form-group">
                        <textarea rows="2" cols="20" class="form-control" id="qt_7" name="qt" ></textarea> 
                        </div>
                    </td>
                    
                   </tr> 
                 </tbody>
               </table>
                    
                    
                </form> 
               </div>  
               <div class="modal-footer">  
                   <button type="button" class="btn btn-danger" data-dismiss="modal">关闭</button>  
                   <button type="button" class="btn btn-danger" onclick="Ok_btn(7,'/outmanager/userinfo/jiechu_update','/outmanager/userinfo/jiechu_list_json?uid=${user.id}');">保存</button>  
               </div>  
           </div>  
       </div>  
   </div>  
   <!---------------------对话窗口7-----------------------end---->
    
    
    
    
    
    
    
    
    
    
    

</body>
</html>