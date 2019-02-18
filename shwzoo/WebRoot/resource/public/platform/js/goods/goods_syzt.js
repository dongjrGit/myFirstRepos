//首页专题管理

var pcount;
var pindex;
var psize = size_common;
//页面加载数据
var proT = {
    bind: function (index) {
        $.ajax({
            url: "/ProductTheme/P_GetList",
            type: "Post",
            data: { pageindex: index, pagesize: psize },
            dataType: "json",
            success: function (data) {
                if (data.Code < 0) {
                    Dalert(data.Desc);
                } else {
                    var listdata = {

                        list: data.Data
                    }

                    var html = template('proTlist', listdata);

                    //翻页时删除表头以外的所有节点，避免after()方法重复加载
                    $("#proT_title").parent().children().each(function () {
                        if ($(this).attr('id') != "proT_title") {
                            this.parentNode.removeChild(this);
                        }
                    })
                    //html 填充
                    $("#proT_title").after(html);
                    //加载table样式：改变偶数行背景色，鼠标移动时背景色
                    init();
                    pcount = data.MaxRow;
                    pindex = data.PageIndex;

                    //分页
                    $("#pager").html(pagination(pcount, pindex, psize, "proT.bind"));
                }
            },
            error: function () {
                //Dalert("数据加载失败");
            }
        });
    }
}
//按钮单击事件绑定
$(function () {
    $(".chaxun").bind("click", proTadd);
    $("input[name=Save]").bind("click", proTsave);
})
//添加
function proTadd() {
    self.location = "goods_spgl_syztSave";
}
//保存前参数验证
function check() {
    return $("#form").validate({
        rules: {
            Name: {
                required: true,
                stringCheck: true,
                rangelength: [1, 100]
            },
            OrderBy: {
                required: true,
                digits: true
            },
        },
        message: {
            Name: {
                required: "专题名称不可为空"
            },
            OrderBy: {
                required: "排序不能为空",
                digits: "必须输入整数"
            }
        }
    });
}
//保存
function proTsave() {

    if (check().form()) {
        //防止重复提交 点击保存后隐藏按钮
        $("input[name='Save']").hide();
        $.ajax({
            url: "/ProductTheme/P_Save",
            type: "Post",
            data: { "Id": $("input[name=Id]").val(), "Name": $("input[name=Name]").val(), "OrderBy": $("input[name=OrderBy]").val(), "Status": $("input[name=Status]").val() },
            dataType: "json",
            success: function (data) {
                if (data.Code < 0) {
                    $("input[name='Save']").show();
                    Dalert(data.Desc);
                }
                else {
                    Dalert(data.Desc,"",backhref);
                    //window.location.href = '/Platform/sp/goods_spgl_syzt';
                }
            },
            error: function () {
            }
        });
    }
}
//删除
function Del(ptid) {

    if (confirm('确定将此记录删除?')) {
        $.ajax({
            url: "/ProductTheme/P_Del",
            type: "Post",
            data: { id: ptid },
            dataType: "json",
            success: function (data) {
                if (data.Code < 0) {
                    Dalert(data.Desc);
                }
                else {
                    proT.bind(pindex);
                }
            },
            error: function () {
                Dalert("删除失败");
            }
        });
    }
}

//修改状态
function setStatus(ptid, ss) {
    $.ajax({
        url: "/ProductTheme/P_ChangeStatus",
        type: "Post",
        data: { id: ptid, status: ss },
        dataType: "json",
        success: function (data) {
            if (data.Code < 0) { Dalert(data.Desc); }
            else {
                var td_html = "";
                if (ss == 0) {
                    td_html = "<span class='lvs'><a href='#' onclick=setStatus(" + ptid + ",1)>启用</a></span>";
                }
                else {
                    td_html = "<span class='lvs'><a href='#' onclick=setStatus(" + ptid + ",0)>禁用</a></span>";
                }
                $("#td_" + ptid).html(td_html);
            }
        },
        error: function () {
            Dalert("修改状态失败");
        }
    });
}

//修改排序
function setOrder(ptid, ob) {
    var obtext = $("#" + ob).val();
    $.ajax({
        url: "/ProductTheme/P_ChangeOrder",
        type: "Post",
        data: { id: ptid, orderby: obtext },
        dataType: "json",
        success: function (data) {
            Dalert(data.Desc);
        },
        error: function () {
            Dalert("修改排序失败");
        }
    });
}
//返回刷新
function backhref() {
    window.location.href = '/Platform/sp/goods_spgl_syzt';
}