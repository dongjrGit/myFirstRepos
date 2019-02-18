/*文章管理*/
var title,moduleid=0;
$(document).ready(function () {
	//获取模块id信息
	moduleid=$("#moduleid").val();
	title=$("#ArtTitle").val();
    Message.bind(1);
    
    //删除信息
    $("body").on("click",".delete", function() {
        var id = $(this).parent().find("input").val();
        ConfirmShow("确认要删除文章吗？", del, id);

    });
    //编辑
    $("body").on("click",".bjxx", function() {
        var id = $(this).parent().find("input").val();
        location.href = "/platform/zd/showArticleManagerAdd?id=" + id+"&partid="+moduleid;
    });
    
    //全选
    $("#SelectAll").click(function () {
        if ($("#SelectAll").prop('checked')) {
            $("input[name=chk_list]").prop("checked", true);
        }
        else {
            $("input[name=chk_list]").prop("checked", false);
        }
    })
    //全部删除信息
    $("#delete_all").click(function () {
        var idList = "";
        $("input[name=chk_list]:checked").each(function () {
            var id = $(this).val();
            idList += id + ",";
        });
        if (idList != "") {
            idList = idList.substring(0, idList.length - 1);

            ConfirmShow("确认要删除选取文章吗？", dellist, idList);
        }
        else {
            Dalert("还没有选取哦");

        }

    });
    //添加跳转
    $("#addbtn").click(function () {
        location.href = "/platform/zd/showArticleManagerAdd?partid="+moduleid;
    });
    //文章搜索
    $("#searchBtn").click(function () {
        var t = $("#ArtTitle").val();
        Message.bind(1, t)
    });
});
var Message = {
    bind: function (index, t) {
        $.ajax({
            url: "/platform/page/getArticleList",
            type: "post",
            data: { page: index, size: 10, title: t,partid:moduleid },
            dataType: "json",
            success: function (data) {
                if (data.code < 0) {
                    alert(data.desc);
                } else {
                    var listdata = {
                        list: data.data
                    }
                    var html = template('datalist', listdata);
                    $("#trlist").siblings().remove();
                    $("#trlist").after(html);
                    //分页
                    pcount = data.maxRow;
                    pindex = data.pageIndex;
                    title = t;
                    $("#pager").html(pagination(pcount, pindex, 10, "pagelist"));
                }
            },
            error: function () {
            }
        });
    }
}
function pagelist(index) {
    Message.bind(index, title);
}

function del(id) {
    $.ajax({
        type: "post",
        url: "/platform/page/delArticles",
        dataType: "json",
        data: {
            id: id
        },
        success: function (rsl) {
            if (rsl.code == 0) {
                Dalert(rsl.desc,"",refresh);
            }
            else {
                Dalert(rsl.desc);
            }
        },
        error: function (e) {
            //Dalert(e.statusText);
        }
    });
}
function dellist(idList) {
    $.ajax({
        type: "post",
        url: "/platform/page/delArticlesList",
        dataType: "json",
        data: {
        	idList: idList
        },
        success: function (rsl) {
            if (rsl.code == 0) {
                Dalert(rsl.desc); Message.bind(1);
            }
            else {
                Dalert(rsl.desc);
            }
        },
        error: function (e) {
            //Dalert(e.statusText);
        }
    })
}

function refresh() {
    location.reload();
}