//分页
//callback 获取当前页数据的函数，由外部传入
function pagination(pagecount, pageindex, pagerow, callback) {
	var pcount = pagecount;// 总条数
	var prow = pagerow;// 每页条数
	var pindex = pageindex;// 当前页
	var totalpage;
	var htmlpage = "";
	if (pcount % prow > 0) {
		totalpage = parseInt(pcount / prow) + 1
	} else {
		totalpage = parseInt(pcount / prow)
	}
	if (totalpage == 0) {
	} else {
		if (totalpage > 0 && pcount <= prow) {
			htmlpage = "<div class='pager'>";
			htmlpage += "<span class='pager-hover' >";
			htmlpage += "<a class='disadled' >&lt;</a>";
			htmlpage += "<a href='#' class='sel'>1</a>";
			htmlpage += "<a class='disadled' >&gt;</a>";
			htmlpage += "</span>";
			// 页面总数和页面跳转
			htmlpage += "<span class='pager-style'>共" + totalpage + "页,</span>";
			htmlpage += "到第<input id='pager_jump' type='text'  class='pager-inp'>页";
			htmlpage += " <input name='btnpage' type='button' value='确认' class='pager-but'>";
			htmlpage += "</div>";
		} else {
			htmlpage += "<div class='pager'>";
			htmlpage += "<span class='pager-hover' >";
			if (pageindex > 1) {
				htmlpage += "<a href='javascript:gotopage("
						+ (parseInt(pageindex) - 1) + "," + callback
						+ ");'>&lt;</a>";
			} else {
				htmlpage += "<a class='disadled' >&lt;</a>";
			}
			var strbreak = "<b >…</b>";
			for (var i = 1; i <= totalpage; i++) {
				if (pageindex <= 3) {
					if (i <= 5) {
						if (i == pageindex) {
							htmlpage += "<a href='javascript:gotopage(" + i
									+ "," + callback + ");' class='sel'>" + i
									+ "</a>";
						} else {
							htmlpage += "<a href='javascript:gotopage(" + i
									+ "," + callback + ");' class=''>" + i
									+ "</a>";
						}
					} else {
						htmlpage += strbreak;
						break;
					}

				} else {
					if (i <= pageindex) {
						if (i == parseInt(pageindex) - 3) {
							// //当前页大于等于6，显示第1页，和左靠近当前页的3页
							if (parseInt(pageindex) - 5 > 0) {
								htmlpage += strbreak;
								htmlpage += "<a href='javascript:gotopage(" + i
										+ "," + callback + ");' >" + i + "</a>";
							} else {
								htmlpage += "<a href='javascript:gotopage(" + i
										+ "," + callback + ");' >" + i + "</a>";
							}

						}

						else if (i >= parseInt(pageindex) - 3 || i == 1) {
							if (i == pageindex) {
								htmlpage += "<a href='javascript:gotopage(" + i
										+ "," + callback + ");' class='sel'>"
										+ i + "</a>";
							} else {
								htmlpage += "<a href='javascript:gotopage(" + i
										+ "," + callback + ");'>" + i + "</a>";
							}
						}
					} else {
						if (i == parseInt(pageindex) + 2) {
							// //当前页大于总页数减2，当前页后面全部显示
							if (parseInt(pageindex) + 2 < totalpage) {
								htmlpage += "<a href='javascript:gotopage(" + i
										+ "," + callback + ");' >" + i + "</a>";
								htmlpage += strbreak;
							} else {
								htmlpage += "<a href='javascript:gotopage(" + i
										+ "," + callback + ");' >" + i + "</a>";
							}

						}
						// //当前页小于总页数减2，显示最后1页，和右靠近当前页的2页
						// else if (i <= pageindex + 2 || i == totalpage) {
						else if (i <= parseInt(pageindex) + 2) {
							htmlpage += "<a href='javascript:gotopage(" + i
									+ "," + callback + ");' >" + i + "</a>";
						}
					}
				}
			}
			if (pageindex == totalpage) {
				htmlpage += "<a class='disadled' >&gt;</a>";
			} else {
				htmlpage += "<a href='javascript:gotopage("
						+ (parseInt(pageindex) + 1) + "," + callback
						+ ");'>&gt;</a>";
			}
			htmlpage += "</span>";
			// 页面总数和页面跳转
			htmlpage += "<span class='pager-style'>共" + totalpage + "页,</span>";
			htmlpage += "到第<input id='pager_jump' type='text'  class='pager-inp'>页";
			htmlpage += " <input name='btnpage' type='button' onclick='javascript:jump_page("
					+ callback + ");' value='确认' class='pager-but'>";

			htmlpage += "</div>";
		}
	}
	return htmlpage;
}
// 跳转
function jump_page(callback) {

	var num = $("#pager_jump").val();
	gotopage(num, callback);
	$("#pager_jump").val(num);
}

// 翻页
function gotopage(index, callback) {
	pindex = index;
	callback(index);
}
