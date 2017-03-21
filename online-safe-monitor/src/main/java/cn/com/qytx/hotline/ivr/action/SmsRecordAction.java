package cn.com.qytx.hotline.ivr.action;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.qytx.hotline.ivr.domain.SmsSumRecord;
import cn.com.qytx.hotline.ivr.service.ISendSumSms;
import cn.com.qytx.platform.base.action.BaseActionSupport;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.org.domain.UserInfo;

public class SmsRecordAction extends BaseActionSupport {

	private static final long serialVersionUID = -1243858209006914174L;

	private static final String PHONE="phone";
	private static final String CREATEUSERIDNAME="createUserIdName";
	private static final String SENDTIMESTR="sendTimeStr";
	private static final String CONTENT="content";
	//输入
	@Autowired
	private transient ISendSumSms sendSumSmsService;
	private Timestamp sendTimeStart;
	private Timestamp sendTimeEnd;
	private Integer customerCallLogId;
	private String searchkey;
	//输出
	
	public String listOfWelcome(){
    	try{
    		UserInfo userInfo = (UserInfo) this.getSession().getAttribute("adminUser");
    		if(userInfo!=null){
                Page<SmsSumRecord> pageInfo = sendSumSmsService.findCCLByPage(getPageable(), searchkey, sendTimeStart, sendTimeEnd, customerCallLogId);
                /** 得到结果 */
                List<SmsSumRecord> smsSumRecordList = pageInfo.getContent();

                List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
                if (smsSumRecordList != null)
                {
                	int i = getPageable().getPageNumber() * getPageable().getPageSize() +1;
                    for (SmsSumRecord ssr : smsSumRecordList){
                        Map<String, Object> map = new HashMap<String, Object>();
                        //标识符
                        map.put("vid", ssr.getVid());
                        //序号0
                        map.put("orderNumber", i);
                        //接收号码1
                        String phone=ssr.getPhone();
                        if(StringUtils.isNotEmpty(phone)){
                        	map.put(PHONE, phone);
                        }else{
                        	map.put(PHONE, "");
                        }
                        //操作人员2
                        UserInfo ui = ssr.getUserInfo();
                        if (ui!=null && ui.getUserName()!=null && !"".equals(ui.getUserName())){
                        	map.put(CREATEUSERIDNAME, ui.getUserName());
                        }else{
                        	map.put(CREATEUSERIDNAME, "");
                        }
                        //发送时间3
                        Timestamp sendTime = ssr.getSendTime();
                        String sendTimeStr=getTimeStrByTimestamp(sendTime,"MM-dd HH:mm");
                        if(StringUtils.isNotEmpty(sendTimeStr)){
                        	map.put(SENDTIMESTR, sendTimeStr);
                        }else{
                        	map.put(SENDTIMESTR, "");
                        }
                        //短信内容4
                        String content = ssr.getContent();
                        if(StringUtils.isNotEmpty(content)){
                        	map.put(CONTENT, content);
                        }else{
                        	map.put(CONTENT, "");
                        }
                        
                        if(i==5){
                        	break;
                        }
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
	
	public String list(){
		try{
			UserInfo userInfo = (UserInfo) this.getSession().getAttribute("adminUser");
			if(userInfo!=null){
				Page<SmsSumRecord> pageInfo = sendSumSmsService.findCCLByPage(getPageable(), searchkey, sendTimeStart, sendTimeEnd, customerCallLogId);
				
				/** 得到结果 */
				List<SmsSumRecord> smsSumRecordList = pageInfo.getContent();
				
				List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
				if (smsSumRecordList != null)
				{
					int i = getPageable().getPageNumber() * getPageable().getPageSize() +1;
					for (SmsSumRecord ssr : smsSumRecordList){
						Map<String, Object> map = new HashMap<String, Object>();
						//标识符
						map.put("vid", ssr.getVid());
						//序号0
						map.put("orderNumber", i);
						//接收号码1
						String phone=ssr.getPhone();
						if(StringUtils.isNotEmpty(phone)){
							map.put(PHONE, phone);
						}else{
							map.put(PHONE, "");
						}
						//操作人员2
						UserInfo ui = ssr.getUserInfo();
						if (ui!=null){
							map.put(CREATEUSERIDNAME, ui.getUserName());
						}else{
							map.put(CREATEUSERIDNAME, "");
						}
						//发送时间3
						Timestamp sendTime = ssr.getSendTime();
						String sendTimeStr=getTimeStrByTimestamp(sendTime,"MM-dd HH:mm");
						if(StringUtils.isNotEmpty(sendTimeStr)){
							map.put(SENDTIMESTR, sendTimeStr);
						}else{
							map.put(SENDTIMESTR, "");
						}
						//短信内容4
						String content = ssr.getContent();
						if(StringUtils.isNotEmpty(content)){
							map.put(CONTENT, content);
						}else{
							map.put(CONTENT, "");
						}
						
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
	 * 将 Timestamp 按指定字符串格式进行格式化。
	 * @param timestamp 将要转换的时间戳对象
	 * @param timeFormatStr 将要转换成的时间格式。如："yyyy-MM-dd HH:mm:ss"
	 * @return 参数为空时，返回空字符串
	 */
	private static String getTimeStrByTimestamp(Timestamp timestamp,String timeFormatStr){
		if(timestamp==null || timeFormatStr==null){
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(timeFormatStr);
		long time = timestamp.getTime();
		Date date=new Date(time);
		
		return sdf.format(date);
	}
	
	/* get set */
	

	public Timestamp getSendTimeStart() {
		return sendTimeStart;
	}

	public void setSendTimeStart(Timestamp sendTimeStart) {
		this.sendTimeStart = sendTimeStart;
	}

	public Timestamp getSendTimeEnd() {
		return sendTimeEnd;
	}

	public void setSendTimeEnd(Timestamp sendTimeEnd) {
		this.sendTimeEnd = sendTimeEnd;
	}

	public Integer getCustomerCallLogId() {
		return customerCallLogId;
	}

	public void setCustomerCallLogId(Integer customerCallLogId) {
		this.customerCallLogId = customerCallLogId;
	}

	public String getSearchkey() {
		return searchkey;
	}

	public void setSearchkey(String searchkey) {
		this.searchkey = searchkey;
	}

}
