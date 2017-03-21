package cn.com.wh.login.action;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import cn.com.qytx.cbb.file.config.FilePathConfig;
import cn.com.qytx.cbb.sso.server.cache.SSOCache;
import cn.com.qytx.cbb.util.OnlineUserListener;
import cn.com.qytx.platform.base.action.BaseActionSupport;
import cn.com.qytx.platform.log.service.ILog;
import cn.com.qytx.platform.log.service.LogType;
import cn.com.qytx.platform.org.domain.CompanyInfo;
import cn.com.qytx.platform.org.domain.GroupInfo;
import cn.com.qytx.platform.org.domain.ModuleInfo;
import cn.com.qytx.platform.org.domain.RoleInfo;
import cn.com.qytx.platform.org.domain.RoleUser;
import cn.com.qytx.platform.org.domain.UserInfo;
import cn.com.qytx.platform.org.service.ICompany;
import cn.com.qytx.platform.org.service.IGroup;
import cn.com.qytx.platform.org.service.IModule;
import cn.com.qytx.platform.org.service.IRole;
import cn.com.qytx.platform.org.service.IRoleUser;
import cn.com.qytx.platform.org.service.IUser;
import cn.com.qytx.platform.utils.encrypt.MD5;
import cn.com.wh.WHConstant;
import cn.com.wh.company.domain.SafetyInstitutions;
import cn.com.wh.company.domain.WHCompany;
import cn.com.wh.company.service.ISafetyInstitutions;
import cn.com.wh.company.service.IWHCompany;
import cn.com.wh.login.domain.WhEnterprise;
import cn.com.wh.login.domain.WhUser;
import cn.com.wh.login.service.IWhEnterprise;
import cn.com.wh.login.service.IWhUser;
import cn.com.wh.safeaccident.util.Tool;
import cn.com.wh.util.DataInitUtil;

public class LoginAction extends BaseActionSupport
{
    /**
     * 描述含义
     */
    private static final long serialVersionUID = -2274761802164524030L;

    Logger logger = LoggerFactory.getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME);

    @Resource
    private IModule moduleService;
    @Resource
    private IUser userService;
	@Resource
	private IGroup groupService;
    @Resource
    private IRole roleService;
    @Resource
	private IRoleUser roleUserService;
    /** 系统日志接口 */
    @Resource
    private ILog logService;
    private int skinLogo;// 皮肤标志
    @Resource(name = "filePathConfig")
    private FilePathConfig filePathConfig;
    @Autowired
	public IWHCompany companyImpl;
    @Resource(name = "companyService")
    private ICompany companyService;
    /** 客户端企业接口 */
    @Autowired
	public IWhEnterprise whEnterpriseImpl;
    
    /** 客户端用户接口 */
    @Autowired
	public IWhUser whUserImpl;
    /**
	 * 安全管理机构接口
	 */
	@Autowired
	private ISafetyInstitutions sisService;
    
    
    /**
     * 外部提供登录接口
     * @param userType  用户类型1政府管理员，2企业管理员,3政府端普通用户  
     * @param companyCode  客户端企业code
     * @param userCode 客户端人员code
     * @return
     */
    public String loginAjax(){
    	ServletRequestAttributes attr = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request =attr.getRequest();
        Object userType = request.getParameter("userType");//用户类型
        Object companyCode = request.getParameter("companyCode");//开户企业Code
        Object userCode = request.getParameter("userCode");
        Object sysTypeObj = request.getParameter("sysType");//系统类型  0通知公告  1安全生产系统
        int sysType = sysTypeObj==null?1:Integer.parseInt(sysTypeObj.toString());
        //是否是政府端用户
        boolean isGovernment = (userType!=null&&Integer.parseInt(userType.toString())==1&&userCode!=null);
        //是否是企业端用户
        boolean isEnterprise = (userType!=null&&Integer.parseInt(userType.toString())==2&&userCode!=null&&companyCode!=null);
        if (!isGovernment&&!isEnterprise) {//非政府端用户也非企业端用户      参数不正确
        	logger.error("参数信息不正确,userType="+userType+"##companyCode="+companyCode+"##userCode="+userCode);
        	return null;
		}
    	//把公司信息放到session中
        CompanyInfo companyInfo=companyService.findOne(1);
    	request.getSession().setAttribute("companyInfo",companyInfo);//把单位列表存放到session
    	//把用户类型放到session中     1政府管理员，2企业管理员,3政府端普通用户  
        Integer whroletype = Integer.parseInt(userType.toString());
        this.getSession().setAttribute("whroletype", whroletype);
    	UserInfo sessionUser = new UserInfo();
    	//通过用户code去客户的表中获得用户信息
    	WhUser user = whUserImpl.findByUserCode(userCode.toString());
    	if (user!=null) {
    		//todo  在userInfo表中插入人员信息
    		sessionUser.setUserName(user.getNickName());
    		sessionUser.setCompanyId(1);
    		sessionUser.setIsDelete(0);
    		sessionUser.setPhone(user.getMobile());
    		sessionUser.setLoginName(user.getCode());
    		sessionUser.setLoginPass(user.getLoginPwd());
    		sessionUser.setIsDefault(1);
    		sessionUser.setOrderIndex(1);
    		sessionUser.setUserState(0);
    		sessionUser.setUserId(user.getId());
    		//保存用户权限表
    		RoleUser roleUser = new RoleUser();
    		roleUser.setCompanyId(1);
    		roleUser.setType(1);
    		if (isEnterprise) {
				roleUser.setRoleId(2);
			}else if (isGovernment) {
				roleUser.setRoleId(3);
			}
    		roleUser.setUserId(user.getId());
    		if (!roleUserService.isExistRoleUser(roleUser.getRoleId(), user.getId())) {
    			roleUserService.saveOrUpdate(roleUser);
			}
		}else {//用户信息不存在
			logger.error("用户信息不正确，用户code为："+userCode);
			return null;
		}
    	//获取用户IP
        String ip = getIpAddr(request);
        sessionUser.setIp(ip);
    	//企业用户需要判断企业信息是否完善
    	if (isEnterprise) {
    		//判断企业端信息是否正确
			WhEnterprise enterprise = whEnterpriseImpl.findByEnterpriseCode(companyCode.toString());
			if (enterprise!=null) {
				WHCompany whcom = companyImpl.findByLinkId(enterprise.getCode());
				if (whcom==null) {//本企业在我的企业表中不存在，需要进行企业新增操作
					//初始化企业信息
					initCompanyInfo(enterprise,user,sessionUser);
				}else {
					sessionUser.setGroupId(whcom.getGroupId());
				}
			}else {
				logger.error("企业信息不正确，企业code为："+companyCode);
				return null;
			}
		}
    	//把人员信息放到session中
        request.getSession().setAttribute("adminUser",sessionUser);//登录信息保存在Session
        request.getSession().setAttribute("adminUserXC",sessionUser);//登录信息保存在Session
        //把菜单放到session中
        List<ModuleInfo> moduleList = moduleService.findAll();
        request.getSession().setAttribute("moduleList",moduleList);//把模块列表存放到session
        // 监听登录用户
        if (!OnlineUserListener.onlineUserIdList.contains(sessionUser.getUserId()))
        {
            OnlineUserListener.onlineUserIdList.add(sessionUser.getUserId());
        }
        // sso单点登录
        String sso_token = UUID.randomUUID().toString();
        SSOCache.getInstance().store(sso_token, sessionUser);
        super.getRequest().getSession().setAttribute("sso_token", sso_token);

        String downPath = filePathConfig.getFileViewPath();
        this.getSession().setAttribute("downPath", downPath);
        
        //刷新全局公司名称MAP
      	DataInitUtil.flush();
        
        //把企业信息放到session中
        String companyName = "";
        if (getLoginUser()!=null&&getLoginUser().getGroupId()!=null) {
        	companyName = companyImpl.getCompanyName(getLoginUser().getGroupId());
		}
        this.getSession().setAttribute("companyName", companyName);
        //把系统类型放到session中
        this.getSession().setAttribute("sysType", sysType);
    	return SUCCESS;
    }
    
    public void flushCache(){
    	DataInitUtil initutil = new DataInitUtil();
    	initutil.flush();
    }
    
    /**
     * 初始化企业
     * @param enterprise
     * @param user
     * @return
     */
    private Map<String,Object> initCompanyInfo(WhEnterprise enterprise,WhUser user,UserInfo info){
    	//保存部门
		GroupInfo group = new GroupInfo();
		group.setCompanyId(1);
		group.setGroupName(enterprise.getEnterpriseName());
		group.setGroupType(1);
		group.setIsDelete(0);
		groupService.saveOrUpdate(group);
		group.setIsForkGroup(group.getGroupId());
		groupService.saveOrUpdate(group);
		info.setGroupId(group.getGroupId());
		
//		//保存登录用户
//		UserInfo info = new UserInfo();
//		info.setCompanyId(1);
//		info.setGroupId(group.getGroupId());
//		info.setGroupName(enterprise.getEnterpriseName());
//		info.setLoginName(user.getNickName());
//		info.setLoginPass(user.getLoginPwd());
//		info.setPhone(user.getMobile());
//		info.setUserName(user.getNickName());
//		info.setIsDelete(0);
//		info.setIsDefault(1);
//		info.setOrderIndex(1);
//		info.setUserState(0);
//		info.setOfficeWidget(0);
//		info.setPartitionCompanyId(1);
//		info.setIsForkGroup(group.getGroupId());
//		//loginName字段存储客户用户表的code信息
//		info.setLoginName(user.getCode());
//		userService.saveOrUpdate(info);
		
		
		//保存公司扩展表
		WHCompany wHCompany = new WHCompany();
		wHCompany.setGroupId(group.getGroupId());
		wHCompany.setIsForkGroup(group.getGroupId());
		wHCompany.setCompanyName(enterprise.getEnterpriseName());
		wHCompany.setIndustryClassification(enterprise.getIndustryTypes());
		wHCompany.setIsDelete(0);
		wHCompany.setCreateUserId(user.getId());
		wHCompany.setCreateTime(new Timestamp(System.currentTimeMillis()));
		wHCompany.setMemo("");
		wHCompany.setLinkId(enterprise.getCode());
		companyImpl.saveOrUpdate(wHCompany);
		
		//记录日志
//		logService.saveOrUpdate( Tool.generateLog(getLoginUser(), 
//				this.getRequest().getRemoteAddr(), 
//				"注册企业成功", 
//				LogType.LOG_QYXX_ADD, 
//				wHCompany, 
//				wHCompany.getCompanyId()) );
		
		//刷新全局公司名称MAP
		DataInitUtil.flush();
		
		SafetyInstitutions sis = new SafetyInstitutions();
		sis.setGroupId(group.getGroupId());
		sis.setGroupName(group.getGroupName());
		sis.setCreateUserId(user.getId());
		sis.setCreateTime(new Timestamp(System.currentTimeMillis()));
		sis.setIsDelete(0);
		sis.setIsForkGroup(group.getGroupId());
		sisService.saveOrUpdate(sis);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("company", wHCompany);
		return map;
    }
    /*
     * 获取用户IP
     */
    public  String getIpAddr(HttpServletRequest request) { 
        String ip = request.getHeader("x-forwarded-for"); 
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("Proxy-Client-IP"); 
        } 
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("WL-Proxy-Client-IP"); 
        } 
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getRemoteAddr(); 
        } 
        return ip; 
    }

    /**
     * 登陆后跳转
     * @return
     */
    public String loginForward() throws Exception
    {
    	UserInfo userInfo = (UserInfo) getSession().getAttribute("adminUser");
    	/*
        // 登录日志
        Log log = new Log();
        log.setCompanyId(userInfo.getCompanyId());
        log.setInsertTime(new Timestamp(new Date().getTime()));
        log.setModuleName("user");
        log.setSysName("xyoa3.0");
        log.setIp(this.getRequest().getRemoteAddr());
        log.setIsDelete(0);
        log.setLogType(LogType.LOG_LOGIN_SUCCESS);
        log.setRefId(userInfo.getUserId());
        log.setRemark("登录成功");
        log.setUserId(userInfo.getUserId());
        log.setUserName(userInfo.getUserName());
        log.setType(0);// 手动添加系统日志
        logService.saveOrUpdate(log);
        */
        
        // 监听登录用户
        if (!OnlineUserListener.onlineUserIdList.contains(userInfo.getUserId()))
        {
            OnlineUserListener.onlineUserIdList.add(userInfo.getUserId());
        }
        // sso单点登录
        String sso_token = UUID.randomUUID().toString();
        SSOCache.getInstance().store(sso_token, userInfo);
        super.getRequest().getSession().setAttribute("sso_token", sso_token);

        String downPath = filePathConfig.getFileViewPath();
        this.getSession().setAttribute("downPath", downPath);

        // 获取角色 判断是企业管理员,政府管理员
        List<RoleInfo> roleList = roleService.getRoleByUser(userInfo.getUserId()); // 根据人员Id获取角色列表
        Integer whroletype = 0;
        if (null != roleList && !roleList.isEmpty()) {
            for (RoleInfo temoRoleInfo : roleList) {
                String temoRolecode = temoRoleInfo.getRoleCode();
                if ( StringUtils.isNotEmpty(temoRolecode) ) {
                	if( WHConstant.MANAGEADMIN.equals(temoRolecode) || WHConstant.ADMIN.equals(temoRolecode) ){
	                	//政府
	                	whroletype = 1;
	                	break;
                	} else if( WHConstant.ENTERPRISEADMIN.equals(temoRolecode) ) {
                		//企业
                		whroletype = 2;
                		break;
                	} else if ( WHConstant.MANAGECOMMON.equals(temoRolecode) ){
                		//政府普通用户
                		whroletype = 3;
                		break;
                	}
                }
            }
        }
        this.getSession().setAttribute("whroletype", whroletype);
        //把企业信息放到session中
        String companyName = "";
        if (getLoginUser()!=null&&getLoginUser().getGroupId()!=null) {
        	companyName = companyImpl.getCompanyName(getLoginUser().getGroupId());
		}
        this.getSession().setAttribute("companyName", companyName);
        return SUCCESS;
    }

    /**
     * 根据用户修改皮肤标志
     * @param id
     * @param skinLogo
     * @throws Exception
     */
    public String updateUserSkinLogo() throws Exception
    {
        UserInfo loginUser = (UserInfo) getSession().getAttribute("adminUser");
        if (skinLogo != 0)
        {
            userService.updateUserSkinLogo(loginUser.getUserId(), skinLogo);
        }
        loginUser = userService.findOne(loginUser.getUserId());
        this.getSession().setAttribute("adminUser", loginUser);
        loginForward();
        return null;
    }

    /**
     * 判断session是否有效
     * 功能：
     * @return
     */
    public String checkSession()
    {
        UserInfo loginUser = (UserInfo) getSession().getAttribute("adminUser");
        if (loginUser != null)
        {
            ajax("0");
        }
        else
        {
            ajax("1");
        }
        return null;
    }

    public int getSkinLogo()
    {
        return skinLogo;
    }

    public void setSkinLogo(int skinLogo)
    {
        this.skinLogo = skinLogo;
    }

}
