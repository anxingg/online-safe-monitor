package cn.com.qytx.cbb.sso.server.cache;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


/**
 * 功能：SSO缓存实现,单例模式
 * 作者： jiayongqiang
 * 创建时间：2014年7月1日
 */
public final class SSOCache {
	
	//单例模式------------------------------------------------------
	
	private static SSOCache instance = null;
	
	private SSOCache(){
		
	}
	
	public static synchronized SSOCache getInstance(){
		if(instance == null){
			instance = new SSOCache();
		}
		return instance;
	}
	
	//-----------------------------------------------------------------------------
	
	//间隔30分钟如果没有访问则清空该token，使token失效
	private final Long CONFIG_TIME = 30*60*1000L;
//	private final Long CONFIG_TIME = 50*1000L;
	
	//token和用户的映射
	private  Map<String,Object> cacheUser = new HashMap<String, Object>();
	//token和刷新时间的映射
	Map<String,Long> cacheTime = new HashMap<String, Long>();
	
	
	/**
	 * 功能：保存对象
	 * 作者：jiayongqiang
	 * 参数：
	 * 输出：
	 */
	public void store(String token,Object obj){
		cacheUser.put(token, obj);
		cacheTime.put(token, Calendar.getInstance().getTimeInMillis());
	}
	
	/**
	 * 功能：获取对象
	 * 作者：jiayongqiang
	 * 参数：
	 * 输出：
	 */
	public Object read(String token){
		cacheTime.put(token, Calendar.getInstance().getTimeInMillis());
		new ReadCacheListener(token).eventChange(new ReadCacheEvent());
		return cacheUser.get(token);
	}
	
	/**
	 * 功能：清除缓存中失效的对象
	 * 作者：jiayongqiang
	 * 参数：
	 * 输出：
	 */
	public synchronized void clean(){
		Set<Map.Entry<String, Long>> set = cacheTime.entrySet();
		for(Map.Entry<String, Long> temp:set){
			String token = temp.getKey();
			Long l = temp.getValue();
			Long c = Calendar.getInstance().getTimeInMillis();
			//如果超时，则清除
			if(c-l>CONFIG_TIME){
				cacheUser.remove(token);
				cacheTime.remove(token);
			}
		}
	}
	
	/**
	 * 功能：销毁缓存
	 * 作者：jiayongqiang
	 * 参数：
	 * 输出：
	 */
	public void destory(){
		cacheUser.clear();
		cacheTime.clear();
	}

}
