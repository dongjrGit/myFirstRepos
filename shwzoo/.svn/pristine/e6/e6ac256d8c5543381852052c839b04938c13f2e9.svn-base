<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8"%>
<%@ include file="/decorators/getFileUrl.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

 <script type="text/javascript" src="${pageContext.request.contextPath }/resource/ajaxfileupload.js"></script> 
<script type="text/javascript">
    function formSubmit() {
        //防止重复提交 点击保存后隐藏按钮
        $("input[name='commit']").hide();
        //TODO 前端校验数据格式
        $.ajax({
            url: "/platform/brand/insertBrand",
            data: $("#form").serialize(),
            type: "Post",
            dataType: "json",
            success: function (data) {
                if (data.code == 0) {
                    Dalert("保存成功！", "", backhref);
                    //window.location.href = "/Platform/sp/goods_shangpinpeizhi_shangpinpinpai";
                } else {
                    $("input[name='commit']").show();
                    Dalert(data.desc);
                }
            }
        });
    }
    function backhref() {
        window.location.href = "/platform/product/showBrand";
    }
</script>
<div class="clear"></div>
<div class="mainright">
    <div class="l_wzmb">
        <div class="l_wzmbtop">
            <ul>
                <li class="sj_hover"><a href="javascript:void(0);">添加商品品牌</a><span class="sj-img"></span></li>

            </ul>
        </div>
        <div class="tjcpxx">
            <div class="tjcpxx-con">
                <div class="tjcpxx-con-con">
                    <form id="form" >
                        <div class="tjcpxx-con-mk1">
                            <div class="tjcpxx-con-form-title1"><label><span class="red marrig5">*</span>商品品牌：</label></div>
                            <div class="tjcpxx-con-form1">
                                <input class="tjcpxx-fprm-inp" name="name" value="" type="text">
                                <span class="huise">商品名称不能为空 长度限制在0-50个字符之间！</span>
                            </div>
                            <input type="hidden" name="id" id="id"/>
                            <input type="hidden" id="imgsrc" value="<%=path %>" />
                        </div><!--tjcpxx-con-mk stop -->
                        <!-- <div class="tjcpxx-con-mk1">
                                <div class="tjcpxx-con-form-title1"><label><span class="red marrig5">*</span>店铺ID：</label></div>
                                <div class="tjcpxx-con-form1">
                                    <input class="tjcpxx-fprm-inp" name="shopid" type="text">
                                </div>
                            </div> --><!--tjcpxx-con-mk stop -->
                        <div class="tjcpxx-con-mk1">
                            <div class="tjcpxx-con-form-title1"><label><span class="red marrig5">*</span>品牌照片：</label></div>
                            <div class="tjcpxx-con-form1">
                                <div class="tjcpxx-con-form-upimg">
                                    <img id="loadimg" width="120px" height="115px" src="" />
                                </div>
                                <input type="hidden" name="img" value="" />
                                    <div style=" width:200px;margin-left:10px; float:left;">
                                        <input type="file" id="singlefile" name="pics" />
                                        <a href="javascript:void(0);" style="color:#000"><div class="tjcpxx-con-form-upthis">本地上传</div></a> 
                                    </div>
                            </div>
                        </div><!--tjcpxx-con-mk stop -->
                        <div class="tjcpxx-con-mk1">
                            <div class="tjcpxx-con-form-title1"></div>
                            <div class="tjcpxx-con-form1" style="position:relative;">
                                <label for="selectimg" class="error"></label>
                                <span class="beizhu-mc-upimg">上传图片要小于500kb</span>
                            </div>
                        </div><!--tjcpxx-con-mk stop -->
                        <div class="tjcpxx-con-mk1">
                            <div class="tjcpxx-con-form-title1"><label>品牌官方地址：</label></div>
                            <div class="tjcpxx-con-form1">
                                <input class="tjcpxx-fprm-inp" name="officialurl" value="" type="text">
                            </div>
                        </div><!--tjcpxx-con-mk stop -->
                        <div class="tjcpxx-con-mk1">
                            <div class="tjcpxx-con-form-title1"><label>品牌备注：</label></div>
                            <div class="tjcpxx-con-form1">
                                <textarea class="tjcpxx-con-form1-text" name="description"></textarea>
                            </div>
                        </div><!--tjcpxx-con-mk stop -->
                        <div class="tjcpxx-con-mk1">
                            <div class="tjcpxx-con-form-title1"></div>
                            <div class="tjcpxx-con-form1 huise" style="padding-top:20px;">
                                <input class="preserve-inp" name="commit" type="button" value="保存" onclick="formSubmit()">
                                <input class="preserve-inp_hs" name="" type="button" value="取消" onclick="formCancel()">
                            </div>
                        </div><!--tjcpxx-con-mk stop -->

                    </form>
                </div>
            </div><!--tjcpxx-con stop -->
        </div><!--tjcpxx stop -->
    </div>
</div><!--mainright stop -->
<script type="text/javascript">
    $(document).ready(function () {
    	var id = $("#id").val();
        $(".tjcpxx-con-form-upthis").click(function () {
            $.ajaxFileUpload({
            	url : "/app/api/img/upload",
				secureuri : false,
				fileElementId : 'singlefile',
				dataType : "json",
				//relationtype: 模块（品牌( 11)）
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
                },
                error:function(e){
                	 alert(JSON.stringify(e));
                }
            });
        });
        
        $("#form").validate({
            rules: {
                name: {
                    required: true,
                    maxlength: 200
                },
                //selectimgs: {required:true},
                officialUrl: {
                    url: true
                },
                desc: {
                    maxlength: 200
                }
            },
            message: {
                name: { required: "品牌名不可为空", maxlength: "最多输入50个汉子" },

                //selectimgs: {
                //    required: "必填"
                //},
                officialUrl: {
                    url: "请输入正确的网址"
                },
                desc: {
                    maxlength: "最多输入50个汉子"
                }
            }, errorPlacement: function (error, element) {
                error.appendTo(element.parent());
            },
            submitHandler: function (form) {
                $(form).ajaxSubmit(formSubmit());
            }
        });
    })
    function formCancel() {
    	window.location.href = "/platform/product/showBrand";
    }
</script>
