//用户积分
var pcount;
var pindex;
var psize = 10;
var ptype;
var ptimebucket;
var Points = {
    //列表
    getList: function (index, type, timebucket) {
       // ptype = type;
      //  alert(ptype);
        ptimebucket = timebucket;
        $.ajax(({
            type: "post",
             url: "/pc/point/getpoints",
           // url:"/PointRecord/PointRecord_List",
            dataType: "json",
            data: {"type": type,
            	"page": index,
            	"size": psize, 
            	"time": timebucket,
            	"ch":1
            	 },
            success: function (rsl) {
            	rsl= eval('(' + rsl + ')');
                if (rsl.code == 0) {
                    var listdata = {
                        list: rsl.data
                    }
                    if (ptimebucket !=undefined){
                        var html = template('pointslist', listdata);
                    } else
                        var html = "";
                    //翻页时删除表头以外的所有节点，避免after()方法重复加载
                    $("#tr_title").parent().children().each(function () {
                        if ($(this).attr('id') != "tr_title") {
                            this.parentNode.removeChild(this);
                        }
                    })
                    $("#tr_title").after(html);

                    pcount = rsl.maxRow;
					pindex = rsl.pageIndex;
                    $("#pager").html(pagination(pcount, pindex, psize, "points_pagelist"));
                }
                else {
                    Dalert(rsl.desc);
                }
            },
            error: function (e) {
            	
            }
        }));
    }
}

//分页回调
function points_pagelist(index) {
    Points.getList(index, ptype, ptimebucket);
}

//时段改变
function Points_TimeBucket() {
    var timeBut = $("#select_timebucket").val();
    Points.getList(1, '', timeBut);
}

//改变评论类型
function ChangeType(obj, type) {
    var timeBut = $("#select_timebucket").val();
    var typeval = parseInt(type);
    
    switch (typeval) {
        case 0:
            Points.getList(1, typeval, timeBut);
            break;
        case 1:
            Points.getList(1, typeval, timeBut);
            break;
        default:
            Points.getList(1, '', timeBut);
            break;
    }
    $(".active").attr('class', 'normal');
    $(obj).attr('class', 'active');
}