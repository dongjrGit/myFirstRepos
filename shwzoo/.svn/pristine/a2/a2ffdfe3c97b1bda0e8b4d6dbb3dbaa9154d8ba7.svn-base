// JavaScript Document

/* 当鼠标移到表格上是，当前一行背景变色 */
$(document).ready(function () {
    $(".data_list tr td").mouseover(function () {
        $(this).parent().find("td").css("background-color", "#d5f4fe");
    });
})
/* 当鼠标在表格上移动时，离开的那一行背景恢复 */
$(document).ready(function () {
    $(".data_list tr td").mouseout(function () {
        var bgc = $(this).parent().attr("bg");
        $(this).parent().find("td").css("background-color", bgc);
    });
})

$(document).ready(function () {
    var color = "#ffffff"
    $(".data_list tr:odd td").css("background-color", color);  //改变偶数行背景色
    /* 把背景色保存到属性中 */
    $(".data_list tr:odd").attr("bg", color);
    $(".data_list tr:even").attr("bg", "#F7F7F7");
})

$(document).ready(function () {
    $(".sctp").click(function () {
        $(".sctp-con").css("display", "block")
    })
	
})
