
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
				    "title": "紧密型业务外包人员关键职责分类",
				    "halign":"center",
				    "align":"center",
				    "colspan": 155   //跨度
				}
			], 
             //2 行
			[
				{
					"title": "填报单位："+$('#unit').val()+'('+$('#repdate').val()+')',
				    "halign":"left",
				    "align":"center",
				    "colspan": 155   //跨度
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
				    "rowspan": 3,    //跨列
				},{
				    "title": "总计",
				    "halign":"center",
				    "align":"center",
				    "colspan": 1,   //跨行
				    "rowspan": 3,    //跨列
				},
				{
				    "title": "市场序列",
				    "halign":"center",
				    "align":"center",
				    "colspan": 68,   //跨行
				    "rowspan": 1,    //跨列
				},
				{
				    "title": "技术序列",
				    "halign":"center",
				    "align":"center",
				    "colspan": 55,   //跨行
				    "rowspan": 1,    //跨列
				},
				{
				    "title": "内部支撑序列",
				    "halign":"center",
				    "align":"center",
				    "colspan": 24,   //跨行
				    "rowspan": 1,    //跨列
				},
				{
				    "title": "管理序列",
				    "halign":"center",
				    "align":"center",
				    "colspan": 5,   //跨行
				    "rowspan": 1,    //跨列
				},
				{
				    "title": "未入套人员",
				    "halign":"center",
				    "align":"center",
				    "colspan": 1,   //跨行
				    "rowspan": 3,    //跨列
				}
				
				
				
				],
				
				
				[ //2...
				   				    
				    {
					    "title": "合计",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 2,    //跨列
					}
					,
					{
					    "title": "产品与行业应用管理",
					    "halign":"center",
					    "align":"center",
					    "colspan": 9,   //跨行
					    "rowspan": 1,    //跨列
					}
					,
					{
					    "title": "渠道管理",
					    "halign":"center",
					    "align":"center",
					    "colspan": 4,   //跨行
					    "rowspan": 1,    //跨列
					}
					,
					{
					    "title": "市场业务管理",
					    "halign":"center",
					    "align":"center",
					    "colspan": 13,   //跨行
					    "rowspan": 1,    //跨列
					},
					{
					    "title": "市场营销策划",
					    "halign":"center",
					    "align":"center",
					    "colspan": 6,   //跨行
					    "rowspan": 1,    //跨列
					},
					{
					    "title": "终端管理",
					    "halign":"center",
					    "align":"center",
					    "colspan": 5,   //跨行
					    "rowspan": 1,    //跨列
					},
					{
					    "title": "公众客户销售",
					    "halign":"center",
					    "align":"center",
					    "colspan": 4,   //跨行
					    "rowspan": 1,    //跨列
					},
					{
					    "title": "国际业务销售",
					    "halign":"center",
					    "align":"center",
					    "colspan": 4,   //跨行
					    "rowspan": 1,    //跨列
					},
					{
					    "title": "集团客户销售",
					    "halign":"center",
					    "align":"center",
					    "colspan": 5,   //跨行
					    "rowspan": 1,    //跨列
					},
					{
					    "title": "客户服务",
					    "halign":"center",
					    "align":"center",
					    "colspan": 17,   //跨行
					    "rowspan": 1,    //跨列
					},
					
					//////////////////////////
				   
					{
					    "title": "合计",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 2,    //跨列
					}
					,{
					    "title": "采购管理",
					    "halign":"center",
					    "align":"center",
					    "colspan": 3,   //跨行
					    "rowspan": 1,    //跨列
					},{
					    "title": "工程建设",
					    "halign":"center",
					    "align":"center",
					    "colspan": 11,   //跨行
					    "rowspan": 1,    //跨列
					},{
					    "title": "工程设计",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 2,    //跨列
					},{
					    "title": "计划规划",
					    "halign":"center",
					    "align":"center",
					    "colspan": 3,   //跨行
					    "rowspan": 1,    //跨列
					},{
					    "title": "技术研发与管理",
					    "halign":"center",
					    "align":"center",
					    "colspan": 4,   //跨行
					    "rowspan": 1,    //跨列
					},{
					    "title": "网络运维",
					    "halign":"center",
					    "align":"center",
					    "colspan": 15,   //跨行
					    "rowspan": 1,    //跨列
					},{
					    "title": "信息化",
					    "halign":"center",
					    "align":"center",
					    "colspan": 17,   //跨行
					    "rowspan": 1,    //跨列
					}
					
					////////////////////////////////////
					,{
					    "title": "合计",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 2,    //跨列
					},{
					    "title": "财务会计",
					    "halign":"center",
					    "align":"center",
					    "colspan": 4,   //跨行
					    "rowspan": 1,    //跨列
					},{
					    "title": "党群、纪检、工会",
					    "halign":"center",
					    "align":"center",
					    "colspan": 4,   //跨行
					    "rowspan": 1,    //跨列
					},{
					    "title": "风险管理",
					    "halign":"center",
					    "align":"center",
					    "colspan": 4,   //跨行
					    "rowspan": 1,    //跨列
					},{
					    "title": "人力资源管理",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 2,    //跨列
					},{
					    "title": "战略运营",
					    "halign":"center",
					    "align":"center",
					    "colspan": 3,   //跨行
					    "rowspan": 1,    //跨列
					},{
					    "title": "综合行政与后勤",
					    "halign":"center",
					    "align":"center",
					    "colspan": 7,   //跨行
					    "rowspan": 1,    //跨列
					}
					///////////////////////////
					,{
					    "title": "合计",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    "rowspan": 2,    //跨列
					},{
					    "title": "管理",
					    "halign":"center",
					    "align":"center",
					    "colspan": 4,   //跨行
					    "rowspan": 1,    //跨列
					}
					
					
					
				],
				
				
				////////////////////////////////
				
				[
				    {
					    "title": "小计",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					},{
					    "title": "产品安全管理",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					},{
					    "title": "产品管理",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					},{
					    "title": "产品开发",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					},{
					    "title": "产品应用平台管理",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					},{
					    "title": "导航产品管理",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					},{
					    "title": "行业应用开发",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					},{
					    "title": "合作业务管理",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					},{
					    "title": "宽带产品管理",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					}					
			        ////////////////////////
					,{
					    "title": "小计",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					},{
					    "title": "集团客户渠道管理",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					},{
					    "title": "实体渠道管理",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					},{
					    "title": "实体渠道运营",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					}
					///////////////////
					,{
					    "title": "小计",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					},{
					    "title": "IDC业务管理",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					},{
					    "title": "导航业务管理",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					},{
					    "title": "电子商务业务管理",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					},{
					    "title": "国际业务管理",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					},{
					    "title": "集团客户业务管理",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					},{
					    "title": "监管事务管理",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					},{
					    "title": "卡类与号码管理",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					},{
					    "title": "客户管理",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					},{
					    "title": "欠费与信用管理",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					},{
					    "title": "市场业务管理",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					},{
					    "title": "收入保障管理",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					},{
					    "title": "支付业务管理",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					}
					////////////////////////////
					
					,{
					    "title": "小计",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					},{
					    "title": "经营分析与管理",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					},{
					    "title": "经营监控",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					},{
					    "title": "品牌与传播",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					},{
					    "title": "市场研究与策略",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					},{
					    "title": "营销策划与管理",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					}
					//////////////////////////////
					,{
					    "title": "小计",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					},{
					    "title": "行业终端与管理",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					},{
					    "title": "终端管理",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					},{
					    "title": "终端运营",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					},{
					    "title": "终端支撑",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					}

					//////////////////////////////
					,{
					    "title": "小计",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					},{
					    "title": "公众客户销售",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					},{
					    "title": "公众客户销售管理",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					},{
					    "title": "公众客户销售支撑",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					}
					
					//////////////////////////////
					,{
					    "title": "小计",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					},{
					    "title": "国际运营商客户销售",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					},{
					    "title": "国际运营商客户销售管理",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					},{
					    "title": "国际运营商客户销售支撑",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					}
					
					
					//////////////////////////////
					,{
					    "title": "小计",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					},{
					    "title": "集团大客户销售",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					},{
					    "title": "集团大客户销售管理",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					},{
					    "title": "集团大客户销售支撑",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					},{
					    "title": "集团中小企业销售",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					}
					
					//////////////////////////////
					,{
					    "title": "小计",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					},{
					    "title": "10010客服热线",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					},{
					    "title": "10015升级投诉热线",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					},{
					    "title": "VIP服务管理",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					},{
					    "title": "VIP客户经理",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					}
					,{
					    "title": "导航服务监督",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					},{
					    "title": "导航坐席含BPO业务坐席",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					},{
					    "title": "电话营销",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					},{
					    "title": "服务管理",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					}
					
					,{
					    "title": "服务监督服务",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					},{
					    "title": "国际客户客服热线",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					},{
					    "title": "国际漫游管理",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					},{
					    "title": "客服现场服务管理",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					}
					
					,{
					    "title": "客服中心支撑",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					},{
					    "title": "客户服务",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					},{
					    "title": "投诉管理客服热线",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					},{
					    "title": "新型互联网",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					}
					
					///////////////////////////////////////////////
					
					,{
					    "title": "小计",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					},{
					    "title": "采购管理",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					},{
					    "title": "物资管理",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					},{
					    "title": "小计",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					},{
					    "title": "传输工程",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					}
					
					,{
					    "title": "动力、环境及配套工程",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					},{
					    "title": "国际工程",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					},{
					    "title": "集团客户工程",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					},{
					    "title": "交换工程",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					},{
					    "title": "接入网工程",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					}
					
					
					,{
					    "title": "局房建设",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					},{
					    "title": "平台工程",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					},{
					    "title": "数据工程",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					},{
					    "title": "移动工程",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					}
										
					,{
					    "title": "小计",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					},{
					    "title": "规划管理",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					},{
					    "title": "计划管理",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					},{
					    "title": "小计",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					},{
					    "title": "技术管理",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					}					
					

					,{
					    "title": "技术研发",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					},{
					    "title": "小计",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					},{
					    "title": "研究咨询",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					},{
					    "title": "传输专业",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					},{
					    "title": "动力环境及配套专业",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					}					
					
					
					
					,{
					    "title": "国际网络维护",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					},{
					    "title": "交换专业",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					},{
					    "title": "接入网专业",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					},{
					    "title": "数据专业",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					},{
					    "title": "网管支撑专业",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					}					
						
					,{
					    "title": "网络安全管理",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					},{
					    "title": "网络服务专业",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					},{
					    "title": "维护管理专业",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					},{
					    "title": "线路专业",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					},{
					    "title": "业务平台专业",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					}					
					
					
					,{
					    "title": "移动专业",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					},{
					    "title": "资源管理专业",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					},{
					    "title": "小计",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					},{
					    "title": "IT承载网管理",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					},{
					    "title": "IT规划",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					}					

					,{
					    "title": "IT项目管理",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					},{
					    "title": "电子商务系统开发与支撑",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					},{
					    "title": "计费账务",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					},{
					    "title": "数据分析",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					},{
					    "title": "数据管理",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					}					
					

					
					,{
					    "title": "维护管理",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					},{
					    "title": "系统公共资源",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					},{
					    "title": "系统集成",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					},{
					    "title": "系统建设",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					},{
					    "title": "系统应用支撑",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					}					
					
					
					,{
					    "title": "系统运行维护",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					},{
					    "title": "系统质量管理",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					},{
					    "title": "信息安全",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					},{
					    "title": "需求管理",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					}
					
					//////////////////////////////////////////////
					
					,{
					    "title": "小计",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					},{
					    "title": "服务支撑",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					},{
					    "title": "管理支撑",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					},{
					    "title": "经营支撑",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					},{
					    "title": "小计",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					}					
					
					,{
					    "title": "党团",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					},{
					    "title": "工会",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					},{
					    "title": "纪检监察",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					},{
					    "title": "小计",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					},{
					    "title": "法律风险",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					}
					

					,{
					    "title": "内部审计",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					},{
					    "title": "内控风险",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					},{
					    "title": "小计",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					},{
					    "title": "企业管理",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					}
									
					
					,{
					    "title": "投资与对外合作",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					},{
					    "title": "小计",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					},{
					    "title": "安全保卫",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					},{
					    "title": "存续管理",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					},{
					    "title": "海外公司管理",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					}
			
					
					,{
					    "title": "后勤管理与服务",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					},{
					    "title": "综合行政",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					},{
					    "title": "组织内综合事务",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					}
	

					//////////////////////////////////
					
					
					,{
					    "title": "小计",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					},{
					    "title": "集团管理人员",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					},{
					    "title": "省管理人员",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
					},{
					    "title": "市管理人员",
					    "halign":"center",
					    "align":"center",
					    "colspan": 1,   //跨行
					    
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
				},{
				    field: 'z1',
				    title: '52'
				},{
				    field: 'a2',
				    title: '53'
				},{
				    field: 'b2',
				    title: '54'
				},{
				    field: 'c2',
				    title: '55'
				},{
	                field: 'd2',
	                title: '56'
	            },{
	                field: 'e2',
	                title: '57'
	            },{
	                field: 'f2',
	                title: '58'
	            },{
	                field: 'g2',
	                title: '59'
	            },{
	                field: 'h2',
	                title: '60'
	            },{
	                field: 'i2',
	                title: '61'
	            },{
	                field: 'j2',
	                title: '62'
	            },{
				    field: 'k2',
				    title: '63'
				},{
				    field: 'l2',
				    title: '64'
				},{
				    field: 'm2',
				    title: '65'
				},{
				    field: 'n2',
				    title: '66'
				},{
				    field: 'o2',
				    title: '67'
				},{
				    field: 'p2',
				    title: '68'
				},{
				    field: 'q2',
				    title: '69'
				},{
				    field: 'r2',
				    title: '70'
				},{
				    field: 's2',
				    title: '71'
				},{
				    field: 't2',
				    title: '72'
				},{
				    field: 'u2',
				    title: '73'
				},{
				    field: 'v2',
				    title: '74'
				},{
				    field: 'w2',
				    title: '75'
				},{
				    field: 'x2',
				    title: '76'
				},{
				    field: 'y2',
				    title: '77'
				},{
				    field: 'z2',
				    title: '78'
				}
  				,	
  					
  				{
	                field: 'a3',
	                title: '79'
	            },{
	                field: 'b3',
	                title: '80'
	            },{
	                field: 'c3',
	                title: '81'
	            },{
	                field: 'd3',
	                title: '82'
	            },{
	                field: 'e3',
	                title: '83'
	            },{
	                field: 'f3',
	                title: '84'
	            },{
	                field: 'g3',
	                title: '85'
	            },{
	                field: 'h3',
	                title: '86'
	            },{
	                field: 'i3',
	                title: '87'
	            },{
	                field: 'j3',
	                title: '88'
	            },{
	                field: 'k3',
	                title: '89'
	            },{
	                field: 'l3',
	                title: '90'
	            },{
	                field: 'm3',
	                title: '91'
	            },{
	                field: 'n3',
	                title: '92'
	            },{
	                field: 'o3',
	                title: '93'
	            },{
	                field: 'p3',
	                title: '94'
	            },{
	                field: 'q3',
	                title: '95'
	            },{
	                field: 'r3',
	                title: '96'
	            },{
	                field: 's3',
	                title: '97'
	            },{
	                field: 't3',
	                title: '98'
	            },{
	                field: 'u3',
	                title: '99'
	            },{
	                field: 'v3',
	                title: '100'
	            },{
	                field: 'w3',
	                title: '101'
	            },{
	                field: 'x3',
	                title: '102'
	            },{
	                field: 'y3',
	                title: '103'
	            },{
	                field: 'z3',
	                title: '104'
	            },{
	                field: 'a4',
	                title: '105'
	            },{
	                field: 'b4',
	                title: '106'
	            },{
	                field: 'c4',
	                title: '101'
	            },{
	                field: 'd4',
	                title: '108'
	            },{
	                field: 'e4',
	                title: '109'
	            },{
	                field: 'f4',
	                title: '110'
	            },{
	                field: 'g4',
	                title: '111'
	            },{
	                field: 'h4',
	                title: '112'
	            },{
	                field: 'i4',
	                title: '113'
	            },{
	                field: 'j4',
	                title: '114'
	            },{
				    field: 'k4',
				    title: '115'
				},{
				    field: 'l4',
				    title: '116'
				},{
				    field: 'm4',
				    title: '117'
				},{
				    field: 'n4',
				    title: '118'
				},{
				    field: 'o4',
				    title: '119'
				},{
				    field: 'p4',
				    title: '120'
				},{
				    field: 'q4',
				    title: '121'
				},{
				    field: 'r4',
				    title: '122'
				},{
				    field: 's4',
				    title: '123'
				},{
				    field: 't4',
				    title: '124'
				},{
				    field: 'u4',
				    title: '125'
				},{
				    field: 'v4',
				    title: '126'
				},{
				    field: 'w4',
				    title: '127'
				},{
				    field: 'x4',
				    title: '128'
				},{
				    field: 'y4',
				    title: '129'
				},{
				    field: 'z4',
				    title: '130'
				},{
				    field: 'a5',
				    title: '131'
				},{
				    field: 'b5',
				    title: '132'
				},{
				    field: 'c5',
				    title: '133'
				},{
	                field: 'd5',
	                title: '134'
	            },{
	                field: 'e5',
	                title: '135'
	            },{
	                field: 'f5',
	                title: '136'
	            },{
	                field: 'g5',
	                title: '137'
	            },{
	                field: 'h5',
	                title: '138'
	            },{
	                field: 'i5',
	                title: '139'
	            },{
	                field: 'j5',
	                title: '140'
	            },{
				    field: 'k5',
				    title: '141'
				},{
				    field: 'l5',
				    title: '142'
				},{
				    field: 'm5',
				    title: '143'
				},{
				    field: 'n5',
				    title: '144'
				},{
				    field: 'o5',
				    title: '145'
				},{
				    field: 'p5',
				    title: '146'
				},{
				    field: 'q5',
				    title: '147'
				},{
				    field: 'r5',
				    title: '148'
				},{
				    field: 's5',
				    title: '149'
				},{
				    field: 't5',
				    title: '150'
				},{
				    field: 'u5',
				    title: '151'
				},{
				    field: 'v5',
				    title: '152'
				},{
				    field: 'w5',
				    title: '153'
				},{
				    field: 'x5',
				    title: '154'
				},{
				    field: 'y5',
				    title: '155'
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