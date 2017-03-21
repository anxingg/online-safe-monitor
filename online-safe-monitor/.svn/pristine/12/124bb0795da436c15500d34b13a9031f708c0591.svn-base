package cn.com.qytx.workflow.constans;

import cn.com.qytx.platform.org.dao.GroupDao;
import cn.com.qytx.platform.org.dao.RoleDao;
import cn.com.qytx.platform.org.dao.UserDao;

/**
 * Created by izerui.com on 14-4-30.
 */
@SuppressWarnings("rawtypes")
public enum CandidateEnum {

    GROUP("userInfos","userId in (?)", "candidate",UserDao.class),
    USER("groupInfos","groupId in (?)","depts", GroupDao.class),
    ROLE("roleInfos","roleId in (?)","roles", RoleDao.class);


	private Class dClass;
    private String hql;
    private String name;
    private String paramName;


    CandidateEnum(String name, String hql, String paramName, Class dClass) {
        this.name = name;
        this.hql = hql;
        this.paramName = paramName;
        this.dClass = dClass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHql() {
        return hql;
    }

    public void setHql(String hql) {
        this.hql = hql;
    }

	public Class getdClass() {
        return dClass;
    }

    public void setdClass(Class dClass) {
        this.dClass = dClass;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }
}
