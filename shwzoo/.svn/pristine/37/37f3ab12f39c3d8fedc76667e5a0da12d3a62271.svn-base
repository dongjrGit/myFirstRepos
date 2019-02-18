<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
			<%@ include file="/decorators/getFileUrl.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript" src="${pageContext.request.contextPath }/resource/ajaxfileupload.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resource/public/seller/js/spgl/spgl_skuedit.js"></script>

<script type="text/javascript">
    function formSubmit() {
        //按钮隐藏防止重复提交
        $("input[name='commit']").hide();
        $.ajax({
            url: "/seller/shopsku/updateSku",
            data: $("#form").serialize(),
            type: "Post",
            dataType: "json",
            success: function (data) {
                if (data.code == 0) {
                    Dalert("保存成功！","",backhref);
                    //window.location.href = "goods_spgl_skulb?spu_id=" + $("#cid").val();
                } else {
                    $("input[name='commit']").show();
                    Dalert(data.desc);
                }
            }
        });
    }
    function backhref() {
        window.location.href = "spgl_skulist?spuid=" + $("#spuid").val();
    }
</script>
<style>
    .tjcpxx-con-form-upimg{display:table-cell; width:120px; height:115px; background:#f4f4f4; border:1px solid #D9D9D9; padding:1px; text-align:center; line-height:115px; float:left; margin:0px 0px 10px 0px;}
.tjcpxx-con-form-upthis{ width:105px; height:35px; float:left; background:#FFE5E6; border:1px solid #D9D9D9; margin:30px auto auto 5px; text-align:center; line-height:35px;}
</style>
<div id="container">
    <!--主要内容 左边导航结束 -->
    <div class="allcon">
        <div class="position">
            您所在的位置：商品管理 &gt; 库存商品管理 &gt; 商品信息编辑
        </div><!--所在位置信息  结束 -->
        <div class="zhgl-con">
            <div class="zhgl-con-con">
                <form id="form">
                    <div class="zhgl-con-top">
                        <input type="hidden" value="${spu.classid }" name="classid" id="classid" />
                        <input type="hidden" value="${spu.id }" name="spuid" id="spuid" />
                        <input type="hidden" id="imgsrc" value="<%=path %>" />
                        <div class="zhgl-con-top-title">商品信息编辑</div>
                    </div>
                    <table>
                        <tr>
                            <td class="xjdpzzh-left"><label><span class="red marrig5">*</span>库存商品名称：</label></td>
                            <td>
                                <input class="text-inp-big" name="name" type="text" value="${data.name }">
                                <input type="hidden" name="id" id="id" value="${data.id }" />
                                <span class="huise">
                                                                    商品名称不能为空，长度限制在100个字符以内
                                </span>
                            </td>
                        </tr><!--tjcpxx-con-mk stop -->
                        <tr>
                            <td class="xjdpzzh-left">副标题：</label></td>
                            <td>
                                <input class="text-inp-big" name="subtitle" type="text" value="${data.subtitle }">
                                <span class="huise">长度限制在100个字符以内
                                </span>
                            </td>
                        </tr>
                        <tr>
                            <td class="xjdpzzh-left"><label><span class="red marrig5">*</span>商品编码：</label></td>
                            <td>
                                <input class="text-inp-big" type="text" value="${data.num }" disabled>
                                <span class="huise">
                                                                 商品编码不可修改，添加时由后台自动生成
                                </span>
                            </td>
                        </tr><!--tjcpxx-con-mk stop -->
                          <tr>
                            <td class="xjdpzzh-left"><label><span class="red marrig5">*</span>库存图片：</label></td>
                            <td>
                                <div class="tjcpxx-con-form">
                                   <c:choose>
									<c:when test="${data.imgurlApp==null }">
										<div class="tjcpxx-con-form-upimg">
											<img id="loadimg" width="120px" height="115px"
												src="" />
										</div>
									</c:when>
									<c:otherwise>
										<div class="tjcpxx-con-form-upimg">
											<img id="loadimg" width="120px" height="115px"
												src="<%=path %>${data.imgurlApp }" />
										</div>
									</c:otherwise>
								</c:choose>

								<input type="hidden" name="img" value="${data.imgurlApp }" />
								<div style="width: 200px; margin-left: 10px; float: left;">
									<input type="file" id="singlefile" name="pics" /> <a
										href="javascript:void(0);" style="color: #000">
										<div class="tjcpxx-con-form-upthis">本地上传</div></a>
								</div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td class="xjdpzzh-left"><label><span class="red marrig5">*</span>商品原价：</label></td>
                            <td>
                                <input class="text-inp-big" name="oldprice" type="text" value="<fmt:formatNumber value="${data.oldprice}" pattern="0.00" />">
                            </td>
                        </tr><!--tjcpxx-con-mk stop -->
                        <tr>
                            <td class="xjdpzzh-left"><label><span class="red marrig5">*</span>pc价格：</label></td>
                            <td>
                                <input class="text-inp-big" name="price" type="text" value="<fmt:formatNumber value="${data.price}" pattern="0.00" />">
                            </td>
                        </tr><!--tjcpxx-con-mk stop -->
                        
                        <tr>
                            <td class="xjdpzzh-left"><label><span class="red marrig5">*</span>App售价：</label></td>
                            <td>
                                <input class="text-inp-big" name="appprice" type="text" value="<fmt:formatNumber value="${data.appprice}" pattern="0.00" />">
                            </td>
                        </tr><!--tjcpxx-con-mk stop -->
                        <tr>
							<td class="xjdpzzh-left"><label><span
									class="red marrig5">*</span>Wap售价：</label></td>
							<td><input class="text-inp-big" name="wapprice" type="text" value="<fmt:formatNumber value="${data.wapprice}" pattern="0.00" />">
							</td>
						</tr>
						<tr>
							<td class="xjdpzzh-left"><label><span
									class="red marrig5">*</span>微信售价：</label></td>
							<td><input class="text-inp-big" name="wechatprice" type="text" value="<fmt:formatNumber value="${data.wechatprice}" pattern="0.00" />">
							</td>
						</tr>
                        <tr>
                            <td class="xjdpzzh-left"><label><span class="red marrig5">*</span>商品库存：</label></td>
                            <td>
                                <input class="text-inp-big" name="stock" type="text" value="${data.stock }">
                            </td>
                        </tr><!--tjcpxx-con-mk stop -->
                        <tr>
                            <td class="xjdpzzh-left"><label><span class="red marrig5">*</span>商品销量：</label></td>
                            <td>
                                <input class="text-inp-big" type="text" value="${data.salescount }" disabled>
                            </td>
                        </tr><!--tjcpxx-con-mk stop -->
                        <tr>
                            <td class="xjdpzzh-left"><label><span class="red marrig5">*</span>预警数量：</label></td>
                            <td>
                                <input class="text-inp-big" name="warnnum" type="text" value="${data.warnnum }">
                            </td>
                        </tr><!--tjcpxx-con-mk stop -->
                        <tr>
                            <td class="xjdpzzh-left"><label>备注：</label></td>
                            <td>
                                <input class="text-inp-big" name="remark" type="text" value="${data.remark }">
                            </td>
                        </tr><!--tjcpxx-con-mk stop -->
                        <tr>
                            <td class="xjdpzzh-left" colspan="2">
                                <div class="zhgl-con-top">
                                    <div class="zhgl-con-top-title">商品规格信息</div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td class="xjdpzzh-left"></td>
                            <td>
                        <tbody class="tjcpxx-con" id="specslist_div">
                            <script id="specslist" type="text/html">
                                {{each list as specs i}}
                                <tr id="specslist_div">
                                    <td class="xjdpzzh-left">
 {{if specs.ismust==1}}
                              <span class="red marrig5">*</span>
                              {{/if}}
<label>{{specs.specsName}}：</label></td>
                                    <td name="specs_div" data="{{specs.specsID}}">
                                        {{if specs.isEntry == 1}}
                                        <input class="text-inp-big" name="specs_{{specs.specsID}}" type="text" />
                                        {{else}}
                                        <select class="sel-form" name="specs_{{specs.specsID}}">
                                            <option value="0">请选择</option>
                                            {{each specs.valuelist as value j}}
                                            <option value="{{value.id}}">{{value.value}}</option>
                                            {{/each}}
                                        </select>
                                        {{/if}}
                                    </td>
                                </tr><!--tjcpxx-con-mk stop -->
                                {{/each}}
                            </script>
                        </tbody>
                        </td>
                        </tr>
                        <tr>
                            <td class="xjdpzzh-left"></td>
                            <td>
                                <input class="big-but" name="commit" type="button" value="保存" onclick="formSubmit()">
                                <input class="big-but-huise" name="" type="button" value="返回" onclick="backhref()">
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
        SKU.bind();
  	  $(".tjcpxx-con-form-upthis").click(function () {
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
    })
</script>