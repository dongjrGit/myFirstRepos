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
    var reid =  GetQueryStringByName("reid");
    //主题推荐
    if (type == "1") {
        //分类
        Class.unit("fc");
    }
    //品牌推荐
    if (type == "2") {
        //品牌
        getBrandlist();
        $("#brand").attr("style", "display:block");
    }
    //精品推荐
    if (type == "3") {
        //精品推荐
        $("#spname").attr("style", "display:block");
        $("#spuprice").attr("style", "display:block");
        autoxl.bind("spuName", getSpuStartwithName);
    }
    //取消返回
    $("input[name=cancel]").click(function () {
        formCancel(type,reid);
    });
    $("div[name=spu_name_select]").find("ul").find("li").each(function(){
   	 var sid = $("#spuName").attr("data");
 		$(this).bind("click", function(){
 			 if (sid != "" && parseInt(sid) > 0) {
 				 getSpu(sid, 0);
 			 }
 		});
 		
 	});
//    $("div[name=spu_name_select]").find("ul").find("li").live("click",function () {
//        var sid = $("#spuName").attr("data");
//        if (sid != "" && parseInt(sid) > 0) {
//            getSpu(sid, 0);
//        }
//    });
});

function formCancel(type,reid) {
    var backhref = "javascript:;";
    if (type == "1") {
        backhref = "TopicMarket";
    }
    if (type == "2") {
        backhref = "BrandMarket";
    }
    if (type == "3") {
        backhref = "BoutiqueMarket";
    }
    location.href = backhref+"?id="+reid;
}
function check() {
    return $("#form").validate({
        rules: {
            name: {
                required: true,
                maxlength: 200
            },
            //selectimgs: {required:true},
            spuPrice: {
                required: true
            },
            display: {
                required: true
            }
        },
        message: {
            name: { required: "商品名不可为空", maxlength: "最多输入50个汉子" },

            //selectimgs: {
            //    required: "必填"
            //},
            spuPrice: {
                required: "商品价格不可为空"
            },
            display: {
                digtal: "最多输入50个汉子"
            }
        }, errorPlacement: function (error, element) {
            error.appendTo(element.parent());
        }
    });
}

function formSubmit() {
    //if (check().form()) {
        var type = GetQueryStringByName("type");
        addInfo(type,0);
    //}
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

//商品分类调用
var Class = {
    unit: function (callback) {
        Class.getFirstClass(callback); //获取一级分类
    },
    getFirstClass: function (callback) {
        $.ajax({
            url: "/Class/P_GetFirstClass",
            type: "Post",
            data: {},
            dataType: "json",
            success: function (data) {
                if (data.Code < 0) {
                    Dalert(data.Desc);
                } else {
                    var listdata = {
                        list: data.Data
                    }
                    var html = template('flist', listdata);
                    $("#defaultfc").after(html);
                    if (callback) {
                        //callback("fc");
                    }
                }
            },
            error: function () {

            }
        });
    },
    firstChange: function (callback, value) {  //根据父ID获取分类
        var fid = $("#firstID").val();

        if (value == "fc") {
            fid = $("#firstID").val();
        }
        else if (value == "sc") {
            fid = $("#secondID").val();
        }
        $.ajax({
            url: "/Class/P_GetChildrenByFatherID",
            type: "Post",
            data: { "fatherID": fid },
            dataType: "json",
            success: function (data) {
                if (data.Code < 0) {
                    Dalert(data.Desc);
                } else {
                    var listdata = {
                        list: data.Data
                    }
                    if (value == "fc") {
                        var html = '<option value="0" >无</option>' + template('slist', listdata);
                        $("#secondID").html(html);
                        if (callback) {
                            callback("sc");
                        }
                    }
                    else {
                        if (value == "sc") {
                            var html = '<option value="" >无</option>' + template('tlist', listdata);
                            $("#thirdID").html(html);
                            getthird();
                        }
                    }

                }
            },
            error: function () {

            }
        })
    },
    callback: function (value) {   //分类回调
        var fid = $("#fid").val(), sid = $("#sid").val(); tid = $("#tid").val();
        if (value == "fc" && fid > 0) {
            $("#firstID option").each(function () {
                if ($(this).val() == fid) {
                    $(this).attr("selected", "selected");
                } else {
                    $(this).removeAttr("selected");
                }
            });
        }
        if (value == "sc" && sid > 0) {
            $("#secondID option").each(function () {
                if ($(this).val() == sid) {
                    $(this).attr("selected", "selected");
                } else {
                    $(this).removeAttr("selected");
                }
            });
        }
        Class.firstChange(Class.callback, value);
    }
}
//获取第三级分类
function getthird() {
    tid = $("#tid").val();
    if (tid > 0) {
        $("#thirdID option").each(function () {
            if ($(this).val() == tid) {
                $(this).attr("selected", "selected");
            } else {
                $(this).removeAttr("selected");
            }
        })
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

function addInfo(type) {
    var id = GetQueryStringByName("id");
    if (parseInt(id) > 0) {

    } else {
        id = 0;
    }
    var reid =  GetQueryStringByName("reid");
    var img = $("#saveimg").val();
    var display = $("#display").val();
    var orderby = $("#orderby").val();
    switch (type) {
        case "1": {
            var cid = $("#thirdID").val();
            if (cid == 0) {
                cid = $("#secondID").val();
                if (cid == 0) {
                    cid = $("#firstID").val();
                }
            }
            $.ajax({//SaveRC(string reID, string rcID, string cID, string img, string orderby, string display)
                url: "/APP_Market/SaveRC",
                type: "Post",
                data: { reID: reid, rcID:id, cID: cid, img: img, orderby: orderby, display: display },
                dataType: "json",
                success: function (data) {
                    Dalert(data.Desc);
                }
            });
            break;
        }
        case "2": {
            var bid = $("#brandlist").val();
            $.ajax({//SaveRB(string reID, string rbID, string bID, string img, string orderby, string display)
                url: "/APP_Market/SaveRB",
                type: "Post",
                data: { reID: reid, rbID: id, bID: bid, img: img, orderby: orderby, display: display },
                dataType: "json",
                success: function (data) {
                      Dalert(data.Desc);
                }
            });
            break;
        }
        case "3": {
            var sid = $("#spuName").attr("data");
            var sname = $("#spuName").val();
            var sprice = $("#spuPrice").val();
            $.ajax({//SaveRS(string reID, string rsID, string sID,string spuName,string spuPrice, string img, string orderby, string display)
                url: "/APP_Market/SaveRS",
                type: "Post",
                data: { reID: reid, rsID: id, sID: sid, spuName: sname, spuPrice: sprice, img: img, orderby: orderby, display: display },
                dataType: "json",
                success: function (data) {
                    Dalert(data.Desc);
                }
            });
            break;
        }
    }
}

function getSpu(sid) {

    $.ajax({
        url: "/APP_Market/GetSPUByID",
        type: "Post",
        data: { sID: sid},
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