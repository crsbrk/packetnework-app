package pnet.warning;


import pnet.main.R;
import pnet.model.WarningInfo;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ShowWarningInfoDetailActivity extends Activity {
	private TextView happenTimeTextView = null;
	private TextView netypeTextView = null;
	private TextView severityTextView = null;
	private TextView infoTextView = null;
	private TextView addtextTextView = null;
	private TextView positionTextView = null;
	private WarningInfo warnInfo = null;
	
	public ShowWarningInfoDetailActivity() {
		// TODO 自动生成的构造函数存根
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		System.out.println("in on Create of WARNING DETAIL ACTIVITY BEFORE LOAD LAYOUT!!!!");
		
		setContentView(R.layout.warning_info_detail);
		
	System.out.println("in on Create of WARNING DETAIL ACTIVITY AFTER LOAD LAYOUT!!!");
	
		happenTimeTextView = (TextView) findViewById(R.id.happenTmeTextView);
		netypeTextView = (TextView) findViewById(R.id.netypeTextView);
		severityTextView = (TextView) findViewById(R.id.severityTextView);
		infoTextView = (TextView) findViewById(R.id.infoTextView);
		addtextTextView = (TextView) findViewById(R.id.addtextTextView);
		positionTextView = (TextView) findViewById(R.id.positionTextView);
		
		
		System.out.println("BEFORE GET INTENT ");
		Intent intent = getIntent();
		warnInfo = (WarningInfo) intent.getSerializableExtra("warningInfo");	
		System.out.println("BEFORE UPDATE WARNINFO DETAIL  ");
		updateWarnInfoDetail(warnInfo);
		super.onCreate(savedInstanceState);
	}

	void updateWarnInfoDetail(WarningInfo wInfo){

		System.out.println("GET SEVERITY COLOR!");
		
		int color = Integer.parseInt(wInfo.getSeverity());
		System.out.println("COLOR-->" + color);
		happenTimeTextView.setText(wInfo.getHappenTime());
		netypeTextView.setText(wInfo.getNeType());
		severityTextView.setText(wInfo.getSeverity() + "级告警");
		switch (color) {
		case 1:
			severityTextView.setTextColor(getResources().getColor(R.color.alarm_letter_red));
			break;
		case 2:
			severityTextView.setTextColor(getResources().getColor(R.color.alarm_letter_orange));//rgb(255, 96, 0)橘黄色
			break;
		case 3:
			severityTextView.setTextColor(getResources().getColor(R.color.alarm_letter_yellow));
			break;
		case 4:
			severityTextView.setTextColor(getResources().getColor(R.color.alarm_letter_blue));
		default:
			break;
		}
		
		infoTextView.setText(wInfo.getInfo());
		addtextTextView.setText(wInfo.getAddText());
		positionTextView.setText(wInfo.getPosition());
	}


}
