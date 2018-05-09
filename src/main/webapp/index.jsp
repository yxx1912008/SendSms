<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%response.sendRedirect("admin/index"); %>


<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="keywords" contect="富文本,富文本编辑器,富文本框,在线编辑器,html,web,javascript">
    <meta name="Description" contect="wangEditor - 轻量级web富文本编辑器">
    <title>KitAdmin1.0————专业APP开发后台管理框架</title>

    <link rel="stylesheet" type="text/css"
          href="<%=basePath%>templates/style/benstyle/bootstrap-3.3.0/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css"
          href="<%=basePath%>templates/style/benstyle/fontawesome-4.2.0/css/font-awesome.min.css">
    <!--[if IE]>
    <link rel="stylesheet" type="text/css"
          href="<%=basePath%>templates/style/benstyle/fontawesome-4.2.0/css/font-awesome-ie7.min.css">
    <![endif]-->

    <link rel="stylesheet" type="text/css" href="<%=basePath%>templates/style/benstyle/css/common.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>templates/style/benstyle/css/index.css">
    <link rel="shortcut icon" href="<%=basePath%>templates/style/favicon.png"/>
    <link rel="stylesheet" href="<%=basePath%>templates/style/plugins/layui/css/layui.css" media="all">
    <script src="<%=basePath%>templates/style/plugins/layui/layui.js"></script>

    <%--编辑器增强版--%>
    <link rel="stylesheet" href="<%=basePath%>templates/style/kindeditor/themes/default/default.css"/>
    <link rel="stylesheet" href="<%=basePath%>templates/style/kindeditor/plugins/code/prettify.css"/>
    <script charset="utf-8" src="<%=basePath%>templates/style/kindeditor/kindeditor-all.js"></script>
    <script charset="utf-8" src="<%=basePath%>templates/style/kindeditor/lang/zh-CN.js"></script>
    <script charset="utf-8" src="<%=basePath%>templates/style/kindeditor/plugins/code/prettify.js"></script>

    <script>
        KindEditor.ready(function (K) {
            var editor1 = K.create('textarea[name="content1"]', {
                width: '100%',
                themeType: 'simple',
                cssPath: '<%=basePath%>templates/style/kindeditor/plugins/code/prettify.css',
                uploadJson: '<%=basePath%>apiCommon/upload_json',
                fileManagerJson: '<%=basePath%>apiCommon/file_manager_json',
                allowFileManager: true,
                afterCreate: function () {
                    var self = this;
                    K.ctrl(document, 13, function () {
                        self.sync();
                        document.forms['example'].submit();
                    });
                    K.ctrl(self.edit.doc, 13, function () {
                        self.sync();
                        document.forms['example'].submit();
                    });
                }
            });
            prettyPrint();
        });
    </script>
</head>
<body>
<!-- jumbotron（超大屏幕） -->
<div class="jumbotron" style="background-color: #743f8e">
    <div class="container">
        <center>
            <h1><i class="layui-icon" style="font-size: 100px;color: #ffffff">&#xe6af;</i></h1>
            <h1 style="color: #ffffff">KitAdmin1.0.4</h1>
            <p style="color: #ffffff">
                基于SSM（SpringMVC+Spring+MyBatis）的APP开发JAVA后台管理框架
            </p>
        </center>
    </div>
</div>

<!-- body container -->
<div class="container">
    <center>
        <div style="max-width:1100px;">

            <!--链接导航-->
            <div class="link-container clearfix">
                <a href="http://www.openkit.cc" target="_blank">
                    <i class="layui-icon">&#xe68e;</i> 官网
                </a>
                <a href="http://www.openkit.cc/docs/kitadmin/#/" target="_blank">
                    <i class="layui-icon">&#xe705;</i> 文档
                </a>
                <a href="http://www.openkit.cc" target="_blank">
                    <i class="layui-icon">&#xe63f;</i> OpenKit
                </a>
                <a href="http://bbs.openkit.cc" target="_blank">
                    <i class="layui-icon">&#xe606;</i> 社区
                </a>
                <a href="https://gitee.com/benhailong/kitAdmin1.0" target="_blank">
                    <i class="layui-icon">&#xe60c;</i> git
                </a>
                <a href="http://admin.openkit.cc/admin/index" target="_blank">
                    <i class="layui-icon">&#xe756;</i> 演示
                </a>
                <a href="http://www.benhailong.com" target="_blank">
                    <i class="layui-icon">&#xe612;</i> 作者博客
                </a>
            </div><!--链接导航 end-->

            <!--demo-->
            <div style="text-align:left;">
                <div id="kitEditor"><p>kitEditor 富文本编辑器</b> 简约版本</p></div>
            </div>
            <textarea id="kitEditorText" style="width:100%; height:200px;display: none"></textarea>
            <!--demo end-->
            <%-- 编辑器 加强版 --%>

            <div style="height: 20px"></div>

            <form name="example" method="post" action="<%=basePath%>apiCommon/test">
    <textarea name="content1" cols="100" rows="8" style="width:700px;height:350px;visibility:hidden;">
    kitEditor 富文本编辑器 增强版本
    </textarea>
            </form>
            <%-- 编辑器 加强版 --%>

            <!--最新跟新-->
            <div class="info-container">
                <p class="title">
                    <i class="layui-icon">&#x1002;</i>
                    最新更新
                </p>
                <ul>
                    <li>版本 <b>V1.0.4</b></li>
                    <li>集成了第三方的富文本编辑器，不并且实现对应多张图片或者单张图片上传的方法</li>
                    <li>OpenKit版本升级到了1.1.12</li>
                </ul>
            </div><!--最新跟新 end-->

            <!--交流方式-->
            <div class="info-container">
                <p class="title">
                    <i class="layui-icon">&#xe63a;</i>
                    交流方式
                </p>
                <ul>
                    <li>交流QQ群 <a target="_blank"
                                 href="//shang.qq.com/wpa/qunwpa?idkey=8501eea4d970827340a20ab7d51e97cb9a9f6bbf786a943ecde9638500f83be0"><b>696279396</b></a>
                    </li>
                    <li>
                        <a href="https://github.com/wangfupeng1988/wangEditor/releases" target="_blank">
                            OpenKit交流社区
                        </a>
                    </li>
                    <li>
                        <a href="http://study.163.com/course/courseMain.htm?courseId=1005001015&utm_campaign=commission&utm_source=cp-400000000396007&utm_medium=share"
                           target="_blank">
                            产品在线直播课程
                        </a>
                    </li>
                </ul>
            </div><!--交流方式 end-->

            <!--了解作者-->
            <div class="info-container">
                <p class="title">
                    <i class="layui-icon">&#xe612;</i>
                    了解作者
                </p>
                <ul>
                    <li>
                        <a href="http://www.benhailong.com" target="_blank" style="font-weight: bold">
                            关注作者的博客
                        </a>
                        -
                        贲海龙，江湖人称“老贲”或“大奔”，年薪百万的程序员，做过很多国际项目，什么谷歌无人机，百度无人驾驶什么都不在话下，一个开着跑车上班的程序员，一生没什么爱好，不爱抽烟不爱喝酒，唯一的爱好就是吹牛逼😆😆😆
                    </li>
                    <li>
                        <a href="" target="_blank" style="font-weight: bold">
                            作者视频教程
                        </a>
                        -
                        <a href="https://i.xue.taobao.com/detail.htm?spm=a2174.7365761.39b9.41.Q0W8e6&courseId=84717"
                           target="_blank">
                            《零基础PS视频教程》
                        </a>
                        -
                        <a href="http://study.163.com/course/courseMain.htm?courseId=1005001015&utm_campaign=commission&utm_source=cp-400000000396007&utm_medium=share"
                           target="_blank">
                            《Java实战编辑开发》
                        </a>
                        -
                        <a href="http://study.163.com/course/introduction/1005040029.htm?utm_campaign=commission&utm_medium=share&utm_source=cp-400000000396007"
                           target="_blank">
                            《JAVA编程实战学习班在线直播教程》
                        </a>

                    </li>
                </ul>
            </div><!--了解作者 end-->
            <!--鸣谢 start-->
            <div class="info-container" style="border-bottom:none;">
                <p class="title">
                    <i class="layui-icon">&#xe63a;</i>
                    特别鸣谢
                </p>
                <ul>
                    <li>第三方根据库： <a href="http://www.openkit.cc" target="_blank"><b>openkit</b></a></li>
                    <li>分页插件： <a href="https://pagehelper.github.io/" target="_blank"><b>pagehelper</b></a></li>
                    <li>统一mapper： <a href="https://gitee.com/free/Mapper" target="_blank"><b>mapper</b></a></li>
                    <li>前端框架： <a href="http://www.layui.com/" target="_blank"><b>layui</b></a></li>
                    <li>后台管理静态页面： <a href="https://gitee.com/besteasyteam/kit_admin" target="_blank"><b>kit_admin</b>（这个名字竟然奇迹般的跟我的项目一样儿一样儿的！）</a>
                    </li>
                    <li>富文本编辑器： <a href="http://www.wangeditor.com/" target="_blank"><b>wangEditor&nbsp;&nbsp;V3.1.0</b></a>
                    </li>

                </ul>
                <p><br></p>
                <p>嗯，下面开始不要脸了，项目这么好，你确定不给点小费？</p>
                <p><img width="500px" src="https://box.kancloud.cn/bf6f9c0e4f5a786c20d02c56b56a0eaf_600x500.png"/></p>

            </div><!--鸣谢 end-->
        </div>
    </center>
</div><!-- body container end -->

<!--footer-->
<footer>
    <center>
        <p>&copy;2018 openkit.cc&nbsp;&nbsp;项目作者：贲海龙</p>
    </center>
</footer><!--footer end-->

<script type="text/javascript" src='<%=basePath%>templates/style/benstyle/js/jquery-1.10.2.min.js'></script>
<script type="text/javascript" src='<%=basePath%>templates/style/benstyle/bootstrap-3.3.0/js/bootstrap.min.js'></script>
<script type="text/javascript" src='<%=basePath%>templates/style/wangEditor/wangEditor.min.js'></script>
<script type="text/javascript" src="<%=basePath%>templates/style/benstyle/js/kitEditor.js"></script>

</body>
</html>
