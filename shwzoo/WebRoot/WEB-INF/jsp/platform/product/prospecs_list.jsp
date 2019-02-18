<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="${pageContext.request.contextPath }/resource/public/platform/js/goods/goods_productspecs.js"></script>

<script src="${pageContext.request.contextPath }/resource/public/platform/js/goods/goods_class.js"></script>

<div class="mainright">
    <div class="clear"></div>
    <div class="dotted mar35"></div>
    <div class="account-form">
        <span>商品分类：</span>
        <input type="hidden" value="" id="fid" />
        <input type="hidden" value="" id="sid" />
        <input type="hidden" value="" id="tid" />
        <select class="the-form-select-one" name="firstID" id="firstID" onchange="Class.firstChange(Class.callback, 'fc')" onclick="initlist()">
            <option value="0" id="defaultfc" selected>无</option>
            <script id="flist" type="text/html">
                {{each list as fclass i}}
                <option value="{{fclass.id}}">{{fclass.name}}</option>
                {{/each}}
            </script>
        </select>-->
        <select class="the-form-select-one" name="secondID" id="secondID" onchange="Class.firstChange(Class.callback, 'sc')" onclick="initlist()">
            <option value="0" id="defaultsc" selected>无</option>
            <script id="slist" type="text/html">
                {{each list as sclass i}}
                <option value="{{sclass.id}}">{{sclass.name}}</option>
                {{/each}}
            </script>
        </select>-->
        <select class="the-form-select-one" name="thirdID" id="thirdID" onchange="initlist()">
            <option value="0" id="defaulttc" selected>无</option>
            <script id="tlist" type="text/html">
                {{each list as tclass i}}
                <option value="{{tclass.id}}">{{tclass.name}}</option>
                {{/each}}
            </script>
        </select>
        <span class="marrig10"></span>
        <span>规格类型：</span>
        <select id="TypeID" name="" class="the-form-select-one">
        <option value="0" id="defaulttc" selected>无</option>
        </select>
        <span class="marrig10"></span>
        <input class="inquire" name="btnsearch" type="button" value="查询">
        <span class="marrig10"></span>
        <input class="inquire" name="btnadd" type="button" value="+添加产品规格">
    </div><!--notice stop -->
    <div class="clear"></div>
    <div class="mar35"></div>
    <div class="table-con">
        <table class="data_list">
            <tr id="specs_title">
                <th width="15%">规格名称</th>
                <th width="20%">显示位置</th>
                <th width="20%">所属分类</th>
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
                    {{else }}
                    <td>
                        {{if specs.displaylocation.indexOf('1')>=0}}详情列表,{{/if}}
                        {{if specs.displaylocation.indexOf('2')>=0}}商品详情,{{/if}}
                        {{if specs.displaylocation.indexOf('3')>=0}}规格参数{{/if}}
                        {{if specs.displaylocation.indexOf('4')>=0}}购物车列表{{/if}}
                    </td>
                    {{/if}}

                    <td>{{specs.classFullName}}</td>
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
                        <a href="/platform/product/prospecs_edit?id={{specs.id}}"><span class="shenlan">编辑</span></a>

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
        //specs.bind(0, 0, 1);
        Class.unit(Class.callback);
        specs.bind(1);
    })
</script>
