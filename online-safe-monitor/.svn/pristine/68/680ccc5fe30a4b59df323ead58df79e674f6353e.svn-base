package cn.com.wh.company.action;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import cn.com.qytx.platform.base.action.BaseActionSupport;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Sort;
import cn.com.qytx.platform.base.query.Sort.Direction;
import cn.com.qytx.platform.log.service.ILog;
import cn.com.qytx.platform.log.service.LogType;
import cn.com.qytx.platform.org.domain.UserInfo;
import cn.com.wh.company.domain.WHCompanyProduct;
import cn.com.wh.company.service.IWHCompanyProduct;
import cn.com.wh.dangerchemicals.domain.DangerChemicalsInfo;
import cn.com.wh.dangerchemicals.service.DangerChemicalsService;
import cn.com.wh.safeaccident.util.Tool;
import cn.com.wh.util.DataInitUtil;

import com.google.gson.Gson;

/**
 * 功能：企业产品
 * 作者：吴胜光
 * 时间：2015年8月25日
 * 修改人：
 * 修改时间：
 */
public class CompanyProductAction extends BaseActionSupport{

	private static final long serialVersionUID = -3209484199082218431L;
	
	@Autowired
	public IWHCompanyProduct companyProductImpl; 
	
	/**
	 * 公司危化品接品
	 */
	@Autowired
	private DangerChemicalsService dcService;
	
	/**
	 * 系统日志接口
	 */
	@Resource
	private ILog logService;
	
	private WHCompanyProduct product;
	
	private Integer groupId;
	
	private Integer materialType;
	
	private String materialName;
	
	private Integer id;

	
	/**
	 * 保存企业产品
	 * @return
	 */
	public String saveProduct(){
		try {
			LOGGER.info("保存企业产品begin...");
			
			UserInfo userInfo = getLoginUser();//获取登录用户
			product.setIsDelete(0);
			product.setCreateTime(new Timestamp(System.currentTimeMillis()));
			product.setIsForkGroup(userInfo.getGroupId());
			product.setGroupId(userInfo.getGroupId());
			companyProductImpl.saveOrUpdate(product);
			LOGGER.info("保存企业产品end...产品ID="+product.getVid()+",groupid="+userInfo.getGroupId());
			
			//记录日志
			logService.saveOrUpdate( Tool.generateLog(getLoginUser(), 
					this.getRequest().getRemoteAddr(), 
					"新增企业产品成功", 
					LogType.LOG_CP_ADD, 
					product, 
					product.getVid()) );
			ajax("1");
		} catch (Exception e) {
			e.printStackTrace();
			ajax("0");
			LOGGER.error("保存企业产品异常...e:"+e.getMessage());
		}
		return null;
	}
	
	
	/**
	 * 修改企业产品
	 * @return
	 */
	public String updateProduct(){
		try {
			LOGGER.info("修改企业产品begin...");
			WHCompanyProduct oldProduct = companyProductImpl.findOne(product.getVid());
			oldProduct.setMaterialType(product.getMaterialType());
			oldProduct.setMaterialTypeName((product.getMaterialTypeName()));
			oldProduct.setProductId(product.getProductId());
			oldProduct.setMaterialName(product.getMaterialName());
			
			oldProduct.setOutputYear(product.getOutputYear());
			oldProduct.setOutputMouth(product.getOutputMouth());
			oldProduct.setUseYear(product.getUseYear());
			oldProduct.setUseMouth(product.getUseMouth());

			oldProduct.setMemo(product.getMemo());
			
			companyProductImpl.saveOrUpdate(oldProduct);
			LOGGER.info("修改企业产品end...产品ID="+product.getVid());
			
			//记录日志
			logService.saveOrUpdate( Tool.generateLog(getLoginUser(), 
					this.getRequest().getRemoteAddr(), 
					"修改企业产品成功", 
					LogType.LOG_CP_UPDATE, 
					oldProduct, 
					oldProduct.getVid()) );
			ajax("1");
		} catch (Exception e) {
			e.printStackTrace();
			ajax("0");
			LOGGER.error("修改企业产品异常...e:"+e.getMessage());
		}
		return null;
	}
	
	/**
	 * 企业产品列表
	 * @return
	 */
	public String listWHCompanyProduct(){
		try {
			int pageNum = (int) (Math.ceil((double) this.getIDisplayStart()
					/ (double) this.getIDisplayLength())) + 1;
			Sort sort = new Sort(new Sort.Order(Direction.DESC, "createTime"));
			Page<WHCompanyProduct> pageInfo = companyProductImpl.findByPage(this.getPageable(sort), groupId, materialType, materialName);
			List<WHCompanyProduct> list = pageInfo.getContent();
			List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
			int i = (pageNum - 1) * this.getIDisplayLength() + 1;
			if (null != list && list.size() > 0) {
				for (WHCompanyProduct wHCompanyProduct : list) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("no", i);
					
					map.put("id", wHCompanyProduct.getVid());
					
					// 公司名称
					String companyName = DataInitUtil.companyMap.get(wHCompanyProduct.getGroupId());

					map.put("companyName", companyName == null ? "-" : companyName);
					
					//材料种类
					String materialTypeName = wHCompanyProduct.getMaterialTypeName();

					map.put("materialTypeName", materialTypeName == null ? "-" : materialTypeName);
					
					// 物质名称
					String materialName = wHCompanyProduct.getMaterialName();

					map.put("materialName", materialName == null ? "-" : materialName);
					
					//年设计产量（吨）
					Integer outputYear = wHCompanyProduct.getOutputYear();

					map.put("outputYear", outputYear == null ? "" : outputYear);
					//月设计产量（吨）
					Integer outputMouth = wHCompanyProduct.getOutputMouth();

					map.put("outputMouth", outputMouth == null ? "" : outputMouth);
					//年消耗量（吨）
					Integer useYear = wHCompanyProduct.getUseYear();

					map.put("useYear", useYear == null ? "" : useYear);
					//月消耗量（吨）
					Integer useMouth = wHCompanyProduct.getUseMouth();

					map.put("useMouth", useMouth == null ? "" : useMouth);
					
					//备注
					String memo = wHCompanyProduct.getMemo();

					map.put("memo", memo == null ? "" : memo);
					
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
			LOGGER.error("查询企业产品异常...e:"+e.getMessage());
		}
		
		return null;
	}
	
	
	/**
	 * 删除
	 * @return
	 */
	public String ajaxDeleteProductById(){
		
		try {
			WHCompanyProduct product = companyProductImpl.findOne(id);
			product.setIsDelete(1);
			companyProductImpl.saveOrUpdate(product);
			LOGGER.info("删除企业产品，vid="+id);
			
			//记录日志
			logService.saveOrUpdate( Tool.generateLog(getLoginUser(), 
					this.getRequest().getRemoteAddr(), 
					"删除企业产品成功", 
					LogType.LOG_CP_DELETE, 
					product, 
					id) );
			ajax("1");
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("删除企业产品异常，vid="+id+",异常信息："+e.getMessage());
			ajax("0");
		}
		return null;
	}
	
	
	/**
	 * 获取产品信息
	 * @return
	 */
	public String getProductInfo(){
		try {
			
			WHCompanyProduct product = companyProductImpl.findOne(id);
			
			Map<String, Object> map = new HashMap<String, Object>();
			if(product!=null){
				map.put("materialType", product.getMaterialType());
				map.put("materialTypeName", product.getMaterialTypeName());
				map.put("productId", product.getProductId());
				map.put("materialName", product.getMaterialName());
				
				map.put("outputYear", product.getOutputYear());
				map.put("outputMouth", product.getOutputMouth());
				map.put("useYear", product.getUseYear());
				map.put("useMouth", product.getUseMouth());
				
				map.put("memo", product.getMemo());
				
			}
			
			Gson json = new Gson();
			String jsons = json.toJson(map);
			
			PrintWriter writer = new PrintWriter(this.getResponse().getWriter());
			writer.print(jsons);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			LOGGER.error("获取企业产品异常,id="+id+",e:"+e.getMessage());
		}
		
		return null;
	}
	
	/**
	 * 查询所有危化品对象
	 * @return
	 */
	public String findWHPAll(){
		try{
			List<DangerChemicalsInfo> dangerChemicalsList = dcService.findAll();
			Gson json = new Gson();
			String jsons = json.toJson(dangerChemicalsList);
			
			PrintWriter writer = new PrintWriter(this.getResponse().getWriter());
			writer.print(jsons);
			writer.flush();
			writer.close();
		}catch(Exception e){
			LOGGER.error("通过主键查询危险化学品时出错", e);
		}
		return null;
	}
	
	
	public WHCompanyProduct getProduct() {
		return product;
	}

	public void setProduct(WHCompanyProduct product) {
		this.product = product;
	}


	public Integer getGroupId() {
		return groupId;
	}


	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}


	public Integer getMaterialType() {
		return materialType;
	}


	public void setMaterialType(Integer materialType) {
		this.materialType = materialType;
	}


	public String getMaterialName() {
		return materialName;
	}


	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}
	
	
	
	
}
