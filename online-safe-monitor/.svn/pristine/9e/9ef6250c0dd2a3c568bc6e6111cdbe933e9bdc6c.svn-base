package cn.com.qytx.hotline.ivr.action;

import java.io.OutputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.qytx.cbb.org.util.ExportExcel;
import cn.com.qytx.hotline.ivr.domain.MsiWorkload;
import cn.com.qytx.hotline.ivr.domain.Msiuser;
import cn.com.qytx.hotline.ivr.service.IMsiUser;
import cn.com.qytx.hotline.ivr.service.ISeatWorkReport;
import cn.com.qytx.platform.base.action.BaseActionSupport;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Sort;
import cn.com.qytx.platform.base.query.Sort.Direction;
import cn.com.qytx.platform.org.domain.GroupInfo;
import cn.com.qytx.platform.org.domain.UserInfo;
import cn.com.qytx.platform.org.service.IGroup;

/**
 * 功能: 坐席考勤的action 版本: 1.0 开发人员: 李婷婷 创建日期: 2014-12-22 
 */
public class SeatAttendanceAction extends BaseActionSupport {
	/**
	 * 描述含义
	 */
	private static final long serialVersionUID = 4167059369417012109L;
	private static final String TIMETYPE="yyyy-MM-dd HH:mm:ss";
	private static final String BEGINTIME = "beginTime";
	
	private Integer isForkGroup;
	/**
	 * 所属地市的map
	 */
	private Map<Integer,String> gMap = new HashMap<Integer,String>();
	private String searchKey;//查询关键字
	private String beginTimeStr;//查询开始时间（字符串）
	private String endTimeStr;//查询结束时间（字符串）
	
	
	
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
	public String getSearchKey() {
		return searchKey;
	}
	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}
	public Map<Integer, String> getgMap() {
		return gMap;
	}
	public void setgMap(Map<Integer, String> gMap) {
		this.gMap = gMap;
	}
	
	public Integer getIsForkGroup() {
		return isForkGroup;
	}
	public void setIsForkGroup(Integer isForkGroup) {
		this.isForkGroup = isForkGroup;
	}

	@Resource
	private IGroup groupService;
	@Resource
	private transient ISeatWorkReport isSeatWorkReport;
	/**
	 * 话务员登记
	 */
	@Autowired
	private transient IMsiUser msiuserService;
	/**
	 * 坐席考勤表
	 * @return
	 */
	public  String  seatAttendance(){
		try {
			UserInfo userInfo = (UserInfo)this.getSession().getAttribute("adminUser");
			if(userInfo!=null&&userInfo.getIsForkGroup()!=null){
				List<GroupInfo> gList = groupService.findGroupListByGrade(0);
				Integer group = 0;
				String name="";
				Integer isForkGroupUser = null;
				if(gList!=null){
					isForkGroupUser = getLoginUser().getIsForkGroup();
					for(GroupInfo g : gList){
//						Map<String, Object> map = new HashMap<String, Object>();
						group = g.getIsForkGroup();
						name = StringUtils.defaultString(g.getGroupName(), "");
						gMap.put(group, name);//所属地市name
					}
				}
				Page<MsiWorkload> pageInfo = null;
				if(isForkGroup!=null&&isForkGroup!=-1){
					pageInfo = isSeatWorkReport.findSeatWorkByPage(getPageable(new Sort(Direction.DESC, BEGINTIME)),isForkGroup, beginTimeStr,endTimeStr,searchKey);
				}else{
					pageInfo = isSeatWorkReport.findSeatWorkByPage(getPageable(new Sort(Direction.DESC, BEGINTIME)),isForkGroupUser, beginTimeStr,endTimeStr,searchKey);
				}
//				Page<MsiWorkload> pageInfo = isSeatWorkReport.findSeatWorkByPage(getPageable(new Sort(Direction.DESC, BEGINTIME)),isForkGroup, beginTimeStr,endTimeStr,searchKey);
				List<MsiWorkload> list = pageInfo.getContent();
				List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
				if(list!=null){
					int i = getPageable().getPageNumber() * getPageable().getPageSize() + 1;
					for(MsiWorkload mwk : list){
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("num", i);//序号
						i++;
						String workNo = "-";//工号
						String workName = "-";//话务员
//						String isForkGroupName = "-";//所属地市
						if(mwk.getMsiUserID()!=null){
							Msiuser ms = msiuserService.findById(mwk.getMsiUserID());
							if(ms!=null){
								workNo = ms.getWorkNo();
								workName = ms.getName();
//								GroupInfo outTemp = groupService.findOne(ms.getIsForkGroup());
//								if(outTemp!=null&&outTemp.getGroupName()!=null){
//									isForkGroupName = outTemp.getGroupName();
//								}
							}
						}
//						map.put("isForkGroupName", isForkGroupName);
						map.put("workNo", workNo);
						map.put("workName", workName);
						//签入时间
//						String checkInTimeStr = "-";
						Timestamp checkInTime = mwk.getBeginTime();
//						if(checkInTime!=null){
//							checkInTimeStr = getTimeStrByTimestamp(checkInTime,TIMETYPE);
//						}
						map.put("checkInTimeStr", checkInTime==null?"-":getTimeStrByTimestamp(checkInTime,TIMETYPE));
						//签出时间
//						String checkOutTimeStr = "-";
						Timestamp checkOutTime = mwk.getEndTime();
//						if(checkOutTime!=null){
//							checkOutTimeStr = getTimeStrByTimestamp(checkOutTime,TIMETYPE);
//						}
						map.put("checkOutTimeStr", checkOutTime==null?"-":getTimeStrByTimestamp(checkOutTime,TIMETYPE));
						//2015-01-21马恺添加新坐席考勤表字段
						map.put("onlineTime", mwk.getOnlineTime()==null?"-":mwk.getOnlineTime());
						map.put("busyTime", mwk.getBusyTime()==null?"-":mwk.getBusyTime());
						map.put("restTime", mwk.getRestTime()==null?"-":mwk.getRestTime());
						map.put("changeBusyTimes", mwk.getChangeBusyTimes()==null?"-":mwk.getChangeBusyTimes());
						mapList.add(map);
					}
				}
				this.ajaxPage(pageInfo, mapList);
			}
		} catch (Exception e) {
//			e.printStackTrace();
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	/**
	 * 导出
	 */
	public String exportSeatAttendanceList(){
		HttpServletResponse response = this.getResponse();
        response.setContentType("application/vnd.ms-excel");
        OutputStream outp = null;
    	try{
    		UserInfo userInfo = (UserInfo) this.getSession().getAttribute("adminUser");
    		if(userInfo!=null){
//    			@SuppressWarnings("unused")
//				List<GroupInfo> gList = groupService.getFirstLevelGroup(userInfo.getCompanyId());
    			this.setIDisplayStart(0);
    			this.setIDisplayLength(999999);
    			Integer isForkGroupUser = getLoginUser().getIsForkGroup();
    			Page<MsiWorkload> pageInfo = null;
                if(isForkGroup!=null&&isForkGroup!=-1){
                	pageInfo = isSeatWorkReport.findSeatWorkByPage(getPageable(new Sort(Direction.DESC, BEGINTIME)),isForkGroup, beginTimeStr,endTimeStr,searchKey);
                }else{
                	pageInfo = isSeatWorkReport.findSeatWorkByPage(getPageable(new Sort(Direction.DESC, BEGINTIME)),isForkGroupUser, beginTimeStr,endTimeStr,searchKey);
                }
    			
    			/** 得到结果 */
                List<MsiWorkload> list = pageInfo.getContent();
                // 把报表信息填充到map里面
                response.addHeader("Content-Disposition", "attachment;filename="
                        + new String("坐席考勤表.xls".getBytes(), "iso8859-1"));// 解决中文
                
                outp = response.getOutputStream();
                //坐席（话务员）对象放到map里
//                Map<Integer, Msiuser> msiuserMap = new HashMap<Integer, Msiuser>();
//                List<Msiuser> msiList = msiuserService.findAllmisUserList();
//                if(msiList!=null){
//                	for(Msiuser m :msiList){
//                		Integer msiuserId = m.getVid();
//                    	msiuserMap.put(msiuserId, m);
//                    }
//                }
                //所属地市对象放到map里
//                Map<Integer, GroupInfo> giMap = new HashMap<Integer, GroupInfo>();
//                List<GroupInfo> giList = groupService.findAll();
//                if(giList!=null){
//                	for(GroupInfo g : giList){
//                		Integer gId = g.getGroupId();
//                		giMap.put(gId, g);
//                	}
//                }
                List<Map<String, Object>> mapList = analyzeResult(list);
                
                ExportExcel exportExcel = new ExportExcel(outp, getExportHeadList(), mapList,
                		getExportKeyList());
                
                exportExcel.export();

    		}
    	}catch(Exception e){
    		LOGGER.error(e.getMessage());
    	}
		return null;
	}
	
	private List<String> getExportKeyList(){
        List<String> exportKey = new ArrayList<String>();
        exportKey.add("num");//0
//        exportKey.add("isForkGroupName");//1
        exportKey.add("workNo");//2
        exportKey.add("workName");//3
        exportKey.add("checkInTimeStr");//4
        exportKey.add("checkOutTimeStr");//5
        exportKey.add("onlineTime");//6
        exportKey.add("busyTime");//7
        exportKey.add("restTime");//8
        exportKey.add("changeBusyTimes");//9

        return exportKey;
	}
	
	private List<String> getExportHeadList(){
        List<String> headList = new ArrayList<String>();
        headList.add("序号");//0
//        headList.add("所属地市");//1
        headList.add("坐席工号");//2
        headList.add("坐席姓名");//3
        headList.add("登录时间");//4
        headList.add("退出时间");//5
        headList.add("在线时长(分钟)");//6
        headList.add("置忙时长(分钟)");//7
        headList.add("空闲时长(分钟)");//8
        headList.add("置忙次数");//9

        return headList;
	}
	
	private List<Map<String, Object>>  analyzeResult(List<MsiWorkload> list){
		
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        if (list != null)
        {
        	int i = 1;
			for(MsiWorkload mwk : list){
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("num", i++);//序号
				String workNo = "-";//工号
				String workName = "-";//话务员
				String isForkGroupName = "-";//所属地市
				if(mwk.getMsiUserID()!=null){
					Msiuser ms = msiuserService.findById(mwk.getMsiUserID());
					if(ms!=null){
						workNo = ms.getWorkNo();
						workName = ms.getName();
						GroupInfo outTemp = groupService.findOne(ms.getIsForkGroup());
						if(outTemp!=null&&outTemp.getGroupName()!=null){
							isForkGroupName = outTemp.getGroupName();
						}
					}
				}
				map.put("isForkGroupName", isForkGroupName);
				map.put("workNo", workNo);
				map.put("workName", workName);
				//签入时间
				String checkInTimeStr = "-";
				Timestamp checkInTime = mwk.getBeginTime();
				if(checkInTime!=null){
					checkInTimeStr = getTimeStrByTimestamp(checkInTime,TIMETYPE);
				}
				map.put("checkInTimeStr", checkInTimeStr);
				//签出时间
				String checkOutTimeStr = "-";
				Timestamp checkOutTime = mwk.getEndTime();
				if(checkOutTime!=null){
					checkOutTimeStr = getTimeStrByTimestamp(checkOutTime,TIMETYPE);
				}
				map.put("checkOutTimeStr", checkOutTimeStr);
				
				//2015-01-21马恺添加新坐席考勤表字段
				map.put("onlineTime", mwk.getOnlineTime()==null?"-":mwk.getOnlineTime());
				map.put("busyTime", mwk.getBusyTime()==null?"-":mwk.getBusyTime());
				map.put("restTime", mwk.getRestTime()==null?"-":mwk.getRestTime());
				map.put("changeBusyTimes", mwk.getChangeBusyTimes()==null?"-":mwk.getChangeBusyTimes());
				mapList.add(map);
            }
        }
        return mapList;
	}
	/**
	 * 将 Timestamp 按指定字符串格式进行格式化。
	 * 
	 * @param timestamp
	 *            将要转换的时间戳对象
	 * @param timeFormatStr
	 *            将要转换成的时间格式。如："yyyy-MM-dd HH:mm:ss"
	 * @return 参数为空时，返回空字符串
	 */
	private static String getTimeStrByTimestamp(Timestamp timestamp,
			String timeFormatStr) {
		if (timestamp == null || timeFormatStr == null) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(timeFormatStr);
		long time = timestamp.getTime();
		Date date = new Date(time);

		return sdf.format(date);
	}
	
	
	/**
	 * 2015-01-21马恺添加
	 * 定时向misworkload表中的登陆状态插入 在线时长，空闲时长，置忙时长，置忙次数信息
	 */
	public void insertLoginMsiUserInfo(){
		List<MsiWorkload> loginList = isSeatWorkReport.getLoginedMsiUser();
		for(MsiWorkload temp:loginList){
			Integer msiUserId = temp.getMsiUserID();
			Timestamp beginTime = temp.getBeginTime();
			Timestamp endTime = temp.getEndTime()==null?temp.getNativeTime():temp.getEndTime();
			Long begin = beginTime.getTime();
			Long end = endTime.getTime();
			Long online = (end-begin)/(1000*60);
			//在线时长
			Integer onlineTime = online.intValue();
			temp.setOnlineTime(onlineTime);
			
			
			List<MsiWorkload> stateList = isSeatWorkReport.getLoginedMsistate(msiUserId,beginTime, endTime);
			//置忙时长
			Long busyTime = Long.valueOf(0);
			//置闲时长
			Long restTime = Long.valueOf(0);
			//置忙次数
			Integer changeBusyTimes = 0;
			
			for(MsiWorkload temp2 : stateList){
				Integer state = temp2.getMsiState();
				Timestamp beginTime2 = temp2.getBeginTime();
				Timestamp endTime2 = temp2.getEndTime()==null?temp2.getNativeTime():temp2.getEndTime();
				Long begin2 = beginTime2.getTime();
				Long end2 = endTime2.getTime();
				Long time2 = end2-begin2;
				if(state==4){
					restTime += time2;
				}else if(state==5){
					busyTime += time2;
					changeBusyTimes++;
				}
			}
			Integer busy = busyTime.intValue()/(1000*60);
			Integer rest = restTime.intValue()/(1000*60);
			
			temp.setBusyTime(busy);
			temp.setRestTime(rest);
			temp.setChangeBusyTimes(changeBusyTimes);
			
			isSeatWorkReport.saveOrUpdate(temp);
		}
	}
	

}
