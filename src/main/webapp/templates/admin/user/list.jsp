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
    <title>会员用户列表</title>
    <link rel="stylesheet" href="<%=basePath%>templates/style/plugins/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="<%=basePath%>templates/style/build/css/doc.css" media="all">
    <script src="<%=basePath%>templates/style/plugins/layui/layui.js"></script>
    <script src="<%=basePath%>templates/style/plugins/layui/jquery-3.3.1.min.js"></script>
</head>

<body>
    <div class="kit-doc">
        <!--这里写页面的代码-->
        <blockquote class="layui-elem-quote">这里前台注册的会员</blockquote>
        <div class="kit-doc-title">
            <fieldset>
                <legend><a name="blockquote">会员用户列表</a></legend>
            </fieldset>
        </div>
        <div>

            <c:if test="${kitG.groupC==1}"><button class="layui-btn" id="add">添加</button></c:if>
            <div class="layui-inline" style="float: right">
                <div class="layui-input-inline">
                    <input name="search" id="search" lay-verify="required" autocomplete="off" class="layui-input" type="text">
                </div>
                <button class="layui-btn layui-btn-primary" onclick="search()">搜索</button>
            </div>
                
            <div style="clear:both;"></div>

            <table class="layui-hide" id="test" lay-filter="demo"></table>

            <script type="text/html" id="indexTpl">
                {{d.LAY_TABLE_INDEX+1}}
            </script>
            <script type="text/html" id="kaiguan">
                <input type="checkbox" value="{{ d.userId }}" {{#  if(d.userType == 1){ }} checked="" {{#  } }}  name="open" lay-skin="switch" lay-filter="ahType" lay-text="开|关">
            </script>

            <script type="text/html" id="barDemo">
                <c:if test="${kitG.groupU==1}"><button class="layui-btn layui-btn-xs" lay-event="edit">编辑</button></c:if>
                {{#  if(d.kitAdminId != 1){ }}
                    <c:if test="${kitG.groupR==1}"><button class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</button></c:if>
                {{#  } }}
            </script>
        </div>
        <!--这里写页面的代码-->
    </div>

    <script>
        // 添加
        layui.use(['jquery', 'layer', 'table'], function () {
            var table = layui.table;
                form = layui.form;
            //让层自适应iframe
            $('#add').on('click', function(){
                var index = layer.open({
                    type: 2,
                    content: '<%=basePath%>user/goAdd',
                    area: ['400px', '570px'],
                    maxmin: true,
                    end: function () {
                        location.reload();
                    }
                });
                parent.layer.iframeAuto(index);
            });

            // 监听开关事件
            form.on('switch(ahType)', function(data){

                var a = data.elem.checked;
                var b = 0;
                var id = data.value;
                if(a){
                    b=1
                }else(
                    b=2
                )
                // 开关方法
                $.post("<%=basePath%>user/updateType", {"id": id,"kaiguan": b}, function (data) {
                    if (data.code == "200") {
                        layer.msg(data.msg, {icon: 1, time: 1000});
                    } else {
                        layer.msg(data.msg, {icon: 0, time: 1000});
                    }
                });

            });
        });

        // 渲染数据
        layui.use('table', function(){
            var table = layui.table;
            var search = $('#search').val();

            table.render({
                elem: '#test'
                ,url:'<%=basePath%>user/getAllJson'
                ,where: {search: search}
                ,method: 'post'
                ,page: {layout: ['limit', 'count', 'prev', 'page', 'next', 'skip']}
                ,cols: [[
                    {field:'',align:'center', width:70,  title: '序号', toolbar: '#indexTpl'}
                    ,{field:'userName', title: '用户名'}
                    ,{field:'userLoginName', title: '登录名'}
                    ,{field:'userPhone', title: '手机号码'}
                    ,{field:'userPhone', title: '手机号码'}
                    ,{field:'userEmail', title: '邮箱'}
                    ,{field:'ahType',align:'center', width:80, toolbar: '#kaiguan', title: '开关'}
                    ,{field:'right',align:'center', width:150, toolbar: '#barDemo', title: '操作'}
                ]]

            });

            //监听工具条
            table.on('tool(demo)', function(obj){
                var data = obj.data;
                if(obj.event === 'edit'){
                    // 编辑
                    var index = layer.open({
                        type: 2,
                        content: '<%=basePath%>user/goUpdate?id='+data.userId,
                        area: ['400px', '570px'],
                        maxmin: true,
                        end: function () {
                            location.reload();
                        }
                    });
                    parent.layer.iframeAuto(index);

                } else if(obj.event === 'del'){
                    layer.confirm('真的要删除么？', function(index){
                        // 写删除方法
                        $.post("<%=basePath%>user/del", {"id": data.userId}, function (data) {
                            if (data.code == "200") {
                                layer.msg(data.msg, {icon: 1, time: 1000});
                                // 前端修改
                                layer.close(index);
                                window.location.reload();
                            } else {
                                layer.msg(data.msg, {icon: 0, time: 1000});
                                layer.close(index);
                            }
                        });
                    });
                }
            });
        });
        function search(){
            var table = layui.table;
            var search = $('#search').val();

            table.render({
                elem: '#test'
                ,url:'<%=basePath%>user/getAllJson'
                ,where: {search: search}
                ,method: 'post'
                ,page: {layout: ['limit', 'count', 'prev', 'page', 'next', 'skip']}
                ,cols: [[
                    {field:'',align:'center', width:70,  title: '序号', toolbar: '#indexTpl'}
                    ,{field:'userName', title: '用户名'}
                    ,{field:'userLoginName', title: '登录名'}
                    ,{field:'userPhone', title: '手机号码'}
                    ,{field:'userPhone', title: '手机号码'}
                    ,{field:'userEmail', title: '邮箱'}
                    ,{field:'ahType',align:'center', width:80, toolbar: '#kaiguan', title: '开关'}
                    ,{field:'right',align:'center', width:150, toolbar: '#barDemo', title: '操作'}
                ]]

            });
        }

    </script>

</body>

</html>