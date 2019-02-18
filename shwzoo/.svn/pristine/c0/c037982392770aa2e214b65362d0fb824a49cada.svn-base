//商品分类公共调用
var Class = {
    unit: function (callback) {
        Class.getFirstClass(callback); //获取一级分类
    }, 
    getFirstClass: function (callback) {
        $.ajax({
            url: "/platform/commodity/GetClassByFatherID",
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
    	
    	
        var fid = $('#firstID option:selected').val();
      
        if (value == "fc") {
            fid = $("#firstID").val();
        }
        else if (value == "sc") {
            fid = $("#secondID").val();
        }
        $.ajax({
            url: "/platform/commodity/GetClassByFatherID",
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
                        var html = '<option value="-1" id="defaultsc" selected>无</option>' + template('slist', listdata);
                        $("#secondID").html(html);
                        if (callback) {
                            callback("sc");
                        }
                    } 
                    if (value == "sc") {
                            var html = '<option value="-1" id="defaulttc" selected>无</option>' + template('tlist', listdata);
                            $("#thirdID").html(html);
                            getthird();
                    }

                }
            },
            error: function () {

            }
        })
    },
    callback: function (value) {   //分类回调
        var fid = $('#fid').val(); 
        var sid = $('#sid').val(); 
        var tid = $('#tid').val();;
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
    var  tid =$('#tid').val();
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