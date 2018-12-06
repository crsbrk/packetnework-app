package pnet.vpdn;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import pnet.download.HttpDownloader;
import pnet.main.AppConstant;
import pnet.main.R;
import pnet.model.ApnInfo;
import pnet.vpdn.service.SearchVpdnListsService;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class VpdnListsActivity extends ListActivity {

	private static final int UPDATE = 1;
	private static final int ABOUT = 2;
	private List<ApnInfo> apnInfos = null;
	private Handler handler = null;
	private String xml = null;
	private ProgressDialog prsDlg = null;
	private Button searchButton = null;
	private String searchXML = null;
	static int SEARCH = 222222;
	static int CANT_REACH_SERVER = 12345;
	public static final String GET_NO_APNS_ACTION = "pnet.vpdnListsActivity..action.GET_NO_APNS_ACTION";
	public static final String GET_SEARCHED_APNS_ACTION = "pnet.vpdnListsActivity..action.GET_SEARCHED_APNS_ACTION";
	SearchedApnsReceiver searchedApnsRcvr = null;
	GetNoApnsReceiver getNoApnRcvr = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO �Զ����ɵķ������
		super.onCreate(savedInstanceState);
		setContentView(R.layout.vpdn_app);
		searchButton = (Button) findViewById(R.id.searchButton);

		searchButton.setOnClickListener(new MyClickListener());

		searchedApnsRcvr = new SearchedApnsReceiver();
		IntentFilter filter = new IntentFilter();
		filter.addAction(GET_SEARCHED_APNS_ACTION);
		registerReceiver(searchedApnsRcvr, filter);

		getNoApnRcvr = new GetNoApnsReceiver();
		IntentFilter filterNo = new IntentFilter();
		filterNo.addAction(GET_NO_APNS_ACTION);
		registerReceiver(getNoApnRcvr, filterNo);

		// updateListView();
		// new Thread(downloadRun).start();
		handler = new Handler() {

			@Override
			public void handleMessage(Message msg) {
				// TODO �Զ����ɵķ������
				{

					if (msg.what == CANT_REACH_SERVER) {
						// if (null == xml ) {
						// Toast toast = Toast.makeText(VpdnListsActivity.this,
						// "�޷�����Զ�̷�����", Toast.LENGTH_SHORT);
						// toast.setGravity(Gravity.TOP | Gravity.CENTER, 0,
						// 220);
						// toast.show();
						//
						// }else {
						// java.lang.System.out.println("vpdn xml-->" + xml);
						// // System.out.println("xml--->"+xml);
						// // ��xml�ļ����н��������������Ľ�����õ�Mp3Info������
						// // �����ЩMp3Info������õ�List����
						// System.out.println("~~before parse vpdn xml~~~~~~~~");
						//
						// Log.e("after Parse xml", apnInfos.toString());
						// prsDlg.dismiss();
						// SimpleAdapter simpleAdapter =
						// buildSimpleAdapter(apnInfos);
						// // �����SimpleAdapter�������õ�ListActivity����
						// setListAdapter(simpleAdapter);
						// }
						prsDlg.dismiss();

						Toast toast = Toast.makeText(VpdnListsActivity.this,
								"û�в�ѯ�����ݣ������ԣ�", Toast.LENGTH_SHORT);
						toast.setGravity(Gravity.TOP | Gravity.CENTER, 0, 220);
						toast.show();

					} else if (msg.what == SEARCH) {

						prsDlg.dismiss();
						System.out.println("----> in handlerMessage SEARCH");
						SimpleAdapter simpleAdapter = buildSimpleAdapter(apnInfos);
						// �����SimpleAdapter�������õ�ListActivity����
						setListAdapter(simpleAdapter);

					}
				}
				super.handleMessage(msg);
			}

		};

		// updateListView();
	}

	class MyClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {

			// prsDlg = new ProgressDialog(VpdnListsActivity.this);
			// prsDlg.show();
			// prsDlg.setMessage("Ŭ��������...");
			prsDlg = ProgressDialog.show(VpdnListsActivity.this, "",
					"�����У����Ժ󡭡�");

			EditText etext = (EditText) findViewById(R.id.apn_text);
			String apnText = etext.getText().toString().trim();

			if (apnText == null) {
				apnText = "";
			}

			System.out.println("apnText----->" + apnText);
			/*
			 * String searchURL =
			 * "http://192.168.53.40:8080/apnusers/searchbyxml.xml?utf8=%E2%9C%93&keyword="
			 * + apnText + "&commit=Search";
			 */

			String searchURL = AppConstant.URL.VPDN_URL;
			// searchURL =
			// "http://192.168.53.40:8080/apnusers/searchbyxml.xml?utf8=%E2%9C%93&keyword="
			// + URLEncoder.encode(apnText,"UTF-8") + "&commit=Search";

			try {
				searchURL = "http://192.168.50.23/cgi-bin/getapnxml.pl?keyword="
						+ URLEncoder.encode(apnText, "UTF-8");
			} catch (Exception e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}

			// searchXML = PnetURLConnection.sendGet(searchURL, null);
			// Message msg = new Message();
			// msg.what = SEARCH;
			// handler.sendMessage(msg);
			Intent searchVpdnServiceIntent = new Intent();
			searchVpdnServiceIntent.putExtra("searchURL", searchURL); // ������ַ
			searchVpdnServiceIntent.setClass(VpdnListsActivity.this,
					SearchVpdnListsService.class);
			startService(searchVpdnServiceIntent);

			System.out.println("have started startVpdnServiceIntent  url---> "
					+ searchURL);
		}

	}

	// @Override
	// protected void onListItemClick(ListView l, View v, int position, long id)
	// {
	// // TODO �Զ����ɵķ������
	//
	// super.onListItemClick(l, v, position, id);
	// }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO �Զ����ɵķ������
		menu.add(0, UPDATE, 1, R.string.apninfo_update);
		menu.add(0, ABOUT, 2, R.string.apninfo_about);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO �Զ����ɵķ������

		if (UPDATE == item.getItemId()) {
			// �û�����˸����б�ť
			updateListView();
			// new Thread(downloadRun).start();

		} else if (ABOUT == item.getItemId()) {
			// �û�����˹��ڰ�ť
		}

		return super.onOptionsItemSelected(item);
	}

	Runnable runnableUi = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub

		}
	};

	private int updateListView() {

		// ���ذ�������apn������Ϣ��xml�ļ�

		prsDlg = new ProgressDialog(VpdnListsActivity.this);
		prsDlg.show();
		prsDlg.setMessage("Ŭ��������...");
		new Thread() {
			@Override
			public void run() {

				xml = downloadXML("http://192.168.50.23:80/fzw/vpdntest.xml"); // 10.0.2.2ģ��������10.0.2.2������ĵ��Ա���
				Message msg = new Message();
				msg.what = 1;
				handler.sendMessage(msg);
			}
		}.start();

		return 0;

	}

	private SimpleAdapter buildSimpleAdapter(List<ApnInfo> apnInfos) {
		// ����һ��List���󣬲�����SimpleAdapter�ı�׼��
		// ��mp3Infos���е�������ӵ�List����ȥ
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		for (Iterator iterator = apnInfos.iterator(); iterator.hasNext();) {
			ApnInfo apnInfo = (ApnInfo) iterator.next();
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("company_name", apnInfo.getUsername());
			map.put("apn_name", apnInfo.getApn());
			list.add(map);
		}
		// ����һ��SimpleAdapter����
		SimpleAdapter simpleAdapter = new SimpleAdapter(this, list,
				R.layout.apn_info_item, new String[] { "company_name",
						"apn_name" }, new int[] { R.id.company_name,
						R.id.apn_name });

		return simpleAdapter;
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {

		if (apnInfos != null) {
			ApnInfo apnInfo = apnInfos.get(position);
			Intent intent = new Intent();

			intent.putExtra("oneApn", apnInfo);// �����mp3��Ϣ
			intent.setClass(this, ShowOneSearchedApnDetail.class);
			startActivity(intent);
		}

		super.onListItemClick(l, v, position, id);
	}

	private String downloadXML(String urlStr) {

		HttpDownloader httpDownloader = new HttpDownloader();
		Log.e("download", "in downloadXML");
		String result = httpDownloader.download(urlStr);// "http://127.0.0.1:8080/mp3/resources.xml"

		return result;
	}

	// private List<ApnInfo> parse(String xmlStr) {
	// SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
	// List<ApnInfo> infos = new ArrayList<ApnInfo>();
	// try {
	// System.out.println("in parse......");
	// Log.e("parse", "before  XMLReader xmlReader");
	// XMLReader xmlReader = saxParserFactory.newSAXParser()
	// .getXMLReader();
	// Log.e("parse", "before  VpdnListContentHandler");
	// VpdnListContentHandler vpdnListContentHandler = new
	// VpdnListContentHandler(
	// infos);
	//
	// xmlReader.setContentHandler(vpdnListContentHandler);
	//
	// Log.e("parse", "before  xmlReader.parse" + xmlStr);
	// xmlReader.parse(new InputSource(new StringReader(xmlStr)));
	//
	// Log.e("parse", "after  xmlReader.parse");
	//
	// /*
	// * for (Iterator iterator = infos.iterator(); iterator.hasNext();) {
	// * ApnInfo apnInfo = (ApnInfo) iterator.next();
	// * System.out.println(mp3Info); System.out.println("right here");
	// *
	// * }
	// */
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	//
	// // Log.e("infos in private parse",infos.toString());
	// return infos;
	// }

	class SearchedApnsReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context arg0, Intent arg1) {
			// TODO �Զ����ɵķ������
			apnInfos = (List<ApnInfo>) arg1
					.getSerializableExtra("searchedApnInfos");

			Message msg = new Message();
			msg.what = SEARCH;
			handler.sendMessage(msg);
		}

	}

	class GetNoApnsReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context arg0, Intent arg1) {
			// TODO �Զ����ɵķ������
			System.out.println("get no apn receiver broadcast ");
			Message msg = new Message();
			msg.what = CANT_REACH_SERVER;
			handler.sendMessage(msg);

		}
	}

	@Override
	protected void onDestroy() {

		unregisterReceiver(searchedApnsRcvr);
		unregisterReceiver(getNoApnRcvr);

		super.onDestroy();
	}

}
