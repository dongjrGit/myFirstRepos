var autoxl = {
	/*
	 * callback 成功 有数据时 调的方法 ID 当前 输入框 Id名称
	 */
	bind : function(id, callback, width, left) {

		$(window).bind("click", function() {
			$("#" + id).parent().next().hide();
		});
		var $id = $("#" + id);
		var nwidth = 200;
		try {
			if (width != null && width != "") {
				nwidth = width;
			}
		} catch (e) {

		}
		var fleft = 13;
		try {
			if (left != null && left != "") {
				fleft = left;
			}
		} catch (e) {

		}
		$id.parent().next().css({
			display : "none",
			width : nwidth + 'px',
			'background-color' : 'white',
			'max-height' : '180px',
			position : 'absolute',
			border : '1px solid #ccc ',
			'z-index' : 999,
			'overflow' : 'auto'
		});

		objEvt = $._data($id[0], "events");
		if (!(objEvt && objEvt["click"])) {
			$id.bind("click", function() {
				
				$id.parent().next().css({
					'left' : $("#" + id).offset().left - fleft});
				if (callback != null) {
					callback(function(html) {
						autoxl.bindselect(id, html, callback);
					});
				}
			});
		}
		// $id.bind("keypress", function () {
		// if (callback != null) {
		// callback(function (html) {
		// autoxl.bindselect(id, html);
		// }, event);
		// }
		// });
		if (!(objEvt && objEvt["keyup"])) {
			$id.bind("keyup", function() {
				if (callback != null) {
					callback(function(html) {
						autoxl.bindselect(id, html);
					});
				}
			});
		}
	},
	selectUser : function(pid, li) {
		var $li = li;
		var id = $li.attr("data");
		var val = $li.attr("data-val");
		if (val == null || val == "" || val == 'undefined')
			val = $li.html();
		$("#" + pid).val(val);
		$("#" + pid).attr("data", id);
		$("#" + pid).parent().next().hide();
	},
	selectOver : function(li) {
		var $li = li;
		$li.parent().find("li").each(function() {
			$(this).removeAttr("style");
		})
		$li.css("background-color", "grey");
	},
	bindselect : function(id, html, callback) {
		$("#" + id).parent().next().find("ul").html(html);
		$("#" + id).parent().next().find("li").each(function() {
			var objEvt = $._data($(this)[0], "events");
			if (!(objEvt && objEvt["click"])) {
				$(this).bind("click", function() {
					$("#" + id).focus();
					autoxl.selectUser(id, $(this));
				});
				$(this).bind("mouseover", function() {
					autoxl.selectOver($(this));
				});
			}
		});
		if ($("#" + id).val() == "") {
			$("#" + id).removeAttr("data");
		} else {
			//$("#" + id).attr("data", id);
		}
		$("#" + id).parent().next().show();
	}

};
