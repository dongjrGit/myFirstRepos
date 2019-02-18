var ch=3;
var concernShop = {
    //获取收藏的店铺列表
    getconsernshoplist: function (type)
    {
        $("#shops").show();
        $("#spu").hide();
        $.ajax(({
            type: "post",
            url: "/wap/concern/selectCollect",
            dataType: "json",
            data: { token: "", type:type , ch: ch },
            success: function (fh)
            {
                if (fh.code == 0) {
                    if (fh.datas != "") {
                        var concernshoplist = {
                            list: fh.Data
                        }
                        var html = template('shoplist', concernshoplist);
                            $("#shops").html(html);
                    }
                    else { $("#shops").html("<div class='l_dpqjdmk'><i></i>您还没有关注任何店铺</div>"); }
                }
                else { $("#shops").html("<div class='l_dpqjdmk'><i></i>您还没有关注任何店铺</div>"); 
                    }
            },
            error:function(){{ $("#shops").html("<div class='l_dpqjdmk'><i></i>数据请求失败</div>"); }}
        }))
    },
    //获取收藏的商品列表
    getconcedrnprolist: function (type)
    {
        $("#spu").show();
        $("#shops").hide();
        $.ajax(({
            type: "post",
            url: "/wap/concern/selectCollect",
            dataType:"json",
            data: { token: "", type:type , ch: ch },
            success: function (fh)
            {
                if (fh.code == 0) {
                    if (fh.data != "") {
                        var concernprolist =
                            {
                                list: fh.data
                            }
                        var html = template('spulist', concernprolist);
                        $("#spu").html(html);
                    }
                    else { $("#spu").html("<div class='l_mslist'><i></i>您还没有关注任何商品</div>"); }

                }
                else { $("#spu").html("<div class='l_mslist'><i></i>您还没有关注任何商品</div>"); }
            },
            error: function () { $("#spu").html("<div class='l_mslist'><i></i>数据请求失败</div>"); }
        }))
    }
}