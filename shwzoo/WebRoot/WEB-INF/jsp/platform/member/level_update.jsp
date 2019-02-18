<!--  @{
    ViewBag.Title = "会员管理&gt;会员管理&gt;会员等级&gt;更新等级";
    Layout = "~/Areas/Platform/Views/Shared/_Layout_iframe_conent_v11.cshtml";
}-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript" src="${pageContext.request.contextPath }/resource/public/platform/js/member/memberlevelupdate.js"></script>

<div class="mainright">
    <div class="clear"></div>
    <div class="dotted mar35"></div>
    <div class="l_wzmb">
        <div class="l_wzmbtop">
            <ul>
                <li class="sj_hover"><a href="javascript:void(0);" id="leveltitle">编辑等级</a><span class="sj-img"></span></li>
            </ul>
        </div>

            <div class="tjcpxx-con-con">
                <form id="updatelevelForm">

                    <div class="tjcpxx-con-mk1">
                        <div class="tjcpxx-con-form-title1"><label><span class="red marrig5">*</span>等级数：</label></div>
                        <div class="tjcpxx-con-form1">
                            <input class="tjcpxx-fprm-inp" id="levelnum" name="levelnum" type="text">
                        </div>
                    </div><!--tjcpxx-con-mk stop -->
                    <div class="tjcpxx-con-mk1">
                        <div class="tjcpxx-con-form-title1"><label><span class="red marrig5">*</span>等级名称：</label></div>
                        <div class="tjcpxx-con-form1">
                            <input class="tjcpxx-fprm-inp" id="levelname" name="levelname" type="text">
                        </div>
                    </div><!--tjcpxx-con-mk stop -->
                    <div class="tjcpxx-con-mk1">
                        <div class="tjcpxx-con-form-title1"><label><span class="red marrig5">*</span>晋级标准（消费金额）：</label></div>
                        <div class="tjcpxx-con-form1">
                            <input class="tjcpxx-fprm-inp" id="levelup" name="levelup" type="text">
                        </div>
                    </div><!--tjcpxx-con-mk stop -->
                    <div class="tjcpxx-con-mk1">
                        <div class="tjcpxx-con-form-title1"><label>状态：</label></div>
                        <div class="tjcpxx-con-form1">
                            <select name="levelsta" id="levelsta" class="the-form-select-one">
                                <option value="0">正常</option>
                                <option value="1">冻结</option>
                            </select>
                        </div>
                    </div><!--tjcpxx-con-mk stop -->

                    <div class="tjcpxx-con-mk1">
                        <div class="tjcpxx-con-form-title1"></div>
                        <div class="tjcpxx-con-form1 huise">
                            <input type="hidden" id="hidden_memberid" value="" />
                            <input class="preserve-inp marrig35 mar35" name="submit_ok" id="submit_ok" type="button" value="保存">
                            <input class="preserve-inp_hs" name="backBtn" id="backBtn" type="button" value="返回">
                        </div>
                    </div><!--tjcpxx-con-mk stop -->

                </form>
            </div>
       
    </div><!--tjcpxx stop -->
</div><!--mainright stop -->

