//专题-精品推荐管理

var pcount;
var pindex;
var psize = size_common;
var id = "";
//页面加载数据
var proTspu = {
    bind: function (protid, index) {
        var datahtml = "";
        $.ajax({
            url: "/ProductTheme/P_GetProSPUList",
            type: "Post",
            data: { pageindex: index, pagesize: psize, proTId: protid },
            dataType: "json",
            success: function (data) {
                if (data.Code < 0) {
                    Dalert(data.Desc);
                } else {
                    var listdata = {

                        list: data.Data
                    }
                 
                    var html = template('proTspulist', listdata);

                    //翻页时删除表头以外的所有节点，避免after()方法重复加载
                    $("#proTspu_title").parent().children().each(function () {
                        if ($(this).attr('id') != "proTspu_title") {
                            this.parentNode.removeChild(this);
                        }
                    })
                    //html 填充
                    $("#proTspu_title").after(html);
                    //加载table样式：改变偶数行背景色，鼠标移动时背景色
                    init();
                    id = protid; //首页专题ID
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
    initClass: function (protid) {
        $.ajax({
            url: "/ProductTheme/P_GetProClassList",
            type: "Post",
            data: { pageindex: 1, pagesize: 100, proTId: protid },
            dataType: "json",
            success: function (data) {
                if (data.Code < 0) {
                    Dalert(data.Desc);
                } else {
                    var listdata = {
                        list: data.Data
                    }
                    var html = '<option value="0" id="defaultclass" selected>无</option>' + template('classlist', listdata);
                    $("#classId").html(html);
                    if ($("#fid").val() > 0) {
                        getClasssel();
                    }

                }
            },
            error: function () {
                //Dalert("数据加载失败");
            }
        });
    },
    getSPU: function () {
        var cid = $("#classId").val();
        //根据分类ID获取商品列表
        $.ajax({
            url: "/Product/P_GetSPUListByQuery",
            type: "Post",
            data: { "page": 1, "size": 100, "cid": cid },
            dataType: "json",
            success: function (data) {
                if (data.Code < 0) {
                    Dalert(data.Desc);
                } else {
                    var listdata = {
                        list: data.Data
                    }
                    var html = '<option value="0" id="defaultspu" selected>无</option>' + template('spulist', listdata);
                    $("#spuid").html(html);
                    getSPUsel();
                }
            },
            error: function () {
                //Dalert("数据加载失败");
            }
        });
    }
}
//分页回调函数
function pagelist(index) {
    proTspu.bind(id, index);
}
//删除
function Del(ptid) {

    if (confirm('确定将此记录删除?')) {
        $.ajax({
            url: "/ProductTheme/P_DelProTspu",
            type: "Post",
            data: { Ids: ptid },
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
//获取专题分类下拉框
function getClasssel() {
  
    var fid = $("#fid").val();
    //根据商品ID获取分类ID
    $.ajax({
        url: "/Product/GetProductByID",
        type: "Post",
        data: { "id": fid },
        dataType: "json",
        success: function (data) {
            if (data.Code < 0) {
                Dalert(data.Desc);
            } else {
                var listdata = {
                    list: data.Data
                }
                $("#claid").val(data.Data.ClassID);
                $("#classId option").each(function () {
                    if ($(this).val() == $("#claid").val()) {
                        $(this).attr("selected", "selected");

                    } else {
                        $(this).removeAttr("selected");

                    }

                });
                proTspu.getSPU();
            }
        },
        error: function () {
            //Dalert("数据加载失败");
        }
    });
    
}
//获取分类下的商品
function getSPUsel() {
    var fid = $("#fid").val();
  
    if (fid > 0) {
       
        $("#spuid option").each(function () {
            if ($(this).val() == fid) {
                $(this).attr("selected", "selected");

            } else {
                $(this).removeAttr("selected");

            }

        });

       
    }

}
///ChangeProSPUOrder
//修改排序
function setOrder(ptid, ob) {
    var obtext = $("#" + ob).val();
    $.ajax({
        url: "/ProductTheme/P_ChangeProSPUOrder",
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
//批量删除
function dels()
{
    var ptid = "";
    var chks = document.getElementsByName("chksel");
    for (var i = 0; i < chks.length; i++) {
        if (chks[i].checked) {
            if (ptid == "") {
                ptid = chks[i].value;
            }
            else {
                ptid += "," + chks[i].value;
            }
         }
    }
    if (ptid != "") {
        $.ajax({
            url: "/ProductTheme/P_DelProTspu",
            type: "Post",
            data: { Ids: ptid },
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
//按钮单击事件绑定
$(function () {
    $("input[name=chkall]").click(checkAll);
    $("input[name=dels]").click(dels);
    $("input[name=Save]").click(SaveProTspu);
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
//保存精品推荐
function SaveProTspu() {
 
    if (formSubmit()) {
        //防止重复提交 点击保存后隐藏按钮
        $("input[name='Save']").hide();
        $.ajax({
            url: "/ProductTheme/P_SaveProTspu",
            type: "Post",
            data: { "Id": $("input[name=Id]").val(), "proTId": $("input[name=proTId]").val(), "spuid": $("#spuid").val(), "orderby": $("input[name=orderby]").val() },
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
        if ($("#proTId").val() == "0") {
            Dalert("请选择分类");
            $("#proTId").focus();
            return false;
        }
        else if ($("#spuid").val() == "0") {
            Dalert("请选择商品");
            $("#spuid").focus();
            return false;
        }
        return true;
    }

}
//返回刷新
function backhref() {
    window.location.href = '/Platform/sp/goods_spgl_syzt';
}