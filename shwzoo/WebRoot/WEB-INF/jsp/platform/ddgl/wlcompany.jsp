<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="l_ckxqcon_con">
<form id="form" method="post">
    <table>
        <tr>
          <td class="text-right">物流公司：</td>
            <td width="245px">
            <select id="logname" name=logname class="the-form-select" style="width:160px;">
                <c:forEach var="wl" items="${wllist}" varStatus="i">
					 <option value="${wl.code}">${wl.name}</option>
				</c:forEach>
				 </select>
            </td>
        </tr>
        <tr>
            <td class="text-right">物流编号：</td>
            <td width="245px">
                <input id="logcode" name="logcode" style="width:160px;" ></input>
            </td>
        </tr>
        <tr>
            <td class="text-right"></td>
            <td width="205px">
                <div class="clear"></div>
                <div class="l_ckxqbotm">
                    <a href="javascript:void(0);" name="btnok" onclick="formSubmit()"><span class="l_ty">确认</span></a>
                    <a href="javascript:void(0);" onclick="Xclose()"><span class="l_bty">关闭</span></a>
                    <input type="hidden" id="orderid" name="orderid" value="${orderid}" >
                </div>

            </td>
        </tr>
    </table>
    </form>
 </div>

<script type="text/javascript">
function formSubmit() {
	if(check().form()){
		 $.ajax({
	         url: "/platform/order/sendprofororder",
	         type: "Post",
	         data: { "orderid": $("#orderid").val(), 
	        	 "logisticscode": $("input[name=logcode]").val(), 
	        	 "logisticsname" : $("#logname").val() },
	         dataType: "json",
	         success: function (data) {
	             if (data.code < 0) {
	                 Dalert(data.desc);
	             } else {
	            	 Dalert(data.desc,"",refresh);
	             }
	         },
	         error: function () {

	         }
	     });
	}
}
//保存前参数验证
function check() {
    return $("#form").validate({
        rules: {
        	logname: {
                required: true
            },
            logcode: {
                required: true
            }
        },
        message: {
        	logname: {
                required: "物流公司不可为空"
            },
            logcode: {
                required: "物流编号不能为空"
            }
        }
    });
}
    function Xclose() {
        parent.window.closeDialog();
    }
   //刷新
    function refresh() {
    parent.location.reload();
    }
</script>