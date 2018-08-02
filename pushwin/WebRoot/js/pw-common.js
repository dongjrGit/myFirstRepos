/**
 * 设置未来(全局)的AJAX请求默认选项
 * 主要设置了AJAX请求遇到Session过期的情况
 */
jQuery.ajaxSetup({
    complete: function(xhr,status) {
        var sessionStatus = xhr.getResponseHeader('sessionstatus');
        
        if(sessionStatus == 'timeout') {
        	mini.showMessageBox({
                showModal: true,
                width: 250,
                title: "提示",
                iconCls: "mini-messagebox-warning",
                message: "登录已超时",
                x: 'center',
                y: 'middle',
                callback: function(action){
                	window.top.location.href = getRootPath();
        		}
            });
        }
    }
});

$(document).ajaxComplete(function(xhr,status){
    $("#mask", window.parent.document).hide();
});


//js获取项目根路径，如： http://localhost:8083/uimcardprj
function getRootPath(){
    //获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
    var curWwwPath=window.document.location.href;
    //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
    var pathName=window.document.location.pathname;
    var pos=curWwwPath.indexOf(pathName);
    //获取主机地址，如： http://localhost:8083
    var localhostPaht=curWwwPath.substring(0,pos);
    //获取带"/"的项目名，如：/uimcardprj
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
    return(localhostPaht+projectName);
}



$(document).ajaxStart(function(){
	showMask();
});

//兼容火狐、IE8   
//显示遮罩层    
function showMask(){
/*	$("#mask", window.parent.document).css("height",$(parent.document).height());
	$("#mask", window.parent.document).css("width",$(parent.document).width());
	$("#mask", window.parent.document).show();*/
}  
//隐藏遮罩层  
function hideMask(){     
/*	$("#mask", window.parent.document).hide();  */   
} 
