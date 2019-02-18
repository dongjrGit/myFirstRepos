
var pageIndex = 1;
var pageSize = 2;
var PageBar = function () {
  
}
PageBar.GetPageBar = function (callback, pageindex, pagesize, maxrows) {
    var html = '<div class="g_fyanq fl">';
    if (pageindex == 1)
        html += '<a  class="g_shangy"><font></font>上一页</a>';
    else
        html += '<a  href="javascript:void(0);" class="g_shangy"><font></font>上一页</a>';
    var mod = maxrows % pagesize;
    if (mod > 0)
        mod = 1;
    var page = mod + parseInt(maxrows / pagesize);
    alert(page);
    return html;
}


//    <a href="#" class="g_fydqys">1</a>
//    <a href="#">2</a>
//    <a href="#">3</a>
//    <a href="#">4</a>
//    <a href="#">5</a>
//    <a href="#">...</a>
//    <a href="#">7</a>
//    <a href="#" class="g_xiay">下一页<font></font></a>
//</div>
//<div class="g_zys fl">
//    共<font>7</font>页
//</div>
//<div class="g_tyq fl">
//    到第<input type="text" class="g_tysrk" placeholder="1" />页
//</div>
//<div class="g_tyanq fl">
//    <a href="#">确定</a>
//</div>
//<div class="clear"></div>

/*
*名称:图片上传本地预览插件 v1.1
*介绍:基于JQUERY扩展,图片上传预览插件 目前兼容浏览器(IE 谷歌 火狐) 不支持safari
*插件网站:http://keleyi.com/keleyi/phtml/image/16.htm
*参数说明: Img:图片ID;Width:预览宽度;Height:预览高度;ImgType:支持文件类型;Callback:选择文件显示图片后回调方法;
*使用方法: 
<div>
<img id="ImgPr" width="120" height="120" /></div>
<input type="file" id="up" />
把需要进行预览的IMG标签外 套一个DIV 然后给上传控件ID给予uploadPreview事件
$("#up").uploadPreview({ Img: "ImgPr", Width: 120, Height: 120, ImgType: ["gif", "jpeg", "jpg", "bmp", "png"], Callback: function () { }});
*/
jQuery.fn.extend({
    uploadPreview: function (opts) {
        var _self = this,
            _this = $(this);
        opts = jQuery.extend({
            Img: "ImgPr",
            Width: 100,
            Height: 100,
            ImgType: ["gif", "jpeg", "jpg", "bmp", "png"],
            Callback: function () { }
        }, opts || {});
        _self.getObjectURL = function (file) {
            var url = null;
            if (window.createObjectURL != undefined) {
                url = window.createObjectURL(file)
            } else if (window.URL != undefined) {
                url = window.URL.createObjectURL(file)
            } else if (window.webkitURL != undefined) {
                url = window.webkitURL.createObjectURL(file)
            }
            return url
        };
        _this.change(function () {
            if (this.value) {
                if (!RegExp("\.(" + opts.ImgType.join("|") + ")$", "i").test(this.value.toLowerCase())) {
                    alert("选择文件错误,图片类型必须是" + opts.ImgType.join("，") + "中的一种");
                    this.value = "";
                    return false
                }
                if ($.browser.msie) {
                    try {
                        $("#" + opts.Img).attr('src', _self.getObjectURL(this.files[0]))
                    } catch (e) {
                        var src = "";
                        var obj = $("#" + opts.Img);
                        var div = obj.parent("div")[0];
                        _self.select();
                        if (top != self) {
                            window.parent.document.body.focus()
                        } else {
                            _self.blur()
                        }
                        src = document.selection.createRange().text;
                        document.selection.empty();
                        obj.hide();
                        obj.parent("div").css({
                            'filter': 'progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)',
                            'width': opts.Width + 'px',
                            'height': opts.Height + 'px'
                        });
                        div.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = src
                    }
                } else {
                    $("#" + opts.Img).attr('src', _self.getObjectURL(this.files[0]))
                }
                opts.Callback()
            }
        })
    }
});
//截取字符串
function cutString(str,len) {
	if(str==null){
		return "";
	}
    if (len <= 0) {
        return str;
    }
    else {
        var newLength = 0;
        var newStr = "";
        var chineseRegex = /[^\x00-\xff]/g;
        var singleChar = "";
        var strLength = str.replace(chineseRegex, "**").length;
        for (var i = 0; i < strLength; i++) {
            singleChar = str.charAt(i).toString();
            if (singleChar.match(chineseRegex) != null) {
                newLength += 2;
            }
            else {
                newLength++;
            }
            if (newLength > len) {
                break;
            }
            newStr += singleChar;
        }
        //if (hasDot && strLength > len) {
        //    newStr += "...";
        //}
        return newStr;
    }
}