package cn.com.qytx.workflow.service.impl;

import java.util.HashMap;

import org.jsoup.helper.StringUtil;

public class ComparableMap extends HashMap<String, String> implements Comparable<ComparableMap>{

	/**
	 * 描述含义
	 */
	private static final long serialVersionUID = 438199691565370218L;

	@Override
	public int compareTo(ComparableMap o) {
		// TODO Auto-generated method stub
		String index = o.get("actionIndex");
		String thsIndex = this.get("actionIndex");
		int indexInt = 0,thsIndexInt =0;
		if(!StringUtil.isBlank(index)){
			indexInt = Integer.parseInt(index);
		}
		if(!StringUtil.isBlank(thsIndex)){
			thsIndexInt = Integer.parseInt(thsIndex);
		}
		return thsIndexInt-indexInt;
	}

}
