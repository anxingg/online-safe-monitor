package cn.com.qytx.hotline.ivr.action;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.qytx.hotline.ivr.domain.SeatWorkload;
import cn.com.qytx.hotline.ivr.service.IMsiWorkload;
import cn.com.qytx.hotline.util.ExportExcel;
import cn.com.qytx.oa.util.StringUtil;
import cn.com.qytx.platform.base.action.BaseActionSupport;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Sort;
import cn.com.qytx.platform.base.query.Sort.Direction;
import cn.com.qytx.platform.org.domain.UserInfo;

import com.google.gson.Gson;

/**
 * 坐席工作量的Action
 * @author lihuawei
 */
public class SeatTaskStatisticsAction extends BaseActionSupport
{
    /**
     * 描述含义
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * log4j日志对象
     */
    private static final Logger logger = Logger.getLogger(SeatTaskStatisticsAction.class);

    private static final String ADMINUSER = "adminUser";
    private static final String WORKNO = "workNo";
    private static final String NAME = "name";
    // 输入
    @Autowired
    private transient IMsiWorkload iMsiWorkloadService;
    /**
	 * 列表页面查询条件－开始时间
	 */
	private String searchBeginTime;
    /**
	 * 列表页面查询条件－结束时间
	 */
	private String searchEndTime;
    private Integer queueId;
    private Integer groupId; // 查询部门
    private String workIds;
    private int queryType = 1;// 1是按天查询 2是按小时进行查询
    private String fromPage;

    // 输出
    /**
     * 坐席工作量按天查询导出
     * 功能：
     * @return
     */
    public String exporting()
    {
        HttpServletResponse response = this.getResponse();
        response.setContentType("application/vnd.ms-excel");
        OutputStream outp = null;
        try
        {
            UserInfo userInfo = (UserInfo) this.getSession().getAttribute(ADMINUSER);
            if (userInfo != null)
            {
            	workIds = URLDecoder.decode(workIds, "utf-8");
                this.setIDisplayStart(0);
                this.setIDisplayLength(999999);
                Integer IsForkGroup = getLoginUser().getIsForkGroup();
                Page<SeatWorkload> pageInfo = iMsiWorkloadService.findSeatWorkloadGroupByState(
                        getPageable(new Sort(Direction.DESC, "vid")), getTimestampByString(searchBeginTime, "yyyy-MM-dd HH:mm:ss"), 
                        getTimestampByString(searchEndTime, "yyyy-MM-dd HH:mm:ss"), queueId,
                        groupId, workIds, IsForkGroup, userInfo.getCompanyId());
                /** 得到结果 */
                List<SeatWorkload> phoneTaskList = pageInfo.getContent();
                // 把报表信息填充到map里面
                response.addHeader("Content-Disposition", "attachment;filename="
                        + new String("坐席工作量.xls".getBytes(), "iso8859-1"));// 解决中文
                String innerName = "坐席工作量";// 设置表内的表名（第一行，跨行，居中）
                outp = response.getOutputStream();
                List<Map<String, Object>> mapList = analyzeResult(phoneTaskList);
                ExportExcel exportExcel = new ExportExcel(outp, mapList, getExportKeyList(), innerName);
//                ExportExcel exportExcel = new ExportExcel(outp, getExportHeadList(), mapList,
//                        getExportKeyList());
                exportExcel.exportSeatWork();
            }
        }
        catch (Exception e)
        {
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    /**
     * 坐席工作量按小时查询导出
     * 功能：
     * @return
     */
    public String exportingHourse()
    {
        HttpServletResponse response = this.getResponse();
        response.setContentType("application/vnd.ms-excel");
        OutputStream outp = null;
        try
        {
            UserInfo userInfo = (UserInfo) this.getSession().getAttribute("adminUser");
            if (userInfo != null)
            {
                // 分页信息
                Map<String, SeatWorkload> seatMap = iMsiWorkloadService
                        .findSeatWorkloadGroupByDayState(null, getTimestampByString(searchBeginTime, "yyyy-MM-dd HH:mm:ss"), 
                                getTimestampByString(searchEndTime, "yyyy-MM-dd HH:mm:ss"), queueId,
                                groupId, workIds);
                // 把报表信息填充到map里面
                response.addHeader("Content-Disposition", "attachment;filename="
                        + new String("坐席工作量.xls".getBytes(), "iso8859-1"));// 解决中文
                outp = response.getOutputStream();
                List<Map<String, Object>> mapList = getSeatMapForList(seatMap);
                queryType = 2;
                ExportExcel exportExcel = new ExportExcel(outp, getExportHeadList(), mapList,
                        getExportKeyList());
                exportExcel.export();

            }
        }
        catch (Exception e)
        {
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    private List<String> getExportKeyList()
    {
        List<String> exportKey = new ArrayList<String>();
        exportKey.add(WORKNO);// 0
        exportKey.add(NAME);// 1
        exportKey.add("totleLoginMinute");// 13
        exportKey.add("totleBusyMinute");// 14
        exportKey.add("totleFreeMinute");// 15
        if (queryType == 2)
        {
            exportKey.add("timeString");// 1
        }
        exportKey.add("callIn");// 2
        exportKey.add("answercallIn");// 3
        exportKey.add("answercallInRate");// 4
        exportKey.add("averageRingingSecond");// 5
        exportKey.add("averageCallInSecond");// 6
        exportKey.add("totalCallInMinute");// 7
        exportKey.add("callOut");// 8
        exportKey.add("callOutSuccess");// 9
        exportKey.add("callOutSuccessRate");// 10
        exportKey.add("averageCallOutSecond");// 11
        exportKey.add("totleCallOutMinute");// 12
        

        return exportKey;
    }

    private List<String> getExportHeadList()
    {
        List<String> headList = new ArrayList<String>();
        headList.add("坐席工号");// 0
        headList.add("坐席姓名");// 1
        headList.add("登录总时长（分）");// 13
        headList.add("置忙总时长（分）");// 14
        headList.add("空闲总时长（分）");// 15
        if (queryType == 2)
        {
            headList.add("统计时间");// 1
        }
        headList.add("呼入数");// 2
        headList.add("接听数");// 3
        headList.add("呼入接听率（%）");// 4
        headList.add("平均振铃时长（秒）");// 5
        headList.add("平均呼入通话时长（秒）");// 6
        headList.add("呼入通话总时长（分）");// 7
        headList.add("外呼数");// 8
        headList.add("外呼成功数");// 9
        headList.add("外呼成功率（%）");// 10
        headList.add("平均外呼通话时长（秒）");// 11
        headList.add("外呼通话总时长（分）");// 12

        return headList;
    }

    private List<Map<String, Object>> analyzeResult(List<SeatWorkload> phoneTaskList)
    {

        List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        if (phoneTaskList != null)
        {
            for (SeatWorkload swl : phoneTaskList)
            {
                Map<String, Object> map = new HashMap<String, Object>();

                // 坐席Id
                map.put("msiuserId", swl.getMsiuserId());
                // 坐席工号
                String workNo = swl.getWorkNo();
                if (StringUtils.isNotEmpty(workNo))
                {
                    map.put(WORKNO, workNo);
                }
                else
                {
                    map.put(WORKNO, "");
                }
                // 坐席姓名1
                String name = swl.getName();
                if (StringUtils.isNotEmpty(name))
                {
                    map.put(NAME, name);
                }
                else
                {
                    map.put(NAME, "");
                }
                // 呼入数2
                int callIn = swl.getCallIn();
                map.put("callIn", callIn);
                // 接听数3
                int answercallIn = swl.getAnswercallIn();
                map.put("answercallIn", answercallIn);
                // 呼入接听率（%）4
                double answercallInRate = getCompletionRate(answercallIn, callIn);
                // map.put("answercallInRate", answercallInRate+"%");
                map.put("answercallInRate", answercallInRate);// 去掉%
                // 平均振铃时长（秒） 5
                int averageRingingSecond = swl.getAverageRingingSecond();
                map.put("averageRingingSecond", averageRingingSecond);
                // 平均呼入通话时长（秒）6
                int averageCallInSecond = swl.getAverageCallInSecond();
                map.put("averageCallInSecond", averageCallInSecond);
                // 呼入通话总时长（分）7
                int totalCallInMinute = swl.getTotalCallInMinute();
                map.put("totalCallInMinute", totalCallInMinute);
                // 外呼数8
                int callOut = swl.getCallOut();
                map.put("callOut", callOut);
                // 外呼成功数9
                int callOutSuccess = swl.getCallOutSuccess();
                map.put("callOutSuccess", callOutSuccess);
                // 外呼成功率(%)10
                double callOutSuccessRate = getCompletionRate(callOutSuccess, callOut);
                // map.put("callOutSuccessRate", callOutSuccessRate+"%");
                map.put("callOutSuccessRate", callOutSuccessRate);// 去掉%
                // 平均外呼通话时长（秒）11
                int averageCallOutSecond = swl.getAverageCallOutSecond();
                map.put("averageCallOutSecond", averageCallOutSecond);
                // 外呼通话总时长（分）12
                int totleCallOutMinute = swl.getTotleCallOutMinute();
                map.put("totleCallOutMinute", totleCallOutMinute);
                // 登录总时长（分）13
                int totleLoginMinute = swl.getTotleLoginMinute();
                map.put("totleLoginMinute", totleLoginMinute);
                // 置忙总时长（分）14
                int totleBusyMinute = swl.getTotleBusyMinute();
                map.put("totleBusyMinute", totleBusyMinute);
                // 空闲总时长（分）15
                int totleFreeMinute = swl.getTotleFreeMinute();
                map.put("totleFreeMinute", totleFreeMinute);

                mapList.add(map);
            }
        }
        return mapList;
    }

    public String list()
    {
        try
        {
            UserInfo userInfo = (UserInfo) this.getSession().getAttribute(ADMINUSER);
            if (userInfo != null)
            {
                // 判断是否为坐席前端
                Integer isForkGroup = null;
                if (!StringUtil.isEmpty(fromPage) && fromPage.equals("seat"))
                {
                    isForkGroup = getLoginUser().getIsForkGroup();

                    // 判断是否为坐席班长, 坐席班长可以查询本组下所有成员工作量
                    int isZxbz = getSession().getAttribute("isZxbz") == null ? 0: (Integer) (getSession().getAttribute("isZxbz"));//修改空指针问题，当用浏览器打开坐席端页面时，点开坐席工作量会报空指针。
                    if (1 == isZxbz)
                    {
                        workIds = "";
                    }

                }
                else
                {
                    isForkGroup = null;
                }

                Page<SeatWorkload> pageInfo = iMsiWorkloadService.findSeatWorkloadGroupByState(
                        getPageable(new Sort(Direction.DESC, "vid")), getTimestampByString(searchBeginTime, "yyyy-MM-dd HH:mm:ss"), 
                        getTimestampByString(searchEndTime, "yyyy-MM-dd HH:mm:ss"), queueId,
                        groupId, workIds, isForkGroup, userInfo.getCompanyId());
                /** 得到结果 */
                List<SeatWorkload> phoneTaskList = pageInfo.getContent();
                List<Map<String, Object>> mapList = analyzeResult(phoneTaskList);

                this.ajaxPage(pageInfo, mapList);
            }
        }
        catch (Exception e)
        {
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    /**
     * 功能：获取某个坐席的一天工作量
     * @return
     */
    public String dayList()
    {
        PrintWriter out = null;
        try
        {
            UserInfo userInfo = (UserInfo) this.getSession().getAttribute(ADMINUSER);
            if (userInfo != null)
            {
                out = this.getResponse().getWriter();
                Map<String, SeatWorkload> seatMap = iMsiWorkloadService
                        .findSeatWorkloadGroupByDayState(null, getTimestampByString(searchBeginTime, "yyyy-MM-dd HH:mm:ss"), 
                                getTimestampByString(searchEndTime, "yyyy-MM-dd HH:mm:ss"), queueId,
                                groupId, workIds);
                int iTotalRecords = seatMap.size();
                List<Map<String, Object>> mapList = getSeatMapForList(seatMap);
                Map<String, Object> jsonMap = new HashMap<String, Object>();
                jsonMap.put("iTotalRecords", iTotalRecords);
                jsonMap.put("iTotalDisplayRecords", iTotalRecords);
                jsonMap.put("aaData", mapList);
                Gson json = new Gson();
                String jsons = json.toJson(jsonMap);
                out.print(jsons);
            }
        }
        catch (Exception e)
        {
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    /**
     * 功能： 把查询的结果转换为list数组
     * @param seatMap
     * @return
     */
    private List<Map<String, Object>> getSeatMapForList(Map<String, SeatWorkload> seatMap)
    {
        /** 得到结果 */
        List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        for (Map.Entry<String, SeatWorkload> tempEntry : seatMap.entrySet())//Iterator<String> iterator = seatMap.keySet().iterator(); iterator.hasNext();
        {
//            String timeString = (String) iterator.next();
//            SeatWorkload swl = seatMap.get(timeString);
        	String timeString = tempEntry.getKey();
        	SeatWorkload swl = tempEntry.getValue();
            Map<String, Object> map = new HashMap<String, Object>();
            // 坐席Id
            map.put("timeString", timeString);
            map.put("msiuserId", swl.getMsiuserId());
            // 坐席工号
            String workNo = swl.getWorkNo();
            if (StringUtils.isNotEmpty(workNo))
            {
                map.put("workNo", workNo);
            }
            else
            {
                map.put("workNo", "");
            }
            // 坐席姓名1
            String name = swl.getName();
            if (StringUtils.isNotEmpty(name))
            {
                map.put("name", name);
            }
            else
            {
                map.put("name", "");
            }
            // 呼入数2
            int callIn = swl.getCallIn();
            map.put("callIn", callIn);
            // 接听数3
            int answercallIn = swl.getAnswercallIn();
            map.put("answercallIn", answercallIn);
            // 呼入接听率（%）4
            double answercallInRate = getCompletionRate(answercallIn, callIn);
            map.put("answercallInRate", answercallInRate + "%");
            // 平均振铃时长（秒） 5
            int averageRingingSecond = swl.getAverageRingingSecond();
            map.put("averageRingingSecond", averageRingingSecond);
            // 平均呼入通话时长（秒）6
            int averageCallInSecond = swl.getAverageCallInSecond();
            map.put("averageCallInSecond", averageCallInSecond);
            // 呼入通话总时长（分）7
            int totalCallInMinute = swl.getTotalCallInMinute();
            map.put("totalCallInMinute", totalCallInMinute);
            // 外呼数8
            int callOut = swl.getCallOut();
            map.put("callOut", callOut);
            // 外呼成功数9
            int callOutSuccess = swl.getCallOutSuccess();
            map.put("callOutSuccess", callOutSuccess);
            // 外呼成功率(%)10
            double callOutSuccessRate = getCompletionRate(callOutSuccess, callOut);
            map.put("callOutSuccessRate", callOutSuccessRate + "%");
            // 平均外呼通话时长（秒）11
            double averageCallOutSecond = swl.getAverageCallOutSecond();
            map.put("averageCallOutSecond", averageCallOutSecond);
            // 外呼通话总时长（分）12
            double totleCallOutMinute = swl.getTotleCallOutMinute();
            map.put("totleCallOutMinute", totleCallOutMinute);
            // 登录总时长（分）13
            double totleLoginMinute = swl.getTotleLoginMinute();
            map.put("totleLoginMinute", totleLoginMinute);
            // 置忙总时长（分）14
            double totleBusyMinute = swl.getTotleBusyMinute();
            map.put("totleBusyMinute", totleBusyMinute);
            // 空闲总时长（分）15
            double totleFreeMinute = swl.getTotleFreeMinute();
            map.put("totleFreeMinute", totleFreeMinute);

            mapList.add(map);
        }
        return mapList;
    }

    // 计算交办率
    private double getCompletionRate(int completeNum, int assignedNum)
    {
        if (assignedNum == 0)
        {
            return 0.0;
        }
        BigDecimal a = new BigDecimal(completeNum * 100);
        BigDecimal b = new BigDecimal(assignedNum);
        BigDecimal c = a.divide(b, 1, BigDecimal.ROUND_HALF_UP);// 四舍五入
        double result = c.doubleValue();
        return result;
    }
    
	/**
	 * 把 yyyy-MM-dd HH:mm:ss 格式的字符串转成 Timestamp
	 * @param timeString
	 * @return
	 */
	private Timestamp getTimestampByString(String timeString,String timeFormat){//yyyy-MM-dd HH:mm
		if(timeString==null || "".equals(timeString)){
			return null;
		}
		Timestamp ts=null;
		SimpleDateFormat sdf= new SimpleDateFormat(timeFormat);
		sdf.setTimeZone(TimeZone.getTimeZone("GMT+08:00"));
		try {
			java.util.Date date=sdf.parse(timeString);
			ts=new Timestamp(date.getTime());
		} catch (Exception e) {
			logger.error("getTimestampByString() error: ", e);
		}
		return ts;
	}

    /* get set */
	public Integer getQueueId()
    {
        return queueId;
    }

    public void setQueueId(Integer queueId)
    {
        this.queueId = queueId;
    }

    public Integer getGroupId()
    {
        return groupId;
    }

    public void setGroupId(Integer groupId)
    {
        this.groupId = groupId;
    }

    /**
     * @return the workIds
     */
    public String getWorkIds()
    {
        return workIds;
    }

    /**
     * @param workIds the workIds to set
     */
    public void setWorkIds(String workIds)
    {
        this.workIds = workIds;
    }

    public String getFromPage()
    {
        return fromPage;
    }

    public void setFromPage(String fromPage)
    {
        this.fromPage = fromPage;
    }

	public String getSearchBeginTime() {
		return searchBeginTime;
	}

	public void setSearchBeginTime(String searchBeginTime) {
		this.searchBeginTime = searchBeginTime;
	}

	public String getSearchEndTime() {
		return searchEndTime;
	}

	public void setSearchEndTime(String searchEndTime) {
		this.searchEndTime = searchEndTime;
	}

}
