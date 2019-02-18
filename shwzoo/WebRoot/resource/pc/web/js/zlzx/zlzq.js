var cid;
var pindex = 1, psize = 24;
var pcount;
$(function() {
	//获取专区分类
	getzqclass();

	//地图标签点击
	$("#select_li li").click(function() {
		if ($(this).attr("id") == "zlmap") {
			$("#main").show();
			$("#pager").hide();
			$("#allmap").hide();
			$("#zlzqlist").hide();
		} else {
			$("#main").hide();
			$("#pager").show();
			$("#allmap").hide();
			$("#zlzqlist").show();
		}
		var istop=$(this).attr("istop");
		var iscom=$(this).attr("iscom");
		if(istop==0){
			getzqlist(1, psize, cid,"",istop);
		}
		if(iscom==0){
			getzqlist(1, psize, cid,iscom);
		}
	});
	//分类标签点击
	$("#container").on("click", "#ul_class li", function() {
		$("#ul_class li").removeClass('active');
		$(this).addClass('active');
		cid = $(this).attr("data");
		getzqlist(1, psize, cid);
	});
})
function getzqlist(index, psize, id, isrecommend, istop, provincename, cityname, areaname) {
	if (!psize) {
		psize = 24;
	}
	$.ajax({
		type : "post",
		url : "/zlnews/zlzq/getzqpagelist",
		dataType : "json", // pageindex, pagesize, cid, isrecommend, istop, provincename, cityname, areaname
		data : {
			"pageindex" : index,
			"pagesize" : psize,
			"cid" : id,
			"isrecommend" : isrecommend,
			"istop" : istop,
			"provincename" : provincename,
			"cityname" : cityname,
			"areaname" : areaname
		},
		success : function(rsl) {
			if (rsl.code == 0) {
				var listdata = {
					list : rsl.data
				}
				var pagehtml = template('rowslist', listdata);
				$("#ul_data").html(pagehtml);
				pcount = rsl.maxRow;
				pindex = rsl.pageIndex;
				// 分页
				$("#pager").html(pagination(pcount, pindex, psize, "getpageindex"));
			}
		}
	})

}
function getprovincelist() {
	$.ajax({
		url : "/pc/address/getallprovince",
		type : "Post",
		data : {},
		dataType : "json",
		success : function(data) {
			if (data.code < 0) {
				alert(data.desc);
			} else {
				var listdata = {
					list : data.data
				}
				var html = "<li data='' isselect='1'>全部地区</li>" +
					template('provincelist', listdata);
				$("#province_ul").html(html);
				$('.zlzq-xmnrulareacon li').each(function (){
					$(this).bind("click",function(){
						$('.zlzq-xmnrularea').html($(this).text()+'<i></i>');
						$('.zlzq-xmnrulareacon').hide();
						$('.zlzq-xmnrularea').attr("data_tag","0");
						var cid = $("#ul_class li[class='active']").attr("data");
						getzqlist(1, 24, cid,'','',$(this).attr("data"));
					})
				})
			}
		},
		error : function() {
		}
	});
}

function getpageindex(index) {
	var cid = $("#ul_class li[class='active']").attr("data");
	var isrecommend=$("#tj[class='active']").attr("iscom");
	var rq=$("#rq[class='active']").attr("istop");
	getzqlist(index,psize,cid,isrecommend,rq);
}

//获取专区分类
function getzqclass() {
	$.ajax({
		type : "post",
		url : "/zlnews/zlzq/getzqclass",
		dataType : "json",
		data : {},
		success : function(rsl) {
			if (rsl.code == 0) {
				var listdata = {
					list : rsl.data
				}
				var pagehtml = template('lilist', listdata);
				$("#ul_class").html(pagehtml);
				if ($("#ul_class li").length > 0) {
					cid = $("#ul_class li").first().attr("data");
					getzqlist(1, psize, cid);
				}

			}
		}
	})

}