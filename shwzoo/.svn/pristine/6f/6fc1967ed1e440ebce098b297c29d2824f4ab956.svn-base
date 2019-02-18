<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>

<script type="text/javascript" src="${ctx }/resource/public/seller/js/Zhgl/BasicInfo.js"></script>
<script type="text/javascript" src="${ctx }/resource/public/commonjs/Birthday-picker.js"></script>
<div id="container">
    <div class="allcon">
        <div class="position">
            您所在的位置：账号管理 &gt; 个人中心 &gt; 个人资料
        </div><!--所在位置信息  结束 -->
        <div class="zhgl-con">
            <div class="zhgl-con-top">
                <div class="zhgl-con-top-title">个人资料</div>
            </div><!--zhgl-con-top  stop -->
            <div class="zhgl-con-con">
                <form id="saveSellerForm" action="#" method="post" onsubmit="return check();">
                    <table>
                        <tr>
                            <td class="xjdpzzh-left"><span class="red">*</span>昵称：</td>
                            <td><input id="text_nickname" name="text_nickname" type="text" class="text-inp-big"></td>
                        </tr>
                        <tr>
                            <td class="xjdpzzh-left"><span class="red">*</span>真实姓名：</td>
                            <td><input id="text_realname" name="text_realname" type="text" class="text-inp-big"></td>
                        </tr>
                        <tr>
                            <td class="xjdpzzh-left">性别：</td>
                            <td>
                                <select id="select_sex" name="select_sex" class="sel-form">
                                    <option value="-1">请选择</option>
                                    <script id="sexselect" type="text/html">
                                        {{each list as value index}}
                                        <option value="{{value.code}}">{{value.name}}</option>
                                        {{/each}}
                                    </script>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td class="xjdpzzh-left">出生日期：</td>
                            <td>
                                <div class="tjcpxx-con-form1 div_birthday">
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td class="xjdpzzh-left"><span class="red">*</span>固定电话：</td>
                            <td><input id="text_phone" name="text_phone" type="text" class="text-inp-big"></td>
                        </tr>
                        <tr>
                            <td class="xjdpzzh-left"><span class="red">*</span>所在地区：</td>
                            <td>
                                <select id="select_province" name="select_province" class="sel-form">
                                    <option value="-1">请选择</option>
                                    <script id="proviceselect" type="text/html">
                                        {{each list as Pro index}}
                                        <option value="{{Pro.code}}">{{Pro.name}}</option>
                                        {{/each}}
                                    </script>
                                </select>
                                <select id="select_city" name="select_city" class="sel-form">
                                    <option value="-1">请选择</option>
                                    <script id="cityselect" type="text/html">
                                        {{each list as Pro index}}
                                        <option value="{{Pro.code}}">{{Pro.name}}</option>
                                        {{/each}}
                                    </script>
                                </select>
                                <select id="select_area" name="select_area" class="sel-form">
                                    <option value="-1">请选择</option>
                                    <script id="areaselect" type="text/html">
                                        {{each list as Pro index}}
                                        <option value="{{Pro.code}}">{{Pro.name}}</option>
                                        {{/each}}
                                    </script>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td class="xjdpzzh-left"><span class="red">*</span>详细地址：</td>
                            <td><input id="text_address" name="text_address" type="text" class="text-inp-big"></td>
                        </tr>
                        <tr>
                            <td class="xjdpzzh-left">邮政编码：</td>
                            <td><input id="text_postcode" name="text_postcode" type="text" class="text-inp-big"></td>
                        </tr>
                        <tr>
                            <td class="xjdpzzh-left">证件类型：</td>
                            <td>
                                <select id="select_cardtype" name="select_cardtype" class="sel-form">
                                    <option value="-1">请选择</option>
                                    <script id="cardtypeselect" type="text/html">
                                        {{each list as value index}}
                                        <option value="{{value.code}}">{{value.name}}</option>
                                        {{/each}}
                                    </script>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td class="xjdpzzh-left">证件号码：</td>
                            <td><input id="text_card" name="text_card" type="text" class="text-inp-big"></td>
                        </tr>
                        <tr>
                            <td class="xjdpzzh-left">月收入水平：</td>
                            <td>
                                <select id="select_incomemonth" name="select_incomemonth" class="sel-form">
                                    <option value="-1">请选择</option>
                                    <script id="incomemonthtypeselect" type="text/html">
                                        {{each list as value index}}
                                        <option value="{{value.code}}">{{value.name}}</option>
                                        {{/each}}
                                    </script>
                                </select>
                            </td>
                        </tr>
                      <!--   <tr>
                                <td class="xjdpzzh-left" style="vertical-align:top;">备注：</td>
                                <td>
                                    <textarea id="text_mark" name="text_mark" cols="" rows="" class="form-area"></textarea>
                                </td>
                            </tr> -->
                        <tr>
                            <td>&nbsp;</td>
                            <td style="padding-top:10px;">
                                <input type="hidden" id="hidden_userid" value="${userid }" />
                                <input type="hidden" id="hidden_userattrid" value="" />
                                <input name="submit_ok" type="submit" value="提交申请" id="submit_ok" class="big-but">
                            </td>
                        </tr>
                    </table>
                </form>
            </div><!--zhgl-con-con  stop -->
        </div><!--zhgl-con  stop -->
    </div><!--主要内容 右边结束 -->
</div><!--主要内容 右边结束 -->
<script type="text/javascript">
    $(document).ready(function () {
        //初始化
        Init.bind();
        //表单验证
        Vaildate.bind();
    })
</script>
