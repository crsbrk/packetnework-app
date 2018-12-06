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
		// TODO �Զ����ɵĹ��캯�����
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
			//�򿪺�URL֮�������
				URLConnection conn = realUrl.openConnection();
				
				//����ͨ�õ���������
				conn.setRequestProperty("accept", "*/*");
				conn.setRequestProperty("connection", "Keep-Alive");
				conn.setRequestProperty("user-agent", "Mozilla/4.0(compatible;MSIE 6.0;Windows NT 5.1;SV1)");
				//����ʵ�ʵ�����
				conn.connect();
				//��ȡ����Ӱ��ͷ�ֶ�
				Map<String,List<String>> map = conn.getHeaderFields();
				//�������е���Ӧͷ�ֶ�
				for (String key : map.keySet()){
					System.out.println(key + "--->" + map.get(key));
				}
				
				
				//����BufferedReader����������ȡURL����Ӧ
				in = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
				String line;
				while ((line = in.readLine()) != null) {
					result += line;
					
				}
				
		} catch (Exception e) {
			// TODO �Զ����ɵ� catch ��
			System.out.println("����GET��������쳣��" + e);
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
