<html>

<head>
<%@ page language="Java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- 引入头文件 -->
<%@include file="../common/head.jsp" %>


<!--  样式冲突，先不在公共jsp中使用，放到具体的页面上去 -->
<link rel="stylesheet" href="../static/bootstrap/css/bootstrap.min.css">

</head><script language="javascript" type="text/javascript">

//form submit
function submitExcel(){

     var excelFile = $("#excelFile").val();
     if (excelFile==''){
     
         alert("请选择需上传的文件!");
         return false;
    
     }
     
     
     if(excelFile.indexOf('.xls')==-1&&excelFile.indexOf('.xlsx')==-1)
     {
         alert("文件格式不正确，请选择正确的Excel文件！");
         return false;
     
     
     }
     
     //确定提交
     if(window.confirm("您确定将文件中的数据导入到系统！？")) {
         $("#fileUpload").submit();
     }
}




</script>


<body>
    <div class="panel-body" style="padding-bottom:0px;width:95%;font-size:12px">
        <div class="panel panel-default">
            <div class="panel-heading">外包合同信息批量导入</div>
            <div class="panel-body">
               <form  id="fileUpload" action="/outmanager/data/contract_import" method="post" enctype="multipart/form-data">
                    <div class="form-group" style="margin-top:15px">
                        
                        <div class="col-sm-5">
                          
					         <br>
					                            
					        <label class="control-label col-sm-3" for="txt_search_departmentname">选择文件：</label> 
					        <input type="file"  id="excelFile" name="excelFile" size=50>
					
					         <br>
					         <font color='red'>
					             注意：只支持excle文件格式!<a href='/outmanager/filemodel/外包合同维护模板.xls' target='_blank'>模板下载</a>
					         </font>
					         
					         
					         
					         <br>
					         <br>
                        </div>
                           
                                           
                        <div class="col-sm-1" style="text-align:left;">
                            <button type="button" style="margin-left:50px" id="btn_query" class="btn btn-danger" onclick="submitExcel();">导入数据</button>
                        </div>
                        
                                     
                        
                        
                    </div>
                </form>
            </div>
        </div> 
        
        
        </div>

</body>
</html>