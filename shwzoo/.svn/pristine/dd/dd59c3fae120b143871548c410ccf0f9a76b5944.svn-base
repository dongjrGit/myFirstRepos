//售后服务
var pcount;
var pindex;
var psize = 10;
var pseachstr;
//完成服务
var p_completestatus;

var AtterService = {
    //售后服务订单列表
    getOrderList: function (index, seachstr) {
        pseachstr = seachstr;
        $.ajax(({
            type: "post",
            url: "/pc/khFwmemb/getorder",
            dataType:"json",  
            async: false,
            cache: false, 
            data: { "page": index,
            	 "size": psize,
            	  "SearchStr": seachstr,
            	  "ch": 1},
            success: function (rsl) {

                if (rsl.code == 0) {
                    var listdata = {
                        list: rsl.data
                    }
                   
                    var html = template('orderslist', listdata);

                   
                    //翻页时删除表头以外的所有节点，避免after()方法重复加载
                    $("#tr_listtitle").parent().children().each(function () {
                        if ($(this).attr('id') != "tr_listtitle") {
                            this.parentNode.removeChild(this);
                        }
                    })
                    $("#tr_listtitle").after(html);
                	pcount = rsl.maxRow;
					pindex = rsl.pageIndex;
                    $("#pager").html(pagination(pcount, pindex, psize, "orders_pagelist"));

                    //产品页面跳转
                   // $(".a_spuhref").on("click", Goods_Href);

                }
                else {
                    Dalert(rsl.desc);
                }
            },
            error: function(jqXHR, error, errorThrown) {
                alert(jqXHR.status);          
                },
        }));
        exit;
    },getfkList: function (index, seachstr) {
        pseachstr = seachstr;
        $.ajax(({
            type: "post",
            url: "/pc/khFwmemb/queryMemberFeedBack",
            dataType:"json",  
            async: false,
            cache: false, 
            data: { "page": index,"size": psize},
            success: function (rsl) {

                if (rsl.code == 0) {
                    var listdata = {
                        list: rsl.data
                    }
                   
                    var html = template('completelist', listdata);

                   
                    //翻页时删除表头以外的所有节点，避免after()方法重复加载
                    $("#tr_listtitle").parent().children().each(function () {
                        if ($(this).attr('id') != "tr_listtitle") {
                            this.parentNode.removeChild(this);
                        }
                    })
                    $("#tr_listtitle").after(html);
                	pcount = rsl.maxRow;
					pindex = rsl.pageIndex;
                    $("#pager").html(pagination(pcount, pindex, psize, "orders_pagelist"));

                    //产品页面跳转
                   // $(".a_spuhref").on("click", Goods_Href);

                }
                else {
                    Dalert(rsl.desc);
                }
            },
            error: function(jqXHR, error, errorThrown) {
                alert(jqXHR.status);          
                },
        }));
        exit;
    },
    //获取完成的售后服务列表
    getCompleteList: function (index, status) {
        p_completestatus = status;
        $.ajax(({
            type: "post",
            url: "/pc/khFwmemb/getAfterservice",
            dataType: "json",
            async: false,
            data: { "status": status,
            	"page": index, 
            	"size": psize,
            	"ch":1},
            success: function (rsl) {
            
                if (rsl.code == 0) {
                    var listdata = {
                        list: rsl.data
                    }

                    var html = template('completelist', listdata);

                    //翻页时删除表头以外的所有节点，避免after()方法重复加载
                    $("#tr_listtitle").parent().children().each(function () {
                        if ($(this).attr('id') != "tr_listtitle") {
                            this.parentNode.removeChild(this);
                        }
                    })
                    $("#tr_listtitle").after(html);

                    pcount = rsl.maxRow;
					pindex = rsl.pageIndex;
                    $("#pager").html(pagination(pcount, pindex, psize, "complete_pagelist"));
                }
                else {
                    Dalert(rsl.desc);
                }
            },
            error: function (e) {

            }
        }));
    }
}

//售后服务订单列表分页回调
function orders_pagelist(index) {
    AtterService.getOrderList(index, pseachstr);
}

//完成服务列表分页回调
function complete_pagelist(index) {
    AtterService.getCompleteList(index, p_completestatus);
}

//产品页面跳转
function Goods_Href(skuid) {
  //  var skuid = parseInt($(this).attr("sku-val"));
    $.ajax(({
        type: "post",
        url: "/pc/khFwmemb/getspuid",
        dataType: "json",
        data: { "skuid": skuid },
        success: function (rsl) {
            if (rsl.code == 0) {
                var spuid = parseInt(rsl.data);
                if (spuid > 0) {
                    window.location.href = "/web/products/proinfo.html?spuid=" + spuid;

                }
            }
            else {
                Dalert(rsl.desc);
            }
        },
        error: function (e) {

        }
    }));
}