/*会员等级*/

$(document)
		.ready(
				function() {
					mlevel.bind();

					// 编辑信息
					$("body").on("click",".bjxx",
									function() {
										var id = $(this).parent().find("input")
												.val();
										location.href = "/platform/member/showLevel_updat?isEdOrAd=0&&leID="
												+ id;// 设定编辑还是添加：0为编辑，1为添加

									});
					// 添加按钮
					$("#addLevel")
							.click(
									function() {
										location.href = "/platform/member/showLevel_updat?isEdOrAd=1";// 设定编辑还是添加：0为编辑，1为添加
									})
					// 删除按钮
					$("body").on("click",".delete", function() {
						var id = $(this).parent().find("input").val();
						ConfirmShow("确定要删除等级吗？", del, id);
					});
				});

var mlevel = {
	bind : function() {
		$.ajax({
			url : "/platform/memberlevel/queryAll",
			type : "post",
			dataType : "json",
			success : function(data) {
				if (data.code < 0) {
					// alert(data.Desc);
				} else {
					var listdata = {
						list : data.data
					}
					var html = template('levellist', listdata);
					$("#table_list").after(html);
				}
			},
			error : function() {
			}
		});
	}
}
// 删除 id：用户等级id
function del(id) {
	$.ajax({
		type : "post",
		url : "/platform/memberlevel/deleteById",
		dataType : "json",
		data : {
			id : id
		},
		success : function(rsl) {
			if (rsl.code < 0) {
				Dalert(rsl.desc, "",function () { window.location.href = '/platform/member/showMember_level'; });
			} else {
				Dalert(rsl.desc,"",function () { window.location.href = '/platform/member/showMember_level'; });
			}
		},
		error : function(e) {

		}
	})
}
// 刷新
function refresh() {
	mlevel.bind();
}