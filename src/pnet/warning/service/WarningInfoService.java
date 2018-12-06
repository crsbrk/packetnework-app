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
		// TODO �Զ����ɵĹ��캯�����
		super("yyyyyyyyyyy");   
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO �Զ����ɵķ������
		return null;
	}

	
	@Override
	public void onCreate() {
		// TODO �Զ����ɵķ������
		firstCreate = true;
		System.out.println("service--->onCreate");
		super.onCreate();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		
		// ��ȡ�澯XML���㲥��warning activity����ʱ��ÿ5����
		System.out.println("service--->onStartCommand");

//		if (firstCreate) {
//			warningUrl = intent.getStringExtra("warningURL");
//			System.out.println("warningURL in Service --->" + warningUrl);
//			getWarningXML();
//		}
		return super.onStartCommand(intent, flags, startId);
		
	}
	//===============================
	//��ȡXML����
	//===============================
 	int   getWarningXML(){
		
	
				
				// TODO �Զ����ɵķ������  ͨ���㲥��ȡ��warningUrl  
 		System.out.println("warningUrl in warning service"+warningUrl);
			    String  warningXml = PnetURLConnection.sendGet(warningUrl,null); //���ظ澯��xml
			    System.out.println("String xml in WarningInfoService--->" + warningXml);
			    

		    	if (null == warningXml) {
		    		//�����޷�����Զ�̷������㲥
//					Toast toast = Toast.makeText(WarningListsActivity.this, "�޷�����Զ�̷�����", Toast.LENGTH_SHORT);
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
		    		 Collections.sort(warningInfos, new Comparator<WarningInfo>() {  //���澯��������

						@Override
						public int compare(WarningInfo lhs, WarningInfo rhs) {
						
							return lhs.getSeverity().compareTo(rhs.getSeverity());
						}
					}); 
		    		 
		    		//���͹㲥���¿ؼ�	
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
		// TODO �Զ����ɵķ������
		//timer.cancel();
		super.onDestroy();
	}

	//ɾ��֪ͨ   
	  private void clearNotification() {
	  // ������ɾ��֮ǰ���Ƕ����֪ͨ  
		 NotificationManager notificationManager = (NotificationManager) this.getSystemService(NOTIFICATION_SERVICE);  
		notificationManager.cancel(0); 
		
		    }
	
	private void showNotification(int warnsNum) {
		// Intent intent = new Intent(WarningListsActivity.this,
		// WarningListsActivity.class);
		// PendingIntent pi

		// ����һ��NotificationManager������

		NotificationManager notificationManager = (NotificationManager)this.getSystemService(android.content.Context.NOTIFICATION_SERVICE);
		// ����Notification�ĸ�������
		Notification notification = new Notification(R.drawable.warning,"�澯", System.currentTimeMillis());

		// FLAG_AUTO_CANCEL ��֪ͨ�ܱ�״̬���������ť�������
		// FLAG_NO_CLEAR ��֪ͨ���ܱ�״̬���������ť�������
		// FLAG_ONGOING_EVENT ֪ͨ��������������
		// FLAG_INSISTENT �Ƿ�һֱ���У���������һֱ���ţ�֪���û���Ӧ
		//notification.flags |= Notification.FLAG_ONGOING_EVENT; // ����֪ͨ�ŵ�֪ͨ����"Ongoing"��"��������"����

		// notification.flags |= Notification.FLAG_NO_CLEAR; //
		// �����ڵ����֪ͨ���е�"���֪ͨ"�󣬴�֪ͨ�������������FLAG_ONGOING_EVENTһ��ʹ��
		notification.flags |= Notification.FLAG_SHOW_LIGHTS;

		// DEFAULT_ALL ʹ������Ĭ��ֵ�������������𶯣������ȵ�
		// DEFAULT_LIGHTS ʹ��Ĭ��������ʾ
		// DEFAULT_SOUNDS ʹ��Ĭ����ʾ����
		// DEFAULT_VIBRATE ʹ��Ĭ���ֻ��𶯣������<uses-permission
		// android:name="android.permission.VIBRATE" />Ȩ��

		notification.defaults = Notification.DEFAULT_LIGHTS
				| Notification.DEFAULT_VIBRATE | Notification.DEFAULT_SOUND;

		// ����Ч������
		// notification.defaults=Notification.DEFAULT_LIGHTS|Notification.DEFAULT_SOUND;
		notification.ledARGB = Color.BLUE;
		notification.ledOnMS = 3000; // ����ʱ�䣬����
		// ����֪ͨ���¼���Ϣ
		CharSequence contentTitle = "�澯��Ϣ"; // ֪ͨ������
		CharSequence contentText = warnsNum + "���澯"; // ֪ͨ������
		Intent notificationIntent = new Intent(getApplicationContext(),
				WarningListsActivity.class); // �����֪ͨ��Ҫ��ת��Activity

		PendingIntent contentItent = PendingIntent.getActivity(this, 0,
				notificationIntent, 0);

		notification.setLatestEventInfo(this, contentTitle, contentText,
				contentItent);

		// ��Notification���ݸ�NotificationManager
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
		// TODO �Զ����ɵķ������
		if (firstCreate) {
			warningUrl = intent.getStringExtra("warningURL");
			System.out.println("warningURL in Service --->" + warningUrl);
			getWarningXML();
		}
		
	}


	
}






































