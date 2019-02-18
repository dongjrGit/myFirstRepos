/**
 * 提示框
 * 
 * @param content 内容
 * @param title   标题
 * @param width   宽
 * @param height  高
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