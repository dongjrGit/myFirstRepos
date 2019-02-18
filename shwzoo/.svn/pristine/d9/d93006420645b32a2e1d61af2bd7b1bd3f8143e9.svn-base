package com.yinlian.wssc.search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.yinlian.api.app.dto.TrackQueryDto;
import com.yinlian.wssc.web.util.ConstanValue;

/**
 *
 * 快递鸟物流轨迹即时查询接口
 *
 * @技术QQ群: 456320272
 * @see: http://www.kdniao.com/YundanChaxunAPI.aspx
 * @copyright: 深圳市快金数据技术服务有限公司
 *
 * DEMO中的电商ID与私钥仅限测试使用，正式环境请单独注册账号
 * 单日超过500单查询量，建议接入我方物流轨迹订阅推送接口
 * 
 * ID和Key请到官网申请：http://www.kdniao.com/ServiceApply.aspx
 */

public class KdniaoTrackQueryAPI {

    private static final String DEFAULT_CHARSET = "UTF-8";                 // 默认编码

    private static final String DATATYPE_JSON   = "2";                     // json的数据类型

    private static final String DATATYPE_XML    = "1";                     //xml的数据类型

    private String              EBusinessID     = ConstanValue.EBUSINESSID;

    private String              AppKey          = ConstanValue.APPKEY;

    private String              ReqURL          = ConstanValue.REQURL;

    /**
     * Json方式 查询订单物流轨迹
     * @throws Exception 
     */
    public String getOrderTracesByJson(String ordercode, String expCode, String expNo)
                                                                                      throws Exception {
        String requestData = "{'OrderCode':'" + ordercode + "','ShipperCode':'" + expCode
                             + "','LogisticCode':'" + expNo + "'}";

        Map<String, String> params = new HashMap<String, String>();
        params.put("RequestData", urlEncoder(requestData, DEFAULT_CHARSET));
        params.put("EBusinessID", EBusinessID);
        params.put("RequestType", ConstanValue.REQUESTTYPE);
        String dataSign = encrypt(requestData, AppKey, DEFAULT_CHARSET);
        params.put("DataSign", urlEncoder(dataSign, DEFAULT_CHARSET));
        params.put("DataType", DATATYPE_JSON);

        String result = sendPost(ReqURL, params);

        //根据公司业务处理返回的信息......

        return result;
    }

    /**
     * XML方式 查询订单物流轨迹
     * @throws Exception 
     */
    public String getOrderTracesByXml() throws Exception {
        String requestData = "<?xml version=\"1.0\" encoding=\"utf-8\" ?>" + "<Content>"
                             + "<OrderCode></OrderCode>" + "<ShipperCode>SF</ShipperCode>"
                             + "<LogisticCode>589707398027</LogisticCode>" + "</Content>";

        Map<String, String> params = new HashMap<String, String>();
        params.put("RequestData", urlEncoder(requestData, DEFAULT_CHARSET));
        params.put("EBusinessID", EBusinessID);
        params.put("RequestType", ConstanValue.REQUESTTYPE);
        String dataSign = encrypt(requestData, AppKey, DEFAULT_CHARSET);
        params.put("DataSign", urlEncoder(dataSign, DEFAULT_CHARSET));
        params.put("DataType", DATATYPE_XML);

        String result = sendPost(ReqURL, params);

        //根据公司业务处理返回的信息......

        return result;
    }

    /**
     * MD5加密
     * @param str 内容       
     * @param charset 编码方式
     * @throws Exception 
     */
    private String MD5(String str, String charset) throws Exception {
        MessageDigest md = MessageDigest.getInstance(ConstanValue.MD5);
        md.update(str.getBytes(charset));
        byte[] result = md.digest();
        StringBuffer sb = new StringBuffer(32);
        for (int i = 0; i < result.length; i++) {
            int val = result[i] & 0xff;
            if (val <= 0xf) {
                sb.append("0");
            }
            sb.append(Integer.toHexString(val));
        }
        return sb.toString().toLowerCase();
    }

    /**
     * base64编码
     * @param str 内容       
     * @param charset 编码方式
     * @throws UnsupportedEncodingException 
     */
    private String base64(String str, String charset) throws UnsupportedEncodingException {
        String encoded = base64Encode(str.getBytes(charset));
        return encoded;
    }

    private String urlEncoder(String str, String charset) throws UnsupportedEncodingException {
        String result = URLEncoder.encode(str, charset);
        return result;
    }

    /**
     * 电商Sign签名生成
     * @param content 内容   
     * @param keyValue Appkey  
     * @param charset 编码方式
     * @throws UnsupportedEncodingException ,Exception
     * @return DataSign签名
     */
    private String encrypt(String content, String keyValue, String charset)
                                                                           throws UnsupportedEncodingException,
                                                                           Exception {
        if (keyValue != null) {
            return base64(MD5(content + keyValue, charset), charset);
        }
        return base64(MD5(content, charset), charset);
    }

    /**
    * 向指定 URL 发送POST方法的请求     
    * @param url 发送请求的 URL    
    * @param params 请求的参数集合     
    * @return 远程资源的响应结果
    */
    private String sendPost(String url, Map<String, String> params) {
        OutputStreamWriter out = null;
        BufferedReader in = null;
        StringBuilder result = new StringBuilder();
        try {
            URL realUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // POST方法
            conn.setRequestMethod("POST");
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.connect();
            // 获取URLConnection对象对应的输出流
            out = new OutputStreamWriter(conn.getOutputStream(), DEFAULT_CHARSET);
            // 发送请求参数            
            if (params != null) {
                StringBuilder param = new StringBuilder();
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    if (param.length() > 0) {
                        param.append("&");
                    }
                    param.append(entry.getKey());
                    param.append("=");
                    param.append(entry.getValue());
                    //System.out.println(entry.getKey()+":"+entry.getValue());
                }
                //System.out.println("param:"+param.toString());
                out.write(param.toString());
            }
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            int code = conn.getResponseCode();
            if (code == 200) {
                in = new BufferedReader(new InputStreamReader(conn.getInputStream(),
                    DEFAULT_CHARSET));
            }
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result.toString();
    }

    private static char[] base64EncodeChars = new char[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
            'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y',
            'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
            'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6',
            '7', '8', '9', '+', '/'        };

    public static String base64Encode(byte[] data) {
        StringBuffer sb = new StringBuffer();
        int len = data.length;
        int i = 0;
        int b1, b2, b3;
        while (i < len) {
            b1 = data[i++] & 0xff;
            if (i == len) {
                sb.append(base64EncodeChars[b1 >>> 2]);
                sb.append(base64EncodeChars[(b1 & 0x3) << 4]);
                sb.append("==");
                break;
            }
            b2 = data[i++] & 0xff;
            if (i == len) {
                sb.append(base64EncodeChars[b1 >>> 2]);
                sb.append(base64EncodeChars[((b1 & 0x03) << 4) | ((b2 & 0xf0) >>> 4)]);
                sb.append(base64EncodeChars[(b2 & 0x0f) << 2]);
                sb.append("=");
                break;
            }
            b3 = data[i++] & 0xff;
            sb.append(base64EncodeChars[b1 >>> 2]);
            sb.append(base64EncodeChars[((b1 & 0x03) << 4) | ((b2 & 0xf0) >>> 4)]);
            sb.append(base64EncodeChars[((b2 & 0x0f) << 2) | ((b3 & 0xc0) >>> 6)]);
            sb.append(base64EncodeChars[b3 & 0x3f]);
        }
        return sb.toString();
    }

    //DEMO
    public static void main(String[] args) {
        KdniaoTrackQueryAPI api = new KdniaoTrackQueryAPI();
        try {
            String result = api.getOrderTracesByJson("", "SF", "210706289747");

            System.out.println(result);

            Gson gson = new Gson();
            TrackQueryDto dto = gson.fromJson(result, TrackQueryDto.class);
            System.out.println(dto.toString());
            // {  \"EBusinessID\": "1256897",  \"ShipperCode\": "SF",  \"Success\": true,  "LogisticCode": "210706289747",  "State": "3",  "Traces": [    {      "AcceptTime": "2016-04-25 18:35:49",      "AcceptStation": "顺丰速运 已收取快件",      "Remark": ""    },    {      "AcceptTime": "2016-04-25 18:35:49",      "AcceptStation": "快件离开【合肥政务区银泰营业点】,正发往 【合肥经开集散中心】",      "Remark": ""    },    {      "AcceptTime": "2016-04-25 19:55:55",      "AcceptStation": "快件到达 【合肥经开集散中心】",      "Remark": ""    },    {      "AcceptTime": "2016-04-25 20:53:28",      "AcceptStation": "快件离开【合肥经开集散中心】,正发往 【北京国航集散中心】",      "Remark": ""    },    {      "AcceptTime": "2016-04-26 17:51:28",      "AcceptStation": "快件到达 【北京国航集散中心】",      "Remark": ""    },    {      "AcceptTime": "2016-04-26 17:51:58",      "AcceptStation": "快件离开【北京国航集散中心】,正发往 【北京首都机场集散中心2】",      "Remark": ""    },    {      "AcceptTime": "2016-04-26 18:02:55",      "AcceptStation": "快件到达 【北京首都机场集散中心2】",      "Remark": ""    },    {      "AcceptTime": "2016-04-26 18:02:55",      "AcceptStation": "快件离开【北京首都机场集散中心2】,正发往 【北京顺义集散中心】",      "Remark": ""    },    {      "AcceptTime": "2016-04-26 20:52:43",      "AcceptStation": "快件到达 【北京顺义集散中心】",      "Remark": ""    },    {      "AcceptTime": "2016-04-26 22:01:09",      "AcceptStation": "快件离开【北京顺义集散中心】,正发往 【北京平房桥集散中心】",      "Remark": ""    },    {      "AcceptTime": "2016-04-26 23:25:33",      "AcceptStation": "快件到达 【北京平房桥集散中心】",      "Remark": ""    },    {      "AcceptTime": "2016-04-27 01:16:28",      "AcceptStation": "快件离开【北京平房桥集散中心】,正发往 【北京朝阳常营回族区营业点】",      "Remark": ""    },    {      "AcceptTime": "2016-04-27 07:34:51",      "AcceptStation": "快件到达 【北京朝阳常营回族区营业点】",      "Remark": ""    },    {      "AcceptTime": "2016-04-27 08:37:04",      "AcceptStation": "正在派送途中,请您准备签收(派件人:相里鹏博,电话:13811843394)",      "Remark": ""    },    {      "AcceptTime": "2016-04-27 11:50:37",      "AcceptStation": "已签收,感谢使用顺丰,期待再次为您服务",      "Remark": ""    },    {      "AcceptTime": "2016-04-27 11:52:30",      "AcceptStation": "在官网运单资料&签收图,可查看签收人信息",      "Remark": ""    }  ]}
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
