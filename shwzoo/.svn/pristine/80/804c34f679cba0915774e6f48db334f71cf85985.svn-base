//订单详情

//根据状态显示操作按钮
function GetStatus(id, ss) {
	var zt = "", cz = "";
	switch (ss) {
	case 0:
		zt = "待付款";
		cz = "";
		break;
	case 1:
		zt = "出票中";
//		cz = "<input name='' type='button' value='发货' onclick=order.fh(" + id
//				+ "," + ss + ") class='inquire' />";
		break;
	case 2:
		zt = "待使用";
		cz = "";
		break;
	case 3:
		zt = "审核中";
		cz = "<input name='' type='button' value='查看原因' onclick=order.thhsh("
			+ id + ",9,2) class='inquire' />";
		break;
	case 4:
		zt = "已取消";
		/*cz = "<input name='' type='button' value='查看原因' onclick=order.qxqr("
				+ id + ",'" + $("#reason").val() + "',4) class='inquire' /> ";*/
		break;
	case 5:
		zt = "未付款已取消";
		cz = "";
		$("#span_reason").show();
		break;
	case 6:
		zt = "已付款取消申请中";
		cz = "<input name='' type='button' value='查看原因' onclick=order.qxqr("
				+ id + ",'" + $("#reason").val() + "',6) class='inquire' />";
		break;
	case 7:
		zt = "已付款已取消";
		cz = "";
		$("#span_reason").show();
		break;
	case 8:
		zt = "已完成";
		cz = "";
		break;
	case 9:
		zt = "已完结";
		cz = "";
		break;
	case 10:
		zt = "已退款";
		cz = "";
		$("#span_reason").show();
		break;
	}
	$("#status").html(zt);
	$("span[name=ddcz]").html(cz);
}