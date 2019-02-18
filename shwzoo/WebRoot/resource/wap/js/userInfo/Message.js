//消息列表
var ch=3;
var Message = {
    //uID:用户id，sta：状态（0已读，1未读），index：起始页
    deletemessage: function (id) {
        $.ajax({
            url: "/api/wap/messages/deleteMessageById",
            type: "post",
            data: {id:id,ch:ch},
            dataType: "json",
            success: function (data) {            	
                if (data.code == 0) {
                	alert(data.desc);
                	location.href="/wap/messages/queryMessages";
                    }else{alert(data.desc);}
                
                    
                },
            error: function () {
            	alert("您还没有收到消息哦");
            }
        })
    }
}
