var userTreeCache = new Object();// 用户缓存下一步选人树
var _curActDefId_ = "";
/**
 * 设置主办、协办 ： 涉及到流程 地市公司收文
 */
function setWF_SelNextNodeList(nextActDefId) {
	$("#workflow_curSelectedStepDefineIdString").val(nextActDefId);
	var flag = false;
	// 如果是地市收文 各部门办理环节,如果其他流程需要处理此类业务, 只需要在相关业务js中 重写此方法即可
	if (typeof(window['dealHostDept']) === "function") {
		flag = dealHostDept(nextActDefId);
	}

	if (flag) {
		$("#menu1").hide();
		$("#menu2").show();
	} else {
		$("#menu1").show();
		$("#menu2").hide();
	}

	if (typeof(window['limitHostDept']) === "function") {
		// 判断是不是一级部门
		var limitFlag = limitHostDept();
		if (limitFlag) {
			//$("#li_auditing").attr("style", "display: block;");
			$("#li_readed").attr("style", "display: block;");
		} else {
			if (typeof(window['showAuditing']) === "function") {
				var isAuditing = showAuditing();
				if (isAuditing) {
					$("#li_readed").attr("style", "display: block;");
				}
			}
		}
	}
}

/**
 * 阅示 如果用户已经存在已选用户列表, 则不加入 ,否则,加入右边列表
 */
function addReaded() {
	addBtn("[阅示]", 5);
}

/** 主办* */
function addOrganizer() {
	var obj = document.getElementById("actUserTreeFrame").contentWindow;
	var treeNodeId = obj.document.getElementById("seltreeNodeId").value;
	var treeNodeName = obj.document.getElementById("seltreeNodeName").value;
	var opts = $("#nextActUserSel").get(0).options;
	var workflow_type = $("#workflow_type").val();
	if (treeNodeId == "" && treeNodeId.length == 0) {
		return;
	}

	// 判断是否可以选择多个主办
	var flag = false;// 默认只能选择一个主办
	if (typeof(window['limitHostDept']) === "function") {
		flag = limitHostDept();
	}

	// 收文流程 部门落实环节 只能送一个主办
	if (workflow_type == 'reissue') {
		if (!flag) {
			// 判断已选用户中是否有 主办 如果有 则提示
			var count = 0;
			// 判断是否已经选择了主办
			for (var j = 0; j < opts.length; j++) {
				if (opts[j].text.indexOf("[主办]") > 0) {
					count++;
				}
			}

			if (count > 0) {
				$.messager.alert("操作提示","当前环节只能选择一个主办用户!","warning");
				return;
			}
		}
	}

	var treeNodeIds = treeNodeId.split("#");
	var treeNodeNames = treeNodeName.split("#");
	var size = opts.length;
	var number = 0;

	// 判断主办个数, 如果限制只能选一个主办
	if (!flag) {
		for (var s = 0; s < size; s++) {
			if (opts[s].text.indexOf("[主办]") > 0) {
				number++;
				if (number >= 1) {
					if (treeNodeIds.length > 1) {
						$.messager.alert("操作提示","当前环节只能选择一个主办!","warning");
						return;
					} else {
						// 移除该值
						jQuery("#nextActUserSel option[value='" + opts[s].value
								+ "']").remove();
						$("#nextActUserSel").append("<option value="
								+ treeNodeId + ">" + treeNodeName + "[主办]"
								+ "</option>");
						return;
					}
				}
			}
		}
	}

	var noExistIds = [];
	var noExistNames = [];
	if (opts.length == 0) {
		noExistIds = treeNodeIds;
		noExistNames = treeNodeNames;
	}

	for (var i = 0; i < treeNodeIds.length; i++) {
		for (var j = 0; j < opts.length; j++) {
			if (treeNodeIds[i] === opts[j].value) {
				break;
			}

			if (j == opts.length - 1) {
				noExistIds.push(treeNodeIds[i]);
				noExistNames.push(treeNodeNames[i]);
			}
		}
	}

	// 可以选择多个主办
	if (noExistIds.length > 0) {
		for (var j = 0; j < noExistIds.length; j++) {
			$("#nextActUserSel").append("<option value=" + noExistIds[j] + ">"
					+ noExistNames[j] + "[主办]" + "</option>");
		}
	}
}

/** *协办** */
function addCoorganizer() {
	addBtn("[协办]", 2);
}

/**
 * 审阅:生成待阅，重要待阅
 */
function addAuditRead() {
	addBtn("[审阅]", 4);
}

/**
 * 公共按钮逻辑
 * 
 * @param btnName
 *            按钮名称
 * @param btnType
 *            按钮类型 1,主办；2,协办; 3,阅办;4,审阅; 5,阅示
 */
function addBtn(btnName, btnType) {
	var obj = document.getElementById("actUserTreeFrame").contentWindow;
	var treeNodeId = obj.document.getElementById("seltreeNodeId").value;
	var treeNodeName = obj.document.getElementById("seltreeNodeName").value;
	var opts = $("#nextActUserSel").get(0).options;
	if (treeNodeId == "" && treeNodeId.length == 0) {
		return;
	}

	// 所有选中的
	var flag = false;
	var treeNodeIds = treeNodeId.split("#");
	var treeNodeNames = treeNodeName.split("#");
	var workflow_type = $("#workflow_type").val();
	var count = 0;
	// 阅办
	if (btnType == 3) {
		// 判断是否已经选择了主办
		for (var j = 0; j < opts.length; j++) {
			if (opts[j].text.indexOf("[主办]") != -1 || opts[j].text.indexOf("[协办]") != -1) {
				count++;
			}
		}

		if ("reissue" != workflow_type) {
			if (count == 0) {
				//alert("请您至少选择一个主办或协办用户!");
				//return;
			}
		}
	}

	var noExistIds = [];
	var noExistNames = [];
	if (opts.length == 0) {
		noExistIds = treeNodeIds;
		noExistNames = treeNodeNames;
	}

	for (var i = 0; i < treeNodeIds.length; i++) {
		for (var j = 0; j < opts.length; j++) {
			if (treeNodeIds[i] === opts[j].value) {
				break;
			}

			if (j == opts.length - 1) {
				noExistIds.push(treeNodeIds[i]);
				noExistNames.push(treeNodeNames[i]);
			}
		}
	}

	if (noExistIds.length > 0) {
		for (var j = 0; j < noExistIds.length; j++) {
			$("#nextActUserSel").append("<option value=" + noExistIds[j] + ">" + noExistNames[j] + btnName + "</option>");
		}
	}
}

/** 阅办:生成待阅，一般待阅；阅办必须选择主办或者协办（必须选择一个主办）* */
function addReading() {
	addBtn("[阅办]", 3);
}

/**
 * 移除用户
 */
function removeUser() {
	var opt = $("#nextActUserSel option:selected");
	
	var workflowType = $("#workflow_type");
	if(workflowType != null && "reissue"==workflowType.val()){
		var text = opt.text();
		if(text.indexOf("(已发") > 0){
			return false;
		}
	}
	
	opt.remove();
}

/**
 * 刷新右边用户列表
 * 
 * @param treeNodeId
 * @param treeNodeName
 */
function selectNextActivityUser(treeNodeId, treeNodeName,treeNodedeptid) {
	//把选择的路由对应的节点id也拼进去
	var selRouterId = $("#WF_SelNextNodeList").val();
	// 过滤空格
	if (treeNodeId == "" && treeNodeId.length == 0) {
		return;
	}

	var opts = $("#nextActUserSel").get(0).options;
	var processdefid = $("#workflow_processdefid").val();
	var nextActSelOpt = $("#WF_SelNextNodeList option:selected");
	var workflow_type = $("#workflow_type").val();
	var flag = false;// 是否为主协办选择
	if (typeof(window['dealHostDept']) === "function") {
		// 撤销不需要显示主协办
		if (workflow_type != 'cancel') {
			flag = dealHostDept();
		}
	}

	// 各部门办理
	if (flag) {
		treeNodeName += "[主办]";;
	}

	// 判断是否可以选择多个主办
	var hostFlag = false;
	if (typeof(window['limitHostDept']) === "function") {
		hostFlag = limitHostDept();
	}

	// 收文流程 部门落实环节 只能送一个主办
	if (workflow_type == 'reissue') {
		if (!hostFlag) {
			// 判断已选用户中是否有 主办 如果有 则提示
			var opts = $("#nextActUserSel").get(0).options;
			var count = 0;
			// 判断是否已经选择了主办
			for (var j = 0; j < opts.length; j++) {
				if (opts[j].text.indexOf("[主办]") > 0) {
					count++;
				}
			}

			if (count > 0) {
				$.messager.alert("操作提示","当前环节只能选择一个主办用户!","warning");
				return;
			}
		}
	}

	// 判断已选用户个数
	if (opts.length > 0) {
		// 判断当前环节是否允许选多个处理人
		var nextActDefTypeStr = nextActSelOpt.attr("type");// 1单选 其他:多选
		var isMultiInstance = (nextActDefTypeStr != 1);// 是否多选
		if (!isMultiInstance) {
			// 先清空用户列表 再把当前选择的用户加入用户列表
			$("#nextActUserSel").empty();
			$("#nextActUserSel").append("<option value='" + treeNodeId +"#"+treeNodedeptid +"'>"
					+ treeNodeName + "</option>");
			return;
		} else {
			// 判断该用户是否已经选择
			var index = 0;
			for (var i = 0; i < opts.length; i++) {
				if (opts[i].value == treeNodeId+"#"+treeNodedeptid) {
					index++;
					break;
				}
			}

			if (index == 0) {
				if (flag) {
					// 是否可以多次选择主办
					if (!hostFlag) {
						var count = 0;
						// 判断是否已经选择了主办
						for (var j = 0; j < opts.length; j++) {
							if (opts[j].text.indexOf("[主办]") > 0) {
								count++;
								// 移除该值
								jQuery("#nextActUserSel option[value='"
										+ opts[j].value + "']").remove();
								$("#nextActUserSel").append("<option value="
										+ treeNodeId+"#"+treeNodedeptid +">" + treeNodeName
										+ "</option>");
								break;
							}
						}

						if (count == 0) {
							$("#nextActUserSel").append("<option value="
									+ treeNodeId+"#"+treeNodedeptid + ">" + treeNodeName
									+ "</option>");
						}
					} else {
						// 可以选择多个主办
						var count = 0;
						for (var i = 0; i < opts.length; i++) {
							if (opts[i].value == treeNodeId+"#"+treeNodedeptid ) {
								count++;
								break;
							}
						}

						if (count == 0) {
							$("#nextActUserSel").append("<option value="
									+ treeNodeId+"#"+treeNodedeptid + ">" + treeNodeName
									+ "</option>");
						}
					}
				} else {
					$("#nextActUserSel").append("<option value=" + treeNodeId+"#"+treeNodedeptid + ">" + treeNodeName + "</option>");
				}
			}
		}
	} else {
		$("#nextActUserSel").append("<option value='" + treeNodeId+"#"+treeNodedeptid + "'>"
				+ treeNodeName + "</option>");
	}
}

// 获取流程提交的action
function getActionName() {
	var actionPath = rootpath + "/workflow/";
	var flagVal = $("#workflow_flag").val();
	var fval = $("#workflow_actbizattributes").val().split(";")[11];

	var nextActSelOpt = $("#WF_SelNextNodeList option:selected");
	var isEndEvent = nextActSelOpt.attr("end");// 是否结束
	if (isEndEvent == true || isEndEvent == "true") {
		actionPath += "endProcess.do";
		return actionPath;
	}

	if (fval == 3) {
		// 新建
		actionPath += "newProcess.do";
	}

	if (fval == 0 || flagVal == 1) {
		// 待办处理
		actionPath += "submitProcess.do";
	}

	if (flagVal == 2) {
		// 在办处理
		var callType = $("#workflow_type").val();
		if (callType == "cancel") {
			// 撤销
			actionPath += "deleteSubProcess.do";
		} else if (callType == "reissue") {
			// 补发
			actionPath += "addSubProcess.do";
		}
	}
	return actionPath;
}

function addNextActivityUser() {
	var obj = document.getElementById("actUserTreeFrame").contentWindow;
	var treeNodeId = obj.document.getElementById("seltreeNodeId").value;
	var treeNodeName = obj.document.getElementById("seltreeNodeName").value;
	var treeNodedeptid = obj.document.getElementById("seltreedeptid").value;
	
	//修改by lyh at 20151020 需求为比如我点中公司领导，选过去。如果允许选多个人，就把所有公司领导都到已选,如果只允许选一个人，就提示只允许选一个人，
	var nextActSelOpt = $("#WF_SelNextNodeList option:selected");
	var nextActDefTypeStr = nextActSelOpt.attr("type");// 1单选 其他:多选
	//alert(nextActDefTypeStr);
	//nextActDefTypeStr != 1
	//if (treeNodeId!='')
	//	selectNextActivityUser(treeNodeId, treeNodeName,treeNodedeptid);
	//else
	//{
		var seltreeNodeall = obj.document.getElementById("seltreeNodeall").value;
		if (seltreeNodeall!='')
			{
				var tmp1=seltreeNodeall.split(',');
				if (tmp1.length-1>nextActDefTypeStr && nextActDefTypeStr!='0' && nextActDefTypeStr!='' )
					$.messager.alert("操作提示","本环节最多允许选择"+nextActDefTypeStr+"个用户。","warning");
				else
				{
					for(var i=0;i<tmp1.length;i++){
						var tmp2=tmp1[i].split('#');
						selectNextActivityUser(tmp2[0], tmp2[1],tmp2[2]);
					}
				}
			}
		else
			{
			var tmp1=treeNodeId.split('#');
			var tmp2=treeNodeName.split('#');
			var tmp3=treeNodedeptid.split('#');
			if (tmp1.length-1>nextActDefTypeStr && nextActDefTypeStr!='0' && nextActDefTypeStr!='' )//if (tmp1.length>nextActDefTypeStr && nextActDefTypeStr!='0' && nextActDefTypeStr!=''  )
				$.messager.alert("操作提示","本环节最多允许选择"+nextActDefTypeStr+"个用户","warning");
			else
			{
				for(var i=0;i<tmp1.length;i++){
					//var tmp2=tmp1[i].split('#');
					selectNextActivityUser(tmp1[i], tmp2[i],tmp3[i]);
				}
			}
			//selectNextActivityUser(treeNodeId, treeNodeName,treeNodedeptid);
			}
	//}
}

function removeNextActivityUser() {
	var workflow_type = $("#workflow_type").val();
	var tempNextUserName = $('#nextActUserSel option:selected').text();
	if ("reissue" == workflow_type) {
		if (tempNextUserName.substring(tempNextUserName.length - "(已发)".length) == "(已发)") {
			return;
		}
	} else if ("cancel" == workflow_type) {
		var opt = $('#nextActUserSel option:selected')[0];
		if (opt) {
			var win = $("#actUserTreeFrame").get(0).contentWindow;
			win.addNode4UserTree1("2", opt.value, opt.text, false);
		}
	}
	$('#nextActUserSel option:selected').remove();
}

/**
 * 更新选人树内容
 */
function updateUserTreeContent(content) {
	var parentDoc = document.getElementById("actUserTreeFrame").contentWindow.document,
	tree=parentDoc.getElementById("userOrgTree");
	if (tree) {
		tree.innerHTML = content;
	}
}

var cacheUtil = {
	getFromCache : function(curActDefId) {
		var step = $("#WF_SelNextNodeList option:selected");
		if(step.attr("end") == "true"){
			return false;
		}
		
		var stepName = step.text();
		if(stepName && stepName.indexOf("会签") > -1){
			return false;
		}
		
		//console.log("获取缓存curActDefId:" + curActDefId);
		var actTreeCache = userTreeCache[curActDefId];
		//console.log("获取结果actTreeCache:"+actTreeCache);
		if (actTreeCache) {
			//console.log("找到缓存");
			var treeData = actTreeCache[0];
			var formStr = actTreeCache[1];
			var users = actTreeCache[2];
			
			if (formStr) {
				cacheUtil.updateFormContent(formStr);
			}
			if (treeData) {
				cacheUtil.updateTreeData(treeData);
			}
			if (users) {
				cacheUtil.initSelectedUsers(users);
			}
			//console.log("根据缓存初始化成功");
			return true;
		}
		//console.log("没找到缓存");
		return false;
	},
	setCache : function(curActDefId) {
		//console.log("设置缓存curActDefId:" + curActDefId);
		try {
			var parentWin = document.getElementById("actUserTreeFrame").contentWindow;
			var queryForm = parentWin.document.getElementById("queryForm");
			
			if(queryForm){
				var formStr = queryForm.innerHTML;
				var treeData = parentWin.usersJson;
				var selectedUsers = cacheUtil.getSelectedUserString();
	
				if (treeData) {
					userTreeCache[curActDefId] = [];
					userTreeCache[curActDefId] = [treeData, formStr, selectedUsers];
					//console.log("设置缓存curActDefId:" + curActDefId + "成功");
					return;
				}
			}
		} catch (e) {
			//console.log(e);
		}
		//console.log("设置缓存curActDefId:" + curActDefId + "失败");
	},
	getSelectedUserString : function() {
		var opts = $("#nextActUserSel").get(0);
		while (!opts) {
			setTimeout(1000, opts = $("#nextActUserSel").get(0));
		}
		return opts.innerHTML;
	},
	initSelectedUsers : function(userStr) {
		var opts = $("#nextActUserSel").get(0);
		while (!opts) {
			setTimeout(1000, opts = $("#nextActUserSel").get(0));
		}
		if(opts.innerHTML != userStr){
			$("#nextActUserSel").empty();
			$("#nextActUserSel").append(userStr);
		}
	},
	updateFormContent : function(content) {
		$("#actUserTreeFrame").contents().find("#queryForm").html(content);
		
	},
	updateTreeData : function(treeData) {
		var parentWin = document.getElementById("actUserTreeFrame").contentWindow;
		var parentDoc = $(parentWin.document);
		var zNodes = eval(treeData);
		var setting = parentWin.setting;

		parentWin.$.fn.zTree.destroy("userOrgTree");
		$(parentWin.document).ready(function() {
			parentWin.$.fn.zTree.init(parentDoc.find("#userOrgTree"), setting,zNodes);
			var treeObj = parentWin.$.fn.zTree.getZTreeObj("userOrgTree");
			var userCount = parentDoc.find("#userCount").val();
			if (userCount != null && userCount != '') {
				// 如果用户只一个人 则默认让他在右边显示
				if (userCount == 1 || userCount == '1') {
					var treeObj = parentWin.$.fn.zTree.getZTreeObj("userOrgTree");
					// 默认全打开
					treeObj.expandAll(true);

					var nodes = treeObj.getNodes()[0].children;
					if (nodes) {
						var result = "";
						for (var i = 0; i < nodes.length; i++) {
							result += parentWin.getAllChildrenNodes(nodes[i],result);
						}

						if (result != '') {
							var array = result.split("#");
							selectNextActivityUser(array[0], array[1]);
						}
					}
				}
			}

			/** 根据特殊业务去掉下一步选人组件待选用户和已选用户* */
			try {
				if (typeof(window.flushStepUser()) === "function") {
					flushStepUser();
				}
			} catch (e) {
			}

			try {
				var treeObj = parentWin.$.fn.zTree.getZTreeObj("userOrgTree");
				var nds = treeObj.getNodes();
				if (nds != null && nds.length > 0 && nds[0].isParent)
					treeObj.expandNode(nds[0], true, false, false, true);
				nds = nds[0].children;
				if (nds != null && nds.length > 0 && nds[0].isParent)
					treeObj.expandNode(nds[0], true, false, false, true);
				nds = nds[0].children;
				if (nds != null && nds.length > 0 && nds[0].isParent)
					treeObj.expandNode(nds[0], true, false, false, true);
			} catch (e) {
			}
		});
	}
};
