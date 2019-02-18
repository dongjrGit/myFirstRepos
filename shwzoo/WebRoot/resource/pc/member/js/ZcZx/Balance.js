//余额
var pcount;
var pindex;
var psize = 10;
var ptype;
var pstatus;
var ptimebucket;
var Balance = {
    //列表
    getList: function (index, timetype,status) {
        ptype = timetype;
        $.ajax(({
            type: "post",
            url: "/pc/coupon/balance",
            dataType: "json",
            data: { page: index, size: psize, timetype: timetype,status:status,financetype:0},
            success: function (rsl) {
                if (rsl.code == 0) {
                    var listdata = {
                        list: rsl.data
                    }
                    var html = template('balancelist', listdata);

                    //翻页时删除表头以外的所有节点，避免after()方法重复加载
                    $("#tr_title").parent().children().each(function () {
                        if ($(this).attr('id') != "tr_title") {
                            this.parentNode.removeChild(this);
                        }
                    })
                    $("#tr_title").after(html);

                    pcount = rsl.maxRow;
					pindex = rsl.pageIndex;					
                    $("#pager").html(pagination(pcount, pindex, psize, "balance_pagelist"));
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
function balance_pagelist(index) {
    Balance.getList(index, ptype,"");
}

//时段改变
function Balance_TimeBucket(obj) {
    var timeBut = $(obj).attr("timebut-val");
    Balance.getList(1,timeBut,null);
    $(".active").attr('class', 'normal');
    $(obj).attr('class', 'active');
}

//是否支付状态变更
function ChangeStatus(obj) {
    var status = $(obj).attr("status-val");
    if(status==""){
    	Balance.getList(1,0,status);
    }else{
    	Balance.getList(1,0,status);
    }
    
}


//充值第一步
function Balance_Pay1(obj) {
	var money = $("#text_monery").val();
	//var a = parseInt(money);
	if(isNaN(money)){
		alert("请输入有效数字");
	}
	else if (money<=0) {
		alert("请输入有效数字");
    }
	else{
		var moneryval = $("#text_monery").val();
	    window.location.href = '/member/coupon/balancepay2?money=' + moneryval;
	}  
}



//充值第二步提交
function Submit() {
    $("#formPay").submit();
}

//充值第三步提交按钮
function GoPay() {
    $("#formPay").submit();
}
