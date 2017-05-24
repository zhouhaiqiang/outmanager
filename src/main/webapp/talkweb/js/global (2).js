/**
 * 新增或修改条件变量值
 * 
 * @param {条件变量名称}
 *            key
 * @param {条件变量值}
 *            text
 * @return {修改后的条件变量字符串}
 */
function inserOrUpdateConditionFields(key, text) {
	var conditionFields = $("#workflow_conditionFields").val();
	var newConditionFields = "";
	if (conditionFields != null && conditionFields != '') {
		conditionFields = conditionFields.substring(0,
				conditionFields.length - 1);
		var exist = false;
		var array1 = conditionFields.split("*");
		for ( var i = 0; i < array1.length; i++) {
			var str = array1[i];
			var array2 = str.split("^");
			if (array2[0] == key) {
				array2[1] = text;
				newConditionFields += array2[0] + "^" + text + "*";
				exist = true;
			} else {
				newConditionFields += array2[0] + "^" + array2[1] + "*";
			}
		}

		if (!exist) {
			newConditionFields += key + "^" + text + "*";
		}
	} else {
		newConditionFields = key + "^" + text + "*";
	}

	$("#workflow_conditionFields").val(newConditionFields);
	return newConditionFields;
}

var PageUtil = {
	WORD_PERM : "^word",
	ATTACHADD_PERM : "^AttachAdd",
	ATTACHDEL_PERM : "^AttachDel",
	ANGREE_PERM : "^Angree",
	MAINCC_PERM : "^Maincc",
	GETCODENUM : "^GetCodeNum",
	TITLE_PERM : "^Title",
	FILEDS_PERM : "^Fileds",
	YFRQ_PERM : "^YFRQ",
	HUIQIAN_PERM : "^Huiqian",
	HELP_PERM : "^Help",
	TRANSATIONEND_PERM : "^transationEnd",
	GENERALFLAG_PERM : "^generalFlag",
	BUTBACK_PERM : "^butback",
	DISPATCH_PERM : "^butdistribution",
	WORDLOCK_PERM : "^WordLock"
};

PageUtil.isCurStep = function(stepId) {
	return $("#workflow_curStepDefineId").val() == stepId;
};

PageUtil.isCurSeletedStep = function(stepId) {
	return $("#workflow_curSelectedStepDefineIdString").val() == stepId;
};

PageUtil.getCurStepName = function() {
	return $("#actListSel").find("option:selected").text();
};

PageUtil.hasPermission = function(perm) {
	var perms = document.getElementById('workflow_actbizattributes').value;
	if (perms == null || perms == "") {
		return false;
	}
	return perms.indexOf(perm) > -1;
};

PageUtil.getFlowActState = function() {
	var flag = $("#workflow_actbizattributes").val();
	try {
		flag = flag.split(";");
		flag = flag[11];
	} catch (err) {
		flag = 1;
	}
	return flag;
};

PageUtil.isFirstNewState = function() {
	var flag = PageUtil.getFlowActState();
	if (flag == 3) {
		return true;
	}
	return false;
};

PageUtil.isNewState = function() {
	var flag = PageUtil.getFlowActState();
	if (flag == 0 || flag == 3) {
		return true;
	}
	return false;
};

PageUtil.isTaskState = function() {
	var flag = document.getElementById("workflow_flag").value;
	return flag == 1;
};

PageUtil.isTakenState = function() {
	var flag = document.getElementById("workflow_flag").value;
	return flag == 2;
};

PageUtil.isEndState = function() {
	var flag = document.getElementById("workflow_flag").value;
	return flag == 3;
};

PageUtil.isToSendEnd = function() {
	if($("#actListSel").find("option:selected").attr("end") == "true") {
		return true;
	}
	return false;
};

PageUtil.inserOrUpdateConditionFields = inserOrUpdateConditionFields;

// 意见展示
function iconClick(divId, mindBar) {
	var icoEle = window.event.srcElement;
	var imgEle = "img_" + mindBar + "_" + divId;
	var icoDiv = icoEle.parentNode.parentNode;
	var msgdiv = $(icoDiv).nextAll("#" + divId)[0];
	var showFlag = msgdiv.style.display;

	if (showFlag == "block") {
		$("#" + imgEle).attr("src", rootpath + "/resources/image/icon-29.gif");
		msgdiv.style.display = "none";
		// $(icoDiv).removeClass("sj");
	} else {
		$("#" + imgEle).attr("src", rootpath + "/resources/image/icon-30.gif");
		msgdiv.style.display = "block";
		// $(icoDiv).addClass("sj");
	}
}

function receiveIconClick(divId, mindBar) {
	var icoEle = window.event.srcElement;
	var imgEle = "img_" + mindBar + "_" + divId;
	var icoDiv = icoEle.parentNode;
	var msgdiv = $(icoDiv).nextAll("#" + divId)[0];
	var showFlag = msgdiv.style.display;

	if (showFlag == "block") {
		$("#" + imgEle).attr("src", rootpath + "/resources/image/icon-29.gif");
		msgdiv.style.display = "none";
		// $(icoDiv).removeClass("sj");
	} else {
		$("#" + imgEle).attr("src", rootpath + "/resources/image/icon-30.gif");
		msgdiv.style.display = "block";
		// $(icoDiv).addClass("sj");
	}
}

var fileDialog;// 文件上传窗口
// 分发
var mainccDialog, sbmtType;
function mainccSelect(submitType, title) {
	sbmtType = submitType;
	var ttl = "添加主送、抄送";
	if (title) {
		ttl = title;
		var workdocFlowTypeName = $("#workdocFlowTypeName").val();
		if (workdocFlowTypeName != '') {
			if (workdocFlowTypeName.indexOf("收文") == -1) {
				if ($("#isCreateWord").val() == 0) {
					alert("请您生成文档");
					return false;
				}
			}
		} else {
			if ($("#isCreateWord").val() == 0) {
				alert("请您生成文档");
				return false;
			}
		}
	}

	var workdocId = $("#workdocId").val();
	var flowDefineName = $("#workflow_flowDefineName").val();
	var workdocCreateDeptId= $("#workdocCreateDeptId").val();
	var path = rootpath + "/send/sendSelect.do?flowDefineName="
			+ flowDefineName + "&submitType=" + submitType + "&workdocId="
			+ workdocId+"&workdocCreateDeptId="+workdocCreateDeptId;
	mainccDialog = art.dialog.open(path, {
		title : ttl,
		lock : true,
		width : 640,
		height : 510
	});
}

function mainccSubmit(callback) {
	if (sbmtType == "dispatch") {
		pageElementEdit(false);
		var workflowForm = $("#workflowForm");
		var rootpath = "/" + webroot;
		var flowPrefix = rootpath + "/workflow/";

		// 分发
		var actionPath = flowPrefix + "distributeProcess.do";
		$("#workflow_businessTitle").val($("#workdocTitle").val());
		jQuery.ajax({
			url : actionPath,
			data : workflowForm.serialize(),
			type : "POST",
			cache : false,
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			beforeSend : function() {
			},
			error : function(request) {
				pageElementEdit(true);
				alert("表单提交出错，请稍候再试");
			},
			success : function(data) {
				pageElementEdit(true);
				if (data === true) {
					document.getElementById("isSend").value = "true";
					alert("分发已提交！", 1);
					callback();
				} else {
					alert("分发失败！", 2);
				}
			}
		});
	} else {
		callback();
	}
}

/**
 * 选人组件 点击同意和不同意时 刷新选人组件 1:isAgree^true 2:isAgree^false
 * 
 * @param str
 *            1:同意，2：不同意 空为无
 */
function flashNextSteps(str) {
	var conditionFields = $("#workflow_conditionFields").val();
	if (conditionFields != '') {
		if (conditionFields.indexOf("isAgree") != -1) {
			if (str == '1') {
				conditionFields = changeConditionFields("isAgree", "true");
			} else if (str == '0') {
				conditionFields = changeConditionFields("isAgree", "false");
			} else {
				conditionFields = changeConditionFields("isAgree", "无");
			}
		} else {
			if (str == '1') {
				conditionFields += "isAgree^true*";
			} else if (str == '0') {
				conditionFields += "isAgree^false*";
			} else {
				conditionFields += "isAgree^无*";
			}
		}
	} else {
		if (str == '1') {
			conditionFields = "isAgree^true*";
		} else if (str == '0') {
			conditionFields = "isAgree^false*";
		} else {
			conditionFields = "isAgree^无*";
		}
	}

	$("#actListDiv").empty();
	$("#nextActUserSel").empty();
	// 刷新选人树
	getNextActivity(conditionFields);
}

/** 获取文件编号
workdocWenZhong：文种
accountId：用户ID
workflow_flowInstanceId:流程实例ID
workflow_processinstname：流程实例名称
workflow_processdefid：流程定义ID
workflow_processdefName:流程定义名
*/
function getFileCode() {
	document.getElementById('workdocCreateDeptName').disabled = false;
	var workdocWenZhong = document.getElementById("workdocWenZhong").value;
	var workdocCreateAccountId = document.getElementById("workdocCreateAccountId").value;
	var workflow_processinstname = document.getElementById("workflow_processinstname").value;
	var workflow_flowInstanceId = document.getElementById("workdocId").value;
	var workflow_processdefid = document.getElementById("workflow_processdefid").value;
	var workflow_processdefName = document.getElementById("workflow_flowDefineName").value;
	var workdocCreateDeptName = document.getElementById('workdocCreateDeptName').value;
	var workflow_processDisplayName = document.getElementById('workflow_processDisplayName').value;
	if(workflow_processDisplayName){
		workflow_processdefName = workflow_processDisplayName;
	}
	var workdocWenZhong = document.getElementById("workdocWenZhong").value;
	var isCreateWord = document.getElementById("isCreateWord").value;
	getFileNnm(workdocWenZhong,workdocCreateAccountId,workflow_flowInstanceId
		,workflow_processinstname,workflow_processdefid,workflow_processdefName
		,workdocCreateDeptName,workflow_processdefName,workdocWenZhong
		,isCreateWord);
	document.getElementById('workdocCreateDeptName').disabled = true;
}

/**
 * 获取文件编号和流水号 workdocWenZhong：文种 accountId：用户ID workflow_flowInstanceId:流程实例ID
 * workflow_processinstname：流程实例名称 workflow_processdefid：流程定义ID
 * workflow_processdefName:流程定义名
 * 
 */
function getFileNnm(workdocWenZhong, accountId, workflow_flowInstanceId,
		workflow_processinstname, workflow_processdefid,
		workflow_processdefName, workdocCreateDeptName,
		appExampleTypeName,fileTypeName,isCreateWord,isSubWin) {
	var actionPath = rootpath + "/workflow/";
	if(isSubWin == null || isSubWin == ''){
		isSubWin = false;
	}
	
	$.ajax({
		type : 'post',// 可选get
		async :false,
		url : actionPath + 'findFileCode.do',
		data : {
			"workdocWenZhong" : workdocWenZhong,
			"accountId" : accountId,
			"workflow_flowInstanceId" : workflow_flowInstanceId,
			"workflow_processinstname" : workflow_processinstname,
			"workflow_processdefid" : workflow_processdefid,
			"workflow_processdefName" : workflow_processdefName,
			"workdocCreateDeptName" : workdocCreateDeptName,
			"appExampleTypeName" : fileTypeName,
			"fileTypeName" : fileTypeName,
			"isCreateWord" : isCreateWord
		},
		cache : false,
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		success : function(msg) {
			var msgs = msg.split('#');
			if(!isSubWin){
				document.getElementById("workdocFileNumber").value = msgs[0];
			}else{
				parent.document.getElementById("workdocFileNumber").value = msgs[0];
			}
			
			if (typeof (msgs[1]) != "undefined") {
				var glideNumber;
				if(!isSubWin){
					glideNumber = document.getElementById("workdocGlideNumber");
				}else{
					glideNumber = parent.document.getElementById("workdocGlideNumber");
				}
				if(glideNumber){
					glideNumber.value = msgs[1];
				}
			}

			// 调用暂存接口
			if(!isSubWin){
				butsavecode();
			}else{
				window.parent.window.butsavecode();
			}
		},
		error : function(msg) {
		}
	});
}
// 打开正文
function openZhengWenUrl(fileUpload_url, url) {
	$("#submitIfo").attr("target", "_blank");
	$("#submitIfo").attr("src",
			fileUpload_url + "jsp/wordPage.jsp?" + encodeURI(encodeURI(name)));
}

// 正文
function openZhengWen(iShowTrace) {
	var quanxian = document.getElementById('workflow_actbizattributes').value;
	var flag = quanxian.split(";");// 是否是拟稿,新建
	
	var IP = document.getElementById("IP").value;
	var PORT = document.getElementById("PORT").value;
    var zhengwenUrl = document.getElementById("zhengwenUrl").value;
	var fileUpload_url = document.getElementById("workflow_fileUpload_url").value;
 
	var flowid = escape(document.getElementById("workdocId").value);
	var userid = document.getElementById("workflow_accountName").value;
	var workdocCreateUnitId = document.getElementById("workdocCreateUnitId").value; 
	var workdocWenZhong;
	var wenzhong = document.getElementById("workdocWenZhong");
	if(wenzhong.tagName == "SELECT"){
		workdocWenZhong = wenzhong.options[wenzhong.selectedIndex].text.replace(/^\s|\s$/g,""); 
	}else{
		workdocWenZhong = $("#workdocWenZhongText").val();
	}
	
	var appid = document.getElementById("workflow_appId").value; 
	
	var params = "flowId=" + flowid + "&userId=" + userid + "&appId=" + appid+"&compID="+workdocCreateUnitId+"&IP="+IP+"&PORT="+PORT+"&zhengwenUrl="+zhengwenUrl+"&hongtou="+workdocWenZhong+"红头";
  	// 拟稿，暂存不显示修订
	if (flag[11] == 0 ||flag[11] == 3) {
		params = params + "&iRevise=0";
	} else {
		params = params + "&iRevise=1";
	}
	params+="&iShowTrace="+iShowTrace;
	
	if(typeof window["zhengWenParams"] === "function"){
		params = zhengWenParams(params);
	}else{
		var flagArray = document.getElementById("workflow_flag").value;
		// 是否可以编辑正文
		if (!(quanxian.indexOf("^Word") != -1)||flagArray==2||flagArray==3) {
			params += "&iReadOnly=1";
		}
	}
	
	$("#submitIfo").attr("target","_blank");
	$("#submitIfo").attr("src",fileUpload_url + "jsp/wordPage.jsp?" + encodeURI(encodeURI(params)));
}

// 修改文件序号
function changeFileNo(id) {
	// 修改文件顺序号
	var list = $("#" + id).find("span[id^=num_]");
	var lastNum = 1;// 上一个num值
	var startNum = 0;// 初始值为0
	$(list).each(function(i) {
		var text = $(this).val();
		var id = $(this).attr('id');
		lastNum = startNum + 1;
		startNum = lastNum;
		$("#" + id).attr("num", lastNum);
		$("#" + id).html("(" + lastNum + ")");
	});
}

/**
 * 修改workflow_conditionFields中流程变量值
 * 
 * @param key
 *            流程变量key
 * @param text
 *            流程变量value
 * @returns {String}
 */
function changeConditionFields(key, text) {
	var conditionFields = $("#workflow_conditionFields").val();
	var newConditionFields = "";
	if (conditionFields != null && conditionFields != '') {
		conditionFields = conditionFields.substring(0,
				conditionFields.length - 1);
		var array1 = conditionFields.split("*");
		for ( var i = 0; i < array1.length; i++) {
			var str = array1[i];
			var array2 = str.split("^");
			if (array2[0] == key) {
				newConditionFields += array2[0] + "^" + text + "*";
			} else {
				newConditionFields += array2[0] + "^" + array2[1] + "*";
			}
		}
	}
	return newConditionFields;
}

// 下载文件
function downloadFile(filename) {
	// 需要判断使用word控件下载打开
	var fileDown_url = $("#workflow_fileDown_url").val();
	if (isNull(fileDown_url)) {
		fileDown_url = $("#fileDown_url").val();
	}
	// 判断附件路径是否为d://filestore
	// 有则截取
	// 附件路径存的格式d:/filestore/2013/workdoc/0/21/201308/ZHOUJING201308231105356/
	if (filename.indexOf("d:/") != -1) {
		filename = filename.replace("d:/", "");
	}
	window.open(fileDown_url + filename, "_blank");
}

// 下载文件,需要判断使用word控件下载打开
function downloadFile4NewWord(filename) {
	var fileDown_url = $("#workflow_fileDown_url").val();
	if (isNull(fileDown_url)) {
		fileDown_url = $("#fileDown_url").val();
	}
	// 判断附件路径是否为d://filestore
	// 有则截取
	// 附件路径存的格式d:/filestore/2013/workdoc/0/21/201308/ZHOUJING201308231105356/
	if (filename.indexOf("d:/") != -1) {
		filename = filename.replace("d:/", "");
	}
	window.open(fileDown_url + filename, "_blank");
}

// 删除文件 fileid =ID^名称^路径#
function deleteFile(fileid) {
	if (!fileid || fileid === "") {
		return;
	}

	art.dialog({
		content : "是否刪除此文件?",
		icon : "question",
		width : 250,
		fixed : true,
		lock : true,
		ok : function() {
			commitDelFile(fileid);
		},
		cancelVal : '取消',
		cancel : function() {
			art.dialog.close();
		}
	});
}

function commitDelFile(fileid) {
	var my = document.getElementById("div" + fileid.split("^")[0]);
	if (my != null) {
		my.parentNode.removeChild(my);
	}

	var workdocAttachment = document
			.getElementById("workflow_workdocAttachment").value;
	var workdocAttachmentArray = workdocAttachment.replace(fileid + "#", "");
	document.getElementById("workdocAttachment_div").value = document
			.getElementById("workdocAttachment_div").innerHTML;
	document.getElementById("workflow_workdocAttachment").value = workdocAttachmentArray;

	if (jQuery("#workflow_workdocCkzl").length > 0) {
		var workdocCkzl = document.getElementById("workflow_workdocCkzl").value, workdocCkzlArray = workdocCkzl
				.replace(fileid, ""), workdocCkzl_div = document
				.getElementById("workdocCkzl_div");

		if (workdocCkzl_div) {
			workdocCkzl_div.value = workdocCkzl_div.innerHTML;
			document.getElementById("workflow_workdocCkzl").value = workdocCkzlArray;
		}
	}

	// d调用文件服务-删除
	var fileUpload_url = $("#workflow_fileUpload_url").val();
	if (isNull(fileUpload_url)) {
		fileUpload_url = $("#fileUpload_url").val();
	}

	var workdocId = document.getElementById("workdocId").value;
	$.ajax({
		type : 'post',// 可选get
		url : fileUpload_url + 'file/deleteFile.do',
		data : {
			"fildId" : fileid.split("^")[0],
			"fileShareBusinessKey" : workdocId
		},
		cache : false,
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		success : function(msg) {
		},
		error : function(msg) {
		}
	});
	// 修改序号
	changeFileNo("workdocAttachment_div");
	if (jQuery("#workflow_workdocCkzl").length > 0) {
		changeFileNo("workdocCkzl_div");
	}
}

// 关闭文件上传窗口
function ifWindowClosed(flag) {
	var returnStr = $("#workflow_workdocAttachment").val();
	var workdocCkzl = $("#workflow_workdocCkzl").val();
	if (flag == 3) {
		if (null != returnStr && typeof (returnStr) != "undefined"
				&& "" != returnStr) {
			parent.fileDialog.close();
		}
	}
	if (flag == 4) {
		if (null != workdocCkzl && typeof (workdocCkzl) != "undefined"
				&& "" != workdocCkzl) {
			parent.fileDialog.close();
		}
	}
}
/**
 * 打开文件上传窗口 path:打开路径包括如下参数 flowid:业务ID userid：登陆人ID appid：
 * fileUpload_url：文件上传路径 flag：判断是附件 还是参考资料 3：附件 4：参考资料
 */
function openFile(path) {
	fileDialog = art.dialog.open(path, {
		title : '文件管理',
		lock : true,
		width : 570,
		height : 370
	});
}

// 生成文档，编号暂存
function butsavecode() {
	// 暂存时，开放页面禁用按钮
	pageElementEdit(false);
	var actionPath = flowPrefix + "saveProcess.do";
	var title = $("#workdocTitle").val();
	if (title == '') {
		alert("标题不能为空!!");
		$("#workdocTitle").focus();
		return;
	}

	$("#workdocTitle").val((title));
	$("#workflow_businessTitle").val($("#workdocTitle").val());
	// 判断是否有流程实例 是否为拟稿保存
	var flag = PageUtil.getFlowActState();
	// 3为拟稿.0为暂存
	if (flag != 3) {// 流程中的暂存
		$("#tempIdea").val(($("#logTransactIdea").text()));
		actionPath = flowPrefix + "saveData.do";
	}

	jQuery.ajax({
		url : actionPath,
		data : workflowForm.serialize(),
		type : "POST",
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		beforeSend : function() {
		},
		error : function(request) {
			alert("表单提交出错，请稍候再试", 3);
		},
		success : function(data) {
		}
	});
	pageElementEdit(true);
}

function deptNameReadOnly(type) {
	var deptName = document.getElementById("workdocCreateDeptName");
	if (type != "new") {
		if (deptName.type == "select") {
			if (type != "new") {
				$("#workdocCreateDeptName").css({
					"backgroundColor" : "#FDFBFB"
				});
				$("#workdocCreateDeptName").attr("disabled", "true");
			}
		} else if (deptName.type == "text") {
			$("#workdocCreateDeptName").css({
				"backgroundColor" : "#FDFBFB"
			});
			$("#workdocCreateDeptName").attr("readonly", "readonly");
		}
	}else{
		if (deptName.type == "text") {
			$("#workdocCreateDeptName").css({
				"backgroundColor" : "#FDFBFB"
			});
		}
		$("#workdocCreateDeptName").attr("readonly", "readonly");
	}
//	if (deptName.indexOf("SELECT") != -1) {
//		if (type != "new") {
//			$("#workdocCreateDeptName").css({
//				"backgroundColor" : "#FDFBFB"
//			});
//			$("#workdocCreateDeptName").attr("disabled", "disabled");
//		}
//	} else if (deptName.indexOf("INPUT") != -1) {
//		$("#workdocCreateDeptName").css({
//			"backgroundColor" : "#FDFBFB"
//		});
//		$("#workdocCreateDeptName").attr("readonly", "disabled");
//	}
}

function readOnlyDeptName(quanxian) {
	// if (quanxian.split(";")[11] != 3 && quanxian.split(";")[11] != 0) {//
	// 不是新建以及新建暂存的时候
	if (quanxian.split(";")[11] != 3 && quanxian.split(";")[11] != 0) {
		deptNameReadOnly("old");
	} else {
		deptNameReadOnly("new");
	}
}

function SetWinHeight(obj) {
	var win = obj;
	if (document.getElementById) {
		if (win && !window.opera) {
			if (win.contentDocument && win.contentDocument.body.offsetHeight) {
				win.height = win.contentDocument.body.offsetHeight;
			} else if (win.Document && win.Document.body.scrollHeight) {
				win.height = win.Document.body.scrollHeight;
			}
		}
	}

	var workdocId = $(obj).contents().find("#workdocId").val();
	if (workdocId != '' && workdocId != 'undefined') {
		setTimeout(function() {
			jQuery("#content3").attr("class", "");
			jQuery("#content3").hideLoading();
		}, 1000);
	}
}

/**
 * 特殊字符转换
 * 
 * @param a
 * @returns {String}
 */
function replaceFunction(a) {
	var b = '';
	if (a != null && a != "" && typeof (a) != "undefined") {
		j = a.length;
		// 特殊字符
		fibdn = new Array("\\", "/", "\"", "<", ">", "|", "&", "(", ")", "%",
				"#", "?", "+", "'",'"','"');
		// 替换为中文字符
		replacestr = new Array("＼", "／", "＂", "＜", "＞", "‖", "＆", "（", "）",
				"％", "＃", "？", "＋", "’","“","“");
		i = fibdn.length;
		for (jj = 0; jj < j; jj++) {
			temp1 = a.charAt(jj);
			for (ii = 0; ii < i; ii++) {
				temp2 = fibdn[ii];
				if (temp1 == temp2) {
					temp1 = replacestr[ii];
					break;
				}
			}
			b = b + temp1;
		}
	}
	return b;
}

function isNull(val) {
	return !val || val.length === 0 || /^\s*$/g.test(val);
}

function openFilePage(flag){
	var flowid = escape(document.getElementById("workdocId").value);
	var userid = document.getElementById("workdocCreateAccountId").value;
	var appid = document.getElementById("workflow_appId").value;
	var fileUpload_url = document.getElementById("workflow_fileUpload_url").value;
	if (isNull(fileUpload_url)) {
		fileUpload_url = $("#fileUpload_url").val();
	}
	
	var IP = document.getElementById("IP").value;
	var PORT = document.getElementById("PORT").value; 
	var name = "flowId=" + flowid + "&userId=" + userid + "&appId=" + appid
			+ "&flag=" + flag+"&IP="+IP+"&PORT="+PORT+"";
	var path = rootpath+"/commons/uploadfile.jsp?fileUpload_url=" + fileUpload_url
			+ "&flowId=" + flowid + "&userId=" + userid + "&appId=" + appid
			+ "&flag=" + flag+"&IP="+IP+"&PORT="+PORT+"";
	openFile(path);
}

// 获取主办、协办情况
function refreshTab(workdocId) {
	var workflow_flag = $("#workflow_flag").val();
	if (workflow_flag != "") {
		if (workflow_flag == 0 || workflow_flag == '0') {
			return;
		}
	}

	var actionPath = rootpath + "/undertake/";
	var path = actionPath + "refreshTab.do";
	var hostDeptStr = "";
	var assistDeptStr = "";
	var passUsers = "";
	//var passIdea = "";
	jQuery.ajax({
		url : path,
		data : {
			"workdocId" : workdocId
		},
		type : "POST",
		dataType : "json",
		success : function(data) {
			var jsonObject = eval(data);
			if (jsonObject != null && jsonObject != '') {
				for ( var i = 0; i < jsonObject.length; i++) {
					hostDeptStr = jsonObject[i].hostDeptStr;
					assistDeptStr = jsonObject[i].assistDeptStr;
					passUsers = jsonObject[i].passUsers;
					//passIdea = jsonObject[i].passIdea;
				}
			}
			$("#workdocZhuBan").html(hostDeptStr);
			$("#workdocXieBan").html(assistDeptStr);
			$("#workdocPassUsers").html(passUsers);
			//$("#passIdea").html(passIdea)
		},
		error : function(data) {
			alert("加载数据失败！");
		}
	});
}

window.alert = showAlert;

/*
 * 重写alert方法,如有需要 可再次扩展
 * 
 * @param info 弹出框提示信息 @param type 弹出框类型 默认是警告 ,1,成功；2，失败 ;3,成功且关闭窗口
 */
function showAlert(info, type) {
	var icon = "warning";
	var flag = false;
	if (typeof (type) != "undefined" && type != null && type != '') {
		if (type == 1 || type == '1') {
			icon = "succeed";
		} else if (type == 2 || type == '2') {
			icon = "error";
		} else if (type == 3 || type == '3') {
			icon = "succeed";
			flag = true;
		} else {
			icon = "warning";
		}
	}

	art.dialog({
		title : '温馨提示',
		fixed : true,
		lock : true,
		icon : icon,
		width : 250,
		content : info,
		ok : function() {
			if (typeof (type) != "undefined" && type != null && type != '') {
				if (type != 2 && type != '2') {
					art.dialog.close();
				}
			}

			if (flag) {
				window.opener = null;
				window.open('', '_self');
				window.close();
			}
		}
	});
}

/**
 * xml转义字符替换
 * 
 * @param {需要替换的字符串}
 *            str
 * @param {转化模式
 *            0：将特殊字符转义 1：将转义字符转换回来} mode
 * @return {}
 */
function xmlCharReplace(str, mode) {
	if (mode == 0) {
		return str.replace(/\n/g, "<br>").replace(/</g, "&lt;").replace(/>/g,
				"&gt;").replace(/"/g, "&quot;").replace(/'/g, "&apos;")
				.replace(/ /g, "&nbsp;").replace(/&/g, "&amp;");
	} else if (mode == 1) {
		// 可能后台也经过了编码，需要三次解码
		str = str.replace(/[&＆]lt;/g, "<").replace(/[&＆]gt;/g, ">").replace(
				/[&＆]quot;/g, "\"").replace(/[&＆]apos;/g, "'").replace(
				/[&＆]amp;/g, "&").replace(/[&＆]nbsp;/g, " ").replace(/<br>/g,
				"\n");
		str = str.replace(/[&＆]lt;/g, "<").replace(/[&＆]gt;/g, ">").replace(
				/[&＆]quot;/g, "\"").replace(/[&＆]apos;/g, "'").replace(
				/[&＆]amp;/g, "&").replace(/[&＆]nbsp;/g, " ").replace(/<br>/g,
				"\n");
		return str.replace(/[&＆]lt;/g, "<").replace(/[&＆]gt;/g, ">").replace(
				/[&＆]quot;/g, "\"").replace(/[&＆]apos;/g, "'").replace(
				/[&＆]amp;/g, "&").replace(/[&＆]nbsp;/g, " ").replace(/<br>/g,
				"\n");
	}
}

function hiddenIdea() {
	var flag2 = $("#workflow_flag").val();// 影藏意见
	var flag = PageUtil.getFlowActState();// 影藏意见
	if (flag == 3 || flag == 0) {
		try {
			var workdocFlowTypeName = $("#workdocFlowTypeName").val();
			if(workdocFlowTypeName.indexOf("收文") ==-1) {
				$("#ideadiv").hide();
				$("#table-chooseuser").css('border-top-style', 'solid');
				// 流转记录展开
				var div1 = document.getElementById('pd1u104');
				// 关闭
				var div2 = document.getElementById('pd0u104');
				div1.style.display = 'none';
				div2.style.display = 'none';
			} else {
				var div1 = document.getElementById('u104');
				div1.style.display = 'none';
			}
		} catch (e) {
		}
	}
}

var _isHuiqian_ = false;//标志当前选中环节是否是会签环节
/**
 * 会签获取部门通用函数
 * @param {} curActDefId
 */
function checkhuiqian(curActDefId) {
	if ($("#workflow_type").val() != "cancel") {
		var deptNum = "";
		var test = document.getElementsByTagName("input");
		var testValue = "";
		
		for (i = 0; i < test.length; i++) {
			if (test[i].type == "checkbox" && test[i].name.indexOf("workdocHuiQian") != -1) {
				if($("#workflow_type").val() == "reissue"){
					deptNum = deptNum + test[i].name.substr(14) + "^";
					continue;
				}
				
				if(document.getElementById(test[i].name).checked){
					deptNum = deptNum + test[i].name.substr(14) + "^";
				}
			}
		}
		if (null != deptNum && '' != deptNum) {
			setActivityUser(deptNum.substr(0, deptNum.length - 1), curActDefId);
		}else{
			setActivityUser("", curActDefId);
		}
		
		_isHuiqian_ = true;
	}
}

/**
 * 部门发文带出师会签的特殊会签获取部门通用函数
 * @param {} curActDefId
 */
function deptCheckhuiqian(curActDefId, orgType) {
	if ($("#workflow_type").val() != "cancel") {
		var deptNum = "";
		var test = document.getElementsByTagName("input");
		var testValue = "";
		if("dept" == orgType){
			for (i = 0; i < test.length; i++) {
				if (test[i].type == "checkbox" && "deptHuiQianCheck" == $(test[i]).attr("class")) {
					if($("#workflow_type").val() == "reissue"){
						deptNum = deptNum + test[i].name.substr(14) + "^";
						continue;
					}
					
					if(document.getElementById(test[i].name).checked){
						deptNum = deptNum + test[i].name.substr(14) + "^";
					}
				}
			}
		}else if("org" == orgType){
			for (i = 0; i < test.length; i++) {
				if (test[i].type == "checkbox" && "orgHuiQianCheck" == $(test[i]).attr("class")) {
					if($("#workflow_type").val() == "reissue"){
						deptNum = deptNum + test[i].name.substr(14) + "^";
						continue;
					}
					
					if(document.getElementById(test[i].name).checked){
						deptNum = deptNum + test[i].name.substr(14) + "^";
					}
				}
			}
		}
		if (null != deptNum && '' != deptNum) {
			setActivityUser(deptNum.substr(0, deptNum.length - 1), curActDefId);
		}else{
			setActivityUser("", curActDefId);
		}
		
		_isHuiqian_ = true;
	}
}

function onTreeLoaded(){
	if(!_isHuiqian_ || $("#workflow_type").val() == "reissue" || $("#workflow_type").val() == "cancel")
		return;
//	if (PageUtil.hasPermission(PageUtil.HUIQIAN_PERM)) {
//		if (PageUtil.isCurSeletedStep(flowCons.FLOW_HUIQIAN)) {
			var fra = $("#actUserTreeFrame").get(0);
			if(!fra){
				return ;
			}
			
			var ztree = fra.contentWindow.$.fn.zTree.getZTreeObj("userOrgTree");
			var total = $("input:checkbox[id^='workdocHuiQian']").size();
			var seled = $("input:checkbox[id^='workdocHuiQian']:checked").size();
			
			if(seled == 0){
				$("#nextActUserSel").empty();
			}
			
			if (!ztree 
				|| seled == 0
				|| (seled > 0 && ztree.getNodes().length > 0 && ztree.getNodes()[0].children && total == ztree.getNodes()[0].children.length)) {
				return;
			}
			
			$("#nextActUserSel").empty();
			var otherNodes = ztree.getNodesByFilter(function(node) {
				return node.level != 1 && !node.isParent;
			});
			
			for (var i = 0; i < otherNodes.length; i++) {
				selectNextActivityUser(otherNodes[i].id,otherNodes[i].name);
			}
//		}
//	}
}

function textAreaKeyPress(obj){
	var id = $(obj).attr("id");
	var counter = 0;
	if (typeof(id) == "undefined") { 
	    return;
	}   
	
	var range = document.getElementById(id).createTextRange();
    var rect = range.getClientRects();
    //直观行
    counter = rect.length;
	if (counter > 4) {
		$("#"+ id).attr("style","width: 654px;overflow-x: hidden;overflow-y:visible;height:78px;");
	}
}

function onloadTextArea() {
	if ($("#workdocRemark").length > 0) {
    	commonTextArea("workdocRemark");
	}
	
    if ($("#workdocDuMind").length > 0) {
    	commonTextArea("workdocDuMind");
	}
}

function commonTextArea(id) {
	var counter = 0;
	var obj=$("#" + id);
	if (!obj[0]["createTextRange"]) {
		return;
	}
	var range = obj[0].createTextRange();
    var rect = range.getClientRects();
    //直观行
    counter = rect.length;
	if (counter > 1) {
		obj[0].style.posHeight = obj[0].scrollHeight;
	}
	
	if (counter > 4) {
		$("#" + id).attr("style",
			"width: 654px;overflow-x: hidden;overflow-y:visible;height:78px;");
	}
}

function checkKey(eventObject, obj) {
    var eventObj = eventObject || window.event;
    var eventSource = eventObj.srcElement;
    var id = $(obj).attr("id");
    if ((eventObj.keyCode >= 65 && eventObj.keyCode <= 90) 
    		|| (eventObj.keyCode >= 97 && eventObj.keyCode <= 122) 
    		|| eventObj.keyCode == '0x8' || eventObj.keyCode == '0x2E') {}
    obj.style.posHeight = obj.scrollHeight;
    
    //换行键
	if (eventObj.keyCode == 13) {
		obj.rows += 1;
		obj.style.posHeight = obj.scrollHeight;
	}
    
    if($("#" + id).text() == '') {
    	$("#" + id).attr("style", "width: 654px;overflow-x: hidden;overflow-y:hidden;height:20px;");
    }
    textAreaKeyPress(obj);
}

/**
 * 判断元素在数组中是否存在，
 * 存在则返回true 否则返回false
 * 
 * @param needle
 *            需要查找的值
 * @param array
 *            源数组
 * @returns {Boolean}
 */
function hasProcessDefidExist(needle,array){   
	if(typeof needle == "string" || typeof needle == "number"){  
	    var len = array.length;  
	    for(var i = 0;i < len;i++){  
	       if(needle===array[i]){  
	          return true;  
	       }  
	    }  
	    return false;  
	}  
}  

/**
 * 获取收文流水号
 */
function getGlideNumber (){
	var attributes = $("#workflow_actbizattributes").val();
	if (attributes != null && attributes != '') {
		if (!(attributes.indexOf('^GetCodeNum') != -1)) {//没有权限
			return;
		}
	}
	
	var actionPath = rootpath + "/workflow/";
	$.ajax({
		type : 'post',// 可选get
		url : actionPath + 'getFileFlowCode.do',
		data : {
			"ruleId" : $("#receiveRuleId").val(),
			"workdocWenZhong" : $("#workdocWenZhong").val(),
			"accountId" : $("#workflow_accountId").val(),//收文取当前登录人
			"workflow_flowInstanceId" : $("#workdocId").val(),
			"workflow_processinstname" : $("#workflow_processinstname").val(),
			"workflow_processdefid" : $("#workflow_processdefid").val(),
			"workflow_processdefName" : $("#workflow_flowDefineName").val(),
			"workdocCreateDeptName" : $("#workdocCreateDeptName").val(),
			"appExampleTypeName" : $("#workdocFlowTypeName").val(),
			"fileTypeName" : "",
            "isCreateWord" : $("#isCreateWord").val()
		},
		cache : false,
		async : false,
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		success : function(msg) {
			$("#workdocGlideNumber").val(msg);
		}
	});
}

/**
 * 设置控件样式
 * @param cId 
 *     控件ID
 */
function setControlCss(cId) {
	$("#" + cId).attr("readonly", true);
	$("#" + cId).css({"backgroundColor" : "#FDFBFB"});
}

/**
 * 设置下拉框样式
 * @param cId
 *     控件ID
 */
function setSelectCss(cId) {
	$("#" + cId).attr("disabled", true);
	$("#" + cId).css({"backgroundColor" : "#FDFBFB"});
}

/**
 * 收文流程 获取主办部门综合员 
 * @param curActDefId
 */
function setActivityOrgUser(curActDefId) {
	var packageid = $("#workflow_packageid").val();
	var versionid = $("#workflow_versionid").val();
	var processdefid = $("#workflow_processdefid").val();
	var flowDefineId = $("#workflow_flowDefineId").val();
	var curStepDefineId = $("#workflow_curStepDefineId").val();
	var workflow_flowInstanceId = $("#workflow_flowInstanceId").val();
	var workflow_accountOrgId = $("#workflow_accountOrgId").val();
	var curStepInstanceId = $("#workflow_curStepInstanceId").val();
	var workflow_type = $("#workflow_type").val();
	var curStepInstanceId = $("#workflow_curStepInstanceId").val();
	var topflowInstanceId = $("#workflow_topflowInstanceId").val();
	var actionPath = rootpath + "/workflow/";
	
	var workflow_type = $("#workflow_type").val();
	var workdocId = $("#workdocId").val();
	var nextActivityUserUrl = actionPath + "getActivityOrgUser.do";
	nextActivityUserUrl += "?workflow_flowInstanceId="
			+ workflow_flowInstanceId + "&workflow_flowDefineId="
			+ flowDefineId + "&workflow_curStepDefineId=" + curStepDefineId
			+ "&workflow_accountOrgId=" + workflow_accountOrgId + "&s1="
			+ curActDefId + "&workflow_workdocId=" + workdocId 
			+"&workflow_topflowInstanceId=" + topflowInstanceId;
	$("#actUserTreeFrame").attr("src", nextActivityUserUrl);
}

// 获取传阅意见
function refreshPassIdea(readerId,workdocId,readerType) {
	var actionPath = rootpath + "/reader/";
	var path = actionPath + "getPassreaderIdea.do";
	var passIdea = "";
	jQuery.ajax({
		url : path,
		data : {
			"readerId" : readerId,
			"workdocId" : workdocId,
			"readerType" : readerType
		},
		type : "POST",
		dataType : "json",
		success : function(data) {
			//alert("1:"+data.responseText);
			var jsonObject = eval(data);
			//alert("json:"+jsonObject.ideaString[0].createTimeStr);
			if (jsonObject != null && jsonObject.ideaString != null && jsonObject.ideaString.length > 0) {
				var innerHtml = "";
				for( var i = 0; i < jsonObject.ideaString.length; i++) {
					var curObject = jsonObject.ideaString[i];
					var hrHtml = "";
					if(i>0){
						hrHtml = "<DIV style=\"BORDER-TOP: #c1c9cf 1px dashed; OVERFLOW: hidden; HEIGHT: 1px\"></DIV>";
					}
					
					var div_passIdea = " <div align=\"left\" class=\"divFont\" style=\"padding-left: 8px\">"+curObject.passIdeaString+"</div>";
					var div_right = "<div align=\"right\" class=\"divFont\" style=\"margin-top: 10px;\">"
					div_right += "<span style=\"padding-right: 10px\">"+curObject.passUserName+"</span>"
					div_right += "<span style=\"padding-right: 5px\">"+curObject.createTimeStr+"</span>"
					div_right += "</div>"
					
					innerHtml += hrHtml+div_passIdea+div_right;
				}
				
				$("#td_passIdea").html(innerHtml);
			}
			
		},
		error : function(data) {
			alert("error");
		}
	});
}

/**
 * 商密文件必须送综合部会签
 */
function sendToZongHuiqian(){
	var returnFlag = true;
	
	var workdocCreateOrgId = $("#workdocCreateOrgId").val();
	var workdocCreateOrgName = $("#workdocCreateOrgName").val();
  	var zong_dept_id = $("#zong_dept_id").val();
  
  	//当前公文是否为综合部拟稿的
	if(workdocCreateOrgId != zong_dept_id && workdocCreateOrgName.indexOf("综合部") < 0){
		var objHoldTime = $("#workdocHoldTime");
 	   	var objSecretLevel = $("#workdocSecretLevel");
 	   
 	   	if(objHoldTime && objHoldTime.attr("id") && objSecretLevel && objSecretLevel.attr("id")){
			if(objSecretLevel.val() == "ordinarySecret" || objSecretLevel.val() == "coreSecret"){			//普通商密或核心商密，必须送综合部会签
				$("#actListSel option").each(function(){					//当前环节可以选择相关部门会签
					if($(this).val() == "archive-comp-send_act6" || $(this).val() == "archive-subcomp-send_act61" || $(this).val() == "workdoc-subcomp-sign-pack_wp1_act12" || $(this).val() == "workdoc-comp-sign-pack_wp1_act6"){
						returnFlag = false;
						
						//签核历程中是否有综合部会签过
						var hadHuiqian = false;
						var relevantHuiqianDeptIds = $("#relevantHuiqianDeptIds");
						var relevantHuiqianDeptNames = $("#relevantHuiqianDeptNames");
						
						if(relevantHuiqianDeptIds && relevantHuiqianDeptIds.attr("id") && relevantHuiqianDeptIds.val().indexOf(","+zong_dept_id+",") >= 0){
							hadHuiqian = true;
						}
						if(!hadHuiqian && relevantHuiqianDeptNames && relevantHuiqianDeptNames.attr("id") && relevantHuiqianDeptNames.val().indexOf("综合部")>=0){
							hadHuiqian = true;
						}
						
						if(!hadHuiqian){									//综合部人员没有会签过
							if($("#actListSel").val() != $(this).val()){
								alert("商密文件必须送综合部会签。");
								$("#actListSel").val($(this).val());
								changeActUserTree();
							}else{
								//选择的会签人员中是否有综合部人员
								var chooseZongUser = false;					//是否有选择综合部人员
								$("#nextActUserSel option").each(function(){
									var curUserMsg = $(this).val();
									var curOrgId = curUserMsg.substring(curUserMsg.indexOf("^")+1);
									if(curOrgId == zong_dept_id){
										chooseZongUser = true;
									}
								})
								
								if(!chooseZongUser){
									alert("商密文件必须送综合部会签。");
								}else{
									returnFlag = true;					//有选择综合部人员会签
								}
							}
						}else{
							returnFlag = true;							//综合部人员有会签过
						}
					}
				})
		  	}else{
		  		returnFlag = true;										//非普通商密及核心商密
		  	}
	  	}
  	}else{
  		returnFlag = true;												//当前拟稿部门为综合部
  	}
	
	return returnFlag;
}

/**
 * 置某些按钮可用与不可用
 * @param {Object} enable:true表示置为不可用
 * @param {Object} isParent:true表示是在子窗口调用此方法
 * @param {Object} isOpenZhengWen:true表示点击的按钮是编辑正文
 */
function enableSomeBtn(enable,isParent,isOpenZhengWen){
	if(enable == null){
		enable = false;
	}
	if(isOpenZhengWen == null){	
		isOpenZhengWen = false;
	}
	
	var btnId = "workdocZhengWen";
	if(isOpenZhengWen){
		btnId = "butcreatepaper";
	}
	
	var objCreatePaper = null;
	var objZhengWen = null;
	var objIdeaSave = null;
	var objNextExecute = null;
	var objSave = null;
	
	if(isParent != null && isParent){
		objCreatePaper = parent.document.getElementById("butcreatepaper");			//生成文档
		objZhengWen = parent.document.getElementById("workdocZhengWen");			//编辑正文
		objIdeaSave = parent.document.getElementById("btnIdeaSave");				//意见暂存
		objNextExecute = parent.document.getElementById("nextExecuteBtn");			//流程处理
		objSave = parent.document.getElementById("butsave");						//暂存
	}else{
		objCreatePaper = document.getElementById("butcreatepaper");					//生成文档
		objZhengWen = document.getElementById("workdocZhengWen");					//编辑正文
		objIdeaSave = document.getElementById("btnIdeaSave");						//意见暂存
		objNextExecute = document.getElementById("nextExecuteBtn");					//流程处理
		objSave = document.getElementById("butsave");								//暂存
	}
	
	if(objCreatePaper != null){
		objCreatePaper.disabled = enable;
	}
	if(objZhengWen != null){
		objZhengWen.disabled = enable;
	}
	if(objIdeaSave != null){
		objIdeaSave.disabled = enable;
	}
	if(objNextExecute != null){
		objNextExecute.disabled = enable;
	}
	if(objSave != null){
		objSave.disabled = enable;
	}
}
