function formSubmit() {
		var ischeck=true;
		$("input[name='isoffer']").val(0);
		$("input[name='isnk']").val(0);
		$("input[name='istoday']").val(0);
		//库存商品信息
		var unit_param=[];
		var month = $("#showm").val();
		var year=$("#showy").val();
		$(".addr").each(function(){
			  var  daydate=$(this).find("input[name=daydate]").attr("data");
			  var stock=$(this).find("input[name=stock]").val();
			  var money=$(this).find("input[name=money]").val();
			  
			  if((stock!=""&& stock!=undefined && parseInt(stock)>=0)|| 
					  (money!=""&& money!=undefined && parseInt(money)>=0)){
				  
				  unit_param.push({
					    "showyear":year,
					    "showmonth":month,
			        	"showdays":daydate,
			        	"price":money,
			        	"stock":stock
			      });
		      }
			  
		});
		
		if(ischeck){
			if($("#iszk").prop('checked')){
				$("input[name='isoffer']").val(1);
			}
			if($("#protype").prop('checked')){
				$("input[name='isnk']").val(1);
			}
			if($("#isday").prop('checked')){
				$("input[name='istoday']").val(1);
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
