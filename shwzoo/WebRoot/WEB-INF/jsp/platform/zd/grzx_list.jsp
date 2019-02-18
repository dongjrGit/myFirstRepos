<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script src="/resource/public/platform/js/SiteManagement/articleslist.js"></script>
<div class="mainright">
    <div class="clear"></div>
    <div class="mar35"></div>
    <div class="member-xz">
        <span><input id="SelectAll" name="SelectAll" type="checkbox" value="">全选</span>
        <span><input class="member-inp" name="" id="delete_all" type="button" value="批量删除"></span>
        <span><input name="" type="text" class="tjcpxx-fprm-inp" style="position:initial" id="ArtTitle" placeholder="请输入文章标题" value="" onfocus="if(this.value==defaultValue) {this.value='';}" onblur="if(!value) {value=defaultValue; this.type='text';}"></span>
        <input name="moduleid" type="hidden" id="moduleid" value="${moduleid}">
        <input class="inquire" name="" type="button" id="searchBtn" value="搜索">
        <span><input class="inquire" name="" id="addbtn" type="button" value="添加文章"></span>
    </div>
    <div class="table-con">
        <table class="data_list">
            <tr id="trlist">
                <th width="80px">选择</th>
                <th>文章标题</th>
                <th>摘要</th>
                <th>副标题</th>
                <th width="140px">所属类目</th>
                <th width="160px">发布时间</th>
                <th width="60px">点击数</th>
                <th width="200px">操作</th>
            </tr>
            <script type="text/html" id="datalist">
                {{each list as art i}}
                <tr>
                     <td><input name="chk_list" type="checkbox" value="{{art.id}}"></td>
                    <td>{{art.title}}</td>
                    <td>{{art.digest}}</td>
                    <td>{{art.bytitle}}</td>
                    <td>{{art.classfy}}</td>
                    <td>{{art._sendtime}}</td>
                    <td>{{art.clicknum}}</td>
                    <td class="zdgl-wzlbbj">
                        <input type="hidden" value="{{art.id}}" />
                        <span class="bjxx shenlan"><a href="javascript:void(0);">编辑</a></span>
                        <span class="delete shenlan"><a href="javascript:void(0);">删除</a></span>
                    </td>
                </tr>
                {{/each}}
            </script>
        </table>
    </div><!--table-con  stop -->

    <div class="page" id="pager">

    </div><!--page stop -->


</div>