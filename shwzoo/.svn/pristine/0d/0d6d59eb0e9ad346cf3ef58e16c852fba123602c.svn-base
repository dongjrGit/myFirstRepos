
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>
<script src="${ctx }/resource/public/seller/js/Zhgl/UserMassages.js"></script>
<div id="container">
    <div class="allcon">
        <div class="position">
            您所在的位置：账号管理 &gt; 个人中心 &gt; 站内消息
        </div><!--所在位置信息  结束 -->
        <div class="the-form">
            <input type="hidden" id="userID" value="${userid }" />
            <form>
                <div class="clear"></div>
                <div class="submit-but">
                   <!-- <input name="cboxall" type="checkbox" id="selectAll" value="全选"> -->
                    <input name="" type="button" value="全部删除" id="delete_all" class="but-comm">
                    <input name="" type="button" value="标记已读" id="read_all" class="but-comm">
                </div>
            </form>
        </div><!--表单部分结束 -->
        <div class="clear"></div>
        <div class="Tab-Control">
            <div id=con>
                <ul id=tags>
                    <li class="selectTag" id="Tabone">
                        <a onclick="selectTab('tagContent0', this)" href="javascript:void(0)">未读消息</a>
                    </li>
                    <li id="Tabtwo">
                        <a onclick="selectTab('tagContent0', this)" href="javascript:void(0)">已读消息</a>
                    </li>
                </ul>
                <div id="tagContent">
                    <div class="tagContent" id="tagContent0" style="display:block">
                        <div class="con15"></div>
                        <table>
                            <tr id="Messagedata">
                                <th><input name="cboxall" type="checkbox" id="selectAll" value="全选"></th>
                                <th>状态</th>
                                <th>发件人</th>
                                <th>标题</th>
                                <th>发送时间</th>
                                <th width="150px">操作</th>
                            </tr>
                            <script type="text/html" id="messagelist">
                                {{each list as mes i}}
                                <tr name="dataTr">
                                    <td><input name="chk_list" id="checklist" type="checkbox" value='{{mes.id}}'></td>
                                    <td>{{if mes.status==0}}已读{{else}}未读{{/if}}</td>
                                    <td>{{mes.sendusername}}</td>
                                    <td>{{mes.title}}</td>
                                    <td>{{mes.sendtimestr}}</td>
                                    <td class="xxbj">
                                        <a href="javascript:void(0);" class="lookinfo">查看</a>
                                        <a href="javascript:void(0);" class="delete">删除</a>
                                    </td>
                                </tr>
                                {{/each}}
                            </script>
                        </table>
                    </div><!--第一个标签的内容  结束 -->

                </div>
            </div>
            <div id="pager" class="page">
                <a href="javascript:void(0);">&lt;</a>
                <a href="javascript:void(0);">1</a>
                <span class="page-hover"><a href="javascript:void(0);">&gt;</a></span>
                <span class="page-style">共1页,</span>
                到第<input name="" type="text" class="page-inp">页
                <input name="" type="button" value="确认" class="page-but">

            </div><!--page stop -->
        </div><!--选项卡Tab-Control  结束-->
    </div><!--主要内容 右边结束 -->
</div>