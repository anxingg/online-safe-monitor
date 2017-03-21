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
import cn.com.wh.dangerchemicals.domain.DangerChemicalsInfo;
import cn.com.wh.dangerchemicals.service.DangerChemicalsService;

import com.google.gson.Gson;

/**
 * @author lilipo
 * 
 */
public class DangerChemicalsListAction extends BaseActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2499355497145273547L;

	/**
	 * 列表中的列没有数据
	 */
	private static final String LIST_COLS_NO_DATA = "-";

	/**
	 * 公司危化品接品
	 */
	@Autowired
	private DangerChemicalsService dcService;

	/**
	 * 附件接品
	 */
	@Autowired
	private IAttachment attachmentService;

	/**
	 * 公司危化品对象
	 */
	private DangerChemicalsInfo dangerChemicals;

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
			Page<DangerChemicalsInfo> pageInfo = dcService.findByPage(
					this.getPageable(sort), dangerChemicals);

			List<DangerChemicalsInfo> list = pageInfo.getContent();
			List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
			int i = (pageNum - 1) * this.getIDisplayLength() + 1;
			if (null != list && list.size() > 0) {

				for (DangerChemicalsInfo dc : list) {
					Map<String, Object> map = new HashMap<String, Object>();
					// 序号
					map.put("no", i);

					// 主键
					map.put("vid", dc.getVid());

					// 物质名称
					map.put("materialName", StringUtils.defaultString(
							dc.getMaterialName(), LIST_COLS_NO_DATA));

					// 分子式
					map.put("molecularFormula", StringUtils.defaultString(
							dc.getMolecularFormula(), LIST_COLS_NO_DATA));

					// 稳定性
					Integer stability = dc.getStability();
					if(stability == null){
						map.put("stabilityStr", LIST_COLS_NO_DATA);
					}else if (stability == 0){
						map.put("stabilityStr", "不稳定");
					}else {
						map.put("stabilityStr", "稳定");
					}

					// 侵入途径
					map.put("intrusiveWay", StringUtils.defaultString(showIntrusiveWay(dc.getIntrusiveWay()), LIST_COLS_NO_DATA));

					// 外观与性状
					map.put("appearance", StringUtils.defaultString(dc.getAppearance(), LIST_COLS_NO_DATA));

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
	
	private String showIntrusiveWay(String intrusiveWay){
		
		if(StringUtils.isBlank(intrusiveWay) || ",".equals(intrusiveWay)){
			return null;
		}else {
			String[] iws = intrusiveWay.split(",");
			StringBuffer sbf = new StringBuffer();
			for(String s: iws){
				if(StringUtils.isNotBlank(s)){
					sbf.append(switchIntrusiveWay(Integer.parseInt(s)));
					sbf.append("、");
				}
			}
			if(sbf.length() > 0){
				return sbf.substring(0, sbf.length()-1);
			}else {
				return sbf.toString();
			}
		}
	}
	
	private String switchIntrusiveWay(int intrusiveWay){
		//1、吸入； 2、皮肤； 3、口
		switch(intrusiveWay){
			case 1:
				return "吸入";
			case 2:
				return "皮肤";
			case 3:
				return "误食";
			default:
				return "";
		}
	}

	// private Attachment getAttachmentById(final String reportId) {
	// String temp = null;
	// if (StringUtils.isBlank(reportId)) {
	// return null;
	// } else {
	// temp = reportId.replaceAll(",", "");
	// return attachmentService.findOne(Integer.parseInt(temp));
	// }
	// }

	public String getMaterialName() {
		return materialName;
	}

	public DangerChemicalsInfo getDangerChemicals() {
		return dangerChemicals;
	}

	public void setDangerChemicals(DangerChemicalsInfo dangerChemicals) {
		this.dangerChemicals = dangerChemicals;
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
