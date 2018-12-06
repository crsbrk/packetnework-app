package pnet.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pnet.vpdn.VpdnListsActivity;
import pnet.webview.MyWebViewActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class AccountsList extends ListActivity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.accounts_list);
		
		SimpleAdapter adapter = new SimpleAdapter(this,getData(),R.layout.show_item_with_pic,
				new String[]{"title","info","img"},
				new int[]{R.id.title,R.id.info,R.id.img});
		
		setListAdapter(adapter);
	}
	
	
	private List<Map<String, Object>> getData() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("title", "��ҵAPN��ͻ���Ӧ֧��ϵͳ");
		map.put("info", "��ѯ��ҵ�û�̨����Ϣ���鿴��·��·������Ϣ");
		map.put("img", R.drawable.vpdnroundcircle);
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("title", "����������ͼ");
		map.put("info", "��̬��ʾ��������Ԫ���");
		map.put("img", R.drawable.topology);
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("title", "�豸̨����Ϣ");
		map.put("info", "ͨ�����²��ϵ�������豸̨����Ϣ");
		map.put("img", R.drawable.flatbook);
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
			Intent intent2vpdn = new Intent();
			intent2vpdn.setClass(AccountsList.this, VpdnListsActivity.class);
			startActivity(intent2vpdn);

			break;
		case 1:
			Intent intent2topo = new Intent();
			intent2topo.setClass(AccountsList.this, MyWebViewActivity.class);  
			intent2topo.putExtra("URLFromQR", AppConstant.URL.TOPO_URL);//Intent��ѹ��һ���ֵ��,��ʾ����  
			AccountsList.this.startActivity(intent2topo);  
			break;
		case 2:
			Intent intent2facilityAcount = new Intent();
			intent2facilityAcount.setClass(AccountsList.this, MyWebViewActivity.class);  
			intent2facilityAcount.putExtra("URLFromQR", AppConstant.URL.ACCOUNT_URL);//Intent��ѹ��һ���ֵ��,��ʾ����  
			AccountsList.this.startActivity(intent2facilityAcount);  
			break;
		
		default:
			break;
		}

		
	}
	
	
}
