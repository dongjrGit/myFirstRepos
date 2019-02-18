package com.yinlian.wssc.web.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IdCardVerifyUtil {

	// 每位加权因子
	 private static int power[] = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 };
	 
	/** 
     * 18位身份证校验,粗略的校验 
     * @author lyl 
     * @param idCard 
     * @return 
     */  
    public static boolean is18ByteIdCard(String idCard){  
        Pattern pattern1 = Pattern.compile("^(\\d{6})(19|20)(\\d{2})(1[0-2]|0[1-9])(0[1-9]|[1-2][0-9]|3[0-1])(\\d{3})(\\d|X|x)?$"); //粗略的校验  
        Matcher matcher = pattern1.matcher(idCard);  
        if(matcher.matches()){  
            return true;  
        }  
        return false;  
    }
    /** 
     * 15位身份证校验,粗略的校验 
     * @author lyl 
     * @param idCard 
     * @return 
     */  
    public static boolean is15ByteIdCard(String idCard){  
    	 Pattern pattern1 = Pattern.compile("^([1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{2})?$"); 
         Matcher matcher = pattern1.matcher(idCard);  
         if(matcher.matches()){
        	 Map<String, String> cityMap = initCityMap();  
             if(cityMap.get(idCard.substring(0,2)) == null ){  
                 return false;  
             }  
             return true;  
         }  
         return false;  
    }
    /**
     * 将15位的身份证转成18位身份证
     * 
     * @param idcard
     * @return
     */
     public static String convertIdcarBy15bit(String idcard) {
     String idcard17 = null;
     // 非15位身份证
     if (idcard.length() != 15) {
      return null;
     }
     
     if (isDigital(idcard)) {
      // 获取出生年月日
      String birthday = idcard.substring(6, 12);
      Date birthdate = null;
      try {
      birthdate = new SimpleDateFormat("yyMMdd").parse(birthday);
      } catch (Exception e) {
      e.printStackTrace();
      }
      Calendar cday = Calendar.getInstance();
      cday.setTime(birthdate);
      String year = String.valueOf(cday.get(Calendar.YEAR));
     
      idcard17 = idcard.substring(0, 6) + year + idcard.substring(8);
     
      char c[] = idcard17.toCharArray();
      String checkCode = "";
     
      if (null != c) {
      int bit[] = new int[idcard17.length()];
     
      // 将字符数组转为整型数组
      bit = converCharToInt(c);
      int sum17 = 0;
      sum17 = getPowerSum(bit);
     
      // 获取和值与11取模得到余数进行校验码
      checkCode = getCheckCodeBySum(sum17);
      // 获取不到校验位
      if (null == checkCode) {
       return null;
      }
     
      // 将前17位与第18位校验码拼接
      idcard17 += checkCode;
      }
     } else { // 身份证包含数字
      return null;
     }
     return idcard17;
     }
     /**
      * 数字验证
      * 
      * @param str
      * @return
      */
      public static boolean isDigital(String str) {
      return str == null || "".equals(str) ? false : str.matches("^[0-9]*$");
      }
      /**
       * 将身份证的每位和对应位的加权因子相乘之后，再得到和值
       * 
       * @param bit
       * @return
       */
       public static int getPowerSum(int[] bit) {
       
       int sum = 0;
       
       if (power.length != bit.length) {
        return sum;
       }
       
       for (int i = 0; i < bit.length; i++) {
        for (int j = 0; j < power.length; j++) {
        if (i == j) {
         sum = sum + bit[i] * power[j];
        }
        }
       }
       return sum;
       }
       
       /**
       * 将和值与11取模得到余数进行校验码判断
       * 
       * @param checkCode
       * @param sum17
       * @return 校验位
       */
       public static String getCheckCodeBySum(int sum17) {
       String checkCode = null;
       switch (sum17 % 11) {
       case 10:
        checkCode = "2";
        break;
       case 9:
        checkCode = "3";
        break;
       case 8:
        checkCode = "4";
        break;
       case 7:
        checkCode = "5";
        break;
       case 6:
        checkCode = "6";
        break;
       case 5:
        checkCode = "7";
        break;
       case 4:
        checkCode = "8";
        break;
       case 3:
        checkCode = "9";
        break;
       case 2:
        checkCode = "x";
        break;
       case 1:
        checkCode = "0";
        break;
       case 0:
        checkCode = "1";
        break;
       }
       return checkCode;
       }
       
       /**
       * 将字符数组转为整型数组
       * 
       * @param c
       * @return
       * @throws NumberFormatException
       */
       public static int[] converCharToInt(char[] c) throws NumberFormatException {
       int[] a = new int[c.length];
       int k = 0;
       for (char temp : c) {
        a[k++] = Integer.parseInt(String.valueOf(temp));
       }
       return a;
       }
    /** 
     * 18位身份证校验,比较严格校验 
     * @author lyl 
     * @param idCard 
     * @return 
     */  
    public static boolean is18ByteIdCardComplex(String idCard){  
        Pattern pattern1 = Pattern.compile("^(\\d{6})(19|20)(\\d{2})(1[0-2]|0[1-9])(0[1-9]|[1-2][0-9]|3[0-1])(\\d{3})(\\d|X|x)?$");   
        Matcher matcher = pattern1.matcher(idCard);  
        int[] prefix = new int[]{7,9,10,5,8,4,2,1,6,3,7,9,10,5,8,4,2};  
        int[] suffix = new int[]{ 1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2 };  
        if(matcher.matches()){  
            Map<String, String> cityMap = initCityMap();  
            if(cityMap.get(idCard.substring(0,2)) == null ){  
                return false;  
            }  
            int idCardWiSum=0; //用来保存前17位各自乖以加权因子后的总和  
            for(int i=0;i<17;i++){  
                idCardWiSum+=Integer.valueOf(idCard.substring(i,i+1))*prefix[i];  
            }  
              
            int idCardMod=idCardWiSum%11;//计算出校验码所在数组的位置  
            String idCardLast=idCard.substring(17);//得到最后一位身份证号码  
              
            //如果等于2，则说明校验码是10，身份证号码最后一位应该是X  
            if(idCardMod==2){  
                if(idCardLast.equalsIgnoreCase("x")){  
                    return true;  
                }else{  
                    return false;  
                }  
            }else{  
                //用计算出的验证码与最后一位身份证号码匹配，如果一致，说明通过，否则是无效的身份证号码  
                if(idCardLast.equals(suffix[idCardMod]+"")){  
                    return true;  
                }else{  
                    return false;  
                }  
           }  
        }  
        return false;  
    }  
      
    private static Map<String, String> initCityMap(){  
        Map<String, String> cityMap = new HashMap<String, String>();  
            cityMap.put("11", "北京");  
            cityMap.put("12", "天津");  
            cityMap.put("13", "河北");  
            cityMap.put("14", "山西");  
            cityMap.put("15", "内蒙古");  
              
            cityMap.put("21", "辽宁");  
            cityMap.put("22", "吉林");  
            cityMap.put("23", "黑龙江");  
              
            cityMap.put("31", "上海");  
            cityMap.put("32", "江苏");  
            cityMap.put("33", "浙江");  
            cityMap.put("34", "安徽");  
            cityMap.put("35", "福建");  
            cityMap.put("36", "江西");  
            cityMap.put("37", "山东");  
              
            cityMap.put("41", "河南");  
            cityMap.put("42", "湖北");  
            cityMap.put("43", "湖南");  
            cityMap.put("44", "广东");  
            cityMap.put("45", "广西");  
            cityMap.put("46", "海南");  
              
            cityMap.put("50", "重庆");  
            cityMap.put("51", "四川");  
            cityMap.put("52", "贵州");  
            cityMap.put("53", "云南");  
            cityMap.put("54", "西藏");  
              
            cityMap.put("61", "陕西");  
            cityMap.put("62", "甘肃");  
            cityMap.put("63", "青海");  
            cityMap.put("64", "宁夏");  
            cityMap.put("65", "新疆");  
              
            cityMap.put("71", "台湾");  
            cityMap.put("81", "香港");  
            cityMap.put("82", "澳门");  
            cityMap.put("91", "国外");  
//          System.out.println(cityMap.keySet().size());  
            return cityMap;  
        } 
    public static boolean IdCardComplex(String idCard){  
    	if(idCard.length()==15){
    		idCard=convertIdcarBy15bit(idCard);
    	}
    	return is18ByteIdCardComplex(idCard);
    }
    public static void main(String[] args) {
    	  System.out.println(is18ByteIdCardComplex("340322198908244224"));
    	
    }
}
