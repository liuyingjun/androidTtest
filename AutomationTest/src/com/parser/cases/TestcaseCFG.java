package com.parser.cases;

import java.util.HashMap;
import java.util.Set;

/**
*
* @author xp006871
* This Class is used to parse the test case part of the configure file
* It corresponding to <testcase></testcase> part.
*/
public class TestcaseCFG {

	private String strTCNameString;
	private HashMap<String, String> paraMetersHashMap = null;

	public TestcaseCFG(HashMap<String, String> paraMeHashMap) {
		this.paraMetersHashMap = paraMeHashMap;
	}

	public String getStrTCNameString() {
		return strTCNameString;
	}

	public void setStrTCNameString(String strTCNameString) {
		this.strTCNameString = strTCNameString;
	}

	public void setParameter(String key, String value) {
		this.paraMetersHashMap.put(key, value);
	}

	public HashMap<String, String> getParameter() {
		return paraMetersHashMap;
	}

	public String returnString() {
		Set<String> keySet = paraMetersHashMap.keySet();
		StringBuffer strBF = new StringBuffer();
		strBF.append("TC_Name:" + strTCNameString + "\n");
		for (String str : keySet) {
			String valueString = str+":"+ paraMetersHashMap.get(str);
			strBF.append(valueString);
			strBF.append("\n");
		}

		return strBF.toString();
	}

	@Override
	public String toString() {
		return returnString();
	}
}