
<%@ page language="java" contentType="text/html; charset=UTF-8"  import="java.util.*,java.io.*"
	pageEncoding="UTF-8"%>
	<%
  String path = "";
 Properties pro = new Properties();  
 try{  
 //读取配置文件
	  InputStream inStream = this.getClass().getClassLoader().getResourceAsStream("cfg.properties");
      pro.load(inStream);        
 }  
 catch(FileNotFoundException e){  
     out.println(e);  
 }  
 catch(IOException e){out.println(e);} 

//通过key获取配置文件
 path = "";//pro.getProperty("image_src"); 
%>