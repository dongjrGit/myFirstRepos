<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript" src="/resource/public/seller/js/spgl/spfl_spsxj.js"></script>

<script type="text/javascript">
    $(document).ready(function () {
        spgl.bind();
        spgl.Getlist(1);
    })
</script>
<div id="container">
    <div class="allcon">
        <div class="position">
            您所在的位置：商品管理 &gt; 商品管理 &gt; 商品上下架
        </div><!--所在位置信息  结束 -->
        <div class="the-form">
            <form>

                <span><label>商品编号：</label><input id="select_pronum" type="text" class="text-inp"></span>
                <span><label>商品名称：</label><input id="select_proname" type="text" class="text-inp"></span>
                <span>
                    <label>商品状态：</label>
                    <select class="sel-form" id="status_select">
                        <option value="">所有</option>
                        <option value="2">已下架</option>
                        <option value="3">已上架</option>
                        <option value="5">已冻结</option>
                        <option value="6">解冻申请中</option>
                    </select>
                </span>
                <span>
                 <!--    <label>所属分类：</label>
                    <select class="sel_allmost" id="fc_select" onchange="spgl.fatherChange('fc')">
                        <script id="flist" type="text/html">
                            {{each list as fclass i}}
                            <option value="{{fclass.id}}">{{fclass.name}}</option>
                            {{/each}}
                        </script>
                    </select>
                    <select class="sel_allmost" id="sc_select" onchange="spgl.fatherChange('sc')">
                        <script id="slist" type="text/html">
                            {{each list as sclass i}}
                            <option value="{{sclass.id}}">{{sclass.name}}</option>
                            {{/each}}
                        </script>
                    </select>
                    <select class="sel_allmost" id="tc_select">
                        <script id="tlist" type="text/html">
                            {{each list as tclass i}}
                            <option value="{{tclass.id}}">{{tclass.name}}</option>
                            {{/each}}
                        </script>
                    </select>   -->
                </span>
                <div class="submit-but">
                    <input id="select_button" type="button" value="查询" class="but-comm">
                    <input type="button" value="选中的商品上架" onclick="spsxjEdit('2,4')" class="but-comm">
                    <input type="button" value="选中的商品下架" onclick="spsxjEdit('3')" class="but-comm">
                    <input name="sqjd" type="button" value="申请解冻" class="but-comm">
                </div>
            </form>
        </div><!--表单部分结束 -->
        <div class="clear"></div>
        <div class="thgl">
            <table>
                <tr>
                    <th width="10%"><input name="chkall" id="selectAll" type="checkbox" value="" ></th>
                    <th width="10%">商品编号</th>
                    <th width="15%">商品名称</th>
                    <th width="*">商品短标题</th>
                    <th width="10%">价格（元）</th>
                    <th width="10%">状态</th>
                    <th width="12%">信息编辑</th>
                </tr>
                <tbody id="datalist">
                    <script id="splist" type="text/html">
                        {{each list as pro i}}
                        <tr>
                            <td><input id="chk_{{pro.id}}" name="chksel" type="checkbox" value="{{pro.id}}_{{pro.status}}"></td>
                            <td>{{pro.proNum}}</td>
                            <td>{{pro.name}}</td>
                            <td>{{pro.titleShort}}</td>
                            <td>{{pro.price}}</td>
                            <td>
                                <div class="pos_rela">
                                    {{if pro.status==2 || pro.status==4}}<span  data-id="{{pro.id}}">已下架</span>
                                    {{else if pro.status==3}}<span  data-id="{{pro.id}}">已上架</span>
                                    {{else if pro.status==5}}<span  data-id="{{pro.id}}">冻结</span>
                                    {{else if pro.status==6}}<span  data-id="{{pro.id}}">解冻申请中</span>
                                    {{/if}}
                                    <div id="divrecord_{{pro.id}}" class="l_xsztcon" style="display:none;">
                                        <div class="l_xtb_xszt"></div>
                                        <div class="l_xszt">
                                            <ul></ul>
                                        </div>
                                    </div>
                                </div>
                            </td>
                            <td class="xxbj">
                                {{if pro.status==2 || pro.status==4}}<a href="javascript:void(0);" data-type="3" data-spuid="{{pro.id}}">上架</a>
                                {{else if pro.status==3}}<a href="javascript:void(0);" data-type="4" data-spuid="{{pro.id}}">下架</a>
                                {{else if pro.status==5}}<a href="javascript:void(0);" data-type="6" data-spuid="{{pro.id}}">申请解冻</a>
                                {{else if pro.status==6}}<span class="huise">解冻申请中</span>
                                {{/if}}
                            </td>
                        </tr>
                        {{/each}}
                    </script>
                </tbody>
            </table>
        </div><!--thgl  stop -->
        <div class="clear"></div>
        <div id="pager" class="page">

        </div>
    </div><!--主要内容 右边结束 -->
</div>
