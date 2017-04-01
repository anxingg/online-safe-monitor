package cn.com.qytx.cbb.log.action;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import cn.com.qytx.cbb.consts.CbbConst;
import cn.com.qytx.cbb.org.util.ExportExcel;
import cn.com.qytx.platform.base.action.BaseActionSupport;
import cn.com.qytx.platform.log.domain.Log;
import cn.com.qytx.platform.log.service.ILog;
import cn.com.qytx.platform.org.domain.GroupInfo;
import cn.com.qytx.platform.org.domain.UserInfo;
import cn.com.qytx.platform.org.service.IGroup;
import cn.com.qytx.platform.utils.datetime.DateTimeUtil;

import com.google.gson.Gson;


/**
 * 功能: 日志action
 * 版本: 1.0
 * 开发人员: 冯东旭
 * 创建日期: 2013-3-21
 * 修改日期: 2013-3-21
 * 修改列表:
 */
public class LogAction  extends BaseActionSupport {
	private static final long serialVersionUID = 1L;
    /** 最大显示条数*/
    private Integer max;

    /** 系统日志接口 */
    @Resource(name = "logService")
    private  ILog logImpl;
    
    @Resource(name = "groupService")
    private IGroup groupService;
    
	/**查询条件 start*/
    private Integer logType;
    private String userIds;
    private String startTime;
    private String endTime;
    private String ip;
    private String remark;
    
    private String userName;
    /**查询条件 start*/
    
    //日志ID
    private Integer logId;
    //日志IDs
    private String logIds;
	/**
	 * 待删除的ID集合
	 */
	private String deleteIds;
	
    public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
     * 批量删除日志
     * @return
     */
    public String deleteListByIds(){
    	String[] ids = deleteIds.split(",");
    	for(int i=0;i<ids.length;i++){
    		logImpl.delete(Integer.parseInt(ids[i]),false);
    	}
    	ajax("0");
		return null;
    }

	/**
	 * 日志查询
	 * @return
	 */
	public String getLogList(){
			UserInfo userInfo = this.getSessionSupport().getCurrentLoginUser();
			if(userIds!=null&&!userIds.equals("")){
				userIds = userIds.substring(1, userIds.length()-1);
			}
			String  gId = getGroupIds(userInfo.getUserId());
			List<Log> list =logImpl.getLogListByParam(max, logType, userIds, startTime, endTime, ip, remark,userName,gId);
			List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
			String nullString="&nbsp;";
			int orderNumber=0;	//序号
			Map<String, Object> map = null;
			if (list != null) {
	            for(Log  l: list) {
	            	orderNumber++;
	            	map = new HashMap<String, Object>();
	                map.put("id", l.getId());
	                map.put("orderNumber", orderNumber);
	                map.put("logType", null==l.getLogType()?nullString:l.getLogType());
	                map.put("userName",null==l.getUserName()?nullString:l.getUserName());
	                map.put("insertTime",null==l.getInsertTime()?nullString: DateTimeUtil.dateToString(l.getInsertTime(), CbbConst.TIME_FORMAT_SUBSTR));
	                map.put("ip",null== l.getIp()?nullString:l.getIp());
	                map.put("ipAddress",null==l.getIpAddress()?nullString:l.getIpAddress());
	                map.put("remark",null==l.getRemark()?nullString:l.getRemark());
	                mapList.add(map);
	            }
	        Map<String, Object> jsonMap = new HashMap<String, Object>();
	        jsonMap.put("aaData", mapList);
	       ajax(jsonMap);
		}
		return null;
	}

    /**
     * 查询条件 批量删除日志
     * @return
     */
    public String deleteListByParam(){
			if(userIds!=null&&!userIds.equals("")){
				userIds = userIds.substring(1, userIds.length()-1);
			}
    		logImpl.deleteListByParam(max,logType, userIds, startTime, endTime, ip, remark);
    		ajax("0");
		return null;
    }
    
	/**
	 * 日志概况
	 * 功能：
	 * @return
	 */
	public String getAllLogNums(){
		String allLogNum = logImpl.getAllLogNums();
		String[] allLogNums = allLogNum.split("-");
        Map<String, Object> jsonMap = new HashMap<String, Object>();
	    try {
	        jsonMap.put("allDay", allLogNums[0]);
	        jsonMap.put("allNum", allLogNums[1]);
	        jsonMap.put("thisYearNum", allLogNums[2]);
	        jsonMap.put("thisMonthNum", allLogNums[3]);
	        jsonMap.put("todayNum", allLogNums[4]);
	        jsonMap.put("averageNum", allLogNums[5]);
	        Gson json = new Gson();
	        String jsons = json.toJson(jsonMap);
	        PrintWriter writer;
			writer = new PrintWriter(this.getResponse().getWriter());
	        writer.print(jsons);
	        writer.flush();
	        writer.close();
        } catch (IOException e) {
        	LOGGER.error(e.getMessage(), e);
		}
        return null;
	}

	public Integer getMax() {
		return max;
	}

	public void setMax(Integer max) {
		this.max = max;
	}

	public Integer getLogType() {
		return logType;
	}

	public void setLogType(Integer logType) {
		this.logType = logType;
	}

	public String getUserIds() {
		return userIds;
	}

	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getLogId() {
		return logId;
	}

	public void setLogId(Integer logId) {
		this.logId = logId;
	}

	public String getLogIds() {
		return logIds;
	}

	public void setLogIds(String logIds) {
		this.logIds = logIds;
	}

	public String getDeleteIds() {
		return deleteIds;
	}

	public void setDeleteIds(String deleteIds) {
		this.deleteIds = deleteIds;
	}

	
    private List<Map<String, Object>> logResult(List<Log> list){
        // 把订单信息填充到map里面
        List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        if (list != null)
        {
            for (Log l : list)
            {
                Map<String, Object> map = new HashMap<String, Object>();
                // ID
                map.put("id", l.getId());

                // 日志日期
                if(l.getUserName()!=null){
                	 map.put("userName",l.getUserName());
                }else{
                	map.put("userName","");
                }
                // 创建时间
                if(l.getInsertTime()!=null){
                	 map.put("insertTime",DateTimeUtil.dateToString(l.getInsertTime(), CbbConst.TIME_FORMAT_STR)); 	
                }else{
                	 map.put("insertTime","");
                }
                //IP
                if(l.getIp()!=null){
                	map.put("ip", l.getIp());
                }else{
                	map.put("ip", "");
                }
                //ip所在地
                if(l.getIpAddress()!=null){
                	map.put("ipAddress", l.getIpAddress());
                }else{
                	map.put("ipAddress", "");
                }
                //日志类型
                String logTypeName = "";
                if(l.getLogType()!=null){
                	Integer logType=l.getLogType();
    				if (logType == 0) {
    					logTypeName = "全部日志";
    				} /*else if (logType == 1) {
    					logTypeName = "登录日志";
    				} else if (logType == 2) {
    					logTypeName = "密码错误";
    				} 
    				else if (logType == 3) {
    					logTypeName = "用户名错误";
    				}  else if (logType == 4) {
    					logTypeName = "清除密码";
    				} else if (logType == 5) {
    					logTypeName = "非法IP登录";
    				} else if (logType == 6) {
    					logTypeName = "退出系统";
    				} */
    				else if (logType == 7) {
    					logTypeName = "用户添加";
    				} else if (logType == 8) {
    					logTypeName = "用户修改";
    				} else if (logType == 9) {
    					logTypeName = "用户删除";
    				} 
    				/*
    				else if (logType == 10) {
    					logTypeName = "用户离职";
    				} 
    				 */
    				else if (logType == 11) {
    					logTypeName = "登录密码修改";
    				} 
    				/*
    				else if (logType == 12) {
    					logTypeName = "部门添加";
    				} else if (logType == 13) {
    					logTypeName = "部门修改";
    				} else if (logType == 14) {
    					logTypeName = "部门删除";
    				} else if (logType == 15) {
    					logTypeName = "公告删除";
    				}  else if (logType == 16) {
    					logTypeName = "邮件发送";
    				} else if (logType == 17) {
    					logTypeName = "邮件删除";
    				} else if (logType == 18) {
    					logTypeName = "按模块设置管理范围";
    				}
    				*/
    				 else if (logType == 25) {
    					logTypeName = "重置单位密码";
    				}else if (logType == 26) {
    					logTypeName = "事故新增";
    				} else if (logType == 27) {
    					logTypeName = "事故修改";
    				} else if (logType == 28) {
    					logTypeName = "事故删除";
    				} else if (logType == 29) {
    					logTypeName = "公司危险化学品新增";
    				} else if (logType == 30) {
    					logTypeName = "公司危险化学品修改";
    				} else if (logType == 31) {
    					logTypeName = "公司危险化学品删除";
    				} else if (logType == 32) {
    					logTypeName = "重大危险源新增";
    				} else if (logType == 33) {
    					logTypeName = "重大危险源修改";
    				} else if (logType == 34) {
    					logTypeName = "重大危险源删除";
    				} else if (logType == 35) {
    					logTypeName = "安全隐患排查新增";
    				} else if (logType == 36) {
    					logTypeName = "安全隐患排查修改";
    				} else if (logType == 37) {
    					logTypeName = "安全隐患排查删除";
    				} else if (logType == 38) {
    					logTypeName = "年度培训新增";
    				} else if (logType == 39) {
    					logTypeName = "年度培训修改";
    				} else if (logType == 40) {
    					logTypeName = "年度培训删除";
    				} else if (logType == 41) {
    					logTypeName = "安全培训记录新增";
    				} else if (logType == 42) {
    					logTypeName = "安全培训记录修改";
    				} else if (logType == 43) {
    					logTypeName = "安全培训记录删除";
    				} else if (logType == 44) {
    					logTypeName = "岗前三级培训新增";
    				} else if (logType == 45) {
    					logTypeName = "岗前三级培训修改";
    				} else if (logType == 46) {
    					logTypeName = "岗前三级培训删除";
    				} else if (logType == 47) {
    					logTypeName = "题库管理新增";
    				} else if (logType == 48) {
    					logTypeName = "题库管理修改";
    				} else if (logType == 49) {
    					logTypeName = "题库管理删除";
    				} else if (logType == 50) {
    					logTypeName = "题库管理生效";
    				} else if (logType == 51) {
    					logTypeName = "题库管理失效";
    				} else if (logType == 52) {
    					logTypeName = "试卷管理新增";
    				} else if (logType == 53) {
    					logTypeName = "试卷管理生效";
    				} else if (logType == 54) {
    					logTypeName = "试卷管理失效";
    				} else if (logType == 55) {
    					logTypeName = "试卷管理删除";
    				} else if (logType == 56) {
    					logTypeName = "应急演练新增";
    				} else if (logType == 57) {
    					logTypeName = "应急演练修改";
    				} else if (logType == 58) {
    					logTypeName = "应急演练删除";
    				} else if (logType == 59) {
    					logTypeName = "应急预案新增";
    				} else if (logType == 60) {
    					logTypeName = "应急预案修改";
    				} else if (logType == 61) {
    					logTypeName = "应急预案删除";
    				} else if (logType == 62) {
    					logTypeName = "企业注册";
    				} else if (logType == 63) {
    					logTypeName = "企业信息修改";
    				} /*else if (logType == 64) {
    					logTypeName = "企业法人新增";
    				} */else if (logType == 65) {
    					logTypeName = "企业法人修改";
    				} else if (logType == 66) {
    					logTypeName = "企业证照修改";
    				} else if (logType == 67) {
    					logTypeName = "企业证照删除";
    				} else if (logType == 68) {
    					logTypeName = "特种作业人员新增";
    				} else if (logType == 69) {
    					logTypeName = "特种作业人员修改";
    				} else if (logType == 70) {
    					logTypeName = "特种作业人员删除";
    				} else if (logType == 71) {
    					logTypeName = "安全管理人员新增";
    				} else if (logType == 72) {
    					logTypeName = "安全管理人员修改";
    				} else if (logType == 73) {
    					logTypeName = "安全管理人员删除";
    				} else if (logType == 74) {
    					logTypeName = "企业产品新增";
    				} else if (logType == 75) {
    					logTypeName = "企业产品修改";
    				} else if (logType == 76) {
    					logTypeName = "企业产品删除";
    				} else if (logType == 77) {
    					logTypeName = "危险化学品新增";
    				} else if (logType == 78) {
    					logTypeName = "危险化学品修改";
    				} else if (logType == 79) {
    					logTypeName = "危险化学品删除";
    				} else if (logType == 80) {
    					logTypeName = "职业卫生专家新增";
    				} else if (logType == 81) {
    					logTypeName = "职业卫生专家修改";
    				} else if (logType == 82) {
    					logTypeName = "职业卫生专家删除";
    				} else if (logType == 83) {
    					logTypeName = "数据字典新增";
    				} else if (logType == 84) {
    					logTypeName = "数据字典修改";
    				} else if (logType == 85) {
    					logTypeName = "数据字典删除";
    				} else if (logType == 86) {
    					logTypeName = "公告发布";
    				} else if (logType == 87) {
    					logTypeName = "公告存草稿";
    				} else if (logType == 88) {
    					logTypeName = "公告修改";
    				} else if (logType == 89) {
    					logTypeName = "公告删除";
    				} else if (logType == 90) {
    					logTypeName = "公告置顶";
    				} else if (logType == 91) {
    					logTypeName = "公告取消置顶";
    				} else if (logType == 92) {
    					logTypeName = "公告终止";
    				} else if (logType == 93) {
    					logTypeName = "公告生效";
    				} else if (logType == 94) {
    					logTypeName = "政策法规发布";
    				} else if (logType == 95) {
    					logTypeName = "政策法规存草稿";
    				} else if (logType == 96) {
    					logTypeName = "政策法规修改";
    				} else if (logType == 97) {
    					logTypeName = "政策法规删除";
    				} else if (logType == 98) {
    					logTypeName = "政策法规置顶";
    				} else if (logType == 99) {
    					logTypeName = "政策法规取消置顶";
    				} else if (logType == 100) {
    					logTypeName = "政策法规终止";
    				} else if (logType == 101) {
    					logTypeName = "政策法规生效";
    				} else if (logType == 102) {
    					logTypeName = "危险化学品目录新增";
    				} else if (logType == 103) {
    					logTypeName = "危险化学品目录修改";
    				} else if (logType == 104) {
    					logTypeName = "危险化学品目录删除";
    				} else if (logType == 105) {
    					logTypeName = "应急机构管理新增";
    				} else if (logType == 106) {
    					logTypeName = "应急机构管理修改";
    				} else if (logType == 107) {
    					logTypeName = "应急机构管理删除";
    				} else if (logType == 108) {
    					logTypeName = "救援物资新增";
    				} else if (logType == 109) {
    					logTypeName = "救援物资修改";
    				} else if (logType == 110) {
    					logTypeName = "救援物资删除";
    				} else if (logType == 111) {
    					logTypeName = "安全管理机构修改";
    				} else if (logType == 112) {
    					logTypeName = "工艺流程新增";
    				} else if (logType == 113) {
    					logTypeName = "工艺流程修改";
    				} else if (logType == 114) {
    					logTypeName = "工艺流程删除";
    				} else if (logType == 115) {
    					logTypeName = "重大危险源危化品目录对象新增";
    				} else if (logType == 116) {
    					logTypeName = "重大危险源危化品目录对象修改";
    				} else if (logType == 117) {
    					logTypeName = "重大危险源危化品目录对象删除";
    				} else if (logType == 118) {
    					logTypeName = "安全生产费用提取新增";
    				} else if (logType == 119) {
    					logTypeName = "安全生产费用使用新增";
    				} else if (logType == 120) {
    					logTypeName = "非煤矿山专家新增";
    				} else if (logType == 121) {
    					logTypeName = "非煤矿山专家修改";
    				} else if (logType == 122) {
    					logTypeName = "非煤矿山专家删除";
    				} else if (logType == 123) {
    					logTypeName = "危险化学品专家新增";
    				} else if (logType == 124) {
    					logTypeName = "危险化学品专家修改";
    				} else if (logType == 125) {
    					logTypeName = "危险化学品专家删除";
    				}
    				
                }	map.put("logType", logTypeName);
                //备注
                if(l.getRemark()!=null){
                	map.put("remark", l.getRemark());
                }else{
                	map.put("remark", "");
                }
                mapList.add(map);
            }
        }
        return mapList;
    }

    /**
     * 功能： 系统日志表头
     * @return
     */
    private List<String> getLogExportHeadList(){
        List<String> headList = new ArrayList<String>();
        headList.add("时间");
        headList.add("用户姓名");
        headList.add("IP地址");
//        headList.add("IP所在地");
        headList.add("日志类型");
        headList.add("备注");
        return headList;
    }

    /**
     * 功能：
     * @return
     */
    private List<String> getLogExportKeyList(){
        List<String> headList = new ArrayList<String>();
        headList.add("insertTime");
        headList.add("userName");
        headList.add("ip");
//        headList.add("ipAddress");
        headList.add("logType");
        headList.add("remark");
        return headList;
    }
    /**
     * 导出文件(系统日志)
     */
    public void exportLog(){
        HttpServletResponse response = this.getResponse();
        response.setContentType("application/vnd.ms-excel");
        OutputStream outp;
        
        try{
            // 首先获取登录人基本信息
            UserInfo user = this.getSessionSupport().getCurrentLoginUser();
			
			// 处理导出时,人员前后的,
	        if(userIds!=null&&!userIds.equals("")){
	            userIds = userIds.substring(1, userIds.length()-1);
	        }
			List<Log> list =logImpl.getLogListByParam(max, logType, userIds, startTime, endTime, ip, remark, userName,getGroupIds(user.getUserId()));

            // 把联系人信息填充到map里面
            response.addHeader("Content-Disposition",
                    "attachment;filename=" + new String("系统日志.xls".getBytes(), "iso8859-1"));// 解决中文
                                                                                           // 文件名问题
            outp = response.getOutputStream();
            List<Map<String, Object>> mapList = logResult(list);

            ExportExcel exportExcel = new ExportExcel(outp, getLogExportHeadList(), mapList,getLogExportKeyList());
            exportExcel.export();
        } catch (Exception e){
            LOGGER.error("getAddressGroupList error. ", e);
        }
    }

	/**
	 * 功能：根据二级局获取部门ID集合
	 * @param
	 * @return
	 * @throws   
	 */
	private String getGroupIds(int userId){
		UserInfo userInfo = this.getSessionSupport().getCurrentLoginUser();
		GroupInfo forkGroup = groupService.getForkGroup(userInfo.getCompanyId(),userId);
		List<GroupInfo> groupList = null;
		if(forkGroup != null){
			groupList = groupService.getSubGroupList(forkGroup.getGroupId());
			groupList.add(forkGroup);
		}
		String groupIds = "";
		if(groupList!=null){
			for(Iterator<GroupInfo> it = groupList.iterator(); it.hasNext();){
				GroupInfo temp = it.next();
				groupIds+=temp.getGroupId();
				if(it.hasNext()){
					groupIds+=",";
				}
			}
		}
		return groupIds;
	}
}
