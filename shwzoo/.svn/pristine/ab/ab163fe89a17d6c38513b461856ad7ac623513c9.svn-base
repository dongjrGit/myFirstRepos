var color = "#ffffff";
function init()
{
    //data_list样式
    /* 当鼠标移到表格上是，当前一行背景变色 */
    $(".data_list tr td").mouseover(function () {
        $(this).parent().find("td").css("background-color", "#d5f4fe");
    });

    /* 当鼠标在表格上移动时，离开的那一行背景恢复 */
    $(".data_list tr td").mouseout(function () {
        var bgc = $(this).parent().attr("bg");
        $(this).parent().find("td").css("background-color", bgc);
    });

    $(".data_list tr:odd td").css("background-color", color);  //改变偶数行背景色
    /* 把背景色保存到属性中 */
    $(".data_list tr:odd").attr("bg", color);
    $(".data_list tr:even").attr("bg", "#F7F7F7");

    //glance-over-con样式
    $(".glance-over-con table tr:odd td").css("background-color", color);  //改变偶数行背景色
    /* 把背景色保存到属性中 */
    $(".glance-over-con table tr:odd").attr("bg", color);
    $(".glance-over-con table tr:even").attr("bg", "#ccc");

    //sctp-con样式
    $(".sctp-con ul li").mouseenter(function () {
        $(this).find(".sctp-con-dele").css("display", "block");
    })
    $(".sctp-con ul li").mouseleave(function () {
        $(this).find(".sctp-con-dele").css("display", "none");
    })
    $(".sctp").click(function () {
        $(".sctp-con").css("display", "block");
    })
    $(".close").click(function () {
        $(".sctp-con").hide();
    });
    $(".text-center td").css("text-align", "center");

    $(function () {
        $("input[type=file]").change(function () { $(this).parents(".uploader").find(".filename").val($(this).val()); });
        $("input[type=file]").each(function () {
            if ($(this).val() == "") { $(this).parents(".uploader").find(".filename").val("批量导入商品"); }
        });
    });

    $("#medo").click(function () {
        $(this).css({ "display": "none" });
        $("#medo2").css({ "display": "block" });
        $(".logo").hide();
        $(".greet").css({ "left": "20px" });
        $("#left").hide();
        $("#content").css({ "margin-left": "0px" });
        $(".message-con").css({ "left": "20px" });
        $(".ddgl").css({ "padding-left": "20px" });
    });
    $("#medo2").click(function () {
        $(this).css({ "display": "none" });
        $("#medo").css({ "display": "block" });
        $(".logo").show();
        $(".greet").css({ "left": "330px" });
        $("#left").show();
        $("#content").css({ "margin-left": "300px" });
        $(".message-con").css({ "left": "330px" });
        $(".ddgl").css({ "padding-left": "320px" });
    });

    $("#screening").click(function () {
        var odiv = document.getElementById('screening-con')
        if (odiv.style.display == 'block') {
            odiv.style.display = 'none'
            $("#screening").html("<a>更多筛选条件 &or;</a>");
            $("#screening a:hover").css('text-decoration', 'none');
        }
        else {
            odiv.style.display = 'block'
            $("#screening").html("<a>精简筛选条件 &and;</a>");
            $("#screening a:hover").css('text-decoration', 'none');
        }
    });

    //weather-left-ddgl 样式
    $(".weather-left-ddgl tr:odd td").css("background-color", color);  //改变偶数行背景色
    /* 把背景色保存到属性中 */
    $(".weather-left-ddgl tr:odd").attr("bg", color);
    $(".weather-left-ddgl tr:even").attr("bg", "#F7F7F7");

    //data_list_detailed样式
    /* 当鼠标移到表格上是，当前一行背景变色 */
    $(".data_list_detailed tr td").mouseover(function () {
        $(this).parent().find("td").css("background-color", "#d5f4fe");
    });

    /* 当鼠标在表格上移动时，离开的那一行背景恢复 */
    $(".data_list_detailed tr td").mouseout(function () {
        var bgc = $(this).parent().attr("bg");
        $(this).parent().find("td").css("background-color", bgc);
    });

    $(".data_list_detailed tr:odd td").css("background-color", color);  //改变偶数行背景色
    /* 把背景色保存到属性中 */
    $(".data_list_detailed tr:odd").attr("bg", color);
    $(".data_list_detailed tr:even").attr("bg", "#F7F7F7");

    $(".unwind-two").click(function () {
        $(this).toggleClass("unwind-three");
        $(this).parents("tr").next().find(".tab-scale").toggle();
        $(this).parents("tr").next().first().toggle();

    });
    $(".unwind").click(function () {
        $(".tab-scale").css({ "display": "block", "width": "100%" });
        $(".tab-scale").parents("tr").css({ "display": "table-row", "width": "100%" });//
        $(".tab-scale").parents("td").css({ "width": "100%" });
        $(".tab-scale").find(".table-con-mk-con").css({ "display": "table-row", "width": "100%" });
        $(".unwind-two").addClass("unwind-three");
    });
    $(".unwind-one").click(function () {
        $(".tab-scale").css({ "display": "none", "width": "100%" });
        $(".tab-scale").parents("tr").css({ "display": "none", "width": "100%" });
        $(".tab-scale").find(".table-con-mk-con").css({ "display": "none", "width": "100%" });
        $(".unwind-two").removeClass("unwind-three");
    });

    //日期+星期
    var day = "";
    var month = "";
    var ampm = "";
    var ampmhour = "";
    var myweekday = "";
    var year = "";
    mydate = new Date();
    myweekday = mydate.getDay();
    mymonth = mydate.getMonth() + 1;
    myday = mydate.getDate();
    myyear = mydate.getYear();
    year = (myyear > 200) ? myyear : 1900 + myyear;
    if (myweekday == 0)
        weekday = " 星期日 ";
    else if (myweekday == 1)
        weekday = " 星期一 ";
    else if (myweekday == 2)
        weekday = " 星期二 ";
    else if (myweekday == 3)
        weekday = " 星期三 ";
    else if (myweekday == 4)
        weekday = " 星期四 ";
    else if (myweekday == 5)
        weekday = " 星期五 ";
    else if (myweekday == 6)
        weekday = " 星期六 ";
    /*alert(t);*/
    $(".time-one").text(year + "年" + mymonth + "月" + myday + "日 " + weekday);

    $('#t1 > tbody td:even').css('background', '#F7F7F7');
    $('#t1 > tbody td:even').css('font-weight', 'bolder');
    $('#t1 > tbody td:even').css('color', '#333');
    $('#t1 > tbody td:odd').css('background', '#ffffff');

    $("#SelectAll").change(function () {

        if ($(this).attr("checked") == "checked") {
            $("[name=chk_list]:checkbox").each(function () {
                $(this).attr("checked", true);
            })
        }
        else {
            $("[name=chk_list]:checkbox").each(function () {
                $(this).attr("checked", false);
            })
        }
    });
    $("#SelectAll-one").click(function () {
        var flag = $(this).attr("checked");
        $("[name=chk_list]:checkbox").each(function () {
            $(this).attr("checked", flag);
        })
    });

    $(".browse-red").click(function () {
        $(".glance-over-con").css("display", "block")
    });
    $(".go-back").click(function () {
        $(".glance-over-con").css("display", "none")
    });
}
