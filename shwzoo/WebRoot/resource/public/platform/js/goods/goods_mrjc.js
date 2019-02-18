//每日精彩管理
var pcount;
var pindex;
var psize = size_common;
var id = "";
//页面加载数据
var Mrjc = {
    bind: function (index) {
        var datahtml = "";
        $.ajax({
            url: "/sy_mrjc/P_GetList",
            type: "Post",
            data: { index: index, size: psize },
            dataType: "json",
            success: function (data) {
                if (data.Code < 0) {
                    Dalert(data.Desc);
                } else {
                    var listdata = {

                        list: data.Data
                    }

                    var html = template('mrjclist', listdata);

                    //翻页时删除表头以外的所有节点，避免after()方法重复加载
                    $("#mrjc_title").parent().children().each(function () {
                        if ($(this).attr('id') != "mrjc_title") {
                            this.parentNode.removeChild(this);
                        }
                    })
                    //html 填充
                    $("#mrjc_title").after(html);
                    //加载table样式：改变偶数行背景色，鼠标移动时背景色
                    init();
                    pcount = data.MaxRow;
                    pindex = data.PageIndex;

                    //分页
                    $("#pager").html(pagination(pcount, pindex, psize, "pagelist"));

                }
            },
            error: function () {
                //Dalert("数据加载失败");
            }
        });
    }
}
//页面回调函数
function pagelist(index) {
    Mrjc.bind(index);
}
//删除
function Del(mrjcid) {

    if (confirm('确定将此记录删除?')) {
        $.ajax({
            url: "/sy_mrjc/P_Del",
            type: "Post",
            data: { Id: mrjcid },
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
//修改排序  ob-排序
function setOrder(mrjcid, ob) {

    var obtext = $("#" + ob).val();
    $.ajax({
        url: "/sy_mrjc/P_ChangeOrder",
        type: "Post",
        data: { id: mrjcid, orderby: obtext },
        dataType: "json",
        success: function (data) {
            Dalert(data.Desc);
        },
        error: function () {
            Dalert("修改排序失败");
        }
    });
}
//批量删除
function dels() {

    var msid = "";
    var chks = document.getElementsByName("chksel");
    for (var i = 0; i < chks.length; i++) {
        if (chks[i].checked) {
            if (msid == "") {
                msid = chks[i].value;
            }
            else {
                msid += "," + chks[i].value;
            }
        }
    }
    if (msid != "") {
        $.ajax({
            url: "/sy_mrjc/P_Dels",
            type: "Post",
            data: { Ids: msid },
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
//按钮事件加载
$(function () {
    $("input[name=chkall]").click(checkAll);
    $("input[name=dels]").click(dels);
    $("input[name=add]").click(add);
    $("input[name=Save]").click(Save);
})
//全选与取消全选
function checkAll() {
    if ($("input[name=chkall]").attr("checked")) {
        $("input[name=chksel]").attr("checked", true);
    }
    else {
        $("input[name=chksel]").attr("checked", false);
    }
}
//添加
function add() {
    self.location = "goods_symrjcSave?act=add";
}
//保存
function Save() {

    if (formSubmit()) {
        //防止重复提交 点击保存后隐藏按钮
        $("input[name='Save']").hide();
        $.ajax({
            url: "/sy_mrjc/P_Save",
            type: "Post",
            data: { "Id": $("input[name=Id]").val(), "Cid": $("input[name=ClassID]").val(), "SpuID": $("#spuid").val(), "Orderby": $("input[name=orderby]").val() },
            dataType: "json",
            success: function (data) {
                if (data.Code < 0) {
                    $("input[name='Save']").show();
                    Dalert(data.Desc);
                }
                else {
                    Dalert(data.Desc, "", backhref);
                    //window.location.href = '/Platform/sp/goods_symrjc';
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
            orderby: {
                required: true,
                digits: true
            },
        },
        message: {
            orderby: {
                required: "排序不能为空",
                digits: "必须输入整数"
            }
        }
    });
}
//验证是否选择分类及商品
function formSubmit() {
    if (check().form()) {

        if ($("#thirdID").val() != "0") {
            $("#ClassID").val($("#thirdID").val());
            return true;
        }
        else {
            if ($("#secondID").val() != "0") {
                $("#ClassID").val($("#secondID").val());
                return true;
            }
            else {
                if ($("#firstID").val() != "0") {
                    $("#ClassID").val($("#firstID").val());
                    return true;
                }
                else {
                    Dalert("请选择商品分类");
                    $("#firstID").focus();
                    return false
                }
            }
        }
        if ($("#spuid").val() == "0") {
            Dalert("请选择商品");
            $("#spuid").focus();
            return false;
        }

    }

}
//返回刷新
function backhref() {
    window.location.href = '/Platform/sp/goods_symrjc';
}