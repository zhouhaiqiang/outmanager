<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>

<title>中国联通山西省分公司外包管理系统</title>

<!-- 引入头文件 -->
<%@include file="../common/head.jsp" %>

<!-- 引入标签文件 -->
<%@include file="../common/tag.jsp" %>


<link rel="stylesheet" type="text/css" href="<%=path%>/css/linkey/bpm/easyui/themes/red/easyui.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/css/linkey/bpm/easyui/themes/red/icon.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/talkweb/bpm_red/css/bpm_style.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/talkweb/bpm_new/style.css" /> 
<link rel="stylesheet" type="text/css" href="<%=path%>/talkweb/bpm_page/css/main.css" />  




<script type="text/javascript">
$(function(){
	loadRightPage("业务管理","<%=path%>/common/blank.html","menua_000");
});

	function getTree(id){
		 $("#topnav li").click(function () {
		        $(this).addClass("curr").siblings().removeClass("curr");//增加删除新样式
		 });
	}
	
	function changemenucolor(id){
	    var h4 = $("#menulist").find("h4");
	    h4.each(function(){
	       $(this).attr("class",""); 
	    });
	    $("#"+id).parent().attr("class","curr");
	}

	function loadRightPage(title,url,id){
		document.getElementById("rightIframe").src=url;
	}

	function loacalPage(title,url,id){
	 //   changemenucolor(id);

	 //alert(url);

	    loadRightPage(title,url,id);
	}
	
	function showChild(id){
		var ul = $("#menulist").find("ul");
		ul.each(function(){
			 $(this).css("display","none");
	    });
		 $("#"+id).attr("class","curr");
		 $("#"+id+"_1").css("display","block");
	}
	function changClass(id){
		var name_sb=id.substr(6,7);
		var bgLis=document.getElementsByTagName("menuli");
		for(var i = 0; i < bgLis.length; i++){
			bgLis[i].style.backgroundColor = "#fff";
			if((i+1) == name_sb){
				bgLis[i].style.backgroundColor = "#e7e7f1";
			}
		}
	}
	function change(obj){
		$(".menuli").each(function(){
			$(this).css("background-color","");
		});
		$(obj).css("background-color","rgb(239, 236, 236)");
	}
</script>
<body>
<input type="hidden" id="path" value="<%=path%>" />
<div data-options="region:'north',split:false" border="false" style="height:120px;overflow:hidden">
<!--头部 begin-->
<div id="top">
<div id="logo"><img src="<%=path%>/talkweb/bpm_new/img/logo.png" width="101" height="56" /></div>
<div id="title">中国联通山西省分公司外包管理系统</div>
<div class="bg">
<div class="info" id="top_div_info">
		<ul>
			<li>
				<span id='UserName'></span>
			<img src="<%=path%>/talkweb/bpm_new/img/man.png" width="12" height="15" /><span id='UserName'>${userName}</span>
			<%--<span onclick="closePage();" style='cursor:pointer'>退出</span>
			
			--%></li>

		</ul>
	</div>
</div>
</div>
<!--导航 begin-->
<div class="nav">
       <ul id="topnav">
       		<li>
           		<a id="a_1" onclick="getTree(1)" href="javascript:void(0);">首页</a>
            </li>
            <li>
           		<a id="a_2" onclick="getTree(2)" href="javascript:void(0);">业务管理</a>
            </li>
            <li>           		
           		<a id="a_3" onclick="getTree(3)" href="javascript:void(0);">系统管理</a>
            </li>
            <li>           		
           		<a id="a_4" onclick="getTree(4)" href="javascript:void(0);">示范管理</a>          		
            </li>
            <li>           		
           		<a id="a_5" onclick="getTree(5)" href="javascript:void(0);">常用文档</a>
            </li>
       </ul>
</div>
    
    <!--导航 end--> 
    </div>
	<!--头部 end-->
	<div region="bottom" border="true" fit="true" style="width:100%;height:100%">
		<div class="panel layout-panel layout-panel-west" style="width: 228px; top: 120px; left: 0px;" abp="27">
		        <div class="menu_x" id="menu" >
		            <div class="column-1 left">
		                <div class="daibanleftmenu" id="menulist">
		                	<h4 class="curr">
								<a href='javascript:' id='menua_000' onclick='changemenucolor("menua_000");loadRightPage("业务管理","<%=path%>/common/blank.html","menua_000");showChild(id);'  href="javascript:">业务管理</a>
							</h4>
							
									                
		                	<h4>
								<a href='javascript:' id='menua_001' onclick='changemenucolor("menua_001");loadRightPage("外包公司信息管理","<%=path%>/common/blank.html","menua_001");showChild(id);'  href="javascript:">外包公司信息管理</a>
							</h4>
							<ul class="tag-list"  id="menua_001_1">
								<li class="menuli" id="menu001" onclick="changClass('menu001')">
									<a id='menua_001_01' onclick='loacalPage("外包公司信息维护","../data/company_list","menua_001_01")' href="javascript:">外包公司信息维护</a>
								</li>	
								<li class="tag-list" id="menua_001_2">	
									<a id='menua_001_01' onclick='loacalPage("外包公司批量上传","../data/company_upload","menua_001_01")' href="javascript:">外包公司批量上传</a>
								</li>							
							</ul>
							
							
							
							<h4>
								<a href='javascript:'id="menua_002"  onclick='changemenucolor("menua_002");showChild(id);'>外包合同信息管理</a>
							</h4>
							<ul class="tag-list" style="display:none" id="menua_002_1">
								
								
								<li class="tag-list" id="menua_001_2">	
									<a id='menua_001_01' onclick='loacalPage("外包合同信息维护","../data/contract_list","menua_001_01")' href="javascript:">外包合同信息维护</a>
								</li>																
								<li class="tag-list" id="menua_001_2">	
									<a id='menua_001_01' onclick='loacalPage("外包合同批量上传","../data/contract_upload","menua_001_01")' href="javascript:">外包合同批量上传</a>
								</li>									
								
									
							</ul>
							<h4>
								<a href='javascript:' id='menua_003' onclick='changemenucolor("menua_003");showChild(id);'>外包人员信息管理</a>
							</h4>
							<ul class="tag-list" style="display:none" id="menua_003_1">
									
								<li class="tag-list" id="menua_001_2">	
									<a id='menua_001_01' onclick='loacalPage("外包人员信息维护","../user/list","menua_001_01")' href="javascript:">外包人员信息维护</a>
								</li>																
								<li class="tag-list" id="menua_001_2">	
									<a id='menua_001_01' onclick='loacalPage("外包人员批量上传","../user/upload","menua_001_01")' href="javascript:">外包人员批量上传</a>
								</li>									
								
							</ul>
							<h4>
								<a href='javascript:' id='menua_004' onclick='changemenucolor("menua_004");showChild(id);'>外包人员费用管理 </a>
							</h4>
							<ul class="tag-list" style="display:none" id="menua_004_1">
									
									
								<li class="tag-list" id="menua_001_2">	
									<a id='menua_001_01' onclick='loacalPage("银行信息维护","../pay/queryBankPage.action","menua_001_01")' href="javascript:">外包费用信息维护</a>
								</li>																
								<li class="tag-list" id="menua_001_2">	
									<a id='menua_001_01' onclick='loacalPage("银行信息维护","../pay/queryBankPage.action","menua_001_01")' href="javascript:">外包费用批量上传</a>
								</li>										
								<li class="tag-list" id="menua_001_2">	
									<a id='menua_001_01' onclick='loacalPage("银行信息维护","../pay/queryBankPage.action","menua_001_01")' href="javascript:">外包集体费用信息维护</a>
								</li>																
							</ul>							
							
							
							<h4>
								<a href='javascript:' id='menua_005' onclick='changemenucolor("menua_005");showChild(id);'>外包信息报表管理 </a>
							</h4>
							<ul class="tag-list" style="display:none" id="menua_005_1">
									
									
								<li class="tag-list" id="menua_001_2">	
									<a id='menua_001_01' onclick='loacalPage("银行信息维护","../pay/queryBankPage.action","menua_001_01")' href="javascript:">规范型外包合同报表</a>
								</li>																
								<li class="tag-list" id="menua_001_2">	
									<a id='menua_001_01' onclick='loacalPage("银行信息维护","../pay/queryBankPage.action","menua_001_01")' href="javascript:">紧密型外包合同报表</a>
								</li>										
								<li class="tag-list" id="menua_001_2">	
									<a id='menua_001_01' onclick='loacalPage("银行信息维护","../pay/queryBankPage.action","menua_001_01")' href="javascript:">紧密型人员信息报表 </a>
								</li>	
								
								<li class="tag-list" id="menua_001_2">	
									<a id='menua_001_01' onclick='loacalPage("银行信息维护","../pay/queryBankPage.action","menua_001_01")' href="javascript:">紧密型信息统计报表 </a>
								</li>																
								<li class="tag-list" id="menua_001_2">	
									<a id='menua_001_01' onclick='loacalPage("银行信息维护","../pay/queryBankPage.action","menua_001_01")' href="javascript:">外包费用信息报表 </a>
								</li>										
								<li class="tag-list" id="menua_001_2">	
									<a id='menua_001_01' onclick='loacalPage("银行信息维护","../pay/queryBankPage.action","menua_001_01")' href="javascript:">外包费用信息报表 </a>
								</li>
																	
								<li class="tag-list" id="menua_001_2">	
									<a id='menua_001_01' onclick='loacalPage("银行信息维护","../pay/queryBankPage.action","menua_001_01")' href="javascript:">发薪人员维护表 </a>
								</li>																							
							</ul>
										
								
		                </div>
		            </div>
		        </div>
		</div>
		
		<!--layout-west----end-->
		
		<!--layout-center----start-->
		<div class="panel layout-panel layout-panel-center" style="width: 1060px; top: 120px; left: 227px;">
			<div title="" class="panel-body panel-body-noheader layout-body panel-noscroll" style="padding-bottom: 5px; padding-left: 5px; width: 1116px; padding-right: 5px; height: 723px; border-top: #e7e7e7 1px solid; padding-top: 0px;" abp="33"  data-options="region:'center'" fcount="1">
				<div class="easyui-tabs tabs-container" id="tabs" style="border-bottom: #e7e7e7 1px solid; border-left: #e7e7e7 1px solid; width: 1116px; height: 723px; border-right: #e7e7e7 1px solid;" abp="34" data-options="region:'center',fit:true,border:true,plain:true" fitted="true">
				<iframe src="" frameBorder="0" scrolling="auto" width="100%" height="100%" id="rightIframe" name="rightIframe"></iframe>
			</div>
			</div>
		</div>
	</div>
</body>