package pnet.warning.service;


import java.io.Serializable;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Timer;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import pnet.httpConnection.PnetURLConnection;
import pnet.main.R;
import pnet.model.WarningInfo;
import pnet.warning.WarningListsActivity;
import pnet.xmlParser.WarningListContentHandler;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.IBinder;
import android.util.Log;

public class WarningInfoService extends IntentService{

	private String warningUrl = null;
	private String warningXml = null;
	Timer timer = null;
	boolean firstCreate = true;
	 private List<WarningInfo> warningInfos = null;
	
	 
	public WarningInfoService() {
		// TODO 自动生成的构造函数存根
		super("yyyyyyyyyyy");   
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO 自动生成的方法存根
		return null;
	}

	
	@Override
	public void onCreate() {
		// TODO 自动生成的方法存根
		firstCreate = true;
		System.out.println("service--->onCreate");
		super.onCreate();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		
		// 获取告警XML并广播给warning activity，定时器每5分钟
		System.out.println("service--->onStartCommand");

//		if (firstCreate) {
//			warningUrl = intent.getStringExtra("warningURL");
//			System.out.println("warningURL in Service --->" + warningUrl);
//			getWarningXML();
//		}
		return super.onStartCommand(intent, flags, startId);
		
	}
	//===============================
	//获取XML数据
	//===============================
 	int   getWarningXML(){
		
	
				
				// TODO 自动生成的方法存根  通过广播获取到warningUrl  
 		System.out.println("warningUrl in warning service"+warningUrl);
			    String  warningXml = PnetURLConnection.sendGet(warningUrl,null); //返回告警的xml
			    System.out.println("String xml in WarningInfoService--->" + warningXml);
			    

		    	if (null == warningXml) {
		    		//发送无法连接远程服务器广播
//					Toast toast = Toast.makeText(WarningListsActivity.this, "无法连接远程服务器", Toast.LENGTH_SHORT);
//					toast.setGravity(Gravity.TOP | Gravity.CENTER, 0, 220);
//					toast.show();
		    		Intent intent = new Intent();
		    		intent.putExtra("connectServer", false);
		    		intent.setAction(WarningListsActivity.GETWARNINGXML_ACTION);
					sendBroadcast(intent);
					
				}
		    	else {
		    		System.out.println("warningXML in service"+warningXml);
		    		
		    		warningInfos = warningXMLparse(warningXml);
		    		 Collections.sort(warningInfos, new Comparator<WarningInfo>() {  //按告警级别排序

						@Override
						public int compare(WarningInfo lhs, WarningInfo rhs) {
						
							return lhs.getSeverity().compareTo(rhs.getSeverity());
						}
					}); 
		    		 
		    		//发送广播更新控件	
//					Message msg = new Message();
//					msg.what = 0x111;
//					handler.sendMessage(msg);

					int warnsNum =warningInfos.size();
					
					if (warnsNum != 0) {
					//	showNotification(warnsNum);
					}
					
		    		
		    		Intent intent = new Intent();
					intent.putExtra("warningInfos", (Serializable)warningInfos);
					intent.putExtra("connectServer", true);
					intent.putExtra("warningNumbers", warnsNum);
					intent.setAction(WarningListsActivity.GETWARNINGXML_ACTION);
					sendBroadcast(intent);
					

			}
				
		return 0;
			}

	@Override
	public void onDestroy() {
		// TODO 自动生成的方法存根
		//timer.cancel();
		super.onDestroy();
	}

	//删除通知   
	  private void clearNotification() {
	  // 启动后删除之前我们定义的通知  
		 NotificationManager notificationManager = (NotificationManager) this.getSystemService(NOTIFICATION_SERVICE);  
		notificationManager.cancel(0); 
		
		    }
	
	private void showNotification(int warnsNum) {
		// Intent intent = new Intent(WarningListsActivity.this,
		// WarningListsActivity.class);
		// PendingIntent pi

		// 创建一个NotificationManager的引用

		NotificationManager notificationManager = (NotificationManager)this.getSystemService(android.content.Context.NOTIFICATION_SERVICE);
		// 定义Notification的各种属性
		Notification notification = new Notification(R.drawable.warning,"告警", System.currentTimeMillis());

		// FLAG_AUTO_CANCEL 该通知能被状态栏的清除按钮给清除掉
		// FLAG_NO_CLEAR 该通知不能被状态栏的清除按钮给清除掉
		// FLAG_ONGOING_EVENT 通知放置在正在运行
		// FLAG_INSISTENT 是否一直进行，比如音乐一直播放，知道用户响应
		//notification.flags |= Notification.FLAG_ONGOING_EVENT; // 将此通知放到通知栏的"Ongoing"即"正在运行"组中

		// notification.flags |= Notification.FLAG_NO_CLEAR; //
		// 表明在点击了通知栏中的"清除通知"后，此通知不清除，经常与FLAG_ONGOING_EVENT一起使用
		notification.flags |= Notification.FLAG_SHOW_LIGHTS;

		// DEFAULT_ALL 使用所有默认值，比如声音，震动，闪屏等等
		// DEFAULT_LIGHTS 使用默认闪光提示
		// DEFAULT_SOUNDS 使用默认提示声音
		// DEFAULT_VIBRATE 使用默认手机震动，需加上<uses-permission
		// android:name="android.permission.VIBRATE" />权限

		notification.defaults = Notification.DEFAULT_LIGHTS
				| Notification.DEFAULT_VIBRATE | Notification.DEFAULT_SOUND;

		// 叠加效果常量
		// notification.defaults=Notification.DEFAULT_LIGHTS|Notification.DEFAULT_SOUND;
		notification.ledARGB = Color.BLUE;
		notification.ledOnMS = 3000; // 闪光时间，毫秒
		// 设置通知的事件消息
		CharSequence contentTitle = "告警信息"; // 通知栏标题
		CharSequence contentText = warnsNum + "条告警"; // 通知栏内容
		Intent notificationIntent = new Intent(getApplicationContext(),
				WarningListsActivity.class); // 点击该通知后要跳转的Activity

		PendingIntent contentItent = PendingIntent.getActivity(this, 0,
				notificationIntent, 0);

		notification.setLatestEventInfo(this, contentTitle, contentText,
				contentItent);

		// 把Notification传递给NotificationManager
		notificationManager.notify(0, notification);

	}
	
	
	private List<WarningInfo> warningXMLparse(String xmlStr) {
		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
		List<WarningInfo> infos = new ArrayList<WarningInfo>();
		try {
			 System.out.println("in parse......");
//			 Log.e("parse", "before  XMLReader xmlReader");
			XMLReader xmlReader = saxParserFactory.newSAXParser()
					.getXMLReader();
//			Log.e("parse", "before  warningListContentHandler");
			WarningListContentHandler warningListContentHandler = new WarningListContentHandler(
					infos);
			
			xmlReader.setContentHandler(warningListContentHandler);
			
			Log.e("parse", "before  xmlReader.parse"+ xmlStr);
			xmlReader.parse(new InputSource(new StringReader(xmlStr)));

			Log.e("parse", "after  xmlReader.parse");


		} catch (Exception e) {
			e.printStackTrace();
		}
		return infos;
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		// TODO 自动生成的方法存根
		if (firstCreate) {
			warningUrl = intent.getStringExtra("warningURL");
			System.out.println("warningURL in Service --->" + warningUrl);
			getWarningXML();
		}
		
	}


	
}






































