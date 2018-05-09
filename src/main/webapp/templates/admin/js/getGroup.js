/**
 * 验证是否有权限
 *
 * adminid 后台用户的id
 * kitWeb 网站名称
 * actionid 权限id
 */
function getGroup(actionid){
    // 全局设置ajax
    $.ajaxSetup ({
        async: false
    });

    // 获取cookie里边的值
    var adminid = getCooken("adminid");
    var kitWeb = getCooken("kitWeb");

    // 为空的拦截
    if(adminid==""||adminid==null){
        layer.alert('由于你长时间没有操作，请重新登录!', {offset: 't',icon: 6});
        top.location="/admin/index";
    }
    if(kitWeb==""||kitWeb==null){
        layer.alert('由于你长时间没有操作，请重新登录!', {offset: 't',icon: 6});
        top.location="/admin/index";
    }
    // 组装数据
    var params = new URLSearchParams();
    params.append('adminid', adminid);
    params.append('kitWeb', kitWeb);
    params.append('actionid', actionid);

    var a = '';

    // 请求
    // $.post("/gGroupLimit/getGroup",{'adminid':adminid,'kitWeb':kitWeb,actionid:actionid},function(data){
    $.post("/gGroupLimit/getGroup",params,function(data){
        // 获取 session
        if(data.code!=200){
            layer.alert(data.msg, {offset: 't',icon: 6});
            top.location="/admin/index";
        }
        if(data.code==200){
            var returnmsg = data.limit.groupType;
            // layer.alert(returnmsg);
            a = returnmsg;
        }
    });
   // alert(a)
    return a;
};

/**
 * 读取cookie
 */

function getCooken(name){
    var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
    if(arr=document.cookie.match(reg)){
        return unescape(arr[2]);
    }else{
        return null;
    }
};

/**
 * 删除 cookie
 */
function delCookie(name) {
    var exp = new Date();
    exp.setTime(exp.getTime() - 1);
    var cval=getCookie(name);
    if(cval!=null)
        document.cookie= name + "="+cval+";expires="+exp.toGMTString();
}

/**
 * 存储cookie
 * s20是代表20秒
 * m20表示20分钟
 * h是指小时，如12小时则是：h12
 * d是天数，30天则：d30
 *
 * @param str
 * @returns {number}
 */
function setCookie(name,value,time) {
    var strsec = getsec(time);
    var exp = new Date();
    exp.setTime(exp.getTime() + strsec*1);
    document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString();
}

/**
 * 时间转换
 * @param str
 * @returns {number}
 */
function getsec(str){
    var str1=str.substring(1,str.length)*1;
    var str2=str.substring(0,1);
    if (str2=="s") {
        return str1*1000;
    } else if (str2=="m"){
        return str1*60*1000;
    } else if (str2=="h") {
        return str1*60*60*1000;
    } else if (str2=="d") {
        return str1*24*60*60*1000;
    }
}


