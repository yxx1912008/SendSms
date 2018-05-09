<%--
  Created by IntelliJ IDEA.
  User: benhailong
  Date: 2018/2/5
  Time: 下午6:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<jsp:include page="common/header.jsp" flush="true"> <jsp:param name="name" value="value"/> </jsp:include>--%>

<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>${kitWeb.kitWebName}</title>
    <link rel="stylesheet" href="<%=basePath%>templates/style/plugins/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="<%=basePath%>templates/style/plugins/font-awesome/css/font-awesome.min.css"
          media="all">
    <link rel="stylesheet" href="<%=basePath%>templates/style/build/css/app.css" media="all">
    <link rel="stylesheet" href="<%=basePath%>templates/style/build/css/themes/green.css" media="all">
    <script>
        // session 验证
        var session = "${kitWeb.kitWebName}";
        if (session == null || session == "") {
            window.location.href = '<%=basePath%>' + "admin/index";
        }
    </script>
</head>

<body class="kit-theme">
<div class="layui-layout layui-layout-admin kit-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">${kitWeb.kitWebName}</div>
        <div class="layui-logo kit-logo-mobile">K</div>
        <ul class="layui-nav layui-layout-left kit-nav" kit-one-level>
            <li class="layui-nav-item"><a href="javascript:;">控制台</a></li>
            <li class="layui-nav-item"><a href="javascript:;">商品管理</a></li>
        </ul>
        <ul class="layui-nav layui-layout-right kit-nav">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <img src="<%=basePath%>${admin.kitAdminImgUrl}" class="layui-nav-img"> ${admin.kitAdminName}
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="javascript:alert('功能正在开发中');">基本资料</a></dd>
                    <dd><a href="javascript:alert('功能正在开发中');">安全设置</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item"><a href="<%=basePath%>admin/uplogin"><i class="fa fa-sign-out"
                                                                               aria-hidden="true"></i> 注销</a></li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black kit-side">
        <div class="layui-side-scroll">
            <div class="kit-side-fold"><i class="fa fa-navicon" aria-hidden="true"></i></div>
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree" lay-filter="kitNavbar" kit-navbar>

                <%--默认打开栏目 在外层的 li 标签添加  layui-nav-itemed--%>
                <c:forEach items="${limitList}" var="limitList1">
                    <c:if test="${limitList1.limitLeaderid==0}">
                        <li class="layui-nav-item">
                            <a class="" href="javascript:;"><i class="fa fa-plug"
                                                               aria-hidden="true"></i><span> ${limitList1.limitTitle}</span></a>
                            <c:forEach items="${limitList}" var="limitList2">
                                <dl class="layui-nav-child">
                                    <c:if test="${limitList1.limitId==limitList2.limitLeaderid}">
                                        <dd>
                                            <a href="javascript:;" kit-target
                                               data-options="{url:'${limitList2.limitAction}',icon:'${limitList2.limitIco}',title:'${limitList2.limitTitle}',id:'${limitList2.limitId}'}">
                                                <i class="layui-icon">${limitList2.limitIco}</i><span> ${limitList2.limitTitle}</span></a>
                                        </dd>
                                    </c:if>
                                </dl>
                            </c:forEach>
                        </li>
                    </c:if>
                </c:forEach>
            </ul>
        </div>
    </div>
    <div class="layui-body" id="container">
        <!-- 内容主体区域 -->
        <div style="padding: 15px;">主体内容加载中,请稍等...</div>
    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
        2018 &copy;
        <a href="http://www.icaomei.com/" target="_blank">www.icaomei.com</a> 爱草媒

    </div>
</div>

<script src="<%=basePath%>templates/style/plugins/layui/layui.js"></script>
<script src="<%=basePath%>admin/js/getGroup.js"></script>
<script>
    // 数据存 Cookie
    var adminid = '${admin.kitAdminId}';
    var kitWeb = '${kitWeb.kitWebName}';
    setCookie("adminid", adminid, 'm15');
    setCookie("kitWeb", kitWeb, 'm15');
</script>
<script>
    var message;
    layui.config({
        base: '<%=basePath%>templates/style/build/js/'
    }).use(['app', 'message'], function () {
        var app = layui.app,
            $ = layui.jquery,
            layer = layui.layer;
        //将message设置为全局以便子页面调用
        message = layui.message;
        //主入口
        app.set({
            type: 'iframe'
        }).init();
        $('#pay').on('click', function () {
            layer.open({
                title: false,
                type: 1,
                content: '<img src="<%=basePath%>templates/style/build/images/pay.png" />',
                area: ['500px', '250px'],
                shadeClose: true
            });
        });
    });
</script>
</body>

</html>