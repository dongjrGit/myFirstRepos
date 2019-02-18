//获取商品列表
var claid = "";
var searchlist = {
    bind: function () {
        claid = $("#cid").val();
//        //获取商品列表
//    	searchlist.keysSearch(1);
    	 $("li[name='search_values']").each(function () {
             $(this).bind("click", searchlist.checkValueSelect);
         });
         $("#orderby").find("li").bind("click", function () {
             var $li = $(this);
             if ($li.attr("isselect") == "1") {
                 if ($li.attr("data_tag") == "0") {
                     $li.attr("data_tag", "1");
                     $li.addClass("active");
                 }
                 else {
                     $li.attr("data_tag", "0");
                     $li.removeClass("active");
                 }
             } else {
                 $li.parent().find("li").each(function () {
                     $(this).attr("isselect", "0");
                     $(this).removeClass("l_zhss");
                 })
                 $li.attr("isselect", "1");
                 $li.addClass("l_zhss");
             }
             searchlist.keysSearch(1);
         });
     	var pcount = $("#pro_total").val();
    	var pindex = $("#pro_index").val();
    	$("#pager").html(pagination(pcount, pindex, 20, "searchlist.keysSearch"));
    },
    keysSearch: function (index,classid) {
    	var searchStr = [],searchvalues=[];
        var maxprice=0.0,minprice=0.0;
        $("#search_list").find("ul").each(function () {
            var $ul = $(this);
            var selected = $ul.find("li[data_tag='1']").length;
            if (selected > 0) {
            	if($ul.attr("data_attrType")==3){
            		$ul.find("li[data_tag='1']").each(function () {
                        $valuetd = $(this);
                        minprice=$valuetd.attr("data_value");
                        maxprice=$valuetd.attr("data-maxvalue");
                    })
            	}else{
                    var searchvalues=[];
                    $ul.find("li[data_tag='1']").each(function () {                    	
                        $valuetd = $(this);
                        if($valuetd.attr("data_type")==1){
                        	searchvalues.push({
                             	type:$valuetd.attr("data_type"),
                                 minvalue:$valuetd.attr("data_value"),
                                 maxvalue:$valuetd.attr("data-maxvalue"),
                                 disvalue:'',
                                 attrtype:$ul.attr("data_attrType"),
                                 Id:null
                             });
                        }else{
                        	 searchvalues.push({
                             	type:$valuetd.attr("data_type"),
                                 minvalue:$valuetd.attr("data_value"),
                                 maxvalue:'',
                                 disvalue:'',
                                 attrtype:$ul.attr("data_attrType"),
                                 Id:null
                             });
                        }
                    })
                    searchStr.push({
                    	type:$ul.attr("data_attrType"),
                    	typeid:	$ul.attr("data_typeID"),
                    	ischeckbox:$ul.attr("data"),
                    	values:searchvalues,
                    	id:null,
                        name:''
                    });
            	}               
            }
        })
        var $select_order = $("#orderby").find("li[isselect='1']").first();
        var orderby = $select_order.attr("data");
        var orderType = $select_order.attr("data_tag");

        var pcount;
        var pindex;
        var psize = 20;
        var keys = $("#keywords").val();
        if(classid != null && classid != "undefined"){
        	 claid = classid;
        }else{
        	claid=0;
        }
        $.ajax({
        	  url: "/pc/products/getprolist",
              type: "Post",
              data: { "cid": claid, "page": index, "size": psize, "searchstr": JSON.stringify(searchStr),"ch": 1,
              	"orderby": orderby, "ordertype": orderType,"minprice":minprice,"maxprice":maxprice,"keywords":keys },
              dataType: "json",
              success: function (data) {
                if (data.code < 0) {
                    alert(data.desc);
                } else {
                    var listdata = {
                        list: data.data
                    }
                    var html = template('pro_list', listdata);
                    $("#productlist").html(html);

                    pcount = data.maxRow;
                    pindex = data.pageIndex;
                    $("#pro_count").html(pcount);
                    //图片切换
                    searchlist.ImgMove();
                    //分页
                    $("#pager").html(pagination(pcount, pindex, psize, "searchlist.keysSearch"));
                }
            },
            error: function () {
            }
        });
    },
    checkValueSelect: function () {
        var $li = $(this);
        var tag = $li.attr("data_tag");
        if (tag == "0") {
            if ($li.parent().attr("data") == "False") {
                $li.parent().children("li").each(function () {
                    if ($li != $(this)) {
                        $(this).attr("data_tag", "0");
                        $(this).removeAttr("style");
                    }
                })
            }
            $li.attr("data_tag", "1");
            $li.css("color", "red");
        } else {
            $li.attr("data_tag", "0");
            $li.removeAttr("style");
            $("li[name='select_class']").css("color","#595959");
        }
        searchlist.keysSearch(1);
    },
    ImgMove: function () {
        $(".l_xdimg").click(function () {
            var spuid = $(this).attr("data-id");
            $("#" + spuid).attr("src", $(this).attr("src"));
        });
    }
}

function search() {
    var strDiv = $("#searchDiv").val();
}

function getKeysStartwithName(callback, event) {
    var name = $("#select_key").val();
    if (event)
        name += String.fromCharCode(event.keyCode);
    if (name != undefined && name != "") {
        $.ajax({
            url: "/pc/products/W_GetKeysList",
            type: "Post",
            data: { "keys": name },
            dataType: "json",
            success: function (data) {

                if (data.code == 0) {
                    var listdata = {
                        list: data.data
                    }
                    var html = template('select_keylist', listdata);

                    if (callback) {
                        callback(html);
                    }
                } else {
//                	 Dalert(data.desc);
                }
            }
        });
    }

}
function GetClassList() {
    var name = $("#select_key").val();
    $.ajax({
        url: "/pc/products/getclasslist",
        type: "Post",
        data: { "keywords": name,"ch":1 },
        dataType: "json",
        success: function (data) {

            if (data.code == 0) {
                var listdata = {
                    list: data.data
                }
                var html = template('flist', listdata);
                $("#select_class").html(html);
            } else {
//            	 Dalert(data.desc);
            }
        }
    });
}

function changeclass(lidom, classid){
	$("li[name='select_class']").css("color","#595959");
	$(lidom).css("color","#FFA100");
	searchlist.keysSearch(1,classid);
}

function selectClass(classid) {
	searchlist.keysSearch(1,classid);
    $.ajax({
        url: "/pc/products/getsearchattrlist",
        type: "Post",
        data: { "claid": classid,"ch":1 },
        dataType: "json",
        success: function (data) {
            if (data.code == 0) {
                var listdata = {
                    list: data.data
                }
                var html = template('specsvalue_list', listdata);
                $("#search_list").html(html);
                $("li[name='search_values']").each(function () {
                    $(this).bind("click", searchlist.checkValueSelect);
                });
            } else {
//                Dalert(data.desc);
            }
        }
    });

}

