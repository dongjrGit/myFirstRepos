<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

 <script type="text/javascript" src="${pageContext.request.contextPath }/resource/public/platform/js/historybeans/historybeansl.js"></script> 

<div class="mainright">
    <div class="clear"></div>
    <div class="dotted mar35"></div>
    <div class="account-form">
         <span>所属用户：<input id="select_user" type="text" class="inp-seller" /></span>
            <div>
                <ul>
                    <script id="select_userlist" type="text/html">
                        {{each list as users i}}
                        <li data="{{users.id}}">{{users.username}}</li>
                        {{/each}}
                    </script>
                </ul>
            </div>
        <span class="marrig10"></span>
        <span>
            选择时间：
            <input type="text" id="select_begin" onfocus="WdatePicker({ maxDate: '#F{$dp.$D(\'select_end\')}' })" class="text_time" /> 至
            <input type="text" id="select_end" onfocus="WdatePicker({ minDate: '#F{$dp.$D(\'select_begin\')}' })" class="text_time" />
        </span>
        <span class="marrig10"></span>
        <span class="notice-fenlei-mk2"><input class="inquire" name="" type="button" value="查询" id="select_input"></span>
    </div>
    <div class="clear"></div>
    <div class="mar35"></div>
   
     <div class="table-con">
        <table class="data_list" >
           <thead >
            <tr id="memberlist_title">
                <th>用户名</th>
                <th>经采豆</th>
                <th>积分</th>
                <th>兑换日期</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody id="list_title">
            <script id="slist" type="text/html">
                {{each list as hs i}}
                <tr>
                    <input name="hiddenid" id="hiddenid" type="hidden" value='{{hs.id}}'>
                    <td>{{hs.username}}</td>
					<td>{{hs.beans}}</td>
                    <td>{{hs.points}}</td>
                    <td>{{hs.creattimestr}}</td>
                    <td><span class="shenlan" id="delete_id">删除</span></td>
                </tr>
                {{/each}}
            </script>
       </tbody>
        </table>
    </div> 
    
    
    
    
    <div class="clear"></div>

    <div id="pager" class="page">

    </div><!--page stop -->


</div><!--mainright stop -->
<script type="text/javascript">
    $(document).ready(function () {
        //初始化数据
        //Init.bind();
        HB.bind();
        HB.getHbList(1);
        $("#delete_id").on("click",null, function() {
			var id = $(this).parent().parent().find("input").val();
			ConfirmShow("确认要删除信息吗？", deleteById, id);
		});
    })
</script>
