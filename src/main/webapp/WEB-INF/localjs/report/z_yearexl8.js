
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
				    "title": "紧密型业务外包人员职位层级变动分析",
				    "halign":"center",
				    "align":"center",
				    "colspan": 51   //跨度
				}
			], 
             //2 行
			[
				{
					"title": "填报单位："+$('#unit').val()+'('+$('#repdate').val()+')',
				    "halign":"left",
				    "align":"center",
				    "colspan": 51   //跨度
				},
			
			],
			//3  行
			
           
			
			/////////////////////////////////////////////////////////
			 
			   
			   // 1...
		    [
			 			    
				{
				    "title": "单位",
				    "halign":"center",
				    "align":"center",
				    "colspan": 1,   //跨行
				    "rowspan": 4,    //跨列
				},
				{
				    "title": "职位等级晋升人员情况（％）",
				    "halign":"center",
				    "align":"center",
				    "colspan": 25,   //跨行
				    "rowspan": 1,    //跨列
				},
				{
				    "title": "职位等级下降人员情况（％）",
				    "halign":"center",
				    "align":"center",
				    "colspan": 25,   //跨行
				    "rowspan": 1,    //跨列
				}

				],
				
				
				[ //2...
				   				    
				  {
					    "title": "合计",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 3,    //跨列
					}
					,
					{
					    "title": "占比",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 3,    //跨列
					}
					,
					{
					    "title": "职级分布",
					    "halign":"center",
					    "align":"center",
					    "colspan": 18,   //跨行
					    "rowspan": 1,    //跨列
					}
					,
					{
					    "title": "岗位序列分布",
					    "halign":"center",
					    "align":"center",
					    "colspan": 5,   //跨行
					    "rowspan": 1,    //跨列
					},
					{
					    "title": "合计",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 3,    //跨列
					}
					,
					{
					    "title": "占比",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 3,    //跨列
					},
					{
					    "title": "职级分布",
					    "halign":"center",
					    "align":"center",
					    "colspan": 18,   //跨行
					    "rowspan": 1,    //跨列
					}
					,
					{
					    "title": "岗位序列分布",
					    "halign":"center",
					    "align":"center",
					    "colspan": 5,   //跨行
					    "rowspan": 1,    //跨列
					}
				   
	
				],
				
				
				////////////////////////////////
				[
					{
					    "title": "其中：（原岗位处于以下等级）",
					    "halign":"center",
					    "align":"center",
					    "colspan": 18,   //跨行
					    "rowspan": 1,    //跨列
					},					
					{
					    "title": "销售与客户服务序列",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 2,    //跨列
					},					
					{
					    "title": "市场与产品序列",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 2,    //跨列
					},					
					{
					    "title": "建设与维护序列",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 2,    //跨列
					},					
					{
					    "title": "内部支撑序列",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 2,    //跨列
					},					
					{
					    "title": "管理序列",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 2,    //跨列
					},{
					    "title": "其中：（原岗位处于以下等级）",
					    "halign":"center",
					    "align":"center",
					    "colspan": 18,   //跨行
					    "rowspan": 1,    //跨列
					},					
					{
					    "title": "销售与客户服务序列",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 2,    //跨列
					},					
					{
					    "title": "市场与产品序列",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 2,    //跨列
					},					
					{
					    "title": "建设与维护序列",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 2,    //跨列
					},					
					{
					    "title": "内部支撑序列",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 2,    //跨列
					},					
					{
					    "title": "管理序列",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 2,    //跨列
					}
					
					
					
					
					
					


				 
				 ]
               ,
               [ //4...
				    {
					    "title": "1",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 1    //跨列				    
					},{
					    "title": "2",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 1    //跨列				    
					},{
					    "title": "3",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 1    //跨列				    
					} ,{
					    "title": "4",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 1    //跨列				    
					},{
					    "title": "5",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 1    //跨列				    
					} ,{
					    "title": "5",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 1    //跨列				    
					},{
					    "title": "7",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 1    //跨列				    
					} ,{
					    "title": "8",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 1    //跨列				    
					},{
					    "title": "9",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 1    //跨列				    
					},{
					    "title": "10",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 1    //跨列				    
					},{
					    "title": "11",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 1    //跨列				    
					} ,{
					    "title": "12",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 1    //跨列				    
					},{
					    "title": "13",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 1    //跨列				    
					} ,{
					    "title": "14",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 1    //跨列				    
					},{
					    "title": "15",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 1    //跨列				    
					} ,{
					    "title": "16",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 1    //跨列				    
					} ,{
					    "title": "17",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 1    //跨列				    
					},{
					    "title": "18",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 1    //跨列				    
					},
					
					/////////////////////////////////////////////////
					
					{
					    "title": "1",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 1    //跨列				    
					},{
					    "title": "2",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 1    //跨列				    
					},{
					    "title": "3",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 1    //跨列				    
					} ,{
					    "title": "4",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 1    //跨列				    
					},{
					    "title": "5",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 1    //跨列				    
					} ,{
					    "title": "5",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 1    //跨列				    
					},{
					    "title": "7",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 1    //跨列				    
					} ,{
					    "title": "8",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 1    //跨列				    
					},{
					    "title": "9",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 1    //跨列				    
					},{
					    "title": "10",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 1    //跨列				    
					},{
					    "title": "11",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 1    //跨列				    
					} ,{
					    "title": "12",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 1    //跨列				    
					},{
					    "title": "13",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 1    //跨列				    
					} ,{
					    "title": "14",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 1    //跨列				    
					},{
					    "title": "15",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 1    //跨列				    
					} ,{
					    "title": "16",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 1    //跨列				    
					} ,{
					    "title": "17",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 1    //跨列				    
					},{
					    "title": "18",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 1    //跨列				    
					} 
			
			
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
	                field: 'j',
	                title: '10'
	            },{
	                field: 'k',
	                title: '11'
	            },{
	                field: 'l',
	                title: '12'
	            },{
	                field: 'm',
	                title: '13'
	            },{
	                field: 'n',
	                title: '14'
	            },{
	                field: 'o',
	                title: '15'
	            },{
	                field: 'p',
	                title: '16'
	            },{
	                field: 'q',
	                title: '17'
	            },{
	                field: 'r',
	                title: '18'
	            },{
	                field: 's',
	                title: '19'
	            },{
	                field: 't',
	                title: '20'
	            },{
	                field: 'u',
	                title: '21'
	            },{
	                field: 'v',
	                title: '22'
	            },{
	                field: 'w',
	                title: '23'
	            },{
	                field: 'x',
	                title: '24'
	            },{
	                field: 'y',
	                title: '25'
	            },{
	                field: 'z',
	                title: '26'
	            },{
	                field: 'a1',
	                title: '27'
	            },{
	                field: 'b1',
	                title: '28'
	            },{
	                field: 'c1',
	                title: '29'
	            },{
	                field: 'd1',
	                title: '30'
	            },{
	                field: 'e1',
	                title: '31'
	            },{
	                field: 'f1',
	                title: '32'
	            },{
	                field: 'g1',
	                title: '33'
	            },{
	                field: 'h1',
	                title: '34'
	            },{
	                field: 'i1',
	                title: '35'
	            },{
	                field: 'j1',
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
				    field: 's1',
				    title: '45'
				},{
				    field: 't1',
				    title: '46'
				},{
				    field: 'u1',
				    title: '47'
				},{
				    field: 'v1',
				    title: '48'
				},{
				    field: 'w1',
				    title: '49'
				},{
				    field: 'x1',
				    title: '50'
				},{
				    field: 'y1',
				    title: '51'
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

