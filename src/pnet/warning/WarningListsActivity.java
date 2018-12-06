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
		// TODO 自动生成的构造函数存根
		
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {

		if (warningInfos != null) {
			WarningInfo warnInfo = warningInfos.get(position);
			Intent intent = new Intent();

			intent.putExtra("warningInfo", warnInfo);// 点击的warning item信息
//			intent.putExtra("position", position);// 点击的位置
//			intent.putExtra("mp3Infos", (Serializable) mp3Infos);// 整个mp3列表
			
			intent.setClass(this, ShowWarningInfoDetailActivity.class);
			 
			startActivity(intent);
		}

		super.onListItemClick(l, v, position, id);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		
		wxmlR = new WarningXMLReceiver();
		IntentFilter filter = new IntentFilter();
		filter.addAction(GETWARNINGXML_ACTION);
		registerReceiver(wxmlR, filter);

System.out.println("in waringListsActivity  onCreate");
		
		handler = new Handler(){

			@Override
			public void handleMessage(Message msg) {
				// TODO 自动生成的方法存根
				if (msg.what == SENDGET) {
					//更新View
		//			java.lang.System.out.println("warningList Activity xml-->" + warningXml);
					
					
					if (!connectServerFlag) {
						Toast toast = Toast.makeText(WarningListsActivity.this, "无法连接远程服务器", Toast.LENGTH_SHORT);
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
							// 将这个SimpleAdapter对象设置到ListActivity当中
							setListAdapter(simpleAdapter);
						}
						else
						{
							Toast toast = Toast.makeText(WarningListsActivity.this, "系统安全运行，没有告警信息", Toast.LENGTH_SHORT);
							toast.setGravity(Gravity.TOP | Gravity.CENTER, 0, 220);
							toast.show();

						}
						
						//设置notification
				
						
					}						
				}				
				super.handleMessage(msg);
			}		
		};
	
		updateWarningListView();
		
		super.onCreate(savedInstanceState);
	}
	
	

	
	
	


	
	private SimpleAdapter buildSimpleAdapter(List<WarningInfo> warningInfos) {
		// 生成一个List对象，并按照SimpleAdapter的标准，
		// 将mp3Infos当中的数据添加到List当中去
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		for (Iterator iterator = warningInfos.iterator(); iterator.hasNext();) {
			WarningInfo wInfo = (WarningInfo) iterator.next();
			HashMap<String, Object> map = new HashMap<String, Object>();
			
			System.out.println("HappenTime 处理子串之前----->" + wInfo.getHappenTime());
			
			String hpTmStr = wInfo.getHappenTime().trim();
			
			if(wInfo.getHappenTime().trim().length() > 5){
				 hpTmStr = wInfo.getHappenTime().trim().substring(5);
			}
			//String hpTmStr = wInfo.getHappenTime().trim().substring(5);
		
			System.out.println("HappenTime----->" + hpTmStr);

			map.put("netElementName", wInfo.getNeType().trim());
			map.put("happenTimeAndNetype", hpTmStr + "" );
			map.put("severity", wInfo.getSeverity().trim()+"级告警");
			map.put("alarmShortInfo", wInfo.getInfo());
			
//			System.out.println("map's happentime and nettpye---->"+map.get("happenTimeAndNetype"));
			list.add(map);
		}
		
		// 创建一个SimpleAdapter对象   hanppenTimeAndNetype
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
		// TODO 自动生成的方法存根
		return super.getListView();
	}

private int updateWarningListView() {

	System.out.println("in updateWarningListView");
	progressdlg = new ProgressDialog(WarningListsActivity.this);
	progressdlg.setMessage("努力加载中...");
	progressdlg.show();
	// 返回网站的xml文件
	//要求更新
	Intent warningInfoServiceIntent = new Intent();
	warningInfoServiceIntent.putExtra("warningURL", AppConstant.URL.WARN_URL);//传入告警网址
	warningInfoServiceIntent.setClass(WarningListsActivity.this, WarningInfoService.class);
	startService(warningInfoServiceIntent);
	
	 
	return 0;

}

class WarningXMLReceiver extends BroadcastReceiver{

	@Override
	public void onReceive(Context arg0, Intent arg1) {
		// TODO 自动生成的方法存根
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
