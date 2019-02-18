<!-- 直营商品列表 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
  <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<script type="text/javascript" src="/resource/public/platform/js/product/spgl_zyspgl_spulb.js"></script>
<div class="mainright">
    <div class="clear"></div>
    <div class="dotted mar35"></div>
    <div class="account-form">
        所属分类：
        <select class="the-form-select-one" id="fc_select" onchange="SPU.fatherChange('fc')">
            <script id="flist" type="text/html">
                {{each list as fclass i}}
                <option value="{{fclass.id}}" >{{fclass.name}}</option>
                {{/each}}
            </script>
        </select>-->
        <select class="the-form-select-one" id="sc_select" onchange="SPU.fatherChange('sc')">
            <script id="slist" type="text/html">
                {{each list as sclass i}}
                <option value="{{sclass.id}}" >{{sclass.name}}</option>
                {{/each}}
            </script>
        </select>-->
        <select class="the-form-select-one" id="tc_select">
            <script id="tlist" type="text/html">
                {{each list as tclass i}}
                <option value="{{tclass.id}}" >{{tclass.name}}</option>
                {{/each}}
            </script>
        </select>
        <span class="marrig10"></span>
        <span>商品名称：<input type="text" id="name_select" value="${name }" class="inp-seller" /></span>
        <span class="marrig10"></span>
        <span>商品编号：<input type="text" id="num_select" value="${num }" class="inp-seller" /></span>
        <span class="marrig10"></span>
        <span>
            商品状态：
            <select id="status_select" class="the-form-select-one">
                <option value="">所有</option>
                <option value="4">已下架</option>
                <option value="3">已上架</option>
            </select>
        </span>
        <span class="marrig10"></span>
        <span class="notice-fenlei-mk2"><input class="chaxun" name="select_button" type="button" value="查询"></span>
        <a href="${pageContext.request.contextPath }/platform/product/showAddDirect" target="_self"><input class="chaxun" name="" type="button" value="+添加商品"></a>

    </div>
    <div class="clear"></div>
    <div class="mar35"></div>
    <div class="table-con">
        <table class="data_list">
            <thead>
                <tr>
                    <th width="5%">序号</th>
                    <th>商品名称</th>
                    <th width="10%">商品编码</th>
                    <th width="10%">商品短标题</th>
                    <th width="20%">所属分类</th>
                    <th width="5%">商品状态</th>
                    <th width="30%">操作</th>
                </tr>
            </thead>
            <tbody id="list_title">
                <script id="spulist" type="text/html">
                    {{each list as spu i}}
                    <tr>
                        <td>{{i+1}}</td>
                        <td>{{spu.name}}</td>
                        <td>{{spu.proNum}}</td>
                        <td>{{spu.titleShort}}</td>
                        <td>{{spu.fullPathName}}</td>
                        <td>
                            <div class="pos_rela">
                                {{if spu.status==0}}未审核
                                {{else if spu.status==1}}<span class="l_xsztdj" data-id="{{spu.id}}">提交审核中</span>
                                {{else if spu.status==2 || spu.status==4}}<span class="l_xsztdj" data-id="{{spu.id}}">已下架</span>
                                {{else if spu.status==3}}<span class="l_xsztdj" data-id="{{spu.id}}">已上架</span>
                                {{else}}
                                {{/if}}
                                <div id="divrecord_{{spu.id}}" class="l_xsztcon" style="display:none;">
                                    <div class="l_xtb_xszt"></div>
                                    <div class="l_xszt">
                                        <ul></ul>
                                    </div>
                                </div>
                            </div>
                        </td>
                        <td>
                            {{if spu.status==2 || spu.status==4}}<a href="javascript:void(0);" name="shelve" data_type="3" data="{{spu.id}}"><span class="shenlan">上架</span></a>
                            {{else if spu.status==3}}<a href="javascript:void(0);" name="shelve" data_type="4" data="{{spu.id}}"><span class="shenlan">下架</span></a>
                            {{/if}}
                            <a href="javascript:tpgl({{spu.id}})"><span class="shenlan">图片管理</span></a>
                            <a href="javascript:xg({{spu.id}})"><span class="shenlan">编辑</span></a>
                            <a href="javascript:void(0);" class="del" data="{{spu.id}}"><span class="shenlan">删除</span></a>
                            <a href="javascript:ccsp({{spu.id}})"><span class="shenlan">库存商品管理</span></a>
                        </td>
                    </tr>
                    {{/each}}
                </script>
            </tbody>
        </table>
        <div class="clear"></div>
        <div id="pager" class="page">

        </div><!--page stop -->
       
    </div><!--table-con  stop -->

</div><!--mainright stop -->
<script type="text/javascript">
    $(document).ready(function () {
    	SPU.bind();
    	SPU.getSPUList(1, SPU.unit);
    });
    /*
    	修改
    */
    function xg(id){
    	var ht='/platform/product/showUpdateDirect?id='+id+"&fc="+$("#fc_select option:selected").val();
			ht+='&sc='+$("#sc_select option:selected").val()+"&tc="+$("#tc_select option:selected").val();
			ht+='&name='+$("#name_select").val()+"&num="+$("#num_select").val();
			ht+='&status='+$("#status_select option:selected").val();
    	location.href=ht;
    }
    /*
    	库存商品列表
    */
    function ccsp(id){
    	var ht='/platform/product/goods_zySkuList?spuid='+id+"&fc="+$("#fc_select option:selected").val();
    	ht+='&sc='+$("#sc_select option:selected").val()+"&tc="+$("#tc_select option:selected").val();
		ht+='&name='+$("#name_select").val()+"&num="+$("#num_select").val();
		ht+='&status='+$("#status_select option:selected").val();
		location.href=ht;
    }
    
    /*
		图片管理列表
	*/
	function tpgl(id){
		var ht='/platform/product/showSpuImg?spuid='+id+"&fc="+$("#fc_select option:selected").val();
		ht+='&sc='+$("#sc_select option:selected").val()+"&tc="+$("#tc_select option:selected").val();
		ht+='&name='+$("#name_select").val()+"&num="+$("#num_select").val();
		ht+='&status='+$("#status_select option:selected").val();
		location.href=ht;
	}
</script>

