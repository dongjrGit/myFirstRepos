// JavaScript Document

	  /* 当鼠标移到表格上是，当前一行背景变色 */
      $(document).ready(function(){
            $(".data_list tr td").mouseover(function(){
                $(this).parent().find("td").css("background-color","#d5f4fe");
            });
      })
      /* 当鼠标在表格上移动时，离开的那一行背景恢复 */
      $(document).ready(function(){ 
            $(".data_list tr td").mouseout(function(){
                var bgc = $(this).parent().attr("bg");
                $(this).parent().find("td").css("background-color",bgc);
            });
      })
      
      $(document).ready(function(){ 
            var color="#ffffff"
            $(".data_list tr:odd td").css("background-color",color);  //改变偶数行背景色
            /* 把背景色保存到属性中 */
            $(".data_list tr:odd").attr("bg",color);
            $(".data_list tr:even").attr("bg","#F7F7F7");
      })
	  $(document).ready(function(){ 
            var color="#ffffff"
            $(".glance-over-con table tr:odd td").css("background-color","#ffffff");  //改变偶数行背景色
            /* 把背景色保存到属性中 */
            $(".glance-over-con table tr:odd").attr("bg",color);
            $(".dglance-over-con table tr:even").attr("bg","#ccc");
      })
	  
	  $(document).ready(function(){ 
            $(".sctp-con ul li").mouseenter(function(){
				$(this).find(".sctp-con-dele").css("display","block");
			})
			$(".sctp-con ul li").mouseleave(function(){
				$(this).find(".sctp-con-dele").css("display","none");
			})
			$(".sctp").click(function(){
				$(".sctp-con").css("display","block");	
			})
			$(".close").click(function(){
				$(".sctp-con").hide();		
			});
			$(".text-center td").css("text-align","center");	
			
			$(function(){
				$("input[type=file]").change(function(){$(this).parents(".uploader").find(".filename").val($(this).val());});
				$("input[type=file]").each(function(){
				if($(this).val()==""){$(this).parents(".uploader").find(".filename").val("批量导入商品");}
				});
			});
	});
	  
	  
	$(document).ready(function(){ 
    	$("#medo").click(function(){
				$(this).css({"display":"none",});
				$("#medo2").css({"display":"block",});
				$(".logo").hide();
				$(".greet").css({"left":"20px",});
				$("#left").hide();
				$("#content").css({"margin-left":"0px",});
				$(".message-con").css({"left":"20px",});
				$(".ddgl").css({"padding-left":"20px",});
			}); 
		$("#medo2").click(function(){
				$(this).css({"display":"none",});
				$("#medo").css({"display":"block",});
				$(".logo").show();
				$(".greet").css({"left":"330px",});
				$("#left").show();
				$("#content").css({"margin-left":"300px",});
				$(".message-con").css({"left":"330px",});
				$(".ddgl").css({"padding-left":"320px",});
			});   	     	
	});
	
	
	
	$(document).ready(function(){ 
            $("#screening").click(function(){
				/*  $("#screening a").toggle(
  function () {
    $(this).css("color","#f00");
  },
  function () {
    $(this).css("color","#666");
  }
);  */
				var odiv=document.getElementById('screening-con')
					if(odiv.style.display=='block')
					{
						odiv.style.display='none'
						$("#screening").html("<a>更多筛选条件 &or;</a>");
						$("#screening a:hover").css('text-decoration','none'); 
						}
					else
					{
						odiv.style.display='block'
						 $("#screening").html("<a>精简筛选条件 &and;</a>");
						 $("#screening a:hover").css('text-decoration','none'); 
						}
			});
			
			/* $("#screening").onMouseDown(function(){
				$("#screening a").css("color","#f00");
			})*/
			
      });
	  
	
	
	
      
      $(document).ready(function(){ 
            var color="#EFEFEF"
            $(".weather-left-ddgl tr:odd td").css("background-color",color);  //改变偶数行背景色
            /* 把背景色保存到属性中 */
            $(".weather-left-ddgl tr:odd").attr("bg",color);
            $(".weather-left-ddgl tr:even").attr("bg","#F7F7F7");
      })
	  
	  /* 当鼠标移到表格上是，当前一行背景变色 */
      $(document).ready(function(){
            $(".data_list_detailed tr td").mouseover(function(){
                $(this).parent().find("td").css("background-color","#d5f4fe");
            });
      })
      /* 当鼠标在表格上移动时，离开的那一行背景恢复 */
      $(document).ready(function(){ 
            $(".data_list_detailed tr td").mouseout(function(){
                var bgc = $(this).parent().attr("bg");
                $(this).parent().find("td").css("background-color",bgc);
            });
      })
      
      $(document).ready(function(){ 
            var color="#ffffff"
            $(".data_list_detailed tr:odd td").css("background-color",color);  //改变偶数行背景色
            /* 把背景色保存到属性中 */
            $(".data_list_detailed tr:odd").attr("bg",color);
            $(".data_list_detailed tr:even").attr("bg","#F7F7F7");
      })
	  
	  $(document).ready(function(){ 
			$(".unwind-two").click(function(){
				$(this).toggleClass("unwind-three");
			  $(this).parents("tr").next().find(".tab-scale").toggle();
			  $(this).parents("tr").next().first().toggle();
			 	
			});	
			$(".unwind").click(function(){
				 $(".tab-scale").css({"display":"block","width":"100%"});
				 $(".tab-scale").parents("tr").css({"display":"table-row","width":"100%"});//
				 $(".tab-scale").parents("td").css({"width":"100%"});
				 $(".unwind-two").addClass("unwind-three");
			});
			$(".unwind-one").click(function(){
				 $(".tab-scale").css({"display":"none","width":"100%"});
				 $(".tab-scale").parents("tr").css({"display":"none","width":"100%"});
				 $(".unwind-two").removeClass("unwind-three");
			});	      	
	  });
	  
	  
	  
	  /* 首页获取当前时间   星期  开始*/
	  $(document).ready(function(){
			var day="";
			var month="";
			var ampm="";
			var ampmhour="";
			var myweekday="";
			var year="";
			mydate=new Date();
			myweekday=mydate.getDay();
			mymonth=mydate.getMonth()+1;
			myday= mydate.getDate();
			myyear= mydate.getYear();
			year=(myyear > 200) ? myyear : 1900 + myyear;
			if(myweekday == 0)
			weekday=" 星期日 ";
			else if(myweekday == 1)
			weekday=" 星期一 ";
			else if(myweekday == 2)
			weekday=" 星期二 ";
			else if(myweekday == 3)
			weekday=" 星期三 ";
			else if(myweekday == 4)
			weekday=" 星期四 ";
			else if(myweekday == 5)
			weekday=" 星期五 ";
			else if(myweekday == 6)
			weekday=" 星期六 "; 
			  /*alert(t);*/
			  $(".time-one").text(year+"年"+mymonth+"月"+myday+"日 "+weekday);
			  
  
	  });
	   /* 首页获取当前时间   星期  结束*/
	  
	  $(function(){ 
                //可以是:$('#t1 tbody tr:even').css('background','red'); 
                $('#t1 > tbody td:even').css('background','#F7F7F7'); 
				$('#t1 > tbody td:even').css('font-weight','bolder'); 
				$('#t1 > tbody td:even').css('color','#333'); 
                $('#t1 > tbody td:odd').css('background','#ffffff'); 
            }); 
			
			
	 function setTab(name,cursel,n){
			for(i=1;i<=n;i++){
			var menu=document.getElementById(name+i);
			var con=document.getElementById("con_"+name+"_"+i);
			menu.className=i==cursel?"hover":"";
			con.style.display=i==cursel?"block":"none";
		}
	};
	
	
	$(document).ready(function(){ 
		$("#SelectAll").click(function() { 
			var flag = $(this).attr("checked"); 
			$("[name=chk_list]:checkbox").each(function() { 
			$(this).attr("checked", flag); 
			}) 
		});
		$("#SelectAll-one").click(function() { 
			var flag = $(this).attr("checked"); 
			$("[name=chk_list]:checkbox").each(function() { 
			$(this).attr("checked", flag); 
			}) 
		});
	});
	$(document).ready(function(){ 
		$(".browse-red").click(function(){
			$(".glance-over-con").css("display","block")	
		});
		$(".go-back").click(function(){
			$(".glance-over-con").css("display","none")	
		});
		
		//文本框的焦点
		/*
		$("input[type='text']").focus( function(){ 
			$(this).css("border","1px solid #F56366")
		});
		$("input[type='text']").blur( function(){ 
			$(this).css("border","1px solid #ccc")
		});
		$(".tab2-bor-none").focus( function(){ 
			$(this).css("border","none")
		});
		$(".tab2-bor-none").blur( function(){ 
			$(this).css("border","none")
		});*/
		
		//在线银行卡支付表格单元行高度固定
		$(".pay-title").css("height","52px")
		
		//控制面板图片管理 图片分类 编辑里面 控制左边图片的高度与宽度相等
		//alert($(".kzmb-bjtp-left-img").width())
		var imgWidth=$(".kzmb-bjtp-left-img").width()
		$(".kzmb-bjtp-left-img").css("height",imgWidth)
		
		
		//底部信息管理  友情链接  文字链接  点击添加  增加表格单元行
		$("#wzlj-tj").bind("click",function(){
			$("#wzlj-tab").append('<tr><td><input class="wzlj-xh" name="" type="text"></td><td><input class="wzlj-gsmc" name="" type="text"></td><td><input class="wzlj-gsmc" name="" type="text"></td><td class="wzlj-td-bj"><span class="lvs"><a href="#">编辑</a></span>  <span class="lvs"><a href="#">删除</a></span></td></tr>');
		});
		
		//底部信息管理  友情链接  文字链接图片链接  点击删除  删除对应的单元行
		$(".wzlj-lvs").click(function(){
			//jConfirm('你确定这么做吗?', '确认对话框', function(r) {
				//jAlert('是否确定 ' + r, '确定内容');
			//});
			$(this).parents("tr").remove();
		});
		
		//底部信息管理  友情链接  图片链接  点击添加  增加表格单元行
		$("#wzlj-tj-one").bind("click",function(){
			$("#wzlj-tab-one").append('<tr><td><input class="wzlj-xh" name="" type="text"></td><td><span class="lvs"><a href="#">上传图片</a></span></td><td><input class="wzlj-gsmc" name="" type="text"></td><td class="wzlj-td-bj"><span class="lvs"><a href="#">编辑</a></span>  <span class="wzlj-lvs"><a href="#">删除</a></span></td></tr>');
		});
		
		
		//控制面板 其他设置 计划任务  点击计划任务执行说明  弹出说明
		$(".jhrw-sm").click(function(){
			$(".jhrw-trzx").find("ul").toggle();	
		});
		
		//seo优化  新增关键词
		$("#xzkey").bind("click",function(){
			$("#xzgjz-stop").before('<tr><td><input name="" type="checkbox" value=""></td><td>4</td><td><input name="" type="text" class="seo-inp" value=""></td><td><input name="" type="text" class="seo-inp" value=""></td><td><input name="" type="text" class="seo-inp-pl" value=""></td><td>0</td><td>2015-05-07 15:52:53</td><td class="sjbf-cz"><a href="#">更新</a> 　 <a href="#">删除</a> 　</td></tr>');
		});
		
		
		//站点管理 站点管理 导航菜单 开始
		$(".Column-con2 table").hide();
		$("#dhcd-Column-con img").click(function(){
			$(this).attr("src","images/bg10.png");
			//$('#dhcd-Column-con img').not(this).attr("src","images/bg09.png"); 
			$(this).parents("div").children("div").children(".Column-tab").show();
			
		});
		
		//控制面板  系统设置  时间设置  北京时间
		$(document).ready(function(){
			setInterval(function() {
					var now = (new Date()).toLocaleString();
					$('#txt').text(now);
				});
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
		
	//登录界面复选框
	$(".fxklogin img").click(function(){
		$(this).change().attr("src","images/check02.png");
	});									
                                   
		 
		
	});
	
	