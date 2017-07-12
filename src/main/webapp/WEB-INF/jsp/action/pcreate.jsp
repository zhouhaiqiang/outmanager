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

<!--  页面私有js --> 
<script src="../localjs/common.js"></script>
  
<script src="../localjs/action/pcreate.js"></script>


</head>

<body>
  
     <div class="panel-body" style="padding-bottom:0px;width:95%;font-size:12px">

               <div class="modal-header">                   
                   <h4 class="modal-title" id="myModalLabel">业务活动批量创建</h4>  
               </div>  
               <div class="modal-body"> 
               <form id="editform">  
               
                           <table class="table table-bordered">
               
                 <tbody>
                                         
                   <tr>
                     <td class="active"> 
                        <label  class="control-label">组织<span style="color:red">*</span></label>
                    </td>
                    <td >                         
             
                         <input type="text" class="form-control" id="unit" name="unit" readonly onclick="showMenu1(); return false;">                      
                         <div id="menuContent1" class="menuContent" style="display:none;z-index:9;position: fixed;left:100px;top:100px">
							<ul id="treeDemo1" class="ztree"></ul>
						</div>
                         
                         
                    </td>
              
              
                    <td class="active"> 
                        <label  class="control-label">开始时间<span style="color:red">*</span></label>
                    </td>
                    <td>                  
                         <div class="input-group date form_date col-md-6" data-date="" data-date-format="yyyy-mm-dd">
		                    <input class="form-control" size="16" type="text" value="" id="startdate" name="startdate" readonly>
							<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
		                </div>
                    </td>
                    
                   </tr> 
                   
                     <tr>
                     <td class="active"> 
                        <label  class="control-label">业务线<span style="color:red">*</span></label>
                    </td>
                    <td>
                      <select class="form-control selectpicker show-tick" id="ywline" name="ywline" title="请选择 ..." onchange="chggwnumber(this.value);">                      
					  </select>
                    </td>
              
                    <td class="active"> 
                        <label  class="control-label">业务活动<span style="color:red">*</span></label>
                    </td>
                    <td>                  
                      
                      <select class="form-control selectpicker show-tick" id="ywaction" name="ywaction" title="请选择 ...">                      
					  </select>
                    </td>
                    
                   </tr>                      
             
                 </tbody>
               </table>
                    

                </form>  
               </div>  
               <div class="modal-footer">                   
                   <button type="button" class="btn btn-danger" onclick="Ok_btn();">创建</button>  
               </div>  
           </div>               
        

    

    
    

</body>
</html>