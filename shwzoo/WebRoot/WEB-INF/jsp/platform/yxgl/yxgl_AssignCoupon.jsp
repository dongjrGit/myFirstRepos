<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script src="${pageContext.request.contextPath }/resource/public/platform/js/yxgl/couponassign.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
    	assignc.bind(); 
    })
</script>

<div class="mainright">
    <div class="clear"></div>
    <div class="account-form">
        <span>优惠劵代码：<input type="text" id="code" class="inp-seller" /></span>
       <span class="marrig10"></span>
        <span>过期时间：</span>
        <input type="text" id="ends" class="Wdate" onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd', maxDate: '#F{$dp.$D(\'ende\')}' })" value="" readonly="readonly" />-
        <input type="text" id="ende" class="Wdate" onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd', minDate: '#F{$dp.$D(\'ends\')}' })" value="" readonly="readonly" />
        <span class="marrig10"></span>
        <input class="inquire" name="btnsearch" type="button" value="查询">
    </div>

    <div class="clear"></div>
    <div class="mar35"></div>
    <div class="table-con">
        <table class="data_list">
            <tr id="buy_title">
                <th width="18%">优惠劵代码</th>
                <th width="10%">分配总数</th>
                <th width="10%">剩余分配数</th>
                <th width="8%">面值（元）</th>
                <th width="8%">满足条件</th>
                <th width="10%">过期时间</th>
                <th>操作</th>
            </tr>
            <tbody id="datalist">
                <script id="assignlist" type="text/html">
                    {{each list as coupon i}}
                    <tr>
                        <td>{{coupon.code}}</td>
                        <td>{{coupon.totalcount}}张</td>
                        <td>{{coupon.lastcount}}张</td>
                        <td>{{coupon.value | toFixed}}</td>
                        <td>满{{coupon.quota | toFixed}}元</td>
                        <td>{{coupon.endtimestr}}</td>
                        <td>
{{if coupon.isout==1}}
                           {{if coupon.totalcount==coupon.lastcount}}
                           <a href="yxgl_AssignCouponAdd?gcode={{coupon.code}}"><span class="shenlan">分配</span></a>        
                           {{else if coupon.lastcount>0}} 
                           <a href="yxgl_AssignCouponAdd?gcode={{coupon.code}}"><span class="shenlan">分配</span></a> 
                           <span class="marrig35"></span>
                           <a href="yxgl_AssignCouponDetail?gcode={{coupon.code}}"><span class="shenlan">查看分配</span></a> 
                           {{else}}       
                           <a href="yxgl_AssignCouponDetail?gcode={{coupon.code}}"><span class="shenlan">查看分配</span></a>      
                           {{/if}}
{{else}}
                          {{if coupon.totalcount>coupon.lastcount}}
                          <a href="yxgl_AssignCouponDetail?gcode={{coupon.code}}"><span class="shenlan">查看分配</span></a>   
                          {{/if}}
{{/if}}
                           
                            
                        </td>
                    </tr>
                    {{/each}}
                </script>
            </tbody>
        </table>
    </div>
    <div class="clear"></div>
    <div id="pager" class="page">

    </div>
</div>