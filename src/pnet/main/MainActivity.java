package pnet.main;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import pnet.webview.MyWebViewActivity;

import com.baidu.android.common.logging.Log;
import com.baidu.android.pushservice.CustomPushNotificationBuilder;
import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;
//import com.baidu.push.PushDemoActivity;
import com.baidu.push.Utils;
//import com.demo.R;


import android.os.Bundle;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.LocalActivityManager;
import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	RelativeLayout mainLayout = null;
	TextView infoText = null;
//	Button initButton = null;
//	Button initWithApiKey = null;
//	Button displayRichMedia = null;
//	Button setTags = null;
//	Button delTags = null;
	public static int initialCnt = 0;
	private boolean isLogin = false;

	Context context = null;
	LocalActivityManager manager = null;
	ViewPager pager = null;
	TabHost tabHost = null;
	TextView t1,t2,t3;
	
	private int offset = 0;// 动画图片偏移量
	private int currIndex = 0;// 当前页卡编号
	private int bmpW;// 动画图片宽度
	private ImageView cursor;// 动画图片

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		 ActionBar actionBar = getActionBar(); //设置action bar
		  actionBar.setDisplayHomeAsUpEnabled(true);
		    
		context = MainActivity.this;
		manager = new LocalActivityManager(this , true);
		manager.dispatchCreate(savedInstanceState);

		InitImageView();
		initTextView();
		initPagerViewer();
		
		Resources resource = this.getResources();
		String pkgName = this.getPackageName();
		
		Log.e("in pnet  main", "on Create");
		
		// 以apikey的方式登录，一般放在主Activity的onCreate中
		PushManager.startWork(getApplicationContext(),
				PushConstants.LOGIN_TYPE_API_KEY, 
				Utils.getMetaValue(MainActivity.this, "api_key"));
		
		
		//设置自定义的通知样式，如果想使用系统默认的可以不加这段代码
        CustomPushNotificationBuilder cBuilder = new CustomPushNotificationBuilder(
        		resource.getIdentifier("notification_custom_builder", "layout", pkgName), 
        		resource.getIdentifier("notification_icon", "id", pkgName), 
        		resource.getIdentifier("notification_title", "id", pkgName), 
        		resource.getIdentifier("notification_text", "id", pkgName));
        cBuilder.setNotificationFlags(Notification.FLAG_AUTO_CANCEL);
        cBuilder.setNotificationDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE);
        cBuilder.setStatusbarIcon(this.getApplicationInfo().icon);
        cBuilder.setLayoutDrawable(resource.getIdentifier("simple_notification_icon", "drawable", pkgName));
		PushManager.setNotificationBuilder(this, 1, cBuilder);
		
		
	}
	/**
	 * 初始化标题
	 */
	private void initTextView() {
		t1 = (TextView) findViewById(R.id.text1);
		t2 = (TextView) findViewById(R.id.text2);
		t3 = (TextView) findViewById(R.id.text3);

		t1.setOnClickListener(new MyOnClickListener(0));
		t2.setOnClickListener(new MyOnClickListener(1));
		t3.setOnClickListener(new MyOnClickListener(2));
		
	}
	/**
	 * 初始化PageViewer
	 */
	private void initPagerViewer() {
		pager = (ViewPager) findViewById(R.id.viewpage);
		final ArrayList<View> list = new ArrayList<View>();
		Intent intent = new Intent(context, AccountsList.class);
		list.add(getView("AccountsList", intent));
		Intent intent2 = new Intent(context, MaintainanceList.class);
		list.add(getView("MaintainanceList", intent2));
		Intent intent3 = new Intent(context, IndictitorList.class);
		list.add(getView("IndictitorList", intent3));

		pager.setAdapter(new MyPagerAdapter(list));
		pager.setCurrentItem(0);
		pager.setOnPageChangeListener(new MyOnPageChangeListener());
	}
	/**
	 * 初始化动画
	 */
	private void InitImageView() {
		cursor = (ImageView) findViewById(R.id.cursor);
		bmpW = BitmapFactory.decodeResource(getResources(), R.drawable.roller)
		.getWidth();// 获取图片宽度
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		int screenW = dm.widthPixels;// 获取分辨率宽度
		offset = (screenW / 3 - bmpW) / 2;// 计算偏移量
		Matrix matrix = new Matrix();
		matrix.postTranslate(offset, 0);
		cursor.setImageMatrix(matrix);// 设置动画初始位置
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	/**
	 * 通过activity获取视图
	 * @param id
	 * @param intent
	 * @return
	 */
	private View getView(String id, Intent intent) {
		return manager.startActivity(id, intent).getDecorView();
	}

	/**
	 * Pager适配器
	 */
	public class MyPagerAdapter extends PagerAdapter{
		List<View> list =  new ArrayList<View>();
		public MyPagerAdapter(ArrayList<View> list) {
			this.list = list;
		}

		@Override
		public void destroyItem(ViewGroup container, int position,
				Object object) {
			ViewPager pViewPager = ((ViewPager) container);
			pViewPager.removeView(list.get(position));
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public int getCount() {
			return list.size();
		}
		@Override
		public Object instantiateItem(View arg0, int arg1) {
			ViewPager pViewPager = ((ViewPager) arg0);
			pViewPager.addView(list.get(arg1));
			return list.get(arg1);
		}

		@Override
		public void restoreState(Parcelable arg0, ClassLoader arg1) {

		}

		@Override
		public Parcelable saveState() {
			return null;
		}

		@Override
		public void startUpdate(View arg0) {
		}
	}
	/**
	 * 页卡切换监听
	 */
	public class MyOnPageChangeListener implements OnPageChangeListener {

		int one = offset * 2 + bmpW;// 页卡1 -> 页卡2 偏移量
		int two = one * 2;// 页卡1 -> 页卡3 偏移量

		@Override
		public void onPageSelected(int arg0) {
			Animation animation = null;
			switch (arg0) {
			case 0:
				if (currIndex == 1) {
					
					animation = new TranslateAnimation(one, 0, 0, 0);
					 t2.setTextColor(getResources().getColor(R.color.black));
				} else if (currIndex == 2) {
					animation = new TranslateAnimation(two, 0, 0, 0);
					 t3.setTextColor(getResources().getColor(R.color.black));
				}
				 t1.setTextColor(getResources().getColor(R.color.deepblue));
				 System.out.println(currIndex);
				break;
			case 1:
				if (currIndex == 0) {
					animation = new TranslateAnimation(offset, one, 0, 0);
					 t1.setTextColor(getResources().getColor(R.color.black));
				} else if (currIndex == 2) {
					animation = new TranslateAnimation(two, one, 0, 0);	
					 t3.setTextColor(getResources().getColor(R.color.black));
				}
				 t2.setTextColor(getResources().getColor(R.color.deepblue));
				 System.out.println(currIndex);
				break;
			case 2:
				if (currIndex == 0) {
					animation = new TranslateAnimation(offset, two, 0, 0);
					 t1.setTextColor(getResources().getColor(R.color.black));
				} else if (currIndex == 1) {
					animation = new TranslateAnimation(one, two, 0, 0);
					 t2.setTextColor(getResources().getColor(R.color.black));
				}
				 t3.setTextColor(getResources().getColor(R.color.deepblue));
				 System.out.println(currIndex);
				break;
			}
			currIndex = arg0;
			animation.setFillAfter(true);// True:图片停在动画结束位置
			animation.setDuration(300);
			cursor.startAnimation(animation);
		}

		@Override
		public void onPageScrollStateChanged(int arg0) {
			
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			
		}
	}
	/**
	 * 头标点击监听
	 */
	public class MyOnClickListener implements View.OnClickListener {
		private int index = 0;

		public MyOnClickListener(int i) {
			index = i;
		}

		@Override
		public void onClick(View v) {
			pager.setCurrentItem(index);
		}
	};
	
	@Override
	protected void  onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		// TODO 自动生成的方法存根
		Log.e("in onActivityResult", "----------------------before RESULT_OK judge");
		//处理扫描结果（在界面上显示）
		if (resultCode == RESULT_OK) {
			Bundle bundle = data.getExtras();
			String scanResult = bundle.getString("result");
			//resultTextView.setText(scanResult);
		//	QRTextView.setText(scanResult);
			
			Toast.makeText(getApplicationContext(), scanResult,
				     Toast.LENGTH_SHORT).show();
			System.out.println(scanResult + "!!!!!!!!!!!!!!!!!扫描出的网址");
			
			if (scanResult.startsWith("http")) {
				//浏览器
				Log.e("judge", "url start with http --------------------");
				
				Intent intent=new Intent();  
				intent.setClass(MainActivity.this,MyWebViewActivity.class);  
				intent.putExtra("URLFromQR", scanResult);//Intent中压入一组键值对  
				MainActivity.this.startActivity(intent);  
			} else
			{
				Log.e("judge", "url NOT start with http------------------ ");
				
				//显示文字内容
				Toast.makeText(getApplicationContext(), scanResult + "不是URL",
					     Toast.LENGTH_SHORT).show();
			}
		}
	
	}
	
	@Override
	public void onStart() {
		super.onStart();

		PushManager.activityStarted(this);
	}

	@Override
	public void onResume() {
		super.onResume();

		showChannelIds();
	}

	@Override
	protected void onNewIntent(Intent intent) {
		// 如果要统计Push引起的用户使用应用情况，请实现本方法，且加上这一个语句
		setIntent(intent);

		handleIntent(intent);
	}

	@Override
	public void onStop() {
		super.onStop();
		PushManager.activityStoped(this);
	}

	
	/**
	 * 处理Intent
	 * 
	 * @param intent
	 *            intent
	 */
	private void handleIntent(Intent intent) {
		String action = intent.getAction();

		if (Utils.ACTION_RESPONSE.equals(action)) {

			String method = intent.getStringExtra(Utils.RESPONSE_METHOD);

			if (PushConstants.METHOD_BIND.equals(method)) {
				String toastStr = "";
				int errorCode = intent.getIntExtra(Utils.RESPONSE_ERRCODE, 0);
				if (errorCode == 0) {
					String content = intent
							.getStringExtra(Utils.RESPONSE_CONTENT);
					String appid = "";
					String channelid = "";
					String userid = "";

					try {
						JSONObject jsonContent = new JSONObject(content);
						JSONObject params = jsonContent
								.getJSONObject("response_params");
						appid = params.getString("appid");
						channelid = params.getString("channel_id");
						userid = params.getString("user_id");
					} catch (JSONException e) {
						Log.e(Utils.TAG, "Parse bind json infos error: " + e);
					}

					SharedPreferences sp = PreferenceManager
							.getDefaultSharedPreferences(this);
					Editor editor = sp.edit();
					editor.putString("appid", appid);
					editor.putString("channel_id", channelid);
					editor.putString("user_id", userid);
					editor.commit();

				showChannelIds();

					toastStr = "Bind Success";
				} else {
					toastStr = "Bind Fail, Error Code: " + errorCode;
					if (errorCode == 30607) {
						Log.d("Bind Fail", "update channel token-----!");
					}
				}

				Toast.makeText(this, toastStr, Toast.LENGTH_LONG).show();
			}
		} else if (Utils.ACTION_LOGIN.equals(action)) {
			String accessToken = intent
					.getStringExtra(Utils.EXTRA_ACCESS_TOKEN);
			PushManager.startWork(getApplicationContext(),
					PushConstants.LOGIN_TYPE_ACCESS_TOKEN, accessToken);
			isLogin = true;
		//	initButton.setText("更换百度账号初始化Channel");
			Log.e("baidu"," 更换百度账号初始化Channel");
			
		} else if (Utils.ACTION_MESSAGE.equals(action)) {
			String message = intent.getStringExtra(Utils.EXTRA_MESSAGE);
			String summary = "Receive message from server:\n\t";
			Log.e(Utils.TAG, summary + message);
			JSONObject contentJson = null;
			String contentStr = message;
			try {
				contentJson = new JSONObject(message);
				contentStr = contentJson.toString(4);
			} catch (JSONException e) {
				Log.d(Utils.TAG, "Parse message json exception.");
			}
			summary += contentStr;
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage(summary);
			builder.setCancelable(true);
			Dialog dialog = builder.create();
			dialog.setCanceledOnTouchOutside(true);
			dialog.show();
		} else {
			Log.i(Utils.TAG, "Activity normally start!");
		}
	}
	private void showChannelIds() {
		String appId = null;
		String channelId = null;
		String clientId = null;

		SharedPreferences sp = PreferenceManager
				.getDefaultSharedPreferences(this);
		appId = sp.getString("appid", "");
		channelId = sp.getString("channel_id", "");
		clientId = sp.getString("user_id", "");
		
		Resources resource = this.getResources();
		String pkgName = this.getPackageName();
		infoText = (TextView) findViewById(resource.getIdentifier("text", "id", pkgName));

		String content = "\tApp ID: " + appId + "\n\tChannel ID: " + channelId
				+ "\n\tUser ID: " + clientId + "\n\t";
		if (infoText != null) {
			infoText.setText(content);
			infoText.invalidate();
		}
	}


}
