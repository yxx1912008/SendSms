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
    <title>支付配置</title>
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
                <li class="layui-this">整体配置</li>
                <li>支付宝</li>
                <li>微信</li>
                <li>支付测试</li>
                <li>使用说明</li>
            </ul>
            <div class="layui-tab-content" style="height: 100px;">
                <%--整体配置--%>
                <div class="layui-tab-item layui-show">
                    <form class="layui-form layui-form" action="">
                        <div class="layui-form-item">
                            <label class="layui-form-label"><span class="layui-badge-dot"></span> &nbsp;支付宝</label>
                            <div class="layui-input-block">
                                <input type="checkbox" <c:if test="${ps.payIsAlipay==1}">checked=""</c:if> name="open"  lay-skin="switch" lay-filter="alipayType" lay-text="开启|关闭">
                            </div>
                        </div>

                        <div class="layui-form-item">
                            <label class="layui-form-label"><span class="layui-badge-dot"></span> &nbsp;微信</label>
                            <div class="layui-input-block">
                                <input type="checkbox" <c:if test="${ps.payIsWeixin==1}">checked=""</c:if> name="open" lay-skin="switch" lay-filter="weixinType" lay-text="开启|关闭">
                            </div>
                        </div>

                    </form>

                </div>
                <%--支付宝--%>

                <div class="layui-tab-item">
                        <form class="layui-form layui-form" action="">
                            <div class="layui-form-item">
                                <label class="layui-form-label"><span class="layui-badge-dot"></span> &nbsp;AppId</label>
                                <div class="layui-input-inline" style="width: 40%">
                                    <input type="text" name="psaAppId" lay-verify="required" value="${psa.psaAppId}" placeholder="" autocomplete="off" class="layui-input">
                                </div>
                                <div class="layui-form-mid layui-word-aux">支付宝开发者后台提供的AppId</div>
                            </div>

                            <div class="layui-form-item">
                                <label class="layui-form-label"><span class="layui-badge-dot"></span> &nbsp;密钥</label>
                                <div class="layui-input-inline" style="width: 40%" >
                                    <%--<textarea class="layui-textarea layui-hide" name="psaPrivateKey" lay-verify="required" id="LAY_demo_editor">${psa.psaPrivateKey}</textarea>--%>
                                    <input type="password" name="psaPrivateKey" value="${psa.psaPrivateKey}" lay-verify="required" placeholder="" autocomplete="off" class="layui-input">
                                </div>
                                <div class="layui-form-mid layui-word-aux">支付宝开发者后台提供的密钥</div>
                            </div>

                            <div class="layui-form-item">
                                <label class="layui-form-label"><span class="layui-badge-dot"></span> &nbsp;公钥</label>
                                <div class="layui-input-inline" style="width: 40%" >
                                    <%--<textarea class="layui-textarea layui-hide" name="psaPublicKey" lay-verify="required" id="">${psa.psaPublicKey}</textarea>--%>
                                    <input type="text" name="psaPublicKey" value="${psa.psaPublicKey}" lay-verify="required" placeholder="" autocomplete="off" class="layui-input">
                                </div>
                                <div class="layui-form-mid layui-word-aux">支付宝开发者后台提供的公钥</div>
                            </div>

                            <div class="layui-form-item">
                                <label class="layui-form-label"><span class="layui-badge-dot"></span> &nbsp;接口地址</label>
                                <div class="layui-input-inline" style="width: 40%">
                                    <input type="text" name="psaServerUrl" value="${psa.psaServerUrl}" lay-verify="required" placeholder="" autocomplete="off" class="layui-input">
                                </div>
                                <div class="layui-form-mid layui-word-aux">支付宝提供的接口地址</div>
                            </div>

                            <div class="layui-form-item">
                                <label class="layui-form-label"><span class="layui-badge-dot"></span> &nbsp;回调前缀</label>
                                <div class="layui-input-inline" style="width: 40%">
                                    <input type="text" name="psaDoMain" value="${psa.psaDoMain}" lay-verify="required" placeholder="" autocomplete="off" class="layui-input">
                                </div>
                                <div class="layui-form-mid layui-word-aux">设置支付宝的回调地址前缀</div>
                            </div>

                            <div class="layui-form-item">
                                <label class="layui-form-label">编码格式</label>
                                <div class="layui-input-inline" >
                                    <input type="text" name="psaCharset" value="${psa.psaCharset}" lay-verify="required" value="UTF-8" placeholder="" autocomplete="off" class="layui-input" disabled="">
                                </div>
                                <div class="layui-form-mid layui-word-aux">编码格式默认 UTF8 不能修改，不可以修改</div>
                            </div>

                            <div class="layui-form-item">
                                <label class="layui-form-label">参数设置</label>
                                <div class="layui-input-inline">
                                    <input type="text" name="psaFormat" value="${psa.psaFormat}" lay-verify="required" value="JSON" placeholder="" autocomplete="off" class="layui-input" disabled="">
                                </div>
                                <div class="layui-form-mid layui-word-aux">仅仅支持 JSON，不可以修改</div>
                            </div>

                            <div class="layui-form-item">
                                <label class="layui-form-label">符号类型</label>
                                <div class="layui-input-inline">
                                    <input type="text" name="psaSignType" value="${psa.psaSignType}" lay-verify="required" value="RSA2" placeholder="" autocomplete="off" class="layui-input" disabled="">
                                </div>
                                <div class="layui-form-mid layui-word-aux">仅仅支持 RSA2，不可以修改</div>
                            </div>

                            <div class="layui-form-item">
                                <button class="layui-btn" lay-submit="required" lay-filter="alipay">提交</button>
                            </div>
                        </form>

                    </div>
                    <%--微信--%>
                <div class="layui-tab-item">
                    <form class="layui-form layui-form" action="">
                        <div class="layui-form-item">
                            <label class="layui-form-label"><span class="layui-badge-dot"></span> &nbsp;AppId</label>
                            <div class="layui-input-inline" style="width: 40%">
                                <input type="text" name="pswAppId" value="${psw.pswAppId}" lay-verify="required" placeholder="" autocomplete="off" class="layui-input">
                            </div>
                            <div class="layui-form-mid layui-word-aux">微信开发者后台提供的AppId</div>
                        </div>

                        <div class="layui-form-item">
                            <label class="layui-form-label"><span class="layui-badge-dot"></span> &nbsp;凭证</label>
                            <div class="layui-input-inline" style="width: 40%">
                                <input type="text" name="pswAppSecret" value="${psw.pswAppSecret}" lay-verify="required" placeholder="" autocomplete="off" class="layui-input">
                            </div>
                            <div class="layui-form-mid layui-word-aux">微信开发者后台提供的凭证</div>
                        </div>

                        <div class="layui-form-item">
                            <label class="layui-form-label"><span class="layui-badge-dot"></span> &nbsp;密钥</label>
                            <div class="layui-input-inline" style="width: 40%" >
                                <input type="password" name="pswAppKey" value="${psw.pswAppKey}" lay-verify="required" placeholder="" autocomplete="off" class="layui-input">
                            </div>
                            <div class="layui-form-mid layui-word-aux">微信开发者后台提供的密钥</div>
                        </div>

                        <div class="layui-form-item">
                            <label class="layui-form-label"><span class="layui-badge-dot"></span> &nbsp;商户号</label>
                            <div class="layui-input-inline">
                                <input type="text" name="pswMchid" value="${psw.pswMchid}" lay-verify="required" placeholder="" autocomplete="off" class="layui-input">
                            </div>
                            <div class="layui-form-mid layui-word-aux">请输入微信提供的商户号</div>
                        </div>

                        <div class="layui-form-item">
                            <label class="layui-form-label"><span class="layui-badge-dot"></span> &nbsp;商户密钥</label>
                            <div class="layui-input-inline" style="width: 40%">
                                <input type="password" name="pswPartnerKey" value="${psw.pswPartnerKey}" lay-verify="required" value="" placeholder="" autocomplete="off" class="layui-input" >
                            </div>
                            <div class="layui-form-mid layui-word-aux">商户号对应的秘钥</div>
                        </div>

                        <div class="layui-form-item">
                            <label class="layui-form-label"><span class="layui-badge-dot"></span> &nbsp;商户id</label>
                            <div class="layui-input-inline">
                                <input type="text" name="pswPartnerId" value="${psw.pswPartnerId}" lay-verify="required" value="" placeholder="" autocomplete="off" class="layui-input" >
                            </div>
                            <div class="layui-form-mid layui-word-aux">微信提供的商户ID</div>
                        </div>

                        <div class="layui-form-item">
                            <label class="layui-form-label"><span class="layui-badge-dot"></span> &nbsp;商品描述</label>
                            <div class="layui-input-inline" style="width: 40%">
                                <input type="text" name="pswBody" value="${psw.pswBody}" lay-verify="required" placeholder="" autocomplete="off" class="layui-input">
                            </div>
                            <div class="layui-form-mid layui-word-aux">商品描述</div>
                        </div>

                        <div class="layui-form-item">
                            <label class="layui-form-label"><span class="layui-badge-dot"></span> &nbsp;固定值</label>
                            <div class="layui-input-inline">
                                <input type="text" name="pswGrantType" value="${psw.pswGrantType}" lay-verify="required" value="" placeholder="" autocomplete="off" class="layui-input" >
                            </div>
                            <div class="layui-form-mid layui-word-aux">固定值，无需修改</div>
                        </div>

                        <div class="layui-form-item">
                            <label class="layui-form-label"><span class="layui-badge-dot"></span> &nbsp;支付地址</label>
                            <div class="layui-input-inline" style="width: 40%">
                                <input type="text" name="pswGateUrl" lay-verify="required"  value="${psw.pswGateUrl}" placeholder="" autocomplete="off" class="layui-input">
                            </div>
                            <div class="layui-form-mid layui-word-aux">微信后台提供的支付接口调用接口</div>
                        </div>

                        <div class="layui-form-item">
                            <label class="layui-form-label"><span class="layui-badge-dot"></span> &nbsp;回调地址</label>
                            <div class="layui-input-inline" style="width: 40%">
                                <input type="text" name="pswNotifyUrl" lay-verify="required"  value="${psw.pswNotifyUrl}" placeholder="" autocomplete="off" class="layui-input" >
                            </div>
                            <div class="layui-form-mid layui-word-aux">微信服务器回调通知url，就是回调前缀</div>
                        </div>

                        <div class="layui-form-item">
                            <label class="layui-form-label"><span class="layui-badge-dot"></span> &nbsp;请求IP</label>
                            <div class="layui-input-inline">
                                <input type="text" name="pswSpbillCreateip" lay-verify="required"  value="${psw.pswSpbillCreateip}" placeholder="" autocomplete="off" class="layui-input">
                            </div>
                            <div class="layui-form-mid layui-word-aux">发送微信支付的请求IP地址，一般就是你的服务器的IP地址</div>
                        </div>


                        <div class="layui-form-item">
                            <button class="layui-btn" lay-submit="" lay-filter="weixin">提交</button>
                        </div>
                    </form>
                </div>
                    <%--支付测试--%>
                <div class="layui-tab-item">
                    <form class="layui-form layui-form" action="">
                        <div class="layui-form-item">
                            <label class="layui-form-label">平台</label>
                            <div class="layui-input-inline">
                                <select name="type" lay-verify="required" lay-search="">
                                    <option value="1">支付宝</option>
                                    <option value="2">微信</option>
                                </select>
                            </div>
                        </div>

                        <div class="layui-form-item">
                            <label class="layui-form-label">支付方式</label>
                            <div class="layui-input-inline">
                                <select name="payType" lay-verify="required" lay-search="">
                                    <option value="1">APP支付</option>
                                    <%--<option value="2">网页支付</option>--%>
                                </select>
                            </div>
                        </div>

                        <div class="layui-form-item">
                            <label class="layui-form-label">费用</label>
                            <div class="layui-input-block">
                                <input name="money" lay-verify="required|number" placeholder="输入需要测试的费用" autocomplete="off" class="layui-input" type="text">
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


            // 监听支付宝开关
            form.on('switch(alipayType)', function(data){

                var a = data.elem.checked;
                var b = 0;
                var id = data.value;
                if(a){
                    b=1
                }else(
                    b=2
                )
                // 开关方法
                $.post("<%=basePath%>pay/updateAlipayPayType", {"kaiguan": b}, function (data) {
                    if (data.code == "200") {
                        layer.msg(data.msg, {icon: 1, time: 1000});
                    } else {
                        layer.msg(data.msg, {icon: 0, time: 1000});
                    }
                });

            });

            // 监听微信开关
            form.on('switch(weixinType)', function(data){

                var a = data.elem.checked;
                var b = 0;
                var id = data.value;
                if(a){
                    b=1
                }else(
                    b=2
                )
                // 开关方法
                $.post("<%=basePath%>pay/updateWeixinPayType", {"kaiguan": b}, function (data) {
                    if (data.code == "200") {
                        layer.msg(data.msg, {icon: 1, time: 1000});
                    } else {
                        layer.msg(data.msg, {icon: 0, time: 1000});
                    }
                });

            });

            //监听支付宝提交，发送请求
            form.on('submit(alipay)', function(data){

                $.post("<%=basePath%>pay/updateAlipay",data.field,function(d){
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

            //监听提交，发送请求
            form.on('submit(weixin)', function(data){

                $.post("<%=basePath%>pay/updateWeixin",data.field,function(d){
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
