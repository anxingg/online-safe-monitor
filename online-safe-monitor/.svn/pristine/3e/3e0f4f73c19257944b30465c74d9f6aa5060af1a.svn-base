package cn.com.qytx.hotline.area.action;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.qytx.hotline.area.service.IFiveGradeArea;
import cn.com.qytx.monitor.client.log.MonitorLogger;
import cn.com.qytx.monitor.client.logImpl.Log4jImpl;
import cn.com.qytx.platform.base.action.BaseActionSupport;
import cn.com.qytx.platform.org.domain.UserInfo;
/**
 * 功能: 删除区域
 * 版本: 1.0
 * 开发人员: 李立泼
 * 创建日期: 2014-11-11
 * 修改人员：张东领
 * 修改日期: 2015-2-11
 * 修改列表:
 */
public class DeleteAreaAction extends BaseActionSupport {

	/**
     * 序列号
     */
    private static final long serialVersionUID = 5962256899765209081L;
    /**
     * log4j日志对象
     */
    private final static MonitorLogger logger =new Log4jImpl(DeleteAreaAction.class);
    
    //输入
    
	/**
	 * 行政区域接口
	 */
	@Autowired
	private IFiveGradeArea fiveGradeAreaService;
	
	/**
	 * 行政区域的编码
	 */
	private String code;
	
	/**
	 * 行政区域的级别 province  city  county  town  village
	 */
	private String grade;
	
	/**
	 * 行政区域的名称
	 */
	private String areaName;
	
	/**
	 * 行政区域的排序号
	 */
	private String orderNum;
	
	/**
	 * 行政区域的名称是否表示的是一个具体的地址
	 */
	private String isRealName;
	
	//输出
	
	/**
	 * 功能：删除
	 * @return
	 */
	public String deleteArea(){
		UserInfo userInfo = (UserInfo) this.getSession().getAttribute("adminUser");
		try{
			if(userInfo!=null){
				Integer count = fiveGradeAreaService.getSubRegionCountByCodeAndGrade(code, getSubGrade(grade));
				if(count>0){
					ajax("如果想删除"+areaName+"，请先删除所有的下级区域。");
					logger.info("行政区域模块   删除操作。删除操作参数内容： [code="+ code + ",areaName=" + areaName + ",orderNum=" +orderNum + ",grade=" + grade + ",isRealName=" + isRealName + "] ，如果想删除"+areaName+"，请先删除所有的下级区域。当前操作人：" + userInfo.getUserName() + "( ID: " + userInfo.getUserId() +" ) ");
				}else{
					fiveGradeAreaService.deleteRegionByCodeAndGrade(code, grade);
					logger.info("行政区域模块   删除操作。删除内容： [code="+ code + ",areaName=" + areaName + ",orderNum=" +orderNum + ",grade=" + grade + ",isRealName=" + isRealName + "] 。当前操作人：" + userInfo.getUserName() + "( ID: " + userInfo.getUserId() +" ) ");
					ajax("0");
				}
			}
		}catch(Exception e){
			ajax("1");
			logger.error("行政区域模块  deleteArea方法 error. 当前操作人：" + userInfo.getUserName() + "( ID: " + userInfo.getUserId() +" ) ",e);
		}
		return null;
	}
	
	/**
	 * 根据当前级别，返回下一级别。
	 * @param grade
	 * @return
	 */
	public String getSubGrade(String grade) {
		if(grade==null){
			return null;
		}
		if("province".equals(grade)){
			return "city";
		}else if("city".equals(grade)){
			return "county";
		}else if("county".equals(grade)){
			return "town";
		}else if("town".equals(grade)){
			return "village";
		}else if("village".equals(grade)){
			return "village_d";
		}
		return null;
	}
	
	/* getter setter */
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	public String getIsRealName() {
		return isRealName;
	}
	public void setIsRealName(String isRealName) {
		this.isRealName = isRealName;
	}
	
}
