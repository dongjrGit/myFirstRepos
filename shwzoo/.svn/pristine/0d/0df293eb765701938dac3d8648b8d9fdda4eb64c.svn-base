//*控制面板--地区管理--地区管理*/
$(document).ready(function () {

    /* 当鼠标在表格上移动时，那一行背景变色 */
    $("body").on("mouseover",".teblelist tr td", function () {
        $(this).parent().find("td").css("background-color", "#d5f4fe");
    });

    /* 当鼠标在表格上移动时，离开的那一行背景恢复 */
    $("body").on("mouseout",".teblelist tr td", function () {
        var bgc = $(this).parent().attr("bg");
        $(this).parent().find("td").css("background-color", "#ffffff");
    });
})

$(document).ready(function () {

    $(".provinces .provin-tilte tr").last().css("border-bottom", "none")
    //编辑
    $("body").on("click",".bjxx", function () {
        var code = $(this).attr("data-code");
        var name = $(this).parents("a").find("label")[0].innerText;
        var level = $(this).attr("data-tag");
        var d = dialog({
            title: '编辑',
            content: "<label>名称:</label><input id='edName' value='" + name + "' />",
            cancelValue: "取消",
            okValue: "确定",
            fixed: true,
            cancel: function () { },
            ok: function () {
                var edName = $("#edName").val();
                Eidt(level, edName, code);
               // window.location.reload();
            }
        });
        d.show();
    })
    //添加
    $("body").on("click",".tjxx", function () {
    	 var code = $(this).attr("data-code");
         var level = $(this).attr("data-tag");
    	
    	var strhtml="";
    	strhtml+="<div><label>编码:</label><input id='edcode' value='' /><br/>";
    	strhtml+="<label>名称:</label><input id='edName' value='' /></div>";
    	var d = dialog({
    		title: '添加',
    		content: strhtml,
    		cancelValue: "取消",
    		okValue: "确定",
    		fixed: true,
    		cancel: function () { },
    		ok: function () {
    			var edcode = $("#edcode").val();
    			var edName = $("#edName").val();
    			add(level, edName, edcode, code);
                
    		}
    	});
    	d.show();
    })
    //删除
    $("body").on("click",".delete", function () {
    	var code = $(this).attr("data-code");
        var level = $(this).attr("data-tag");
        var d = dialog({
            title: '删除',
            content: "谨慎操作，确认要删除吗？",
            cancelValue: "取消",
            okValue: "确定",
            cancel: function () { },
            ok: function () {
                var edName = $("#edName").val();
                Delete(level, code);
//               window.location.reload();
            }
        });
        d.show();
    })
    //省展开
    $("body").on("click",".unwind-two",function () {
        $(this).toggleClass("unwind-three");
        $(this).parents("tr").find(".region").toggle();        
    });

    //全部展开 
    $(".unwind").click(function () {
        $(".region").css("display", "table-row");
        $(".unwind-two").addClass("unwind-three");
    });


    //全部收回
    $(".unwind-one").click(function () {
        $(".region").css("display", "none");
        $(".unwind-two").removeClass("unwind-three");
    });

    AreaList.bind();
    //编辑
    function Eidt(tag, name, code) {
        $.ajax({
            url: "/platform/areamanagement/updateByCode",
            type: "post",
            data: { tag: tag, name: name, code: code },//tag:1为区，2为市，3为省
            dataType: "json",
            success: function (data) {
                if (data.code < 0) {
                    Dalert(data.desc);
                } else {
                    Dalert(data.desc);
                    AreaList.bind();
                }
            },
            error: function () {
            }
        });
    }
    //添加
    function add(tag, name, code, fcode) {
        $.ajax({
            url: "/platform/areamanagement/add",
            type: "post",
            data: { tag: tag, name: name, code: code, fcode: fcode  },//tag:2为区，3为市，0为省
            dataType: "json",
            success: function (data) {
                if (data.code < 0) {
                    Dalert(data.desc);
                } else {
                	 Dalert(data.desc);
                	 AreaList.bind();
                     //window.location.reload();
                }
            },
            error: function () {
            }
        });
    }
    //删除
    function Delete(tag, code) {
        $.ajax({
            url: "/platform/areamanagement/deleteAreaByCode",
            type: "post",
            data: { tag: tag, code: code },//tag:1为区，2为市，3为省
            dataType: "json",
            success: function (data) {
                if (data.code < 0) {
                    Dalert(data.desc);
                } else {
                    Dalert(data.desc);
                    AreaList.bind();
                   // window.location.reload();
                }
            },
            error: function () {
            }
        });
    }

});
//地区列表
var AreaList = {
    bind: function () {
        $.ajax({
            url: "/platform/areamanagement/queryAreaList",
            type: "post",
            dataType: "json",
            success: function (data) {
                if (data.code < 0) {
                    //alert(data.Desc);
                } else {
                    var listdata = {
                        list: data.data
                    }
                    var html = template('arealist', listdata);
                    $("#datalist").siblings().remove();
                    $("#datalist").after(html);
                }
            },
            error: function () {
            }
        });
    }
}
