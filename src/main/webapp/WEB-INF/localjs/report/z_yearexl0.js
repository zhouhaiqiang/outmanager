
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
				    "title": "全口径人员总量统计表",
				    "halign":"center",
				    "align":"center",
				    "colspan": 36   //跨度
				}
			], 
             //2 行
			[
				{
					"title": "填报单位："+$('#unit').val()+'('+$('#repdate').val()+')',
				    "halign":"left",
				    "align":"center",
				    "colspan": 36   //跨度
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
				    "rowspan": 6,    //跨列
				},
				{
				    "title": "全口径用工总量",
				    "halign":"center",
				    "align":"center",
				    "colspan": 25,   //跨行
				    "rowspan": 1,    //跨列
				},
				{
				    "title": "离开本单位仍保留劳动关系的人员",
				    "halign":"center",
				    "align":"center",
				    "colspan": 6,   //跨行
				    "rowspan": 2,    //跨列
				}
				,
				{
				    "title": "离退休人员",
				    "halign":"center",
				    "align":"center",
				    "colspan": 4,   //跨行
				    "rowspan": 2,    //跨列
				}
				
				
				],
				
				
				[ //2...
				   				    
				  {
					    "title": "年末人数",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 5,    //跨列
					}
					,
					{
					    "title": "平均人数",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 5,    //跨列
					}
					,
					{
					    "title": "全部从业人员",
					    "halign":"center",
					    "align":"center",
					    "colspan": 21,   //跨行
					    "rowspan": 1,    //跨列
					}
					,
					{
					    "title": "紧密型业务外包人员",
					    "halign":"center",
					    "align":"center",
					    "colspan": 2,   //跨行
					    "rowspan": 1,    //跨列
					}
				   
					
					
					
				],
				
				
				////////////////////////////////
				[
				 {
					    "title": "年末人数",
					    "halign":"center",
					    "align":"center",
					    "colspan": 3,   //跨行
					    "rowspan": 1,    //跨列
					},{
					    "title": "平均人数",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 4,    //跨列
					}
					,{
					    "title": "合同制在岗员工",
					    "halign":"center",
					    "align":"center",
					    "colspan": 5,   //跨行
					    "rowspan": 1,    //跨列
					},{
					    "title": "劳务派遣人员",
					    "halign":"center",
					    "align":"center",
					    "colspan": 4,   //跨行
					    "rowspan": 1,    //跨列
					},
					{
					    "title": "非全日制用工",
					    "halign":"center",
					    "align":"center",
					    "colspan": 2,   //跨行
					    "rowspan": 1,    //跨列
					},
					{
					    "title": "其他人员",
					    "halign":"center",
					    "align":"center",
					    "colspan": 6,   //跨行
					    "rowspan": 1,    //跨列
					},
					{
					    "title": "年末人数",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 4,    //跨列
					},
					{
					    "title": "平均人数",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 4,    //跨列
					},
					{
					    "title": "年末人数",
					    "halign":"center",
					    "align":"center",
					    "colspan": 3,   //跨行
					    "rowspan": 1,    //跨列
					},
					{
					    "title": "平均人数",
					    "halign":"center",
					    "align":"center",
					    "colspan": 3,   //跨行
					    "rowspan": 1,    //跨列
					},
					{
					    "title": "合计",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 4,    //跨列
					}
					,
					{
					    "title": "其中",
					    "halign":"center",
					    "align":"center",
					    "colspan": 3,   //跨行
					    "rowspan": 1,    //跨列
					}
					
					

				 
				 ]
                ,
				
				[ //3...				    
				    {
					    "title": "合计",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 3,    //跨列				    
					},
					{
					    "title": "其中：",
					    "halign":"center",
					    "align":"center",
					    "colspan": 2,   //跨行
					    "rowspan": 1,    //跨列				    
					}, 
					
					{
					    "title": "年末人数",
					    "halign":"center",
					    "align":"center",
					    "colspan": 2,   //跨行
					    "rowspan": 1,    //跨列				    
					}
					,
					{
					    "title": "平均人数",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 3,    //跨列				    
					},
					{
					    "title": "其中",
					    "halign":"center",
					    "align":"center",
					    "colspan": 2,   //跨行
					    "rowspan": 1,    //跨列				    
					}
					,
					{
					    "title": "年末人数",
					    "halign":"center",
					    "align":"center",
					    "colspan": 3,   //跨行
					    "rowspan": 1,    //跨列				    
					}
					,
					{
					    "title": "平均人数",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 3,    //跨列				    
					},
					{
					    "title": "年末人数",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 3,    //跨列				    
					},
					{
					    "title": "平均人数",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 3,    //跨列				    
					}
					
					////////////其他人员/////////////////////////
					,
					{
					    "title": "年末人数",
					    "halign":"center",
					    "align":"center",
					    "colspan": 3,   //跨行
					    "rowspan": 1,    //跨列				    
					},
					{
					    "title": "平均人数",
					    "halign":"center",
					    "align":"center",
					    "colspan": 3,   //跨行
					    "rowspan": 1,    //跨列				    
					},
					////////////////////////
					{
					    "title": "合计",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 3,    //跨列				    
					},{
					    "title": "其中",
					    "halign":"center",
					    "align":"center",
					    "colspan": 2,   //跨行
					    "rowspan": 1,    //跨列				    
					},{
					    "title": "合计",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 3,    //跨列				    
					},{
					    "title": "其中",
					    "halign":"center",
					    "align":"center",
					    "colspan": 2,   //跨行
					    "rowspan": 1,    //跨列				    
					}
					
					////////////////////////
				    
					,{
					    "title": "离休",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 3,    //跨列				    
					},{
					    "title": "退休",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 3,    //跨列				    
					},{
					    "title": "退职",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 3,    //跨列				    
					}  
				    
				],[ //4...
				    
				    {
					    "title": "使用的农村劳动力",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 2    //跨列				    
					},{
					    "title": "接收军队转业、复员退伍人员总数",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 2    //跨列				    
					},{
					    "title": "小计",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 2    //跨列				    
					},{
					    "title": "其中：女性",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 2    //跨列				    
					},
					
					////////////////////////////////////
					{
					    "title": "待岗人员",
					    "halign":"center",
					    "align":"center",
					    "colspan": 2,   //跨行
					    "rowspan": 1    //跨列				    
					},{
					    "title": "小计",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 2    //跨列				    
					},{
					    "title": "其中",
					    "halign":"center",
					    "align":"center",
					    "colspan": 2,   //跨行
					    "rowspan": 1    //跨列				    
					},
					////////////////////
					{
					    "title": "小计",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 2    //跨列				    
					},{
					    "title": "其中:",
					    "halign":"center",
					    "align":"center",
					    "colspan": 2,   //跨行
					    "rowspan": 1    //跨列				    
					},{
					    "title": "小计",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 2    //跨列				    
					},{
					    "title": "其中:",
					    "halign":"center",
					    "align":"center",
					    "colspan": 2,   //跨行
					    "rowspan": 1    //跨列				    
					},
					////////////////////////////
					{
					    "title": "内部退养人员",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 2    //跨列				    
					},{
					    "title": "长期病、休假人员",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 2    //跨列				    
					},{
					    "title": "内部退养人员",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 2    //跨列				    
					},{
					    "title": "长期病、休假人员",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 2    //跨列				    
					}
					
					/////////////////////////////
					      
				    
				],[ //5...
				    {
					    "title": "期末人数",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 1    //跨列				    
					},{
					    "title": "平均人数",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 1    //跨列				    
					},{
					    "title": "使用的农村劳动力",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 1    //跨列				    
					} ,{
					    "title": "接收军队转业、复员退伍人员总数",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 1    //跨列				    
					},{
					    "title": "聘用的离退休人员",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 1    //跨列				    
					} ,{
					    "title": "聘用的港澳台和外籍人员",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 1    //跨列				    
					},{
					    "title": "聘用的离退休人员",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 1    //跨列				    
					} ,{
					    "title": "聘用的港澳台和外籍人员",
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

