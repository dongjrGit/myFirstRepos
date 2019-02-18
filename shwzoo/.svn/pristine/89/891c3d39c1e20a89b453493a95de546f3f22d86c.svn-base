<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>
   
<link href="${ctx }/resource/public/seller/css/pager2.css" rel="stylesheet" />
<script src="${ctx }/resource/public/seller/js/pager2.js"></script>
<script src="${ctx }/resource/public/seller/js/MyShop/BrandInfo.js"></script>
<div id="container">
    <div class="allcon">
        <div class="position">
            您所在的位置：我的店铺 &gt; 店铺信息及资质管理 &gt; 品牌信息
        </div><!--所在位置信息  结束 -->
        <div class="clear"></div>
        <div class="the-form">
            <form>
                <div class="clear"></div>
              <!--  <span></span> -->

                <div class="submit-but">
                    <label>品牌名称：</label><input name="" id="brandName" type="text" class="text-inp">
                    <input name="" type="button" id="searchBrand" value="查询" class="but-comm">
                    <input name="" type="button" value="清空" id="clearSearch" class="but-comm">
                    <input name="" type="button" id="addBrand" value="申请新品牌" class="but-comm">
                </div>
            </form>
        </div><!--表单部分结束 -->
        <div class="clear"><input type="hidden" id="shopID" value="${shopid }"></div>
        <div class="thgl">
            <table>
                <!-- <tr class="blank-tr"><td colspan="8"><div style="height:10px;"></div></td></tr> -->
                <tr id="datalist">
                    <th>序号</th>
                    <th>品牌名称</th>
                    <th>品牌说明</th>
                    <th>创建时间</th>
                    <th>审核状态</th>
                    <th>审核时间</th>
                    <th>操作</th>
                </tr>
                <script type="text/html" id="brandlist">
                    {{each list as bra i}}
                    <tr>
                        <td>{{i+1}}</td>
                        <td>{{bra.brandname}}</td>
                        <td>{{bra.description}}</td>
                        <td>{{bra.creattimestr}}</td>
                        <td>
                            {{if bra.checkstatus==0}}待审核
                            {{else}}
                            {{if bra.checkstatus==1}}审核通过
                            {{else bra.checkstatus==2}}审核未通过
                            {{/if}}
                            {{/if}}
                        </td>
                        <td>{{bra.checktimestr}}</td>
                        <td><input type="hidden" id="brandID" value="{{bra.id}}" /><span class="lvse">删除</span></td>
                    </tr>
                    {{/each}}
                </script>
            </table>
        </div><!--thgl 表格部分结束 -->
        <div class="page" id="pager">

        </div><!--page stop -->

    </div><!--主要内容 右边结束 -->
</div>
<script type="text/javascript">
$(document).ready(function() {
	var s = $("#shopID").val();
	var name = $("#brandName").val();
	Brand.bind(s, 1, name);
})
</script>
