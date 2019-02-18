<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="/resource/public/platform/js/product/spgl_flgl.js"></script>
<div class="mainright">
    <div class="clear"></div>
    <div class="dotted mar35"></div>
    <div class="notice-fenlei">
        <div class="notice-fenlei-mk3"><span class="unwind"></span><a href="javascript:void(0);">全部展开</a></div>
        <div class="notice-fenlei-mk4"><span class="unwind-one"></span><a href="javascript:void(0);">全部收缩</a></div>
        <div class="notice-fenlei-mk1">
            <a href="goods_classadd" target="_self"><input class="chaxun" name="" type="button" value="+添加商品分类"></a>
        </div>
    </div><!--notice stop -->
    <div class="clear"></div>
    <div class="mar35"></div>
    <div class="table-con">
        <table class="data_list">
            <tr id="list_title">
                <th width="50%">栏目名称</th>
                <th width="15%">预警数量</th>
                <th width="35%">操作</th>
            </tr>

            <script id="classlist" type="text/html">
                {{each list as fclass i}}
                <tr>
                    <td class="li-he45 text-left"><span class="unwind-two"></span>{{fclass.name}}</td>
                    <td>
                        {{fclass.warnnum}}
                    </td>
                    <td>
                        <a href="goods_classedit?id={{fclass.id}}"><span class="shenlan">编辑</span></a>
 						
                        <a href="class_specslist?cid={{fclass.id}}"><span class="shenlan">商品规格</span></a>
						<a href="class_stypelist?cid={{fclass.id}}"><span class="shenlan">规格类型</span></a>
                        <a href="javascript:void(0);" name="del"><span class="shenlan" onclick="Class.deleteClass({{fclass.id}})">删除</span></a>
                    </td>
                </tr>
                <tr style="border:none;display:none">
                    <td colspan="5" style="border:none">
                        <div class="tab-scale">
                            <table width="100%" style="width:100%">
                                {{each fclass.children as sclass j}}
                                <tr class="table-con-mk" style="border:1px solid #ccc;">
                                    <td class="text-left padleft100" width="50%"><span class="unwind-two" style="margin-top:0px;margin-left:0px;"></span>{{sclass.name}}</td>

                                    <td width="15%">
                                        {{sclass.warnnum}}
                                     </td>

                                    <td width="35%">
                                        <a href="goods_classedit?id={{sclass.id}}"><span class="shenlan">编辑</span></a>
										<a href="class_specslist?cid={{sclass.id}}"><span class="shenlan">商品规格</span></a>
                                                        
										<a href="class_stypelist?cid={{sclass.id}}"><span class="shenlan">规格类型</span></a>
                        
                                        <a href="javascript:void(0);" name="del"><span class="shenlan" onclick="Class.deleteClass({{sclass.id}})">删除</span></a>
                                    </td>
                                </tr>
                                <tr class="table-con-mk-con" style="border:1px solid #ccc;display: none;">
                                    <td colspan="3" style="border:none">
                                        <div class="table-con-mk">
                                            <table width="100%" style="width:100%;height:100%">
                                                {{each sclass.children as tclass y}}
                                                <tr>
                                                    <td class="text-left padleft130" width="50%">{{tclass.name}}</td>
                                                    <td width="15%">
                                                        {{tclass.warnnum}}
                                                    </td>
                                                    <td width="35%">
                                                        <a href="class_specslist?cid={{tclass.id}}"><span class="shenlan">商品规格</span></a>
                                                        <a href="class_stypelist?cid={{tclass.id}}"><span class="shenlan">规格类型</span></a>
                                                        <a href="goods_classedit?id={{tclass.id}}"><span class="shenlan">编辑</span></a>

                                                        <a href="javascript:void(0);" name="del" onclick="Class.deleteClass({{tclass.id}})"><span class="shenlan">删除</span></a>
                                                    </td>
                                                </tr>
                                                {{/each}}
                                            </table>
                                        </div>
                                    </td>
                                </tr>
                                {{/each}}
                            </table>
                        </div>
                        <!--小的一级栏目护肤品   结束 -->
                    </td>
                </tr>
                {{/each}}
            </script>

        </table>

    </div><!--table-con  stop -->

</div><!--mainright stop -->
<script type="text/javascript">
    $(document).ready(function () {
        Class.bind();
    })
</script>