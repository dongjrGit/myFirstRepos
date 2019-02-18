<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="mainright">
    <div class="clear"></div>
    <div class="dotted mar35">
    </div>
    <div class="l_wzmbtop">
        <ul>
            <li class="sj_hover"><a href="javascript:void(0)">浏览明细</a><span class="sj-img"></span></li>
        </ul>
        <input class="preserve-inp_hs" name="btn_goback" id="btn_goback" type="button" value="返回" style="float:right;margin-bottom:3px;">
    </div><!--l_wzmbtop   stop -->
    <div class="dpplb">
        <table id="table_browserdetail">
            <script id="browserdetaillist" type="text/html">
                {{each list as pro i}}
                <tr>
                    <td style="vertical-align:top;">{{pro.createtimestr}}</td>
                    <td class="tp-list">
                        <ul>
                            <li>
                                <img src="{{pro.imgurl}}" width="160" height="160">
                            </li>
                            <li>{{pro.spuname}}</li>
                        </ul>
                    </td>
                </tr>
                {{/each}}
            </script>
        </table>
    </div><!--dpplb全部内容 结束 -->
</div>
<script type="text/javascript">
    $(document).ready(function () {
        //返回按钮点击
        $("#btn_goback").bind("click", function () {
            window.location.href = "/platform/member/showMember_BrowserHistory";
        });

        var memberID = GetQueryStringByName("memberid");
        $.ajax(({
            type: "post",
            url: "/platform/memberbrowserhistory/queryBrowseHistoryDetail",
            dataType: "json",
            data: { memberid: memberID },
            success: function (rsl) {
                if (rsl.code == 0) {
                    var listdata = {
                        list: rsl.data
                    }
                    var html = template('browserdetaillist', listdata);

                    $("#table_browserdetail").html(html);
                }
                else {
                    Dalert(rsl.desc);
                }
            },
            error: function (es) {
            }
        }));
    })
</script>