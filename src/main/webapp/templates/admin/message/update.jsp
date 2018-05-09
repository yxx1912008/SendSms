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
    <title>短信配置</title>
    <link rel="stylesheet" href="<%=basePath%>templates/style/plugins/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="<%=basePath%>templates/style/build/css/doc.css" media="all">
    <script src="<%=basePath%>templates/style/plugins/layui/layui.js"></script>
    <script src="<%=basePath%>templates/style/plugins/layui/jquery-3.3.1.min.js"></script>
</head>
<body>

<div class="kit-doc">
    <!--这里写页面的代码-->
    <div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
        <ul class="layui-tab-title">
            <li class="layui-this">阿里大鱼</li>
            <li>云之讯</li>
            <li>短信测试</li>
            <li>使用说明</li>
        </ul>
        <div class="layui-tab-content" style="height: 100px;">
            <div class="layui-tab-item layui-show">
                <form class="layui-form layui-form-pane" action="">

                    <div class="layui-form-item">
                        <label class="layui-form-label">KeyId</label>
                        <div class="layui-input-block">
                            <input name="keyId" lay-verify="required" value="${kitModel.keyId}"
                                   placeholder="请输入你在阿里大鱼后台的AccessKeyId" autocomplete="off" class="layui-input"
                                   type="text">
                        </div>
                    </div>

                    <div class="layui-form-item">
                        <label class="layui-form-label">KeySecret</label>
                        <div class="layui-input-block">
                            <input name="keySecret" lay-verify="required" value="${kitModel.ketSecret}"
                                   placeholder="请输入你在阿里大鱼后台的AccessKeySecret" autocomplete="off" class="layui-input"
                                   type="password">
                        </div>
                    </div>

                    <div class="layui-form-item">
                        <label class="layui-form-label">短信签名</label>
                        <div class="layui-input-block">
                            <input name="signName" lay-verify="required" value="${kitModel.signName}"
                                   placeholder="请输入你在阿里大鱼认证通过的短信签名" autocomplete="off" class="layui-input" type="text">
                        </div>
                    </div>

                    <div class="layui-form-item">
                        <label class="layui-form-label">短信模板</label>
                        <div class="layui-input-block">
                            <input name="dayutemplate" lay-verify="" value="${kitModel.templateDayu}"
                                   placeholder="这里是默认短信模板，可为空，不传短信模板的时候调用这个，当然我们默认是设置验证码短信模板" autocomplete="off"
                                   class="layui-input" type="text">
                        </div>
                    </div>

                    <div class="layui-form-item">
                        <button class="layui-btn" lay-submit="required" lay-filter="dayu">提交</button>
                    </div>
                </form>

            </div>
            <div class="layui-tab-item">
                <form class="layui-form layui-form-pane" action="">
                    <div class="layui-form-item">
                        <label class="layui-form-label">Sid</label>
                        <div class="layui-input-block">
                            <input name="sid" lay-verify="required" value="${kitModel.sid}" placeholder="请输入你在云之讯的唯一标识"
                                   autocomplete="off" class="layui-input" type="text">
                        </div>
                    </div>

                    <div class="layui-form-item">
                        <label class="layui-form-label">Token</label>
                        <div class="layui-input-block">
                            <input name="token" lay-verify="required" value="${kitModel.token}"
                                   placeholder="请输入你在云之讯的token" autocomplete="off" class="layui-input" type="password">
                        </div>
                    </div>

                    <div class="layui-form-item">
                        <label class="layui-form-label">AppId</label>
                        <div class="layui-input-block">
                            <input name="appId" lay-verify="required" value="${kitModel.appId}" placeholder="云之讯的AppId"
                                   autocomplete="off" class="layui-input" type="text">
                        </div>
                    </div>

                    <div class="layui-form-item">
                        <label class="layui-form-label">短信模板</label>
                        <div class="layui-input-block">
                            <input name="yzxtemplate" lay-verify="" value="${kitModel.templateYzx}"
                                   placeholder="这里是默认短信模板，可为空，不传短信模板的时候调用这个，当然我们默认是设置验证码短信模板" autocomplete="off"
                                   class="layui-input" type="text">
                        </div>
                    </div>

                    <div class="layui-form-item">
                        <button class="layui-btn" lay-submit="" lay-filter="yunzhixun">提交</button>
                    </div>
                </form>
            </div>
            <div class="layui-tab-item">
                <form class="layui-form layui-form-pane" action="">
                    <div class="layui-form-item">
                        <label class="layui-form-label">平台</label>
                        <div class="layui-input-inline">
                            <select name="messagetype" lay-verify="required" lay-search="">
                                <option value="1">阿里大鱼</option>
                                <option value="2">云之讯</option>
                            </select>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">手机号</label>
                        <div class="layui-input-block">
                            <input name="phone" lay-verify="required|phone" placeholder="请输入接收短信的手机号码"
                                   autocomplete="off" class="layui-input" type="text">
                        </div>
                    </div>

                    <div class="layui-form-item">
                        <label class="layui-form-label">验证码</label>
                        <div class="layui-input-block">
                            <input name="code" lay-verify="required"
                                   placeholder="如果模板中存在多个参数请按照下面的格式书写：key,value,key,value" autocomplete="off"
                                   class="layui-input" type="text">
                        </div>
                    </div>

                    <div class="layui-form-item">
                        <label class="layui-form-label">短信模板</label>
                        <div class="layui-input-block">
                            <input name="template" lay-verify="" placeholder="短信模板，请在各自后台读取" autocomplete="off"
                                   class="layui-input" type="text">
                        </div>
                    </div>

                    <div class="layui-form-item">
                        <button class="layui-btn" lay-submit="" lay-filter="test">提交</button>
                    </div>
                </form>
            </div>

            <div class="layui-tab-item">

                <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
                    <legend>说明</legend>
                </fieldset>

                <blockquote class="layui-elem-quote">
                    首先，不得不说，OpenKit在发送短信已经做了很多的封装，已经大大节约了工作时间，提高了工作效率，那么为什么我们再KitAdmin总还要重新封装呢？我们在KitAdmin中保留了原有的所有的OpenKit中的所有模块，同时在原有的基础上整合，比如短信，我们现在只要一个接口，其他的都是在后台配置，这样大大提高开发效率，减少开发失误
                </blockquote>

                <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
                    <legend>调用</legend>
                </fieldset>

                <blockquote class="layui-elem-quote layui-quote-nm">
                    这里希望大家注意一下<br/>
                    1. 如果后台设置了使用短信的第三方，可以不传 messageType 参数，如果设置了，将预先使用代码中设置的第三方，（1：代表阿里大鱼，2：代表云之讯）<br/>
                    2. 如果想调用后台设置的默认短信模板，请直接将 template 参数留空即可，如果传入，将预加载你代码中设置的模板<br/>
                    3. 注意，不管你是使用阿里大鱼，还是云之讯，我们传入变量的时候都要使用这种格式 key,value,key,value(中间英文“,”不需要空格)
                    哪怕你只有一个参数，也要严格按照这个方式，传入参数，如果你是阿里大鱼那么 Key
                    代表变量名，value代表对于的变量值，如果是云之讯，key不需要有，但是可以随便书写，value代表参数，顺序要按照短信模板中的参数的顺序传入
                </blockquote>


                <pre class="layui-code">
KitMessage kitMessage = new KitMessage();
if(messagetype!=null && !"".equals(messagetype)){
    kitMessage.setMessagetype(Integer.valueOf(messagetype));
}
kitMessage.setPhone(phone);
kitMessage.setCode(code);
kitMessage.setTemplate(template);

// 调用
map = messageService.send(kitMessage);</pre>

                <blockquote class="layui-elem-quote">
                    对，你没有看错，不管是阿里大鱼，还是云之讯，你只要后台配置好之后，接下来，你只要上面的一行代码，就可以搞定短信验证，当然就算你后台配置使用的是云之讯，你同样可以强制在代码中强制使用云之讯，同样的道理，后台你配置了，短信模板，如果你不传短信模板，我们会默认调用后台设置的默认短信模板，如果有特殊要求，你可以传入对应参数
                </blockquote>


            </div>
        </div>
    </div>


    <!--这里写页面的代码-->
</div>

<script>
    layui.use(['form', 'layedit', 'laydate', 'element', 'layer'], function () {
        var form = layui.form,
            layer = layui.layer,
            element = layui.element;

        //监听提交，发送请求
        form.on('submit(dayu)', function (data) {

            $.post("<%=basePath%>message/update", data.field, function (d) {

                // 获取 session
                if (d.code != 200) {
                    layer.alert(d.msg, {offset: 't', icon: 5});
                }
                if (d.code == 200) {
                    layer.alert(d.msg, {offset: 't', icon: 6});
                }
            });
            return false;
        });

        //监听提交，发送请求
        form.on('submit(yunzhixun)', function (data) {

            $.post("<%=basePath%>message/update", data.field, function (d) {
                // 获取 session
                if (d.code != 200) {
                    layer.alert(d.msg, {offset: 't', icon: 5});
                }
                if (d.code == 200) {
                    layer.alert(d.msg, {offset: 't', icon: 6});
                }
            });
            return false;
        });

        //监听提交，发送请求
        form.on('submit(test)', function (data) {

            $.post("<%=basePath%>message/messageTest", data.field, function (d) {
                // 获取 session
                if (d.code != 200) {
                    layer.alert(d.msg, {offset: 't', icon: 5});
                }
                if (d.code == 200) {
                    layer.alert(d.msg, {offset: 't', icon: 6});
                }
            });
            return false;
        });

    });
</script>
</body>
</html>
