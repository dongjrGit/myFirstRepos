<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script src="${pageContext.request.contextPath }/resource/public/platform/js/goods/goods_class.js"></script>
<script src="${pageContext.request.contextPath }/resource/public/platform/js/yxgl/manzengSave.js"></script>

<div class="mainright">
    <div class="l_wzmb">
        <div class="l_wzmbtop">
            <ul>
                <li class="sj_hover"><a href="javascript:void(0);">添加满赠活动</a><span class="sj-img"></span></li>

            </ul>
        </div><!--l_wzmbtop   stop -->
        <div class="tjcpxx">
            <div class="tjcpxx-con">
                <div class="tjcpxx-con-con">
                    <form id="form" method="post">
                        <div class="tjcpxx-con-mk">
                            <div class="tjcpxx-con-form-title"><label><span class="red marrig5">*</span>名称：</label></div>
                            <div class="tjcpxx-con-form">
                                <input id="name" class="tjcpxx-fprm-inp" name="name" type="text" value="">
                            </div>
                        </div>

                        <div class="tjcpxx-con-mk">
                            <div class="tjcpxx-con-form-title"><label><span class="red marrig5">*</span>满赠类型：</label></div>
                            <div class="tjcpxx-con-form">
                                <select name="acttype" id="acttype" class="the-form-select" onchange="activity.TypeChange()">
                                    <option value="0">针对金额</option>
                                    <option value="1">针对商品</option>
                                </select>
                            </div>
                        </div>
                        <div id="divje" class="tjcpxx-con-mk">
                            <div class="tjcpxx-con-form-title"><label><span class="red marrig5">*</span>满足条件：</label></div>
                            <div class="tjcpxx-con-form">
                                <input id="fullprice" class="tjcpxx-fprm-inp" style="width:150px;" name="fullprice" type="text" value="">&nbsp;元
                            </div>
                        </div>
                        <div id="divsp" style="display:none">
                            <div class="tjcpxx-con-mk1">
                                <div class="tjcpxx-con-form-title"><label><span class="red marrig5">*</span>商品分类：</label></div>
                                <div class="tjcpxx-con-form1">
                                    <input type="hidden" value="" name="spuid" />
                                    <input type="hidden" value="" id="fid" />
                                    <input type="hidden" value="" id="sid" />
                                    <input type="hidden" value="" id="tid" />
                                    <select name="firstID" id="firstID" onchange="Class.firstChange(Class.callback, 'fc')">
                                        <option value="-1" id="defaultfc" selected>无</option>
                                        <script id="flist" type="text/html">
                                            {{each list as fclass i}}
                                            <option value="{{fclass.id}}">{{fclass.name}}</option>
                                            {{/each}}
                                        </script>
                                    </select>-->
                                    <select name="secondID" id="secondID" onchange="Class.firstChange(Class.callback, 'sc')">
                                        <option value="-1" id="defaultsc" selected>无</option>
                                        <script id="slist" type="text/html">
                                            {{each list as sclass i}}
                                            <option value="{{sclass.id}}">{{sclass.name}}</option>
                                            {{/each}}
                                        </script>
                                    </select>-->
                                    <select name="thirdID" id="thirdID" onchange="activity.getSPU()">
                                        <option value="-1" id="defaulttc" selected>无</option>
                                        <script id="tlist" type="text/html">
                                            {{each list as tclass i}}
                                            <option value="{{tclass.id}}">{{tclass.name}}</option>
                                            {{/each}}
                                        </script>
                                    </select>
                                </div>
                            </div>

                            <div class="tjcpxx-con-mk">
                                <div class="tjcpxx-con-form-title"><label><span class="red marrig5">*</span>标准商品：</label></div>
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
                            <div class="tjcpxx-con-mk">
                                <div class="tjcpxx-con-form-title"><label><span class="red marrig5">*</span>满足条件：</label></div>
                                <div class="tjcpxx-con-form">
                                    <input id="count" class="tjcpxx-fprm-inp" style="width:150px;" name="count" type="text" value="">&nbsp;件
                                </div>
                            </div>
                        </div>
                        
                        <div class="tjcpxx-con-mk">
                            <div class="tjcpxx-con-form-title"><label><span class="red marrig5">*</span>开始时间：</label></div>
                            <div class="tjcpxx-con-form">
                                <input type="text" name="start" id="start" class="Wdate2" onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd HH:mm:ss', maxDate: '#F{$dp.$D(\'end\')}' })" value="" readonly="readonly" />
                            </div>
                        </div>
                        <div class="tjcpxx-con-mk">
                            <div class="tjcpxx-con-form-title"><label><span class="red marrig5">*</span>结束时间：</label></div>
                            <div class="tjcpxx-con-form">
                                <input type="text" name="end" id="end" class="Wdate2" onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd HH:mm:ss', minDate: '#F{$dp.$D(\'start\')}' })" value="" readonly="readonly" />
                            </div>
                        </div>
                        <div class="tjcpxx-con-mk">
                            <div class="tjcpxx-con-form-title"><label><span class="red marrig5">*</span>数量：</label></div>
                            <div class="tjcpxx-con-form">
                                <input id="stock" class="tjcpxx-fprm-inp" style="width:150px;" name="stock" type="text" value="">&nbsp;件
                            </div>
                        </div>
                       <!--  <div class="tjcpxx-con-mk">
                            <div class="tjcpxx-con-form-title"><label><span class="red marrig5">*</span>手机专享：</label></div>
                            <div class="tjcpxx-con-form huise">
                                <input name='isphone' checked type='radio' value='0'><span>否</span>
                                <span class='marrig35'></span>
                                <input name='isphone' type='radio' value='1'><span>是</span>
                            </div>
                        </div> -->
                          <div class="tjcpxx-con-mk">
                            <div class="tjcpxx-con-form-title"><span class="red">*</span>使用平台：</div>
                            <div class="tjcpxx-con-form">
                                <input name='useplatform' checked type='checkbox' value='1'><span>pc端</span>
                                <span class='marrig35'></span>
                                <input name='useplatform' type='checkbox' value='2'><span>app端</span>
                            </div>
                        </div>
                        <div class="tjcpxx-con-mk">
                            <div class="tjcpxx-con-form-title"></div>
                            <div id="divStatus" class="tjcpxx-con-form huise">
                                <input name='status' checked type='radio' value='0'><span>启用</span>
                                <span class='marrig35'></span>
                                <input name='status' type='radio' value='1'><span>禁用</span>

                            </div>
                        </div>

                        <div class="tjcpxx-con-mk">
                            <div class="tjcpxx-con-form-title"></div>
                            <div class="tjcpxx-con-form huise">
                                <input class="preserve-inp" name="Save" type="button" value="保存">
                                <input id="action" type="hidden" value="addFullgift">
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
<script type="text/javascript">
    $(document).ready(function () {
        Class.unit(Class.callback);
    });
    function formCancel() { location.href = "yxgl_FullgiftList";}
</script>
