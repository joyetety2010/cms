<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.*, java.text.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/layer/mobile/need/layer.css">

<%-- <link rel="stylesheet"
	href="${pageContext.request.contextPath}/zui/css/zui.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/zui/css/zui.lite.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/zui/css/zui-theme.min.css"> --%>
</head>
<body data-type="generalComponents">


<%-- 	<header class="am-topbar am-topbar-inverse admin-header">
		<jsp:include page="/jsp/headcom.jsp"></jsp:include>
	</header>







	<div class="tpl-page-container tpl-page-header-fixed">
		<jsp:include page="/jsp/leftcom.jsp"></jsp:include>
	</div>
 --%>



<div class="tpl-portlet-components">
		<ol class="am-breadcrumb">
                <li><a href="#" class="am-icon-home">首页</a></li>
                <li>功能列表</li>
                <li><a href="${pageContext.request.contextPath}/admin/user/list">用户管理</a></li>
                <li class="am-active">添加用户</li>
            </ol>
	
		
			<div class="portlet-title">
				<div class="caption font-green bold">添加用户</div>
			</div>
			<div class="tpl-block ">
				<div class="am-g tpl-amazeui-form">

						<form action="${pageContext.request.contextPath}/admin/user/add" class="am-form" id="doc-vld-msg" method="post">
							<fieldset>
								<div class="am-form-group">
									<label for="doc-vld-name-2-1">账号名：</label> <input type="text" name="userName" class="js-ajax-validate"
										id="doc-vld-name-2-1" minlength="3" pattern="^[A-Za-z0-9]+$"
										placeholder="输入账号名（至少 3 个字符）" maxlength="100" required />
								</div>

								<div class="am-form-group">
									<label for="doc-vld-pwd-1">密码：</label> <input
										type="password" id="doc-vld-pwd-1" minlength="6" name="passWord"
										maxlength="50" data-validation-message="请输入不少于6位的密码"
										placeholder="请输入不少于6位的密码" required />
								</div>

								<div class="am-form-group">
									<label for="doc-vld-url-2-1">确认密码：</label> <input
										type="password" id="doc-vld-url-2-1" minlength="6"
										maxlength="50" data-validation-message="请与上面输入的值一致" data-equal-to="#doc-vld-pwd-1"
										placeholder="请与上面输入的值一致" required />
								</div>

								<div class="am-form-group">
									<label for="doc-vld-age-2-1">用户名：</label> <input type="text" name="nickName"
										class="" id="doc-vld-age-2-1" placeholder="输入你的用户名"
										maxlength="100" data-validation-message="请输入用户名" required />
								</div>

								<div class="am-form-group">
									<label class="am-form-label">角色：</label> <label
										class="am-checkbox-inline"> <input type="checkbox"
										value="1" name="docVlCb" minchecked="1" maxchecked="3"
										required> 管理员
									</label> <label class="am-checkbox-inline"> <input
										type="checkbox" value="2" name="docVlCb"> 数据录入员
									</label> <label class="am-checkbox-inline"> <input
										type="checkbox" value="3" name="docVlCb"> 查看者
									</label>
								</div>


								<button class="am-btn am-btn-secondary" type="submit">提交</button>
							</fieldset>
						</form>

					</div>
				</div>
			</div>













	<script
		src="${pageContext.request.contextPath}/assets/js/jquery.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/assets/js/amazeui.min.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/app.js"></script>
	<script src="${pageContext.request.contextPath}/layer/mobile/layer.js"></script>
</body>
<script type="text/javascript">
$(function() {
	  $('#doc-vld-msg').validator({
	    onValid: function(validity) {
	      $(validity.field).closest('.am-form-group').find('.am-alert').hide();
	    },

	    onInValid: function(validity) {
	      var $field = $(validity.field);
	      var $group = $field.closest('.am-form-group');
	      var $alert = $group.find('.am-alert');
	      
	      
	      // 使用自定义的提示信息 或 插件内置的提示信息
	      var msg = $field.data('validationMessage') || this.getValidationMessage(validity);

	      if (!$alert.length) {
	        $alert = $('<div class="am-alert am-alert-danger"></div>').hide().
	          appendTo($group);
	      }
	      $alert.html(msg).show();
	    },
	    validate: function(validity) {
	    	if ($(validity.field).is('.js-ajax-validate')) {
	    		var v = $(validity.field).val();
	            // 异步操作必须返回 Deferred 对象
	            return $.ajax({
	              url: "${pageContext.request.contextPath}/admin/user/checkUser",
	              data:{"userName":v},
	              type:"post",
	              // cache: false, 实际使用中请禁用缓存
	              dataType: 'json'
	            }).then(function(data) {
	            	
	            	if(data.success){
	            		validity.valid = false;
	            	var $field = $(validity.field);
	            	var $group = $field.closest('.am-form-group');
	          	    var $alert = $group.find('.am-alert');
	          	      // 使用自定义的提示信息 或 插件内置的提示信息
	          	      var msg = "该用户已存在！";
						
	          	      if (!$alert.length) {
	          	        $alert = $('<div class="am-alert am-alert-danger"></div>').hide().
	          	          appendTo($group);
	          	        
	          	      }
	          	      $alert.html(msg).show();
	            	}
	              return validity;
	            }, function() {
	              return validity;
	            });
	          }
	    } 
	    
	  });
	});
	
</script>
</html>