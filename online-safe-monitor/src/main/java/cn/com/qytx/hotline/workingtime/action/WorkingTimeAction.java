package cn.com.qytx.hotline.workingtime.action;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.qytx.hotline.workingtime.domain.WorkingTime;
import cn.com.qytx.hotline.workingtime.service.IWorkingTime;
import cn.com.qytx.monitor.client.log.MonitorLogger;
import cn.com.qytx.monitor.client.logImpl.Log4jImpl;
import cn.com.qytx.platform.base.action.BaseActionSupport;
import cn.com.qytx.platform.org.domain.UserInfo;

/**
 * 作者：李贺
 * 创建时间：2014年12月7日
 * 修改时间：2014年12月7日
 *
 */
public class WorkingTimeAction extends BaseActionSupport{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 5910202463479253036L;
	/**
     * log4j日志对象
     */
	 private final static MonitorLogger logger =new Log4jImpl(WorkingTimeAction.class);

	@Autowired
	private transient IWorkingTime workingTimeService;
	
	/**工作时间实体类*/
	private transient WorkingTime wt;
	
	public String findWorkingTime(){
		WorkingTime wt = null;
		try {
 			List<WorkingTime> list = workingTimeService.findWorkingTime();
			Map<String, Object> map = new HashMap<String, Object>();
			if(list != null && list.size() > 0 ){
				//数据库中存在一套可用的时间
				wt = list.get(0);
				map.put("id", wt.getVid());
				map.put("amGo", wt.getAmGoWorkTime());
				map.put("amOff", wt.getAmOffWorkTime());
				map.put("pmGo", wt.getPmGoWorkTime());
				map.put("pmOff", wt.getPmOffWorkTime());
				logger.info(" 数据库中存在一套可用的时间 . " + wt);
			}else{
				logger.info(" 数据库中不存在可用的时间 . ");
			}
			ajax(map);
			
		} catch (Exception e) {
			logger.error("findWorkingTime error.", e);
		}
		return "";
	}
	
	/**
	 * 修改工作时间
	 * @return
	 */
	public String updataWorkT(){
		UserInfo user = getLoginUser();
		try {
			WorkingTime wtime = null;
			if(wt != null){
				if(wt.getVid() != null){
					wtime = workingTimeService.findById(wt.getVid());
				}else{
					wtime = new WorkingTime();
				}
				wtime.setIsForkGroup(getLoginUser().getIsForkGroup());
				wtime.setLastUpdateTime(new Timestamp( new Date().getTime()));
				wtime.setLastUpdateUserId(getLoginUser().getUserId());
				wtime.setIsDelete(0);
				wtime.setAmGoWorkTime(wt.getAmGoWorkTime());
				wtime.setAmOffWorkTime(wt.getAmOffWorkTime());
				wtime.setPmGoWorkTime(wt.getPmGoWorkTime());
				wtime.setPmOffWorkTime(wt.getPmOffWorkTime());
				wtime.setCompanyId(user.getCompanyId());
				workingTimeService.saveOrUpdate(wtime);
				if(wt.getVid() != null){
					logger.info("工作时间，修改。" + wtime);
				}else{
					logger.info("工作时间，新增。 " + wtime);
				}
			}
			ajax(1);
		} catch (Exception e) {
			logger.error("updataWorkT error.", e);
			ajax(0);
		}
		return null;
	}
	
	

	public WorkingTime getWt() {
		return wt;
	}

	public void setWt(WorkingTime wt) {
		this.wt = wt;
	}
	
}
