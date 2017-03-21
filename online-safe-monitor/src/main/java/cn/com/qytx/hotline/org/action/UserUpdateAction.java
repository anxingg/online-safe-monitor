package cn.com.qytx.hotline.org.action;

import java.net.URLDecoder;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.qytx.hotline.ivr.domain.Msiuser;
import cn.com.qytx.hotline.ivr.service.IMsiUser;
import cn.com.qytx.hotline.org.service.IHotlineUser;
import cn.com.qytx.platform.base.action.BaseActionSupport;
import cn.com.qytx.platform.log.domain.Log;
import cn.com.qytx.platform.log.service.ILog;
import cn.com.qytx.platform.log.service.LogType;
import cn.com.qytx.platform.org.domain.GroupInfo;
import cn.com.qytx.platform.org.domain.GroupUser;
import cn.com.qytx.platform.org.domain.RoleInfo;
import cn.com.qytx.platform.org.domain.UserInfo;
import cn.com.qytx.platform.org.service.IGroup;
import cn.com.qytx.platform.org.service.IGroupUser;
import cn.com.qytx.platform.org.service.IRole;
import cn.com.qytx.platform.org.service.IRoleUser;
import cn.com.qytx.platform.org.service.IUser;
import cn.com.qytx.platform.utils.encrypt.MD5;
import cn.com.qytx.platform.utils.pinyin.Pinyin4jUtil;
import cn.com.qytx.platform.utils.validate.Validate;

/**
 * <br/>
 * 功能:更新人员信息 <br/>
 * 版本: 1.0 <br/>
 * 开发人员: 任 <br/>
 * 创建日期: 2013-4-9 <br/>
 * 修改日期: 2013-4-9 <br/>
 * 修改列表:
 */
public class UserUpdateAction extends BaseActionSupport {

	/**
	 * 描述含义
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String SUCCESS = "success";
	/**
	 * 用户信息
	 */
	@Resource(name = "userService")
	private transient IUser userService;
	/**
	 * 部门人员信息
	 */
	@Resource(name = "groupUserService")
	IGroupUser groupUserService;

	/**
	 * 角色人员信息
	 */
	@Resource(name = "roleUserService")
	private transient IRoleUser roleUserService;

	/**
	 * 部门,群组管理接口
	 */
	@Resource
	private IGroup groupService;

	@Resource
	private IRole roleService;

	/** 系统日志接口 */
	@Resource
	private ILog logService;
	@Autowired
	private transient IMsiUser msiuser;
	/**
	 * 呼叫中心用户接口
	 */
	@Autowired
	private IHotlineUser hotlineUserService;

	// 用户
	private UserInfo user;
	// 组id
	private int groupId;
	// 主角色id字符串
	private String roleIds;
	//主角色code字符串
	private String roleCodes;
	// 辅助角色id字符串
	private String assistIds;

	// 管理范围
	private Integer managementRange;
	// 管理组Id集合
	private String appointGroupIds;
	// 管理组名集合
	private String appointGroupNames;
	// 操作类型 根据type区分是维护通讯簿还是维护登录用户
	private String type;

	// 修改时,修改前的用户ID
	private Integer oldUserId;
	/**
	 * 角色名称
	 */
	private String roleNames;
	private String birthDay;

	/**
	 * 坐席类型,是否为离线坐席
	 */
	private Integer seatType;
    private String userIds = "";
    
    private Integer userId;
    private String userName;
    /**
     * 人员电话
     */
    private String phone;
    /**
     * 角色ID
     */
    private Integer roleId;
    
	/**
	 * 更新人员
	 * 
	 * @return
	 */
	public String updateUser() {
		firstReturn(type, user);

		// 判断姓名和电话不能同时重复
		UserInfo existUser = userService.getUserByNamePhone(getLoginUser()
				.getCompanyId(), user.getUserName(), user.getPhone());
		UserInfo userOld = userService.findOne(user.getUserId());

		if (null != existUser
				&& existUser.getUserName().equals(user.getUserName())
				&& existUser.getPhone().equals(user.getPhone())
				&& userOld.getUserId().intValue() != existUser.getUserId()
						.intValue()) {
			ajax("该成员已存在！");
			return null;
		}

		UserInfo tmp = userService.getUserByUserName(user.getLoginName());
		boolean isExist = userOld.getLoginName().equals(user.getLoginName());
		if (!isExist && tmp != null) {
			ajax("登录名已经存在！");
			return null;
		}
		if (groupId == 0) {
			ajax("请选择部门！");
			return null;
		}
		if (StringUtils.isNotBlank(user.getLoginName())) {
			userOld.setLoginName(user.getLoginName());
		}
		userOld.setUserName(user.getUserName());
		userOld.setOrderIndex(user.getOrderIndex());
		userOld.setAlterName(user.getAlterName());
		userOld.setSex(user.getSex());
		
		settingBirthday(birthDay, userOld);
		
		userOld.setPhone(user.getPhone());
		userOld.setPhonePublic(user.getPhonePublic());
		userOld.setPhone2(user.getPhone2());
		userOld.setEmail(user.getEmail());
		userOld.setSignType(user.getSignType());
		userOld.setSinWidget(user.getSinWidget());
		userOld.setOfficeWidget(user.getOfficeWidget());
		userOld.setTaoDa(user.getTaoDa());
		userOld.setPhoto(user.getPhoto());
		userOld.setJob(user.getJob());
		if (user.getSignUrl() != null && !user.getSignUrl().equals("")) {
			userOld.setSignUrl(user.getSignUrl());
		}
		if (user.getNtkoUrl() != null && !user.getNtkoUrl().equals("")) {
			userOld.setNtkoUrl(user.getNtkoUrl());
		}

		String py = Pinyin4jUtil.getPinYinHeadChar(user.getUserName());
		userOld.setPy(py);

		userService.saveOrUpdate(userOld);

		if (user.getUserId() > 0) {
			// 添加部门/群众人员对应关系
			addGroupUser(groupId, user);
			ajax(SUCCESS);
			return null;
		} else {
			ajax("人员修改失败！");
			return null;
		}

	}
	
	private String firstReturn(String type,UserInfo user){
		if (StringUtils.isEmpty(type)) {
			// 如果登录名不为空
			if (StringUtils.isNotEmpty(user.getLoginName())) {
				if (!Validate.isLoginName(user.getLoginName())) {
					ajax("用户名必须为数字、字母或者下划线！");
					return null;
				}
			} else {
				ajax("登录名不能为空！");
				return null;
			}
			if (StringUtils.isNotEmpty(user.getLoginPass())&&!Validate.isLoginPass(user.getLoginPass())) {
				ajax("密码格式不正确, 长度6-16位，区分大小写，只能使用字母、数字！");
				return null;
			}
		}

		if (StringUtils.isNotEmpty(user.getEmail())&&!Validate.isEmail(user.getEmail())) {
			ajax("邮箱格式不正确！");
			return null;
		}
		if (StringUtils.isNotEmpty(user.getPhone())&&!Validate.isPhone(user.getPhone())) {
			ajax("手机格式不正确！");
			return null;
		}
		
		List<UserInfo> us = userService.findUsersByPhone(user.getPhone());
		if (us != null && us.size() > 0) {
			for (int i = 0; i < us.size(); i++) {
				UserInfo u = us.get(i);
				if ((u.getUserId().intValue() != user.getUserId())
						&& (u.getPhone().equals(user.getPhone()))) {
					ajax("手机号码不能重复!");
					return null;
				}
			}
		}
		return null;
	}
	
	private void settingBirthday(String birthDay,UserInfo userOld){
		if (StringUtils.isNotBlank(birthDay)) {
			try {
				userOld.setBirthDay(DateUtils.parseDate(birthDay, "yyyy-MM-dd"));
			} catch (ParseException e) {
				LOGGER.error(e.getMessage());
			}
		} else {
			userOld.setBirthDay(null);
		}
	}
	
	private void addGroupUser(Integer groupId,UserInfo user){
		GroupUser gu = new GroupUser();
		gu.setGroupId(groupId);
		gu.setUserId(user.getUserId());
		gu.setCompanyId(getLoginUser().getCompanyId());
//		groupUserService.deleteGroupUserByUserIds(user.getUserId()
//				.toString());
		groupUserService.saveOrUpdate(gu);
	}
	
	
	public String onlyUpdateMsiUserName() {
		try{
			String userIdStr = this.getRequest().getParameter("userId");
			String userNameStr = this.getRequest().getParameter("userName");
			userNameStr = URLDecoder.decode(userNameStr, "utf-8");
			if( StringUtils.isNotBlank(userIdStr) ){
				Integer userId = Integer.parseInt(userIdStr);
				UserInfo userNew = userService.findOne(userId);
				if( userNew != null ){
					Msiuser mu = msiuser.getMsiuerByWorkNo( userNew.getLoginName() );
					mu.setName( userNameStr );
					msiuser.saveOrUpdate(mu);
					ajax("1");//修改成功
				}else{
					ajax("0");//修改失败
				}
			}else{
				ajax("0");//修改失败
			}
		}catch(Exception e){
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**	
	 * 功能：新增或者修改登录用户管理
	 */
	public String updateLoginUser() {
		// 如果登录名不为空
		if (StringUtils.isNotEmpty(user.getLoginName())) {
			if (!Validate.isLoginName(user.getLoginName())) {
				ajax("用户名必须为数字、字母或者下划线！");
				return null;
			}
		} else {
			ajax("登录名不能为空！");
			return null;
		}
		UserInfo userOld = userService.findOne(user.getUserId());
		String oldLoginName = "";
//		if(userOld.getIsForkGroup() != getLoginUser().getIsForkGroup()){
//			ajax("nolimit");
//			return null;
//		}
		// 修改时,也需要按照新增处理,原因是可以选择其他用户
		oldLoginName = returnUpdate(type, userOld);
		// 需要判断登录名是否重复
		UserInfo tmp = userService.findByLoginName(user.getLoginName());
		boolean isExist = userOld.getLoginName().equals(user.getLoginName());
		if (!isExist && tmp != null) {
			ajax("user.loginName_is_exist");
			return null;
		}

		// 设置登录名和密码
		String ln = user.getLoginName();
		userOld.setLoginName(ln);
		MD5 md5 = new MD5();
		if (StringUtils.isNotBlank(user.getLoginPass())) {
			String pass = md5.encrypt(user.getLoginPass());
			userOld.setLoginPass(pass);
		}
		userOld.setUserState(user.getUserState());
		if (userOld.getRegisterTime() == null) {
			userOld.setRegisterTime(new Date());
		}
		userService.saveOrUpdate(userOld);

		// 如果角色为坐席或者坐席班长
		boolean isSeat = returnIsSeat(roleIds);

		if ("add".equals(type)) {
			// 同时需要在MsiUser表中添加数据,登录名和密码保持一致
			// 同时新增ivr坐席人员表信息
			if (isSeat) {
				// 保存ivr坐席人员表
				isSeatAdd(userOld);
				
				// 同时维护MSIServiceUser关联表
				// MsiserviceUser msiserviceUserBean = new MsiserviceUser();
				// msiserviceUserBean.setMsiid(mu.getVid());
				// msiserviceUserBean.setServiceId(msi.getVid());
				// msiserviceUserBean.setCompanyId(1);
				// iMsiserviceUser.save(msiserviceUserBean);
			}
		} else {
			// 如果角色为呼叫中心
			if (isSeat) {
				isSeatUpdate(userOld, oldLoginName);
			}
		}

		if (user.getUserId() > 0) {
			addLog(roleIds, type);
			ajax(SUCCESS);
			return null;
		} else {
			ajax("人员登录设置失败！");
			return null;
		}

	}
	
	private String returnUpdate(String type,UserInfo userOld){
		String oldLoginName = "";
		if ("update".equals(type) && null != oldUserId
				&& oldUserId.intValue() != user.getUserId().intValue()) {
			// 删除by lhw 原因是 配置新登录用户时, 状态一直是0 导致,不能配置登录用户, 暂时没有发现此代码的用处
			// Integer userState = userOld.getUserState();
			// if (userState.intValue() != UserInfo.USERSTATE_UNLOGIN)
			// {
			// ajax("user.user_is_exist");
			// return null;
			// }
			oldLoginName = userOld.getLoginName();
			// 清空原用户登录信息
			UserInfo original = userService.findOne(oldUserId.intValue());
			original.setUserState(UserInfo.USERSTATE_UNLOGIN);

			// 更新用户名和密码信息,保证用户不能再登陆
			original.setLoginPass("");
			original.setLoginName(UUID.randomUUID().toString());
//			original.setIsForkGroup(getLoginUser().getIsForkGroup());
			userService.saveOrUpdate(original);

			// 同时删除该用户配置的角色信息
			roleUserService.saveRoleUsersByRoleIds(null, original.getUserId(),
					original.getCompanyId(), 1, true);
		} else {
			oldLoginName = userOld.getLoginName();
		}
		return oldLoginName;
	} 
	
	private boolean returnIsSeat(String roleIds){
		boolean isSeat = false;
		if (StringUtils.isNotEmpty(roleIds)) {
			String[] roleIdArr = StringUtils.split(roleIds, ",");
			if (null != roleIdArr) {
				for (String roleId : roleIdArr) {
//					if (Integer.parseInt(roleId) == 2|| Integer.parseInt(roleId) == 5) {
//						isSeat = true;
//						break;
//					}
					RoleInfo roleInfo = roleService.findOne(Integer.parseInt(roleId));
					if("siter".equals(roleInfo.getRoleCode()) || "seatLeader".equals(roleInfo.getRoleCode())){
						isSeat = true;
						break;
					}
				}
			}
		}
		return isSeat;
	}
	
	private void isSeatAdd(UserInfo userOld){
		Msiuser mu = new Msiuser();
		mu.setUserNum(userOld.getLoginName());
		mu.setWorkNo(userOld.getLoginName());
		mu.setPass(userOld.getLoginPass());
		mu.setName(userOld.getUserName());
		mu.setMsiphone(userOld.getPhone());
		mu.setState(0);
		mu.setBrecord(1);
		mu.setCompanyid(userOld.getCompanyId());
		mu.setRole(1);
		mu.setTeamid(-1);
		mu.setIsForkGroup(userOld.getIsForkGroup());
		mu.setCompanyId(userOld.getCompanyId());
		if (1 == seatType) {
			// 内线坐席
			mu.setMsitype(0);
			mu.setMsiWorkType(1);
		} else if (2 == seatType) {
			// 外线在线
			mu.setMsitype(1);
			mu.setMsiWorkType(1);
		} else if (3 == seatType) {
			// 外线离线
			mu.setMsitype(1);
			mu.setMsiWorkType(6);
		}

		msiuser.saveOrUpdate(mu);
	}
	
	private void isSeatUpdate(UserInfo userOld,String oldLoginName){
		Msiuser mu = msiuser.getMsiuerByWorkNo(oldLoginName);
		if (null == mu)
		{
		    mu = new Msiuser();
		}
		mu.setUserNum(userOld.getLoginName());
		mu.setWorkNo(userOld.getLoginName());
		mu.setPass(userOld.getLoginPass());
		mu.setName(userOld.getUserName());
		mu.setMsitype(1);
		mu.setMsiphone(userOld.getPhone());
		// 是否为离线坐席
		mu.setMsiWorkType(seatType);
		mu.setState(0);
		mu.setBrecord(1);
		mu.setCompanyid(userOld.getCompanyId());
		mu.setRole(1);
		mu.setTeamid(-1);
		mu.setIsForkGroup(userOld.getIsForkGroup());
		mu.setCompanyId(userOld.getCompanyId());
		if (1 == seatType) {
			// 内线坐席
			mu.setMsitype(0);
			mu.setMsiWorkType(1);
		} else if (2 == seatType) {
			// 外线在线
			mu.setMsitype(1);
			mu.setMsiWorkType(1);
		} else if (3 == seatType) {
			// 外线离线
			mu.setMsitype(1);
			mu.setMsiWorkType(6);
		}
		msiuser.saveOrUpdate(mu);
	}
	
	private void addLog(String roleIds,String type){
		if (StringUtils.isNotEmpty(roleIds)) {
			// 添加主角色
			String[] roleIdArr = StringUtils.split(roleIds, ",");
			Integer[] roleIdArrInteger = new Integer[roleIdArr.length];
			for (int i = 0; i < roleIdArr.length; i++) {
				String roleId = roleIdArr[i];
				roleIdArrInteger[i] = Integer.parseInt(roleId);
			}
			roleUserService.saveRoleUsersByRoleIds(roleIdArrInteger,
					user.getUserId(), getLoginUser().getCompanyId(), 1,
					true);
		}
		// 系统日志添加
		UserInfo userInfo = (UserInfo) getSession().getAttribute(
				"adminUser");
		Log log = new Log();
		log.setCompanyId(userInfo.getCompanyId());
		log.setInsertTime(new Timestamp(new Date().getTime()));
		log.setIp(this.getRequest().getRemoteAddr());
		log.setIsDelete(0);
		if ("add".equals(type)) {
			log.setLogType(LogType.LOG_USER_ADD);// 对照LogType类中的常量修改
		} else if ("update".equals(type)) {
			log.setLogType(LogType.LOG_USER_UPDATE);// 对照LogType类中的常量修改
		}
		log.setRefId(userInfo.getUserId());
		if ("add".equals(type)) {
			log.setRemark("添加用户成功");
		} else if ("update".equals(type)) {
			log.setRemark("修改用户成功");
		}
		log.setUserId(userInfo.getUserId());
		log.setUserName(userInfo.getUserName());
		log.setType(0);// 手动添加系统日志
		logService.saveOrUpdate(log);
	}
	   /**
     * 功能：删除登录用户信息,主要是清空登录名,密码,同时设置用户状态为2
     */
    public void deleteLoginUser(){
	    if (StringUtils.isNotEmpty(userIds))
	    {
	    	if (userIds.endsWith(","))
	        {
	            userIds = userIds.substring(0, userIds.length() - 1);
	        }
	        if (userIds.startsWith(","))
	        {
	            userIds = userIds.substring(1, userIds.length());
	        }
	        UserInfo userOld = userService.findOne(
	                Integer.parseInt(userIds));
	        userOld.setUserState(UserInfo.USERSTATE_UNLOGIN);
	        List<RoleInfo> roleList= roleService.getRoleByUser(userOld.getUserId());
	       // userOld.setIsDelete(1);
	    	// 如果角色为坐席或者坐席班长
			for (RoleInfo roleInfo : roleList) {
				if(roleInfo.getRoleId()==2||roleInfo.getRoleId()==5){
					Msiuser mu=msiuser.getMsiuerByWorkNo(userOld.getLoginName());
					msiuser.delete(mu, true);
					break;
					}
				}
					
	        // 更新用户名和密码信息,保证用户不能再登陆
	        userOld.setLoginPass("");
	        userOld.setLoginName(UUID.randomUUID().toString());
	        userService.saveOrUpdate(userOld);
	
	        // 同时删除该用户配置的角色信息
	        roleUserService.saveRoleUsersByRoleIds(null, userOld.getUserId(),
	                userOld.getCompanyId(), 1, true);
	        
	        //系统日志添加
            UserInfo userInfo = (UserInfo)getSession().getAttribute("adminUser");
    		Log log = new Log();
    		log.setCompanyId(userInfo.getCompanyId());
    		log.setInsertTime(new Timestamp(new Date().getTime()));
    		log.setIp(this.getRequest().getRemoteAddr());
    		log.setIsDelete(0);
    		log.setLogType(LogType.LOG_USER_DELETE);//对照LogType类中的常量修改
    		log.setRefId(userInfo.getUserId());
    		log.setRemark("删除用户成功");
    		log.setUserId(userInfo.getUserId());
    		log.setUserName(userInfo.getUserName());
    		log.setType(0);//手动添加系统日志
    		logService.saveOrUpdate(log);
    		
	        ajax(SUCCESS);
	    }
	    else
	    {
	        ajax("请选择要删除的人员！");
	    }
	}
    
    /**
     * 删除人员
     * @return
     */
    public String deleteUser()
    {
    	boolean deleteFlag = false;
        if (StringUtils.isNotEmpty(userIds))
        {
            if (userIds.endsWith(","))
            {
                userIds = userIds.substring(0, userIds.length() - 1);
            }
            if (userIds.startsWith(","))
            {
                userIds = userIds.substring(1, userIds.length());
            }
            
            String[] userIdArray = userIds.split(",");
            for(int i=0;i<userIdArray.length;i++){
            	UserInfo u = userService.findOne(Integer.parseInt(userIdArray[i]));
            	if (null != u)
            	{
                    List<RoleInfo> roleList= roleService.getRoleByUser(u.getUserId());
                    // 如果角色为坐席或者坐席班长
                    for (RoleInfo roleInfo : roleList) {
                        if(roleInfo.getRoleId()==2||roleInfo.getRoleId()==5){
                            Msiuser mu=msiuser.getMsiuerByWorkNo(u.getLoginName());
                            msiuser.delete(mu, true);
                            break;
                            }
                        }
                    // 同时删除该用户配置的角色信息
                    roleUserService.saveRoleUsersByRoleIds(null, u.getUserId(),
                            u.getCompanyId(), 1, true); 
            	}
            }
            userService.deleteUserByIds(userIds, false, getLoginUser().getCompanyId());
//            userService.deleteUserByIds(userIds,false, deleteFlag,getLoginUser().getCompanyId());
            
            ajax(SUCCESS);
            return null;
        }
        else
        {
            ajax("请选择要删除的人员！");
            return null;
        }
    }

	public String detailLoginUser() {
		user = userService.findOne(user.getUserId());
		Msiuser mu = msiuser.getMsiuerByWorkNo(user.getLoginName());
		// 获取用户配置的角色信息
		List<RoleInfo> roleList = roleService.findRolesByUserId(
				user.getUserId(), 1);

		roleIds = "";
		roleNames = "";
		roleCodes = ",";
		for (RoleInfo roleInfo : roleList) {
			Integer roleId = roleInfo.getRoleId();
			String roleName = roleInfo.getRoleName();
			String roleCode = roleInfo.getRoleCode();
			roleIds += roleId + ",";
			roleNames += roleName + ",";
			roleCodes += roleCode + ",";
		}

		if (!"".equals(roleNames) && roleNames.indexOf(',') > 0) {
			roleNames = roleNames.substring(0, roleNames.lastIndexOf(','));
		}
		// 获取单位/部门级联关系
		String groupName = groupService.getGroupNamePathByUserId(
				user.getCompanyId(), user.getUserId());
		this.getRequest().setAttribute("groupName", groupName);
		this.getRequest().setAttribute("msi", mu);
		return type;
	}
	/**
	 * 功能：新增和修改人员时更新is_fork_group字段
	 * 开发人员:张东领
	 * 创建日期:2015年4月9日
	 * 修改人员:
	 * 修改日期:
	 * @return
	 */
	public String updateUserIsforkgroup(){
		try {
			if(StringUtils.isNotBlank(phone)){
				user = hotlineUserService.findByPhone(phone);
				if(user!=null&&user.getGroupId()!=null){
					Integer isForkGroup = null;
					GroupInfo groupInfo = groupService.findOne(user.getGroupId());
					if(groupInfo!=null){
						isForkGroup = groupInfo.getIsForkGroup();
					}
					if(isForkGroup!=null){
						user.setIsForkGroup(isForkGroup);
						hotlineUserService.saveOrUpdate(user);
						ajax(0);
					}
				}
			}
		} catch (Exception e) {
			LOGGER.error("更新is_fork_group字段异常;方法:updateUserIsforkgroup()", e);
		}
		return null;
	}
	/**
	 * 功能：根据角色ID得到角色Code
	 * 开发人员:张东领
	 * 创建日期:2015年4月10日
	 * 修改人员:
	 * 修改日期:
	 * @return
	 */
	public String getRoleCodeById(){
		try {
			if(roleId!=null){
				RoleInfo roleInfo = roleService.findOne(roleId);
				if(roleInfo!=null&&StringUtils.isNotBlank(roleInfo.getRoleCode())){
					ajax(roleInfo.getRoleCode());
				}
			}
		} catch (Exception e) {
			LOGGER.error("根据角色ID得到角色Code异常!方法:getRoleCodeById()", e);
		}
		return null;
	}

	public UserInfo getUser() {
		return user;
	}

	public void setUser(UserInfo user) {
		this.user = user;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}

	public String getRoleCodes() {
		return roleCodes;
	}

	public void setRoleCodes(String roleCodes) {
		this.roleCodes = roleCodes;
	}

	public String getAssistIds() {
		return assistIds;
	}

	public void setAssistIds(String assistIds) {
		this.assistIds = assistIds;
	}

	public Integer getManagementRange() {
		return managementRange;
	}

	public void setManagementRange(Integer managementRange) {
		this.managementRange = managementRange;
	}

	public String getAppointGroupIds() {
		return appointGroupIds;
	}

	public void setAppointGroupIds(String appointGroupIds) {
		this.appointGroupIds = appointGroupIds;
	}

	public String getAppointGroupNames() {
		return appointGroupNames;
	}

	public void setAppointGroupNames(String appointGroupNames) {
		this.appointGroupNames = appointGroupNames;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRoleNames() {
		return roleNames;
	}

	public void setRoleNames(String roleNames) {
		this.roleNames = roleNames;
	}

	public Integer getOldUserId() {
		return oldUserId;
	}

	public void setOldUserId(Integer oldUserId) {
		this.oldUserId = oldUserId;
	}

	/**
	 * @return the birthDay
	 */
	public String getBirthDay() {
		return birthDay;
	}

	/**
	 * @param birthDay
	 *            the birthDay to set
	 */
	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}

	/**
	 * @return the seatType
	 */
	public Integer getSeatType() {
		return seatType;
	}

	/**
	 * @param seatType
	 *            the seatType to set
	 */
	public void setSeatType(Integer seatType) {
		this.seatType = seatType;
	}

	/**
	 * @return the userIds
	 */
	public String getUserIds() {
		return userIds;
	}

	/**
	 * @param userIds the userIds to set
	 */
	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

}
