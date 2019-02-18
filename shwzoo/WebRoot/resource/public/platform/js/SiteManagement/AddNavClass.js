//添加底部导航
$(document).ready(function () {
    getFirstClass();

    var id = GetQueryStringByName("id");
    if (id > 0) {
        $("#contitle").html("编辑文章分类");
    } else {
        $("#contitle").html("添加文章分类");
    }
    getForm(id)
    //添加
    function save() {
        if (id > 0) {
            edit(id);
        } else {
            add();
        }
    };
    //返回
    $("#backbtn").click(function () {
        location.href = "/platform/zd/showArticleClass";
    });
    
    //保存
    $("#savebtn").click(function () {
        if (check().form()) {
            save();
        }
    });
    

  //验证表单
  function check() {
      return $("#form").validate({
          rules: {
              ClassName: "required"
          },
          message: {
              ClassName: { required: "类别名称不可为空" }
          }
      });
  }
});

//返回链接
function backNavClass() {
    location.href = "/platform/zd/showArticleClass";
}
//获取表单
function getForm(id) {
    $.ajax({
        type: "post",
        url: "/platform/page/queryArticleClassById",
        dataType: "json",
        data: {
            id: id
        },
        success: function (rsl) {
            if (rsl.code == 0) {
            	 $("#ClassName").attr("value", rsl.data.classname);
                 if (rsl.data.parentid == 0) {
                	 //$("#firstID").val(rsl.data.id);
                     $("#firstID").get(0).selectedIndex = 0
                 } else {
                     $("#firstID option").each(function () {
                    	 $("#firstID").val(rsl.data.parentid);
                         //if ($(this).text() == rsl.data.parentid) {
                         //    $(this).attr("selected", true);
                        //     return false;
                       // }
                     });
                 }
                 //getFirstClass(getFirstID, rsl.data.id)

                 $("#Path").attr("value", rsl.data.path);
                 $("#Order").attr("value", rsl.data.sort);
                 $("#navtype").val(rsl.data.navtype);
            }
        },
        error: function (e) {
            //Dalert(e.statusText);
        }
    });
}
//获取首项分类
function getFirstClass() {
    $.ajax({
        url: "/platform/page/queryfirstclass",
        type: "Post",
        data: {},
        dataType: "json",
        success: function (data) {
            if (data.code < 0) {
                // alert(data.Desc);
            } else {
                var listdata = {
                    list: data.data
                }
                var html = template('flist', listdata);
                $("#defaultfc").after(html);
            }
        },
        error: function () {

        }
    });
}
//添加 
function add() {
    $("#savebtn").hide();
    $.ajax({
        type: "post",
        url: "/platform/page/addNavClass",
        dataType: "json",
        data: $("#form").serialize(),
        success: function (rsl) {
            if (rsl.code == 0) {
                Dalert(rsl.desc, "", backNavClass);
            } else {
                Dalert(rsl.desc); $("#savebtn").show();
            };
        },
        error: function (e) {
            //Dalert(e.statusText);

        }
    });
}
//编辑 id
function edit() {
    $.ajax({
        type: "post",
        url: "/platform/page/editNavClass",
        dataType: "json",
        data:$("#form").serialize(),
        success: function (rsl) {
            if (rsl.code == 0) {
                Dalert(rsl.desc, "", backNavClass);
            } else {
                Dalert(rsl.desc);
            }
        },
        error: function (e) {
            //Dalert(e.statusText);
        }
    });
}
/*function getFirstID(id) {
    var pid = -1;
    $.ajax({
        url: "/platform/page/getFirstId",
        async: false,
        type: "Post",
        data: { childid: id },
        dataType: "json",
        success: function (data) {
            if (data.Code < 0) {
                // alert(data.Desc);
            } else {
                pid = data.Data
                if (pid == 0) {
                    $("#firstID").attr("value", id);
                    firstChange(pid);
                }
                else {
                    $("#firstID").attr("value", pid);
                    firstChange(pid, id);
                }
            }
        }
    });
    return pid;
}

//根据首项获取分类子项 fid：首相id，chilid：子项id
function firstChange(fid, chilid) {
    if (fid == null || fid == "" || fid == undefined) {
        fid = $("#firstID").val();
    }
    if (fid != -1) {
        $.ajax({
            url: "/platform/page/querychildbyfid",
            type: "Post",
            data: { "fatherID": fid },
            dataType: "json",
            success: function (data) {
                if (data.code < 0) {
                    //Dalert(data.Desc);
                } else {
                    var listdata = {
                        list: data.data
                    }
                    var html = '<option value="-1" >选择类型</option>' + template('slist', listdata);
                    $("#childid").html(html);
                }
                if (chilid != null && fid != "" && fid != undefined) {
                    $("#childid").attr("value", chilid);
                }
            },
            error: function () {

            }
        })
    } else {
        var html = '<option value="-1"  selected>选择类型</option>';
        $("#childid").html(html);
    }
}*/
