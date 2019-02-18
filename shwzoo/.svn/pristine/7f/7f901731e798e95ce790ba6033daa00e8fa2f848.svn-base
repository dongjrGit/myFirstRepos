<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="${pageContext.request.contextPath }/resource/public/platform/js/goods/class_specstype.js"></script>

<div class="mainright">
    <div class="clear"></div>
    <div class="dotted mar35"></div>
    <div class="the-form">
        <input id="claid" type="hidden" value="${classid}" />
        
        <input class="inquire" type="button" id="delAll" value="全部删除">
        <input class="inquire" type="button" id="updAll" value="更新排序">
        <input class="inquire" name="addtype" type="button" value="+添加规格类型">
        <input class="inquire" name="reback" type="button" value="返回商品分类">
        <div style="margin-top:8px;">
            <span>所属分类：</span><span>${Fname}</span><span>--></span><span>${Sname}</span><span>--></span><span>${Tname}</span>
        </div>
    </div>
    <div class="clear"></div>
    <div class="table-con">
        <table class="data_list">
            <tr id="type_title">
                <th><input type="checkbox" name="ch_All" id="ch_All" value="" /></th>
                <th width="20%">规格类型名称</th>
                <th width="15%">排序</th>
                <th width="20%">状态</th>
                <th width ="30%">操作</th>
            </tr>
            <tbody id="datalist">
                <script id="typelist" type="text/html">
                    {{each list as pro i}}
                    <tr>
                        <td><input type="checkbox" name="ck_list" value="{{pro.id}}" /></td>
                        <td>{{pro.name}}</td>
                        <td><input class="data_list_cs" id='ob_{{pro.id}}' name="" type="text" value='{{pro.orderby}}' onfocus="if(this.value==defaultValue) {this.value='';}" onblur="if(!value) {value=defaultValue; this.type='text';}"> <span class="lvs"><a href="javascript:void(0);" onclick="setOrder({{pro.id}},'ob_{{pro.id}}')">保存</a></span></td>
                        {{if pro.status==0}}
                    <td id="td_{{pro.id}}">
                        <span class="lvs"><a data-id="{{pro.id}}" data-status="1" class="set" href="javascript:void(0);">启用</a></span>
                    </td>
                    {{else }}
                    <td id="td_{{pro.id}}">
                        <span class="lvs"><a data-id="{{pro.id}}" data-status="0" class="set" href="javascript:void(0);">禁用</a></span>
                    </td>
                    {{/if}}
                        <td>
                            <a href="class_stypeedit?id={{pro.id}}"><span class="shenlan">编辑</span></a>
                            <span class="marrig35"></span>
                             <a href="javascript:void(0);" data-id="{{pro.id}}" class="del"><span class="shenlan">删除</span></a>
                        </td>
                    </tr>
                    {{/each}}
                </script>
            </tbody>
        </table>
    </div>
    <div class="clear"></div>
    <div id="pager" class="page">

    </div>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        var cid = $("#claid").val();

        if (cid == "" || cid == undefined) {
            alert("分类ID为空，请刷新重试");
        }
        else {
            type.bind(1);
        }
    })
</script>
