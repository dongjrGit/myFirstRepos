<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>
<%@ include file="/decorators/getFileUrl.jsp"%>
<style>
    .border {
        border: hidden;
        BACKGROUND-COLOR: transparent
    }
</style>
<script src="/resource/public/commonjs/showImage.js"></script>
<script src="/resource/public/seller/js/MyShop/CompanyInfo.js"></script>

<div id="container">
    <div class="allcon">
        <div class="position">
            您所在的位置：我的店铺 &gt; 店铺信息及资质管理 &gt; 公司信息
        </div><!--所在位置信息  结束 -->
        <div class="clear"></div>
        <div class="dpxx">
            <input type="hidden" id="shopID" value="${shopid }" />
            <input type="hidden" id="imgsrc" value="<%=path %>" />
            <input type="hidden" id="EditID" />
            <table class="dpxx-tab" id="editonesave">
            <c:if test="${status == 0||status==2}">
            <tr style="text-align: center;
    font-weight: 700;
    color: red;">
            <td colspan="2" style="text-align: center;">
			${status==0?"信息已提交平台请等待平台审核":""}${status == 2?"您的信息审核不通过请核对信息进行修改":"" }
            </td>
            
            </tr>
            </c:if>
                <tr class="dpxx-tab-title">
                    <td colspan="2" style="position:relative">
                        公司基本信息
                        <div class="change-dpxx"><input type="button" id="editone" value="修改" class="small-but"><input type="button" id="saveone" style="display:none" value="保存" class="small-but"></div>
                    </td>
                </tr>
                <tr>
                    <td class="name-dpxx">公司名称</td>
                    <td id="gsmc"></td>
                </tr>
                <tr>
                    <td class="name-dpxx">公司所在地</td>
                    <td id="gsszd">
                       <!--  <input type="text" readonly="readonly" id="gsszd"> -->

                    </td>
                </tr>

                <!-- <tr>
                    <td class="name-dpxx">公司官网地址</td>
                    <td id="gsgwdz"> </td>
                </tr> -->
                <tr>
                    <td class="name-dpxx">公司性质</td>
                    <td id="gsxz"></td>
                </tr>
                <!-- <tr>
                    <td class="name-dpxx">公司荣誉</td>
                    <td id="gsry"> </td>
                </tr> -->
                <tr>
                    <td class="name-dpxx">营业执照号码</td>
                    <td id="yyzzhm"></td>
                </tr>
                <!-- <tr>
                    <td class="name-dpxx">经营品类</td>
                    <td id="jypl"> </td>
                </tr> -->

                <tr>
                    <td class="name-dpxx">公司详细地址</td>
                    <td><input type="text" readonly="readonly" id="gsxxdz"> </td>
                </tr>
                <tr>
                    <td class="name-dpxx">固定电话</td>
                    <td><input type="text" readonly="readonly" id="gddh"> </td>
                </tr>
                <tr>
                    <td class="name-dpxx">客服电话</td>
                    <td><input type="text" readonly="readonly" id="kfdh"> </td>
                </tr>
                <!-- <tr>
                    <td class="name-dpxx">电子邮箱</td>
                    <td><input type="text" readonly="readonly" id="dzyx"> </td>
                </tr> -->
                <tr>
                    <td class="name-dpxx">传真</td>
                    <td><input type="text" readonly="readonly" id="cz"> </td>
                </tr>
                <tr>
                    <td class="name-dpxx">邮政编码</td>
                    <td><input type="text" readonly="readonly" id="yzbm"> </td>
                </tr>
                 <%-- <tr>
                    <td class="name-dpxx">开户行名称</td>
                    <td><input type="text" readonly="readonly" value="${bankname}" id="BankName"> </td>
                </tr>
                <tr>
                    <td class="name-dpxx">银行卡号</td>
                    <td><input type="text" readonly="readonly" value="${bankcardno}" id="BankCardNo"> </td>
                </tr>
                 <tr>
                    <td class="name-dpxx">持卡人姓名</td>
                    <td><input type="text" readonly="readonly" value="${holdername}" id="HoderName"> </td>
                </tr> --%>
                <!-- <tr>
                        <td class="name-dpxx">组织机构代码</td>
                        <td><input type="text" readonly="readonly" id="zzjgdm"> </td>
                    </tr> -->
                <!-- <tr>
                        <td class="name-dpxx">组织机构代码证有效期</td>
                        <td><input type="text" readonly="readonly" id="zzjgdmzyxq"></td>
                    </tr>
                    <tr>
                        <td class="name-dpxx">组织机构代码证电子版</td>
                        <td><input id="zzjgdmzdzb"></td>
                    </tr> -->
            </table>

            <table class="dpxx-tab" id="lxrbj">
                <tr class="dpxx-tab-title">
                    <td colspan="2" style="position:relative">
                        公司联系人信息
                        <div class="change-dpxx"><input type="button" id="edittwo" value="修改" class="small-but"><input type="button" id="savetwo" style="display:none" value="保存" class="small-but"></div>
                    </td>
                </tr>
                <tr>
                    <td class="name-dpxx">公司负责人姓名</td>
                    <td><input type="text" readonly="readonly" id="gsfzrxm"></td>
                </tr>
                <tr>
                    <td class="name-dpxx">公司负责人电话</td>
                    <td><input type="text" readonly="readonly" id="gsfzrdh"> </td>
                </tr>
                <tr>
                    <td class="name-dpxx">公司负责人电子邮箱</td>
                    <td><input type="text" readonly="readonly" id="gsfzrdzyx"> </td>
                </tr>
                <!-- <tr>
                    <td class="name-dpxx">公司负责人职位</td>
                    <td><input type="text" readonly="readonly" id="gsfzrzw"> </td>
                </tr> -->
            </table>

            <table class="dpxx-tab">
                <tr class="dpxx-tab-title">
                    <td colspan="2" style="position:relative">
                        营业执照信息
                        <div class="change-dpxx"></div>
                    </td>
                </tr>
                <tr>
                    <td class="name-dpxx"></td>
                    <td><div id="yyzzxx"></div></td>
                </tr>

            </table>

            <table class="dpxx-tab">
                <tr class="dpxx-tab-title">
                    <td colspan="2" style="position:relative">
                        税务登记证
                        <div class="change-dpxx"></div>
                    </td>
                </tr>
                <tr>
                    <td class="name-dpxx">&nbsp;</td>
                    <td><div id="swdjxx"></div></td>
                </tr>

            </table>

            <table class="dpxx-tab">
                <tr class="dpxx-tab-title">
                    <td colspan="2" style="position:relative">
                        组织机构代码
                        <div class="change-dpxx"></div>
                    </td>
                </tr>
                <tr>
                    <td class="name-dpxx">&nbsp;</td>
                    <td><div id="zzjgdm"></div></td>
                </tr>
            </table> 
             <!-- <table class="dpxx-tab">
                <tr class="dpxx-tab-title">
                    <td colspan="2" style="position:relative">
                        三品一标证书
                        <div class="change-dpxx"></div>
                    </td>
                </tr>
                <tr>
                    <td class="name-dpxx">&nbsp;</td>
                    <td><div id="spy"></div></td>
                </tr>
            </table> 
            <table class="dpxx-tab">
                <tr class="dpxx-tab-title">
                    <td colspan="2" style="position:relative">
                        生产许可证或流通许可证
                        <div class="change-dpxx"></div>
                    </td>
                </tr>
                <tr>
                    <td class="name-dpxx">&nbsp;</td>
                    <td><div id="scxkz"></div></td>
                </tr>
            </table> 
            <table class="dpxx-tab">
                <tr class="dpxx-tab-title">
                    <td colspan="2" style="position:relative">
                        近期产品检测报告
                        <div class="change-dpxx"></div>
                    </td>
                </tr>
                <tr>
                    <td class="name-dpxx">&nbsp;</td>
                    <td><div id="jcbg"></div></td>
                </tr>
            </table> 
           
            <table class="dpxx-tab">
                <tr class="dpxx-tab-title">
                    <td colspan="2" style="position:relative">
                       开户银行许可证
                        <div class="change-dpxx"></div>
                    </td>
                </tr>
                <tr>
                    <td class="name-dpxx">&nbsp;</td>
                    <td><div id="khyhxkz"></div></td>
                </tr>
            </table>
             <table class="dpxx-tab">
                <tr class="dpxx-tab-title">
                    <td colspan="2" style="position:relative">
                        其它资质
                        <div class="change-dpxx"></div>
                    </td>
                </tr>
                <tr>
                    <td class="name-dpxx">&nbsp;</td>
                    <td><div id="gszz"></div></td>
                </tr>
            </table> -->
        </div><!--dpxx stop -->

    </div><!--主要内容 右边结束 -->
   
</div>
 <div id="bigimg" class="l_lbimg" style="display: none;">
		<img alt="" />
		<div class="l_close">X</div>
	</div>

