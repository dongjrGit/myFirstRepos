package com.music.log;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

public class ApplicationLog {
	Logger logger = Logger.getLogger(ApplicationLog.class);
	String logs = null;
	/** 
     * 前置通知：在某连接点之前执行的通知，但这个通知不能阻止连接点前的执行 
     */  
    public void doBefore(JoinPoint jp) {      
        logs = jp.getTarget().getClass().getName() + " 类的 "  
                + jp.getSignature().getName()+" 方法开始执行 ***Start***";  
        logger.info(logs);  
    }  
      
    /** 
     * 环绕通知：包围一个连接点的通知，可以在方法的调用前后完成自定义的行为，也可以选择不执行 
     */  
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {  
          
        Object result=null;  
        try{    
            result = pjp.proceed();    
        }catch(Exception e){    
            logs = "方法："+pjp.getTarget().getClass() + "." + pjp.getSignature().getName()+ "()  ";  
            logs = logs+"错误信息如下：["+e+"]";  
            logger.info(logs);   
        }  
    
        return result;  
    }  
      
    /** 
     * 后置通知 
     */  
    public void doAfter(JoinPoint jp) {  
        logs =jp.getTarget().getClass().getName() + " 类的 "  
                + jp.getSignature().getName() +" 方法执行结束 ***End***";   
        logger.info(logs);  
    } 
}
