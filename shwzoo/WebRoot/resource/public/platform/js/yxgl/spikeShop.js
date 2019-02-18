
//秒杀活动-店铺申请列表
var pcount, pindex, psize = size_common;
var spikeshop = {
    getlist: function (index) {
        var spikeid = $("#spikeid").val();
        $.ajax({
            url: "/platform/SpikeActivity/getShopBySpikeID",
            type: "Post",
            data: {
                "page": index, "size": psize, "id": spikeid
            },
            dataType: "json",
            success: function (data) {
                if (data.code < 0) {
                    Dalert(data.desc);
                } else {
                    var listdata = {

                        list: data.data
                    }

                    var html = template('spikeshoplist', listdata);

                    //html 填充
                    $("#datalist").html(html);
                    pcount = data.maxRow;
                    pindex = data.pageIndex;
                    //绑定删除事件
                    spikeshop.unit();
                    //分页
                    $("#pager").html(pagination(pcount, pindex, psize, "spikeshop.getlist"));
                    
                }
            },
            error: function () {

            }
        });
    },
    getChecklist: function (index) {
        //活动类型 活动编号 开始时间-开始 开始时间-结束  结束时间-开始  结束时间-结束
        var type1, num = "", s1, e1, s2, e2;
        type1 = $("#type_select").val();
        num = $("#num").val();
        s1 = $("#starts").val();
        e1 = $("#starte").val();
        s2 = $("#ends").val();
        e2 = $("#ende").val();
        $.ajax({
            url: "/platform/SpikeActivity/getShopApplyList",
            type: "Post",
            data: {
                "page": index, "size": psize,"start1": s1, "end1": e1, "start2": s2, "end2": e2,"stype": type1, "num": num
            },
            dataType: "json",
            success: function (data) {
                if (data.code < 0) {
                    Dalert(data.desc);
                } else {
                    var listdata = {

                        list: data.data
                    }

                    var html = template('spikelist', listdata);

                    //html 填充
                    $("#datalist").html(html);
                    pcount = data.maxRow;
                    pindex = data.pageIndex;
                    //绑定删除事件
                    spikeshop.unitcheck();
                    //分页
                    $("#pager").html(pagination(pcount, pindex, psize, "spikeshop.getChecklist"));
                    $("input[name=chksel]").attr("checked", false);
                }
            },
            error: function () {

            }
        });
    },
    unit: function () {
        $(".check").each(function () {
            $(this).bind("click", spikeshop.check);
        });
    },
    check: function () {
        if (confirm("确定要审核吗？")) {
            var id = $(this).attr("data-id");
            var checktype = $(this).attr("data-type");
            $.ajax({
                url: "/platform/SpikeActivity/updateCheck",
                type: "Post",
                data: { "ids": id, "status": checktype },
                dataType: "json",
                success: function (data) {
                    if (data.code == 0) {
                        Dalert(data.desc, 1000);
                        spikeshop.getlist(pindex);
                    } else {
                        Dalert(data.desc);
                    }
                }
            })
        }
    },
    unitcheck: function () {
        $(".check").each(function () {
            $(this).bind("click", spikeshop.checklist);
        });
    },
    checklist: function () {
        if (confirm("确定要审核吗？")) {
            var id = $(this).attr("data-id");
            var checktype = $(this).attr("data-type");
            $.ajax({
                url: "/platform/SpikeActivity/updateCheck",
                type: "Post",
                data: { "ids": id, "status": checktype },
                dataType: "json",
                success: function (data) {
                    if (data.code == 0) {
                        Dalert(data.desc, 1000);
                        spikeshop.getChecklist(pindex);
                    } else {
                        Dalert(data.desc);
                    }
                }
            })
        }
    }
}
$(function () {
    $("input[name=chkall]").click(checkAll);
    $("input[name=checks]").bind("click", function () { checks(2); });
    $("input[name=checkbhs]").bind("click", function () { checks(3); });
    $("input[name=btnsearch]").bind("click", function () { spikeshop.getChecklist(1); });
})
function checkAll() {
    if ($("input[name=chkall]").prop("checked")) {
        $("input[name=chksel]").prop({checked:true});
    }
    else {
        $("input[name=chksel]").prop({checked:false});
    }
}
//满赠活动审核 批量
function checks(checktype) {
    var ids = "";
    if (confirm("您确定要批量审核吗？")) {
        $('input[name="chksel"]:checked').each(function () {
            var id = $(this).val();
            if (ids == "") {
                ids = id;
            }
            else {
                ids += "," + id;
            }
        });
        if (ids != "") {
            $.ajax({
                url: "/platform/SpikeActivity/updateCheck",
                type: "Post",
                data: { "ids": ids, "status": checktype },
                dataType: "json",
                success: function (data) {
                    if (data.code == 0) {
                        Dalert(data.desc, 1000);
                        spikeshop.getChecklist(pindex);
                    } else {
                        Dalert(data.desc);
                    }
                }
            });
        }
    }
}