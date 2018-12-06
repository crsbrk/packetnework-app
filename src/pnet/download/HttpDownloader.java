package pnet.download;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.util.Log;


public class HttpDownloader {

	private URL url = null;
	StringBuffer sb = new StringBuffer(8*1024);
	String line = null;
	BufferedReader buffer = null;
	HttpURLConnection urlConn = null; 


	
	
	public String download(String urlStr){
		try{
			
			//创建url对象
			url = new URL(urlStr);
			Log.e("in HttpDownloader URL-------->", ""+url);
			//创建一个Http连接
			urlConn = (HttpURLConnection) url
									.openConnection();
			
			Log.e("in HttpDownloader urlConn-------->", ""+urlConn.toString());
			
			urlConn.setConnectTimeout(1000);
			//使用IO读取数据
			buffer = new BufferedReader(new InputStreamReader(urlConn
					.getInputStream(),"UTF-8"));
			while ((line = buffer.readLine()) != null){
				sb.append(line);
			}
		} catch(Exception e){
			e.printStackTrace();
			Log.e("in URL", "URL Exception");
			return null;
		} finally{
			try {
				buffer.close();
				urlConn.disconnect();

			} catch (Exception e){
				e.printStackTrace();
			}
		}
		
		return sb.toString();
	}
/*	
	//该函数返回整型  -1；表示下载出错     0:代表下载文件成功   1：代表文件已存在
	public int downFile(String urlStr, String path, String fileName){
		InputStream inputStream = null;
		try	{
			FileUtilis fileUtils = new FileUtilis();
			if (fileUtils.isFileExist(fileName, path)){
				return 1;
			}
			else{
				inputStream = getInputStreamFromUrl(urlStr);
				File resultFile = fileUtils.write2SDFromInput(path, fileName, inputStream);
				if (null == resultFile){
					System.out.println("resultFile == null");
					return  -1;
				}
			}
			
		}catch	(Exception e){
			e.printStackTrace();
			return -1;
			
		} finally{
			try {
				inputStream.close();
			}catch (Exception e){
				e.printStackTrace();
			}
			
		}
		
		return 0;
		
	}
*/	
	//根据URL得到输入流
	private InputStream getInputStreamFromUrl(String urlStr) throws IOException {
		// TODO Auto-generated method stub
		url = new URL(urlStr);
		HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
		InputStream inputStream = urlConn.getInputStream();
		return inputStream;
	}

}
