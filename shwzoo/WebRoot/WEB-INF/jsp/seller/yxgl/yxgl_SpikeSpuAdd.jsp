<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="container">
    <div class="allcon">
        <div class="position">
            您所在的位置：营销管理 &gt;  闪购列表 &gt; 添加商品
        </div><!--所在位置信息  结束 -->
        <div class="zhgl-con">
            <div class="zhgl-con-top">
                <div class="zhgl-con-top-title">添加商品</div>
            </div><!--zhgl-con-top  stop -->
            <form id="form" method="post">
                <div class="zhgl-con-con">
                    <input id="id" name="spikeid" type="hidden" value="${spikeid }" />
                    <input id="spuid" name="spuid" type="hidden" value="" />
                    <table>
                        <tr>
                            <td class="xjdpzzh-left"><span class="red">*</span>商品：</td>
                            <td>
                                <div class="tjcpxx-con-form1">
                                    <input id="select_spu" type="text" class="text-inp-big" />
                                </div>
                                <div style="margin-left:15px;" onclick="getSpuPriceStartwithName()">
                                    <ul>
                                        <script id="select_spulist" type="text/html">
                                            {{each list as spu i}}
                                            <li data="{{spu.id}}">{{spu.name}}</li>
                                            {{/each}}
                                        </script>
                                    </ul>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td class="xjdpzzh-left">商品价格：</td>
                            <td>
                                <input id="presentPrice" class="text-inp-big" name="presentPrice" type="text" value="" disabled="true">&nbsp;元
                            </td>
                        </tr>
                        <tr>
                            <td class="xjdpzzh-left"><span class="red">*</span>优惠价格：</td>
                            <td>
                                <input id="price" class="text-inp-big" name="price" type="text" value="">&nbsp;元
                            </td>
                        </tr>
                        <tr>
                            <td class="xjdpzzh-left"><span class="red">*</span>商品数量：</td>
                            <td>
                                <input id="count" class="text-inp-big" name="count" type="text" value="">
                            </td>
                        </tr>
                        <tr>
                            <td class="xjdpzzh-left"><span class="red">*</span>排序：</td>
                            <td>
                                <input id="orderby" class="text-inp-big" name="orderby" type="text" value="">
                            </td>
                        </tr>
                        <tr>
                            <td class="xjdpzzh-left"><span class="red">*</span>手机专享：</td>
                            <td>
                                <input name='isphone' checked type='radio' value='0'><span>否</span>
                                <span class='marrig35'></span>
                                <input name='isphone' type='radio' value='1'><span>是</span>
                            </td>
                        </tr>
                        
                        <!--   <tr>
                        	<td class="xjdpzzh-left"><span class="red">*</span>使用平台： </td>
                        	<td>
                        		 <input name='useplatform' checked type='checkbox' value='1'><span>pc端</span>
                                <span class='marrig35'></span>
                                <input name='useplatform' type='checkbox' value='2'><span>app端</span>
                                <span class='marrig35'></span>
                                <input name='useplatform' type='checkbox' value='3'><span>wap端</span>
                                <span class='marrig35'></span>
                                <input name='useplatform' type='checkbox' value='4'><span>微信端</span>
                        	</td>
                    	 
                        </tr> -->
                        <tr>
                            <td class="xjdpzzh-left"></td>
                            <td>
                                <input name="Save" type="button" value="保存" class="big-but">
                                <input id="action" type="hidden" value="addSpikeSpu">
                                <input name="" type="button" value="取消" onclick="formCancel()" class="big-but-huise">
                            </td>
                        </tr>
                    </table>
                </div>

            </form>
        </div>
    </div>
</div>
<script src="/resource/public/seller/js/yxgl/spikeskuSave.js"></script>
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