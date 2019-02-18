//商品审核
var pcount;
var pindex;
var psize = size_product;
var cid;
var list = {
    bind: function () {
        list.getFirstClass();
        $("input[name='select_button']").first().bind("click", function () { list.getSHList(1); });
    },
    initid: function () {
        if ($("#tc_select").val() != "" && $("#tc_select").val() != undefined) {
            cid = $("#tc_select").val();
        }
        else {
            if ($("#sc_select").val() != "" && $("#sc_select").val() != undefined) {
                cid = $("#sc_select").val();
            }
            else {
                if ($("#fc_select").val() != "" && $("#fc_select").val() != undefined) {
                    cid = $("#fc_select").val();
                }
            }
        }
    },
    getFirstClass: function () {
        $.ajax({
            url: "/platform/commodity/GetClassByFatherID",
            type: "Post",
            data: {"fatherid": 0},
            dataType: "json",
            success: function (data) {
                if (data.code < 0) {
                    Dalert(data.desc);
                } else {
                    var listdata = {
                        list: data.data
                    }
                    var html = '<option value="" id="defaultfc" selected>全部</option>' + template('flist', listdata);
                    $("#fc_select").html(html);

                }
            }
        });
    },
    fatherChange: function (fc) {
        var fid = 0;
        if (fc != null) {
            if (fc == "fc") {
                fid = $("#fc_select").val();
            } else {
                fid = $("#sc_select").val();
            }
        }
        if (fid > 0) {
            $.ajax({
                url: "/platform/commodity/GetClassByFatherID",
                type: "Post",
                data: { "fatherid": fid },
                dataType: "json",
                success: function (data) {
                    if (data.code < 0) {
                        Dalert(data.desc);
                    } else {
                        var listdata = {
                            list: data.data
                        }
                        if (fc == "fc") {
                            var html = '<option value="" id="defaultsc" selected>全部</option>' + template('slist', listdata);
                            $("#sc_select").html(html);
                        } else {
                            var html = '<option value="" id="defaulttc" selected>全部</option>' + template('tlist', listdata);
                            $("#tc_select").html(html);
                        }
                    }
                }
            })
        }
        else {
            if (fc == "fc") {
                $("#sc_select").html('<option value="" id="defaultsc" selected>全部</option>');
                $("#tc_select").html('<option value="" id="defaulttc" selected>全部</option>');
            }
            else {
                $("#tc_select").html('<option value="" id="defaulttc" selected>全部</option>');
            }
        }
    }
}
//获取商品待审核列表
function getSHList(index) {
    list.initid();
    var shopid = $("#select_shop").attr("data");
    var spuname = $("#name_select").val();
    var num = $("#num_select").val();
    $.ajax({
        url: "/platform/procuctajax/p_getshopauditlist",
        type: "Post",
        data: { "cid": cid, "sid": shopid,"page": index, "size": psize, "name": spuname, "num": num },
        dataType: "json",
        success: function (data) {
            if (data.code < 0) {
                Dalert(data.desc);
            } else {
                var listdata = {
                    list: data.data
                }
                var html = template('spulist', listdata);
                $("#list_title").html(html);
                pcount = data.maxRow;
                pindex = data.pageIndex;
                $("input[name=chksel]").attr("checked", false);
                //分页
                $("#pager").html(pagination(pcount, pindex, psize, "pagelist"));

                $("#list_title").find("a").each(function (e) {
                    var $obj = $(this);
                    if ($obj.attr("data-spuid")) {
                        $obj.bind("click", spsh);
                    }
                });
            }
        },
        error: function () {

        }
    })
}
function pagelist(index) {
    list.initid();
    getSHList(index);

}
$(function () {
    $("input[name=chkall]").click(checkAll);
    $("input[name=spshs]").click(spshs);
    $("input[name=search]").click(search);
    autoxl.bind("select_shop", getShopStartwithName);
})

function checkAll() {
    if ($("input[name=chkall]").prop("checked")) {
        $("input[name=chksel]").prop("checked", true);
    }
    else {
        $("input[name=chksel]").prop("checked", false);
    }
}
//查询
function search() {
    list.initid();
    getSHList(1);
}

/*
callback 成功 有数据时 调的方法 
event 事件
*/
function getShopStartwithName(callback, event) {
    var sname = $("#select_shop").val();
    if (event)
        sname += String.fromCharCode(event.keyCode);
    $.ajax({
        url: "/platform/shop/getShopStartWithName",
        type: "Post",
        data: { "name": sname },
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
//商品审核 批量
function spshs() {
    var spid = "";
    if (confirm("您确定要批量审核吗？")) {
        var chks = document.getElementsByName("chksel");
        for (var i = 0; i < chks.length; i++) {
            if (chks[i].checked) {
                if (spid == "") {
                    spid = chks[i].value;
                }
                else {
                    spid += "," + chks[i].value;
                }
            }
        }
        if (spid != "") {
            $.ajax({
                url: "/platform/spu/shopbatchaudit",
                type: "Post",
                data: { "spuids": spid },
                dataType: "json",
                success: function (data) {
                    //Dalert(data.Desc);
                    if (data.code == 0) {
                        Dalert(data.desc, 1000);
                        pagelist(pindex);
                    } else {
                        Dalert(data.desc);
                    }
                }
            });
        }else{
        	 Dalert("未选中审核商品");
        }
    }
}
//商品审核 单个
function spsh() {
    var spuid = $(this).attr("data-spuid");
    if (confirm("您确定要审核该商品吗？")) {
        $.ajax({
            url: "/platform/spu/spuCheck",
            type: "Post",
            data: { "id": spuid },
            dataType: "json",
            success: function (data) {
                if (data.code == 0) {
                    Dalert(data.desc, 1000);
                    pagelist(pindex);
                } else {
                    Dalert(data.desc);
                }
            }
        });
    }
}

function refresh() {
location.reload();
}