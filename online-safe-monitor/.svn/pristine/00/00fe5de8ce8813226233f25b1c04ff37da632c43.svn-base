package cn.com.qytx.hotline.area.action;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.qytx.hotline.area.service.IFiveGradeArea;
import cn.com.qytx.monitor.client.log.MonitorLogger;
import cn.com.qytx.monitor.client.logImpl.Log4jImpl;
import cn.com.qytx.platform.base.action.BaseActionSupport;
import cn.com.qytx.platform.org.domain.UserInfo;
/**
 * 功能: 新增区域
 * 版本: 1.0
 * 开发人员: 李立泼
 * 创建日期: 2014-11-11
 * 修改人员：张东领
 * 修改日期: 2015-2-11
 * 修改列表:
 */
public class AddNewAreaAction extends BaseActionSupport {

	/**
     * 序列号
     */
    private static final long serialVersionUID = -8403084335357890568L;
    
    /**
     * log4j日志对象
     */
    private final static MonitorLogger logger =new Log4jImpl(AddNewAreaAction.class);
    
    /**
     * 常量字符串1 
     */
    private static final String STR1 = "( ID: ";
    
	/**
	 * 常量字符串2
	 */
	private static final String STR2 = " ) ";
    
    /**
     * 常量值，表示市
     */
    private static final String CITY = "city";
    
    /**
     * 常量值，表示县
     */
    private static final String COUNTY = "county";
    
    /**
     * 常量值，表示乡
     */
    private static final String TOWN = "town";
    
    /**
     * 常量值，表示村
     */
    private static final String VILLAGE = "village";
    
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
	 * 功能：添加新区域
	 * @return
	 */
	public String addNewArea(){
		UserInfo userInfo = (UserInfo) this.getSession().getAttribute("adminUser");
		try{
			if(userInfo!=null){
				if(Integer.parseInt(orderNum)>999){
					ajax("排序号不能超过3位数。");
					logger.info("行政区域模块   新增操作。新增操作参数内容： [code="+ code + ",areaName=" + areaName + ",orderNum=" +orderNum + ",grade=" + grade + ",isRealName=" + isRealName + "] ，排序号不能超过3位数。当前操作人：" + userInfo.getUserName() + STR1 + userInfo.getUserId() +STR2);
					throw new Exception("排序号不能超过3位数。");
				}
				//code需要重新生成
				//查询grade对应的表下的记录数据
				
				Integer haveThisNameCount = fiveGradeAreaService.getSubRegionCountByCodeAndGradeAndRegionName(code, grade, areaName);
				if(haveThisNameCount>0){
					ajax("已经存在"+areaName+"，不能重复添加。");
					logger.info("行政区域模块   新增操作。新增操作参数内容： [code="+ code + ",areaName=" + areaName + ",orderNum=" +orderNum + ",grade=" + grade + ",isRealName=" + isRealName + "] ，已经存在" + areaName + "，不能重复添加。当前操作人：" + userInfo.getUserName() + STR1 + userInfo.getUserId() +STR2);
					return null;
				}
				Integer count = fiveGradeAreaService.getSubRegionCountByCodeAndGrade(code, grade);
				
				String NewCode=getNewCode(code,count);
				fiveGradeAreaService.insertRegionByGrade(NewCode, areaName, grade, orderNum, isRealName);
				logger.info("行政区域模块   新增操作。新增内容： [code="+ NewCode + ",areaName=" + areaName + ",orderNum=" +orderNum + ",grade=" + grade + ",isRealName=" + isRealName + "] 。当前操作人：" + userInfo.getUserName() + STR1 + userInfo.getUserId() +STR2);
				ajax("0");
			}
		}catch(Exception e){
			logger.error("行政区域模块  addNewArea方法 error. 当前操作人：" + userInfo.getUserName() + STR1 + userInfo.getUserId() +STR2,e);
		}
		return null;
	}
	
	/**
	 * 生成新的编号
	 * @param code2
	 * @param count
	 * @return  如 41 05 23 106 201  （12位）
	 * @throws Exception 
	 */
	private String getNewCode(String code2,Integer count) throws Exception {
		//code2是上一级的完整code
		String subCode=getSubCodeByCodeAndGrade(code2,getParentGrade(grade));
		count++;
		if(count>=90){
			ajax("新增区域的数量超出范围。");
			throw new Exception("新增数量超出范围。");
		}
		StringBuffer sbf=new StringBuffer(subCode);
		sbf.append(count.toString());
		while(sbf.length()<12){
			sbf.append("0");
		}
		return sbf.toString();
	}
	
	/**
	 * 根据当前级别，返回上一级别。
	 * @param grade
	 * @return
	 */
	public String getParentGrade(String grade) {
		if(grade==null){
			return null;
		}
		if(CITY.equals(grade)){
			return "province";
		}else if(COUNTY.equals(grade)){
			return CITY;
		}else if(TOWN.equals(grade)){
			return COUNTY;
		}else if(VILLAGE.equals(grade)){
			return TOWN;
		}else{
			return "";
		}
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
			return CITY;
		}else if(CITY.equals(grade)){
			return COUNTY;
		}else if(COUNTY.equals(grade)){
			return TOWN;
		}else if(TOWN.equals(grade)){
			return VILLAGE;
		}else if(VILLAGE.equals(grade)){
			return VILLAGE;
		}
		return null;
	}
	
	/**
	 * 截取code
	 * @param code
	 * @param grade
	 * @return
	 */
	public String getSubCodeByCodeAndGrade(String code,String grade){
		if(code==null || "".equals(code) || grade==null || "".equals(grade)){
			return null;
		}
		try{
			if("province".equals(grade)){ //省
				return code.substring(0, 2);
			}else if(CITY.equals(grade)){ //市
				return code.substring(0, 4);
			}else if(COUNTY.equals(grade)){ //县
				return code.substring(0, 6);
			}else if(TOWN.equals(grade)){ //乡
				return code.substring(0, 9);
			}else if(VILLAGE.equals(grade)){ //村
				return code.substring(0, 12);
			}
		}catch(Exception e){
			logger.error("行政区域模块  getSubCodeByCodeAndGrade方法 error. ",e);
			return null;
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
