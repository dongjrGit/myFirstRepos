<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ include file="/decorators/getFileUrl.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>
<script type="text/javascript" src="/resource/ajaxfileupload.js"></script>
<script type="text/javascript" src="/resource/public/platform/js/news/list.js"></script>
<script>
	
</script>

<div class="clear"></div>
<div class="mainright">
    <div class="clear"></div>
    <div class="dotted mar35"></div>
    <div class="l_wzmb">
        <div class="l_wzmbtop">
            <ul>
                <li class="sj_hover"><a href="javascript:void(0);" id="contitle">添加中绿基地</a><span class="sj-img"></span></li>
            </ul>
        </div><!--l_wzmbtop   stop -->
        <div class="tjcpxx-con-con">
            <form id="form">
                <div class="tjcpxx-con-mk1">
                    <div class="tjcpxx-con-form-title1"><label><span class="red marrig5">*</span>基地名称：</label></div>
                    <div class="tjcpxx-con-form1">
                        <input class="tjcpxx-fprm-inp" id="name" name="name" value="${info.name}" type="text">
                    </div>
                    <input type="hidden" value="${info.id}" id="id" name="id"/>
                </div>
                
                <div class="tjcpxx-con-mk1">
                    <div class="tjcpxx-con-form-title1"><label>基地电话：</label></div>
                    <div class="tjcpxx-con-form1">
                        <input class="tjcpxx-fprm-inp" value="${info.tel}" id="tel"  name="tel" type="text">
                    </div>
                </div>
                <div class="tjcpxx-con-mk1">
                    <div class="tjcpxx-con-form-title1"><label>经度：</label></div>
                    <div class="tjcpxx-con-form1">
                        <input class="tjcpxx-fprm-inp" value="${info.longitude}" id="longitude"   name="longitude" type="text">
                    </div>
                </div>
                <div class="tjcpxx-con-mk1">
                    <div class="tjcpxx-con-form-title1"><label>纬度：</label></div>
                    <div class="tjcpxx-con-form1">
                        <input class="tjcpxx-fprm-inp" value="${info.latitude}" id="latitude" name="latitude" type="text">
                    </div>
                </div>
                   <div class="tjcpxx-con-mk1">
							<div class="tjcpxx-con-form-title">
								<label>所在地：</label>
							</div>
							<div class="tjcpxx-con-form1">
								<input id="RegionID" name="regionid" type="hidden" value="0" /> <input
									type="hidden" value="${vo.provincecode}" id="province" /> <input type="hidden"
									value="${info.citycode}" id="city" /> <input type="hidden" value="${info.areacode}"
									id="area" />
									<select id="select_province" name="select_province"  style="width: 92px"
										class="the-form-select-one">
										<option value="-1">请选择</option>
										<script id="proviceselect" type="text/html">
                							{{each list as pro index}}
                							<option value="{{pro.code}}">{{pro.name}}</option>
                							{{/each}}
                						</script>
									</select> --><select id="select_city" name="select_city" style="width: 92px" class="the-form-select-one">
										<option value="-1">请选择</option>
										<script id="cityselect" type="text/html">
                							{{each list as pro index}}
                							<option value="{{pro.code}}">{{pro.name}}</option>
                							{{/each}}
                						</script>
									</select> --><select id="select_area" name="select_area" style="width: 92px" class="the-form-select-one">
										<option value="-1">请选择</option>
										<script id="areaselect" type="text/html">
                							{{each list as pro index}}
                							<option value="{{pro.code}}">{{pro.name}}</option>
                							{{/each}}
                						</script>
								</select>
							</div>
						</div>
                
                <div class="tjcpxx-con-mk1">
                    <div class="tjcpxx-con-form-title1"><label>详细地址：</label></div>
                    <div class="tjcpxx-con-form1">
                        <input class="tjcpxx-fprm-inp" value="${info.address}" id="address"  name="address" type="text">
                    </div>
                </div>
                <div class="tjcpxx-con-mk1">
                    <div class="tjcpxx-con-form-title1"></div>
                    <div class="tjcpxx-con-form1 huise">
                        <input class="preserve-inp marrig35 mar35" name="" onclick="save()" type="button" value="保存">
                        <input class="preserve-inp_hs" name="" onclick="backgref()" type="button" value="返回">
                    </div>
                </div><!--tjcpxx-con-mk stop -->

            </form>
        </div>
    </div><!--tjcpxx stop -->
</div><!--mainright stop -->
<script type="text/javascript">
	$(function (){
		var id=$('#id').val();
		if(id!=null||id!=""){
			
		}
		BindRegion();
	})

	function backgref(){
		location.href='/platform/zlbase/Listpage';
	}
	function save(){
		 
		var shengcode=$("#select_province option:selected").val();
		var shicode=$("#select_city option:selected").val();
		var qucode=$("#select_area option:selected").val();
		var shengname=$("#select_province option:selected").text();
		var shiname=$("#select_city option:selected").text();
		var quname=$("#select_area option:selected").text();
		var name=$('#name').val();
		var tel=$('#tel').val();
		var id=$('#id').val();
		var longitude=$('#longitude').val();
		var latitude=$('#latitude').val();
		var address=$('#address').val();
		/* if(shengcode>0){
			Dalert("请选择市") 
			return false;
		}
		if(shicode>0){
			Dalert("请选择区")
			return false;
		} */
		if(name==''||name==null){
			Dalert("请填写名称")
			return false;
		}
		if(tel==''||tel==null){
			Dalert("请填写电话")
			return false;
		}
		if(longitude==''||longitude==null){
			Dalert("请填写经度")
			return false;
		}
		if(latitude==''||latitude==null){
			Dalert("请填写维度")
			return false;
		}
		if(address==''||address==null){
			Dalert("请填写地址")
			return false;
		}
		 $.ajax({
	            url: "/platform/zlbaseinfo/Edit",
	            type: "Post",
	            data: {          
	                "shengcode": shengcode, 
	                "shicode": shicode, 
	                "qucode": qucode,
	                "name":name, 
	                "shengname":shengname, 
	                "quname":quname, 
	                "shiname":shiname, 
	                "tel": tel,
	                "id":id,
	                "longitude":longitude, 
	                "latitude": latitude,
	                "address":address
	            },
	            dataType: "json",
	            success: function (data) {
	            	alert(data)
	                //Dalert(data.desc,"",backgref)
	            },
	            error: function () {

	            }
	        }); 
		
	}
	
</script>