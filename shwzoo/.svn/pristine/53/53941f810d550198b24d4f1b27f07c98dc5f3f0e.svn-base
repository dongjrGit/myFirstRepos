//用户优惠卷列表
var pcount, pindex, psize = size_common;
var couponuser = {
    bind: function () {
        $(".search").bind("click", function () { couponuser.getlist(1); });
        $(".add").bind("click", couponuser.add);
        $("body").on("click",".xgyfxxgz-close", function () {
            $("#adduser").css("display", "none");
        });
        $("input[name=chkall]").click(couponuser.checkAll);
        /*couponuser.getlist(1);*/
    },
    getlist: function (index) {
        name = $("#name").val();
        $.ajax({
        	  url: "/platform/coupon/getMenberList",
              type: "Post",
              data: { 
            	  "page": index, 
            	  "size": psize, 
            	  "username": $("#name").val(), 
            	  "couponid": $("#couponid").val(),
            	  "mobile": $("#select_mobile").val(), 
            	  "datef": $("#select_begin").val(), 
            	  "datet": $("#select_end").val()
            	  },
            dataType: "json",
            success: function (data) {
                if (data.code < 0) {
                    Dalert(data.desc);
                } else {
                    var listdata = {

                        list: data.data
                    }

                    var html = template('userlist', listdata);

                    //html 填充
                    $("#memberlist").html(html);
                    pcount = data.maxRow;
                    pindex = data.pageIndex;
                    //绑定删除事件
                    coupon.unit();
                    //分页
                    $("#pager1").html(pagination(pcount, pindex, psize, "couponuser.getlist"));
                }
            },
            error: function () {

            }
        });
    },
    add: function () {
        var userids = "";
        couponuser.getlist(1);
       
            var chks = document.getElementsByName("chksel");
            for (var i = 0; i < chks.length; i++) {
                if (chks[i].checked) {
                    if (userids == "") {
                        userids = chks[i].value;
                    }
                    else {
                        userids += "," + chks[i].value;
                    }
                }
            }
            if (userids != "") {
            	 if (confirm("您确定要添加选中会员吗？")) {
		            $.ajax({
		                url: "/platform/coupon/addUClist",
		                type: "Post",
		                data: { "userids": userids, "couponid": $("#couponid").val() },
		                dataType: "json",
		                success: function (data) {
		                    //Dalert(data.Desc);
		                    if (data.code == 0) {
		                        Dalert(data.desc, 3000);
		                        location.reload();
		                    } else {
		                        Dalert(data.desc);
		                    }
		                }
		            });
            	 } 
            } else {
                Dalert("没有选择会员！");
            }
       
    },
    checkAll: function () {
        if ($("input[name=chkall]").prop("checked") == true) {
            $("input[name=chksel]").prop({checked:true});
        }
        else {
            $("input[name=chksel]").prop({checked:false});
        }
    }
}