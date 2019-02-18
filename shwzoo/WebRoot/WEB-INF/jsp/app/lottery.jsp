<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">

<title>幸运大转盘抽奖</title>
<link href="${pageContext.request.contextPath }/images/activity-style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jQueryRotate.2.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.easing.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/GetQueryString.js"></script>
<script type="text/javascript">
   var count = 0;
	$(function() {
		$("#inner").click(function() {
			count++
			if(count>3) {
				alert("每人每天3次抽奖机会")
				return;
			}
			lottery();
		});
	});
	function lottery() {
		var token = GetQueryStringByName("token");
		var ch = GetQueryStringByName("ch");
		$.ajax({
			type : 'POST',
			url : '/app/api/lottery/award',
			dataType : 'json',
			data: {token:token,ch:ch},
			cache : false,
			success : function(json) {
				var angle = parseInt(json.data); //角度 
				var msg = json.desc; 
				$("#outer").rotate({ 
					duration : 5000, //转动时间 
					angle : 0, 
					animateTo : 3600 + angle, //转动角度 
					easing : $.easing.easeOutSine, 
					callback : function() {
					confirm(msg);
					}
				});
				
			},
			error : function() {
				alert('出错了！');
				return false;
			}
		});
	}
</script>
</head>

<body class="activity-lottery-winning">

	<div class="main">
		<script type="text/javascript">
			var loadingObj = new loading(document.getElementById('loading'), {
				radius : 20,
				circleLineWidth : 8
			});
			loadingObj.show();
		</script>

		<div id="outercont">
			<div id="outer-cont" style="overflow:hidden;">
				<div id="outer">
					<img src="${pageContext.request.contextPath }/images/activity-lottery-1.png" width="310px">
				</div>
			</div>
			<div id="inner-cont">
				<div id="inner">
					<img src="${pageContext.request.contextPath }/images/activity-lottery-2.png">
				</div>
			</div>
		</div>
	</div>
<div class="content">
    <div class="boxcontent boxyellow">
      <div class="box">
        <div class="title-green"><span>奖项设置：</span></div>
        <div class="Detail">
          <p>赠送${groupname }代金卷${groupvalue }个 </p>
          <p>赠送${productname }商品${productvalue }个 </p>
          <p>赠送积分${beanvalue }个</p>
          <p>谢谢参与</p>
        </div>
      </div>
    </div>
    
    <div class="boxcontent boxyellow">
      <div class="box">
        <div class="title-green"><span>中奖规则：</span></div>
        <div class="Detail">
          <p>每人每天抽奖${ccounter }次</p>
          <p>每人每天只能中奖${lcount }次 </p>
        </div>
      </div>
    </div>
  </div>

</body>
</html>