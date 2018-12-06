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
		map.put("title", "行业APN大客户响应支撑系统");
		map.put("info", "查询行业用户台帐信息，查看线路、路由器信息");
		map.put("img", R.drawable.vpdnroundcircle);
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("title", "分组网拓扑图");
		map.put("info", "动态显示分组网网元情况");
		map.put("img", R.drawable.topology);
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("title", "设备台帐信息");
		map.put("info", "通过上下层关系，体现设备台帐信息");
		map.put("img", R.drawable.flatbook);
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
			Intent intent2vpdn = new Intent();
			intent2vpdn.setClass(AccountsList.this, VpdnListsActivity.class);
			startActivity(intent2vpdn);

			break;
		case 1:
			Intent intent2topo = new Intent();
			intent2topo.setClass(AccountsList.this, MyWebViewActivity.class);  
			intent2topo.putExtra("URLFromQR", AppConstant.URL.TOPO_URL);//Intent中压入一组键值对,显示拓扑  
			AccountsList.this.startActivity(intent2topo);  
			break;
		case 2:
			Intent intent2facilityAcount = new Intent();
			intent2facilityAcount.setClass(AccountsList.this, MyWebViewActivity.class);  
			intent2facilityAcount.putExtra("URLFromQR", AppConstant.URL.ACCOUNT_URL);//Intent中压入一组键值对,显示拓扑  
			AccountsList.this.startActivity(intent2facilityAcount);  
			break;
		
		default:
			break;
		}

		
	}
	
	
}
