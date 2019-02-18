<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript" src="${pageContext.request.contextPath }/resource/public/platform/js/goods/goods_values.js"></script>


    <div class="table-con">
        <table class="data_list">
            <tr id="value_title">
                <th width="40%">属性值</th>
                <th width="20%">状态</th>
                <th>操作</th>
            </tr>
            <tbody id="datalist">
            <script id="valuelist" type="text/html">
                {{each list as value i}}
                <tr>
                    <td width="40%">{{value.value}}</td>
                    <td id="td_{{value.id}}">
                        {{if value.status==0}}
                        <span class="lvs"><a id="a_{{value.id}}" href="javascript:void(0);" onclick="setStatus({{value.id}},1)">启用</a></span>
                        {{else }}
                        <span class="lvs"><a id="a_{{value.id}}" href="javascript:void(0);" onclick="setStatus({{value.id}},0)">禁用</a></span>
                        {{/if}}
                    </td>
                    <td>
                        <a href="javascript:void(0);" onclick="show()"><span class="shenlan">新增</span></a>
                        <span class="marrig35"></span>
                        <a href="javascript:void(0);" onclick="delvalue({{value.id}})" ><span class="shenlan">删除</span></a> 
                </tr>
                {{/each}}
            </script>
            </tbody>
        </table>
    </div>
<div class="clear"></div>
<div id="pager" class="page">

</div>
<div class="clear"></div>
<div id="the-form-value" class="the-form" style="display:none" >
    <form>
    <input id="specsid" type="hidden" value="${specsid}" />
        <div class="the-form-mk">
            <span class="the-form-mk-title">属性值：</span>
            <input id="txtvalue" name="value" type="text" class="the-form-inp" value="" />
        </div>
        <span class="marrig10"></span>
        <input class="inquire" onclick="addvalue()" name="Save" type="button" value="添加">
        <span class="marrig10"></span>
        <input class="inquire" onclick="cancel()" type="button" value="取消">
    </form>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        bind(1);
    })
</script>