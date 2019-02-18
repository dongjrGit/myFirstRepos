var iscool5 = {
	info : {
		pullDownAction : "",
		pullUpAction : "",
		items_per_page : 1,
		scroll_in_progress : false,
		myScroll : null
	},
	init : function(pullDownAction, pullUpAction) {
		iscool5.info.pullDownAction = pullDownAction;
		iscool5.info.pullUpAction = pullUpAction;
		load_content();
	}
};

load_content = function(refresh, next_page) {

	// console.log(refresh, next_page);
	setTimeout(function() {
		// This immitates the CALLBACK of your AJAX function
		if (!refresh) {
			// Loading the initial content

		} else if (refresh && !next_page) {
			iscool5.info.pullDownAction();

		} else if (refresh && next_page) {
			iscool5.info.pullUpAction();
		}

		if (refresh) {

			iscool5.info.myScroll.refresh();
			$(".pullUp").hide();
			$(".pullDown").hide();
			pullActionCallback();

		} else {

			if (iscool5.info.myScroll) {
				iscool5.info.myScroll.destroy();
				$(iscool5.info.myScroll.scroller).attr('style', ''); // Required
				// since
				// the
				// styles
				// applied
				// by
				// IScroll
				// might
				// conflict
				// with
				// transitions
				// of
				// parent
				// layers.
				iscool5.info.myScroll = null;
			}
			trigger_myScroll();

		}
	}, 500);

};

function pullDownAction() {
	load_content('refresh');
	$('#wrapper > #scroller > ul').data('page', 1);

	// Since "topOffset" is not supported with iscroll-5
	$('#wrapper > .scroller').css({
		top : 0
	});

}
function pullUpAction(callback) {
	if ($('#wrapper > #scroller > ul').data('page')) {
		var next_page = parseInt($('#wrapper > #scroller > ul').data('page'),
				10) + 1;
	} else {
		var next_page = 2;
	}
	load_content('refresh', next_page);
	$('#wrapper > #scroller > ul').data('page', next_page);

	if (callback) {
		callback();
	}
}
function pullActionCallback() {
	if (pullDownEl && pullDownEl.className.match('loading')) {

		pullDownEl.className = 'pullDown';
		pullDownEl.querySelector('.pullDownLabel').innerHTML = 'Pull down to refresh';

		iscool5.info.myScroll.scrollTo(0, parseInt(pullUpOffset) * (-1), 200);

	} else if (pullUpEl && pullUpEl.className.match('loading')) {

		$('.pullUp').removeClass('loading').html('');

	}
}

var pullActionDetect = {
	count : 0,
	limit : 10,
	check : function(count) {
		if (count) {
			pullActionDetect.count = 0;
		}
		// Detects whether the momentum has stopped, and if it has reached the
		// end - 200px of the scroller - it trigger the pullUpAction
		setTimeout(
				function() {
					if (iscool5.info.myScroll.y <= (iscool5.info.myScroll.maxScrollY + 200)
							&& pullUpEl && !pullUpEl.className.match('loading')) {
						$('.pullUp')
								.addClass('loading')
								.html(
										'<span class="pullUpIcon">&nbsp;</span><span class="pullUpLabel">Loading...</span>');
						pullUpAction();
					} else if (pullActionDetect.count < pullActionDetect.limit) {
						pullActionDetect.check();
						pullActionDetect.count++;
					}
				}, 200);
	}
}

function trigger_myScroll(offset) {
	pullDownEl = document.querySelector('#wrapper .pullDown');
	if (pullDownEl) {
		pullDownOffset = pullDownEl.offsetHeight;
	} else {
		pullDownOffset = 0;
	}
	pullUpEl = document.querySelector('#wrapper .pullUp');
	if (pullUpEl) {
		pullUpOffset = pullUpEl.offsetHeight;
	} else {
		pullUpOffset = 0;
	}

	if ($('#wrapper ul > li').length < iscool5.info.items_per_page) {
		// If we have only 1 page of result - we hide the pullup and pulldown
		// indicators.
		$('#wrapper .pullDown').hide();
		$('#wrapper .pullUp span').hide();
		offset = 0;
	} else if (!offset) {
		// If we have more than 1 page of results and offset is not manually
		// defined - we set it to be the pullUpOffset.
		offset = pullUpOffset;
	}

	iscool5.info.myScroll = new IScroll('#wrapper', {
		probeType : 1,
		tap : true,
		click : false,
		preventDefaultException : {
			tagName : /.*/
		},
		mouseWheel : true,
		scrollbars : true,
		fadeScrollbars : true,
		interactiveScrollbars : false,
		keyBindings : false,
		deceleration : 0.0002,
		startY : (parseInt(offset) * (-1))
	});

	iscool5.info.myScroll.on('scrollStart', function() {
		iscool5.info.scroll_in_progress = true;
	});
	iscool5.info.myScroll
			.on(
					'scroll',
					function() {
						iscool5.info.scroll_in_progress = true;
						$(".pullUp").show();
						$(".pullDown").show();
						if ($('#wrapper ul > li').length >= iscool5.info.items_per_page) {
							if (this.y >= 5 && pullDownEl
									&& !pullDownEl.className.match('flip')) {
								pullDownEl.className = 'pullDown flip';
								pullDownEl.querySelector('.pullDownLabel').innerHTML = 'Release to refresh';
								this.minScrollY = 0;
							} else if (this.y <= 5 && pullDownEl
									&& pullDownEl.className.match('flip')) {
								pullDownEl.className = 'pullDown';
								pullDownEl.querySelector('.pullDownLabel').innerHTML = 'Pull down to refresh';
								this.minScrollY = -pullDownOffset;
							}

							// console.log(this.y);
							pullActionDetect.check(0);

						}
					});
	iscool5.info.myScroll
			.on(
					'scrollEnd',
					function() {
						// console.log('scroll ended');
						setTimeout(function() {
							iscool5.info.scroll_in_progress = false;
						}, 100);
						if ($('#wrapper ul > li').length >= iscool5.info.items_per_page) {
							if (pullDownEl
									&& pullDownEl.className.match('flip')) {
								pullDownEl.className = 'pullDown loading';
								pullDownEl.querySelector('.pullDownLabel').innerHTML = 'Loading...';
								pullDownAction();
							}
							// We let the momentum scroll finish, and if reached
							// the end - loading the next page
							pullActionDetect.check(0);
						}
					});

	// In order to prevent seeing the "pull down to refresh" before the iScoll
	// is trigger - the wrapper is located at left:-9999px and returned to
	// left:0 after the iScoll is initiated
	setTimeout(function() {
		$('#wrapper').css({
			left : 0
		});
	}, 100);
}

document.addEventListener('touchmove', function(e) {
	e.preventDefault();
}, false);