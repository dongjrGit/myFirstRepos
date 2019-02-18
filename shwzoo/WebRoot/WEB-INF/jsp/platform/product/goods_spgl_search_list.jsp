<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="${pageContext.request.contextPath }/resource/public/platform/js/product/spgl_searchlb.js"></script>

<div class="clear"></div>
<div class="mainright">
    <div class="clear"></div>
    <div class="dotted mar35"></div>
    <div class="account-form">
        所属分类：
        <select class="the-form-select-one" id="fc_select" onchange="Search.fatherChange(Search.callback, 'fc')">
        	<option value="0" id="defaultfc" selected="">无</option>
            <script id="flist" type="text/html">
                {{each list as fclass i}}
                <option value="{{fclass.id}}" {{if '${fc}' == fclass.id }}selected="selected" {{/if}}>{{fclass.name}}</option>
                {{/each}}
            </script>
        </select>-->
        <select class="the-form-select-one" id="sc_select" onchange="Search.fatherChange(null, 'sc')">
         <option value="0" id="defaultfc" selected="selected">无</option>
            <script id="slist" type="text/html">
                {{each list as sclass i}}
                <option value="{{sclass.id}}"  {{if '${sc}' == sclass.id }}selected="selected" {{/if}}>{{sclass.name}}</option>
                {{/each}}
            </script>
        </select>-->
        <select class="the-form-select-one" id="tc_select" onchange="Search.getSearchList(Search.unit)">
        <option value="0" id="defaultfc" selected="selected">无</option>
            <script id="tlist" type="text/html">
                {{each list as tclass i}}
                <option value="{{tclass.id}}"  {{if '${tc}' == tclass.id }}selected="selected" {{/if}}>{{tclass.name}}</option>
                {{/each}}
            </script>
        </select>
        <span class="marrig10"></span>
        <span>属性名称：<input type="text" id="name_select" class="inp-seller" value="${serachattr.name }" /></span>
        <span class="marrig10"></span>
        <input class="chaxun" name="" type="button" value="查询" onclick="Search.getSearchList(Search.unit)">
        <span class="marrig10"></span>
        <a href="javascript:void(0);" target="_self"><input class="chaxun addnew_button" type="button" value="+添加搜索属性"></a>
    </div><!--notice stop -->

    <div class="clear"></div>
    <div class="mar35"></div>
    <div class="table-con">
        <table class="data_list">
            <thead>
                <tr>
                    <th>序号</th>
                    <th>属性名称</th>
                    <th>排序</th>
                    <th>状态</th>
                    <th>是否多选</th>
                    <th>使用站点</th>
                    <th>操作</th>
                </tr>
            </thead>
            <tbody id="list_title">
                <script id="searchlist" type="text/html">
                    {{each list as attr i}}
                    <tr>
                        <td>{{i+1}}</td>
                        <td>{{attr.name}}</td>
                        <td>{{attr.sort}}</td>
						{{if attr.status == 0}}
                        	<td>启用</td>
						{{else}}
							<td>禁用</td>
						{{/if}}
                        <td>
                            {{if attr.ischeckbox}}是
                            {{else}}否
                            {{/if}}
                        </td>
						<td> {{if attr.usesite!=null&&attr.usesite.indexOf('1')>=0}}pc,{{/if}}
                        {{if attr.usesite!=null&&attr.usesite.indexOf('2')>=0}}app,{{/if}}
                        {{if attr.usesite!=null&&attr.usesite.indexOf('3')>=0}}wap{{/if}}</td>
                        <td>
							
                            <a href="${pageContext.request.contextPath }/platform/product/showSearValue?attrid={{attr.id}}&attrtype={{attr.attrtype}}"><span class="shenlan">属性值管理</span></a>
                            <a href="javascript:void(0);" class="edit" data_radio="{{if attr.ischeckbox}}1{{else}}0{{/if}}" data_name="{{attr.name}}" data_type="{{attr.attrtype}}" data_id="{{attr.id}}" data_order="{{attr.sort}}" data_typeid="{{attr.typeid}}" data_usesite="{{attr.usesite}}"><span class="shenlan">编辑</span></a>
                            <a href="javascript:void(0);" class="del" data="{{attr.id}}"><span class="shenlan">删除</span></a>
							{{if attr.status == 0}}
								<span class="shenlan"><a onclick= "setStatus({{attr.id}},1)">禁用</a></span>
							{{else}}
								<span class="shenlan"><a onclick= "setStatus({{attr.id}},0)">启用</a></span>
							{{/if}}
                        </td>
                    </tr>
                    {{/each}}
                </script>
            </tbody>
            <tr id="addnew_tr" style="display:none;">
                <td class="red">+</td>
                <td>
                    <select id="addnew_type">
                        <option value="2">品牌</option>
                        <option value="1">规格</option>
                        <option value="3">价格</option>
                    </select> 
                    
                    <!--规格选项开始-->
                    <div style="display:none;">
                        <script id="tests" type="text/html">
                            {{each list as specs i}}
                            <option value="{{specs.id}}">{{specs.name}}</option>
                            {{/each}}
                        </script>
                    </div>
                    <!--规格选项消结束-->
                   <select id="addnew_typeid">
                        <option value="0">品牌</option>
                    </select> 
                </td>
                <td><input type="text" id="addnew_order" /></td>
                <td><input type="hidden" id="addnew_id" /></td>
                <td>
                    <input type="radio" name="addnew_ischeckbox" value="0" />否
                    <input type="radio" name="addnew_ischeckbox" value="1" />是
                </td>
                <td>
                 	<input type="checkbox" value="1" id="userSites" name="userSites" />pc
                     <input type="checkbox" value="2"  id="userSites" name="userSites"/>app
                     <input type="checkbox" value="3" id="userSites" name="userSites"/>wap
                </td>
                <td>
                    <a href="javascript:void(0);" id="addnew_submit"><span class="shenlan">保存</span></a>
                </td>
            </tr>
        </table>
        <div class="clear"></div>
        <div id="pager" class="page">

        </div><!--page stop -->
        <div class="clear"></div>
        <div class="mar35"></div>
        <div class="sctp-con">
           
            <div class="clear"></div>
            <div class="preserve">
                <input class="preserve-inp" name="" type="button" value="保存">
            </div><!--preserve stop -->
            <div class="close">X</div>
        </div><!-- sctp-con stop-->
    </div><!--table-con  stop -->

</div><!--mainright stop -->
<script type="text/javascript">
    $(document).ready(function () {
        Search.bind(Search.callback);
    })
</script>
