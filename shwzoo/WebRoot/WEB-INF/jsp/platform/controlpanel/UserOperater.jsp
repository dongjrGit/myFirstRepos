<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/decorators/getFileUrl.jsp"%>
 <script type="text/javascript" src="/resource/public/platform/js/ControlPanel/UserOperater.js"></script>


<div class="mainright">
<style type="text/css">

/* .tjcpxx-con-form-title1{
width: 80px;
} */
 .tjcpxx-con-mk1{display:inline-block;width:auto;} 


</style>
    <!--l_wzmb  开始 -->
    <div ><!-- class="l_wzmb" --><input type="hidden"  id="id" name="id" />
        <div style="margin-top:20px; padding:10px;">
            <div class="members-form">
                <form>
                	<div style="float: left; padding-right: 20px;">
								
					</div>
					<input type="hidden" name="mark" id="mark" value="">
							<input type="hidden" name="position" id="position" value="">
					 		<span>操作类型：
			                 <select class="the-form-select-one" name="firstID" id="firstID" onchange="Ad.firstChange()">
			                                    <option value="" id="defaultfc" selected>无</option>
			                                    <script id="flist" type="text/html">
                                        {{each list as ad i}}
                                        <option value="{{ad.code}}">{{ad.name}}</option>
                                        {{/each}}
                                    </script>
			                   </select>
			                   </span>
			                   
			                   <span>来源：
			                 <select class="the-form-select-one" name="scoure" id="secendID" onchange="Ad.firstChange()">
			                                    <option value="" id="defaultfc" selected>无</option>
			                                    <script id="secend" type="text/html">
                                        {{each list as scoure i}}
                                        <option value="{{scoure.code}}">{{scoure.name}}</option>
                                        {{/each}}
                                    </script>
			                   </select>
			                   </span>
			                   
			                   <span>
                        <div class="tjcpxx-con-mk1" name="fltype" id="username" style="">
                            <label>用户名称：</label>
                         <ad>
                                <input class="inp-seller" type="text" name="userName" id="userName" value="${name }" />
                          </ad> 
                            <div name="spu_name_select"  >
                                <ul>
                                    <script id="select_userlist" type="text/html">
                                        {{each list as user i}}
                                        <li data="{{user.userid}}">{{user.loginname}}</li>
                                        {{/each}}
                                    </script>
                                </ul>
                            </div>
                        </div>
                        </span>
                        
			            <span>描述：<input class="members-form-inp" id="descript" type="text" value=""></span>
						  
			                    <span class="marrig10"></span>
      						 	 <span>
						          <span>操作日期：</span>
						          <input type="text" id="select_begin" class="Wdate" onfocus="WdatePicker({ maxDate: '#F{$dp.$D(\'select_end\')}' })"  />
						          -<input type="text" id="select_end" class="Wdate" onfocus="WdatePicker({ minDate: '#F{$dp.$D(\'select_begin\')}' })"  />
						      </span>           	
                 
                    <span><input class="inquire" id="searchtitle" type="button" value="搜索"></span>
                </form>
            </div><!--account-form stop -->
            <div class="member-xz" style="margin-top:10px;">

            </div>
            <div class="clear"></div>
            <div class="table-con" id="divshow">
                <table class="data_list">
                    <tr id="Advertdata">
                        <!-- <th><input id="SelectAll" name="ch_All" type="checkbox" value=""></th> -->
                        <th>操作类型</th>
                        <th>来源</th>
                        <th>操作人姓名</th>
                        <th>操作人ip</th>
                        <th>添加时间</th>
                        <th>页面名称</th>
                    </tr>
                    <tbody id="datalist">
                    <script id="Advertlist" type="text/html">
 						{{each list as records i}}
                        <tr name="dataTr">
                           <input name="chk_list" type="hidden" value='{{records.id}}'>
							{{if records.type==0}}
							<td>查看</td>
							{{else if records.type==1}}
							<td>修改</td>
							{{else if records.type==2}}
							<td>添加</td>
							{{else if records.type==3}}
							<td>删除</td>
                             {{else}}
                            <td></td>
							{{/if}}
							{{if records.source==0}}
							<td>平台后台</td>
							{{else if records.source==1}}
							<td>卖家中心</td>
							{{else if records.source==2}}
							<td>买家中心</td>
							{{else if records.source==3}}
							<td>web前端</td>
                             {{else}}
                            <td></td>
							{{/if}}
							<td>{{records.username}}</td>
							<td>{{records.userip}}</td>
							<td>{{records._createtime}}</td>
							<td>{{records.page}}</td>
                        </tr>
                        {{/each}}
                    </script>
                    </tbody>
                </table>
            </div><!--table-con  stop  -->
            <div class="clear"></div>

            <div class="page" id="pager">
            </div><!--page stop -->
        </div>
    </div>
    <!--l_wzmb  结束 -->
</div><!--mainright stop -->
<script type="text/javascript">
    $(document).ready(function () {
        //初始化数据
        advert.bind(1);

    })
</script>
