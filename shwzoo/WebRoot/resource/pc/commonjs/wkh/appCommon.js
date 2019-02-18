var page=[1];//当前页
var pageUrl="";//页面url
var params=null;//参数
var content="";//主体
var pages=[1];//总页数
var isEndword=true;
/*$(function(){
	$(window).scroll(function(){
		var scrollTop=$(this).scrollTop();
		var scrollHeight=$(document).height();
		var windowHeight=$(this).height();
		if(scrollTop+windowHeight==scrollHeight){
			initPage();
		}
	});
});*/

function initPage(s){
			if (VarUtil.isNull(s)) {
				s=0;
			}
			params.page=page[s];
			if(page[s]<=pages[s]){
				var nohtml = '';
				var data=AjaxUtil.Get(pageUrl, params, 'html',beforeSend(),complete());
				if(data){
					pages[s]=$(data).find('input[name="pages"]').val();
					$('#'+content).append(data);
					
					if(pages[s]==page[s]){
						/*if(isEndword){
						$("#jiazai").remove();
						nohtml+='<div class="jiazai" id="jiazai" style="text-align: center;background-color: #fff;margin-top: 1px;margin-bottom: 11px;" >';
						nohtml+='<span style="font-size: 10px;">加载完啦</span>';
						nohtml+='</div>';
						$("#"+content).after(nohtml);
						}*/
					}else{
						/*if(isEndword){
						$("#jiazai").remove();
						nohtml+='<div class="jiazai" id="jiazai" style="text-align: center;background-color: #fff;margin-top: 1px;margin-bottom: 11px;" >';
						nohtml+='<span style="font-size: 10px;" onclick="initPage()">点击加载更多</span>';
						nohtml+='</div>';
						$("#"+content).after(nohtml);

						}*/
					}
					page[s]++;
				}else{

					/*if(isEndword){
					$("#jiazai").remove();
					nohtml+='<div class="jiazai" id="jiazai" style="text-align: center;background-color: #fff;margin-top: 1px;margin-bottom: 11px;" >';
					nohtml+='<span style="font-size: 10px;">加载完啦</span>';
					nohtml+='</div>';
					$("#"+content).after(nohtml);

					}*/
				}
	}
}



function beforeSend(){
	/*var nohtml = '';
	nohtml+='<div class="row" style="text-align:center" id="zhuanquan"  >';
	nohtml+='<img vertical-align=middle widht=20 height=20  src="'+ctx+'/STATIC/images/zhuanquan.jpg"/>';
	nohtml+='</div>';
	$('body').append(nohtml);*/
}


function complete(){
	/*$("#zhuanquan").remove();*/
}
