var pcount;
var pindex;
var psize = 10;
var pspuid;
//商品咨询
var Consult = {
    bind: function () {
        //分页绑定
        pcount = $("#consult_count").val();
        pindex = $("#consult_index").val();
        $("#consultpager").html(pagination(pcount, pindex, psize, "pagelist"));
    },
    //发表咨询
    add: function (spuid) {
        var type = $("input[name='radio_cousulttype']:checked").val();
        var content = $("#text_consultcontent").val();
        var shopId=$("#h_shopid").val();
        if (VarUtil.isNull(content)) {
            alert("请输入咨询内容");
            return;
        }

        if (!WebLogin.isLogin()) {
    		showlogindiv();
			return false;
		}
        var data=AjaxUtil.Post("/pc/products/addConsult", { spuId: spuid, consultType: type, consultContent: content,shopId:shopId })
         if (data.code == 0) {
                    $("#text_consultcontent").val("");
                    $("#div_addconsult").hide();
                    alert("发表咨询成功");
                }
                else {
                    alert(data.desc);
                }
    },
    //获取咨询列表
    getlist: function (index, spuid) {
        pspuid = spuid;
        var rsl=AjaxUtil.Post("/pc/products/getConsultPage", { spuId: spuid, page: index, size: psize });
       
        if (rsl.code == 0) {
            var listdata = {
                list: rsl.data
            }
            var html=template('consultlist', listdata);
           
            $("#div_consultlist").html(html);
           
            //分页绑定
            pcount = rsl.maxRow;
            pindex = rsl.pageIndex;
            $("#consultpager").html(pagination(pcount, pindex, psize, "pagelist"));
        }
        else {
            //alert(rsl.desc);
        }
    }
}

