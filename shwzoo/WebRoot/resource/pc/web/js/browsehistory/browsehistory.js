var hissize = 5;
var hiindex = 1;
var hicount = 0;
//浏览记录
var BrowseHistory = {
    getList: function (callback) {
        $.ajax(({
            type: "post",
            url: "/BrowseHistory/B_GetHistoryList",
            dataType: "json",
            data: { Index: hiindex, Size: hissize, ChannelType: 0 },
            success: function (rsl) {
                if (rsl.Code == 0) {
                    var listdata = {
                        list: rsl.Data
                    }
                    $("#ul_hislist").html(template('hislist', listdata));
                    hicount = rsl.MaxRow;
                    hiindex = rsl.PageIndex;
                    if (callback) {
                        callback();
                    }
                }
                else {

                }
            },
            error: function (e) {

            }
        }));
    },
    callbackForList: function () {
        $("#div_last").unbind("click");
        $("#div_next").unbind("click");

        $("#div_last").bind("click", function () {
            if (hiindex > 1) {
                hiindex -= 1;
                BrowseHistory.getList(BrowseHistory.callbackForList);
            }
        });

        $("#div_next").bind("click", function () {
            if ((hiindex * hissize) < hicount) {
                hiindex += 1;
                BrowseHistory.getList(BrowseHistory.callbackForList);
            }
        });

        //会员中心首页
        var hisnum = $("#ul_hislist li").length;
        if (hisnum == null || parseInt(hisnum) <= 0) {
            $("#div_nohistory").show();
            $("#a_morehis").remove();
            $("#div_yeshistory").remove();
           
        }
    },
    add: function () {
        if (user.IsLogin()) {
            //商品spuid
            var spuid = GetQueryStringByName("sid");
            if (parseInt(spuid) > 0) {
                $.ajax(({
                    type: "post",
                    url: "/BrowseHistory/B_SaveHistory",
                    dataType: "json",
                    data: { SPUID: spuid, ChannelType: 0 },
                    success: function (rsl) {
                        if (rsl.Code == 0) {

                        }
                    },
                    error: function (e) {
                    }
                }));
            }
        }
    }
}