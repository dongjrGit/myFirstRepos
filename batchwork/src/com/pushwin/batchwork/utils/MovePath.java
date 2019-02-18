package com.pushwin.batchwork.utils;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pushwin.batchwork.common.SpringProperty;


//@Service("MovePath")
public class MovePath {
	
	/*protected Logger logger = LoggerFactory.getLogger(MovePath.class);
	
	private static String oldpath = (String) SpringProperty.props
			.get("importoldpath");

	private static String newpath = (String) SpringProperty.props
			.get("importnewpath");
	
	*/
	//@Transactional
	public static void movePath(String oldpath,String newpath){
		try {  
            File oldfile = new File(oldpath);  
            File newfile=new File(newpath+oldpath);
          
            if (oldfile.renameTo(newfile)) {  
                System.out.println("移动成功");  
            } else {  
                System.out.println("移动失败");  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
	
	public static void main(String[] args) {
		String oldpath="D:/NJBANK/njcb/20160301/1.zip";
		String newpath="D:/NJBANK/njcb";
		movePath(oldpath, newpath);
	}
}
