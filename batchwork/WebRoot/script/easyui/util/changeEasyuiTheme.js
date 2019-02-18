/**
 * 
 * @requires jQuery,EasyUI,jQuery cookie plugin
 * 
 * 更换EasyUI主题的方法
 * 
 * @param themeName
 *            主题名称
 */
function changeThemeFun(themeName) {
	if ($.cookie('easyuiThemeName',{path:'/'})) {
		$('#layout_north_pfMenu').menu('setIcon', {
			target : $('#layout_north_pfMenu div[title=' + $.cookie('easyuiThemeName',{path:'/'}) + ']')[0],
			iconCls : 'emptyIcon'
		});
	}
	$('#layout_north_pfMenu').menu('setIcon', {
		target : $('#layout_north_pfMenu div[title=' + themeName + ']')[0],
		iconCls : 'tick'
	});

	var $easyuiTheme = $('#easyuiTheme');
	var url = $easyuiTheme.attr('href');
	var href = url.substring(0, url.indexOf('themes')) + 'themes/' + themeName + '/easyui.css';
	$easyuiTheme.attr('href', href);

	var $iframe = $('iframe');
	if ($iframe.length > 0) {
		for ( var i = 0; i < $iframe.length; i++) {
			var ifr = $iframe[i];
			try {
				$(ifr).contents().find('#easyuiTheme').attr('href', href);
			} catch (e) {
				try {
					ifr.contentWindow.document.getElementById('easyuiTheme').href = href;
				} catch (e) {
				}
			}
		}
	}

	$.cookie('easyuiThemeName', themeName, {
		expires : 7,
		path:'/'
	});
};

if ($.cookie('easyuiThemeName')) {
    changeThemeFun($.cookie('easyuiThemeName',{path:'/'}));
}