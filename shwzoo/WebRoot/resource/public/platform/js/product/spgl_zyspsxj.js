//直营商品上下架
var pcount, pindex, psize = size_product;
var SPU = {
    bind: function () {
        SPU.getFirstClass();

        $("input[name='select_button']").first().bind("click", function () { SPU.getSPUList(1, SPU.unit); });
    },
    getFirstClass: function () {
        $.ajax({
        	 url: "/platform/commodity/GetClassByFatherID",
             type: "post",
             data: { 'fatherid': 0 },
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
    },
    getSPUList: function (index) {
        var cid = $("#tc_select").val();
        if (cid == "" || cid == undefined) {
            cid = $("#sc_select").val();
            if (cid == "" || cid == undefined) {
                cid = $("#fc_select").val();
            }
        }
        var name = $("#name_select").val();
        var num = $("#num_select").val();
        var status = $("#status_select").val();
        $.ajax({
            url: "/platform/procuctajax/p_getregularshelveslist",
            type: "Post",
            data: { "cid": cid, "page": index, "size": psize, "name": name, "num": num, "status": status },
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
                    $("#pager").html(pagination(pcount, pindex, psize, "SPU.getSPUList"));
       
                    $("#list_title").find("a").each(function (e) {
                        var $obj = $(this);
                        if ($obj.attr("data-type")) {
                            $obj.bind("click", spsxj);
                        }

                    });
//                    //状态记录绑定
//                    $(".l_xsztdj").bind("click", function (e) {
//                        //鼠标点击上一个div没有关闭
//                        if ($('.l_xsztcon').css('display', 'block')) {
//                            $('.l_xsztcon').css('display', 'none');
//                        }
//                        var spuid = $(this).attr("data-id");
//                        $.ajax({
//                            url: "/Product/GetStatusRecords",
//                            type: "Post",
//                            data: { "spuid": spuid, "index": 1, "size": size_prostatus },
//                            dataType: "json",
//                            success: function (data) {
//                                Dalert(data.Desc);
//                                if (data.Code == 0) {
//                                    var rehtml = "";
//                                    for (var i = 0; i < data.Data.length ; i++) {
//                                        rehtml += "<li>";
//                                        rehtml += "商品状态：";
//                                        if (data.Data[i].NewValue == 1) rehtml += "提交审核中";
//                                        else if (data.Data[i].NewValue == 2 || data.Data[i].NewValue == 4) rehtml += "下架";
//                                        else if (data.Data[i].NewValue == 3) rehtml += "上架";
//                                        else if (data.Data[i].NewValue == 5) rehtml += "冻结";
//                                        else if (data.Data[i].NewValue == 6) rehtml += "解冻申请中";
//                                        rehtml += "</li>";
//
//                                        rehtml += "<li>操作时间：" + data.Data[i].CreateTime + "</li>";
//                                        rehtml += "<li>操作人：" + data.Data[i].CreateUserName;
//                                        rehtml += "</li>";
//                                    }
//                                    $(".l_xszt ul").html(rehtml);
//                                    if (data.Data.length > 0)
//                                        $("#divrecord_" + spuid).show();
//                                }
//                            }
//                        })
//                        stopPropagation(e);
//                    });
                    $(document).bind('click', function () {
                        $('.l_xsztcon').css('display', 'none');
                    });
                }
            },
            error: function () {

            }
        })
    }
}
function pagelist(index) {
    SPU.getSPUList(index);
}
$(function () {
    $("input[name=chkall]").click(checkAll);
    $("input[name=spsj]").click(function () { spsxjs('2,4'); });
    $("input[name=spxj]").click(function () { spsxjs('3'); });
    $("input[name='select_button']").first().bind("click", function () { SPU.getSPUList(1); });
})
//全选
function checkAll() {
    if ($("input[name=chkall]").prop("checked")) {
        $("input[name=chksel]").prop("checked", true);
    }
    else {
        $("input[name=chksel]").prop("checked", false);
    }
}
//商品上下架
function spsxjs(value) {
    var spid = "", status = "", action = "", type;
    if (value == '3') {
        action = "下架"; type = 4;
    } else { action = "上架"; type = 3; }
    var chks = document.getElementsByName("chksel");
    for (var i = 0; i < chks.length; i++) {
        if (chks[i].checked) {
            status = chks[i].value;
            if (value.indexOf(status.split("_")[1]) < 0) {
                Dalert(action + "操作有误，查看勾选框并确认商品状态。");
                document.getElementById($(chks[i]).attr('id')).focus();
                spid = "";
                break;
            }
            else {
                if (spid == "") {
                    spid = status.split("_")[0];
                }
                else {
                    spid += "," + status.split("_")[0];
                }
            }
        }
    }
    if (spid.length > 0) {
        $.ajax({
            url: "/platform/spu/regularpbatchshelves",
            type: "Post",
            data: { "spuids": spid, "status": type },
            dataType: "json",
            success: function (data) {
                if (data.Code < 0) {
                    Dalert(data.Desc);
                } else {
                    Dalert(action + "成功",1000);
                    pagelist(pindex);
                }
            },
            error: function () {

            }
        })
    }
}

//商品上下架 单个
function spsxj() {
    var $obj = $(this);
    var spid = $obj.attr("data-spuid");
    var value = $obj.attr("data-type");
    var action = "", type;
    if (value == '4') {
        action = "下架"; type = 4;
    } else { action = "上架"; type = 3; }
    if (confirm("您确定要" + action + "吗？")) {
        $.ajax({
            url: "/platform/spu/zyshelve",
            type: "Post",
            data: { "id": spid, "status": type },
            dataType: "json",
            success: function (data) {
                if (data.Code < 0) {
                    Dalert(data.Desc);
                } else {
                    Dalert(action + "成功", 1000);
                    $("#chk_"+spid).val(spid+"_"+value);
                    ChangeStatus(type, $obj);
                }
            },
            error: function () {

            }
        })
    }
}
//商品状态变化不刷新页面
function ChangeStatus(type, $obj) {
    var $prev = $obj.parent().prev();
    switch (parseInt(type)) {
        case 3:
            $obj.html("<span class='shenlan'>下架</span>"); // 已上架
            $obj.attr("data-type", 4);

            $prev.find("span").first().html("<a class='lanse'>已上架</a>");
            break;
        case 4:
            $obj.html("<span class='shenlan'>上架</span>"); //已下架
            $obj.attr("data-type", 3);

            $prev.find("span").first().html("<a class='red'>已下架</a>");
            break;
    }
}

function refresh() {
location.reload();
}

//对div的click事件绑定事件处理程序，阻止事件冒泡，防止其冒泡到document，而调用document的onclick方法隐藏了该div。
function stopPropagation(e) {
    if (e.stopPropagation)
        e.stopPropagation();
    else
        e.cancelBubble = true;
}