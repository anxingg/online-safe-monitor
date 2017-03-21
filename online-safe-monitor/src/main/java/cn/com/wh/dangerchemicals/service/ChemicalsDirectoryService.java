package cn.com.wh.dangerchemicals.service;

import java.util.List;

import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.wh.dangerchemicals.domain.ChemicalsDirectory;

public interface ChemicalsDirectoryService {

	/**
	 * 新增或修改
	 * 
	 * @param dangerChemicals
	 * @return ChemicalsDirectory
	 */
	ChemicalsDirectory saveOrUpdate(ChemicalsDirectory dangerChemicals);

	/**
	 * 查询一个对象
	 * 
	 * @param id
	 *            主键
	 * @return ChemicalsDirectory
	 */
	ChemicalsDirectory findOne(Integer id);

	/**
	 * 分页查询
	 * 
	 * @param pageable
	 * @param dangerChemicals
	 * @param whroletype
	 * @return Page<ChemicalsDirectory>
	 */
	Page<ChemicalsDirectory> findByPage(Pageable pageable,
			ChemicalsDirectory dangerChemicals);

	/**
	 * 查询这个物质名称或分子式是否已经使用了。
	 * 
	 * @param id
	 *            主键
	 * @param name
	 *            物质名称或分子式
	 * @param which
	 *            用来区分传来的名称是物质名称还是分子式（1表示 物质名称； 2表示分子式）
	 * @return boolean 返回 true 表示已经使用了； 返回 false 表示还没有被使用。
	 */
	boolean usedThisName(Integer id, String name, int which);
	
	/**
	 * @return
	 */
	List<ChemicalsDirectory> findAll();
}
