//商品列表左侧推广
var pcount;
var pindex;
var psize = size_common;
var cid = "";
//页面加载数据
var Zctg = {
    bind: function (index,claid) {
        var datahtml = "";
        $.ajax({
            url: "/ProductSpread/P_GetList",
            type: "Post",
            data: { "index": index, "size": psize, "cid": claid },
            dataType: "json",
            success: function (data) {
                if (data.Code < 0) {
                    Dalert(data.Desc);
                } else {
                    var listdata = {

                        list: data.Data
                    }
                  
                    var html = template('zctglist', listdata);

                    //翻页时删除表头以外的所有节点，避免after()方法重复加载
                    $("#zctg_title").parent().children().each(function () {
                        if ($(this).attr('id') != "zctg_title") {
                            this.parentNode.removeChild(this);
                        }
                    })
                    //html 填充
                    $("#zctg_title").after(html);
                    //加载table样式：改变偶数行背景色，鼠标移动时背景色
                    init();
                    cid = claid;
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
    },
    initcid: function () {

            if ($("#thirdID").val() != "0") {
                cid = $("#thirdID").val();
            }
            else {
                if ($("#secondID").val() != "0") {
                    cid = $("#secondID").val();
                }
                else {
                    if ($("#firstID").val() != "0") {
                        cid = $("#firstID").val();
                    } else {
                        cid = 0;
                    }
                }
            }
    }
}
//分页回调函数
function pagelist(index) {
    Zctg.initcid();
    Zctg.bind(index, cid);
}
//按钮单击事件绑定
$(function () {
    $("input[name=chkall]").click(checkAll);
    $("input[name=dels]").click(dels);
    $("input[name=add]").click(add);
    $("input[name=Save]").click(Save);
    $("input[name=search]").click(search);
})
//全选
function checkAll() {
    if ($("input[name=chkall]").attr("checked")) {
        $("input[name=chksel]").attr("checked", true);
    }
    else {
        $("input[name=chksel]").attr("checked", false);
    }
}
//查询
function search() {
    Zctg.initcid();
    Zctg.bind(1,cid);
}
//添加
function add() {
    self.location = "goods_spgl_zctgSave";
}

//修改排序
function setOrder(spid, ob) {
  
    var obtext = $("#" + ob).val();
    $.ajax({
        url: "/ProductSpread/P_ChangeOrder",
        type: "Post",
        data: { id: spid, orderby: obtext },
        dataType: "json",
        success: function (data) {
            Dalert(data.Desc);
        },
        error: function () {
            Dalert("修改排序失败");
        }
    });
}
//保存
function Save() {
   
    if (formSubmit()) {
        //防止重复提交 点击保存后隐藏按钮
        $("input[name='Save']").hide();
        $.ajax({
            url: "/ProductSpread/P_Save",
            type: "Post",
            data: { "Id": $("input[name=Id]").val(), "Cid": $("#ClassID").val(), "SpuID": $("#spuid").val(), "Orderby": $("input[name=orderby]").val() },
            dataType: "json",
            success: function (data) {
                if (data.Code < 0) {
                    $("input[name='Save']").show();
                    Dalert(data.Desc);
                }
                else {
           
                    Dalert(data.Desc,"",backhref);
                    //window.location.href = '/Platform/sp/goods_spgl_zctg';
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
function formSubmit() {
    if (check().form()) {
            if ($("#thirdID").val() != "0") {
                $("#ClassID").val($("#thirdID").val());
                //return true;
            }
            else {
                if ($("#secondID").val() != "0") {
                    $("#ClassID").val($("#secondID").val());
                    //return true;
                }
                else {
                    if ($("#firstID").val() != "0") {
                        $("#ClassID").val($("#firstID").val());
                        //return true;
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
        return true;
    }

}
//删除
function dels() {
  
    var spreadid = "";
    var chks = document.getElementsByName("chksel");
    for (var i = 0; i < chks.length; i++) {
        if (chks[i].checked) {
            if (spreadid == "") {
                spreadid = chks[i].value;
            }
            else {
                spreadid += "," + chks[i].value;
            }
        }
    }
    if (spreadid != "") {
        $.ajax({
            url: "/ProductSpread/P_Del",
            type: "Post",
            data: { Ids: spreadid },
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
//返回刷新
function backhref() {
    window.location.href = '/Platform/sp/goods_spgl_zctg';
}