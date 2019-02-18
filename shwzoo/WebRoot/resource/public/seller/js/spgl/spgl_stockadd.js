
function formSubmit() {
	// 防止重复提交 点击保存后隐藏按钮
	$("input[name='commit']").hide();
	
	var unit_param=[];
	var month = $("#showm").val();
	var year=$("#showy").val();
	var spuid=$("#spuid").val();
	var skuid=$("#skuid").val();
	$(".addr").each(function(){
		  var  daydate=$(this).find("input[name=daydate]").attr("data");
		  var stock=$(this).find("input[name=stock]").val();
		  var money=$(this).find("input[name=money]").val();
		  
		  if((stock!=""&& stock!=undefined && parseInt(stock)>=0)|| 
				  (money!=""&& money!=undefined && parseInt(money)>=0)){
			  
			  unit_param.push({
				  	"spuid":spuid,
				  	"skuid":skuid,
				    "showyear":year,
				    "showmonth":month,
		        	"showdays":daydate,
		        	"price":money,
		        	"stock":stock
		      });
	      }
	});
	
	$("#skuparam").val(JSON.stringify(unit_param));
	
	$.ajax({
		url : "/seller/shopproduct/addTimeStock",
		data : $("#form").serialize(),
		type : "Post",
		dataType : "json",
		success : function(data) {
			if (data.code == 0) {
				Dalert("保存成功！", "3000", refresh);
			} else {
				$("input[name='commit']").show();
				Dalert(data.desc,10000);
			}
		},
		error : function(e) {

		}
	});
}


function refresh() {
    parent.location.reload();
}

function Xclose() {
    parent.window.closeDialog();
}

