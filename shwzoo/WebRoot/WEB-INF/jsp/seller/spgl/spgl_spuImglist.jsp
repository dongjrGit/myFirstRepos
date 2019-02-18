<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ include file="/decorators/getFileUrl.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resource/artTemplate.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resource/public/seller/js/spgl/spgl_splb.js"></script>

	
<script src="${pageContext.request.contextPath }/resource/public/commonjs/showImage.js"></script>

<div id="container">
    <div class="allcon">
        <div class="position">
            您所在的位置：商品管理 &gt; 标准商品管理 &gt; 商品图片列表
        </div><!--所在位置信息  结束 -->
        <div class="the-form">
            <div style="float:left; padding-right:20px;">
                <h1>
                    <span>所属商品：${spu.name }</span>
                </h1>
            </div>
            <a href="spgl_spuImgAdd?spuid=${spu.id }" target="_self"><input class="but-comm" name="" type="button" value="+添加图片"></a>

            <a href="spgl_spulist"><input class="but-comm" name="backPage" type="button" value="返回商品列表"></a>

        </div>
        <!--notice stop -->
        <div class="clear"></div>
        <div class="thgl">
            <table class="data_list">
                <thead>
                    <tr>
                        <th width="5%">序号</th>
                        <th>图片</th>
                        <th width="10%">排序</th>
                        <th width="20%">操作</th>
                    </tr>
                </thead>
                <tbody id="list_title">
                <c:if test="${imglist != null and imglist.size()>0 }">
                <c:forEach items="${imglist }" var="img" varStatus="i">
                  <tr>
                        <td>${i.index }</td>
                        <td class="skuimg"><img width="300px" height="120px" src="<%=path %>${img.imgurl }" /></td>
                        <td>${img.orderby }</td>
                        <td>
                            <a href="spgl_spuImgEdit?id=${img.id }&spuid=${spu.id}"><span class="shenlan">编辑</span></a>
                            <a href="javascript:void(0);" class="del" data="${img.id }"><span class="shenlan">删除</span></a>
                        </td>
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
</div>
<script type="text/javascript">
    $(document).ready(function () {
        //关闭弹出层事件
        $(".l_close").bind("click", function () { $("#bigimg").hide(); });
        $("#bigimg img").bind("click", Jump);
        $(".del").bind("click", function () {
            var $a = $(this);
            if (confirm("确认要执行此操作吗？")) {
                var id = $a.attr("data");
                $.ajax({
                	url : "/seller/shopproduct/delSpuImage",
                    type: "Post",
                    data: {'id': id},
                    dataType: "json",
                    success: function (data) {
                        if (data.code < 0) {
                            Dalert(data.desc);
                        } else {
                            Dalert("删除成功！", 1000, function () {
                                window.location.reload();
                            })
                        }
                    }
                });
            }
        })
    })
    //点击大图跳转方法
    function Jump() {
        var imgurl = $("#bigimg img").attr("src");
        imgJump(imgurl);
    }
</script>