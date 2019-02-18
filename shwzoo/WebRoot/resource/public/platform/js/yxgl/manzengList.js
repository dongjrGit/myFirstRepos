//满赠活动列表
var pcount, pindex, psize = size_product;
var manzeng = {
    bind: function () {
        autoxl.bind("select_shop", manzeng.getShopStartwithName, true);
        $("input[name=btnsearch]").bind("click", function () { manzeng.getlist(1); });
        $("input[name=btnadd]").bind("click", function () { location.href = "yxgl_FullgiftAdd"; });
        $("input[name=btncheck]").bind("click", function () { manzeng.getChecklist(1); });
        $("input[name=chkall]").click(checkAll);
        $("input[name=checks]").bind("click", function () { checks(); });
    },
    getlist: function (index) {
        // 开始时间-开始 开始时间-结束  结束时间-开始  结束时间-结束
        var checkss,detailtype, num, name = "", s1, e1, s2, e2;
        var sid = $("#select_shop").attr("data");
        checkss = $("#checkss").val();
        detailtype = $("#type_select").val();
        name = $("#name").val();
        num = $("#num").val();
        s1 = $("#starts").val();
        e1 = $("#starte").val();
        s2 = $("#ends").val();
        e2 = $("#ende").val();
        $.ajax({
            url: "/platform/fullgift/getList",
            type: "Post",
            data: {
            	"page": index, "size": psize, "startf": s1, "startt": e1, "endf": s2, "endt": e2, "usetype": detailtype,
                "num": num, "name": name, "shopid": sid, "status": checkss
            },
            dataType: "json",
            success: function (data) {
                if (data.code < 0) {
                    Dalert(data.desc);
                } else {
                    var listdata = {

                        list: data.data
                    }

                    var html = template('manzenglist', listdata);

                    //html 填充
                    $("#datalist").html(html);
                    pcount = data.maxRow;
                    pindex = data.pageIndex;
                    //绑定删除事件
                    manzeng.unit();
                    //分页
                    $("#pager").html(pagination(pcount, pindex, psize, "manzeng.getlist"));
                }
            },
            error: function () {

            }
        });
    },
    getChecklist: function (index) {
        //分类路径 商品名称 开始时间-开始 开始时间-结束  结束时间-开始  结束时间-结束
        var detailtype, num, name = "", s1, e1, s2, e2;
        var sid = $("#select_shop").attr("data");
        detailtype = $("#type_select").val();
        name = $("#name").val();
        num = $("#num").val();
        s1 = $("#starts").val();
        e1 = $("#starte").val();
        s2 = $("#ends").val();
        e2 = $("#ende").val();
        $.ajax({
            url: "/platform/fullgift/getCheckList",
            type: "Post",
            data: {
            	"page": index, "size": psize, "startf": s1, "startt": e1, "endf": s2, "endt": e2, "usetype": detailtype,
                "num": num, "name": name, "shopid": sid
            },
            dataType: "json",
            success: function (data) {
                if (data.code < 0) {
                    Dalert(data.desc);
                } else {
                    var listdata = {

                        list: data.data
                    }

                    var html = template('checklist', listdata);

                    //html 填充
                    $("#datalist").html(html);
                    pcount = data.maxRow;
                    pindex = data.pageIndex;
                    //绑定审核事件
                    manzeng.docheck();
                    //分页
                    $("#pager").html(pagination(pcount, pindex, psize, "manzeng.getChecklist"));
                    $("input[name=chksel]").attr("checked", false);
                }
            },
            error: function () {

            }
        });
    },
    docheck: function () {
        $(".check").each(function () {
            $(this).bind("click", manzeng.check);
        });
    },
    check: function () {
        var id = $(this).attr("data-id");
        $.ajax({
            url: "/platform/fullgift/updateCheck",
            type: "Post",
            data: { "id": id },
            dataType: "json",
            success: function (data) {
                if (data.code == 0) {
                    Dalert(data.desc, 1000);
                    manzeng.getChecklist(pindex);
                } else {
                    Dalert(data.desc);
                }
            }
        })
    },
    unit: function () {
        $(".del").each(function () {
            $(this).bind("click", manzeng.del);
        });
        $(".ischeck").each(function () {
            $(this).bind("click", manzeng.ischeck);
        });
    },
    del: function () {
        if (confirm("确定要删除吗？")) {
            var id = $(this).attr("data");
            $.ajax({
                url: "/platform/fullgift/deleteFullgift",
                type: "Post",
                data: { "id": id },
                dataType: "json",
                success: function (data) {
                    if (data.code == 0) {
                        Dalert(data.desc, 1000);
                        manzeng.getlist(pindex);
                    } else {
                        Dalert(data.desc);
                    }
                }
            })
        }
    },
    ischeck: function () {
        var id = $(this).attr("data");
        $.ajax({
            url: "/platform/fullgift/updateCheck",
            type: "Post",
            data: { "id": id },
            dataType: "json",
            success: function (data) {
                if (data.code == 0) {
                    Dalert(data.desc, 1000);
                    var td_html = "";
                    td_html = "<a href='yxgl_GiftList?id=" + id + "'><span class='shenlan'>赠品列表</span></a>";
                    td_html += "<span class='marrig35'>";
                    td_html += "<a href='yxgl_FullgiftDetail?id=" + id + "'><span class='shenlan'>查看</span></a>";
                    td_html += "<span class='marrig35'></span>";
                    td_html += "<a href='yxgl_FullgiftEdit?id=" + id + "'><span class='shenlan'>编辑</span></a>";
                    td_html += "<span class='marrig35'></span>";
                    td_html += "<a href='javascript:void(0);' class='del' data='" + id + "'><span class='shenlan'>删除</span></a>";

                    $("#cz_" + id).html(td_html);
                    $("#check_" + id).html("<span>已审核</span>");
                } else {
                    Dalert(data.desc);
                }
            }
        })
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
            url: "/platform/Shop/getShopStartWithName",
            type: "Post",
            data: { "name": name },
            dataType: "json",
            success: function (data) {

                if (data.code == 0) {
                    var listdata = {
                        list: data.data
                    }
                    var html = template('select_shoplist', listdata);

                    if (callback) {
                        callback(html);
                    }
                } else {
                    Dalert(data.desc);
                }
            }
        });
    }
}
//禁用启用
function setStatus(id, ss) {
    $.ajax({
        url: "/platform/fullgift/updateStatus",
        type: "Post",
        data: { "id": id, "status": ss },
        dataType: "json",
        success: function (data) {
            if (data.code < 0) { Dalert(data.desc); }
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
function checkAll() {
	if ($("input[name=chkall]").prop("checked") == true) {
        $("input[name=chksel]").prop("checked", true);
    }
    else {
        $("input[name=chksel]").prop("checked", false);
    }
   
}
//满赠活动审核 批量
function checks() {
    var activityids = "";
    if (confirm("您确定要批量审核吗？")) {
        var chks = document.getElementsByName("chksel");
        for (var i = 0; i < chks.length; i++) {
            if (chks[i].checked) {
                if (activityids == "") {
                    activityids = chks[i].value;
                }
                else {
                    activityids += "," + chks[i].value;
                }
            }
        }
        if (activityids != "") {
            $.ajax({
                url: "/platform/fullgift/updateCheckList",
                type: "Post",
                data: { "ids": activityids },
                dataType: "json",
                success: function (data) {
                    //Dalert(data.Desc);
                    if (data.code == 0) {
                        Dalert(data.desc, 1000);
                        manzeng.getChecklist(pindex);
                    } else {
                        Dalert(data.desc);
                    }
                }
            });
        }
    }
}