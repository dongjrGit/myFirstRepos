<%@ page language="java"  contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script src="/resource/public/platform/js/zlbase/zlbase.js"></script>
<script src="http://malsup.github.io/jquery.form.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
    	base.getlist(1);
    })
    function toedit(obj){
    	location.href='/platform/zlbase/edit';
    }
</script>
<div class="mainright">
    <div class="clear"></div>
    <div class="account-form">
       
        <input class="inquire chaxun" onclick="toedit()" name="btnadd" type="button" value="添加基地信息">
    </div>

    <div class="clear"></div>
    <div class="mar35"></div>
    <div class="table-con">
        <table class="data_list">
            <tr id="pack_title">
                <th width="12%">基地名称</th>
                <th width="12%">联系电话</th>
                <th width="12%">经度</th>
                <th width="12%">纬度</th>
                <th width="20%">基地地址</th>
                <th>操作</th>
            </tr>
            <tbody id="datalist">
                <script id="baselist" type="text/html">
                    {{each list as base i}}
                    <tr>
                        <td>{{base.name}}</td>
                        <td>{{base.tel}}</td>  
                        <td>{{base.longitude}}</td>
                        <td>{{base.latitude}}</td>
                        <td>{{base.provincename}} {{base.cityname}} {{base.areaname}} {{base.address}}</td>
						<td>
							<a href="javascript:void(0);" name="del"><span class="shenlan" onclick="base.del({{base.id}})">删除</span></a>
							<a href="/platform/zlbase/edit?id={{base.id}}" name="del"><span class="shenlan" >修改</span></a>
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