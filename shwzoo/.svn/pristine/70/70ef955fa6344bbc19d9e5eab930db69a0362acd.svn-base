<!-- {
    ViewBag.Title = "会员管理&gt;会员咨询评论&gt;咨询列表";
    Layout = "~/Areas/Platform/Views/Shared/_Layout_iframe_conent_v11.cshtml";
} -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript" src="${pageContext.request.contextPath }/resource/public/platform/js/member/goodconsult.js"></script>

<div class="mainright">
    <div class="clear"></div>
    <div class="dotted mar35"></div>
    <div class="account-form">
        <span>产品标题：</span><input class="inp-seller" id="select_title" type="text">
        <span class="marrig10"></span>
        <span>产品编号：</span><input class="inp-seller" id="select_pronum" type="text">
        <span class="marrig10"></span>
        <input class="chaxun" name="select_button" type="button" value="查询" onclick="GoodConsultList.bind(1)">
        <!-- <span id="screening"><a href="javascript:void(0);">更多筛选条件 &or;</a></span>
            <div class="clear"></div>

            <div id="screening-con">
                <div class="the-form-mk">
                    <span class="the-form-mk-title">产品分类：</span>
                    <select name="" class="the-form-select">
                        <option selected>全部分类</option>
                        <option>全部分类</option>
                        <option>全部分类</option>
                        <option>全部分类</option>
                        <option>全部分类</option>
                    </select>
                </div>
                <div class="the-form-mk">
                    <span class="the-form-mk-title">最后修改时间：</span><input class="the-form-inp" name="" type="text">
                </div>
                <div class="the-form-mk">
                    <span class="the-form-mk-title">上下架状态：</span>
                    <select name="" class="the-form-select">
                        <option selected>不限</option>
                        <option>全部分类</option>
                        <option>全部分类</option>
                        <option>全部分类</option>
                        <option>全部分类</option>
                    </select>
                </div>
                <div class="clear"></div>
                <div class="notice">
                    <div class="notice-but">导出Excel表</div>
                    <div class="notice-but">+添加产品</div>
                </div>
                <div class="clear"></div>
            </div> -->

    </div><!--the-form stop -->

    <div class="clear"></div>
    <div class="mar35"></div>
    <div class="table-con">
        <table class="data_list">
            <tr id="goodconsultlist_title">
                <th>店铺</th>
                <th>商品名称</th>
                <th>咨询类型</th>
                <th>咨询内容</th>
                <th>咨询人</th>
                <th>咨询时间</th>
                <th>回复人</th>
                <th>回复时间</th>
                <th>状态</th>
                <th>操作</th>
            </tr>
            <script id="goodconsultlist" type="text/html">
                {{each list as pro i}}
                <tr>
                    <td>{{pro.shopname}}</td>
                    <td>{{pro.spuname}}</td>
                    {{if pro.consulttype==1 }}
                    <td>库存及配送</td>
                    {{else if pro.consulttype==2 }}
                    <td>支付问题</td>
                    {{else if pro.consulttype==3 }}
                    <td>发票及保修</td>
                    {{else if pro.consulttype==4 }}
                    <td>促销及赠品</td>
                    {{else}}
                    <td>商品咨询</td>
                    {{/if}}
                    <td>
                        <div title="{{pro.consultcontent}}">
                            {{if pro.consultcontent.length>10}}
                            咨询问题：{{pro.consultcontent.substring(0,10)}}...
                            {{else}}
                            咨询问题：{{pro.consultcontent}}
                            {{/if}}
                        </div>
                        {{if pro.replycontent==null}}
                        <div class="div_replycontent">
                            未回复
                        </div>
                        {{else}}
                        <div class="div_replycontent" title="{{pro.replycontent}}">
                            {{if pro.replycontent.length>10}}
                            咨询回复：{{pro.replycontent.substring(0,10)}}...
                            {{else}}
                            咨询回复：{{pro.replycontent}}
                            {{/if}}
                        </div>
                        {{/if}}

                    </td>
                    <td>{{pro.consultname}}</td>
                    <td>{{pro.consultdateStr}}</td>
                    <td>{{pro.replyname}}</td>
                    <td>{{pro.replydateStr}}</td>
                    {{if pro.status==1}}
                    <td>已回复</td>
                    {{else}}
                    <td>未回复</td>
                    {{/if}}
                    <td>
                        <input type="hidden" id="hidden_goodconsultid" value="{{pro.id}}" />
                        {{if pro.status==1}}
                        <a href="javascript:void(0);"><span class="bjxx">回复</span></a>
                        {{else}}
                        <a href="javascript:void(0);" class="a_goodconsultreply"><span class="bjxx shenlan">回复</span></a>
                        {{/if}}
                        <a href="javascript:void(0);" class="a_goodconsultdelete"><span class="delete shenlan">删除</span></a>
                    </td>
                </tr>
                {{/each}}
            </script>


        </table>

    </div><!--table-con  stop -->

    <div class="clear"></div>
    <div id="pager" class="page">
    </div><!--page stop -->


</div><!--mainright stop -->
<script type="text/javascript">
    $(document).ready(function () {
        //列表以及分页数据绑定
        GoodConsultList.bind(1);
    })
</script>
