//商品配置-搜索属性
var pcount, pindex;
var psize = size_product;
//绑定事件
var Search = {
    bind: function (callback) {
        Search.getFirstClass(callback);
        $("#addnew_type").bind("change", Search.typeChange);
        $("#addnew_submit").bind("click", Search.save);
    },
    //获取商品一级分类
    getFirstClass: function (callback) {
        $.ajax({
            url: "/platform/commodity/GetClassByFatherID",
            type: "Post",
            data: {'fatherid': 0 },
            dataType: "json",
            cache:false,
            async:false,
            success: function (data) {
            	
                if (data.code < 0) {
                    Dalert(data.desc);
                } else {
                    var listdata = {
                        list: data.data
                    }
                    
                    var html = /*'<option value="0" id="defaultfc" selected="">无</option>'+*/template('flist', listdata);
                    $("#fc_select").html(html);
                    if (callback) {
                        callback("fc");
                    }
                }
            },
            error: function () {

            }
        });
    },
    //根据父ID获取分类
    fatherChange: function (callback, fc) {
        var fid = 0;
        if (fc != null) {
            if (fc == "fc") {
                fid = $("#fc_select").val();
            } else {
                fid = $("#sc_select").val();
            }
        }
        $.ajax({
            url: "/platform/commodity/GetClassByFatherID",
            type: "Post",
            data: { 'fatherid': fid },
            dataType: "json",
            cache:false,
            async:false,
            success: function (data) {
                if (data.code < 0) {
                   // Dalert(data.desc);
                } else {
                    var listdata = {
                        list: data.data
                    }
                    if (fc == "fc") {
                        var html = /*'<option value="0" id="defaultfc" selected="">无</option>'+*/template('slist', listdata);
                        $("#sc_select").html(html);
                        if (callback) {
                            callback("sc");
                        }
                    } else {
                        var html = /*'<option value="0" id="defaultfc" selected="">无</option>'+*/template('tlist', listdata);
                        $("#tc_select").html(html);
                        Search.getSearchList(Search.unit);
                    }
                }
            },
            error: function () {

            }
        })
    },
    //获取搜索属性列表
    getSearchList: function (callback) {
    	
        var cid = $("#tc_select").val();
        var name = $("#name_select").val();
        $.ajax({
            url: "/platform/searchattr/querySearchAttribute",
            type: "Post",
            data: { "classid": cid, "page": 1, "size": psize, "name": name },
            dataType: "json",
            cache:false,
            async:false,
            success: function (data) {
                if (data.code < 0) {
                    Dalert(data.desc);
                } else {
                    var listdata = {
                        list: data.data
                    }
                    var html = template('searchlist', listdata);
                    $("#list_title").html(html);
                    if (callback) {
                        callback();
                    }
                    pcount = data.maxRow;
                    pindex = data.pageIndex;

                    //分页
                    $("#pager").html(pagination(pcount, pindex, psize, "Search.getPager"));
                }
            },
            error: function () {

            }
        })
    },
    callback: function (fc) {
        Search.fatherChange(Search.callback, fc);
    },
    //绑定列表事件
    unit: function () {
        $(".del").each(function () {
            $(this).bind("click", Search.del);
        });
        $(".addnew_button").first().bind("click", function () {
            $("#addnew_type").val("2");
            Search.typeChange("0");
            $("#addnew_order").val("0");
            $("#addnew_id").val("0");           
            
            $("input[name='addnew_ischeckbox']").first().attr("checked", "checked");
            $("#addnew_tr").show();
        });
        $(".edit").each(function () {
            $(this).bind("click", Search.unit_shownew);
        });
    },
    unit_shownew: function(){
    	var name = $(this).attr("data_name");
        var type = $(this).attr("data_type");
        var typeid = $(this).attr("data_typeid");
        var id = $(this).attr("data_id");
        var order = $(this).attr("data_order");
        var ischeckbox = $(this).attr("data_radio");
        var usesite=$(this).attr("data_usesite");
        $("#addnew_typeid").val(typeid);
        $("#addnew_typeid option:selected").text(name);
        $("#addnew_type").val(type);
       // Search.typeChange(typeid);
        $("#addnew_order").val(order);
        $("#addnew_id").val(id);
        $("input[name='addnew_ischeckbox']").each(function () {
            if ($(this).val() == ischeckbox)
                $(this).attr("checked", "checked");
        });
        $("input[name='userSites']").each(function () {
            if (usesite.indexOf($(this).val())>=0){
            	$(this).attr("checked", "checked");
            }
        });
        $("#addnew_type").prop("disabled", true);
        $("#addnew_tr").show();
    },
    //根据分类获取规格
    typeChange: function (selectid) {
        var type = $("#addnew_type").val();
        var cid = $("#tc_select").val();
        if (type == "1") {
            $.ajax({
                url: "/platform/searchattr/querySpecsByClassId",
                type: "Post",
                data: { "classID": cid },
                dataType: "json",
                success: function (data) {
                    if (data.code < 0) {
                        Dalert(data.desc);
                    } else {
                        var listdata = {
                            list: data.data
                        }
                        var html = template('tests', listdata);
                        $("#addnew_typeid").html(html);
                    }
                }
            });
        } else if(type == "2"){
            $("#addnew_typeid").html('<option value="0">品牌</option>');
        }else{
        	 $("#addnew_typeid").html('<option value="0">价格</option>');
        }
    },
    //删除
    del: function () {
        if (confirm("确定要删除吗？")) {
            var id = $(this).attr("data");
            $.ajax({
                url: "/platform/searchattr/deleteSearchAttrById",
                type: "Post",
                data: { "id": id },
                dataType: "json",
                success: function (data) {
                    Dalert(data.desc);
                    if (data.code == 0) {
                        location.reload();
                    }
                }
            })
        }
    },
    //添加搜索属性
    save: function () {
    	var stringBuilder = [];
        var id = $("#addnew_id").val();
        var typeid = $("#addnew_typeid").val();
        var cid = $("#tc_select").val();
        var attrtype = $("#addnew_type").val();
        var order = $("#addnew_order").val();
        var name = $("#addnew_typeid option:selected").text();
        var fid = $("#fc_select").val();
        var sid = $("#sc_select").val();
        if(fid != 0) {
        	stringBuilder.push(fid+",");
        }
        if(sid != 0) {
        	stringBuilder.push(sid+",");
        }
        if(cid != 0) {
        	stringBuilder.push(cid+",");
        }
        var obj=document.getElementsByName('userSites');
        var s="";
        for(var i=0; i<obj.length; i++){ 
        	if(obj[i].checked) s+=obj[i].value+','; //如果选中，将value添加到变量s中 
        
        } 
        var fullpath = stringBuilder.join("");
        var ischeckbox = $("input[name='addnew_ischeckbox']:checked").val();
        $.ajax({
            url: "/platform/searchattr/insertSearchAttr",
            type: "Post",
            data: { "fullpath": fullpath, "id": id, "name": name, "attrType": attrtype,"status":0,"classID": cid, "typeID": typeid, "isCheckBox": ischeckbox, "order": order,"userSites":s },
            dataType: "json",
            success: function (data) {
                if (data.code == 0) {
                	 Dalert(data.desc,2000,function(){ location.href='/platform/product/showSearchAttr?fc='+$("#fc_select option:selected").val()+"&sc="+$("#sc_select option:selected").val()+"&tc="+$("#tc_select option:selected").val();
                     });
              }
            }
        })
    },
    
    getPager: function (index) {
        var cid = $("#tc_select").val();
        $.ajax({
        	url: "/platform/searchattr/querySearchAttribute",
            type: "Post",
            data: { "classid": cid, "page": index, "size": psize },
            dataType: "json",
            success: function (data) {
                if (data.code < 0) {
                    Dalert(data.desc);
                } else {
                    var listdata = {
                        list: data.data
                    }
                    var html = template('searchlist', listdata);
                    $("#list_title").html(html);
                    pcount = data.maxRow;
                    pindex = data.pageIndex;

                    //分页
                    $("#pager").html(pagination(pcount, pindex, psize, "Search.getPager"));
                }
            },
            error: function () {

            }
        })
    },
   
}
function setStatus (id,status) {
	 $.ajax({
        url: "/platform/searchattr/updateStatus",
        type: "Post",
        data: { id: id, status:status },
        dataType: "json",
        success: function (data) {
       	 if (data.code < 0){
       		 Dalert(data.desc);
       	 }else{
       		Search.bind(Search.callback);
       	 }
            
        },
        error: function () {
       	 
        }
    });
}