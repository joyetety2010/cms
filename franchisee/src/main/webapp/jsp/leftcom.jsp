<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*, java.text.*"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
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
	<link rel="stylesheet"
	href="${pageContext.request.contextPath}/layer/skin/laypage.css">
</head>
<body>
	
      <div class="tpl-left-nav tpl-left-nav-hover">
            <div class="tpl-left-nav-list"  id="div01">
                <ul class="tpl-left-nav-menu">
                    <li class="tpl-left-nav-item">
                        <a href="index.html" class="nav-link active" id="index">
                            <i class="am-icon-home"></i>
                            <span>首页</span>
                        </a>
                    </li>

                    <li class="tpl-left-nav-item">
                        <a href="javascript:;" class="nav-link tpl-left-nav-link-list" id="list">
                            <i class="am-icon-list"></i>
                            <span>功能列表</span>
                            <i class="am-icon-angle-right tpl-left-nav-more-ico am-fr am-margin-right" id="p1"></i>
                        </a>
                        <ul class="tpl-left-nav-sub-menu" id="p2">
                            <li>
                            <shiro:hasPermission name="/admin/user/*">
                            	 <a href="javascript:checked('${pageContext.request.contextPath}/admin/user/list','user')" id="user">
                                    <i class="am-icon-angle-right"></i>
                                    <span>用户管理</span>
                                     <i class="am-icon-hand-pointer-o tpl-left-nav-content-ico am-fr am-margin-right"></i>
                                </a>
                            </shiro:hasPermission>
                            <shiro:hasPermission name="/admin/data/*">
                            	 <a href="table-images-list.html">
                                    <i class="am-icon-angle-right"></i>
                                    <span>数据管理</span>
                                     <i class="am-icon-hand-pointer-o tpl-left-nav-content-ico am-fr am-margin-right"></i>
                                    </a>
                            </shiro:hasPermission>
                            <shiro:hasPermission name="/admin/view/*">
                            	  <a href="form-news.html">
                                        <i class="am-icon-angle-right"></i>
                                        <span>店铺查看</span>
                                         <i class="am-icon-hand-pointer-o tpl-left-nav-content-ico am-fr am-margin-right"></i>
                                        </a>
                            	
                            </shiro:hasPermission>
                            </li>
                        </ul>
                    </li>

                    <li class="tpl-left-nav-item ">
                        <a href="javascript:checked('${pageContext.request.contextPath}/admin/editPswd','pswd')" class="nav-link tpl-left-nav-link-list" id="pswd">
                            <i class="am-icon-key"></i>
                            <span>修改密码</span>
                        </a>
                    </li>
                </ul>
            </div>
        </div>
          <script src="${pageContext.request.contextPath}/assets/js/jquery.min.js"></script>
          	<script src="${pageContext.request.contextPath}/layer/mobile/layer.js"></script>
	
</body>
<script type="text/javascript">
	 $(function(){
		/* var type = '${type}';
		if(type == 'user'){
			 $("#list").attr("class",'nav-link tpl-left-nav-link-list active');
			$("#index").attr("class",'nav-link');
			$("#p1").attr("class","am-icon-angle-right tpl-left-nav-more-ico am-fr am-margin-right tpl-left-nav-more-ico-rotate");
			$("#p2").attr("style","display:block");
			$("#user").attr("class","active"); 
			
		}else if(type == "pswd"){
			$("#index").attr("class",'nav-link');
			$("#p2").attr("style","display:block");
			$("#pswd").attr("class",'nav-link tpl-left-nav-link-list active');
		} */
		
		indexShow();
		fucListOpen();
		
		
	}); 
	 
	function checked(url,tar){
		removeAllCheck();
		onCheck(tar);
		window.open(url,'myFrameName');
	} 
	 
	
	function indexHide(){
		$("#index").attr("class",'nav-link');
	}
	
	function indexShow(){
		$("#index").attr('class','nav-link tpl-left-nav-link-list active');
	}
	
	function fucListOpen(){
		$("#p1").attr("class","am-icon-angle-right tpl-left-nav-more-ico am-fr am-margin-right tpl-left-nav-more-ico-rotate");
		$("#p2").attr("style","display:block");
	}
	
	function fucListClose(){
		$("#p1").attr("class","am-icon-angle-right tpl-left-nav-more-ico am-fr am-margin-right");
		$("#p2").attr("style","display:none");
	}
	
	function onCheck(tar){
		$("#"+tar).addClass('active');
	}
	
	function removeAllCheck(){
		$("#div01 a").removeClass('active'); 
	}

</script>
</html>