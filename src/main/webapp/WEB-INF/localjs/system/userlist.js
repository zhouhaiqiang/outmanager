
//on load
$(function () {
	
    //6.页面项目初始化部分
	
	
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
            url: '/outmanager/system/user_list_json',         //请求后台的URL（*）
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
                field: 'code',
                title: '人员编号'
            },{
                field: 'name',
                title: '人员姓名'
            },           
            {
                field: 'id',
                title: '用户类型',
                formatter : function (value, row, index) {                 	   
                	return "正式员工";    	
                }
           
            }, {
                field: 'id',
                title: '是否有效',
                formatter : function (value, row, index) {                 	   
                	return "有效";    	
                }
            }, {
                field: 'id',
                title: '职责',
                formatter : function (value, row, index) {                 	   
                    	return "<a href='#' onclick='openml(\""+value+"\");'>职责详细</a>";    	              
                }
            },
            
            ]
        });
    };
    

	//初始化人员职责列表（空表）
    $('#tb_data2').bootstrapTable({
        url: '/outmanager/system/userduyt_list_json?uid=xxxxx',         //请求后台的URL（*）
        method: 'get',                      //请求方式（*）
        toolbar: '#toolbar2',                //工具按钮用哪个容器
        striped: false,                      //是否显示行间隔色
        cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: false,                   //是否显示分页（*）
        sortable: false,                     //是否启用排序       
        sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
        pageNumber:1,                       //初始化加载第一页，默认第一页
        pageSize: 100,                       //每页的记录行数（*）
        pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
        search: false,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
        strictSearch: false,
        showColumns: false,                  //是否显示所有的列
        showRefresh: false,                  //是否显示刷新按钮
        clickToSelect: false,                //是否启用点击选中行
        height: 500,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
        uniqueId: "id",                     //每一行的唯一标识，一般为主键列
        showToggle:false,                    //是否显示详细视图和列表视图的切换按钮
        cardView: false,                    //是否显示详细视图
        detailView: false,                   //是否显示父子表
        columns: [{
            checkbox: true
        },{
            field: 'duty',
            title: '职责名称'
        },{
            field: 'remark',
            title: '职责描述'
        },{
            field: 'unit',
            title: '组织名称'
        },{
            field: 'selforg',
            title: '管理本单位'
        },{
            field: 'suborg',
            title: '管理子单位'
        }
        
        ]
    });   
    
    
    
    

    //得到查询的参数
    oTableInit.queryParams = function (params) {
        var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
           limit: params.limit,   //页面大小
           offset: params.offset,  //页码

           unit:$("#query_unit").val(),
           code:$("#query_code").val(),

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
     
     
     $('#btn_reset').click(function(){
 	   	 //清除查询条件
	     $('#query_name').val("");
	     $('#query_code').val("");

    });
     
     

    $('#btn_add').click(function(){

  	  $('#id').val("");
  	  $('#unitid').val("");
  	  $('#unit').val("");
  	  
  	  //初始化控件
  	  initdroplist($("#selforg"),"/outmanager/config/dict_json","是","通用是否")
  	  
  	  initdroplist($("#suborg"),"/outmanager/config/dict_json","是","通用是否")
  	  
  	  //职务选择框
  	  initdroplist($("#duty"),"/outmanager/config/duty_json","","")
  		  
  	  
  	  $('#editModal').modal("show");
  	  
  	  
        
     });
    
      // 编辑任务按钮点击事件
      $('#btn_edit').click(function(){
    	 
    	  var arr = $('#tb_data2').bootstrapTable('getSelections');          
          if(arr.length==1){ 
        
        	  var selects = $.parseJSON(JSON.stringify(arr));
        	
        	  
        	  $('#id').val(selects[0].id);

          	  //初始化控件
          	  initdroplist($("#selforg"),"/outmanager/config/dict_json",selects[0].selforg,"通用是否")
          	  
          	  initdroplist($("#suborg"),"/outmanager/config/dict_json",selects[0].suborg,"通用是否")
          	  
          	  //职务选择框
          	  initdroplist($("#duty"),"/outmanager/config/duty_json",selects[0].duty,"")

          	  $('#unitid').val(selects[0].unitid);
          	  $('#unit').val(selects[0].unit);
          	

        	  $('#editModal').modal("show");

          } else {
              alert("请选择要编辑的记录！");
          } 
      });
    
      // 删除任务按钮点击事件
      $('#btn_delete').click(function(){
          var arr = $('#tb_data2').bootstrapTable('getSelections'); 
          
          
        
          if(arr.length>0){        	 
        	   if(confirm("确定删除此任务吗？")){
        		   
        		  //处理删除多个        		
        		  delrecode(arr[0].userid,"/outmanager/system/duty_del",arr);
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
    
    var url="/outmanager/system/export";
    window.open(url,'','toolbar=yes,menubar=yes,resizable=yes,location=yes,status=yes,scrollbars=yes');
    setTimeout("focus();",5); 
           
}


//删除所选的记录
function delrecode(uid,url,arr) {

	$.ajax({
		  type: 'POST',
		  url: url,		  
		  dataType: "text",
		  contentType:'application/json;charset=UTF-8', 		  
		  data: JSON.stringify(arr),		  
		  success: function(data){

			  alert("处理成功");
			  
			  //刷新
			  refreshduty(uid);
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
			  //refreshtab();
			  
			  refreshduty($('#uid').val());
		  },
		  
		  
		});
	
}  



//刷新
function refreshtab(){
	$('#tb_data').bootstrapTable(  
            "refresh",  
            {   
            	url: '/outmanager/system/user_list_json',
            }  
  );
	
}


//刷新职务
function refreshduty(uid){

	$('#tb_data2').bootstrapTable(  
            "refresh",  
            {   
            	url: '/outmanager/system/userduyt_list_json?uid='+uid,
            }  
  );
	
}


//show modal
function openml(uid){
	
		
	//选定用户	
	//列表
	$('#uid').val(uid);
	//职务编辑
	$('#userid').val(uid);
 	

    
    //强制刷新
    refreshduty(uid);
	
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
                    message: '姓名必填'
                }
            }
        },
        
        idnumber: {
            validators: {
                notEmpty: {
                    message: '省份证必填'
                }
            }
        },
         
        unit: {
            validators: {
                notEmpty: {
                    message: '所属组织名称必填'
                }
            }
        },
        
        inunicomdate: {
            validators: {
                notEmpty: {
                    message: '从事联通业务开始时间必填'
                }
            }
        },
        
        companyid: {
            validators: {
                notEmpty: {
                    message: '所属公司必填'
                }
            }
        },
        
        concode: {
         
            validators: {
                notEmpty: {
                    message: '所属合同编号必填'
                }
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
	updaterecode("/outmanager/system/duty_update",jsonstr);
	
	//提交数据到后台	
	$('#editModal').modal("hide");
	
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

//岗位序列——类型联动
function chggwnumber(selvalue){
	
	//岗位分类
	initdroplist($("#gwtype"),"/outmanager/config/dict_json","",selvalue)
	
	
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
		id = "";
		nodes.sort(function compare(a,b){return a.id-b.id;});
		for (var i=0, l=nodes.length; i<l; i++) {
			v += nodes[i].name + ",";		
			id += nodes[i].id + ",";
		
		}
		if (v.length > 0 ) v = v.substring(0, v.length-1);
		if (id.length > 0 ) id = id.substring(0, id.length-1);

		$("#unit").val(v);
		$("#unitid").val(id);

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
		
		
		var newleft=cityOffset.left-365;
		
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

