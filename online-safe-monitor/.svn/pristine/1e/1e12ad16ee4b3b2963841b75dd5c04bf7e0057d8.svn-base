package cn.com.wh.dangerchemicals.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import cn.com.qytx.platform.base.dao.BaseDao;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.wh.dangerchemicals.domain.ChemicalsDirectory;

@Repository
public class ChemicalsDirectoryDao extends BaseDao<ChemicalsDirectory, Serializable>
		implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7769846065626354453L;

	public Page<ChemicalsDirectory> findByPage(Pageable pageable,
			ChemicalsDirectory dangerChemicals) {

		StringBuffer sbf = new StringBuffer(" isDelete = 0 ");

		List<Object> params = new ArrayList<Object>();

		// 编号
		String code = dangerChemicals.getCode();
		
		if (StringUtils.isNotBlank(code)) {
			sbf.append(" and code like ?");
			params.add("%" + code + "%");
		}

		// 物质名称
		String materialName = dangerChemicals.getMaterialName();

		if (StringUtils.isNotBlank(materialName)) {
			sbf.append(" and materialName like ?");
			params.add("%" + materialName + "%");
		}

		return super.findAll(sbf.toString(), pageable, params.toArray());
	}

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
	public boolean usedThisName(Integer id, String name, int which) {
		List<Object> params = new ArrayList<Object>();

		StringBuffer sbf = new StringBuffer(" isDelete = 0");
		sbf.append(" and ")
				.append(which == 1 ? "materialName" : "molecularFormula")
				.append(" = ?");
		params.add(name);
		if (id != null) {
			sbf.append(" and vid != ?");
			params.add(id);
		}
		System.out.println(sbf);
		return ((which != 1 && which != 2) || StringUtils.isBlank(name)) ? false
				: haveSome(getCollectionSize(super.findAll(sbf.toString(),
						params.toArray())));
	}

	private boolean haveSome(int n) {
		return (n > 0) ? true : false;
	}

	private int getCollectionSize(Collection<?> c) {
		return (c == null || c.isEmpty()) ? 0 : c.size();
	}

}
