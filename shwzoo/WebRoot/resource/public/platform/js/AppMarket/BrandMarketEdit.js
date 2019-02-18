$(function () {
    //上传图片
    $(".tjcpxx-con-form-upthis").click(function () {
        $.ajaxFileUpload({
            url: "/upLoad/UploadFile",
            secureuri: false,
            fileElementId: 'selectimg',
            dataType: "json",
            //ftype:上传文件类型（图片文件=1，其他文件=2）
            //module: 模块（ 会员 = 0, 店铺 = 1, 平台 = 2, 产品 = 3, 其他 = 4,）
            data: { ftype: 1, module: 4 },
            type: 'POST',
            success: function (result) {
                $("input[name='img']").val(result.Data[0]);
                if (result.Code == 0){
                	Dalert("上传成功");                	
                	$("#loadimg").attr("src", result.Data[0]);
                }
                else
                    $("#loadimg").attr("src", "");
                //TODO 结束正在加载中
            }
        });
    });
    var type = GetQueryStringByName("type");
    var reid = GetQueryStringByName("reid");

    //品牌
    getBrandlist();

    //取消返回
    $("input[name=cancel]").click(function () {
        formCancel();
    });
    var id = GetQueryStringByName("id");
    if (parseInt(id) > 0) {
        getInfo(id);
    }
});
//取消
function formCancel() {
    var reid = GetQueryStringByName("reid");
    location.href = "BrandMarket?id=" + reid;
}
//验证信息
function check() {
    return $("#formBrand").validate({
        rules: {
            saveimg: {
                required: true
            },
            orderby: {
                required: true
                //digtal: true
            }
        },
        message: {
            saveimg: {
                required: "请选择图片"
            },
            orderby: {
                required: "请添加排序"
               // digtal: "请填入整数"
            }
        },
        //errorPlacement: function (error, element) {
        //    error.appendTo(element.parent());
        //},
        errorElement: "label",
    });
}

function formSubmit() {
    if (check().form()) {
        var type = GetQueryStringByName("type");
        addInfo(type, 0);
    }
}
//品牌下拉框
function getBrandlist() {
    $.ajax({
        url: "/APP_Market/GetAllBrands",
        type: "Get",
        dataType: "json",
        success: function (data) {
            if (data.Code < 0) {
                Dalert(data.Desc);
            } else {
                var listdata = {
                    list: data.Data
                }
                var html = template('select_brand', listdata);
                $("#brandlist").html(html);
            }
        }
    });
}
//获取编辑信息
function getInfo(id) {

    $.ajax({
        url: "/APP_Market/GetRBByID",
        type: "Post",
        data: { bID: id },
        dataType: "json",
        success: function (data) {
            if (data.Code == 0) {
                var res = data.Data;
                $("#brandlist").val(res.BrandID);
                $("#spuName").val(res.SpuName);
                $("#loadimg").attr("src", res.Img);
                $("#saveimg").val(res.Img);
                $("#display").val(res.Display);
                $("#orderby").val(res.OrderBy);
            }
            Dalert(data.Desc, "", formCancel);
        }
    });


}
//添加品牌推荐信息
function addInfo(type) {
    var id = GetQueryStringByName("id");
    if (parseInt(id) > 0) {

    } else {
        id = 0;
    }
    var reid = GetQueryStringByName("reid");
    var img = $("#saveimg").val();
    if (img == "") {
        img = $("#loadimg").val();
    }
    var display = $("#display").val();
    var orderby = $("#orderby").val();
    var bid = $("#brandlist").val();
    var pagemark = $("#PageMark").val();
    $.ajax({//SaveRB(string reID, string rbID, string bID, string img, string orderby, string display)
        url: "/APP_Market/SaveRB",
        type: "Post",
        data: { reID: reid, rbID: id, bID: bid, img: img, orderby: orderby, display: display, pagemark: pagemark },
        dataType: "json",
        success: function (data) {
            if (data.Code == 0) {
                Dalert(data.Desc, "", formCancel);
            } else {
                Dalert(data.Desc);
            }
        }
    });
}

