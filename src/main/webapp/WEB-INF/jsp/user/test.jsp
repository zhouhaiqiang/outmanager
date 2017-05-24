








<link rel="stylesheet" type="text/css" href="/outmanager/css/linkey/bpm/easyui/themes/red/easyui.css" />
<link rel="stylesheet" type="text/css" href="/outmanager/css/linkey/bpm/easyui/themes/red/icon.css" />
<link rel="stylesheet" type="text/css" href="/outmanager/talkweb/bpm_red/css/bpm_style.css" />
<link rel="stylesheet" type="text/css" href="/outmanager/talkweb/bpm_new/style.css" /> 
<link rel="stylesheet" type="text/css" href="/outmanager/talkweb/bpm_page/css/main.css" />  
<title>中国联通山西省分公司薪酬统发系统</title>
<script language="javascript" type="text/javascript" src="/outmanager/js/jquery/base/jquery-1.4.2.min.js"></script>
<script type="text/javascript">
$(function(){
	loadRightPage("我的待办","../pay/todoList.action","menua_001");
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
<input type="hidden" id="path" value="/outmanager" />
<div data-options="region:'north',split:false" border="false" style="height:120px;overflow:hidden">
<!--头部 begin-->
<div id="top">
<div id="logo"><img src="/outmanager/talkweb/bpm_new/img/logo.png" width="101" height="56" /></div>
<div id="title">中国联通山西薪酬统发系统</div>
<div class="bg">
<div class="info" id="top_div_info">
		<ul>
			<li>
				<span id='UserName'></span>
			<img src="/outmanager/talkweb/bpm_new/img/man.png" width="12" height="15" /><span id='UserName'>杨伟</span>
			</li>

		</ul>
	</div>
</div>
</div>
<!--导航 begin-->
<div class="nav">
       <ul id="topnav">
       		<li>
           		<a id="a_1" onclick="getTree(1)" href="javascript:void(0);">薪酬统发系统</a>
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
								<a href='javascript:' id='menua_000' onclick='changemenucolor("menua_000");loadRightPage("我的待办","../pay/todoList.action","menua_001");showChild(id);'  href="javascript:">我的待办</a>
							</h4>		                
		                	<h4>
								<a href='javascript:' id='menua_001' onclick='changemenucolor("menua_001");loadRightPage("银行信息","../pay/queryBankPage.action","menua_001");showChild(id);'  href="javascript:">银行信息</a>
							</h4>
							<ul class="tag-list" style="display:none" id="menua_001_1">
								<li class="menuli" id="menu001" onclick="changClass('menu001')">
									<a id='menua_001_01' onclick='loacalPage("银行信息维护","../pay/queryBankPage.action","menua_001_01")' href="javascript:">银行信息维护</a>
								</li>
<!-- 								<li class="menuli" id="menu002" onclick="changClass('menu002')">
									<a id='menua_001_02' onclick='loacalPage("报盘文件设置","http://www.baidu.com","menua_001_02")' href="javascript:">报盘文件设置</a>
								</li> -->
							</ul>
							<h4>
								<a href='javascript:'id="menua_002"  onclick='changemenucolor("menua_002");showChild(id);'>工资总额申报</a>
							</h4>
							<ul class="tag-list" style="display:none" id="menua_002_1">
								
											
																				
										
									
										<li class="menuli" id="menu005" onclick="change(this)">
											<a id='menua_002_03' onclick='loacalPage("省分薪酬管理员审核","../pay/queryDeclarePage.action?link_id=3","menua_002_03")' href="javascript:">省分薪酬管理员审核</a>
										</li>									
											
																																									
								
									<li class="menuli" id="menu006" onclick="change(this)">
										<!-- <a id='menua_002_04' onclick='loacalPage("日志查询","../pay/queryDeclarePage.action?link_id=4","menua_002_04")' href="javascript:">日志查询</a> -->
										<a id='menua_002_04' onclick='loacalPage("日志查询","../pay/queryDeclareLogPage.action?link_id=4","menua_002_04")' href="javascript:">日志查询</a>
									</li>
							</ul>
							<h4>
								<a href='javascript:' id='menua_003' onclick='changemenucolor("menua_003");loadRightPage("工资表导入","../pay/paymentQuery.action","menua_003");showChild(id);'>工资表导入</a>
							</h4>
							<ul class="tag-list" style="display:none" id="menua_003_1">
									<li class="menuli" id="menu007" onclick="change(this)">
										<a id='menua_003_01' onclick='loacalPage("合同制","../pay/queryCheckData.action?type=1&payReport.type=1&payReport.year=2017&payReport.month=5","menua_003_01")'>合同制</a>
									</li>
									
										
											<li class="menuli" id="menu007" onclick="change(this)">
												<a id='menua_003_01' onclick='loacalPage("合同制数据查询","../pay/queryContractCheckData.action?type=1&payReport.type=1&payReport.year=2017&payReport.month=5","menua_003_05")'>合同制数据查询</a>
											</li>
										
									
										<li class="menuli" id="menu008" onclick="change(this)">
										<a id='menua_003_02' onclick='loacalPage("劳务派遣制","../pay/queryCheckData.action?type=2&payReport.type=2&payReport.year=2017&payReport.month=5","menua_003_02")'>劳务派遣</a>
									</li>
									</li>
										<li class="menuli" id="menu009" onclick="change(this)">
										<a id='menua_003_03' onclick='loacalPage("紧密型","../pay/queryCheckData.action?type=3&payReport.type=3&payReport.year=2017&payReport.month=5","menua_003_03")'>紧密型</a>
									</li>
									</li>
										<li class="menuli" id="menu0010" onclick="change(this)">
										<a id='menua_003_04' onclick='loacalPage("业务外包人员","../pay/queryCheckData.action?type=4&payReport.type=4&payReport.year=2017&payReport.month=5","menua_003_04")'>业务外包人员</a>
									</li>
								</ul>
							<h4>
								<a href='javascript:' id='menua_004' onclick='changemenucolor("menua_004");showChild(id);'>流程审批 </a>
							</h4>
							<ul class="tag-list" style="display:none" id="menua_004_1">
							
								
																
								
								
									<li class="menuli" id="menu0013" onclick="change(this)">
										<a id='menua_004_03' onclick='loacalPage("省分薪酬管理员审核","../pay/queryApprovalPage.action?node_id=0003","menua_004_03")' href="javascript:">省分薪酬管理员审核</a>
									</li>								
								
								
								
							
									<li class="menuli" id="menu0016" onclick="change(this)">
										<!-- <a id='menua_004_06' onclick='loacalPage("日志查询","../pay/queryApprovalPage.action?node_id=0006","menua_004_06")' href="javascript:">日志查询</a> -->
										<a id='menua_004_06' onclick='loacalPage("日志查询","../pay/queryApprovalLogPage.action?node_id=0006","menua_004_06")' href="javascript:">日志查询</a>
									</li>
									<li class="menuli" id="menu0017" onclick="change(this)">
										<a id='menua_004_07' onclick='loacalPage("流程统计","../pay/queryApprovalSummary.action","menua_004_07")' href="javascript:">流程统计</a>
									</li>									
							</ul>							
								
									
										<h4>
											<a href='javascript:' id='menua_005' onclick='changemenucolor("menua_005");loadRightPage("生成报盘文件","../pay/queryBankPage4offer.action","menua_005")'>生成报盘文件</a>
										</h4>
										
								
							
								
							
		                </div>
		            </div>
		        </div>
		</div>
		
		<!--layout-west----end-->
		
		<!--layout-center----start-->
		<div class="panel layout-panel layout-panel-center" style="width: 1126px; top: 120px; left: 227px;">
			<div title="" class="panel-body panel-body-noheader layout-body panel-noscroll" style="padding-bottom: 5px; padding-left: 5px; width: 1116px; padding-right: 5px; height: 723px; border-top: #e7e7e7 1px solid; padding-top: 0px;" abp="33"  data-options="region:'center'" fcount="1">
				<div class="easyui-tabs tabs-container" id="tabs" style="border-bottom: #e7e7e7 1px solid; border-left: #e7e7e7 1px solid; width: 1116px; height: 723px; border-right: #e7e7e7 1px solid;" abp="34" data-options="region:'center',fit:true,border:true,plain:true" fitted="true">
				<iframe src="" frameBorder="0" scrolling="no" width="100%" height="100%" id="rightIframe" name="rightIframe"></iframe>
			</div>
			</div>
		</div>
	</div>
</body>