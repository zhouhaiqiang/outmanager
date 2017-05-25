
//on load
$(function () {
	

    //1.初始化Table
    var oTable = new TableInit();
    oTable.Init();

    //2.初始化Button的点击事件
    var oButtonInit = new ButtonInit();
    oButtonInit.Init();
    
//    
//    //3. 对话框隐藏
//    $('#myModal').modal("hide");
//    
//    //4. 表单验证
//    validatorForm();
//    
//    //5. 提交事件
//    modalaction();
   
});


var TableInit = function () {
    var oTableInit = new Object();
    //初始化Table
    oTableInit.Init = function () {
    	
    	
    	//分配table
        $('#tb_data2').bootstrapTable({
            url: '/outmanager/userinfo/fenpei_list_json?uid='+$('#id').val(),         //请求后台的URL（*）
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
                field: 'company',
                title: '公司名称'
            },{
                field: 'concode',
                title: '合同编号'
            },{
                field: 'unit',
                title: '服务部门'
            }, {
                field: 'startdate',
                title: '开始日期',
                formatter : function (value, row, index) {
                    if (value=="") {
                        return '';
                    
                    } else {                    	
                    	return jsonDateFormat(value);    	
                    }
                }
            },{
                field: 'enddate',
                title: '结束日期',
                formatter : function (value, row, index) {
                    if (value=="") {
                        return '';
                    
                    } else {                    	
                    	return jsonDateFormat(value);    	
                    }
                }
            },{
                field: 'gw',
                title: '岗位序列'
            },{
                field: 'gwtype',
                title: '岗位分类'
            },{
                field: 'gwjb',
                title: '参考岗级'
            },{
                field: 'kaohei',
                title: '考核信息'
            },{
                field: 'jicong',
                title: '基从单元负责人'
            },{
                field: 'zuixiaody',
                title: '核算到最小单元'
            },{
                field: 'bak1',
                title: '备用1'
            },{
                field: 'bak2',
                title: '备用1'
            }
            
            ]
        });
   
    
    
    //教育table
    $('#tb_data3').bootstrapTable({
        url: '/outmanager/userinfo/jiaoyu_list_json?uid='+$('#id').val(),         //请求后台的URL（*）
        method: 'get',                      //请求方式（*）
        toolbar: '#toolbar3',                //工具按钮用哪个容器
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
            field: 'school',
            title: '学校'
        },{
            field: 'startdate',
            title: '入学时间',
            formatter : function (value, row, index) {
                if (value=="") {
                    return '';
                
                } else {                    	
                	return jsonDateFormat(value);    	
                }
            }
        },{
            field: 'enddate',
            title: '毕业时间',
            formatter : function (value, row, index) {
                if (value=="") {
                    return '';
                
                } else {                    	
                	return jsonDateFormat(value);    	
                }
            }
        },{
            field: 'xueli',
            title: '学历'
        },{
            field: 'ismax',
            title: '是否最高学历'
        },{
            field: 'xuewei',
            title: '学位'
        },{
            field: 'zy',
            title: '第一专业'
        }
        
        ]
    });
    
    //职业table
    $('#tb_data4').bootstrapTable({
        url: '/outmanager/userinfo/zhiye_list_json?uid='+$('#id').val(),         //请求后台的URL（*）
        method: 'get',                      //请求方式（*）
        toolbar: '#toolbar4',                //工具按钮用哪个容器
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
            field: 'startdate',
            title: '开始时间',
            formatter : function (value, row, index) {
                if (value=="") {
                    return '';
                
                } else {                    	
                	return jsonDateFormat(value);    	
                }
            }
        },{
            field: 'enddate',
            title: '结束时间',
            formatter : function (value, row, index) {
                if (value=="") {
                    return '';
                
                } else {                    	
                	return jsonDateFormat(value);    	
                }
            }
        },{
            field: 'rdunit',
            title: '认定单位'
        },{
            field: 'rdname',
            title: '认定技术名称'
        },{
            field: 'rddengji',
            title: '认定技术等级'
        },{
            field: 'ismain',
            title: '是否主要技能认定'
        }
        
        ]
    });    
    

    //专业table
    $('#tb_data5').bootstrapTable({
        url: '/outmanager/userinfo/zhuanye_list_json?uid='+$('#id').val(),         //请求后台的URL（*）
        method: 'get',                      //请求方式（*）
        toolbar: '#toolbar5',                //工具按钮用哪个容器
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
            field: 'startdate',
            title: '开始时间',
            formatter : function (value, row, index) {
                if (value=="") {
                    return '';
                
                } else {                    	
                	return jsonDateFormat(value);    	
                }
            }
        },{
            field: 'enddate',
            title: '结束时间',
            formatter : function (value, row, index) {
                if (value=="") {
                    return '';
                
                } else {                    	
                	return jsonDateFormat(value);    	
                }
            }
        },{
            field: 'name',
            title: '专业技术名称'
        },{
            field: 'gotdate',
            title: '取得资格日期',
            formatter : function (value, row, index) {
                if (value=="") {
                    return '';
                
                } else {                    	
                	return jsonDateFormat(value);    	
                }
            }
        },{
            field: 'dengji',
            title: '专业技术等级'
        }
        
        ]
    });
    
    
    //劳动关系table
    $('#tb_data6').bootstrapTable({
        url: '/outmanager/userinfo/laodong_list_json?uid='+$('#id').val(),         //请求后台的URL（*）
        method: 'get',                      //请求方式（*）
        toolbar: '#toolbar6',                //工具按钮用哪个容器
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
            field: 'unit',
            title: '服务单位'
        },{
            field: 'constatus',
            title: '合同状态'
        },{
            field: 'contype',
            title: '合同类型'
        },{
            field: 'nwconnumber',
            title: '合同编号'
        },{
            field: 'startdate',
            title: '开始日期',
            formatter : function (value, row, index) {
                if (value=="") {
                    return '';
                
                } else {                    	
                	return jsonDateFormat(value);    	
                }
            }
        },{
            field: 'enddate',
            title: '终止日期',
            formatter : function (value, row, index) {
                if (value=="") {
                    return '';
                
                } else {                    	
                	return jsonDateFormat(value);    	
                }
            }
        },{
            field: 'lwcomnane',
            title: '劳务公司名称'
        }
        
        ]
    });
    
 
    //解除关系table
    $('#tb_data7').bootstrapTable({
        url: '/outmanager/userinfo/jiechu_list_json?uid='+$('#id').val(),         //请求后台的URL（*）
        method: 'get',                      //请求方式（*）
        toolbar: '#toolbar7',                //工具按钮用哪个容器
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
            field: 'jcreason',
            title: '解除原因'
        },{
            field: 'jcdate',
            title: '解除日期',
        	formatter : function (value, row, index) {
                if (value=="") {
                    return '';
                
                } else {                   	
                	return jsonDateFormat(value);    	
                }
            }
        },{
            field: 'gzenddate',
            title: '费用终止日',
        	formatter : function (value, row, index) {
                if (value=="") {
                    return '';
                
                } else {                   	
                	return jsonDateFormat(value);    	
                }
            }
        },{
            field: 'qt',
            title: '途径说明（新外包公司名）'
        }
        
        ]
    });
    
    };

    return oTableInit;
}
    



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


//	
//    	
//  	  //初始下拉框
//  	  initdroplist($("#companyid"),"/outmanager/config/companyjson","","")  	  
//  	  
//      //合同编号
//      initdroplist($("#concode"),"/outmanager/config/contractcode_json","","")      	  
//  	  
//
//  	  //政治面貌
//	  initdroplist($("#zhengzhi"),"/outmanager/config/dict_json","","政治面貌")
//		
//	  //国籍
//	  initdroplist($("#nationality"),"/outmanager/config/dict_json","","国籍")
//	  
//	  //民族
//	  initdroplist($("#mingz"),"/outmanager/config/dict_json","","民族")	  
//	  
//	  //户口类型
//	  initdroplist($("#hukoutype"),"/outmanager/config/dict_json","","户口类型")	  
//	  
//	  //从事外包业务类型
//	  initdroplist($("#ywtype"),"/outmanager/config/dict_json","","从事外包业务类型")	  
//	  
//	  //从事联通服务途径
//	  initdroplist($("#ywtj"),"/outmanager/config/dict_json","","从事联通服务途径")
//	  
//	  //纳税地
//	  initdroplist($("#nsaddress"),"/outmanager/config/dict_json","","纳税地")	  
//	  
//	  //社保缴纳地
//	  initdroplist($("#sbaddress"),"/outmanager/config/dict_json","","社保缴纳地")	  
//	  
//	  //岗位序列
//	  initdroplist($("#gwnumber"),"/outmanager/config/dict_json","","岗位序列")	  
//	  	  	  	  
//	  //参考岗级
//	  initdroplist($("#gwdj"),"/outmanager/config/dict_json","","参考岗级")	  	  
	 
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

        		   delrecode("/outmanager/user/user_del",arr);
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
          
	var datatype = $('#datatype').val();
	
	if(datatype==""){
		alert("请选择导出数据类型！");
		return;
	}
    var bool = window.confirm("导出有点慢，确定导出吗？");
    
    if(!bool){            
        return;
    }       
    
    var url="/outmanager/user/export?type="+datatype;
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
            	url: '/outmanager/user/user_list_json',
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
	updaterecode("/outmanager/user/user_update",jsonstr);
	
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

