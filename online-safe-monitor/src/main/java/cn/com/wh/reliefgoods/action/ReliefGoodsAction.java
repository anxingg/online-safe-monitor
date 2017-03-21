package cn.com.wh.reliefgoods.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import cn.com.qytx.cbb.dict.service.IDict;
import cn.com.qytx.platform.base.action.BaseActionSupport;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Sort;
import cn.com.qytx.platform.base.query.Sort.Direction;
import cn.com.qytx.platform.log.service.ILog;
import cn.com.qytx.platform.log.service.LogType;
import cn.com.wh.reliefgoods.domain.ReliefGoods;
import cn.com.wh.reliefgoods.domain.SearchVo;
import cn.com.wh.reliefgoods.service.IReliefGoods;
import cn.com.wh.safeaccident.util.Tool;

import com.google.gson.Gson;
/**
 * 
 * <br/>功能:应急机构action
 * <br/>版本: 1.0
 * <br/>开发人员: 吴洲
 * <br/>创建日期: 2015年9月28日
 * <br/>修改日期: 2015年9月28日
 * <br/>修改列表:
 */
public class ReliefGoodsAction extends BaseActionSupport{

	private static final long serialVersionUID = -9036816638265397456L;
	
	@Autowired
	private IReliefGoods reliefGoodsService;
	
	/**
	 * 系统日志接口
	 */
	@Resource
	private ILog logService;
	
	@Autowired
	private IDict dictService;
	
	private SearchVo  vo;//查询vo
	
	private ReliefGoods info;
	
	private Integer goodsId;
	
	/**
	 * 
	 * 功能：救援物资分页
	 * @return
	 */
	public String findGoodsList(){
		try {
			int pageNum = (int) (Math.ceil((double) this.getIDisplayStart() / (double) this.getIDisplayLength())) + 1;
			Sort sort = new Sort(new Sort.Order(Direction.ASC, "groupId"),new Sort.Order(Direction.DESC, "goodType"),new Sort.Order(Direction.ASC, "createTime"));
			Page<ReliefGoods> pageInfo = reliefGoodsService.findByPage(this.getPageable(sort), vo);
			List<ReliefGoods> list = pageInfo.getContent();
			List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
			Map<String, String> dictmap = dictService.findMap("goodsType", 1);
			int i = (pageNum - 1) * this.getIDisplayLength() + 1;
			if (null != list && list.size() > 0) {
				for (ReliefGoods info : list) {
					Map<String, Object> map = new HashMap<String, Object>();
					//主键
					map.put("id", info.getId());
					//序号
					map.put("no", i);
					//装备名称
					map.put("title", info.getGoodName()==null?"":info.getGoodName());
					//所属类别
					if(info.getGoodType()!=null){
						if (dictmap!=null&&dictmap.get(info.getGoodType().toString())!=null) {
							map.put("titleType", dictmap.get(info.getGoodType().toString()));
						}else {
							map.put("titleType", "");
						}
					}else{
						map.put("titleType", "");
					}
					//单位
					map.put("unit", info.getGoodUnit()==null?"":info.getGoodUnit());
					//数量
					map.put("count", info.getGoodNum()==null?0:info.getGoodNum());
					//配备地点
					map.put("place", info.getEquippedPlace()==null?"":info.getEquippedPlace());
					//保管人
					map.put("keeper", info.getKeeper()==null?"":info.getKeeper());
					//电话
					map.put("phone", info.getPhone()==null?"":info.getPhone());
					//公司名称
					map.put("companyName", info.getGroupName()==null?"":info.getGroupName());
					//部门id
					map.put("groupId", info.getGroupId());
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
	
    /**
     * 
     * 功能：添加/修改救援物资
     * @return
     */
	public String saveOrUpdateGoods() {
		try {
			if (info.getId()!=null) {
				ReliefGoods oldInfo = reliefGoodsService.findOne(info.getId());
				oldInfo.setEquippedPlace(info.getEquippedPlace());
				oldInfo.setGoodName(info.getGoodName());
				oldInfo.setGoodNum(info.getGoodNum());
				oldInfo.setGoodType(info.getGoodType());
				oldInfo.setGoodUnit(info.getGoodUnit());
				oldInfo.setKeeper(info.getKeeper());
				oldInfo.setPhone(info.getPhone());
				reliefGoodsService.saveGoods(oldInfo);
				ajax("1");
				//记录日志
				logService.saveOrUpdate( Tool.generateLog(getLoginUser(), 
						this.getRequest().getRemoteAddr(), 
						"修改救援物资成功", 
						LogType.LOG_JYWZ_UPDATE, 
						oldInfo, 
						oldInfo.getId()) );
			}else {
				info.setCreateTime(new Date());
				info.setIsDelete(0);
				info.setIsForkGroup(getLoginUser().getIsForkGroup());
				info.setGroupId(getLoginUser().getIsForkGroup());
				info.setGroupName(getSession().getAttribute("companyName").toString());
				info.setCreateUserId(getLoginUser().getUserId());
				reliefGoodsService.saveGoods(info);
				ajax("1");
				
				//记录日志
				logService.saveOrUpdate( Tool.generateLog(getLoginUser(), 
						this.getRequest().getRemoteAddr(), 
						"新增救援物资成功", 
						LogType.LOG_JYWZ_ADD, 
						info, 
						info.getId()) );
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			ajax("0");
		}
		return null;
	}
	
	
	/**
	 * 功能：删除救援物资
	 * 
	 * @return
	 */
	public String deleteGoods() {
		try {
			reliefGoodsService.deleteGoods(goodsId);
			ajax("1");
			
			//记录日志
			logService.saveOrUpdate( Tool.generateLog(getLoginUser(), 
					this.getRequest().getRemoteAddr(), 
					"删除救援物资成功", 
					LogType.LOG_JYWZ_DELETE, 
					null, 
					goodsId) );
		} catch (Exception e) {
			e.printStackTrace();
			ajax("0");
		}
		return null;
	}
	
	/**
	 * 获得救援物资详情
	 * @return
	 */
	public String loadGoodsDetail(){
        info = reliefGoodsService.findOne(goodsId);
		return "update";
	}
	
	
	public SearchVo getVo() {
		return vo;
	}
	public void setVo(SearchVo vo) {
		this.vo = vo;
	}

	public ReliefGoods getInfo() {
		return info;
	}

	public void setInfo(ReliefGoods info) {
		this.info = info;
	}

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	

	
}
