var BannerEdit = {
	getBannerType : function() {
		$.ajax({
			url : "/zoo/banner/getBannerType",
			type : "Post",
			data : {},
			dataType : "json",
			success : function(data) {

				if (data.code < 0) {
					Dalert(data.desc);
				} else {
					var listdata = {
						list : $.parseJSON(data.data)
					}
					var html = template('typeList', listdata);
					$("#type").append(html);
				}
			},
			error : function() {
			}
		});
	},
	typeChange:function(obj){
        var type;
        if(!obj){
        	 type = $("#type").val();
        	 $("#url").val("");
             $("#select_spu").val("");
             $("#select_spu").attr("data","")
             $("#select_shop").val("");
             $("#select_shop").attr("data","");
             $("#scenicid").val("");
             $("#scenicid").attr("data","");
             //编辑器
             $("#editorId").val("");
             $("#editorId").attr("data","");
        }else{
        	type=obj;
        }
        if (type == 1) {
            $("#divurl").hide();
            $("#divspu").hide();
            $("#divshop").show();
            $("#divScenic").hide();
            $("#divEditor").hide();
        }else if(type==2){
        	 $("#divurl").hide();
             $("#divspu").show();
             $("#divScenic").hide();
             $("#divshop").hide();
             $("#divEditor").hide();
        }else if(type==3){
        	$("#divurl").hide();
            $("#divspu").hide();
            $("#divshop").hide();
            $("#divScenic").show();
            $("#divEditor").hide();
        }else if(type=='0'){
        	$("#divurl").show();
            $("#divspu").hide();
            $("#divshop").hide();
            $("#divScenic").hide();
            $("#divEditor").hide();
        }else if(type==4){
        	$("#divurl").hide();
            $("#divspu").hide();
            $("#divshop").hide();
            $("#divScenic").hide();
            $("#divEditor").show();
        }else{
        	$("#divurl").hide();
            $("#divspu").hide();
            $("#divshop").hide();
            $("#divScenic").hide();
            $("#divEditor").hide();
        }
	},
	save:function(){
		if(!checkForm()){
			return;
		}
		var data ={};
		data.id = $("#id").val();
		data.img = $("#img").val();
		data.title = $("#title").val();
		data.sort=$("#sort").val();
		var type = $("#type").val();
		data.type =type
		var typeId = "";
		if(2==type){
			typeId =  $("#select_spu").attr("data");
		}
		if(1==type){
			typeId= $("#select_shop").attr("data");
		}
		if(3==type){
			typeId= $("#scenicid").attr("data");
		}
		if(4==type){
			typeId= $("#editorId").attr("data");
		}
		data.typeId= typeId;
		data.url = $("#url").val();
		
		data.status=$("#radio_status:checked").val();
		var jsonStr = JSON.stringify(data); 
		// 防止重复提交 点击保存后隐藏按钮
		$("input[name='Save']").hide();
		$.ajax({
			url : "/zoo/banner/editlist",
			type : "Post",
			data : {data:jsonStr},
			dataType : "json",
			success : function(data) {
				if (data.code < 0) {
					$("input[name='Save']").show();
					Dalert(data.desc);
				} else {
					Dalert("保存成功！", "", function() {
						window.location.href = 'list';
					});
				}
			},
			error : function() {
			}
		});
	}
}

//按钮事件绑定
$(function() {
	$("input[name=Save]").bind("click", BannerEdit.save);
	autoxl.bind("select_spu", getSpuStartwithName,true);
	autoxl.bind("select_shop", getShopStartwithName);
	// 景点列表
	autoxl.bind("scenicid", getScenicStartwithName);
	
	autoxl.bind("editorId", getEditorStartwithName);
	if($("#id").val()){
		BannerEdit.typeChange($("#srcType").val());
	}
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
                if (data.data.length == 0) {
					$("#select_spu").attr("data", "");
				} else {
					$("#select_spu").attr("data", "");
					data.data.forEach(function(value, index) {
						if (value.name == name) {
							$("#select_spu").attr("data", value.id);
							return;
						}
					});
				}
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
                if (data.data.length == 0) {
					$("#select_shop").attr("data", "");
				} else {
					$("#select_shop").attr("data", "");
					data.data.forEach(function(value, index) {
						if (value.name == name) {
							$("#select_shop").attr("data", value.id);
							return;
						}
					});
				}
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
		url : "/zoo/editor/listbyTitle",
		type : "post",
		data : {
			"title" : title,
			"type":1
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


function checkForm(){
	//Dalert("请上传景点图片！");
	if(!$('#title').val()){
		Dalert("请填写标题！");
		return false;
	}
	if(!$("#img").val()){
		Dalert("请上传图片！");
		return false;
	}
	if(!$("#type").val()){
		Dalert("请选择跳转类型！");
		return false;
	}
	var type = $("#type").val();
	if(type==2){
		if(!$("#select_spu").val()){
			Dalert("请选择商品！");
			return false;
		}else{
			if(!$("#select_spu").attr("data")){
				Dalert("您填写的商品名称不存在！");
				return false;
			}
		}
	}else if(type==1){
		if(!$("#select_shop").val()){
			Dalert("请选择商店！");
			return false;
		}else{
			if(!$("#select_shop").attr("data")){
				Dalert("您填写的商店名称不存在！");
				return false;
			}
		}
		
	}else if(type==3){
		if(!$("#scenicid").val()){
			Dalert("请选择景点设施！");
			return false;
		}else{
			if(!$("#scenicid").attr("data")){
				Dalert("您填写的名称不存在！");
				return false;
			}
		}
	}else if(type==4){
		if(!$("#editorId").val()){
			Dalert("请选择图文标题！");
			return false;
		}else{
			if(!$("#editorId").attr("data")){
				Dalert("您填写的名称不存在！");
				return false;
			}
		}
	}else{
		if(!$("#url").val()){
			Dalert("您填写链接地址！");
			return false;
		}
	}
	if($('#sort').val()){
		if(!(/^\d+$/.test( $('#sort').val() ))){
			Dalert("排序字段不是正整数！");
			return false;
		}
	}else{
		Dalert("排序字段不是正整数！");
		return false;
	}
	return true;
}