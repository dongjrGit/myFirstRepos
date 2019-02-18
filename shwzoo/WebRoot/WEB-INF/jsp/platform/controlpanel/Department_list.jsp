<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<script src="${pageContext.request.contextPath }/resource/public/platform/js/ControlPanel/departlist.js"></script>

<script type="text/javascript">
    $(document).ready(function () {

        var keywords = $("#keys").val();
        if (isNull(keywords)) keywords = "";
        depart.bind(keywords);
    });

    function isNull(data)
    { return (data == "" || data == undefined || data == null) ? "" : data; }
</script>
<div class="mainright">
    <div class="clear"></div>
    <div class="dotted mar35"></div>
    <div class="notice-fenlei">
        <span>部门名称：</span>
        <input id="keys" name="" type="text" class="inp-seller" value="" />
      <!--  @*<span >
            <input id="keys" class="xc-gjz" style="width:100px" name="" type="text" value="部门名称" >
        </span>*@ --> 
        <span class="marrig10"></span>
        <input class="chaxun" name="search" type="button" value="查询"><span class="marrig10"></span>
        <input class="chaxun" name="add" type="button" value="添加部门"><span class="marrig10"></span>
        <div class="notice-fenlei-mk3"><span class="unwind"></span><a href="javascript:void(0);">全部展开</a></div>
        <div class="notice-fenlei-mk4"><span class="unwind-one"></span><a href="javascript:void(0);">全部收缩</a></div>

    </div><!--notice stop -->
    <div class="clear"></div>
    <div class="mar35"></div>
    <div class="table-con">
        <table class="data_list">
            <tr id="depart_title">
                <th width="50%">部门名称（编号）</th>
                <th width="15%">部门级别</th>
                <th width="15%">状态</th>
                <th width="20%">操作</th>
            </tr>

            <script id="depart_list" type="text/html">
                {{each list as fdepart i}}
                <tr>
                    <td class="li-he45 text-left"><span class="unwind-two"></span>{{fdepart.name}}({{fdepart.num}})</td>
                    <td>{{fdepart.level}}</td>
                    {{if fdepart.status==0}}
                    <td >
                      		 禁用
                    </td>
                    {{else }}
                    <td >
                        		启用
                    </td>
                    {{/if}}
                    <td>
						{{if fdepart.status==0}}
                        <span class="lvs shenlan" id="span_{{fdepart.id}}"><a id="a_{{fdepart.id}}" href="javascript:void(0);" onclick="setStatus({{fdepart.id}},1)">启用</a></span>
                   		 {{else }}
                        <span class="lvs shenlan" id="span_{{fdepart.id}}><a id="a_{{fdepart.id}}" href="javascript:void(0);" onclick="setStatus({{fdepart.id}},0)">禁用</a></span>
                       {{/if}}
                        <a href="/platform/controlpanel/department_Save?id={{fdepart.id}}"><span class="bjxx shenlan">编辑</span></a>
                        <span class="marrig35"></span>
                        <a href="javascript:void(0);" name="del"><span class="delete shenlan" onclick="del({{fdepart.id}})">删除</span></a>
                    </td>
                </tr>
                <tr style="border:none;display:none">
                    <td colspan="5" style="border:none">
                        <div class="tab-scale">
                            <table width="100%" style="width:100%">
                                {{each fdepart.childrens as sdepart j}}
                                <tr class="table-con-mk" style="border:1px solid #ccc;">
                                    <td class="text-left padleft100">{{sdepart.name}}({{sdepart.num}})</td>
                                    <td width="15%">{{sdepart.level}}</td>
                                    {{if sdepart.status==0}}
                    				<td >
                      		 				禁用
                   					</td>
                    				{{else }}
                    				<td>
                        				启用
                   					</td>
                                    {{/if}}
                                    <td width="20%">
										{{if sdepart.status==0}}
                                        <span class="lvs shenlan"><a id="a_{{sdepart.id}}" href="javascript:void(0);" onclick="setStatus({{sdepart.id}},1)">启用</a></span>
                                    	{{else }}
                                        <span class="lvs shenlan"><a id="a_{{sdepart.id}}" href="javascript:void(0);" onclick="setStatus({{sdepart.id}},0)">禁用</a></span>
                                   		 {{/if}}
                                        <a href="/platform/controlpanel/department_Save?id={{sdepart.id}}"><span class="bjxx shenlan">编辑</span></a>
                                        <span class="marrig35"></span>
                                        <a href="javascript:void(0);" name="del"><span class="delete shenlan" onclick="del({{sdepart.id}})">删除</span></a>
                                    </td>
                                </tr>
                                {{each sdepart.childrens as tdepart y}}
                                <tr class="table-con-mk-con" style="border:1px solid #ccc;">
                                    <td class="text-left padleft130">{{tdepart.name}}({{tdepart.num}})</td>
                                    <td width="15%">{{tdepart.level}}</td>
                                    {{if tdepart.status==0}}
                                    <td >
                      		 			禁用
                    				</td>
                    				{{else }}
                   					 <td >
                        				启用
                   					</td>
                                    {{/if}}
                                    <td width="20%">
										{{if tdepart.status==0}}
                                        <span class="lvs shenlan"><a id="a_{{tdepart.id}}" href="javascript:void(0);" onclick="setStatus({{tdepart.id}},1)">启用</a></span>
                                    	{{else }}
                                        <span class="lvs shenlan"><a id="a_{{tdepart.id}}" href="javascript:void(0);" onclick="setStatus({{tdepart.id}},0)">禁用</a></span>
                                   		 {{/if}}
                                        <a href="/platform/controlpanel/department_Save?id={{tdepart.id}}"><span class="bjxx shenlan">编辑</span></a>
                                        <span class="marrig35"></span>
                                        <a href="javascript:void(0);" onclick="del({{tdepart.id}})"><span class="delete shenlan">删除</span></a>
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

        </table>
    </div><!--table-con  stop -->

</div><!--mainright stop -->
