//楼层管理
$(function () {
    //获取列表
    Floor.getAll(1);
    //查询

	$("input[name=select_button]").click(function() {
		
		Floor.getAll(1);
	});

});

var pindex, psize = 10, pcount = 0;
var Floor = {
    getAll: function (index) {
        var na = $("#flname").val(); //名称
        var tp = $("#fltype").val(); //楼层类型
        //var dy = $("#fldisplay").val(); // 显示位置
      //  var pt = $("#flprotype").val();
        var pg = $("#flmark").val(); //楼层标识

        $.ajax({
            url: "/platform/floor/queryFloorListByCriteria",
            type: "Get",
            data: { "page": index,
            	"size": psize, 
            	"name": na,
            	"type": tp, 
            	//"display": dy,
            	//"proType": pt,
            	"pagetag": pg },
            dataType: "json",
            success: function (data) {
                if (data.code < 0) {
                   // Dalert(data.Desc);
                } else {
                    var listdata = {
                        list: data.data
                    }
                    var html = template('floorlist', listdata);

                    //翻页时删除表头以外的所有节点，避免after()方法重复加载
                    $("#list_title").parent().children().each(function () {
                        if ($(this).attr('id') != "list_title") {
                            this.parentNode.removeChild(this);
                        }
                    })
                    //html 填充
                    $("#list_title").after(html);
                    pcount = data.maxRow;
					pindex = data.pageIndex;
                    //分页
                    $("#pager").html(pagination(pcount, pindex, psize, "pagelist"));

                }
            },
            error: function () {

            }
        });
    },
    //删除
    del: function (id) {
        ConfirmShow("确定要删除吗？", confrimDel, id, "");
    }
}

function pagelist(index) {
    Floor.getAll(index);
}
//修改排序 tp=0设置显示位置，tp=1设置排序
function setFloorValue(flid, selecter,tp) {
    var obtext = $("#" + selecter).val();
    $.ajax({
        url: "/platform/floor/SetFloorValue",
        type: "Post",
        data: { id: flid, 
        	setValue: obtext,
        	type:tp },
        dataType: "json",
        success: function (data) {
        	
            Dalert(data.desc,"",function () { window.location.href = '/platform/floor/list'; });
        },
        error: function () {
            Dalert("修改失败");
        }
    });
}

function confrimDel(id) {
    $.ajax({
        url: "/platform/floor/deleteById",
        type: "Post",
        data: { "fID": id },
        dataType: "json",
        success: function (data) {
        	Dalert(data.desc, "",function () { window.location.href = '/platform/floor/list'; });
        }
    });
}
