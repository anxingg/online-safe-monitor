package cn.com.qytx.oa.news.util;

import javax.servlet.http.HttpServletRequest;

public class ServletUtils {
	/**
	 * 得到int型参数从request里
	 * */
	public static int getIntFromReq(HttpServletRequest request, String param) {
		int rt = 0;
		String p = request.getParameter(param);
		try {
			return Integer.parseInt(p);
		} catch (NumberFormatException e) {
			rt = 0;
		}
		
		return rt;
	}
	
	/**
	 * 得到Str型参数从request里
	 * */
	public static String getStrFromReq(HttpServletRequest request, String param) {
		String p = request.getParameter(param);

		return (p == null) ? "" : p;
	}
}
