package cn.com.qytx.oa.datapriv.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.qytx.oa.datapriv.dao.DataPrivDao;
import cn.com.qytx.oa.datapriv.domain.DataPriv;
import cn.com.qytx.oa.datapriv.service.IDataPriv;
import cn.com.qytx.platform.base.service.impl.BaseServiceImpl;

/**
 * 功能:数据权限Impl 版本: 1.0 开发人员: 李华伟 创建日期: 2013-3-22 修改日期: 2013-3-22 修改列表:
 */
@Service("dataPrivService")
@Transactional
public class DataPrivImpl extends BaseServiceImpl<DataPriv> implements
		IDataPriv {
	/**
	 * 描述含义
	 */
	private static final long serialVersionUID = -7586752153635405568L;
	/**
	 * 通讯薄联系人Dao
	 */
	@Resource(name = "dataDao")
	DataPrivDao dataPrivDao;

	/**
	 * 获取查询数据权限额外的hql语句
	 * 
	 * @param vidStr
	 *            映射主键字符串
	 * @param moudleName
	 *            模块名称 不能为空
	 * @param subMoudle
	 *            子模块名称 可为空
	 * @param userId
	 *            用户ID
	 * @param groupId
	 *            用户所属群组Id
	 * @param roleId
	 *            用户所属角色
	 * @return String
	 */
	public String getDataPrivHql(String vidStr, String moudleName,
			String subMoudle, Integer userId, Integer groupId, Integer roleId) {
		if (StringUtils.isEmpty(vidStr) || StringUtils.isEmpty(moudleName)) {
			return null;
		}

		if (null == userId || null == groupId || null == roleId) {
			return null;
		}

		StringBuffer sbHql = new StringBuffer();
		String str = ",%' ";
		
		sbHql.append(vidStr);
		sbHql.append(" in (");
		sbHql.append("select dp.refId from DataPriv dp where (dp.userIds like '%,");
		sbHql.append(userId);
		sbHql.append(str);
		sbHql.append(" or dp.groupIds like '%,");
		sbHql.append(userId);
		sbHql.append(str);
		sbHql.append(" or dp.roleIds like '%," + roleId + ",%')");
		sbHql.append(" and dp.moduleName = '" + moudleName + "'");
		if (!StringUtils.isEmpty(subMoudle)) {
			sbHql.append(" and dp.subModuleName = '" + subMoudle + "'");
		}
		sbHql.append(")");
		return sbHql.toString();
	}

	/**
	 * 获取查询数据权限额外的hql语句
	 * 
	 * @param vidStr
	 *            映射主键字符串
	 * @param moudleName
	 *            模块名称 不能为空
	 * @param subMoudle
	 *            子模块名称 可为空
	 * @param userId
	 *            用户ID
	 * @param groupId
	 *            用户所属群组Id
	 * @param roleIdList
	 *            用户所属角色集合
	 * @return String
	 */
	public String getDataPrivHql(String vidStr, String moudleName,
			String subMoudle, Integer userId, Integer groupId,
			List<Integer> roleIdList) {
		if (StringUtils.isEmpty(vidStr) || StringUtils.isEmpty(moudleName)) {
			return null;
		}

		if (null == userId || null == groupId || null == roleIdList) {
			return null;
		}

		String str = ",%'";
		StringBuffer sbHql = new StringBuffer();
		sbHql.append(" " + vidStr + " in (");
		sbHql.append("select dp.refId from DataPriv dp where (dp.userIds like '%,");
		sbHql.append(userId);
		sbHql.append(str);
		sbHql.append(" or dp.groupIds like '%,");
		sbHql.append(groupId);
		sbHql.append(str);

		for (Integer roleId : roleIdList) {
			sbHql.append(" or dp.roleIds like '%,");
			sbHql.append(roleId);
			sbHql.append(str);
		}
		sbHql.append(") and dp.moduleName = '" + moudleName + "'");
		if (!StringUtils.isEmpty(subMoudle)) {
			sbHql.append(" and dp.subModuleName = '" + subMoudle + "'");
		}
		sbHql.append(")");
		return sbHql.toString();
	}

	/**
	 * 获取查询数据权限额外的hql语句
	 * 
	 * @param vidStr
	 *            映射主键字符串
	 * @param moudleName
	 *            模块名称 不能为空
	 * @param subMoudle
	 *            子模块名称 可为空
	 * @param userId
	 *            用户ID
	 * @param groupId
	 *            用户所属群组Id
	 * @param roleIdList
	 *            用户所属角色集合
	 * @return String
	 */
	public List<DataPriv> getFileSortNedPriv(String vidStr, String moudleName,
			String subMoudle, Integer userId, Integer groupId,
			List<Integer> roleIdList) {
		if (StringUtils.isEmpty(vidStr) || StringUtils.isEmpty(moudleName)) {
			return null;
		}

		if (null == userId || null == groupId || null == roleIdList) {
			return null;
		}
		StringBuilder hql = new StringBuilder();
		hql.append("1=1");
		StringBuilder paramHql = new StringBuilder();

		List<Object> params = new ArrayList<Object>();
		/** refId */
		if (null != vidStr && !"".equals(vidStr)) {
			paramHql.append(" and x.refId= ?");
			params.add(Integer.parseInt(vidStr));
		}

		/** 模块名称 */
		if (null != moudleName && !"".equals(moudleName)) {
			paramHql.append(" and x.moduleName = ?");
			params.add(moudleName);
		}

		/** 用户名称 */
		if (null != userId) {
			paramHql.append(" and (x.userIds like ? ");
			params.add("%," + userId + ",%");
		}

		/** 用户机构 */
		if (null != groupId) {
			paramHql.append("or x.groupIds like ? ");
			params.add("%," + groupId + ",%");
		}
		/** 用户角色 */
		if (null != roleIdList && roleIdList.size() != 0) {
			for (Integer roleId : roleIdList) {
				paramHql.append(" or x.roleIds like '%," + roleId
						+ ",%'");
			}
			paramHql.append(")");
		}

		if (paramHql.length() > 0) {

			hql.append(paramHql);
			return dataPrivDao.findAll(hql.toString(), params.toArray());
		}

		return dataPrivDao.findAll(hql.toString());

	}

	/**
	 * 返回权限实体类
	 * 
	 * @return DataPriv
	 */

	@SuppressWarnings("deprecation")
	public DataPriv getDataPrivByRefId(Integer refId, String moduleName) {
		return (DataPriv) dataPrivDao
				.findUnique(
						"from DataPriv dataPriv where dataPriv.refId=? and dataPriv.moduleName=?",
						refId, moduleName);
	}

	/**
	 * 修改权限实体类
	 * 
	 * @return void
	 */

	@SuppressWarnings("deprecation")
	public int updateDataPriv(Integer refId, String moduleName, String roleIds,
			String roleNames, String userIds, String userNames,
			String groupIds, String GroupName) {
		return dataPrivDao
				.bulkUpdate(
						"update DataPriv dataPriv set dataPriv.roleIds=? and dataPriv.roleNames=? and dataPriv.userIds=? and dataPriv.userNames=? and dataPriv.groupIds=? and dataPriv.groupNames=? where dataPriv.refId=? and dataPriv.moduleName=?",
						roleIds, roleNames, userIds, userNames, roleIds,
						roleNames, refId, moduleName);
	}

	/**
	 * 删除权限
	 * 
	 * @return void
	 */
	public void deleteFileSort(String refId, String moduleName, String roleIds,
			String groupIds, String userIds, String roleNames,
			String groupNames, String userNames) {

		dataPrivDao.deleteFileSort(refId, moduleName, roleIds, groupIds,
				userIds, roleNames, groupNames, userNames);
	}

	/**
	 * 得到权限列表
	 */
	public List<DataPriv> getFileSortById(String finalFileSortIds,
			String finalFileSortIdPrivs) {

		return dataPrivDao
				.findAll("from DataPriv dataPriv where dataPriv.refId in ("
						+ finalFileSortIds.substring(0,
								finalFileSortIds.length() - 1)
						+ ") and dataPriv.moduleName in ("
						+ finalFileSortIdPrivs + ")");
	}

	/**
	 * 根据人员得到权限列表
	 */

	public List<DataPriv> getFileSortByPerson(String userIds) {

		return dataPrivDao
				.findAll(
						"from DataPriv dataPriv where dataPriv.userIds like ?",
						userIds);
	}

	/**
	 * 
	 * 功能：根据id得到一个权限列表
	 * 
	 * @param fileSortId
	 * @return
	 */
	public List<DataPriv> getChildFileSortById(int fileSortId) {
		return dataPrivDao.getChildFileSortById(fileSortId);
	}

	/**
	 * 
	 * 功能：
	 * 
	 * @param dataprivid
	 * @param roleIds
	 * @param roleNames
	 * @param userIds
	 * @param userNames
	 * @param groupIds
	 * @param GroupName
	 * @return
	 */
	public int updateDataPrivById(Integer dataprivid, String roleIds,
			String roleNames, String userIds, String userNames,
			String groupIds, String GroupName) {

		return dataPrivDao.updateDataPrivById(dataprivid, roleIds, roleNames,
				userIds, userNames, groupIds, GroupName);
	}
	

    /**
     * 根据ID获取对象
     * 功能：
     * @return
     */
	public DataPriv getDataPrivById(Integer id) {
		return dataPrivDao.getDataPrivById(id);
	}
}
