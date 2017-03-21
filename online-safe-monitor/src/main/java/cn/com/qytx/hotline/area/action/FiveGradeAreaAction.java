package cn.com.qytx.hotline.area.action;

import java.io.PrintWriter;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.qytx.hotline.area.service.IFiveGradeArea;
import cn.com.qytx.monitor.client.log.MonitorLogger;
import cn.com.qytx.monitor.client.logImpl.Log4jImpl;
import cn.com.qytx.platform.base.action.BaseActionSupport;
import cn.com.qytx.platform.org.domain.UserInfo;

import com.google.gson.Gson;
/**
 * 功能:  获得各级地址
 * 版本: 1.0
 * 开发人员: 李立泼
 * 创建日期: 2014-11-11
 * 修改人员：张东领
 * 修改日期: 2015-2-11
 * 修改列表:
 */
public class FiveGradeAreaAction extends BaseActionSupport {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -7309167190557568356L;
	
	/**
     * log4j日志对象
     */
	 private final static MonitorLogger logger =new Log4jImpl(FiveGradeAreaAction.class);
    
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
	
	//输出
	
	/**
	 * 功能：得到地址
	 * @return
	 */
	public String getAddress(){
		PrintWriter out = null;
		UserInfo userInfo = getLoginUser();
		try{
			if(userInfo!=null){
				this.getResponse().setCharacterEncoding("UTF-8");
				out = new PrintWriter(this.getResponse().getWriter());
				Object result;
				if(!"".equals(code)){
					//根据地区code和地区的行政级别查询其下一级的行政地区;有直辖的，越级查询。
					result=fiveGradeAreaService.findSubRegionsByGrade(code, grade);
				}else{ //code为空时取省级的所有对象。
					result=fiveGradeAreaService.getProvinceList();
				}
				Gson json = new Gson();
				String jsons = json.toJson(result);
				out.print(jsons);
				out.close();
			}
		}catch(Exception e){
			logger.error("行政区域模块  getAddress方法 error. 当前操作人：" + userInfo.getUserName() + "( ID: " + userInfo.getUserId() +" ) ",e);
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
	
}
