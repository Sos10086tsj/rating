<%@tag pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/base/baselib.jspf" %>
<div class="easyui-panel" style="padding:5px">
	<ul class="easyui-tree" id="js_menu_tree">
		<c:forEach items="${menus }" var="menu">
			<li>
				<span>${menu.name }</span>
				<ul>
					<c:forEach items="${menu.subMenu }" var="subMenu">
						<li data-options="attributes:{'url':'${ctx }/${subMenu.url }','title':'${subMenu.name }'}">${subMenu.name }</li>
					</c:forEach>
				</ul>
			</li>
		</c:forEach>
	</ul>
</div>