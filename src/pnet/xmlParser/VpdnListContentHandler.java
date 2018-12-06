package pnet.xmlParser;

import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.util.Log;

import pnet.model.ApnInfo;

public class VpdnListContentHandler extends DefaultHandler {
	private List<ApnInfo> infos = null;

	public List<ApnInfo> getInfos() {
		return infos;
	}

	public VpdnListContentHandler(List<ApnInfo> infos) {
		super();
		this.infos = infos;
	}

	public void setInfos(List<ApnInfo> infos) {
		this.infos = infos;
	}

	private ApnInfo apnInfo = null;
	private String tagName = null;

	//private String temp = "";
	private StringBuffer temp =new StringBuffer();
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		//Èç¹û·Ç¿Õ
		Log.e("attribute1", ""+tagName);
		
		 {
			
			//temp4charaters = new String (ch, start, length);
			// temp = temp + new String(ch, start, length);
			 temp.append(ch, start, length);
			if (tagName.equals("commhis"))
				temp.append('\n');
		}
					
		
			
			
		super.characters(ch, start, length);
	}

	@Override
	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub
		System.out.println("end document");
		// System.out.println("end document apninfos"+apnInfo.toString());
		super.endDocument();
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		
		if (temp!=null){
			
			System.out.println("temp-->"+temp);
		}
		
		if (tagName.equals("id")) {
			apnInfo.setId(temp.toString());
		} else if (tagName.equals("apn")) {
			apnInfo.setApn(temp.toString());
		} else if (tagName.equals("alias")) {
			apnInfo.setAlias(temp.toString());

		} else if (tagName.equals("status")) {
			apnInfo.setStatus(temp.toString());

		} else if (tagName.equals("city")) {
			apnInfo.setCity(temp.toString());
			
		} else if (tagName.equals("username")) {
			apnInfo.setUsername(temp.toString());
			
		} else if (tagName.equals("industry")){
			apnInfo.setIndustry(temp.toString());
		} else if (tagName.equals("ggsnid")){
			apnInfo.setGgsnid(temp.toString());
			
		} else if (tagName.equals("protocol")){
				apnInfo.setProtocol(temp.toString());
		}else if (tagName.equals("vpnid")){
			apnInfo.setVpnid(temp.toString());
		}else if (tagName.equals("interfaceipbegin")){
			apnInfo.setInterfaceipbegin(temp.toString());
		}else if (tagName.equals("interfaceipend")){
			apnInfo.setInterfaceipend(temp.toString());
		}else if (tagName.equals("ippooltype")) {
			apnInfo.setIppooltype(temp.toString());
		}else if (tagName.equals("ippool")) {
			apnInfo.setIppool(temp.toString());
		}else if (tagName.equals("routeid")) {
			apnInfo.setRouteid(temp.toString());
		}else if (tagName.equals("routeif")) {
			apnInfo.setRouteif(temp.toString());
		}else if (tagName.equals("routevlan")) {
			apnInfo.setRoutevlan(temp.toString());
		}else if (tagName.equals("linename")) {
			apnInfo.setLinename(temp.toString());
		}else if (tagName.equals("contactid")) {
			apnInfo.setContactid(temp.toString());
		}else if (tagName.equals("commhis")) {
			apnInfo.setCommhis(temp.toString());
		}else if (tagName.equals("created_at")) {
			apnInfo.setCreated_at(temp.toString());
		}else if (tagName.equals("updated_at")) {
			apnInfo.setUpdated_at(temp.toString());
		}else if (tagName.equals("loopback")) {
			apnInfo.setLoopback(temp.toString());
		}else if (tagName.equals("transinfo")) {
			apnInfo.setTransinfo(temp.toString());
		}

		tagName = "";
		
		
		if (localName.equals("nil-classes"))
			infos = null;
		System.out.println("endElement qName-->" + qName);
		System.out.println("endElement localName-->" + localName);
		if (localName.equals("apnuser")) {
			infos.add(apnInfo);

			System.out.println("ApnInfo---->" + apnInfo);
		}
		tagName = "";
		//temp = "";
		// tagName = "";
		// attributeValue2 = "";
		// attributeName2 = "";
		System.out.println("-------------------------------------------");
		super.endElement(uri, localName, qName);
	}

	@Override
	public void startDocument() throws SAXException {

		System.out.println("startDocument");
		
		super.startDocument();
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		
		 temp.delete(0,temp.length());
		this.tagName = localName;
		if (tagName.equals("apnuser")) {
			apnInfo = new ApnInfo();
			Log.i("create a apnInfo", "created!");
		}
		super.startElement(uri, localName, qName, attributes);
	}

}
