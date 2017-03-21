package cn.com.qytx.hotline.report.action;

import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.qytx.hotline.report.domain.WorkSummary;
import cn.com.qytx.hotline.report.domain.WorkSummarySearch;
import cn.com.qytx.hotline.report.service.IWorkSummary;
import cn.com.qytx.hotline.util.ExportExcel;
import cn.com.qytx.platform.base.action.BaseActionSupport;
import cn.com.qytx.platform.org.domain.GroupInfo;
import cn.com.qytx.platform.org.domain.UserInfo;
import cn.com.qytx.platform.org.service.IGroup;
/**
 * 功能:跳转到工作汇总表
 * 版本: 1.0
 * 开发人员: 张东领
 * 创建日期: 2014-12-1
 * 修改日期: 2014-12-1
 * 修改列表:
 */
public class WorkSummaryAction extends BaseActionSupport  {

	/**
	 * 描述含义
	 */
	private static final long serialVersionUID = 5043085921470587588L;
	  /**
     * 工作汇总接口
     */
    @Autowired
    private transient IWorkSummary workSummaryService;
    /**
     * 工作汇总查询实体
     */
    private transient WorkSummarySearch wss;
	/**
	 * 所属地市接口
	 */
	@Autowired
	private IGroup groupService;
	private Integer loginIsFork;//登录用户权限
	/**
	 * 功能：跳转到工作汇总表页面
	 * @return
	 */
	public String toWorkSummary(){
		try {
			UserInfo userInfo = (UserInfo) this.getSession().getAttribute("adminUser");
    		if(userInfo!=null&&userInfo.getIsForkGroup()!=null){
    			loginIsFork = userInfo.getIsForkGroup();
    		}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return SUCCESS;
	}
	/**
	 * 功能：加载城市名
	 * @return
	 */
	public String loadCityName(){
		try {
			UserInfo userInfo = (UserInfo) this.getSession().getAttribute("adminUser");
			if(userInfo!=null&&userInfo.getIsForkGroup()!=null){
				List<GroupInfo> gList = groupService.findGroupListByGrade(0);
				Integer isForkGroup = 0;
				String name="";
				List<Map<String, Object>> mapList = new ArrayList<Map<String,Object>>();
				if(gList!=null){
					for(GroupInfo g : gList){
						Map<String, Object> map = new HashMap<String, Object>();
						isForkGroup = g.getIsForkGroup();
						if(isForkGroup!=null&&isForkGroup!=0){
							map.put("isForkGroup", isForkGroup);
						}
						name = StringUtils.defaultString(g.getGroupName(), "");
						if(name!=null&&!"".equals(name)){
							map.put("name", name);
						}
						mapList.add(map);
					}
				}
				this.ajax(mapList);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	 /**
     * 功能：获取列表数据
     * @return
     */
    public String workSummaryList()
    {
        try
        {
            UserInfo userInfo = this.getLoginUser();
            if (userInfo != null)
            {
                if (userInfo.getIsForkGroup() != null && userInfo.getIsForkGroup() != 2)
                {
                    wss.setIsForkGroup(userInfo.getIsForkGroup());
                }
                List<WorkSummary> wsList = workSummaryService.workSummaryNumber(wss,userInfo.getCompanyId());
                List<Map<String, Object>> mapList = analyzeResult(wsList);
                this.ajax(mapList);
            }
        }
        catch (Exception e)
        {
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    /**
     * 功能：导出
     * @return
     */
    public String exporting()
    {
        HttpServletResponse response = this.getResponse();
        response.setContentType("application/vnd.ms-excel");
        OutputStream outp = null;
        try
        {
            UserInfo userInfo = (UserInfo) this.getSession().getAttribute("adminUser");
            if (userInfo != null)
            {
                if (userInfo.getIsForkGroup() != null && userInfo.getIsForkGroup() != 2)
                {
                    wss.setIsForkGroup(userInfo.getIsForkGroup());
                }
                /** 得到结果 */
                List<WorkSummary> wsList = workSummaryService.workSummaryNumber(wss,userInfo.getCompanyId());
                // 把报表信息填充到map里面
                response.addHeader("Content-Disposition", "attachment;filename="
                        + new String("工作汇总表.xls".getBytes(), "iso8859-1"));// 解决中文
                String innerName = "工作汇总表";// 设置表内的表名（第一行，跨行，居中）
                outp = response.getOutputStream();
                List<Map<String, Object>> mapList = analyzeResult(wsList);
                ExportExcel exportExcel = new ExportExcel(outp, getExportKeyList(), mapList,
                        getExportKeyList(), innerName, getExportLastKeyList());
                exportExcel.exportComplex();
            }
        }
        catch (Exception e){
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    Integer phoneTotal = 0;// 电话咨询共计
    Integer soundTotal = 0;// 语音留言共计
    Integer messageTotal = 0;// 短信平台共计
    Integer allCountTotal = 0;// 合计共
    private List<Map<String, Object>> analyzeResult(List<WorkSummary> wsList){
        List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        if (wsList != null){
            int i = 1;
            Map<String, Object> lastmap = new HashMap<String, Object>();
            for (WorkSummary ws : wsList){// 计算共计数量
                Integer allCount = ws.getAllCount();
                if (allCount != null){
                    allCountTotal += allCount;
                }
                Integer phoneNum = ws.getPhoneNum();
                if (phoneNum != null){
                    phoneTotal += phoneNum;
                }
                // 语音留言
                Integer soundNum = ws.getSoundNum();
                if (soundNum != null){
                    soundTotal += soundNum;
                }
                // 短信平台
                Integer messageNum = ws.getMessageNum();
                if (messageNum != null){
                    messageTotal += messageNum;
                }
            }
            for (WorkSummary ws : wsList){// 显示数据
                Map<String, Object> map = new HashMap<String, Object>();
                // 序号
                map.put("no", i);
                // 业务类型
                String businessName = StringUtils.defaultString(ws.getBusinessName(), "");
                map.put("businessName", businessName);
                // 合计数量
                Integer allCount = ws.getAllCount()!=null ? ws.getAllCount() : 0;
                map.put("allCount", allCount);
                // 合计百分比
                String allCountPercent = returnPercent(allCount, allCountTotal);
                map.put("allCountPercent", allCountPercent);
                // 12333电话咨询
                Integer phoneNum = ws.getPhoneNum() !=null ? ws.getPhoneNum() : 0;
                map.put("phoneNum", phoneNum);
                // 12333电话咨询百分比
                String phoneNumPercent = returnPercent(phoneNum, allCount);
                map.put("phoneNumPercent", phoneNumPercent);
                // 语音留言
                Integer soundNum = ws.getSoundNum() != null ? ws.getSoundNum() : 0;
                map.put("soundNum", soundNum);
                // 语音留言百分比
                String soundNumPercent = returnPercent(soundNum, allCount);
                map.put("soundNumPercent", soundNumPercent);
                // 短信平台
                Integer messageNum = ws.getMessageNum() !=null ?ws.getMessageNum() : 0;
                map.put("messageNum", messageNum);
                // 短信平台百分比
                String messageNumPercent = returnPercent(messageNum, allCount);
                map.put("messageNumPercent", messageNumPercent);
                mapList.add(map);
                i++;
            }
            lastmap.put("phoneTotal", phoneTotal);
            lastmap.put("soundTotal", soundTotal);
            lastmap.put("messageTotal", messageTotal);
            lastmap.put("allCountTotal", allCountTotal);
            mapList.add(lastmap);
        }
        return mapList;
    }
    
    /**
     * 功能：返回百分比字符串
     * @param Molecular分子
     * @param Denominator分母
     * @return
     */
    private String returnPercent(Integer Molecular,Integer Denominator){
    	String percent = "";
    	if(Denominator!=null && Denominator>0 && Molecular!=0){
    		percent = getPercent(Molecular, Denominator);
    	}else{
    		percent = "0%";
    	}
    	return percent;
    }
    
    private List<String> getExportKeyList()
    {
        List<String> exportKey = new ArrayList<String>();
        exportKey.add("no");// 0序号
        exportKey.add("businessName");// 1业务类型
        exportKey.add("phoneNum");// 2电话咨询数量
        exportKey.add("phoneNumPercent");// 3电话咨询百分比
        exportKey.add("soundNum");// 4语音留言数量
        exportKey.add("soundNumPercent");// 5语音留言百分比
        exportKey.add("messageNum");// 6短信数量
        exportKey.add("messageNumPercent");// 7短信百分比
        exportKey.add("allCount");// 8合计
        exportKey.add("allCountPercent");// 9合计百分比

        return exportKey;
    }

    private List<String> getExportLastKeyList()
    {
        List<String> exportLastKey = new ArrayList<String>();
        exportLastKey.add("phoneTotal");// 电话咨询共计0
        exportLastKey.add("soundTotal");// 语音留言共计1
        exportLastKey.add("messageTotal");// 短信平台共计2
        exportLastKey.add("allCountTotal");// 合计共计3
        return exportLastKey;
    }

    /**
     * 功能：计算百分比
     * @param x：分子
     * @param total：分母
     * @return
     */
    public String getPercent(int x, int total)
    {
        String result = "";// 接受百分比的值
        double x_double = x * 1.0;
        double tempresult = x_double / total;
        DecimalFormat df1 = new DecimalFormat("#.##%");    // ##.00%
                                                        // 百分比格式，后面不足2位的用0补齐
        result = df1.format(tempresult);
        return result;
    }

    public WorkSummarySearch getWss()
    {
        return wss;
    }
    public void setWss(WorkSummarySearch wss)
    {
        this.wss = wss;
    }
	public Integer getLoginIsFork() {
		return loginIsFork;
	}
	public void setLoginIsFork(Integer loginIsFork) {
		this.loginIsFork = loginIsFork;
	}
	
}
