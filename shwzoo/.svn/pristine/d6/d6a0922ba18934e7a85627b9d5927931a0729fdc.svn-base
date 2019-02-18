/*站内信*/
$(document).ready(function () {
    var userID = $("#userID").val();
    
    // Message.bind(1,10);
    //显示清空
    $("#Messagedata").find("span").mouseenter(function () {
        $(".l_pbmesxx").removeClass("none");
    });
    $("#Messagedata").mouseleave(function () {
        $(".l_pbmesxx").addClass("none");
    });
    //清空信息
    $(".l_pbmesxx").find("ul").find("li").click(function () {
        if (confirm("确认清空信息吗？")) {
            DelAllMes();
        }
    });
    //删除信息
    $(".l_gbyhqchc").live("click", function () {
        var id = $(this).find("input").val();
        // ConfirmShow("确认要删除信息吗？", del, id); //confirm弹出框 js/dialogShow.js
        del(id);
    });
    //查看信息
    $(".lookinfo").live("click", function () {
        var id = $(this).parent().find("input").val();
        if (type == 1) {
            readMessage(id,false);
        }
        location.href = "MessageRead?id=" + id + "&&uid=" + userID;
    });
   
    /*删除单个信息
      id:信息id
    */
    function del(id) {
        $.ajax({
            type: "post",
            url: "/pc/pcmessage/deleteMessageById",
            dataType: "json",
            data: {
            	id: id
            },
            success: function (rsl) {
                if (rsl.code == 0) {
                	alert("删除消息成功");
                	location.href = "/member/pcmessage/messagelist";
                	
                }                
            },
            error: function (e) {
                // Dalert(e.statusText);
            }
        });
    }
    
ReadAllMes();
});
//设置信息已读
function ReadAllMes() {	
	  $.ajax({
          type: "post",
          url: "/pc/pcmessage/setReadMessage",
          dataType: "json",
          data: {},
          success: function (rsl) {
              if (rsl.code == 0) {              	
              }             
          },
          error: function (e) {
          	
          }
      })
   
}
//删除所有信息
function DelAllMes() {	
	var ids=$("input[name='chMes']");
	var idstr="";
	for (var int = 0; int < ids.length; int++) {
		idstr+=ids[int].value+",";
	}
	  $.ajax({
          type: "post",
          url: "/pc/pcmessage/deleteMessageById",
          dataType: "json",
          data: {"ids":idstr},
          success: function (rsl) {
              if (rsl.code == 0) {
              	alert("删除消息成功");              	
              	location.reload()
              }
              else {
              }
              
          },
          error: function (e) {
          	
          }
      })
   
}

var pindex=1,psize=10, pcount;
var Message = {
    //uID:用户id，index：起始页
    bind: function (index) {
        $.ajax({
            url: "/pc/pcmessage/messagelist",
            type: "Get",
            data: { "page": index, "pagesize": psize },
            dataType: "json",
            success: function (data) {
                if (data.code < 0) {                   
                    $("#pager").attr("style", "display:none");
                } else {                   
                    $("#pager").attr("style", "display:block");
                    var listdata = {
                        list: data.data
                    }
                    var html = template('rowslist', listdata);
                    //翻页时删除表头以外的所有节点，避免after()方法重复加载
                    // $("#Messagedata").parent().children().each(function () {
                        // if ($(this).attr('id') != "Messagedata") {
                            // this.parentNode.removeChild(this);
                        // }
                    // })
                    $("#contentRows").html(html);
                    //分页                   
                    pcount = data.maxRow;
                    pindex = data.pageIndex;                    
                    $("#pager").html(pagination(pcount, pindex, psize, "pagelist"));
                }
            },
            error: function () {                
            }
        });
    }
}
function pagelist(index) {
    Message.bind(index);
}