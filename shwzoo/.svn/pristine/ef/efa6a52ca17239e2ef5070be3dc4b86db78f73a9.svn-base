<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<div class="mainright">
    <div class="l_wzmb">
        <div class="l_wzmbtop">
            <ul>
                <li class="sj_hover"><a href="javascript:void(0);">添加关联分类</a><span class="sj-img"></span></li>

            </ul>
        </div><!--l_wzmbtop   stop -->
        <div class="tjcpxx">
            <div class="tjcpxx-con">
                <div class="tjcpxx-con-con">
                    <form id="form" method="post">
                            <div class="tjcpxx-con-mk1">
                                <div class="tjcpxx-con-form-title"><label><span class="red marrig5">*</span>商品分类：</label></div>
                                <div class="tjcpxx-con-form1">
                                    <input id="ClassID" name="classid" type="hidden" value="0" />
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
                                    <select name="thirdID" id="thirdID">
                                        <option value="0" id="defaulttc" selected>无</option>
                                        <script id="tlist" type="text/html">
                                            {{each list as tclass i}}
                                             <option value="{{tclass.id}}">{{tclass.name}}</option>
                                            {{/each}}
                                        </script>
                                    </select>
                                </div>
                            </div>
                        <div class="tjcpxx-con-mk1">
                            <div class="tjcpxx-con-form-title"><label><span class="red marrig5">*</span>排序：</label></div>
                            <div class="tjcpxx-con-form1">
                                <input id="orderby" class="tjcpxx-fprm-inp" name="orderby" type="text" value="">
                            </div>
                        </div>
                      
                        <div class="tjcpxx-con-mk">
                            <div class="tjcpxx-con-form-title"></div>
                            <div class="tjcpxx-con-form huise">
                                <input class="preserve-inp" name="Save" type="button" value="保存">
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
<script src="${pageContext.request.contextPath }/resource/public/platform/js/goods/goods_class.js"></script>
<script src="${pageContext.request.contextPath }/resource/public/platform/js/yxgl/recommclass.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        if ($("#orderby").val() == "" || $("#orderby").val() == undefined) {
            $("#orderby").val(1);
        }
        Class.unit(Class.callback);
    });
    function formCancel() {
      
        location.href = "yxgl_RecommClassList";      
    }
</script>