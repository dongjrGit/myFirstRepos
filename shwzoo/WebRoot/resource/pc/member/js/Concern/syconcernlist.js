//用户中心首页关注列表
//商品关注
$(document).ready(function () {

    //加载关注列表
   Concern.getlist(Concern.callbackForList);

});
var hiindex = 1;
var hicount = 0;
var psize = 5;
var Concern = {
    /*uid:用户id
      index：起始页码
    */
    getlist: function (callback) {
        $.ajax({
            type: "post",
            url: "/Concern/GetSkuConcerns",
            dataType: "json",
            data: { page: hiindex, limit: psize },
            success: function (rsl) {
                if (rsl.Code == 0) {
                    var listdata = {
                        list: rsl.Data
                    }
                    var html = template('conlist', listdata);

                    $("#ul_conlist").html(html);
                    if (callback) {
                        callback();
                    }
                   
                }
                else {
                }
            },
            error: function (e) {
                //alert(e.statusText);
            }
        });
    },
    callbackForList: function () {
        $("#conlast").unbind("click");
        $("#connext").unbind("click");

        $("#conlast").bind("click", function () {
            if (hiindex > 1) {
                hiindex -= 1;
                Concern.getlist(Concern.callbackForList);
            }
        });

        $("#connext").bind("click", function () {
            if ((hiindex * hissize) < hicount) {
                hiindex += 1;
                Concern.getlist(Concern.callbackForList);
            }
        });

        //会员中心首页
        var hisnum = $("#ul_conlist li").length;
        if (hisnum != null && parseInt(hisnum) > 0) {
            $("#noconlist").hide();

            $("#hasconlist").show();

        }
    }
}