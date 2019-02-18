/**
 * 提示框
 * 
 * @param content
 *            内容
 * @param title
 *            标题
 * @param width
 *            宽
 * @param height
 *            高
 */
function okShow(content, title, width, height) {
	if (!width) {
		width = 150
	}
	if (!height) {
		height = 50
	}
	if (title == null || title == "") {
		title = '信息提示';
	}
	dialog({
		width : width,
		height : height,
		fixed : true,
		title : title,
		content : content,
		button : [ {
			value : '确定',
			autofocus : true
		} ]
	}).show();
}

/**
 * 
 * @param content
 * @param title
 * @param width
 * @param height
 * @param oKCallback
 * @param param
 */
function okEventShow(content, title, width, height, oKCallback, param) {
	if (!width) {
		width = 150
	}
	if (!height) {
		height = 50
	}
	if (title == null || title == "") {
		title = '信息提示';
	}
	dialog({
		width : width,
		height : height,
		fixed : true,
		title : title,
		content : content,
		button : [ {
			value : '确定',
			callback : function() {
				if (oKCallback && param) {
					oKCallback(param);
				} else {
					if (oKCallback) {
						oKCallback();
					}
				}
			},
			autofocus : true
		} ]
	}).show();
}

/**
 * 设置未来(全局)的AJAX请求默认选项 主要设置了AJAX请求遇到Session过期的情况
 */
jQuery.ajaxSetup({
	complete : function(xhr, status) {
		var sessionStatus = xhr.getResponseHeader('sessionstatus');
		if (sessionStatus == 'timeout') {
			okEventShow("系统登录超时，请重新登录", null, null, null, gotoLogin, null);
		}
	}
});

function gotoLogin() {
	window.top.location.href = "/platform/login";
}