package cn.com.qytx.hotline.org.action;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.qytx.hotline.org.service.IHotlineGroup;
import cn.com.qytx.platform.base.action.BaseActionSupport;
import cn.com.qytx.platform.org.domain.GroupInfo;
import cn.com.qytx.platform.org.domain.UserInfo;
/**
 * 功能:更新部门的is_fork_group字段
 * 版本: 1.0
 * 开发人员: 张东领
 * 创建日期: 2015年4月9日
 * 修改人员：
 * 修改日期: 
 * 修改列表:
 */
public class UpdateGroupAction extends BaseActionSupport{
	
	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -6624192790845981692L;
	/**
	 * log4j
	 */
	private static final Logger logger = Logger.getLogger(UpdateGroupAction.class);
	/**
	 * 部门接口
	 */
	@Autowired
	private IHotlineGroup groupService;
	
	/**
	 * 部门名称
	 */
	private String groupName;
	/**
	 * 部门实体
	 */
	private GroupInfo groupInfo;

	/**
	 * 功能：更新部门is_fork_group字段
	 * 开发人员:张东领
	 * 创建日期:2015年4月9日
	 * 修改人员:
	 * 修改日期:
	 * @return
	 */
	public String updateGroupIsforkgroup(){
		try {
			if(StringUtils.isNotBlank(groupName)){
				groupInfo = groupService.findByName(groupName);
				if(groupInfo!=null){
					Integer isForkGroup = null;
//					String path = groupInfo.getPath();
//					if(StringUtils.isNotBlank(path)){
//						if(path.indexOf(",")>-1){//存在上级部门，例：2,10,101
//							String[] pathArray = path.split(",");
//							isForkGroup = Integer.parseInt(pathArray[0]);
//						}else{
//							isForkGroup = Integer.parseInt(path);
//						}
//					}
					UserInfo userInfo = getLoginUser();
					if(userInfo!=null&&userInfo.getIsForkGroup()!=null){
						isForkGroup = userInfo.getIsForkGroup();
					}
					if(isForkGroup!=null){
						groupInfo.setIsForkGroup(isForkGroup);
					}
					groupService.saveOrUpdate(groupInfo);
					ajax("0");
				}
			}
		} catch (Exception e) {
			logger.error("更新部门is_fork_group字段异常！方法:updateGroupIsforkgroup()", e);
		}
		return null;
	}
	
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public GroupInfo getGroupInfo() {
		return groupInfo;
	}
	public void setGroupInfo(GroupInfo groupInfo) {
		this.groupInfo = groupInfo;
	}
}
