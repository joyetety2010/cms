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
	href="${pageContext.request.contextPath}/layer/skin/laypage.css">
<script type="text/javascript">
$(function(){
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
});
</script>
</head>

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
								<tbody>

									<c:forEach items="${userList}" var="info" varStatus="status">
										<tr>
									<%-- 		<input type="hidden" value="${info.user.id}" id="user${status.index + 1}"></input>
											<input type="hidden" value="${info.user.status}" id="status${status.index + 1}"></input> --%>
											<td>${(page.currPage-1)*(page.offset) + (status.index+1)}</td>
											<td>${info.user.userName}</td>
											<td>
											<input type="checkbox" <c:if test="${fn:contains(info.roles,'1')}"> checked="checked"</c:if> onclick="changeRole(${info.user.id},1,this);">管理员 &nbsp
											<input type="checkbox" <c:if test="${fn:contains(info.roles,'2')}"> checked="checked"</c:if> onclick="changeRole(${info.user.id},2,this);">数据录入员 &nbsp
											<input type="checkbox" <c:if test="${fn:contains(info.roles,'3')}"> checked="checked"</c:if> onclick="changeRole(${info.user.id},3,this);">查看者 
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
						
<script type="text/javascript">
	$(function(){
		
		 $('.tpl-switch').find('.tpl-switch-btn-view').on('click', function() {
	            $(this).prev('.tpl-switch-btn').prop("checked", function() {
	            	var status;
	                    if ($(this).is(':checked')) {
	                    	status = 0;
	                    	var curWwwPath = window.document.location.href;
	                        //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
	                        var pathName = window.document.location.pathname;
	                        var pos = curWwwPath.indexOf(pathName);
	                        //获取主机地址，如： http://localhost:8083
	                        var localhostPaht = curWwwPath.substring(0, pos);
	                        var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);  
	                        var url = localhostPaht+projectName+"/admin/user/update";
	                        $.ajax({
	               	          type: "POST",
	               	          url:url ,
	               	          data: {
	               	        	  "userId":$(this).val(),
	               	        	  "status":status  
	               	          },
	               	          dataType: "json",
	               	          success: function(data){
	               	        	  if(data.message!="成功"){
	               	        		 alert("修改状态失败！"); 
	               	        	  }
	               	      },
	               	      error:function(data){
	               	    	alert("请求失败！");
	               	    	  }
	               		  });
	                        return false;
	                    } else {
	                    	status =1;
	                    	var curWwwPath = window.document.location.href;
	                        //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
	                        var pathName = window.document.location.pathname;
	                        var pos = curWwwPath.indexOf(pathName);
	                        //获取主机地址，如： http://localhost:8083
	                        var localhostPaht = curWwwPath.substring(0, pos);
	                        var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);  
	                        var url = localhostPaht+projectName+"/admin/user/update";
	                        $.ajax({
	               	          type: "POST",
	               	          url:url ,
	               	          data: {
	               	        	  "userId":$(this).val(),
	               	        	  "status":status  
	               	          },
	               	          dataType: "json",
	               	          success: function(data){
	               	        	  if(data.message!="成功"){
	               	        		 alert("修改状态失败！"); 
	               	        	  }
	               	      },
	               	      error:function(data){
	               	    	alert("请求失败！");
	               	    	  }
	               		  });
	                        return true;
	                    }
	                    /**/
	                        
	                })
	             
	                // console.log('123123123')
	        
	})
	

	
	})
</script>
</html>