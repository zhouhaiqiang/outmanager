
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
                      
                      //1 行      
                      [       
             				{
          				    "title": "人员情况统计表",
          				    "halign":"center",
          				    "align":"center",
          				    "colspan": 55   //跨度
          				}
          			], 
                       //2 行
          			[
          				{
          				    "title": $('#unit').val()+'('+$('#repdate').val()+')',
          				    "halign":"center",
          				    "align":"center",
          				    "colspan": 55   //跨度
          				},
          			
          			]
                      
                      ,
                      
                      //a行
                      [
      				{
      				    "title": "单位",
      				    "halign":"center",
      				    "align":"center",
      				    "colspan": 1,   //跨行
      				    "rowspan": 4,    //跨列
      				},{
      				    "title": "本省外包人员总量",
      				    "halign":"center",
      				    "align":"center",
      				    "colspan": 1,   //跨行
      				    "rowspan": 4,    //跨列
      				},{
      				    "title": "本量部用工总",
      				    "halign":"center",
      				    "align":"center",
      				    "colspan": 1,   //跨行
      				    "rowspan": 4,    //跨列
      				}
                       
      				,{
      				    "title": "本部业务部门",
      				    "halign":"center",
      				    "align":"center",
      				    "colspan": 23,   //跨行
      				    "rowspan": 1,    //跨列
      				},{
      				    "title": "本部支撑部门",
      				    "halign":"center",
      				    "align":"center",
      				    "colspan": 27,   //跨行
      				    "rowspan": 1,    //跨列
      				}
                       
      				,{
      				    "title": "个性化部门人员合计",
      				    "halign":"center",
      				    "align":"center",
      				    "colspan": 1,   //跨行
      				    "rowspan": 4,    //跨列
      				}
      				,{
      				    "title": "基层责任单元",
      				    "halign":"center",
      				    "align":"center",
      				    "colspan": 1,   //跨行
      				    "rowspan": 4,    //跨列
      				}
      				
      				]

                      ,
                      //b行
                      [
      					{
      					    "title": "合计",
      					    "halign":"center",
      					    "align":"center",
      					    "colspan": 1,   //跨行
      					    "rowspan": 3,    //跨列
      					},{
      					    "title": "公司管理层",
      					    "halign":"center",
      					    "align":"center",
      					    "colspan": 1,   //跨行
      					    "rowspan": 3,    //跨列
      					},{
      					    "title": "综合部",
      					    "halign":"center",
      					    "align":"center",
      					    "colspan": 4,   //跨行
      					    "rowspan": 1,    //跨列
      					}
      					
      					
      					/////////////////////////////
      					
      					,{
      					    "title": "企业发展部",
      					    "halign":"center",
      					    "align":"center",
      					    "colspan": 1,   //跨行
      					    "rowspan": 3,    //跨列
      					},{
      					    "title": "计划管理部",
      					    "halign":"center",
      					    "align":"center",
      					    "colspan": 1,   //跨行
      					    "rowspan": 3,    //跨列
      					},{
      					    "title": "计划管理部",
      					    "halign":"center",
      					    "align":"center",
      					    "colspan": 1,   //跨行
      					    "rowspan": 3,    //跨列
      					}
      					,{
      					    "title": "财务部",
      					    "halign":"center",
      					    "align":"center",
      					    "colspan": 1,   //跨行
      					    "rowspan": 3,    //跨列
      					}
      					
      					,{
      					    "title": "人力资源部",
      					    "halign":"center",
      					    "align":"center",
      					    "colspan": 1,   //跨行
      					    "rowspan": 3,    //跨列
      					},{
      					    "title": "物资采购与管理部",
      					    "halign":"center",
      					    "align":"center",
      					    "colspan": 1,   //跨行
      					    "rowspan": 3,    //跨列
      					},{
      					    "title": "审计部",
      					    "halign":"center",
      					    "align":"center",
      					    "colspan": 1,   //跨行
      					    "rowspan": 3,    //跨列
      					}
      					,{
      					    "title": "监管事务部",
      					    "halign":"center",
      					    "align":"center",
      					    "colspan": 1,   //跨行
      					    "rowspan": 3,    //跨列
      					}					
      					,{
      					    "title": "法律风险管理部",
      					    "halign":"center",
      					    "align":"center",
      					    "colspan": 1,   //跨行
      					    "rowspan": 3,    //跨列
      					},{
      					    "title": "安全保卫部（北方）",
      					    "halign":"center",
      					    "align":"center",
      					    "colspan": 1,   //跨行
      					    "rowspan": 3,    //跨列
      					},{
      					    "title": "存续管理部（北方）",
      					    "halign":"center",
      					    "align":"center",
      					    "colspan": 1,   //跨行
      					    "rowspan": 3,    //跨列
      					}
      					
      					,{
      					    "title": "党群工作部",
      					    "halign":"center",
      					    "align":"center",
      					    "colspan": 1,   //跨行
      					    "rowspan": 3,    //跨列
      					}
      					,{
      					    "title": "工会",
      					    "halign":"center",
      					    "align":"center",
      					    "colspan": 1,   //跨行
      					    "rowspan": 3,    //跨列
      					}
      					
      					,{
      					    "title": "纪检/监察室",
      					    "halign":"center",
      					    "align":"center",
      					    "colspan": 1,   //跨行
      					    "rowspan": 3,    //跨列
      					},{
      					    "title": "基建办",
      					    "halign":"center",
      					    "align":"center",
      					    "colspan": 1,   //跨行
      					    "rowspan": 3,    //跨列
      					}
      					,{
      					    "title": "离退休服务中心/离退休办公室",
      					    "halign":"center",
      					    "align":"center",
      					    "colspan": 1,   //跨行
      					    "rowspan": 3,    //跨列
      					},{
      					    "title": "培训中心/学院",
      					    "halign":"center",
      					    "align":"center",
      					    "colspan": 1,   //跨行
      					    "rowspan": 3,    //跨列
      					}
      					
      					/////////////////////////////////////
      					
      					,{
      					    "title": "合计",
      					    "halign":"center",
      					    "align":"center",
      					    "colspan": 1,   //跨行
      					    "rowspan": 3,    //跨列
      					},{
      					    "title": "市场营销",
      					    "halign":"center",
      					    "align":"center",
      					    "colspan": 7,   //跨行
      					    "rowspan": 1,    //跨列
      					}
      					,
      					{
      					    "title": "集团客户部",
      					    "halign":"center",
      					    "align":"center",
      					    "colspan": 1,   //跨行
      					    "rowspan": 3,    //跨列
      					}
      					,{
      					    "title": "客户服务部",
      					    "halign":"center",
      					    "align":"center",
      					    "colspan": 3,   //跨行
      					    "rowspan": 1,    //跨列
      					},
      					{
      					    "title": "产品创新部",
      					    "halign":"center",
      					    "align":"center",
      					    "colspan": 1,   //跨行
      					    "rowspan": 3,    //跨列
      					},
      					{
      					    "title": "电子商务部",
      					    "halign":"center",
      					    "align":"center",
      					    "colspan": 1,   //跨行
      					    "rowspan": 3,    //跨列
      					}
      					,{
      					    "title": "信息化事业部",
      					    "halign":"center",
      					    "align":"center",
      					    "colspan": 3,   //跨行
      					    "rowspan": 1,    //跨列
      					}
      					
      					,
      					{
      					    "title": "市场监控中心",
      					    "halign":"center",
      					    "align":"center",
      					    "colspan": 1,   //跨行
      					    "rowspan": 3,    //跨列
      					},
      					{
      					    "title": "信息安全部",
      					    "halign":"center",
      					    "align":"center",
      					    "colspan": 1,   //跨行
      					    "rowspan": 3,    //跨列
      					},
      					{
      					    "title": "网络建设部",
      					    "halign":"center",
      					    "align":"center",
      					    "colspan": 1,   //跨行
      					    "rowspan": 3,    //跨列
      					}
      					
      					
      					,
      					{
      					    "title": "运行维护部",
      					    "halign":"center",
      					    "align":"center",
      					    "colspan": 7,   //跨行
      					    "rowspan": 1,    //跨列
      					}
      					
      					
                        ]
                      
                      ,
                      //c行
                      [
                       
      					{
      					    "title": "小计",
      					    "halign":"center",
      					    "align":"center",
      					    "colspan": 1,   //跨行
      					    "rowspan": 2,    //跨列
      					},{
      					    "title": "其中",
      					    "halign":"center",
      					    "align":"center",
      					    "colspan": 3,   //跨行
      					    "rowspan": 1,    //跨列
      					},{
      					    "title": "小计",
      					    "halign":"center",
      					    "align":"center",
      					    "colspan": 1,   //跨行
      					    "rowspan": 2,    //跨列
      					},{
      					    "title": "其中",
      					    "halign":"center",
      					    "align":"center",
      					    "colspan": 6,   //跨行
      					    "rowspan": 1,    //跨列
      					},{
      					    "title": "小计",
      					    "halign":"center",
      					    "align":"center",
      					    "colspan": 1,   //跨行
      					    "rowspan": 2,    //跨列
      					},{
      					    "title": "其中",
      					    "halign":"center",
      					    "align":"center",
      					    "colspan": 2,   //跨行
      					    "rowspan": 1,    //跨列
      					},{
      					    "title": "小计",
      					    "halign":"center",
      					    "align":"center",
      					    "colspan": 1,   //跨行
      					    "rowspan": 2,    //跨列
      					},{
      					    "title": "其中",
      					    "halign":"center",
      					    "align":"center",
      					    "colspan": 2,   //跨行
      					    "rowspan": 1,    //跨列
      					},{
      					    "title": "小计",
      					    "halign":"center",
      					    "align":"center",
      					    "colspan": 1,   //跨行
      					    "rowspan": 2,    //跨列
      					},{
      					    "title": "其中",
      					    "halign":"center",
      					    "align":"center",
      					    "colspan": 6,   //跨行
      					    "rowspan": 1,    //跨列
      					}

      					


                        
                        ]
                      ,
                      //d行
                      [
      					{
      					    "title": "行政服务中心/物业中心",
      					    "halign":"center",
      					    "align":"center",
      					    "colspan": 1,   //跨行
      					    "rowspan": 1,    //跨列
      					},{
      					    "title": "新闻中心",
      					    "halign":"center",
      					    "align":"center",
      					    "colspan": 1,   //跨行
      					    "rowspan": 1,    //跨列
      					},{
      					    "title": "督查/督导办",
      					    "halign":"center",
      					    "align":"center",
      					    "colspan": 1,   //跨行
      					    "rowspan": 1,    //跨列
      					},{
      					    "title": "宽固营销",
      					    "halign":"center",
      					    "align":"center",
      					    "colspan": 1,   //跨行
      					    "rowspan": 1,    //跨列
      					},{
      					    "title": "互联网业务部",
      					    "halign":"center",
      					    "align":"center",
      					    "colspan": 1,   //跨行
      					    "rowspan": 1,    //跨列
      					},{
      					    "title": "市场营销支撑中心",
      					    "halign":"center",
      					    "align":"center",
      					    "colspan": 1,   //跨行
      					    "rowspan": 1,    //跨列
      					},{
      					    "title": "社会合作中心/对外合作部",
      					    "halign":"center",
      					    "align":"center",
      					    "colspan": 1,   //跨行
      					    "rowspan": 1,    //跨列
      					},{
      					    "title": "市场营销部",
      					    "halign":"center",
      					    "align":"center",
      					    "colspan": 1,   //跨行
      					    "rowspan": 1,    //跨列
      					},{
      					    "title": "国际业务销售中心",
      					    "halign":"center",
      					    "align":"center",
      					    "colspan": 1,   //跨行
      					    "rowspan": 1,    //跨列
      					}
      					
      					/////////////////////////
      					
      					,{
      					    "title": "客户服务部",
      					    "halign":"center",
      					    "align":"center",
      					    "colspan": 1,   //跨行
      					    "rowspan": 1,    //跨列
      					},{
      					    "title": "客服呼叫中心",
      					    "halign":"center",
      					    "align":"center",
      					    "colspan": 1,   //跨行
      					    "rowspan": 1,    //跨列
      					}
                        
      					/////////////////////////
      					
      					,{
      					    "title": "信息化事业部",
      					    "halign":"center",
      					    "align":"center",
      					    "colspan": 1,   //跨行
      					    "rowspan": 1,    //跨列
      					},{
      					    "title": "计费账务中心",
      					    "halign":"center",
      					    "align":"center",
      					    "colspan": 1,   //跨行
      					    "rowspan": 1,    //跨列
      					},{
      					    "title": "业务平台维护中心",
      					    "halign":"center",
      					    "align":"center",
      					    "colspan": 1,   //跨行
      					    "rowspan": 1,    //跨列
      					},{
      					    "title": "网络/线路维护中心",
      					    "halign":"center",
      					    "align":"center",
      					    "colspan": 1,   //跨行
      					    "rowspan": 1,    //跨列
      					},{
      					    "title": "运行维护部",
      					    "halign":"center",
      					    "align":"center",
      					    "colspan": 1,   //跨行
      					    "rowspan": 1,    //跨列
      					},{
      					    "title": "网络管理中心",
      					    "halign":"center",
      					    "align":"center",
      					    "colspan": 1,   //跨行
      					    "rowspan": 1,    //跨列
      					},{
      					    "title": "网络优化中心",
      					    "halign":"center",
      					    "align":"center",
      					    "colspan": 1,   //跨行
      					    "rowspan": 1,    //跨列
      					},{
      					    "title": "集团客户响应中心",
      					    "halign":"center",
      					    "align":"center",
      					    "colspan": 1,   //跨行
      					    "rowspan": 1,    //跨列
      					}
      					
                        ] 
                      
                      ,
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

