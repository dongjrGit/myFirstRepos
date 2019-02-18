<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/resource/public/platform/js/announcement/announcement.js"></script>

<div class="mainright">
    <!--l_wzmb  开始 -->
    <div ><!-- class="l_wzmb" --><input type="hidden"  id="id" name="id" />
        <div style="margin-top:20px; padding:10px;">
            <div class="members-form">
                <form>
                    <!-- <span>发布人：<input class="members-form-inp" id="sName" type="text" value=""></span> -->
                    <span>标题：<input class="members-form-inp" id="sTitle" type="text" value=""></span>
                    <span>发布日期：</span>
                    <input type="text" id="time" onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd', maxDate: '#F{$dp.$D(\'time1\')}' })" value="" readonly="readonly" class="Wdate" />-
                    <input type="text" id="time1" onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd',minDate: '#F{$dp.$D(\'time\')}' })" value="" readonly="readonly" class="Wdate" />
                    <span><input class="inquire" id="searchtitle" type="button" value="搜索"></span>
                    <span><a href="/platform/announcement/announcementAdd" target="_self"><input class="inquire" name="" type="button" value="+添加通告"></a></span>
                </form>
            </div><!--account-form stop -->
            <div class="member-xz" style="margin-top:10px;">
                <!-- <span><input id="SelectAll" name="check" type="checkbox" value="">全选</span> -->
                <span><input class="inquire" id="delete_all" type="button" value="批量删除"></span>
                <span><input class="inquire" type="button" id="updAll" value="更新排序"></span>
            </div>
            <div class="clear"></div>
            <div class="table-con" id="divshow">
                <table class="data_list">
                    <tr id="Announcedata">
                        <th><input id="SelectAll" name="ch_All" type="checkbox" value=""></th>
                        <th>标题</th>
                        <th>内容</th>
                        <th>创建时间</th>
                        <!-- <th>发送人</th> -->
                        <th>排序</th>
                        <th>操作</th>
                    </tr>
                    <script id="announcementlist" type="text/html">
              			 {{each list as annu i}}
                        <tr name="dataTr">
                            <td><input name="chk_list" type="checkbox" value='{{annu.id}}'></td>
                            <td>{{annu.title}}</td>
                            <td>{{annu.content}}</td>
							<td>{{annu.creattimestr}}</td>
							<td><input class="data_list_cs" id='ob_{{annu.id}}' name="" type="text" value='{{annu.sort}}' onfocus="if(this.value==defaultValue) {this.value='';}" onblur="if(!value) {value=defaultValue; this.type='text';}"> <span class="lvs"><a href="javascript:void(0);" onclick="setOrder({{annu.id}},'ob_{{annu.id}}')">保存</a></span></td>
							<td class="member-operate">
                                <span class="cxtt shenlan">编辑</span>
                                <span class="delete shenlan">删除</span>
								<span class="marrig35"></span>
                            </td>
                        </tr>
                        {{/each}}
                    </script>
                </table>
            </div><!--table-con  stop -->
            <div class="clear"></div>

            <div class="page" id="pager">


            </div><!--page stop -->
        </div>
    </div>
    <!--l_wzmb  结束 -->
</div><!--mainright stop -->
