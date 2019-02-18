$(document).ready(function() {
     Ad.unit(); 
     //advert.bind(1);
    $("#searchtitle").bind("click", function() {
        advert.bind(1);
    });
  
    $("body").on("click", ".set", function() {
        var advertid = $(this).attr("data-id");
        var ss = $(this).attr("data-status");
        $.ajax({
            url : "/platform/adverising/UpdateStatus",
            type : "Post",
            data : {
                'id' : advertid,
                'status' : ss
            },
            dataType : "json",
            success : function(data) {
                if (data.code < 0) {
                    Dalert(data.desc);
                } else {
                    var td_html = "";
                    if (ss == "0") {
                        td_html = "<span class='lvs'><a href='#' data-id='" + advertid + "' data-status='0'>启用</a></span>";
                    } else {
                        td_html = "<span class='lvs'><a href='#'data-id='" + advertid + "' data-status='1'>禁用</a></span>";
                    }
                    $("#td_" + advertid).html(td_html);
                    refresh();
                }
            },
            error : function() {
                Dalert("修改状态失败");
            }
        });
    })
    // 删除广告
    $("body").on("click", ".delete", function() {
        var id = $(this).parent().parent().find("input").val();
        //var id = $(this).attr("data-id");
        ConfirmShow("确认要删除信息吗？", del, id);

    });

    // 编辑
    $("body").on("click", ".cxtt", function() {
        var id = $(this).parent().parent().find("input").val();
        //    var id = $(this).attr("data-id");
        location.href = "/platform/advertimg/showeditAdvert?id=" + id;
    });

})
var advert = {
    bind : function(index) {
        /*
         * var begin=$("#time").val(); var end=$("#time1").val();
         */
        var type = $("#firstID option:selected").val();
        var status = $("#status option:selected").val();
        /*if($("#position").val()==""||$("#position").val()==null){
         $("#position").val(1);
         }
         var position=$("#position").val();*/
        var position = $("#secondID").val();
        $.ajax({
            url : "/platform/adverising/queryAdvert",
            type : "Get",
            dataType : "json",
            data : {
                page : index,
                size : 10,
                position : position,
                type : type,
                status : status
            },
            success : function(data) {
                if (data.code < 0) {
                    $("#divshow").attr("style", "display:none");
                    $("#pager").attr("style", "display:none");
                } else {
                    $("#divshow").attr("style", "display:block;")
                    $("#pager").attr("style", "display:block");
                    var listdata = {
                        list : data.data
                    }
                    var html = template('Advertlist', listdata);
                    $("#datalist").html(html);
                    // 分页
                    // title = title;
                    pcount = data.maxRow;
                    pindex = data.pageIndex;
                    $("#pager").html(pagination(pcount, pindex, 10, "pagelist"));
                }
                /*
                 * if($("#SelectAll").attr("checked")=="checked"){
                 * $("input[name=ch_All]").attr("checked",false); }
                 */
            },
            error : function() {

            }
        });
    }
}
function pagelist(index) {
    advert.bind(index);
}

function refresh() {
    // location.reload();
    advert.bind(1);
}

var Ad = {
    unit : function() {
        Ad.getList();
    },
    getList : function() {
        $.ajax({
            url : "/platform/adverising/getPageMark",
            type : "Post",
            data : {},
            dataType : "json",
            success : function(data) {

                if (data.code < 0) {
                    Dalert(data.desc);
                } else {
                    var listdata = {
                        list : data.data
                    }
                    var html = template('flist', listdata);
                    $("#firstID").append(html);
                }
            },
            error : function() {
            }
        });
    }
}

// 删除 id:图片id
function del(id) {
    $.ajax({
        type : "post",
        url : "/platform/adverising/deleteAdvertById",
        dataType : "json",
        data : {
            id : id
        },
        success : function(rsl) {
            if (rsl.code == 0) {
                Dalert(rsl.desc, "", refresh);
            } else {
                Dalert(rsl.desc);
            }
        },
        error : function(e) {

        }
    });
}

//修改排序
function setValue(id, ob) {
    var obtext = $("#" + ob).val();
    $.ajax({
        url : "/platform/advertimg/SetSort",
        type : "Post",
        data : {
            "id" : id,
            "setValue" : obtext
        },
        dataType : "json",
        success : function(data) {
            Dalert(data.desc);
        },
        error : function() {
            Dalert("修改排序失败");
        }
    });
}