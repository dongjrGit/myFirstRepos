var ch = 3;
var feedback = {
    //添加反馈成功
    addfeenback: function (content,type,mobile) {
        $.ajax(({
            type: "post",
            url: "/api/wap/feedback/addFeedBack",
            dataType: "json",
            data: { content: content, type: type,mobile:mobile ,ch:ch},
            success: function (rsl) {
                if (rsl.code == 0) {
                	alert("添加反馈成功");
                	window.location.href = "/wap/userinfo/homepage?ch=3";
                }
                else {
                	alert(rsl.desc);
                }
            },
            error: function (e) {

            }
        }));
    }
}