
//on load
$(function () {
	
	
    //6.页面项目初始化部分
	//初始下拉框
	initdroplist($("#query_companyid"),"/outmanager/config/companyjson","","");
	
	//合同编号
	initdroplist($("#query_concode"),"/outmanager/config/contractcode_json","","");
	

	//月份选择
	initmonthlist($("#query_startmonth"),"");
	initmonthlist($("#query_endmonth"),"");
	initmonthlist($("#month"),"");
	
	//初始化reset
    $('#btn_reset').click(function(){	
    	formReset();	
    });
	
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
            url: '/outmanager/gongzi/grlist_json',         //请求后台的URL（*）
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
                field: 'month',
                title: '月份'
            },{
                field: 'unit',
                title: '联通组织'
            },{
                field: 'companyid',
                title: '公司名称'
            },{
                field: 'code',
                title: '人员编号'
            },{
                field: 'name',
                title: '姓名'
            },{
                field: 'yfa',
                title: '应发工资'
            },{
                field: 'shifa',
                title: '实发工资'
            },{
                field: 'gswuxian',
                title: '五险一金公司部分'
            },{
                field: 'qtjine',
                title: '集体支出总额'
            }
            
            ]
        });
    };
    


    //得到查询的参数
    oTableInit.queryParams = function (params) {
        var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
           limit: params.limit,   //页面大小
           offset: params.offset,  //页码
           
           username:$("#query_name").val(),
           unit:$("#query_unit").val(),
           companyid:$("#query_companyid").val(),
           
           
           concode:$("#query_concode").val(),
           usercode:$("#query_code").val(),
           startmonth:$("#query_startmonth").val(), 
           endmonth:$("#query_endmonth").val(),
     
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

      //添加时必须清掉的项目
      $('#id').val("");
    	
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

        	  initmonthlist($("#month"),selects[0].month);
        	  
          	  //初始下拉框
          	  initdroplist($("#companyid"),"/outmanager/config/companyjson",selects[0].companyid,"") 
          	  
          	  
          	  //用户
          	  initdropuser($("#username"),"/outmanager/config/getusers_json",selects[0].name, selects[0].companyid)	         	  
          	  $("#userid").val(selects[0].userid);
          	  
          	  
          	  $("#jiben").val(selects[0].jiben);
          	  $("#jixiao").val(selects[0].jixiao);
          	  $("#jintie").val(selects[0].jintie);
          	  $("#guojie").val(selects[0].guojie);
          	  
          	  $("#jiaban").val(selects[0].jiaban);
          	  $("#qtgz").val(selects[0].qtgz);
          	  $("#yfa").val(selects[0].yfa);
          	  $("#sxkk").val(selects[0].sxkk);  
          	  
          	  
          	  $("#shkk").val(selects[0].shkk);
          	  $("#shifa").val(selects[0].shifa);
          	  $("#yanglao").val(selects[0].yanglao);
          	  $("#shengyu").val(selects[0].shengyu);         	  
     
          	  
          	  
          	  $("#shiye").val(selects[0].shiye);
          	  $("#yiliao").val(selects[0].yiliao);
          	  $("#gongshang").val(selects[0].gongshang);
          	  $("#gongji").val(selects[0].gongji);           	  
          	  
          	  
          	  $("#gswuxian").val(selects[0].gswuxian);
          	  $("#gonghui").val(selects[0].gonghui);
          	  $("#guanli").val(selects[0].guanli);
          	  $("#shuijin").val(selects[0].shuijin);   
          	  
          	  $("#qtjine").val(selects[0].qtjine);
          	  $("#remark").val(selects[0].remark);
          	  $("#startfwdate").val(selects[0].startfwdate);
          	  $("#startbxdate").val(selects[0].startbxdate);            	  
        	      	 
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

        		   delrecode("/outmanager/gongzi/grdel",arr);
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
    
    var url="/outmanager/gongzi/grexport";
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
            	url: '/outmanager/gongzi/grlist_json',
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
	updaterecode("/outmanager/gongzi/grupdate",jsonstr);
	
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
 * obj  下拉选择框object
 * url    区数据url
 * defvalue  默认选择的id值
 * clm 取列的名字，默认是‘name’
 * 初始化下拉选择框
 * @returns
 */
function initdropuser(obj,url,defvalue,lx){  
	
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
		        		 opts += "<option selected value="+keyvalue.id+">"+keyvalue.name+"</option>";
		        	 } else {
		        		 opts += "<option value="+keyvalue.id+">"+keyvalue.name+"</option>";
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

//公司和人员联动
function chgcompany(selvalue){	
	//人员
	initdropuser($("#username"),"/outmanager/config/getusers_json","",selvalue)	
	
}


//人员选择
function chgusername(selvalue){	
	$("#userid").val(selvalue);	
}

//获取最近12个月的数据
var last_year_month = function() {  
	
	
    var d = new Date();  
    var result = [];  
    for(var i = 0; i < 12; i++) {  
        d.setMonth(d.getMonth() - 1);  
        var m = d.getMonth() + 1;  
        m = m < 10 ? "0" + m : m;  
        //在这里可以自定义输出的日期格式  
        result.push(d.getFullYear() + "-" + m);  
        //result.push(d.getFullYear() + "年" + m + '月');  
    }  
   
    return result;  
} 

/**
 * 初始化月份选择下拉框
 * @param obj
 * @returns
 */
function initmonthlist(obj,defvalue){ 
	

	if(defvalue!=undefined && ""!=defvalue){
		
		obj.append("<option selected >" + defvalue + "</option>");
	}
	
	for(var allinfo = last_year_month(), i = 0; i < allinfo.length; i++) {  
	   obj.append("<option value='" + allinfo[i] + "'>" + allinfo[i] + "</option>");
    } 		
    //重新显示
    obj.selectpicker('refresh');
    obj.selectpicker('render');
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

