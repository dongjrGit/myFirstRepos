//店铺关注
$(document).ready(function () {
    var userID = $("#userID").val();
    Concern.bind(1);

    //单个取消关注
    $(".canselC").live("click", function () {
        var id = $(this).attr("value");
        if (confirm("确定要取消关注吗")) {
            del(id);
        }
        //ConfirmShow("确定要取消关注吗?", del, id);//confirm弹出框 js/dialogShow.js
    });
    //全选
    $("input[name=ckall]").change(function () {
        if ($(this).attr("checked") == "checked") {
            $('input[name="shopconcern"]').attr("checked", true);
        }
        else {
            $('input[name="shopconcern"]').attr("checked", false);
        }
        var ck = $(this).attr("checked");
        $("input[name=ckall]").each(function () {
            if (ck == "checked") {
                $('input[name="ckall"]').attr("checked", true);
            } else {
                $('input[name="ckall"]').attr("checked", false);
            }
        })
    });
    //多个取消关注
    $(".cancelall_shop").click(function () {

        var idList = "";
        $('input[name="shopconcern"]:checked').each(function () {
            var id = $(this).val();
            if (id != "" && id != undefined) {
                idList += id + ",";
            }
        });
        if (idList != "") {
            if (confirm("确认取消选取的关注吗?")) {
                delList(idList);
            }
            //ConfirmShow("确认取消选取的关注吗?", delList, idList);
        }
        else {
            alert("请勾选要取消的关注");
        }

    });

});
//取消多个关注
function delList(idList) {
    idList = idList.substring(0, idList.length - 1);
    $.ajax({
        type: "post",
        url: "/pc/concern/delMoreCollectShop",
        dataType: "json",
        data: {
            "cIDList": idList,
            "ch":1
        },
        success: function (rsl) {
            if (rsl.code == 0) {

                alert(rsl.desc)
                refresh();
            } else {
                alert(rsl.desc);
            }
        },
        error: function (e) {
            //alert(e.statusText);
        }
    })
}

/*取消单个关注
  id:关注id
*/
function del(id) {
    $.ajax({
        type: "post",
        url: "/pc/concern/delCollectShop",
        dataType: "json",
        data: {
        	"shopId": id
        },
        success: function (rsl) {
            if (rsl.code == 0) {
                alert(rsl.desc);
                refresh();
            } else {
                alert(rsl.desc);
            }
        },
        error: function (e) {
            //alert(e.statusText);
        }
    })
}
//刷新
function refresh() {
    location.reload();
}
//关注列表 分页数据绑定
var psize = 10;
var Concern = {
    /*
  index：起始页码
*/
    bind: function (index) {
        $.ajax({
            type: "post",
            url: "/pc/concern/selectShopCollect",
            dataType: "json",
            data: { "page": index,
            	"size": psize ,
            	"ch":1},
            success: function (rsl) {
            	
                if (rsl.code == 0) {
                    $(".wdys").removeClass("none");

                    var listdata = {
                        list: rsl.data
                    }
                    var html = template('concernslist', listdata);

                    $("#shopConList").html(html);

                    pcount = rsl.maxRow;
					pindex = rsl.pageIndex;

                    $("#pager").html(pagination(pcount, pindex, psize, "pagelist"));

                    $(".shopgoods").each(function () {
                        var shopid = $(this).attr("data");
                        if (shopid != "" && shopid != undefined) {
                        	
                            getshopgood(shopid, $(this));
                        }
                    });
                }
                else {
                    $(".wdys").removeClass("none");
                    $(".wdys").html("<div class='l_mygzddp'><i></i>您还没有关注任何店铺</div>");
                }
            },
            error: function (e) {
                //alert(e.statusText);
            }
        });
    }
}
//分页回调
function pagelist(index) {
    Concern.bind(index);
}

function getshopgood(shopid, selecter) {
	
    $.ajax({
        type: "post",
        url: "/pc/concern/GetSpuByShopID",
        dataType: "json",
        data: { "shopid": shopid,
        	"ch":1
        	},
        success: function (rsl) {
            if (rsl.code == 0) {
            	
                var data = rsl.data;
                if (data != null && data.length > 0) {
                    var li = "";
                    var lit = "</li>";
                    //<li>
                    //<a href="#"><img src="images/tp_11.png"></a>
                    //<p>￥ 699.00</p>
                    //</li>
                    for (var i = 0; i < data.length; i++) {
                        var p=data[i].price;
                        if (p == undefined) {
                            p = 0;
                        }
                        li += "<li><a href='/web/products/proinfo.html?spuid="+data[i].id+"'><img src='" + data[i].imgurl + "'></a><p>￥ " + p + "</p></li>";
                    }
                    selecter.html(li);
                }

            }
        },
        error: function (e) {
            //alert(e.statusText);
        }
    });
}

