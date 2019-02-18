//分类链接商品规格
var pcount;
var pindex;
var psize = size_product;
var cid; //商品分类id
//var tid;
//根据规格类型ID,商品分类ID获取商品规格分页列表数据
var specs = {
    bind: function (index) {
    	cid = $("#claid").val();
        $.ajax({
            url: "/platform/commodity/getProSpecsByClassId",
            type: "Post",
            data: { 'classid': cid, 'page': index, 'size': psize },
            dataType: "json",
            success: function (data) {
            	
                if (data.code < 0) {
                    Dalert(data.desc);
                } else {
                    var listdata = {

                        list: data.data
                    }

                    var html = template('specslist', listdata);

                    //html 填充
                    $("#datalist").html(html);
                    //加载table样式：改变偶数行背景色，鼠标移动时背景色
                    init();
                    pcount = data.maxRow;
                    pindex = data.pageIndex;
                    specs.unit();
                    //分页
                    $("#pager").html(pagination(pcount, pindex, psize, "specs.bind"));
                }
            },
            error: function () {

            }
        });
    },
    unit:function(){
    	$(".del").each(function() {
			$(this).bind("click", specs.del);
		});
		$(".setstatus").each(function() {
			$(this).bind("click", specs.setstatus);
		});
		$(".setentry").each(function() {
			$(this).bind("click", specs.setentry);
		});
    },
    del:function(){
    	var specsid=$(this).attr("data-id");
    	 if (confirm('确定将此记录删除?')) {
    	        $.ajax({
    	            url: "/platform/commodity/deleteProductSpecsById",
    	            type: "Post",
    	            data: { 'id': specsid },
    	            dataType: "json",
    	            success: function (data) {
    	                if (data.code < 0) {
    	                    Dalert(data.desc);
    	                }
    	                else {
    	                	specs.bind(pindex);
    	                }
    	            },
    	            error: function () {
    	                Dalert("删除失败");
    	            }
    	        });
    	    }
    },
    setstatus:function(){
    	var id = $(this).attr("data-id");
    	var status = $(this).attr("data-status");
    	  $.ajax({
    	        url: "/platform/commodity/UpdateStatus",
    	        type: "Post",
    	        data: { 'id': id, 'status': status },
    	        dataType: "json",
    	        success: function (data) {
    	            if (data.code < 0) { Dalert(data.desc); }
    	            else {
    	                var td_html = "", a_html = "";
    	                if (status == 0) {
    	                    td_html = "启用";
    	                    a_html = "<a href='javascript:void(0);' class='setstatus' data-id='"+id+"' class='setstatus' data-status='1' ><span class='shenlan'>禁用</span></a>";
    	                }
    	                else {
    	                    td_html = "禁用";
    	                    a_html = "<a href='javascript:void(0);' class='setstatus' data-id='"+id+"' class='setstatus' data-status='0'><span class='shenlan'>启用</span></a>";
    	                }
    	                $("#td_" + id).html(td_html);
    	                $("#a_" + id).html(a_html);
    	                $(".setstatus").each(function() {
    	        			$(this).bind("click", specs.setstatus);
    	        		});
    	            }
    	        },
    	        error: function () {
    	            Dalert("修改规格状态失败");
    	        }
    	    });
    	
    },
    setentry:function(){
    	var id = $(this).attr("data-id");
    	var isentry = $(this).attr("data-isentry");
    	 $.ajax({
    	        url: "/platform/commodity/updateIsEntry",
    	        type: "Post",
    	        data: { 'id': id, 'isentry': isentry },
    	        dataType: "json",
    	        success: function (data) {
    	            if (data.code < 0) { Dalert(data.desc); }
    	            else {
    	                var td_html = "", a_html = "";;
    	                if (isentry == 'false') {
    	                    td_html = "不可输入";
    	                    a_html = "<a href='javascript:void(0);' data-id='"+id+"' data-isentry='true' class='setentry'><span class='shenlan'>可输入</span></a>";
    	                    $("#IsEntryShow_" + id).attr("style", "display:inline-block");
    	                }
    	                else {
    	                    td_html = "可输入";
    	                    a_html = "<a href='javascript:void(0);' data-id='"+id+"' data-isentry='false' class='setentry' ><span class='shenlan'>不可输入</span></a>";
    	                    $("#IsEntryShow_" + id).attr("style", "display:none");
    	                }
    	                $("#istd_" + id).html(td_html);
    	                $("#is_" + id).html(a_html);
    	                $(".setentry").each(function() {
    	        			$(this).bind("click", specs.setentry);
    	        		});
    	            }
    	        },
    	        error: function () {
    	            Dalert("修改规格是否可输入失败");
    	        }
    	    });
    }
}

//页面加载触发事件
$(function () {
    $("input[name=btnadd]").bind("click", specsadd);
    $("input[name=reback]").bind("click", function () { location.href = "goods_class"; });
    $("input[name=Save]").bind("click", Save);
});

//添加跳转
function specsadd() {
    self.location = "class_specsadd?cid=" + cid;
}

//排序
function setOrder(specsid, ob) {
    var obtext = $("#" + ob).val();
    $.ajax({
        url: "/platform/commodity/UpdateOrderBy",
        type: "Post",
        data: { 'id': specsid, 'orderby': obtext },
        dataType: "json",
        success: function (data) {
            Dalert(data.desc);
        },
        error: function () {
            Dalert("修改规格排序失败");
        }
    });
}
//编辑规格属性值
function getvalues(specsname, specsid) {
    var d = dialog({
        title: specsname + '的属性值',
        url: 'specs_values?specsid=' + specsid,
        width: 560,
        height: 600
    });

    d.show();

}
//保存
function Save() {
    var action = $("#specs_action").val();
    var location = "";
    if (formSubmit()) {
//        var chks = document.getElementsByName("displayL");
//        for (var i = 0; i < chks.length; i++) {
//            if (chks[i].checked) {
//                if (location == "") {
//                    location = chks[i].value;
//                }
//                else {
//                    location += "," + chks[i].value;
//                }
//            }
//        }
        //防止重复提交 点击保存后隐藏按钮
        $("input[name='Save']").hide();
        $.ajax({
            url: "/platform/commodity/" + action,
            type: "Post",
            data: $("#form").serialize(),
            dataType: "json",
            success: function (data) {
                if (data.code == 0) {
                    Dalert("保存成功！","",backhref);
                }
                else {
                    $("input[name='Save']").show();
                    Dalert(data.desc);
                }
            },
            error: function () {
            }
        });
    }
}
//保存前验证
function check() {
    return $("#form").validate({
        rules: {
            name: {
                required: true,
                rangelength: [1, 100]
            },
            orderby: {
                required: true,
                digits: true
            }
        },
        message: {
            name: {
                required: "规格名称不可为空"
            },
            orderby: {
                required: "排序不能为空",
                digits: "必须输入整数"
            }
        }
    });
}
function formSubmit() {
    if (check().form()) {
        if (isNouNull($("#firstID").val())) {
            $("#ClassID").val($("#firstID").val());
            if (isNouNull($("#secondID").val())) {
            	 $("#ClassID").val($("#secondID").val());
            	 if (isNouNull($("#thirdID").val())) {
            		 $("#ClassID").val($("#thirdID").val());
				}
			}
        }
        else {
            Dalert("请选择商品分类");
            $("#thirdID").focus();
            return false;
        }
        if ($("#ClassID").val() == "0") {
            return false;
        }
        var location = "";
        var chks = document.getElementsByName("displayL");
        for (var i = 0; i < chks.length; i++) {
            if (chks[i].checked) {
                if (location == "") {
                    location = chks[i].value;
                }
                else {
                    location += "," + chks[i].value;
                }
            }
        }
        $("#displaylocation").val(location);
        if (location == "") { $("#disloca").show(); return false; }
        return true;
    }

}

function isNouNull(value){
	if (value!=null&&value!=""&&value!=0&&value!="0") {
		return true;
	}else{
		return false;
	}
}

//获取商品规格类型
function initlist() {
    var strselect = "";
    cid = $("#fid").val();
    if ($("#sid").val()!="") {
		cid=$("#sid").val();
		if ($("#tid").val()!="") {
			cid=$("#tid").val();
		}
	}
    var selectvalue = $("#typeid").val();
    $.ajax({
        url: "/platform/Specstype/getTypeByClassID",
        type: "Post",
        dataType: "json",
        data: { "classid": $("#fid").val(),"classid2":$("#sid").val(),"classid3":$("#tid").val() },
        success: function (data) {
            if (data.code < 0) {
                Dalert(data.desc);
            } else {
            	 var listdata = {
                         list: data.data
                     }
            	 var html = '<option value="0"></option>' + template('tlist', listdata);
                 $("#TypeID").html(html);
                 $("#TypeID option").each(function () {
                     if ($(this).val() == selectvalue) {
                         $(this).attr("selected", "selected");
                     } else {
                         $(this).removeAttr("selected");
                     }
                 });
            }
        },
        error: function () {
        }
    });
}
//返回刷新
function backhref() {
    window.location.href = '/platform/product/class_specslist?cid=' + $("#ClassID").val();
}