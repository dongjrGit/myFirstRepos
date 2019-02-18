//底部导航
$(document).ready(function () {

    Class.bind();
    //父级展开
    $("body").on("click",".unwind-two", function() {
        $(this).toggleClass("unwind-three");
        $(this).parents("tr").next().find(".tab-scale").toggle();
        $(this).parents("tr").next().toggle();

    });
    //全部展开
    $(".unwind").click(function () {
        $(".tab-scale").css({ "display": "block", "width": "100%" });
        $(".tab-scale").parents("tr").css({ "display": "table-row", "width": "100%" });//
        $(".tab-scale").parents("td").css({ "width": "100%" });
        $(".unwind-two").addClass("unwind-three");
    });
    //全部收缩
    $(".unwind-one").click(function () {
        $(".tab-scale").css({ "display": "none", "width": "100%" });
        $(".tab-scale").parents("tr").css({ "display": "none", "width": "100%" });
        $(".unwind-two").removeClass("unwind-three");
    });
    /* 当鼠标在表格上移动时，那一行背景变色 */
    $("body").on("mouseover",".data_list tr td", function() { 
        $(this).parent().find("td").css("background-color", "#d5f4fe");
    });

    /* 当鼠标在表格上移动时，离开的那一行背景恢复 */
    $("body").on("mouseout",".data_list tr td", function() {
        var bgc = $(this).parent().attr("bg");
        $(this).parent().find("td").css("background-color", "#ffffff");
    });
    //删除
    $("body").on("click",".delete", function() {
        var id = $(this).parent().parent().find("input").val();
        var deldia = dialog({
            title: '提示',
            content: '确定要删除分类吗？',
            okValue: '确定',
            cancelValue: '取消',
            cancel: function () { },
            ok: function () {
                $.ajax({
                    type: "post",
                    url: "/platform/news/delNewsClassById",
                    dataType: "json",
                    data: {
                        "id": id
                    },
                    success: function (rsl) {
                        if (rsl.code < 0) {
                            Dalert(rsl.desc);
                        }
                        else {
                            Dalert(rsl.desc,"",refresh);
                        }
                    },
                    error: function (e) {
                        //alert(e.statusText);
                    }
                })
            }
        }).show();
    });
    //编辑
    $("body").on("click",".sctp", function() {
        var id = $(this).parent().parent().find("input").val();
        var type=$("#type").val();
        location.href = "/platform/news/showAddNewsClass?id=" + id+"&type="+type;
    })
    //添加
    $("#addbtn").click(function () {
    	var type=$("#type").val();
        location.href = "/platform/news/showAddNewsClass?type="+type;
    });
    
})

var Class = {
    bind: function () {
        Class.getClassTree();
    },
    getClassTree: function () {
    	var type=$("#type").val();
        $.ajax({
            url: "/platform/news/queryClassTree",
            type: "Post",
            data: {"type":type},
            dataType: "json",
            success: function (data) {
                if (data.code < 0) {
                    Dalert(data.desc);
                } else {
                    var html="";
                    $.each(data.data,function(index){
                    	var vo=data.data[index];
                    	html+='<tr>';
                    	html+='<td class="li-he45 text-left"><span class="unwind-two"></span>'+vo.name+'</td>';
                    	html+='<td width="15%">'+vo.sort+'</td>';
                    	html+='<td>';
                    	html+='<a href="javascript:void(0);"><span class="sctp shenlan">编辑</span></a>';
                    	html+='<span class="marrig35"></span><input type="hidden" value="'+vo.id+'">';
                    	html+=' <a href="javascript:void(0);"><span class="delete shenlan">删除</span></a>';
                    	html+=' </td>';
                    	html+='</tr>';

                		html+='<tr style="border:none;display:none">';
                		html+='<td colspan="6" style="border:none">';
                		html+='<div class="tab-scale">';
                		html+='<table width="100%" style="width:100%">';
                    	$.each(vo.list,function(index){
                    		var vos=vo.list[index];
                    		html+='<tr class="table-con-mk"  style="border:1px solid #ccc;">';
                    		html+=' <td class="text-left padleft100" width="40%"><span class="unwind-two" style="margin-top:0px;margin-left:0px;"></span>'+vos.name+'</td>';
                    		html+='<td width="15%">'+vos.sort+'</td>';
                    		html+='<td width="20%">';
                    		html+=' <a href="javascript:void(0);"><span class="sctp shenlan">编辑</span></a>';
                    		html+=' <span class="marrig35"></span><input type="hidden" value="'+vos.id+'">';
                    		html+='<a href="javascript:void(0);"><span class="delete shenlan">删除</span></a>';
                    		html+='</td></tr>';
                    		html+=initHtml(vos);
                    	});

                		html+='</table> </div></td></tr>';
                    });
                    
                    
                    $("#list_title").after(html);
                }
            },
            error: function () {

            }
        });
    }
}

function refresh() {
    location.reload();
}


//便利
function initHtml(data){
	var htmls="";
	if(data.list!=null){
	htmls+='<tr class="table-con-mk-con" style="display: none;">';
	htmls+='<td colspan="5" style="border:none">';
	htmls+='<div class="table-con-mk">';
	htmls+='<table width="100%" style="width:100%;height:100%">';
	$.each(data.list,function(index){
			var valuedata=data.list[index];
			htmls+='<tr>';
			htmls+='<td class="text-left padleft130" width="40%"><span class="unwind-two" style="margin-top:0px;margin-left:0px;"></span>'+valuedata.name+'</td>';
            htmls+='<td width="15%">'+valuedata.sort+'</td>';
			htmls+='<td >';
            htmls+='<a href="javascript:void(0);"><span class="sctp shenlan">编辑</span></a>';
            htmls+='<span class="marrig35"></span><input type="hidden" value="'+valuedata.id+'">';
			htmls+='<a href="javascript:void(0);"><span class="delete shenlan">删除</span></a>';
			htmls+='</td>';
            htmls+='</tr>';
            if(valuedata.list!=null){
            	htmls+=initHtml(valuedata);
            }
	});

	htmls+='</div></table></td></tr>';
	}
    return htmls;
}

