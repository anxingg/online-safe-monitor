package cn.com.wh.thresholdtemplate.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import com.google.gson.Gson;
import cn.com.qytx.platform.base.action.BaseActionSupport;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Sort;
import cn.com.qytx.platform.base.query.Sort.Direction;
import cn.com.qytx.platform.log.service.ILog;
import cn.com.qytx.platform.log.service.LogType;
import cn.com.wh.thresholdtemplate.domain.ThresholdTemplate;
import cn.com.wh.thresholdtemplate.service.IThresholdTemplate;
import cn.com.wh.util.DataInitUtil;
import cn.com.wh.util.Tool;

public class ThresholdTemplateAction extends BaseActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	public IThresholdTemplate thresholdTemplateImpl; 
		
	/**
	 * 系统日志接口
	 */
	@Resource
	private ILog logService;
	
	private ThresholdTemplate thresholdTemplate;
	
	private String watchType;
	
	private String keyWord;
	
	private Integer vid;
	
	public String getWatchType() {
		return watchType;
	}
	public void setWatchType(String watchType) {
		this.watchType = watchType;
	}
	public String getKeyWord() {
		return keyWord;
	}
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
	
	public Integer getVid() {
		return vid;
	}
	public void setVid(Integer vid) {
		this.vid = vid;
	}
	
	public ThresholdTemplate getThresholdTemplate() {
		return thresholdTemplate;
	}
	public void setThresholdTemplate(ThresholdTemplate thresholdTemplate) {
		this.thresholdTemplate = thresholdTemplate;
	}
	private String formatLevel(ThresholdTemplate thresholdTemplate)
	{
		StringBuilder sb=new StringBuilder();
		String str,level;
		if(thresholdTemplate.getLevel1Type()!=null){
			level=DataInitUtil.getNamefromDict("error_level",thresholdTemplate.getLevel1Type());
			str=String.format("%d~%d(%s)", thresholdTemplate.getLeve1Low(),
					thresholdTemplate.getLevel1High(),level);
			sb.append(str);
		}
		if(thresholdTemplate.getLevel2Type()!=null){
			level=DataInitUtil.getNamefromDict("error_level",thresholdTemplate.getLevel2Type());
			str=String.format("%d~%d(%s)", thresholdTemplate.getLeve2Low(),
					thresholdTemplate.getLevel2High(),level);
			sb.append(str);
		}
		if(thresholdTemplate.getLevel3Type()!=null){
			level=DataInitUtil.getNamefromDict("error_level",thresholdTemplate.getLevel3Type());
			str=String.format("%d~%d(%s)", thresholdTemplate.getLeve3Low(),
					thresholdTemplate.getLevel3High(),level);
			sb.append(str);
		}
		if(thresholdTemplate.getLevel4Type()!=null){
			level=DataInitUtil.getNamefromDict("error_level",thresholdTemplate.getLevel4Type());
			str=String.format("%d~%d(%s)", thresholdTemplate.getLeve4Low(),
					thresholdTemplate.getLevel4High(),level);
			sb.append(str);
		}
		if(thresholdTemplate.getLevel5Type()!=null){
			level=DataInitUtil.getNamefromDict("error_level",thresholdTemplate.getLevel5Type());
			str=String.format("%d~%d(%s)", thresholdTemplate.getLeve5Low(),
					thresholdTemplate.getLevel5High(),level);
			sb.append(str);
		}
		if(thresholdTemplate.getLevel6Type()!=null){
			level=DataInitUtil.getNamefromDict("error_level",thresholdTemplate.getLevel6Type());
			str=String.format("%d~%d(%s)", thresholdTemplate.getLeve6Low(),
					thresholdTemplate.getLevel6High(),level);
			sb.append(str);
		}
		if(thresholdTemplate.getLevel7Type()!=null){
			level=DataInitUtil.getNamefromDict("error_level",thresholdTemplate.getLevel7Type());
			str=String.format("%d~%d(%s)", thresholdTemplate.getLeve7Low(),
					thresholdTemplate.getLevel7High(),level);
			sb.append(str);
		}
		if(thresholdTemplate.getLevel8Type()!=null){
			level=DataInitUtil.getNamefromDict("error_level",thresholdTemplate.getLevel8Type());
			str=String.format("%d~%d(%s)", thresholdTemplate.getLeve8Low(),
					thresholdTemplate.getLevel8High(),level);
			sb.append(str);
		}
		if(thresholdTemplate.getLevel9Type()!=null){
			level=DataInitUtil.getNamefromDict("error_level",thresholdTemplate.getLevel9Type());
			str=String.format("%d~%d(%s)", thresholdTemplate.getLeve9Low(),
					thresholdTemplate.getLevel9High(),level);
			sb.append(str);
		}
		if(thresholdTemplate.getLevel10Type()!=null){
			level=DataInitUtil.getNamefromDict("error_level",thresholdTemplate.getLevel10Type());
			str=String.format("%d~%d(%s)", thresholdTemplate.getLeve10Low(),
					thresholdTemplate.getLevel10High(),level);
			sb.append(str);
		}
		return sb.toString();
	}
	public String getList(){
		try {
			int pageNum = (int) (Math.ceil((double) this.getIDisplayStart()
					/ (double) this.getIDisplayLength())) + 1;
			Sort sort = new Sort(new Sort.Order(Direction.DESC, "createTime"));
			Page<ThresholdTemplate> pageInfo = thresholdTemplateImpl.findThresholdTemplateByPage(this.getPageable(sort),
					watchType,keyWord);
			List<ThresholdTemplate> list = pageInfo.getContent();
			List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
			int i = (pageNum - 1) * this.getIDisplayLength() + 1;
			if (null != list && list.size() > 0) {
				for (ThresholdTemplate thresholdTemplate : list) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("no", i);
					
					map.put("vid", thresholdTemplate.getVid());
					
					map.put("templateName", thresholdTemplate.getTemplateName());
					
					String watchType=DataInitUtil.getNamefromDict("watchType",Integer.parseInt(thresholdTemplate.getWatchType()));
					map.put("watchType",watchType);
					
					String range=String.format("%d~%d", thresholdTemplate.getRangeLow() ==null?0:thresholdTemplate.getRangeLow(),
							thresholdTemplate.getRangeHigh() ==null?0:thresholdTemplate.getRangeHigh());
					map.put("range", range);
					
					String level=this.formatLevel(thresholdTemplate);
					map.put("level", level);
					
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
	 * 过滤提交的template值，由于有默认显示的，用户设计没有输入
	 * @param template
	 * @return
	 * @throws Exception 
	 */
	public ThresholdTemplate filter(ThresholdTemplate template) throws Exception
	{
		int has=0;
		if((template.getLevel1Type()!=null)&&
				(template.getLevel1Type().equals(-1))){
			template.setLevel1Type(null);
			template.setLeve1Low(null);
			template.setLevel1High(null);
			has++;
		}
		if((template.getLevel2Type()!=null)&&
				(template.getLevel2Type().equals(-1))){
			template.setLevel2Type(null);
			template.setLeve2Low(null);
			template.setLevel2High(null);
			has++;
		}
		if((template.getLevel3Type()!=null)&&
				(template.getLevel3Type().equals(-1))){
			template.setLevel3Type(null);
			template.setLeve3Low(null);
			template.setLevel3High(null);
			has++;
		}
		if((template.getLevel4Type()!=null)&&
				(template.getLevel4Type().equals(-1))){
			template.setLevel4Type(null);
			template.setLeve4Low(null);
			template.setLevel4High(null);
			has++;
		}
		if((template.getLevel5Type()!=null)&&
				template.getLevel5Type().equals(-1)){
			template.setLevel5Type(null);
			template.setLeve5Low(null);
			template.setLevel5High(null);
			has++;
		}
		if((template.getLevel6Type()!=null)&&
				template.getLevel6Type().equals(-1)){
			template.setLevel6Type(null);
			template.setLeve6Low(null);
			template.setLevel6High(null);
			has++;
		}
		if((template.getLevel7Type()!=null)&&
				template.getLevel7Type().equals(-1)){
			template.setLevel7Type(null);
			template.setLeve7Low(null);
			template.setLevel7High(null);
			has++;
		}
		if((template.getLevel8Type()!=null)&&
				template.getLevel8Type().equals(-1)){
			template.setLevel8Type(null);
			template.setLeve8Low(null);
			template.setLevel8High(null);
			has++;
		}
		if((template.getLevel9Type()!=null)&&
				template.getLevel9Type().equals(-1)){
			template.setLevel9Type(null);
			template.setLeve9Low(null);
			template.setLevel9High(null);
			has++;
		}
		if((template.getLevel10Type()!=null)&&
				template.getLevel10Type().equals(-1)){
			template.setLevel10Type(null);
			template.setLeve10Low(null);
			template.setLevel10High(null);
			has++;
		}
		if(has>=10){
			throw new Exception ("至少要存在一个");
		}
		return template;
		
	}
	/**
	 * 保存
	 * @return
	 */
	public String save(){
		String log="阈值模板新增";
		try{
			if(vid!=null){//是修改
				thresholdTemplate.setVid(vid);
				log="阈值模板修改";
			}
			LOGGER.info(log+"开始。。。");
			thresholdTemplate=filter(thresholdTemplate);
			thresholdTemplate.setCreateTime(new Timestamp(System.currentTimeMillis()));
			thresholdTemplate.setEnableWarning(1);
			thresholdTemplate.setIsDelete(0);
			thresholdTemplateImpl.saveOrUpdate(thresholdTemplate);
			LOGGER.info(log+"结束，vid:"+thresholdTemplate.getVid());
			//记录日志
			logService.saveOrUpdate( Tool.generateLog(getLoginUser(), 
					this.getRequest().getRemoteAddr(), 
					log+"成功", 
					LogType.LOG_YZMB_ADD, 
					thresholdTemplate, 
					thresholdTemplate.getVid()) );
			ajax(1);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.info(log+"异常，e:"+e.getMessage());
			ajax(0);
		}
		return null;
	}
	
	/**
	 * 删除
	 * @return
	 */
	public String delete(){
		try {
			thresholdTemplate=thresholdTemplateImpl.findOne(vid);
			thresholdTemplate.setIsDelete(1);
			thresholdTemplateImpl.saveOrUpdate(thresholdTemplate);
			LOGGER.info("删除阈值模板，vid："+vid);
			
			//记录日志
			logService.saveOrUpdate(Tool.generateLog(getLoginUser(), 
					this.getRequest().getRemoteAddr(), 
					"删除阈值模板成功", 
					LogType.LOG_YZMB_DELETE, 
					thresholdTemplate, 
					vid) );
			ajax("1");
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("删除阈值模板异常，vid="+vid+",异常信息："+e.getMessage());
			ajax("0");
		}
		return null;
	}
	
	/**
	 * 获取信息
	 * @return
	 */
	public String getInfo(){
		try {
			thresholdTemplate = thresholdTemplateImpl.findOne(vid);
			Gson json = new Gson();
			String jsons = json.toJson(thresholdTemplate);
			PrintWriter writer = new PrintWriter(this.getResponse().getWriter());
			writer.print(jsons);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			LOGGER.error("getInfo发生异常： "+e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取阈值模板集合
	 * @return
	 */
	public String getThresholdTemplateList(){
		try {
			List<ThresholdTemplate> list = thresholdTemplateImpl.unDeleted().findAll();
			Gson json = new Gson();
			String jsons = json.toJson(list);
			PrintWriter writer = new PrintWriter(this.getResponse().getWriter());
			writer.print(jsons);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			LOGGER.error("getThresholdTemplateList发生异常： "+e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	
}
