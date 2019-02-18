//商品上下架
var pcount;
var pindex;
var psize = 10;
var spgl = {
    bind: function () {
        $("#select_button").bind("click", function () { spgl.Getlist(1); });
        spgl.getFirstClass();
    },
    getFirstClass: function () {
        $.ajax({
            url: "/seller/shopcategory/getClassByFatherID",
            type: "Post",
            data: {"fatherid": 0 },
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
    },
    //获取商品列表
    Getlist: function (index) {
        var cid = $("#tc_select").val();
        if (cid == "" || cid == undefined) {
            cid = $("#sc_select").val();
            if (cid == "" || cid == undefined) {
                cid = $("#fc_select").val();
            }
        }
        var pronum = $("#select_pronum").val();
        var proname = $("#select_proname").val();
        var prostatus = $("#status_select").val();
        $.ajax({
            url: "/seller/shopproduct/s_getshopshelveslist",
            type: "Post",
            data: { "page": index, "size": psize, "cid": cid, "num": pronum, "name": proname, "status": prostatus },
            dataType: "json",
            success: function (data) {
                if (data.code < 0) {
                    Dalert(data.desc);
                } else {
                    var listdata = {
                        list: data.data
                    }
                    var html = template('splist', listdata);
                    //html 填充
                    $("#datalist").html(html);

                    pcount = data.maxRow;
                    pindex = data.pageIndex;
                    spgl.unit();
                    //分页
                    $("#pager").html(pagination(pcount, pindex, psize, "pagelist"));
                    $("input[name=chksel]").attr("checked", false);
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
    },
    unit:function(){
      //绑定上下架和申请解冻事件
      $("#datalist").find("a").each(function (e) {
          var $obj = $(this);
          if ($obj.attr("data-type") != undefined) {
              if ($obj.attr("data-type") == "3" || $obj.attr("data-type") == "4") {
                  $obj.bind("click", spsxj);
              }
              else {
                  $obj.bind("click", ApplyForThaw);
              }
          }

      });
    }
}

//分页回调加载数据
function pagelist(index) {
    spgl.Getlist(index);
}

$(function () {
    $("input[name=chkall]").click(checkAll);
    $("input[name=sqjd]").click(ApplyForThaws);
})


//申请解冻 批量
function ApplyForThaws() {
    var spid = "", status = "";
    if (confirm("您确定要批量申请解冻吗？")) {
        var chks = document.getElementsByName("chksel");
        for (var i = 0; i < chks.length; i++) {
            if (chks[i].checked) {
                status = chks[i].value;
                if (status.split("_")[1] == 5) {
                    if (spid == "") {
                        spid = status.split("_")[0];
                    }
                    else {
                        spid += "," + status.split("_")[0];
                    }

                }
                else {
                    Dalert("申请解冻操作有误，不是冻结状态。");
                    document.getElementById($(chks[i]).attr('id')).focus();
                    spid = "";
                    break;
                }
            }
        }
        if (spid.length > 0) {
            $.ajax({
                url: "/seller/shopproduct/applyForThawList",
                type: "Post",
                data: { "spuids": spid },
                dataType: "json",
                success: function (data) {
                    if (data.code < 0) {
                        Dalert(data.desc);
                    } else {
                        Dalert("申请解冻成功", 1000);

                        pagelist(pindex);
                    }
                },
                error: function () {

                }
            })
        }
    }
}
//申请解冻 单个
function ApplyForThaw() {
    var $obj = $(this);
    var spid = $obj.attr("data-spuid");
    if (confirm("您确定要申请解冻该商品吗？")) {
        $.ajax({
            url: "/seller/shopproduct/applyForThaw",
            type: "Post",
            data: { "id": spid },
            dataType: "json",
            success: function (data) {
                if (data.code < 0) {
                    Dalert(data.desc);
                } else {
                    Dalert("申请解冻成功");
                    $("#chk_"+spid).val(spid+"_6");
                    ChangeStatus(6, $obj);
                }
            },
            error: function () {

            }
        })
    }
}

function checkAll() {
    if ($("input[name=chkall]").prop("checked") == true) {
        $("input[name=chksel]").prop("checked", true);
    }
    else {
        $("input[name=chksel]").prop("checked", false);
    }
}
//商品上下架 批量
function spsxjEdit(value) {
    var spid = "", status = "", action = "", type;
    if (value == '2,4') {
        action = "上架"; 
        type=3;
    } else { action = "下架";  type=4;}
        var chks = document.getElementsByName("chksel");
        for (var i = 0; i < chks.length; i++) {
            if (chks[i].checked) {
                status = chks[i].value;
                if (confirm("您确定要批量" + action + "吗？")) {
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
        }
        if (spid.length > 0) {
            $.ajax({
                url: "/seller/shopproduct/shopbatchshelves",
                type: "Post",
                data: { "spuids": spid, "status": type },
                dataType: "json",
                success: function (data) {
                    if (data.code < 0) {
                        Dalert(data.desc);
                    } else {
                        Dalert(action + "成功", 1000);
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
    var action = "", type;
    var $obj = $(this);
    var spid = $obj.attr("data-spuid");
    var value = $obj.attr("data-type");
    if (value == '4') {
        action = "下架"; 
    } else { action = "上架"; }
    if (confirm("您确定要" + action + "吗？")) {
        $.ajax({
            url: "/seller/shopproduct/shelve",
            type: "Post",
            data: { "id": spid, "status": value },
            dataType: "json",
            success: function (data) {
                if (data.code < 0) {
                    Dalert(data.desc);
                } else {
                    Dalert(action + "成功", 1000);
                    $("#chk_"+spid).val(spid+"_"+value);
                    ChangeStatus(value, $obj);
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
            $obj.html("下架"); // 已上架
            $obj.attr("data-type", 4);
            $prev.find("span").first().html("已上架");
            //$prev.html("已上架");
            break;
        case 4:
            $obj.html("上架"); //已下架
            $obj.attr("data-type", 3);
            $prev.find("span").first().html("已下架");
            //$prev.html("已下架");
            break;

        case 6:
            $obj.html("解冻申请中"); //解冻申请
            $obj.attr("data-type", false);
            $prev.find("span").first().html("解冻申请中");
            //$prev.html("解冻申请中");
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