//商品分类属性
var pcount;
var pindex;
var psize = size_product;
//分类ID
var cid;
//页面加载数据
var attr = {
    bind: function (classid,index) {
        $.ajax({
            url: "/ProductAttr/GetListByClassID",
            type: "Post",
            data: { ClassID: classid, pageindex: index, pagesize: psize },
            dataType: "json",
            success: function (data) {
                if (data.Code < 0) {
                    Dalert(data.Desc);
                } else {
                    var listdata = {

                        list: data.Data
                    }
                   
                    var html = template('attrlist', listdata);

                    //翻页时删除表头以外的所有节点，避免after()方法重复加载
                    $("#attr_title").parent().children().each(function () {
                        if ($(this).attr('id') != "attr_title") {
                            this.parentNode.removeChild(this);
                        }
                    })
                    //html 填充
                    $("#attr_title").after(html);
                    //加载table样式：改变偶数行背景色，鼠标移动时背景色
                    init();
                    pcount = data.MaxRow;
                    pindex = data.PageIndex;

                    //分页
                    $("#pager").html(pagination(pcount, pindex, psize, "pagelist"));
                }
            },
            error: function () {
                Dalert("数据加载失败");
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
                    }
                }
        }
    }
}

//分页回调加载数据
function pagelist(index) {
    attr.initcid();
    attr.bind(cid,index);
}

$(function () {

    $("input[name=attradd]").bind("click", attradd);
    $("input[name=attrsearch]").bind("click", attrsearch);
    $("input[name=Save]").bind("click", Save);

});

//添加分类属性
function attradd() {
    
    self.location = "goods_sppz_spattrEdit";
}


//删除分类属性
function attrdel(attrid) {

    if (confirm('确定将此记录删除?'))
    {
        $.ajax({
            url: "/ProductAttr/Del",
            type: "Post",
            data: { id: attrid },
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

//修改分类属性状态  ss-状态
function setStatus(attrid, ss) {
    $.ajax({
        url: "/ProductAttr/SetProAttrStatus",
        type: "Post",
        data: { id: attrid, status: ss },
        dataType: "json",
        success: function (data) {
            if (data.Code < 0) { Dalert(data.Desc); }
            else {
                var td_html = "";
                if (ss == 0) {
                    td_html = "<span class='lvs'><a href='#' onclick=setStatus(" + attrid + ",1)>启用</a></span>";
                }
                else {
                    td_html = "<span class='lvs'><a href='#' onclick=setStatus(" + attrid + ",0)>禁用</a></span>";
                }
                $("#td_" + attrid).html(td_html);
            }
        },
        error: function () {
            Dalert("修改状态失败");
        }
    });
}

//修改分类属性排序   ob-排序
function setOrder(attrid, ob) {
    var obtext = $("#"+ob).val();
        $.ajax({
            url: "/ProductAttr/ChangeOrder",
            type: "Post",
            data: { id: attrid, orderby: obtext },
            dataType: "json",
            success: function (data) {
                Dalert(data.Desc);
            },
            error: function () {
                Dalert("修改排序失败");
            }
        });
}

//查询
function attrsearch() {
    attr.initcid();
    attr.bind(cid, 1);
}

function getvalues(attrname, attrid) {
    var d = dialog({
        title: attrname + '的属性值',
        url: 'goods_sppz_values?action=1&id=' + attrid,
        width:560,
        height:560
    });

    d.show();

}

function Save() {
    var action = $("#attr_action").val();

    if (formSubmit()) {
        //防止重复提交 点击保存后隐藏按钮
        $("input[name='Save']").hide();
        $.ajax({
            url: "/ProductAttr/" + action,
            type: "Post",
            data: { "id": $("input[name=id]").val(), "name": $("input[name=name]").val(), "orderBy": $("input[name=orderBy]").val(), "ClassID": $("#ClassID").val(), "status": $("input[name=status]").val() },
            dataType: "json",
            success: function (data) {
                if (data.Code < 0) {
                    $("input[name='Save']").show();
                    Dalert(data.Desc);
                }
                else {
                    Dalert(data.Desc);
                    //window.location.href = '/Platform/sp/goods_symrjc';
                }
            },
            error: function () {
               
            }
        });
    }
}

function check() {
    return $("#form").validate({
        rules: {
            ClassID: "required",
            name: {
                required: true,
                stringCheck: true,
                rangelength: [1, 100]
            },
            orderBy: {
                required: true,
                digits: true
            },
        },
        message: {
            ClassID: { required: "商品分类不可为空" },
            name: {
                required: "属性名称不可为空"
            },
            orderBy: {
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
        }

        else {
            Dalert("请选择商品分类");
            $("#thirdID").focus();
            return false;
        }
        if ($("#ClassID").val() != "0") {
            return true;
        }
    }

}

