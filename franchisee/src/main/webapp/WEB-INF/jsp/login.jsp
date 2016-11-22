<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*, java.text.*"
	pageEncoding="UTF-8"%>
<!doctype html>
<html>

<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>Amaze UI Admin index Examples</title>
  <meta name="description" content="这是一个 index 页面">
  <meta name="keywords" content="index">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta name="renderer" content="webkit">
  <meta http-equiv="Cache-Control" content="no-siteapp" />
  <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/assets/i/favicon.png">
  <link rel="apple-touch-icon-precomposed" href="${pageContext.request.contextPath}/assets/i/app-icon72x72@2x.png">
  <meta name="apple-mobile-web-app-title" content="Amaze UI" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/amazeui.min.css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/admin.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/app.css">
  <link rel="stylesheet"
	href="${pageContext.request.contextPath}/layer/mobile/need/layer.css">	
  
</head>

<body data-type="login">



  <div class="am-g myapp-login">
	<div class="myapp-login-logo-block  tpl-login-max">
		<div class="myapp-login-logo-text">
			<div class="myapp-login-logo-text">
				Amaze UI<span> Login</span> <i class="am-icon-skyatlas"></i>
				
			</div>
		</div>

		<div class="login-font">
			<i>Log In </i> or <span> Sign Up</span>
		</div>
		<div class="am-u-sm-10 login-am-center">
			<form class="am-form" method="post" action="${pageContext.request.contextPath}/login">
				<fieldset>
					<div class="am-form-group">
						<input type="text" class="" id="doc-ipt-email-1" placeholder="请输入账户名" name="userName">
					</div>
					<div class="am-form-group">
						<input type="password" class="" id="doc-ipt-pwd-1" placeholder="请输入密码" name="passWord">
					</div>
					<p><button type="submit" class="am-btn am-btn-default">登录</button></p>
					
				</fieldset>
			</form>
		</div>
	</div>
</div>



  <script src="${pageContext.request.contextPath}/assets/js/jquery.min.js"></script>
  <script src="${pageContext.request.contextPath}/assets/js/amazeui.min.js"></script>
  <script src="${pageContext.request.contextPath}/assets/js/app.js"></script>
  <script src="${pageContext.request.contextPath}/layer/mobile/layer.js"></script>
</body>
<script >
$(function(){
	var error = "${emsg}";
	
	if(error !=null && error!=""){
		layer.open({
		    content: error
		    ,skin: 'msg'
		    ,time: 2 //2秒后自动关闭
		  });
	}
});

</script>


</html>