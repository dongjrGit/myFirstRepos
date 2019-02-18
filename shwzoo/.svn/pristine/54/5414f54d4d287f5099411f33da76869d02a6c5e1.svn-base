/*编辑售后服务*/
$(document).ready(function () {
    var id = GetQueryStringByName("id");
    var ordercode = GetQueryStringByName("code")

    $("#orderCode").attr("value", ordercode);
    GetByID(id);
    //保存
    $("#confirmbtn").one("click", function () {
        var sta = $("#Status").val();
        $.ajax({
            url: "/AfterService/Confirm",
            type: "post",
            data: { id: id, status: sta },
            dataType: "json",
            success: function (data) {
                if (data.Code < 0) {
                    Dalert(data.Desc);
                } else {
                    Dalert(data.Desc, "", backhref);//location.href = "Aftersales";
                    //$("#confirmbtn").attr("value", "返回");
                    //$("#confirmbtn").attr("id", "backbtn");
                }

            }
        });
    })
    //返回
    $("#backBtn").on("click",null, function () {
        backhref();
    })
    //根据id获取服务 id：服务id
    function GetByID(id) {
        $.ajax({
            url: "/AfterService/GetByID",
            type: "post",
            data: { id: id },
            dataType: "json",
            success: function (data) {
                if (data.Code < 0) {
                    //Dalert(data.Desc);
                } else {
                    var af = data.Data;
                    $("#ContactsName").attr("value", af.ContactsName);
                    $("#Mobile").attr("value", af.Mobile);
                    $("#GetAddr").attr("value", af.GetAddr);
                    if (af.Type == 0) {
                        $("#Type").attr("value", "退货");
                    } else {
                        if (af.Type == 1) {
                            $("#Type").attr("value", "换货");
                        } else {
                            if (af.Type == 2) {
                                $("#Type").attr("value", "维修");
                            }
                        }
                    }
                    if (af.Credential == 0) {
                        $("#Credential").attr("value", "发票");
                    } else {
                        if (af.Credential == 1) {
                            $("#Credential").attr("value", "质检报告");
                        }
                    }
                    $("#Desc").attr("value", af.Desc);
                    $("#CreateTime").attr("value", af.CreateTime);
                    if (af.Status == 0) {
                        $("#Status")[0].selectedIndex = 0;
                    } else {
                        $("#Status")[0].selectedIndex = 1;
                    }
                    if (af.ImgUrl == "" || af.ImgUrl == null) {
                        $("#imgs").html("<div class='tjcpxx-con-form-upimg'>暂无照片</div>");//<img src='/web/imgs/g_58.png" />
                    } else {
                        var imgs = af.ImgUrl.split(';');
                        var html = "";
                        for (var i in imgs) {
                            html += "<div class='tjcpxx-con-form-upimg'><img src='" + imgs[i] + "' /></div>";
                        }
                        $("#imgs").html(html);
                    }

                }

            }
        });
    }

})
//返回服务列表
function backhref() {
    location.href = "Aftersales";
}

//获取传参
function GetQueryString(sProp) {
    var re = new RegExp("[&,?]" + sProp + "=([^//&]*)", "i");
    var a = re.exec(document.location.search);
    if (a == null)
        return "";
    return a[1];
}