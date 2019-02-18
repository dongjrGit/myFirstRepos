//专题-分类管理

var pcount;
var pindex;
var psize = size_common;
//fp 商品分类全路径
var fp = "", id = "";
//页面加载数据
var proTclass = {
    bind: function (protid, index) {
        var datahtml = "";
        $.ajax({
            url: "/ProductTheme/P_GetProClassList",
            type: "Post",
            data: { pageindex: index, pagesize: psize, proTId: protid },
            dataType: "json",
            success: function (data) {
                if (data.Code < 0) {
                    Dalert(data.Desc);
                } else {
                    var len = data.Data.length;
                    datahtml += "<tr>";
                    datahtml += "<th width='20%'>专题名称</th>";
                    datahtml += "<th width='20%'>分类名称</th>";
                    datahtml += "<th width='40%'>分类全路径</th>";
                    datahtml += "<th>操作</th>";
                    datahtml += "</tr>";
                    if (len > 0) {
                        for (var i = 0; i < len; i++) {
                            datahtml += "<tr> ";
                            datahtml += "<td>" + data.Data[i].Name + "</td> ";
                            datahtml += "<td>" + data.Data[i].Cname + "</td> ";
                            GetFullPath(data.Data[i].FullPath);
                            //if (fp == "") { fp = data.Data[i].Cname; }
                            //else {
                            //    fp += "->" + data.Data[i].Cname;
                            //}
                            datahtml += "<td>" + fp + "</td> ";
                            fp = "";
                            datahtml += "<td><a href='#' onclick='Del(" + data.Data[i].ID + ")'><span class='shenlan'>删除</span></a></td> ";
                            datahtml += "</tr> ";
                        }
                    }

                    $(".data_list").html(datahtml);
                    //加载table样式：改变偶数行背景色，鼠标移动时背景色
                    init();
                    id = protid;
                    pcount = data.MaxRow;
                    pindex = data.PageIndex;

                    //分页
                    $("#pager").html(pagination(pcount, pindex, psize, "pagelist"));
                }
            },
            error: function () {
                //Dalert("数据加载失败");
            }
        });
    }
}
//分页回调函数
function pagelist(index) {
    proTclass.bind(id, index);
}
//删除
function Del(ptid) {

    if (confirm('确定将此记录删除?')) {
        $.ajax({
            url: "/ProductTheme/P_DelProTClass",
            type: "Post",
            data: { id: ptid },
            dataType: "json",
            success: function (data) {
                if (data.Code < 0) {
                    Dalert(data.Desc);
                }
                else {
                    pagelist(pindex);
                }
            },
            error: function () {
                Dalert("删除失败");
            }
        });
    }
}
//获取分类全路径
function GetFullPath(fullpath) {

    if (fullpath == null || fullpath == undefined) { fp = ""; }
    else {
        $.ajax({
            url: "/Class/P_GetClaFullPath",
            type: "Post",
            data: { "fullpath": fullpath },
            dataType: "json",
            async: false,
            success: function (data) {
                if (data.Code < 0) {
                    Dalert(data.Desc);
                }
                else {

                    fp = data.Data;
                }
            },
            error: function () {

            }
        });
    }
}

$(function () {
    $("input[name=Save]").bind("click", AddProTClass);
})
//添加分类
function AddProTClass() {
    if (formSubmit()) {
        //防止重复提交 点击保存后隐藏按钮
        $("input[name='Save']").hide();
        $.ajax({
            url: "/ProductTheme/P_AddProTClass",
            type: "Post",
            data: { "ClassID": $("#ClassID").val(), "proTId": $("input[name=proTId]").val() },
            dataType: "json",
            success: function (data) {
                if (data.Code < 0) {
                    $("input[name='Save']").show();
                    Dalert(data.Desc);
                }
                else {
                    Dalert(data.Desc, "", backhref);
                    //window.location.href = '/Platform/sp/goods_spgl_syzt';
                }
            },
            error: function () {
            }
        });
    }
}

function formSubmit() {
    if ($("#thirdID").val() != "0") {
        $("#ClassID").val($("#thirdID").val());
    }
    else {
        if ($("#secondID").val() != "0") {
            $("#ClassID").val($("#secondID").val());
        }
        else {
            if ($("#firstID").val() != "0") {
                $("#ClassID").val($("#firstID").val());
            }
            else {
                Dalert("请选择商品分类");
                $("#firstID").focus();
                return false
            }
        }
    }
    if ($("#ClassID").val() != "0") {
        return true;
    }
}
//返回刷新
function backhref() {
    window.location.href = '/Platform/sp/goods_spgl_syzt';
}