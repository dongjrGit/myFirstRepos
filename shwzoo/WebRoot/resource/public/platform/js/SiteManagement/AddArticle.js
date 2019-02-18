//添加文章
var editor, content,partid;
KindEditor.ready(function (K) {
    //K.create('#content1');
    //取消功能 打印，插入模板，插入代码，插入flash，插入视频，插入表情，锚点
	editor = K.create('#content1', {
		items : [ 'source', 'undo', 'redo', 'preview', 'cut', 'cpoy',
				'paste', 'plainpaste', 'wordpaste', 'justifyleft',
				'justifycenter', 'justifyright', 'justifyfull',
				'insertorderedlist', 'insertunorderedlist', 'indent',
				'outdent', 'subscript', 'superscript', 'clearhtml',
				'quickformat', 'selectall', 'fullscreen', 'formatblock',
				'fontname', 'fontsize', 'forecolor', 'hilitecolor', 'bold',
				'italic', 'underline', 'strikethrough', 'lineheight',
				'removeformat', 'image', 'multiimage', 'insertfile',
				'table', 'hr', 'baidumap', 'pagebreak', 'link', 'unlink' ],
			uploadJson :"/zoo/image/upload?relationtype=1&iskdr=1"
	});
    if ($("input[name=content]").val() != ""
		&& $("input[name=content]").val() != undefined) {
	editor.html($("input[name=content]").val());
}
});

$(document).ready(function () {
	partid=$("#partid").val();
    getFirstClass();
    $("#Classfy").change(function () {
        firstChange();
    });
    var id = $("#id").val();
    if (id > 0) {
        $("#contitle").html("编辑文章");
    } else {
        $("#contitle").html("添加文章");
    }
    if($("#fid").val()>0){
    	firstChange($("#fid").val(), $("#classfyid").val());
    }
    //保存
    $("#savebtn").click(function () {
        if (check().form()) {
            save();
        }
    });
});
    //验证表单
function check() {
        return $("#ArticleForm").validate({
            rules: {
                title: {
                    required: true
                },
                content1: {
                    required: true
                },
            },
            //设置提示信息
            messages: {
            	title: {
                    required: "必填"
                },
                content1: {
                    required: "必填"
                }
            },
            //设置错误信息存放标签
            errorElement: "label"//,
            //设置验证触发事件
            //debug: true
        })
    }
function backhref() {
	location.href = "/platform/zd/wzzx";
}
//获取分类首项
function getFirstClass() {
    $.ajax({
        url: "/platform/page/queryfclassbypart",
        type: "Post",
        data: {"partid":partid},
        dataType: "json",
        success: function (data) {
            if (data.code < 0) {
                //Dalert(data.Desc);
            } else {
                var listdata = {
                    list: data.data
                }
                var html = "<option value='' selected>选择类型</option>"
                html += template('flist', listdata);

                $("#Classfy").html(html);
                var fid=$("#classfyid").val();
                if($("#fid").val()>0){
                	fid=$("#fid").val();
                	
                }
                if(fid>0){
                	$("#Classfy option").each(function () {
                    if ($(this).val() == fid) {
                        $(this).attr("selected", "selected");
                    } else {
                        $(this).removeAttr("selected");
                    }
                });
                } 
            }
        },
        error: function () {

        }
    });
}
//根据首项获取分类子项 fid：首相id，chilid：子项id 
function firstChange(fid, chilid) {
    if (fid == null || fid == "" || fid == undefined) {
        fid = $("#Classfy").val();
    }
    if (fid >0) {
        $.ajax({
            url: "/platform/page/querychildbyfid",
            type: "Post",
            data: { "fatherid": fid },
            dataType: "json",
            success: function (data) {
                if (data.code < 0) {
                    //Dalert(data.Desc);
                } else {
                    var listdata = {
                        list: data.data
                    }
                    var html = '<option value="0" >选择类型</option>' + template('slist', listdata);
                    $("#childid").html(html);
                }
                if (chilid != null && fid != "" && fid != undefined) {
                    $("#childid").attr("value", chilid);
                }
            },
            error: function () {

            }
        })
    } else {
        var html = '<option value="0"  selected>选择类型</option>';
        $("#childid").html(html);
    }
}
//保存内容
function save() {
    $("#savebtn").hide();
    var save_action=$("#action").val();
    editor.sync();
    content = editor.html();
    $("#content").val(content);
    var child=$("#childid").val();
    var fid=$("#Classfy").val();   
    if(fid != null && fid != "" && fid > 0){
    	$("#classfyid").val(fid);
    	$("#classfy").val($("#Classfy").find("option:selected").text());
    }
    if(child != null && child != "" && child > 0){
    	$("#classfyid").val(child);
    	$("#classfy").val($("#childid").find("option:selected").text());
    }
    if (id != "") {
        $.ajax({
            type: "post",
            url: "/platform/page/"+save_action,
            dataType: "json",
            data: $("#ArticleForm").serialize(),
            success: function (rsl) {
                if (rsl.code == 0) {
                    Dalert(rsl.desc);
                    $("#backbtn").click();
                } else {
                    Dalert(rsl.desc); $("#savebtn").show();
                }
            },
            error: function (e) {
                // Dalert(e.statusText);
            }
        });
    } else {
        Dalert("请先设置文章分类");
    }
}

function getFirstID(id) {
    var pid = -1;
    $.ajax({
        url: "/platform/page/getFirstId",
        async: false,
        type: "Post",
        data: { childid: id },
        dataType: "json",
        success: function (data) {
            if (data.code < 0) {
                // alert(data.Desc);
            } else {
                pid = data.data
            }
        }
    });
    return pid;
}