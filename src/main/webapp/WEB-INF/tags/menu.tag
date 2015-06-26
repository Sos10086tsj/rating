<%@tag pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/base/baselib.jspf" %>
<%--
<div class="easyui-accordion" data-options="fit:true,border:false">
<c:forEach items="${menus }" var="menu" varStatus="index">
	<div title="${menu.name }" <c:if test="${index.index == 0 }">data-options="selected:true"</c:if>>
		<c:forEach items="${menu.subMenu }" var="subMenu">
			<div><a href='${ctx }/${subMenu.url }'>${subMenu.name }</a></div>
		</c:forEach> 
	</div>
</c:forEach>
</div> --%>
<div class="easyui-panel" style="padding:5px">
	<ul class="easyui-tree">
		<c:forEach items="${menus }" var="menu">
			<li>
				<span>${menu.name }</span>
				<ul>
					<c:forEach items="${menu.subMenu }" var="subMenu">
						<li ><span><a href="${ctx }/${subMenu.url }">${subMenu.name }</a></span></li>
					</c:forEach>
				</ul>
			</li>
		</c:forEach>
	</ul>
</div>