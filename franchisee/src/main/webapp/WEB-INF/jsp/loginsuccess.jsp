<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.*, java.text.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
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
<link rel="icon" type="image/png"
	href="${pageContext.request.contextPath}/assets/i/favicon.png">
<link rel="apple-touch-icon-precomposed"
	href="${pageContext.request.contextPath}/assets/i/app-icon72x72@2x.png">
<meta name="apple-mobile-web-app-title" content="Amaze UI" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/amazeui.min.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/admin.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/app.css">

</head>
<body data-type="index">


	<header class="am-topbar am-topbar-inverse admin-header">
		<jsp:include page="/jsp/headcom.jsp"></jsp:include>
	</header>

	<div class="tpl-page-container tpl-page-header-fixed">
		<jsp:include page="/jsp/leftcom.jsp"></jsp:include>
	
		
	</div>
	<div class="tpl-content-wrapper "> 
		<iframe id="myFrameId" name="myFrameName"
				 onload="changeFrameHeight();"></iframe>
				 </div>
		
		
		

	<script
		src="${pageContext.request.contextPath}/assets/js/jquery.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/assets/js/amazeui.min.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/iscroll.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/app.js"></script>
</body>

<script type="text/javascript">
	/* function jump(url){
			$("#j-content").attr("src",url);
	} */
	function changeFrameHeight(){
	    var ifm= document.getElementById("myFrameId"); 
	    ifm.height=document.documentElement.clientHeight;
	    /* alert($(".tpl-content-wrapper").width()) */
	    ifm.width=$(".tpl-content-wrapper").width();
	}
	window.onresize=function(){  
	     changeFrameHeight();  
	}  
	
</script>

</html>