<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/decorators/getFileUrl.jsp"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="${pageContext.request.contextPath }/resource/ajaxfileupload.js"></script>

<script type="text/javascript">
    function formSubmit() {
        //var ss = $("#form").submit()  /yxgl_GroupBuyImgList
        var action = $("#action").val();
        $("input[name='commit']").hide();
        $.ajax({
            url: "/seller/shopgroupbuy/"+action,
            data: $("#form").serialize(),
            type: "Post",
            dataType: "json",
            success: function (data) {
                if (data.code == 0) {
                	
                    Dalert("保存成功！", "", function () {
                        window.location.href = "/seller/YxglShop/yxgl_GroupBuyImgList?id=" + $("#groupid").val();
                    });
                } else {
                	$("input[name='commit']").show();
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
            您所在的位置：营销管理 &gt; 团购图片管理 &gt; 编辑团购图片
        </div><!--所在位置信息  结束 -->
        <div class="zhgl-con">
            <div class="zhgl-con-top">
                <div class="zhgl-con-top-title">编辑团购图片</div>
            </div>
            <div class="zhgl-con-con">
                <form id="form">
                    <table>
                        <tr>
                            <td class="xjdpzzh-left"><label><span class="red marrig5">*</span>团购图片：</label></td>
                            <td>
                                <input type="hidden" id="groupid" name="groupid" value="${data.groupbyid }" />
                                <input type="hidden" id="id" name="id" value="${data.id }" />
                                 <input type="hidden" id="imgsrc" value="<%=path %>" />
                                 <input id="action" type="hidden" value="${action}" />
                                <div class="tjcpxx-con-form">
                                    <div class="tjcpxx-con-form-upimg"><img id="loadimg" width="120px" height="115px" src="<%=path %>${data.imgurl}" /></div>
                                    <div style=" width:200px;margin-left:10px; float:left;">
                                        <input type="file" name="pics" id="singlefile" />
                                        <input type="hidden" name="img" value="${data.imgurl}" />
                                        <a href="javascript:void(0);" style="color:#000"><div class="tjcpxx-con-form-upthis">本地上传</div></a>
                                    </div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td class="xjdpzzh-left"><label><span class="red marrig5">*</span>排序：</label></td>
                            <td>
                                <input class="text-inp-big" name="sort" id="sort"  type="text" value="${data.sort}">
                            </td>
                        </tr>
                        <tr>
                            <td class="xjdpzzh-left"><label><span class="red marrig5">*</span>状态：</label></td>
                            <td>
                              <c:choose>
									<c:when test="${data.status==null }">
									    <input name="status" checked type='radio' value='0'>
										<span>启用</span>
										<span class='marrig35'></span>
										<input name='status' type='radio' value='1'>
										<span>禁用</span>
									</c:when>
									<c:when test="${data.status==0 }">
										<input name="status" checked type='radio' value='0'>
										<span>启用</span>
										<span class='marrig35'></span>
										<input name='status' type='radio' value='1'>
										<span>禁用</span>
									</c:when>
									<c:otherwise>
										<input name='status' type='radio' value='0'>
										<span>启用</span>
										<span class='marrig35'></span>
										<input name='status' checked type='radio' value='1'>
										<span>禁用</span>
									</c:otherwise>
								</c:choose>
                            </td>
                        </tr>
                        <tr>
                            <td class="xjdpzzh-left"></td>
                            <td>
                                <input class="big-but" name="commit" type="button" value="保存" onclick="formSubmit()">
                                <a href="yxgl_GroupBuyImgList?id=${data.groupbyid }"><input class="big-but-huise" type="button" value="取消" onclick="backhref()"></a>
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
					relationtype : 2
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
                        $("#loadimg").attr("src", "");
                    //TODO 结束正在加载中
                },
                error:function(e){
                	 alert(JSON.stringify(e));
                }
            });
        });
        if ($("#sort").val() == "" || $("#sort").val() == undefined) {
            $("#sort").val(1);
        }
    })
</script>