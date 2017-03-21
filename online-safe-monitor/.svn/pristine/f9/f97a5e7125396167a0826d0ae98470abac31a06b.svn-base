package cn.com.wh.dangerchemicals.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.qytx.cbb.file.service.IAttachment;
import cn.com.qytx.platform.base.action.BaseActionSupport;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Sort;
import cn.com.qytx.platform.base.query.Sort.Direction;
import cn.com.wh.dangerchemicals.domain.ChemicalsDirectory;
import cn.com.wh.dangerchemicals.service.ChemicalsDirectoryService;

import com.google.gson.Gson;

/**
 * @author lilipo
 * 
 */
public class ChemicalsDirectoryListAction extends BaseActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2079631018628638667L;

	/**
	 * 列表中的列没有数据
	 */
	private static final String LIST_COLS_NO_DATA = "-";

	/**
	 * 公司危化品目录接品
	 */
	@Autowired
	private ChemicalsDirectoryService cdService;

	/**
	 * 附件接品
	 */
	@Autowired
	private IAttachment attachmentService;

	/**
	 * 公司危化品对象
	 */
	private ChemicalsDirectory chemicalsDirectory;

	private String materialName;
	private Integer whroletype;

	/**
	 * @return
	 */
	public String list() {
		try {
			int pageNum = (int) (Math.ceil((double) this.getIDisplayStart()
					/ (double) this.getIDisplayLength())) + 1;

			// 创建排序规则
			Sort sort = new Sort(new Sort.Order(Direction.DESC, "createDate"));

			// 查询
			Page<ChemicalsDirectory> pageInfo = cdService.findByPage(
					this.getPageable(sort), chemicalsDirectory);

			List<ChemicalsDirectory> list = pageInfo.getContent();
			List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
			int i = (pageNum - 1) * this.getIDisplayLength() + 1;
			if (null != list && list.size() > 0) {

				for (ChemicalsDirectory dc : list) {
					Map<String, Object> map = new HashMap<String, Object>();
					// 序号
					map.put("no", i);

					// 主键
					map.put("vid", dc.getVid());

					// 编号
					map.put("code", StringUtils.defaultString(dc.getCode(),
							LIST_COLS_NO_DATA));

					// 品名
					map.put("materialName", StringUtils.defaultString(
							dc.getMaterialName(), LIST_COLS_NO_DATA));

					// 别名
					map.put("molecularFormula", StringUtils.defaultString(
							dc.getMolecularFormula(), LIST_COLS_NO_DATA));

					// CAS号
					map.put("cas", StringUtils.defaultString(dc.getCas(),
							LIST_COLS_NO_DATA));

					// 备注
					map.put("other", StringUtils.defaultString(dc.getOther(),
							LIST_COLS_NO_DATA));

					mapList.add(map);
					i++;
				}
			}
			Map<String, Object> jsonMap = new HashMap<String, Object>();
			jsonMap.put("iTotalRecords", pageInfo.getTotalElements());
			jsonMap.put("iTotalDisplayRecords", pageInfo.getTotalElements());
			jsonMap.put("aaData", mapList);
			Gson json = new Gson();
			String jsons = json.toJson(jsonMap);
			PrintWriter writer = new PrintWriter(this.getResponse().getWriter());
			writer.print(jsons);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}

	public String getMaterialName() {
		return materialName;
	}

	public ChemicalsDirectory getChemicalsDirectory() {
		return chemicalsDirectory;
	}

	public void setChemicalsDirectory(ChemicalsDirectory chemicalsDirectory) {
		this.chemicalsDirectory = chemicalsDirectory;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public Integer getWhroletype() {
		return whroletype;
	}

	public void setWhroletype(Integer whroletype) {
		this.whroletype = whroletype;
	}

}
