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


 --%>



<%-- 
	<div class="tpl-page-container tpl-page-header-fixed">
		<jsp:include page="/jsp/leftcom.jsp"></jsp:include>
	</div>

 --%>


	<div class="tpl-portlet-components">
		<ol class="am-breadcrumb">
                <li><a href="#" class="am-icon-home">首页</a></li>
                <li class="am-active">修改密码</li>
            </ol>
	
	
			<div class="portlet-title">
				<div class="caption font-green bold">修改密码</div>
			</div>
			<div class="tpl-block ">

				<div class="am-g tpl-amazeui-form">
					<div class="am-u-sm-12 am-u-md-9">

						<form action="${pageContext.request.contextPath}/admin/editPswd" class="am-form" id="doc-vld-msg" method="post">
							<fieldset>
								
								<div class="am-form-group">
									<label for="doc-vld-pwd-1">原始密码：</label> <input
										type="password" id="doc-vld-pwd-0" minlength="6"  class="js-ajax-validate" name="oldPsd"
										maxlength="50" 
										placeholder="请输入原始密码" required />
								</div>

								<div class="am-form-group">
									<label for="doc-vld-pwd-1">新密码：</label> <input
										type="password" id="doc-vld-pwd-1" minlength="6" name="passWord"
										maxlength="50" data-validation-message="请输入不少于6位的密码"
										placeholder="请输入不少于6位的密码" required />
								</div>

								<div class="am-form-group">
									<label for="doc-vld-url-2-1">确认新密码：</label> <input
										type="password" id="doc-vld-url-2-1" minlength="6"
										maxlength="50" data-validation-message="请与上面输入的值一致" data-equal-to="#doc-vld-pwd-1"
										placeholder="请与上面输入的值一致" required />
								</div>

								<button class="am-btn am-btn-secondary" type="submit">保存修改</button>
							</fieldset>
						</form>

					</div>
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
	              url: "${pageContext.request.contextPath}/admin/checkPswd",
	              data:{"passWord":v},
	              type:"post",
	              // cache: false, 实际使用中请禁用缓存
	              dataType: 'json'
	            }).then(function(data) {
	            	
	            	if(!data.success){
	            		validity.valid = false;
	            	var $field = $(validity.field);
	            	var $group = $field.closest('.am-form-group');
	          	    var $alert = $group.find('.am-alert');
	          	      // 使用自定义的提示信息 或 插件内置的提示信息
	          	      var msg = "你输入的原始密码不正确！";
						
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