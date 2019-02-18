//查看信息详情
$(document).ready(function () {
    var feedBackid = GetQueryStringByName("id");
    GetForm(feedBackid);
    $("#backBtn").click(function () {
        location.href = "/platform/member/showMemberFeedBack_list";
    })
})

//根据id获取详情
function GetForm(feedBackid) {
    $.ajax(({
        type: "post",
        url: "/platform/memberfeedback/queryFeedBackById",
        dataType: "json",
        data: { id: feedBackid },
        success: function (rsl) {
            if (rsl.code == 0) {
                var m = rsl.data;
             //   $("#text_title").val(m.title);
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
