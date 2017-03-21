package cn.com.qytx.hotline.report.action;

import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import cn.com.qytx.hotline.ivr.domain.MsicallLog;
import cn.com.qytx.hotline.ivr.service.IMsicallLog;
import cn.com.qytx.platform.base.action.BaseActionSupport;

import com.google.gson.Gson;

/**
 * 功能:来点时段分布报表类
 */
public class TimeFrameAction extends BaseActionSupport{
	private static final long serialVersionUID = 1L;
	private static final String EXCELCONTENTTYPE = "application/vnd.ms-excel";
	private static final String NUM = "num";
	private static final String CHARSET = "iso8859-1";
	private static final String CD = "Content-Disposition";
	private static final String AF = "attachment;filename=";
	private static final String CBLERROR = "getCallBlockList error : ";
	private static final String CLERROR = "chatList error. ";
	private static final String TIMEEND = ":59";
	private static final String ACCEPTEDNUM = "acceptedNum";
	private static final String CALLINNUM = "callInNum";
	private static final String SURRENDERNUM = "surrenderNum";
	private static final String AUTOCHECK = "autoCheck";
	
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
	 * 一级部门id
	 */
	private Integer groupId;
	/**
	 * 分时段图表显示哪些列( {"呼入总量":0,"人工受理":0, "放弃":0 ,"自动查询":0} ) 0表示不启用，1表示启用。
	 */
	private String showCols;
	/**
	 * 功能：获得来电时段分布表
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getTimeFrame() {
		try {
			List<Map<String, Object>> list;
			List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>(); // NOPMD by jishubu on 15-2-12 ����11:19
			list = msiCallLogService.findTimeFrame(beginTime, endTime, groupId ,getLoginUser().getCompanyId());
			
			if (!list.isEmpty()) {
				Map<Integer, Object> callInNumMap;
				Map<Integer, Object> acceptedNumMap;
				Map<Integer, Object> surrenderNumMap;
				Map<Integer, Object> autoCheckMap;
				callInNumMap = (Map<Integer, Object>) list.get(0).get("callInNumMap");
				acceptedNumMap = (Map<Integer, Object>) list.get(0).get("acceptedNumMap");
				surrenderNumMap = (Map<Integer, Object>) list.get(0).get("surrenderNumMap");
				autoCheckMap = (Map<Integer, Object>) list.get(0).get("autoCheckMap");
				for (int i = 0; i < 24; i++) {
					Map<String, Object> map = new HashMap<String, Object>();
					// 序号
					map.put(NUM, i + 1);
					// 时段
					if (i >= 10) {
						map.put("no", i + ":00-" + i + TIMEEND);
					} else {
						map.put("no", "0" + i + ":00-0" + i + TIMEEND);
					}
					map.put(CALLINNUM, callInNumMap.get(i));
					map.put(ACCEPTEDNUM, acceptedNumMap.get(i));
					map.put(SURRENDERNUM, surrenderNumMap.get(i));
					map.put(AUTOCHECK, autoCheckMap.get(i));
					listMap.add(map);
				}
			}
			ajax(listMap);
		} catch (Exception e) {
			LOGGER.error(CBLERROR, e);
		}
		return null;
	}
	
	/**
	 * 导出来电时间分布表
	 */
	@SuppressWarnings("unchecked")
	public void exportTimeFrame() {
		HttpServletResponse response = this.getResponse();
		response.setContentType(EXCELCONTENTTYPE);
		OutputStream outp = null;
		try {
			response.addHeader(CD, AF
					+ new String(("来电时间分布表.xls").getBytes(), CHARSET));// 解决中文
			outp = response.getOutputStream();
			Integer n = 24;
			List<Map<String, Object>> list;
			List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
			list = msiCallLogService.findTimeFrame(beginTime, endTime, groupId,getLoginUser().getCompanyId());
			if (list.size() > 0) {
				Map<Integer, Object> callInNumMap;
				Map<Integer, Object> acceptedNumMap;
				Map<Integer, Object> surrenderNumMap;
				Map<Integer, Object> autoCheckMap;
				callInNumMap = (Map<Integer, Object>) list.get(0).get(
						"callInNumMap");
				acceptedNumMap = (Map<Integer, Object>) list.get(0).get(
						"acceptedNumMap");
				surrenderNumMap = (Map<Integer, Object>) list.get(0).get(
						"surrenderNumMap");
				autoCheckMap = (Map<Integer, Object>) list.get(0).get(
						"autoCheckMap");
				for (int i = 0; i < n; i++) {
					Map<String, Object> map = new HashMap<String, Object>();
					// 序号
					map.put(NUM, i + 1);
					// 时段
					if (i >= 10) {
						map.put("no", i + ":00-" + i + TIMEEND);
					} else {
						map.put("no", "0" + i + ":00-0" + i + TIMEEND);
					}
					map.put(CALLINNUM, callInNumMap.get(i));
					map.put(ACCEPTEDNUM, acceptedNumMap.get(i));
					map.put(SURRENDERNUM, surrenderNumMap.get(i));
					map.put(AUTOCHECK, autoCheckMap.get(i));
					listMap.add(map);
				}
			}
			List<Map<String, Object>> mapList = timeFrameResult(listMap);
			cn.com.qytx.hotline.util.ExportExcel exportExcel = new cn.com.qytx.hotline.util.ExportExcel(
					outp, getTimeFrameHeadList(), mapList,getTimeFrameKeyList());
			exportExcel.exportTimeFrame();
		} catch (Exception e) {
			LOGGER.error(CLERROR, e);
		}
	}
	
	private List<Map<String, Object>> timeFrameResult(List<Map<String, Object>> list) {
		try {
			/** 总计计算 总计只分装5个key值到map中 导出时注意 */
			Integer sumCallIn = 0;// 呼入总量 总计
			Integer sumAccepted = 0;// 人工受理总计
			Integer sumSurreder = 0;// 放弃 总计
			Integer sumAutoCheck = 0;// 自动查询 总计
			for (Map<String, Object> map : list) {
				sumCallIn += (Integer) map.get(CALLINNUM);
				sumAccepted += (Integer) map.get(ACCEPTEDNUM);
				sumSurreder += (Integer) map.get(SURRENDERNUM);
				sumAutoCheck += (Integer) map.get(AUTOCHECK);
			}
			Map<String, Object> map = new HashMap<String, Object>();
			// 合并栏显示名字
			map.put(NUM, "总计");
			// 呼入总量
			map.put(CALLINNUM, sumCallIn);
			// 人工受理
			map.put(ACCEPTEDNUM, sumAccepted);
			// 放弃数
			map.put(SURRENDERNUM, sumSurreder);
			// 自助查询数
			map.put(AUTOCHECK, sumAutoCheck);
			list.add(map);
			/** 结束 */
			return list;
		} catch (Exception e) {
			LOGGER.error(CBLERROR, e);
		}
		return null;
	}
	
	/**
	 * 功能：导出加头部
	 */
	private List<String> getTimeFrameHeadList() {
		List<String> headList = new ArrayList<String>();
		headList.add("序号");
		headList.add("时段");
		headList.add("呼入总量");
		headList.add("人工受理");
		headList.add("放弃");
		headList.add("自动查询");
		return headList;
	}
	
	/**
	 * 功能：导出对应的字段
	 */
	private List<String> getTimeFrameKeyList() {
		List<String> headList = new ArrayList<String>();
		headList.add(NUM);
		headList.add("no");
		headList.add(CALLINNUM);
		headList.add(ACCEPTEDNUM);
		headList.add(SURRENDERNUM);
		headList.add(AUTOCHECK);
		return headList;
	}
	/**
	 * 分时段统计的图表
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getTimeFrameForPlot() {
		try {
			List<Map<String, Object>> list;
			list = msiCallLogService.findTimeFrame(beginTime, endTime, groupId ,getLoginUser().getCompanyId());
			if (list.size() > 0) {
				Map<Integer, Object> callInNumMap;// 呼入总量
				Map<Integer, Object> acceptedNumMap;// 人工受理
				Map<Integer, Object> surrenderNumMap;// 放弃
				Map<Integer, Object> autoCheckMap;// 自动查询
				callInNumMap = (Map<Integer, Object>) list.get(0).get("callInNumMap");
				acceptedNumMap = (Map<Integer, Object>) list.get(0).get("acceptedNumMap");
				surrenderNumMap = (Map<Integer, Object>) list.get(0).get("surrenderNumMap");
				autoCheckMap = (Map<Integer, Object>) list.get(0).get("autoCheckMap");
				// [[数据数组],[X轴刻度数组],[图例数组]]
				Gson gson = new Gson();
				if (showCols.endsWith(",")) {
					showCols = showCols.substring(0, showCols.length() - 1);
				}
				String[] colNames = showCols.split(",");
				// 数据数组
				List<List<Integer>> dataList = new LinkedList<List<Integer>>();
				List<String> ticksList = new LinkedList<String>();
				for (int i = 0; i < colNames.length; i++) {
					String colName = colNames[i];
					if ("呼入总量".equals(colName)) {
						List<Integer> dataListOfCallIn = mapToList(callInNumMap);
						dataList.add(dataListOfCallIn);// 数据
						ticksList.add(colName);// 图例
					}
					if ("人工受理".equals(colName)) {
						List<Integer> dataListOfAccepted = mapToList(acceptedNumMap);
						dataList.add(dataListOfAccepted);// 数据
						ticksList.add(colName);// 图例
					}
					if ("放弃".equals(colName)) {
						List<Integer> dataListOfSurrender = mapToList(surrenderNumMap);
						dataList.add(dataListOfSurrender);// 数据
						ticksList.add(colName);// 图例
					}
					if ("自动查询".equals(colName)) {
						List<Integer> dataListOfAutoCheck = mapToList(autoCheckMap);
						dataList.add(dataListOfAutoCheck);// 数据
						ticksList.add(colName);// 图例
					}
				}
				List<Object> result = new LinkedList<Object>();
				result.add(dataList);
				// 改成字符串数组，要不第一个0显示不出来。
				result.add(new String[] { "0", "1", "2", "3", "4", "5", "6",
						"7", "8", "9", "10", "11", "12", "13", "14", "15",
						"16", "17", "18", "19", "20", "21", "22", "23" });// 刻度
				result.add(ticksList);

				String jsonData = gson.toJson(result);
				ajax(jsonData);
			}
		} catch (Exception e) {
			LOGGER.error(CBLERROR, e);
		}
		return null;
	}
	/**
	 * 功能：map to list
	 * @param map
	 * @return list
	 */
	public List<Integer> mapToList(Map<Integer, Object> map) {
		List<Integer> dataList = new LinkedList<Integer>();
		Set<Integer> set = map.keySet();
		Iterator<Integer> it = set.iterator();
		while (it.hasNext()) {
			dataList.add((Integer) map.get(it.next()));
		}
		return dataList;
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
	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public String getShowCols() {
		return showCols;
	}

	public void setShowCols(String showCols) {
		this.showCols = showCols;
	}
	
	
}
