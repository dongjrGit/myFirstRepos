<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ include file="/decorators/getFileUrl.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="${pageContext.request.contextPath }/resource/ajaxfileupload.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resource/public/platform/js/product/spgl_zyskuEdit.js"></script>
<script type="text/javascript">
    function formSubmit() {
        $("input[name='commit']").hide();
        $.ajax({
            url: "/platform/sku/addzySku",
            data: $("#form").serialize(),
            type: "Post",
            dataType: "json",
            success: function (data) {
                if (data.code == 0) {
                    Dalert("保存成功！", "", function () {
                        window.location.href = "/platform/product/goods_zySkuList?spuid=" + $("#spuid").val();
                    });
                } else {
                    $("input[name='commit']").show();
                    Dalert(data.desc);
                }
            }
        });
    }
    function formCancel() {
        window.location.href = "/platform/product/goods_zySkuList?spuid=" + $("#spuid").val();
    }
</script>
<div class="mainright">
    <div class="l_wzmb">
        <form id="form">
            <div class="l_wzmbtop">
                <ul>
                    <li class="sj_hover"><a href="javascript:void(0);">添加库存商品</a><span class="sj-img"></span></li>

                </ul>
            </div><!--l_wzmbtop   stop -->
            <div class="tjcpxx">
                <div class="tjcpxx-con-con">
                    <input type="hidden" value="${spuid}" name="spuid" id="spuid" />
                    <input type="hidden" value="${classid}" name="classid" id="classid" />
                    <input type="hidden" id="imgsrc" value="<%=path %>" />
                    <div class="tjcpxx-con">

                        <div class="tjcpxx-con-mk">
                            <div class="tjcpxx-con-form-title"><label><span class="red marrig5">*</span>库存商品名称：</label></div>
                            <div class="tjcpxx-con-form">
                                <input class="tjcpxx-fprm-inp" name="name" type="text" value="">
                                <input type="hidden" name="id" id="id" value="0" />
                            </div>
                            <div class="remark">库存商品名称不能为空，长度限制在100个字符以内</div>
                        </div><!--tjcpxx-con-mk stop -->
                        <div class="tjcpxx-con-mk">
                            <div class="tjcpxx-con-form-title"><label>副标题：</label></div>
                            <div class="tjcpxx-con-form">
                                <input class="tjcpxx-fprm-inp" name="subtitle" type="text" value="">
                            </div>
                            <div class="remark">长度限制在100个字符以内</div>
                        </div>
                        <div class="tjcpxx-con-mk">
							<div class="tjcpxx-con-form-title">
							<label><span class="red marrig5">*</span>库存图片：</label>
							</div>
							<div class="tjcpxx-con-form">				
                                <div class="tjcpxx-con-form-upimg">
									<img id="loadimg" width="120px" height="115px" src="" />	
                                </div>
                                <input type="hidden" name="img" value="" />
                                     <div style=" width:200px; float:right;">
                                        <input type="file" name="pics" id="singlefile" />
                                        <a href="javascript:void(0);" style="color:#000"><div class="tjcpxx-con-form-upthis">本地上传</div></a> 
                                    </div>
                             </div>
					   </div>
                        <div class="tjcpxx-con-mk">
                            <div class="tjcpxx-con-form-title"><label><span class="red marrig5">*</span>pc价格：</label></div>
                            <div class="tjcpxx-con-form">
                                <input class="tjcpxx-fprm-inp" name="price" type="text" value="">
                            </div>
                        </div><!--tjcpxx-con-mk stop -->
                        <div class="tjcpxx-con-mk">
                            <div class="tjcpxx-con-form-title"><label><span class="red marrig5">*</span>商品原价：</label></div>
                            <div class="tjcpxx-con-form">
                                <input class="tjcpxx-fprm-inp" name="oldprice" type="text" value="">
                            </div>
                        </div><!--tjcpxx-con-mk stop -->
                        <div class="tjcpxx-con-mk">
                            <div class="tjcpxx-con-form-title"><label><span class="red marrig5">*</span>App售价：</label></div>
                            <div class="tjcpxx-con-form">
                                <input class="tjcpxx-fprm-inp" name="appprice" type="text" value="">
                            </div>
                        </div><!--tjcpxx-con-mk stop -->
						<div class="tjcpxx-con-mk">
                            <div class="tjcpxx-con-form-title"><label><span class="red marrig5">*</span>wap售价：</label></div>
                            <div class="tjcpxx-con-form">
                                <input class="tjcpxx-fprm-inp" name="wapprice" type="text" value="">
                            </div>
                        </div>
                        <div class="tjcpxx-con-mk">
                            <div class="tjcpxx-con-form-title"><label><span class="red marrig5">*</span>微信售价：</label></div>
                            <div class="tjcpxx-con-form">
                                <input class="tjcpxx-fprm-inp" name="wechatprice" type="text" value="">
                            </div>
                        </div>
                        
                        <div class="tjcpxx-con-mk">
                            <div class="tjcpxx-con-form-title"><label><span class="red marrig5">*</span>商品库存：</label></div>
                            <div class="tjcpxx-con-form">
                                <input class="tjcpxx-fprm-inp" name="stock" type="text" value="">
                            </div>
                        </div><!--tjcpxx-con-mk stop -->
                        <div class="tjcpxx-con-mk">
                            <div class="tjcpxx-con-form-title"><label><span class="red marrig5">*</span>预警数量：</label></div>
                            <div class="tjcpxx-con-form">
                                <input class="tjcpxx-fprm-inp" name="warnnum" type="text" value="">
                            </div>
                        </div><!--tjcpxx-con-mk stop -->

                        <div class="tjcpxx-con-mk">
                            <div class="tjcpxx-con-form-title"><label>备注：</label></div>
                            <div class="tjcpxx-con-form">
                                <input class="tjcpxx-fprm-inp" name="remark" type="text" value="">
                            </div>
                        </div><!--tjcpxx-con-mk stop -->
                    </div>

                    <div class="clear"></div>
                    <div class="dotted mar35"></div>

                </div><!--tjcpxx-con stop -->
            </div><!--tjcpxx stop -->
            <div class="l_wzmbtop">
                <ul>
                    <li class="sj_hover"><a href="javascript:void(0);">商品规格信息</a><span class="sj-img"></span></li>

                </ul>
            </div><!--l_wzmbtop   stop -->
            <div class="tjcpxx">
                <div class="tjcpxx-con-con" id="specslist_div">
                    <script id="specslist" type="text/html">
                        {{each list as specs i}}
                        <div class="tjcpxx-con-mk">
                           <div class="tjcpxx-con-form-title">
                              {{if specs.ismust==1}}
                              <span class="red marrig5">*</span>
                              {{/if}}
                             <label>{{specs.specsName}}：</label>
                           </div>
                            <div class="tjcpxx-con-form" name="specs_div" data="{{specs.specsID}}">
                                {{if specs.isEntry == 1}}

                                <input class="tjcpxx-fprm-inp" name="specs_{{specs.specsID}}"  type="text" />
                                {{else}}
                                <select name="specs_{{specs.specsID}}">
                                    <option value="0">请选择</option>
                                    {{each specs.valuelist as value j}}
                                    <option value="{{value.id}}">{{value.value}}</option>
                                    {{/each}}
                                </select>
                                {{/if}}
                            </div>
                        </div>
                        {{/each}}
                    </script>
                </div>

                <div class="tjcpxx-con-mk">
                    <div class="tjcpxx-con-form-title"></div>
                    <div class="tjcpxx-con-form huise">
                        <input class="preserve-inp" name="commit" type="button" value="保存" onclick="formSubmit()">
                        <input class="preserve-inp_hs" name="" type="button" value="取消" onclick="formCancel()">
                    </div>
                </div><!--tjcpxx-con-mk stop -->
            </div>
        </form>
    </div>
</div><!--mainright stop -->
<script type="text/javascript">
    $(document).ready(function () {
        SKU.getSpecsListByClass();
		$(".tjcpxx-con-form-upthis").click(function() {
			$.ajaxFileUpload({
				url : "/app/api/img/upload",
				secureuri : false,
				fileElementId : 'singlefile',
				dataType : "json",
				//relationtype: 模块（会员头像( 0), 店铺(1), 产品 ( 2), 其他 (20);）
				data : {
					"relationtype" : 2,
					"type" : 0
				},
				type : 'post',
				success : function(result) {
					$("input[name='img']").val(result.data);
					if (result.code == 0){
						Dalert("上传成功");						
						$("#loadimg").attr("src",$("#imgsrc").val()+result.data[0]);
					}
					else
					{
                   	 $("#loadimg").attr("src", "");
                   	 Dalert("上传图片失败");
                   }
						//TODO 结束正在加载中
				},
				error : function(e) {
					alert(JSON.stringify(e));
				}
			});
		});
    })
</script>