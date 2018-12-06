package pnet.warning;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import pnet.main.AppConstant;
import pnet.main.R;
import pnet.model.WarningInfo;
import pnet.warning.service.WarningInfoService;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class WarningListsActivity extends ListActivity {




	private static final int SENDGET= 0x111123;

   private String warningXml = null;
	private List<WarningInfo> warningInfos = null;
	private ProgressDialog progressdlg = null;	 
	private Handler handler = null;
	
	private boolean connectServerFlag = false;
	
	
	 WarningXMLReceiver wxmlR = null;
	 
	public static final String GETWARNINGXML_ACTION = "pnet.warning.action.GETWARNINGXML_ACTION";

	
	 
	public WarningListsActivity() {
		// TODO �Զ����ɵĹ��캯�����
		
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {

		if (warningInfos != null) {
			WarningInfo warnInfo = warningInfos.get(position);
			Intent intent = new Intent();

			intent.putExtra("warningInfo", warnInfo);// �����warning item��Ϣ
//			intent.putExtra("position", position);// �����λ��
//			intent.putExtra("mp3Infos", (Serializable) mp3Infos);// ����mp3�б�
			
			intent.setClass(this, ShowWarningInfoDetailActivity.class);
			 
			startActivity(intent);
		}

		super.onListItemClick(l, v, position, id);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO �Զ����ɵķ������
		
		wxmlR = new WarningXMLReceiver();
		IntentFilter filter = new IntentFilter();
		filter.addAction(GETWARNINGXML_ACTION);
		registerReceiver(wxmlR, filter);

System.out.println("in waringListsActivity  onCreate");
		
		handler = new Handler(){

			@Override
			public void handleMessage(Message msg) {
				// TODO �Զ����ɵķ������
				if (msg.what == SENDGET) {
					//����View
		//			java.lang.System.out.println("warningList Activity xml-->" + warningXml);
					
					
					if (!connectServerFlag) {
						Toast toast = Toast.makeText(WarningListsActivity.this, "�޷�����Զ�̷�����", Toast.LENGTH_SHORT);
						toast.setGravity(Gravity.TOP | Gravity.CENTER, 0, 220);
						toast.show();

					}
					else {
						System.out.println("~~before parse xml in handler msg~~~~~~~~");
						//warningInfos = warningXMLparse(warningXml);
						progressdlg.dismiss();
//						Log.e("after Parse xml", warningInfos.toString());
						
						if (!warningInfos.isEmpty()) {
							SimpleAdapter simpleAdapter = buildSimpleAdapter(warningInfos);
							// �����SimpleAdapter�������õ�ListActivity����
							setListAdapter(simpleAdapter);
						}
						else
						{
							Toast toast = Toast.makeText(WarningListsActivity.this, "ϵͳ��ȫ���У�û�и澯��Ϣ", Toast.LENGTH_SHORT);
							toast.setGravity(Gravity.TOP | Gravity.CENTER, 0, 220);
							toast.show();

						}
						
						//����notification
				
						
					}						
				}				
				super.handleMessage(msg);
			}		
		};
	
		updateWarningListView();
		
		super.onCreate(savedInstanceState);
	}
	
	

	
	
	


	
	private SimpleAdapter buildSimpleAdapter(List<WarningInfo> warningInfos) {
		// ����һ��List���󣬲�����SimpleAdapter�ı�׼��
		// ��mp3Infos���е�������ӵ�List����ȥ
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		for (Iterator iterator = warningInfos.iterator(); iterator.hasNext();) {
			WarningInfo wInfo = (WarningInfo) iterator.next();
			HashMap<String, Object> map = new HashMap<String, Object>();
			
			System.out.println("HappenTime �����Ӵ�֮ǰ----->" + wInfo.getHappenTime());
			
			String hpTmStr = wInfo.getHappenTime().trim();
			
			if(wInfo.getHappenTime().trim().length() > 5){
				 hpTmStr = wInfo.getHappenTime().trim().substring(5);
			}
			//String hpTmStr = wInfo.getHappenTime().trim().substring(5);
		
			System.out.println("HappenTime----->" + hpTmStr);

			map.put("netElementName", wInfo.getNeType().trim());
			map.put("happenTimeAndNetype", hpTmStr + "" );
			map.put("severity", wInfo.getSeverity().trim()+"���澯");
			map.put("alarmShortInfo", wInfo.getInfo());
			
//			System.out.println("map's happentime and nettpye---->"+map.get("happenTimeAndNetype"));
			list.add(map);
		}
		
		// ����һ��SimpleAdapter����   hanppenTimeAndNetype
		WarnSimpleAdapter warnSimpleAdapter = new WarnSimpleAdapter(this , list, R.layout.warning_info_item, new String[] { "happenTimeAndNetype", "severity","netElementName","alarmShortInfo" },
				new int[] { R.id.hanppenTimeAndNetype, R.id.severity , R.id.netElementName, R.id.alarmShortInfo} );
		
/*		SimpleAdapter simpleAdapter = new SimpleAdapter(this, list,
				R.layout.warning_info_item, new String[] { "happenTimeAndNetype", "severity","netElementName","alarmShortInfo" },
				new int[] { R.id.hanppenTimeAndNetype, R.id.severity , R.id.netElementName, R.id.alarmShortInfo});
*/
		return warnSimpleAdapter;
	}

	
@Override
	public ListView getListView() {
		// TODO �Զ����ɵķ������
		return super.getListView();
	}

private int updateWarningListView() {

	System.out.println("in updateWarningListView");
	progressdlg = new ProgressDialog(WarningListsActivity.this);
	progressdlg.setMessage("Ŭ��������...");
	progressdlg.show();
	// ������վ��xml�ļ�
	//Ҫ�����
	Intent warningInfoServiceIntent = new Intent();
	warningInfoServiceIntent.putExtra("warningURL", AppConstant.URL.WARN_URL);//����澯��ַ
	warningInfoServiceIntent.setClass(WarningListsActivity.this, WarningInfoService.class);
	startService(warningInfoServiceIntent);
	
	 
	return 0;

}

class WarningXMLReceiver extends BroadcastReceiver{

	@Override
	public void onReceive(Context arg0, Intent arg1) {
		// TODO �Զ����ɵķ������
		warningInfos =(List<WarningInfo>)arg1.getSerializableExtra("warningInfos");
		connectServerFlag = arg1.getBooleanExtra("connectServer", false);
		Message msg = new Message();
		msg.what = SENDGET;
		handler.sendMessage(msg);
	}
	
}




@Override
protected void onDestroy() {
	// TODO Auto-generated method stub
	unregisterReceiver(wxmlR);

	super.onDestroy();
}


}
