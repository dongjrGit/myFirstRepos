var pcount = 100;
var prow = 10;
var pindex;
//分页
function pagination(pagecount, pageindex, pagerow,callback) {
    var pcount = pagecount;//总条数
    var prow = pagerow;//每页条数
    var pindex = pageindex;//当前页
    var totalpage;
    var htmlpage;
    if(pagecount % pagerow>0)
    {
        totalpage=parseInt(pagecount / pagerow) + 1
    }
    else{
        totalpage=parseInt(pagecount / pagerow)
    }
    if (pcount <= prow) {
        htmlpage = "";
    }
    else {

        htmlpage = "<div class='pager clearfix'>";
        htmlpage += "<div id='J_bottomPage' class='p-wrap'>";
        htmlpage += "<span class='p-num'>";
        if (pageindex > 1) {
            htmlpage += "<a href='javascript:void(0);' onclick='gotopage(" + (parseInt(pageindex) - 1) + "," + callback + ");' class='pn-prev'><i><</i><em>上一页</em></a>";
        }
        else {
            htmlpage += "<a class='pn-prev disabled '><i><</i><em>上一页</em></a>";
        }
        var strbreak = "<b class='pn-break'>…</b>";
        for (var i = 1; i <= totalpage; i++) {
            if (pageindex<=3) {
                if (i <= 5) {
                    if (i == pageindex) {
                        htmlpage += "<a href='javascript:void(0);' onclick='gotopage(" + i + "," + callback + ");' class='curr'>" + i + "</a>";
                    }
                    else {
                        htmlpage += "<a href='javascript:void(0);' onclick='gotopage(" + i + "," + callback + ");' class=''>" + i + "</a>";
                    }
                }
                else {
                    htmlpage += strbreak;
                    break;
                }

            }
            else {
                if (i <= pageindex) {
                    if (i == parseInt(pageindex) - 3) {
                        ////当前页大于等于6，显示第1页，和左靠近当前页的3页
                        if (parseInt(pageindex) - 5 > 0) {
                            htmlpage += strbreak;
                            htmlpage += "<a href='javascript:void(0);' onclick='gotopage(" + i + "," + callback + ");' class=''>" + i + "</a>";
                        }
                        else {
                            htmlpage += "<a href='javascript:void(0);' onclick='gotopage(" + i + "," + callback + ");' class=''>" + i + "</a>";
                        }

                    }

                    else if (i >= parseInt(pageindex) - 3 || i == 1) {
                        if (i == pageindex) {
                            htmlpage += "<a href='javascript:void(0);' onclick='gotopage(" + i + "," + callback + ");' class='curr'>" + i + "</a>";
                        }
                        else {
                            htmlpage += "<a href='javascript:void(0);' onclick='gotopage(" + i + "," + callback + ");' class=''>" + i + "</a>";
                        }
                    }
                }
                else {
                    if (i == parseInt(pageindex) + 2) {
                        ////当前页大于总页数减3，当前页后面全部显示
                        if (parseInt(pageindex) + 3 < totalpage) {
                            htmlpage += "<a href='javascript:void(0);' onclick='gotopage(" + i + "," + callback + ");' class=''>" + i + "</a>";
                            htmlpage += strbreak;
                        }
                        else {
                            htmlpage += "<a href='javascript:void(0);' onclick='gotopage(" + i + "," + callback + ");' class=''>" + i + "</a>";
                        }

                    }
                        ////当前页小于总页数减2，显示最后1页，和右靠近当前页的2页
                    else if (i <= parseInt(pageindex) + 2 || i == totalpage) {
                        htmlpage += "<a href='javascript:void(0);' onclick='gotopage(" + i + "," + callback + ");' class=''>" + i + "</a>";
                    }
                }
            }
        }
        if (pageindex == totalpage) {
            htmlpage += "<a class='pn-next disabled'> 下一页<i>></i></a>";
        }
        else {
            htmlpage += "<a href='javascript:void(0);' onclick='gotopage(" + (parseInt(pageindex) + 1) + "," + callback + ");' class='pn-next'> 下一页<i>></i></a>";
        }
        htmlpage += "</span>";
        //页面总数和页面跳转
        htmlpage += "<span class='p-skip'>";
        htmlpage += "<em>共<b>" + totalpage + "</b>页&nbsp;&nbsp;到第</em>";
        htmlpage += "           <input id='page_jump_num' value='' onkeydown='javascript:if(event.keyCode==13){jump_page();return false;}' class='input-txt' /><em>页</em>";
        htmlpage += "            <a href='javascript:jump_page(" + callback + ");' class='btn btn-default'>确定</a>";
        htmlpage += "        </span>";
        htmlpage += "</div>";
        htmlpage += "</div>";
    }
    return htmlpage;
}
//分页跳转
//跳转
function jump_page(callback) {

    var num = $("#page_jump_num").val();
    gotopage(num, callback);
    $("#page_jump_num").val(num);
}

//翻页
function gotopage(index, callback) {
    pindex = index;
    callback(index);
}
