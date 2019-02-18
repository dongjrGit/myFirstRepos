//商品关注
$(document).ready(function () {

    //加载关注列表
    Concern.bind(1);

    //单个取消关注
    $(".delsc").live("click", function () {
        var id = $(this).find("input").val();
        if (confirm("确定要取消关注吗?")) {
        	
            del(id);
        }
        //ConfirmShow("确定要取消关注吗?", del, id);//confirm弹出框 js/dialogShow.js
    });
    //多个取消关注
    $(".cancelall_spu").click(function () {

        var idList = "";
        $('input[name="spuconcern"]:checked').each(function () {
            var id = $(this).val();
            if (id != "" && id != undefined) {
                idList += id + ",";
            }
        });
        if (idList != "") {
            if (confirm("确认取消选取的关注吗?")) {
            	// var id =$('input[name="spuconcern"]').val();
            	delList(idList);
            }
            //ConfirmShow("确认取消选取的关注吗?", delList, idList);
        }
        else {
            alert("请勾选要取消的关注");
        }

    });
    //全选
    $("input[name=ckall]").change(function () {
        if ($(this).attr("checked") == "checked") {
            $('input[name="spuconcern"]').attr("checked", true);
        }
        else {
            $('input[name="spuconcern"]').attr("checked", false);
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
    //关注加入购物车
    $(".conaddcar").live("click", function () {
        //添加 callback:回调函数goodsid:组合商品ID或商品SKUID ispack:是否组合商品（组合商品为1 非组合商品未0）
        //spuid:商品SPUID(组合商品时为包含的商品种类数量) 
        //callback, shopid, goodsid, goodscount, ispack, spuid, ischecked:1
        var shopid = $(this).attr("data_sid");
        var spuid = $(this).attr("data_spuid");
        var skuid = $(this).attr("data_skuid");
        //获取秒杀活动ID
        var spikeid = "";
        if (shopid != undefined && spuid != undefined && skuid != undefined && shopid != "" && spuid != "" && skuid != "") {
        	cart.add(cart.addcallback, shopid, skuid, 1, 0, spuid, 1, spikeid);
        }
    });
});
/*取消单个关注
  id:关注id
*/
function del(id) {
    $.ajax({
        type: "post",
        url: "/pc/concern/delCollectSpu",
        dataType: "json",
        data: {
        	"spuId": id
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
            alert("取消关注错误");
        }
    })
}
/*取消多个关注
  idList:关注id集合 （格式：1，2，3，...）
*/
function delList(idList) {
    idList = idList.substring(0, idList.length - 1);
    $.ajax({
        type: "post",
        url: "/pc/concern/delMoreCollectSpu",
        dataType: "json",
        data: {
            "cIDList": idList,
            "ch":1
        },
        success: function (rsl) {
        	
            if (rsl.code == 0) {
                alert(rsl.desc);refresh();
            } else {
                alert(rsl.desc);
            }
        },
        error: function (e) {
            //alert(e.statusText);
        }
    })
}
function refresh() {
    location.reload();
}
//关注列表 分页数据绑定
var psize = 10;
var Concern = {
    /*uid:用户id
      index：起始页码
    */
    bind: function (index) {
    	
        $.ajax({
            type: "post",
            url: "/pc/concern/selectSpuCollect",
            dataType: "json",
            data: { "page": index,
            	"size": psize ,
            	"ch":1},
            success: function (rsl) {
            	
                if (rsl.code == 0) {
                    var listdata = {
                        list: rsl.data
                    }
                    var html = template('concernlist', listdata);
                   
                    $("#proConList").html(html);

                    pcount = rsl.maxRow;
					pindex = rsl.pageIndex;
                    $("#pager").html(pagination(pcount, pindex, psize, "pagelist"));

                }
                else {
                	
                    $(".wdys").html("<div class='l_mygzddp'><i></i>您还没有关注任何商品</div>");

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



