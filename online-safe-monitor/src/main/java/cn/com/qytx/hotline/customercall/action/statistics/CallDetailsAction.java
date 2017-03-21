package cn.com.qytx.hotline.customercall.action.statistics;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.qytx.cbb.org.util.ExportExcel;
import cn.com.qytx.hotline.ivr.domain.MsicallLog;
import cn.com.qytx.hotline.ivr.domain.Msiuser;
import cn.com.qytx.hotline.ivr.service.IMsiUser;
import cn.com.qytx.hotline.ivr.service.IMsicallLog;
import cn.com.qytx.hotline.util.PropertiesUtil;
import cn.com.qytx.platform.base.action.BaseActionSupport;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Sort;
import cn.com.qytx.platform.base.query.Sort.Direction;
import cn.com.qytx.platform.org.domain.UserInfo;
import cn.com.qytx.platform.utils.datetime.DateTimeUtil;

/**
 * 通话明细的Action
 * 李立泼
 * @author lihuawei
 */
public class CallDetailsAction extends BaseActionSupport {
	private static final long serialVersionUID = -6340492620872309489L;
	//输入
	@Autowired
	private transient IMsicallLog msiCallLogService; //通话记录
	@Autowired
	private transient IMsiUser msiUserService; //通话记录
	private String beginTime;
	private String endTime;
	private String searchkey;
	private Integer callType;
	private final String _call="call";
	private final String _called="called";
	private final String _callTypeName="callTypeName";
	private final String _callEndTimeStr="callEndTimeStr";
	private final String _timeStr="timeStr";
	private final String _workNo="workNo";
	private final String _name="name";
	private final String _voxFileName="voxFileName";
	/**
	 * 功能：导出通话明细
	 * @return
	 */
	public String exporting(){
		final HttpServletResponse response = this.getResponse();
        response.setContentType("application/vnd.ms-excel");
        OutputStream outp = null;
    	try{
    		final UserInfo userInfo = getLoginUser();
    		if(userInfo!=null){
    			this.setIDisplayStart(0);
    			this.setIDisplayLength(999999);
    			Page<MsicallLog> pageInfo = msiCallLogService.findCallLogByPage(getPageable(new Sort(Direction.DESC,"vid")),searchkey,callType,beginTime,endTime,null,userInfo);
                /** 得到结果 */
                List<MsicallLog> msicallLogList = pageInfo.getContent();
                // 把报表信息填充到map里面
                response.addHeader("Content-Disposition", "attachment;filename="
                        + new String("通话明细.xls".getBytes(), "iso8859-1"));// 解决中文
                outp = response.getOutputStream();
                //查询到所有的登录用户，存入一个HashMap中。
                List<Msiuser> msiuserList = msiUserService.findAllmisUserList();
                Map<Integer, Msiuser> msiuserMap = new HashMap<Integer, Msiuser>();
                if( msiuserList != null && !msiuserList.isEmpty() ){
                	for( Msiuser msiuser: msiuserList ){
                		msiuserMap.put(msiuser.getVid(), msiuser);
                	}
                }
                List<Map<String, Object>> mapList = analyzeResult(msicallLogList, msiuserMap);
                ExportExcel exportExcel = new ExportExcel(outp, getExportHeadList(), mapList,getExportKeyList());
                exportExcel.export();
    		}
    	}catch(Exception e){
    		LOGGER.error(e.getMessage());
    	}
    	return null;
	}
	private List<String> getExportKeyList(){
        List<String> exportKey = new ArrayList<String>();
        exportKey.add("orderNumber");//0
        exportKey.add(_call);//1
        exportKey.add(_called);//2
        exportKey.add(_callTypeName);//3
        exportKey.add("callInTimeStr");//4
        exportKey.add(_callEndTimeStr);//5
        exportKey.add(_timeStr);//6
        exportKey.add(_workNo);//7
        exportKey.add(_name);//8
        exportKey.add(_voxFileName);//9
        return exportKey;
	}
	
	private List<String> getExportHeadList(){
        List<String> headList = new ArrayList<String>();
        headList.add("序号");//0
        headList.add("主叫号码");//1
        headList.add("被叫号码");//2
        headList.add("呼叫类型");//3
        headList.add("开始时间");//4
        headList.add("结束时间");//5
        headList.add("时长（秒）");//6
        headList.add("坐席工号");//7
        headList.add("坐席姓名");//8
        headList.add("录音文件");//9

        return headList;
	}
	
	private List<Map<String, Object>>  analyzeResult(List<MsicallLog> msicallLogList, Map<Integer, Msiuser> msiuserMap){
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		if (msicallLogList != null) {
			int i = getPageable().getPageNumber() * getPageable().getPageSize() + 1;
            for (MsicallLog msl : msicallLogList){
                Map<String, Object> map = new HashMap<String, Object>();
                //序号0
                map.put("orderNumber", i);
                //主叫号码1
                map.put(_call, StringUtils.isNotEmpty(msl.getCall())==true?msl.getCall():"");
				//被叫号码2
                map.put(_called, StringUtils.isNotEmpty(msl.getCalled())==true?msl.getCalled():"");
                putMsiLogTwo(msl, map);
                //坐席工号7
                //坐席姓名8
                Integer msiuserId=msl.getMsiuserId();
                Msiuser msiuser = new Msiuser();
                if(msiuserId!=null){
                    msiuser=msiuserMap.get(msl.getMsiuserId());
                }
                if(msiuser!=null){
            		map.put(_workNo, msiuser.getWorkNo());
                	map.put(_name, msiuser.getName());
            	}else{
            		map.put(_workNo, "");
                	map.put(_name, "");
            	}
                //录音文件9
                if(StringUtils.isBlank(msl.getVoxFile())){
					// 语音文件名称
					 map.put(_voxFileName,"-");
				 }else{
					 String voxFilename = msl.getVoxFile().substring( msl.getVoxFile().indexOf("/") + 1);
					 map.put(_voxFileName, StringUtils.isBlank(voxFilename)?"-":voxFilename );
				 }
                mapList.add(map);
                i++;
            }
        }
        return mapList;
	}
	
	/**
	 * 通话明细页面列表显示的方法
	 * @return
	 */
	public String list(){
    	try{
    		UserInfo userInfo = getLoginUser();
    		if(userInfo!=null){
                String basePath = "";
				if ("".equals(PropertiesUtil.get("fileIp",""))) {// 语音文件在远程
					basePath = this.getRequest().getScheme() + "://"
							+ this.getRequest().getServerName() + ":"
							+ this.getRequest().getServerPort() + "/";
				} else {

					basePath = this.getRequest().getScheme()
							+ "://"
							+ PropertiesUtil.get("fileIp","")
							+ ":"
							+ PropertiesUtil.get("filePort","") + "/";
				}
    			Page<MsicallLog> pageInfo = msiCallLogService.findCallLogByPage(getPageable(new Sort(Direction.DESC,"vid")),searchkey,callType,beginTime,endTime,null,userInfo);
                /** 得到结果 */
                List<MsicallLog> msicallLogList = pageInfo.getContent();
                //查询到所有的登录用户，存入一个HashMap中。
                List<Msiuser> msiuserList = msiUserService.findAllmisUserList();
                Map<Integer, Msiuser> msiuserMap = new HashMap<Integer, Msiuser>();
                if( msiuserList != null && !msiuserList.isEmpty() ){
                	for( Msiuser msiuser: msiuserList ){
                		msiuserMap.put(msiuser.getVid(), msiuser);
                	}
                }
                List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
                if (msicallLogList != null) {
                	int i = getPageable().getPageNumber() * getPageable().getPageSize() + 1;
                    for (MsicallLog msl : msicallLogList){
                        Map<String, Object> map = putMsicallLog(basePath, i,
								msl, msiuserMap);
                        mapList.add(map);
                        i++;
                    }
                }
                this.ajaxPage(pageInfo, mapList);
    		}
    	}catch(Exception e){
    		LOGGER.error(e.getMessage());
    	}
    	return null;
	}
	/**
	 * 功能：
	 * @param basePath
	 * @param i
	 * @param msl
	 * @return
	 */
	private Map<String, Object> putMsicallLog(String basePath, int i,
			MsicallLog msl, Map<Integer, Msiuser> msiuserMap) {
		Map<String, Object> map = new HashMap<String, Object>();
		//序号0
		map.put("orderNumber", i);
		//主叫号码1
		map.put(_call, StringUtils.isNotEmpty(msl.getCall())==true?msl.getCall():"");
		//被叫号码2
		map.put(_called, StringUtils.isNotEmpty(msl.getCalled())==true?msl.getCalled():"");
		putMsiLogTwo(msl, map);
		//坐席工号7
		//坐席姓名8
		Integer msiuserId=msl.getMsiuserId();
		Msiuser msiuser=new Msiuser();
		if(msiuserId!=null){
		     msiuser=msiuserMap.get(msl.getMsiuserId());
		}
		if(msiuser!=null){
			map.put(_workNo, msiuser.getWorkNo());
	    	map.put(_name, msiuser.getName());
		}else{
			map.put(_workNo, "");
	    	map.put(_name, "");
		}
		//录音文件9
		if(StringUtils.isBlank(msl.getVoxFile())){
			 map.put("voxFile","-");
			// 语音文件名称
			 map.put(_voxFileName,"-");
		 }else{
			 String voxFile =  basePath + PropertiesUtil.get("filepath","")+"user/"+
					 (StringUtils.isNoneBlank(msl.getVoxFile())?msl.getVoxFile():"");
			 map.put("voxFile",voxFile);
			 String voxFilename = msl.getVoxFile().substring( msl.getVoxFile().indexOf("/") + 1);
			 map.put(_voxFileName, StringUtils.isBlank(voxFilename)?"-":voxFilename );
		 }
		return map;
	}
	/**
	 * 功能：
	 * @param msl
	 * @param map
	 */
	private void putMsiLogTwo(MsicallLog msl, Map<String, Object> map) {
		Integer callType=msl.getCallType();
         String callTypeS="";
         String callTypeName="";
         if(callType!=null){
         	callTypeS=callType.toString();
         	if(callType==1){
         		callTypeName="呼入";
         	}else if(callType==2){
         		callTypeName="呼出";
         	}
         }
        map.put("callType", callTypeS);
     	map.put(_callTypeName, callTypeName);
		//开始时间4
		map.put("callInTimeStr",DateTimeUtil.timestampToString(msl.getTalkInTime(),"yyyy-MM-dd HH:mm:ss"));
		//结束时间5
		map.put(_callEndTimeStr,DateTimeUtil.timestampToString(msl.getTalkEndTime(),"yyyy-MM-dd HH:mm:ss"));
		//时长（秒） 6
		map.put(_timeStr, msl.getSeconds()!=null?msl.getSeconds():"");
	}
    /**
     * 远程url地址
     */
    private String urlString;
    public void downLoadUrl()
    {
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
            int i =in.read(b);
            while (i> 0)
            {
                outp.write(b, 0, i);
                i = in.read(b);
            }
            outp.flush();
        }
        catch (Exception e)
        {
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
    }
	public String getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getSearchkey() {
		return searchkey;
	}
	public void setSearchkey(String searchkey) {
		this.searchkey = searchkey;
	}
	public Integer getCallType() {
		return callType;
	}
	public void setCallType(Integer callType) {
		this.callType = callType;
	}
	public String getUrlString() {
		return urlString;
	}
	public void setUrlString(String urlString) {
		this.urlString = urlString;
	}
}
