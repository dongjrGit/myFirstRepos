//中绿专区
var pindex = 1;
var isclick = false;
//滑动距离
var hasdata = true;
var myScroll;
var pullFlag = 0;
//判断下拉1 上拉0
$(document).ready(function() {
	myScroll = new IScroll('#wrapper', {
		preventDefault : false,
		tap : true,
		click : true,
		probeType : 3,
		mouseWheel : true
	});

	// myScroll.on('scroll', positionJudge);
	myScroll.on("scrollEnd", function() {
		if (this.y >= 0 && this.maxScrollY != 0) {//下拉刷新操作
			// pindex=pindex+1;
			// hasdata=true;
			// getzqlist();
		} else {
			if (this.y - this.maxScrollY == 0 && this.y != 0)//到底部
				if (hasdata) {
					pindex = pindex + 1;
					getzqlist();
				}
		}
		myScroll.refresh();
	});

	//标签点击
	$('.sep-dqxz li').click(function() {
		var select = $(this).attr("sele");
		if (select == 3) {
			window.location.href = "/wap/find/findzlzqmap.html";
		} else {
			if (select != 0) {
				isclick = true;
				hasdata = true;
				pindex = 1;
				getzqlist();
			}
		}
	})
	//分类点击
	$('#zqclass_ul li').click(function() {
		isclick = true;
		hasdata = true;
		pindex = 1;
		getzqlist();
	})
	//地区点击
	$('#seleadr').click(function() {
		$('.merchantJS-mask').show();
	})
	//地区选择
	$('.sep-dqxz-areacon span').click(function() {
		$('#seleadr p span').text($(this).html());
		$('.merchantJS-mask').hide();
		$('#seleadr').addClass('active');
		$("#h_pcode").val($(this).attr("pcode"));
		pindex = 1;
		isclick = true;
		getzqlist();
	})
	getzqlist();
	 
	// document.addEventListener('touchmove', function(e) {
	// e.preventDefault();
	// }, false);
	myScroll.refresh();
})
//获取信息
function getzqlist() {
	var psize, id, isrecommend = "", istop = "", provincecode = "";

	psize = 10;
	$("#zqclass_ul li").each(function() {//分类id
		if ($(this).attr("class") == "active") {
			id = $(this).attr("data");
		}
	});

	var sele = "";
	$(".sep-dqxz li").each(function() {
		if ($(this).attr("class") == "active") {
			sele = $(this).attr("sele");
		}
	});
	if (sele == 0) {
		provincecode = $("#h_pcode").val();
	}
	if (sele == 1) {
		istop = 0;
	}
	if (sele == 2) {
		isrecommend = 0;
	}
	$.ajax({
		type : "post",
		url : "/wap/zlzx/getwapzqpagelist",
		dataType : "json",
		data : {
			"pageindex" : pindex,
			"pagesize" : psize,
			"cid" : id,
			"isrecommend" : isrecommend,
			"istop" : istop,
			"provincecode" : provincecode
		},
		success : function(rsl) {
			if (rsl.code == 0) {
				if (rsl.data.length > 0) {
					var listdata = {
						list : rsl.data
					}
					var pagehtml = template('lilist', listdata).replace("[[asd]]",GetQueryStringByName("b"));
					if (isclick) {
						$("#ullist").html(pagehtml);
					} else {
						$("#ullist").append(pagehtml);
					}
					hasdata = true;
				} else {
					if (isclick) {
						$("#ullist").html("");
					}
					hasdata = false;
				}
			} else {
				hasdata = false;
			}
			isclick = false;
			myScroll.refresh();
		},
		error : function(e) {

		}
	})

}

