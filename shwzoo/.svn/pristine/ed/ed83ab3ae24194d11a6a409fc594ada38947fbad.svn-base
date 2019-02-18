<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ include file="/decorators/getFileUrl.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>
<link href="${ctx }/resource/public/commonjs/jQuery.Validate.css" rel="stylesheet" />
<script src="${ctx }/resource/public/commonjs/jquery.validate.js"></script>
<script src="${ctx }/resource/public/commonjs/messages_cn.js"></script>
<script type="text/javascript" src="${ctx }/resource/ajaxfileupload.js"></script>
<script src="${ctx }/resource/public/seller/js/MyShop/BrandAdd.js"></script>
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
    <div class="allcon">
        <div class="position">
            您所在的位置：我的店铺 &gt; 店铺管理 &gt; 添加品牌
        </div><!--所在位置信息  结束 -->
        <div class="dpztbg">
            <form id="brandForm">
                <table>
                    <tr>
                        <td width="120px"><span class="red marrig5">*</span>品牌名称：</td>
                        <td>
                        <input name="brandName" id="brandName" type="text" class="text-inp-dpztbg" required>
                        <input type="hidden" id="imgsrc" value="<%=path %>" />
                        </td>
                    </tr>
                    <tr>
                        <td width="120px"><span class="red marrig5">*</span>品牌描述：</td>
                        <td><input name="brandDesc" id="brandDesc" type="text" class="text-inp-dpztbg" required></td>
                    </tr>
                    <tr>
                        <td width="120px"><span class="red marrig5">*</span>品牌图片：</td>
                        <td>
                            <div class="tjcpxx-con-form1">
                                <div class="tjcpxx-con-form-upimg">
                                    <img id="loadimg" width="120px" height="115px" src="" />
                                </div>
                                <input type="hidden" id="img" name="img" value="" required />
                                <div style=" width:200px;margin-left:10px; float:left;">
                                    <input type="file" id="singlefile" name="pics" />
                                    <a href="javascript:void(0);" style="color:#000"><div class="tjcpxx-con-form-upthis">本地上传</div></a>
                                </div>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td width="120px"><span class="red marrig5">*</span>官网地址：</td>
                        <td><input name="brandUrl" id="brandUrl" type="text" class="text-inp-dpztbg" required></td>
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                        <td style="padding-top:10px;">
                            <input name="" type="button" id="savebtn" value="提交申请" class="big-but">
                            <input name="" type="button" id="backbtn" value="返回" class="big-but-huise">
                        </td>
                    </tr>
                </table>
            </form>
        </div><!--dpztbg  stop-->
    </div><!--主要内容 右边结束 -->
</div>
<script type="text/javascript">
    $(document).ready(function () {
        $("#brandForm").validate();
        $(".tjcpxx-con-form-upthis").click(function () {
            $.ajaxFileUpload({
            	url : "/app/api/img/upload",
				secureuri : false,
				fileElementId : 'singlefile',
				dataType : "json",
				//ftype:上传文件类型（图片文件=1，其他文件=2）
				//relationtype: 模块（会员头像( 0), 店铺(1), 产品 ( 2), 其他 (20);） 可以自由增加
				data : {
					relationtype : 11
				},
                type: 'POST',
                success: function (result) {
                    //  alert(JSON.stringify(result));
                    //$(".url1").html(JSON.stringify(result));
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
                }
            });
        });
    })
</script>
