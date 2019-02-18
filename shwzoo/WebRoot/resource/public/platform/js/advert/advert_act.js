var Ad = {
	unit : function() {
			//Ad.getList();
			Ad.getTypeList();
		},
    TypeChange: function () {
        var type = $("#type").val();
        $("#url").val("");
        if (type == 0) {
            $("#divurl").show();
            $("#divspu").hide();
            $("#divshop").hide();
            $("#divtopic").hide();
            $("#divScenic").hide();
            $("#divEditor").hide();
        }else if(type==1){
        	 $("#divurl").hide();
             $("#divspu").hide();
             $("#divshop").show();
             $("#divtopic").hide();
             $("#divScenic").hide();
             $("#divEditor").hide();
        }else if(type==2){
        	$("#divurl").hide();
            $("#divspu").show();
            $("#divshop").hide();
            $("#divtopic").hide();
            $("#divScenic").hide();
            $("#divEditor").hide();
        }else if(type==3){
        	$("#divurl").hide();
            $("#divspu").hide();
            $("#divshop").hide();
            $("#divtopic").show();
        }else if(type==4){
        	$("#divurl").hide();
            $("#divspu").hide();
            $("#divshop").hide();
            $("#divtopic").hide();
            $("#divScenic").show();
            $("#divEditor").hide();
        }else if(type ==5){
        	$("#divurl").hide();
            $("#divspu").hide();
            $("#divshop").hide();
            $("#divtopic").hide();
            $("#divScenic").hide();
            $("#divEditor").show();
        }
    },
    getTypeList : function() {
		$.ajax({
			url : "/platform/adverising/getPopupAdType",
			type : "Post",
			data : {},
			dataType : "json",
			success : function(data) {
				
				if (data.code < 0) {
					Dalert(data.desc);
				} else {
					var listdata = {
						list : data.data
					}
					var html = template('flist2', listdata);
					$("#type").append(html);
					if (parseInt($("#hidden_advertid").val()) > 0) {
						var topictype = $("#topictype").val();
						$("#type").val(topictype);
					}
				}
			},
			error : function() {
			}
		});
	}
}

// 按钮事件绑定
$(function() {
	Ad.unit();
	$("input[name=Save]").bind("click", Save);
	autoxl.bind("select_spu", getSpuStartwithName);
	autoxl.bind("select_shop", getShopStartwithName);
	autoxl.bind("select_topic", getTopicStartwithName);
	
	// 景点列表
	autoxl.bind("scenicid", getScenicStartwithName);
	autoxl.bind("editorId", getEditorStartwithName);
})


function getSpuStartwithName(callback, event) {
    var name = $("#select_spu").val();
    name=name.replace(/'/g, '');
    if (event)
        name += String.fromCharCode(event.keyCode);
    $.ajax({
        url: "/platform/spu/getSspuStartWithName",
        type: "Post",
        data: { "name": name},
        dataType: "json",
        success: function (data) {
            if (data.code == 0) {
                var listdata = {
                    list: data.data
                }
                var html = template('select_spulist', listdata);

                if (callback) {
                    callback(html);
                }
            } else {
                Dalert(data.desc);
            }
        }
    });
}

function getShopStartwithName(callback, event) {
    var name = $("#select_shop").val();
    name=name.replace(/'/g, '');
    if (event)
        name += String.fromCharCode(event.keyCode);
    $.ajax({
        url: "/platform/shop/getShopStartWithName",
        type: "Post",
        data: { "name": name},
        dataType: "json",
        success: function (data) {
            if (data.code == 0) {
                var listdata = {
                    list: data.data
                }
                var html = template('select_shoplist', listdata);

                if (callback) {
                    callback(html);
                }
            } else {
                Dalert(data.desc);
            }
        }
    });
}
function getTopicStartwithName(callback, event) {
    var name = $("#select_topic").val();
    name=name.replace(/'/g, '');
    if (event)
        name += String.fromCharCode(event.keyCode);
    $.ajax({
        url: "/platform/topic/getTopicStartWithName",
        type: "Post",
        data: { "name": name,webset:$("#webset option:selected").val()},
        dataType: "json",
        success: function (data) {
            if (data.code == 0) {
                var listdata = {
                    list: data.data
                }
                var html = template('select_topiclist', listdata);

                if (callback) {
                    callback(html);
                }
            } else {
                Dalert(data.desc);
            }
        }
    });
}

/**
 * 查设施列表
 * 
 * @param callback
 * @param event
 */
function getScenicStartwithName(callback, event) {
	var name = $("#scenicid").val();
	if (event)
		name += String.fromCharCode(event.keyCode);
	$.ajax({
		url : "/zoo/scenic/queryScenicName",
		type : "post",
		data : {
			"name" : name,
			"state":1
		},
		dataType : "json",
		success : function(data) {
			if (data.code == 0) {
				var listdata = {
					list : data.data
				}
				var html = template('select_sceniclist', listdata);
				if (data.data.length == 0) {
					$("#scenicid").attr("data", "");
				} else {
					$("#scenicid").attr("data", "");
					data.data.forEach(function(value, index) {
						if (value.scenicname == name) {
							$("#scenicid").attr("data", value.id);
							return;
						}
					});
				}
				if (callback) {
					callback(html);
				}
			} else {
				Dalert(data.data);
			}
		},
		error : function(xhr,status,error) {
			//alert(xhr.responseText);
			Dalert("操作失败");
		}
	});
}

function getEditorStartwithName(callback, event){
	var title = $("#editorId").val();
	if (event)
		name += String.fromCharCode(event.keyCode);
	$.ajax({
		url : "/zoo/editor/listByTitle",
		type : "post",
		data : {
			"title" : title,
			"type":2
		},
		dataType : "json",
		success : function(data) {
			if (data.code == 0) {
				var listdata = {
					list : data.data
				}
				var html = template('select_editorlist', listdata);
				if (data.data.length == 0) {
					$("#editorId").attr("data", "");
				} else {
					$("#editorId").attr("data", "");
					data.data.forEach(function(value, index) {
						if (value.title == title) {
							$("#editorId").attr("data", value.id);
							return;
						}
					});
				}
				if (callback) {
					callback(html);
				}
			} else {
				Dalert(data.data);
			}
		},
		error : function(xhr,status,error) {
			//alert(xhr.responseText);
			Dalert("操作失败");
		}
	});
}
// 保存
function Save() {
	var loadimg = $("#img").val();
	var title = $("#title").val();
	var pagemark = 12;
	var pagemarkid =12;
	var display =1;
	var orderby=1;
	var type = $("#type").val();
	var spuid = $("#select_spu").attr("data");
	var shopid = $("#select_shop").attr("data");
	var topicid = $("#select_topic").attr("data");
	var scenicId = $("#scenicid").attr("data");
	var editorId = $("#editorId").attr("data");
	
	
	var url = $("#url").val();
	var ws=2;
	var statusVal = "";
	for (var i = 0; i < $("input[name=radio_status]").length; i++) {
		if ($("input[name=radio_status]")[i].checked) {
			statusVal = $("input[name=radio_status]")[i].value;
		}
	}
	var Oldid = $("#hidden_advertid").val();

	// 防止重复提交 点击保存后隐藏按钮
	$("input[name='Save']").hide();
	$.ajax({
		url : "/platform/adverising/savePopupAd",
		type : "Post",
		data : {
			imgurl : loadimg,
			url : url,
			status : statusVal,
			title:title,
			pagemark:pagemark,
			pagemarkid:pagemarkid,
			type:type,
			spuid:spuid,
			shopid:shopid,
			topicid:topicid,
			display:display,
			orderby:orderby,
			id:Oldid,
			webset:ws,
			scenicId:scenicId,
			editorId:editorId
		},
		dataType : "json",
		success : function(data) {
			if (data.code < 0) {
				$("input[name='Save']").show();
				Dalert(data.desc);
			} else {
				Dalert("保存成功！");
				$("input[name='Save']").show();
			}
		},
		error : function() {
		}
	});
}
