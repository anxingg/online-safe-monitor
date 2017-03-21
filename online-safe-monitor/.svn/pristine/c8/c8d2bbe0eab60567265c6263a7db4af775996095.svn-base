/**
 * 
 */
package cn.com.qytx.hotline.init;

import org.springframework.beans.factory.annotation.Autowired;

import cn.com.qytx.hotline.init.service.IInitKXH;
import cn.com.qytx.monitor.client.log.MonitorLogger;
import cn.com.qytx.monitor.client.logImpl.Log4jImpl;
import cn.com.qytx.platform.base.action.BaseActionSupport;

/**
 * 功能: 初始化开销户的一些信息
 * 版本: 1.0 
 * 开发人员: 李立泼 
 * 创建日期: 2015-04-11 
 * 修改日期: 修改列表:
 */
public class InitKXHAction extends BaseActionSupport {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 4587883297895601241L;

	/**
	 * log日志
	 */
	private final static MonitorLogger logger =new Log4jImpl(InitKXHAction.class);

	/**
	 * 初始化开销户的接口
	 */
	@Autowired
	private transient IInitKXH initKXHService;

	/**
	 * 公司ID
	 */
	private Integer companyId;

	public String execute() {
		if (companyId != null) {
			logger.info("开销户系统开户后，传过来的参数：companyId = " + companyId);
			
			//根据公司ID，更新部门的is_fork_group字段，并返回部门ID
			Integer groupId = initKXHService.updateGroupInfoByCompnayId(companyId);
			
			//根据公司ID，更新人员的is_fork_group字段
			initKXHService.updateUserInfoByCompnayId(companyId, groupId);
			
			//根据公司ID，更新tb_data_filter_rule表的is_fork_group字段
			if(groupId != null){
				initKXHService.updateDataFilterRuleByCompnayId(companyId, groupId);
			}
		}else{
			logger.info("开销户系统开户后，传过来的参数：companyId 为空。");
		}
		return null;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

}
