//商品添加 编辑
function formSubmit() {
	var ischeck=true;
	//库存商品信息
	var unit_param=[];
	if($("#protype").prop('checked')==false){
	$(".skupday").each(function(){
		  var num=$(this).find("input[name=tnum]").val();
		  var price=$(this).find("input[name=tprice]").val();
		  
		  if(num==""||num==undefined){
				ischeck=false;
				Dalert("请填写商品ID");
				return ischeck;
			}
		  if(price==""||price==undefined||parseFloat(price)<=0){
				ischeck=false;
				Dalert("请填写商品售价");
				return ischeck;
			}
		  var id=0;
		  if($("#action").val()=="edit"){
			  id=$(this).find("input[name=tid]").val();
			  if(id==""||id==undefined||parseInt(id)<=0){
					ischeck=false;
					return ischeck;
				}
		  }
		  unit_param.push({
          	"id":id,
          	"ticketnum":num,
          	"price":price,
          	"istoday":1,
          	"appprice":0.00
          });
	});
	}
	$(".skup").each(function(){
		  var num=$(this).find("input[name=tnum]").val();
		  var price=$(this).find("input[name=tprice]").val();
		  
		  if(num==""||num==undefined){
				ischeck=false;
				Dalert("请填写商品ID");
				return ischeck;
			}
		  if(price==""||price==undefined||parseFloat(price)<=0){
				ischeck=false;
				Dalert("请填写商品售价");
				return ischeck;
			}
		  var id=0;
		  if($("#action").val()=="edit"){
			  id=$(this).find("input[name=tid]").val();
			  if(id==""||id==undefined||parseInt(id)<=0){
					ischeck=false;
					return ischeck;
				}
		  }
		  unit_param.push({
        	"id":id,
        	"ticketnum":num,
        	"price":price,
        	"istoday":0,
        	"appprice":0.00
        });
	});
	if(unit_param=="[]"){
		ischeck=false;
		Dalert("请填写商品包装信息");
		return ischeck;
	}
	if($("#stype").val()=="1"){
		var showy=$("#showy").val();
		var showm=$("#showm").val();
		var showdays=$("#showdays").val();
		 if(showy==""||showy==undefined){
				ischeck=false;
				Dalert("请选择年");
				return ischeck;
			}
		 if(showm==""||showm==undefined){
				ischeck=false;
				Dalert("请选择月份");
				return ischeck;
			}
		 if(showdays==""||showdays==undefined){
				ischeck=false;
				Dalert("请填写表演日期");
				return ischeck;
			}
	}
	if(ischeck){
		if($("#iszk").prop('checked')){
			$("input[name='isoffer']").val(1);
		}
		if($("#protype").prop('checked')){
			$("input[name='isnk']").val(1);
		}
		
		$("#skuparam").val(JSON.stringify(unit_param));
		//按钮隐藏防止重复提交
		$("input[name='commit']").hide();
		editor.sync();
		$("input[name=description]").val(editor.html());
		var act="insertSpu";
		if($("#action").val()=="edit")act="updateSpu";
		$.ajax({
			url : "/platform/spu/"+act,
			data : $("#form").serialize(),
			type : "Post",
			dataType : "json",
			success : function(data) {
				if (data.code == 0) {
					Dalert("保存成功！", "", backhref);
				} else {
					$("input[name='commit']").show();
					Dalert(data.desc);
				}
			}
		});
	}
}
//选择店铺
function shopChange(){
	var stype=$("#shopid").children('option:selected').attr("data");
	$("#stype").val(stype);
	if(stype=="0"){
		$("#divday").show();
		$("#divshow").hide();
		$("#divnk").show();
		$('#divday .tjcpxx-con-form').addClass("skupday");
	}else if(stype=="1"){
		$("#divnk").hide();
		$("#divday").hide();
		$("#divshow").show();
		$('#divday .tjcpxx-con-form').removeClass("skupday");
	}else{
		$("#divnk").hide();
		$("#divday").hide();
		$("#divshow").hide();
		$('#divday .tjcpxx-con-form').removeClass("skupday");
	}
}

function timeChange(){
	var skuid=$("input[name=tid]").val();
	var showy=$("#showy").val();
	var showm=$("#showm").val();
	   $.ajax({
           url: "/platform/spu/getBySkuShowtime",
           type: "Post",
           data: {"skuid": skuid,"showy": showy,"showm": showm},
           dataType: "json",
           success: function (data) {
               if (data.code < 0) {
                   Dalert(data.desc);
               } else {
                   $("#showdays").val(data.data);
               }
           }
       });
}