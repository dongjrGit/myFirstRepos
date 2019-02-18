package ticket.xml.utils;

import com.yinlian.wssc.web.po.Idcardinfo;
import com.yinlian.wssc.web.po.Orderdetail;

public class XmlFuncUtils {
	
	//SEND_CODE_REQ  下单
	public static String sendCodeReq(String groupNum,String price,Idcardinfo idcardinfo1,String odsstring){
		StringBuffer stringBuffer2 = new StringBuffer();
		stringBuffer2.append("<order>");
		stringBuffer2.append("<certificateNo>" + idcardinfo1.getCard() + "</certificateNo>");
		stringBuffer2.append("<linkName>" + idcardinfo1.getName() + "</linkName>");
		stringBuffer2.append("<linkMobile>" + idcardinfo1.getPhone() + "</linkMobile>");
		stringBuffer2.append("<orderCode>" + groupNum + "</orderCode>"); // A
		stringBuffer2.append("<orderPrice>" + price + "</orderPrice>");
		stringBuffer2.append("<payMethod>third_vm</payMethod>");
		stringBuffer2.append("<ticketOrders>");
		// 多种类商品循环
		stringBuffer2.append(odsstring);
		stringBuffer2.append("</ticketOrders>");
		stringBuffer2.append("</order>");

		return stringBuffer2.toString();
	}
	//QUERY_IMG_URL_REQ  获取二维码
	public static String queryImgUrlReq(String groupNum){
		StringBuffer stringImg = new StringBuffer();
		stringImg.append("<order>");
		stringImg.append("<orderCode>" + groupNum + "</orderCode>");
		stringImg.append("</order>");
		return stringImg.toString();
	}
	//QUERY_ORDER_REQ  查询订单
	public static String queryCodeReq(String groupNum){
		StringBuffer stringCode = new StringBuffer();
		stringCode.append("<order>");
		stringCode.append("<orderCode>" + groupNum + "</orderCode>");
		stringCode.append("</order>");
		return stringCode.toString();
	}
	//SEND_SM_REQ  发短信
	public static String sendSmReq(String groupNum){
		StringBuffer stringMsg = new StringBuffer();
		stringMsg.append("<order>");
		stringMsg.append("<orderCode>" + groupNum + "</orderCode>");
		stringMsg.append("<tplCode>20130914000000001</tplCode>");
		stringMsg.append("</order>");
		return stringMsg.toString();
	}
	//RETURN_TICKET_NUM_NEW_REQ  退票申请
	public static String returnTicketReq(Orderdetail detail){
		StringBuffer stringMsg = new StringBuffer();
		stringMsg.append("<returnTicket>");
		stringMsg.append("<orderCode>" + detail.getProcode() + "</orderCode>"); // 子订单号
		stringMsg.append("<returnNum>" + detail.getProductcount() + "</returnNum>"); // 退票数量
		stringMsg.append("<thirdReturnCode></thirdReturnCode>"); // 第三方退单号
		stringMsg.append("<idCards></idCards>"); // 如果是实名制订单退票，请带上身份证号码多个身份证号中间用
													// “,”分割
		stringMsg.append("</returnTicket>"); //
		return stringMsg.toString();
	}
}
