package cn.com.wh.zhdwxy.action;

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
import cn.com.wh.safeaccident.util.Tool;
import cn.com.wh.zhdwxy.domain.DangerSourcesGood;
import cn.com.wh.zhdwxy.service.IDangerSourcesGood;

import com.google.gson.Gson;

/**
 * 
 * @author lilipo
 * 
 */
public class DangerSourcesGoodListAction extends BaseActionSupport {


	/**
	 * 
	 */
	private static final long serialVersionUID = 7156277801956455795L;

	/**
	 * 列表中的列没有数据
	 */
	private static final String LIST_COLS_NO_DATA = "-";

	/**
	 * 接口
	 */
	@Autowired
	private IDangerSourcesGood dsgService;

	/**
	 * 附件接口
	 */
	@Autowired
	private IAttachment attachmentService;

	/**
	 * 对象
	 */
	private DangerSourcesGood dangerSourcesGood;

	/**
	 * 分页查询
	 * @return
	 */
	public String list() {
		try {
			int pageNum = (int) (Math.ceil((double) this.getIDisplayStart()
					/ (double) this.getIDisplayLength())) + 1;
			
			// 创建排序规则
			Sort sort = new Sort(new Sort.Order(Direction.DESC, "vid"));

			// 查询
			Page<DangerSourcesGood> pageInfo = dsgService.findByPage(
					this.getPageable(sort), dangerSourcesGood);

			List<DangerSourcesGood> list = pageInfo.getContent();
			List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
			int i = (pageNum - 1) * this.getIDisplayLength() + 1;
			if (null != list && !list.isEmpty()) {
				//获取危险性类别数据字典
				Map<Integer, String> dangerObjTypeMap = Tool.loadaccidentCharacterTypemap("dangerObjType");

				for (DangerSourcesGood dsg : list) {
					Map<String, Object> map = new HashMap<String, Object>();
					// 序号
					map.put("no", i++);

					// 主键
					map.put("vid", dsg.getVid());

					// 危化品名称
					map.put("dangerGoodName", StringUtils.defaultString(dsg.getDangerGoodName(),
							LIST_COLS_NO_DATA));

					// 危险性类别
					map.put("dangerObjType", StringUtils.defaultString(
							dangerObjTypeMap.get(dsg.getDictId()), LIST_COLS_NO_DATA));

					// UN编号
					map.put("unCode", StringUtils.defaultString(
							dsg.getUnCode(), LIST_COLS_NO_DATA));

					// 生产用途
					map.put("purpose", StringUtils.defaultString(dsg.getPurpose(),
							LIST_COLS_NO_DATA));

					// 生产工艺
					map.put("process", StringUtils.defaultString(dsg.getProcess(),
							LIST_COLS_NO_DATA));
					
					// 单元内存量
					map.put("physicalState", StringUtils.defaultString(dsg.getPhysicalState(),
							LIST_COLS_NO_DATA));

					mapList.add(map);
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

	public DangerSourcesGood getDangerSourcesGood() {
		return dangerSourcesGood;
	}

	public void setDangerSourcesGood(DangerSourcesGood dangerSourcesGood) {
		this.dangerSourcesGood = dangerSourcesGood;
	}


}
