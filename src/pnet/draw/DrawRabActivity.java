/**
 * chris
 */
package pnet.draw;

import pnet.httpConnection.PnetURLConnection;
import pnet.vpdn.VpdnListsActivity;
import android.R;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * @author chris
 * 
 */
public class DrawRabActivity extends Activity {

	private SurfaceView sf;
	private SurfaceHolder sfh; // surfaceView的 控制器
	private Thread thread = null;
	private String rabUrl;
	private Canvas cDraw;
	private TextView  timeView ;
	private String timeStr;
	private ProgressDialog prsDlg = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		setContentView(pnet.main.R.layout.draw_rab);
		// String rabUrl =(String)
		// this.getIntent().getCharSequenceExtra("URLFromQR");
		// String rabResult[] = getDataFromUrl(rabUrl);
		rabUrl = (String) this.getIntent().getCharSequenceExtra("URLFromQR");
		timeView = (TextView)findViewById(pnet.main.R.id.timeView);
		
		sf = (SurfaceView) findViewById(pnet.main.R.id.surfaceView1);
		
		//prsDlg = ProgressDialog.show(this, "", "加载中，请稍后……  ");//
		// 得到控制器
		sfh = sf.getHolder();
		// 对 surfaceView 进行操作
		sfh.addCallback(new DoThings());// 自动运行surfaceCreated以及surfaceChanged
		timeView.setText(timeStr);
		
		// drawRab(rabResult);

		super.onCreate(savedInstanceState);
	}

	/**
	 * 获取rab失败的字符串
	 * */
	String[] getDataFromUrl(String rabUrl) {
		String rabStr = PnetURLConnection.sendGet(rabUrl, null);
		Log.i("rabStr", rabStr);
		String[] parts = rabStr.split("\\|");
		// Log.e("partsr    ---", parts[0]+parts[1]);
		return parts;
	}

	/**
	 * 画出图
	 * */
	void drawRab(String rabResult[]) {

	}

	private class DoThings implements SurfaceHolder.Callback {

		// private String []rabStr ;
		// DoThings(String[] rab){
		// rabStr = rab;
		// Log.i("rab", rabStr[0] + "" + rabStr.toString());
		// }

		@Override
		public void surfaceChanged(SurfaceHolder holder, int format, int width,
				int height) {
			// 在surface的大小发生改变时激发
			System.out.println("surfaceChanged");
		}

		@Override
		public void surfaceCreated(SurfaceHolder holder) {

			thread = new Thread() {
				public void run() {

					WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
					Display display = wm.getDefaultDisplay();
					Log.i("screen", "屏幕尺寸1: 宽度 = " + display.getWidth()
							+ "高度 = :" + display.getHeight());

					String rabResult[] = getDataFromUrl(rabUrl);
//					Log.i("rab", rabResult[0] + "" + rabResult[1]);
//					Log.e("rabResult length", "" + rabResult.length);

					// 1.这里就是核心了， 得到画布 ，然后在你的画布上画出要显示的内容
					cDraw = sfh.lockCanvas(new Rect(0, 0, display.getWidth(),
							display.getHeight()));

					for (int count = 0; count < rabResult.length; count++) {
					//	Log.i("rabResult" + "count", rabResult[count]);

						// 2.开画
						
						if (Integer.parseInt(rabResult[2]) <50){
							
						}
						if (0 == count) {
							Paint pText = new Paint();
							pText.setColor(Color.GRAY);
							pText.setTextSize(10);
							cDraw.drawText(rabResult[0], 0, 0, pText);
							timeStr = rabResult[0];

						} else {
							if (count % 2 == 0) { // 偶数画框框
							//	System.out.println(count);

								Paint p = new Paint();
								if (Integer.parseInt(rabResult[count]) < 100) {
									p.setColor(Color.rgb(78, 175, 87));
								} else if (Integer.parseInt(rabResult[count]) >= 100
										&& Integer.parseInt(rabResult[count]) < 500) {
									p.setColor(Color.rgb(255, 169, 118));
								} else if (Integer.parseInt(rabResult[count]) >= 500
										&& Integer.parseInt(rabResult[count]) < 1000) {
									p.setColor(Color.rgb(216, 80, 0));
								} else {
									p.setColor(Color.rgb(170, 67, 60));
								}

								if (Integer.parseInt(rabResult[count]) >= 50) {
									Paint pText = new Paint();
									pText.setTextSize(48);
									pText.setColor(Color.GRAY);
									cDraw.drawText(rabResult[count - 1]
											+ " rab fail:" + rabResult[count],
											0, 30 + 50 * (count - 1), pText);

									Rect aa = new Rect(0,
											30 + 50 * (count - 1) + 10,
											Integer.parseInt(rabResult[count]),
											30 + 50 * (count - 1) + 10 + 30);

									cDraw.drawRect(aa, p);
								}

							}

						}// end of else

					}// end of for

					// 3. 解锁画布 更新提交屏幕显示内容
					sfh.unlockCanvasAndPost(cDraw);

				//	prsDlg.dismiss();
				};
			};
			thread.start();

		}

		@Override
		public void surfaceDestroyed(SurfaceHolder holder) {
			// 销毁时激发，一般在这里将画图的线程停止、释放。
			System.out.println("surfaceDestroyed==");
			//thread.interrupt();
			//thread.destroy();
		}

	}

	@Override
	protected void onDestroy() {
		// TODO 自动生成的方法存根

		super.onDestroy();
	}
}









