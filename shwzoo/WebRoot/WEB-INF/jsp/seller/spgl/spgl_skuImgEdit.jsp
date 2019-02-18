<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ include file="/decorators/getFileUrl.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<script type="text/javascript" src="${pageContext.request.contextPath }/resource/ajaxfileupload.js"></script>

<script type="text/javascript">
    function formSubmit() {
        //var ss = $("#form").submit()
         $("input[name=commit]").hide();
        $.ajax({
            url: "/seller/shopsku/editSkuImage",
            data: $("#form").serialize(),
            type: "Post",
            dataType: "json",
            success: function (data) {
                if (data.code == 0) {
                    Dalert("保存成功！", 2000, function () {
                        window.location.href = "spgl_skuImglist?skuid=" + $("#skuid").val();
                    });
                } else {
                	 $("input[name=commit]").show();
                    Dalert(data.desc);
                }
            }
        });
    }
</script>
<style>
    .tjcpxx-con-form-upimg {
        display: table-cell;
        width: 120px;
        height: 115px;
        background: #f4f4f4;
        border: 1px solid #D9D9D9;
        padding: 1px;
        text-align: center;
        line-height: 115px;
        float: left;
        margin: 0px 0px 10px 0px;
    }

    .tjcpxx-con-form-upthis {
        width: 105px;
        height: 35px;
        float: left;
        background: #FFE5E6;
        border: 1px solid #D9D9D9;
        margin: 30px auto auto 5px;
        text-align: center;
        line-height: 35px;
    }
</style>
<div id="container">
    <!--主要内容 左边导航结束 -->
    <div class="allcon">
        <div class="position">
            您所在的位置：商品管理 &gt; 商品图片管理 &gt; 编辑库存商品图片
        </div><!--所在位置信息  结束 -->
        <div class="zhgl-con">
            <div class="zhgl-con-top">
                <div class="zhgl-con-top-title">编辑库存商品图片</div>
            </div>
            <div class="zhgl-con-con">
                <form id="form">
                    <table>
                        <tr>
                            <td class="xjdpzzh-left"><label><span class="red marrig5">*</span>商品图片：</label></td>
                            <td>
                                <input type="hidden" id="skuid" name="skuid" value="${img.skuId }" />
                                <input type="hidden" id="id" name="id" value="${img.id }" />
                                 <input type="hidden" id="imgsrc" value="<%=path %>" />
                                <div class="tjcpxx-con-form">
                                    <div class="tjcpxx-con-form-upimg"><img id="loadimg" width="120px" height="115px" src="<%=path %>${img.imgurl}" /></div>
                                    <div style=" width:200px;margin-left:10px; float:left;">
                                        <input type="file" name="pics" id="singlefile" />
                                        <input type="hidden" name="img" value="${img.imgurl}" />
                                        <a href="javascript:void(0);" style="color:#000"><div class="tjcpxx-con-form-upthis">本地上传</div></a>
                                    </div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td class="xjdpzzh-left"><label><span class="red marrig5">*</span>排序：</label></td>
                            <td>
                                <input class="text-inp-big" name="orderby" id="orderby"  type="text" value="${img.orderby}">
                            </td>
                        <tr>
                            <td class="xjdpzzh-left"><label><span class="red marrig5">*</span>是否APP图片：</label></td>
                            <td>
                            <c:choose>
									<c:when test="${img.imgsite != null}">
										<c:choose>
											<c:when test="${fn:indexOf(img.imgsite,'1')>=0}">
												<input name="imgsite" type="checkbox" checked value="1">
												<span>pc</span>
											</c:when>
											<c:otherwise>
												<input name="imgsite" type="checkbox" value="1">
												<span>pc</span>
											</c:otherwise>
										</c:choose>
										<c:choose>
											<c:when test="${fn:indexOf(img.imgsite,'2')>=0}">
												<input name="imgsite" type="checkbox" checked value="2">
												<span>app</span>
											</c:when>
											<c:otherwise>
												<input name="imgsite" type="checkbox" value="2">
												<span>app</span>
											</c:otherwise>
										</c:choose>
										<c:choose>
											<c:when test="${fn:indexOf(img.imgsite,'3')>=0}">
												<input name="imgsite" type="checkbox" checked value="3">
												<span>wap</span>
											</c:when>
											<c:otherwise>
												<input name="imgsite" type="checkbox" value="3">
												<span>wap</span>
											</c:otherwise>
										</c:choose>									
									</c:when>
									<c:otherwise>
										<input name="imgsite" type="checkbox" value="1">
										<span>pc</span>
										<input name="imgsite" type="checkbox" value="2">
										<span>app</span>
										<input name="imgsite" type="checkbox" value="3">
										<span>wap</span>
									</c:otherwise>

								</c:choose>
                                <%-- <c:choose>
									<c:when test="${img.isapp==1 }">
										<input name='isapp' checked type='radio' value='1'>
										<span>是</span>
										<span class='marrig35'></span>
										<input name='isapp' type='radio' value='0'>
										<span>否</span>
									</c:when>
									<c:otherwise>
										<input name='isapp' type='radio' value='1'>
										<span>是</span>
										<span class='marrig35'></span>
										<input name='isapp' checked type='radio' value='0'>
										<span>否</span>
									</c:otherwise>
								</c:choose> --%>
                            </td>
                        </tr>    
                        </tr>
                        <tr style="display:none;">
                            <td class="xjdpzzh-left"><label><span class="red marrig5">*</span>状态：</label></td>
                            <td>
                                <input class="text-inp-big" type="text" value="" disabled>
                            </td>
                        </tr>
                        <tr>
                            <td class="xjdpzzh-left"></td>
                            <td>
                                <input class="big-but" name="commit" type="button" value="保存" onclick="formSubmit()">
                                <a href="spgl_skuImglist?skuid=${img.skuId }"><input class="big-but-huise" type="button" value="取消" onclick="backhref()"></a>
                            </td>
                        </tr>
                    </table>

                </form>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    $(document).ready(function () {
    	var id = $("#id").val();
        $(".tjcpxx-con-form-upthis").click(function () {
            $.ajaxFileUpload({
            	url : "/app/api/img/upload",
				secureuri : false,
				fileElementId : 'singlefile',
				dataType : "json",
				//ftype:上传文件类型（图片文件=1，其他文件=2）
				//relationtype: 模块（会员头像( 0), 店铺(1), 产品 ( 2), 其他 (20);） 可以自由增加
				data : {
					"relationtype" : 2,
					"type" : 0
				},
                type: 'POST',
                success: function (result) {
                    $("input[name='img']").val(result.data); 
                    if (result.code == 0){
                    	Dalert("上传成功");                    	
                        $("#loadimg").attr("src", $("#imgsrc").val()+result.data[0]);
                    }
                    else
                    {
                   	 $("#loadimg").attr("src", "");
                   	 Dalert("上传图片失败");
                   }
                    //TODO 结束正在加载中
                },
                error:function(e){
                	 alert(JSON.stringify(e));
                }
            });
        });
        if ($("#orderby").val() == "" || $("#orderby").val() == undefined) {
            $("#orderby").val(1);
        }
    })
</script>