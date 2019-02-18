var pcount;
var pindex;
var psize = size_product;
var cid; //商品分类id

var spubuy = {
    getlist: function (index) {
        //分类路径 商品名称 开始时间-开始 开始时间-结束  结束时间-开始  结束时间-结束
        var cid = "", name = "", s1, e1, s2, e2;
        if ($("#firstID").val() != "0" && $("#firstID").val() != undefined) {
            cid = $("#firstID").val();
        }
        if ($("#secondID").val() != "0" && $("#secondID").val() != undefined) {
            cid += "," + $("#secondID").val();
        }
        if ($("#thirdID").val() != "0" && $("#thirdID").val() != undefined) {
            cid = "," + $("#thirdID").val();
        }
        name = $("#name").val();
        s1 = $("#starts").val();
        e1 = $("#starte").val();
        s2 = $("#ends").val();
        e2 = $("#ende").val();
        var sid = $("#select_shop").attr("data");
        var status = $("#checkstatus").val();
        $.ajax({
            url: "/ProductBuying/P_GetList",
            type: "Post",
            data: { "classpath": cid, "proname": name, "index": index, "size": psize, "start1": s1, "end1": e1, "start2": s2, "end2": e2, "shopid": sid, "checkss": status },
            dataType: "json",
            success: function (data) {
                if (data.Code < 0) {
                    Dalert(data.Desc);
                } else {
                    var listdata = {

                        list: data.Data
                    }

                    var html = template('buylist', listdata);

                    //html 填充
                    $("#datalist").html(html);
                    pcount = data.MaxRow;
                    pindex = data.PageIndex;

                    //分页
                    $("#pager").html(pagination(pcount, pindex, psize, "pagelist"));
                }
            },
            error: function () {

            }
        });
    },
    /*
callback 成功 有数据时 调的方法 
event 事件
*/
    getShopStartwithName: function (callback, event) {
        var name = $("#select_shop").val();
        if (event)
            name += String.fromCharCode(event.keyCode);
        $.ajax({
            url: "/Shop/GetListStartwithName",
            type: "Post",
            data: { "name": name },
            dataType: "json",
            success: function (data) {

                if (data.Code == 0) {
                    var listdata = {
                        list: data.Data
                    }
                    var html = template('select_shoplist', listdata);

                    if (callback) {
                        callback(html);
                    }
                } else {
                    Dalert(data.Data);
                }
            }
        });
    }
}
//翻页
function pagelist(index) {
    spubuy.getlist(index);

}

//页面加载触发事件
$(function () {
    autoxl.bind("select_shop", spubuy.getShopStartwithName, true);
    $("input[name=ch_All]").click(checkAll);
    $("input[name=btnadd]").bind("click", add);
    $("input[name=delAll]").bind("click", dels);
    $("input[name=btnsearch]").bind("click", function () {  spubuy.getlist(1); });
});

//添加跳转
function add() {
    self.location = "goods_BuyAdd";
}
//删除
function Del(buyid) {

    if (confirm('确定将此记录删除?')) {
        $.ajax({
            url: "/ProductBuying/P_Delete",
            type: "Post",
            data: { id: buyid },
            dataType: "json",
            success: function (data) {
                if (data.Code < 0) {
                    Dalert(data.Desc);
                }
                else {
                    pagelist(pindex);
                }
            },
            error: function () {
                Dalert("删除失败");
            }
        });
    }
}
//禁用启用
function setStatus(id, ss) {
    $.ajax({
        url: "/ProductBuying/P_ChangeStatus",
        type: "Post",
        data: { "id": id, "ss": ss },
        dataType: "json",
        success: function (data) {
            if (data.Code < 0) { Dalert(data.Desc); }
            else {
                var td_html = "";
                if (ss == 0) {
                    td_html = "<span class='lvs'><a href='#' onclick=setStatus(" + id + ",1)>启用</a></span>";
                }
                else {
                    td_html = "<span class='lvs'><a href='#' onclick=setStatus(" + id + ",0)>禁用</a></span>";
                }
                $("#td_" + id).html(td_html);
            }
        },
        error: function () {
            Dalert("修改状态失败");
        }
    });
}
//排序
function setOrder(id, ob) {
    var obtext = $("#" + ob).val();
    $.ajax({
        url: "/ProductBuying/P_ChangeOrder",
        type: "Post",
        data: { "id": id, "orderby": obtext },
        dataType: "json",
        success: function (data) {
            Dalert(data.Desc);
        },
        error: function () {
            Dalert("修改排序失败");
        }
    });
}
//全选
function checkAll() {
    if ($("input[name=ch_All]").attr("checked")) {
        $("input[name=ck_list]").attr("checked", true);
    }
    else {
        $("input[name=ck_list]").attr("checked", false);
    }
}
//批量删除
function dels() {
    if (confirm('确定批量删除选中记录吗?')) {
        var buyids = "";
        var chks = document.getElementsByName("ck_list");
        for (var i = 0; i < chks.length; i++) {
            if (chks[i].checked) {
                if (buyids == "") {
                    buyids = chks[i].value;
                }
                else {
                    buyids += "," + chks[i].value;
                }
            }
        }
        if (buyids != "") {
            $.ajax({
                url: "/ProductBuying/P_Dels",
                type: "Post",
                data: { ids: buyids },
                dataType: "json",
                success: function (data) {
                    if (data.Code < 0) {
                        Dalert(data.Desc);
                    }
                    else {
                        pagelist(pindex);
                    }
                },
                error: function () {
                    Dalert("删除失败");
                }
            });
        }
    }
}
//审核
function Check(id, ss) {
    $.ajax({
        url: "/ProductBuying/P_ChangeChackStatus",
        type: "Post",
        data: { "id": id, "ss": ss },
        dataType: "json",
        success: function (data) {
            if (data.Code < 0) { Dalert(data.Desc); }
            else {
                var td_html = "";
                if (ss == 2) {
                    td_html = "<span>审核通过</span>";
                }
                else {
                    td_html = "<span>审核不通过</span>";
                }
                $("#check_" + id).html(td_html);
                td_html = "<a href='goods_BuyUpdate?id=" + id + "'><span class='shenlan'>编辑</span></a><span class='marrig35'></span>";
                td_html += "<a href='javascript:void(0);' onclick='Del(" + id + ")'><span class='shenlan'>删除</span></a>";
                $("#cz_" + id).html(td_html);
            }
        },
        error: function () {
        }
    });
}