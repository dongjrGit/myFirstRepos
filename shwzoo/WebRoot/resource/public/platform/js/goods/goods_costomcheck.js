var pcount, pindex, psize = size_product;
//获取自定义分类数据
var Custom = {
    bind: function () {
        Custom.getFirstClass();
        autoxl.bind("select_shop", Custom.getShopStartwithName);
        $("input[name='select_button']").first().bind("click", function () { Custom.getClassList(1); });
    },
    getFirstClass: function () {
        $.ajax({
            url: "/platform/commodity/GetClassByFatherID",
            type: "post",
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
                type: "post",
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
    getClassList: function (index) {
        var cid = $("#tc_select").val();
        if (cid == "" || cid == undefined) {
            cid = $("#sc_select").val();
            if (cid == "" || cid == undefined) {
                cid = $("#fc_select").val();
            }
        }

        var sid = $("#select_shop").attr("data");
        $.ajax({
            url: "/platform/commodity/getCheckList",
            type: "Post",
            data: { "classid": cid, "shopid": sid, "page": index, "size": psize },
            dataType: "json",
            success: function (data) {
                if (data.code < 0) {
                    Dalert(data.desc);
                } else {
                    var listdata = {
                        list: data.data
                    }
                    var html = template('cuslist', listdata);
                    $("#list_title").html(html);
                    pcount = data.maxRow;
                    pindex = data.pageIndex;
                    //分页
                    $("#pager").html(pagination(pcount, pindex, psize, "pagelist"));
                }
            },
            error: function () {

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
            url: "/Shop/GetListStartwithName",
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
//分页回调函数
function pagelist(index) {
    Custom.getClassList(index);
}
//按钮事件绑定
$(function () {
    $("input[name=chkall]").click(checkAll);
    $("input[name=customsh]").click(function () { customchecks(2); });
    $("input[name=custombh]").click(function () { customchecks(3); });
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

//自定义分类审核 批量 ctype
function customchecks(status) {
    var cids = "";
    var chks = document.getElementsByName("chksel");
    for (var i = 0; i < chks.length; i++) {
        if (chks[i].checked) {
            if (cids == "") {
                cids = chks[i].value;
            }
            else {
                cids += "," + chks[i].value;
            }
        }
    }
    if (cids != "") {
        $.ajax({
            url: "/platform/commodity/checkCategoryList",
            type: "Post",
            data: { "ids": cids, "status": status },
            dataType: "json",
            success: function (data) {
            	 if (data.code < 0) {
            		 Dalert(data.desc);
            	 }             
            	 else{
                    Dalert(data.desc,1000);
                    location.reload();
                }
            }
        });
    }
}

//自定义分类审核 
function customcheck(id, status) {
    var action = "审核";
    if (status==3) {
        action = "驳回";
    }
    if (confirm("确定要" + action + "此分类吗？")) {
        $.ajax({
            url: "/platform/commodity/checkCategory",
            type: "Post",
            data: { "id": id, "status": status },
            dataType: "json",
            success: function (data) {
            	 if (data.code < 0) {
            		 Dalert(data.desc);
            	 }             
            	 else{
                    Dalert(data.desc,1000);
                    location.reload();
                }
            }
        });
    }
}
