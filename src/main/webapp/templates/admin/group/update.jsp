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
    <title>添加分组</title>
    <link rel="stylesheet" href="<%=basePath%>templates/style/plugins/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="<%=basePath%>templates/style/build/css/doc.css" media="all">
    <script src="<%=basePath%>templates/style/plugins/layui/layui.js"></script>
    <script src="<%=basePath%>templates/style/plugins/layui/jquery-3.3.1.min.js"></script>
</head>
<body>

    <div class="kit-doc">
        <form class="layui-form layui-form-pane" action="">
            <input name="groupId" lay-verify="required" value="${kitModel.groupId}" autocomplete="off" class="layui-input" type="hidden">
            <div class="layui-form-item">
                <label class="layui-form-label">组名</label>
                <div class="layui-input-block">
                    <input name="groupName" lay-verify="required" value="${kitModel.groupName}" placeholder="请输入菜单标题" autocomplete="off" class="layui-input" type="text">
                </div>
            </div>
            <div class="layui-form-item" pane="">
                <label class="layui-form-label">分配权限</label>
                <div class="layui-input-block" style="padding-left: 10px;padding-right: 10px">
                <c:forEach items="${kitList}" var="kitList1" >
                    <c:if test="${kitList1.limitLeaderid==0}">
                        <table class="layui-table" lay-filter="kittable">
                            <thead>
                                <tr>
                                    <th>
                                        <input name="limitone_${kitList1.limitId}" value="${kitList1.limitId}" <c:if test="${kitList1.type==1}">checked</c:if>  lay-filter="limit" lay-skin="primary" title="${kitList1.limitTitle}"  type="checkbox">
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${kitList}" var="kitList2" >
                                <c:if test="${kitList2.limitLeaderid==kitList1.limitId}">
                                    <tr>
                                        <td>
                                            <input name="limittwo0_${kitList2.limitId}" lay-filter="limit_1" <c:if test="${kitList2.type==1}">checked</c:if> value="${kitList2.limitId}" lay-skin="primary" title="${kitList2.limitTitle}"  type="checkbox">
                                            <input name="limittwo1_${kitList2.limitId}" lay-filter="limit_1_1" <c:if test="${kitList2.c==1}">checked</c:if> value="1" lay-skin="primary" title="增加"  type="checkbox">
                                            <input name="limittwo2_${kitList2.limitId}" lay-filter="limit_1_1" <c:if test="${kitList2.r==1}">checked</c:if> value="1" lay-skin="primary" title="删除"  type="checkbox">
                                            <input name="limittwo3_${kitList2.limitId}" lay-filter="limit_1_1" <c:if test="${kitList2.u==1}">checked</c:if> value="1" lay-skin="primary" title="修改"  type="checkbox">
                                            <input name="limittwo4_${kitList2.limitId}" lay-filter="limit_1_1" <c:if test="${kitList2.d==1}">checked</c:if> value="1" lay-skin="primary" title="查询"  type="checkbox">
                                        </td>
                                    </tr>
                                </c:if>
                            </c:forEach>
                            </tbody>
                        </table>
                    </c:if>
                </c:forEach>
                </div>
            </div>

            <div class="layui-form-item">
                <button class="layui-btn" lay-submit="" lay-filter="add">提交</button>
            </div>
        </form>

        <!--这里写页面的代码-->
    </div>

    <script>
        layui.use(['form', 'layedit', 'laydate', 'element', 'layer'], function(){
            var form = layui.form,
                layer = layui.layer,
                element = layui.element;

            //监听提交，发送请求
            form.on('submit(add)', function(data){

                $.post("<%=basePath%>gGroup/update",data.field,function(data){
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


            //==================================================================================================

            // checkbox 点击事件处理
            // 选中一级栏目，二级栏目全选
            form.on('checkbox(limit)', function(data){
                var child = $(data.elem).parents('table').find('tbody input[type="checkbox"]');
                child.each(function(index, item){
                    item.checked = data.elem.checked;
                });
                form.render('checkbox');
            });
            // 选中二级栏目，增删改查全选
            form.on('checkbox(limit_1)', function(data){
                var child = $(data.elem).parents('tr').find('td input[type="checkbox"]');
                child.each(function(index, item){
                    item.checked = data.elem.checked;
                });
                if(data.elem.checked){
                    $(data.elem).closest("table").find("thead tr th input:checkbox:first").prop("checked",true);
                }else{
                    var l2 = $(data.elem).parents('table').find('tbody').find("input:checked").length; // 获取当前元素父元素下面的已经选中的就好了个数
                    if(!l2>0){
                        $(data.elem).closest("table").find("thead tr th input:checkbox:first").prop("checked",false);
                    }
                }
                form.render('checkbox');
            });
            // 如果有一个增加删除选中，
            form.on('checkbox(limit_1_1)', function(data){
                var l = $(data.elem).parent().find("input:checked").length; // 获取当前元素父元素下面的已经选中的就好了个数
                if (data.elem.checked){ // 说明下面有选中的
                    // 如果是选中，那么 第一个  和标题的一个 必须选中
                    $(data.elem).closest("tr").find("td input:checkbox:first").prop("checked",true);
                    $(data.elem).closest("table").find("thead tr th input:checkbox:first").prop("checked",true);
                } else {
                    if(l<=1){
                        $(data.elem).closest("tr").find("td input:checkbox:first").prop("checked",false);
                    }
                    var l2 = $(data.elem).parents('table').find('tbody').find("input:checked").length; // 获取当前元素父元素下面的已经选中的就好了个数
                    if(!l2>0){
                        $(data.elem).closest("table").find("thead tr th input:checkbox:first").prop("checked",false);
                    }
                }
                form.render('checkbox');
            });
        });
    </script>
</body>
</html>
