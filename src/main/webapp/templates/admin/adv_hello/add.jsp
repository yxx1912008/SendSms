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
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>添加管理员</title>
    <link rel="stylesheet" href="<%=basePath%>templates/style/plugins/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="<%=basePath%>templates/style/build/css/doc.css" media="all">
    <script src="<%=basePath%>templates/style/plugins/layui/layui.js"></script>
    <script src="<%=basePath%>templates/style/plugins/layui/jquery-3.3.1.min.js"></script>
</head>
<body>

    <div class="kit-doc">
        <form class="layui-form " action="">

            <div class="layui-form-item">
                <label class="layui-form-label"><span class="layui-badge-dot"></span> &nbsp;标题</label>
                <div class="layui-input-block">
                    <input name="ahTitle" lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input" type="text">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">描述</label>
                <div class="layui-input-block">
                    <textarea  name="ahSummary" placeholder="请输入描述" class="layui-textarea"></textarea>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label"><span class="layui-badge-dot"></span> &nbsp;排序</label>
                <div class="layui-input-block">
                    <input name="ahSequence" lay-verify="" value="50" autocomplete="off" class="layui-input" type="text">
                </div>
            </div>

            <div class="layui-form-item" >
                <label class="layui-form-label" style="height: 137px; line-height: 117px"><span class="layui-badge-dot"></span> &nbsp;上传图片</label>
                <input name="imgurl" id="imgurl" lay-verify="required" value="" autocomplete="off" class="layui-input" type="hidden">
                <div class=" layui-upload-drag" id="img" >
                    <i class="layui-icon">&#xe654;</i>
                    <p>点击上传图片</p>
                    <img id="demo1" style="position: absolute;height: 137px;width: 137px;margin: -106px auto auto -30px;display: none" />
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">链接地址</label>
                <div class="layui-input-block">
                    <input name="ahUrl" lay-verify="" placeholder="请输入图片链接地址" autocomplete="off" class="layui-input" type="text">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label"><span class="layui-badge-dot"></span> &nbsp;起止日期</label>
                <div class="layui-input-block">
                    <input type="text" name="ahStartAndEndTime" class="layui-input" id="test10" placeholder=" - ">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label"><span class="layui-badge-dot"></span> &nbsp;是否开启</label>
                <div class="layui-input-block">
                    <input type="checkbox" name="ahType" lay-skin="switch" checked lay-text="开启|关闭">
                </div>
            </div>

            <div class="layui-form-item">
                <button class="layui-btn" lay-submit="" lay-filter="add">提交</button>
            </div>
        </form>

        <!--这里写页面的代码-->
    </div>

    <script>
        layui.use(['form', 'layedit', 'laydate', 'element', 'layer', 'upload'], function(){
            var form = layui.form,
                layer = layui.layer,
                element = layui.element,
                upload = layui.upload;
                laydate = layui.laydate;

            //日期时间范围
            laydate.render({
                elem: '#test10'
                ,type: 'datetime'
                ,range: true
            });

            //普通图片上传
            var uploadInst = upload.render({
                elem: '#img'
                , url: '/apiCommon/setImage'
                , field: 'layuiFile'
                , before: function (obj) {
                    //预读本地文件示例，不支持ie8
                    obj.preview(function (index, file, result, data) {
                        $('#demo1').css('display','block').attr('src', result); //链接（base64）
                    });
                }
                , done: function (res) {
                    //如果上传失败
                    if (res.code > 0) {
                        return layer.msg('上传失败');
                    }
                    //上传成功
                    if(res.code == 0){
                        $('#imgurl').val(res.data.src);
                    }
                }
                , error: function () {
                    //演示失败状态，并实现重传
                    var demoText = $('#demoText');
                    demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-mini demo-reload">重试</a>');
                    demoText.find('.demo-reload').on('click', function () {
                        uploadInst.upload();
                    });
                }
            });


            //监听提交，发送请求
            form.on('submit(add)', function(data){
                if(data.field.kitAdminPassword!=data.field.kitAdminPasswordAgen){
                    layer.alert('两次密码输入不一致');
                    return false;
                }
                $.post("<%=basePath%>advhello/add",data.field,function(data){
                    // 获取 session
                    if(data.code!=200){
                        layer.alert(data.msg, {offset: 't',icon: 5});
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
