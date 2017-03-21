package cn.com.qytx.hotline.ivr.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import cn.com.qytx.hotline.ivr.domain.Msiuser;
import cn.com.qytx.platform.base.dao.BaseDao;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.qytx.platform.org.domain.UserInfo;

/**
 * 项目名称：wzerp 类名称：话务员登记Dao 类描述： 创建人：WangBin 创建时间：2012-11-21
 * @version
 */
@SuppressWarnings("deprecation")
@Component
public class MsiuserDao extends BaseDao<Msiuser, Integer> implements Serializable {

	private static final long serialVersionUID = -287573700266298603L;

	/**
	 * 功能：获取坐席列表
	 * @return List<Msiuser>
	 */
	public List<Msiuser> findMymisUserList(UserInfo userInfo) {
		String hql = " vid is not null ";
		// 只能查询本部门的坐席情况
		return this.dataFilter().findAll(hql);
	}
	
	/**
	 * 获取坐席分页
	 * @param pageInfo
	 * @param isForkGroup
	 * @param searchKey 
	 * @return
	 */
	public Page<Msiuser> monitorMisuserByPage(Pageable pageInfo, Integer isForkGroup, String searchKey){
		StringBuilder hql = new StringBuilder();
		hql.append("  vid is not null and state in ( 1, 3, 4, 5, 13)");
		if(isForkGroup!=null&&isForkGroup!=-1){
			hql.append(" and isForkGroup="+isForkGroup);
		}
		if(StringUtils.isNotEmpty(searchKey)){
			hql.append(" and (workNo like '%"+searchKey+"%' or name like '%"+searchKey+"%')");
		}
		return this.findAll(hql.toString(), pageInfo);
	}

	/**
	 * @Title:查询话务员分页列表
	 * @Description:
	 * @param @param pageInfo
	 * @param @param params
	 * @param @param workIds 坐席ids
	 * @param @return
	 * @return 返回类型
	 * @throws
	 */
	public Page<Msiuser> findMsiuserByPage(Pageable pageInfo, String params,
			UserInfo userInfo, Integer queueId, Integer groupId, String workIds,Integer isForkGroup) {
		String hql = " vid is not null ";
		List<Object> paramList = new ArrayList<Object>();
		if (!isEmpty(workIds)) {
			workIds = workIds.replace(",", "','");
			hql += " and name like '%" + workIds + "%' ";
		}
		if(isForkGroup!=null){
			hql += " and isForkGroup = ? ";
			paramList.add(isForkGroup);
		}
		if (!isEmpty(params) && !params.equals("请输入工号或绑定号码")) {
			hql += " and workNo like ? or msiphone like ?";
			paramList.add("%" + params + "%");
			paramList.add("%" + params + "%");
		}	
		// 队列Id
		if (null != queueId && -1 != queueId) {
			hql += "  and vid in (select mu.msiid from MsiserviceUser mu where mu.serviceId = ?)";
			paramList.add(queueId);
		}

		if (null != paramList && paramList.size() > 0) {
			return this.dataFilter().findAll(hql, pageInfo, paramList.toArray());
		} else {
			return this.dataFilter().findAll(hql, pageInfo);
		}
	}

	/**
	 * @Title:查询话务员分页总记录数
	 * @Description:
	 * @param @param params
	 * @param @return
	 * @return 返回类型
	 * @throws
	 */
	public int getTotalCount(String params) {
		String hql = " from Msiuser m where m.vid is not null ";
		List<Object> paramList = new ArrayList<Object>();
		if (!isEmpty(params) && !params.equals("请输入工号或绑定号码")) {
			hql += " and m.workNo like ? or m.msiphone like ?";
			paramList.add("%" + params + "%");
			paramList.add("%" + params + "%");
		}
		List<Object> msiuserList;
		if (null != paramList && paramList.size() > 0) {
			msiuserList = this.find(hql, paramList.toArray());
		} else {
			msiuserList = this.find(hql);
		}
		if (null != msiuserList && msiuserList.size() > 0) {
			return msiuserList.size();
		} else {
			return 0;
		}
	}

	/**
	 * @Title:根据工号查询Id
	 * @Description:
	 * @param @param workNo
	 * @param @return
	 * @return 返回类型
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public List<Object> findIdByWorkNo2(String workNo) {
		String hql = " select m.vid from Msiuser m where m.workNo = ?";
		return entityManager.createQuery(hql).setParameter(1, workNo).getResultList();
	}

	/**
	 * @Title:得到列表
	 * @Description:
	 * @param @param workNo
	 * @param @return
	 * @return 返回类型
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public List<Object> misUserList() {
		String hql = " select WorkNo from MSIUser ";
		return entityManager.createNativeQuery(hql).getResultList();
	}

	/**
	 * @Title:根据绑定电话查询Id
	 * @Description:
	 * @param @param msiphone
	 * @param @return
	 * @return 返回类型
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public List<Object> findIdByPhone(String msiphone) {
		String hql = " select m.vid from Msiuser m where m.msiphone = ?";
		return entityManager.createQuery(hql).setParameter(1, msiphone).getResultList();
	}
	/**
	 * 字符串是否为空
	 * 
	 * @param str
	 * @return
	 */
	public boolean isEmpty(String str) {
		if (str == null || str.equals("")) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * 根据坐席角色删除对应的坐席信息
	 * @param role  坐席角色
	 */
	public void deleteByRole(Integer role) {
		if (null != role) {
			String hql = "delete Msiuser where role=?";
			executeQuery(hql, role);
		}
	}
	/**
	 * @Title:根据工号查询Id
	 * @Description:
	 * @param @param workNo
	 * @param @return
	 * @return 返回类型
	 * @throws
	 */
	public Msiuser findByWorkNo(String workNo) {
		return this.findOne("workNo=?", workNo);
	}
	/**
	 * 功能：根据登录名删除坐席登录用户
	 * 
	 * @param workNo
	 */
	public void deleteByWorkNo(String workNo) {
		String hql = "delete Msiuser m where m.workNo = ?";
		executeQuery(hql, workNo);
	}
	/**
	 * 
	 * 功能：坐席端登录校验
	 * 
	 * @param workNo
	 * @param pass
	 * @return
	 */
	public Msiuser login(String workNo, String pass) {
		Object obj = super.findUnique("from Msiuser where workNo= ? and pass=?",workNo, pass);
		if (obj instanceof Msiuser) {
			return (Msiuser) obj;
		} else {
			return null;
		}
	}

	/**
	 * 
	 * 功能：获取坐席列表
	 * 
	 * @return List<Msiuser>
	 */
	public List<Msiuser> findAllmisUserList() {
		return this.findAll();
	}

	/**
	 * 
	 * 功能：获取坐席列表
	 * 
	 * @return List<Msiuser>
	 */
	public List<Msiuser> findMsiUserByWorkNo(String workNo) {
		String hql = "from Msiuser where workNo like ? order by workNo";
		return this.findAll(hql, "%" + workNo + "%");
	}

	/**
	 * 
	 * 功能：根据坐席服务Id,获取对应坐席
	 * 
	 * @return List<Msiuser>
	 */
	public List<Msiuser> findMsiUserByServiceId(String serviceId) {
		String hql = " vid in (select mu.msiid from MsiserviceUser mu where mu.serviceId = ?)";
		return this.findAll(hql, Integer.parseInt(serviceId));
	}

	public List<Msiuser> findMsiUserListByIds(String ids) {
		if( StringUtils.isNotBlank(ids) ){
			String hql = "vid in (" + ids + ")";
			return this.findAll(hql);
		}else{
			return null;
		}
	}

	/**
	 * 功能：根据用户表Id获取对应的坐席数据
	 * 
	 * @param msiUserIdArr
	 *            msiUserIdArr
	 * @return List<Msiuser>
	 */
	public List<Msiuser> findMsiUserByUserIds(String[] msiUserIdArr) {
		if (null != msiUserIdArr) {
			StringBuffer hql = new StringBuffer();
			hql.append("workNo in ( select u.loginName from UserInfo u where  u.isDelete=0 and u.userId in(");

			for (int i = 0; i < msiUserIdArr.length; i++) {
				if (StringUtils.isEmpty(msiUserIdArr[i])) {
					continue;
				}
				hql.append(msiUserIdArr[i]);
				if (i != msiUserIdArr.length - 1) {
					hql.append(",");
				}
			}
			hql.append("))");
			return super.findAll(hql.toString());
		}
		return null;
	}
	
	/**
	 * 功能：查询当前在线坐席的分组
	 * @param msiType 内线/外线
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Object> findMsiUserGruops(Integer msiType,Integer isForkGroup){
		StringBuilder sql = new StringBuilder();
		sql.append("select is_fork_group from MSIUser where msiWorkState<>0 and msiWorkState<>2 and MSIWorkType=1 and MSIType="+msiType+" ");
		sql.append(" group by is_fork_group order by is_fork_group desc");
		return this.entityManager.createNativeQuery(sql.toString()).getResultList();
	}
	
	public List<Msiuser> findMsiUserBySearchKey(String searchKey){
		StringBuilder hql = new StringBuilder();
		hql.append("1=1");
		if(StringUtils.isNotBlank(searchKey)){
			hql.append(" and (workNo like '%"+searchKey+"%' or name like '%"+searchKey+"%') ");
		}
		return super.findAll(hql.toString());
	}
	
}
