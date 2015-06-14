package com.parser.cases;

import java.util.HashMap;
import java.util.Set;

/**
 *
 * @author xp006871
 ** This Class is used to parse the config part of the configure file
* It corresponding to <Config></Config> part.
 */
public class WholeCFG { // mode="4" network="1" varient="Gina"
						// testtype="Reliability"
	private HashMap<String, String> configParas;

	public WholeCFG(HashMap<String, String> paras) {
		this.configParas = paras;
	}

	public HashMap<String, String> getParas() {
		return configParas;
	}

	public String returnToString() {
		Set<String> keySet = configParas.keySet();
		StringBuffer strBF = new StringBuffer();
		strBF.append("Config" + "\n");
		for (String str : keySet) {
			String valueString = str+":"+ configParas.get(str);
			strBF.append(valueString);
			strBF.append("\n");
		}

		return strBF.toString();
	}
	@Override
	public String toString() {
		return this.returnToString();
	}

}
