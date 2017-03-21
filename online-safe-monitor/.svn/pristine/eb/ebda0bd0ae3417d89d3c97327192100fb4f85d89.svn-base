package cn.com.wh.dangerchemicals.service;

import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.wh.dangerchemicals.domain.CompanyDangerChemicals;
import cn.com.wh.dangerchemicals.domain.CompanyDangerChemicalsStatisticsResult;

public interface CompanyDangerChemicalsService {

	/**
	 * 新增或修改对象
	 * 
	 * @param cdc
	 * @return CompanyDangerChemicals
	 */
	CompanyDangerChemicals saveOrUpdate(CompanyDangerChemicals cdc);

	/**
	 * 通过主键查询一个对象
	 * 
	 * @param vid
	 *            主键
	 * @return CompanyDangerChemicals
	 */
	CompanyDangerChemicals findOne(Integer vid);

	/**
	 * 分页查询
	 * 
	 * @param pageable
	 * @param materialName
	 *            物质名称
	 * @param dangerId
	 *            危险品ID
	 * @param groupId
	 *            企业ID
	 * @param whroletype
	 *            角色
	 * @return Page<CompanyDangerChemicals>
	 */
	Page<CompanyDangerChemicals> findByPage(Pageable pageable,
			String materialName, Integer dangerId, Integer groupId,
			Integer whroletype);

	/**
	 * 分页统计查询
	 * @param pageable
	 * @param materialName 物质名称
	 * @param statisticsType 统计类型（1、园区； 2、企业）
	 * @param groupId 企业ID
	 * @return Page<CompanyDangerChemicalsStatisticsResult>
	 */
	Page<CompanyDangerChemicalsStatisticsResult> findStatisticsResultByPage(
			Pageable pageable, String materialName, Integer statisticsType,
			Integer groupId);
}
