<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>
<script type="text/javascript" src="${ctx }/resource/public/platform/js/searchkey/searchkeylist.js">
    
</script>
 <div class="mainright">
    <div class="clear"></div>
    <div class="dotted mar35"></div>
    <div class="account-form">
        <a href="javascript:void(0);" target="_self"><input class="chaxun addnew_button" type="button" value="+添加关键字"></a>
    </div><!--notice stop -->

    <div class="clear"></div>
    <div class="mar35"></div>
    <div class="table-con">
        <form id="forms" method="post">
            <table class="data_list">
                <thead>
                    <tr>
                        <th width="5%">序号</th>
                        <th width="35%">关键字</th>
                        <th width="25%">排序</th>
                        <!-- <th width="20%">使用站点</th> -->
                        <th>操作</th>
                    </tr>
                </thead>
                <tbody id="list_title">
                    <script id="searchlist" type="text/html">
                        {{each list as keyws i}}
                        <tr>
                            <td>{{i+1}}</td>
                            <td>{{keyws.keyWords}}</td>
                            <td>
                                <input class="data_list_cs" id='ob_{{keyws.id}}' name="" type="text" value='{{keyws.orderBy}}' onfocus="if(this.value==defaultValue) {this.value='';}"
                                       onblur="if(!value) {value=defaultValue; this.type='text';}">
                                <span class="lvs">
                                    <a href="javascript:void(0);" class="saveorderby" data="{{keyws.id}}">保存</a>
                                </span>
                            </td>
							<!--<td>
							{{if keyws.useSites!=null&&keyws.useSites.indexOf('1')>=0}}pc,{{/if}}
                        	{{if keyws.useSites!=null&&keyws.useSites.indexOf('2')>=0}}app,{{/if}}
                        	{{if keyws.useSites!=null&&keyws.useSites.indexOf('3')>=0}}wap{{/if}}
							</td>-->

                            <td>
                                <a href="javascript:void(0);" class="del" data="{{keyws.id}}"><span class="shenlan">删除</span></a>
                            </td>
                        </tr>
                        {{/each}}
                    </script>
                </tbody>
                
                <tr id="addnew_tr" style="display:none;">
                    <td class="red">+</td>
                    <td><input type="text" id="keyword" name="keyword" value="" /></td>
                    <td><input type="text" id="orderby" name="orderby" value="" /></td>
                     <!-- <td>
                     <input type="checkbox" value="1"  name="userSites" />pc
                     <input type="checkbox" value="2"  name="userSites"/>app
                     <input type="checkbox" value="3"  name="userSites"/>wap
                     </td> -->
                    <td>
                        <a href="javascript:void(0);" id="addnew_submit"><span class="shenlan">保存</span></a>
                        <a href="javascript:void(0);" id="addnew_cancel"><span class="shenlan">取消</span></a>
                    </td>
                </tr>
            </table>
        </form>
        <div class="clear"></div>
        <div id="pager" class="page">

        </div><!--page stop -->
    </div><!--table-con  stop -->

</div><!--mainright stop -->

<script type="text/javascript">
    $(document).ready(function () {
        Search.bind();
        //修改排序
        $("#forms").on("click",".saveorderby",function(){
        	 var id = $(this).attr("data");        
             var obtext = $("#ob_" + id).val();
        	Search.setOrder(id,obtext);
        })
    })
</script>