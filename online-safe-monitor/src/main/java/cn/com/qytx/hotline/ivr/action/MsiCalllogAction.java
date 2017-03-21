package cn.com.qytx.hotline.ivr.action;

import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.qytx.cbb.org.util.ExportExcel;
import cn.com.qytx.hotline.customercall.domain.CclMsiLog;
import cn.com.qytx.hotline.customercall.service.ICustomerCallLog;
import cn.com.qytx.hotline.ivr.domain.CallCenterConst;
import cn.com.qytx.hotline.ivr.domain.MsiWorkload;
import cn.com.qytx.hotline.ivr.domain.MsicallLog;
import cn.com.qytx.hotline.ivr.domain.Msiservice;
import cn.com.qytx.hotline.ivr.domain.Msiuser;
import cn.com.qytx.hotline.ivr.service.IMsiUser;
import cn.com.qytx.hotline.ivr.service.IMsiWorkload;
import cn.com.qytx.hotline.ivr.service.IMsicallLog;
import cn.com.qytx.hotline.ivr.service.IMsiservice;
import cn.com.qytx.monitor.client.log.MonitorLogger;
import cn.com.qytx.monitor.client.logImpl.Log4jImpl;
import cn.com.qytx.platform.base.action.BaseActionSupport;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Sort;
import cn.com.qytx.platform.base.query.Sort.Direction;
import cn.com.qytx.platform.org.domain.GroupInfo;
import cn.com.qytx.platform.org.domain.UserInfo;
import cn.com.qytx.platform.org.service.IGroup;
import cn.com.qytx.platform.utils.datetime.DateTimeUtil;

import com.google.gson.Gson;

/**
 * 功能: 与通话记录有关的action 版本: 1.0 开发人员: 李华伟 创建日期: 2014-1-10 修改日期: 2014-1-10 修改列表:
 */
public class MsiCalllogAction extends BaseActionSupport {
	/**
	 * 描述含义
	 */
	private static final long serialVersionUID = 4167059369417012109L;
	private static final String WORKNO = "workNo";
	private static final String VID = "vid";
	private static final String STATETIME = "stateTime";
	private static final String HHMM = "HH:mm";
	private static final String EXCELCONTENTTYPE = "application/vnd.ms-excel";
	private static final String ISFORKGROUP = "isForkGroup";
	private static final String NUM = "num";
	private static final String STATESTR = "stateStr";
	private static final String CHARSET = "iso8859-1";
	private static final String CD = "Content-Disposition";
	private static final String AF = "attachment;filename=";
	private static final String CBLERROR = "getCallBlockList error : ";
	private static final String CLERROR = "chatList error. ";

	/**
	 * log4j日志对象
	 */
	 private final static MonitorLogger logger =new Log4jImpl(MsiCalllogAction.class);
	/**
	 * 通话记录
	 */
	private CclMsiLog cclMsiLog;
	/**
	 * 工单服务
	 */
	@Autowired
	private transient ICustomerCallLog customerCallLogService;
	/**
	 * 通话记录服务
	 */
	@Autowired
	private transient IMsicallLog msiCallLogService;
	/**
	 * 部门/群组
	 */
	@Resource
	private IGroup groupService;
	/**
	 * 话务员登记
	 */
	@Autowired
	private transient IMsiUser msiuserService;
	/**
	 * 队列
	 */
	@Autowired
	private transient IMsiservice msiservice;
	/**
	 * 坐席工作状态统计
	 */
	@Autowired
	private transient IMsiWorkload msiWorkloadService;
	/**
	 * 统计范围 范围0表示全部 1表示自己所在班
	 */
	private Integer scope;
	/**
	 * 查询开始时间
	 */
	private Timestamp beginTime;
	/**
	 * 查询开始时间str
	 */
	private String beginTimeStr;
	/**
	 * 查询结束时间
	 */
	private Timestamp endTime;
	/**
	 * 查询结束时间str
	 */
	private String endTimeStr;
	/**
	 * 呼叫队列
	 */
	private Integer queueId;
	/**
	 * 关键字
	 */
	private String searchkey;
	/**
	 * msiuserIDs
	 */
	private String msiUserIds;
	/**
	 * 坐席ids
	 */
	private String msiids;
	/**
	 * 一级部门id
	 */
	private Integer groupId;
	/**
	 * 所属地市的map
	 */
	private Map<Integer, String> gMap = new HashMap<Integer, String>();
	/**
	 * 权限控制
	 */
	private String isForkGroup;

	/**
	 * 功能：保存通话记录
	 */
	public void saveCclMsiLog() {
		Msiuser seatUser = (Msiuser) getSession().getAttribute("seatUser");
		cclMsiLog.setMsiUserId(seatUser.getVid());
		customerCallLogService.saveCclMsiLog(cclMsiLog);
		ajax(1);
	}
	/**
	 * 坐席监控的列表页面
	 */
	public String getMsiUserByPage() {
		String group = this.getRequest().getParameter(ISFORKGROUP);
		Integer isForkGroup=getLoginUser().getIsForkGroup();
		if (StringUtils.isNotEmpty(group)) {
			isForkGroup = Integer.parseInt(group);
		}
		try {
			Page<Msiuser> pageInfo = msiuserService.monitorMisuserByPage(
					getPageable(new Sort(Direction.DESC, "loginTime")),
					isForkGroup, searchkey);
			List<Msiuser> list = pageInfo.getContent();
			List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
			if (list != null) {
				mapList = parseList(list);
			}
			this.ajaxPage(pageInfo, mapList);
		} catch (Exception e) {
			logger.error("getMsiUserByPage error", e);
		}
		return null;
	}

	public List<Map<String, Object>> parseList(List<Msiuser> list) {
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		int i = getPageable().getPageNumber() * getPageable().getPageSize() + 1;
		for (Msiuser mu : list) {
			//调用Msiuser实体类将对象转为map方法
			Map<String, Object> map = mu.msiuserToMap();
			map.put(NUM, i);
			i++;
			map.put(ISFORKGROUP, mu.getIsForkGroup());
			GroupInfo outTemp = groupService.findOne(mu.getIsForkGroup());
			map.put("isForkGroupName", outTemp.getGroupName());
			Integer state = mu.getState();
			setMapForState(map, state);
			// 保持时间
			MsiWorkload workLoad = msiWorkloadService.getMsiWorkload(
					mu.getVid(), state);
			if (workLoad != null) {
				Timestamp beginTime = workLoad.getBeginTime();
				Timestamp now = new Timestamp(System.currentTimeMillis());
				Long time = (now.getTime() - beginTime.getTime()) / (1000 * 60);
				String st = time.toString();
				if (st != null && !"".equals(st)) {
					Integer stateTime = Integer.parseInt(st);
					if (stateTime < 0) {
						stateTime = 0;
					}
					mu.setStateTime(stateTime);
					map.put(STATETIME, stateTime + "分钟");
				} else {
					mu.setStateTime(0);
					map.put(STATETIME, 0 + "分钟");
				}
			} else {
				mu.setStateTime(0);
				map.put(STATETIME, 0 + "分钟");
			}
			// 签入时间
			map.put("loginTimeStr",DateTimeUtil.timestampToString(mu.getLoginTime(), HHMM));
			mapList.add(map);
		}
		return mapList;
	}

	/**
	 * 功能：坐席状态 1：在线 3：呼入通话 4：置闲 5：置忙 13：外呼通话
	 * @param map
	 * @param state
	 */
	public void setMapForState(Map<String, Object> map, Integer state) {
		if (state == 1) {
			map.put(STATESTR, "在线");
		} else if (state == 3) {
			map.put(STATESTR, "接听");
		} else if (state == 4) {
			map.put(STATESTR, "置闲");
		} else if (state == 5) {
			map.put(STATESTR, "置忙");
		} else if (state == 13) {
			map.put(STATESTR, "外呼");
		}
	}

	/**
	 * 明细查询－呼损明细列表
	 */
	public void getCallBlockList() {
		try {
			Page<MsicallLog> pageInfo = msiCallLogService.findCallBlockList(
					getPageable(new Sort(Direction.DESC, VID)), beginTime,
					endTime, queueId, searchkey);
			List<MsicallLog> list = pageInfo.getContent();
			List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
			if (list != null) {
				mapList = analyzeCallBlockResult(list);
			}
			this.ajaxPage(pageInfo, mapList);
		} catch (Exception e) {
			logger.error(CBLERROR, e);
		}
	}

	public String findMsiUserByUserIds() {
		List<Msiuser> msiuserList = msiuserService.findMsiUserListByIds(msiUserIds);
		Gson gosn = new Gson();
		String userListStr = gosn.toJson(msiuserList);
		ajax(userListStr);
		return null;
	}

	private List<Map<String, Object>> analyzeCallBlockResult(
			List<MsicallLog> list) {
		// 把工单信息填充到map里面
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		List<Msiservice> msiQueueList = msiservice.findAll();
		List<Msiuser> msiUserList = msiuserService.findAllmisUserList();
		int i = getPageable().getPageNumber() * getPageable().getPageSize() + 1;
		for (MsicallLog ml : list) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("orderNumber", i);
			map.put(VID, ml.getVid());
			// 主叫号码
			map.put("call", ml.getCall());
			// 被叫号码
			map.put("called", ml.getCalled());
			// 呼入时间
			Timestamp callInTime = ml.getCallInTime();
			map.put("callInTime", DateTimeUtil.timestampToString(callInTime,
					CallCenterConst.TIME_FORMAT_STANDARD_STR));
			// 振铃时间
			Timestamp talkInTime = ml.getTalkInTime();
			map.put("talkInTime", DateTimeUtil.timestampToString(talkInTime,
					CallCenterConst.TIME_FORMAT_STANDARD_STR));
			// 振铃时长 （秒）
			map.put("linkCalled", ml.getLinkCalled());
			// 队列
			String queue = getQueueName(msiQueueList, ml.getServiceId());
			map.put("queue", queue);

			// 坐席工号
			String workNo = getWorkNoById(msiUserList, ml.getMsiuserId());
			map.put(WORKNO, workNo);

			// 呼叫原因
			String endtype = getEndtypeStr(ml.getEndType());
			map.put("endtype", endtype);

			Long callEndLong = talkInTime.getTime()
					+ Long.valueOf(ml.getLinkCalled()) * 1000;
			Timestamp callEndTime = new Timestamp(callEndLong);
			// 挂断时间
			if (ml.getCallEndTime() != null) {
				map.put("callEndTime", DateTimeUtil.timestampToString(
						callEndTime, CallCenterConst.TIME_FORMAT_STANDARD_STR));
			} else {
				map.put("callEndTime", '-');
			}
			mapList.add(map);
			i++;
		}
		return mapList;
	}

	/**
	 * 功能：导出呼损统计情况
	 */
	public void exportCallBlockList() {
		HttpServletResponse response = this.getResponse();
		response.setContentType(EXCELCONTENTTYPE);

		OutputStream outp = null;
		try {
			this.setIDisplayStart(0);
			this.setIDisplayLength(999999);
			Page<MsicallLog> pageInfo = msiCallLogService.findCallBlockList(
					getPageable(new Sort(Direction.DESC, VID)), beginTime,
					endTime, queueId, searchkey);
			/** 得到结果 */
			List<MsicallLog> msicallLogList = pageInfo.getContent();
			response.addHeader(CD, AF
					+ new String(("呼损清单.xls").getBytes(), CHARSET));// 解决中文
			// 文件名问题
			outp = response.getOutputStream();
			List<Map<String, Object>> mapList = analyzeCallBlockResult(msicallLogList);
			ExportExcel exportExcel = new ExportExcel(outp,
					getCallBlockHeadList(), mapList, getCallBlockKeyList());

			exportExcel.export();

		} catch (Exception e) {
			logger.error(CLERROR, e);
		}
	}

	private List<String> getCallBlockHeadList() {
		List<String> headList = new ArrayList<String>();
		headList.add("序号");
		headList.add("主叫号码");
		headList.add("被叫号码");
		headList.add("呼入时间");
		headList.add("振铃时间");
		headList.add("振铃时长 （秒）");
		headList.add("挂断时间");
		headList.add("坐席工号");
		headList.add("呼损原因");
		return headList;
	}

	private List<String> getCallBlockKeyList() {
		List<String> headList = new ArrayList<String>();
		headList.add("orderNumber");
		headList.add("call");
		headList.add("called");
		headList.add("callInTime");
		headList.add("talkInTime");
		headList.add("linkCalled");
		headList.add("callEndTime");
		headList.add(WORKNO);
		headList.add("endtype");
		return headList;
	}

	// 获取队列名称
	private String getQueueName(List<Msiservice> msiQueueList, Integer serviceId) {
		if (null == msiQueueList || msiQueueList.isEmpty() || null == serviceId) {
			logger.error("getQueueName error : serviceId = " + serviceId);
			return "-";
		}
		for (Msiservice ms : msiQueueList) {
			if (ms.getVid().intValue() == serviceId) {
				// return ms.getContent();
				return ms.getServiceName();
			}
		}
		return "-";
	}

	// 获取坐席工号
	private String getWorkNoById(List<Msiuser> msiuserList, Integer msiuserId) {
		if (null == msiuserList || msiuserList.isEmpty() || null == msiuserId) {
			logger.error("getWorkNoById error : msiuserId = " + msiuserId);
			return "-";
		}
		for (Msiuser ms : msiuserList) {
			if (ms.getVid().intValue() == msiuserId) {
				return ms.getWorkNo();
			}
		}
		return "-";
	}

	// 获取呼损原因
	private String getEndtypeStr(Integer endtype) {
		if (null == endtype) {
			return "-";
		} else if (endtype == 0) {
			return "坐席拒接";
		} else if (endtype == 1) {
			return "用户挂断";
		} else if (endtype == 2) {
			return "振铃超时";
		} else {
			return "-";
		}
	}

	/**
	 * 功能：根据坐席id字符串获得队列信息及坐席信息
	 * 
	 * @param msiids
	 *            坐席ids
	 * @return
	 */
	public String getMsiUserByUserIds() {
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		List<Object[]> list = msiservice.findAllByMsiUserIds(msiids);
		if (list != null && !list.isEmpty()) {
			for (int i = 0; list.size() > i; i++) {
				Map<String, Object> map = new HashMap<String, Object>();
				if (list.get(i) != null && list.get(i).length >= 0) {
					if (list.get(i)[0] != null) {
						map.put("muId", list.get(i)[0]);
					}
					if (list.get(i)[1] != null) {
						map.put("muName", list.get(i)[1]);
					}
					if (list.get(i)[2] != null) {
						map.put("muWorkNo", list.get(i)[2]);
					}
					if (list.get(i)[3] != null) {
						map.put("msId", list.get(i)[3]);
					}
					if (list.get(i)[4] != null) {
						map.put("msServiceName", list.get(i)[4]);
					}
				}
				listMap.add(map);
			}
		}
		Gson gosn = new Gson();
		String userListStr = gosn.toJson(listMap);
		ajax(userListStr);
		return null;
	}

	/**
	 * 功能：根据坐席id字符串以及登录坐席所属区域获得队列信息及坐席信息
	 */
	public String getMisUserByUserIdAndIsFork() {
		UserInfo userInfo = getLoginUser();
		if (userInfo != null && userInfo.getIsForkGroup() != null) {
			List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
			List<Object[]> list = msiservice.findAllByIdsAndIsFork(msiids,
					userInfo.getIsForkGroup());
			if (list != null && !list.isEmpty()) {
				for (int i = 0; list.size() > i; i++) {
					Map<String, Object> map = new HashMap<String, Object>();
					if (list.get(i) != null && list.get(i).length >= 0) {
						if (list.get(i)[0] != null) {
							map.put("muId", list.get(i)[0]);
						}
						if (list.get(i)[1] != null) {
							map.put("muName", list.get(i)[1]);
						}
						if (list.get(i)[2] != null) {
							map.put("muWorkNo", list.get(i)[2]);
						}
						if (list.get(i)[3] != null) {
							map.put("msId", list.get(i)[3]);
						}
						if (list.get(i)[4] != null) {
							map.put("msServiceName", list.get(i)[4]);
						}
					}
					listMap.add(map);
				}
			}
			Gson gosn = new Gson();
			String userListStr = gosn.toJson(listMap);
			ajax(userListStr);
			}
		return null;
	}


	
	/**
	 * 功能：获得所属地市
	 * @return
	 */
	public List<GroupInfo> getGroupName() {
		try {
			List<GroupInfo> list;
			Map<String, Object> map = new HashMap<String, Object>();
			list = groupService.findGroupListByGrade(0);
			// 用于判断是否隐藏所属地市查询条件
			map.put("isForkGroup", getLoginUser().getIsForkGroup());
			map.put("list", list);
			ajax(map);
		} catch (Exception e) {
			LOGGER.error("getGroupName error.", e);
		}
		return null;
	}
	public Integer getScope() {
		return scope;
	}

	public void setScope(Integer scope) {
		this.scope = scope;
	}

	public Timestamp getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Timestamp beginTime) {
		this.beginTime = beginTime;
	}

	public String getBeginTimeStr() {
		return beginTimeStr;
	}

	public void setBeginTimeStr(String beginTimeStr) {
		if (!StringUtils.isEmpty(beginTimeStr) && beginTimeStr.length() == 10) {
			setBeginTime(DateTimeUtil.stringToTimestamp(beginTimeStr,
					CallCenterConst.DATE_FORMAT_STR));
		} else if (!StringUtils.isEmpty(beginTimeStr)
				&& beginTimeStr.length() == 16) {
			setBeginTime(DateTimeUtil.stringToTimestamp(beginTimeStr,
					CallCenterConst.TIME_FORMAT_STR_NOT_MIN));
		}
		this.beginTimeStr = beginTimeStr;
	}

	public Timestamp getEndTime() {
		return endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public String getEndTimeStr() {
		return endTimeStr;
	}

	public void setEndTimeStr(String endTimeStr) {
		if (!StringUtils.isEmpty(endTimeStr) && endTimeStr.length() == 10) {
			setEndTime(DateTimeUtil.stringToTimestamp(endTimeStr
					+ " 23:59:59.999", CallCenterConst.TIME_ALL_FORMAT_STR));
		} else if (!StringUtils.isEmpty(endTimeStr)
				&& endTimeStr.length() == 16) {
			setEndTime(DateTimeUtil.stringToTimestamp(endTimeStr + ":59.999",
					CallCenterConst.TIME_ALL_FORMAT_STR));
		}
		this.endTimeStr = endTimeStr;
	}

	public Integer getQueueId() {
		return queueId;
	}

	public void setQueueId(Integer queueId) {
		this.queueId = queueId;
	}

	public String getSearchkey() {
		return searchkey;
	}

	public void setSearchkey(String searchkey) {
		this.searchkey = searchkey;
	}

	public CclMsiLog getCclMsiLog() {
		return cclMsiLog;
	}

	public void setCclMsiLog(CclMsiLog cclMsiLog) {
		this.cclMsiLog = cclMsiLog;
	}

	public String getMsiUserIds() {
		return msiUserIds;
	}

	public void setMsiUserIds(String msiUserIds) {
		this.msiUserIds = msiUserIds;
	}

	public String getMsiids() {
		return msiids;
	}

	public void setMsiids(String msiids) {
		this.msiids = msiids;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public Map<Integer, String> getGMap() {
		return gMap;
	}

	public void setGMap(Map<Integer, String> gMap) {
		this.gMap = gMap;
	}

	public String getIsForkGroup() {
		return isForkGroup;
	}

	public void setIsForkGroup(String isForkGroup) {
		this.isForkGroup = isForkGroup;
	}
}
