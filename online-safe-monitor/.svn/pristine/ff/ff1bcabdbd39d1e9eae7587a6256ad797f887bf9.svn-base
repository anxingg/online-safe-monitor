package cn.com.qytx.hotline.user.action;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;

import org.springframework.beans.factory.annotation.Autowired;

import cn.com.qytx.hotline.ivr.domain.MsiCompanyInfo;
import cn.com.qytx.hotline.ivr.domain.Msiuser;
import cn.com.qytx.hotline.ivr.service.IMsiCompanyInfo;
import cn.com.qytx.hotline.ivr.service.IMsiUser;
import cn.com.qytx.hotline.ivr.service.IMsiservice;
import cn.com.qytx.hotline.mis.dao.PhoneAttributionDao;
import cn.com.qytx.hotline.mis.domain.PhoneAttribution;
import cn.com.qytx.platform.base.action.BaseActionSupport;
import cn.com.qytx.platform.org.domain.GroupInfo;
import cn.com.qytx.platform.org.domain.GroupUser;
import cn.com.qytx.platform.org.domain.RoleInfo;
import cn.com.qytx.platform.org.domain.UserInfo;
import cn.com.qytx.platform.org.service.IGroup;
import cn.com.qytx.platform.org.service.IGroupUser;
import cn.com.qytx.platform.org.service.IRole;
import cn.com.qytx.platform.org.service.IUser;
import cn.com.qytx.platform.utils.encrypt.MD5;

import com.google.gson.Gson;

/**
 * User: 黄普友Date: 12-11-1 Time: 上午12:18
 */
public class LoginAction extends BaseActionSupport {
	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 8182416859560770547L;
	private String workNo;
	private String pass;
	private String remember;
	// 用户信息服务类
	@Autowired
	private transient IMsiUser userService;
	
	/**
	 * 队列
	 */
	@Autowired
	private transient IMsiservice msiservice;

	// 用户信息
	@Autowired
	private transient IUser loginuserService;

	// 公司信息服务类
	@Autowired
	private transient IMsiCompanyInfo companyInfoService;

	// 角色信息
	@Autowired
	IRole roleService;
	@Autowired
	IGroup groupService;
	@Autowired
	IGroupUser groupUserService;
	
    //用户所属部门
    public final static String USER_GROUP = "usergroup";
    //用户部门的子部门集合
    public final static String USER_SUBGROUPS = "usersubgroups";
    //用户的角色集合
    public final static String USER_ROLES = "userroles";
	
	/**
	 * 来电归属地
	 */
	@Autowired
	private PhoneAttributionDao phoneAttributionDao;

	public String login() {
		try {
			Msiuser user = (Msiuser) this.getSession().getAttribute("seatUser");
			if (user != null) {

				return SUCCESS;
			} else {
				return LOGIN;
			}
		} catch (Exception ex) {

			return LOGIN;
		}
	}

	/**
	 * 判断登录是否合法
	 */
	public void loginAjax() {
//		PrintWriter writer  = null;
		try {
//			writer = new PrintWriter(this.getResponse().getWriter());
			Cookie cookies[] = this.getRequest().getCookies();
			if (cookies != null) {
				for (int i = 0; i < cookies.length; i++) {
					String tempuid_1 = cookies[i].getName();
					if (tempuid_1.equals("s_workNo")
							|| tempuid_1.equals("s_pass")) {
						cookies[i].setMaxAge(0);
						cookies[i].setPath("/");
						this.getResponse().addCookie(cookies[i]);
					}
				}
			}
			if (null == pass || "".equals(pass) || null == workNo) {
				String msg = "NameorPassError";
				// this.setMessage(msg);
				Gson json = new Gson();
				String jsons = json.toJson(msg);
				ajax(jsons);
//				writer.print(jsons);
//				writer.flush();
			}
			MD5 md5 = new MD5();
			String newPass = md5.encrypt(pass);
			Msiuser user = userService.login(workNo, newPass);
			if (user != null) {
				// 获取员工对应的公司信息
				MsiCompanyInfo companyInfo = companyInfoService.findByVid(user.getCompanyId());
				this.getSession().setAttribute("seatUser", user);
				if (remember != null && "1".equals(remember)) {
					Cookie c = new Cookie("s_workNo", workNo);
					Cookie cc = new Cookie("s_pass", pass);
					c.setMaxAge(7 * 24 * 3600);
					c.setPath("/");
					cc.setPath("/");
					cc.setMaxAge(7 * 24 * 3600);
					this.getResponse().addCookie(c);
					this.getResponse().addCookie(cc);
				}
				this.getRequest().setAttribute("passerror", "passerror");
				this.getRequest().setAttribute("workNo", workNo);
				this.getRequest().setAttribute("pass", pass);
				UserInfo loginuser = loginuserService.findByLoginName(workNo);
				
				
				if (null == loginuser || null == loginuser.getUserState()
						|| 1 == loginuser.getUserState().intValue()) {
					String msg = "forbid";
					Gson json = new Gson();
					String jsons = json.toJson(msg);
					ajax(jsons);
//					writer.print(jsons);
//					writer.flush();
				} else {
					loginAjaxOne( loginuser, companyInfo, user);
				}

			} else {
				String msg = "NameorPassError";
				Gson json = new Gson();
				String jsons = json.toJson(msg);
				ajax(jsons);
//				writer.print(jsons);
//				writer.flush();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			LOGGER.error(ex.getMessage());
		}
//		finally{
//			if(writer!=null){
//				writer.close();
//			}
//		}
	}
	
	/**
	 * loginAjax()方法扩展
	 */
	private void loginAjaxOne(UserInfo loginuser,MsiCompanyInfo companyInfo,Msiuser user){
		// 获取来电归属地
		PhoneAttribution pb = phoneAttributionDao.findByPhone(loginuser.getPhone());
		if(pb!=null&&pb.getMobileArea()!=null&&!"".equals(pb.getMobileArea())){
			this.getSession().setAttribute("phoneArea", pb.getMobileArea());
		}else{
			this.getSession().setAttribute("phoneArea", "未知");
		}
		this.getSession().setAttribute("adminUser", loginuser);

		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("companyInfo", companyInfo);
		jsonMap.put("adminUser", user);
		jsonMap.put("user", loginuser);
		

		//isZxbz  0是普通坐席  1是坐席班长
		int isZxbz = 0;
		List<RoleInfo> roleList = roleService
				.getRoleByUser(loginuser.getUserId()); // 根据人员Id获取角色列表
		StringBuffer roleSb = new StringBuffer();
		roleSb.append(",");
		if (null != roleList) {
			for (RoleInfo tempRoleInfo : roleList) {
				if (tempRoleInfo.getRoleCode().equals("seatLeader")) {
					isZxbz = 1;
					break;
				}
			}

			for (RoleInfo tempRoleInfo : roleList) {
				roleSb.append(tempRoleInfo.getRoleCode());
				roleSb.append(",");
			}
			this.getSession().setAttribute("rolecode",
					roleSb.toString());
		}
		this.getSession().setAttribute("isZxbz", isZxbz);
		jsonMap.put("isZxbz", isZxbz);
		List<GroupUser> gList = groupUserService
				.getGroupUserByUserId(loginuser.getUserId());
		if (gList != null && !gList.isEmpty()) {
			GroupUser groupUser = gList.get(0);
			GroupInfo groupInfoBean = groupService
					.findOne(groupUser.getGroupId());
			this.getSession().setAttribute("groupId",
					groupInfoBean.getGroupId());
		}
		this.getSession().setAttribute("workNo", workNo);
		this.getSession().setAttribute("loginPass", pass);
		this.getSession().setAttribute("remember", remember);

        // 更新最后登录时间
        loginuser.setLastLoginTime(new Timestamp(System.currentTimeMillis()));
        loginuserService.saveOrUpdate(loginuser);
		
		//add by jiayq，将用户信息按照CBB规范存放到SESSION中
		setUserInfoToSession(loginuser);

		Gson json = new Gson();
		
		List<Object[]> temp1 = msiservice.findAllByMsiUserIds(user.getVid().toString());
		//this.getSession().setAttribute("msiuserQueue", (Integer)temp1.get(0)[3]);//当前登录人所属的队列
		this.getSession().setAttribute( "msiuserQueues", json.toJson(temp1) );//当前登录人所属的队列
		
		List<Object[]> temp2 = msiservice.findMsiServiceIdAndNameByForkGroupId( loginuser.getIsForkGroup() );
		this.getSession().setAttribute( "currentGroupQueues", json.toJson(temp2) );//当前登录人所属的部门的专家队列
		
		String jsons = json.toJson(jsonMap);
		ajax(jsons);
//		writer.print(jsons);
//		writer.flush();
	}
	
	
	/**将用户信息存放到session中
	 * @param userInfo
	 */
	private void setUserInfoToSession(UserInfo userInfo){
			this.getSession().setAttribute("adminUser", userInfo);
	    	Integer groupId = userInfo.getGroupId();
	    	if(groupId!=null){
		    	GroupInfo gInfo = groupService.findOne(groupId);
		    	List<GroupInfo> subgrouplist = groupService.getSubGroupList(groupId);
		    	List<RoleInfo> roleList =roleService.getRoleByUser(userInfo.getUserId());
		    	this.getSession().setAttribute(USER_GROUP, gInfo);
		    	this.getSession().setAttribute(USER_SUBGROUPS, subgrouplist);
		    	this.getSession().setAttribute(USER_ROLES, roleList);
	    }
	}
	
	/**
	 * 功能：根据电话查询归属地
	 * @return
	 */
	private String phone;
	public String getPhoneArea(){
		String phoneArea=null;
		try {
			if(phone!=null&&!"".equals(phone)){
				PhoneAttribution pb = phoneAttributionDao.findByPhone(phone);
				if(pb!=null&&pb.getMobileArea()!=null&&!"".equals(pb.getMobileArea())){
					phoneArea = pb.getMobileArea();
				}
			}
			ajax(phoneArea);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	
	
	public String getWorkNo() {
		return workNo;
	}

	public void setWorkNo(String workNo) {
		this.workNo = workNo;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getRemember() {
		return remember;
	}

	public void setRemember(String remember) {
		this.remember = remember;
	}
}
