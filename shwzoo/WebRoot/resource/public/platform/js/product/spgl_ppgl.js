//品牌管理
var pcount;
var pindex;
var psize = size_product;
var Brand = {
	bind : function() {
		// $(".chaxun").bind("click", Brand.getAll);
	},
	getAll : function(index) {
		var name = $("#name_select").val();
		$
				.ajax({
					url : "/platform/brand/queryBrand",
					type : "Post",
					data : {
						page : index,
						size : psize,
						"name" : name
					},
					dataType : "json",
					success : function(data) {
						if (data.code < 0) {
							Dalert(data.desc);
						} else {
							var listdata = {
								list : data.data
							}
							var html = template('brandlist', listdata);
							// $("#list_title").after(html);
							// 翻页时删除表头以外的所有节点，避免after()方法重复加载
							$("#list_title")
									.parent()
									.children()
									.each(
											function() {
												if ($(this).attr('id') != "list_title") {
													this.parentNode
															.removeChild(this);
												}
											})
							// html 填充
							$("#list_title").after(html);
							// 加载table样式：改变偶数行背景色，鼠标移动时背景色
							init();
							pcount = data.maxRow;
							pindex = data.pageIndex;
							// 分页
							$("#pager").html(
									pagination(pcount, pindex, psize,
											"Brand.getAll"));
							// 点击图片查看大图
							$(".ppimg img")
									.each(
											function() {
												$(this)
														.click(
																function() {
																	if ($(this)
																			.attr(
																					"src") != ""
																			&& $(
																					this)
																					.attr(
																							"src") != undefined)
																		Show(
																				"bigimg",
																				$(
																						this)
																						.attr(
																								"src"));
																});
											});
						}
					},
					error : function() {

					}
				});
	},
	// 删除
	del : function(id) {
		if (confirm("确定要删除么？")) {
			$.ajax({
				url : "/platform/brand/deleteById",
				type : "Post",
				data : {
					"id" : id
				},
				dataType : "json",
				success : function(data) {
					Dalert(data.desc, "", refresh);
					// location.reload();
				}
			});
		}
	}
}
function refresh() {
	location.reload();
}