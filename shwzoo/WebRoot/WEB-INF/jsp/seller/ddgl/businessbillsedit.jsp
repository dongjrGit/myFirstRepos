<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<style>
    .myinput {
        border: hidden;
        BACKGROUND-COLOR: transparent;
        float:left;
        padding-left:10px;
        width:180px;
        height:27px;
    }
</style>
<script src="/resource/public/seller/js/DDgl/businessbillsedit.js"></script>
<div class="mainright">
    <div class="l_wzmb">
        <div class="l_wzmbtop">
            <ul>
                <li class="sj_hover"><a href="javascript:void(0);">对账单信息</a><span class="sj-img"></span></li>

            </ul>
            <span class="l_fhddlb"><input class="inquire" name="bback" type="button" value="返回对账单列表"></span>
        </div><!--l_wzmbtop   stop -->
        <!--n_ddxq  begin -->
        <div class="n_ddxq">
            <div class="n_ddxqmk" style="border-bottom:none;">
                <div class="n_ddxqmktop">&nbsp;</div>

                <div class="n_spqd">
                    <form id="billcheck">
                        <table>
                            <tr>
                                <td>时间段：</td>
                                <td colspan="3"><input style="width:320px;" class="myinput" id="beTime" type="text" value="" /></td>
                            </tr>
                            <tr>
                                <td>供货商名称：</td>
                                <td><input id="shopname" class="myinput" type="text" value="" /></td>
                                <td>总营业额：</td>
                                <td><input id="trunover" class="myinput" type="text" value="" /></td>
                            </tr>
                            <tr>
                                <td>银行开户名：</td>
                                <td><input id="bankusername" class="myinput" type="text" value="" /></td>
                                <td>佣金：</td>
                                <td><input id="commission" class="myinput" type="text" value="" /></td>
                            </tr>
                            <tr>
                                <td>公司银行账号：</td>
                                <td><input id="banknum" class="myinput" type="text" value="" /></td>
                                <td>转账时间：</td>
                                <td><input type="text" name="tTime" id="tTime" style="float:left;padding-left:10px" class="Wdate" onfocus="WdatePicker({ maxDate: '#F{$dp.$D(\'select_end\')}' })"></td>
                            </tr>
                            <tr>
                                <td>开户银行支行名称</td>
                                <td><input id="bankname" class="myinput" type="text" value="" /></td>
                                <td>转账金额：</td>
                                <td><input id="tmoney" class="myinput" type="text" value="" /></td>
                            </tr>
                            <tr>
                                <td>汇款方式：</td>
                                <td><input id="ttype" name="ttype" style="float:left;padding-left:10px;width:180px;height:27px" type="text" value="" /></td>
                                <td>转账人：</td>
                                <td><input id="opertor" class="myinput" type="text" value="" /></td>
                            </tr>
                            <tr>
                                <td>备注：</td>
                                <td colspan="3"><textarea style="float:left;padding-left:10px" class="tjcpxx-con-form1-text" name="text_content" id="remark" cols="" rows=""></textarea></td>
                            </tr>

                        </table>
                    </form>
                </div><!--n_spqd  stop -->
                <div style="padding-top:10px; align-content:center">
                    <input style="align-content:center;" class="inquire" name="confirm" id="confirm" type="button" value="确认">
                </div>
                <div class="clear"></div>
            </div><!--n_ddxqmk  stop -->
        </div>
        <!--n_ddxq  stop -->
    </div><!--主要内容 右边结束 -->

</div>