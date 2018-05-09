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
    <link rel="stylesheet" href="<%=basePath%>templates/style/plugins/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="<%=basePath%>templates/style/build/css/doc.css" media="all">
    <script src="<%=basePath%>templates/style/plugins/layui/layui.js"></script>
    <script src="<%=basePath%>templates/style/plugins/layui/jquery-3.3.1.min.js"></script>
</head>

<body>
    <div class="kit-doc">
        <blockquote class="layui-elem-quote">注意这里是项目的全局配置，下面的这些配置对整个网站都会起到作用！</blockquote>
        <div class="kit-doc-title">
            <fieldset>
                <legend><a name="blockquote">系统配置</a></legend>
            </fieldset>
        </div>
        <form class="layui-form layui-form-pane" action="">
            <div class="layui-form-item">
                <label class="layui-form-label">网站名称</label>
                <div class="layui-input-inline">
                    <input type="text" name="kitWebName" lay-verify="required" value="${kitModel.kitWebName}" autocomplete="off" class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">短信设置</label>
                <div class="layui-input-inline">
                    <select name="kitWebMessage" lay-verify="required" lay-search="">
                        <option <c:if test="${kitModel.kitWebMessage==1}">selected</c:if>  value="1">阿里大鱼</option>
                        <option <c:if test="${kitModel.kitWebMessage==2}">selected</c:if>  value="2">云之讯</option>
                    </select>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">推送设置</label>
                <div class="layui-input-inline">
                    <select name="kitWebPush" lay-verify="required" lay-search="">
                        <option value="1" <c:if test="${kitModel.kitWebPush==1}">selected</c:if> >极光</option>
                        <option value="2" <c:if test="${kitModel.kitWebPush==2}">selected</c:if> >个推</option>
                    </select>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">文件存储</label>
                <div class="layui-input-inline">
                    <select name="kitWebFile" lay-verify="required" lay-search="">
                        <option value="1" <c:if test="${kitModel.kitWebFile==1}">selected</c:if> >本地</option>
                        <option value="2" <c:if test="${kitModel.kitWebFile==2}">selected</c:if> >七牛云</option>
                    </select>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">验证码</label>
                <div class="layui-input-inline">
                    <input type="text" name="kitSigeSize" lay-verify="required|number|sigeSize" value="${kitModel.kitSigeSize}" autocomplete="off" class="layui-input">
                </div>
                <div class="layui-form-mid layui-word-aux">位</div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label" style="width: 130px">验证码有效期</label>
                <div class="layui-input-inline">
                    <input type="text" name="kitSignActiveTime" lay-verify="required|number|activeTime" value="${kitModel.kitSignActiveTime}" autocomplete="off" class="layui-input">
                </div>
                <div class="layui-form-mid layui-word-aux">分钟</div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label" style="width: 130px">APP首登幻灯片</label>
                <div class="layui-input-inline">
                    <input type="text" name="kitWebHellowAdvSize" lay-verify="required|number" value="${kitModel.kitWebHellowAdvSize}" autocomplete="off" class="layui-input">
                </div>
                <div class="layui-form-mid layui-word-aux">个</div>
            </div>


            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button class="layui-btn" lay-submit="" lay-filter="update">保存</button>
                </div>
            </div>
        </form>
    </div>
    <script>
        layui.use(['form', 'layedit', 'laydate'], function(){
            var form = layui.form
                ,layer = layui.layer;

            // 非layui自带表单验证
            form.verify({
                sigeSize: function(value, item){ //value：表单的值、item：表单的DOM对象
                    if(value > 8){
                        return '验证码位数必须小于等于8';
                    }
                    if(value < 4){
                        return '验证码位数必须大于等于4';
                    }
                },

                activeTime: function(value, item){ //value：表单的值、item：表单的DOM对象
                    if(value > 30){
                        return '验证码有效期建议在5到30分钟之间';
                    }
                    if(value < 5){
                        return '验证码有效期建议在5到30分钟之间';
                    }
                }

            });


            //监听提交，发送请求
            form.on('submit(update)', function(data){
                $.post("<%=basePath%>web/update",data.field,function(data){
                    // 获取 session
                    if(data.code!=200){
                        layer.alert(data.msg, {offset: 't',icon: 6});
                    }
                    if(data.code==200){
                        layer.alert(data.msg, {offset: 't',icon: 6});
                    }
                });
                return false;
            });
        });
    </script>

</body>

</html>