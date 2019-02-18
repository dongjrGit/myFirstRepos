//查看信息详情
$(document).ready(function () {
    var receiveID = GetQueryStringByName("id");
    GetForm(receiveID);
    $("#backBtn").click(function () {
        location.href = "/platform/member/showp_Message";
    })
})

//根据id获取详情
function GetForm(mid) {
    $.ajax(({
        type: "post",
        url: "/platform/message/queryMesRecById",
        dataType: "json",
        data: { id: mid },
        success: function (rsl) {
            if (rsl.code == 0) {
                var m = rsl.data;
                $("#text_title").val(m.title);
                $("#text_content").val(m.content);
            }
            else {
                //Dalert(rsl.Desc);
            }
        },
        error: function (es) {
            
        }
    }));
}
