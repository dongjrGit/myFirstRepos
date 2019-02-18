<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title></title>
    <script src="js/jquery-1.8.2.min.js"></script>
    <script src="js/jquery.form.js"></script>
    <script src="js/AjaxFileUploaderV2.1/ajaxfileupload.js"></script>
</head>
<body>
    <form class="testform">
        <label>文件1</label><input type="file" name="pics" />
        <label>文件2</label><input type="file" name="pics" />
        <input type="button" value="多文件上传" class="filesupload" /><br />
        <div class="url"></div>
        <img class="show2" width="200" />
        <img class="show3" width="200" />
    </form>
    <br />
    <hr />
    <div class="testfile">
        <label>文件1</label><input type="file" name="pics" id="singlefile" />
        <input type="button" value="单文件上传" class="singleupload" /><br /><div class="url1"></div>
        <img class="show1" width="200" />
    </div>
</body>
</html>
<script>
  
    $(function () {
        $(".singleupload").bind("click", function (e) {
            $.ajaxFileUpload({
                url: "/app/api/img/upload",
                secureuri: false,
                fileElementId: 'singlefile',
                dataType: "json",
                //relationtype: 模块（会员头像( 0), 店铺(1), 产品 ( 2), 其他 (20);） 可以自由增加
                data: { relationtype:2 },
                type: 'POST',
                success: function (result) {
                    //  alert(JSON.stringify(result));
                    $(".url1").html(JSON.stringify(result));
                    if (result.Code == 0)
                        $(".show1").attr("src", result.Data[0]);
                    else
                        $(".show1").attr("src", "");
                    //TODO 结束正在加载中
                },
                error: function (e) {
                    alert(JSON.stringify(e));
                    //TODO 结束正在加载中
                }
            });
        });

        $(".filesupload").bind("click", function (e) {
            $(".testform").ajaxSubmit({
                url: "/app/api/img/upload",
                dataType: "json",
                type: 'POST',
                //relationtype: 模块（会员头像( 0), 店铺(1), 产品 ( 2), 其他 (20);）可以自由增加
              data: { relationtype: 2 },
                success: function (result) {
                    //  alert(JSON.stringify(result));
                    $(".url").html(JSON.stringify(result));
                    if (result.Code == 0) {
                        $(".show2").attr("src", result.Data[0]); $(".show3").attr("src", result.Data[1]);
                    } else {
                        $(".show2").attr("src", ""); $(".show3").attr("src", "");
                    }
                    //TODO 结束正在加载中
                },
                error: function (e) {
                    alert(JSON.stringify(e));
                    //TODO 结束正在加载中
                }
            });
        });
    });


</script>