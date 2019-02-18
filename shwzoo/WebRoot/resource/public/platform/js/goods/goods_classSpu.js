//获取商品分类及分类下的商品 公共调用

var action = "";  //0-添加 1-修改
var type = 0;
var Class = {
    unit: function (Act, callback) {
        action = Act;
        Class.getFirstClass(callback);
    },
    getFirstClass: function (callback) {
        $.ajax({
            url: "/Class/P_GetFirstClass",
            type: "Post",
            data: {},
            dataType: "json",
            success: function (data) {
                if (data.Code < 0) {
                    Dalert(data.Desc);
                } else {
                    var listdata = {
                        list: data.Data
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
    firstChange: function (callback, value) {
        var fid = $("#firstID").val();

        if (value == "fc") {
            fid = $("#firstID").val();
        }
        else if (value == "sc") {
            fid = $("#secondID").val();
        }
        $.ajax({
            url: "/Class/P_GetChildrenByFatherID",
            type: "Post",
            data: { "fatherID": fid },
            dataType: "json",
            success: function (data) {
                if (data.Code < 0) {
                    Dalert(data.Desc);
                } else {
                    var listdata = {
                        list: data.Data
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
                            var html = '<option value="0" id="defaulttc" selected>无</option>' + template('tlist', listdata);
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
    callback: function (value) {
        var fid = $("#fid").val(), sid = $("#sid").val(), tid = $("#tid").val();
        if (action == "1" && type==0) {
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
        }
        Class.firstChange(Class.callback, value);

    },
    getSPU: function () {
        cid=$("#thirdID").val();
        //根据分类ID获取商品列表
        if (cid > 0) {
            $.ajax({
                url: "/Product/P_GetSPUListByQuery",
                type: "Post",
                data: { "page": 1, "size": 100, "cid": cid },
                dataType: "json",
                success: function (data) {
                    if (data.Code < 0) {
                        Dalert(data.Desc);
                    } else {
                        var listdata = {
                            list: data.Data
                        }
                        var html = '<option value="0" id="defaultspu" selected>无</option>' + template('spulist', listdata);
                        $("#spuid").html(html);
                        if (action == "1" && data.Data.length > 0 && type == 0) { getSPUsel();}
                        
                    }
                },
                error: function () {
                    //alert("数据加载失败");
                }
            });
        }
    }
}
function getSPUsel() {
    var Spuid = $("#Spu_ID").val();
    if (Spuid > 0) {
        $("#spuid option").each(function () {
            if ($(this).val() == Spuid) {
                $(this).attr("selected", "selected");
                type += 1;
            } else {
                $(this).removeAttr("selected");

            }

        });
    }

}
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
        Class.getSPU();
    }

}