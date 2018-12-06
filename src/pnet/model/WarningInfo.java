package pnet.model;

import java.io.Serializable;

public class WarningInfo implements Serializable{
	private static final long serialVersion = 1L;
	private String happenTime;
	private String neType;
	private String severity;
	private String info;
	private String addText;
	private String code;
	private String reason;
	private String position;
	
	public WarningInfo() {
		// TODO 自动生成的构造函数存根
	}

	public String getHappenTime() {
		return happenTime;
	}

	public void setHappenTime(String happenTime) {
		this.happenTime = happenTime.trim();
	}

	public String getNeType() {
		return neType;
	}

	public void setNeType(String neType) {
		this.neType = neType.trim();
	}

	public String getSeverity() {
		return severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity.trim();
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info.trim();
	}

	public String getAddText() {
		return addText;
	}

	public void setAddText(String addText) {
		this.addText = addText.trim();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code.trim();
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason.trim();
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position.trim();
	}

	public static long getSerialversion() {
		return serialVersion;
	}

	@Override
	public String toString() {
		return "WarningInfo [happenTime=" + happenTime + ", neType=" + neType
				+ ", severity=" + severity + ", info=" + info + ", addText="
				+ addText + ", code=" + code + ", reason=" + reason
				+ ", position=" + position + "]";
	}



}














