$(document).ready(function() {
    var type = "",isclick=false,fid=0;
    
    callback = upAjax;//上拉加载数据

    //点击发现类型
    $("#sftype").find("a").click(function() {
        index = 1;        
        type = $(this).attr("atype");
        fid= $(this).attr("fid");
        if (parseInt(type) ==2||parseInt(type) ==3) {
            isclick = true;
            upAjax();
        } 
    })
    //根据spuID获取商品评论数据
    function upAjax() {        
        $.ajax({
            url : "/api/wap/discover/getFindRleation",
            type : "Post",
            data : {
                "findid" : fid,
                "type" : type,
                "page" : index,//iscroll_list已定义好直接引用
                "size" : 10,
                "ch":3
            },
            dataType : "json",
            success : function(res) {
                if (res.code == 0) {
                    var data = res.data;
                    if (data != undefined && data != null) {
                        var listdata = {
                            list : data
                        }
                        var html="";
                        if(type==2){
                          html = template('topspulist', listdata); 
                        }else{
                          html = template('newslist', listdata);  
                        }                         
                        if (isclick) {
                            $("#list").html(html);
                        } else {
                            $("#list").append(html);
                        }
                        hasdata = true;//是否有数据iscroll_list中定义，没有数据index将不再增加页数
                        myScroll.refresh();//刷新加载
                    } else {
                        hasdata = false;
                        if (isclick) {
                            //$('#list').html("<li><font style='font-size:0.16rem;'>暂无数据</font></li>");
                        }
                    }
                } else {
                    if (isclick) {
                        //$('#list').html("<li><font style='font-size:0.16rem;'>暂无数据</font></li>");
                    }
                }
                isclick = false;
            },
            error : function() {
                $('#list').html("<li><font style='font-size:0.16rem;'>数据请求失败，请重新刷新</font><li>");
            }
        });        
    }
    
})

