/**
 * 团购图片管理
 */

var pcount, pindex, psize = 10;
var img = {
    bind: function () {
        $("input[name=btnadd]").bind("click", function () { location.href = "yxgl_GroupBuyImgEdit"; });
        img.getimglist(1);
    },
    getimglist: function (index) {
        var groupid = $("#groupid").val();
        $.ajax({
            url: "/seller/shopgroupbuy/getImgList",
            type: "Post",
            data: {
                "page": index, "size": psize, "id": groupid
            },
            dataType: "json",
            success: function (data) {
                if (data.code < 0) {
                    Dalert(data.desc);
                } else {
                    var listdata = {

                        list: data.data
                    }

                    var html = template('imglist', listdata);

                    //html 填充
                    $("#datalist").html(html);
                    pcount = data.maxRow;
                    pindex = data.pageIndex;
                    //绑定删除事件
                    img.unit();
                    //分页
                    $("#pager").html(pagination(pcount, pindex, psize, "img.getimglist"));
                }
            },
            error: function () {

            }
        });
    },
    unit: function () {
        $(".del").each(function () {
            $(this).bind("click", img.del);
        });
    },
    del: function () {
        if (confirm("确定要删除吗？")) {
            var id = $(this).attr("data");
            $.ajax({
                url: "/seller/shopgroupbuy/delimg",
                type: "Post",
                data: { "id": id },
                dataType: "json",
                success: function (data) {
                    if (data.code == 0) {
                        Dalert(data.desc, 1000);
                        img.getimglist(pindex);
                    } else {
                        Dalert(data.desc);
                    }
                }
            })
        }
    }
}

//按钮事件绑定
$(function () {
    $("input[name=Save]").bind("click", Save);
})
//保存
function Save() {
    var action = $("#action").val();
    if (check().form()) {
        //防止重复提交 点击保存后隐藏按钮
        $("input[name='Save']").hide();
        $.ajax({
            url: "/seller/shopgroupbuy/" + action,
            type: "Post",
            data: $("#form").serialize(),
            dataType: "json",
            success: function (data) {
                if (data.code < 0) {
                    $("input[name='Save']").show();
                    Dalert(data.desc);
                }
                else {
                    Dalert("保存成功！", "", function () { window.location.href = 'yxgl_GroupBuyImgList?id='+$("#groupid").val(); });
                }
            },
            error: function () {
            }
        });
    }
}
//保存前参数验证
function check() {
    return $("#form").validate({
        rules: {
            img: {
                required: true
            },
            sort: {
                digits: true
            }
        },
        message: {
        	img: {
                required: "图片不能为空",
            },
            sort: {
                required: "现价不能为空",
                digits: true
            }
        }
    });
}
//更新排序
function setStatus(id, ob) {
	 var obtext = $("#" + ob).val();
    $.ajax({
        url: "/seller/shopgroupbuy/updateImgSort",
        type: "Post",
        data: { "id": id, "sort": obtext },
        dataType: "json",
        success: function (data) {
        	Dalert(data.desc);
        },
        error: function () {
            Dalert("修改排序失败");
        }
    });
}