//添加底部导航
$(document).ready(function () {
    var id = GetQueryStringByName("id");
    if (id > 0) {
        $("#contitle").html("编辑文章分类");
    } else {
        $("#contitle").html("添加文章分类");
    }
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
    	var type=$("#ctype").val();
        location.href = "/platform/news/clist?type="+type;
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
              name: "required"
          },
          message: {
        	  name: { required: "类别名称不可为空" }
          }
      });
  }
});

//返回链接
function backNavClass() {
	var type=$("#ctype").val();
    location.href = "/platform/news/clist?type="+type;
}

//添加 
function add() {
    $("#savebtn").hide();
    $.ajax({
        type: "post",
        url: "/platform/news/addNewsClass",
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
        url: "/platform/news/editNewsClass",
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
