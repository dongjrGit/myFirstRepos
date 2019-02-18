<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

 <script type="text/javascript" src="/resource/public/platform/js/member/memberlist.js"></script> 

<div class="mainright">
    <div class="clear"></div>
    <div class="dotted mar35"></div>
    <div class="account-form">
    	
        <span>用户名：<input class="inp-seller" id="select_name" type="text"></span>
        <span class="marrig10"></span>
        <span>手机号：<input class="inp-seller" id="select_mobile" type="text"></span>
        <span class="marrig10"></span>
      <%-- <span>邮箱：<input class="inp-seller" id="select_email" type="text"></span>  
        <span class="marrig10"></span>
        --%>
        <span>
            性别：<select name="" id="select_sex" class="the-form-select-one">
                <script id="sexselect" type="text/html">
                    <option value="-1" selected>请选择</option>
                    {{each list as value index}}
                    <option value="{{value.code}}">{{value.name}}</option>
                    {{/each}}
                </script>
            </select>
        </span>
        <span class="marrig10"></span>
        <span>
            选择时间：
            <input type="text" id="select_begin" onfocus="WdatePicker({ maxDate: '#F{$dp.$D(\'select_end\')}' })" class="text_time" /> 至
            <input type="text" id="select_end" onfocus="WdatePicker({ minDate: '#F{$dp.$D(\'select_begin\')}' })" class="text_time" />
        </span>
        <span class="marrig10"></span>
        <span class="notice-fenlei-mk2"><input class="inquire" name="" type="button" value="查询" onclick="MemberList.bind(1)"></span>
     <!--     @*<span id="screening"><a href="javascript:void(0);">更多筛选条件 &or;</a></span>*@-->
        <span><a href="/platform/member/showMemberAdd" target="_self"><input class="inquire" name="" type="button" value="+添加用户"></a></span>
     <!--   @*<span><input class="inquire" name="" type="button" value="导出Excel表"></span>*@-->
    </div><!--account-form stop -->
    <div class="clear"></div>
    <div class="mar35"></div>
    <div class="member-xz" style="margin-top:10px;">
        <span><input id="selectall" name="chk_list" type="checkbox" value="">全选</span>
        <span><input class="member-inp" name="emailsend" id="emailsend" type="button" value="批量发邮件"></span>
        <span><input class="member-inp" name="mobilesend" id="mobilesend" type="button" value="批量发短信"></span>
        <span><input class="member-inp" name="messagesend" id="messagesend" type="button" value="批量发站内信"></span>
        <span><input class="member-inp" name="pushmsg" id="pushmsg" type="button" value="批量推送消息"></span>
    </div>
    <div class="table-con">
        <table class="data_list">
            <tr id="memberlist_title">
                <th>选择</th>
                <th>用户名</th>
                <%--<th>邮箱</th> --%>
                
                <th>手机</th>
                <th>性别</th>
                <th>省份</th>
                <th>加入日期</th>
               <%-- <th>账户余额</th> --%>
                <th>账户状态</th>
                <th>操作</th>
            </tr>
            <script id="memberlist" type="text/html">
                {{each list as pro i}}
                <tr>
                    <td><input name="chk_list" class="member_check_list" type="checkbox" value="{{pro.id}}"></td>
					
                    <td>{{pro.username}}</td>
                   <%-- <td id="td_memberemail">{{pro.email}}</td>--%>
                    <td id="td_membermobile">{{pro.mobile}}</td>
                    {{if pro.sex==1 }}
                    <td>男</td>
                    {{else if pro.sex==2 }}
                    <td>女</td>
                    {{else if pro.sex==0}}
                    <td>保密</td>
					{{else}}
                    <td>---</td>
                    {{/if}}
					{{if pro.provincecode==-1 }}
					<td> </td>
					{{else}}
                    <td>{{pro.provincename}}</td>
					{{/if}}
                    <td>{{pro.createtimestr}}</td>
                    <%--{{if pro.balance==null || pro.balance==0 }}
                    <td>0.00</td>
                    {{else}}
                    
                    <td>{{pro.balance | toFixed}}</td>
                    {{/if}}--%>
					<td>
                            {{if pro.status==0}}
                            		正常
                            {{else }}
                            	冻结
                            {{/if}}
                            
                     </td>
                    <td class="member-operate">
                        <input type="hidden" id="hidden_memberid" value="{{pro.userid}}" />
                        <a href="javascript:void(0);" class="a_memberupdate"><span class="editor-red shenlan">编辑</span></a>
                        <a href="javascript:void(0);" class="a_memberemail"><span class="emails shenlan">发送邮件</span></a>
                        <a href="javascript:void(0);" class="a_membermobile"><span class="iphone shenlan">发送短信</span></a>
                        <a href="javascript:void(0);" class="a_memberinfo"><span class="information shenlan">发送站内信</span></a>
						<a href="javascript:void(0);" class="a_memberpush"><span class="shenlan">推送消息</span></a>
                        <%--<a href="/platform/member/showMember_Recharge?memberid={{pro.userid}}"><span class="shenlan">会员充值</span></a> --%>
                       <a href="javascript:void(0);" class="a_pwdupdate"><span class="shenlan">修改密码</span></a>
						<%--<a href="javascript:void(0);" class="a_pwdpaypaupdate"><span class="shenlan">修改支付密码</span></a>--%> 
 
                        <a href="javascript:void(0);" class="a_memberdelete"><span class="delete shenlan">删除</span></a>
						{{if pro.status==0}}
                            <span class="shenlan"><a id="a_{{pro.id}}" href="javascript:void(0);" onclick="setStatus({{pro.id}},1)">冻结</a></span>
                            {{else }}
                            <span class="shenlan"><a id="a_{{pro.id}}" href="javascript:void(0);" onclick="setStatus({{pro.id}},0)">解冻</a></span>
                            {{/if}}
                    </td>
                </tr>
                {{/each}}
            </script>
<!-- <a href="/platform/member/showMember_bankcard?memberid={{pro.userid}}"><span class="shenlan">绑定银行卡列表</span></a> -->
        </table>
    </div><!--table-con  stop -->
    <div class="clear"></div>

    <div id="pager" class="page">

    </div><!--page stop -->


</div><!--mainright stop -->
<script type="text/javascript">
    $(document).ready(function () {
        //初始化数据
        Init.bind();
        MemberList.bind(1);
    })
</script>
