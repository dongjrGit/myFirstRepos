<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <script type="text/javascript" src="${pageContext.request.contextPath }/resource/public/platform/js/member/p_Massage.js"></script>

<div class="mainright">
    <!--l_wzmb  开始 -->
    <div ><!-- class="l_wzmb" --><input type="hidden" value="${userid }" id="userID" />
        <div class="l_wzmbtop">
            <ul>
                <li id="one1" class="sj_hover"><a href="javascript:void(0);">新消息</a><span class="sj-img"></span></li>
                <li class="l_wzmbtop_sx">|</li>
                <li id="one2"><a href="javascript:void(0);">已读消息</a></li>
            </ul>
        </div><!--l_wzmbtop   stop -->
        <div style="margin-top:20px; padding:10px;">
            <div class="members-form">
                <form>
                    <span>收件人：<input class="members-form-inp" id="sName" type="text"></span>
                    <span>发送日期：</span>
                    <input type="text" id="time" onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd', maxDate: '#F{$dp.$D(\'time1\')}' })" value="" readonly="readonly" class="Wdate" />-
                    <input type="text" id="time1" onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd',minDate: '#F{$dp.$D(\'time\')}' })" value="" readonly="readonly" class="Wdate" />

                    <span><input class="inquire" id="searchmes" type="button" value="搜索"></span>
                </form>
            </div><!--account-form stop -->
            <div class="member-xz" style="margin-top:10px;">
                <!-- <span><input id="SelectAll" name="check" type="checkbox" value="">全选</span> -->
                <span><input class="member-inp" id="delete_all" type="button" value="批量删除"></span>
                <span><input class="member-inp" id="read_all" type="button" value="标记已读"></span>
            </div>
            <div class="clear"></div>
            <div class="table-con" id="divshow">
                <table class="data_list">
                    <tr id="Messagedata">
                        <th><input id="SelectAll" name="check" type="checkbox" value=""></th>
                        <th>发件人</th>
                        <th>收件人</th>
                        <th>标题</th>
                        <th>时间</th>
                        <th>操作</th>
                    </tr>
                    <script id="messagelist" type="text/html">
                        {{each list as mes i}}
                        <tr name="dataTr">
                            <td><input name="chk_list" type="checkbox" value='{{mes.id}}'></td>
                            <td>{{mes.sendusername}}</td>
                            <td>{{mes.username}}</td>
                            <td>{{mes.title}}</td>
                            <td>{{mes.sendtimestr}}</td>
                            <td class="member-operate">
                                <span class="bjxx shenlan">查看</span>
                                <span class="delete shenlan">删除</span>
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
