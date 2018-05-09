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
    <title>网站设置</title>
    <link rel="stylesheet" href="<%=basePath%>templates/style/plugins/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="<%=basePath%>templates/style/build/css/doc.css" media="all">
    <script src="<%=basePath%>templates/style/plugins/layui/layui.js"></script>
    <script src="<%=basePath%>templates/style/plugins/layui/jquery-3.3.1.min.js"></script>
    <script src="<%=basePath%>templates/admin/js/getGroup.js"></script>
    <!-- 这个是用于测试session是不是存在 -->
    <%--<script>--%>
        <%--layui.use(['jquery', 'layer'], function () {--%>
            <%--var returnmsg = getGroup(2);--%>
            <%--layer.alert(returnmsg, {offset: 't',icon: 6});--%>
        <%--});--%>
    <%--</script>--%>

</head>

<body>
    <div class="kit-doc">
        <!--这里写页面的代码-->
        <blockquote class="layui-elem-quote">注意这里是项目的左侧菜单栏目管理，如果是系统自带的，我们禁止你删除，如果是你自己添加的，你可以选择可以删除还是不可以删除</blockquote>
        <div class="kit-doc-title">
            <fieldset>
                <legend><a name="blockquote">菜单管理</a></legend>
            </fieldset>
        </div>
        <div>
            <c:if test="${kitG.groupC==1}"><button class="layui-btn" id="add">添加</button></c:if>
            <%--<div class="layui-inline" style="float: right">--%>
                <%--<div class="layui-input-inline">--%>
                    <%--<input name="phone" lay-verify="required|phone" autocomplete="off" class="layui-input" type="tel">--%>
                <%--</div>--%>
                <%--<button class="layui-btn layui-btn-primary">搜索</button>--%>
            <%--</div>--%>
        </div>

        <table class="layui-table" lay-filter="kittable">
            <colgroup>
                <col width="30">
                <col width="60">
                <col width="60">
                <col width="200">
                <col>
                <col width="180">
            </colgroup>
            <thead>
            <tr>
                <th>ID</th>
                <th>排序</th>
                <th>图标</th>
                <th>标题</th>
                <th>链接</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${kitList}" var="kitList1" >
                <c:if test="${kitList1.limitLeaderid==0}">
                    <tr>
                        <td>${kitList1.limitId}</td>
                        <td>${kitList1.limitSequence}</td>
                        <td><i class="layui-icon">${kitList1.limitIco}</i></td>
                        <td>${kitList1.limitTitle}</td>
                        <td>${kitList1.limitAction}</td>
                        <td>
                            <c:if test="${kitList1.limitSystem==2}">系统菜单，无法操作</c:if>
                            <c:if test="${kitList1.limitSystem==1}">
                                <c:if test="${kitG.groupU==1}"><button class="layui-btn layui-btn-sm layui-btn-normal" onClick="update(this,'${kitList1.limitId}')"><i class="layui-icon">&#xe642;</i> 编辑</button></c:if>
                                <c:if test="${kitG.groupR==1}"><button class="layui-btn layui-btn-sm layui-btn-danger" onClick="del(this,'${kitList1.limitId}')"><i class="layui-icon">&#xe640;</i> 删除</button></c:if>
                            </c:if>

                        </td>
                    </tr>
                    <c:forEach items="${kitList}" var="kitList2" >
                        <c:if test="${kitList2.limitLeaderid==kitList1.limitId}">
                            <tr>
                                <td>${kitList2.limitId}</td>
                                <td>${kitList2.limitSequence}</td>
                                <td><i class="layui-icon">${kitList2.limitIco}</i></td>
                                <td><font color="#e1e1e1">&nbsp;|--&nbsp;&nbsp;|--&nbsp;&nbsp;</font>${kitList2.limitTitle}</td>
                                <td>${kitList2.limitAction}</td>
                                <td>
                                    <c:if test="${kitList2.limitSystem==2}">系统菜单，无法操作</c:if>
                                    <c:if test="${kitList2.limitSystem==1}">
                                        <c:if test="${kitG.groupU==1}"><button class="layui-btn layui-btn-sm layui-btn-normal" onClick="update(this,'${kitList2.limitId}')"><i class="layui-icon">&#xe642;</i> 编辑</button></c:if>
                                        <c:if test="${kitG.groupR==1}"><button class="layui-btn layui-btn-sm layui-btn-danger" onClick="del(this,'${kitList2.limitId}')"><i class="layui-icon">&#xe640;</i> 删除</button></c:if>
                                    </c:if>
                                </td>
                            </tr>
                        </c:if>
                    </c:forEach>
                </c:if>
            </c:forEach>
            </tbody>
        </table>
        <!--这里写页面的代码-->
    </div>

    <script>
        layui.use(['jquery', 'layer', 'table'], function () {

            var table = layui.table;

            //让层自适应iframe
            $('#add').on('click', function(){
                var index = layer.open({
                    type: 2,
                    content: '<%=basePath%>gLimit/goAdd',
                    area: ['800px', '600px'],
                    maxmin: true,
                    end: function () {
                        location.reload();
                    }
                });
                parent.layer.iframeAuto(index);
            });
        });


        // 删除
        function del(obj,id){
            //询问框
            layer.confirm('真的要删除么？', {
                btn: ['确定','取消'], //按钮
                end: function () {
                    location.reload();
                }
            }, function(){
                $.post("<%=basePath%>gLimit/del",{'id':id},function(data){
                    // 获取 session
                    if(data.code!=200){
                        layer.msg(data.msg, {icon: 5});
                    }
                    if(data.code==200){
                        layer.msg(data.msg, {icon: 6});
                    }
                    parent.layer.iframeAuto(index);
                });
            }, function(){
            });
        };

        // 修改
        function update(obj,id){
            var index = layer.open({
                type: 2,
                content: '<%=basePath%>gLimit/goUpdate?id='+id,
                area: ['800px', '600px'],
                maxmin: true,
                end: function () {
                    location.reload();
                }
            });
            parent.layer.iframeAuto(index);
        };

    </script>

</body>

</html>