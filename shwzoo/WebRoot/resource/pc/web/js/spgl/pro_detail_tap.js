//商品spuid
$(document).ready(function (e) {
 
 SPUComment.bind();
 var spuId= $("#spuId").val();
 Consult.getlist(1, spuId);
                //商品咨询列表
                //Consult.getlist(1, spuid);       
 
 

    
    //商品评论列表
    //SPUComment.getList(1, 0);

    //商品咨询添加
    $("#a_consultsubmit").on("click", function () {
        Consult.add($("#spuId").val());
    });
    //商品咨询列表
    //Consult.getlist(1, spuid);
    //发表评论跳转
    $(".a_gocomment").bind("click", function () {
        if (!user.IsLogin()) {
            //显示登录弹出层
            showlogindiv();
            return;
        }
        window.location.href = '/Member/DdZx/CommentList';
    });

    //标签切换
    $("#fixed").find("li").click(function () {
        $("#fixed").find("li").removeClass("l_xzzt");
        $(this).toggleClass("l_xzzt");

        var num = $(this).attr("id").split('_')[1];
        $("#pro_detial_tap_" + num).attr("style", "display:block");//显示标签对应选项页
        for (i = 0; i < 6; i++) {
            if (i != num && i != 1) {
                $("#pro_detial_tap_" + i).attr("style", "display:none");
            }
        }
        switch (num) {
            case "1":
                break;
            case "2":
                break;
            default:
                break;
        }
    });
    //var sInfo = $("#spuInfo").val();
    //var pList = $("#packingList").val();
    //var afService = $("#afterService").val();
    //$("div[name=div_spuInfo]").html(sInfo);
    //$("div[name=div_packingList]").html(pList);
    //$("div[name=div_afterService]").html(afService);

});

function changetype(obj, type) {
    SPUComment.getList(1, type);
    $(".active").attr('class', 'normal');
    $(obj).attr('class', 'active');
}
function GetSpecs(skuid, mark) {
    if (skuid == "" || skuid == null || skuid == undefined) {
        return;
    }
    $.ajax({
        url: "/pc/products/getspecsbyskuid",
        type: "post",
        data: {  "skuid": skuid, "mark": mark },
        dataType: "json",
        success: function (res) {
            if (res.code == 0) {
                var data = res.data;
               
                if (mark == 3) {
                var dl = "", ht = "";
                for (var i = 0; i < data.length; i++) {
                    var typename = data[i].name;
                    if (typename == null || typename == undefined) {
                        typename = "";
                    }
                    var dd = "";
                    dl = " <dl class='standard'><dt>" + typename + "</dt>";
                    for (var j = 0; j < data[i].childs.length; j++) {
                        dd += "<dd><label>" + data[i].childs[j].name + "</label><span>" + data[i].childs[j].value + " </span></dd>";
                    }
                    ht += dl + dd + " </dl>";
                }
                $("div[name=div_specsList]").html(ht);
                }
                if (mark == 2) {
                var li = "";
                for (var i = 0; i < data.length; i++) {
                    for (var j = 0; j < data[i].childs.length; j++) {
                        li += "<li>" + data[i].childs[j].name + ":" + data[i].childs[j].value + "</li>";
                    }
                }
                $("ul[name=InfoList]").html(li);
                }
            }
        }
    });
}
