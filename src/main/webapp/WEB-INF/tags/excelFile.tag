<%@tag pageEncoding="UTF-8"%>
<%@ attribute name="url" required="true" type="java.lang.String" %>
<%@ attribute name="text" required="true" type="java.lang.String" %>
<div>
	<a href="${url }" style="padding-bottom:2px;" title="${text }">
		<img src="${ctx }/resources/images/excel_logo.jpg" width = "20px" height="20px" style="float:left;"/><span>${text }</span>
	</a>
	
</div>