//确认提示框 
function ConfirmShow(content, okcallback, param, title) {//content：提示内容;okcallback:方法名称;param:参数
    if (content != null && content != "" && content != undefined)
        if (title == null || title == "") {
            title = '信息提示';
        }
        var d = dialog({
            width: '150px',
            height: 50,
            fixed: true,
            title: title,
            content: content,
            //okValue: '确定',
            //cancelValue: '取消',
            //ok:  function () {

            //        },
            //cancel: function () { }

            button: [
           {
               value: '确定',
               callback: function () {
                   if (okcallback != null && param != null && okcallback != "" && param != "") {
                       okcallback(param);
                   } else {
                       if (okcallback != null && okcallback != "") {
                           okcallback();
                       }
                   }

               },
               autofocus: true
           },
           {
               value: '取消',
               callback: function () {
               }
           }]
        }).show();
}
//信息提示弹出框
function Dalert(content, time, okcallback, title) {//content：提示内容;time:自动关闭时间默认2秒;okcallback:方法名称;
    if (title == null || title == "") {
        title = '信息提示';
    }
    if (content != null && content != "" && content != undefined) {
        var d = dialog({
            //width: '15em',
            height: 50,
            fixed: true,
            title: title,
            content: content
        }).show();

        if (time == null || time == undefined || time == "") {
            time = 2000;
        }
        setTimeout(function () {
            d.close();
            if (okcallback != null && okcallback != "") {
                okcallback();
            }
        }, time);
    }
}