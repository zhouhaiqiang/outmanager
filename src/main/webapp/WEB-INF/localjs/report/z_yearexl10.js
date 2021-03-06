
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
				    "title": "人员素质结构优化分析",
				    "halign":"center",
				    "align":"center",
				    "colspan": 45   //跨度
				}
			], 
             //2 行
			[
				{
				    "title": "填报单位："+$('#unit').val()+'('+$('#repdate').val()+')',
				    "halign":"left",
				    "align":"center",
				    "colspan": 45   //跨度
				},
			
			],
			//3  行
			
           
			
			/////////////////////////////////////////////////////////
			 
			   
			   // 1...
		    [
			 			    
				{
				    "title": "组织",
				    "halign":"center",
				    "align":"center",
				    "colspan": 1,   //跨行
				    "rowspan": 4,    //跨列
				},
				{
				    "title": "综合情况",
				    "halign":"center",
				    "align":"center",
				    "colspan": 8,   //跨行
				    "rowspan": 1,    //跨列
				},
				{
				    "title": "全部从业人员",
				    "halign":"center",
				    "align":"center",
				    "colspan": 9,   //跨行
				    "rowspan": 1,    //跨列
				},
				{
				    "title": "合同制员工",
				    "halign":"center",
				    "align":"center",
				    "colspan": 9,   //跨行
				    "rowspan": 1,    //跨列
				},
				{
				    "title": "劳务派遣人员",
				    "halign":"center",
				    "align":"center",
				    "colspan": 9,   //跨行
				    "rowspan": 1,    //跨列
				},
				{
				    "title": "紧密型业务外包人员",
				    "halign":"center",
				    "align":"center",
				    "colspan": 9,   //跨行
				    "rowspan": 1,    //跨列
				}
				
				
				],
				
				
				[ //2...
				   				    
				   {
					    "title": "平均年龄",
					    "halign":"center",
					    "align":"center",
					    "colspan": 2,   //跨行
					    "rowspan": 1,    //跨列
					},{
					    "title": "35岁及以下人员占比",
					    "halign":"center",
					    "align":"center",
					    "colspan": 2,   //跨行
					    "rowspan": 1,    //跨列
					},{
					    "title": "学历结构",
					    "halign":"center",
					    "align":"center",
					    "colspan": 4,   //跨行
					    "rowspan": 1,    //跨列
					},{
					    "title": "占比提升（％）",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 3,    //跨列
					},{
					    "title": "平均年龄",
					    "halign":"center",
					    "align":"center",
					    "colspan": 2,   //跨行
					    "rowspan": 1,    //跨列
					},{
					    "title": "35岁及以下人员占比",
					    "halign":"center",
					    "align":"center",
					    "colspan": 2,   //跨行
					    "rowspan": 1,    //跨列
					},{
					    "title": "学历结构",
					    "halign":"center",
					    "align":"center",
					    "colspan": 4,   //跨行
					    "rowspan": 1,    //跨列
					},{
					    "title": "占比提升（％）",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 3,    //跨列
					},{
					    "title": "平均年龄",
					    "halign":"center",
					    "align":"center",
					    "colspan": 2,   //跨行
					    "rowspan": 1,    //跨列
					},{
					    "title": "35岁及以下人员占比",
					    "halign":"center",
					    "align":"center",
					    "colspan": 2,   //跨行
					    "rowspan": 1,    //跨列
					},{
					    "title": "学历结构",
					    "halign":"center",
					    "align":"center",
					    "colspan": 4,   //跨行
					    "rowspan": 1,    //跨列
					},{
					    "title": "占比提升（％）",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 3,    //跨列
					},{
					    "title": "平均年龄",
					    "halign":"center",
					    "align":"center",
					    "colspan": 2,   //跨行
					    "rowspan": 1,    //跨列
					},{
					    "title": "35岁及以下人员占比",
					    "halign":"center",
					    "align":"center",
					    "colspan": 2,   //跨行
					    "rowspan": 1,    //跨列
					},{
					    "title": "学历结构",
					    "halign":"center",
					    "align":"center",
					    "colspan": 4,   //跨行
					    "rowspan": 1,    //跨列
					},{
					    "title": "占比提升（％）",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 3,    //跨列
					},{
					    "title": "平均年龄",
					    "halign":"center",
					    "align":"center",
					    "colspan": 2,   //跨行
					    "rowspan": 1,    //跨列
					},{
					    "title": "35岁及以下人员占比",
					    "halign":"center",
					    "align":"center",
					    "colspan": 2,   //跨行
					    "rowspan": 1,    //跨列
					},{
					    "title": "学历结构",
					    "halign":"center",
					    "align":"center",
					    "colspan": 4,   //跨行
					    "rowspan": 1,    //跨列
					}
					
				],
				
				
				////////////////////////////////
				[
				   {
					    "title": $('#repdate').val().substring(0,4)+"年",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 2,    //跨列
					},{
					    "title": "降低（岁）",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 2,    //跨列
					},{
					    "title": $('#repdate').val().substring(0,4)+"年",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 2,    //跨列
					},{
					    "title": "提升 （％）",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 2,    //跨列
					},{
					    "title": "研究生及以上学历占比",
					    "halign":"center",
					    "align":"center",
					    "colspan": 2,   //跨行
					    "rowspan": 1,    //跨列
					},{
					    "title": "大学本科学历占比",
					    "halign":"center",
					    "align":"center",
					    "colspan": 2,   //跨行
					    "rowspan": 1,    //跨列
					},{
					    "title": $('#repdate').val().substring(0,4)+"年",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 2,    //跨列
					},{
					    "title": "降低（岁）",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 2,    //跨列
					},{
					    "title": $('#repdate').val().substring(0,4)+"年",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 2,    //跨列
					},{
					    "title": "提升 （％）",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 2,    //跨列
					},{
					    "title": "研究生及以上学历占比",
					    "halign":"center",
					    "align":"center",
					    "colspan": 2,   //跨行
					    "rowspan": 1,    //跨列
					},{
					    "title": "大学本科学历占比",
					    "halign":"center",
					    "align":"center",
					    "colspan": 2,   //跨行
					    "rowspan": 1,    //跨列
					},{
					    "title": $('#repdate').val().substring(0,4)+"年",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 2,    //跨列
					},{
					    "title": "降低（岁）",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 2,    //跨列
					},{
					    "title": $('#repdate').val().substring(0,4)+"年",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 2,    //跨列
					},{
					    "title": "提升 （％）",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 2,    //跨列
					},{
					    "title": "研究生及以上学历占比",
					    "halign":"center",
					    "align":"center",
					    "colspan": 2,   //跨行
					    "rowspan": 1,    //跨列
					},{
					    "title": "大学本科学历占比",
					    "halign":"center",
					    "align":"center",
					    "colspan": 2,   //跨行
					    "rowspan": 1,    //跨列
					},{
					    "title": $('#repdate').val().substring(0,4)+"年",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 2,    //跨列
					},{
					    "title": "降低（岁）",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 2,    //跨列
					},{
					    "title": $('#repdate').val().substring(0,4)+"年",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 2,    //跨列
					},{
					    "title": "提升 （％）",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 2,    //跨列
					},{
					    "title": "研究生及以上学历占比",
					    "halign":"center",
					    "align":"center",
					    "colspan": 2,   //跨行
					    "rowspan": 1,    //跨列
					},{
					    "title": "大学本科学历占比",
					    "halign":"center",
					    "align":"center",
					    "colspan": 2,   //跨行
					    "rowspan": 1,    //跨列
					},{
					    "title": $('#repdate').val().substring(0,4)+"年",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 2,    //跨列
					},{
					    "title": "降低（岁）",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 2,    //跨列
					},{
					    "title": $('#repdate').val().substring(0,4)+"年",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 2,    //跨列
					},{
					    "title": "提升 （％）",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 2,    //跨列
					},{
					    "title": "研究生及以上学历占比",
					    "halign":"center",
					    "align":"center",
					    "colspan": 2,   //跨行
					    "rowspan": 1,    //跨列
					},{
					    "title": "大学本科学历占比",
					    "halign":"center",
					    "align":"center",
					    "colspan": 2,   //跨行
					    "rowspan": 1,    //跨列
					}

				 
				 ]
                ,
				
				[ //3...				    
				   
				  
				    {
					    "title": $('#repdate').val().substring(0,4)+"年",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 1,    //跨列
					},{
					    "title": "提升（％）",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 1,    //跨列
					},{
					    "title": $('#repdate').val().substring(0,4)+"年",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 1,    //跨列
					},{
					    "title": "提升 （％）",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 1,    //跨列
					}, {
					    "title": $('#repdate').val().substring(0,4)+"年",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 1,    //跨列
					},{
					    "title": "提升（％）",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 1,    //跨列
					},{
					    "title": $('#repdate').val().substring(0,4)+"年",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 1,    //跨列
					},{
					    "title": "提升 （％）",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 1,    //跨列
					}, {
					    "title": $('#repdate').val().substring(0,4)+"年",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 1,    //跨列
					},{
					    "title": "提升（％）",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 1,    //跨列
					},{
					    "title": $('#repdate').val().substring(0,4)+"年",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 1,    //跨列
					},{
					    "title": "提升 （％）",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 1,    //跨列
					}, {
					    "title": $('#repdate').val().substring(0,4)+"年",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 1,    //跨列
					},{
					    "title": "提升（％）",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 1,    //跨列
					},{
					    "title": $('#repdate').val().substring(0,4)+"年",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 1,    //跨列
					},{
					    "title": "提升 （％）",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 1,    //跨列
					}, {
					    "title": $('#repdate').val().substring(0,4)+"年",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 1,    //跨列
					},{
					    "title": "提升（％）",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 1,    //跨列
					},{
					    "title": $('#repdate').val().substring(0,4)+"年",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 1,    //跨列
					},{
					    "title": "提升 （％）",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 1,    //跨列
					},
				 

			
			],
			
			
			
			//db 报表数据暂时
			[
			
                      
	             {
	                field: 'a',
	                title: '1'
	            },{
	                field: 'b',
	                title: '2'
	            },{
	                field: 'c',
	                title: '3'
	            },{
	                field: 'd',
	                title: '4'
	            },{
	                field: 'e',
	                title: '5'
	            },{
	                field: 'f',
	                title: '6'
	            },{
	                field: 'g',
	                title: '7'
	            },{
	                field: 'h',
	                title: '8'
	            },{
	                field: 'i',
	                title: '9'
	            },{
	                field: 'jd',
	                title: '10'
	            },{
	                field: 'kd',
	                title: '11'
	            },{
	                field: 'ld',
	                title: '12'
	            },{
	                field: 'md',
	                title: '13'
	            },{
	                field: 'nd',
	                title: '14'
	            },{
	                field: 'od',
	                title: '15'
	            },{
	                field: 'pd',
	                title: '16'
	            },{
	                field: 'qd',
	                title: '17'
	            },{
	                field: 'rd',
	                title: '18'
	            },{
	                field: 'sd',
	                title: '19'
	            },{
	                field: 'td',
	                title: '20'
	            },{
	                field: 'ud',
	                title: '21'
	            },{
	                field: 'vd',
	                title: '22'
	            },{
	                field: 'wd',
	                title: '23'
	            },{
	                field: 'xd',
	                title: '24'
	            },{
	                field: 'yd',
	                title: '25'
	            },{
	                field: 'zd',
	                title: '26'
	            },{
	                field: 'a1d',
	                title: '27'
	            },{
	                field: 'b1d',
	                title: '28'
	            },{
	                field: 'c1d',
	                title: '29'
	            },{
	                field: 'd1d',
	                title: '30'
	            },{
	                field: 'e1d',
	                title: '31'
	            },{
	                field: 'f1d',
	                title: '32'
	            },{
	                field: 'g1d',
	                title: '33'
	            },{
	                field: 'h1d',
	                title: '34'
	            },{
	                field: 'i1d',
	                title: '35'
	            },{
	                field: 'j1d',
	                title: '36'
	            },{
				    field: 'k1',
				    title: '37'
				},{
				    field: 'l1',
				    title: '38'
				},{
				    field: 'm1',
				    title: '39'
				},{
				    field: 'n1',
				    title: '40'
				},{
				    field: 'o1',
				    title: '41'
				},{
				    field: 'p1',
				    title: '42'
				},{
				    field: 'q1',
				    title: '43'
				},{
				    field: 'r1',
				    title: '44'
				},{
				    field: 'r1',
				    title: '45'
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

