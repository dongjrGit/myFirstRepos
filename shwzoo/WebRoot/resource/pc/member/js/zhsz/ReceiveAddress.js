//收货地址
$(document).ready(function () {
	
    Address.bind();//地址列表

    
    //添加
    $("#addAddress").click(function () {
    	
        var count = Number($("#adCount").text());
        if (count <= 20) {
            var id = $(this).parent().find("input").val();
            location.href = "/member/userInfo/addRecAdr.html";
        } else {
            alert("最多只能创建20个地址");s
        }
    });
  
   
    //删除
    $(".deleteAd").on("click", function () {
    
        var id = $(this).parent().find("input").val();
       // ConfirmShow("确定要删除收货地址吗？", del, id);//confirm弹出框 js/dialogShow.js
        if (confirm("确定要删除收货地址吗？")) {
            del(id);
        }
    });
    

    
   
    //编辑
    $(".editAd").on("click", function () {
        //$(".r_loginhui").attr("style", "display:block");
        var id = $(this).parent().find("input").val();
        location.href = "/member/userInfo/addRecAdr.html?dd=" + id;
    })
    
    //设为默认
    $(".defaultAd").on("click", function () {

        if ($(this).text() != "默认地址") {
            var id = $(this).parent().find("input").val();
            $.ajax(({
                type: "post",
                url: "/pc/user/setDefault",
                dataType: "json",
                data: { "id": id,
                	    "ch":1},
                success: function (rsl) {
                    if (rsl.code == 0) {    
                        Address.bind();
                    } else {
                        alert(rsl.desc);
                    }
                }
            }));
        }
    })
    //删除 id:地址id
    function del(id) {
        $.ajax({
            type: "post",
            url: "/pc/user/delReceiverAddr",
            dataType: "json",
            data: {
                "id": id
            },
            success: function (rsl) {
                alert(rsl.desc); Address.bind();
            },
            error: function (e) {
                //Dalert(e.statusText);
            }
        })
    }
    
   

});

function shanchu(id){
	 //var id = $(this).parent().find("input").val();
     // ConfirmShow("确定要删除收货地址吗？", del, id);//confirm弹出框 js/dialogShow.js
      if (confirm("确定要删除收货地址吗？")) {
    	  $.ajax({
              type: "post",
              url: "/pc/user/delReceiverAddr",
              dataType: "json",
              data: {
                  "id": id
              },
              success: function (rsl) {
                  alert(rsl.desc); Address.bind();
              },
              error: function (e) {
                  //Dalert(e.statusText);
              }
          })
      }
	
}

function setDefault(id){
	if ($(this).text() != "默认地址") {
       // var id = $(this).parent().find("input").val();
        $.ajax(({
            type: "post",
            url: "/pc/user/setDefault",
            dataType: "json",
            data: { "id": id,
            	    "ch":1},
            success: function (rsl) {
                if (rsl.code == 0) {       
                	Dalert("设置成功！")
                    Address.bind();
                } else {
                    alert(rsl.desc);
                }
            }
        }));
    }
	
}



function edit(id){
	location.href = "/member/userInfo/addRecAdr.html?dd=" + id;
	
}

var Address = {
    //地址列表
    bind: function () {
        $.ajax(({
            type: "post",
            url: "/pc/user/selectAddrByUserId",
            dataType: "json",
            data: {"ch":1},
            success: function (rsl) {
                if (rsl.code == 0) {
                    var listdata = {
                        list: rsl.data
                    }
                    var html = template('addresslist', listdata);
                    //翻页时删除表头以外的所有节点，避免after()方法重复加载
                    $("#adlist").parent().children().each(function () {
                        if ($(this).attr('id') != "adlist") {
                            this.parentNode.removeChild(this);
                        }
                    })
                    $("#adlist").after(html);
                    $("#adCount").html(rsl.data.length);//地址个数
                }
                else {

                }
            }
        }));
    }
}



