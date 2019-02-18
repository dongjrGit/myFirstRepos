//评论订单列表 分页数据绑定
var pcount;
var pindex;
var psize = 10;
var pstatus = 0;

var Comment = {
    //根据评论类型获取列表
    getList: function (index, status) {
        pstatus = status;
        $.ajax(({
            type: "post",
            url: "/pc/order/getcommentorder",
            dataType: "json",
            data: { page: index,
            	size: psize, 
            	Type: status,
            	ch: 1 },
            success: function (rsl) {
                if (rsl.code == 0) {
                    var listdata = {
                        list: rsl.data
                    }
                    
                    var html = template('commentorderlist', listdata);

                    //翻页时删除表头以外的所有节点，避免after()方法重复加载
                    $("#commentorderlist_title").parent().children().each(function () {
                        if ($(this).attr('id') != "commentorderlist_title") {
                            this.parentNode.removeChild(this);
                        }
                    })
                    $("#commentorderlist_title").after(html);

                    pcount = rsl.maxRow;
                    pindex = rsl.pageIndex;
                    //alert(pindex);
                    $("#pager").html(pagination(pcount, pindex, psize, "comment_pagelist"));

                    //产品页面跳转
                    $(".a_spuhref").bind("click", Goods_Href);

                    //数据列表为空样式加载
                    $("#div_nolist").remove();
                    if (listdata.list.length <= 0) {
                        var htmno = '<div class="l_wplsp" id="div_nolist"><img src="/resource/pc/web/images/plno.png"><p>当前暂无待评论的商品~</p></div>';
                        $("#table_commentlist").after(htmno);
                        return;
                    }

                    //设置显示名称
                    switch (status) {
                        case 0:
                            $(".a_showsubmitdiv").text("发表评论");
                            break;
                       /* case 1:
                            $(".a_showsubmitdiv").text("继续评论");
                            break;*/
                        case 1:
                            $(".a_showsubmitdiv").text("查看评论");
                            break;
                    }
                    //发表评论div设定
                    SubmitDivSetting();
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

//分页回调
function comment_pagelist(index) {
    Comment.getList(index, pstatus);
}

$(document).ready(function() {
	var pcount = $("#pro_total").val();
	var pindex = $("#pro_index").val();
	$("#pager").html(pagination(pcount, pindex, 20, "comment_pagelist"));
})

//弹出信息显示
function SubmitDivSetting() {
    $(".a_showsubmitdiv").on("click", function () {
        var commentdata;
        var orderdetailID = $(this).parent().find("#hidden_orderdetailid").val();
        $.ajax(({
            type: "post",
            url: "/pc/order/getcommentDetail",
            dataType: "json",
            async: false,
            data: { "orderdetailID": orderdetailID,
            	"ch":1},
            success: function (rsl) {
                if (rsl.code == 0) {
                    commentdata = rsl.data;
                   
                }
                else {
                    alert(rsl.desc);
                }
            },
            error: function (e) {

            }
        }));

        var sd = $(this).parent().parent().next().find(".div_submitcomment");
        if (sd.attr('hidden')) {
         /*   sd.find("#text_commenttitle").attr("value", commentdata.Title);*/
        	
            sd.find("#text_commentcontent").attr("value", commentdata.content);
           
            //星级加载
            var starnum = 0;
            starnum = parseInt(commentdata.star);
            if (starnum > 0 && starnum <= 5) {
                var objstar = sd.find("#td_stras");
                objstar.html(GetHtmForStars(starnum, 'p_stars'));
            }
            //描述相符
            var stardeciptnum = 0;
            stardeciptnum = parseInt(commentdata.starDepict);
            if (stardeciptnum > 0 && stardeciptnum <= 5) {
                var objdeciptstar = sd.find("#td_strasdepict");
                objdeciptstar.html(GetHtmForStars(stardeciptnum, 'p_starsdepict'));
            }
            //服务态度
            var starservicenum = 0;
            starservicenum = parseInt(commentdata.starService);
            if (starservicenum > 0 && starservicenum <= 5) {
                var objservicestar = sd.find("#td_strasservice");
                objservicestar.html(GetHtmForStars(starservicenum, 'p_starsservice'));
            }
            //发货速度
            var starspeednum = 0;
            starspeednum = parseInt(commentdata.starSpeed);
            if (starspeednum > 0 && starspeednum <= 5) {
                var objspeedstar = sd.find("#td_strasspeed");
                objspeedstar.html(GetHtmForStars(starspeednum, 'p_starsspeed'));
            }

            //图片加载
            var html = '';
            var html1 = '';//用户上传图片
            var html2 = '';//系统图片
            var html3 = '';
            var count = 0;
            
            if (commentdata.showImgList != null) {

                for (var i = 0; i < commentdata.showImgList.length; i++) {
                	
                    html1 += '<div class="tjzp"><img width="115" high="142" src="' + commentdata.showImgList[i].imgurl + '" /></div>';
                }
            }
            for (var i = 0; i < 5; i++) {
                html2 += '<a href="javascript:void(0)" class="a_addimg" onclick="uploadimg(this,event)"><div class="tjzp">添加照片</div></a>';
            }
            //html3 = '<p style="margin-top:123px;color:#A0A0A0;">' + count + '/5</p>';
            if (html1 != "") {
                html = html1 + html3;
            }
            else {
                html = html2 + html3;
            }
            sd.find("#td_imglist").html(html);


            sd.show();
            sd.attr('hidden', false);
            if (parseInt(commentdata.id) > 0) {
                if (parseInt(commentdata.Type) == 1) {
                    $(".a_commentsubmit").bind("click", function () {
                        AddShowImg(commentdata.id);
                        sd.hide();
                        sd.attr('hidden', true);
                    });
                }
                else {
                    $(".a_commentsubmit").bind("click", function () {
                        sd.hide();
                        sd.attr('hidden', true);
                    });
                }
            }
            else {
                $(".a_commentsubmit").on("click", function () {
                    var obj = $(this);
                    if (Checks(obj)) {
                        AddComment(obj);
                    }
                });
            }
        }
        else {
            sd.hide();
            sd.attr('hidden', true);
        }
    });
}

//图片上传
function uploadimg(obj, oEvent) {
    $("#file_img").click();
    $("#img_imgs-error").remove();
    $("#file_img").change(function () {
        $.ajaxFileUpload({
            url: "/app/api/img/upload",
            secureuri: false,
            fileElementId: 'file_img',
            dataType: "json",
            //ftype:上传文件类型（图片文件=1，其他文件=2）
            //module: 模块（ 会员 = 0, 店铺 = 1, 平台 = 2, 产品 = 3, 其他 = 4,）
            data: { relationtype : 5 },
            type: 'POST',
            success: function (result) {
                if (result.code == 0) {
                    html1 = '<img width="115" high="142" class="img_imgstr" src="' + result.data[0] + '" />';
                    $(obj).find(".tjzp").html(html1);
                }
                else {
                    var errhtml = '<label id="img_imgs-error" class="error" for="img_imgs">' + result.desc + '</label>';
                    $("#td_imglist").append(errhtml);
                }
                //TODO 结束正在加载中
            },
            error: function (e) {
                //alert(JSON.stringify(e));
                //TODO 结束正在加载中
            }
        });
    });
}

//校验
function Check() {
    return $("#submitForm").validate({
        rules: {
            text_commenttitle: {
                required: true
            },
            text_commentcontent: {
                required: true
            }
        },
        messages: {
            text_commenttitle: {
                required: "请输入标题"
            },
            text_commentcontent: {
                required: "请输入心得"
            }
        },
        errorPlacement: function (error, element) {
            error.appendTo(element.parent());
        },
        debug: true
    })
}
//校验星级是否选择
function CheckStars() {
    var result = false;
    var stars = GetCheckedStarsTotal();
    if (stars > 0 && stars <= 5) {
        result = true;
    }
    if (!result) {
        var html = '<label id="p_stars-error" class="error" for="p_stars">请点击评分星级</label>';
        $("#p_stars").append(html);
    }
    return result;
}

//新版校验
function Checks(obj) {
    //清空错误消息
    $(".error").remove();
    //商品评分星级校验
    var starsobj = $(obj).parents(".table_submit").find("#p_stars");
    var stars = GetCheckedStarsTotal(starsobj);
    if (stars <= 0 || stars > 5) {
        var html = '<label id="p_stars-error" class="error" for="p_stars">请点击评分星级</label>';
        $(starsobj).append(html);
        return false;
    }
    //描述相符星级校验
    var starsdepictobj = $(obj).parents(".table_submit").find("#p_starsdepict");
    var starsdepict = GetCheckedStarsTotal(starsdepictobj);
    if (starsdepict <= 0 || starsdepict > 5) {
        var html = '<label id="p_starsdepict-error" class="error" for="p_starsdepict">请点击评分星级</label>';
        $(starsdepictobj).append(html);
        return false;
    }
    //服务态度星级校验
    var starsserviceobj = $(obj).parents(".table_submit").find("#p_starsservice");
    var starsservice = GetCheckedStarsTotal(starsserviceobj);
    if (starsservice <= 0 || starsservice > 5) {
        var html = '<label id="p_starsservice-error" class="error" for="p_starsservice">请点击评分星级</label>';
        $(starsserviceobj).append(html);
        return false;
    }
    //发货速度星级校验
    var starsspeedobj = $(obj).parents(".table_submit").find("#p_starsspeed");
    var starsspeed = GetCheckedStarsTotal(starsspeedobj);
    if (starsspeed <= 0 || starsspeed > 5) {
        var html = '<label id="p_starsspeed-error" class="error" for="p_starsspeed">请点击评分星级</label>';
        $(starsspeedobj).append(html);
        return false;
    }

    //评论内容
    var content = $(obj).parents(".table_submit").find("#text_commentcontent").val();
    if (content == null || content == "") {
        var html = '<label id="p_content-error" class="error" for="p_content">请输入心得</label>';
        $(obj).parents(".table_submit").find("#td_commentcontent").append(html);
        return false;
    }

    return true;
}

//获取选中星星的个数
function GetCheckedStarsTotal(starsobj) {
    var star = 0;
    var imgArray = $(starsobj).children();
    for (var i = 0; i < imgArray.length; i++) {
        var val = parseInt($(imgArray[i]).attr("checkedval"));
        if (val == 1) {
            star += 1;
        }
    }
    return star;
}

//根据选择的星星个数生成html代码
function GetHtmForStars(starsnum, idvalue) {
    var htmlstar1 = '<p class="star_p" id="' + idvalue + '">';
    var htmlstar2 = '';
    var htmlstar3 = '</p>';

    for (var i = 0; i < starsnum; i++) {
        htmlstar2 += '<span class="xing-yellow" checkedval="1" title=""></span>';
    }
    for (var i = 0; i < 5 - starsnum; i++) {
        htmlstar2 += '<span class="xing-huise" checkedval="0" title=""></span>';
    }
    return htmlstar1 + htmlstar2 + htmlstar3;
}

//商品评论
function AddComment(obj) {
    var orderdetailID = obj.parent().find("#hidden_orderdetailid").val();
  /*  var sellerID = obj.parent().find("#hidden_sellerid").val();*/
    var skuID = obj.parent().find("#hidden_skuid").val();

    var star = 0;//商品评分
    var $obj = $(obj).parents(".table_submit").find("#p_stars");
    star = GetCheckedStarsTotal($obj);
    var stardepict = 0;//描述相符评分
    var $depictobj = $(obj).parents(".table_submit").find("#p_starsdepict");
    stardepict = GetCheckedStarsTotal($depictobj);
    var starservice = 0;//服务态度评分
    var $serviceobj = $(obj).parents(".table_submit").find("#p_starsservice");
    starservice = GetCheckedStarsTotal($serviceobj);
    var starspeed = 0;//发货速度评分
    var $speedobj = $(obj).parents(".table_submit").find("#p_starsspeed");
    starspeed = GetCheckedStarsTotal($speedobj);

    var title = obj.parent().parent().parent().find("#text_commenttitle").val();
    var content = obj.parent().parent().parent().find("#text_commentcontent").val();

    //上传图片路径
    var imgstrs = "";
    $(".img_imgstr").each(function (e) {
        var str = $(this).attr("src");
        if (str != null && str != "") {
            imgstrs += str + ",";
        }
    });
    if (imgstrs != "" && imgstrs.length > 0) {
        imgstrs = imgstrs.substring(0, imgstrs.length - 1);
    }

    var showName = 0;
    if (obj.parent().find('#checked_showname').attr('checked')) {
        showName = 1;
    }

    $.ajax(({
        type: "post",
        url: "/pc/comment/addComment",
        dataType: "json",
        async: false,
        data: { OrderDetailID: orderdetailID,
        	SellerID: "",
        	SKUID: skuID, 
        	Star: star, 
        	Content: content,
        	ShowName: showName, 
        	ImgStrs: imgstrs,
        	StarDepict: stardepict, 
        	StarService: starservice,
        	StarSpeed: starspeed,
        	ch:1},
        success: function (rsl) {
            if (rsl.code == 0) {
                Dalert("评论成功", "", refresh);
                //location.reload()
            }
            else {
                Dalert(rsl.desc);
            }
        },
        error: function (e) {

        }
    }));
}

//商品晒单
function AddShowImg(commentID) {

    //上传图片路径
    var imgstrs = "";
    $(".img_imgstr").each(function (e) {
        var str = $(this).attr("src");
        if (str != null && str != "") {
            imgstrs += str + "|";
        }
    });
    if (imgstrs != "" && imgstrs.length > 0) {
        imgstrs = imgstrs.substring(0, imgstrs.length - 1);
    }

    if (parseInt(commentID) > 0 && imgstrs != "") {
        $.ajax(({
            type: "post",
            url: "/Comment/B_ShowImgByID",
            dataType: "json",
            async: false,
            data: { CommentID: commentID, ImgStrs: imgstrs },
            success: function (rsl) {
                if (rsl.Code == 0) {
                    Dalert("晒单成功", "", refresh);
                    //location.reload()
                }
                else {
                    Dalert(rsl.Desc);
                }
            },
            error: function (e) {

            }
        }));
    }
}
function refresh() {
    location.reload();
}

//产品页面跳转
function Goods_Href() {
    var skuid = parseInt($(this).attr("sku-val"));
    $.ajax(({
        type: "post",
        url: "/pc/khFwmemb/getspuid",
        dataType: "json",
        data: {  "skuid": skuid },
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