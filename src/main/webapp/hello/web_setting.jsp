<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>系统配置</title>
    <link rel="stylesheet" href="../templates/style/plugins/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="../templates/style/build/css/doc.css" media="all">
    <script src="../templates/style/plugins/layui/layui.js"></script>
    <script src="../templates/style/plugins/layui/jquery-3.3.1.min.js"></script>
    <script src="js/getGroup.js"></script>
    <!-- 这个是用于测试session是不是存在 -->
    <script>
        layui.use(['jquery', 'layer'], function () {
            var returnmsg = getGroup(2);
            layer.alert(returnmsg, {offset: 't',icon: 6});
        });
    </script>
</head>

<body>
    <div class="kit-doc">
        <blockquote class="layui-elem-quote">注意这里是项目的全局配置，下面的这些配置对整个网站都会起到作用！</blockquote>
        <div class="kit-doc-title">
            <fieldset>
                <legend><a name="blockquote">系统配置</a></legend>
            </fieldset>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">网站名称</label>
            <div class="layui-input-inline">
                <input type="text" name="kitWebName"  lay-verify="required" placeholder="" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit="" lay-filter="demo1">保存</button>
            </div>
        </div>
    </div>

    <%--<script>--%>
        <%--// 请求获取数据--%>
        <%--$(function(){--%>
            <%--$.post("/web/getModel",function(data){--%>
                <%--// 获取 session--%>
                <%--$('input[name="kitWebName"]').val(data.kitWebName);--%>
            <%--});--%>
        <%--});--%>
    <%--</script>--%>

</body>

</html>