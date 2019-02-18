//营销推荐管理
$(function () {
    //获取列表
    Market.getAll();
    $("input[name=add]").click(function () {
        ConfigShow();
    });
});
var Market = {
    getAll: function (index) {
        $.ajax({
            url: "/APP_Market/GetMarketList",
            type: "Get",
            dataType: "json",
            success: function (data) {
                if (data.Code < 0) {
                    Dalert(data.Desc);
                } else {
                    var listdata = {
                        list: data.Data
                    }
                    var html = template('Marketlist', listdata);
                    //$("#list_title").after(html);
                    //翻页时删除表头以外的所有节点，避免after()方法重复加载
                    $("#list_title").parent().children().each(function () {
                        if ($(this).attr('id') != "list_title") {
                            this.parentNode.removeChild(this);
                        }
                    })
                    //html 填充
                    $("#list_title").after(html);

                    //分页
                    //$("#pager").html(pagination(pcount, pindex, psize, "Market.getAll"));

                }
            },
            error: function () {

            }
        });
    },
    //删除
    del: function (id) {
        if (confirm("确定要删除么？")) {
            $.ajax({
                url: "/APP_Market/DelMarket",
                type: "Post",
                data: { "mID": id },
                dataType: "json",
                success: function (data) {
                    Dalert(data.Desc, "", Market.getAll);
                }
            });
        }
    }
}

//修改排序
function setMarket(mkid, selecter, type) {
    var obtext = $("#" + selecter).val();
    $.ajax({
        url: "/APP_Market/SetMarket",
        type: "Post",
        data: { id: mkid, content: obtext, type: type },
        dataType: "json",
        success: function (data) {
            Dalert(data.Desc);
        },
        error: function () {
            Dalert("修改失败");
        }
    });
}
//弹出框 
//setType：配置类型，title：弹出框标题，setname：配置名称，setValue：配置值， setDesc：配置描述
function ConfigShow(title) {
    if (title == "undefined" || title == "" || title == null) { title = "添加营销推荐"; }
    var contentHTML = "";
    contentHTML += "<form id='SetForm'><div class='tjcpxx-con-mk1'><div class='tjcpxx-con-form-title1'><label id='td_SetName'>类型：</label></div>";
    contentHTML += "<div class='tjcpxx-con-form1'> <select id='MarketType' name='MarketType'><option value='1'>主题推荐</option><option value='2'>品牌推荐</option><option value='3'>精品推荐</option></select></div></div>";

    contentHTML += "<div class='tjcpxx-con-mk1'><div class='tjcpxx-con-form-title1'><label id='td_SetName'>名称：</label></div>";
    contentHTML += "<div class='tjcpxx-con-form1'><input class='tjcpxx-fprm-inp' id='SetName' name='SetName' type='text' value=''></div></div>";

    contentHTML += "<div class='tjcpxx-con-mk1'><div class='tjcpxx-con-form-title1'><label id='td_SetOrderBy'>排序：</label></div>";
    contentHTML += "<div class='tjcpxx-con-form1'><input class='tjcpxx-fprm-inp' id='SetOrderBy' name='SetOrderBy' type='text' value=''></div></div>";

    contentHTML += "</div></form>";

    d = dialog({
        title: title,
        id: "showid",//防止重复弹出
        content: contentHTML,
        width: 500,
        height: 180,
        button: [{
            value: '保存',
            callback: function () {
                var sname = $("#SetName").val().trim();
                var sOrderby = $("#SetOrderBy").val().trim();

                var mType = $("#MarketType").val();
                if (check().form()) {
                    add(mType, sname, sOrderby);
                }
                else {
                    return false;
                };
            },
            autofocus: true
        },
               {
                   value: '取消',
                   callback: function () {

                   }
               }
        ]
        //,modal: true //蒙层
    });
    d.show();
}
//添加
function add(mtype, name, orderby) {
    $.ajax({
        url: "/APP_Market/AddMarket",
        type: "Post",
        data: { "type": mtype, "name": name, "orderby": orderby },
        dataType: "json",
        success: function (data) {
            Dalert(data.Desc);
        },
        error: function () {
            Dalert("添加失败");
        }
    });
}
//表单验证
function check() {
    return $("#SetForm").validate({
        rules: {
            SetName: {
                maxlength: 10,
                required: true
            },
            SetOrderBy: {
                required: true,
                digits: true
            }
        },
        //设置提示信息
        messages: {
            SetName: {
                maxlength: "长度限制在10个字符以内",
                required: "请输入配置名称"
            },
            SetOrderBy: {
                required: "请输入配置值",
                digits: "请输入整数"
            }
        },
        //设置错误信息存放标签
        errorElement: "label",
        //设置验证触发事件
        focusInvalid: true

    })
}
