<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="mainright">
    <div class="l_wzmb">
        <div class="l_wzmbtop">
            <ul>
                <li class="sj_hover"><a href="javascript:void(0);">添加商品</a><span class="sj-img"></span></li>
            </ul>
        </div><!--l_wzmbtop   stop -->
        <div class="tjcpxx">
            <div class="tjcpxx-con">
                <div class="tjcpxx-con-con">
                    <form id="form" method="post">
                        <input id="id" name="spikeid" type="hidden" value="${spikeid }" />
                        <input id="spuid" name="spuid" type="hidden" value="" />
                        <div class="tjcpxx-con-mk1">
                            <div class="tjcpxx-con-form-title"><label><span class="red marrig5">*</span>商品：</label></div>
                            <div class="tjcpxx-con-form1">
                                <input id="select_spu" type="text" class="tjcpxx-fprm-inp" value="" />
                            </div>
                            <div style="margin-top:25px;margin-left:15px;" onclick="getSpuPriceStartwithName()">
                                <ul>
                                    <script id="select_spulist" type="text/html">
                                        {{each list as spu i}}
                                        <li data="{{spu.id}}">{{spu.name}}</li>
                                        {{/each}}
                                    </script>
                                </ul>
                            </div>
                        </div>
                        <div class="tjcpxx-con-mk1">
                            <div class="tjcpxx-con-form-title"><label>商品价格：</label></div>
                            <div class="tjcpxx-con-form1">
                                <input id="money" class="tjcpxx-fprm-inp" name="money" type="text" value="" disabled="true">&nbsp;元
                            </div>
                        </div>
                        <div class="tjcpxx-con-mk1">
                            <div class="tjcpxx-con-form-title"><label><span class="red marrig5">*</span>优惠价格：</label></div>
                            <div class="tjcpxx-con-form1">
                                <input id="price" class="tjcpxx-fprm-inp" name="price" type="text" value="">&nbsp;元
                            </div>
                        </div>
                        <div class="tjcpxx-con-mk1">
                            <div class="tjcpxx-con-form-title"><label><span class="red marrig5">*</span>商品数量：</label></div>
                            <div class="tjcpxx-con-form1">
                                <input id="count" class="tjcpxx-fprm-inp" name="count" type="text" value="">
                            </div>
                        </div>
                        <div class="tjcpxx-con-mk1">
                            <div class="tjcpxx-con-form-title"><label><span class="red marrig5">*</span>排序：</label></div>
                            <div class="tjcpxx-con-form1">
                                <input id="orderby" class="tjcpxx-fprm-inp" name="orderby" type="text" value="">
                            </div>
                        </div>
                        <div class="tjcpxx-con-mk1">
                            <div class="tjcpxx-con-form-title"><label><span class="red marrig5">*</span>手机专享：</label></div>
                            <div id="divStatus" class="tjcpxx-con-form huise">
                                <input name='isphone' checked type='radio' value='0'><span>否</span>
                                <span class='marrig35'></span>
                                <input name='isphone' type='radio' value='1'><span>是</span>
                            </div>
                        </div>

                        <div class="tjcpxx-con-mk">
                            <div class="tjcpxx-con-form-title"></div>
                            <div class="tjcpxx-con-form huise">
                                <input class="preserve-inp" name="Save" type="button" value="保存">
                                <input id="action" type="hidden" value="addSpikeSpu">
                                <span class="marrig35"></span>
                                <input class="preserve-inp_hs" name="" type="button" value="取消" onclick="formCancel()">
                            </div>
                        </div>


                    </form>
                </div>
            </div>
        </div><!--tjcpxx-con stop -->
    </div>
</div>
<script src="${pageContext.request.contextPath }/resource/public/platform/js/yxgl/spikeskuSave.js"></script>
<script>
    $(document).ready(function () {
        if ($("#orderby").val() == "" || $("#orderby").val() == undefined) {
            $("#orderby").val(1);
        }
    })
    function formCancel() {
        location.href = "yxgl_SpikeSpuList?id=" + $("#id").val();
    }
</script>