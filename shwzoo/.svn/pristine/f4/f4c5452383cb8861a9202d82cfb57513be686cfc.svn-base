//添加收货地址
$(document).ready(function () {
    var aid = "";

    GetProvData(0, "");
    //地址添加点击
    $(".l_xzshdzrig span, .l_xzshdzrig a").click(function () {
        $(".l_xzszall").css("display", "block")
    });
    //关闭地址添加
    $(".l_gban").click(function () {
        $(".l_xzszall").css("display", "none")
    });
    //地址确定点击
    $("#addaddr").click(function () {
        if (check().form()) {
            if (aid != "" && aid != null) {
                EditAddress(aid); aid = "";
            } else {
                AddAddress();
            }
        }
    });

    //省改变
    $("#adProv").change(function () {
        var p1 = $(this).children('option:selected').val();
        $.ajax({
            type: "post",
            url: "/pc/user/selectAreas",
            dataType: "json",
            data: { RegionType: 1, ParentCode: p1,"ch":"1" },
            success: function (rsl) {
                if (rsl.code == 0) {
                    var optionstring = "";
                    for (var i = 0; i < rsl.data.length; i++) {
                        var jsonObj = rsl.data[i];
                        optionstring += "<option value=\"" + jsonObj.code + "\" >" + jsonObj.name + "</option>";
                    }
                    $("#adCity").html("<option value=''>请选择</option> " + optionstring);
                    $("#adArea").html("<option value=''>请选择</option>");
                }
            }
        });
    })

    //市改变
    $("#adCity").change(function () {
        var p1 = $(this).children('option:selected').val();
        $.ajax({
            type: "post",
            url: "/pc/user/selectAreas",
            dataType: "json",
            data: { RegionType: 2, ParentCode: p1,"ch":"1" },
            success: function (rsl) {
                if (rsl.code == 0) {
                    var optionstring = "";
                    for (var i = 0; i < rsl.data.length; i++) {
                        var jsonObj = rsl.data[i];
                        optionstring += "<option value=\"" + jsonObj.code + "\" >" + jsonObj.name + "</option>";
                    }
                    $("#adArea").html("<option value=''>请选择</option> " + optionstring);
                }
            }
        });
    })

    //删除
    $("body").on("click",".deleteAd", function () {
        var id = $(this).parent().find("input").val();
        if (id != "" && id != null) {
            del(id);
        }
    });
    //编辑
    $("body").on("click",".editAd", function () {
        var id = $(this).parent().parent().find("input").val();
        if (id != "" && id != null) {
            $("#addressdiv").attr("style", "display:block");
            GetForm(id);
            aid = id;
        }

    })
    //设为默认
     $("body").on("click",".setDef", function () {
        var id = $(this).parent().find("input").val();
        if (id != "" && id != null) {
            SetHomeAdr(id);
        }
    })
    //表单验证
    function check() {
        var res = $("#addr_Form").validate({
            rules: {
                recName: {
                    required: true
                }
                ,
                adProv: {
                    required: true
                }
                ,
                adCity: {
                    required: true
                },
                adArea: {
                    required: true
                },
                adInfo: {
                    required: true
                },
                phoneNum: {
                    required: true
                    ,
                    isMobile: true
                }
            },
            messages: {
                recName: {
                    required: "请输入收件人姓名"
                },
                adProv: {
                    required: "请选择省"
                },
                adCity: {
                    required: "请选择市"
                },
                adArea: {
                    required: "请选择区"
                },
                adInfo: {
                    required: " 请输入详细地址"
                },
                phoneNum: {
                    required: "请输入手机号码",
                    isMobile: "输入的手机号格式不正确"
                }
            }
            ,
            errorPlacement: function (error, element) {
                error.appendTo(element.parent());
            }
        });
        return res;
    }

});
//获取省
function GetProvData(rType, pCode) {
    $.ajax(({
        type: "post",
        url: "/pc/user/selectAreas",
        dataType: "json",
        async: false,
        data: { RegionType: rType, ParentCode: pCode,"ch":"1" },
        success: function (rsl) {
            if (rsl.code == 0) {
                var optionstring = "";
                for (var i in rsl.data) {
                    var jsonObj = rsl.data[i];
                    optionstring += "<option value=\"" + jsonObj.code + "\" >" + jsonObj.name + "</option>";
                }
                $("#adProv").html("<option value=''>请选择</option> " + optionstring);

            }
            else {
                //alert(rsl.Desc);
            }
        },
        error: function (e) {
            //alert(e.statusText);
        }
    }));
}
//表单提交
function AddAddress(uID) {
    var recName = $("#recName").val();
    var adProv = $("#adProv").val();
    var adCity = $("#adCity").val();
    var adArea = $("#adArea").val();
    var adProvName = $("#adProv option:selected").text();
    var adCityName = $("#adCity option:selected").text();
    var adAreaName = $("#adArea option:selected").text();
    var adInfo = $("#adInfo").val();
    var phoneNum = $("#phoneNum").val();
    var telNum = $("#telNum").val();
    var telArea = "";
    var telExt = "";
    var isHome = 0 
    if (isHome == 1) {
        SetHomeAdr("")
    }

    $.ajax(({
        type: "post",
        url: "/pc/user/addReceiverAddr",
        dataType: "json",
        data: {
            userID: uID, name: recName, provinceCode: adProv, provinceName: adProvName, cityCode: adCity, cityName: adCityName, areaCode: adArea, areaName: adAreaName, address: adInfo, isHome: isHome,
            mobile: phoneNum, isDefault: 0, telArea: telArea, telNum: telNum, telExt: telExt, isReceive: true
        },
        //data:$("#addrForm").serialize(),//form表单提交 需添加jquery.form.js 
        success: function (rsl) {
            if (rsl.code == 0) {
                alert(rsl.desc);
            }
            else {
                alert(rsl.desc);
            } 
            location.reload();
        },
        error: function (e) {
            //alert(e.statusText);
        }
    }));

}
//修改地址
function EditAddress(adid) {
    var recName = $("#recName").val();
    var adProv = $("#adProv").val();
    var adCity = $("#adCity").val();
    var adArea = $("#adArea").val();
    var adProvName = $("#adProv option:selected").text();
    var adCityName = $("#adCity option:selected").text();
    var adAreaName = $("#adArea option:selected").text();
    var adInfo = $("#adInfo").val();
    var phoneNum = $("#phoneNum").val();
    var telNum = $("#telNum").val();
    var telArea = "";
    var telExt = "";
    var isHome = 0; //$("input[name=isHome]:checked").val();
    if (isHome == 1) {
        SetHomeAdr(adid)
    }
    //var defAd = $("input[name=defAd]:checked").val();
    $.ajax(({
        type: "post",
        url: "/pc/user/updReceiverAddr",
        dataType: "json",
        data: {
            adID: adid, name: recName, provinceCode: adProv, provinceName: adProvName, cityCode: adCity, cityName: adProvName, areaCode: adArea, areaName: adAreaName,
            address: adInfo, isHome: isHome, mobile: phoneNum, isDefault: 0, telArea: telArea, telNum: telNum, telExt: telExt, isReceive: true
        },
        success: function (rsl) {
            if (rsl.code == 0) {
                alert(rsl.desc);
            }
            else {
                alert(rsl.desc);
            } location.reload();
        }
    }));
}
//获取表单
function GetForm(id) {

    $.ajax(({
        type: "post",
        url: "/pc/user/selectAddrInfo",
        dataType: "json",
        data: { adID: id },
        success: function (rsl) {
            if (rsl.code == 0) {
                var ad = rsl.data;
                $("#recName").attr("value", ad.name);
                $("#adProv").attr("value", ad.provincecode);
                var opList = document.getElementById("adProv").childNodes;
                
                for (var i = 0, len = opList.length; i < len; i++) {
                    if (opList[i].value ==ad.provincecode) {
                        opList[i].selected = true;
                        break;
                    }
                }
                GetCitySelect();
                $("#adCity").attr("value", ad.citycode);
                var opList = document.getElementById("adCity").childNodes;
                
                for (var i = 0, len = opList.length; i < len; i++) {
                    if (opList[i].value ==ad.citycode) {
                        opList[i].selected = true;
                        break;
                    }
                }
                GetAreaSelect();
                $("#adArea").attr("value", ad.areacode);
                var opList = document.getElementById("adArea").childNodes;
                
                for (var i = 0, len = opList.length; i < len; i++) {
                    if (opList[i].value ==ad.areacode) {
                        opList[i].selected = true;
                        break;
                    }
                }
                $("#adInfo").attr("value", ad.address);
                $("#phoneNum").attr("value", ad.mobile);
                $("#telNum").attr("value", ad.telNumber);

                //if (ad.IsDefault == 1) {
                //    $("input[name='defAd']").first().attr("checked", "checked");
                //    $("input[name='defAd']").last().removeAttr("checked");
                //} else {
                //    $("input[name='defAd']").last().attr("checked", "checked");
                //    $("input[name='defAd']").first().removeAttr("checked");
                //}

            }

        }
    }));
}
//获取市
function GetCitySelect() {
    var p1 = $("#adProv").val();
    $.ajax({
        type: "post",
        url: "/pc/user/selectAreas",
        dataType: "json",
        data: { RegionType: 1, ParentCode: p1,"ch":"1" },
        async: false,
        success: function (rsl) {
            if (rsl.code == 0) {
                var optionstring = "";
                for (var i in rsl.data) {
                    var jsonObj = rsl.data[i];
                    optionstring += "<option value=\"" + jsonObj.code + "\" >" + jsonObj.name + "</option>";
                }
                $("#adCity").html("<option value='0'>请选择</option> " + optionstring);
                $("#adArea").html("<option value='0'>请选择</option>");
            }

        }
    });
}
//获取区
function GetAreaSelect() {
    var p1 = $("#adCity").val();
    $.ajax({
        type: "post",
        url: "/pc/user/selectAreas",
        dataType: "json",
        data: { RegionType: 2, ParentCode: p1,"ch":"1" },
        async: false,
        success: function (rsl) {
            if (rsl.code == 0) {
                var optionstring = "";
                for (var i in rsl.data) {
                    var jsonObj = rsl.data[i];
                    optionstring += "<option value=\"" + jsonObj.code + "\" >" + jsonObj.name + "</option>";
                }
                $("#adArea").html("<option value='0'>请选择</option> " + optionstring);
            }

        }
    });
}
//删除地址
function del(id) {
    var d = dialog({
        title: "删除",
        content: "确认要删除收货地址吗？",
        okValue: "确认",
        cancelValue: "取消",
        ok: function () {
            $.ajax({
                type: "post",
                url: "/pc/user/delReceiverAddr",
                dataType: "json",
                data: {
                    "id": id,
                    "ch":"1"
                },
                success: function (rsl) {
                    alert(rsl.desc);
                    location.reload();
                }
            })
        },
        cancel: function () { }
    }).show();

}


function SetHomeAdr(adid) {
    $.ajax({
        type: "post",
        url: "/Address/B_isHomeAddress",
        dataType: "json",
        data: {
            isHomeID: adid
        },
        success: function (rsl) {
            if (rsl.Code == 0) {

            }
            else {
                alert(rsl.Desc);
            }
        }
    });

}