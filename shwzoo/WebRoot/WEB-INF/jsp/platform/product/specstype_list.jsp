<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="${pageContext.request.contextPath }/resource/public/platform/js/goods/goods_specstype.js"></script>
<script src="${pageContext.request.contextPath }/resource/public/platform/js/goods/goods_class.js?v=1.01"></script>
<div class="mainright">
    <div class="clear"></div>
    <div class="account-form">
        <input type="hidden" value="" id="fid" />
        <input type="hidden" value="" id="sid" />
        <input type="hidden" value="" id="tid" />
        <span>商品分类：</span>
        <select class="the-form-select-one" name="firstID" id="firstID" onchange="Class.firstChange(Class.callback, 'fc')">
            <option value="0" id="defaultfc" selected>无</option>
            <script id="flist" type="text/html">
                {{each list as fclass i}}
                <option value="{{fclass.id}}">{{fclass.name}}</option>
                {{/each}}
            </script>
        </select>-->
        <select class="the-form-select-one" name="secondID" id="secondID" onchange="Class.firstChange(Class.callback, 'sc')">
            <option value="0" id="defaultsc" selected>无</option>
            <script id="slist" type="text/html">
                {{each list as sclass i}}
                <option value="{{sclass.id}}">{{sclass.name}}</option>
                {{/each}}
            </script>
        </select>-->
        <select class="the-form-select-one" name="thirdID" id="thirdID">
            <option value="0" id="defaulttc" selected>无</option>
            <script id="tlist" type="text/html">
                {{each list as tclass i}}
                <option value="{{tclass.id}}">{{tclass.name}}</option>
                {{/each}}
            </script>
        </select>
        <span class="marrig10"></span>
        <input class="inquire" name="btnsearch" type="button" value="查询">
        <div class="martop8">
            <input class="inquire" type="button" id="delAll" value="全部删除">
            <input class="inquire" type="button" id="updAll" value="更新排序">
            <input class="inquire chaxun" name="" type="button" value="+添加规格类型">
        </div>
    </div>

<div class="clear"></div>
<div class="mar35"></div>
<div class="table-con">
    <table class="data_list">
        <tr id="type_title">
            <th width="10%"><input type="checkbox" name="ch_All" id="ch_All" value="" /></th>
            <th width="20%">规格类型名称</th>
            <th width="15%">排序</th>
            <th width="15%">所属分类</th>
            <th width="20%">状态</th>
            <th>操作</th>
        </tr>
        <tbody id="datalist">
            <script id="typelist" type="text/html">
                {{each list as pro i}}
                <tr>
                    <td><input type="checkbox" name="ck_list" value="{{pro.id}}" /></td>
                    <td>{{pro.name}}</td>
                    <td><input class="data_list_cs" id='ob_{{pro.id}}' name="" type="text" value='{{pro.orderby}}' onfocus="if(this.value==defaultValue) {this.value='';}" onblur="if(!value) {value=defaultValue; this.type='text';}"> <span class="lvs"><a href="javascript:void(0);" onclick="setOrder({{pro.id}},'ob_{{pro.id}}')">保存</a></span></td>

                        <td>{{pro.fullpathName}}</td>					
					<td id="td_{{pro.id}}">
                            {{if pro.status==0}}
                            <span class="lvs"><a id="a_{{pro.id}}" href="javascript:void(0);" onclick="setStatus({{pro.id}},1)">启用</a></span>
                            {{else }}
                            <span class="lvs"><a id="a_{{pro.id}}" href="javascript:void(0);" onclick="setStatus({{pro.id}},0)">禁用</a></span>
                            {{/if}}
                            
                     </td>
                    <td>
                        <a href="specstype_edit?id={{pro.id}}"><span class="shenlan">编辑</span></a>
                        <span class="marrig35"></span>
                        <a href="javascript:void(0);" data-id="{{pro.id}}" class="del"><span class="shenlan">删除</span></a>
                    </td>
                </tr>
                {{/each}}
            </script>
        </tbody>
    </table>
    <!--  -->
</div>
<div class="clear"></div>
<div id="pager" class="page">

</div>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        Class.unit(Class.callback);
        type.bind(1);
    })
</script>