//商品自定义分类
var pcount;
var pindex;
var psize = 10;
var claname = "";
//页面加载数据
var spfl = {
    getlist: function (index) {
        var datahtml = "";
        var cid = $("#tc_select").val();
        if (cid == "" || cid == undefined) {
            cid = $("#sc_select").val();
            if (cid == "" || cid == undefined) {
                cid = $("#fc_select").val();
            }
        }
        var name = $("#select_name").val();
        var status = $("#status_select").val();;
        $.ajax({
            url: "/platform/shop/queryCategorys",
            type: "Post",
            data: { "page": index, "size": psize, "fid": cid, "name": name, "status": status },
            dataType: "json",
            success: function (data) {
                if (data.code < 0) {
                    Dalert(data.desc);
                } else {
                	 var listdata = {
                             list: data.data
                         }
                    var html=template('customlist', listdata);
                    $("#datalist").html(html);
                    pcount = data.maxRow;
                    pindex = data.pageIndex;

                    //分页
                    $("#pager").html(pagination(pcount, pindex, psize, "spfl.getlist"));
                }
            },
            error: function () {
                //Dalert("数据加载失败");
            }
        });
    },
    bind: function () {
        $("#select_button").bind("click", function () { spfl.getlist(1); });
        spfl.getFirstClass();
      },
      getFirstClass: function () {
    $.ajax({
        url: "/platform/shop/getClassByFatherID",
        type: "post",
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
            url: "/platform/shop/getClassByFatherID",
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
}
 }


//按钮绑定事件
$(function () {
    $("input[name=spflAdd]").bind("click", function(){self.location = "spgl_customEdit";});
})

//删除
function del(classid) {
    if (confirm('确定将此记录删除?')) {
        $.ajax({
            url: "/platform/shop/deleteCategory",
            type: "Post",
            data: { 'id': classid },
            dataType: "json",
            success: function (data) {
                if (data.code < 0) {
                    Dalert(data.desc);
                }
                else {
                	spfl.getlist(pindex);
                }
            },
            error: function () {
                Dalert("删除失败");
            }
        });
    }
}
//更改状态
function ChangeStatus(classid) {
    
    $.ajax({
        url: "/platform/shop/subReview",
        type: "Post",
        data: { 'id': classid},
        dataType: "json",
        success: function (data) {
            if (data.code < 0) {
                Dalert(data.desc);
            }
            else {
            	var td_html="<span>审核中</span>";
            	$("#td_"+classid).html(td_html);
            	td_html=" <span class='bjxx'>编辑</span> ";                                       
            	td_html+=" <span class='marrig35'></span>";
            	td_html+=" <a href='javascript:void(0);' onclick='del("+classid+")><span class='shenlan'>删除</span></a>";
            	$("#cz_"+classid).html(td_html);
            }
        },
        error: function () {

        }
    });
}