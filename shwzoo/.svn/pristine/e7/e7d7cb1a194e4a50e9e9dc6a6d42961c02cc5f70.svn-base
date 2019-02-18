//站内信查看
$(document).ready(function () {
    var id = GetQueryStringByName("id");

    GetByID(id);
    $("#backBtn").click(function () {
        location.href = "/Member/KhFwmemb/MessageList";
    })

});
//根据信息id获取信息 id:信息id
function GetByID(id) {
    $.ajax({
        url: "/Message/GetMesByID",
        type: "post",
        data: { id: id },
        dataType: "json",
        success: function (data) {
            if (data.Code < 0) {
                //alert(data.Desc);
            } else {
                var af = data.Data;
                $("#sendUser").attr("value", af.SendUserName);

                $("#msContent").attr("value", af.Content);
            }

        }
    });
}