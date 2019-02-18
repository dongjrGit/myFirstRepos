//商品评论
$(document).ready(function() {
    var spuid = GetQueryStringByName("spuid");

    var star = "",isclick=false;
    
    callback = upAjax;//上拉加载数据

    //评论类型点击
    $("#commtype li").click(function() {
        index = 1;
        $("#commtype li").removeClass("red");
        $(this).addClass("red");
        star = $(this).find("a").attr("datatype");
        if (parseInt(star) >= 0 && parseInt(star) <= 2) {
            isclick = true;
            upAjax();
        } else {
            if (parseInt(star) == -1) {
                isclick = true;
                upAjax();
            }
        }
    })
    //根据spuID获取商品评论数据
    function upAjax() {
        $.ajax({
            url : "/api/wap/products/proCommentList",
            type : "Post",
            data : {
                "ch" : 3,
                "sid" : spuid,
                "page" : index,//iscroll_list已定义好直接引用
                "size" : 8,
                "star" : star
            },
            dataType : "json",
            success : function(res) {
                if (res.code == 0) {
                    var data = res.data;
                    if (data != undefined && data != null) {
                        var listdata = {
                            list : data.list
                        }
                        var html = template('comlist', listdata);
                        if (isclick) {
                            $("#list").html(html);
                        } else {
                            $("#list").append(html);
                        }
                        hasdata = true;//是否有数据iscroll_list中定义，没有数据index将不再增加页数
                        myScroll.refresh();//刷新加载
                    } else {
                        hasdata = false;
                        if (isclick) {
                            //$('#list').html("<li><font style='font-size:0.16rem;'>暂无数据</font></li>");
                        }
                    }
                } else {
                    if (isclick) {
                        //$('#list').html("<li><font style='font-size:0.16rem;'>暂无数据</font></li>");
                    }
                }
                isclick = false;
            },
            error : function() {
                $('#list').html("<li><font style='font-size:0.16rem;'>数据请求失败，请重新刷新</font><li>");
            }
        });        
    }

})

