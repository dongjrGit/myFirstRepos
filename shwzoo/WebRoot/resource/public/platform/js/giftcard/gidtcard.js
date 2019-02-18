//礼品卡
var pcount, pindex, psize = size_product;
var gift = {
  
    getlist: function (index) {
        //所属店铺 编号 名称 审核状态 开始时间-开始 开始时间-结束  结束时间-开始  结束时间-结束
    	cardcode = $("#cardcode").val();
    	cardname = $("#cardname").val();
    	cardtype = $("#cardtype").val();
    	cardface = $("#cardface").val();
    	cardpc = $("#cardpc").val();
    	checkss = $("#checkss").val();
    	starts = $("#starts").val();
    	starte = $("#starte").val();
        $.ajax({
            url: "/platform/coupon/giftcardList",
            type: "Post",
            data: {
                "page": index, "size": psize, "cardcode": cardcode,"name":cardname,"cardtype": cardtype, "cardface": cardface, "cardpc": cardpc,
                "checkss": checkss, "starts": starts, "starte": starte
            },
            dataType: "json",
            success: function (data) {
                if (data.code < 0) {
                    Dalert(data.desc);
                } else {
                    var listdata = {

                        list: data.data
                    }

                    var html = template('packlist', listdata);
                    //html 填充
                    $("#datalist").html(html);
                    pcount = data.maxRow;
                    pindex = data.pageIndex;
                    //分页
                    $("#pager").html(pagination(pcount, pindex, psize, "gift.getlist"));
                }
            },
            error: function () {

            }
        });
    },
    inputExcel:function(){
    	var contentHTML = "";
        contentHTML += '<div><form id="form" action="/platform/shop/importcard" method="post" enctype="multipart/form-data" >';
        contentHTML += '<input type="file" id="file"  style="width:220px"  name="file" value="浏览..">';
        contentHTML += '<input type="button" onclick="input()"   class="inquire" value="导入"/></form></div>';
        d = dialog({
            title: '导入',
            content: contentHTML,
            width: 280,
            height:30,
        });
       
        d.show();
    },
    del: function (id) {
        if (confirm("确定要删除吗？")) {
            $.ajax({
                url: "/platform/coupon/deletecard",
                type: "Post",
                data: { "id": id },
                dataType: "json",
                success: function (data) {
                    if (data.code == 0) {
                    	Dalert(data.desc, "", refresh);
                    } else {
                        Dalert(data.desc);
                    }
                }
            })
        }
    },save:function(){
		var id=$('#id').val();
		var remark=$('#remark').val();
		var name=$('#name').val();
		var saveimg=$('#saveimg').val();
		 $.ajax({
	            url: "/platform/coupon/updatecard",
	            type: "Post",
	            data: {
	                "saveimg": saveimg, "name": name, "remark": remark,"id":id
	            },
	            dataType: "json",
	            success: function (data) {
	                Dalert(data.desc,"",backgref)
	            },
	            error: function () {

	            }
	        });
		
	} 
}
 
//页面刷新
function refresh() {
	location.href="/platform/market/giftcard_list";
}
