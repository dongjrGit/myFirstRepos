var ch = 3;
var receiveraddresss = {
    creataddress : function(href) {
        //添加收货地址
        //var orderid = GetQueryStringByName("orderid");
        var name = $("#name").val();
        var mobile = $("#mobile").val();
        var address = $("#address").val();
        var provinceName = $("#province option:selected").text();
        var cityName = $("#city option:selected").text();
        var areaName = $("#area option:selected").text();
        var provinceCode =$("#province option:selected").val();
        var cityCode = $("#city option:selected").val();
        var areaCode = $("#area option:selected").val();
        var isDefault = 0;
        if (document.getElementById("moren").checked)
            isDefault = 1;
        $.ajax(( {
            type : "post",
            url : "/api/wap/receiveraddress/addReceiverAddr",
            dataType : "json",
            data : {
                name : name,
                mobile : mobile,
                provinceCode : provinceCode,
                cityCode : cityCode,
                areaCode : areaCode,
                provinceName : provinceName,
                cityName : cityName,
                areaName : areaName,
                address : address,
                isDefault : isDefault,
                ch : ch
            },
            success : function(fh) {
                if (fh.code == 0) {
                    alert(fh.desc);
                    location.href = href==""?"/wap/receiveraddress/selectAddrByUserId":href;//"/wap/receiveraddress/selectAddrByUserId?ch=3&href="+href;;
                } else {
                    alert(fh.desc);
                }
            },
            error : function(e) {

            }
        }))

    },
    //删除收货地址
    deteleaddress : function(id) {
        $.ajax(( {
            type : "post",
            url : "/api/wap/receiveraddress/delReceiverAddr",
            dataType : "json",
            data : {
                id : id,
                ch : ch
            },
            success : function(fh) {
                if (fh.code == 0) {
                	 alert(fh.desc);
                    window.location.reload();
                } else {
                    alert(fh.desc);
                }
            },
            error : function(e) {

            }
        }))
    },
    //修改收货地址
    updateaddress : function(href) {
        var name = $("#name").val();
        var id = $("#province").attr("value");
        var mobile = $("#mobile").val();
        var address = $("#address").val();

        var pcode = $("#province option:selected").val();      
        var ccode = $("#city option:selected").val();       
        var acode = $("#area option:selected").val();
        var province = $("#province option:selected").text();      
        var city = $("#city option:selected").text();       
        var area = $("#area option:selected").text();
        var isDefault = 0;
        if (document.getElementById("moren").checked)
            isDefault = 1;
        $.ajax(( {
            type : "post",
            url : "/api/wap/receiveraddress/updAddr",
            dataType : "json",
            data : {
                id : id,
                name : name,
                mobile : mobile,
                province : province,
                city : city,
                area : area,
                provincecode : pcode,
                citycode : ccode,
                areacode : acode,
                address : address,
                isDefault : isDefault,
                ch : ch
            },
            success : function(fh) {
                if (fh.code == 0) {
                    alert(fh.desc);
                    location.href = "/wap/receiveraddress/selectAddrByUserId?ch=3&href="+href;
                } else {
                    alert(fh.desc);
                }
            },
            error : function(e) {

            }
        }))

    },
    //查询所有的省
    GetProvData : function() {
        $.ajax(( {
            type : "post",
            url : "/api/wap/getaddress/selectAllProvice",
            dataType : "json",
            data : {
                ch : ch
            },
            success : function(rsl) {
                if (rsl.code == 0) {
                    var optionstring = "";
                    for (var i in rsl.data) {
                        var jsonObj = rsl.data[i];
                        optionstring += "<option value=\"" + jsonObj.code + "\" >" + jsonObj.name + "</option>";
                    }
                    $("#province").html(optionstring);

                } else {
                    alert(rsl.Desc);
                }
            },
            error : function(e) {
                //alert(e.statusText);
            }
        }));
    },
    //获取对应省的市
    GetCitySelect : function() {
        var p1 = document.getElementById("province").value;
        if (p1 != 110000) {
            $.ajax(( {
                type : "post",
                url : "/api/wap/getaddress/selectAllCity",
                dataType : "json",
                data : {
                    proviceCode : p1,
                    ch : ch
                },
                async : false,
                success : function(rsl) {
                    if (rsl.code == 0) {
                        var optionstring = "";
                        for (var i in rsl.data) {
                            var jsonObj = rsl.data[i];
                            optionstring += "<option value=\"" + jsonObj.code + "\" >" + jsonObj.name + "</option>";
                        }
                        $("#city").html("<option value=''>请选择</option> "+optionstring);
                    }
                },
                error : function(e) {
                    //alert(e.statusText);
                }
            }));
        } else {
        }
    }
}
$(function() {
    //省改变
    $(".Eidvua-dizhi").on("change","#province",function() {
        var p1 = document.getElementById("province").value;
        $.ajax(( {
            type : "post",
            url : "/api/wap/getaddress/selectAllCity",
            dataType : "json",
            data : {
                proviceCode : p1,
                ch : ch
            },
            success : function(rsl) {
                if (rsl.code == 0) {
                    var optionstring = "";
                    for (var i in rsl.data) {
                        var jsonObj = rsl.data[i];
                        optionstring += "<option value=\"" + jsonObj.code + "\" >" + jsonObj.name + "</option>";
                    }
                    $("#city").html("<option value=''>请选择</option> "+optionstring);
                    $("#area").html("<option value=''>请选择</option> ");
                }
            },
            error : function(e) {                
            }
        }));
    }),
    //市改变
    $(".Eidvua-dizhi").on("change","#city",function() {
        var ci = document.getElementById("city").value;
        $.ajax(( {
            type : "post",
            url : "/api/wap/getaddress/selectAllArea",
            dataType : "json",
            data : {
                cityCode : ci,
                ch : ch
            },
            success : function(rsl) {
                if (rsl.code == 0) {
                    var optionstring = "";
                    for (var i in rsl.data) {
                        var jsonObj = rsl.data[i];
                        optionstring += "<option value=\"" + jsonObj.code + "\" >" + jsonObj.name + "</option>";
                    }
                    $("#area").html(optionstring);
                }
            },
            error : function(e) {               
            }
        }));
    })
})
