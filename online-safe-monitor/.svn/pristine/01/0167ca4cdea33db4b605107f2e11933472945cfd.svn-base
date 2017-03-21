package cn.com.qytx.hotline.ivr.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.qytx.hotline.ivr.domain.Msiservice;
import cn.com.qytx.hotline.ivr.service.IMsiservice;
import cn.com.qytx.monitor.client.log.MonitorLogger;
import cn.com.qytx.monitor.client.logImpl.Log4jImpl;
import cn.com.qytx.platform.base.action.BaseActionSupport;
import cn.com.qytx.platform.org.domain.UserInfo;

import com.google.gson.Gson;


/**
 * 功能: 坐席队列列表
 * 版本: 1.0
 * 开发人员: 
 * 创建日期: 
 * 修改人员：李立泼
 * 修改日期: 2015-03-03
 * 修改列表:
 */
public class SeatQueueAction extends BaseActionSupport {

	/**
	 * 描述含义
	 */
	private static final long serialVersionUID = 1L;
	
	/**
     * log4j日志对象
     */
	 private final static MonitorLogger logger =new Log4jImpl(SeatQueueAction.class);
	
	/**
	 * 坐席队列接口
	 */
	@Autowired
	private transient  IMsiservice msiservice;
	
	/**
	 * 常量值
	 */
	private static final String CONTENT = "content";
	
	/**
	 * 列表查询的每页开始
	 */
	private Integer iDisplayStart;
	
	/**
	 * 列表查询的每页显示数
	 */
	private Integer iDisplayLength;
	
	/**
	 * 队列id
	 */
	private Integer vid;
	
	/**
	 * 坐席Id列表
	 */
	private String userIdList;
	
	/**
	 * 队列说明
	 */
	private String serviceName;
	
	/**
	 * 是否过滤(坐席队列) 1 过滤  0  不过滤
	 */
	private transient boolean isFilter;

	/**
	 * 坐席队列修改页面中的保存方法
	 * @return
	 */
	public String save() {
		final UserInfo userInfo = getLoginUser();
		try {
			if (userInfo != null) {
				final List<UserInfo> userInfoList = new ArrayList<UserInfo>();

				final Msiservice msObj = msiservice.findById(vid);
				if (userInfoList != null) {
					msObj.setUserInfoList(userInfoList);
				}
				msObj.setServiceName(serviceName);
				msObj.setMsiUserIds(userIdList);
				msiservice.saveOrUpdate(msObj);
				logger.info("坐席队列模块   修改操作。修改后的内容：" + msObj + " 。当前操作人：" + userInfo.getUserName() + "( ID: " + userInfo.getUserId() +" ) ");
				ajax("0");
			}
		} catch (Exception e) {
			try {
				ajax("1");
			} catch (Exception e1) {
				logger.error("坐席队列模块  save方法 error. 当前操作人：" + userInfo.getUserName() + "( ID: " + userInfo.getUserId() +" ) ",e);
			}
			logger.error("坐席队列模块  save方法 error. 当前操作人：" + userInfo.getUserName() + "( ID: " + userInfo.getUserId() +" ) ",e);
		}
		return null;
	}

	/**
	 * 
	 * @return
	 */
	public String list() {
		PrintWriter out = null;
		UserInfo userInfo = getLoginUser();
		try {
			if (userInfo != null) {
				out = this.getResponse().getWriter();
				//分页信息
				if (iDisplayLength == 0) {
					iDisplayLength = 15;
				}
				int iTotalRecords = 0;
				int iTotalDisplayRecords = 0;
				//获取所有队列信息（不过滤）
				List<Msiservice> list = msiservice.findAllDao(false);
				//把队列信息填充map里
				List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
				if (list != null) {
					iTotalRecords = list.size();
					iTotalDisplayRecords = list.size();
					int index = 1;
					for (Msiservice msiservice : list) {
						Map<String, Object> map = new HashMap<String, Object>();
						// 标识符
						map.put("vid", msiservice.getVid());
						// 序号
						map.put("index", index);
						// 对应按键
						String content = msiservice.getContent();
						map.put(CONTENT, StringUtils.isNotEmpty(content)?content:"");
						// 按键状态
						Integer isEnable = msiservice.getIsEnable();
						map.put("isEnable", (isEnable == null || isEnable == 0)?"可用":"不可用");
						// 队列说明
						String serviceName = msiservice.getServiceName();
						map.put("serviceName", StringUtils.isNotEmpty(serviceName)?serviceName:"");
						// 队列成员
						List<UserInfo> userInfoList = msiservice
								.getUserInfoList();
						// 人数
						if (userInfoList != null && userInfoList.size() > 0) {
							map.put("userCount", userInfoList.size());
							StringBuffer sbf = new StringBuffer();
							for (UserInfo u : userInfoList) {
								sbf.append(u.getUserName());
								sbf.append(",");
							}
							map.put("userInfoList", (sbf.lastIndexOf(",") == (sbf.length() - 1))?sbf.substring(0, sbf.length() - 1):sbf.toString());
						} else {
							map.put("userInfoList", "");
							map.put("userCount", "0");
						}
						index++;
						mapList.add(map);
					}
				}
				Map<String, Object> jsonMap = new HashMap<String, Object>();
				jsonMap.put("iTotalRecords", iTotalRecords);
				jsonMap.put("iTotalDisplayRecords", iTotalDisplayRecords);
				jsonMap.put("aaData", mapList);

				Gson json = new Gson();
				String jsons = json.toJson(jsonMap);
				out.print(jsons);
			}
		} catch (Exception e) {
			logger.error("坐席队列模块  list方法 error. 当前操作人：" + userInfo.getUserName() + "( ID: " + userInfo.getUserId() +" ) ",e);
		}
		return null;
	}

	/**
	 * 功能：获取可用的坐席队列 
	 * isFilter == true 过滤
	 * isFilter == false 不过滤
	 */
	public void getValidQueue() {
		PrintWriter out = null;
		try {
			out = this.getResponse().getWriter();
			List<Msiservice> list = msiservice.findAllDao(isFilter);;
			List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
			if (null != list && !list.isEmpty()) {
				Map<String, Object> map;
				for (Msiservice msiservice : list) {
					map = new HashMap<String, Object>();
					map.put("vid", msiservice.getVid());
					map.put(CONTENT, msiservice.getContent());
					map.put("queueName", msiservice.getServiceName());
					mapList.add(map);
				}
			}
			Gson json = new Gson();
			String jsons = json.toJson(mapList);
			out.print(jsons);
		} catch (Exception ex) {
			logger.error("坐席队列模块  list方法 error. ",ex);
		} finally {
			if (null != out) {
				out.close();
			}
		}
	}


	/* getter setter */
	public Integer getVid() {
		return vid;
	}

	public void setVid(Integer vid) {
		this.vid = vid;
	}

	public Integer getIDisplayStart() {
		return iDisplayStart;
	}

	public void setIDisplayStart(Integer iDisplayStart) {
		this.iDisplayStart = iDisplayStart;
	}

	public Integer getIDisplayLength() {
		return iDisplayLength;
	}

	public void setIDisplayLength(Integer iDisplayLength) {
		this.iDisplayLength = iDisplayLength;
	}

	public String getUserIdList() {
		return userIdList;
	}

	public void setUserIdList(String userIdList) {
		this.userIdList = userIdList;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public boolean isFilter() {
		return isFilter;
	}

	public void setFilter(boolean isFilter) {
		this.isFilter = isFilter;
	}

}
