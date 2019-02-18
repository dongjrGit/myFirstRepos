<!-- @{
    ViewBag.Title = "专题管理";
    Layout = "~/Areas/Platform/Views/Shared/_Layout_iframe_conent_v11.cshtml";
} -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/decorators/getFileUrl.jsp"%>
<script type="text/javascript" src="/resource/ajaxfileupload.js"></script>
<script src="/resource/public/platform/js/giftcard/gidtcard.js"></script>
<div class="clear"></div>
<div class="mainright">
    <div class="clear"></div>
    <div class="dotted mar35"></div>
    <div class="l_wzmb">
        <div class="l_wzmbtop">
            <ul>
                <li class="sj_hover"><a href="javascript:void(0);" id="contitle">礼品卡修改</a><span class="sj-img"></span></li>
            </ul>
        </div><!--l_wzmbtop   stop -->
        <div class="tjcpxx-con-con">
            <form id="form">
                <div class="tjcpxx-con-mk1">
                    <div class="tjcpxx-con-form-title1"><label><span class="red marrig5">*</span>礼品卡名称：</label></div>
                    <div class="tjcpxx-con-form1">
                        <input class="tjcpxx-fprm-inp" id="name" name="name" value="${giftcard.name }" type="text">
                    </div>
                    <input type="hidden" value="${giftcard.id}" id="id" name="id"/>
                </div>
                
                <div class="tjcpxx-con-mk1">
                    <div class="tjcpxx-con-form-title1"><label>礼品卡编号：</label></div>
                    <div class="tjcpxx-con-form1">
                        <input class="tjcpxx-fprm-inp" value="${giftcard.code}" id="sort" readonly="readonly" name="sort" type="text">
                    </div>
                </div>
                <div class="tjcpxx-con-mk1">
                    <div class="tjcpxx-con-form-title1"><label>面值：</label></div>
                    <div class="tjcpxx-con-form1">
                        <input class="tjcpxx-fprm-inp" value="${giftcard.facevalue}" id="sort" readonly="readonly" name="sort" type="text">
                    </div>
                </div>
                <div class="tjcpxx-con-mk1">
                    <div class="tjcpxx-con-form-title1"><label>礼品卡卡密：</label></div>
                    <div class="tjcpxx-con-form1">
                        <input class="tjcpxx-fprm-inp" value="${giftcard.password}" id="sort" readonly="readonly" name="sort" type="text">
                    </div>
                </div>
                 <div class="tjcpxx-con-mk1">
                    <div class="tjcpxx-con-form-title1"><label><span class="red marrig5">*</span>图片：</label></div>
                    <div class="tjcpxx-con-form1">
                        <div class="tjcpxx-con-form-upimg">
                            <img id="loadimg" width="120px" height="115px" src="${giftcard.img}" />
                        </div>
                        <input type="hidden" id="saveimg" name="img" />
                        <div style=" width:200px;margin-left:10px; float:left;">
                            <input type="file" id="selectimg" name="selectimgs" />
                            <a href="javascript:void(0);" style="color:#000"><div class="tjcpxx-con-form-upthis">本地上传</div></a>
                        </div>
                    </div>
                </div>
                
                <div class="tjcpxx-con-mk1">
                    <div class="tjcpxx-con-form-title1"><label>描述：</label></div>
                    <div class="tjcpxx-con-form1">
                        <input class="tjcpxx-fprm-inp" value="${giftcard.remark}" id="remark"  name="remark" type="text">
                    </div>
                </div>
                <div class="tjcpxx-con-mk1">
                    <div class="tjcpxx-con-form-title1"></div>
                    <div class="tjcpxx-con-form1 huise">
                        <input class="preserve-inp marrig35 mar35" name="" onclick="gift.save()" type="button" value="保存">
                        <input class="preserve-inp_hs" name="" onclick="backgref()" type="button" value="返回">
                    </div>
                </div><!--tjcpxx-con-mk stop -->

            </form>
        </div>
    </div><!--tjcpxx stop -->
</div><!--mainright stop -->
<script type="text/javascript">
	function backgref(){
		location.href='/platform/market/giftcard_list';
	}
	$(".tjcpxx-con-form-upthis").click(
			function() {
				$.ajaxFileUpload({
					url : "/app/api/img/upload",
					secureuri : false,
					fileElementId : 'selectimg',
					dataType : "json",
					// ftype:上传文件类型（图片文件=1，其他文件=2）
					// module: 模块（ 会员 = 0, 店铺 = 1, 平台 = 2, 产品 = 3, 其他 = 4,）
					data : {
						relationtype : 6
					},
					type : 'POST',
					success : function(result) {
						
						$("input[name='img']").val(result.data[0]);
						if (result.code == 0){
							Dalert("上传成功");
							$("#loadimg").attr("src", result.data[0]);
						}else
							$("#loadimg").attr("src", "");
						// TODO 结束正在加载中
					}
				});
			});
	
	
</script>