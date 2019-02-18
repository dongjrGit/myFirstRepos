<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script src="${pageContext.request.contextPath}/resource/public/seller/js/yxgl/packagesave.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        if ($("#orderby").val() == "" || $("#orderby").val() == undefined) {
            $("#orderby").val(1);
        }
    })

</script>
<div class="container">
    <div class="allcon">
        <div class="position">
            您所在的位置：营销管理 &gt; 组合商品 &gt; 添加组合商品
        </div><!--所在位置信息  结束 -->
        <div class="zhgl-con">
            <div class="zhgl-con-top">
                <div class="zhgl-con-top-title">添加组合商品</div>
            </div><!--zhgl-con-top  stop -->
            <form id="form" method="post">
                <div class="zhgl-con-con">
                    <table>
                        <tr>
                            <td class="xjdpzzh-left"><span class="red">*</span>套餐名称：</td>
                            <td>
                                <input id="name" class="text-inp-big" name="name" type="text" value="">
                            </td>
                        </tr>
                        <tr>
                            <td class="xjdpzzh-left"><span class="red">*</span>数量：</td>
                            <td>
                                <input id="count" class="text-inp-big" style="width:155px;" name="count" type="text" value="">
                            </td>
                        </tr>
                        <tr>
                            <td class="xjdpzzh-left"><span class="red">*</span>开始时间：</td>
                            <td>
                                <input type="text" name="start" id="start" class="Wdate" onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd HH:mm:ss', maxDate: '#F{$dp.$D(\'end\')}' })" value="" readonly="readonly" />
                            </td>
                        </tr>
                        <tr>
                            <td class="xjdpzzh-left"><span class="red">*</span>结束时间：</td>
                            <td>
                                <input type="text" name="end" id="end" class="Wdate" onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd HH:mm:ss', minDate: '#F{$dp.$D(\'start\')}' })" value="" readonly="readonly" />
                            </td>
                        </tr>
                       
                        <tr>
                            <td class="xjdpzzh-left"><span class="red">*</span>排序：</td>
                            <td>
                                <input id="orderby" class="text-inp-big" style="width:155px;" name="orderby" type="text" value="">
                            </td>
                        </tr>
                          <tr>
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
                    	 
                        </tr>
                        <tr>
                            <td class="xjdpzzh-left">&nbsp;</td>
                            <td>
                                <input name='status' checked type='radio' value='0'><span>启用</span>
                                <span class='marrig35'></span>
                                <input name='status' type='radio' value='1'><span>禁用</span>
                            </td>
                        </tr>
                        <tr>
                            <td class="xjdpzzh-left"></td>
                            <td>
                                <input name="Save" type="button" value="保存" class="big-but">
                                <input id="action" type="hidden" value="addPackage">
                                <input name="" type="button" value="取消" onclick="formCancel()" class="big-but-huise">
                            </td>
                        </tr>
                    </table>
                </div>
            </form>
        </div>
    </div>
</div>
<script>
    function formCancel() {

        location.href = "yxgl_PackageList";

    }
</script>