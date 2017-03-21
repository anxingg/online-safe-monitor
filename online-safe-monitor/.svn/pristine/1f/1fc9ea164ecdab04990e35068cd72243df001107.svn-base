package cn.com.qytx.hotline.report.action;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import cn.com.qytx.cbb.org.util.ExportExcel;
import cn.com.qytx.hotline.ivr.domain.InQueueInfo;
import cn.com.qytx.hotline.ivr.service.IMsicallLog;
import cn.com.qytx.platform.base.action.BaseActionSupport;
import cn.com.qytx.platform.org.domain.UserInfo;

import com.google.gson.Gson;

/**
 * 排队统计的Action
 * 
 * @author lihuawei
 * 
 */
public class QueueStatisticsAction extends BaseActionSupport {
	/**
	 * 描述含义
	 */
	private static final long serialVersionUID = 1L;
	// 输入
	@Autowired
	private transient IMsicallLog iMsicallLogService;
	private Timestamp beginTime;
	private Timestamp endTime;
	private Integer queueId;
	// 输出
	/**
	 * 所属部门ID
	 */
	private Integer groupId;

	public String getChart() {
		PrintWriter out = null;
		try {
			UserInfo userInfo1 = getLoginUser();
			if (userInfo1 != null) {
				Integer isForkGroup = userInfo1.getIsForkGroup();
				out = this.getResponse().getWriter();
				List<InQueueInfo> list = iMsicallLogService.findInQueueInfo(
						beginTime, endTime, queueId, groupId,isForkGroup,userInfo1.getCompanyId());
				Gson json = new Gson();
				String jsons = json.toJson(list);
				out.print(jsons);
			}
		} catch (Exception e) {
			LOGGER.error("chatList error. ", e);
		}
		return null;
	}

	/**
	 * 导出排队统计
	 */
	public void export() {
		HttpServletResponse response = this.getResponse();
		response.setContentType("application/vnd.ms-excel");
		OutputStream outp = null;
		try {
			// 首先获取登录人基本信息
			UserInfo userInfo1 = getLoginUser();
			Integer isForkGroup;
			if (userInfo1 != null) {
				isForkGroup = userInfo1.getIsForkGroup();
				// 文件名问题
				List<InQueueInfo> result = iMsicallLogService.findInQueueInfo(
						beginTime, endTime, queueId, groupId,isForkGroup,userInfo1.getCompanyId());
				response.addHeader(
						"Content-Disposition",
						"attachment;filename="
								+ new String(("排队统计.xls").getBytes(),
										"iso8859-1"));// 解决中文
				outp = response.getOutputStream();
				List<Map<String, Object>> mapList = analyzeResult(result);

				ExportExcel exportExcel = new ExportExcel(outp,
						getExportHeadList(), mapList, getExportKeyList());

				exportExcel.export();
			}
		} catch (Exception e) {
			LOGGER.error("chatList error. ", e);
		}
	}

	/**
	 * 功能：导出加头部
	 */
	private List<String> getExportHeadList() {
		List<String> headList = new ArrayList<String>();
		headList.add("时间");
		headList.add("排队数");
		return headList;
	}

	/**
	 * 功能：导出对应的字段
	 */
	private List<String> getExportKeyList() {
		List<String> headList = new ArrayList<String>();
		headList.add("inQueueHour");
		headList.add("totleInQueueNum");
		return headList;
	}

	private List<Map<String, Object>> analyzeResult(List<InQueueInfo> list) {
		// 把排队信息填充到map里面
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		if (list != null) {
			for (InQueueInfo ii : list) {
				Map<String, Object> map = new HashMap<String, Object>();
				String inQueueHour = ii.getInQueueHour();
				if (org.apache.commons.lang3.StringUtils
						.isNotEmpty(inQueueHour)) {
					map.put("inQueueHour", inQueueHour);
				} else {
					map.put("inQueueHour", "");
				}
				int totleInQueueNum = ii.getTotleInQueueNum();
				map.put("totleInQueueNum", totleInQueueNum);
				mapList.add(map);
			}
		}
		return mapList;
	}

	/* get set */
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

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

}
