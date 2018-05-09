<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html>
<head>
	<meta charset="utf-8" />
	<title>KindEditor JSP</title>
	<link rel="stylesheet" href="<%=basePath%>templates/style/kindeditor/themes/default/default.css" />
	<link rel="stylesheet" href="<%=basePath%>templates/style/kindeditor/plugins/code/prettify.css" />
	<script charset="utf-8" src="<%=basePath%>templates/style/kindeditor/kindeditor-all.js"></script>
	<script charset="utf-8" src="<%=basePath%>templates/style/kindeditor/lang/zh-CN.js"></script>
	<script charset="utf-8" src="<%=basePath%>templates/style/kindeditor/plugins/code/prettify.js"></script>


	<link rel="stylesheet" href="<%=basePath%>templates/style/plugins/layui/css/layui.css" media="all">
	<link rel="stylesheet" href="<%=basePath%>templates/style/plugins/font-awesome/css/font-awesome.min.css" media="all">
	<link rel="stylesheet" href="<%=basePath%>templates/style/build/css/app.css" media="all">
	<link rel="stylesheet" href="<%=basePath%>templates/style/build/css/themes/green.css" media="all">

	<script>
		KindEditor.ready(function(K) {
			var editor1 = K.create('textarea[name="content1"]', {
			    width:'100%',
                themeType : 'simple',
				cssPath : '<%=basePath%>templates/style/kindeditor/plugins/code/prettify.css',
                uploadJson : '<%=basePath%>apiCommon/upload_json',
                fileManagerJson : '<%=basePath%>apiCommon/file_manager_json',
				allowFileManager : true,
				afterCreate : function() {
					var self = this;
					K.ctrl(document, 13, function() {
						self.sync();
						document.forms['example'].submit();
					});
					K.ctrl(self.edit.doc, 13, function() {
						self.sync();
						document.forms['example'].submit();
					});
				}
			});
			prettyPrint();
		});
	</script>
</head>
<body>
	<form name="example"  method="post" action="<%=basePath%>apiCommon/test">
		<textarea name="content1"  cols="100" rows="8" style="width:700px;height:200px;visibility:hidden;">
			${model}
		</textarea>
		<br />
		<input type="submit" name="button" value="提交内容" /> (提交快捷键: Ctrl + Enter)
		<br>
		<br>
		<br>


		<input type="button" name="getHtml" value="取得HTML" />
		<input type="button" name="isEmpty" value="判断是否为空" />
		<input type="button" name="getText" value="取得文本(包含img,embed)" />
		<input type="button" name="selectedHtml" value="取得选中HTML" />
		<br />
		<br />
		<input type="button" name="setHtml" value="设置HTML" />
		<input type="button" name="setText" value="设置文本" />
		<input type="button" name="insertHtml" value="插入HTML" />
		<input type="button" name="appendHtml" value="添加HTML" />
		<input type="button" name="clear" value="清空内容" />
		<input type="reset" name="reset" value="Reset" />
	</form>
</body>

<script src="<%=basePath%>templates/style/plugins/layui/layui.js"></script>
<script src="<%=basePath%>admin/js/getGroup.js"></script>
<script>
    var editor;
    KindEditor.ready(function(K) {
        editor = K.create('textarea[name="content1"]', {
            allowFileManager : true
        });
        K('input[name=getHtml]').click(function(e) {
            alert(editor.html());
        });
        K('input[name=isEmpty]').click(function(e) {
            alert(editor.isEmpty());
        });
        K('input[name=getText]').click(function(e) {
            alert(editor.text());
        });
        K('input[name=selectedHtml]').click(function(e) {
            alert(editor.selectedHtml());
        });
        K('input[name=setHtml]').click(function(e) {
            editor.html('<h3>Hello KindEditor</h3>');
        });
        K('input[name=setText]').click(function(e) {
            editor.text('<h3>Hello KindEditor</h3>');
        });
        K('input[name=insertHtml]').click(function(e) {
            editor.insertHtml('<strong>插入HTML</strong>');
        });
        K('input[name=appendHtml]').click(function(e) {
            editor.appendHtml('<strong>添加HTML</strong>');
        });
        K('input[name=clear]').click(function(e) {
            editor.html('');
        });
    });
</script>
</html>
