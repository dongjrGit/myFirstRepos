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

    //商品自动选取
    if (type == "3") {
        //精品推荐
        $("#spname").attr("style", "display:block");
        $("#spuprice").attr("style", "display:block");
        autoxl.bind("spuName", getSpuStartwithName);
    }
    //取消返回
    $("input[name=cancel]").click(function () {
        backhref();
    });
    //自动获取获取价格
    $("div[name=spu_name_select]").find("ul").find("li").live("click", function () {
        var sid = $("#spuName").attr("data");
        if (sid != "" && parseInt(sid) > 0) {
            getSpu(sid);
        }
    });
    var id = GetQueryStringByName("id");
    if (parseInt(id) > 0) {
        getInfo(id);
    }
});
function backhref() {
    var reid = GetQueryStringByName("reid");
    location.href = "BoutiqueMarket?id=" + reid;
}
//验证数据
function check() {
    return $("#form").validate({
        rules: {
            spuName: {
                required: true,
                maxlength: 200
            },
            spuPrice: {
                required: true
            },
            img: { required: true },
            orderby: {
                required: true
                //digtal: true
            }
        },
        message: {
            spuName: {
                required: "商品名不可为空",
                maxlength: "最多输入50个汉子"
            },
            spuPrice: {
                required: "商品价格不可为空"
            },
            img: {
                required: "请选择图片"
            },
            orderby: {
                required: "请添加排序"
                //digtal: "请填入整数"
            }
        },
        errorPlacement: function (error, element) {
            error.appendTo(element.parent());
        }
    });
}
//添加数据
function formSubmit() {
    if (check().form()) {
        var type = GetQueryStringByName("type");
        addInfo(type, 0);
    }
}

//获取spu列表
function getSpuStartwithName(callback, event) {
    var name = $("#spuName").val();
    if (event)
        name += String.fromCharCode(event.keyCode);
    $.ajax({
        url: "/APP_Market/GetListStartwithName",
        type: "Post",
        data: { "spuName": name },
        dataType: "json",
        success: function (data) {

            if (data.Code == 0) {
                var listdata = {
                    list: data.Data
                }
                var html = template('select_spulist', listdata);

                if (callback) {
                    callback(html);
                }
            } else {
                Dalert(data.Data);
            }
        }
    });
}
//获取编辑数据
function getInfo(id) {

    var sid = $("#spuName").attr("data");
    $.ajax({
        url: "/APP_Market/GetRSByID",
        type: "Post",
        data: { bID: id },
        dataType: "json",
        success: function (data) {
            if (data.Code == 0) {
                var res = data.Data;
                $("#spuName").val(res.SpuName);
                $("#spuName").attr("data", res.SpuID);
                $("#spuPrice").val(res.SpuPrice);
                $("#loadimg").attr("src", res.Img);
                $("#saveimg").val(res.Img);
                $("#orderby").val(res.OrderBy);
            }
            Dalert(data.Desc, "", backhref);
        }
    });
}
//添加数据
function addInfo(type) {
    var id = GetQueryStringByName("id");
    if (parseInt(id) > 0) {

    } else {
        id = 0;
    }
    var reid = GetQueryStringByName("reid");
    var img = $("#saveimg").val();
    var display = $("#display").val();
    var orderby = $("#orderby").val();
    var pmark = $("#PageMark").val();

    var sid = $("#spuName").attr("data");
    var sname = $("#spuName").val();
    var sprice = $("#spuPrice").val();

    $.ajax({//SaveRS(string reID, string rsID, string sID,string spuName,string spuPrice, string img, string orderby, string display)
        url: "/APP_Market/SaveRS",
        type: "Post",
        data: { reID: reid, rsID: id, sID: sid, spuName: sname, spuPrice: sprice, img: img, orderby: orderby, display: display, pagemark: pmark },
        dataType: "json",
        success: function (data) {
            if (data.Code == 0) { Dalert(data.Desc, "", backhref); } else {
                Dalert(data.Desc);
            }
        }
    });

}
//获取spu信息
function getSpu(sid) {

    $.ajax({
        url: "/APP_Market/GetSPUByID",
        type: "Post",
        data: { sID: sid },
        dataType: "json",
        success: function (data) {
            if (data.Code == 0) {
                var res = data.Data;
                if (data != "") {
                    $("#spuPrice").val(res.Price);
                }
            }
            //Dalert(data.Desc);
        }
    });
}