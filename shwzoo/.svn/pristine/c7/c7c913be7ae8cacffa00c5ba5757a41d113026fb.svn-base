<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/resource/public/platform/js/member/memberlevel.js"></script>

<div class="mainright">
    <div class="clear">
        <input type="hidden" id="isEdOrAd" />
        <input type="hidden" id="meID_hid" />
    </div>
    <div class="dotted mar35"></div>
    <div class="mar35"></div>
    <div class="member-xz">
        <span><input class="inquire" id="addLevel" type="button" value="添加用户等级"></span>
    </div>
    <div class="table-con">
        <table class="data_list">
            <tr id="table_list">
                <th>等级数</th>
                <th>等级名称</th>
                <th>晋级标准</th>
                <th>状态</th>
                <th>操作</th>
            </tr>
            <script id="levellist" type="text/html">
                {{each list as level i}}
                <tr>
                    <td>{{level.level}}</td>
                    <td>{{level.name}}</td>
                    <td>{{level.upstandard}}</td>
                    {{if level.status==0}}
                    <td>正常</td>
                    {{else}}
                    <td>冻结</td>
                    {{/if}}
                    <td class="member-operate">
                        <input type="hidden" value="{{level.id}}">
                        <span class="bjxx shenlan">编辑</span>
                        <span class="delete shenlan">删除</span>
                    </td>
                </tr>
                {{/each}}
            </script>
        </table>
        <div style="display:none" id="eidtdata">

            <form id="form">
                <table>
                    <tr>
                        <td>等级数：</td>
                        <td><input class="dpgl-dpdj" type="text" id="levelnum" name="levelnum"></td>
                    </tr>
                    <tr>
                        <td>等级名称：</td>
                        <td><input class="dpgl-dpdj" id="levelname" name="levelname" type="text"></td>
                    </tr>
                    <tr>
                        <td>晋级标准：</td>
                        <td><input class="dpgl-dpdj" id="levelup" name="levelup" type="text"></td>
                    </tr>
                    <tr>
                        <td>周期：</td>
                        <td><input class="dpgl-dpdj" id="Cycle" name="Cycle" type="text"></td>
                    </tr>
                    <tr>
                        <td>周期单位：</td>
                        <td>
                            <select name="levelsta" id="CycleUnit" class="mar-djmc">
                                <option value="年" selected>年</option>
                                <option value="月">月</option>
                                <option value="日">日</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>下降等级：</td>
                        <td><input class="dpgl-dpdj" id="LevelDown" name="LevelDown" type="text"></td>
                    </tr>
                    <tr>
                        <td>下降积分：</td>
                        <td><input class="dpgl-dpdj" id="PointDown" name="PointDown" type="text"></td>
                    </tr>
                    <tr>
                        <td>状态：</td>
                        <td>
                            <select name="levelsta" id="levelsta" class="mar-djmc">
                                <option value="0" selected>正常</option>
                                <option value="1">冻结</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td><input class="dpgl-dpdj-tjsq" id="submitbt" type="submit" value="确认"></td>
                        <td><input class="dpgl-dpdj-tjsq" id="cancelbt" type="button" value="取消"></td>
                    </tr>
                </table>
            </form>
        </div>

    </div><!--table-con  stop -->


</div><!--mainright stop -->
