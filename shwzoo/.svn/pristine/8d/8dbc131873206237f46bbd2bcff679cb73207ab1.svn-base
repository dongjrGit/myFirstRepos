// JavaScript Document

/*商品信息管理  tab选项卡 开始*/
function selectTag(showContent,selfObj){
	// 操作标签
	var tag = document.getElementById("tags").getElementsByTagName("li");
	var taglength = tag.length;
	for(i=0; i<taglength; i++){
		tag[i].className = "";
	}
	selfObj.parentNode.className = "selectTag";
	// 操作内容
	for(i=0; j=document.getElementById("tagContent"+i); i++){
		j.style.display = "none";
	}
	document.getElementById(showContent).style.display = "block";
}
/*商品信息管理  tab选项卡 结束*/

/*我的店铺 店铺管理 店铺装修设置 选项卡  开始*/
function setTab(name,cursel,n){

	for(i=1;i<=n;i++){
		var menu=document.getElementById(name+i);
		var con=document.getElementById("con_"+name+"_"+i);
		menu.className=i==cursel?"hover":"";
		con.style.display=i==cursel?"block":"none";
	}
}
/*我的店铺 店铺管理 店铺装修设置 选项卡  结束*/

$(document).ready(function(){
	
	 //订单管理 退货管理 表格的隔行变色
	 $(".thgl table tr:odd td").css("background-color","#F2F2F2");  //改变偶数行背景色
	 
	 //修改运单号 修改快递单号弹出框 表格单元行去掉背景色
	 $(".xgkddh-con-tab tr td").css("background-color","#ffffff"); 
	 
	 //修改运单号 表格点击修改 弹出修改框
	 $(".amend-ydh").click(function(){
		$(this).parent().find(".xgkddh").css("display","block")	 
	 });
	 
	 //我的店铺  财务管理 奖扣管理 箭头的排序的变化
	 $(".sorting img").click(function(){
		 $(this).attr({'src':'images/up.png','title':'日期从小到大排序','alt':'日期从小到大排序'});
		 //$(this).toggle()
	 });
	 
	 //仓库配送 运费模版 弹出框地区  表格的隔行变色
	 $(".xzqy-yfmbcon table tr:odd td").css("background-color","#EDF4FA");  //改变偶数行背景色
	 
	 //
	 $(".sel-yfmbdz").click(function(){
		 $("#yfmb-qycon").css("display","block")
	 });
	  $(".xzqy-yfmbtop-close").click(function(){
		 $("#yfmb-qycon").css("display","none")
	 });
	 $(".yfmb-xiugai").click(function(){
		 $(".xgyfxxgz").css("display","block")
	 });
	 $(".xgyfxxgz-close").click(function(){
		 $(".xgyfxxgz").css("display","none");
		 $(".mrgzxxgz").css("display","none");
		 
	 });
	 $(".xiug-mrmb").click(function(){
		 $(".mrgzxxgz").css("display","block")
	 });
	 
	 //企业注册  下拉框
	$(".selcon-right").click(function(){
		$(this).siblings(".selcon-main").css("display","block");
	});
	
	//企业注册 服务协议
	$(".g_fwxy").click(function(){
		 $(".n_fwxycon").css("display","block")
	 });
	  $(".n_fwxybottom").click(function(){
		 $(".n_fwxycon").css("display","none")
	 });
	 
	 
});



	$(document).ready(function(){
		$("input[type=text]").focus(function(){
		  $(this).css("color","#000")
		});
	});
