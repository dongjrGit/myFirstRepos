//商品楼层列表
$(document).ready(function () {
    //关闭弹出层事件
    $(".l_close").bind("click", function () { $("#bigimg").hide(); });
    $("#bigimg img").bind("click", Jump);
    //列表
    ProFloor.getAll(1);
    //楼层id
    var flid = GetQueryStringByName("flid");
    var id=GetQueryStringByName("id");

    //修改 
    $(document).on("change","span[name=editpf]", function () {
        var id = $(this).attr("data_id");
        var proid = $(this).attr("data_pro");
        location.href = "ProFloorsEdit?id=" + id + "&proid=" + proid+ "&flid=" + flid;
    });
    //添加
    $("input[name=add]").click(function () {
       // location.href = "ProFloorsEdit?&id=-1" + "&flid=" + flid;
    	location.href = "/platform/floor/showProEdit?flid=" + flid;
    });
    
    // 返回
	$("input[name=back]").click(function() {
		 window.location.href = "/platform/floor/list";
	});
    
    //查询
    $("#searchpro").click(function () {
        ProFloor.getAll(1);
    });
})
var pcount;
var pindex;
var psize = 10;
var ProFloor = {
    getAll: function (index) {
        var flid = GetQueryStringByName("flid");
       // var dp = $("#fldisplay").val();
        var ty = $("#profltype").val();
        $.ajax({
            url: "/platform/floor/GetPageList",
            type: "Get",
            data: { "page": index, 
            	"size": psize,
            	"fid": flid,
            	//"display": dp,
            	"type":ty },
            dataType: "json",
            success: function (data) {
                if (data.code < 0) {
                   // Dalert(data.Desc);
                } else {
                    var listdata = {
                        list: data.data
                    }
                    var html = template('ProFloorlist', listdata);

                    //翻页时删除表头以外的所有节点，避免after()方法重复加载
                    $("#list_title").parent().children().each(function () {
                        if ($(this).attr('id') != "list_title") {
                            this.parentNode.removeChild(this);
                        }
                    })
                    //html 填充
                    $("#list_title").after(html);

                    psize = psize;
                    pcount = data.maxRow;
					pindex = data.pageIndex;
                    //分页
                    $("#pager").html(pagination(pcount, pindex, psize, "pagelist"));
                    //点击图片查看大图
                    $(".ppimg img").each(function () {
                        $(this).click(function () {
                            if ($(this).attr("src") != "" && $(this).attr("src") != undefined)
                                Show("bigimg", $(this).attr("src"));
                        });
                    });
                }
            },
            error: function () {

            }
        });
    },
    //删除
    del: function (id) {
        $.ajax({
            url: "/platform/floor/deleteProById",
            type: "Post",
            data: { "fID": id },
            dataType: "json",
            success: function (data) {
                Dalert(data.desc, "", refresh);
                //location.reload();
            }
        });
    }
}
function Del(id) {
    ConfirmShow("确定要删除吗？", ProFloor.del, id, "");
}
function refresh() {
    location.reload();
}
function pagelist(index) {
    ProFloor.getAll(index);
}
//点击大图跳转方法
function Jump() {
    var imgurl = $("#bigimg img").attr("src");
    var surl = "/Platform/img/ImageShow";
    ImageJump(imgurl, surl);
}
//修改排序/显示位置 tp=0显示位置，tp=1拍寻
function setValue(id, ob,tp) {
    var obtext = $("#" + ob).val();
    $.ajax({
        url: "/platform/floor/SetProFloorValue",
        type: "Post",
        data: { "id": id, 
        	"setValue": obtext, 
        	"type": tp },
        dataType: "json",
        success: function (data) {
            Dalert(data.desc);
        },
        error: function () {
            Dalert("修改排序失败");
        }
    });
}