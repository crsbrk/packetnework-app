package pnet.model;



import java.io.Serializable;

public class ApnInfo implements Serializable {
	
	private static final long serialVersion = 1L;
	private String id;
	private String apn;
	private String alias;
	private String status;
	private String city;
	private String username;
	private String industry;
	private String ggsnid;
	private String protocol;
	private String vpnid;
	private String interfaceipbegin;
	private String interfaceipend;
	private String ippooltype;
	private String ippool;
	private String routeid;
	private String routeif;
	private String routevlan;
	private String linename;
	private String contactid;
	private String commhis;
	private String created_at;
	private String updated_at;
	private String loopback;
	private String transinfo;
	
	
	
	
	
	public ApnInfo() {
		super();
	}
	
	
	/**
	 * @param id
	 * @param apn
	 * @param alias
	 * @param status
	 * @param city
	 * @param username
	 * @param industry
	 * @param ggsnid
	 * @param protocol
	 * @param vpnid
	 * @param interfaceipbegin
	 * @param interfaceipend
	 * @param ippooltype
	 * @param ippool
	 * @param routeid
	 * @param routeif
	 * @param routevlan
	 * @param linename
	 * @param contactid
	 * @param commhis
	 * @param created_at
	 * @param updated_at
	 * @param loopback
	 * @param transinfo
	 */


	public ApnInfo(String id, String apn, String alias, String status,
			String city, String username, String industry, String ggsnid,
			String protocol, String vpnid, String interfaceipbegin,
			String interfaceipend, String ippooltype, String ippool,
			String routeid, String routeif, String routevlan, String linename,
			String contactid, String commhis, String created_at,
			String updated_at, String loopback, String transinfo) {
		super();
		this.id = id;
		this.apn = apn;
		this.alias = alias;
		this.status = status;
		this.city = city;
		this.username = username;
		this.industry = industry;
		this.ggsnid = ggsnid;
		this.protocol = protocol;
		this.vpnid = vpnid;
		this.interfaceipbegin = interfaceipbegin;
		this.interfaceipend = interfaceipend;
		this.ippooltype = ippooltype;
		this.ippool = ippool;
		this.routeid = routeid;
		this.routeif = routeif;
		this.routevlan = routevlan;
		this.linename = linename;
		this.contactid = contactid;
		this.commhis = commhis;
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.loopback = loopback;
		this.transinfo = transinfo;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getApn() {
		return apn;
	}



	public void setApn(String apn) {
		this.apn = apn;
	}


	public String getAlias() {
		return alias;
	}


	public void setAlias(String alias) {
		this.alias = alias;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getIndustry() {
		return industry;
	}


	public void setIndustry(String industry) {
		this.industry = industry;
	}


	public String getGgsnid() {
		return ggsnid;
	}


	public void setGgsnid(String ggsnid) {
		this.ggsnid = ggsnid;
	}


	public String getProtocol() {
		return protocol;
	}


	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}


	public String getVpnid() {
		return vpnid;
	}


	public void setVpnid(String vpnid) {
		this.vpnid = vpnid;
	}


	public String getInterfaceipbegin() {
		return interfaceipbegin;
	}


	public void setInterfaceipbegin(String interfaceipbegin) {
		this.interfaceipbegin = interfaceipbegin;
	}


	public String getInterfaceipend() {
		return interfaceipend;
	}


	public void setInterfaceipend(String interfaceipend) {
		this.interfaceipend = interfaceipend;
	}


	public String getIppooltype() {
		return ippooltype;
	}


	public void setIppooltype(String ippooltype) {
		this.ippooltype = ippooltype;
	}


	public String getIppool() {
		return ippool;
	}


	public void setIppool(String ippool) {
		this.ippool = ippool;
	}


	public String getRouteid() {
		return routeid;
	}


	public void setRouteid(String routeid) {
		this.routeid = routeid;
	}


	public String getRouteif() {
		return routeif;
	}


	public void setRouteif(String routeif) {
		this.routeif = routeif;
	}


	public String getRoutevlan() {
		return routevlan;
	}


	public void setRoutevlan(String routevlan) {
		this.routevlan = routevlan;
	}


	public String getLinename() {
		return linename;
	}


	public void setLinename(String linename) {
		this.linename = linename;
	}


	public String getContactid() {
		return contactid;
	}


	public void setContactid(String contactid) {
		this.contactid = contactid;
	}


	public String getCommhis() {
		return commhis;
	}


	public void setCommhis(String commhis) {
		this.commhis = commhis;
	}


	public String getCreated_at() {
		return created_at;
	}


	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}


	public String getUpdated_at() {
		return updated_at;
	}


	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}


	public String getLoopback() {
		return loopback;
	}


	public void setLoopback(String loopback) {
		this.loopback = loopback;
	}


	public String getTransinfo() {
		return transinfo;
	}


	public void setTransinfo(String transinfo) {
		this.transinfo = transinfo;
	}


	public static long getSerialversion() {
		return serialVersion;
	}


	@Override
	public String toString() {
		return "ApnInfo [id=" + id + ", apn=" + apn + ", alias=" + alias
				+ ", status=" + status + ", city=" + city + ", username="
				+ username + ", industry=" + industry + ", ggsnid=" + ggsnid
				+ ", protocol=" + protocol + ", vpnid=" + vpnid
				+ ", interfaceipbegin=" + interfaceipbegin
				+ ", interfaceipend=" + interfaceipend + ", ippooltype="
				+ ippooltype + ", ippool=" + ippool + ", routeid=" + routeid
				+ ", routeif=" + routeif + ", routevlan=" + routevlan
				+ ", linename=" + linename + ", contactid=" + contactid
				+ ", commhis=" + commhis + ", created_at=" + created_at
				+ ", updated_at=" + updated_at + ", loopback=" + loopback
				+ ", transinfo=" + transinfo + "]";
	}


	
	

}
