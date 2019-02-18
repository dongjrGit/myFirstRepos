//权限管理

var pcount;
var pindex;
var psize = size_common;
var type;//权限类型 0-平台 1-店铺
//页面加载数据
var perm = {
    bind: function (index) {
        var sUrl = "";
        var permname = $("#name").val();
        var permtype = $("#seltid").val();
        var select_shop = $("#select_shop").attr("data");
        var sUrl = "/Permission/P_GetPermissionList";
        if (permtype == 2 || permtype == 3) {
            sUrl = "/Permission/S_GetPermissionList";
        }

        $.ajax({
            url: sUrl,
            type: "Post",
            data: { pageindex: index, pagesize: psize, permtype: permtype, name: permname, "shopid": select_shop },
            dataType: "json",
            success: function (data) {
                if (data.Code < 0) {
                    Dalert(data.Desc);
                } else {
                    //js template数据加载
                    var listdata = {

                        list: data.Data
                    }

                    var html = template('permlist', listdata);

                    //翻页时删除表头以外的所有节点，避免after()方法重复加载
                    $("#perm_title").parent().children().each(function () {
                        if ($(this).attr('id') != "perm_title") {
                            this.parentNode.removeChild(this);
                        }
                    })
                    //html 填充
                    $("#perm_title").after(html);
                    //加载table样式：改变偶数行背景色，鼠标移动时背景色
                    init();

                    //ztree加载
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
    //权限类型
    unit: function (ptype) {
        type = ptype;
    }
}
//分页回调函数
function pagelist(index) {
    perm.bind(index);
}
//按钮事件绑定
$(function () {

    $("input[name=permadd]").bind("click", permadd);
    $("input[name=permsearch]").bind("click", permsearch);
    $("input[name=Save]").bind("click", Save);

})
//添加
function permadd() {
    if (type == 0) {
        self.location = "manager_pt_permissionSave";
    }
    else {
        self.location = "manager_dp_permissionSave";
    }

}
//查询
function permsearch() {

    perm.bind(1);
}
//删除
function del(permid) {
    var sUrl = "/Permission/DelPermWeb";
    if (type == 1) {
        sUrl = "/Permission/DelPermShop";
    }
    if (confirm('确定将此记录删除?')) {
        $.ajax({
            url: sUrl,
            type: "Post",
            data: { id: permid },
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
//修改状态   ss-状态
function setStatus(permid, ss) {
    $.ajax({
        url: "/Permission/P_ChangeStatus",
        type: "Post",
        data: { id: permid, status: ss },
        dataType: "json",
        success: function (data) {
            if (data.Code < 0) {
                Dalert(data.Desc);
            }
            else {
                var td_html = "";
                if (ss == 0) {
                    td_html = "<span class='lvs'><a href='#' onclick=setStatus(" + permid + ",1)>启用</a></span>";
                }
                else {
                    td_html = "<span class='lvs'><a href='#' onclick=setStatus(" + permid + ",0)>禁用</a></span>";
                }
                $("#td_" + permid).html(td_html);
            }

        },
        error: function () {
            Dalert("修改状态失败");
        }
    });
}

//获取店铺下拉框
function getShop() {
    var sid = $("#ShopID").val();
    var selhtml = "";
    $.ajax({
        url: "/Shop/GetShopList",
        type: "Post",
        data: {},
        dataType: "json",
        success: function (data) {
            if (data.Code < 0) { Dalert(data.Desc); }
            else
            {
                selhtml = "<option value='0'></option>";
                if (data.Data.length > 0) {
                    for (var i = 0; i < data.Data.length; i++) {
                        //绑定下拉框
                        if (sid == data.Data[i].ID) {
                            selhtml += "<option value='" + data.Data[i].ID + "' selected>" + data.Data[i].Name + "</option>";
                        }
                        else {
                            selhtml += "<option value='" + data.Data[i].ID + "'>" + data.Data[i].Name + "</option>";
                        }
                    }
                }
                $("#sel").html(selhtml);
            }

        },
        error: function () {

        }
    });
}
//保存
function Save() {
    var action = $("#perm_action").val();
    if (formSubmit()) {
        var $selectshop = $("#select_shop");
        if ($selectshop.val() == "") {
            $("#ShopID").val("");
        } else {
            if ($selectshop.attr("data")!=undefined)
            $("#ShopID").val($selectshop.attr("data"));
        }
        //防止重复提交 点击保存后隐藏按钮
        $("input[name='Save']").hide();
        $.ajax({
            url: "/Permission/" + action,
            type: "Post",
            data: $("#form").serialize(),
                //{
                //    "Id": $("input[name=Id]").val(), "Code": $("input[name=Code]").val(), "Name": $("input[name=Name]").val(), "Type": $("#TypeSel").val(), "fid": $("#fid").val(),
                //    "ShopID": $("#ShopID").val(), "Url_Cname": $("input[name=Url_Cname]").val(), "Url_Ename": $("input[name=Url_Ename]").val(), "Description": $("input[name=Description]").val()
                //},
            dataType: "json",
            success: function (data) {
                if (data.Code < 0) {
                    $("input[name='Save']").show();
                    Dalert(data.Desc);
                }
                else {
                    Dalert(data.Desc);
                    if (action.indexOf("Shop") >= 0) {
                        Dalert("保存成功！", "", function () { window.location.href = '/Platform/kz/manager_dp_permission'; });                      
                    }
                    else {
                        Dalert("保存成功！", "", function () { window.location.href = '/Platform/kz/manager_pt_permission'; });
                    }

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
            Name: {
                required: true,
                stringCheck: true,
                rangelength: [1, 100]
            },
            Code: {
                required: true
            },
        },
        message: {
            Name: {
                required: "权限名称不可为空"
            },
            Code: {
                required: "权限代码不能为空"
            }
        }
    });
}
function formSubmit() {
    if (check().form()) {
        if ($("#Url_Cname").val() == "") {
            $("#fid").val(0);
        }
        return true;
    }
    else {
        return false;
    }

}
//权限类型选择带出店铺及相应权限
function TypeSelect(value) {
    var Tsel = value;
    if (Tsel == null || Tsel == undefined) {
        Tsel = $("#TypeSel").val();
    }
    if (Tsel == "2" || Tsel == "3") {
        getShop();
    }
    else {
        $("#sel").html("<option value='0'></option>");
    }
    $("input[name=Url_Cname]").val("");
    $("input[name=Url_Ename]").val("");
}

/*
callback 成功 有数据时 调的方法 
event 事件
*/
function getShopStartwithName(callback, event) {
    var name = $("#select_shop").val();
    if (event)
        name += String.fromCharCode(event.keyCode);
    $.ajax({
        url: "/Shop/GetListStartwithName",
        type: "Post",
        data: {
            "name": name
        },
        dataType: "json",
        success: function (data) {

            if (data.Code == 0) {
                var listdata = {
                    list: data.Data
                }
                var html = template('select_shoplist', listdata);

                if (callback) {
                    callback(html);
                }
            } else {
                Dalert(data.Data);
            }
        }
    });
}

