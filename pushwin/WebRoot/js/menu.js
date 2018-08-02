/* 
   Simple JQuery Accordion menu.
   HTML structure to use:

   <ul id="menu">
     <li><a href="#">Sub menu heading</a>
     <ul>
       <li><a href="http://site.com/">Link</a></li>
       <li><a href="http://site.com/">Link</a></li>
       <li><a href="http://site.com/">Link</a></li>
       ...
       ...
     </ul>
     <li><a href="#">Sub menu heading</a>
     <ul>
       <li><a href="http://site.com/">Link</a></li>
       <li><a href="http://site.com/">Link</a></li>
       <li><a href="http://site.com/">Link</a></li>
       ...
       ...
     </ul>
     ...
     ...
   </ul>

Copyright 2007 by Marco van Hylckama Vlieg

web: http://www.i-marco.nl/weblog/
email: marco@i-marco.nl
Download by http://sc.xueit.com
Free for non-commercial use
*/

function initMenu() {
  var bool = false;
  $('#menu ul').hide();
  $('#menu ul:first').hide();
  $('#menu li a').click(
    function() {
      var checkElement = $(this).next();
      if((checkElement.is('ul')) && (checkElement.is(':visible'))) {
    	  if(bool == false)
    	  {
    		  return; 
    	  }else
    	  {
    		  checkElement.slideUp('normal');
    	  }
        }
      if((checkElement.is('ul')) && (!checkElement.is(':visible'))) {
        $('#menu ul:visible').slideUp('normal');
          checkElement.slideDown('normal');
          bool =true;
        }
      }
    );
  }
$(document).ready(function() {initMenu();});

$(function (){
	var x = 10;
	var y = 20;
	$('.t4ui_skin span').mouseover(function (e){
		var href = $(this).attr('href');
		var tooltip = '<div id="tooltip"><img src="'+ href +'" alt="产品预览图"/></div>'; //创建<div>元素
		var mid = $('#t4ui_mainA', parent.document);  
		mid.append(tooltip);
	}).mouseout(function (){
		var mid = $('#t4ui_mainA', parent.document);  
		mid.find('#tooltip').remove();
	});
});