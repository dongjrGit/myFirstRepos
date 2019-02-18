//添加收货地址
$(document).ready(function () {

    var id = GetQueryStringByName("dd");//获取地址栏参数 js/GetQueryString.js
    if ($.trim(id) != "" && id != undefined) {
        $("#title").html("编辑地址");
        GetForm(id);//获取表单 id：地址id
    } else {
        $("#title").html("添加地址");
    }
    GetProvData(0, "");//获取省

    //省改变
    $("#adProv").change(function () {
        var p1 = $(this).children('option:selected').val();
        $.ajax(({
            type: "post",
            url: "/pc/user/selectAreas",
            dataType: "json",
            data: { "RegionType": 1,
            	    "ParentCode": p1 },
            success: function (rsl) {
                if (rsl.code == 0) {
                    var optionstring = "";
                    for (var i in rsl.data) {
                        var jsonObj = rsl.data[i];
                        optionstring += "<option value=\"" + jsonObj.code + "\" >" + jsonObj.name + "</option>";
                    }
                    $("#adCity").html("<option value=''>请选择</option> " + optionstring);
                    $("#adArea").html("<option value=''>请选择</option>");
                }
            },
            error: function (e) {
                Dalert(e.statusText);
            }
        }));
    })

    //市改变
    $("#adCity").change(function () {
        var p1 = $(this).children('option:selected').val();
        $.ajax(({
            type: "post",
            url: "/pc/user/selectAreas",
            dataType: "json",
            data: { RegionType: 2, ParentCode: p1 },
            success: function (rsl) {
                if (rsl.code == 0) {
                    var optionstring = "";
                    for (var i in rsl.data) {
                        var jsonObj = rsl.data[i];
                        optionstring += "<option value=\"" + jsonObj.code + "\" >" + jsonObj.name + "</option>";
                    }
                    $("#adArea").html("<option value=''>请选择</option> " + optionstring);
                }
            },
            error: function (e) {
                //Dalert(e.statusText);
            }
        }));
    })

    //表单验证
    function check() {
      return  $("#addrForm").validate({
            rules: {
                recName: {
                    required: true
                },
                adProv: {
                    required: true
                },
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
                    required: true,
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
            },
            errorPlacement: function (error, element) {
                error.appendTo(element.parent());
            }
        })
    }
    function Control() {
        if (id != "" && id != undefined) {
            EditAddress(id);//编辑地址
        } else {
            AddAddress();//添加地址
        }
    }
    //返回按钮
    $("#backBtn").click(function () {
        BackHref();
    })
    //保存按钮
    $("#savebtn").click(function () {
        if(check().form())
        Control();
    })
});
//获取省
function GetProvData(rType, pCode) {
    $.ajax(({
        type: "post",
        url: "/pc/user/selectAreas",
        dataType: "json",
        async: false,
        data: { "RegionType": rType,
        	"ParentCode": pCode },
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
                Dalert(rsl.desc);
            }
        },
        error: function (e) {
            //alert(e.statusText);
        }
    }));
}
//表单提交 
function AddAddress() {
    $("#savebtn").hide();
    var recName = $("#recName").val();
    var adProv = $("#adProv").val();
    var adCity = $("#adCity").val();
    var adArea = $("#adArea").val();
    var adProvName = $("#adProv option:selected").text().trim();
    var adCityName = $("#adCity option:selected").text().trim();
    var adAreaName = $("#adArea option:selected").text().trim();
    var adInfo = $("#adInfo").val();
    var phoneNum = $("#phoneNum").val();
    var telNum = $("#telNum").val();
    var telArea = $("#telArea").val();
    var telExt = $("#telExt").val();
    var isHome = $("input[name=isHome]:checked").val();
    //var defAd = $("input[name=defAd]:checked").val();
    //$("#isHome").val(isHome);
    //$("#defAd").val(defAd);
   /* if (isHome == 1) {
        SetHomeAdr("")
    }*/

    $.ajax(({
        type: "post",
        url: "/pc/user/addReceiverAddr",
        dataType: "json",
        data: {
            "name": recName,
            "provinceCode": adProv,
            "provinceName": adProvName,
            "cityCode": adCity,
            "cityName": adCityName,
            "areaCode": adArea, 
            "areaName": adAreaName,
            "address": adInfo, 
            "isHome": isHome,
            "mobile": phoneNum,
            "isDefault": 0,
            "telArea": telArea, 
            "telNum": telNum,
            "telExt": telExt, 
            "isReceive": true
        },
        //data:$("#addrForm").serialize(),//form表单提交 需添加jquery.form.js 
        success: function (rsl) {
            if (rsl.code == 0) {
                BackHref();
            }
            else {
               $("#savebtn").show(); BackHref(); 
            }
        },
        error: function (e) {
            // Dalert(e.statusText);
        }
    }));

}
//修改地址adid：地址id
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
    var telArea = $("#telArea").val();
    var telExt = $("#telExt").val();
    var isHome = $("input[name=isHome]:checked").val();
   /* if (isHome == 1) {
        SetHomeAdr(adid)
    }*/
    //var defAd = $("input[name=defAd]:checked").val();
    $.ajax(({
        type: "post",
        url: "/pc/user/updReceiverAddr",
        dataType: "json",
        data: {
            "adID": adid, 
            "name": recName,
            "provinceCode": adProv,
            "provinceName": adProvName,
            "cityCode": adCity,
            "cityName": adCityName,
            "areaCode": adArea, 
            "areaName": adAreaName,
            "address": adInfo, 
            "isHome": isHome,
            "mobile": phoneNum,
            "isDefault": 0,
            "telArea": telArea, 
            "telNum": telNum,
            "telExt": telExt, 
            "isReceive": true
        },
        success: function (rsl) {
            if (rsl.code == 0) {
               BackHref();
            }
        },
        error: function (e) {
            // Dalert(e.statusText);
        }
    }));
}
//获取表单
function GetForm(id) {
    $.ajax(({
        type: "post",
        url: "/pc/user/selectAddrInfo",
        dataType: "json",
        data: { "adID": id },
        success: function (rsl) {
            if (rsl.code == 0) {
                var ad = rsl.data;
                $("#recName").attr("value", ad.name);
               
                $("#adInfo").attr("value", ad.address);
                $("#phoneNum").attr("value", ad.mobile);
                $("#telNum").attr("value", ad.telNumber);
                $("#telArea").attr("value", ad.telAreacode);
                $("#telExt").attr("value", ad.telExtension);
                if (ad.ishome == 1) {
                    $("input[name='isHome']").first().attr("checked", "checked");
                    $("input[name='isHome']").last().removeAttr("checked");
                } else {
                    $("input[name='isHome']").last().attr("checked", "checked");
                    $("input[name='isHome']").first().removeAttr("checked");
                }
                
                $("#adProv").val( ad.provincecode);
                GetCitySelect();
                $("#adCity").val( ad.citycode);
                GetAreaSelect();
                $("#adArea").val( ad.areacode);
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
    $.ajax(({
        type: "post",
        url: "/pc/user/selectAreas",
        dataType: "json",
        data: { "RegionType": 1,
        	"ParentCode": p1 },
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
        },
        error: function (e) {
            //alert(e.statusText);
        }
    }));
}
//获取区
function GetAreaSelect() {
    var p1 = $("#adCity").val();
    $.ajax(({
        type: "post",
        url: "/pc/user/selectAreas",
        dataType: "json",
        data: { "RegionType": 2,
        	"ParentCode": p1 },
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
        },
        error: function (e) {
            //alert(e.statusText);
        }
    }));
}

function BackHref() {
    location.href = "/member/userInfo/receiveAddress.html";
}

/*function SetHomeAdr(adid) {
    $.ajax({
        type: "post",
        url: "/Address/B_isHomeAddress",
        dataType: "json",
        data: {
            "isHomeID": adid
        },
        success: function (rsl) {
            if (rsl.code == 0) {

            }
            else {
                Dalert(rsl.desc);
            }
        },
        error: function (e) {
            //alert(e.statusText);
        }
    });

}*/