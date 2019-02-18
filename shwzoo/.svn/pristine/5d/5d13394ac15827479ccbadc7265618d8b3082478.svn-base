//菜单分类
var pcount;
var pindex;
var psize = 20;
var Menu = {
    getlist: function (index) {
        var name = $("#name").val();
        var mtype = $("#selmtype").val();
        var menutype = $("#seltype").val();
        $.ajax({
            url: "/platform/menu/getList",
            type: "Post",
            data: { "page": index, "size": psize, "name": name, "type": mtype, "menutype": menutype },
            dataType: "json",
            success: function (data) {
                if (data.code < 0) {
                    Dalert(data.desc);
                } else {
                    //js template数据加载
                    var listdata = {

                        list: data.data
                    }

                    var html = template('menulist', listdata);
                    //html 填充
                    $("#datalist").html(html);

                    //ztree加载
                    pcount = data.maxRow;
                    pindex = data.pageIndex;

                    //分页
                    $("#pager").html(pagination(pcount, pindex, psize, "Menu.getlist"));
                }
            },
            error: function () {
                Dalert("数据加载失败");
            }
        });
    }
}

//获取第三级分类
function getthird() {
    tid = $("#tid").val();
    if (tid > 0) {
        $("#thirdID option").each(function () {
            if ($(this).val() == tid) {
                $(this).attr("selected", "selected");
            } else {
                $(this).removeAttr("selected");
            }
        })
    }

}

//按钮事件绑定
$(function () {
    $("input[name=add]").bind("click", add);
    $("input[name=search]").bind("click", function () { Menu.getlist(1); });
})

function add() {
    location.href = "Control_Menuadd";
}

function del(menuid) {
    if (confirm('确定将此记录删除?')) {
        $.ajax({
            url: "/platform/menu/deleteMenu",
            type: "Post",
            data: { id: menuid },
            dataType: "json",
            success: function (data) {
                if (data.code < 0) {
                    Dalert(data.desc);
                }
                else {
                	Menu.getlist(pindex);
                }
            },
            error: function () {
                Dalert("删除失败");
            }
        });
    }
}