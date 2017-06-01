
//on load
$(function () {
	
	
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
    
    
	//选择公司查询条件
	try {		
		zTreeInitData();			
	} catch(e){
		alert("单位数据初始化失败！");
	}  
    
    
    //1.初始化Table
    var oTable = new TableInit();
    oTable.Init();

    //2.初始化Button的点击事件
    var oButtonInit = new ButtonInit();
    oButtonInit.Init();
    
    
    //3. 对话框隐藏
    $('#myModal7').modal("hide");
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
            url: '/outmanager/userinfo/fenpei_list_json?uid='+$('#uid').val(),         //请求后台的URL（*）
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
                field: 'connumber',
                title: '合同编号'
            },{
                field: 'fwdept',
                title: '服务部门'
            }, {
                field: 'startdate',
                title: '开始日期',
                formatter : function (value, row, index) {
                	return jsonDateFormat(value);
                }
            },{
                field: 'enddate',
                title: '结束日期',
                formatter : function (value, row, index) {
                	return jsonDateFormat(value);
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
                field: 'kaohe',
                title: '考核信息'
            },{
                field: 'qt1',
                title: '备用1'
            },{
                field: 'qt2',
                title: '备用1'
            }
            
            ]
        });
   
    
    
    //教育table
    $('#tb_data3').bootstrapTable({
        url: '/outmanager/userinfo/jiaoyu_list_json?uid='+$('#uid').val(),         //请求后台的URL（*）
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
            title: '毕业学校'
        },{
            field: 'startdate',
            title: '入学时间',
            formatter : function (value, row, index) {
            	return jsonDateFormat(value);
            }
        },{
            field: 'enddate',
            title: '毕业时间',
            formatter : function (value, row, index) {
            	return jsonDateFormat(value);
            }
        },{
            field: 'xueli',
            title: '学历'
        },{
            field: 'ismaxxl',
            title: '是否最高学历'
        },{
            field: 'xuewei',
            title: '学位'
        },{
            field: 'd1zy',
            title: '第一专业'
        }
        
        ]
    });
    
    //职业table
    $('#tb_data4').bootstrapTable({
        url: '/outmanager/userinfo/jineng_list_json?uid='+$('#uid').val(),         //请求后台的URL（*）
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
            	return jsonDateFormat(value);
            }
        },{
            field: 'enddate',
            title: '结束时间',
            formatter : function (value, row, index) {
            	return jsonDateFormat(value);
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
        url: '/outmanager/userinfo/zhiye_list_json?uid='+$('#uid').val(),         //请求后台的URL（*）
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
            	return jsonDateFormat(value);
            }
        },{
            field: 'endtime',
            title: '结束时间',
            formatter : function (value, row, index) {
            	return jsonDateFormat(value);
            }
        },{
            field: 'name',
            title: '专业技术名称'
        },{
            field: 'gotdate',
            title: '取得资格日期',
            formatter : function (value, row, index) {
            	return jsonDateFormat(value);
            }
        },{
            field: 'dengji',
            title: '专业技术等级'
        }
        
        ]
    });
    
    
    //劳动关系table
    $('#tb_data6').bootstrapTable({
        url: '/outmanager/userinfo/laodong_list_json?uid='+$('#uid').val(),         //请求后台的URL（*）
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
            field: 'connumber',
            title: '合同编号'
        },{
            field: 'startdate',
            title: '开始日期',
            formatter : function (value, row, index) {
            	return jsonDateFormat(value);
            }
        },{
            field: 'enddate',
            title: '终止日期',
            formatter : function (value, row, index) {
            	return jsonDateFormat(value);
            }
        },{
            field: 'lwconname',
            title: '劳务公司名称'
        }
        
        ]
    });
    
 
    //解除关系table
    $('#tb_data7').bootstrapTable({
        url: '/outmanager/userinfo/jiechu_list_json?uid='+$('#uid').val(),         //请求后台的URL（*）
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
        		return jsonDateFormat(value);
            }
        },{
            field: 'gzenddate',
            title: '费用终止日',
        	formatter : function (value, row, index) {
        		return jsonDateFormat(value);
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
        
//     //初始化页面上面的按钮事件
//     $('#btn_query').click(function(){
//              	 
//    	  //处理查询		
//		  refreshtab();
//     });
//     
//     
//     $('#btn_reset').click(function(){
// 	   	 //清除查询条件
//	     $('#query_name').val("");
//	     $('#query_code').val("");
//
//    });
//     
//     
//
//    $('#btn_add7').click(function(){
//  	  //显示添加窗口
//  	  openml(7);
//     });
//    
//      // 编辑任务按钮点击事件
//      $('#btn_edit').click(function(){
//    	 
//    	  var arr = $('#tb_data').bootstrapTable('getSelections');          
//          if(arr.length==1){ 
//        
//        	  var selects = $.parseJSON(JSON.stringify(arr));
//    
//        	  //给modal表单 赋值
//        	  $('#id').val(selects[0].id);
//        	  $('#unit').val(selects[0].unit);
//        	  $('#name').val(selects[0].name);
//        	      	 
//        	  openml();
//
//          } else {
//              alert("请选择要编辑的记录！");
//          }    	  
//    	  
//    	  
//    	  
//      });
//    
//      // 删除任务按钮点击事件
//      $('#btn_delete').click(function(){
//    	
//     
//          var arr = $('#tb_data').bootstrapTable('getSelections');          
//          if(arr.length>0){          
//        	   if(confirm("确定删除此任务吗？")){
//        		   
//        		  //处理删除多个
//        		  // var selects = $.parseJSON(JSON.stringify(arr));
//
//        		   delrecode("/outmanager/user/user_del",arr);
//        	   };
//        	  
//   
//          } else {
//              alert("请选择要删除的记录！");
//          }
//      }
//      
//      
//      
//      );
//      
//
//      // 导出结果集
//      $('#btn_export').click(function(){
//    	  doExport();
//		  
//      });      
    
  };
  return oInit; 
};


//6个表格的增删改
function addact(id) {
	
  if(id==7){
	  $('#id_'+id).val("");
	  $('#jcreason_'+id).val("");
	  $('#jcdate_'+id).val("");	
	  $('#gzenddate_'+id).val("");
	  $('#qt_'+id).val("");			  
	  
  }	
  
  //劳动合同
  if(id==6){
	  
	  //合同期限类型
	  initdroplist($("#conqxtype_"+id),"/outmanager/config/dict_json","","合同期限类型");	
	  
	  //合同状态
	  initdroplist($("#constatus_"+id),"/outmanager/config/dict_json","","合同状态");
	  
	  
	  //清理数据
	  $('#id_'+id).val("");
	  $('#unit_'+id).val("");
	  $('#conqxtype_'+id).val("");
	  $('#qixian_'+id).val("");
	  
	  $('#startdate_'+id).val("");	
	  $('#enddate_'+id).val("");	
	  
	  $('#constatus_'+id).val("");
	  $('#concode_'+id).val("");
	  
	  
	  $('#fristunit_'+id).val("");
	  $('#nwconnumber_'+id).val("");
	  $('#lwconname_'+id).val("");		  
	  
  }
  
  
 //专业技术
 if(id==5){
	 
	  $('#id_'+id).val("");
	 
	  //专业技术资格序列
	  initdroplist($("#xulie_"+id),"/outmanager/config/dict_json","","专业技术资格序列");	
	  
	  //专业技术资格名称
	  initdroplist($("#name_"+id),"/outmanager/config/dict_json","","专业技术资格名称");  
	  	  	  
	  //资格专业分类
	  initdroplist($("#zytype_"+id),"/outmanager/config/dict_json","","资格专业分类");
	  	  
	  //专业子分类
	  initdroplist($("#subtype_"+id),"/outmanager/config/dict_json","","专业子分类");	 
	  
	  //取得资格途径
	  initdroplist($("#gotway_"+id),"/outmanager/config/dict_json","","取得资格途径");	
	
	  //资格等级
	  initdroplist($("#dengji_"+id),"/outmanager/config/dict_json","","资格等级");		  
	  
	  //是否为主要专业技术资质
	  initdroplist($("#ismain_"+id),"/outmanager/config/dict_json","","通用是否");  
	  
	  
	  $('#startdate_'+id).val("");	
	  $('#endtime_'+id).val("");
	  
	  $('#gotdate_'+id).val("");
	  $('#outdate_'+id).val("");
	  
	  $('#gotway_'+id).val("");
	  $('#shareunit_'+id).val("");
	  	  
	  $('#qt_'+id).val("");	  
	  $('#zsnumber_'+id).val("");

	  
  }   
  
 
 //技能认定
 if(id==4){
	 
	  $('#id_'+id).val("");
	 
	  //认定技能资格名称
	  initdroplist($("#rdname_"+id),"/outmanager/config/dict_json","","认定技能资格名称");	
	  
	  //认定技能资格等级
	  initdroplist($("#rddengji_"+id),"/outmanager/config/dict_json","","认定技能资格等级");  
	  
	  //是否为主要
	  initdroplist($("#ismain_"+id),"/outmanager/config/dict_json","","通用是否");  

	  	  	  
	  $('#startdate_'+id).val("");	
	  $('#enddate_'+id).val("");

	  $('#rdunit_'+id).val("");
	  $('#qt_'+id).val("");	  
  
  } 

 //教育信息
 if(id==3){
	 
	  $('#id_'+id).val("");
	 
	  //学历
	  initdroplist($("#xueli_"+id),"/outmanager/config/dict_json","","学历");	
	  
	  //是否最高学历
	  initdroplist($("#ismaxxl_"+id),"/outmanager/config/dict_json","","通用是否");		  
	  
	  //学位
	  initdroplist($("#xuewei_"+id),"/outmanager/config/dict_json","","学位");  
	  
	  //是否最高学位
	  initdroplist($("#ismaxxw_"+id),"/outmanager/config/dict_json","","通用是否");  

	  //同等学历
	  initdroplist($("#tdxl_"+id),"/outmanager/config/dict_json","","同等学历");  

	  //相当毕业
	  initdroplist($("#xdby_"+id),"/outmanager/config/dict_json","","相当毕业");  
	  
	  //专业类别
	  initdroplist($("#zytype_"+id),"/outmanager/config/dict_json","","专业类别");  	  
	  
	  
	  //专业子类别
	  initdroplist($("#zysubtype_"+id),"/outmanager/config/dict_json","","专业子类别");  
	  	  	  
	
	  //学习形式
	  initdroplist($("#xxxs_"+id),"/outmanager/config/dict_json","","学习形式");  	
	  
	  $('#school_'+id).val("");
	  
	  $('#startdate_'+id).val("");
	  $('#enddate_'+id).val("");
	  $('#xwdate_'+id).val("");
	  
	  $('#xwzsnumber_'+id).val("");
	  $('#xlzsnumber_'+id).val("");
	  $('#xwunit_'+id).val("");
	  
	  $('#d1xwtype_'+id).val("");
	  $('#d2xwtype_'+id).val("");
	  
	  $('#d1zy_'+id).val("");
	  $('#d2zy_'+id).val("");

	  $('#xuezhi_'+id).val("");
	  $('#xxqk_'+id).val("");
	  $('#qt_'+id).val("");	  
  
  }
 
 
 
 
 
 //分配信息
 if(id==2){
	 
	  $('#id_'+id).val("");
	  $('#fwdept_'+id).val("");
	 
	  //合同编号
	  initdroplist($("#connumber_"+id),"/outmanager/config/contractcode_json","","")	  
	  
	  //公司名称
	  initdroplist($("#company_"+id),"/outmanager/config/companyjson","","");
	  
	  //岗位序列
	  initdroplist($("#gw_"+id),"/outmanager/config/dict_json","","岗位序列");  	  
	  	  

	  //岗位等级
	  initdroplist($("#gwjb_"+id),"/outmanager/config/dict_json","","参考岗级");  	  
	  
	  //基层单元负责人
	  initdroplist($("#fzr_"+id),"/outmanager/config/dict_json","","基层单元负责人"); 
	  
	  //核算到最小单元
	  initdroplist($("#iszuixiao_"+id),"/outmanager/config/dict_json","","通用是否");  	

	  
	  
	  
	  $('#startdate_'+id).val("");
	  $('#enddate_'+id).val("");
	
	  $('#kaohei_'+id).val("");	 
	  $('#qt1_'+id).val("");
	  $('#qt2_'+id).val("");
  
  }  
 
  //打开对话框
  openml(id);
}

function editact(id) {
	
  var arr = $('#tb_data'+id).bootstrapTable('getSelections');          
  if(arr.length==1){ 

	  var selects = $.parseJSON(JSON.stringify(arr));

	  //给modal表单 赋值
	  if(id==7){
		  $('#id_'+id).val(selects[0].id);
		  $('#jcreason_'+id).val(selects[0].jcreason);
		  $('#jcdate_'+id).val(jsonDateFormat(selects[0].jcdate));	
		  $('#gzenddate_'+id).val(jsonDateFormat(selects[0].gzenddate));
		  $('#qt_'+id).val(selects[0].qt);			  
		  
	  }
	  
	  
	  if(id==6){
		  //合同期限类型
		  initdroplist($("#conqxtype_"+id),"/outmanager/config/dict_json",selects[0].conqxtype,"合同期限类型");	
		  
		  //合同状态
		  initdroplist($("#constatus_"+id),"/outmanager/config/dict_json",selects[0].constatus,"合同状态");
		  
		  $('#id_'+id).val(selects[0].id);
		  $('#unit_'+id).val(selects[0].unit);
		  $('#conqxtype_'+id).val(selects[0].conqxtype);
		  $('#qixian_'+id).val(selects[0].qixian);
		  
		  $('#startdate_'+id).val(jsonDateFormat(selects[0].startdate));	
		  $('#enddate_'+id).val(jsonDateFormat(selects[0].enddate));	
		  
		  //$('#constatus_'+id).val(selects[0].constatus);
		  $('#connumber'+id).val(selects[0].connumber);
		  
		  
		  $('#fristunit_'+id).val(selects[0].fristunit);
		  $('#nwconnumber_'+id).val(selects[0].nwconnumber);
		  $('#lwconname_'+id).val(selects[0].lwconname);			  
		  
	  }
	  
	  
	  //专业技术
	  if(id==5){
	 	 
	 	  $('#id_'+id).val(selects[0].id);
	 	 
	 	  //专业技术资格序列
	 	  initdroplist($("#xulie_"+id),"/outmanager/config/dict_json",selects[0].xulie,"专业技术资格序列");	
	 	  
	 	  //专业技术资格名称
	 	  initdroplist($("#name_"+id),"/outmanager/config/dict_json",selects[0].name,"专业技术资格名称");  
	 	  	  	  
	 	  //资格专业分类
	 	  initdroplist($("#zytype_"+id),"/outmanager/config/dict_json",selects[0].zytype,"资格专业分类");
	 	  	  
	 	  //专业子分类
	 	  initdroplist($("#subtype_"+id),"/outmanager/config/dict_json",selects[0].subtype,"专业子分类");	 
	 	  
	 	  //取得资格途径
	 	  initdroplist($("#gotway_"+id),"/outmanager/config/dict_json",selects[0].gotway,"取得资格途径");	
	 	
	 	  //资格等级
	 	  initdroplist($("#dengji_"+id),"/outmanager/config/dict_json",selects[0].dengji,"资格等级");		  
	 	  
	 	  //是否为主要专业技术资质
	 	  initdroplist($("#ismain_"+id),"/outmanager/config/dict_json",selects[0].ismain,"通用是否");  
	 	  
	 	  
		  $('#startdate_'+id).val(jsonDateFormat(selects[0].startdate));	
		  $('#endtime_'+id).val(jsonDateFormat(selects[0].endtime));
		  
		  $('#gotdate_'+id).val(jsonDateFormat(selects[0].gotdate));
		  $('#outdate_'+id).val(jsonDateFormat(selects[0].outdate));
		  
		  
		  
		  $('#zsnumber_'+id).val(selects[0].zsnumber);
		  $('#gotway_'+id).val(selects[0].gotway);
		  
		  $('#shareunit_'+id).val(selects[0].shareunit);				  
		  $('#qt_'+id).val(selects[0].qt);
	 	  
		  
	   }    
	  //技能认定
	  if(id==4){
	 	 
	 	  $('#id_'+id).val(selects[0].id);
	 	 
	 	  //认定技能资格名称
	 	  initdroplist($("#rdname_"+id),"/outmanager/config/dict_json",selects[0].rdname,"认定技能资格名称");	
	 	  
	 	  //认定技能资格等级
	 	  initdroplist($("#rddengji_"+id),"/outmanager/config/dict_json",selects[0].rddengji,"认定技能资格等级"); 
	 	  
	 	  
		  //是否为主要
	 	  initdroplist($("#ismain_"+id),"/outmanager/config/dict_json",selects[0].ismain,"通用是否");  

	 	  	  	  
	 	  $('#startdate_'+id).val(jsonDateFormat(selects[0].startdate));	
	 	  $('#enddate_'+id).val(jsonDateFormat(selects[0].enddate));

	 	  $('#rdunit_'+id).val(selects[0].rdunit);
	 	  $('#qt_'+id).val(selects[0].qt);	  
	   
	   }
	  
	  
	  
	  //教育信息
	  if(id==3){
	 	 
	 	  $('#id_'+id).val(selects[0].id);
	 	 
		  //学历
		  initdroplist($("#xueli_"+id),"/outmanager/config/dict_json",selects[0].xueli,"学历");	
		  
		  //是否最高学历
		  initdroplist($("#ismaxxl_"+id),"/outmanager/config/dict_json",selects[0].ismaxxl,"通用是否");		  
		  
		  //学位
		  initdroplist($("#xuewei_"+id),"/outmanager/config/dict_json",selects[0].xuewei,"学位");  
		  
		  //是否最高学位
		  initdroplist($("#ismaxxw_"+id),"/outmanager/config/dict_json",selects[0].ismaxxw,"通用是否");  

		  //同等学历
		  initdroplist($("#tdxl_"+id),"/outmanager/config/dict_json",selects[0].tdxl,"同等学历");  

		  //相当毕业
		  initdroplist($("#xdby_"+id),"/outmanager/config/dict_json",selects[0].xdby,"相当毕业");  
		  
		  //专业类别
		  initdroplist($("#zytype_"+id),"/outmanager/config/dict_json",selects[0].zytype,"专业类别");  	  
		  		  
		  //专业子类别
		  initdroplist($("#zysubtype_"+id),"/outmanager/config/dict_json",selects[0].zysubtype,"专业子类别");  
		  
		  //学习形式
		  initdroplist($("#xxxs_"+id),"/outmanager/config/dict_json",selects[0].xxxs,"学习形式");  	
		  
		  $('#school_'+id).val(selects[0].school);
		  
		  $('#startdate_'+id).val(jsonDateFormat(selects[0].startdate));
		  $('#enddate_'+id).val(jsonDateFormat(selects[0].enddate));
		  
		  $('#xwdate_'+id).val(jsonDateFormat(selects[0].xwdate));
		  	  
		  $('#xwzsnumber_'+id).val(selects[0].xwzsnumber);
		  $('#xuelizsnumber_'+id).val(selects[0].xuelizsnumber);
		  $('#xwunit_'+id).val(selects[0].xwunit);
		  
		  $('#d1xwtype_'+id).val(selects[0].d1xwtype);
		  $('#d2xwtype_'+id).val(selects[0].d2xwtype);
		  
		  $('#d1zy_'+id).val(selects[0].d1zy);
		  $('#d2zy_'+id).val(selects[0].d2zy);

		  $('#xuezhi_'+id).val(selects[0].xuezhi);
		  $('#xxqk_'+id).val(selects[0].xxqk);
		  $('#qt_'+id).val(selects[0].qt);
		  
	   
	   }	
	  
	  
	  //分配信息
	  if(id==2){
	 	 
		  $('#id_'+id).val(selects[0].id);
		  
		  $('#fwdept_'+id).val(selects[0].fwdept);
		 
	 	  //合同编号
	 	  initdroplist($("#connumber_"+id),"/outmanager/config/contractcode_json",selects[0].connumber,"")	  
	 	  
	 	  //公司名称
	 	  initdroplist($("#company_"+id),"/outmanager/config/companyjson",selects[0].company,"");
	 	  
	 	  //岗位序列
	 	  initdroplist($("#gw_"+id),"/outmanager/config/dict_json",selects[0].gw,"岗位序列"); 
	 	  
	 	  
	 	  //岗位分类
	 	  initdroplist($("#gwtype_"+id),"/outmanager/config/dict_json",selects[0].gwtype,selects[0].gw); 
	 
	 	  
	 	  //岗位等级
	 	  initdroplist($("#gwjb_"+id),"/outmanager/config/dict_json",selects[0].gwjb,"参考岗级");  	  
	 	  

		  //基层单元负责人
		  initdroplist($("#fzr_"+id),"/outmanager/config/dict_json",selects[0].fzr,"基层单元负责人"); 

	 	  
	 	  //核算到最小单元
	 	  initdroplist($("#iszuixiao_"+id),"/outmanager/config/dict_json",selects[0].iszuixiao,"通用是否");  	


	 	  $('#startdate_'+id).val(jsonDateFormat(selects[0].startdate));
	 	  $('#enddate_'+id).val(jsonDateFormat(selects[0].enddate));
	 	
	 	  $('#kaohei_'+id).val(selects[0].kaohei);	 
	 	  $('#qt1_'+id).val(selects[0].qt1);
	 	  $('#qt2_'+id).val(selects[0].qt2);
	   
	  }
      	 
	  openml(id);

  } else {
      alert("请选择要编辑的记录！");
  }  	

}

function delact(id,delurl,listurl) {
	
  var arr = $('#tb_data'+id).bootstrapTable('getSelections');          
  if(arr.length>0){          
	   if(confirm("确定删除此任务吗？")){
		   
		  //处理删除多个
		  delrecode(id,delurl,listurl,arr);
	   };
	  

  } else {
      alert("请选择要删除的记录！");
  }
}



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
function delrecode(id,url,listurl,arr) {
	
	$.ajax({
		  type: 'POST',
		  url: url,		  
		  dataType: "text",
		  contentType:'application/json;charset=UTF-8', 		  
		  data: JSON.stringify(todelarr(arr)),		  
		  success: function(data){

			  alert("处理成功");
			  
			  //刷新
			  sleep(200); //等待数据更新OK
			  refreshtab(id,listurl); 
			
		  },
		  
		  
		});
	
} 


//添加或者更新记录 单条的对话框id 更新数据url， 取数据url，数据字符串
function updaterecode (id,url,listurl,jsonstr) {
	$.ajax({
		  type: 'POST',
		  url: url,		  
		  dataType: "text",
		  contentType:'application/json', 		  
		  data: jsonstr,
		  //async: false, //同步请求
		  success: function(data){

			  alert("处理成功");
			  
			  //刷新
			  sleep(200); //等待数据更新OK
			  refreshtab(id,listurl);
			
		  }
		  
		  
		});
	
}  



//刷新
function refreshtab(id,url){
	$('#tb_data'+id).bootstrapTable(  
            "refresh",  
            {   
            	url: url,
            }  
  );
	
}





//show modal
function openml(id){
	  
	   $('#myModal'+id).modal("show"); 
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
function Ok_btn(id,url,listurl){
		
    var jsonuserinfo = $('#editform'+id).serializeObject();  
    var jsonstr =  JSON.stringify(jsonuserinfo);  
	
    //调用后台
	updaterecode(id,url,listurl,jsonstr);
	
	//提交数据到后台	
	$('#myModal'+id).modal("hide");
		
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
	initdroplist($("#gwtype_2"),"/outmanager/config/dict_json","",selvalue)
	
	
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


var setting6 = {
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
			onClick: onClick6  //填值地方不一样
		}
	};

var setting2 = {
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
			onClick: onClick2  //填值地方不一样
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
				
				  $.fn.zTree.init($("#treeDemo6"), setting6, data.rows);
				  $.fn.zTree.init($("#treeDemo2"), setting2, data.rows);
								  				  
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
	
	function onClick6(e, treeId, treeNode) {
		
		var zTree = $.fn.zTree.getZTreeObj("treeDemo6"),
		nodes = zTree.getSelectedNodes(),
		v = "";
		

		nodes.sort(function compare(a,b){return a.id-b.id;});
		for (var i=0, l=nodes.length; i<l; i++) {
			v += nodes[i].name + ",";
		}
		
		if (v.length > 0 ) v = v.substring(0, v.length-1);
		
		//赋值
		$("#unit_6").val(v);
		//选定之后隐藏
		hideMenu();
	}
	
	
	function onClick2(e, treeId, treeNode) {
		
		var zTree = $.fn.zTree.getZTreeObj("treeDemo2"),
		nodes = zTree.getSelectedNodes(),
		v = "";
		

		nodes.sort(function compare(a,b){return a.id-b.id;});
		for (var i=0, l=nodes.length; i<l; i++) {
			v += nodes[i].name + ",";
		}
		
		if (v.length > 0 ) v = v.substring(0, v.length-1);
		
		//赋值
		$("#fwdept_2").val(v);
		//选定之后隐藏
		hideMenu();
	}	
	

	
	function showMenu6() {
		var cityObj = $("#unit_6");
		var cityOffset = cityObj.offset();
		
		
		var newleft=cityOffset.left-150;
		
		var newtop=cityOffset.top-40;

		
		$("#menuContent6").css({left:newleft + "px", top:newtop + cityObj.outerHeight() + "px"}).slideDown("fast");

		$("body").bind("mousedown", onBodyDown);
	}
	
	
	
	function showMenu2() {
		var cityObj = $("#fwdept_2");
		var cityOffset = cityObj.offset();
		
		
		var newleft=cityOffset.left-150;
		
		var newtop=cityOffset.top-40;

		
		$("#menuContent2").css({left:newleft + "px", top:newtop + cityObj.outerHeight() + "px"}).slideDown("fast");

		$("body").bind("mousedown", onBodyDown);
	}	
	
	
	//隐藏选择框
	function hideMenu() {
		$("#menuContent6").fadeOut("fast");
		$("#menuContent2").fadeOut("fast");
		$("body").unbind("mousedown", onBodyDown);
	}
	
	
	//处理隐藏选择框事件
	function onBodyDown(event) {
		if (!(event.target.id == "menuBtn" 
			|| event.target.id == "menuContent6" 
			|| $(event.target).parents("#menuContent6").length>0
			|| event.target.id == "menuContent2" 
			|| $(event.target).parents("#menuContent2").length>0)	
		) {
			hideMenu();
		}
	}

