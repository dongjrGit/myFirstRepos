var ch = "3";
var type = 1;
var addcomment = {
	/*content : function(orderdetaileid, shopid, gooddescription, sellerattitude,
			logisticsspeed, star, title, showname, content, productid) {

		$.ajax(({
			type : "post",
			url : "/api/wap/comment/addsatisfaction",
			dataType : "json",
			data : {
				orderdetaileid : orderdetaileid,
				shopid : shopid,
				gooddescription : gooddescription,
				sellerattitude : sellerattitude,
				logisticsspeed : logisticsspeed,
				ch : ch
			},
			success : function(fh) {
				if (fh.code == 0) {
					//alert("添加满意度成功");

					addcomment.comment(orderdetaileid, shopid, type, productid,
							star, title, content, showname, gooddescription,
							sellerattitude, logisticsspeed);
				}
			}
		}))
	},*/
	/*comment : function(orderid,shopid,commentstr,description,serve,logistics) {
		
		
	},*/
	
	fimage : function(index) {
		
		$.ajaxFileUpload({
			url : "/app/api/img/upload",
			secureuri : false,
			fileElementId : 'file' + index,
			dataType : "json",
			// ftype:上传文件类型（图片文件=1，其他文件=2）
			// module: 模块（ 会员 = 0, 店铺 = 1, 平台 = 2, 产品 = 3, 其他 = 4,）
			data : {
				relationtype : 5
			},
			type : 'POST',
			success : function(result) {
				if (result.code == 0) {
					$("#img" + index).attr("src", result.data[0]);
					$("#imasrc" + index).val(result.data[0]);
					$("#li" + index).addClass("youtu");
				}
			},
			error : function(e) {
				// alert(JSON.stringify(e));

			}
		});
	}

}

$(document).ready(function(){
	$("#fabu").click(function (){
		var  shopid=$("#shopid").val();
		var  orderid=$("#orderid").val();
		var description = $("#miaoshu").attr("value");
		var logistics = $("#wuliu").attr("value");
		var serve = $("#fuwu").attr("value");
		if(document.getElementById("check").checked==true){var showname=1;}
		else{var showname=0;}
		
		//数组
		var paramjson=[];

		$.each($("input[name=title]"),function(i,item){
			//对象
			var param={};
			var detailid=$("input[name=orderdetailid]")[i].value;
			var productid=$("input[name=productid]")[i].value; 
			var pingjia=$("input[name=pingjia]")[i].value;
			var content=$("textarea[name=content]")[i].value;
			var image="";
			$("img[name=img"+productid+"]").each(function(i,item){
				   var $val=$(this).attr("src");
				  if($val!=null && $val!="" && $val!=undefined && $val!="/resource/wap/images/index_23.png"){
					if(image=="")
						image=$val;
					else
						image +=","+$val;
				}
				
			 });
				
		/*	var imasrc1=$("input[name=imasrc1]")[i].value;
			if($('#imasrc1').val()!=null && $('#imasrc1').val()!=""){
				if(image=="")
					image=$('#imasrc1').val();
				else
					image +=","+$('#imasrc1').val();
			}
			
			var imasrc2=$("input[name=imasrc2]")[i].value;
			if($('#imasrc2').val()!=null && $('#imasrc2').val()!=""){
				if(image=="")
					image=$('#imasrc2').val();
				else
					image +=","+$('#imasrc2').val();
			}
			
			var imasrc3=$("input[name=imasrc3]")[i].value;
			if($('#imasrc3').val()!=null && $('#imasrc3').val()!=""){
				if(image=="")
					image=$('#imasrc3').val();
				else
					image +=","+$('#imasrc3').val();
			}*/
			
			param.orderdetaileid=detailid;
			param.type="1";
			param.spuid=productid;
			param.star=pingjia;
			param.title=item.value;
			param.content=content;
			param.commimg=image;
			param.showname=showname;
			
			paramjson.push(param);
		});

		var pjson=JSON.stringify(paramjson);
		
		$.ajax(({
			type : "post",
			url : "/api/wap/comment/addordercomment",
			dataType : "json",
			data : {
				shopid : shopid,
				orderid : orderid,
				commentstr : pjson,
				gooddescription : description,
				sellerattitude : serve,
				logisticsspeed : logistics,
				ch : ch
			},
			success : function(fh) {
				if (fh.code == 0) {
					alert("添加评论成功");
					window.location.href="/wap/userinfo/homepage?ch=3";
				}else{
					alert(fh.desc);
				}
			}
		}))
	});
})

