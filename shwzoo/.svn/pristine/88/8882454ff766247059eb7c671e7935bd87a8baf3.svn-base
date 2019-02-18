var Ad = {
	unit : function() {
		Ad.getList();
		Ad.getTypeList();
	},
	
	pagemarkChange: function () {
        var type = $("#pagemark").val();
        if (type == 14) {
            $("#divsp").show();
        }
        else {
            $("#divsp").hide();
        }
    },
    TypeChange: function () {
        var type = $("#type").val();
        $("#url").val("");
        if (type == 0) {
            $("#divurl").show();
            $("#divspu").hide();
            $("#divshop").hide();
            $("#divtopic").hide();
        }else if(type==1){
        	 $("#divurl").hide();
             $("#divspu").hide();
             $("#divshop").show();
             $("#divtopic").hide();
        }else if(type==2){
        	$("#divurl").hide();
            $("#divspu").show();
            $("#divshop").hide();
            $("#divtopic").hide();
        }else if(type==3){
        	$("#divurl").hide();
            $("#divspu").hide();
            $("#divshop").hide();
            $("#divtopic").show();
        }
    },
	getList : function() {
		$.ajax({
			url : "/platform/adverising/getPageMark",
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
					var html = template('flist1', listdata);
					$("#pagemark").append(html);
				}
			},
			error : function() {
			}
		});
	},
	getTypeList : function() {
		$.ajax({
			url : "/platform/adverising/getAdvertType",
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
				}
			},
			error : function() {
			}
		});
	}
}

// 按钮事件绑定
$(function() {
	$("input[name=Save]").bind("click", Save);
	autoxl.bind("select_spu", getSpuStartwithName);
	autoxl.bind("select_shop", getShopStartwithName);
	autoxl.bind("select_topic", getTopicStartwithName);
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

// 保存
function Save() {
	var loadimg = $("#img").val();
	var title = $("#title").val();
	var pagemark = $("#pagemark").val();
	var pagemarkid = $("#firstID").val();
	var display = $("#display").val();
	var orderby=$("#orderby").val();
	var type = $("#type").val();
	var spuid = $("#select_spu").attr("data");
	var shopid = $("#select_shop").attr("data");
	var topicid = $("#select_topic").attr("data");
	var url = $("#url").val();
	var ws=$("#webset option:selected").val();
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
		url : "/platform/adverising/edit",
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
			webset:ws
		},
		dataType : "json",
		success : function(data) {
			if (data.code < 0) {
				$("input[name='Save']").show();
				Dalert(data.desc);
			} else {
				Dalert("保存成功！", "", function() {
					window.location.href = 'showAdvertImg';
				});
			}
		},
		error : function() {
		}
	});
}
