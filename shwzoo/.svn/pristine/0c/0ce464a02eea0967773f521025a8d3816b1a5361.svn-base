//返回绑定
var d = []; var sUrl;
var reUrl = {
    load: function (sType) {
        sUrl = sType;
    }
}
var order = {
    updPwd: function () {
        //修改密码
        d = dialog({
            title: '修改密码',
            content: "<span>当前密码：</span><input type='password' name='oldpwd' style='width:150px;' /><p style='height: 10px;'></p><span style='min-width: 60px;display:inline-block;'>新密码：</span><input type='password' name='newPwd' style='width:150px;' /><p style='height: 10px;'></p><span>确认密码：</span><input type='password' name='newpwdagin' style='width:150px;' /><p style='height: 10px;'></p>",
            width: 250,
            okValue: '确认',
            ok: function () {
                $.ajax({
                    url: "/platform/accounts/updatPwd",
                    type: "Post",
                    data: { "oldpwd": $("input[name=oldpwd]").val(), 
                    	"newPwd": $("input[name=newPwd]").val(), 
                    	"renewPwd": $("input[name=newpwdagin]").val()},
                    dataType: "json",
                    success: function (data) {
                        if (data.code < 0) {
                            Dalert(data.desc,10000);
                        } else {
                            Dalert(data.desc,3000);
                            location.reload();
                            closeDialog();
                        }
                    },
                    error: function () {

                    }
                });
                return false;
            },
            cancelValue: '取消',
            cancel: function () { }
            //modal: true, //蒙层
        });
        d.show();
    },

}

//关闭弹出框
function closeDialog() { d.close(); }

function refresh() {
    location.reload();
}

