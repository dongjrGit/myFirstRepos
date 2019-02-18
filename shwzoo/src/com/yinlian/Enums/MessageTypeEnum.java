package com.yinlian.Enums;

/**信息类型*/
public enum MessageTypeEnum
{
   /**手机信息类型*/
    mobilemes(0),  
    
    /**邮件信息*/
    emailmes(1) ,
    
    /**站内信息*/
    webmes (2);
	
    private final int value; 
    public int getValue() { 
        return value; 
    } 
    //构造器默认也只能是private, 从而保证构造函数只能在内部使用 
    MessageTypeEnum(int value) { 
        this.value = value; 
    } 
}

