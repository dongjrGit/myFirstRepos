/**
 * 获取根路径
 */
// js获取项目根路径
function getRootPath() {
	var curWwwPath = window.document.location.href;
	var pathName = window.document.location.pathname;
	var pos = curWwwPath.indexOf(pathName);
	var localhostPaht = curWwwPath.substring(0, pos);
	var projectName = pathName
			.substring(0, pathName.substr(1).indexOf('/') + 1);
	if (projectName == "seller") {
		projectName = "";
		return projectName.replace(/(^\s*)|(\s*$)/g, "");
	} else if (projectName == "platform") {
		projectName = "";
		return projectName.replace(/(^\s*)|(\s*$)/g, "");
	} else {
		return projectName.replace(/(^\s*)|(\s*$)/g, "");
	}
}

