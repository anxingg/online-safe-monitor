package cn.com.qytx.hotline.ivr.action;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;

import cn.com.qytx.hotline.customercall.domain.CustomerCallLog;
import cn.com.qytx.hotline.ivr.domain.MsicallLog;
import cn.com.qytx.hotline.ivr.domain.Msiuser;
import cn.com.qytx.hotline.ivr.service.IMsiUser;
import cn.com.qytx.hotline.ivr.service.IMsicallLog;
import cn.com.qytx.platform.base.action.BaseActionSupport;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Sort;
import cn.com.qytx.platform.base.query.Sort.Direction;
import cn.com.qytx.platform.org.domain.UserInfo;

import com.google.gson.Gson;

/**
 * 项目名称：wzerp 类名称：通话记录控制层 类描述： 创建人：WangBin 创建时间：2012-11-22
 * 
 * @version
 */
public class CallLogManagerAction extends BaseActionSupport {

	private static final long serialVersionUID = 1L;

	@Autowired
	private transient IMsicallLog msiCallLogService; // 通话记录

	@Autowired
	private transient IMsiUser msiuserService; // 话务员登记

	/** 分页开始条数 */
	private int iDisplayStart;

	/** 每页显示条数 */
	private int iDisplayLength;

	private String call; // 主叫号码
	private String callType; // 呼叫方式
	private String workNo;// 坐席工号
	private String begDate; // 开始时间
	private String endDate; // 结束时间
	private String cclSn; // 工单编号

	/**
	 * @Title:查询通话记录分页列表
	 * @Description:
	 * @param @return
	 * @return 返回类型
	 * @throws
	 */
	public String getCallLogList() {
		try {
			// 首先获取登录人基本信息
			UserInfo user = getLoginUser();

			if (iDisplayLength == 0) {
				iDisplayLength = 15;
			}

			String basePath = this.getRequest().getScheme() + "://"
					+ this.getRequest().getServerName() + ":"
					+ this.getRequest().getServerPort() + "/";

			int pageNum = (int) (Math.ceil((double) iDisplayStart
					/ (double) iDisplayLength)) + 1;
			Sort sort = new Sort(new Sort.Order(Direction.DESC, "callInTime"));
			Page<MsicallLog> pageInfo = msiCallLogService.findCallLogByPage(
					this.getPageable(sort), call, workNo, begDate, endDate,
					user, cclSn);
			List<MsicallLog> msicallLogList = pageInfo.getContent(); // 获取结果
			List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
			if (null != msicallLogList && msicallLogList.size() > 0) {
				mapList = parseList(pageNum, basePath, msicallLogList);
			}
			Map<String, Object> jsonMap = new HashMap<String, Object>();
			jsonMap.put("iTotalRecords", pageInfo.getTotalElements());
			jsonMap.put("iTotalDisplayRecords", pageInfo.getTotalElements());
			jsonMap.put("aaData", mapList);
			Gson json = new Gson();
			String jsons = json.toJson(jsonMap);
			PrintWriter writer = new PrintWriter(this.getResponse().getWriter());
			writer.print(jsons);
			writer.flush();
			writer.close();
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}

	private List<Map<String, Object>> parseList(int pageNum, String basePath,
			List<MsicallLog> msicallLogList) {
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		int i = (pageNum - 1) * iDisplayLength + 1;
		for (MsicallLog msicallLogBean : msicallLogList) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("no", i);
			map.put("id", msicallLogBean.getVid());
			// 主叫号码
			String call = msicallLogBean.getCall();

			map.put("call", call == null ? "-" : msicallLogBean.getCall());
			// 被叫号码
			String called = msicallLogBean.getCalled();

			map.put("called", called == null ? "-" : called);
			map.put("callTypeName",
					getCallTypeName(msicallLogBean.getCallType()));
			// 座席工号
			/*
			 * 因为数据库中MSICallLog表中MSIUserId字段中存在-1。在MsicallLog.hbm.xml中用多对一
			 * 关联时，会报 No row with the given identifier exists 的异常。
			 */
			Integer msiuserVid = msicallLogBean.getMsiuserId();
			Msiuser msiuser = null;
			if (msiuserVid != null) {
				msiuser = msiuserService.findById(msiuserVid);
			}

			map.put("workNo",
					(msiuser != null && msiuser.getWorkNo() != null) ? msiuser
							.getWorkNo() : "-");
			// 开始时间

			map.put("callInTimeStr", getTimeStr(msicallLogBean.getCallInTime()));
			// 结束时间
			map.put("callEndTimeStr",
					getTimeStr(msicallLogBean.getCallEndTime()));
			// 时长
			map.put("timeStr",
					getTimeSpan(msicallLogBean.getCallInTime(),
							msicallLogBean.getCallEndTime()));
			if (isEmpty(msicallLogBean.getVoxFile())) {
				map.put("voxFile", "-");
				// 语音文件名称
				map.put("voxFileName", "-");
			} else {
				String voxFile = basePath
						+ getPropertiesValue("voxFilePath.properties",
								"filepath") + "user/"
						+ validateIsEmpty(msicallLogBean.getVoxFile());
				map.put("voxFile", voxFile);

				//
				String[] files = msicallLogBean.getVoxFile().split("/");
				map.put("voxFileName", files[files.length - 1]);
			}

			// 工单编号
			CustomerCallLog cclog = msicallLogBean.getCcLog();
			if (cclog != null) {
				if (cclog.getCclSn() != null) {
					map.put("cclSn", cclog.getCclSn());
				} else {
					map.put("cclSn", "");
				}
			} else {
				map.put("cclSn", "");
			}

			mapList.add(map);
			i++;
		}

		return mapList;
	}

	/**
	 * 获取两个时间差
	 * 
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static String getTimeSpan(Timestamp startTime, Timestamp endTime) {
		try {
			long l = endTime.getTime() - startTime.getTime();
			long s = l / 1000;
			return String.valueOf(s);
		} catch (Exception ex) {
			return "";
		}
	}

	/**
	 * @Title:获取呼叫方式
	 * @Description:
	 * @param @param callType
	 * @param @return
	 * @return 返回类型
	 * @throws
	 */
	public String getCallTypeName(int callType) {
		String resultStr = "";
		switch (callType) {
		case 1:
			resultStr = "呼入";
			break;
		case 2:
			resultStr = "呼出";
			break;
		}
		return resultStr;
	}

	/**
	 * 转换时间格式
	 * 
	 * @param time
	 * @return
	 */
	public String getTimeStr(Timestamp time) {
		if (null == time) {
			return "";
		}
		DateFormat sdf = new SimpleDateFormat("MM-dd HH:mm");
		String newTime = sdf.format(time);
		return newTime;
	}

	/**
	 * 读取properties配置文件中的value值
	 * 
	 * @param srcFile
	 *            properties配置文件的路径
	 * @param key
	 *            properties配置文件中的key
	 * @return properties配置文件中的value
	 */
	public static String getPropertiesValue(String srcFile, String key) {
		String result = "";
		InputStream is = null;
		try {
			Properties properties = new Properties();
			String path = Thread.currentThread().getContextClassLoader()
					.getResource(srcFile).getPath();
			is = new FileInputStream(path);
			properties.load(is);
			result = (String) properties.get(key);
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					// e.printStackTrace();
					LOGGER.error(e.getMessage(), e);
				}
			}
		}
		return result;
	}

	/**
	 * 字符串是否为空
	 * 
	 * @param str
	 * @return
	 */
	public boolean isEmpty(String str) {
		if (str == null || str.equals("") || str.equals("null")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 验证是否为空
	 * 
	 * @param str
	 * @return
	 */
	public String validateIsEmpty(String str) {
		if (str == null || str.equals("")) {
			return "";
		} else {
			return str;
		}
	}

	public Integer getIDisplayStart() {
		return iDisplayStart;
	}

	public void setIDisplayStart(int iDisplayStart) {
		this.iDisplayStart = iDisplayStart;
	}

	public Integer getIDisplayLength() {
		return iDisplayLength;
	}

	public void setIDisplayLength(int iDisplayLength) {
		this.iDisplayLength = iDisplayLength;
	}

	/**
	 * @return the call
	 */
	public String getCall() {
		return call;
	}

	/**
	 * @param call
	 *            the call to set
	 */
	public void setCall(String call) {
		this.call = call;
	}

	/**
	 * @return the callType
	 */
	public String getCallType() {
		return callType;
	}

	/**
	 * @param callType
	 *            the callType to set
	 */
	public void setCallType(String callType) {
		this.callType = callType;
	}

	/**
	 * @return the begDate
	 */
	public String getBegDate() {
		return begDate;
	}

	/**
	 * @param begDate
	 *            the begDate to set
	 */
	public void setBegDate(String begDate) {
		this.begDate = begDate;
	}

	/**
	 * @return the endDate
	 */
	public String getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate
	 *            the endDate to set
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the msiCallLogService
	 */
	public IMsicallLog getMsiCallLogService() {
		return msiCallLogService;
	}

	/**
	 * @param msiCallLogService
	 *            the msiCallLogService to set
	 */
	public void setMsiCallLogService(IMsicallLog msiCallLogService) {
		this.msiCallLogService = msiCallLogService;
	}

	public String getWorkNo() {
		return workNo;
	}

	public void setWorkNo(String workNo) {
		this.workNo = workNo;
	}

	public String getCclSn() {
		return cclSn;
	}

	public void setCclSn(String cclSn) {
		this.cclSn = cclSn;
	}

}
