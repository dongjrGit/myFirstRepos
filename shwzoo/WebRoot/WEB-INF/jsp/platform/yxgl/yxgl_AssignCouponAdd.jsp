<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script src="${pageContext.request.contextPath }/resource/public/platform/js/goods/goods_class.js"></script>
<script src="${pageContext.request.contextPath }/resource/public/platform/js/yxgl/couponassignadd.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        Class.unit(Class.callback);
    })
    function formCancel() {
        location.href = "yxgl_AssignCoupon";
    }
</script>
<div class="mainright">
    <div class="l_wzmb">
        <div class="l_wzmbtop">
            <ul>
                <li class="sj_hover"><a href="javascript:void(0);">分配优惠劵</a><span class="sj-img"></span></li>

            </ul>
        </div><!--l_wzmbtop   stop -->
        <div class="tjcpxx">

            <div class="tjcpxx-con">
                <div class="tjcpxx-con-con">
                    <form id="form" method="post">
                     <div class="tjcpxx-con-mk1">
                            <div class="tjcpxx-con-form-title"><label>优惠劵编码：</label></div>
                            <div class="tjcpxx-con-form1">
                                <input id="code" class="tjcpxx-fprm-inp" name="code" readonly type="text" value="${data.code}">
                            </div>
                        </div>
                         <div class="tjcpxx-con-mk1">
                            <div class="tjcpxx-con-form-title"><label>可分配数量：</label></div>
                            <div class="tjcpxx-con-form1">
                                <input id="lastcount" class="tjcpxx-fprm-inp" name="lastcount" readonly type="text" value="${data.lastcount}">
                            </div>
                        </div>
                        <!-- @*名称*@ -->
                        <div class="tjcpxx-con-mk1">
                            <div class="tjcpxx-con-form-title"><label>名称：</label></div>
                            <div class="tjcpxx-con-form1">
                                <input id="name" class="tjcpxx-fprm-inp" name="name" readonly type="text" value="${data.name}">
                            </div>
                        </div>
                       <!--  @*面值*@ -->
                        <div class="tjcpxx-con-mk1">
                            <div class="tjcpxx-con-form-title"><label>面值：</label></div>
                            <div class="tjcpxx-con-form1">
                                <input name="mjprice" type="hidden" value="${data.quota}">
                                <input id="facevalue" class="tjcpxx-fprm-inp" style="width:150px;" readonly 
                                name="facevalue" type="text" value="${data.value}">&nbsp;元
                            </div>
                        </div>
                      <!--   @*使用对象*@ -->
                        <div class="tjcpxx-con-mk1">
                            <div class="tjcpxx-con-form-title"><label><span class="red marrig5">*</span>使用对象：</label></div>
                            <div class="tjcpxx-con-form1">
                                <select id="usetype" name="usetype" class="the-form-select" onchange="couponassign.Type2Change()">
                                    <option value="0">商品</option>
                                    <option value="1">分类</option>
                                    <option value="2">店铺</option>
                                    <option value="3">全场通用</option>
                                </select>
                            </div>
                        </div>
                         <input id="usetypeid" name="usetypeid" type="hidden" value="" />
                        <div id="divusetype1" class="tjcpxx-con-mk1">
                            <div class="tjcpxx-con-form-title"><label><span class="red marrig5">*</span>商品分类：</label></div>
                            <div class="tjcpxx-con-form1">
                                <input type="hidden" value="" id="fid" />
                                <input type="hidden" value="" id="sid" />
                                <input type="hidden" value="" id="tid" />
                                <select name="firstID" id="firstID" onchange="Class.firstChange(Class.callback, 'fc')">
                                    <option value="0" id="defaultfc" selected>无</option>
                                    <script id="flist" type="text/html">
                                        {{each list as fclass i}}
                                        <option value="{{fclass.id}}">{{fclass.name}}</option>
                                        {{/each}}
                                    </script>
                                </select>-->
                                <select name="secondID" id="secondID" onchange="Class.firstChange(Class.callback, 'sc')">
                                    <option value="0" id="defaultsc" selected>无</option>
                                    <script id="slist" type="text/html">
                                        {{each list as sclass i}}
                                        <option value="{{sclass.id}}">{{sclass.name}}</option>
                                        {{/each}}
                                    </script>
                                </select>-->
                                <select name="thirdID" id="thirdID" onchange="couponassign.getSPU()">
                                    <option value=" 0" id="defaulttc" selected>无</option>
                                    <script id="tlist" type="text/html">
                                        {{each list as tclass i}}
                                        <option value="{{tclass.id}}">{{tclass.name}}</option>
                                        {{/each}}
                                    </script>
                                </select>
                            </div>
                        </div>
                        <div id="divusetype2" class="tjcpxx-con-mk1">
                            <div class="tjcpxx-con-form-title"><label><span class="red marrig5">*</span>商品：</label></div>
                            <div class="tjcpxx-con-form1">
                                <input id="select_spu" type="text" class="tjcpxx-fprm-inp" />
                            </div>
                            <div style="margin-top:25px;margin-left:13px;">
                                <ul>
                                    <script id="select_spulist" type="text/html">
                                        {{each list as spu i}}
                                        <li data="{{spu.id}}">{{spu.name}}</li>
                                        {{/each}}
                                    </script>
                                </ul>
                            </div>
                        </div>
                        <div id="divusetype3" class="tjcpxx-con-mk1" style="display:none;" >
                            <div class="tjcpxx-con-form-title"><label><span class="red marrig5">*</span>所属店铺：</label></div>
                            <div class="tjcpxx-con-form1">
                                <input id="select_shop" type="text" class="tjcpxx-fprm-inp" />
                            </div>
                            <div style="margin-top:25px;margin-left:13px;">
                                <ul>
                                    <script id="select_shoplist" type="text/html">
                                        {{each list as shop i}}
                                        <li data="{{shop.id}}">{{shop.name}}</li>
                                        {{/each}}
                                    </script>
                                </ul>
                            </div>
                        </div>
                         <div class="tjcpxx-con-mk1">
                            <div class="tjcpxx-con-form-title"><label><span class="red marrig5">*</span>数量：</label></div>
                            <div class="tjcpxx-con-form1">
                                <input id="count" class="tjcpxx-fprm-inp" style="width:150px;" name="count" type="text" value="">
                            </div>
                        </div>
                         <div class="tjcpxx-con-mk1">
                            <div class="tjcpxx-con-form-title"><label><span class="red marrig5">*</span>领用类型：</label></div>
                            <div class="tjcpxx-con-form1">
                                <input name='gettype' checked type='radio' value='0'><span>会员领取</span>
                                <span class='marrig35'></span>
                                <input name='gettype' type='radio' value='1'><span>抽奖专用</span>
                            </div>
                        </div>
                        <div class="tjcpxx-con-mk">
                            <div class="tjcpxx-con-form-title"><label>过期时间：</label></div>
                            <div class="tjcpxx-con-form">
                                <input type="text" name="endtime" id="endtime" class="Wdate2" 
                                onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd HH:mm:ss', minDate: '#F{$dp.$D(\'starttime\')}' })" 
                                value="${data.endtimestr}" readonly="readonly" />
                            </div>
                        </div>
                        <div class="tjcpxx-con-mk">
                            <div class="tjcpxx-con-form-title"></div>
                            <div class="tjcpxx-con-form huise">
                                <input class="preserve-inp" name="Save" type="button" value="保存">
                                <input id="action" type="hidden" value="addAssignCoupon">
                                <span class="marrig35"></span>
                                <input class="preserve-inp_hs" name="" type="button" value="取消" onclick="formCancel()">
                            </div>
                        </div><!--tjcpxx-con-mk stop -->


                    </form>
                </div>
            </div>
        </div><!--tjcpxx-con stop -->
    </div>
</div>