package com.parser.cases;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.util.Xml;

public class ParseXMLinPull {

	private XmlPullParser parser;
	private WholeCFG cfgWholeCfg;
	private TestcaseCFG strTCCfg;
	private CommonDataCFG commonData;

	private HashMap<String, String> configParas;
	private HashMap<String, String> paraMeters;
	private ArrayList<TestcaseCFG> arrayList;

	public ArrayList<TestcaseCFG> getArrayList() {
		return arrayList;
	}

	public void setArrayList(ArrayList<TestcaseCFG> arrayList) {
		this.arrayList = arrayList;
	}

	public WholeCFG getCfgWholeCfg() {
		return cfgWholeCfg;
	}

	public void setCfgWholeCfg(WholeCFG cfgWholeCfg) {
		this.cfgWholeCfg = cfgWholeCfg;
	}

	public CommonDataCFG getCommonData() {
		return commonData;
	}

	public void setCommonData(CommonDataCFG commonData) {
		this.commonData = commonData;
	}

	public ParseXMLinPull(InputStream xmlInputStream)
			throws XmlPullParserException {
		parser = XmlPullParserFactory.newInstance().newPullParser();
		parser.setInput(xmlInputStream, "UTF-8");
	}

	public ParseXMLinPull(Reader reader)
			throws XmlPullParserException {
		parser = XmlPullParserFactory.newInstance().newPullParser();
		parser.setInput(reader);
	}
	public void paresXML() throws XmlPullParserException, IOException {
		int eventType = parser.getEventType();
		while (eventType != XmlPullParser.END_DOCUMENT) {
			switch (eventType) {
			case XmlPullParser.START_DOCUMENT:
				configParas = new HashMap<String, String>();
				cfgWholeCfg = new WholeCFG(configParas);
				arrayList = new ArrayList<TestcaseCFG>();
				break;
			case XmlPullParser.START_TAG:
				String tagNameString = parser.getName();
				if ("Config".equals(tagNameString)) {
					// handle Config tag level
					int intAtts = parser.getAttributeCount();
					for (int i = 0; i < intAtts; i++) {
						configParas.put(parser.getAttributeName(i), parser.getAttributeValue(i));
					}
				} else if ("common-data".equals(tagNameString)) {
					paraMeters = new HashMap<String, String>();
					commonData = new CommonDataCFG(paraMeters);
					commonData.setStrTCNameString(parser.getAttributeValue(0));
				} else if ("testcase".equals(tagNameString)) {
					paraMeters = new HashMap<String, String>();
					strTCCfg = new TestcaseCFG(paraMeters);
					strTCCfg.setStrTCNameString(parser.getAttributeValue(0));
				}
				else {
					paraMeters.put(parser.getName(), parser.nextText());
				}
				break;
			case XmlPullParser.END_TAG:
				if ("common-data".equals(parser.getName())) {
					this.setCommonData(commonData);
					paraMeters = null;
				} else if ("testcase".equals(parser.getName())) {
					arrayList.add(strTCCfg);
					strTCCfg = null;
					paraMeters = null;
				} else if ("Config".equals(parser.getName())) {
					this.setCfgWholeCfg(cfgWholeCfg);
				}
				break;
			default:
				break;
			}

			eventType = parser.next();
			if(eventType == XmlPullParser.END_DOCUMENT)
			{
				this.setArrayList(arrayList);
			}

		}
		// eventType=parser.next();
	}

	public XmlPullParser getParser(InputStream xml)
			throws XmlPullParserException {
		// return (parser = Xml.newPullParser()).setInput(xml, "UTF-8");
		parser = Xml.newPullParser();
		parser.setInput(xml, "UTF-8");
		return parser;
	}


}
