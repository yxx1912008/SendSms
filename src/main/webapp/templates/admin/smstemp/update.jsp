<%--
  Created by IntelliJ IDEA.
  User: benhailong
  Date: 2018/2/7
  Time: 下午3:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>添加收信人</title>
    <link rel="stylesheet" href="<%=basePath%>templates/style/plugins/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="<%=basePath%>templates/style/build/css/doc.css" media="all">
    <script src="<%=basePath%>templates/style/plugins/layui/layui.js"></script>
    <script src="<%=basePath%>templates/style/plugins/layui/jquery-3.3.1.min.js"></script>
</head>
<body>

<div class="kit-doc">
    <form class="layui-form layui-form-pane" action="" lay-filter="reflash">
        <input name="tempId" lay-verify="required" value="${kitModel.tempId}" autocomplete="off"
               class="layui-input" type="hidden">

        <fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
            <legend>模板内容预览</legend>
        </fieldset>

        <blockquote class="layui-elem-quote layui-quote-nm">
            ${kitModel.content}
        </blockquote>

        <%--批量输入手机号码--%>
        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">手机号</label>
            <div class="layui-input-block">
                <textarea lay-verify="required" placeholder="请输入手机号,多个手机号请用英文逗号隔开" class="layui-textarea"
                          name="phones"></textarea>
            </div>
        </div>


        <%--批量输入短信变量--%>
   <!--      <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">短信变量</label>
            <div class="layui-input-block">
                <textarea lay-verify="required" placeholder="请输入短信变量,多个变量请按顺序用英文逗号隔开" class="layui-textarea"
                          name="values"></textarea>
            </div>
        </div> -->


	
	<div class="layui-form-item layui-form-text">
	    <input name="valuesCount" lay-verify="required" value="${kitModel.placeholderNum}" autocomplete="off"
               class="layui-input" type="hidden">
            <label class="layui-form-label">短信变量</label>
		<c:forEach  begin="0" end="${kitModel.placeholderNum-1}" step="1" varStatus="status" >
 			<label class="layui-form-label">变量:${status.index+1}</label>
 			<input type="text" name="values[${status.index}]"  lay-verify="required" placeholder="请按照顺序输入变量" autocomplete="off" class="layui-input">
		</c:forEach>
 	</div> 
        <div class="layui-form-item">
            <button class="layui-btn" lay-submit="" lay-filter="add">发送</button>
        </div>
    </form>

    <!--这里写页面的代码-->
</div>

<script>
    layui.use(['form', 'layedit', 'laydate', 'element', 'layer', 'jquery'], function () {
        var form = layui.form,
            layer = layui.layer,
            element = layui.element,
            upload = layui.upload, $ = layui.jquery;

        //监听提交，发送请求
        form.on('submit(add)', function (data) {

            if (data.field.kitAdminPassword != data.field.kitAdminPasswordAgen) {
                layer.alert('两次密码输入不一致');
                return false;
            }
            $.post("<%=basePath%>smsTemp/sendSms", data.field, function (data) {
                // 获取 session
                if (data.code != 200) {
                    layer.alert(data.msg, {offset: 't', icon: 5});
                }
                if (data.code == 200) {
                    layer.alert(data.msg, {offset: 't', icon: 6});
                }
            });
            return false;
        });
    });
</script>
</body>
</html>
