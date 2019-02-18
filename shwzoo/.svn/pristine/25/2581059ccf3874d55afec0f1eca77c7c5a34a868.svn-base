//商品列表
var pindex = 1;
var psize = 20, pcount;
var SPU = {
    bind: function () {
        $("#select_button").bind("click", function () { SPU.getSPUList(1, SPU.unit); });
        SPU.getFirstClass();
    },
    getSPUList: function (index, callback) {
        var cid = $("#tc_select").val();
        if (cid == "" || cid == undefined) {
            cid = $("#sc_select").val();
            if (cid == "" || cid == undefined) {
                cid = $("#fc_select").val();
            }
        }
        var bid = $("#select_brand").val();
        var pronum = $("#select_pronum").val();
        var proname = $("#select_proname").val();
        var status = $("#status_select").val();
        $.ajax({
            url: "/seller/shopproduct/s_getshopList",
            type: "Post",
            data: { "cid": cid, "bid": bid, "status": status, "page": index, "size": psize, "num": pronum, "name": proname },
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

                    //分页
                    $("#pager").html(pagination(pcount, pindex, psize, "pagelist"));
                    if (callback) {
                        callback();
                    }
//                    //状态记录绑定
//                    $(".l_xsztdj").bind("click", function (e) {
//                        //鼠标点击上一个div没有关闭
//                        if ($('.l_xsztcon').css('display', 'block')) {
//                            $('.l_xsztcon').css('display', 'none');
//                        }
//                        var spuid = $(this).attr("data-id");
//                        $.ajax({
//                            url: "/Product_Seller/GetStatusRecords",
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
            }
        })
    },
    //列表删除按钮绑定
    unit: function () {
        $(".del").each(function () {
            $(this).bind("click", SPU.del);
        });
    },
    del: function () {
        if (confirm("确定要删除吗？")) {
            var id = $(this).attr("data");
            $.ajax({
                url: "/seller/shopproduct/deletShopSpu",
                type: "Post",
                data: { "id": id },
                dataType: "json",
                success: function (data) {
                    Dalert(data.desc);
                    if (data.code == 0) {
                        location.reload();
                    }
                }
            })
        }
    },
    getFirstClass: function () {
        $.ajax({
            url: "/seller/shopcategory/getClassByFatherID",
            type: "Post",
            data: {'fatherid':0},
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
                    //if (callback)
                    //    callback();
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
                url: "/seller/shopcategory/getClassByFatherID",
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
//分页回调加载数据
function pagelist(index) {
    SPU.getSPUList(index, SPU.unit);
}

//对div的click事件绑定事件处理程序，阻止事件冒泡，防止其冒泡到document，而调用document的onclick方法隐藏了该div。
function stopPropagation(e) {
    if (e.stopPropagation)
        e.stopPropagation();
    else
        e.cancelBubble = true;
}