<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="${pageContext.request.contextPath }/resource/public/platform/js/goods/class_specs.js"></script>

<script src="${pageContext.request.contextPath }/resource/public/platform/js/goods/goods_class.js"></script>

<div class="mainright">
    <div class="clear"></div>
    <div class="dotted mar35"></div>
   <div class="the-form">
        <input id="claid" type="hidden" value="${classid}" />
        <input class="inquire" name="btnadd" type="button" value="+添加商品规格">
        <input class="inquire" name="reback" type="button" value="返回商品分类">
        <div style="margin-top:8px;">
            <span>所属分类：</span><span>${Fname}</span><span>--></span><span>${Sname}</span><span>--></span><span>${Tname}</span>
        </div>
    </div>
    <div class="clear"></div>
    <div class="mar35"></div>
    <div class="table-con">
        <table class="data_list">
            <tr id="specs_title">
                <th width="15%">规格名称</th>
                <th width="30%">显示位置</th>
                <th width="12%">排序</th>
                <th width="15%">状态</th>
                <th>操作</th>
            </tr>
            <tbody id="datalist">
            <script id="specslist" type="text/html">
                {{each list as specs i}}
                <tr>
                    <td>{{specs.name}}</td>
                    {{if specs.displaylocation==null}}
                    <td></td>
                    {{else }}
                    <td>
                        {{if specs.displaylocation.indexOf('1')>=0}}详情列表,{{/if}}
                        {{if specs.displaylocation.indexOf('2')>=0}}商品详情,{{/if}}
                        {{if specs.displaylocation.indexOf('3')>=0}}规格参数{{/if}}
                        {{if specs.displaylocation.indexOf('4')>=0}}购物车列表{{/if}}
                    </td>
                    {{/if}}
                    <td><input class="data_list_cs" id='ob_{{specs.id}}' name="" type="text" value='{{specs.orderby}}' onfocus="if(this.value==defaultValue) {this.value='';}" onblur="if(!value) {value=defaultValue; this.type='text';}"> <span class="lvs"><a href="javascript:void(0);" onclick="setOrder({{specs.id}},'ob_{{specs.id}}')">保存</a></span></td>
                    <td>
                        <span id="td_{{specs.id}}">
                            {{if specs.status==0}}启用{{else }}禁用{{/if}}
                        </span>
                        <span id="istd_{{specs.id}}">
                            {{if specs.isentry==true}}可输入{{else }}不可输入{{/if}}
                        </span>
                    </td>
                        
                    <td>
                       {{if specs.status==0}}
                        <span id="a_{{specs.id}}"><a href="javascript:void(0);" class="setstatus" data-id="{{specs.id}}" data-status="1" ><span class="shenlan">禁用</span></a></span>
                        {{else }}
                        <span id="a_{{specs.id}}"><a href="javascript:void(0);" class="setstatus" data-id="{{specs.id}}" data-status="0" ><span class="shenlan">启用</span></a></span>
                        {{/if}}
                        {{if specs.isentry==true}}
                        <span id="is_{{specs.id}}">
                            <a data-id="{{specs.id}}" data-isentry="false" class="setentry" ><span class="shenlan">不可输入</span></a>
                        </span>
                        {{else }}
                        <span id="is_{{specs.id}}">
                            <a data-id="{{specs.id}}" data-isentry="true" class="setentry" ><span class="shenlan">可输入</span></a>
                        </span>
                        {{/if}}
                        <a href="class_specsedit?id={{specs.id}}"><span class="shenlan">编辑</span></a>

                        <a href="javascript:void(0);" data-id="{{specs.id}}" class="del"><span class="shenlan">删除</span></a>
                        {{if specs.isentry==false}}
                        <a id="IsEntryShow_{{specs.id}}" style="display:inline-block" onclick="getvalues('{{specs.name}}',{{specs.id}})"><span class="shenlan">属性值</span></a>
                        {{else}}
                        <a id="IsEntryShow_{{specs.id}}" style="display:none" onclick="getvalues('{{specs.name}}',{{specs.id}})"><span class="shenlan">属性值</span></a>
                        {{/if}}
                    </td>
                </tr>
                {{/each}}
            </script>
        </tbody>

        </table>
        <div class="clear"></div>
        <div id="pager" class="page">

        </div>
    </div><!--table-con  stop -->
</div>
<script type="text/javascript">
    $(document).ready(function () {
        specs.bind(1);
    })
</script>
