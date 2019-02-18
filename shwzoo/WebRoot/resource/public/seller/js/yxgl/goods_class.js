/**
 * 获取分类数据
 */
var Class = {
    unit: function (callback) {
        Class.getFirstClass(callback); //获取一级分类
    }, 
    getFirstClass: function (callback) {
        $.ajax({
            url: "/seller/shopcategory/getClassByFatherID",
            type: "post",
            data: { 'fatherid': 0 },
            dataType: "json",
            success: function (data) {
                if (data.code < 0) {
                    Dalert(data.desc);
                } else {
                    var listdata = {
                        list: data.data
                    }
                    var html = template('flist', listdata);
                    $("#defaultfc").after(html);
                    if (callback) {
                        callback("fc");
                    }
                }
            },
            error: function () {

            }
        });
    },
    firstChange: function (callback, value) {  //根据父ID获取分类
        var fid = $("#firstID").val();
      
        if (value == "fc") {
            fid = $("#firstID").val();
        }
        else if (value == "sc") {
            fid = $("#secondID").val();
        }
        $.ajax({
            url: "/seller/shopcategory/getClassByFatherID",
            type: "post",
            data: { 'fatherid': fid },
            dataType: "json",
            success: function (data) {
                if (data.code < 0) {
                    Dalert(data.desc);
                } else {
                    var listdata = {
                        list: data.data
                    }
                    if (value == "fc") {
                        var html = '<option value="0" id="defaultsc" selected>无</option>' + template('slist', listdata);
                        $("#secondID").html(html);
                        if (callback) {
                            callback("sc");
                        }
                    }
                    else {
                        if (value == "sc") {
                            var html = '<option value="" id="defaulttc" selected>无</option>' + template('tlist', listdata);
                            $("#thirdID").html(html);
                            getthird();
                        }
                    }

                }
            },
            error: function () {

            }
        })
    },
    callback: function (value) {   //分类回调
        var fid = $("#fid").val(), sid = $("#sid").val(); tid = $("#tid").val();
        if (value == "fc" && fid > 0) {
            $("#firstID option").each(function () {
                if ($(this).val() == fid) {
                    $(this).attr("selected", "selected");
                } else {
                    $(this).removeAttr("selected");
                }
            });
        }
        if (value == "sc" && sid > 0) {
            $("#secondID option").each(function () {
                if ($(this).val() == sid) {
                    $(this).attr("selected", "selected");
                } else {
                    $(this).removeAttr("selected");
                }
            });
        }
        Class.firstChange(Class.callback, value);
    }
}
//获取第三级分类
function getthird() {
    tid = $("#tid").val();
    if (tid > 0) {
        $("#thirdID option").each(function () {
            if ($(this).val() == tid) {
                $(this).attr("selected", "selected");
            } else {
                $(this).removeAttr("selected");
            }
        })
    }

}