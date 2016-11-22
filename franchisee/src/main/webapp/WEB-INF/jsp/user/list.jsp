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
<%-- <link rel="stylesheet"
	href="${pageContext.request.contextPath}/layer/mobile/need/layer.css">	
	<link rel="stylesheet"
	href="${pageContext.request.contextPath}/layer/skin/laypage.css"> --%>
</head>

<body data-type="generalComponents">


<%-- 	<header class="am-topbar am-topbar-inverse admin-header">
		<jsp:include page="/jsp/headcom.jsp"></jsp:include>
	</header>
	<div class="tpl-page-container tpl-page-header-fixed">
		<jsp:include page="/jsp/leftcom.jsp"></jsp:include>
	</div> --%>

		
	
		<div class="tpl-portlet-components">
			<ol class="am-breadcrumb">
                <li><a href="#" class="am-icon-home">首页</a></li>
                <li>功能列表</li>
                <li class="am-active">用户管理</li>
            </ol>
		
			<div class="tpl-block">
				<div class="am-g">
					<div class="am-u-sm-12 am-u-md-6">
						<div class="am-btn-toolbar">
							<div class="am-btn-group am-btn-group-xs" onclick="addUser();">
								<button type="button"
									class="am-btn am-btn-default am-btn-success" >
									<span class="am-icon-plus"></span> 添加用户
								</button>
							</div>
						</div>
					</div>
					<div class="am-u-sm-12 am-u-md-3">
						<div class="am-input-group am-input-group-sm">
							<input type="text" class="am-form-field" id="contxt" placeholder="请输入用户名"> <span
								class="am-input-group-btn">
								<button 
									class="am-btn  am-btn-default am-btn-success tpl-am-btn-success am-icon-search"
									type="button" onclick="search(1);"></button>
							</span>
						</div>
					</div>
				</div>
				<div class="am-g" >
					<div class="am-u-sm-12" id="j-list">
						<form class="am-form">
							<table
								class="am-table am-table-striped am-table-hover table-main">
								<thead>
									<tr>
										<th class="table-id">序号</th>
										<th class="table-title">用户名</th>
										<th class="table-type">角色</th>
										<th class="table-author">是否启用</th>
										<th class="table-date am-hide-sm-only">创建时间</th>
										<th class="table-set am-hide-sm-only">操作</th>
										
									</tr>
								</thead>
								<tbody >

									<c:forEach items="${userList}" var="info" varStatus="status">
										<tr>
											<input type="hidden" value="${info.user.id}" id="user${status.index + 1}"></input>
											<input type="hidden" value="${info.user.status}" id="status${status.index + 1}"></input>
											<td>${(page.currPage-1)*(page.offset) + (status.index+1)}</td>
											<td>${info.user.userName}</td>
											<td>
											<input type="checkbox" name="rbox${info.user.id }" <c:if test="${fn:contains(info.roles,'1')}"> checked="checked"</c:if> onclick="changeRole(${info.user.id},1,this);">管理员 &nbsp
											<input type="checkbox" name="rbox${info.user.id }" <c:if test="${fn:contains(info.roles,'2')}"> checked="checked"</c:if> onclick="changeRole(${info.user.id},2,this);">数据录入员 &nbsp
											<input type="checkbox" name="rbox${info.user.id }" <c:if test="${fn:contains(info.roles,'3')}"> checked="checked"</c:if> onclick="changeRole(${info.user.id},3,this);">查看者 
												 </td>
											<td>
												
												<c:choose>
												<c:when test="${info.user.userName != userName}">
													<c:choose>
																	<c:when test="${info.user.status ==1}">
																	
																		<div class="tpl-switch" >
																			<input type="checkbox"
																				class="ios-switch bigswitch tpl-switch-btn" checked value="${info.user.id}" disabled/>
																			<div class="tpl-switch-btn-view" >
																				<div></div>
																			</div>
																		</div>
																	</c:when>
																	<c:when test="${info.user.status ==0}">
																		<div class="tpl-switch" >
																			<input type="checkbox"
																				class="ios-switch bigswitch tpl-switch-btn" value="${info.user.id}" disabled/>
																			<div class="tpl-switch-btn-view">
																				<div></div>
																			</div>
																		</div>
																	</c:when>
																	</c:choose>
																	</c:when>
																</c:choose>
																
											</td>
											
											
											
											
											<td class="am-hide-sm-only"><fmt:formatDate
													value="${info.user.createTime}"
													pattern="yyyy-MM-dd HH:mm:ss" /></td>
											<td><c:choose>
											
													<c:when test="${info.user.userName != userName}">
														<input type="button" class="am-btn am-btn-default am-btn-xs am-hide-sm-only" value="删除" onclick="alertLayer(${info.user.id});"/>
														
													</c:when>

												</c:choose></td>
										</tr>
									</c:forEach>


								</tbody>
							</table>
						</form>
						<div id="biuuu_city" class="am-fr"></div>
					</div>
					
	
				</div>
			</div>
		</div>



	<script
		src="${pageContext.request.contextPath}/assets/js/jquery.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/assets/js/amazeui.min.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/app.js"></script>
 	<script src="${pageContext.request.contextPath}/layer/laypage.js"></script> 
</body>
<script type="text/javascript">

$(function(){
	initPage();
});

function initPage(){
	var pages = Math.ceil("${page.total}"/"${page.offset}"); //得到总页数
	var currPage = "${page.currPage}";
	//调用分页
	 laypage({
	    cont: 'biuuu_city',
	    pages: pages,
	    curr:currPage,
	    skin: '#429842',
	    jump: function(obj,frist){
	    	if(!frist){
	    		search(obj['curr']); 
	    	}
	    }
	}) 
}

function changeRole(uId,roleId,obj){
	
	var tmp;
	if($(obj).prop("checked") == true){
		tmp=1;
	}else
		tmp=0;
	
	
	$.ajax({
         type: "POST",
         url:"${pageContext.request.contextPath}/admin/user/changeRole",
         data: {
       	  "userId":uId,
       	  "roleId":roleId,
       	  "check":tmp
         },
         dataType: "json",
         success: function(data){
       	  if(!data.success){
       		$(obj).prop("checked", true);
       		parent.layer.open({
 			    content: data.message
 			    ,skin: 'msg'
 			    ,time: 2 //2秒后自动关闭
 			  });
       	  }
     },
     error:function(data){
   	alert("请求失败！");
   	  }
	  });
}

function search(curr){
	var txt = $("#contxt").val();
	$.ajax({
         type: "GET",
         url:"${pageContext.request.contextPath}/admin/user/list",
         data: {
       	  "txt":txt,
       	  "search":"search",
       	  "curr":curr,
       	  "nOffset":10
         },
         dataType: "text",
         success: function(text){
        	 $("#j-list").html(text);
     },
     error:function(data){
    	 parent.layer.open({
			    content:"请求失败！"
			    ,skin: 'msg'
			    ,time: 2 //2秒后自动关闭
			  });
   	  }
	  });
	
}

function alertLayer(uId){
/* 	 layer.confirm('您确定要删除此用户？', {
		  btn: ['确认','取消'] //按钮
		}, function(){
			 $.ajax({url:"${pageContext.request.contextPath}/admin/user/delete",
    			 type:"POST",
    			 data:{"userId":uId},
    			 dataType: "json",
    			 success:
    			  function(data){
    		 		 if(data.success){
    		 			 location.reload();
    		 		}else{
    		 			layer.open({
    		 			    content: data.message
    		 			    ,skin: 'msg'
    		 			    ,time: 2 //2秒后自动关闭
    		 			  });
    		 		} 
    			  },
    			  error:function(data){
    				  layer.open({
    		 			    content:"请求失败！"
    		 			    ,skin: 'msg'
    		 			    ,time: 2 //2秒后自动关闭
    		 			  });
    		   	  }
    }
    			 );
		}); */
	 
	
	  parent.layer.open({
		    content: '您确定要删除此用户？'
		    ,btn: ['确认', '取消']
		    ,yes: function(index){
		    	parent.layer.close(index);
		    	 $.ajax({url:"${pageContext.request.contextPath}/admin/user/delete",
		    			 type:"POST",
		    			 data:{"userId":uId},
		    			 dataType: "json",
		    			 success:
		    			  function(data){
		    		 		 if(data.success){
		    		 		location.reload();
		    		 		}else{
		    		 			parent.layer.open({
		    		 			    content: data.message
		    		 			    ,skin: 'msg'
		    		 			    ,time: 2 //2秒后自动关闭
		    		 			  });
		    		 		} 
		    			  },
		    			  error:function(data){
		    				  parent.layer.open({
		    		 			    content:"请求失败！"
		    		 			    ,skin: 'msg'
		    		 			    ,time: 2 //2秒后自动关闭
		    		 			  });
		    		   	  }
		    }
		    			 );
		    	
		    	
		    	
		     
		      
		    }
		  }); 
}

function addUser(){
	location.href="${pageContext.request.contextPath}/admin/user/add";
}
		
</script>

</html>