<!DOCTYPE html>
<html lang="zh-CN">
<head>
<%@ page language="Java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!--  样式冲突，先不在公共jsp中使用，放到具体的页面上去 -->
<link rel="stylesheet" href="../static/bootstrap/css/bootstrap.min.css">    
<!-- 引入头文件 -->
<%@include file="../common/head.jsp" %>

<script type="text/javascript">

function bnt_close() {
	
	if (window.confirm("确定不修改密码?")) { 
		window.close();
	}
	
}

function bnt_ok() {
	
	if($('#password').val()!=$('#password1').val()){
		alert("两次输入的密码不一致！");
	} else {

      var pars = {
		           password:$('#password').val(),   //页面大小
		           password1:$('#password1').val(),
		        };
      
       
		
		$.ajax({
			  type: 'POST',
			  url: "/outmanager/user/updatepwd",		  
			  dataType: "text",
			  contentType:'application/json', 		  
			  data:JSON.stringify(pars),
			  success: function(data){

				  alert("处理成功");
				  window.close();		  
			  },
  
			});
	}
	
	
}

</script>

</head>


<body>
 <div class="panel-body" style="padding-bottom:0px;width:100%;font-size:12px">
        <div class="panel panel-default">
          
            <div class="panel-body">
                <form id="formUpdate" class="form-horizontal">
                    <div class="form-group" style="margin-top:15px">
                        <label class="control-label col-sm-1">新密码</label>
                        <div class="col-sm-2">                        
                            <input type="password" class="form-control" id="password" name ="password">
                        </div>
                        
                        
                        <label class="control-label col-sm-1">再输入一次</label>
                        <div class="col-sm-2">
                            <input type="password" class="form-control" id="password1" name ="password1">
                        </div>
                                           
                        <!--------------------- 查询按钮区域---------------------- -->                   
                        <div class="col-sm-5" style="text-align:center;">
                        	<button type="button" id="btn_ok" class="btn btn-danger" onclick='bnt_ok();'>修改</button>
                            <button type="button" id="btn_close" class="btn btn-danger" onclick='bnt_close();'>关闭</button>
                        </div>
                        <!--------------------- 查询按钮区域---------------------- -->

                        
                    </div>
                </form>
            </div>
        </div> 
        </div>
</body>        
        
        