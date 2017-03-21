package cn.com.qytx.hotline.messagemanagement.action;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TimeZone;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.qytx.hotline.crm.domaim.CRM;
import cn.com.qytx.hotline.crm.service.ICRM;
import cn.com.qytx.hotline.customercall.domain.CustomerCallLog;
import cn.com.qytx.hotline.customercall.service.ICustomerCallLog;
import cn.com.qytx.hotline.messagemanagement.domain.VoxMail;
import cn.com.qytx.hotline.messagemanagement.service.IVoxMail;
import cn.com.qytx.monitor.client.log.MonitorLogger;
import cn.com.qytx.monitor.client.logImpl.Log4jImpl;
import cn.com.qytx.oa.util.TimeUtil;
import cn.com.qytx.platform.base.action.BaseActionSupport;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Sort;
import cn.com.qytx.platform.base.query.Sort.Direction;
import cn.com.qytx.platform.org.domain.GroupInfo;
import cn.com.qytx.platform.org.domain.UserInfo;
import cn.com.qytx.platform.org.service.IGroup;
import cn.com.qytx.workflow.cfg.WorkflowGlobalCfg;

import com.google.gson.Gson;

public class MsgManagenmentAction extends BaseActionSupport {

	/**
	 * 描述含义
	 */
	private static final long serialVersionUID = -3159086603782555636L;
	
	/**
     * log4j日志对象
     */
	 private final static MonitorLogger logger =new Log4jImpl(MsgManagenmentAction.class);
    
	/**
	 * 客户接口
	 */
	@Autowired
	private transient ICRM crmService;
	/**
	 * 留言接口
	 */
	@Autowired
	private transient IVoxMail voxMailService;
	/**
	 * 部门接口
	 */
	@Autowired
	private IGroup groupService;
	/**
	 * 工单接口
	 */
	@Autowired
	private transient ICustomerCallLog customerService;
	/**
	 * 留言ID
	 */
	private Integer vmId;
	
	
	/**
	 * 客户实体
	 */
	private CRM crm;
	/**
	 * 留言实体
	 */
	private VoxMail voxMail;
	/**
	 * 留言文件地址
	 */
	private String urlString;
	/**
	 * 文件路径
	 */
	private String path;
	/**
	 * 文件名
	 */
	private String fileName;
	/**
	 * 客户数量
	 */
	private Integer crms;
	
	private Integer processId;

	private String _leaveWord="leaveWord.properties";
	
	
	/**
	 * 列表页面查询条件－开始时间
	 */
	private String searchBeginTime;
	
	/**
	 * 列表页面查询条件－结束时间
	 */
	private String searchEndTime;
	

	public Integer getProcessId() {
		return processId;
	}
	public void setProcessId(Integer processId) {
		this.processId = processId;
	}
	/**
	 * 功能：跳转到处理留言页面
	 * @return
	 */
	public String toDealMessage(){
		try {
			UserInfo userInfo = (UserInfo) this.getSession().getAttribute("adminUser");
			if(userInfo!=null&&vmId!=null){
				voxMail = voxMailService.findOne(vmId);
				if(voxMail!=null){
					//关联客户
					String phone = voxMail.getPhone();
					if(phone!=null&&!"".equals(phone)){
						crm = crmService.findCRMByMobile(phone);
						List<CRM> crmList = crmService.findByPhone(phone);
						if(crmList!=null&&crmList.size()>1){
							crms = 1;
						}else{
							crms = 0;
						}
					}
					//播放录音
					String voxInfo = voxMail.getVoxInfo();
					String pathWav = "";
					if(!"".equals(voxInfo)){
						pathWav = voxInfo.substring(voxInfo.indexOf("/com")+1);
					}
					String ip = getPropertiesValue(_leaveWord, "fileIp");
					String port = getPropertiesValue(_leaveWord, "filePort");
					String voxHotline = getPropertiesValue(_leaveWord, "filepath");
					path = "http://";
					path += ip+":"+port+voxHotline+pathWav;
					
					//文件名称
					if("".equals(voxMail.getVoxInfo())){
						 String[] files = voxMail.getVoxInfo().split("/");
						 fileName = files[files.length-1];
					}
					
					//add by jiayq,指定语言留言工单使用的流程
					this.setProcessId(WorkflowGlobalCfg.ON_VOICE_PROCESSID);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return SUCCESS;
	}
	/**
	 * 功能：获得后台留言管理列表
	 * @return
	 */
	public String getSeatMsgTable(){
		try {
			UserInfo userInfo = (UserInfo) this.getSession().getAttribute("adminUser");
			if(userInfo!=null){
				if(voxMail != null){
					voxMail.setBeginTime(getTimestampByString(searchBeginTime, "yyyy-MM-dd HH:mm:ss"));
					voxMail.setEndTime(getTimestampByString(searchEndTime, "yyyy-MM-dd HH:mm:ss"));
				}else{
					logger.error("getSeatMsgTable() error: voxMail is null");
				}
				if(userInfo.getIsForkGroup()!=null&&userInfo.getIsForkGroup()!=2){
					voxMail.setIsForkGroup(userInfo.getIsForkGroup());
				}
				
				Page<VoxMail> pageInfo = voxMailService.findByPage(getPageable(new Sort(Direction.DESC, "beginTime")), voxMail);
				List<VoxMail> voxMailList = pageInfo.getContent(); // 获取结果
				List<Map<String, Object>> mapList = analyzeResult(voxMailList);
				
				Map<String, Object> jsonMap = new HashMap<String, Object>();
				jsonMap.put("iTotalRecords", pageInfo.getTotalElements());
				jsonMap.put("iTotalDisplayRecords", pageInfo.getTotalElements());
				jsonMap.put("aaData", mapList);
				Gson json = new Gson();
				String jsons = json.toJson(jsonMap);
				PrintWriter writer = new PrintWriter(this.getResponse().getWriter());
				writer.print(jsons);
				writer.flush();
				writer.close();
				
			}
		} catch (Exception e) {
//			e.printStackTrace();
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}
	
	
	private List<Map<String, Object>>  analyzeResult(List<VoxMail> voxMailList){
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		if(voxMailList!=null && voxMailList.size() > 0){
			int i = getPageable().getPageNumber() * getPageable().getPageSize() + 1;
			for (VoxMail vm : voxMailList) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("no", i);
				map.put("id", vm.getVid());
				if(vm.getVid()!=null){
					CustomerCallLog ccl =  customerService.findByAccessSourceid(vm.getVid(), 3);
					if(ccl!=null&&ccl.getVid()!=null){
						map.put("cclId", ccl.getVid());
						map.put("instanceId", ccl.getWorkflowId());
					}
				}
				//所属地区
				String area ="-";
				if(vm.getIsForkGroup() != null ){
					GroupInfo groupInfo = groupService.findOne(vm.getIsForkGroup());
					if(groupInfo != null && groupInfo.getGroupName() != null && !"".equals(groupInfo.getGroupName())){
						area = groupInfo.getGroupName();
					}
				}
				map.put("area", area);
				// 来电号码
				String phone = vm.getPhone() != null && !"".equals(vm.getPhone()) ? vm.getPhone() : "-";
//				if(vm.getPhone() != null && !"".equals(vm.getPhone())){
//					phone = vm.getPhone();
//				}
				map.put("phone", phone);
				//留言时间
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String msgTime = vm.getBeginTime() != null ? format.format(vm.getBeginTime()) : "-";
//				if(vm.getBeginTime() != null){
//					msgTime = format.format(vm.getBeginTime());
//				}
				map.put("time", msgTime);
				//留言时长
				Timestamp beginTime = vm.getBeginTime();
				Timestamp endTime = vm.getEndTime();
				String timeDuration = returnTimeDuration(beginTime, endTime);
//				if(beginTime != null && endTime != null){
//					long time =endTime.getTime()-beginTime.getTime();
//					timeDuration = timeConvert(time);
//				}
				map.put("timeDuration", timeDuration);
				//状态
				String state = returnStata(vm.getState());
//				if (vm.getState() != null) {
//					if(vm.getState() == 1){
//						state = "未处理";
//					}
//					if(vm.getState() == 2){
//						state = "已处理";
//					}
//					if(vm.getState() == 3){
//						state = "无效";
//					}
//				}
				map.put("state", state);
				
				//下载路径
				String path = "-";
				String voxInfo = vm.getVoxInfo();
				String pathWav = returnPathWav(voxInfo);
//				if(!"".equals(voxInfo)){
//					pathWav = voxInfo.substring(voxInfo.indexOf("/com")+1);
//				}
				String ip = getPropertiesValue(_leaveWord, "fileIp");
				String port = getPropertiesValue(_leaveWord, "filePort");
				String voxHotline = getPropertiesValue(_leaveWord, "filepath");
				path = "http://";
				path += ip+":"+port+voxHotline+pathWav;
				map.put("path", path);
				
				//文件名称
				String fileName = "";
				if("".equals(vm.getVoxInfo())){
					 String[] files = vm.getVoxInfo().split("/");
					 fileName = files[files.length-1];
				}
				map.put("fileName", fileName);
				mapList.add(map);
				i++;
			}
		}
		return mapList;
	}
	
	private String returnTimeDuration(Timestamp beginTime,Timestamp endTime){
		String timeDuration = "-";
		if(beginTime != null && endTime != null){
			long time =endTime.getTime()-beginTime.getTime();
			Date date = new Date(time);
			timeDuration = TimeUtil.dateToStr(date, "HH:mm:ss");
		}
		return timeDuration;
	}
	
	private String returnStata(Integer stateInteger){
		String state="-";
		if (stateInteger != null) {
			if(stateInteger == 1){
				state = "未处理";
			}
			if(stateInteger == 2){
				state = "已处理";
			}
			if(stateInteger == 3){
				state = "无效";
			}
		}
		return state;
	}
	
	private String returnPathWav(String voxInfo){
		String pathWav = "";
		if(!"".equals(voxInfo)){
			pathWav = voxInfo.substring(voxInfo.indexOf("/com")+1);
		}
		return pathWav;
	}
	
//	/**
//	 * 功能：时间格式转换
//	 * @param time
//	 * @return
//	 */
//	public String timeConvert(Long time){
//		long hour = time/(60*60*1000); 
//		long minute = (time - hour*60*60*1000)/(60*1000); 
//		long second = (time - hour*60*60*1000 - minute*60*1000)/1000; 
//		if(second >= 60 ) 
//		{ 
//		  second = second % 60; 
//		  minute+=second/60; 
//		} 
//		if(minute >= 60) 
//		{ 
//		  minute = minute % 60; 
//		  hour += minute/60; 
//		} 
//		String sh = ""; 
//		String sm =""; 
//		String ss = ""; 
//		if(hour <10) 
//		{ 
//		   sh = "0" + String.valueOf(hour); 
//		}else 
//		{ 
//		   sh = String.valueOf(hour); 
//		} 
//		if(minute <10) 
//		{ 
//		   sm = "0" + String.valueOf(minute); 
//		}else 
//		{ 
//		   sm = String.valueOf(minute); 
//		} 
//		if(second <10) 
//		{ 
//		   ss = "0" + String.valueOf(second); 
//		}else 
//		{ 
//		   ss = String.valueOf(second); 
//		} 
//		
//		return sh+":" + sm+":" + ss;
//	}
	
	/**
	 * @return 修改VoxMail实体
	 */
	public String updataVoxMail(){
		try {
			if(voxMail != null){
				VoxMail vm = voxMailService.findOne(voxMail.getVid());
				vm.setState(voxMail.getState());
				voxMailService.saveOrUpdate(vm);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
//			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
     * 读取properties配置文件中的value值
     * @param srcFile  properties配置文件的路径
     * @param key  properties配置文件中的key
     * @return  properties配置文件中的value
     */
	public static String getPropertiesValue(String fileName , String key){
		String result = "";
		try {
			Properties properties = new Properties();
//			String path = MsgManagenmentAction.class.getClassLoader().getResource(fileName).getPath();
			String path = Thread.currentThread().getContextClassLoader().getResource(fileName).getPath();
			InputStream inputStatement = new FileInputStream(path);
			properties.load(inputStatement);
			result = (String) properties.get(key);
			inputStatement.close();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
//			e.printStackTrace();
		}
		
		return result;
	}
	
	public String downLoadFile(){
		HttpServletResponse response = this.getResponse();
        response.reset();
        response.setContentType("application/x-download");
        java.io.OutputStream outp = null;
        InputStream in = null;
        try 
        {
            URL url = new URL(urlString);
            java.net.HttpURLConnection conn = (java.net.HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            in = conn.getInputStream();
            response.addHeader("Content-Disposition", "attachment;filename=" + "video.wav");
            outp = response.getOutputStream();
            byte[] b = new byte[1024];
            int i = in.read(b);
            while (i > 0)
            {
                outp.write(b, 0, i);
                i = in.read(b);
            }
            outp.flush();
        }
        catch (Exception e)
        {
//            System.out.println("Error!");
//            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }
        finally
        {
            if (in != null)
            {
                try
                {
                    in.close();
                }
                catch (IOException e)
                {
                    LOGGER.error("downFile error.", e);
                }
                in = null;
            }
        }
		
		
		return null;
	}
	/**
	 * 功能：获得所属地市
	 * @return
	 */
	public List<GroupInfo> getGroupName() {
		try {
			List<GroupInfo> list;
			Map<String, Object> map = new HashMap<String, Object>();
			list = groupService.findGroupListByGrade(0);
			// 用于判断是否隐藏所属地市查询条件
			map.put("isForkGroup", getLoginUser().getIsForkGroup());
			map.put("list", list);
			ajax(map);
		} catch (Exception e) {
			LOGGER.error("getGroupName error.", e);
		}
		return null;
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
			//e.printStackTrace();
			logger.error("getTimestampByString() error: ", e);
		}
		return ts;
	}
	
	public Integer getVmId() {
		return vmId;
	}
	public void setVmId(Integer vmId) {
		this.vmId = vmId;
	}
	public CRM getCrm() {
		return crm;
	}
	public void setCrm(CRM crm) {
		this.crm = crm;
	}
	public VoxMail getVoxMail() {
		return voxMail;
	}
	public void setVoxMail(VoxMail voxMail) {
		this.voxMail = voxMail;
	}
	public String getUrlString() {
		return urlString;
	}
	public void setUrlString(String urlString) {
		this.urlString = urlString;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public Integer getCrms() {
		return crms;
	}
	public void setCrms(Integer crms) {
		this.crms = crms;
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
