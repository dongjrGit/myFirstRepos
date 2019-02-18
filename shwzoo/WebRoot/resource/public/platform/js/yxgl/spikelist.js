
//秒杀活动列表
var pcount, pindex, psize = size_common;
var spike = {
    bind: function () {
        $("input[name=btnsearch]").bind("click", function () { spike.getlist(1); });
        if($("#type_select").val()==3){
        	$("input[name=btnadd]").bind("click", function () { location.href = "yxgl_ExcitingAdd"; });
        }else{
        	$("input[name=btnadd]").bind("click", function () { location.href = "yxgl_SpikeAdd"; });
        }
        
    },
    getlist: function (index) {
        //活动类型 活动编号 开始时间-开始 开始时间-结束  结束时间-开始  结束时间-结束
        var type1, num = "", s1, e1, s2, e2;
        type1 = $("#type_select").val();
        num = $("#num").val();
        s1 = $("#starts").val();
        e1 = $("#starte").val();
        s2 = $("#ends").val();
        e2 = $("#ende").val();
        $.ajax({
            url: "/platform/SpikeActivity/getList",
            type: "Post",
            data: {
                "page": index, "size": psize, "start1": s1, "end1": e1, "start2": s2, "end2": e2,"stype": type1, "num": num
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
                    spike.unit();
                    //分页
                    $("#pager").html(pagination(pcount, pindex, psize, "spike.getlist"));
                }
            },
            error: function () {

            }
        });
    },
    getexcitlist: function (index) {
        //活动类型 活动编号 开始时间-开始 开始时间-结束  结束时间-开始  结束时间-结束
        var type1, num = "", s1, e1, s2, e2;
        type1 = $("#type_select").val();
        num = $("#num").val();
        s1 = $("#starts").val();
        e1 = $("#starte").val();
        s2 = $("#ends").val();
        e2 = $("#ende").val();
        $.ajax({
            url: "/platform/SpikeActivity/getexcitingList",
            type: "Post",
            data: {
                "page": index, "size": psize, "start1": s1, "end1": e1, "start2": s2, "end2": e2,"stype": type1, "num": num
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
                    spike.unit();
                    //分页
                    $("#pager").html(pagination(pcount, pindex, psize, "spike.getlist"));
                }
            },
            error: function () {

            }
        });
    },
    unit: function () {
        $(".del").each(function () {
            $(this).bind("click", spike.del);
        });
    },
    del: function () {
        if (confirm("确定要删除吗？")) {
            var id = $(this).attr("data");
            $.ajax({
                url: "/platform/SpikeActivity/delete",
                type: "Post",
                data: { "id": id },
                dataType: "json",
                success: function (data) {
                    if (data.code == 0) {
                        Dalert(data.desc, 1000);
                        spike.getlist(pindex);
                    } else {
                        Dalert(data.desc);
                    }
                }
            })
        }
    }
}
//禁用启用
function setStatus(id, ss) {
    $.ajax({
        url: "/platform/SpikeActivity/changeStatus",
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