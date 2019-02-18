<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8"%>
<%@ include file="/decorators/getFileUrl.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<script src="${pageContext.request.contextPath }/resource/public/commonjs/showImage.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resource/public/platform/js/product/spgl_ppgl.js"></script>

<div class="clear"></div>
<div class="mainright">
    <div class="clear"></div>
    <div class="dotted mar35"></div>
    <div class="account-form">
        <span>品牌名称：<input type="text" id="name_select" class="inp-seller" /></span>
        <span class="marrig10"></span>
        <input class="chaxun" name="select_button" type="button" value="查询" onclick="Brand.getAll(1)">
        <span class="marrig10"></span>
        <a href="${pageContext.request.contextPath }/platform/product/showBrandAdd" target="_self"><input class="chaxun" name="" type="button" value="+添加商品品牌"></a>
    </div><!--notice stop -->
    <div class="clear"></div>
    <div class="mar35"></div>
    <div class="table-con"> 
        <table class="data_list">
            <tr id="list_title">
                <th width="3%">序号</th>
                <th width="20%">品牌名称</th>
                <th width="17%">品牌图片</th>
                <th>品牌描述</th>
                <th width="20%">操作</th>
            </tr>
            <script id="brandlist" type="text/html">
                {{each list as brand i}}
                <tr>
                    <td>{{i+1}}</td>
                    <td>{{brand.name}}</td>
                    <td class="ppimg"><img src="<%=path %>{{brand.img}}" width="100px" height="80px"></td>
                    <td>{{brand.description}}</td>
                    <td>
                        <a href="${pageContext.request.contextPath }/platform/product/showBrandUpdate?id={{brand.id}}" title="修改"><span class="shenlan">修改</span></a>
                        <a href="javascript:void(0);" onclick="Brand.del({{brand.id}})"><span class="shenlan" title="删除">删除</span></a>
                        <a href="${pageContext.request.contextPath }/platform/shop/showBrandShop?id={{brand.id}}"><span class="shenlan">管理品牌店铺</span></a>
                    </td>
                </tr>
                {{/each}}
            </script>
        </table>
        <div id="bigimg" class="l_lbimg" style="display:none;">
            <img alt="" />
            <div class="l_close">X</div>
        </div>
        <div class="clear"></div>
        <div id="pager" class="page">
         
        </div><!--page stop -->
    </div><!--table-con  stop -->

</div><!--mainright stop -->
<script type="text/javascript">
    $(document).ready(function () {
        //关闭弹出层事件
        $(".l_close").bind("click", function () { $("#bigimg").hide(); });
        $("#bigimg img").bind("click", Jump);
        Brand.getAll(1);
    })
    //点击大图跳转方法
    function Jump() {
    	  var imgurl = $("#bigimg img").attr("src");
          imgJump(imgurl);
    }
</script>