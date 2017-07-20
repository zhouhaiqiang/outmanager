
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
	
    //1.初始化Table
    var oTable = new TableInit();
    oTable.Init();

    //2.初始化Button的点击事件
    var oButtonInit = new ButtonInit();
    oButtonInit.Init();

       
});


var TableInit = function () {
    var oTableInit = new Object();
    //初始化Table
    oTableInit.Init = function () {
        $('#tb_data').bootstrapTable({
            url: '/outmanager/report/recode_list_json',         //请求后台的URL（*）
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
                field: 'repdate',
                title: '查询日期',
            	formatter : function (value, row, index) {
                    if (value=="") {
                        return '';
                    
                    } else {                   	
                    	return jsonDateFormat(value);    	
                    }
                }	
                
            },{
                field: 'intf',
                title: '数据状态'
            },{
                field: 'isrecreate',
                title: '接口状态'
            },{
                field: 'id',
                title: '操作',
                formatter : function (value, row, index) {
                	return '--'
                }
            }
            ]
        });
    };
    


    //得到查询的参数
    oTableInit.queryParams = function (params) {
        var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
           limit: params.limit,   //页面大小
           offset: params.offset,  //页码

           type: "月报",
 
     
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
    	 
    	 
    	  //生成年度报表
    	  var qdate = $('#query_date').val();
    	  
    	  //checkSlastday checkMlastday 日期检测
    	  if(!checkMlastday(qdate)){   		  
    		  alert("请选择一月的最后一天！");
    		  return false;
    	  }
    	  
    	  //生成报表
    	  var url = "/outmanager/report/createmonth";    	  
    	  $.ajax({
    		  type: 'POST',
    		  url: url,		  
    		  dataType: "text",
    		  contentType:'application/json;charset=UTF-8', 		  
    		  data: qdate,		  
    		  success: function(data){

    			  alert(data);   			  
    			  //刷新
    			  refreshtab();
    		  },
    		  
    		  
    		});   	  
     	  

     });
     
     
      
      
      
      
      
      
  };
  return oInit; 
};



//刷新
function refreshtab(){
	$('#tb_data').bootstrapTable(  
            "refresh",  
            {   
            	url: '/outmanager/report/recode_list_json',
            }  
  );
	
}

