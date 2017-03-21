package cn.com.qytx.hotline.report.action;

import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.qytx.cbb.org.util.ExportExcel;
import cn.com.qytx.hotline.ivr.domain.CallQuantity;
import cn.com.qytx.hotline.ivr.service.IMsicallLog;
import cn.com.qytx.platform.base.action.BaseActionSupport;

import com.google.gson.Gson;

/**
 * 功能:话务量综合统计类
 */
public class TelephonetrafficAction extends BaseActionSupport{
	private static final long serialVersionUID = 1L;
	private static final String EXCELCONTENTTYPE = "application/vnd.ms-excel";
	private static final String CHARSET = "iso8859-1";
	private static final String CD = "Content-Disposition";
	private static final String AF = "attachment;filename=";
	private static final String CLERROR = "chatList error. ";
	private static final String CALLINNUM = "callInNum";
	/**
	 * 通话记录服务
	 */
	@Autowired
	private transient IMsicallLog msiCallLogService;
	/**
	 * 查询开始时间
	 */
	private Timestamp beginTime;
	/**
	 * 查询结束时间
	 */
	private Timestamp endTime;
	/**
	 * 呼叫队列
	 */
	private Integer queueId;
	
	
	/**
	 * 功能：获取每天话务量统计情况
	 */
	public void getCallQuantity() {
			Integer isForkGroup = getLoginUser().getIsForkGroup();
			List<CallQuantity> result = msiCallLogService.findCallQuantityNew(
					beginTime, endTime, queueId, isForkGroup,getLoginUser().getCompanyId());
			Gson json = new Gson();
			String jsons = json.toJson(result);
			ajax(jsons);
	}
	
	/**
	 * 功能：导出每天话务量统计情况
	 */
	public void exportCallQuantity() {
		HttpServletResponse response = this.getResponse();
		response.setContentType(EXCELCONTENTTYPE);
		OutputStream outp = null;
		try {
			Integer isForkGroup = getLoginUser().getIsForkGroup();
			// 首先获取登录人基本信息
			List<CallQuantity> result = msiCallLogService.findCallQuantityNew(
					beginTime, endTime, queueId, isForkGroup,getLoginUser().getCompanyId());
			response.addHeader(CD, AF
					+ new String(("话务量综合统计表.xls").getBytes(), CHARSET));// 解决中文
			// 文件名问题
			outp = response.getOutputStream();
			List<Map<String, Object>> mapList = analyzeResult(result);
			ExportExcel exportExcel = new ExportExcel(outp,getExportHeadList(), mapList, getExportKeyList());
			exportExcel.export();

		} catch (Exception e) {
			LOGGER.error(CLERROR, e);
		}
	}
	/**
	 * 功能：把工单信息填充到map里面
	 * @param list
	 * @return
	 */
	private List<Map<String, Object>> analyzeResult(List<CallQuantity> list) {
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		if (list != null) {
			for (CallQuantity cq : list) {
				Map<String, Object> map = new HashMap<String, Object>();
				String callDate = cq.getCallDate();
				map.put("callDate", StringUtils.isNotEmpty(callDate)==true?callDate:"");
				int callInNum = cq.getCallInNum();
				map.put(CALLINNUM, callInNum);
				int callOutNum = cq.getCallOutNum();
				map.put("callOutNum", callOutNum);
				int totleCallNum = cq.getTotleCallNum();
				map.put("totleCallNum", totleCallNum);
				map.put("totleCallLoss", cq.getTotleCallLoss());
				map.put("totleCallInTime", cq.getTotleCallInTime());
				map.put("totleCallOutTime", cq.getTotleCallOutTime());
				map.put("totleLoginTime", cq.getTotleLoginTime());
				map.put("totleBosyTime", cq.getTotleBosyTime());
				map.put("totleNoBosyTime", cq.getTotleNoBosyTime());
				mapList.add(map);
			}
		}
		return mapList;
	}
	/**
	 * 功能：导出加头部
	 * 
	 * @return
	 */
	private List<String> getExportHeadList() {
		List<String> headList = new ArrayList<String>();
		headList.add("日期");
		headList.add("呼入量");
		headList.add("呼出量");
		headList.add("总话务量");
		headList.add("呼损总量");
		headList.add("呼入通话总时长（分）");
		headList.add("呼出通话总时长（分）");
		headList.add("登录总时长（分）");
		headList.add("置忙总时长（分）");
		headList.add("空闲总时长（分）");
		return headList;
	}
	/**
	 * 功能：导出对应的字段
	 * 
	 * @return
	 */
	private List<String> getExportKeyList() {
		List<String> headList = new ArrayList<String>();
		headList.add("callDate");
		headList.add(CALLINNUM);
		headList.add("callOutNum");
		headList.add("totleCallNum");
		headList.add("totleCallLoss");
		headList.add("totleCallInTime");
		headList.add("totleCallOutTime");
		headList.add("totleLoginTime");
		headList.add("totleBosyTime");
		headList.add("totleNoBosyTime");

		return headList;
	}

	public Timestamp getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Timestamp beginTime) {
		this.beginTime = beginTime;
	}

	public Timestamp getEndTime() {
		return endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public Integer getQueueId() {
		return queueId;
	}

	public void setQueueId(Integer queueId) {
		this.queueId = queueId;
	}
	
	
}
