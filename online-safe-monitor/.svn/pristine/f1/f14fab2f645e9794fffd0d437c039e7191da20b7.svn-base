package cn.com.qytx.hotline.balck.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import cn.com.qytx.hotline.balck.domain.Blacklist;
import cn.com.qytx.oa.domain.OaConst;
import cn.com.qytx.platform.base.dao.BaseDao;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;

/**
 * 功能: 黑名单dao
 * 版本: 1.0
 * 开发人员: 李华伟
 * 创建日期: 2013-8-19
 * 修改日期: 2013-8-19
 * 修改列表:
 */
@Component
public class BlacklistDao extends BaseDao<Blacklist, Integer> implements Serializable
{
    
	private static final long serialVersionUID = 1L;

	/**
     * 重写save方法
     */
    @Override
    public Blacklist saveOrUpdate(Blacklist black)
    {
        if (null != black.getId())
        {
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE Blacklist SET begintime = GETDATE(), endtime = (select dateadd(hh, "
                    + black.getDuration() + " ,GETDATE()))");
            sql.append(", createtime = GETDATE(), create_user_id=" + black.getCreateUserId());
            sql.append(", duration = " + black.getDuration() + ", lockType = " + black.getLockType() + ", remark = '" + black.getRemark());
            sql.append("', company_id = " + black.getCompanyId());
            sql.append(" where vid = " + black.getId() + "");
            super.entityManager.createNativeQuery(sql.toString()).executeUpdate();
            return black;
        }
        else
        {
            StringBuilder sql = new StringBuilder(
                    "insert into blacklist (phone,begintime,endtime,createtime,create_user_id,isdelete,duration,lockType,remark,company_id) values('");
            sql.append(black.getPhone()).append("',GETDATE(),(select dateadd(hh,")
                    .append(black.getDuration()).append(",GETDATE())),GETDATE(),");
            sql.append(black.getCreateUserId()).append(",0,").append(black.getDuration()).append(",")
                    .append(black.getLockType()).append(",'")
                    .append(black.getRemark()).append("',")
                    .append(black.getCompanyId()).append(")");
            super.entityManager.createNativeQuery(sql.toString()).executeUpdate();            
            return black;
        }
    }

    /**
     * 功能：分页查询
     * @param page 分页信息
     * @return Page
     */
    public Page<Blacklist> findAll(Pageable pageable, String phone)
    {
        String hql = " isDelete = 0 ";
        List<Object> params = new ArrayList<Object>();
        if (!StringUtils.isEmpty(phone))
        {
            hql += " and phone like ?";
            params.add("%" + phone + "%");
        }
        return super.findAll(hql, pageable, params.toArray());
    }

    /**
     * 功能：根据电话查询黑名单信息
     * @param phone 电话
     * @return Blacklist
     */
    public Blacklist findByPhone(String phone)
    {
        String hql = "isDelete = ? and phone = ?";
        return (Blacklist) super.dataFilter().findOne(hql, OaConst.NOT_DELETE, phone);
    }
}
