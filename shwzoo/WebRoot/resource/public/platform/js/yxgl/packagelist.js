//组合商品列表
var pcount, pindex, psize = size_product;
var pack = {
    bind: function () {
        autoxl.bind("select_shop", pack.getShopStartwithName, true);
        $("input[name=btnsearch]").bind("click", function () { pack.getlist(1); });
        $("input[name=btncheck]").bind("click", function () { pack.getChecklist(1); });
        $("input[name=btnadd]").bind("click", function () { location.href = "yxgl_PackageAdd"; });
        $("input[name=chkall]").click(checkAll);
        $("input[name=checks]").bind("click", function () { checks(); });
    },
    getlist: function (index) {
        //所属店铺 编号 名称 审核状态 开始时间-开始 开始时间-结束  结束时间-开始  结束时间-结束
        var sid = "", num="",checkss="", name = "", s1, e1, s2, e2;
        var sid = $("#select_shop").attr("data");
        num = $("#num").val();
        name = $("#name").val();
        checkss = $("#checkss").val();
        s1 = $("#starts").val();
        e1 = $("#starte").val();
        s2 = $("#ends").val();
        e2 = $("#ende").val();
        $.ajax({
            url: "/platform/package/getList",
            type: "Post",
            data: {
                "page": index, "size": psize, "startf": s1, "startt": e1, "endf": s2, "endt": e2,
                "num": num, "name": name, "status": checkss, "shopid": sid
            },
            dataType: "json",
            success: function (data) {
                if (data.code < 0) {
                    Dalert(data.desc);
                } else {
                    var listdata = {

                        list: data.data
                    }

                    var html = template('packlist', listdata);

                    //html 填充
                    $("#datalist").html(html);
                    pcount = data.maxRow;
                    pindex = data.pageIndex;
                    //绑定删除事件
                    pack.unit();
                    //分页
                    $("#pager").html(pagination(pcount, pindex, psize, "pack.getlist"));
                }
            },
            error: function () {

            }
        });
    },
    getChecklist: function (index) {
        //所属店铺 编号 名称 开始时间-开始 开始时间-结束  结束时间-开始  结束时间-结束
        var sid = "", num = "", checkss = "", name = "", s1, e1, s2, e2;
        var sid = $("#select_shop").attr("data");
        num = $("#num").val();
        name = $("#name").val();
        s1 = $("#starts").val();
        e1 = $("#starte").val();
        s2 = $("#ends").val();
        e2 = $("#ende").val();
        $.ajax({
            url: "/platform/package/getCheckList",
            type: "Post",
            data: {
            	 "page": index, "size": psize, "startf": s1, "startt": e1, "endf": s2, "endt": e2,
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
                    pack.docheck();
                    //分页
                    $("#pager").html(pagination(pcount, pindex, psize, "pack.getChecklist"));
                    $("input[name=chksel]").attr("checked", false);
                }
            },
            error: function () {

            }
        });
    },
    unit: function () {
        $(".del").each(function () {
            $(this).bind("click", pack.del);
        });
        $(".ischeck").each(function () {
            $(this).bind("click", pack.ischeck);
        });
    },
    docheck: function () {
        $(".check").each(function () {
            $(this).bind("click", pack.check);
        });
    },
    check: function () {
        var id = $(this).attr("data-id");
        $.ajax({
            url: "/platform/package/updateIsCheck",
            type: "Post",
            data: { "id": id },
            dataType: "json",
            success: function (data) {
                if (data.code == 0) {
                    Dalert(data.desc, 1000);
                    pack.getChecklist(pindex);
                } else {
                    Dalert(data.desc);
                }
            }
        })
    },
    del: function () {
        if (confirm("确定要删除吗？")) {
            var id = $(this).attr("data");
            $.ajax({
                url: "/platform/package/deletePackage",
                type: "Post",
                data: { "id": id },
                dataType: "json",
                success: function (data) {
                    if (data.code == 0) {
                        Dalert(data.desc, 1000);
                        pack.getlist(pindex);
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
            url: "/platform/package/updateIsCheck",
            type: "Post",
            data: { "id": id },
            dataType: "json",
            success: function (data) {
                if (data.code == 0) {
                    Dalert(data.desc, 1000);
                    var td_html = "";
                    td_html = "<a href='yxgl_PackageDetail?id=" + id + "'><span class='shenlan'>查看</span></a>";
                    td_html += "<span class='marrig35'></span>";
                    td_html += "<a href='yxgl_PackageEdit?id=" + id + "'><span class='shenlan'>编辑</span></a>";
                    td_html += "<span class='marrig35'></span>";
                    td_html += "<a href='yxgl_PackageSku?id=" + id + "'><span class='shenlan'>关联商品</span></a>";
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
            url: "/platform/shop/getShopStartWithName",
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

function checkAll() {
    if ($("input[name=chkall]").prop("checked")) {
        $("input[name=chksel]").prop("checked", true);
    }
    else {
        $("input[name=chksel]").prop("checked", false);
    }
}
//组合商品审核 批量
function checks() {
    var packids = "";
    if (confirm("您确定要批量审核吗？")) {
        var chks = document.getElementsByName("chksel");
        for (var i = 0; i < chks.length; i++) {
            if (chks[i].checked) {
                if (packids == "") {
                    packids = chks[i].value;
                }
                else {
                    packids += "," + chks[i].value;
                }
            }
        }
        if (packids != "") {
            $.ajax({
                url: "/platform/package/updateIsCheckList",
                type: "Post",
                data: { "ids": packids },
                dataType: "json",
                success: function (data) {
                    //Dalert(data.Desc);
                    if (data.code == 0) {
                        Dalert(data.desc, 1000);
                        pack.getChecklist(pindex);
                    } else {
                        Dalert(data.desc);
                    }
                }
            });
        }
    }
}
//禁用启用
function setStatus(id, ss) {
    $.ajax({
        url: "/platform/package/updateStatus",
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