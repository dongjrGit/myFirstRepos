<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/decorators/getFileUrl.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script src="${pageContext.request.contextPath }/resource/public/commonjs/showImage.js"></script>
<div class="clear"></div>
<div class="mainright">
    <div class="clear"></div>
    <div class="dotted mar35"></div>
    <div class="notice-fenlei">
        <div style="float:left; padding-right:20px;">
            <h1>
                <span>所属商品：${spu.name }</span>
            </h1>
        </div>
        <a href="goods_dpspgl"><input class="inquire" name="backPage" type="button" value="返回商品列表"></a>

    </div><!--notice stop -->
    <div class="clear"></div>
    <div class="mar35"></div>
    <div class="table-con">
        <table class="data_list">
            <thead>
                <tr>
                    <th width="5%">序号</th>
                    <th>图片</th>
                    <th width="10%">排序</th>
                  <!--   <th width="20%">操作</th> -->
                </tr>
            </thead>
            <tbody id="list_title">
            <c:if test="${imglist != null and imglist.size()>0 }">
                   <c:forEach items="${imglist }" var="img" varStatus="i">
                    <tr>
                        <td>${i.index }</td>
                        <td class="skuimg"><img width="300px" height="120px" src="<%=path %>${img.imgurl }" /></td>
                        <td>${img.orderby }</td>
                       <%--  <td>
                            <a href="${pageContext.request.contextPath }/platform/spu/showSpuImgEditor?id=${img.id }&spuid=${spu.id}"><span class="shenlan">编辑</span></a>
                            <a href="javascript:void(0);" class="del" data="${img.id }"><span class="shenlan">删除</span></a>
                        </td> --%>
                    </tr>
                </c:forEach>
                </c:if>
            </tbody>
        </table>
      <!-- 点击查看大图 -->   
        <div id="bigimg" class="l_lbimg" style="display:none;">
            <img alt="" />
            <div class="l_close">X</div>
        </div>
        <div class="clear"></div>
        <div class="page" id="pager">

        </div><!--page stop -->
    </div><!--table-con  stop -->

</div><!--mainright stop -->
<script type="text/javascript">
   $(document).ready(function () {
	   //点击图片查看大图
       $(".skuimg img").each(function () {
           $(this).click(function () {
               if ($(this).attr("src") != "" && $(this).attr("src") != undefined)
                   Show("bigimg", $(this).attr("src"));
           });
       });
        //关闭弹出层事件
        $(".l_close").bind("click", function () { $("#bigimg").hide(); });
        $("#bigimg img").bind("click", Jump);
    }) 
    //点击大图跳转方法
    function Jump() {
	   var imgurl = $("#bigimg img").attr("src");
       imgJump(imgurl);
    }
</script>
