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
			//����ɨ�������ڽ�������ʾ��
			if (resultCode == RESULT_OK) {
				Bundle bundle = data.getExtras();
				String scanResult = bundle.getString("result");
				//resultTextView.setText(scanResult);
				
				Toast.makeText(getApplicationContext(), scanResult.toString(),
					     Toast.LENGTH_SHORT).show();
				System.out.println(scanResult.toString() + "!!!!!!!!!!!!!!!!!ɨ�������ַ");
				
				if (scanResult.startsWith("http")) {
					//�����
					Log.e("judge", "url start with http --------------------");
					
					Intent intent=new Intent();  
					intent.setClass(MaintainanceList.this,MyWebViewActivity.class);  
					intent.putExtra("URLFromQR", scanResult.toString());//Intent��ѹ��һ���ֵ��  
					MaintainanceList.this.startActivity(intent);  
				} else
				{
					Log.e("judge", "url NOT start with http------------------ ");
					
					//��ʾ��������
					Toast.makeText(getApplicationContext(), scanResult.toString() + "����URL",
						     Toast.LENGTH_SHORT).show();
				}
			}

			super.onActivityResult(requestCode, resultCode, data);
		}



		private List<Map<String, Object>> getData() {
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("title", "������Ϣ");
			map.put("info", "ʱʱ�������ϣ���¼�������ʼ���֪���ܹ���״̬");
			map.put("img", R.drawable.msg2);
			list.add(map);

			map = new HashMap<String, Object>();
			map.put("title", "�澯��Ϣ");
			map.put("info", "��ѯ��ǰ�豸�ϴ��ڵĸ澯��Ϣ");
			map.put("img", R.drawable.caution);
			list.add(map);

			
			map = new HashMap<String, Object>();
			map.put("title", "ɨһɨ");
			map.put("info", "ɨһɨ�豸�����Ķ�ά�룬ʱʱ�˽��豸������Ϣ");
			map.put("img", R.drawable.qr_code);
			list.add(map);

			map = new HashMap<String, Object>();
			map.put("title", "�û���Ԥ��");
			map.put("info", "Ԥ�������µ��û�������");
			map.put("img", R.drawable.flowchart2);
			list.add(map);
			
			return list;
		}
		//���
		@Override
		protected void onListItemClick(ListView l, View v, int position, long id) {
			// TODO �Զ����ɵķ������
			super.onListItemClick(l, v, position, id);
			
			switch (position) {
			case 0:
				Intent intent2faultInfo = new Intent();  //����
				intent2faultInfo.setClass(MaintainanceList.this, MyWebViewActivity.class);  
				intent2faultInfo.putExtra("URLFromQR", AppConstant.URL.FAULT_URL);//Intent��ѹ��һ���ֵ��,��ʾ����  
				MaintainanceList.this.startActivity(intent2faultInfo);  

				break;
			case 1:
				Intent intent2warns = new Intent();//�澯
				intent2warns.setClass(MaintainanceList.this, WarningListsActivity.class);
				startActivity(intent2warns);
				
				//����WarningInfoService  
				Intent warningInfoServiceIntent = new Intent(); 
				warningInfoServiceIntent.putExtra("warningURL", AppConstant.URL.WARN_URL);//����澯��ַ
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
				Intent intent2Forecast = new Intent();//Ԥ��
				intent2Forecast.setClass(MaintainanceList.this, MyWebViewActivity.class);  
				intent2Forecast.putExtra("URLFromQR", AppConstant.URL.FORECAST_URL);//Intent��ѹ��һ���ֵ��,��ʾ����  
				MaintainanceList.this.startActivity(intent2Forecast);  
				break;

			
			default:
				break;
			}

			
		}
		
		
	}


