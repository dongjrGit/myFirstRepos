package unit.test;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String method = "POST";
		String submitcontent = "{\"userCode\":\"1231111\",\"userPwd\":\"21232f297a57a5a743894a0e4a801fc3\",\"devicePin\":\"1\",\"deviceTime\":\"20150925010122\"}";
			OutputStream os = null;
			try {
				URL url = new URL("http://127.0.0.1:8080/pushwin/controller/endpoint/login");
				HttpURLConnection conn = (HttpURLConnection) url
						.openConnection();
				conn.setRequestMethod("POST");
				conn.setConnectTimeout(30 * 1000);
				conn.setReadTimeout(30 * 1000);
				conn.setDoOutput(true);
				conn.setDoInput(true);
				conn.setUseCaches(false);
				conn.setRequestProperty("Content-type", "application/json");
				conn.setRequestProperty("wms-url", "http://jerry-PC:9009/mobilebank/controller/app/sv");
				conn.setRequestProperty("method",method);
				conn.setRequestProperty("wms-cipher","3des;ffffffff-c472-80e6-ffff-ffffc47280e6");
				conn.setRequestProperty("wms-compress","gzip");
				os = conn.getOutputStream();			
				byte[] bs=submitcontent.getBytes("UTF-8");
				os.write(bs);
				if (conn.getResponseCode() != 200) {
					System.out.println(conn.getResponseCode());
				} else {
					System.out.println(conn.getResponseCode()); 
				}
			}  catch (Exception e) {
				e.printStackTrace();
			}
			finally {
				try {
					if (os!=null) {
						os.flush();
						os.close();
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}		
			}
	}

}
