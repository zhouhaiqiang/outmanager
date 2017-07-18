
//on load
$(function () {
	

	//选择公司查询条件
	try {		
		zTreeInitData();			
	} catch(e){
		alert("单位数据初始化失败！");
	}	
	
    //时间控件
    $('.form_date').datetimepicker({
        language:  'zh-CN',
        weekStart: 1,
        todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		minView: 2,
		forceParse: 0
    });
	
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
            url: '/outmanager/data/contract_list_json',         //请求后台的URL（*）
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
            columns: [{
                checkbox: true
            },{
                field: 'conType',
                title: '合同类型'
            },{
                field: 'unit',
                title: '签署主体'
            },{
                field: 'companyid',
                title: '外包公司'
            }, {
                field: 'name',
                title: '合同名称'
            },{
                field: 'conCode',
                title: '合同编号'
            },{
                field: 'yewu',
                title: '业务范围'
            }, {
                field: 'startdate',
                title: '开始时间',
            	formatter : function (value, row, index) {
                    if (value=="") {
                        return '';
                    
                    } else {                   	
                    	return jsonDateFormat(value);    	
                    }
                }	
            }, {
                field: 'enddate',
                title: '结束时间',
                formatter : function (value, row, index) {
                    if (value=="") {
                        return '';
                    
                    } else {                    	
                    	return jsonDateFormat(value);    	
                    }
                }
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
     
     
    //初始化reset
    $('#btn_reset').click(function(){	
    	formReset();	
    }); 
    

    $('#btn_add').click(function(){

      //id必须清空
      $('#id').val();	
    	
      //初始下拉框
      initdroplist($("#companyid"),"/outmanager/config/companyjson","","")  

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
        	  
        	  $('#startdate').val(jsonDateFormat(selects[0].startdate));
        	  $('#enddate').val(jsonDateFormat(selects[0].enddate));
        	  
        	  
        	  $('#conType').val(selects[0].conType); //下拉框处理
        	  $('#jine').val(selects[0].jine);   
        	  $('#yewu').val(selects[0].yewu);
        	  $('#qixian').val(selects[0].qixian);       	  

        	  $('#conCode').val(selects[0].conCode);
        	 
        	
        	  //$('#zhizhao').val(selects[0].zhizhao); //附件处理 ????
        	  //$('#zizhidoc').val(selects[0].zizhidoc); //附件处理?????
        	  
        	  
        	  //初始下拉框
        	  initdroplist($("#companyid"),"/outmanager/config/companyjson",selects[0].companyid,"")

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

        		   delrecode("/outmanager/data/contract_del",arr);
        	   };
        	  
   
          } else {
              alert("请选择要删除的记录！");
          }
      }
      
      
      
      );
      

      // 导出结果集
      $('#btn_export').click(function(){
    	  doExport();
		  
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
    
    var url="/outmanager/data/exportcontract";
    window.open(url,'','toolbar=yes,menubar=yes,resizable=yes,location=yes,status=yes,scrollbars=yes');
    setTimeout("focus();",5); 
           
}


//删除所选的记录
function delrecode(url,arr) {

	$.ajax({
		  type: 'POST',
		  url: url,		  
		  dataType: "text",
		  contentType:'application/json;charset=UTF-8', 		  
		  data: JSON.stringify(todelarr(arr)),		  
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
            	url: '/outmanager/data/contract_list_json',
            }  
  );
	
}





//show modal
function openml(){
	  
	   $('#myModal').modal("show"); 
}


/*
 * 页面验证
 */
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
                    message: '合同名必填'
                }
            }
        },
        
        unit: {
            validators: {
                notEmpty: {
                    message: '签约主体必填'
                }
            }
        },
        
        startdate: {
            validators: {
                notEmpty: {
                    message: '开始时间必填'
                }
            }
        },
        
        enddate: {
            validators: {
                notEmpty: {
                    message: '结束时间必填'
                }
            }
        },
        
        yewu: {
         
            validators: {
                notEmpty: {
                    message: '业务范围不能为空'
                },
                stringLength: {
                    min: 1,
                    max: 50,
                    message: '业务范围长度在1到50位之间'
                }
            }
        },    
        
        conCode: {
           
            validators: {
                notEmpty: {
                    message: '合同编号不能为空'
                }
                
            }
        },   
        
        jine: {
            
            validators: {
            	numeric: {message: '只能输入数字！'}, 
                
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
	updaterecode("/outmanager/data/contract_update",jsonstr);
	
	//提交数据到后台	
	$('#myModal').modal("hide");
	
}



/**
 * obj  下拉选择框object
 * url    区数据url
 * defvalue  默认选择的id值
 * clm 取列的名字，默认是‘name’
 * 初始化下拉选择框
 * @returns
 */
function initdroplist(obj,url,defvalue,lx){  
	
	//参数
	var par = {
            "lx":lx
        };
	
	$.ajax({    
	        "type" : 'get',    
	        "url": url,  
	        "data": par,
	        "dataType" : "json", 
	        "timeout": 30000,
	        "success" : function(data) {    
	        	 
		         var rows = data.rows;  
		         var opts = "";
		        
		         rows.forEach(function(keyvalue){  
		        		        	
		        	 if(defvalue==keyvalue.name){
		        		 opts += "<option selected >"+keyvalue.name+"</option>";
		        	 } else {
		        		 opts += "<option>"+keyvalue.name+"</option>";
		        	 }
		        			        	  
		         })
		         
		         //添加选项
		         obj.empty();
		         obj.append(opts);
		         
		         //重新显示
		         obj.selectpicker('refresh');
		         obj.selectpicker('render');
		         
	        }
	
	});
}



/**
 *  **************zTree js********************
 *  *******************************
 *  *******************************
 *  *******************************
 */
var setting = {
		view: {
			dblClickExpand: false
		},
		data: {
			simpleData: {
				enable: true
			}
		},
		callback: {
			beforeClick: beforeClick,
			onClick: onClick
		}
	};


var setting1 = {
		view: {
			dblClickExpand: false
		},
		data: {
			simpleData: {
				enable: true
			}
		},
		callback: {
			beforeClick: beforeClick,
			onClick: onClick1  //填值地方不一样
		}
	};

	var zNodes =[
		{id:1, pId:0, name:"北京"},
		{id:2, pId:1, name:"三明"},
		{id:3, pId:1, name:"长沙"}
	 ];
	
	
	//取初始化数据
	function zTreeInitData(){
		
		$.ajax({
			  type: 'GET',
			  url: "/outmanager/config/getunits_json",		  
			  dataType: "json",
			  contentType:'application/json;charset=UTF-8', 		  		   
			  success: function(data){
		
				  $.fn.zTree.init($("#treeDemo"), setting, data.rows);
				  $.fn.zTree.init($("#treeDemo1"), setting1, data.rows);
								  				  
			  }			  			  
			});		
				
	}
	
	
	
    //树操作事件
	function beforeClick(treeId, treeNode) {
//		var check = (treeNode && !treeNode.isParent);
//		if (!check) alert("只能选择城市...");
//		return check;
	}
	

	function onClick(e, treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
		nodes = zTree.getSelectedNodes(),
		v = "";
		nodes.sort(function compare(a,b){return a.id-b.id;});
		for (var i=0, l=nodes.length; i<l; i++) {
			v += nodes[i].name + ",";
		}
		if (v.length > 0 ) v = v.substring(0, v.length-1);
		var cityObj = $("#query_unit");
		cityObj.attr("value", v);
	}
	
	function onClick1(e, treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj("treeDemo1"),
		nodes = zTree.getSelectedNodes(),
		v = "";
		nodes.sort(function compare(a,b){return a.id-b.id;});
		for (var i=0, l=nodes.length; i<l; i++) {
			v += nodes[i].name + ",";
		}
		if (v.length > 0 ) v = v.substring(0, v.length-1);
		var cityObj = $("#unit");
		cityObj.attr("value", v);
	}	
	

	function showMenu() {
		var cityObj = $("#query_unit");
		var cityOffset = cityObj.offset();
		$("#menuContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");

		$("body").bind("mousedown", onBodyDown);
	}
	
	function showMenu1() {
		var cityObj = $("#unit");
		var cityOffset = cityObj.offset();
		
		
		var newleft=cityOffset.left-160;
		
		var newtop=cityOffset.top-40;

		
		$("#menuContent1").css({left:newleft + "px", top:newtop + cityObj.outerHeight() + "px"}).slideDown("fast");

		$("body").bind("mousedown", onBodyDown);
	}	
	
	
	//隐藏选择框
	function hideMenu() {
		$("#menuContent").fadeOut("fast");
		$("#menuContent1").fadeOut("fast");
		$("body").unbind("mousedown", onBodyDown);
	}
	
	
	//处理隐藏选择框事件
	function onBodyDown(event) {
		if (!(event.target.id == "menuBtn" 
			|| event.target.id == "menuContent" 
			|| $(event.target).parents("#menuContent").length>0
			|| event.target.id == "menuContent1" 
			|| $(event.target).parents("#menuContent1").length>0)) {
			hideMenu();
		}
	}

