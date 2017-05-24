var empText = "请输入姓名的中文、拼音等";
var userJson = "";
var intMax = 20000;
var flags = true;
var isAsyncForSearch;
var isRemote = false;
function focusValue(obj) {
	if (empText == obj.value) {
		obj.value = "";
		obj.style.color = "";
	}
}

function blurValue(obj) {
	if (!obj.value.trim().length > 0 || obj.value == null) {
		obj.value = empText;
		obj.style.color = "#cccccc";
	}
}
function searchstart() {
	var searchStr = document.getElementById("searchTextId").value;
	if(searchStr == ""){
		searchList("1");
	}else if (userJson.length < intMax) {
		searchList("1");
	} else if (event.keyCode == 13) {
		searchList("1");
	}
}
function searchList(diyFlag) {
	var searchStr = document.getElementById("searchTextId").value;
	if (searchStr == empText) {
		searchStr = "";
	}
	//首先搜索前天,没有搜索到前台就到后台搜索,modify by lyh at 20151105
	
	try{
	//	document.getElementById("actUserTreeFrame").src="linkey/bpm/images/icons/loading.gif";//return;
		window.frames["actUserTreeFrame"].searchNodes(searchStr);
		}catch(e){
	//		document.getElementById("actUserTreeFrame").src="";
	//		alert(e);
		}
	if (isAsyncForSearch=='0'&& diyFlag!="1"&&searchStr!="")//前段没找到数据就到后端找
	{
		if(!isRemote){
			isRemote = true;
			ShowRouterUserForSearch(searchStr);
		}
	}
	else if(isAsyncForSearch=="0"&&(event.keyCode==8 || event.keyCode==46)&&searchStr==""){
		if(!isRemote){
			isRemote = true;
			ShowRouterUser();
		}
	} 
	else if (diyFlag=="0")
	{
		if(!isRemote){
			isRemote = true;
			ShowRouterUserForSearch(searchStr);
		}
	}
	
	/*
	if(isAsyncForSearch=="0"&&diyFlag!="1"&&searchStr!=""){
		//后端搜索功能
		if(!isRemote){
			isRemote = true;
			ShowRouterUserForSearch(searchStr);
		}
	}else if(isAsyncForSearch=="0"&&(event.keyCode==8 || event.keyCode==46)&&searchStr==""){
		if(!isRemote){
			isRemote = true;
			ShowRouterUser();
		}
	}else{
		//前端搜索功能
		try{
		window.frames["actUserTreeFrame"].searchNodes(searchStr);
		}catch(e){}
	}
	*/
}

//用于后台查询的选人js
function ShowRouterUserForSearch(searchStr){
	//显示路由用户选择
	if(searchStr==""){
		//如果没有搜索内容,则还原
		ShowRouterUser();
		return;
	}
	var NodeStr=$("#WF_SelNextNodeList").val();
	if("huiqianzhong"==NodeStr){
	    return;
	}
	document.getElementById("actUserTreeFrame").src="linkey/bpm/images/icons/loading.gif";//return;
	var tmpArray=NodeStr.split("#");
	var Nodeid=tmpArray[0]; //选中的节点的id
	var gatewayType=tmpArray[1]; //网关类型，可以根据网站类型是否是并行网关来决定是否删除已选择的用户列表
	var url="r?wf_num=R_S029_B100";
	var curNodeid=$("#WF_CurrentNodeid").val();
	var Processid=$("#WF_Processid").val();
	var docUnid=$("#WF_DocUnid").val();
	var BackToPrvNode = $("#WF_SelNextNodeList").find("option:selected").attr("BackToPrvNode");//获取是否是回退上一步的标志
	var random = new Date();
	var formData="";
	formData = "&searchStr="+searchStr+"&CurrentNodeid="+curNodeid+"&Processid="+Processid+"&DocUnid="+docUnid+"&NextNodeid="+Nodeid+"&BackToPrvNode="+BackToPrvNode+"&"+$("#linkeyform").serialize();
	$.post(url,formData,function(data){
		    //这里根据返回的用户json数据，显示人员选择树
			var userList = data.substring(data.indexOf("["), data.indexOf("]")+1);
			userCountForFrame = data.substring(data.indexOf("]")+1, data.length);
			//isAsyncForSearch = data.substring(0,data.indexOf("["));
		    userJson = '('+userList+')';
		    window.frames["actUserTreeFrame"].ShowRouterUserForSearchIs = true;
		    isRemote = false;
		    if(userList=="[]"){
		    	isAsyncForSearch="0";
		    	//document.getElementById("actUserTreeFrame").src="r?wf_num=P_S029_100&ran="+random;
		    	//ShowRouterUser();
		    	document.getElementById("actUserTreeFrame").src="";
		    }else{
		    	isAsyncForSearch="1";
			    document.getElementById("actUserTreeFrame").src="r?wf_num=P_S029_100&ran="+random;
		    }
		},"text");
}