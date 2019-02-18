<!-- @{
    Layout = "~/Areas/Seller/Views/Shared/_Layout_Seller_Center.cshtml";
    ViewBag.Title = "我的店铺 &gt; 店铺信息及资质管理 &gt; 店铺信息";
} -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>

<style>
    .border {
        border: hidden;
        BACKGROUND-COLOR: transparent;
    }
    
</style>
<script type="text/css"></script>
<script type="text/javascript" src="/resource/ajaxfileupload.js"></script>
<script src="/resource/public/seller/js/MyShop/ShopInfo.js"></script>
<link type="text/css" rel="stylesheet" href="/resource/public/platform/css/common.css">
<link type="text/css" rel="stylesheet" href="/resource/public/platform/css/ddgl.css">

<div id="container">
    <div class="allcon">
        <div class="position">
            您所在的位置：我的店铺 &gt; 店铺信息及资质管理 &gt; 店铺信息
        </div><!--所在位置信息  结束 -->
        <div class="clear"></div>
        <div class="dpxx">
            <input type="hidden" id="shopID" value="${shopid }" />
            <input type="hidden" id="userID" value="${userid }" />
            <table class="dpxx-tab">
                <tr class="dpxx-tab-title">
                    <td colspan="4">店铺信息</td>
                </tr>
                <tr>
                    <td class="name-dpxx">店铺名称</td>
                    <td><input id="dpmc" type="text" readonly /></td>
                    <td class="name-dpxx">是否旗舰店</td>
                    <td><input id="sfqjd" type="text" readonly /></td>
                </tr>

            </table>
        </div><!--dpxx stop -->
        <div class="dpxx">
            <table class="dpxx-tab" id="conEdit">
                <tr class="dpxx-tab-title">
                    <td colspan="2" style="position:relative">
                        联系人
                        <div class="change-dpxx"><input type="button" id="editone" value="修改" class="small-but"><input type="button" id="saveone" style="display:none" value="保存" class="small-but"></div>
                    </td>
                </tr>
                <tr>
                    <td class="name-dpxx" >店铺LOGO</td>
                    <td id="im" ><img id="imgurl" src=""  width="120px" height="115px"/></td>
                    <td id="imgdiv" style="display: none;">
		               <div class="tjcpxx-con-form1">
								<div class="tjcpxx-con-form-upimg">
									<img id="loadimg" width="120px" height="115px" src="">
								</div>
								<input type="hidden" id="shoplogo" name="img" value="">
							<div style="width: 200px; float: left; position: relative; padding-left: 30px;">
							<input type="button" value="选择图片" class="h_scimgbut"> <input type="file" id="singlefile" name="pics" class="filemhbut" 　>
							<div>
								<input type="button" style="background:#3180d2; color: #fff;" value="本地上传" class="h_scimgbut">
							</div>
							</div>
						</div>
							

            		</td>
                </tr>
                <tr>
                    <td class="name-dpxx">姓名</td>
                    <td><input id="xm" readonly="readonly" type="text" /></td>
                </tr>
                <tr>
                    <td class="name-dpxx">手机号码</td>
                    <td><input id="sjhm" readonly="readonly" type="text" /></td>
                </tr>
               <!--  <tr>
                        <td class="name-dpxx">固定电话</td>
                        <td><input id="gddh" type="text" /></td>
                    </tr> -->
                <tr>
                    <td class="name-dpxx">电子邮箱</td>
                    <td><input id="dzyx" readonly="readonly" type="text" /></td>
                </tr>
                <!-- <tr>
                        <td class="name-dpxx">传真</td>
                        <td><input id="cz" type="text" /></td>
                    </tr>
                    <tr>
                        <td class="name-dpxx">可开发票类型</td>
                        <td><input id="kkfplx" type="text" /></td>
                    </tr> -->
            </table>

            <!-- <table class="dpxx-tab">
                    <tr class="dpxx-tab-title">
                        <td colspan="2">
                            售后服务
                        </td>
                    </tr>
                    <tr>
                        <td class="name-dpxx">联系人</td>
                        <td><input id="lxr" type="text" /></td>
                    </tr>
                    <tr>
                        <td class="name-dpxx">退货电话</td>
                        <td><input id="thdh" type="text" /></td>
                    </tr>
                    <tr>
                        <td class="name-dpxx">退货邮编</td>
                        <td><input id="thyb" type="text" /></td>
                    </tr>
                    <tr>
                        <td class="name-dpxx">退货地址</td>
                        <td><input id="thdz" type="text" /></td>
                    </tr>
                </table> -->

            <!-- <table class="dpxx-tab">
                    <tr class="dpxx-tab-title">
                        <td colspan="2">
                            库存预警
                        </td>
                    </tr>
                    <tr>
                        <td class="name-dpxx">库存预警数</td>
                        <td><input id="kcyjs" type="text" /></td>
                    </tr>
                </table> -->
        </div><!--dpxx stop -->
        <!-- <div class="dpxx">
                <table class="dpxx-tab">
                    <tr class="dpxx-tab-title">
                        <td colspan="2" style="position:relative">
                            ATP秘钥
                            <div class="change-dpxx" style="width:120px;"><input type="button" value="重置AIP秘钥" class="small-but"></div>
                        </td>
                    </tr>
                    <tr>
                        <td class="name-dpxx">ATP秘钥(secrot)</td>
                        <td><input id="atpmy" type="text" /></td>
                    </tr>
                </table>
            </div> --><!--dpxx stop -->
    </div><!--主要内容 右边结束 -->
</div>
<script type="text/javascript">
$(".h_scimgbut").click(function() {
			$.ajaxFileUpload({
				url : "/app/api/img/upload",
				secureuri : false,
				fileElementId : 'singlefile',
				dataType : "json",
				//relationtype: 模块（品牌( 11)）
				data : {
					relationtype : 40
				},
				type : 'POST',
				success : function(result) {
					//  alert(JSON.stringify(result));
					//$(".url1").html(JSON.stringify(result));
					$("input[name='img']").val(result.data);
					if (result.code == 0){
						Dalert("上传成功");
						$("#loadimg").attr( "src",result.data[0]);
					$("#imgurl").attr( "src",result.data[0]);
					
					}else {
						$("#loadimg").attr("src", "");
						Dalert("上传图片失败");
					}
					//TODO 结束正在加载中
				},
				error : function(e) {
					alert(JSON.stringify(e));
				}
			});
		});
</script>