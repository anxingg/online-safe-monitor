package cn.com.qytx.hotline.ivr.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Component;

import cn.com.qytx.hotline.ivr.domain.MsiCompanyInfo;
import cn.com.qytx.platform.base.dao.BaseDao;

/**
 * User: 黄普友 Date: 12-11-1 Time: 上午12:06
 */
@SuppressWarnings("serial")
@Component
public class MsiCompanyInfoDao extends BaseDao<MsiCompanyInfo,Integer> implements Serializable
{
    /**
     * 根据id获取公司信息
     * @param vid vid
     * @return CompanyInfo
     */
    public MsiCompanyInfo findByVid(Integer vid)
    {
        if (null != vid)
        {
			//Object obj = super.findOne("companyId=?", vid);
        	javax.persistence.Query query = super.entityManager.createQuery("from MsiCompanyInfo where companyId = :companyId");
        	query.setParameter("companyId", vid);
        	Object obj = null;
    		try {
    			obj = query.getSingleResult();
    		} catch (javax.persistence.NoResultException e) {
    			
    		} catch (javax.persistence.NonUniqueResultException ex) {
    			obj = query.getResultList();
    			obj = ((List<?>) obj).get(0);
    		}
            if (obj instanceof MsiCompanyInfo)
            {
                return (MsiCompanyInfo) obj;
            }
        }        
        return null;        
    }
}
