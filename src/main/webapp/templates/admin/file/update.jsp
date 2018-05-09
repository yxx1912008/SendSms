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
    <title>文件存储配置</title>
    <link rel="stylesheet" href="<%=basePath%>templates/style/plugins/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="<%=basePath%>templates/style/build/css/doc.css" media="all">
    <script src="<%=basePath%>templates/style/plugins/layui/layui.js"></script>
    <script src="<%=basePath%>templates/style/benstyle/js/json.js"></script>
    <script src="<%=basePath%>templates/style/plugins/layui/jquery-3.3.1.min.js"></script>
</head>
<body>

    <div class="kit-doc">
        <!--这里写页面的代码-->
        <div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
            <ul class="layui-tab-title">
                <li class="layui-this">七牛云</li>
                <li>存储测试</li>
                <li>使用说明</li>
            </ul>
            <div class="layui-tab-content" style="height: 100px;">

                <%--极光推送--%>

                <div class="layui-tab-item layui-show">
                    <form class="layui-form layui-form" action="">
                        <div class="layui-form-item">
                            <label class="layui-form-label"><span class="layui-badge-dot"></span> Accesskey</label>
                            <div class="layui-input-inline" style="width: 40%">
                                <input type="text" name="fqAccesskey" lay-verify="required" value="${qiniu.fqAccesskey}" placeholder="" autocomplete="off" class="layui-input">
                            </div>
                            <div class="layui-form-mid layui-word-aux">七牛后台查看Accesskey</div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label"><span class="layui-badge-dot"></span> &nbsp;Secretkey</label>
                            <div class="layui-input-inline" style="width: 40%">
                                <input type="text" name="fqSecretkey" lay-verify="required" value="${qiniu.fqSecretkey}" placeholder="" autocomplete="off" class="layui-input">
                            </div>
                            <div class="layui-form-mid layui-word-aux">七牛后台查看Secretkey</div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label"><span class="layui-badge-dot"></span> &nbsp;Bucket</label>
                            <div class="layui-input-inline" style="width: 40%">
                                <input type="text" name="fqBucket" lay-verify="required" value="${qiniu.fqBucket}" placeholder="" autocomplete="off" class="layui-input">
                            </div>
                            <div class="layui-form-mid layui-word-aux">创建的空间名字</div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label"><span class="layui-badge-dot"></span> &nbsp;机房信息</label>
                            <div class="layui-input-inline" style="width: 40%">
                                <select name="fqZone" lay-filter="aihao" lay-verify="required">
                                    <option value="zone0" <c:if test="${qiniu.fqZone == 'zone0'}">selected</c:if> >华东</option>
                                    <option value="zone1" <c:if test="${qiniu.fqZone == 'zone1'}">selected</c:if> >华北</option>
                                    <option value="zone2" <c:if test="${qiniu.fqZone == 'zone2'}">selected</c:if> >华南</option>
                                    <option value="zoneNa0" <c:if test="${qiniu.fqZone == 'zoneNa0'}">selected</c:if> >北美</option>
                                </select>
                            </div>
                            <div class="layui-form-mid layui-word-aux">创建的空间名字</div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label"><span class="layui-badge-dot"></span> &nbsp;访问域名</label>
                            <div class="layui-input-inline" style="width: 40%">
                                <input type="text" name="fqUrl" lay-verify="required" value="${qiniu.fqUrl}" placeholder="" autocomplete="off" class="layui-input">
                            </div>
                            <div class="layui-form-mid layui-word-aux">创建的空间名字</div>
                        </div>


                        <div class="layui-form-item">
                            <button class="layui-btn" lay-submit="required" lay-filter="qiniu">提交</button>
                        </div>
                    </form>

                </div>

                    <%--测试--%>
                <div class="layui-tab-item">
                    <form class="layui-form layui-form" action="">
                        <div class="layui-form-item">
                            <label class="layui-form-label">平台</label>
                            <div class="layui-input-inline">
                                <select name="type" lay-verify="required" lay-search="">
                                    <option value="1">极光推送</option>
                                </select>
                            </div>
                        </div>

                        <div class="layui-form-item">
                            <label class="layui-form-label">推送方式</label>
                            <div class="layui-input-inline">
                                <select name="payType" lay-verify="required" lay-search="">
                                    <option value="1">推送给所有</option>
                                    <option value="2">推送给一类或者一个</option>
                                    <option value="3">透传给所有</option>
                                    <option value="4">透传给一类或者一个</option>
                                </select>
                            </div>
                        </div>

                        <div class="layui-form-item">
                            <label class="layui-form-label">用户ID</label>
                            <div class="layui-input-block">
                                <input name="money" lay-verify="required|number" placeholder="请输入推送的用户ID，如果是所有用户，可以不用填" autocomplete="off" class="layui-input" type="text">
                            </div>
                        </div>


                        <div class="layui-form-item">
                            <button class="layui-btn" lay-submit="" lay-filter="test">提交</button>
                        </div>
                        <div class="kit-doc-title">
                            <fieldset>
                                <legend><a name="blockquote">接口返回值</a></legend>
                            </fieldset>
                        </div>

                    </form>

                    <div class="layui-form-item">
                            <textarea id="json" style="width: 100%;height: 300px;overflow-y :auto;background-color: #E5E5E5;border-radius: 5px;padding: 5px;border: solid 1px #c9c9c9">

                            </textarea>
                        <div style="height: 20px;"></div>
                        <button class="layui-btn layui-btn-normal" lay-filter="format" onclick="format();">格式化</button>
                        <button class="layui-btn layui-btn-warm" lay-filter="zip" onclick="zip();">压缩</button>
                    </div>

                </div>
                <%--使用说明--%>
                <div class="layui-tab-item">
                    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
                        <legend>直播</legend>
                    </fieldset>
                    <blockquote class="layui-elem-quote">
                        <font style="color: red">OpenKit系列产品作者免费在线直播教学：</font>
                        <a href="http://study.163.com/course/courseMain.htm?courseId=1005001015&utm_campaign=commission&utm_source=cp-400000000396007&utm_medium=share" target="_blank">点击这里</a>
                    </blockquote>
                    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
                        <legend>说明</legend>
                    </fieldset>

                    <blockquote class="layui-elem-quote">
                       是的，你没有看错，为了降低开发门槛，提升开发效率。我们再次做了一次尝试，在现实生活中，微信跟支付宝，是水火不相容。正所谓一山不容二虎，但是我们在 KitAdmin 中，却奇迹般的将这两只老虎变成亲家，我们的APP支付接口没有那么复杂，只要一行代码即可完成服务器端对支付宝和微信接口的调用，这是历史上从未有过的事情。
                    </blockquote>

                    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
                        <legend>调用</legend>
                    </fieldset>

                    <blockquote class="layui-elem-quote layui-quote-nm">
                        这里希望大家注意一下<br/>
                        1. 首先我们必须要确定后台的配置都是OK的<br/>
                        2. 同时你需要在后台打开微信支付和支付宝支付<br/>
                    </blockquote>

                    <font>
                        首先我们需要创建一个支付对象 KitPay ，你可以用三种方式创建这个对象，就像下面这样
                    </font>

                    <pre class="layui-code">
/**
 * // 下面是支付宝和微信都要填写
 * String type：支付方式 如果是微信，请传“WEIXINPAY”，如果是支付宝“ALIPAY”
 * String money：价格
 * String orderNumber：订单号
 * String notifyUrl：订单号
 *
 * // 下面是支付宝的必填
 * String body：商品描述
 * String subject：商品名称
 * String timeoutExpress：有效时间  （new 空对象的时候已经添加，没有特殊要求建议不要修改，这里默认设置30分钟）
 * String productCode：支付方式 （new 空对象的时候已经添加，没有特殊要求建议不要修改，这里默认设置：QUICK_MSECURITY_PAY 表示即时到账）
 */

// new 一个空对象，让后根据你需要的参入传入对应的值即可
KitPay kitPay = new KitPay();

// 实例化支付宝对象属性
KitPay kitPay = new KitPay(String body, String subject, String orderNumber, String money, String notifyUrl);

// 实例化微信对象属性
KitPay kitPay = new KitPay(String money, String orderNumber, String notifyUrl);

// 实例化所有对象属性
KitPay kitPay = new KitPay(String type, String money, String orderNumber, String notifyUrl, String body, String subject, String timeoutExpress, String productCode);</pre>

                    <font>
                        有了这个方法之后想触发APP支付的Service业务就显得非常Easy。
                    </font>

                    <pre class="layui-code">
Map<String,Object> map = new HashMap<String,Object>();
map = paySettingService.kitAppPayUnifyAPI(request,response,payUtil);</pre>

                    <font>
                        最后只要将 Map 对象以任何形式返还给APP端即可，APP端凭借次凭证支付，记得还要写回调接口哦，如果毁掉地址不会写的，可以看下OpenKit文档中的：（<a href="http://doc.openkit.cc">http://doc.openkit.cc</a>）支付模块，里边有详细介绍哦。
                    </font>

                    <blockquote class="layui-elem-quote">
                        对，你没有看错，你只要设置一个type，我们就可以直接帮你实现支付宝和微信支付的APP端的所有功能
                    </blockquote>

                </div>
            </div>
        </div>
        <!--这里写页面的代码-->
    </div>

    <script>
        layui.use(['form', 'layedit', 'laydate', 'element', 'layer'], function(){
            var form = layui.form,
                layer = layui.layer,
                element = layui.element;


            //监听七牛云提交，发送请求
            form.on('submit(qiniu)', function(data){

                $.post("<%=basePath%>file/updateQiniu",data.field,function(d){
                    // 获取 session
                    if(d.code!=200){
                        layer.alert(d.msg, {offset: 't',icon: 5});
                    }
                    if(d.code==200){
                        layer.alert(d.msg, {offset: 't',icon: 6});
                    }
                });
                return false;
            });



            //监听测试按钮，发送请求
            form.on('submit(test)', function(data){
                $.post("<%=basePath%>pay/test",data.field,function(d){
                    // 获取 session
                    $('#json').html(jsonFormat(JSON.stringify(d)));
                });
                return false;
            });
        });

        // 格式化
        function format() {
            $('#json').html(jsonFormat($('#json').val()));
        };
        // 压缩
        function zip(){
            $('#json').html(jsonFormat($('#json').val(), true));
        }


    </script>
</body>
</html>
