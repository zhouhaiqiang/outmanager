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

<!--  页面私有js --> 
<script src="../localjs/common.js"></script>
  
<script src="../localjs/user/userinfo.js"></script>

</head>

<body>
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
                      ${user.inworkdate}
                     
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
                      ${user.inunicomdate}
                     
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
            			
			        <div id="toolbar" class="btn-group">
			            <button id="btn_add" type="button" class="btn btn-default">
			                <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
			            </button>
			            
			            <button id="btn_edit" type="button" class="btn btn-default">
			                <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
			            </button>
			  
			            <button id="btn_delete" type="button" class="btn btn-default">
			                <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
			            </button>

			        </div>
			        <table id="tb_data"></table>
			               
			        
			    </div>
            	<!---------------------分配信息---------------------- -->
            </div>
            <div class="tab-pane" id="pan3">
            	<!---------------------基本信息---------------------- -->
            	<!---------------------基本信息---------------------- -->
            </div>
            <div class="tab-pane" id="pan4">           
             	<!---------------------基本信息---------------------- -->
             	<!---------------------基本信息---------------------- -->
            </div>            
            <div class="tab-pane" id="pan5">           
             	<!---------------------基本信息---------------------- -->
             	<!---------------------基本信息---------------------- -->
            </div>
            
            <div class="tab-pane" id="pan6">  
            	<!---------------------基本信息---------------------- -->
            	<!---------------------基本信息---------------------- -->         
             	
            </div>
            
            <div class="tab-pane" id="pan7">           
             	<!---------------------基本信息---------------------- -->
             	<!---------------------基本信息---------------------- -->
            </div>                        
                        
        </div>        
      </div>
    <!--------------------- 列表区域---------------------- -->

</body>
</html>