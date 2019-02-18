var pcount;
var pindex;
var psize = 10;
var cid; //商品分类id

var spubuy = {
    getlist: function (index) {
        //分类路径 商品名称 开始时间-开始 开始时间-结束  结束时间-开始  结束时间-结束
        var cid = "", name = "", s1, e1, s2, e2;
        if ($("#firstID").val() != "0" && $("#firstID").val() != undefined) {
            cid = $("#firstID").val();
        }
        if ($("#secondID").val() != "0" && $("#secondID").val() != undefined) {
            cid += "," + $("#secondID").val();
        }
        if ($("#thirdID").val() != "0" && $("#thirdID").val() != undefined) {
            cid = "," + $("#thirdID").val();
        }
        name = $("#name").val();
        s1 = $("#starts").val();
        e1 = $("#starte").val();
        s2 = $("#ends").val();
        e2 = $("#ende").val();
        $.ajax({
            url: "/ProductBuying/S_GetList",
            type: "Post",
            data: { "classpath": cid, "proname": name, "index": index, "size": psize, "start1": s1, "end1": e1, "start2": s2, "end2": e2 },
            dataType: "json",
            success: function (data) {
                if (data.Code < 0) {
                    Dalert(data.Desc);
                } else {
                    var listdata = {

                        list: data.Data
                    }

                    var html = template('buylist', listdata);

                    //html 填充
                    $("#datalist").html(html);
                    pcount = data.MaxRow;
                    pindex = data.PageIndex;

                    //分页
                    $("#pager").html(pagination(pcount, pindex, psize, "pagelist"));
                }
            },
            error: function () {

            }
        });
    }
}
//翻页
function pagelist(index) {
    spubuy.getlist(index);

}

//页面加载触发事件
$(function () {
    $("input[name=ch_All]").click(checkAll);
    $("input[name=btnadd]").bind("click", add);
    $("input[name=delAll]").bind("click", dels);
    $("input[name=btnsearch]").bind("click", function () {  spubuy.getlist(1); });
});

//添加跳转
function add() {
    self.location = "goods_BuyAdd";
}
//删除
function Del(buyid) {

    if (confirm('确定将此记录删除?')) {
        $.ajax({
            url: "/ProductBuying/S_Delete",
            type: "Post",
            data: { id: buyid },
            dataType: "json",
            success: function (data) {
                if (data.Code < 0) {
                    Dalert(data.Desc);
                }
                else {
                    pagelist(pindex);
                }
            },
            error: function () {
                Dalert("删除失败");
            }
        });
    }
}
//禁用启用
function setStatus(id, ss) {
    $.ajax({
        url: "/ProductBuying/S_ChangeStatus",
        type: "Post",
        data: { "id": id, "ss": ss },
        dataType: "json",
        success: function (data) {
            if (data.Code < 0) { Dalert(data.Desc); }
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
//提交审核
function Check(id) {
    $.ajax({
        url: "/ProductBuying/S_ChangeChack",
        type: "Post",
        data: { "id": id},
        dataType: "json",
        success: function (data) {
            Dalert(data.Desc);
            var tdhtml = "<span class='huise'>编辑</span><span style='margin-right:5px;'></span><span class='huise'>删除</span>";
            $("#cz_" + id).html(tdhtml);
            $("#check_" + id).html("<span>提交审核中</span>");
        },
        error: function () {
        }
    });
}
//全选
function checkAll() {
    if ($("input[name=ch_All]").attr("checked")) {
        $("input[name=ck_list]").attr("checked", true);
    }
    else {
        $("input[name=ck_list]").attr("checked", false);
    }
}
//批量删除
function dels() {
    if (confirm('确定批量删除选中记录吗?')) {
        var buyids = "";
        var chks = document.getElementsByName("ck_list");
        for (var i = 0; i < chks.length; i++) {
            if (chks[i].checked) {
                if (buyids == "") {
                    buyids = chks[i].value;
                }
                else {
                    buyids += "," + chks[i].value;
                }
            }
        }
        if (buyids != "") {
            $.ajax({
                url: "/ProductBuying/S_Dels",
                type: "Post",
                data: { ids: buyids },
                dataType: "json",
                success: function (data) {
                    if (data.Code < 0) {
                        Dalert(data.Desc);
                    }
                    else {
                        pagelist(pindex);
                    }
                },
                error: function () {
                    Dalert("删除失败");
                }
            });
        }
    }
}