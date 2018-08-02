// JavaScript Document
$(document).ready(function () {
     var main = $(window.parent.document).find("#main");
	 var thisheight = $(document.body).height();
	 main.height(thisheight);
 });


function change(whichLink) 
    {
    	var links = document.getElementsByName("a");
    	for (var i = 0; i < links.length; i++) 
		{
			links[i].className = "";
			whichLink.className = "amenu";
		}
	}

function changeli(whichLink)
{
	var links = document.getElementsByTagName("li");
	var linkes = document.getElementsByTagName("span");
    for (var i = 0; i < links.length; i++) 
	{
			links[i].className = "t4ui_li";
			whichLink.className = "t4ui_liTwo";		
	}
}
function changespan(whichLink)
{
	var links = document.getElementsByTagName("span");
    for (var i = 0; i < links.length; i++) 
	{
			links[i].className = "t4ui_tab";
			whichLink.className = "t4ui_tab2";		
	}
}
// iframe自适应
function dyniframesize(down) { 
	var pTar = null; 
	if (document.getElementById){ 
		pTar = document.getElementById(down); 
	} 
	else{ 
		eval('pTar = ' + down + ';'); 
	} 
	if (pTar && !window.opera){ 
		//begin resizing iframe 
		pTar.style.display="block" 
		if (pTar.contentDocument && pTar.contentDocument.body.offsetHeight){ 
		//ns6 syntax 
		pTar.height = pTar.contentDocument.body.offsetHeight + 60;
		pTar.width = pTar.contentDocument.body.scrollWidth;
		} 
		else if (pTar.Document && pTar.Document.body.scrollHeight){ 
		//ie5+ syntax 
		pTar.height = pTar.Document.body.scrollHeight; 
		pTar.width = pTar.Document.body.scrollWidth; 
		} 
	} 
	
	var iFr = $("#t4ui_iframe").contents().find("#t4ui_org").attr('id');
	if (!!iFr){
		$("#t4ui_iframe").attr('height', '620px');
		
	}
}

$(function(){	
	var $li =$(".t4ui_skin span");
	$li.click(function(){
		switchSkin( this.id );
	});
});

//改变皮肤
function switchSkin(skinName){
	var mid = $('#cssfile', parent.document);  
	$("#"+skinName).addClass("selected")                 //当前<li>元素选中
				  .siblings().removeClass("selected");  //去掉其它同辈<li>元素的选中
	mid.attr("href","css/"+ skinName +".css");
	$("#cssfile").attr("href","css/"+ skinName +".css"); //设置不同皮肤	
}

