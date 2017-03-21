package cn.com.qytx.hotline.ivr.action;

import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.qytx.hotline.ivr.domain.Msiuser;
import cn.com.qytx.hotline.ivr.domain.SeatMonitor;
import cn.com.qytx.hotline.ivr.service.IMsiUser;
import cn.com.qytx.monitor.client.log.MonitorLogger;
import cn.com.qytx.monitor.client.logImpl.Log4jImpl;
import cn.com.qytx.platform.base.action.BaseActionSupport;
import cn.com.qytx.platform.org.domain.GroupInfo;
import cn.com.qytx.platform.org.domain.UserInfo;
import cn.com.qytx.platform.org.service.IGroup;

import com.google.gson.Gson;

/**
 * 功能:坐席监控action
 * 版本: 1.0
 * 开发人员: 张东领
 * 创建日期: 2015-2-15
 * 修改日期: 2015-2-15
 * 修改列表:
 */
public class SeatMonitorAction extends BaseActionSupport {

	private static final long serialVersionUID = 1L;
	/**
	 * log4j日志对象
	 */
	 private final static MonitorLogger logger =new Log4jImpl(MsiCalllogAction.class);
	private static final String NAME = "name";//姓名
	private static final String LOGINTIMESTR = "loginTimeStr";//登录时间
	private static final String HHMM = "HH:mm";//时间格式
	private static final String ISFORKGROUP = "isForkGroup";//权限控制

	/**
	 * 话务员登记
	 */
	@Autowired
	private transient IMsiUser msiuserService;
	/**
	 * 部门接口
	 */
	@Resource
	private IGroup groupService;
	/**
	 * 统计范围 范围0表示全部 1表示自己所在班
	 */
	private Integer scope;
	/**
	 * 权限控制
	 */
	private String isForkGroup;

	/**
	 * 所属地市的map
	 */
	private Map<Integer, String> gMap = new HashMap<Integer, String>();
	/**
	 * 来源端（back后台，seat坐席端）
	 */
	private String fromPage;

	/**
	 * 功能：查询坐席监控情况
	 */
	public void getSeatMonitor() {
		PrintWriter out = null;
		try {
			out = this.getResponse().getWriter();
			UserInfo userInfo = getLoginUser();
			SeatMonitor sm = msiuserService.getSeatMonitor(userInfo, scope);
			Map<String, Object> map = new HashMap<String, Object>();
			if (sm != null) {
				//地市坐席监控的map
				map = getMapForNoArea(sm);
			}
			Gson json = new Gson();
			String jsons = json.toJson(map);
			out.print(jsons);
		} catch (Exception ex) {
			logger.error("getSeatMonitor error ", ex);
		} finally {
			if (null != out) {
				out.close();
			}
		}
	}

	/**
	 * 功能：地市坐席监控的map
	 * 
	 * @param sm
	 * @return map
	 */
	public Map<String, Object> getMapForNoArea(SeatMonitor sm) {
		// 调用坐席监控实体类将对象转为map方法
		Map<String, Object> map = sm.seatMonitorToMap();
		List<Map<String, Object>> mapInsideUserList = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> mapOutsideUserList = new ArrayList<Map<String, Object>>();
		//内线坐席列表
		List<Msiuser> insideUserList = sm.getInsideUserList();
		if (insideUserList != null && !insideUserList.isEmpty()) {
			for (Msiuser m : insideUserList) {
				// 调用Msiuser实体类将对象转为map方法
				Map<String, Object> mapInsideUser = m.msiuserToMap();
				mapInsideUser.put(LOGINTIMESTR,
						getTimeStrByTimestamp(m.getLoginTime(), HHMM));// 最后登陆时间
				mapInsideUserList.add(mapInsideUser);
			}
		}
		map.put("insideUserList", mapInsideUserList);// 内线坐席列表
		//外线坐席列表
		List<Msiuser> outsideUserList = sm.getOutsideUserList();
		if (outsideUserList != null && !outsideUserList.isEmpty()) {
			for (Msiuser m : outsideUserList) {
				// 调用Msiuser实体类将对象转为map方法
				Map<String, Object> mapOutsideUser = m.msiuserToMap();
				mapOutsideUser.put(LOGINTIMESTR,
						getTimeStrByTimestamp(m.getLoginTime(), HHMM));// 最后登陆时间
				mapOutsideUserList.add(mapOutsideUser);
			}
		}
		map.put("outsideUserList", mapOutsideUserList);// 外线坐席列表
		return map;
	}

	/**
	 * 功能：分地市查询坐席监控情况
	 */
	public void getSeatMonitorForArea() {
		PrintWriter out = null;
		try {
			out = this.getResponse().getWriter();
			UserInfo userInfo = getLoginUser();
			SeatMonitor sm = msiuserService.getSeatMonitor(userInfo, scope);
			Map<String, Object> map = new HashMap<String, Object>();
			// 内线区域
			List<Map<String, Object>> mapInsideAreaList = new ArrayList<Map<String, Object>>();
			//查询当前在线坐席的分组(0内线)
			List<Object> insideAreas = msiuserService
					.findMsiUserGruops(0, null);
			if (insideAreas.size() > 0) {
				for (Object inGroup : insideAreas) {
					Map<String, Object> mapInsideArea = new HashMap<String, Object>();
					GroupInfo inTemp = groupService.findOne((Integer) inGroup);
					if (inTemp != null) {
						mapInsideArea.put("value", inTemp.getGroupId());
						mapInsideArea.put(NAME, inTemp.getGroupName());

						mapInsideAreaList.add(mapInsideArea);
					}
				}
			}
			// 外线区域
			List<Map<String, Object>> mapOutsideAreaList = new ArrayList<Map<String, Object>>();
			//查询当前在线坐席的分组(1外线)
			List<Object> outsideAreas = msiuserService.findMsiUserGruops(1,
					null);
			if (outsideAreas.size() > 0) {
				for (Object outGroup : outsideAreas) {
					Map<String, Object> mapOutsideArea = new HashMap<String, Object>();
					GroupInfo outTemp = groupService
							.findOne((Integer) outGroup);
					if (outTemp != null) {
						mapOutsideArea.put("value", outTemp.getGroupId());
						mapOutsideArea.put(NAME, outTemp.getGroupName());
						mapOutsideAreaList.add(mapOutsideArea);
					}
				}
			}
			if (sm != null) {
				map = getMapForArea(sm, mapInsideAreaList, mapOutsideAreaList);
			}
			Gson json = new Gson();
			String jsons = json.toJson(map);
			out.print(jsons);
		} catch (Exception ex) {
			logger.error("getSeatMonitor error ", ex);
		} finally {
			if (null != out) {
				out.close();
			}
		}
	}

	/**
	 * 功能：得到区域坐席map
	 * @param sm:坐席监控实体类
	 * @param mapInsideAreaList :内线所述的区域集合
	 * @param mapOutsideAreaList:外线所述的区域集合
	 * @return
	 */
	public Map<String, Object> getMapForArea(SeatMonitor sm,
			List<Map<String, Object>> mapInsideAreaList,
			List<Map<String, Object>> mapOutsideAreaList) {
		UserInfo userInfo = getLoginUser();
		// 调用坐席监控实体类将对象转为map方法
		Map<String, Object> map = sm.seatMonitorToMap();
		List<Map<String, Object>> mapInsideUserList = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> mapOutsideUserList = new ArrayList<Map<String, Object>>();
		// 登陆人员的isForkGroup
		map.put(ISFORKGROUP, userInfo.getIsForkGroup());
		// 内线 外线所述的区域集合
		map.put("insideAreaList", mapInsideAreaList);
		map.put("outsideAreaList", mapOutsideAreaList);

		List<Msiuser> insideUserList = sm.getInsideUserList();
		if (insideUserList != null && !insideUserList.isEmpty()) {
			for (Msiuser m : insideUserList) {
				// 调用Msiuser实体类将对象转为map方法
				Map<String, Object> mapInsideUser = m.msiuserToMap();
				mapInsideUser.put(LOGINTIMESTR,
						getTimeStrByTimestamp(m.getLoginTime(), HHMM));
				mapInsideUser.put(ISFORKGROUP, m.getIsForkGroup());
				mapInsideUserList.add(mapInsideUser);
			}
		}
		map.put("insideUserList", mapInsideUserList);

		List<Msiuser> outsideUserList = sm.getOutsideUserList();
		if (outsideUserList != null && !outsideUserList.isEmpty()) {
			for (Msiuser m : outsideUserList) {
				// 调用Msiuser实体类将对象转为map方法
				Map<String, Object> mapOutsideUser = m.msiuserToMap();
				mapOutsideUser.put(LOGINTIMESTR,
						getTimeStrByTimestamp(m.getLoginTime(), HHMM));
				mapOutsideUser.put(ISFORKGROUP, m.getIsForkGroup());
				mapOutsideUserList.add(mapOutsideUser);
			}
		}
		map.put("outsideUserList", mapOutsideUserList);

		return map;
	}

	/**
	 * 跳转到坐席监控列表页面
	 * 
	 * @return
	 */
	public String toMonitorList() {
		try {
			UserInfo userInfo = getLoginUser();
			if (userInfo != null && userInfo.getIsForkGroup() != null) {
				//获取一级部门列表
				List<GroupInfo> gList = groupService.findGroupListByGrade(0);
				Integer group = 0;
				String name = "";
				if (gList != null) {
					isForkGroup = getLoginUser().getIsForkGroup().toString();
					for (GroupInfo g : gList) {
						group = g.getIsForkGroup();
						name = StringUtils.defaultString(g.getGroupName(), "");
						gMap.put(group, name);
					}
					return fromPage;
				}
			}
		} catch (Exception e) {
			logger.error("toMonitorList error", e);
		}
		return null;
	}

	/**
	 * 将 Timestamp 按指定字符串格式进行格式化。
	 * 
	 * @param timestamp
	 *            将要转换的时间戳对象
	 * @param timeFormatStr
	 *            将要转换成的时间格式。如："yyyy-MM-dd HH:mm:ss"
	 * @return 参数为空时，返回空字符串
	 */
	private static String getTimeStrByTimestamp(Timestamp timestamp,
			String timeFormatStr) {
		if (timestamp == null || timeFormatStr == null) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(timeFormatStr);
		long time = timestamp.getTime();
		Date date = new Date(time);
		return sdf.format(date);
	}

	public Integer getScope() {
		return scope;
	}

	public void setScope(Integer scope) {
		this.scope = scope;
	}

	public String getIsForkGroup() {
		return isForkGroup;
	}

	public void setIsForkGroup(String isForkGroup) {
		this.isForkGroup = isForkGroup;
	}

	public Map<Integer, String> getgMap() {
		return gMap;
	}

	public void setgMap(Map<Integer, String> gMap) {
		this.gMap = gMap;
	}

	public String getFromPage() {
		return fromPage;
	}

	public void setFromPage(String fromPage) {
		this.fromPage = fromPage;
	}
}
