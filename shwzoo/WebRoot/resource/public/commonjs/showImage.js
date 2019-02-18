
//点击小图查看大图
function Show(id,imgurl) {
    $("#" + id + " img").attr("src", imgurl);
    $("#" + id).show();
}

function ImageJump(ImageUrl, JumpUrl) {
    //location.href = JumpUrl + "?imgUrl=" + ImageUrl;
    //window.open(JumpUrl + "?imgUrl=" + ImageUrl);
    window.open("" + JumpUrl + "?imgUrl=" + ImageUrl + "")
}

function imgJump(ImageUrl) {
    window.open(ImageUrl)
}