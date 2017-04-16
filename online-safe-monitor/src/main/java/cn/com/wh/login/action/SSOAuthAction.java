package cn.com.wh.login.action;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.gson.Gson;
import cn.com.qytx.cbb.sso.server.cache.SSOCache;
import cn.com.qytx.platform.base.action.BaseActionSupport;
import cn.com.qytx.platform.org.domain.UserInfo;
import cn.com.wh.login.domain.SSOResult;
import cn.com.wh.login.domain.SSOUserInfo;



public class SSOAuthAction extends BaseActionSupport {

	Logger logger = LoggerFactory.getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME);
    
	/**
	 * 
	 */
	private static final long serialVersionUID = -4365602725482594027L;
	public String token;
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	public String auth()
	{
		SSOResult result=new SSOResult();
		result.setRet(1);
		result.setMsg("未知错误");
		result.setUserInfo(null);
		try{
			if(!StringUtils.isEmpty(token)){
				UserInfo userInfo=(UserInfo)SSOCache.getInstance().read(token);
				if(userInfo!=null){
					result.setRet(0);
					result.setMsg("认证成功");
					SSOUserInfo sso=new SSOUserInfo();
					sso.setLoginName(userInfo.getLoginName());
					sso.setOrganize(userInfo.getGroupName());
					sso.setRole("common");
					sso.setUserName(userInfo.getUserName());
					result.setUserInfo(sso);
				}
				else{
					result.setRet(1);
					result.setMsg("token验证失败");
					result.setUserInfo(null);
				}
			}
			else{
				result.setRet(1);
				result.setMsg("请求中未发现token参数");
				result.setUserInfo(null);
			}
		}
		catch(Exception ex){
			logger.error("SSOAuthAction auth:"+ex.getMessage());
			result.setRet(1);
			result.setMsg("服务端异常");
			result.setUserInfo(null);
		}
		Gson json = new Gson();
		String jsons = json.toJson(result);
        ajax(jsons);
        return null;
	}
}
