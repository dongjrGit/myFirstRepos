<!-- @{
    ViewBag.Title = "店铺管理&gt;店铺管理&gt;店铺审核&&审核明细";
    Layout = "~/Areas/Platform/Views/Shared/_Layout_iframe_conent_v11.cshtml";
}
 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>

<%@ include file="/decorators/getFileUrl.jsp"%>
<script type="text/javascript"
	src="${ctx }/resource/public/platform/js/shop/shopcheckdetail.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        //初始化
        Init.bind();
        //表单验证
        //Vaildate.bind();
    })
</script>

<div class="mainright">
	<div class="l_wzmb">
		<div class="l_wzmbtop">
			<ul>
				<li class="sj_hover"><a href="javascript:void(0);">审核明细</a><span
					class="sj-img"></span></li>
			</ul>
		</div>
		<!--l_wzmbtop   stop -->
		<div style="margin-top: 20px; padding: 10px;">
			<div class="table-con-detail">
				<table id="t1">
					<tbody id="tb_shopcheckdetail">
						<script type="text/html" id="shopcheckdetail">
                            <tr>
                                <td style="width:35%" class="text-right">联系人姓名</td>
                                <td>{{list.principalname}}</td>
                            </tr>
                            <tr>
                                <td class="text-right">联系人手机</td>
                                <td>{{list.principalmobile}}</td>
                            </tr>
                            <tr>
                                <td class="text-right">联系人邮箱</td>
                                <td>{{list.principalemail}}</td>
                            </tr>
                            <tr>
                                <td class="text-right">店铺名称</td>
                                <td>{{list.shopname}}</td>
                            </tr>
                            <tr>
                                <td class="text-right">公司类型</td>
                                <td>
                                    {{if list.companytype==0}}
                                    民营
                                    {{else if list.companytype==1}}
                                    国企
                                    {{else if list.companytype==2}}
                                    外企
                                    {{else}}
                                    其它
                                    {{/if}}
                                </td>
                            </tr>
                            <tr>
                                <td class="text-right">公司名称</td>
                                <td>{{list.companyname}}</td>
                            </tr>
                            <tr>
                                <td class="text-right">公司地址</td>
                                <td>{{list.companyadress}}</td>
                            </tr>
                            <tr>
                                <td class="text-right">公司电话</td>
                                <td>{{list.companytel}}</td>
                            </tr>
                            <tr>
                                <td class="text-right">公司官网</td>
                                <td>{{list.companyweb}}</td>
                            </tr>
                            <tr>
                                <td class="text-right">营业执照编号</td>
                                <td>
                                    {{list.licensenum}}
                                </td>
                            </tr>
							<tr>
                                <td class="text-right">营业执照</td>
                                <td>
                                    <img style="height:90px;width:90px" src="<%=path%>{{list.license}}">
									<img style="height:90px;width:90px" src="<%=path%>{{list.organization}}">
                                        <img style="height:90px;width:90px" src="<%=path%>{{list.tax}}" />
							</td>
                            </tr>
							<tr>
                                <td class="text-right">三品一标证书</td>
                                <td>
                                    <img style="height:90px;width:90px" src="<%=path%>{{spy}}" />
								</td>
                            </tr>
							<tr>
                                <td class="text-right">生产许可证或流通许可证</td>
                                <td>
                                  {{each scxkz as vo}}
                                    <img style="height:90px;width:90px" src="<%=path%>{{vo}}" />
                                	{{/each}}
								</td>
                            </tr>
							<tr>
                                <td class="text-right">近期产品检测报告</td>
                                <td>
								{{each jcbg as vo}}
                                    <img style="height:90px;width:90px" src="<%=path%>{{vo}}" />
                                {{/each}}
								</td>
                            </tr>
							<tr>
                                <td class="text-right">其它资质</td>
                                <td>
								{{each qita as vo}}
                                    <img style="height:90px;width:90px" src="<%=path%>{{vo}}" />
                                {{/each}}
                                </td>
                            </tr>
                            <tr>
                                <td class="text-right">开户银行许可证</td>
                                <td>
                                    <img style="height:90px;width:90px" src="<%=path%>{{list.bank}}" />
                                </td>
                            </tr>

                            <tr>
                                <td class="text-right">申请状态<input type="hidden" id="hidden_oldshopstatus" value="{{list.status}}" /></td>
                                <td>
                                    {{if list.status==1}}
                                    <input name="rd" id="radio_checking" type="radio" value="0">审核中<span class="marrig35"></span>
                                    <input name="rd" id="radio_checkpass" type="radio" value="1" checked>审核通过<span class="marrig35"></span>
                                    <input name="rd" id="radio_checknopass" type="radio" value="2">审核不通过
                                    {{else if list.status==2}}
                                    <input name="rd" id="radio_checking" type="radio" value="0">审核中<span class="marrig35"></span>
                                    <input name="rd" id="radio_checkpass" type="radio" value="1">审核通过<span class="marrig35"></span>
                                    <input name="rd" id="radio_checknopass" type="radio" value="2" checked>审核不通过
                                    {{else}}
                                    <input name="rd" id="radio_checking" type="radio" value="0" checked>审核中<span class="marrig35"></span>
                                    <input name="rd" id="radio_checkpass" type="radio" value="1">审核通过<span class="marrig35"></span>
                                    <input name="rd" id="radio_checknopass" type="radio" value="2">审核不通过
                                    {{/if}}
                                </td>
                            </tr>
                            <tr class="l_nobor">
                                <td class="text-right">&nbsp;</td>
                                <td>
                                    <input class="preserve-inp" name="submit_ok" id="btn_submit" type="button" value="保存">
                                    <input class="preserve-inp_hs" name="btn_goback" id="btn_goback" type="button" value="返回">
                                </td>
                            </tr>
                        </script>
					</tbody>
				</table>
				<div class="table-con-detail-form"></div>
			</div>
			<!--table-con  stop -->
		</div>
	</div>
	<!--table-con  stop -->
	<div class="clear"></div>
	<div id="bigimg" class="l_lbimg" style="display: none;">
		<img alt="" />
		<div class="l_close">X</div>
	</div>


</div>
<!--mainright stop -->
<script src="/resource/public/commonjs/showImage.js"></script>
<script>
	$(function() {
		// 点击图片查看大图
		$("img").each(function() {
		  $(this).click(function() {
			if ($(this).attr("src") != ""
					&& $(this).attr("src") != undefined)
				Show("bigimg", $(this).attr("src"));
		   });
		});
		// 关闭弹出层事件
		$(".l_close").bind("click", function() {
			$("#bigimg").hide();
		});
		$("#bigimg img").bind("click", Jump);
		// 点击大图跳转方法
		function Jump() {
			var imgurl = $("#bigimg img").attr("src");
			var surl = "/Platform/img/ImageShow";
			ImageJump(imgurl, surl);
		}
	});
</script>