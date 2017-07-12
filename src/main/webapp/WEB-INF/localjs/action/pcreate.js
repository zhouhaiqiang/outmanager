
//on load
$(function () {
	

	//选择公司查询条件
	try {		
		zTreeInitData();			
	} catch(e){
		alert("单位数据初始化失败！");
	}
	
	//业务线
	initdroplist($("#ywline"),"/outmanager/config/dict_json","","业务线");

	
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
    
    

    
    //4. 表单验证
    validatorForm();
 
});



//类型联动
function chggwnumber(selvalue){
	
	//岗位分类
	initdroplist($("#ywaction"),"/outmanager/config/dict_json","",selvalue)
	
	
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
function Ok_btn(){
    var jsonuserinfo = $('#editform').serializeObject();  
    var jsonstr =  JSON.stringify(jsonuserinfo);  
	
    //调用后台
	updaterecode("/outmanager/action/pcreateaction",jsonstr);
	
	
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
			  alert(data);			
		  },
		  
		  
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



//类型联动
function chggwnumber(selvalue){
	
	//岗位分类
	initdroplist($("#ywaction"),"/outmanager/config/dict_json","",selvalue)
	
	
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
		
		
		var newleft=cityOffset.left;
		
		var newtop=cityOffset.top-10;

		
		$("#menuContent1").css({left:newleft + "px", top:newtop + cityObj.outerHeight() + "px"}).slideDown("fast");

		$("body").bind("mousedown", onBodyDown);
	}	
	
	
	//隐藏选择框
	function hideMenu() {	
		$("#menuContent1").fadeOut("fast");
		$("body").unbind("mousedown", onBodyDown);
	}
	
	
	//处理隐藏选择框事件
	function onBodyDown(event) {
		if (!(event.target.id == "menuBtn" 		
			|| event.target.id == "menuContent1" 
			|| $(event.target).parents("#menuContent1").length>0)) {
			hideMenu();
		}
	}

