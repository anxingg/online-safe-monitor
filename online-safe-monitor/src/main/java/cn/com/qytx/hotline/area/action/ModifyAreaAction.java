package cn.com.qytx.hotline.area.action;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.qytx.hotline.area.service.IFiveGradeArea;
import cn.com.qytx.monitor.client.log.MonitorLogger;
import cn.com.qytx.monitor.client.logImpl.Log4jImpl;
import cn.com.qytx.platform.base.action.BaseActionSupport;
import cn.com.qytx.platform.org.domain.UserInfo;
/**
 * 功能: 修改区域
 * 版本: 1.0
 * 开发人员: 李立泼
 * 创建日期: 2014-11-11
 * 修改人员：张东领
 * 修改日期: 2015-2-11
 * 修改列表:
 */
public class ModifyAreaAction extends BaseActionSupport {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -1508931453494331278L;
	
	/**
     * log4j日志对象
     */
	 private final static MonitorLogger logger =new Log4jImpl(ModifyAreaAction.class);
    
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
	
	/**
     * 有没有修改行政区域的名称（0 没有修改名,1 修改了名 ）
     */
	private String i;
	
	/**
	 * 功能：修改
	 * @return
	 */
	public String modifyArea(){
		UserInfo userInfo = (UserInfo) this.getSession().getAttribute("adminUser");
		try{
			if(userInfo!=null){
				String orderNumtemp=orderNum;
				if("1".equals(i)){
					orderNumtemp="";
					String subCode = getSubCodeByCodeAndGrade(code,getParentGrade(grade));
					Integer count=fiveGradeAreaService.getCurRegionCountByCodeAndGradeAndRegionName(subCode,grade, areaName,orderNumtemp);
					if(count>0){
						ajax(areaName+"已存在，不能重名。");
						logger.info("行政区域模块  修改操作。" + areaName+"已存在，不能重名。 当前操作人：" + userInfo.getUserName() + "( ID: " + userInfo.getUserId() +" ) ");
						return null;
					}
				}
				fiveGradeAreaService.updateRegionByGrade(code, areaName, orderNum, grade);
				logger.info("行政区域模块  修改操作。修改内容： [code="+ code + ",areaName=" + areaName + ",orderNum=" +orderNum + ",grade=" + grade +"] 当前操作人：" + userInfo.getUserName() + "( ID: " + userInfo.getUserId() +" ) ");
				ajax("0");
			}
		}catch(Exception e){
			ajax("1");
			logger.error("行政区域模块  deleteArea方法 error. 当前操作人：" + userInfo.getUserName() + "( ID: " + userInfo.getUserId() +" ) ",e);
		}
		return null;
	}
	
	/**
	 * 截取code
	 * @param code 行政区域的编码
	 * @param grade 行政区域的级别
	 * @return 截取过的行政区域的编码
	 */
	public String getSubCodeByCodeAndGrade(String code,String grade){
		if(code==null || "".equals(code) || grade==null || "".equals(grade)){
			return null;
		}
		try{
			if("province".equals(grade)){ //省
				return code.substring(0, 2);
			}else if("city".equals(grade)){ //市
				return code.substring(0, 4);
			}else if("county".equals(grade)){ //县
				return code.substring(0, 6);
			}else if("town".equals(grade)){ //乡
				return code.substring(0, 9);
			}else if("village".equals(grade)){ //村
				return code.substring(0, 12);
			}
		}catch(Exception e){
			logger.error("行政区域模块  getSubCodeByCodeAndGrade方法 error. ", e);
			return null;
		}
		return null;
	}
	
	/**
	 * 根据当前级别，返回上一级别。
	 * @param grade 行政区域的级别
	 * @return
	 */
	public String getParentGrade(String grade) {
		if(grade==null){
			return null;
		}
		if("city".equals(grade)){
			return "province";
		}else if("county".equals(grade)){
			return "city";
		}else if("town".equals(grade)){
			return "county";
		}else if("village".equals(grade)){
			return "town";
		}else{
			return "";
		}
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

	public String getI() {
		return i;
	}

	public void setI(String i) {
		this.i = i;
	}
	
}
