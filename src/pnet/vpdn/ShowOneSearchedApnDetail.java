package pnet.vpdn;

import pnet.httpConnection.NetTelnet;
import pnet.main.AppConstant;
import pnet.main.R;
import pnet.model.ApnInfo;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;



public class ShowOneSearchedApnDetail extends Activity{

	private TextView spinnerTextView ;    //spinner
	private Spinner spinner;
	private ArrayAdapter<String> adapter;
	private String[] spinnerData;
	
	private static final int GGSNIPPOOLCMD = 0;
	private static final int APNSTATE = 1;
	private static final int VRF = 2;
	private static final int PINGTUNNEL = 3;
	private static final int TUNNELSTATE = 4;
	private static final int PINGUSERPORTIP =5;
	private static final int PINGUNICOMPORTIP = 6;
	
	private String aimIp = null;
	private String sourceIp = null;
	
	private TextView  showIppoolText = null;
	private Handler showHandler =null;
	private String apnText = null;
	private ApnInfo apn = null;
	private String aliasText = null;
	private  String ggsnidText = null;
	private String statusText = null;
	private String interfaceipbeginText = null;
	private String interfaceipendText = null;
	private String vpnidText = null;
	private String protocolText = null;
	private String cityText = null;
	private String userNameText = null;
	private String industryText  = null;
	
	private String ippooltypeText = null;
	private String ippoolText = null ;
	private String routeidText =null;
	private String routeifText = null;
	private String routevlanText = null;
	private String lineNameText = null;
	private String commhisText =null;
	private String createdatText =null;
	private String updatedatText =null;
	private String loopbackText =null;
	private String transinfoText =null;
	private String contactIDText = null;
	
	public ShowOneSearchedApnDetail() {
		
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		
		 apn = (ApnInfo)getIntent().getSerializableExtra("oneApn");
		
		setContentView(R.layout.searched_one_apn_detail);
		
		apnText = "" + apn.getApn();
	    aliasText = "" + apn.getAlias();
		statusText ="" + apn.getStatus();
		cityText = ""+apn.getCity();
		userNameText = "" + apn.getUsername();
		industryText ="" +  apn.getIndustry();
		ggsnidText ="" +apn.getGgsnid();
		protocolText ="" +apn.getProtocol();
		vpnidText = "" + apn.getVpnid();
	    interfaceipbeginText ="" +  apn.getInterfaceipbegin();
	    interfaceipendText = "" + apn.getInterfaceipend();
		ippooltypeText ="" + apn.getIppooltype();
		ippoolText =""+ apn.getIppool();
		routeidText =""+ apn.getRouteid();
		routeifText = "" + apn.getRouteif();
		routevlanText = "" + apn.getRoutevlan();
		lineNameText =""+ apn.getLinename();
		//String commhisText =" 操作记录："+ apn.getCommhis();
		createdatText ="" + apn.getCreated_at();
		updatedatText ="" +  apn.getUpdated_at();
		loopbackText ="" +  apn.getLoopback();
		transinfoText ="" + apn.getTransinfo();
		contactIDText = "" + apn.getContactid();
		
		commhisText = apn.getCommhis();//.replaceAll("【","[").replaceAll("】","]");
		
		TextView apnTV = (TextView)findViewById(R.id.apn_text);
		TextView aliasTV = (TextView)findViewById(R.id.alias_text);
		TextView statusTV = (TextView)findViewById(R.id.status_text);
		TextView userNameTV = (TextView)findViewById(R.id.username_text);
		TextView cityTV = (TextView)findViewById(R.id.city_text);
		TextView industryTV = (TextView)findViewById(R.id.industry_text);
		TextView ggsnidTV = (TextView)findViewById(R.id.ggsnid_text);
		TextView protocolTV = (TextView)findViewById(R.id.protocol_text);
		TextView vpnidTV = (TextView)findViewById(R.id.vpnid_text);
		TextView interfaceipbeginTV = (TextView)findViewById(R.id.interfaceipbegin_text);
		TextView interfaceipendTV = (TextView)findViewById(R.id.interfaceipend_text);
		TextView ippoolTypeTV = (TextView)findViewById(R.id.ippooltype_text);
		TextView ippoolTV =  (TextView)findViewById(R.id.ippool_text);
		TextView routeifTV = (TextView)findViewById(R.id.routeif_text);
		TextView routeidTV = (TextView)findViewById(R.id.routeid_text);
		TextView routevlanTV = (TextView)findViewById(R.id.routevlan_text);
		TextView lineNameTextTV = (TextView)findViewById(R.id.linename_text);
		TextView commhisTextTV = (TextView)findViewById(R.id.commhis_text);
		TextView createdatTextTV = (TextView)findViewById(R.id.created_at_text);
		TextView upatedatTextTV  = (TextView)findViewById(R.id.updated_at_text);
		TextView loopbackTextTV = (TextView)findViewById(R.id.loopback_text);
		TextView transinfoTextTV = (TextView)findViewById(R.id.transinfo_text);
		TextView contactIDTextTV = (TextView)findViewById(R.id.contactid_text);
		
		Button ippoolButton = (Button) findViewById(R.id.show_ippool_infos_button);
	    showIppoolText = (TextView) findViewById(R.id.ippool_details_info_text);
		
		apnTV.setText(apnText);
		aliasTV.setText(aliasText);
		statusTV.setText(statusText);
		userNameTV.setText(userNameText);
		cityTV.setText(cityText);
		industryTV.setText(industryText);
		ggsnidTV.setText(ggsnidText);
		protocolTV.setText(protocolText);
		vpnidTV.setText(vpnidText);
		interfaceipbeginTV.setText(interfaceipbeginText);
		interfaceipendTV.setText(interfaceipendText);
		ippoolTV.setText(ippoolText);
		ippoolTypeTV.setText(ippooltypeText);
		routeifTV.setText(routeifText);
		routeidTV.setText(routeidText);
		routevlanTV.setText(routevlanText);
		lineNameTextTV.setText(lineNameText);
		
		createdatTextTV.setText(createdatText);
		upatedatTextTV.setText(updatedatText);
		loopbackTextTV.setText(loopbackText);
		transinfoTextTV.setText(transinfoText);
		contactIDTextTV.setText(contactIDText);
		
		ippoolButton.setOnClickListener(new ipPoolListener());
		
		if (commhisText != null ) {
			
			commhisText = commhisText.replaceAll("【","[").replaceAll("】","]");
			String hasEnterStr = putEnterFactory(new StringBuffer (commhisText) );
			commhisTextTV.setText(hasEnterStr);
		}else {
			commhisTextTV.setText("");
		}
		
		if (apn.getProtocol() == null ) 
		{
			Log.e("apn.getProtocal-->", "null");
			apn.setProtocol("L2TP");  //如果台帐上没有写接入方式，默认为L2TP的接入方式，操作会少很多
		}
		
		if (!apn.getProtocol().equals("GRE")) {
			ippoolButton.setVisibility(View.GONE);	//如果不是GRE接入方式，则不显示地址池按钮
			
			spinnerData = AppConstant.OperationSpinner.l2tp_spinner;   //设置操作数据源
		} else {
			spinnerData = AppConstant.OperationSpinner.gre_spinner;
		}
		
		
			
		
		
		 showHandler = new Handler() {

			@Override
			public void handleMessage(Message msg) {
				// TODO 自动生成的方法存根
				
				
		            switch (msg.what) {
						case GGSNIPPOOLCMD:
							String poolInfo = (String) msg.obj;
							if (poolInfo != null){
								showIppoolText.setText(poolInfo);
							}
							else{
								showIppoolText.setText("no data try again");
							}
						break;
						
						case APNSTATE:
						 	Bundle b = msg.getData();  
				            String str = b.getString("apnState");  
				            //String name = b.getString("name");
				            spinnerTextView.setText(str);
				            break;
						case VRF:
							Bundle bundleVRF = msg.getData();  
				            String strVRF = bundleVRF.getString("VRF");  
				            //String name = b.getString("name");
				            spinnerTextView.setText(strVRF);
				            break;
						case PINGTUNNEL:
							Bundle bundlePingTunnel = msg.getData();  
				            String strPingTunnel = bundlePingTunnel.getString("pingTunnel");  
				            //String name = b.getString("name");
				            spinnerTextView.setText(strPingTunnel);
				           
							break;
						case TUNNELSTATE:
							Bundle bundleTunnelState = msg.getData();  
				            String strTunnelState = bundleTunnelState.getString("TunnelState");  
				            //String name = b.getString("name");
				            spinnerTextView.setText(strTunnelState);
				            break;
				            
						case PINGUSERPORTIP:
							Bundle userPortBundle = msg.getData();  
				            String strUserPort = userPortBundle.getString("pingUserPortIp");  
				            //String name = b.getString("name");
				            spinnerTextView.setText(strUserPort);
				            break;
				            
						case PINGUNICOMPORTIP:
							Bundle unicomPortBundle = msg.getData();  
				            String strUnicomPort = unicomPortBundle.getString("pingUnicomPortIp");  
				            //String name = b.getString("name");
				            spinnerTextView.setText(strUnicomPort);
				            break;
				            
					default:
						break;
					}
		            
				super.handleMessage(msg);
			}
			
			
		};
		
		
		spinnerTextView = (TextView) findViewById(R.id.spinnerText);
		spinner = (Spinner) findViewById(R.id.Spinner01);
		//将可选内容与ArrayAdapter连接起来
		adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,spinnerData);
		
		//设置下拉列表的风格
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		//将adapter 添加到spinner中
		spinner.setAdapter(adapter);
//		spinner.setSelection(0,false);
		//添加事件Spinner事件监听  
		spinner.setOnItemSelectedListener(new SpinnerSelectedListener());
		
		//设置默认值
		spinner.setVisibility(View.VISIBLE);
          	
		
		
		
		super.onCreate(savedInstanceState);
	}

	

	class ipPoolListener implements android.view.View.OnClickListener {

		  
		@Override
		public void onClick(View v) {
			
			
			new Thread(){

				@Override
				public void run() {
					// TODO 自动生成的方法存根
				NetTelnet telnet = null;
					if (apn.getGgsnid().equals("GGSN43")) {	
					
						telnet = new NetTelnet(AppConstant.GGSN43.GGSN43ip, AppConstant.GGSN43.GGSN43port, 
							AppConstant.GGSN43.user, AppConstant.GGSN43.password);
					
					} else {
						telnet = new NetTelnet(AppConstant.GGSN42.GGSN42ip, AppConstant.GGSN42.GGSN42port, 
								AppConstant.GGSN42.user, AppConstant.GGSN42.password);
						
					}
					
				
					//	telnet.login(user, password)
					//telnet.sendCommand(AppConstant.GGSNCOMMAND.showGGSNIppoolCommand + changeApnStr(apnText.substring(4)));
					
					telnet.sendCommand(AppConstant.GGSNCOMMAND.showGGSNIppoolCommand + changeApnStr(apnText));
					
					Log.e("pool", AppConstant.GGSNCOMMAND.showGGSNIppoolCommand + changeApnStr(apnText.substring(3)));
					//showIppoolText.setText(telnet.getResultStr());
					
					Message msg = new Message();
					msg.obj = telnet.getResultStr();
					msg.what = GGSNIPPOOLCMD;
					telnet.disconnect();
					Log.e("strCommandResult", telnet.getResultStr());
					showHandler.sendMessage(msg);
					super.run();
				}
				
			}.start();
		}




		 
	 }
	@Override
	protected void onStart() {
		// TODO 自动生成的方法存根
		
		super.onStart();
	}
	
	public String changeApnStr (String apnStr){
		String str = apnStr.replace(".", "_");
		return str;
		
	}
	
	//使用数组形式操作
	class SpinnerSelectedListener implements OnItemSelectedListener{

		
		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			spinnerTextView.setText("正在--->"+spinnerData[arg2]);
			
			switch (arg2) {
			case 0:
				showApnState();break;
			case 1:
				pingUnicomPortIp();break;
			
			case 2:
				pingUserPortIp(); break;
			
			case 3:
				pingTunnelIp();break;
			
			case 4:
				showTunnelState();break;
			
			case 5:
				showVrfInfo();break;
				
			default:
				break;
			}
			
		}

		private void showVrfInfo() {
			// TODO 自动生成的方法存根
			new Thread(){

				@Override
				public void run() {
					// TODO 自动生成的方法存根
				NetTelnet telnet = null;
				telnet = loginGgsn();
				
					telnet.sendCommand(AppConstant.GGSNCOMMAND.showGGSNVRFCommand + apnText);
										
					Message msg = new Message();
					Bundle bundle = new Bundle();
					bundle.putString("VRF", telnet.getResultStr());
					msg.setData(bundle);
					msg.what = VRF;
					telnet.disconnect();
					Log.e("VRFResult", telnet.getResultStr());
					showHandler.sendMessage(msg);
					super.run();
				}
				
			}.start();

		}

		private void showTunnelState() {
			// TODO 自动生成的方法存根
			new Thread(){

				@Override
				public void run() {
					// TODO 自动生成的方法存根
				NetTelnet telnet = null;
				telnet = loginGgsn();
				
					telnet.sendCommand(AppConstant.GGSNCOMMAND.showTunnelStateCommand +vpnidText.trim() );
										
					Message msg = new Message();
					Bundle bundle = new Bundle();
					bundle.putString("TunnelState", telnet.getResultStr());
					msg.setData(bundle);
					msg.what = TUNNELSTATE;
					telnet.disconnect();
					Log.e("TunnelState", telnet.getResultStr());
					showHandler.sendMessage(msg);
					super.run();
				}
				
			}.start();


		}
		
/**
 * ping隧道地址 
 * **/
		private void pingTunnelIp() {

			if (interfaceipbeginText != null && interfaceipbeginText != "") {
				new Thread() {

					@Override
					public void run() {
						// TODO 自动生成的方法存根
						System.out.println("interfaceipbegin-->"
								+ interfaceipbeginText);
						if (interfaceipbeginText.trim() != null
								&& interfaceipbeginText != "") {

							NetTelnet telnet = null;
							telnet = loginGgsn();

							aimIp = ipAddrProcess(interfaceipbeginText, 6); //第六个地址 ipbegin+5
							sourceIp = ipAddrProcess(interfaceipbeginText, 5); //第七个地址 ipbegin + 6
							Log.e("aimip + sourceip", aimIp + "<--->"
									+ sourceIp);
							if (aimIp != null && sourceIp != null) {

								telnet.sendCommand(AppConstant.GGSNCOMMAND.pingTunnelCommand
										+ apnText
										+ " "
										+ aimIp
										+ " source "
										+ sourceIp);

								Message msg = new Message();
								Bundle bundle = new Bundle();
								bundle.putString("pingTunnel",
										telnet.getResultStr());
								msg.setData(bundle);
								msg.what = PINGTUNNEL;
								telnet.disconnect();
								Log.e("pingTunnelResult", telnet.getResultStr());
								showHandler.sendMessage(msg);
							}
						} else {
							Toast.makeText(getApplicationContext(), "错误ip",
									Toast.LENGTH_SHORT).show();
						}

						super.run();
					}

				}.start();
			} else {
				Toast.makeText(getApplicationContext(), "错误ip",
						Toast.LENGTH_SHORT).show();
			}

		}
		
/**
 * 在interfaceipbeginText 的ip地址上 + i
 * @return ip
 * */
		private String ipAddrProcess(String interfaceipbeginText, int i) {
			String ip;
			if (countStrDot(interfaceipbeginText) == 3) {
			
				String ipSegment[] = interfaceipbeginText.split("[.]");
				int fourthSgmt = Integer.parseInt(ipSegment[3]) + i;
				ip = ipSegment[0] +"."+ ipSegment[1]+"." +ipSegment[2] +"."+ fourthSgmt;
			 
			 return ip;		
			}
			else {
				Toast.makeText(getApplicationContext(), "错误的ip "+interfaceipbeginText,
					     Toast.LENGTH_SHORT).show();
				return null;
			}
		}
		
		/**
		 * 判断ip 有几个分隔符
		 * */
		int countStrDot(String str) {
			int count = 0;
			
			for (int i=0; i<str.length(); i++)
				if (str.charAt(i) == '.')
					count++;
		
			return count;
		}

		private void pingUserPortIp() {
			if (interfaceipbeginText != null && interfaceipbeginText != "") {
				// TODO 自动生成的方法存根
				//	 final String pingPortCmd = null;
				String vrfName = null;
				String sourceIp = null;
				System.out.println("protocolText" + protocolText);
				if (protocolText.trim().equals("GRE")) {
					System.out.println(protocolText + " equals GRE");
					vrfName = "GRE";
					if (ggsnidText.trim().equals("GGSN41")) {
						sourceIp = "192.10.0.29";
					} else if (ggsnidText.trim().equals("GGSN42")) {
						sourceIp = "192.10.0.35";
					} else {
						sourceIp = "192.10.0.37";
					}

				} else {
					vrfName = "L2TP";
					if (ggsnidText.trim().equals("GGSN41")) {
						sourceIp = "192.10.0.30";
					} else if (ggsnidText.trim().equals("GGSN42")) {
						sourceIp = "192.10.0.36";
					} else {
						sourceIp = "192.10.0.38";
					}

				}
				final String pingPortCmd = "ping vrf " + vrfName + " "
						+ ipAddrProcess(interfaceipbeginText, 2) + " source "
						+ sourceIp;
				System.out.print("pingPortCmd->" + pingPortCmd);
				new Thread() {

					@Override
					public void run() {
						// TODO 自动生成的方法存根
						if (interfaceipbeginText.trim() != null
								&& interfaceipbeginText.trim() != "") {

							NetTelnet telnet = null;
							telnet = loginGgsn();
							//String aimIp = ipAddrProcess(interfaceipbeginText, 1);
							{
								telnet.sendCommand(pingPortCmd);//

								Message msg = new Message();
								Bundle bundle = new Bundle();
								bundle.putString("pingUserPortIp",
										telnet.getResultStr());
								msg.setData(bundle);
								msg.what = PINGUSERPORTIP;
								telnet.disconnect();
								Log.e("USERPortResult", telnet.getResultStr());
								showHandler.sendMessage(msg);
							}
						} else {
							Toast.makeText(getApplicationContext(),
									"接口ip开始地址错误", Toast.LENGTH_SHORT).show();
						}

						super.run();
					}

				}.start();
			}else {
				Toast.makeText(getApplicationContext(), "错误ip",
						Toast.LENGTH_SHORT).show();
			}

		}

		private void pingUnicomPortIp() {
			
			if (interfaceipbeginText != null && interfaceipbeginText != "") {
				String vrfName = null;
				String sourceIp = null;
				System.out.println("protocolText" + protocolText);
				if (protocolText.trim().equals("GRE")) {
					vrfName = "GRE";
					if (ggsnidText.trim().equals("GGSN41")) {
						sourceIp = "192.10.0.29";
					} else if (ggsnidText.trim().equals("GGSN42")) {
						sourceIp = "192.10.0.35";
					} else {
						sourceIp = "192.10.0.37";
					}

				} else {
					vrfName = "L2TP";
					if (ggsnidText.trim().equals("GGSN41")) {
						sourceIp = "192.10.0.30";
					} else if (ggsnidText.trim().equals("GGSN42")) {
						sourceIp = "192.10.0.36";
					} else {
						sourceIp = "192.10.0.38";
					}

				}
				final String pingPortCmd = "ping vrf " + vrfName + " "
						+ ipAddrProcess(interfaceipbeginText, 1) + " source "
						+ sourceIp;
				System.out.println("ping Port cmd->" + pingPortCmd);
				new Thread() {

					@Override
					public void run() {
						// TODO 自动生成的方法存根
						if (interfaceipbeginText != null && interfaceipbeginText != "") {

							NetTelnet telnet = null;
							System.out.println("interfacipbeginText"
									+ interfaceipbeginText);
							telnet = loginGgsn();
							//	String aimIp = ipAddrProcess(interfaceipbeginText, 1);
							 {

								telnet.sendCommand(pingPortCmd);//

								Message msg = new Message();
								Bundle bundle = new Bundle();
								bundle.putString("pingUnicomPortIp",
										telnet.getResultStr());
								msg.setData(bundle);
								msg.what = PINGUNICOMPORTIP;
								telnet.disconnect();
								Log.e("UnicomPortResult", telnet.getResultStr());
								showHandler.sendMessage(msg);
							} 
						} 
						super.run();
					}

				}.start();
			} else {
				Toast.makeText(getApplicationContext(),
						"接口ip开始地址错误" + interfaceipbeginText,
						Toast.LENGTH_SHORT).show();
			}
				
			


			
		}

		//登录GGSN
		NetTelnet loginGgsn() {
			NetTelnet telnet = null;
			
		if (apn.getGgsnid().equals("GGSN42")) {
			 
			telnet = new NetTelnet(AppConstant.GGSN42.GGSN42ip, AppConstant.GGSN42.GGSN42port, 
					AppConstant.GGSN42.user, AppConstant.GGSN42.password);
			
			 return telnet;
		
		}	else {
			 telnet = new NetTelnet(AppConstant.GGSN43.GGSN43ip, AppConstant.GGSN43.GGSN43port, 
					AppConstant.GGSN43.user, AppConstant.GGSN43.password);
			
			 return telnet;
		}
			
		}
		
		private void showApnState() {
			// TODO 自动生成的方法存根
			new Thread(){

				@Override
				public void run() {
					// TODO 自动生成的方法存根
				NetTelnet telnet = null;
				telnet = loginGgsn();
				
					telnet.sendCommand(AppConstant.GGSNCOMMAND.showGGSNApStateCommand + apnText);
										
					Message msg = new Message();
					Bundle bundle = new Bundle();
					bundle.putString("apnState", telnet.getResultStr());
					msg.setData(bundle);
					msg.what = APNSTATE;
					telnet.disconnect();
					Log.e("strCommandResult", telnet.getResultStr());
					showHandler.sendMessage(msg);
					super.run();
				}
				
			}.start();

		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
		}
	}

	/**
	 * @param StringBuffer sb
	 * @return sb.tostring 在有“通知”的后边添加回车
	 * */
	static String putEnterFactory(StringBuffer sb) {
		int firstAppearance = 0;
		int startIndex =0;
		while (  (firstAppearance = sb.indexOf("通知" , startIndex)) != -1) {
			
			//System.out.println("firstAppearance--->" + firstAppearance);
			sb.insert(firstAppearance+2, '\n');
			startIndex =startIndex + firstAppearance +2;
			
		}
		return sb.toString();
	}
	
}
