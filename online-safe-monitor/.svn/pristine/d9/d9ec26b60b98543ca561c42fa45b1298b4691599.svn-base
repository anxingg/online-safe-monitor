package cn.com.qytx.oa.datapriv.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import cn.com.qytx.oa.datapriv.domain.DataPriv;
import cn.com.qytx.platform.base.dao.BaseDao;

/**
 * 功能:数据权限列表dao
 * 版本: 1.0
 * 开发人员: 李华伟
 * 创建日期: 2013-3-22
 * 修改日期: 2013-3-22
 * 修改列表:
 */
@Repository("dataDao")
public class DataPrivDao extends BaseDao<DataPriv, Integer> implements Serializable
{

    /**
	 * 描述含义
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 功能：批量删除文件夹权限
     * @param refId 关联Id
     * @param moduleName 模块名称
     * @param roleIds 角色Id集合
     * @param groupIds 群组Id集合
     * @param userIds 人员Id集合
     * @param roleNames 角色名称集合
     * @param groupNames 群组名称集合
     * @param userNames 人员名称集合
     */
    @SuppressWarnings("deprecation")
    public void deleteFileSort(String refId, String moduleName, String roleIds, String groupIds,
            String userIds, String roleNames, String groupNames, String userNames)
    {
        StringBuilder hql = new StringBuilder();
        hql.append("delete  DataPriv dataPriv where 1=1");
        StringBuilder paramHql = new StringBuilder();

        List<Object> params = new ArrayList<Object>();

        if (null != refId && !"".equals(refId))
        {
            paramHql.append("and dataPriv.refId= ?");
            params.add(Integer.parseInt(refId));
        }

        if (null != moduleName && !"".equals(moduleName))
        {
            paramHql.append("and dataPriv.moduleName = ?");
            params.add(moduleName);
        }

        /*
         * if (null != roleIds && !"".equals(roleIds))
         * {
         * paramHql.append("and dataPriv.roleIds= ? ");
         * params.add(roleIds);
         * }
         * if (null != groupIds && !"".equals(groupIds))
         * {
         * paramHql.append("and dataPriv.groupIds = ?  ");
         * params.add(groupIds);
         * }
         * if (null != userIds && !"".equals(userIds))
         * {
         * paramHql.append("and dataPriv.userIds= ?  ");
         * params.add(userIds);
         * }
         */

        if (null != roleNames && !"".equals(roleNames))
        {
            paramHql.append("and dataPriv.roleNames = ?");
            params.add(roleNames);
        }

        if (null != groupNames && !"".equals(groupNames))
        {
            paramHql.append("and dataPriv.groupNames = ?");
            params.add(groupNames);
        }
        if (null != userNames && !"".equals(userNames))
        {
            paramHql.append("and dataPriv.userNames = ?");
            params.add(userNames);
        }
        if (paramHql.length() > 0)
        {

            hql.append(paramHql);

        }
        super.bulkDelete(hql.toString(), params.toArray());
    }
    /**
     * 
     * 功能：返回一个列表
     * @param fileSortId
     * @return
     */
	public List<DataPriv> getChildFileSortById(int fileSortId) {
		 
		List<DataPriv> list=super.findAll("from DataPriv dataPriv where dataPriv.refId=?",fileSortId);
		return list;
	}
	
	/**
	 * 
	 * 功能：
	 * @param dataprivid
	 * @param roleIds
	 * @param roleNames
	 * @param userIds
	 * @param userNames
	 * @param groupIds
	 * @param GroupName
	 * @return
	 */
	@SuppressWarnings("deprecation")
    public int updateDataPrivById(Integer dataprivid, String roleIds,
			String roleNames, String userIds, String userNames,
			String groupIds, String GroupName) {
		
		return super.bulkUpdate("update DataPriv set roleIds=?,roleNames=?,userIds=?,userNames=?,groupIds=?,groupNames=? where id=?",roleIds,roleNames, userIds,userNames,groupIds,GroupName,dataprivid);
	}
	
    /**
     * 根据ID获取对象
     * 功能：
     * @return
     */
	public DataPriv getDataPrivById(Integer id) {
		return this.findOne(id);
	}
	
}
