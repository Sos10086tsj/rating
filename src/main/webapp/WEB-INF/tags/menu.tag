<%@tag pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/base/baselib.jspf" %>
<div id='cssmenu'>
		<ul>
			<li class="active"><a href='#'>首页</a></li>
			<c:forEach items="${menus }" var="menu">
				<li <c:if test="${not empty menu.subMenu}" >class='has-sub'</c:if>>
					<a href='#'>${menu.name } ></a>
					<c:forEach items="${menu.subMenu }" var="subMenu">
						<ul>
							<li><a href='${ctx }/${subMenu.url }'>${subMenu.name }</a></li>
						</ul>
					</c:forEach>
				</li>
			</c:forEach>
		</ul>
</div>