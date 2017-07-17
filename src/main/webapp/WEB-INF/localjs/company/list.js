
//on load
$(function () {

    //1.初始化Table
    var oTable = new TableInit();
    oTable.Init();

    //2.初始化Button的点击事件
    var oButtonInit = new ButtonInit();
    oButtonInit.Init();
    
    
    //3. 对话框隐藏
    $('#myModal').modal("hide");
    
    //4. 表单验证
    validatorForm();
    
    //5. 提交事件
    modalaction();
    
});


var TableInit = function () {
    var oTableInit = new Object();
    //初始化Table
    oTableInit.Init = function () {
        $('#tb_data').bootstrapTable({
            url: '/outmanager/data/company_list_json',         //请求后台的URL（*）
            method: 'get',                      //请求方式（*）
            toolbar: '#toolbar',                //工具按钮用哪个容器
            striped: false,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true,                   //是否显示分页（*）
            sortable: false,                     //是否启用排序
            sortOrder: "asc",                   //排序方式
            queryParams: oTableInit.queryParams,//传递参数（*）
            sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
            pageNumber:1,                       //初始化加载第一页，默认第一页
            pageSize: 10,                       //每页的记录行数（*）
            pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
            search: false,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
            strictSearch: false,
            showColumns: false,                  //是否显示所有的列
            showRefresh: false,                  //是否显示刷新按钮
            minimumCountColumns: 2,             //最少允许的列数
            clickToSelect: false,                //是否启用点击选中行
            height: 500,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            uniqueId: "id",                     //每一行的唯一标识，一般为主键列
            showToggle:false,                    //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            detailView: false,                   //是否显示父子表
            
            //表格导出相关设置
            //showExport : true,                   //是否显示
            //exportDataType : "basic",              //数据导出类型    basic, all, selected
            //exportTypes: ['excel'], //['json', 'xml', 'csv', 'txt', 'sql', 'excel'], 导出文件格式
            
            columns: [{
                checkbox: true
            }, {
                field: 'unit',
                align:"center",
                title: '组织名称'
                	
            }, {
                field: 'name',
                align:"center",
                title: '公司名称'
            }, {
                field: 'conType',
                align:"center",
                title: '公司类型'
            }, {
                field: 'boss',
                align:"center",
                title: '法人代表'
            }, {
                field: 'address',
                align:"center",
                title: '注册地址'
            }, {
                field: 'zijin',
                align:"center",
                title: '注册资本(万元)'
            },
            ]
        });
    };
    


    //得到查询的参数
    oTableInit.queryParams = function (params) {
        var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
           limit: params.limit,   //页面大小
           offset: params.offset,  //页码
           
           name:$("#query_name").val(),
           unit:$("#query_unit").val(),
           type:$("#query_type").val(),
     
        };
        

        return temp;
    };
    
    
    oTableInit.queryParamsrset = function (params) {
        var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
           limit: 10,   //页面大小
           offset: 0,  //页码

           name:$("#query_name").val(),
           unit:$("#query_unit").val(),
           type:$("#query_type").val(),
     
        };
        

        return temp;
    };   
    
    return oTableInit;
};


//初始化页面button
var ButtonInit = function () {
    var oInit = new Object();
    
    oInit.Init = function () {
        
     //初始化页面上面的按钮事件
     $('#btn_query').click(function(){
              	 
    	  //处理查询		
		  refreshtab();
     });

    $('#btn_add').click(function(){

    	//显示添加窗口
    	openml();
    	
        
     });
    
      // 编辑任务按钮点击事件
      $('#btn_edit').click(function(){
    	 
    	  var arr = $('#tb_data').bootstrapTable('getSelections');          
          if(arr.length==1){ 
        
        	  var selects = $.parseJSON(JSON.stringify(arr));
    
        	  //给modal表单 赋值
        	  $('#id').val(selects[0].id);
        	  $('#unit').val(selects[0].unit);
        	  $('#name').val(selects[0].name);
        	  $('#address').val(selects[0].address);
        	  $('#conType').val(selects[0].conType); //下拉框处理
        	  $('#zizhi').val(selects[0].zizhi);
    
        	  $('#area').val(selects[0].area);
        	  $('#boss').val(selects[0].boss);       	  
        	  $('#zijin').val(selects[0].zijin);
        	
        	  //$('#zhizhao').val(selects[0].zhizhao); //附件处理 ????
        	  //$('#zizhidoc').val(selects[0].zizhidoc); //附件处理?????
        	 
        	  openml();

          } else {
              alert("请选择要编辑的记录！");
          }    	  
    	  
    	  
    	  
      });
    
      // 删除任务按钮点击事件
      $('#btn_delete').click(function(){
    	
     
          var arr = $('#tb_data').bootstrapTable('getSelections');          
          if(arr.length>0){          
        	   if(confirm("确定删除此任务吗？")){
        		   
        		  //处理删除多个
        		  // var selects = $.parseJSON(JSON.stringify(arr));

        		   delrecode("/outmanager/data/company_del",arr);
        	   };
        	  
   
          } else {
              alert("请选择要删除的记录！");
          }
      }
      
      
      
      );
      

      // 导出结果集
      $('#btn_export').click(function(){
    	  doExport();
    	 // alert(11);
//    	  if(confirm("导出查询结果到excel？")){
//    		  
//    		  //查询参数
//    		  var pars = { 
//    		           name:$("#query_name").val(),
//    		           unit:$("#query_unit").val(),
//    		           type:$("#query_type").val(),
//    		     
//    		  };
//    	  
//    		  
//    		  $.ajax({
//    			  type: 'GET',
//    			  url: '/outmanager/data/exportcompany',		  
//    			  dataType: "text",
//    			  contentType:'application/json;charset=UTF-8', 		  
//    			  data: JSON.stringify(pars),		  
//    			  success: function(data){
//
//    				  alert("处理成功");
// 
//    			  },
//    			  
//    			  
//    			});
//    		
//    	  	} 
//    		  
      });      
      
      
      
      
      
      
  };
  return oInit; 
};




//导出对账信息
function doExport() {
          
    var bool = window.confirm("导出有点慢，确定导出吗？");
    
    if(!bool){            
        return;
    }       
    
    var url="/outmanager/data/exportcompany";
    window.open(url,'','toolbar=yes,menubar=yes,resizable=yes,location=yes,status=yes,scrollbars=yes');
    setTimeout("focus();",5); 
           
}


//删除所选的记录
function delrecode (url,arr) {

	$.ajax({
		  type: 'POST',
		  url: url,		  
		  dataType: "text",
		  contentType:'application/json;charset=UTF-8', 		  
		  data: JSON.stringify(arr),		  
		  success: function(data){

			  alert("处理成功");
			  
			  //刷新
			  refreshtab();
		  },
		  
		  
		});
	
} 


//添加或者更新记录 单条的json str
function updaterecode (url,jsonstr) {

	$.ajax({
		  type: 'POST',
		  url: url,		  
		  dataType: "text",
		  contentType:'application/json', 		  
		  data: jsonstr,	  
		  success: function(data){

			  alert("处理成功");
			  
			  //刷新
			  refreshtab();
		  },
		  
		  
		});
	
}  



//刷新
function refreshtab(){
	$('#tb_data').bootstrapTable(  
            "refresh",  
            {   
            	url: '/outmanager/data/company_list_json',
            }  
  );
	
}





//show modal
function openml(){
	  
	   $('#myModal').modal("show"); 
}

function validatorForm(){
//表单验证
$('#editform').bootstrapValidator({
    message: '这个值没有被验证',
    feedbackIcons: {
        valid: 'glyphicon glyphicon-ok',
        invalid: 'glyphicon glyphicon-remove',
        validating: 'glyphicon glyphicon-refresh'
    },
    fields: {
        name: {
            validators: {
                notEmpty: {
                    message: '公司名必填'
                }
            }
        },
        
        unit: {
            validators: {
                notEmpty: {
                    message: '组织名称必填'
                }
            }
        },
        
        zizhi: {
            message: '用户名还没有验证',
            validators: {
                notEmpty: {
                    message: '公司资质不能为空'
                },
                stringLength: {
                    min: 1,
                    max: 50,
                    message: '用户名长度在1到50位之间'
                }
            }
        },
       
        area: {
            message: '经营范围还没有验证',
            validators: {
                notEmpty: {
                    message: '经营范围不能为空'
                },
                stringLength: {
                    min: 1,
                    max: 100,
                    message: '经营范围长度在1到100位之间'
                }
            }
        },
        
        
        boss: {
            message: '法人代表还没有验证',
            validators: {
                notEmpty: {
                    message: '法人代表不能为空'
                },
                stringLength: {
                    min: 1,
                    max: 10,
                    message: '法人代表长度在1到10位之间'
                }
            }
        },        
        
        address: {
            message: '注册地址还没有验证',
            validators: {
                notEmpty: {
                    message: '注册地址不能为空'
                },
                stringLength: {
                    min: 1,
                    max: 100,
                    message: '注册地址长度在1到100位之间'
                }
            }
        },
        
        zijin: {
            message: '注册资本还没有验证',
            validators: {
                notEmpty: {
                    message: '注册资本不能为空'
                },
                stringLength: {
                    min: 1,
                    max: 20,
                    message: '注册资本长度在1到20位之间'
                },
                numeric: {message: '只能输入数字,单位万元'},  
            }
        },        
        
    }
});


}


//modal处理事件
function modalaction(){
$('#myModal').on('hide.bs.modal',
	    function() {
//			if(confirm("确定不保存？")){
//				
//			} else {
//				//$('#myModal').modal("show");
//			}
	    })
	    
}



//modal处理事件
function Ok_btn(){
    var jsonuserinfo = $('#editform').serializeObject();  
    var jsonstr =  JSON.stringify(jsonuserinfo);  
	
    //调用后台
	updaterecode("/outmanager/data/company_update",jsonstr);
	
	//提交数据到后台	
	$('#myModal').modal("hide");
	
}


