
//on load
$(function () {
	
    //1.初始化Table
    var oTable = new TableInit();
    oTable.Init();       
});


var TableInit = function () {
    var oTableInit = new Object();
    //初始化Table
    oTableInit.Init = function () {
        $('#tb_data').bootstrapTable({
            url: '/outmanager/report/getexl_json',         //请求后台的URL（*）        	
            method: 'get',                      //请求方式（*）
            toolbar: '#toolbar',                //工具按钮用哪个容器
            striped: false,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: false,                   //是否显示分页（*）
            sortable: false,                     //是否启用排序
            sortOrder: "asc",                   //排序方式
            queryParams: oTableInit.queryParams,//传递参数（*）
            sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
            pageNumber:1,                       //初始化加载第一页，默认第一页
            pageSize: 10,                       //每页的记录行数（*）
            pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
            search: false,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
            strictSearch: true,
            showColumns: false,                  //是否显示所有的列
            showRefresh: false,                  //是否显示刷新按钮
            minimumCountColumns: 2,             //最少允许的列数
            clickToSelect: false,                //是否启用点击选中行
            height: 700,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            uniqueId: "id",                     //每一行的唯一标识，一般为主键列
            showToggle:false,                    //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            detailView: false,                   //是否显示父子表
            
            //表格导出相关设置
            showExport : true,                   //是否显示
            exportDataType : "basic",              //数据导出类型    basic, all, selected
            exportTypes: ['excel'], //['json', 'xml', 'csv', 'txt', 'sql', 'excel'], 导出文件格式

            
            columns: [
                
                      
             //表头         
             //1 行         
             [       
				{
				    "title": "各岗位序列人员占比变动情况表",
				    "halign":"center",
				    "align":"center",
				    "colspan": 6   //跨度
				}
			], 
             //2 行
			[
				{
				    "title": "填报单位："+$('#unit').val()+'('+$('#repdate').val()+')',
				    "halign":"left",
				    "align":"center",
				    "colspan": 6   //跨度
				},
			
			],
			//3  行
			
			//db 报表数据暂时
			[   
	            {
	                field: 'a',
	                title: '项目'
	            },{
	                field: 'b',
	                title: '销售与客服序列'
	            },{
	                field: 'c',
	                title: '产品与市场序列'
	            },{
	                field: 'd',
	                title: '建设与维护序列'
	            },{
	                field: 'e',
	                title: '内部支撑序列'
	            },{
	                field: 'f',
	                title: '管理序列'
	            }
	            ]
     
             
             
            ]
        });
    };
    


    //得到查询的参数
    oTableInit.queryParams = function (params) {
        var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
           limit: params.limit,   //页面大小
           offset: params.offset,  //页码
           
           //参数
           id: $('#id').val(),
        	   
        };
        

        return temp;
    };
    
    return oTableInit;
};

