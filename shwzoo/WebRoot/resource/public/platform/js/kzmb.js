// JavaScript Document
      $(document).ready(function(){ 
            var color="#ffffff"
            $(".data_list_detailed tr:odd td").css("background-color",color);  //改变偶数行背景色
            /* 把背景色保存到属性中 */
            $(".data_list_detailed tr:odd").attr("bg",color);
            $(".data_list_detailed tr:even").attr("bg","#F7F7F7");
      })
	  
	  $(document).ready(function(){ 
	  		$(".provinces .provin-tilte tr").last().css("border-bottom","none")
			$(".unwind-two").click(function(){
				$(this).toggleClass("unwind-three");
				$(this).parents("tr").find(".region").toggle();
				
			});
			$(".unwind").click(function(){
				$(".region").css("display","table-row");
				$(".unwind-two").addClass("unwind-three");
				
			});
			$(".unwind-one").click(function(){
				$(".region").css("display","none");
				$(".unwind-two").removeClass("unwind-three");
			});
	  });
	  
	  
	