//商品添加 编辑
function formSubmit() {
	var ischeck=true;
	$("input[name='isoffer']").val(0);
	$("input[name='istoday']").val(0);
	$("input[name='isnk']").val(0);
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
		//$("#divday").show();
		//$("#divshow").hide();
		$("#divnk").show();
		//$('#divday .tjcpxx-con-form').addClass("skupday");
	}else if(stype=="1"){
		$("#divnk").hide();
		//$("#divday").hide();
		//$("#divshow").show();
		//$('#divday .tjcpxx-con-form').removeClass("skupday");
	}else{
		$("#divnk").hide();
		//$("#divday").hide();
		//$("#divshow").hide();
		//$('#divday .tjcpxx-con-form').removeClass("skupday");
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