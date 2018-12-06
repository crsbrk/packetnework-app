package pnet.httpConnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class PnetURLConnection {

	public PnetURLConnection() {
		// TODO 自动生成的构造函数存根
	}

	public static String sendGet(String url , String params){
		String result = "";
		BufferedReader in = null;
		String urlName = url ;
		//String urlName = url+ "?" + params;
		System.out.println("urlName-->" +urlName);
		try {
			URL realUrl = new URL(urlName);
			System.out.print("realUrl===>" + realUrl.toString());
			//打开和URL之间的连接
				URLConnection conn = realUrl.openConnection();
				
				//设置通用的请求属性
				conn.setRequestProperty("accept", "*/*");
				conn.setRequestProperty("connection", "Keep-Alive");
				conn.setRequestProperty("user-agent", "Mozilla/4.0(compatible;MSIE 6.0;Windows NT 5.1;SV1)");
				//建立实际的连接
				conn.connect();
				//获取所有影响头字段
				Map<String,List<String>> map = conn.getHeaderFields();
				//遍历所有的响应头字段
				for (String key : map.keySet()){
					System.out.println(key + "--->" + map.get(key));
				}
				
				
				//定义BufferedReader输入流来读取URL的响应
				in = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
				String line;
				while ((line = in.readLine()) != null) {
					result += line;
					
				}
				
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			System.out.println("发送GET请求出现异常！" + e);
			e.printStackTrace();
		}
		finally{
			try{
				if (in != null) {
					in.close();
				}
			}
			catch(IOException ex){
				ex.printStackTrace();
			}
		}
		
		return result;
	}

}
