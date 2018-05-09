$(function (){
    var E = window.wangEditor
    var editor = new E('#kitEditor')
    var $text1 = $('#kitEditorText')
    editor.customConfig.onchange = function (html) {
        // 监控变化，同步更新到 textarea
        $text1.val(html)
    }

    // editor.customConfig.uploadImgShowBase64 = true
    editor.customConfig.uploadFileName = 'file'
    editor.customConfig.uploadImgServer = 'apiCommon/wUeSetImg'
    editor.create()
    // 初始化 textarea 的值
    $text1.val(editor.txt.html())
});






