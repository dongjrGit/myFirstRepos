/*控制面板--物流公司--更新/添加（暂时无用）*/
$(document).ready(function () {
    //验证表单
    function check() {
        return $("#updatelogForm").validate();
    }
    //保存
    $("#submit_ok").click(function () {
        if (check().form()) {
            LogisticsCom();
        }
    })
    var id = GetQueryStringByName("logID");
    var isadd = GetQueryStringByName("isEdOrAd");

    if (isadd == "0") {
        $(".tjcpxx-title").html("编辑公司");
        GetByID(id);
    } else {
        $(".tjcpxx-title").html("添加公司");
    }

    function LogisticsCom() {
        var comname = $("#comname").val();
        var comtitle = $("#comtitle").val();
        var type = $("input[name='comType']:checked").val();
        var sta = $("#logsta").val();
        if (isadd == 1) {
            add(comname, comtitle, type, sta);
        }
        else {
            edit(id, comname, comtitle, type, sta);
        }
    }


});
//获取表单 id：物流公司id
function GetByID(id) {
    $.ajax({
        type: "post",
        url: "/LogisticsCom/GetByID",
        dataType: "json",
        data: { id: id },
        success: function (rsl) {
            if (rsl.Code < 0) {
                Dalert(rsl.Desc);
            }
            else {
                var lg = rsl.Data;
                $("#comname").attr("value", lg.Name);
                $("#comtitle").attr("value", lg.ShortName);
                if (lg.Type == "0") {
                    $("#kdgs").attr("checked", true);
                    $("#wlgs").attr("checked", false);
                }
                else {
                    $("#kdgs").attr("checked", false);
                    $("#wlgs").attr("checked", true);
                }
                if (lg.Status == "0") {
                    $("#logsta")[0].selectedIndex = 0;
                }
                else {
                    $("#logsta")[0].selectedIndex = 1;
                }
            }
        },
        error: function (e) {
            //Dalert(e.statusText);
        }
    })
}
//添加 name：名称，title：标题，type：类型，sta:状态
function add(name, title, type, sta) {
    $.ajax({
        type: "post",
        url: "/LogisticsCom/AddLogistics",
        dataType: "json",
        data: { name: name, shortName: title, type: type, status: sta },
        success: function (rsl) {
            if (rsl.Code < 0) {
                Dalert(rsl.Desc);
            }
            else {
                Dalert(rsl.Desc, "", backhref);
                //location.href = "LogisticsCom";
            }
        },
        error: function (e) {
            Dalert(e.statusText);
        }
    })
}
//编辑id：物流公司id， name：名称，title：标题，type：类型，sta:状态
function edit(id, name, title, type, sta) {
    $.ajax({
        type: "post",
        url: "/LogisticsCom/EditLogistics",
        dataType: "json",
        data: { id: id, name: name, shortName: title, type: type, status: sta },
        success: function (rsl) {
            if (rsl.Code < 0) {
                Dalert(rsl.Desc);
            }
            else {
                Dalert(rsl.Desc, "", backhref);
                //location.href = "LogisticsCom";
            }
        },
        error: function (e) {
            Dalert(e.statusText);
        }
    })
}

function backhref() {
    location.href = "LogisticsCom";
}

