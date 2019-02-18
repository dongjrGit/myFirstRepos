<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">

<script type="text/javascript"
	src="/resource/public/commonjs/pulltorefresh/iscroll.js"></script>
<script type="text/javascript"
	src="/resource/public/commonjs/pulltorefresh/pulltorefresh.js?v=1.01"></script>
<link rel="stylesheet"
	href="/resource/public/commonjs/pulltorefresh/pulltorefresh.css" />
<script type="text/javascript">
	var myScroll1, generatedCount = 0;
	function inita() {
		myScroll1 = new iScroll('wrapper1');
	}

	function pullDownAction() {
		setTimeout(function() { // <-- Simulate network congestion, remove setTimeout from production!
			var el, li, i;
			el = document.getElementById('thelist');

			for (i = 0; i < 5; i++) {
				li = document.createElement('li');
				li.innerText = 'Generated row ' + (++generatedCount);
				el.insertBefore(li, el.childNodes[0]);
			}
			document.getElementById("pullUp").style.display = "";
			myScroll.refresh();
		}, 1000);
	}

	function pullUpAction() {
		setTimeout(function() {
			var el, li, i;
			el = document.getElementById('thelist');

			for (i = 0; i < 1; i++) {
				li = document.createElement('li');
				li.innerText = 'Generated row ' + (++generatedCount);
				el.appendChild(li, el.childNodes[0]);
			}
			myScroll.refresh();
		}, 1000);
	}
	refresher.init({id:"wrapper",pullUpAction:pullUpAction,pullDownAction:pullDownAction});


	document.addEventListener('touchmove', function (e) { e.preventDefault(); }, false);

	document.addEventListener('DOMContentLoaded', function () { setTimeout(loaded, 200); }, false);
	document.addEventListener('DOMContentLoaded', function () { setTimeout(inita, 200); }, false);
</script>

<style type="text/css" media="all">
#wrapper {
	position: absolute;
	z-index: 1;
	top: 100px;
	left: 12%;
	height: 400px;
	width: 80%;
	background: #aaa;
	border: 1px red solid;
}

#scroller {
	position: absolute;
	z-index: 1;
	/*	-webkit-touch-callout:none;*/
	-webkit-tap-highlight-color: rgba(0, 0, 0, 0);
	width: 100%;
	padding: 0;
}

#scroller ul {
	list-style: none;
	padding: 0;
	margin: 0;
	width: 100%;
	text-align: left;
}

#scroller li {
	padding: 0 10px;
	height: 40px;
	line-height: 40px;
	border-bottom: 1px solid #ccc;
	border-top: 1px solid #fff;
	background-color: #fafafa;
	font-size: 14px;
}

/**
 *
 * Pull down styles
 *
 */
#pullDown, #pullUp {
	background: #fff;
	height: 40px;
	line-height: 40px;
	padding: 5px 10px;
	border-bottom: 1px solid #ccc;
	font-weight: bold;
	font-size: 14px;
	color: #888;
}

#pullDown .pullDownIcon, #pullUp .pullUpIcon {
	display: block;
	float: left;
	width: 40px;
	height: 40px;
	background: url("pull-icon@2x.png") 0 0 no-repeat;
	-webkit-background-size: 40px 80px;
	background-size: 40px 80px;
	-webkit-transition-property: -webkit-transform;
	-webkit-transition-duration: 250ms;
}

#pullDown .pullDownIcon {
	-webkit-transform: rotate(0deg) translateZ(0);
}

#pullUp .pullUpIcon {
	-webkit-transform: rotate(-180deg) translateZ(0);
}

#pullDown.flip .pullDownIcon {
	-webkit-transform: rotate(-180deg) translateZ(0);
}

#pullUp.flip .pullUpIcon {
	-webkit-transform: rotate(0deg) translateZ(0);
}

#pullDown.loading .pullDownIcon, #pullUp.loading .pullUpIcon {
	background-position: 0 100%;
	-webkit-transform: rotate(0deg) translateZ(0);
	-webkit-transition-duration: 0ms;
	-webkit-animation-name: loading;
	-webkit-animation-duration: 2s;
	-webkit-animation-iteration-count: infinite;
	-webkit-animation-timing-function: linear;
}

@
-webkit-keyframes loading {from { -webkit-transform:rotate(0deg)translateZ(0);
	
}

to {
	-webkit-transform: rotate(360deg) translateZ(0);
}
}
</style>
</head>
<body>
	<p id="show"></p>
	<div id="wrapper">
		<div id="scroller">
			<div id="pullDown">
				<span class="pullDownIcon"></span><span class="pullDownLabel">Pull
					down to refresh...</span>
			</div>
			<ul id="thelist">
				<li>Pretty row 1</li>
				<li>Pretty row 2</li>
				<li>Pretty row 3</li>
			</ul>
			<div id="pullUp" style="display: none;">
				<span class="pullUpIcon"></span><span class="pullUpLabel">Pull
					up to refresh...</span>
			</div>
		</div>
	</div>
	<div id="wrapper1" style="height: 400px">
		<div id="scroller1">

			<ul id="thelist">
				<li>Pretty row 1</li>
				<li>Pretty row 2</li>
				<li>Pretty row 3</li>
				<li>Pretty row 1</li>
				<li>Pretty row 2</li>
				<li>Pretty row 3</li>
				<li>Pretty row 1</li>
				<li>Pretty row 2</li>
				<li>Pretty row 3</li>
				<li>Pretty row 1</li>
				<li>Pretty row 2</li>
				<li>Pretty row 3</li>
				<li>Pretty row 1</li>
				<li>Pretty row 2</li>
				<li>Pretty row 3</li>
				<li>Pretty row 1</li>
				<li>Pretty row 2</li>
				<li>Pretty row 3</li>
				<li>Pretty row 1</li>
				<li>Pretty row 2</li>
				<li>Pretty row 3</li>
				<li>Pretty row 1</li>
				<li>Pretty row 2</li>
				<li>Pretty row 3</li>
				<li>Pretty row 1</li>
				<li>Pretty row 2</li>
				<li>Pretty row 3</li>
				<li>Pretty row 1</li>
				<li>Pretty row 2</li>
				<li>Pretty row 3</li>
				<li>Pretty row 1</li>
				<li>Pretty row 2</li>
				<li>Pretty row 3</li>
				<li>Pretty row 1</li>
				<li>Pretty row 2</li>
				<li>Pretty row 3</li>
				<li>Pretty row 1</li>
				<li>Pretty row 2</li>
				<li>Pretty row 3</li>
				<li>Pretty row 1</li>
				<li>Pretty row 2</li>
				<li>Pretty row 3</li>
				<li>Pretty row 1</li>
				<li>Pretty row 2</li>
				<li>Pretty row 3</li>
				<li>Pretty row 1</li>
				<li>Pretty row 2</li>
				<li>Pretty row 3</li>
				<li>Pretty row 1</li>
				<li>Pretty row 2</li>
				<li>Pretty row 3</li>
			</ul>

		</div>
	</div>
</body>
</html>