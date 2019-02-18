package com.pushwin.batchwork.utils;
import java.io.File;  
import java.io.FileInputStream;  
import java.io.FileOutputStream;  
import java.io.IOException;  
import java.io.InputStream;  
import java.io.OutputStream;  
import java.net.URISyntaxException;  
import java.net.URLDecoder;
import java.util.Properties;  

import org.springframework.core.io.DefaultResourceLoader;

  
  
public class PropertiesMgr {  
      
    public static String loadPropertie(String fileURL, String name){  
         Properties prop = new Properties();  
         InputStream in = new DefaultResourceLoader().getClassLoader().getResourceAsStream(fileURL);
         try {  
            prop.load(in);  
            return prop.getProperty(name);  
        } catch (IOException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }finally{  
            try {  
                in.close();  
            } catch (IOException e) {  
                // TODO Auto-generated catch block  
                e.printStackTrace();  
            }  
        }  
        return null;  
    }   
    public static Properties loadProperties(String fileURL){  
        Properties prop = new Properties();  
        InputStream in = new DefaultResourceLoader().getClassLoader().getResourceAsStream(fileURL);
        try {  
           prop.load(in);  
           return prop;  
       } catch (IOException e) {  
           // TODO Auto-generated catch block  
           e.printStackTrace();  
       }finally{  
           try {
        	   if(null != in)
               in.close();  
           } catch (IOException e) {  
               // TODO Auto-generated catch block  
               e.printStackTrace();  
           }  
       }  
       return null;  
   } 
    public static void setProperties(String fileURL, String key, String value) {  
        Properties prop = new Properties();  
        InputStream fis = null;  
        OutputStream fos = null;  
        try {  
//	        java.net.URL  url = PropertiesMgr.class.getResource(fileURL); 
            File file = new File(absolutePath() + fileURL);  
            if (!file.exists())  
                file.createNewFile();  
            fis = new FileInputStream(file);  
            prop.load(fis);  
            fis.close();//涓�畾瑕佸湪淇敼鍊间箣鍓嶅叧闂璮is  
            fos = new FileOutputStream(file);  
            prop.setProperty(key, value);  
            prop.store(fos, "Update '" + key + "' value");  
            fos.close();  
              
        } catch (IOException e) {  
            System.err.println("Visit " + fileURL + " for updating "  
            + value + " value error");  
        }  
        finally{  
            try {
            	if(null != fos)
                fos.close();
            	if(null != fis)
                fis.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
    }   
    public static String absolutePath(){
    	String path = new DefaultResourceLoader().getClassLoader().getResource("/").getPath();
    	path = URLDecoder.decode(path);
    	if (path.endsWith("/")) 
    		path = path.substring(0, path.length() - 1);
    	return path;
    }
    public static void main(String[] args) {  
        //ConfigUtil.getFileIO("name", "gxyTest.properties");  
          System.out.println((PropertiesMgr.class.getResource("/") + "").replace("file:/", ""));
    	PropertiesMgr.setProperties("/bantchwork.properties", "name", "microsoft");  
        System.out.println(PropertiesMgr.loadProperties("/bantchwork.properties"));  
    }  
  
}  
