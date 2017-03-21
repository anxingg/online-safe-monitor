package cn.com.qytx.hotline.mis.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.qytx.hotline.mis.domain.PhoneAttribution;
import cn.com.qytx.hotline.mis.service.IPhoneAttribution;
import cn.com.qytx.monitor.client.log.MonitorLogger;
import cn.com.qytx.monitor.client.logImpl.Log4jImpl;
import cn.com.qytx.platform.base.action.BaseActionSupport;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Sort;
import cn.com.qytx.platform.base.query.Sort.Direction;
import cn.com.qytx.platform.org.domain.UserInfo;

/**
 * 功能:查询列表
 * 版本: 1.0
 * 开发人员: 张东领
 * 创建日期: 2014-12-17
 * 修改日期: 2014-12-17
 * 修改列表:
 */
public class PhoneAttributionListAction extends BaseActionSupport {
	/**
	 * 描述含义
	 */
	private static final long serialVersionUID = 4112829755319529924L;
	
	/**
     * log4j日志对象
     */
	 private final static MonitorLogger logger =new Log4jImpl(PhoneAttributionListAction.class);
	
	/**
	 * 号码段维护接口
	 */
	@Autowired
	transient IPhoneAttribution paService;
	
	/**
	 * 关键字
	 */
	private String searchKey;
	
	/**
	 * 功能：得到列表数据
	 * @return
	 */
	public String list(){
		UserInfo userInfo = (UserInfo) this.getSession().getAttribute("adminUser");
		try {
			if(userInfo!=null){
				Page<PhoneAttribution> pageInfo = paService.findByPage(getPageable(new Sort(Direction.DESC,"id")), searchKey);
				//得到结果
				List<PhoneAttribution> paList = pageInfo.getContent();
				//封装list
				List<Map<String, Object>> mapList = new ArrayList<Map<String,Object>>();
				if(paList!=null){
					int i = getPageable().getPageNumber() * getPageable().getPageSize()+1;
					for(PhoneAttribution pa : paList){
						Map<String, Object> map = new HashMap<String, Object>();
						//标识符
						map.put("vid", pa.getId());
						//序号
						map.put("orderNumber", i);
						//号码段
						String mobileNumber = StringUtils.defaultString(pa.getMobileNumber(), "");
						map.put("mobileNumber", mobileNumber);
						//归属区域
						String mobileArea = StringUtils.defaultString(pa.getMobileArea(), "");
						map.put("mobileArea", mobileArea);
						//手机卡类型
						String mobileType = StringUtils.defaultString(pa.getMobileType(), "");
						map.put("mobileType", mobileType);
						//区号
						String areaCode = StringUtils.defaultString(pa.getAreaCode(), "");
						map.put("areaCode", areaCode);
						//邮编
						String postCode = StringUtils.defaultString(pa.getPostCode(), "");
						map.put("postCode", postCode);
						mapList.add(map);
						i++;
					}
				}
				this.ajaxPage(pageInfo, mapList);
			}
		} catch (Exception e) {
			logger.error("号码段维护模块  list方法 error. 当前操作人：" + userInfo.getUserName() + "( ID: " + userInfo.getUserId() +" ) ",e);
		}
		return null;
	}

	public String getSearchKey() {
		return searchKey;
	}
	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	
}
