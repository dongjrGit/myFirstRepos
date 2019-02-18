var zooOver={
		
		/* 显示遮罩层 */
		 showOverlay:function() {
		    $("#overlay").height(zooOver.pageHeight());
		    $("#overlay").width(zooOver.pageWidth());

		    // fadeTo第一个参数为速度，第二个为透明度
		    // 多重方式控制透明度，保证兼容性，但也带来修改麻烦的问题
		    $("#overlay").fadeTo(200, 0.5);
		},

		/* 隐藏覆盖层 */
		 hideOverlay:function() {
		    $("#overlay").fadeOut(200);
		},

		/* 当前页面高度 */
		pageHeight:function () {
		    return document.body.scrollHeight;
		},

		/* 当前页面宽度 */
		pageWidth:function () {
		    return document.body.scrollWidth;
		},

		/* 定位到页面中心 */
		 adjust:function(id) {
		    var w = $(id).width();
		    var h = $(id).height();
		    
		    var t = zooOver.scrollY() + (windowHeight()/2) - (h/2);
		    if(t < 0) t = 0;
		    
		    var l = zooOver.scrollX() + (windowWidth()/2) - (w/2);
		    if(l < 0) l = 0;
		    
		    $(id).css({left: l+'px', top: t+'px'});
		},

		//浏览器视口的高度
		windowHeight:function () {
		    var de = document.documentElement;

		    return self.innerHeight || (de && de.clientHeight) || document.body.clientHeight;
		},

		//浏览器视口的宽度
		windowWidth:function () {
		    var de = document.documentElement;

		    return self.innerWidth || (de && de.clientWidth) || document.body.clientWidth
		},

		/* 浏览器垂直滚动位置 */
		scrollY : function () {
		    var de = document.documentElement;

		    return self.pageYOffset || (de && de.scrollTop) || document.body.scrollTop;
		},

		/* 浏览器水平滚动位置 */
		scrollX:function () {
		    var de = document.documentElement;

		    return self.pageXOffset || (de && de.scrollLeft) || document.body.scrollLeft;
		}	
		
		
}

