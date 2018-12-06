package pnet.main;

import pnet.webview.MyWebViewActivity;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import pnet.draw.*;

public class IndictitorList extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.indicator_list);
		
		
		 final BaseExpandableListAdapter adapter = new BaseExpandableListAdapter() {
	            //设置组视图的图片
	            int[] logos = new int[] { R.drawable.search_button,R.drawable.search_button,R.drawable.search_button,R.drawable.search_button};
	            //设置组视图的显示文字
	            private String[] generalsTypes = new String[] { "SGSN", "公众GGSN", "行业GGSN","承载网" };
	            //子视图显示文字
	            private String[][] generals = new String[][] {
	                    { "RAB指派失败", "中兴SGSN指标","华为SGSN指标","流量表" },
	                    { "组pool指标" },
	                    { "行业GRE地址池占比", "行业在线用户数指标" },
	                    {"流量监控"}
	            };
	            //子视图图片
	            public int[][] generallogos = new int[][] {
	                    { R.drawable.empty, R.drawable.empty,
	                            R.drawable.empty, R.drawable.empty,
	                            },
	                    { R.drawable.empty
	                            },
	                    { R.drawable.empty, R.drawable.empty,},
	                    {
	                    	R.drawable.empty
	                    } 
	                            
	            };
	            
	            //自己定义一个获得文字信息的方法
	            TextView getTextView() {
	                AbsListView.LayoutParams lp = new AbsListView.LayoutParams(
	                        ViewGroup.LayoutParams.WRAP_CONTENT, 84);
	                TextView textView = new TextView(
	                		IndictitorList.this);
	                textView.setLayoutParams(lp);
	                textView.setGravity(Gravity.CENTER_VERTICAL|Gravity.LEFT);
	                textView.setPadding(36, 0, 0, 0);
	                textView.setTextSize(20);
	                textView.setTextColor(Color.BLACK);
	                return textView;
	            }

	            
	            //重写ExpandableListAdapter中的各个方法
	            @Override
	            public int getGroupCount() {
	                // TODO Auto-generated method stub
	                return generalsTypes.length;
	            }

	            @Override
	            public Object getGroup(int groupPosition) {
	                // TODO Auto-generated method stub
	                return generalsTypes[groupPosition];
	            }

	            @Override
	            public long getGroupId(int groupPosition) {
	                // TODO Auto-generated method stub
	                return groupPosition;
	            }

	            @Override
	            public int getChildrenCount(int groupPosition) {
	                // TODO Auto-generated method stub
	                return generals[groupPosition].length;
	            }

	            @Override
	            public Object getChild(int groupPosition, int childPosition) {
	                // TODO Auto-generated method stub
	                return generals[groupPosition][childPosition];
	            }

	            @Override
	            public long getChildId(int groupPosition, int childPosition) {
	                // TODO Auto-generated method stub
	                return childPosition;
	            }

	            @Override
	            public boolean hasStableIds() {
	                // TODO Auto-generated method stub
	                return true;
	            }

	            @Override
	            public View getGroupView(int groupPosition, boolean isExpanded,
	                    View convertView, ViewGroup parent) {
	                // TODO Auto-generated method stub
	                LinearLayout ll = new LinearLayout(
	                		IndictitorList.this);
	                ll.setOrientation(0);
	                ImageView logo = new ImageView(IndictitorList.this);
	                //logo.setImageResource(logos[groupPosition]);
	                logo.setPadding(50, 50, 0, 50);
	                
	                ll.addView(logo);
	                TextView textView = getTextView();
	                textView.setTextColor(Color.BLACK);
	                textView.setText(getGroup(groupPosition).toString());
	                ll.addView(textView);

	                return ll;
	            }

	            @Override
	            public View getChildView(int groupPosition, int childPosition,
	                    boolean isLastChild, View convertView, ViewGroup parent) {
	                // TODO Auto-generated method stub
	                LinearLayout ll = new LinearLayout(
	                		IndictitorList.this);
	                ll.setOrientation(0);
	                ImageView generallogo = new ImageView(
	                		IndictitorList.this);
	                generallogo
	                        .setImageResource(generallogos[groupPosition][childPosition]);
	                ll.addView(generallogo);
	                TextView textView = getTextView();
	                textView.setText(getChild(groupPosition, childPosition)
	                        .toString());
	                ll.addView(textView);
	                return ll;
	            }

	            @Override
	            public boolean isChildSelectable(int groupPosition,
	                    int childPosition) {
	                // TODO Auto-generated method stub
	                return true;
	            }


				

			

	        };

	        ExpandableListView expandableListView = (ExpandableListView) findViewById(R.id.expandableListIndictator);
	        expandableListView.setAdapter(adapter);
	        expandableListView.expandGroup(0);
	        
	        /*
	         * 
	         * 	            private String[][] generals = new String[][] {
	                    { "RAB指派失败", "中兴SGSN指标","华为SGSN指标","流量表" },
	                    { "组pool指标" },
	                    { "行业GRE地址池占比", "行业在线用户数指标" },
	                    {"流量监控"}
	            };

	         * */
	        //设置item点击的监听器
	        expandableListView.setOnChildClickListener(new OnChildClickListener() {
				
				@Override
				public boolean onChildClick(ExpandableListView parent, View v,
						int groupPosition, int childPosition, long id) {
//					  Toast.makeText(
//				                IndictitorList.this,
//				                "你点击了" + adapter.getChild(groupPosition, childPosition),
//				                Toast.LENGTH_SHORT).show();
					  String item =(String)  adapter.getChild(groupPosition, childPosition);
					  if (item.equals("RAB指派失败")){
							Intent intent2rab = new Intent();
							//intent2rab.setClass(IndictitorList.this, MyWebViewActivity.class);
							intent2rab.setClass(IndictitorList.this, DrawRabActivity.class);
							intent2rab.putExtra("URLFromQR", AppConstant.URL.RAB_FAIL_URL);//Intent中压入一组键值对,显示拓扑  
							IndictitorList.this.startActivity(intent2rab);  
					  }
					  else if(item.equals("中兴SGSN指标")){
							Intent intent2zteSgsn = new Intent();
							intent2zteSgsn.setClass(IndictitorList.this, MyWebViewActivity.class);  
							intent2zteSgsn.putExtra("URLFromQR", AppConstant.URL.ZTE_SGSN_URL);//Intent中压入一组键值对,显示拓扑  
							IndictitorList.this.startActivity(intent2zteSgsn);  
					  }
					  else if(item.equals("华为SGSN指标")){
							Intent intent2hwSgsn = new Intent();
							intent2hwSgsn.setClass(IndictitorList.this, MyWebViewActivity.class);  
							intent2hwSgsn.putExtra("URLFromQR", AppConstant.URL.HW_SGSN_URL);//Intent中压入一组键值对,显示拓扑  
							IndictitorList.this.startActivity(intent2hwSgsn);  
					  }
					  else if(item.equals("流量表")){
							Intent intent2SgsnFlow = new Intent();
							intent2SgsnFlow.setClass(IndictitorList.this, MyWebViewActivity.class);  
							intent2SgsnFlow.putExtra("URLFromQR", AppConstant.URL.FLOW_IN30DAYS_URL);//Intent中压入一组键值对,显示拓扑  
							IndictitorList.this.startActivity(intent2SgsnFlow);  
					  }
					  else if(item.equals("组pool指标")){
							Intent intent2GgsnPool = new Intent();
							intent2GgsnPool.setClass(IndictitorList.this, MyWebViewActivity.class);  
							intent2GgsnPool.putExtra("URLFromQR", AppConstant.URL.GGSN_P_URL);//Intent中压入一组键值对,显示拓扑  
							IndictitorList.this.startActivity(intent2GgsnPool);  
					  }
					  else if(item.equals("行业GRE地址池占比")){
							Intent intent2GREIpPoolGGSNC = new Intent();
							intent2GREIpPoolGGSNC.setClass(IndictitorList.this, MyWebViewActivity.class);  
							intent2GREIpPoolGGSNC.putExtra("URLFromQR", AppConstant.URL.GGSN_C_GRE_CPU_URL);//Intent中压入一组键值对,显示拓扑  
							IndictitorList.this.startActivity(intent2GREIpPoolGGSNC);  
					  }
					  else if(item.equals("行业在线用户数指标")){
							Intent intent2usersGGSNC = new Intent();
							intent2usersGGSNC.setClass(IndictitorList.this, MyWebViewActivity.class);  
							intent2usersGGSNC.putExtra("URLFromQR", AppConstant.URL.GGSN_C_USERS_URL);//Intent中压入一组键值对,显示拓扑  
							IndictitorList.this.startActivity(intent2usersGGSNC);  
					  }
					  else if(item.equals("流量监控")){
							Intent intent2CZFlow = new Intent();
							intent2CZFlow.setClass(IndictitorList.this, MyWebViewActivity.class);  
							intent2CZFlow.putExtra("URLFromQR", AppConstant.URL.CZ_FLOW_URL);//Intent中压入一组键值对,显示拓扑  
							IndictitorList.this.startActivity(intent2CZFlow);  
					  }
					  
					return false;
				}
			});
}
	}

	
	
