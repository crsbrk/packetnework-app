package pnet.webview;

import pnet.main.AppConstant;
import pnet.main.R;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MyWebViewActivity extends Activity {
	
	private 	WebView myWebView =null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		   //设置无标题  
        requestWindowFeature(Window.FEATURE_NO_TITLE);  
        //设置全屏  
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,   
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);  
        
		setContentView(R.layout.web_view);
		Log.e("in myweb", "Oncreate");
		
		String UrlFromQR =(String) this.getIntent().getCharSequenceExtra("URLFromQR");  
		if(UrlFromQR.equals(AppConstant.URL.FORECAST_URL) ){  //预测页面横向显示    || UrlFromQR.equals(AppConstant.URL.RAB_FAIL_URL)
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
	
			
		}else{
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		}
		System.out.println("URLFrom QR" + UrlFromQR);
		
	    myWebView = (WebView) findViewById(R.id.my_webview);
		myWebView.setWebViewClient(new WebViewClient());
		myWebView.getSettings().setLoadsImagesAutomatically(true);//自动加载图片
		myWebView.setInitialScale(25);
		myWebView.getSettings().setJavaScriptEnabled(true); 
		myWebView.getSettings().setSupportZoom(true);
//		myWebView.getSettings().setBuiltInZoomControls(true); 
		myWebView.getSettings().setUseWideViewPort(true);
//		myWebView.clearCache();
		myWebView.loadUrl(UrlFromQR);
		
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    // Check if the key event was the Back button and if there's history
	    if ((keyCode == KeyEvent.KEYCODE_BACK) && myWebView.canGoBack()) {
	        myWebView.goBack();
	        return true;
	    }
	    // If it wasn't the Back key or there's no web page history, bubble up to the default
	    // system behavior (probably exit the activity)
	    return super.onKeyDown(keyCode, event);
	}
}
