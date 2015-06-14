package com.parser.cases;

import java.util.HashMap;
import java.util.Set;

/**
 *
 * @author xp006871
 * This Class is used to parse the test account part of the configure file
 * It corresponding to <common-data></common-data> part.
 */
public class CommonDataCFG {

	private String strName;
	private HashMap<String, String> paraMetersHashMap = null;

	public CommonDataCFG(HashMap<String, String> paraMeHashMap) {
		this.paraMetersHashMap = paraMeHashMap;
	}

	public String getStrTCNameString() {
		return strName;
	}

	public void setStrTCNameString(String strTCNameString) {
		this.strName = strTCNameString;
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
		strBF.append("TC_Name:" + strName + "\n");
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