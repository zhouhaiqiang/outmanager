<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>中国联通山西省分公司外包管理系统</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
		<link href="css/style.css" type="text/css" rel="stylesheet" />
		<link href="css/font.css" type="text/css" rel="stylesheet" />
		<!-- 引入头文件 -->
        <%@include file="../common/head.jsp" %>
        <!-- 引入标签文件 -->
		<%@include file="../common/tag.jsp" %>

		<script type="text/javascript">
			function checkNumber(value)
			{    
				var reg = /^\d+$/;
				return reg.test(value);     
			}    
			
			function submitForm()
			{
				
				var userId = $("#userId").val();
				if(userId == "")
				{
					$("#loginLabel").text("用户名为空!");	
					return false;
				}
				var password = $("#password").val();
				if(password==""){
					$("#loginLabel").text("密码为空!");	
					return false;
				}

				alert(111);
				$("#loginform").submit();
				return true;
			}
			
		</script>
		<style type="text/css">
body {
	margin: 0;
	height: 500px;
	font-size: 12px;
	color: #AD1218;
	FILTER: progid : DXImageTransform . Microsoft .
		Gradient(gradientType = 0, startColorStr = #DCDCDC, endColorStr =
		#ffffff);
}

.content {
	background-image: url(/ecmpmt/themes/base/images/login.gif);
	background-repeat: no-repeat;
	background-position: center center;
	height: 350px;
	width: 586px;
	position: absolute;
	top: 50%;
	left: 50%;
	margin: -175px 0 0 -293px;
}

.font_date {
	font-size: 24px;
	color: #000;
	font-family: "黑体";
	padding: 0 10px;
}
.font_title {
	font-size: 30px;
	color: #000;
	font-family: "黑体";
	padding: 0 10px;
}
</style>
	</head>
	<body>
		<div class="content">
					<div style="padding: 40px 0 0 130px;">
						<font class="font_title">外包管理系统</font>
					</div>
			<div style="width: 400px">
				<form id="loginform" method="post" action="/outmanager/user/auth" >
					<div style="padding: 20px 0 0 40px; line-height: 25px">
						
					</div>
					<div style="padding: 10px 0 0 40px; line-height: 25px">
						用&nbsp;户&nbsp;账&nbsp;号：
						<input type="text" name="userId" id="userId" value=""
							style="width: 200px" />
					</div>
					<div style="padding: 10px 0 0 40px; line-height: 25px">
						用&nbsp;户&nbsp;密&nbsp;码：
						<input name="password" type="password" id="password"
							style="width: 200px" value="" />
					</div>
					<div
						style="padding: 20px 0 0 102px; * padding: 20px 0 0 105px; _padding: 20px 0 0 105px; line-height: 25px">
						<input class="btn" type="button" name="button" id="button"
							value="登录"  style="margin-right: 20px" onclick="submitForm();" />
						<input class="btn" type="reset" name="button" id="button"
							value="取消" />
					</div>
					<div id="loginLabel"
						style="padding: 10px 0 0 40px; line-height: 25px; font-size: 12px;">
						${msg}&nbsp;
					</div>
				</form>
			</div>
			<div
				style="color: #999; font-size: 12px; margin-top: 25px; * margin-top: 15px; _margin-top: 30px; text-align: center">
				中国联通山西省分公司外包管理系统
			</div>
			<script type="text/javascript">
			//Update();
		</script>
		</div>
	</body>
</html>
