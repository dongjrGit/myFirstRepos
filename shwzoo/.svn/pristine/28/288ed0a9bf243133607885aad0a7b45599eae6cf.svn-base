<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ include file="/decorators/getFileUrl.jsp"%>
	<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="/resource/ajaxfileupload.js"></script>
<script type="text/javascript" src="/resource/public/seller/js/spgl/spgl_spueditnew.js"></script>
<script src="/resource/public/commonjs/kindeditor-4.1.10/kindeditor-min.js"></script>
<script src="/resource/public/commonjs/kindeditor-4.1.10/lang/zh_CN.js"></script>
<style type="text/css">
#ruletable{border-collapse :collapse ;}
#ruletable tr, #ruletable td,#ruletable th { border :1px solid #ccc;font-size:12px; text-align :center;}
#ruletable th{padding: 6px 0px;}
.xztjsp-xjgg{height:20px;line-height:20px;float: none;position: static;top: 0;left: 0;}
.xztjsp-inpdivnodw{position: relative;}
.xztjspmk.xztjsp-gg .xztjsp-inpdivnodw{margin-left: 115px;position: relative;border: 1px solid #DDDDDD;padding: 15px;}
.xztjsp-xjgg span{ display:inline-block;width: 20px;height: 20px;text-align: center;background: #409DEB;font-size: 20px;}
.xztjsp-inpdivnodw h3{font-weight: bold;margin: 10px 0px 0px 0px;color: #434343;}
.xztjsp-inpdivnodw table{ width: 150%;border-collapse: collapse;margin: 10px 0px 0px 0px;}
.xztjsp-inpdivnodw table td, .xztjspmk.xztjsp-gg .xztjsp-inpdivnodw table th{border: 1px solid #DDDDDD;text-align: left;padding:5px 5px 5px 15px;font-size: 14px;}
.xztjsp-inpdivnodw table th{color: #656565; font-weight: bold;line-height: 100%;}
.xztjsp-inpdivnodw table td input[type="text"]{border: 1px solid #DDDDDD;background: #fff;height: 23px;color: #43A5F9;padding: 0 5px;width: 90%;}
.xzspsx{width: 50%;position: relative;margin-top: 10px;}
.xzspsx span{display: inline-block;vertical-align: middle;line-height: 25px;min-width: 113px;text-align: right;}
.xzspsx input{ height: 25px;line-height: 25px;border: 1px solid #DDDDDD; background: #fff;padding: 0 5px;width: 100%;box-sizing:border-box;-moz-box-sizing:border-box; -webkit-box-sizing:border-box;-ms-box-sizing:border-box;-o-box-sizing:border-box;}
.xzspsx div{position: absolute;top: 0;left: 115px;right: 0px;}
.xzspsx select{height:25px;line-height:25px;border:1px solid #DDD;background:#fff;padding:0 5px;width:100%;box-sizing:border-box;-moz-box-sizing:border-box;-webkit-box-sizing:border-box;-ms-box-sizing:border-box;-o-box-sizing:border-box;}
.xzthsp-radio{ vertical-align: middle;margin-top: 12.5px;width: 15px;height: 15px;}
.xztjsp-inpdivnodw.nobor{margin-left: 115px;position: relative;border: none;padding:0;}
.xztjsp-inpdivnodw.nobor img{}
.xztjspmk.mt20{ margin-top: 20px;}
.xztjspmk.mt20 input:nth-child(2){ margin-left: 8px;}
.xztjsp-inpdivnodw table td input[type="checkbox"]{ vertical-align: middle;}
.xztjsp-inpdivnodw table td select{border: 1px solid #DDDDDD;background: #fff;height: 25px;padding: 0 5px;width: 90%;}
.xztjsp-sel:nth-child(5){ margin: 0 5%;}
.h_scimg{ margin: 0;}
.h_scimg input[type="file"]{opacity: 0;}
.xztjsp-xjgg{ padding-right: 0;}
.xztjsp-inpdivnodw table{margin-top: 0;width: 90%;}
.xztjsp-xjgg span{ margin-right: 0;}
.xztjsp-ulist li{position: relative;}
.xztjsp-ulist li i{ display: block;font-style: normal;position: absolute;top: 1px;right: 0px;background: #efefef;width: 17px;height: 17px;line-height: 17px;text-align: center;border: 1px solid #DDDDDD;cursor: pointer;}
</style>
<script type="text/javascript">
    var editor;
    KindEditor.ready(function (K) {
        //K.create('#content1');
        //取消功能 打印，插入模板，插入代码，插入flash，插入视频，插入表情，锚点
        editor = K.create('#content1', {
            items: ['source', 'undo', 'redo', 'preview', 'cut', 'cpoy', 'paste', 'plainpaste',
                'wordpaste', 'justifyleft', 'justifycenter', 'justifyright', 'justifyfull', 'insertorderedlist', 'insertunorderedlist',
                'indent', 'outdent', 'subscript', 'superscript', 'clearhtml', 'quickformat', 'selectall', 'fullscreen',
                'formatblock', 'fontname', 'fontsize', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline', 'strikethrough',
                'lineheight', 'removeformat', 'image', 'multiimage', 'insertfile', 'table', 'hr', 'baidumap', 'pagebreak', 'link', 'unlink'],
                uploadJson :"/zoo/image/upload?relationtype=1&iskdr=1"
        });
    });
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
    <div class="allcon">
        <div class="position">
            您所在的位置：商品管理 &gt; 标准商品管理 &gt; 商品信息编辑
        </div><!--所在位置信息  结束 -->
        <div class="zhgl-con">
            <div class="zhgl-con-con">
                <form id="form">
                    <div class="zhgl-con-top">
                        <div class="zhgl-con-top-title">商品基本信息</div>
                    </div>
                    <table>
                        <tr>
                            <td class="xjdpzzh-left"><label><span class="red marrig5">*</span>商品名称：</label></td>
                            <td>
                                <input class="text-inp-big" name="name" type="text" value="${spu.name }">
                                <span class="huise">  商品名称不能为空，长度限制在100个字符以内
                                </span>
                            </td>
                        </tr><!--tjcpxx-con-mk stop -->
                       
                         <tr>
                            <td class="xjdpzzh-left"><label><span class="red marrig5">*</span>商品图片：</label></td>
                            <td>
                                <div class="tjcpxx-con-form">
                                   <c:choose>
									<c:when test="${spu.imgurl==null }">
										<div class="tjcpxx-con-form-upimg">
											<img id="loadimg" width="120px" height="115px"
												src="" />
										</div>
									</c:when>
									<c:otherwise>
										<div class="tjcpxx-con-form-upimg">
											<img id="loadimg" width="120px" height="115px"
												src="${spu.imgurl }" />
										</div>
									</c:otherwise>
								</c:choose>

								<input type="hidden" name="imgurlApp" value="${spu.imgurl }" />
								<div style="width: 200px; margin-left: 10px; float: left;">
									<input type="file" id="singlefile" name="pics" />
										<a href="javascript:void(0);" style="color: #000">
										<div class="tjcpxx-con-form-upthis">本地上传</div></a>
									 
								</div>
                                </div>
                            </td>
                        </tr>
                        <tr>
						<td class="xjdpzzh-left"><label><span
									class="red marrig5">*</span>商品原价：</label></td>
							<td><input class="text-inp-big" name="price" type="text" value="${spu.price }">
							</td>
						</tr>
						<tr>
							<td class="xjdpzzh-left"><label><span
									class="red marrig5">*</span>商品ID：</label></td>
							<td>
							 <input type="hidden" name="tid" value="${sku.id}" />
							<input class="text-inp-big" name="tnum" type="text" value="${sku.ticketnum }">
							</td>
						</tr>
                         <tr>
                            <td class="xjdpzzh-left"><label>商品短标题：</label></td>
                            <td>
                                <input class="text-inp-big" name="titleShort" type="text" value="${spu.titleShort }">
                            </td>
                        </tr><!--tjcpxx-con-mk stop -->

                        <tr>
                            <td class="xjdpzzh-left"><label>商品标签：</label></td>
                            <td>
                                <input class="text-inp-big" name="tag" type="text" value="${spu.tag }">
                            </td>
                        </tr><!--tjcpxx-con-mk stop -->
      
                        <tr>
                            <td class="xjdpzzh-left"><label>商品详情：</label></td>
                            <td>
                                <textarea id="content1" name="" style="width:450px;height:200px;visibility:hidden;">
                                <c:out value="${spu.description }" escapeXml="true" /></textarea>
                                <input class="text-inp-big" name="description" type="hidden" value="${spu.description }">
                            </td>
                        </tr><!--tjcpxx-con-mk stop -->
                        
                       <tr>
							<td class="xjdpzzh-left"><label></label></td>
							<td>  
							        <input id="protype" name="protype" type="checkbox" disabled <c:if test="${spu.protype==1 }"> checked </c:if> /> <span>年卡</span> 
							        <input type="hidden" name="isnk" value="${spu.protype}" />
							        <input name='iszk' id='iszk' type='checkbox' <c:if test="${spu.isoffer==1 }">checked</c:if> ><span>折扣</span>
                                    <input type="hidden" name="isoffer" value="${spu.isoffer}" />
							</td>
						</tr>
						<tr>
							<td class="xjdpzzh-left"><label></label></td>
							<td>  
							        <input id="isday" name="isday" type="checkbox" <c:if test="${spu.istoday==1 }"> checked </c:if> /> <span>购买当天票</span> 
							        <input type="hidden" name="istoday" value="${spu.istoday}" />
							      
							</td>
						</tr>
						<tr>
							<td class="xjdpzzh-left"><label><span class="red marrig5">*</span>日期：</label></td>
							<td>
							<select style="width:150px;margin-right:8px;"  name="showy" id="showy" class="sel_allmost"  disabled>
                                <c:if test="${yearlist != null and yearlist.size() > 0 }">
									<c:forEach items="${yearlist }" var="year">
										<option value="${year}">${year}年</option>
									</c:forEach>
								</c:if>
                            </select>
                            <select style="width:150px;margin-right:8px;"  name="showm" id="showm" class="sel_allmost" disabled>
                                <option value="1">1月</option>
								<option value="2">2月</option>
								<option value="3">3月</option>
								<option value="4">4月</option>
								<option value="5">5月</option>
								<option value="6">6月</option>
								<option value="7">7月</option>
								<option value="8">8月</option>
								<option value="9">9月</option>
								<option value="10">10月</option>
								<option value="11">11月</option>
								<option value="12">12月</option>
                             </select>
							</td>
						</tr>
						
						  <tr>
                            <td class="xjdpzzh-left"><label>库存价格：</label></td>
                            <td>
                               <div class="xztjsp-inpdivnodw fix">
						<!-- 	<input type="hidden" id="unitlist" name="unitlist"> -->
								<input type="hidden" id="showtimelist" name="showtimelist" value="${showtimelist}" />
								<table border="" cellspacing="" cellpadding="" id="ruletable">
								<tbody>
									<tr id="ruletr1">
									<th>日期</th>
									<th>商品库存<span class="red"></span></th>
									<th>商品售价<span class="red"></span></th>
									</tr>
								</tbody></table>
								</div>
                            </td>
                        </tr><!--tjcpxx-con-mk stop -->
						
                        <tr>
                            <td class="xjdpzzh-left"></td>
                            <td>
                                <input type="hidden" id="id" name="id" value="${spu.id}" />
                                <input type="hidden" id="skuid" name="skuid" value="${sku.id}" />
						        <input type="hidden" id="action" name="" value="edit" />
						        <input type="hidden" id="skuparam" name="skuparam" />
                                <input class="big-but" name="commit" type="button" value="保存" onclick="formSubmit()">
                                <input class="big-but-huise" name="" type="button" value="返回" onclick="backhref()">
                            </td>
                        </tr><!--tjcpxx-con-mk stop -->
                    </table>
                </form>
            </div><!--tjcpxx-con stop -->
        </div><!--tjcpxx stop -->
    </div>
</div>
<script type="text/javascript">
    $(document).ready(function () {
		
        $(".tjcpxx-con-form-upthis").click(function () {
            $.ajaxFileUpload({
            	url : "/app/api/img/upload",
				secureuri : false,
				fileElementId : 'singlefile',
				dataType : "json",
				//relationtype: 产品 ( 2) 可以自由增加
				data : {
					"relationtype" : 2,
					//"type" : 0
				},
                type: 'POST',
                success: function (result) {
                    $("input[name='imgurlApp']").val(result.data); 
                    if (result.code == 0){
                    	Dalert("上传成功");
                        $("#loadimg").attr("src", result.data[0]);
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
        
       var year = "${year}";
 	   var month = "${month}";
 	   $("#showy").val(year);
 	   $("#showm").val(month);
 	   $("#year").val(year);
 	   $("#month").val(month);
 	   
 	  var daycount=30;
		if(month==1 || month==3 || month==5 || month==7 || month==8 || month==10 || month==12){
			daycount=31;
			
		}else if(year%4==0 && month==2){
			
			daycount=29;
		}else if(year%4!=0 && month==2){
			
			daycount=28;
		}
		for (var i=daycount;i>=1;i--)
		{
			var addhtml="<tr class='addr'><td><input type='text' name='daydate' id='' disabled data='"+i+"' value='"+i+"日'  /></td>" 
				addhtml+="<td><input type='text' name='stock' id='stock_"+i+"' value='' />";
				addhtml+="</td><td><input type='text' name='money' id='money_"+i+"' value='' /></td></tr>";
				$("#ruletr1").after(addhtml);
		}
		
		getShowTime();
    })
    
    function getShowTime(){
		$.ajax(({
			type : "post",
			url : "/seller/shopproduct/getBySkuShowtime",
			dataType : "json",
			cache:false,
			async : false,
			data : {
				skuid : $("#skuid").val(),
				spuid : $("#id").val(),
				showy : $("#showy").val(),
				showm : $("#showm").val()
			},
			success : function(rsll) {
				if (rsll.code == 0) {
					 var data2 = eval(rsll.data);
					 for(var i in data2){
						 $("#stock_"+data2[i].showdays).val(data2[i].stock);
						 $("#money_"+data2[i].showdays).val(data2[i].price);
					 }
					 
				} else {
					Dalert(rsll.desc);
				}
			},
			error : function(es) {

			}
		}));
	}
	
</script>