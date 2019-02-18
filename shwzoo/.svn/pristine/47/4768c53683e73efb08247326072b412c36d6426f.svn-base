<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>

<script type="text/javascript" src="${ctx }/resource/public/platform/js/jquery.min.js"></script>
<script type="text/javascript" src="${ctx }/resource/public/platform/js/member/membercommentdetail.js"></script>
<div class="mainright">
    <div class="clear"></div>
    <div class="dotted mar35">
    </div>
    <div class="l_wzmbtop">
        <ul>
            <li class="sj_hover"><a href="javascript:void(0)">评论明细</a><span class="sj-img"></span></li>
        </ul>
        <input class="preserve-inp_hs" name="btn_goback" id="btn_goback" type="button" value="返回" style="float:right;margin-bottom:3px;">
    </div><!--l_wzmbtop   stop -->
    <!--<div class="account-form">
        <input class="preserve-inp_hs" name="btn_goback" id="btn_goback" type="button" value="返回" style="float:right;margin-top:10px;">
    </div>the-form stop -->
    <div class="dpplb">
        <table id="table_commentdetail">
            <script id="commentdetail" type="text/html">
                <tr>
                    <td width="230px">评论人</td>
                    <td>{{list.buyername}}</td>
                </tr>
                <tr>
                    <td>评论类型</td>
                    <td>
                        {{if list.type==2}}
                        晒单
                        {{else if list.type==3}}
                        追评
                        {{else}}
                        评论
                        {{/if}}
                    </td>
                </tr>
                <tr>
                    <td>是否匿名</td>
                    <td>
                        {{if list.showname==1}}
                        是
                        {{else}}
                        否
                        {{/if}}
                    </td>
                </tr>
                <tr>
                    <td>订单编号</td>
                    <td>{{list.ordercode}}</td>
                </tr>
                <tr>
                    <td>商品名称</td>
                    <td>{{list.spuname}}</td>
                </tr>
                <tr>
                    <td>店铺</td>
                    <td>{{list.shopname}}</td>
                </tr>
               
                <tr class="dpplb-title">
                    <td colspan="2">评价内容</td>
                </tr>
                <tr>
                    <td>评论星数</td>
                    <td class="star_val" data-val="{{list.star}}"></td>
                </tr>
                {{if list.type==1}}
                <tr>
                    <td style="vertical-align:top;">评价图片</td>
                    <td class="tp-list">
                        <ul>
                            {{each list.imglist as img i}}
                            <li>
                                <img src="{{img.imgurl}}" width="160" height="160">
                                <input type="hidden" value="{{img.id}}" class="hidden_imgid" />
                                <!--<div class="close-dpplb" title="删除">x</div>-->
                            </li>
                            {{/each}}
                        </ul>
                    </td>
                </tr>
                {{/if}}
                <tr>
                    <td style="vertical-align:top;">评价内容</td>
                    <td class="plhf-dy">
                        <div style="height:500px; overflow-y:auto;">
                            <div class="comment-dpplb">
                                <div class="sorting-sz">1</div>
                                <div class="comment-dpplb-con">
                                    <div class="comdp-con-top">
                                        <span>{{list.content}}</span>
                                    </div>
                                    <div class="time-reply">
                                        {{list.createdate}}
                                        <input type="hidden" value="{{list.id}}" class="hidden_commentid" />
                                        <input type="hidden" value="{{list.id}}" class="hidden_replyid" />
                                        <input type="hidden" value="{{list.buyerid}}" clas class="hidden_byreplyuserid" />
                                        <!--<div class="reply-butcon"><input class="reply-but" name="" type="button" value="回复"></div>-->
                                        <div class="clear"></div>
                                        <div class="reply-con">
                                            <textarea class="dpplb-text reply_text" name="" cols="" rows=""></textarea>
                                            <input class="preserve-inp marrig35 mar35 reply_ok" name="" type="submit" value="确认">
                                            <input class="preserve-inp_hs reply_canle" name="" type="button" value="取消">
                                        </div>
                                        <div class="clear"></div>
                                    </div>
                                 
                                </div><!--comment-dpplb-con stop -->
                            </div><!--comment-dpplb -->
                            {{each list.replylist as reply i}}
                            <div class="comment-dpplb">
                                <div class="sorting-sz">{{i+2}}</div>
                                <div class="comment-dpplb-con">
                                    <div class="comdp-con-top-title">
                                        <span class="qianlan">{{reply.createusername}}</span>回复<span class="qianlan">{{reply.byreplyusername}}</span>：{{reply.content}}
                                    </div>
                                    <div class="time-reply">
                                        {{reply.createtimestr}}
                                        <input type="hidden" value="{{list.id}}" class="hidden_commentid" />
                                        <input type="hidden" value="{{list.id}}" class="hidden_replyid" />
                                        <input type="hidden" value="{{reply.createuserid}}" class="hidden_byreplyuserid" />
                                        <div class="reply-butcon"><input class="reply-but" name="" type="button" value="回复"></div>
                                        <div class="clear"></div>
                                        <div class="reply-con">
                                            <textarea class="dpplb-text reply_text" name="" cols="" rows=""></textarea>
                                            <input class="preserve-inp marrig35 mar35 reply_ok" name="" type="submit" value="确认">
                                            <input class="preserve-inp_hs reply_canle" name="" type="button" value="取消">
                                        </div>
                                        <div class="clear"></div>
                                    </div>

                                </div><!--comment-dpplb-con stop -->
                            </div><!--comment-dpplb -->
                            {{/each}}
                        </div>
                    </td>
                </tr>
            </script>
        </table>
    </div><!--dpplb全部内容 结束 -->
</div><!--mainright stop -->
<script type="text/javascript">
    $(document).ready(function () {
        //返回按钮点击
        $("#btn_goback").bind("click", function () {
        	var type = "${type}";
            if(type == 1){
            	window.location.href = "/platform/member/showMemberComment_list";
            }else{
            	window.location.href = "/platform/member/showComment_list";
            }
        });
        //初始化
        Init.bind();
    })
</script>
