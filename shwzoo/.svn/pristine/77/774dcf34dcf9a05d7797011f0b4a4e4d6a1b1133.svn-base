package com.yinlian.app.alipay;


import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.yinlian.Extended.LogType;
import com.yinlian.alipay.AlipayRefundInfo;
import com.yinlian.wssc.web.util.ProductNumUtil;
import com.yl.soft.log.LogHandle;

public class AlipayReturnUtil {

	/**
     * 
     * @方法名称:alipayRefundRequest
     * @内容摘要: ＜支付宝退款请求＞
     * @param out_trade_no 订单支付时传入的商户订单号,不能和 trade_no同时为空。
     * @param trade_no 支付宝交易号，和商户订单号不能同时为空
     * @param refund_amount 需要退款的金额，该金额不能大于订单金额,单位为元，支持两位小数
     * @return 
     * String
     * @exception 
     * @author:鹿伟伟
     * @创建日期:2016年4月12日-下午4:53:30
     */
    public static String alipayRefundRequest(String out_trade_no,String trade_no,double refund_amount){

        // 发送请求
        String strResponse = null;
        try {
            AlipayClient alipayClient = new DefaultAlipayClient
                    ("https://openapi.alipay.com/gateway.do",AppAlipayConfig.getAlipayConfig().appid,
                    	AppAlipayConfig.getAlipayConfig().private_key,"json",
                    	AppAlipayConfig.getAlipayConfig().input_charset,
                    	AppAlipayConfig.getAlipayConfig().alipay_app_public_key,AppAlipayConfig.getAlipayConfig().sign_type);
            AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
            AlipayRefundInfo alidata= new AlipayRefundInfo();
            alidata.setOut_trade_no(out_trade_no);
            alidata.setRefund_amount(refund_amount);
            alidata.setTrade_no(trade_no);
            alidata.setOut_request_no(ProductNumUtil.GetOrderNum());
            request.setBizContent(JSON.toJSONString(alidata));
//            request.setBizContent(JsonUtils.convertToString(alidata));
            AlipayTradeRefundResponse response = alipayClient.execute(request);
            strResponse=response.getCode();
            if ("10000".equals(response.getCode())) {
                //strResponse="退款成功";
            }else {
                strResponse=response.getSubMsg();
            }
        } catch (Exception e) {
        	LogHandle.error(LogType.Order,"支付订单异常! 异常信息:", e,
					"/alipayByGroupOrder");
        }
        return strResponse;

    }
    public static void main(String[] args) {
        //randomValidata();
    	alipayRefundRequest("G20171012094800001","2017101221001004440278561364",0.01);
    }
}
