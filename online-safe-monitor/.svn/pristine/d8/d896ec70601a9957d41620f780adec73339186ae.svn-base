package cn.com.qytx.hotline.user.action;

import javax.annotation.Resource;

import cn.com.qytx.platform.base.action.BaseActionSupport;

/**
 * 
 * 功能: 坐席状态查询action 版本: 1.0 开发人员: 李华伟 创建日期: 2013-8-6 修改日期: 2013-8-6 修改列表:
 */
public class MsiUserAction extends BaseActionSupport {
	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -8832289983712954417L;

	@Resource(name = "msiuserService")
//	IMsiUser msiuserService; // 话务员登记

	/** 分页开始条数 */
	private int iDisplayStart;

	/** 每页显示条数 */
	private int iDisplayLength;

	/**
	 * 功能：查询坐席状态列表
	 */
	/*
	 * public void findMsiUserList() { try { UserInfo userInfo =
	 * (UserInfo)this.getSession().getAttribute("adminUser"); if (iDisplayLength
	 * == 0) { iDisplayLength = 15; } int pageNum = (int)
	 * (Math.ceil(iDisplayStart / iDisplayLength)) + 1; Page<Msiuser> pageInfo =
	 * new Page<Msiuser>(iDisplayLength); pageInfo.setPageNo(pageNum); pageInfo
	 * = msiuserService.findMsiuserByPage(pageInfo, null, userInfo, null,
	 * null,""); int iTotalDisplayRecords = pageInfo.getTotalCount() < 0 ? 0 :
	 * pageInfo.getTotalCount(); List<Msiuser> msiusereList =
	 * pageInfo.getResult(); // 获取结果 int iTotalRecords =
	 * pageInfo.getTotalCount(); List<Map<String, Object>> mapList = new
	 * ArrayList<Map<String, Object>>(); if (null != msiusereList &&
	 * msiusereList.size() > 0) { int i = (pageNum - 1) * iDisplayLength + 1;
	 * for (Msiuser tempMsiuser : msiusereList) { Map<String, Object> map = new
	 * HashMap<String, Object>(); map.put("no", i); // 坐席工号 map.put("workNo",
	 * tempMsiuser.getWorkNo());
	 * 
	 * // 坐席姓名 map.put("name", tempMsiuser.getName());
	 * 
	 * // 坐席状态 map.put("state", tempMsiuser.getState());
	 * 
	 * // 坐席通话状态 map.put("callState", tempMsiuser.getMsiWorkType());
	 * 
	 * // 坐席登录时间 Timestamp loginTime = tempMsiuser.getLoginTime(); if (null !=
	 * loginTime) { map.put("loginTime", DateTimeUtil.dateToString(loginTime,
	 * OaConst.TIME_FORMAT_STR)); } else { map.put("loginTime", "-"); }
	 * mapList.add(map); i++; } } Map<String, Object> jsonMap = new
	 * HashMap<String, Object>(); jsonMap.put("iTotalRecords", iTotalRecords);
	 * jsonMap.put("iTotalDisplayRecords", iTotalDisplayRecords);
	 * jsonMap.put("aaData", mapList); Gson json = new Gson(); String jsons =
	 * json.toJson(jsonMap); PrintWriter writer = new
	 * PrintWriter(this.getResponse().getWriter()); writer.print(jsons);
	 * writer.flush(); writer.close(); } catch (Exception e) {
	 * e.printStackTrace(); LOGGER.error(e.getMessage(), e); } }
	 */
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
}
