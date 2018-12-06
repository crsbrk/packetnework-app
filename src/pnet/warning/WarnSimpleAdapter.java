package pnet.warning;

import java.util.List;
import java.util.Map;



import pnet.main.R;
import pnet.model.WarningInfo;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class WarnSimpleAdapter extends SimpleAdapter {

	public List<WarningInfo> warningAdapterInfos = null;
	private List<? extends Map<String, ?>> mylist = null;
	 LayoutInflater inflater ;
	
	private static int[] backgroundColors = { R.color.alarm_red, R.color.alarm_orange,R.color.alarm_yellow,R.color.alarm_blue};
	private static int[] letterColors = { R.color.alarm_letter_red, R.color.alarm_orange,R.color.alarm_letter_yellow,R.color.alarm_letter_blue };
	
	public WarnSimpleAdapter(Context context,
			List<? extends Map<String, ?>> data, int resource, String[] from,
			int[] to) {

		super(context, data, resource, from, to);
		 mylist = data;
		  inflater = LayoutInflater.from(context); // context from Activity
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		 View view;
       
		 if (convertView == null) {
			    convertView = inflater.inflate(R.layout.warning_info_item, null);
			  }

         View localView = super.getView(position, convertView, parent);//获取到一个linearLayout
         
         System.out.println("before get TextView in warnAdapter");
         
         TextView itemNameView = (TextView)convertView.findViewById(R.id.netElementName);
         TextView itemTimeView = (TextView)convertView.findViewById(R.id.hanppenTimeAndNetype);
         TextView itemSeverityView =  (TextView)convertView.findViewById(R.id.severity);
         TextView itemAlarmInfoView =  (TextView)convertView.findViewById(R.id.alarmShortInfo);
         
         System.out.println("after get TextView in warnAdapter");
         
        Object mySeverity =  mylist.get(position).get("severity");
        String seveityLevel =mySeverity.toString().trim();
        
        Log.e("adapter severity", ""+mylist.get(position).get("severity"));
        
        if (seveityLevel.equals("1级告警")) {
        	 System.out.println("一级告警");
        
       	 localView.setBackgroundResource(backgroundColors[0]);  //设置每一item的背景颜色
             
             itemAlarmInfoView.setTextColor(convertView.getResources().getColor(R.color.alarm_letter_red));
             itemNameView.setTextColor(convertView.getResources().getColor(R.color.alarm_letter_red));
             itemSeverityView.setTextColor(convertView.getResources().getColor(R.color.alarm_letter_red));
             itemTimeView.setTextColor(convertView.getResources().getColor(R.color.alarm_letter_red));
         
        } else if (seveityLevel.equals("2级告警")){
             localView.setBackgroundResource(backgroundColors[1]);

             itemAlarmInfoView.setTextColor(convertView.getResources().getColor(R.color.alarm_letter_orange));
             itemNameView.setTextColor(convertView.getResources().getColor(R.color.alarm_letter_orange));
             itemSeverityView.setTextColor(convertView.getResources().getColor(R.color.alarm_letter_orange));
             itemTimeView.setTextColor(convertView.getResources().getColor(R.color.alarm_letter_orange));
             
             System.out.println("二级告警");
             
         }else if(seveityLevel.equals("3级告警")){
             localView.setBackgroundResource(backgroundColors[2]);

             itemAlarmInfoView.setTextColor(convertView.getResources().getColor(R.color.alarm_letter_yellow));
             itemNameView.setTextColor(convertView.getResources().getColor(R.color.alarm_letter_yellow));
             itemSeverityView.setTextColor(convertView.getResources().getColor(R.color.alarm_letter_yellow));
             itemTimeView.setTextColor(convertView.getResources().getColor(R.color.alarm_letter_yellow));

             System.out.println("三级告警");
         }else {
        	 localView.setBackgroundResource(backgroundColors[3]);

        	 itemAlarmInfoView.setTextColor(convertView.getResources().getColor(R.color.alarm_letter_blue));
             itemNameView.setTextColor(convertView.getResources().getColor(R.color.alarm_letter_blue));
             itemSeverityView.setTextColor(convertView.getResources().getColor(R.color.alarm_letter_blue));
             itemTimeView.setTextColor(convertView.getResources().getColor(R.color.alarm_letter_blue));

        	 System.out.println("其他级告警");
         }
         return localView;
		//return super.getView(position, convertView, parent);
	}
	
	
	
	

}
