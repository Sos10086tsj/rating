<%@tag pageEncoding="UTF-8"%>
<%@ attribute name="uploadFunc" required="true" type="java.lang.String" %>
<%@ attribute name="filedName" required="true" type="java.lang.String" %>
<div>
	<form id="tag_file_upload_form" method="post" enctype="multipart/form-data" >
		<input class="easyui-filebox" name="${filedName}" data-options="prompt:'选择文件'" id="tag_file_box" style="height:30px;">
    	<a href="#" class="easyui-linkbutton" style="height:30px;" onclick="${uploadFunc}">上传</a>
    </form>
</div>