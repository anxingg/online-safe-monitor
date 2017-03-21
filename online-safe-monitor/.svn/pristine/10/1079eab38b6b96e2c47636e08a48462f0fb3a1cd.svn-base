package cn.com.qytx.hotline.mis.action;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.qytx.hotline.mis.domain.PhoneAttribution;
import cn.com.qytx.hotline.mis.service.IPhoneAttribution;
import cn.com.qytx.monitor.client.log.MonitorLogger;
import cn.com.qytx.monitor.client.logImpl.Log4jImpl;
import cn.com.qytx.platform.base.action.BaseActionSupport;
import cn.com.qytx.platform.org.domain.UserInfo;

/**
 * 功能:新增，修改，删除
 * 版本: 1.0
 * 开发人员: 张东领
 * 创建日期: 2014-12-17
 * 修改日期: 2014-12-17
 * 修改列表:
 */
public class PhoneAttributionAction extends BaseActionSupport {
	/**
	 * 描述含义
	 */
	private static final long serialVersionUID = 2600000927582514356L;
	
	/**
     * log4j日志对象
     */
	 private final static MonitorLogger logger =new Log4jImpl(PhoneAttributionAction.class);
    
	/**
	 * 号码段接口
	 */
	@Autowired
	private transient IPhoneAttribution paService;
	
	/**
     * 常量字符串1 
     */
    private static final String STR1 = "( ID: ";
    
	/**
	 * 常量字符串2
	 */
	private static final String STR2 = " ) ";
	
	//输入
	/**
	 * 
	 */
	private Integer id;
	
	/**
	 * 号码段
	 */
	private String mobileNumber;
	
	/**
	 * 实体类
	 */
	private PhoneAttribution pa;
	
	
	/**
	 * 功能：根据号码段查询
	 * @return
	 */
	public Integer findByMobileNumber(){
		UserInfo userInfo = getLoginUser();
		try {
			if(StringUtils.isNotBlank(mobileNumber)){
				PhoneAttribution patt = paService.findByMobileNumber( id, mobileNumber);
				if(patt!=null){
					ajax(0);
				}else{
					ajax(1);
				}
			}
		} catch (Exception e) {
			ajax(0);
			logger.error("号码段维护模块  findByMobileNumber方法 error. 当前操作人：" + userInfo.getUserName() + STR1 + userInfo.getUserId() +STR2,e);
		}
		return null;
	}
	
	/**
	 * 功能：跳转到修改页面
	 * @return
	 */
	public String toModiftPage(){
		UserInfo userInfo = getLoginUser();
		try {
			if(id!=null){
				//李立泼 2015年04月09日替换了根据ID进行查询的方法，新替换的方法没有用到数据权限。
				//pa = paService.findOne(id);
				pa = paService.findById(id);
			}
		} catch (Exception e) {
			logger.error("号码段维护模块  toModiftPage方法 error. 当前操作人：" + userInfo.getUserName() + STR1 + userInfo.getUserId() +STR2,e);
		}
		return SUCCESS;
	}
	
	/**
	 * 功能：新增和修改
	 * @return
	 */
	public Integer addOrModify(){
		UserInfo userInfo = getLoginUser();
		try {
			if(id!=null){//修改
				//李立泼 2015年04月09日替换了根据ID进行查询的方法，新替换的方法没有用到数据权限。
				//PhoneAttribution patt = paService.findOne(id);
				PhoneAttribution patt = paService.findById(id);
				patt.setMobileNumber(pa.getMobileNumber());
				patt.setMobileArea(pa.getMobileArea());
				patt.setAreaCode(pa.getAreaCode());
				paService.saveOrUpdate(patt);
				logger.info("号码段维护模块   修改操作。修改内容： " + patt + " 。当前操作人：" + userInfo.getUserName() + STR1 + userInfo.getUserId() +STR2);
				ajax(0);
			}else{//新增
				PhoneAttribution patt = new PhoneAttribution();
				patt.setMobileNumber(pa.getMobileNumber());
				patt.setMobileArea(pa.getMobileArea());
				patt.setAreaCode(pa.getAreaCode());
				paService.saveOrUpdate(patt);
				logger.info("号码段维护模块   新增操作。新增内容： " + patt + " 。当前操作人：" + userInfo.getUserName() + STR1 + userInfo.getUserId() +STR2);
				ajax(0);
			}
		} catch (Exception e) {
			logger.error("号码段维护模块  addOrModify方法 error. 当前操作人：" + userInfo.getUserName() + STR1 + userInfo.getUserId() +STR2,e);
		}
		return null;
	}
	
	/**
	 * 功能：删除
	 * @return
	 */
	public Integer delete(){
		UserInfo userInfo = getLoginUser();
		try {
			if(id!=null){
				//李立泼 2015年04月09日替换了根据ID进行查询的方法，新替换的方法没有用到数据权限。
				//pa = paService.findOne(id);
				pa = paService.findById(id);
				if(pa!=null){
					String paInfo = pa.toString();
					Integer dp = paService.delete(id, true);
					logger.info("号码段维护模块   删除操作。删除内容： " + paInfo + " 。当前操作人：" + userInfo.getUserName() + STR1 + userInfo.getUserId() +STR2);
					ajax(dp);
				}
			}
		} catch (Exception e) {
			logger.error("号码段维护模块  delete方法 error. 当前操作人：" + userInfo.getUserName() + STR1 + userInfo.getUserId() +STR2,e);
		}
		return null;
	} 
	
	/* getter setter */
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public PhoneAttribution getPa() {
		return pa;
	}
	public void setPa(PhoneAttribution pa) {
		this.pa = pa;
	}
}
