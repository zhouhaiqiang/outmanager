
/** 流程日志处理* */
function refreshLog(id, workdocId) {
	var url = "r?wf_num=R_S029_B015&DocUnid=" + id + "&Flag=log";
	$.get(url, function(data) {
		var jsonobj = eval("(" + data + ")");
		document.getElementById("strtable").innerHTML = jsonobj.strtable;
	})
}

/** 用于捕获 zTree 上鼠标双击之前的事件回调函数，并且根据返回值确定触发 onDblClick 事件回调函数* */
function beforeDblClick(treeId, treeNode) {
	if (treeNode != null && treeNode != '') {
		if (treeNode.isParent) {
			if (treeNode.children && treeNode.children.length > 0) {
				refreshLog(treeNode.id, treeNode.workdocId);
			} else {
				return;
			}
		} else {
			refreshLog(treeNode.id, treeNode.workdocId);
		}
	}
	return true;
}

/** 单击事件触发* */
function getSelectNode(event, treeId, treeNode) {
	if (treeNode != null && treeNode != '') {
		if (treeNode.isParent) {
			if (treeNode.children && treeNode.children.length > 0) {
				refreshLog(treeNode.id, treeNode.workdocId);
			} else {
				return;
			}
		} else {
			refreshLog(treeNode.id, treeNode.workdocId);
		}
	}
	return true;
}

$(document).ready(function() {
	$("#gwlzjl").click(function() {
		clickTable("mytable", "27", "34");
	});

	// 公文传阅
	$("#gwcy").click(function() {
		clickTable("cy_table", "30", "29");
	});

	// 发文情况
	$("#fwqk").click(function() {
		clickTable("mytable2", "27", "34");
	});

	// 传阅情况
	$("#cyqk").click(function() {
		clickTable("mytable3", "27", "34");
		clickTable("cyzf_table", "30", "29");
	});

	// 集团发文
	$("#jtfw").click(function() {
		clickTable("jtfwtable", "30", "29");
	});

	// 省内发文
	$("#sngbmfw").click(function() {
		clickTable("sngbmfwtable", "30", "29");
	});

	// 省内发文
	$("#snfw").click(function() {
		clickTable("snfwtable", "30", "29");
	});

	// 传阅转发
	$("#cyzf").click(function() {
		clickTable("cyzf_table", "30", "29");
	});

	// $.fn.zTree.init($("#flowTree"), setting, zTreeNodes);
	// var treeObj = $.fn.zTree.getZTreeObj("flowTree");
	// treeObj.expandAll(true);
	showTab();
});

/**
 * 展开、伸缩方法
 * 
 * @param id:
 *            要伸缩的table id
 * @param img1
 *            展开时图片
 * @param img2
 *            收缩时图片
 */
function clickTable(id, img1, img2) {
	// status :1隐藏 2显示
	if (typeof ($("#" + id).attr("status")) == "undefined") {
		$("#" + id).attr("status", "1");
		$("#" + id).hide();
		$("#" + id + "_img").attr("src",
				"talkweb/bpm/images/icon-" + img1 + ".gif");
	} else {
		var status = $("#" + id).attr("status");
		if (status == 1) {
			$("#" + id).attr("status", "2");
			$("#" + id).show();
			$("#" + id + "_img").attr("src",
					"talkweb/bpm/images/icon-" + img2 + ".gif");
		} else {
			$("#" + id).hide();
			$("#" + id).attr("status", "1");
			$("#" + id + "_img").attr("src",
					"talkweb/bpm/images/icon-" + img1 + ".gif");
		}
	}

	if (id == "cy_table") {
		refreshTable("cy_table");
	} else if (id == "cyzf_table") {
		refreshTable("cyzf_table");
	} else if (id == "snfwtable") {
		refreshTable("snfwtable");
	} else if (id == "jtfwtable") {
		refreshTable("jtfwtable");
	} else if (id == "sngbmfwtable") {
		refreshTable("sngbmfwtable");
	} else if(id == "mytable2"){
		snFenFaTab("fwqk_table");
	}
}

/**
 * 传阅阅文情况
 */
function refreshTable(tabId) {
	var status = $("#" + tabId).attr("status");
	// status :1隐藏 2显示
	if (status == 1) {
		$("#" + tabId).attr("style", "display:block");
		var tabId_result = jQuery("#" + tabId + "_result").html();
		if (tabId_result == '') {
			// 传阅和传阅转发
			if (tabId == "cy_table" || tabId == "cyzf_table") {
				showReadInfo(tabId);
			}

			// 集团分发和省内分发情况
			if (tabId == "jtfwtable" || tabId == "snfwtable"
					|| tabId == "sngbmfwtable") {
				snFenFaTab(tabId);
			}
		}
	} else {
		$("#" + tabId).attr("style", "display:none");
	}
}

/**
 * 异步获取阅件信息 包括传阅和转发阅件信息
 * 
 * @param tabId
 *            区分阅件和转发阅件
 */
function showReadInfo(tabId) {

	var readurl = "r?wf_num=R_S029_B015&DocUnid=" + GetUrlArg("DocUnid")
			+ "&Flag=mail";

	$.get(readurl, function(data) {
		var jsonobj = eval("(" + data + ")");
		$("#" + tabId + "_loading").attr("style", "display:none");
		$("#" + tabId + "_head").attr("style", "display:black");
		// document.getElementById("#" +tabId + "_result").innerHTML = data;
		jQuery("#" + tabId + "_result").html(jsonobj.strtrs);
		var main = $(window.parent.document).find("#undertakeIframe");
		var thisheight = $(document).height();
		main.height(thisheight + 100);
	});

}

/**
 * 分发情况
 * 
 * @param tabId
 *            
 */
function snFenFaTab(tabId) {
	var wf_docunid = GetUrlArg("DocUnid");
	var url =  "r?wf_num=R_EduWorkDoc_getDistributeLog";
	   $.ajax({
				url : url,
				data : {
					"DocUnid" : wf_docunid,
					"Flag" : "distribute"
				},
				type : "POST",
				dataType : "json",
				success : function(data) {
					$("#" + tabId + "_loading").attr("style", "display:none");
					$("#" + tabId + "_head").attr("style", "display:black");
					var jsonObject = eval(data);
					var html = "";
					var msg = "";
					if (jsonObject == '') {
						msg = "当前没有发文记录!";
						html += "<tr><td colspan='6' style='background-color:#fff; color:#FF0000;'>"
								+ msg + "</td></tr>";
						jQuery("#" + tabId + "_result").html(html);
					} else {
						html = "";
						$
								.each(
										jsonObject,
										function(i, item) {

											var delogStatus = item.status;
											html += "<tr>";
											

											if (i % 2 == 0) {
												html += "<td class='alt'>"
														+ item.receive_username
														+ "</td>";
												html += "<td class='alt'>"
													+ item.delog_dt_name
													+ "</td>";
												html += "<td class='alt'>"
														+ item.send_username
														+ "</td>";
												html += "<td class='alt'>"
														+ item.create_time
														+ "</td>";
												html += "<td class='alt'>"
														+ delogStatus + "</td>";
												html += "<td class='alt'>" + item.lastUpdateTime 
														+ "</td>";
											} else {
												
												html += "<td>"
														+ item.receive_username
														+ "</td>";
												html += "<td>"
													+ item.delog_dt_name
													+ "</td>";
												html += "<td>"
														+ item.send_username
														+ "</td>";
												html += "<td>"
														+ item.create_time
														+ "</td>";
												html += "<td>" + delogStatus
														+ "</td>";
												html += "<td>" + item.lastUpdateTime 
														+ "</td>";
			
											}
											html += "</tr>";
										});

						jQuery("#" + tabId + "_result").html(html);
						var main = $(window.parent.document).find(
								"#undertakeIframe");
						var thisheight = $(document).height();
						main.height(thisheight + 100);
					}
				}
			});
}

function destroyTree() {
	$.fn.zTree.getZTreeObj("flowTree").init($("#flowTree"), setting, null);
}

function addNode4Tree(id, name) {
	var treeObj = $.fn.zTree.getZTreeObj("flowTree");
	var nodeJson = "({asyn:0,id:'" + id + "',pid:'null',name:'" + name + "'})";
	treeObj.addNodes(null, eval(nodeJson));
}

function removeNode(id) {
	var treeObj = $.fn.zTree.getZTreeObj("flowTree");
	var toRemove = treeObj.getNodeByParam('id', id, null);
	treeObj.removeNode(toRemove);
}

function btnClose() {
	window.close();
}

function tabClick() {
	if ($(this).hasClass('activeTab'))
		return;
	$('.hd ul li').removeClass('activeTab');
	$(this).addClass('activeTab');
	var tabId = $(this).attr('tabId');
	$('#content5 > div').hide();
	$('#' + tabId).show();
	if (tabId == 'content4') {
		$("#u104").show();
	}
}

function openlog() {
	$("#content4").hide();
	$("#content3").show();
	var workdocId = $("#workdocId").val();
	$("#undertakeIframe").attr("src", "");
	$("#undertakeIframe").height(0);
	showLoading();
	$("#undertakeIframe").attr(
			"src",
			rootpath + "/undertake/undertakeflowLog.do?attr=0&workdocId="
					+ workdocId);
}

function openlog2() {
	$("#content4").hide();
	$("#content3").show();
	var workdocId = $("#workdocParentId").val();
	$("#undertakeIframe").attr("src",
			rootpath + "/undertake/flowLog.do?attr=0&workdocId=" + workdocId);
}

function openlog11(value) {
	$("#content4").hide();
	$("#content3").show();
	$("#undertakeIframe").attr("src", "");
	$("#undertakeIframe").height(0);
	showLoading();
	$("#undertakeIframe").attr("src",
			rootpath + "/undertake/flowLog.do?attr=0&workdocId=" + value);
}

function showLoading() {
	jQuery("#content3").attr("class", "activity_pane");
	jQuery("#content3").showLoading();
}

function showTab() {
	var len = $('#tab li').length;
	var lastLi = $("#lastLi");
	$(lastLi).addClass("nomal ending activeTab currentending");

	$("#tab li").click(function() {
		var current = $('#tab li').index(this) + 1;
		var currentnext = $(this).next().index() + 1;
		$(".current").removeClass("current");
		$(".currentnext").removeClass("currentnext");
		$(".currentending").removeClass("currentending");
		if (current == len) {
			$(this).addClass("currentending");
			$(".subend").removeClass("subend");
		} else {
			$(this).addClass("current");
			if (currentnext == len) {
				$(".ending").removeClass("ending");
				$(this).next().addClass("subend");
			} else {
				$(this).next().addClass("currentnext");
				$(".subend").removeClass("subend");
			}
			$(".subend").addClass("ending");
		}
	});
}

/**
 * 弹出转发意见
 * 
 * @param userIdea
 */
function popuZfIdea(userIdea) {
	var actionPath = rootpath + "/workflow/";
	var path = actionPath + "showUserIdea.do?userIdea=" + userIdea;
	art.dialog.open(path, {
		title : '转发意见',
		lock : true,
		width : 600,
		height : 200
	});
}

/**
 * 注册事件
 */
function regrsigerQtip() {
	$('td[title]').qtip({
		position : {
			my : 'bottom left',
			at : 'top center'
		},
		style : {
			classes : 'ui-tooltip-rounded'
		}
	});
}
