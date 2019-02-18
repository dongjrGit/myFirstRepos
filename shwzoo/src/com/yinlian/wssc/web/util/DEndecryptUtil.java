package com.yinlian.wssc.web.util;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.concurrent.ExecutionException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.params.HttpMethodParams;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * <p>
 * Title:加密解密
 * </p>
 * 
 * <p>
 * Description: 该类对外提供通信数据的加密解密以及密码加密方式，上层业务系统使用可以对路径进行修改重新编译，但不能修改其它内容
 * </p>
 * 
 * <p>
 * Date : 2015-11-11
 * </p>
 * 
 * <p>
 * Copyright: Copyright (c) 2015 sinoufc
 * </p>
 * 
 * <p>
 * Company: sinoufc
 * </p>
 * 
 * @author tians
 * @version 1.00
 * 
 *          <p>
 *          ------------------------------------------------------------
 *          </p>
 *          <p>
 *          修改历史
 *          </p>
 *          <p>
 *          序号 日期 修改人 修改原因
 *          </p>
 *          <p>
 *          1
 *          </p>
 */

public class DEndecryptUtil {
	private DEndecryptUtil() {

	}

	private static DEndecryptUtil _instances;

	public static DEndecryptUtil get_instances() {
		if (_instances == null) {
			_instances = new DEndecryptUtil();
		}
		return _instances;
	}

    private final String   SPKEY     = "SINOUFC-JK-2015";
    private final String[] strDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a",
            "b", "c", "d", "e", "f" };

    /**
     * 转化字节为字符
     * 
     * @param byte bByte 需要转换的byte
     * @return String 返回转后后String
     */
    private String byteToString(byte bByte) {
        int iRet = bByte;
        if (iRet < 0) {
            iRet += 256;
        }
        int iD1 = iRet / 16;
        int iD2 = iRet % 16;
        return strDigits[iD1] + strDigits[iD2];
    }

    /**
     * 转换字节数组为16进制字串
     * 
     * @param byte[] bByte 需要转换的byte数组
     * @return String 返回转后后String
     */
    private String byteToString(byte[] bByte) {
        StringBuffer sBuffer = new StringBuffer();
        for (int i = 0; i < bByte.length; i++) {
            sBuffer.append(byteToString(bByte[i]));
        }
        return sBuffer.toString();
    }

    /**
     * MD5加密
     * @param String
     *            src 需要加密的String
     * @return String 加密后的String 机密失败或异常返回null,对密码加密时使用
     */
    private String md5Encrypt(String str) {
        String returnStr = null;
        try {
            returnStr = byteToString(md5(str));
        } catch (Exception e) {
            returnStr = null;
            e.printStackTrace();
        }
        return returnStr;
    }

    /**
     * 进行MD5加密
     * 
     * @param String
     *            strSrc 需要机密的字符
     * @return byte[] 指定加密方式为md5后的byte[]
     */

    private byte[] md5(String strSrc) {
        byte[] returnByte = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            returnByte = md5.digest(strSrc.getBytes("UTF-8"));
        } catch (Exception e) {
            returnByte = null;
            e.printStackTrace();
        }
        return returnByte;
    }

    /**
     * 得到3-DES的密钥匙 根据接口规范，密钥匙为24个字节，md5加密出来的是16个字节，因此后面补8个字节的0
     * 
     * @param Strin
     *            spKey 原始的SPKEY
     * 
     * @return byte[] 指定加密方式为md5后的byte[]
     */

    private byte[] getEnKey(String spKey) {
        byte[] desKey = null;
        try {
            byte[] desKey1 = md5(spKey);
            desKey = new byte[24];
            int i = 0;
            while (i < desKey1.length && i < 24) {
                desKey[i] = desKey1[i];
                i++;
            }
            if (i < 24) {
                desKey[i] = 0;
                i++;
            }
        } catch (Exception e) {
            desKey = null;
            e.printStackTrace();
        }

        return desKey;
    }

    /**
     * 3-DES加密
     * 
     * @param byte[] src 要进行3-DES加密的byte[]
     * @param byte[] enKey 3-DES加密密钥
     * @return byte[] 3-DES加密后的byte[]
     */

    private byte[] Encrypt(byte[] src, byte[] enKey) {
        byte[] encryptedData = null;
        try {
            DESedeKeySpec dks = new DESedeKeySpec(enKey);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
            SecretKey key = keyFactory.generateSecret(dks);
            Cipher cipher = Cipher.getInstance("DESede");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            encryptedData = cipher.doFinal(src);
        } catch (Exception e) {
            encryptedData = null;
            e.printStackTrace();
        }
        return encryptedData;
    }

    /**
     * 对字符串进行Base64编码
     * 
     * @param byte[] src 要进行编码的字符
     * 
     * @return String 进行编码后的字符串
     */

    private String getBase64Encode(byte[] src) {
        String requestValue = null;
        try {
            BASE64Encoder base64en = new BASE64Encoder();
            requestValue = base64en.encode(src);
            // System.out.println(requestValue);
        } catch (Exception e) {
            requestValue = null;
            e.printStackTrace();
        }

        return requestValue;
    }

    /**
     * 去掉字符串的换行符号 base64编码3-DES的数据时，得到的字符串有换行符号 ，一定要去掉
     * 
     */

    private String filter(String str) {
        String output = null;
        try {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < str.length(); i++) {
                int asc = str.charAt(i);
                if (asc != 10 && asc != 13)
                    sb.append(str.subSequence(i, i + 1));
            }
            output = new String(sb);
        } catch (Exception e) {
            output = null;
            e.printStackTrace();
        }
        return output;
    }

    /**
     * 对字符串进行URLDecoder.encode(strEncoding)编码
     * 
     * @param String
     *            src 要进行编码的字符串
     * 
     * @return String 进行编码后的字符串
     */

    private String getURLEncode(String src) {
        String requestValue = null;
        try {

            requestValue = URLEncoder.encode(src, "UTF-8");
        } catch (Exception e) {
            requestValue = null;
            e.printStackTrace();
        }

        return requestValue;
    }

    /**
     * 3-DES加密
     * 
     * @param String
     *            src 要进行3-DES加密的String
     * @param String
     *            spkey分配的SPKEY
     * @return String 3-DES加密后的String
     */

    private String get3DESEncrypt(String src, String spkey) {
        String requestValue = null;
        try {

            // 得到3-DES的密钥匙
            byte[] enKey = getEnKey(spkey);
            // 要进行3-DES加密的内容在进行/"UTF-16LE/"取字节
            byte[] src2 = src.getBytes("UTF-16LE");
            // 进行3-DES加密后的内容的字节
            byte[] encryptedData = Encrypt(src2, enKey);

            // 进行3-DES加密后的内容进行BASE64编码
            String base64String = getBase64Encode(encryptedData);
            // BASE64编码去除换行符后
            String base64Encrypt = filter(base64String);

            // 对BASE64编码中的HTML控制码进行转义的过程
            requestValue = getURLEncode(base64Encrypt);
            // System.out.println(requestValue);
        } catch (Exception e) {
            requestValue = null;
            e.printStackTrace();
        }

        return requestValue;
    }

    /**
     * 对字符串进行URLDecoder.decode(strEncoding)解码
     * 
     * @param String
     *            src 要进行解码的字符串
     * 
     * @return String 进行解码后的字符串
     */

    private String getURLDecoderdecode(String src) {
        String requestValue = null;
        try {

            requestValue = URLDecoder.decode(src, "UTF-8");
        } catch (Exception e) {
            requestValue = null;
            e.printStackTrace();
        }

        return requestValue;
    }

    /**
     * 
     * 进行3-DES解密（密钥匙等同于加密的密钥匙）。
     * 
     * @param byte[] src 要进行3-DES解密byte[]
     * @param String
     *            spkey分配的SPKEY
     * @return String 3-DES解密后的String
     */
    private String deCrypt(byte[] debase64, String spKey) {
        String strDe = null;
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("DESede");
            byte[] key = getEnKey(spKey);
            DESedeKeySpec dks = new DESedeKeySpec(key);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
            SecretKey sKey = keyFactory.generateSecret(dks);
            cipher.init(Cipher.DECRYPT_MODE, sKey);
            byte ciphertext[] = cipher.doFinal(debase64);
            strDe = new String(ciphertext, "UTF-16LE");
        } catch (Exception ex) {
            strDe = null;
            ex.printStackTrace();
        }
        return strDe;
    }

    /**
     * 3-DES解密
     * 
     * @param String
     *            src 要进行3-DES解密的String
     * @param String
     *            spkey分配的SPKEY
     * @return String 3-DES加密后的String
     */

    private String get3DESDecrypt(String src, String spkey) {
        String requestValue = null;
        try {

            // 得到3-DES的密钥匙

            // URLDecoder.decodeTML控制码进行转义的过程
            String URLValue = getURLDecoderdecode(src);

            // 进行3-DES加密后的内容进行BASE64编码

            BASE64Decoder base64Decode = new BASE64Decoder();
            byte[] base64DValue = base64Decode.decodeBuffer(URLValue);

            // 要进行3-DES加密的内容在进行/"UTF-16LE/"取字节

            requestValue = deCrypt(base64DValue, spkey);
        } catch (Exception e) {
            requestValue = null;
            e.printStackTrace();
        }
        return requestValue;
    }

    /**
     * 
     * @param String
     *            src 需要加密的String
     * @return String 加密后的String 加密失败或异常返回null,对通信报文进行解密处理
     */
    public String encrypt(String str) {
        String returnStr = null;
        try {
            returnStr = this.get3DESEncrypt(str, this.SPKEY);
        } catch (Exception e) {
            returnStr = null;
            e.printStackTrace();
        }
        return returnStr;
    }

    /**
     * 
     * @param String
     *            src 需要解密的String
     * @return String 机密后的String 解密失败或异常返回null,对通信报文进行解密处理
     */
    public String decrypt(String str) {
        String returnStr = null;
        try {
            returnStr = this.get3DESDecrypt(str, this.SPKEY);
        } catch (Exception e) {
            returnStr = null;
            e.printStackTrace();
        }
        return returnStr;
    }

    /**
     * 密码加密工具
     * @param String
     *            src 需要加密的String
     * @return String 加密后的String 机密失败或异常返回null,对密码加密时使用
     */
    public String passwordEncrypt(String str) {
        String returnStr = null;
        try {
            returnStr = this.md5Encrypt(this.get3DESEncrypt(str, this.SPKEY));
        } catch (Exception e) {
            returnStr = null;
            e.printStackTrace();
        }
        return returnStr;
    }

    /***
     * base64 加密
     */
    public  String Base64Encode(String src) {
        String requestValue = null;
        try {
            BASE64Encoder base64en = new BASE64Encoder();
            requestValue = base64en.encode(src.getBytes());
            // System.out.println(requestValue);
        } catch (Exception e) {
            requestValue = null;
            e.printStackTrace();
        }
        return requestValue;
    }

    /**
     * base64解密
     * @param src
     * @return
     */
    public  String Base64Decode(String src) {
        BASE64Decoder base64Decode = new BASE64Decoder();
        byte[] base64DValue = null;
        try {
            base64DValue = base64Decode.decodeBuffer(src);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(base64DValue);
    }

    public static void main(String[] args) {
        DEndecryptUtil test = new DEndecryptUtil();
        String oldString = " 毒 素发\ndd\n-#$%!<>';{}\\n\t[\\t]<>/,“， ";
        System.out.println("需要加密的内容为:\n" + oldString);
        String reValue = test.passwordEncrypt(oldString);
        System.out.println("密码方式加密后的内容为:\n" + reValue);
        String reValue1 = test.encrypt(oldString);
        System.out.println("加密后的内容:\n" + reValue1);
        String reValue2 = test.decrypt(reValue1);
        System.out.println("解密后的内容:\n" + reValue2);

        System.out.println("解密后的内容是否为原内容相同:" + oldString.equals(reValue2));
    }

    private int                  connectionTimeout = 10000;     // 连接超时时间
    private static final String  CONTENT_TYPE      = "text/xml"; // 传递报文的格式
    private static final int     RETRY_COUNT       = 3;
    private static final boolean SENT_RETRY        = true;

    /**
     * 
     * @param url
     *            接口地址
     * @param xml
     *            传递的xml数据
     * @return 接口返回的内容（为模拟淘宝的调用）
     */
    public String post(String url, String xml) {
        String result = "";
        PostMethod postMethod = new PostMethod(url);

        HttpClientParams httpClientParams = new HttpClientParams();
        httpClientParams.setConnectionManagerTimeout(this.connectionTimeout);
        httpClientParams.setSoTimeout(this.connectionTimeout);
        MultiThreadedHttpConnectionManager connectionManager = new MultiThreadedHttpConnectionManager();
        HttpClient httpClient = new HttpClient(httpClientParams, connectionManager);
        postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
            new DefaultHttpMethodRetryHandler(RETRY_COUNT, SENT_RETRY));
        try {
            postMethod.setRequestEntity(new StringRequestEntity(xml, CONTENT_TYPE, "GBK")); // utf-8格式
            postMethod.addRequestHeader("Connection", "close");
            System.out.println(postMethod.getResponseCharSet());
            httpClient.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);
            int statusCode = httpClient.executeMethod(postMethod);
            if (statusCode != HttpStatus.SC_OK) {
                System.out.println("接口状态为" + statusCode);
            } else {
                // byte[] bodydata = postMethod.getResponseBody();
                // result = new String(bodydata, "GBK"); //转换格式
                // String strResponse = null;
                // StringBuffer buffer = new StringBuffer();
                // BufferedReader reader = null;
                // reader = new BufferedReader(new
                // InputStreamReader(postMethod.getResponseBodyAsStream()));
                // while ((strResponse = reader.readLine()) != null) {
                // buffer.append(strResponse);
                result = postMethod.getResponseBodyAsString();
                // }
            }
        } catch (Exception e) {
            e.printStackTrace();
            try {
                throw new ExecutionException(e);
            } catch (ExecutionException e1) {
                e1.printStackTrace();
            }
        } finally {
            postMethod.releaseConnection();
        }
        return result;
    }
}
