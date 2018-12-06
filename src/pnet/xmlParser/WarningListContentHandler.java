package pnet.xmlParser;

import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import pnet.model.WarningInfo;

public class WarningListContentHandler extends DefaultHandler{
	private List<WarningInfo> warnInfos = null;
	public List<WarningInfo> getInfos() {
		return warnInfos;
	}

	public WarningListContentHandler(List<WarningInfo> infos) {
		super();
		this.warnInfos = infos;
	}

	public void setInfos(List<WarningInfo> infos) {
		this.warnInfos = infos;
	}

	private WarningInfo warnInfo = null;
	private  String tagName = null;
	
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		// TODO Auto-generated method stub
		String temp = new String(ch,start,length);
		if (tagName.equals("Happentime")){
			warnInfo.setHappenTime(temp);
		}
		else if (tagName.equals("Netype")){
			warnInfo.setNeType(temp);
		}
		else if (tagName.equals("Severity")){
			warnInfo.setSeverity(temp);
				
		}
		else if (tagName.equals("Info")){
			warnInfo.setInfo(temp);
				
		}
		else if (tagName.equals("AddText")){
			warnInfo.setAddText(temp);
		}
		else if (tagName.equals("Code")){
			warnInfo.setCode(temp);
		}
		else if (tagName.equals("Reason")){
			warnInfo.setReason(temp);
		}
		else if (tagName.equals("Position")) {
			warnInfo.setPosition(temp);
		}
		super.characters(ch, start, length);
	}

	@Override
	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub
//		System.out.println("end document");
		super.endDocument();
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// TODO Auto-generated method stub
//		System.out.println("end qName-->" + qName);
//		System.out.println("end localName-->" + localName);
		if (localName.startsWith("alarm")){
			warnInfos.add(warnInfo);
			
//			System.out.println("WarningInfo---->" + WarningInfo);
		}
		tagName = "";
		
		super.endElement(uri, localName, qName);
	}

	@Override
	public void startDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.startDocument();
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		// TODO Auto-generated method stub
		this.tagName = localName;
//		System.out.println("startElement localName--->" + localName);
//		System.out.println("startElement qlName--->" + qName);
		
		if (tagName.startsWith("alarm")){
			warnInfo = new WarningInfo();
		}
		super.startElement(uri, localName, qName, attributes);
	}

}
