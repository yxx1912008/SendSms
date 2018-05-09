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
        <form class="layui-form layui-form-pane" action="">
            <input name="kitAdminId" lay-verify="required" value="${kitModel.kitAdminId}" autocomplete="off" class="layui-input" type="hidden">
            <div class="layui-form-item">
                <label class="layui-form-label">用户名</label>
                <div class="layui-input-block">
                    <input name="kitAdminName" lay-verify="required" value="${kitModel.kitAdminName}" placeholder="请输入用户名" autocomplete="off" class="layui-input" type="text">
                </div>
            </div>

            <div class="layui-form-item" >
                <label class="layui-form-label" style="height: 137px; line-height: 117px">上传头像</label>
                <input name="imgurl" id="imgurl" value="${kitModel.kitAdminImgUrl}" lay-verify="required" value="" autocomplete="off" class="layui-input" type="hidden">
                <div class=" layui-upload-drag" id="img" style="border-left: 0px;">
                    <i class="layui-icon">&#xe654;</i>
                    <p>点击上传头像</p>
                    <img id="demo1" src="<%=basePath%>${kitModel.kitAdminImgUrl}" style="position: absolute;height: 137px;width: 137px;margin: -106px auto auto -70px;" />
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">登录账号</label>
                <div class="layui-input-block">
                    <input name="kitAdminUsername" value="${kitModel.kitAdminUsername}" lay-verify="required" placeholder="请输入用户名" autocomplete="off" class="layui-input" type="text">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">密码</label>
                <div class="layui-input-block">
                    <input name="kitAdminPassword" lay-verify="" placeholder="留空表示不修改" value="" autocomplete="off" class="layui-input" type="password">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">确认密码</label>
                <div class="layui-input-block">
                    <input name="kitAdminPasswordAgen" lay-verify="" placeholder="留空表示不修改" autocomplete="off" class="layui-input" type="password">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">所属管理组</label>
                <div class="layui-input-block">
                    <select name="groupId" lay-verify="required" lay-search="">
                        <c:forEach items="${kitList}" var="kitList" >
                            <option <c:if test="${kitList.groupId == kitModel.groupId}">selected</c:if> value="${kitList.groupId}">${kitList.groupName}</option>
                        </c:forEach>
                    </select>
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
                $.post("<%=basePath%>admin/update",data.field,function(data){
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
