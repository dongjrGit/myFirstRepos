$(function name() {
	getInitArea();
	function getInitArea() {

		$
				.ajax({
					url : "/app/api/addr/selectAllProvice",
					type : "post",
					async : false,
					data : {
						ch : 1
					},
					dataType : "json",
					success : function(data) {
						var html = '<li data-id="-1"><a href="nlist?code=-1">全部</a></li>';

						for (var i = 0; i < data.data.length; i++) {
							var val = data.data[i];
							html += '<li data-id="' + val.code + '">'
									+ '<a href="nlist?code=' + val.code + '">'
									+ val.name + '</a>' + '</li>';
						}
						$("#l_area").html(html);
						$('#areacon ul li').click(
								function() {
									var a = $(this).html();
									$('.septop_l').html(
											'<p><span>' + a + '</span></p>');
									$('#areacon').hide();
								});
					},
					error : function() {
					}
				});
	}
	;

});

var bannertop = {
	init : function(type) {
		$.ajax({
			url : "/api/banner/list",
			type : "post",
			async : false,
			data : {
				ch : 1,
				type : type
			},
			dataType : "json",
			success : function(data) {
				var $obj = $("#bannertop");
				$obj.children().eq(0).html("");
				$obj.children().eq(1).html("");
				for (var i = 0; i < data.data.length; i++) {
					var val = data.data[i];
					$obj.children().eq(0).append(
							'<li>' + '<img src="' + val.img + '" ></li>');
					$obj.children().eq(1).append('<i></i>');
				}
			},
			error : function() {
			}
		});
	}
};
