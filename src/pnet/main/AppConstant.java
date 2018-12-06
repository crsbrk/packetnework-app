package pnet.main;

public class AppConstant {

	public class URL{
		//http://192.168.50.23/cgi-bin/getAlarmByXml.py     http://192.168.50.23/cgi-bin/getallztealarmbyxml.py
		public static final String WARN_URL = "http://192.168.50.23/cgi-bin/getallztealarmbyxml.py";//http://192.168.50.23/cgi-bin/getvpdnalarmbyxml.py
		public static final String VPDN_URL = "http://192.168.50.23/cgi-bin/getapnxml.pl";
		public static final String TOPO_URL ="http://192.168.50.23/topo/index.php"; //"http://192.168.50.23/topo/fzwTopo.html";
		public static final String ACCOUNT_URL = "http://192.168.50.59/tools/2/view_m.php?id=1";
		public static final String FAULT_URL = "http://192.168.50.23/fmsg/m";
		
		public static final String FORECAST_URL = "http://192.168.50.59/Auto/Forecast/";		
		
		public static final String ZTE_SGSN_URL = "http://192.168.50.23/cgi-bin/showsgsnnow.py";
		public static final String HW_SGSN_URL = "http://192.168.50.23/cgi-bin/showhwpmnow.py";
		public static final String RAB_FAIL_URL = "http://192.168.50.23/cgi-bin/rab2xmlorjson.py";//"http://192.168.50.23/zc/rab/showrab.php";//"http://192.168.50.59/tools/mob/rab/";   //http://192.168.50.23/cgi-bin/showsgsnrabfnow.py
		public static final String FLOW_IN30DAYS_URL="http://192.168.50.23/cgi-bin/showsgsnflureport.py";
		public static final String GGSN_P_URL = "http://192.168.50.23/cgi-bin/showggsnpool.py";
		//http://192.168.50.23/cgi-bin/showvpdncn.py
		public static final String GGSN_C_USERS_URL ="http://192.168.50.23/cgi-bin/showvpdnall.py";
		public static final String GGSN_C_GRE_CPU_URL = "http://192.168.50.23/cpu/cpuInfo.php";
		
		public static final String CZ_FLOW_URL = "http://192.168.50.59/tools/cz/";
	}
	public  class GGSN43 {
		
		public static final String GGSN43ip = "192.168.43.43";
		public static final int GGSN43port = 23;
		public static final String user = "zhangchen";
		public static final String password = "Zcgz@123";
		
	}
	
	public class GGSN42 {
		public static final String GGSN42ip = "192.168.42.42";
		public static final int GGSN42port = 23;
		public static final String user = "zhangchen";
		public static final String password = "Zcgz@123";
	}
	
	public class GGSNCOMMAND {
//		public static final String showGGSNIppoolCommand = "show ggsn ippool gzsgtt_wxsc_gdapn";
		public static final String showGGSNIppoolCommand = "show pgw ippool ";
		public static final String showGGSNApStateCommand = "show pgw ap-state ";
		public static final String showGGSNVRFCommand = "show ip vrf ";
		public static final String showTunnelStateCommand = "show running-config-interface gre_tunnel";
		public static final String pingTunnelCommand = "ping vrf ";
		public static final String pingPortCommand  = "ping vrf ";
	}
	
	public static class OperationSpinner {
		public static final String[] l2tp_spinner={"查看APN状态","Ping本端端口地址","ping对端端口地址"};
		public static final  String[] gre_spinner={"查看APN状态","Ping本端端口地址","ping对端端口地址","ping tunnel地址","查看tunnel状态","查看VRF信息"};

	}
}


