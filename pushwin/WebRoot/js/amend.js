$(function (){
	var num = 0,
	iframeH;
	function ifH(){
		num ++;
		if (num < 2){
			var iH = $('#t4ui_iframe', parent.document).attr('height');
			iframeH = Number(iH);
		}
		var index = $(this).find('td').eq(1).html();
		if (index == 10){
			$('#datagrid1').height(iframeH - 161);
			$(window.parent.document).find("#t4ui_iframe").attr('height',iframeH);	
		} else if (index == 20){
			$('#datagrid1').height(iframeH + 159);
			$(window.parent.document).find("#t4ui_iframe").attr('height',iframeH + 356);
		}	
	}
	
	var num2 = 0,
	iframeH2;
	function ifH2(){
		num ++;
		if (num < 2){
			var iH = $('#t4ui_iframe', parent.document).attr('height');
			iframeH2 = Number(iH); 
		}
		var index = $(this).find('td').eq(1).html();
		if (index == 10){
			$('#datagrid1').height(iframeH2 - 122);
			$(window.parent.document).find("#t4ui_iframe").attr('height',iframeH2);	
		} else if (index == 20){
			$('#datagrid1').height(iframeH2 + 199);
			$(window.parent.document).find("#t4ui_iframe").attr('height',iframeH2 + 386);
		}
	}
	$('#t4ui_log .mini-listbox-item').live('click', ifH);
	$('#t4ui_mang .mini-listbox-item').live('click', ifH);
	$('.t4ui_busData3 .mini-listbox-item').live('click', ifH2);
});