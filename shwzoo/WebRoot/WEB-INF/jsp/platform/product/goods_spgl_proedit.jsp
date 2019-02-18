<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ include file="/decorators/getFileUrl.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resource/ajaxfileupload.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resource/public/platform/js/product/spgl_proedit.js"></script>
<script
	src="${pageContext.request.contextPath }/resource/public/commonjs/kindeditor-4.1.10/kindeditor-min.js"></script>
<script
	src="${pageContext.request.contextPath }/resource/public/commonjs/kindeditor-4.1.10/lang/zh_CN.js"></script>
<script type="text/javascript">
	function formSubmit() {
		//var ss = $("#form").submit();
		var $selectbrand = $("#select_brand");
		if ($selectbrand.val() == "") {
			$("#brandid").val("");
		} else {
			$("#brandid").val($selectbrand.attr("data"));
		}
		//按钮隐藏防止重复提交
		$("input[name='commit']").hide();
		editor.sync();
		editor1.sync();
		editor2.sync();
		editor3.sync();
		$("input[name=description]").val(editor.html());
		$("input[name=spuinfo]").val(editor1.html());
		$("input[name=packinglist]").val(editor2.html());
		$("input[name=afterservice]").val(editor3.html());
	
		$.ajax({
			url : "/platform/spu/insertDirect",
			data : $("#form").serialize(),
			type : "Post",
			dataType : "json",
			success : function(data) {
				if (data.code == 0) {
					Dalert("保存成功！", "", backhref);
					//window.location.href = "/Platform/sp/goods_spgl_zyspgl";
				} else {
					$("input[name='commit']").show();
					Dalert(data.desc);
				}
			}
		});
	}
	function backhref() {
		window.location.href = "/platform/product/showDirect";
	}
	var editor, editor1, editor2, editor3;
	/*var basepath=getRootPath();  */
	KindEditor.ready(function(K) {
		//K.create('#content1');
		//取消功能 打印，插入模板，插入代码，插入flash，插入视频，插入表情，锚点
		editor = K.create('#content1', {
			items : [ 'source', 'undo', 'redo', 'preview', 'cut', 'cpoy',
					'paste', 'plainpaste', 'wordpaste', 'justifyleft',
					'justifycenter', 'justifyright', 'justifyfull',
					'insertorderedlist', 'insertunorderedlist', 'indent',
					'outdent', 'subscript', 'superscript', 'clearhtml',
					'quickformat', 'selectall', 'fullscreen', 'formatblock',
					'fontname', 'fontsize', 'forecolor', 'hilitecolor', 'bold',
					'italic', 'underline', 'strikethrough', 'lineheight',
					'removeformat', 'image', 'multiimage', 'insertfile',
					'table', 'hr', 'baidumap', 'pagebreak', 'link', 'unlink' ],
				uploadJson :"/app/api/img/upload?relationtype=1&iskdr=1"
		});
		editor1 = K.create('#content2', {
			items : [ 'source', 'undo', 'redo', 'preview', 'cut', 'cpoy',
					'paste', 'plainpaste', 'wordpaste', 'justifyleft',
					'justifycenter', 'justifyright', 'justifyfull',
					'insertorderedlist', 'insertunorderedlist', 'indent',
					'outdent', 'subscript', 'superscript', 'clearhtml',
					'quickformat', 'selectall', 'fullscreen', 'formatblock',
					'fontname', 'fontsize', 'forecolor', 'hilitecolor', 'bold',
					'italic', 'underline', 'strikethrough', 'lineheight',
					'removeformat', 'image', 'multiimage', 'insertfile',
					'table', 'hr', 'baidumap', 'pagebreak', 'link', 'unlink' ],
					uploadJson :"/app/api/img/upload?relationtype=1&iskdr=1"
		});
		editor2 = K.create('#content3', {
			items : [ 'source', 'undo', 'redo', 'preview', 'cut', 'cpoy',
					'paste', 'plainpaste', 'wordpaste', 'justifyleft',
					'justifycenter', 'justifyright', 'justifyfull',
					'insertorderedlist', 'insertunorderedlist', 'indent',
					'outdent', 'subscript', 'superscript', 'clearhtml',
					'quickformat', 'selectall', 'fullscreen', 'formatblock',
					'fontname', 'fontsize', 'forecolor', 'hilitecolor', 'bold',
					'italic', 'underline', 'strikethrough', 'lineheight',
					'removeformat', 'image', 'multiimage', 'insertfile',
					'table', 'hr', 'baidumap', 'pagebreak', 'link', 'unlink' ],
					uploadJson :"/app/api/img/upload?relationtype=1&iskdr=1"
		});
		editor3 = K.create('#content4', {
			items : [ 'source', 'undo', 'redo', 'preview', 'cut', 'cpoy',
					'paste', 'plainpaste', 'wordpaste', 'justifyleft',
					'justifycenter', 'justifyright', 'justifyfull',
					'insertorderedlist', 'insertunorderedlist', 'indent',
					'outdent', 'subscript', 'superscript', 'clearhtml',
					'quickformat', 'selectall', 'fullscreen', 'formatblock',
					'fontname', 'fontsize', 'forecolor', 'hilitecolor', 'bold',
					'italic', 'underline', 'strikethrough', 'lineheight',
					'removeformat', 'image', 'multiimage', 'insertfile',
					'table', 'hr', 'baidumap', 'pagebreak', 'link', 'unlink' ],
					uploadJson :"/app/api/img/upload?relationtype=1&iskdr=1"
		});
	});
</script>

<div class="mainright">
	<!--l_wzmb  开始 -->
	<div class="l_wzmb">
		<form id="form">
			<div class="l_wzmbtop">
				<ul>
					<li class="sj_hover"><a href="javascript:void(0);">商品基本信息</a><span
						class="sj-img"></span></li>

				</ul>
			</div>
			<!--l_wzmbtop   stop -->
			<div class="tjcpxx">
				<div class="tjcpxx-con-con">
					<div class="tjcpxx-con">
						<div class="tjcpxx-con-mk">
							<div class="tjcpxx-con-form-title">
								<label><span class="red marrig5">*</span>商品名称：</label>
							</div>
							<div class="tjcpxx-con-form" style="width: 500px;">
								<input class="tjcpxx-fprm-inp" name="name" type="text">
								<input type="hidden" id="imgsrc" value="<%=path %>" />
								<span class="huise"> 商品名称不能为空，长度限制在100个字符以内 </span>
							</div>
							<div class="remark"></div>
						</div>
						<!--tjcpxx-con-mk stop -->

						<div class="tjcpxx-con-mk">
							<div class="tjcpxx-con-form-title">
								<label><span class="red marrig5">*</span>库存名称：</label>
							</div>
							<div class="tjcpxx-con-form" style="width: 500px;">
								<input class="tjcpxx-fprm-inp" name="sku_name" type="text">
								<span class="huise">库存商品名称不能为空，长度限制在100个字符以内</span>
							</div>
							<div class="remark"></div>
						</div>
						<!--tjcpxx-con-mk stop -->

						<div class="tjcpxx-con-mk">
							<div class="tjcpxx-con-form-title">
								<label><span class="red marrig5">*</span>商品分类：</label>
							</div>
							<div class="tjcpxx-con-form" style="position: relative;">
								<select id="fc_select"
									onchange="SPU.fatherChange(SPU.callback, 'fc')" name="fid">
									<script type="text/html" id="fclist">
                                        {{each list as fclass i}}
                                        <option value="{{fclass.id}}">{{fclass.name}}</option>
                                        {{/each}}
                                    </script>
								</select> <select id="sc_select" onchange="SPU.fatherChange(null,'sc')"
									name="sid">
									<script type="text/html" id="sclist">
                                        {{each list as sclass i}}
                                        <option value="{{sclass.id}}">{{sclass.name}}</option>
                                        {{/each}}
                                    </script>
								</select> <select id="tc_select" name="classid">
									<script type="text/html" id="tclist">
                                        {{each list as tclass i}}
                                        <option value="{{tclass.id}}">{{tclass.name}}</option>
                                        {{/each}}
                                    </script>
								</select>
							</div>
						</div>
					
						<input type="hidden" id="brandid" name="brandid" />
						<div class="tjcpxx-con-mk">
							<div class="tjcpxx-con-form-title">
								<label>商品品牌：</label>
							</div>
							<ad>
							<input id="select_brand" type="text"
								class=" tjcpxx-fprm-inp the-form-select-one" /></ad>
							<div>
								<ul>
									<script id="select_brandlist" type="text/html">
                                        {{each list as brand i}}
                                        <li data="{{brand.id}}">{{brand.name}}</li>
                                        {{/each}}
                                    </script>
								</ul>
							</div>

						</div>
						<div class="tjcpxx-con-mk">
							<div class="tjcpxx-con-form-title">
								<label><span class="red marrig5">*</span>pc价格：</label>
							</div>
							<div class="tjcpxx-con-form">
								<input class="tjcpxx-fprm-inp" name="price" type="text">
							</div>
						</div>
						<!--tjcpxx-con-mk stop -->
						<div class="tjcpxx-con-mk">
							<div class="tjcpxx-con-form-title">
								<label><span class="red marrig5">*</span>商品原价：</label>
							</div>
							<div class="tjcpxx-con-form">
								<input class="tjcpxx-fprm-inp" name="oldprice" type="text">
							</div>
						</div>
						<!--tjcpxx-con-mk stop -->
						<div class="tjcpxx-con-mk">
							<div class="tjcpxx-con-form-title">
								<label><span class="red marrig5">*</span>App售价：</label>
							</div>
							<div class="tjcpxx-con-form">
								<input class="tjcpxx-fprm-inp" name="appprice" type="text">
							</div>
						</div>
						<div class="tjcpxx-con-mk">
							<div class="tjcpxx-con-form-title">
								<label><span class="red marrig5">*</span>Wap售价：</label>
							</div>
							<div class="tjcpxx-con-form">
								<input class="tjcpxx-fprm-inp" name="wapprice" type="text">
							</div>
						</div><div class="tjcpxx-con-mk">
							<div class="tjcpxx-con-form-title">
								<label><span class="red marrig5">*</span>微信售价：</label>
							</div>
							<div class="tjcpxx-con-form">
								<input class="tjcpxx-fprm-inp" name="wechatprice" type="text">
							</div>
						</div>
						 <div class="tjcpxx-con-mk">
                            <div class="tjcpxx-con-form-title">
                            <label><span class="red marrig5">*</span>商品图片：</label></div>
                            <div class="tjcpxx-con-form">
                                <div class="tjcpxx-con-form-upimg">
                                    <img id="loadimg" width="120px" height="115px" src="" />
                                </div>
                                <input type="hidden" name="imgurlApp" value="" />
                                    <div style=" width:200px;margin-left:10px; float:left;">
                                        <input type="file" id="singlefile" name="pics" />
                                        <a href="javascript:void(0);" style="color:#000"><div class="tjcpxx-con-form-upthis">本地上传</div></a> 
                                    </div>
                            </div>
                        </div>
						<!--tjcpxx-con-mk stop -->

						<div class="tjcpxx-con-mk">
							<div class="tjcpxx-con-form-title">
								<label><span class="red marrig5">*</span>商品库存：</label>
							</div>
							<div class="tjcpxx-con-form">
								<input class="tjcpxx-fprm-inp" name="stock" type="text">
							</div>
						</div>
						<!--tjcpxx-con-mk stop -->
						<div class="tjcpxx-con-mk">
							<div class="tjcpxx-con-form-title">
								<label><span class="red marrig5">*</span>预警数量：</label>
							</div>
							<div class="tjcpxx-con-form">
								<input class="tjcpxx-fprm-inp" name="warnnum" type="number" value="0">
							</div>
						</div>
						<!--tjcpxx-con-mk stop -->

				<!-- 		<div class="tjcpxx-con-mk">
							<div class="tjcpxx-con-form-title">
								<label>商品标题：</label>
							</div>
							<div class="tjcpxx-con-form">
								<input class="tjcpxx-fprm-inp" name="title" type="text">
							</div>
						</div> -->
						<!--tjcpxx-con-mk stop -->
						<div class="tjcpxx-con-mk">
							<div class="tjcpxx-con-form-title">
								<label>商品短标题：</label>
							</div>
							<div class="tjcpxx-con-form">
								<input class="tjcpxx-fprm-inp" name="titleShort" type="text">
							</div>
						</div>
						<!--tjcpxx-con-mk stop -->

						<div class="tjcpxx-con-mk">
							<div class="tjcpxx-con-form-title">
								<label>商品标签：</label>
							</div>
							<div class="tjcpxx-con-form">
								<input class="tjcpxx-fprm-inp" name="tag" type="text">
							</div>
						</div>
						<!--tjcpxx-con-mk stop -->

						<div class="tjcpxx-con-mk">
							<div class="tjcpxx-con-form-title">
								<label>商品重量：</label>
							</div>
							<div class="tjcpxx-con-form">
								<input class="tjcpxx-fprm-inp" name="weight" type="text">
							</div>
						</div>
						<!--tjcpxx-con-mk stop -->
						<div class="tjcpxx-con-mk">
							<div class="tjcpxx-con-form-title">
								<label>商品产地：</label>
							</div>
							<div class="tjcpxx-con-form">
								<input class="tjcpxx-fprm-inp" name="producer" type="text">
							</div>
						</div>
						<!--tjcpxx-con-mk stop -->
						<div class="tjcpxx-con-mk">
							<div class="tjcpxx-con-form-title">
								<label>商品包装：</label>
							</div>
							<div class="tjcpxx-con-form">
								<input class="tjcpxx-fprm-inp" name="pack" type="text">
							</div>
						</div>
						<!--tjcpxx-con-mk stop -->
						<div class="tjcpxx-con-mk">
							<div class="tjcpxx-con-form-title">
								<label>保质期(天)：</label>
							</div>
							<div class="tjcpxx-con-form">
								<input class="tjcpxx-fprm-inp" name="shelflife" type="text">
							</div>
						</div>
						<input type="hidden" id="ispostage" name="ispostage" value="false"> 
						<div class="tjcpxx-con-mk">
							<div class="tjcpxx-con-form-title">是否包邮：</div>
							<div class="tjcpxx-con-form huise">
						    <input name="r_ispostage" type="radio" checked="checked" value="0"><span>不包邮</span>
							<span class="marrig35"></span>
							<input name="r_ispostage" type="radio" value="1"><span>包邮</span>
							</div>
						</div>
						<!--tjcpxx-con-mk stop -->
						<div class="tjcpxx-con-mk">
							<div class="tjcpxx-con-form-title">
								<label>商品详情：</label>
							</div>
							<div class="tjcpxx-con-form">
								<textarea id="content2" name=""
									style="width: 450px; height: 200px; visibility: hidden;"></textarea>
								<input class="tjcpxx-fprm-inp" name="spuinfo" type="hidden">
							</div>
						</div>
						<!--tjcpxx-con-mk stop -->

						<div class="tjcpxx-con-mk">
							<div class="tjcpxx-con-form-title">
								<label>手机端商品详情：</label>
							</div>
							<div class="tjcpxx-con-form">
								<textarea id="content1" name=""
									style="width: 450px; height: 200px; visibility: hidden;"></textarea>
								<input class="tjcpxx-fprm-inp" name="description" type="hidden">
							</div>
						</div>

						<!--tjcpxx-con-mk stop -->
						<div class="tjcpxx-con-mk">
							<div class="tjcpxx-con-form-title">
								<label>包装清单：</label>
							</div>
							<div class="tjcpxx-con-form">
								<textarea id="content3" name=""
									style="width: 450px; height: 200px; visibility: hidden;"></textarea>
								<input class="tjcpxx-fprm-inp" name="packinglist" type="hidden">
							</div>
						</div>
						<!--tjcpxx-con-mk stop -->
						<!-- <div style="clear:both;"></div> -->
						<!-- <div style="margin-top: 20px;"></div>
						<div class="tjcpxx-con-mk">
							<div class="tjcpxx-con-form-title">
								<label>售后服务：</label>
							</div>
							<div class="tjcpxx-con-form">
								<textarea id="content4" name=""
									style="width: 450px; height: 200px; visibility: hidden;"></textarea>
								<input class="tjcpxx-fprm-inp" name="afterservice" type="hidden">
							</div>
						</div> -->
						<!--tjcpxx-con-mk stop -->
						<div class="tjcpxx-con-mk fix" style="min-height:202px;">
							<div class="tjcpxx-con-form-title">
								<label>售后服务：</label>
							</div>
							<div class="tjcpxx-con-form">
								<textarea id="content4" name=""
									style="width: 450px; height: 200px; visibility: hidden;"></textarea>
								<input class="tjcpxx-fprm-inp" name="afterservice" type="hidden">
							</div>
							<div style="clear:both"></div>
						</div>
						<!--tjcpxx-con-mk stop -->
						

						<div class="tjcpxx-con-mk" style="margin-top:20px;">
							<div class="tjcpxx-con-form-title">
								<label>库存商品备注：</label>
							</div>
							<div class="tjcpxx-con-form">
								<input class="tjcpxx-fprm-inp" name="remark" type="text">
							</div>
						</div>
						<!--tjcpxx-con-mk stop -->
					</div>
				</div>
				<!--tjcpxx-con stop -->
			</div>
			<div class="l_wzmbtop">
				<ul>
					<li class="sj_hover"><a href="javascript:void(0);">商品规格信息</a><span
						class="sj-img"></span></li>

				</ul>
			</div>
			<!--l_wzmbtop   stop -->
			<div class="tjcpxx">
				<div class="tjcpxx-con-con">
					<div class="tjcpxx-con" id="specslist_div">
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
					<div class="tjcpxx-con">
						<div class="tjcpxx-con-form-title"></div>
						<div class="tjcpxx-con-form huise">
							<input class="preserve-inp" name="commit" type="button"
								value="保存" onclick="formSubmit()"> <input
								class="preserve-inp_hs" name="" type="button" value="取消"
								onclick="formCancel()">
						</div>
					</div>
					<!--tjcpxx-con-mk stop -->
				</div>
			</div>
		</form>
	</div>
	<!--l_wzmb  结束 -->
	<!--tjcpxx stop -->
</div>
<!--mainright stop -->
<script type="text/javascript">
	$(document).ready(function() {
		SPU.bind(SPU.callback);
		   $(".tjcpxx-con-form-upthis").click(function () {
			            $.ajaxFileUpload({
			            	url : "/app/api/img/upload",
							secureuri : false,
							fileElementId : 'singlefile',
							dataType : "json",
							//relationtype: 模块（会员头像( 0), 店铺(1), 产品 ( 2), 其他 (20);） 可以自由增加
							data : {
								"relationtype" : 2,
								"type" : 0
							},
			                type: 'POST',
			                success: function (result) {
			                    $("input[name='imgurlApp']").val(result.data); 
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
			                error:function(e){
			                	 alert(JSON.stringify(e));
			                }
			            });


	        });
	})
	function formCancel() {
		location.href = "/platform/product/showDirect";
	}
</script>
