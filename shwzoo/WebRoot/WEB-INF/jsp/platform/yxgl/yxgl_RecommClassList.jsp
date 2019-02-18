<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script src="${pageContext.request.contextPath }/resource/public/platform/js/yxgl/recommclass.js"></script>
<div class="mainright">
    <div class="clear"></div>
    <div class="account-form">
        <div class="martop8">
            <input class="inquire chaxun" type="button" id="updAll" value="更新排序">
            <input class="inquire chaxun" name="btnadd" type="button" value="+添加商品分类">
        </div>
    </div>

<div class="clear"></div>
<div class="mar35"></div>
<div class="table-con">
    <table class="data_list">
        <tr id="type_title">
            <th width="10%"><input type="checkbox" name="ch_All" id="ch_All" value="" /></th>
            <th width="20%">分类名称</th>
             <th width="40%">分类全路径</th>
            <th width="15%">排序</th>
            <th>操作</th>
        </tr>
        <tbody id="datalist">
            <script id="recommlist" type="text/html">
                {{each list as pro i}}
                <tr>
                    <td><input type="checkbox" name="ck_list" value="{{pro.id}}" /></td>
                    <td>{{pro.classname}}</td>
                    <td>{{pro.classpathname}}</td>
                    <td><input class="data_list_cs" id='ob_{{pro.id}}' name="" type="text" value='{{pro.orderby}}' onfocus="if(this.value==defaultValue) {this.value='';}" onblur="if(!value) {value=defaultValue; this.type='text';}"> <span class="lvs"><a href="javascript:void(0);" onclick="setOrder({{pro.id}},'ob_{{pro.id}}')">保存</a></span></td>
                    <td>
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
    	reclass.bind(1);
    })
</script>