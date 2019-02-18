function formSubmit() {
		var ischeck=true;
		//库存商品信息
		var unit_param=[];
		 if($("#stype").val()=="0" && $("#protype").prop('checked')==false){
			  var num=$("input[name=tdnum]").val();
			  var price=$("input[name=tdprice]").val();
			  
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
				  id=$("input[name=tdid]").val();
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
		 }
			  var num=$("input[name=tnum]").val();
			  var price=$("input[name=tprice]").val();
			  
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
				  id=$("input[name=tid]").val();
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

		var act="insertShopSpu";
		if($("#action").val()=="edit")act="updateShopSpu";
		$.ajax({
			url : "/seller/shopproduct/"+act,
			data : $("#form").serialize(),
			type : "Post",
			dataType : "json",
			success : function(data) {
				if (data.code == 0) {
					Dalert("保存成功！", "", backhref);
				} else {
					$("input[name='commit']").show();
					Dalert("请将信息填写完整！");
				}
			}
		});
	}
}
function backhref() {
	window.location.href = "/seller/productshop/spgl_spulist";
}
function timeChange(){
	var skuid=$("input[name=tid]").val();
	var showy=$("#showy").val();
	var showm=$("#showm").val();
	   $.ajax({
           url: "/seller/shopproduct/getBySkuShowtime",
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