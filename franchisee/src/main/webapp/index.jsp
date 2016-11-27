<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";


	java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	java.util.Date currentTime = new java.util.Date();//得到当前系统时间
	String strdate = formatter.format(currentTime); //将日期时间格式化
	
	String ip = request.getHeader("x-forwarded-for");
	if (null == ip || 0 == ip.length() || "unknown".equalsIgnoreCase(ip)) {
		ip = request.getHeader("Proxy-Client-IP");
	}
	if (null == ip || 0 == ip.length() || "unknown".equalsIgnoreCase(ip)) {
		ip = request.getHeader("WL-Proxy-Client-IP");
	}
	if (null == ip || 0 == ip.length() || "unknown".equalsIgnoreCase(ip)) {
		ip = request.getHeader("X-Real-IP");
	}
	if (null == ip || 0 == ip.length() || "unknown".equalsIgnoreCase(ip)) {
		ip = request.getRemoteAddr();
	}

%>

<html lang="zh-CN">
    <head>
  <meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="description" content="这是一个 index 页面">
<meta name="keywords" content="index">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="renderer" content="webkit">
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css?v=1.0.0">
        <title>登录成功</title>
    </head>

    <body >
    	<div class="tpl-portlet-components">
    			
    		<div class="am-g" >
			  <div class="form-frame shadow-bg">
			    <h3 class="form-title">尊敬的 <span class="error-FontColor2"><shiro:principal property="nickName"/></span>，欢迎您！</h3>
			    <div class="form-layer1">
			      <div class="form-layer2">
			        <form autocomplete="off">
			          <div class="form-group">
			            <label class="form-control-label">
			              <span>登录账号</span>
			              <em>：</em>
			            </label>
			            <div class="form-control-wrap">
			              <div class="form-control-data"><shiro:principal property="userName"/></div>
			            </div>
			          </div>
			          <div class="form-group">
			            <label class="form-control-label">
			              <span>登录时间</span>
			              <em>：</em>
			            </label>
			            <div class="form-control-wrap">
			              <div class="form-control-data"><%=strdate %></div>
			            </div>
			          </div>
			          <div class="form-group">
			            <label class="form-control-label">
			              <span>登录IP</span>
			              <em>：</em>
			            </label>
			            <div class="form-control-wrap">
			              <div class="form-control-data"><%=ip%></div>
			            </div>
			          </div>
			        </form>
			    </div>
			  </div>
			</div>
    		</div>
    	</div>

   		<%-- <div class="layer">
			  <div class="form-frame shadow-bg">
			    <h3 class="form-title">尊敬的 <span class="error-FontColor2"><%=UserName %></span>，欢迎您！</h3>
			    <div class="form-layer1">
			      <div class="form-layer2">
			        <form autocomplete="off">
			          <div class="form-group">
			            <label class="form-control-label">
			              <span>登录账号</span>
			              <em>：</em>
			            </label>
			            <div class="form-control-wrap">
			              <div class="form-control-data"><%=LoginName %></div>
			            </div>
			          </div>
			          <div class="form-group">
			            <label class="form-control-label">
			              <span>登录时间</span>
			              <em>：</em>
			            </label>
			            <div class="form-control-wrap">
			              <div class="form-control-data"><%=strdate %></div>
			            </div>
			          </div>
			          <div class="form-group">
			            <label class="form-control-label">
			              <span>登录IP</span>
			              <em>：</em>
			            </label>
			            <div class="form-control-wrap">
			              <div class="form-control-data"><%=IP %></div>
			            </div>
			          </div>
			        </form>
			      </div>
			    </div>
			  </div>
			</div>
 --%>



</body>
</html>