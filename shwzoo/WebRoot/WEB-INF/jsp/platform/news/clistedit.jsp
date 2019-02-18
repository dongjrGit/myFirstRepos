<!-- @{
    ViewBag.Title = "添加类别";
    Layout = "~/Areas/Platform/Views/Shared/_Layout_iframe_conent_v11.cshtml";
} -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ include file="/decorators/getFileUrl.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>
 <script type="text/javascript" src="${ctx }/resource/ajaxfileupload.js"></script> 
<script src="/resource/public/platform/js/news/clistedit.js"></script>
<script type="text/javascript">

var relevance="${vo.fpath}";

$(function(){
	$.each($("#parentID1 option"),function(){
		if(relevance.indexOf(this.value)>=0){
			$(this).attr("selected","selected");
			flselect(this);
		}
	});
	
});
function flselect(obj){
	if(obj.value!=0){
		var ctype=$("#ctype").val();
	 $.ajax({
         url: "/platform/news/queryClassSub",
         type: "Post",
         data: {"pid":obj.value,"ctype":ctype},
         dataType: "json",
         success: function (data) {
             if (data.code < 0) {
                 Dalert(data.desc);
             } else {
            	 var fldata=data.data;
					
            	 if(fldata.length<=0){
            		 return;
            	 }
            	 var htmls="";
                 htmls+='<select name="parentID" class="the-form-select-one" id="list_fl" onchange="flselect(this)">';
                 htmls+='<option value="0">无</option>';
                 $.each(fldata,function(index){
                     htmls+='<option value="'+this.id+'">'+this.name+'</option>';
                 });
              	 htmls+='</select>';
              	 $(obj).nextAll().remove();
              	 $("#flbox").append(htmls);
                 
                 $.each($("#list_fl option"),function(){
             		if(relevance.indexOf(this.value)>=0){
             			$(this).attr("selected","selected");
             			$(this).onclick();
             		}
             	});
             }
         },
         error: function () {

         }
     });
	}else{
		 $(obj).nextAll().remove();
	}
}

</script>
<div class="clear"></div>
<div class="mainright">

    <div class="clear"></div>
    <div class="dotted mar35"></div>
    <div class="l_wzmb">
        <div class="l_wzmbtop">
            <ul>
                <li class="sj_hover"><a href="javascript:void(0);" id="contitle">添加文章分类</a><span class="sj-img"></span></li>
            </ul>
        </div><!--l_wzmbtop   stop -->
        <div class="tjcpxx-con-con">
            <form id="form">
                <div class="tjcpxx-con-mk1">
                    <div class="tjcpxx-con-form-title1"><label><span class="red marrig5">*</span>分类名称：</label></div>
                    <div class="tjcpxx-con-form1">
                        <input class="tjcpxx-fprm-inp" id="name" name="name" value="${vo.name }" type="text">
                    </div>
                    <input type="hidden" value="${id }" name="id"/>
                </div>
                
                <div class="tjcpxx-con-mk" >
                    <div class="tjcpxx-con-form-title">所属分类：</div>
                    <div class="tjcpxx-con-form" id="flbox">
                     <select name="parentID" id="parentID1" class="the-form-select-one" onchange="flselect(this)">
                    <option value="0">无</option>
	                        <c:forEach items="${fl }" var="data">
	                        <option  value="${data.id }" >${data.name }</option>
	                        </c:forEach>
                     </select>
                     
                    </div>
                </div>
                
                <div class="tjcpxx-con-mk1">
                            <div class="tjcpxx-con-form-title1"><label>图片：</label></div>
                            <div class="tjcpxx-con-form1">
                                <div class="tjcpxx-con-form-upimg">
                                    <img id="loadimg" width="120px" height="115px" src="<%=path %>${vo.imgurl}" />
                                </div>
                                <input type="hidden" name="img" value="${vo.imgurl}" />
                                    <div
							style="width: 200px; float: left; position: relative; padding-left: 30px;">
							<input type="button" value="选择图片" class="h_scimgbut" /> <input
								type="file" id="singlefile" name="pics" 
								class="filemhbut" 
								style="top: 10px; left: 27px;" />
							<div>
								<input type="button" value="本地上传" class="h_scimgbut h_scimgbut1" />
							</div>
						</div>
                            </div>
                        </div>
              </br>
                <div class="tjcpxx-con-mk1">
                    <div class="tjcpxx-con-form-title1"><label>排序：</label></div>
                    <div class="tjcpxx-con-form1">
                        <input class="tjcpxx-fprm-inp" value="${vo.sort }" id="sort" name="sort" type="text">
                    </div>
                </div><!--tjcpxx-con-mk stop -->
                <span class="marrig35"></span><input type="hidden" id="ctype" name="ctype" value=${ctype } type="text">
                <div class="tjcpxx-con-mk1">
                    <div class="tjcpxx-con-form-title1"></div>
                    <div class="tjcpxx-con-form1 huise">
                        <input class="preserve-inp marrig35 mar35" name="" id="savebtn" type="button" value="保存">
                        <input class="preserve-inp_hs" name="" id="backbtn" type="button" value="返回">
                    </div>
                </div><!--tjcpxx-con-mk stop -->

            </form>
        </div>
    </div><!--tjcpxx stop -->
</div><!--mainright stop -->
<script type="text/javascript">
    $(document).ready(function () {
    	$("#singlefile").change(x);
    	function x(){
    		var objUrl = getObjectURL(this.files[0]);
    		console.log("objUrl = " + objUrl);
    		if (objUrl) {
    			$("#loadimg").attr("src", objUrl);
    		} else {
    			$("#loadimg").attr("src", "");
    		}
    	};
    	//建立一个可存取到该file的url
    	function getObjectURL(file) {	
    		var url = null;
    		if (window.createObjectURL != undefined) { // basic
    			url = window.createObjectURL(file);
    		} else if (window.URL != undefined) { // mozilla(firefox)
    			url = window.URL.createObjectURL(file);
    		} else if (window.webkitURL != undefined) { // webkit or chrome
    			url = window.webkitURL.createObjectURL(file);
    		}
    		return url;
    	}
        $(".h_scimgbut1").click(function () {
            $.ajaxFileUpload({
            	url : "/app/api/img/upload",
				secureuri : false,
				fileElementId : 'singlefile',
				dataType : "json",
				//relationtype: 模块（品牌( 11)）
				data : {
					relationtype : 30
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
    })
</script>

