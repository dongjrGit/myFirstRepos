//评论订单列表 分页数据绑定
var pcount;
var pindex;
var psize = 10;
var indexlog;

var CommentOrderList = {
    bind: function (index) {
        $.ajax(({
            type: "post",
            url: "/Comment/B_GetListForComment",
            dataType: "json",
            data: { PageIndex: index, PageSize: psize, CommentType: 0 },
            success: function (rsl) {
                if (rsl.Code == 0) {
                    var listdata = {
                        list: rsl.Data
                    }
                    var html = template('commentorderlist', listdata);

                    //翻页时删除表头以外的所有节点，避免after()方法重复加载
                    $("#commentorderlist_title").parent().children().each(function () {
                        if ($(this).attr('id') != "commentorderlist_title") {
                            this.parentNode.removeChild(this);
                        }
                    })
                    $("#commentorderlist_title").after(html);

                    indexlog = index;
                    pcount = rsl.MaxRow;
                    pindex = rsl.PageIndex;
                    //alert(pindex);
                    $("#pager").html(pagination(pcount, pindex, psize, "commentorder_pagelist"));
                    //发表评论div设定
                    SubmitDivSetting();
                }
                else {
                    Dalert(rsl.Desc);
                }
            },
            error: function (e) {

            }
        }));
    },
    getlistByStatus: function (index, status) {
        $.ajax(({
            type: "post",
            url: "/Comment/B_GetListForComment",
            dataType: "json",
            data: { PageIndex: index, PageSize: psize, CommentType: status },
            success: function (rsl) {
                if (rsl.Code == 0) {
                    var listdata = {
                        list: rsl.Data
                    }
                    var html = template('commentorderlist', listdata);

                    //翻页时删除表头以外的所有节点，避免after()方法重复加载
                    $("#commentorderlist_title").parent().children().each(function () {
                        if ($(this).attr('id') != "commentorderlist_title") {
                            this.parentNode.removeChild(this);
                        }
                    })
                    $("#commentorderlist_title").after(html);

                    indexlog = index;
                    pcount = rsl.MaxRow;
                    pindex = rsl.PageIndex;
                    //alert(pindex);
                    $("#pager").html(pagination(pcount, pindex, psize, "commentorder_pagelist"));
                    //设置显示名称
                    switch (status) {
                        case 0:
                            $(".a_showsubmitdiv").text("发表评论");
                            break;
                        case 1:
                            $(".a_showsubmitdiv").text("继续评论");
                            break;
                        case 2:
                            $(".a_showsubmitdiv").text("查看评论");
                            break;
                    }
                    //发表评论div设定
                    SubmitDivSetting();
                }
                else {
                    Dalert(rsl.Desc);
                }
            },
            error: function (e) {

            }
        }));
    },
    bindByOrderDetail: function (index, orderdetailid) {
        $.ajax(({
            type: "post",
            url: "/Comment/B_GetListForComment",
            dataType: "json",
            data: { PageIndex: index, PageSize: psize, CommentType: -1, OrderDetailID: orderdetailid },
            success: function (rsl) {
                if (rsl.Code == 0) {
                    var listdata = {
                        list: rsl.Data
                    }
                    var html = template('commentorderlist', listdata);

                    //翻页时删除表头以外的所有节点，避免after()方法重复加载
                    $("#commentorderlist_title").parent().children().each(function () {
                        if ($(this).attr('id') != "commentorderlist_title") {
                            this.parentNode.removeChild(this);
                        }
                    })
                    $("#commentorderlist_title").after(html);

                    indexlog = index;
                    pcount = rsl.MaxRow;
                    pindex = rsl.PageIndex;
                    //alert(pindex);
                    $("#pager").html(pagination(pcount, pindex, psize, "commentorder_pagelist"));
                    //设置显示名称
                    var isComment = "";
                    var IsShowImg = "";
                     isComment = rsl.Data[0].IsComment;
                     IsShowImg = rsl.Data[0].IsShowImg;
                    if (isComment == "" || isComment == null) {
                        isComment = "0";
                    }
                    if (IsShowImg == "" || IsShowImg == null) {
                        IsShowImg = "0";
                    }

                    if (parseInt(isComment) == 0) {
                        $(".a_showsubmitdiv").text("发表评论");
                    }
                    else if (parseInt(isComment) == 1) {
                        if (parseInt(IsShowImg) == 0) {
                            $(".a_showsubmitdiv").text("继续评论");
                        } else if (parseInt(IsShowImg) == 1) {
                            $(".a_showsubmitdiv").text("查看评论");
                        }
                    }
                    //发表评论div设定
                    SubmitDivSetting();
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
//分页回调
function commentorder_pagelist(index) {
    if (indexlog != index) {
        CommentOrderList.bind(index);
    }
}

//弹出信息显示
function SubmitDivSetting() {
    $(".a_showsubmitdiv").bind("click", function () {
        var commentdata;
        var orderdetailID = $(this).parent().find("#hidden_orderdetailid").val();
        $.ajax(({
            type: "post",
            url: "/Comment/B_GetByOrderDetailID",
            dataType: "json",
            async: false,
            data: { OrderDetailID: orderdetailID },
            success: function (rsl) {
                if (rsl.Code == 0) {
                    commentdata = rsl.Data;
                }
                else {
                    alert(rsl.Desc);
                }
            },
            error: function (e) {

            }
        }));

        var sd = $(this).parent().parent().next().find(".div_submitcomment");
        if (sd.attr('hidden')) {
            sd.find("#text_commenttitle").attr("value", commentdata.Title);
            sd.find("#text_commentcontent").attr("value", commentdata.Content);
            //星级加载
            var starnum = 0;
            starnum = parseInt(commentdata.Star);
            if (starnum > 0) {
                var htmlstar1 = '<p class="star_p" id="p_stars">';
                var htmlstar2 = '';
                var htmlstar3 = '</p>';

                for (var i = 0; i < starnum; i++) {
                    htmlstar2 += '<img src="/mv10/images/icon_star_2.png" checkedval="1" title="" class="img_stars" />';
                }
                for (var i = 0; i < 5 - starnum; i++) {
                    htmlstar2 += '<img src="/mv10/images/icon_star_1.png" checkedval="0" title="" class="img_stars" />';
                }
                var objstar = sd.find("#td_stras");
                objstar.html(htmlstar1 + htmlstar2 + htmlstar3);
            }
            //图片加载
            var html = '';
            var html1 = '';//用户上传图片
            var html2 = '';//系统图片
            var html3 = '';
            var count = 0;
            if (commentdata.ShowImgList != null) {

                for (var i = 0; i < commentdata.ShowImgList.length; i++) {
                    html1 += '<div class="tjzp"><img width="115" high="142" src="' + commentdata.ShowImgList[i].ImgUrl + '" /></div>';
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
            if (parseInt(commentdata.ID) > 0) {
                if (parseInt(commentdata.Type) == 1) {
                    $(".a_commentsubmit").bind("click", function () {
                        AddShowImg(commentdata.ID);
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
                $(".a_commentsubmit").bind("click", function () {
                    var obj = $(this);
                    if (Check().form() && CheckStars()) {
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
            url: "/upLoad/UploadFile",
            secureuri: false,
            fileElementId: 'file_img',
            dataType: "json",
            //ftype:上传文件类型（图片文件=1，其他文件=2）
            //module: 模块（ 会员 = 0, 店铺 = 1, 平台 = 2, 产品 = 3, 其他 = 4,）
            data: { ftype: 1, module: 0 },
            type: 'POST',
            success: function (result) {
                if (result.Code == 0) {
                    html1 = '<img width="115" high="142" class="img_imgstr" src="' + result.Data[0] + '" />';
                    $(obj).find(".tjzp").html(html1);
                }
                else {
                    var errhtml = '<label id="img_imgs-error" class="error" for="img_imgs">' + result.Desc + '</label>';
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
//获取选中星星的个数
function GetCheckedStarsTotal() {
    var star = 0;
    var imgArray = $("#p_stars").children();
    for (var i = 0; i < imgArray.length; i++) {
        var val = parseInt($(imgArray[i]).attr("checkedval"));
        if (val == 1) {
            star += 1;
        }
    }
    return star;
}

//商品评论
function AddComment(obj) {
    var orderdetailID = obj.parent().find("#hidden_orderdetailid").val();
    var sellerID = obj.parent().find("#hidden_sellerid").val();
    var skuID = obj.parent().find("#hidden_skuid").val();
    //判断星星个数
    var star = 0;
    var imgArray = obj.parent().parent().parent().find("#p_stars").children();
    for (var i = 0; i < imgArray.length; i++) {
        var val = parseInt($(imgArray[i]).attr("checkedval"));
        if (val == 1) {
            star += 1;
        }
    }

    var title = obj.parent().parent().parent().find("#text_commenttitle").val();
    var content = obj.parent().parent().parent().find("#text_commentcontent").val();

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

    var showName = 0;
    if (obj.parent().find('#checked_showname').attr('checked')) {
        showName = 1;
    }

    $.ajax(({
        type: "post",
        url: "/Comment/B_AddComment",
        dataType: "json",
        async: false,
        data: { OrderDetailID: orderdetailID, SellerID: sellerID, SKUID: skuID, Star: star, Title: title, Content: content, ShowName: showName, ImgStrs: imgstrs },
        success: function (rsl) {
            if (rsl.Code == 0) {
                Dalert("评论成功", "", refresh);
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