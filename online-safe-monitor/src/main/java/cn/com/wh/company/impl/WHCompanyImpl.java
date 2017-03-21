package cn.com.wh.company.impl;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.qytx.platform.base.service.impl.BaseServiceImpl;
import cn.com.qytx.platform.org.service.IUser;
import cn.com.wh.company.dao.WHCompanyDao;
import cn.com.wh.company.domain.WHCompany;
import cn.com.wh.company.service.IWHCompany;
import cn.com.wh.login.dao.WhEnterpriseDao;
import cn.com.wh.login.domain.WhEnterprise;

@Service("companyImpl")
@Transactional
public class WHCompanyImpl extends BaseServiceImpl<WHCompany> implements IWHCompany ,Serializable{

	private static final long serialVersionUID = -5443442130361309154L;

	@Resource
	WHCompanyDao wHCompanyDao;
	/** 用户信息 */
    @Resource(name = "userService")
    IUser userService;
    @Autowired
	private WhEnterpriseDao whEnterpriseDao;

	/**
	 * 分页查询  公司信息 
	 * @param pageable 分页信息
	 * @param groupId 部门
	 * @return Page 分页结果数据
	 */
	@Override
	public Page<WHCompany> findWHCompanyByPage(Pageable pageable, Integer groupId) {
		Page<WHCompany> page  = wHCompanyDao.findWHCompanyByPage(pageable, groupId);
		if (page!=null) {
			List<WHCompany> list = page.getContent();
			Map<String, WhEnterprise> map = new HashMap<String, WhEnterprise>();
			String linkIds = "";
			if (list!=null&&list.size()>0) {
				for (WHCompany whCompany : list) {
					linkIds += "," +whCompany.getLinkId();
				}
			}
			if (StringUtils.isNotBlank(linkIds)) {
				linkIds = linkIds.substring(1);
				List<WhEnterprise> enterlist = whEnterpriseDao.findListByEnterpriseCode(linkIds);
				if (enterlist!=null&&enterlist.size()>0) {
					for (WhEnterprise whEnterprise : enterlist) {
						map.put(whEnterprise.getCode(), whEnterprise);
					}
				}
			}
			for (WHCompany whCompany : list) {
				if (map.get(whCompany.getLinkId())!=null) {
					WhEnterprise info = map.get(whCompany.getLinkId());
					whCompany.setCompanyName(info.getEnterpriseName());
					whCompany.setLegalRepresentative(info.getLegalPerson());
				}
			}
		}
		return page;
	}
	
	@Override
	public String getCompanyName(Integer groupId){
		String companyName = "";
		WHCompany cpy = wHCompanyDao.findByGroupId(groupId);
		if (cpy!=null) {
			//从客户表中替换最新的企业信息
			WhEnterprise enterprise = whEnterpriseDao.findByEnterpriseCode(cpy.getLinkId());
			if (enterprise!=null) {
				companyName = enterprise.getEnterpriseName();
			}
		}
		return companyName;
	}

	@Override
	public WHCompany findByGroupId(Integer groupId) {
		WHCompany cpy = wHCompanyDao.findByGroupId(groupId);
		if (cpy!=null) {
			//从客户表中替换最新的企业信息
			WhEnterprise enterprise = whEnterpriseDao.findByEnterpriseCode(cpy.getLinkId());
			if (enterprise!=null) {
				cpy.setCompanyName(enterprise.getEnterpriseName());
				cpy.setRegistrationAddress(enterprise.getRegisteredAddress());
				//处理cityId
				if (enterprise.getAreaCode()!=null) {
					String cityName = whEnterpriseDao.getCityNameByCityCode(enterprise.getAreaCode());
					cpy.setCityId(cityName);
				}
				if (enterprise.getSetUpDate()!=null) {
					cpy.setEstablishmentTime(new Timestamp(enterprise.getSetUpDate().getTime()));
				}else {
					cpy.setEstablishmentTime(null);
					
				}
				cpy.setCompanyProperty(enterprise.getNature());
				cpy.setBusinessLicence(enterprise.getLicenseCode());
				cpy.setProductionScope(enterprise.getProductionRange());
				cpy.setLegalRepresentative(enterprise.getLegalPerson());
				//处理经济类型
				if (enterprise.getEconomyType()!=null||enterprise.getEconomySubType()!=null) {
					if (enterprise.getEconomySubType()!=null) {
						String ecoName = whEnterpriseDao.getEconomyTypeByTypeCode(enterprise.getEconomySubType());
						cpy.setEconomicType(ecoName);
					}else {
						String ecoName = whEnterpriseDao.getEconomyTypeByTypeCode(enterprise.getEconomyType());
						cpy.setEconomicType(ecoName);
					}
				}else {
					cpy.setEconomicType("");
				}
				cpy.setUnitCode(enterprise.getBaseOrgCode());
				cpy.setProductAddress(enterprise.getProductionAddress());
				cpy.setWebsite(enterprise.getWebUrl());
				cpy.setPostalcode(enterprise.getPostCode());
				//处理行业分类
				if (StringUtils.isNotBlank(enterprise.getIndustryTypes())) {
					String industryName = whEnterpriseDao.getIndustryTypeByTypeCode(enterprise.getIndustryTypes());
					cpy.setIndustryClassification(industryName);
				}else {
					cpy.setIndustryClassification("");
				}
				cpy.setIsIn(enterprise.getIsInGarden());
				cpy.setPrecision(enterprise.getLongitude());
				cpy.setDimension(enterprise.getLatitude());
			}
		}
		return cpy;
	}
	
	/**
	 * 根据公司名称 查询所属公司信息
	 * @param companyName
	 * @return 公司
	 */
	@Override
	public WHCompany findByCompanyName(String companyName){
		
		return wHCompanyDao.findByCompanyName(companyName);
	}
	/**
	 * 根据人员id删除所在公司
	 * @param userIds
	 * @param companyId
	 */
	public void deleteCompanyByUserIds(String userIds,Integer companyId){
		wHCompanyDao.deleteCompanyByUserIds(userIds);
		userService.deleteUserByIds(userIds, false, companyId);
	}
	
	/**
	 * 根据linkid 查询公司信息
	 * @param linkId 
	 * @return 公司
	 */
	public WHCompany findByLinkId(String linkId){
		return wHCompanyDao.findByLinkId(linkId);
	}
	
}
