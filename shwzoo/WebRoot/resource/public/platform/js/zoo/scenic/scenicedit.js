$(function() {
	$("input[name=Save]").bind("click", save);
	if ($("#id").val() > 0) {

	}
});

var Init = {
	bind : function() {
		//猜你喜欢
		autoxl.bind("guessLike", getTopicStartwithName, true);
		//商店列表
		autoxl.bind("shopId", getShopStartwithName, true);
	},
	typeChange:function(){
		var typeId = $("#scenicType").val();
		//动物景点、动物互动  属性
		if (typeId == 1 || typeId== 2) {
            //$("#propertyDiv").show();
        }else{
        	//$("#propertyDiv").hide();
        	//$("#interaction").prop("checked",false)
        	//$("#explain").prop("checked",false)
        	//$("#actionShow").prop("checked",false)
        }
		//剧场 收费
		if(typeId==3){
			$("#isChargeDiv").show();
		}else{
			$("#isChargeDiv").hide();
			$("#isCharge").val("");
		}
		//剧场和游乐  猜你喜欢
		if(typeId==3 || typeId==4){
			$("#likeDiv").show();
		}else{
			$("#likeDiv").hide();
			$("#guessLike").attr("data",""); 
			$("#guessLike").val(""); 
		}
		//美食、游乐、剧场、其他       商城
		if(typeId==5 || typeId==3 || typeId==4 ||typeId==9){
			$("#shopDiv").show();
		}else{
			$("#shopDiv").hide();
			$("#shopId").attr("data",""); 
			$("#shopId").val("");
		}
	}
}

/**
 * 查商店列表
 * @param callback
 * @param event
 */
function getShopStartwithName(callback, event) {
	var name = $("#shopId").val();
	if (event)
		name += String.fromCharCode(event.keyCode);
	$.ajax({
		url : "/zoo/scenic/getShopListByName",
		type : "post",
		data : {
			"name" : name
		},
		dataType : "json",
		success : function(data) {
			if (data.code == 0) {
				var listdata = {
					list : data.data
				}
				var html = template('select_shoplist', listdata);
				if(data.data.length==0){
					$("#shopId").attr("data",""); 
				}else{
				   $("#shopId").attr("data","");
				   data.data.forEach(function(value, index) {
					   if(value.name==name){
						   $("#shopId").attr("data",value.id); 
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
		error : function(XMLHttpRequest, status, error) {
			// alert(XMLHttpRequest.status);
			alert("操作失败 \r" + XMLHttpRequest.responseText);
		}
	});
}



/**
 * 查询猜你喜欢专题
 * @param callback
 * @param event
 */
function getTopicStartwithName(callback, event) {
	var name = $("#guessLike").val();
	if (event)
		name += String.fromCharCode(event.keyCode);
	$.ajax({
		url : "/zoo/scenic/getTopicsByName",
		type : "post",
		data : {
			"name" : name
		},
		dataType : "json",
		success : function(data) {
			if (data.code == 0) {
				var listdata = {
					list : data.data
				}
				var html = template('select_spulist', listdata);
				if(data.data.length==0){
					$("#guessLike").attr("data",""); 
				}else{
				   $("#guessLike").attr("data","");
				   /*data.data.forEach(function(value, index) {
					   if(value.name==name){
						   $("#guessLike").attr("data",value.id); 
						   return;
					   }
				   });*/
				   $.each(data.data,function(index, value){
					   if(value.name==name){
						   $("#guessLike").attr("data",value.id); 
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
		error:function(xhr,status,error){
			alert(xhr.responseText);
		}
	});
}

function save() {
	if (formSubmit()) {
		var data={};
		data.id=$('#id').val();
		data.imageid=$('#imageid').val();
		data.createtimestr=$('#createtimestr').val();
		data.scenicname=$('#scenicName').val();
		data.type=$('#scenicType').val();
		data.img=$("input[name='img']").val();
		data.scenicImgs=$("#scenicImgs").val();
		data.opentime=timeEditor.html();
		data.scenicarea=$("#scenicArea").val();
		data.address=$("#address").val();
		data.longitude=$("#longitude").val();
		data.latitude=$("#latitude").val();
		data.content=contEditor.html();
		data.ischarge=$("#isCharge").val();
		data.state=$("input[name=state]:checked").val();
	    data.shopid=$("#shopId").attr("data");
	    data.topicid=$("#guessLike").attr("data");
	    
		//获取checkbox选中状态
		/*if($('#interaction').prop("checked")){
			data.interaction=$('#interaction').val();	
		}
		if($('#explain').prop("checked")){
			data.explain=$('#explain').val();
		}
		if($('#actionShow').prop("checked")){
			data.actionShow=$('#actionShow').val();
		}*/
		var jsonData = JSON.stringify(data); 
		
		// 防止重复提交 点击保存后隐藏按钮
		$("input[name='Save']").hide();
		var type = $("#type").val();
		var ctype = $("#ctype").val();
		$.ajax({
			url : "/zoo/scenic/editlist",
			type : "Post",
			data : {"data":jsonData},
			dataType : "json",
			success : function(data) {
				if (data.code == 0) {
					Dalert("保存成功！", "", function() {
						window.location.href = "/zoo/scenic/list";
					});
				} else {
					$("input[name='Save']").show();
					Dalert(data.desc);
				}
			},
			error : function() {
			}
		});
	}
}

// 保存前参数验证
function check() {
	return $("#form").validate({
		rules : {
			scenicName : {
				required : true
			},
			scenicType:{
				required : true
			},
			scenicArea:{
				required : true
			},
			/*point:{
				required : true,
				checkPoint: true
			},*/
			address:{
				required : true
			},
			longitude:{
				required : true,
				checkPoint: "longitude"
			},
			latitude:{
				required : true,
				checkPoint: "latitude"
			}
			
		},
		messages : {
			scenicName : {
				required : "景点名称不可为空"
			},
			scenicType:{
				required : "景点类别不可为空"
			},
			scenicArea:{
				required : "区域不能为空"
			},
			/*point:{
				required : "坐标不能为空"
			},*/
			address:{
				required : "地址不能为空"
			},
			longitude:{
				required : "经度不能为空"
			},
			latitude:{
				required : "纬度不能为空"
			}
		}
	});
}
/**
 * 对经纬度的校验
 */
$.validator.addMethod("checkPoint", function(value, element, params) {
	var checkPoint;
	if(params=='longitude'){
		 checkPoint = /^([-\+]?(([1-9]?[0-9])|(1[0-7][0-9]))\.\d{1,6}$)|(180\.0{1,6})$/;
	}
    if(params=='latitude'){
		 checkPoint = /^(([-\+]?[1-8]?[0-9]\.\d{1,6})|(90\.0{1,6}))$/;	
	}
	return this.optional(element) || (checkPoint.test(value));
}, "*请输入正确的坐标！");

function formSubmit() {
	if (check().form()) {
		//判断景点图片是否上传
		if($("input[name='img']").val()==''){
			Dalert("请上传景点图片！");
			return false;
		}
		if($("#scenicImgs").val()==''){
			Dalert("请上传详情展示图片！");
			return false;
		}
		if(timeEditor.isEmpty()){
			timeEditor.html('');
			//Dalert("请填写开放时间！");
			//return false;
		}
		if(contEditor.isEmpty()){
			Dalert("请填写详情！");
			return false;
		}
		
		if($("#guessLike").val()!="" && !$("#guessLike").attr("data")){
			Dalert("您输入的名称没有对应的专题！");
			return false;
		}
		if($("#shopId").val()!="" && !$("#shopId").attr("data")){
			Dalert("您输入的店铺不存在！");
			return false;
		}
		return true;
	}

}
