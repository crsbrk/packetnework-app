package pnet.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pnet.warning.WarningListsActivity;
import pnet.warning.service.WarningInfoService;
import pnet.webview.MyWebViewActivity;
import pnet.zxing.activity.CaptureActivity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class MaintainanceList extends ListActivity{
	
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			//setContentView(R.layout.accounts_list);
			
			SimpleAdapter adapter = new SimpleAdapter(this,getData(),R.layout.show_item_with_pic,
					new String[]{"title","info","img"},
					new int[]{R.id.title,R.id.info,R.id.img});
			
			setListAdapter(adapter);
		}
		
		
		
		@Override
		protected void onActivityResult(int requestCode, int resultCode,
				Intent data) {
		
			Log.e("in onActivityResult", "----------------------before RESULT_OK judge");
			//处理扫描结果（在界面上显示）
			if (resultCode == RESULT_OK) {
				Bundle bundle = data.getExtras();
				String scanResult = bundle.getString("result");
				//resultTextView.setText(scanResult);
				
				Toast.makeText(getApplicationContext(), scanResult.toString(),
					     Toast.LENGTH_SHORT).show();
				System.out.println(scanResult.toString() + "!!!!!!!!!!!!!!!!!扫描出的网址");
				
				if (scanResult.startsWith("http")) {
					//浏览器
					Log.e("judge", "url start with http --------------------");
					
					Intent intent=new Intent();  
					intent.setClass(MaintainanceList.this,MyWebViewActivity.class);  
					intent.putExtra("URLFromQR", scanResult.toString());//Intent中压入一组键值对  
					MaintainanceList.this.startActivity(intent);  
				} else
				{
					Log.e("judge", "url NOT start with http------------------ ");
					
					//显示文字内容
					Toast.makeText(getApplicationContext(), scanResult.toString() + "不是URL",
						     Toast.LENGTH_SHORT).show();
				}
			}

			super.onActivityResult(requestCode, resultCode, data);
		}



		private List<Map<String, Object>> getData() {
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("title", "故障信息");
			map.put("info", "时时跟进故障，记录并发送邮件告知网管故障状态");
			map.put("img", R.drawable.msg2);
			list.add(map);

			map = new HashMap<String, Object>();
			map.put("title", "告警信息");
			map.put("info", "查询当前设备上存在的告警信息");
			map.put("img", R.drawable.caution);
			list.add(map);

			
			map = new HashMap<String, Object>();
			map.put("title", "扫一扫");
			map.put("info", "扫一扫设备上贴的二维码，时时了解设备运行信息");
			map.put("img", R.drawable.qr_code);
			list.add(map);

			map = new HashMap<String, Object>();
			map.put("title", "用户数预测");
			map.put("info", "预测三个月的用户附着数");
			map.put("img", R.drawable.flowchart2);
			list.add(map);
			
			return list;
		}
		//点击
		@Override
		protected void onListItemClick(ListView l, View v, int position, long id) {
			// TODO 自动生成的方法存根
			super.onListItemClick(l, v, position, id);
			
			switch (position) {
			case 0:
				Intent intent2faultInfo = new Intent();  //故障
				intent2faultInfo.setClass(MaintainanceList.this, MyWebViewActivity.class);  
				intent2faultInfo.putExtra("URLFromQR", AppConstant.URL.FAULT_URL);//Intent中压入一组键值对,显示拓扑  
				MaintainanceList.this.startActivity(intent2faultInfo);  

				break;
			case 1:
				Intent intent2warns = new Intent();//告警
				intent2warns.setClass(MaintainanceList.this, WarningListsActivity.class);
				startActivity(intent2warns);
				
				//启动WarningInfoService  
				Intent warningInfoServiceIntent = new Intent(); 
				warningInfoServiceIntent.putExtra("warningURL", AppConstant.URL.WARN_URL);//传入告警网址
				System.out.println("warningInfoServiceIntent---->" + warningInfoServiceIntent.getStringExtra("warningURL").toString());
				warningInfoServiceIntent.setClass(MaintainanceList.this, WarningInfoService.class);
				startService(warningInfoServiceIntent);
				break;
			case 2:
				Intent qrCodeIntent = new Intent();
				qrCodeIntent.setClass(MaintainanceList.this, CaptureActivity.class);
				startActivityForResult(qrCodeIntent, 0);
				//startActivity(qrCodeIntent);
				break;
			case 3:
				Intent intent2Forecast = new Intent();//预测
				intent2Forecast.setClass(MaintainanceList.this, MyWebViewActivity.class);  
				intent2Forecast.putExtra("URLFromQR", AppConstant.URL.FORECAST_URL);//Intent中压入一组键值对,显示拓扑  
				MaintainanceList.this.startActivity(intent2Forecast);  
				break;

			
			default:
				break;
			}

			
		}
		
		
	}


