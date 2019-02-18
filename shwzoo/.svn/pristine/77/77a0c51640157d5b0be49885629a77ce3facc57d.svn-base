<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 <script type="text/javascript" src="${pageContext.request.contextPath }/resource/public/platform/js/ControlPanel/AraeManagement.js"></script> 
 
<div class="mainright">


    <div class="clear"></div>
    <div class="dotted mar35"></div>
    <div class="notice-fenlei">
       
        <div class="notice-fenlei-mk3"><span id="showSlow" class="unwind"></span><a href="javascript:void(0);">全部展开</a></div>
        <div class="notice-fenlei-mk4"><span class="unwind-one"></span><a href="javascript:void(0);">全部收缩</a></div>
        <div class="notice-fenlei-mk4"><a href="javascript:void(0);"><span class="tjxx">添加</span></a></div>

    </div><!--notice stop -->
    <div class="clear"></div>
    <div class="mar35"></div>
    <div class="table-con">
        <table class="table_list data_list">
            <tr id="datalist">
                <th width="65%">地区名称</th>

                <th>操作</th>
            </tr>
            <!--分类一展开   开始 -->
            <script id="arealist" type="text/html">
                {{each list as al i}}
                <tr class="provinces" style="width:100%">
                    <td colspan="2">
                        <div class="provin-tilte" style="width:100%; height:100%; border-bottom:none;">
                            <table width="100%" class="teblelist">
                                <tr class="tab-bor">
                                    <td class="li-he45 text-left bor-right" width="65%"><span class="unwind-two"></span>{{al.name}}</td>
                                    <td>
                                        <a href="javascript:void(0);"><span class="tjxx" data-tag="3" data-code="{{al.code}}">添加</span><label style="display:none">{{al.code}}</label></a>
                                        <a href="javascript:void(0);"><span class="bjxx" data-tag="3" data-code="{{al.code}}">编辑</span><label style="display:none">{{al.name}}</label></a>
                                        <span class="marrig35"></span><input type="hidden" value="{{al.code}}" name="3" />
                                        <a href="javascript:void(0);"><span class="delete" data-tag="3" data-code="{{al.code}}">删除</span></a>
                                    </td>
                                </tr>
                                {{each al.list as sl i }}
                                <tr class="region city">
                                    <td class="text-left padleft100 bor-right">{{sl.name}}</td>
                                    <td>
										<a href="javascript:void(0);"><span class="tjxx" data-tag="2" data-code="{{sl.code}}">添加</span><label style="display:none">{{sl.code}}</label></a>
                                        <a href="javascript:void(0);"><span class="bjxx" data-tag="2" data-code="{{sl.code}}">编辑</span><label style="display:none">{{sl.name}}</label></a>
                                        <span class="marrig35"></span><input type="hidden" value="{{sl.code}}" name="2" />
                                        <a href="javascript:void(0);"><span class="delete" data-tag="2" data-code="{{sl.code}}">删除</span></a>
                                    </td>
                                </tr>
                                {{each sl.list as tl i}}
                                <tr class="region area">
                                    <td class="text-left padleft130 bor-right">{{tl.name}}</td>
                                    <td>
                                        <a href="javascript:void(0);"><span class="bjxx" data-tag="1" data-code="{{tl.code}}">编辑</span><label style="display:none">{{tl.name}}</label></a>
                                        <span class="marrig35"></span><input type="hidden" value="{{tl.code}}" name="1" />
                                        <a href="javascript:void(0);"><span class="delete" data-tag="1" data-code="{{tl.code}}">删除</span></a>
                                    </td>
                                </tr>
                                {{/each}}
                                {{/each}}
                            </table>
                        </div>
                    </td>
                </tr>
                {{/each}}
            </script>
            <!--分类展开  结束 -->


        </table>
        <div class="preserve">
            
        </div><!--preserve stop -->
        <div class="clear"></div>
        <div class="mar35"></div>

    </div><!--table-con  stop -->

</div><!--mainright stop -->
