package pnet.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends Activity{
	
	 private final int SPLASH_DISPLAY_LENGHT = 3000; //—”≥Ÿ»˝√Î

	 @Override
	 protected void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);
		 setContentView(R.layout.splash);
		 //int[] backgroundpics = new int[] {R.drawable.start0 ,R.drawable.start1,R.drawable.start2,R.drawable.start3,R.drawable.start4,R.drawable.start5};
         //int whichPic = new Random().nextInt(6);
		 getWindow().setBackgroundDrawableResource(R.drawable.start);
		         new Handler().postDelayed(new Runnable(){
		 
		  
		 
		          @Override
		 
		          public void run() {
		 
		              Intent mainIntent = new Intent(SplashActivity.this,MainActivity.class);
		 
		              SplashActivity.this.startActivity(mainIntent);
		 
		              SplashActivity.this.finish();
		 
		          }
		 
		              
	
		         }, SPLASH_DISPLAY_LENGHT);

}
	 
	 
}




