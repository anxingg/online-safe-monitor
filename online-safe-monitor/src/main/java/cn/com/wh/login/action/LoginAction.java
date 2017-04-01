package cn.com.wh.login.action;


import java.util.List;
import java.util.UUID;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import cn.com.qytx.cbb.file.config.FilePathConfig;
import cn.com.qytx.cbb.sso.server.cache.SSOCache;
import cn.com.qytx.cbb.util.OnlineUserListener;
import cn.com.qytx.platform.base.action.BaseActionSupport;
import cn.com.qytx.platform.org.domain.GroupInfo;
import cn.com.qytx.platform.org.domain.RoleInfo;
import cn.com.qytx.platform.org.domain.UserInfo;
import cn.com.qytx.platform.org.service.IRole;
import cn.com.qytx.platform.org.service.IUser;
import cn.com.wh.WHConstant;
import cn.com.wh.company.service.IWHCompany;
import cn.com.wh.login.service.IWhUser;
import cn.com.wh.support.SessionSupport;
import cn.com.wh.util.DataInitUtil;

public class LoginAction extends BaseActionSupport
{
    /**
     * 描述含义
     */
    private static final long serialVersionUID = -2274761802164524030L;

    Logger logger = LoggerFactory.getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME);
    @Resource
    private IUser userService;
	@Resource
    private IRole roleService;
    private int skinLogo;// 皮肤标志
    @Resource(name = "filePathConfig")
    private FilePathConfig filePathConfig;
    @Autowired
	public IWHCompany companyImpl;
    @Autowired
	public IWhUser whUserImpl;
    public void flushCache(){
    	DataInitUtil initutil = new DataInitUtil();
    	initutil.flush();
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
    	UserInfo userInfo = this.getSessionSupport().getCurrentLoginUser();
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
        this.getSessionSupport().setDownPath(downPath);

        // 获取角色判断是否是管理员
        List<RoleInfo> roleList = roleService.getRoleByUser(userInfo.getUserId()); // 根据人员Id获取角色列表
        Integer whroletype = WHConstant.WHROLETYPE.ENTERPRIS;
        GroupInfo groupInfo=this.getSessionSupport().getCurrentLoginGroup();
        if(groupInfo.getGroupType()==1)  //单位部门
        	whroletype=WHConstant.WHROLETYPE.MANAGE;
        else   //外部通讯录，这里表示管辖的企业
        	whroletype=WHConstant.WHROLETYPE.ENTERPRIS;
        if (null != roleList && !roleList.isEmpty()) {
            for (RoleInfo temoRoleInfo : roleList) {
                String temoRolecode = temoRoleInfo.getRoleCode();
                if ( StringUtils.isNotEmpty(temoRolecode) ) {
                	if( WHConstant.ADMIN.equals(temoRolecode)){
	                	whroletype = WHConstant.WHROLETYPE.ADMIN;
	                	break;
                	}
                }
            }
        }
        
        ((SessionSupport)this.getSessionSupport()).setCurrentWHRoleType(whroletype);
        
        //把企业名称放到session中，涉及两类单位，行政单位与企业单位，名称都会保存进入groupName中
        String companyName = ((SessionSupport)this.getSessionSupport()).getCurrentLoginGroup().getGroupName();
        ((SessionSupport)this.getSessionSupport()).setCurrentCompanyName(companyName);
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
        UserInfo loginUser = this.getSessionSupport().getCurrentLoginUser();
        if (skinLogo != 0)
        {
            userService.updateUserSkinLogo(loginUser.getUserId(), skinLogo);
        }
        loginUser = userService.findOne(loginUser.getUserId());
        this.getSessionSupport().setCurrentLoginUser(loginUser);
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
        UserInfo loginUser = this.getSessionSupport().getCurrentLoginUser();
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
