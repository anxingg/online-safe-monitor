package cn.com.qytx.hotline.report.action;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import cn.com.qytx.hotline.ivr.domain.MsicallLogArea;
import cn.com.qytx.hotline.ivr.service.IMsicallLog;
import cn.com.qytx.platform.base.action.BaseActionSupport;

/**
 * 功能:来电区域统计
 */
public class MisCallLogForAreaAction extends BaseActionSupport{
	private static final long serialVersionUID = 1L;
	private static final String EXCELCONTENTTYPE = "application/vnd.ms-excel";
	private static final String NUM = "num";
	private static final String CHARSET = "iso8859-1";
	private static final String CD = "Content-Disposition";
	private static final String AF = "attachment;filename=";
	private static final String CLERROR = "chatList error. ";
	private static final String CALLINNUM = "callInNum";
	private static final String ACCEPTNUM = "acceptNum";
	private static final String AREA = "area";
	private static final String GIVEUPNUM = "giveUpNum";
	private static final String AUTOQUERYNUM = "autoQueryNum";

	/**
	 * 通话记录服务
	 */
	@Autowired
	private transient IMsicallLog msiCallLogService;
	/**
	 * 查询开始时间str
	 */
	private String beginTimeStr;
	/** 
	 * 查询结束时间str
	 */
	private String endTimeStr;
	/**
	 * 一级部门id
	 */
	private Integer groupId;
	/**
	 * 来电区域统计表
	 */
	public String findMisCallLogByArea() {
		List<MsicallLogArea> mcaList = msiCallLogService.findCallInByArea(
				beginTimeStr, endTimeStr, groupId ,getLoginUser().getCompanyId());
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		Integer callInNumSum = 0;
		Integer acceptNumSum = 0;
		Integer giveUpNumSum = 0;
		Integer autoQueryNumSum = 0;
		for (MsicallLogArea mca : mcaList) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put(AREA, mca.getArea() == null ? "" : mca.getArea());
			map.put(CALLINNUM,mca.getCallInNum() == null ? 0 : mca.getCallInNum());
			callInNumSum = callInNumSum
					+ (mca.getCallInNum() == null ? 0 : mca.getCallInNum());
			map.put(ACCEPTNUM,
					mca.getAcceptNum() == null ? 0 : mca.getAcceptNum());
			acceptNumSum = acceptNumSum
					+ (mca.getAcceptNum() == null ? 0 : mca.getAcceptNum());
			map.put(GIVEUPNUM,
					mca.getGiveUpNum() == null ? 0 : mca.getGiveUpNum());
			giveUpNumSum = giveUpNumSum
					+ (mca.getGiveUpNum() == null ? 0 : mca.getGiveUpNum());
			map.put(AUTOQUERYNUM,
					mca.getAutoQueryNum() == null ? 0 : mca.getAutoQueryNum());
			autoQueryNumSum = autoQueryNumSum
					+ (mca.getAutoQueryNum() == null ? 0 : mca
							.getAutoQueryNum());
			mapList.add(map);
		}
		Map<String, Object> mapSum = new HashMap<String, Object>();
		mapSum.put(AREA, "总计");
		mapSum.put(CALLINNUM, callInNumSum);
		mapSum.put(ACCEPTNUM, acceptNumSum);
		mapSum.put(GIVEUPNUM, giveUpNumSum);
		mapSum.put(AUTOQUERYNUM, autoQueryNumSum);
		mapList.add(mapSum);
		ajax(mapList);
		return null;
	}
	
	/**
	 * 来电区域统计表导出
	 * @return
	 */
	public String exportMisCallLogByArea() {
		HttpServletResponse response = this.getResponse();
		response.setContentType(EXCELCONTENTTYPE);
		OutputStream outp = null;
		try {
			response.addHeader(CD, AF
					+ new String(("来电区域分布表.xls").getBytes(), CHARSET));// 解决中文
			outp = response.getOutputStream();

			List<Map<String, Object>> mapList = getAreaMisCallLog();

			cn.com.qytx.hotline.util.ExportExcel exportExcel = new cn.com.qytx.hotline.util.ExportExcel(
					outp, getAreaCallHeadList(), mapList, getAreaCallKeyList());

			exportExcel.exportTimeFrame();
		} catch (Exception e) {
			LOGGER.error(CLERROR, e);
		}

		return null;
	}
	private List<Map<String, Object>> getAreaMisCallLog() {
		List<MsicallLogArea> mcaList = msiCallLogService.findCallInByArea(
				beginTimeStr, endTimeStr, groupId ,getLoginUser().getCompanyId());
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		Integer callInNumSum = 0;
		Integer acceptNumSum = 0;
		Integer giveUpNumSum = 0;
		Integer autoQueryNumSum = 0;
		int i = 0;
		for (MsicallLogArea mca : mcaList) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put(NUM, i + 1);
			i++;

			map.put(AREA, mca.getArea() == null ? "" : mca.getArea());

			map.put(CALLINNUM,
					mca.getCallInNum() == null ? 0 : mca.getCallInNum());
			callInNumSum = callInNumSum
					+ (mca.getCallInNum() == null ? 0 : mca.getCallInNum());

			map.put(ACCEPTNUM,
					mca.getAcceptNum() == null ? 0 : mca.getAcceptNum());
			acceptNumSum = acceptNumSum
					+ (mca.getAcceptNum() == null ? 0 : mca.getAcceptNum());

			map.put(GIVEUPNUM,
					mca.getGiveUpNum() == null ? 0 : mca.getGiveUpNum());
			giveUpNumSum = giveUpNumSum
					+ (mca.getGiveUpNum() == null ? 0 : mca.getGiveUpNum());

			map.put(AUTOQUERYNUM,
					mca.getAutoQueryNum() == null ? 0 : mca.getAutoQueryNum());
			autoQueryNumSum = autoQueryNumSum
					+ (mca.getAutoQueryNum() == null ? 0 : mca
							.getAutoQueryNum());

			mapList.add(map);

		}
		Map<String, Object> mapSum = new HashMap<String, Object>();
		mapSum.put(NUM, "总计");
		mapSum.put(CALLINNUM, callInNumSum);
		mapSum.put(ACCEPTNUM, acceptNumSum);
		mapSum.put(GIVEUPNUM, giveUpNumSum);
		mapSum.put(AUTOQUERYNUM, autoQueryNumSum);
		mapList.add(mapSum);
		return mapList;
	}

	/**
	 * 功能：导出加头部
	 */
	private List<String> getAreaCallHeadList() {
		List<String> headList = new ArrayList<String>();
		headList.add("序号");
		headList.add("地市");
		headList.add("呼入总量");
		headList.add("人工受理");
		headList.add("放弃");
		headList.add("自动查询");
		return headList;
	}
	/**
	 * 功能：导出对应的字段
	 */
	private List<String> getAreaCallKeyList() {
		List<String> headList = new ArrayList<String>();
		headList.add(NUM);
		headList.add(AREA);
		headList.add(CALLINNUM);
		headList.add(ACCEPTNUM);
		headList.add(GIVEUPNUM);
		headList.add(AUTOQUERYNUM);
		return headList;
	}

	public String getBeginTimeStr() {
		return beginTimeStr;
	}

	public void setBeginTimeStr(String beginTimeStr) {
		this.beginTimeStr = beginTimeStr;
	}

	public String getEndTimeStr() {
		return endTimeStr;
	}

	public void setEndTimeStr(String endTimeStr) {
		this.endTimeStr = endTimeStr;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	
}
