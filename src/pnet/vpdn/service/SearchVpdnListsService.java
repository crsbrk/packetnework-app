package pnet.vpdn.service;

import java.io.Serializable;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import pnet.httpConnection.PnetURLConnection;
import pnet.model.ApnInfo;
import pnet.vpdn.VpdnListsActivity;
import pnet.xmlParser.VpdnListContentHandler;
import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class SearchVpdnListsService extends IntentService {

	public SearchVpdnListsService(String name) {
		super(name);
		// TODO �Զ����ɵĹ��캯�����
	}
	
	public SearchVpdnListsService() {
		super("SearchVpdnListsService");
		// TODO �Զ����ɵĹ��캯�����
	}

	private String searchedApnXML;
	private String searchApnURL;
 	 private List<ApnInfo> apnInfos = null;
	
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO �Զ����ɵķ������
		return null;
	}

	@Override
	public void onCreate() {
		// TODO �Զ����ɵķ������
		super.onCreate();
		System.out.println("----->in SearchVpdnListService on Create");
	}

	/*	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO �Զ����ɵķ������

		searchApnURL = intent.getStringExtra("searchURL");
		getSearchedXML();
		return super.onStartCommand(intent, flags, startId);
	
	}*/

	private void getSearchedXML() {
		
	    searchedApnXML   = PnetURLConnection.sendGet(searchApnURL,null); //���ظ澯��xml
	    System.out.println("String getSearched xml--->" + searchedApnXML);
	    

    	if (null == searchedApnXML) {
    		//�����޷�����Զ�̷������㲥
//			Toast toast = Toast.makeText(WarningListsActivity.this, "�޷�����Զ�̷�����", Toast.LENGTH_SHORT);
//			toast.setGravity(Gravity.TOP | Gravity.CENTER, 0, 220);
//			toast.show();
    		Intent intent = new Intent();
    		intent.setAction(VpdnListsActivity.GET_NO_APNS_ACTION);
			sendBroadcast(intent);
			
		}
    	else {
    		apnInfos = ApnXMLparse(searchedApnXML);
    		
    		if (!apnInfos.isEmpty() ) {
    			System.out.println("apnInfos is not empty");
    			
    			Intent intent = new Intent();
    			intent.putExtra("searchedApnInfos", (Serializable)apnInfos);
    			intent.setAction(VpdnListsActivity.GET_SEARCHED_APNS_ACTION);
    			sendBroadcast(intent);
			}
    		else {
    			
    			System.out.println("apnInfos is empty");
    			Intent intent = new Intent();
        		intent.setAction(VpdnListsActivity.GET_NO_APNS_ACTION);
    			sendBroadcast(intent);
    			
    		}
    			
			

	}
		
	}

	private List<ApnInfo> ApnXMLparse(String xmlStr) {
		// TODO �Զ����ɵķ������
		
		
			SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
			List<ApnInfo> infos = new ArrayList<ApnInfo>();
			
			System.out.println("infos to string--->" + infos.toString());
			if (infos == null)
				System.out.println("infos is null");
			else
				System.out.println("infos is not null");
			
			
			try {
				System.out.println("in parse......");
				Log.e("parse", "before  XMLsearcheServiceReader xmlReader");
				XMLReader xmlReader = saxParserFactory.newSAXParser()
						.getXMLReader();
				Log.e("parse", "before  VpdnListSearcheContentHandler");
				VpdnListContentHandler vpdnListContentHandler = new VpdnListContentHandler(
						infos);

				xmlReader.setContentHandler(vpdnListContentHandler);

				Log.e("parse", "before  xmlReader.parse searchedVpdnservice" + xmlStr);
				xmlReader.parse(new InputSource(new StringReader(xmlStr)));

				Log.e("parse", "after  xmlReader.parse  searchedVpdnService");

				/*
				 * for (Iterator iterator = infos.iterator(); iterator.hasNext();) {
				 * ApnInfo apnInfo = (ApnInfo) iterator.next();
				 * System.out.println(mp3Info); System.out.println("right here");
				 * 
				 * }
				 */
			} catch (Exception e) {
				e.printStackTrace();
			}

			Log.e("infos in searched parse",infos.toString());
			return infos;

	}

	@Override
	protected void onHandleIntent(Intent intent) {
		// TODO �Զ����ɵķ������
		searchApnURL = intent.getStringExtra("searchURL");
		getSearchedXML();
		

	}
	

}
