package cn.com.wh.training.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import cn.com.qytx.cbb.dict.service.IDict;
import cn.com.qytx.cbb.file.domain.Attachment;
import cn.com.qytx.cbb.file.service.IAttachment;
import cn.com.qytx.platform.base.action.BaseActionSupport;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Sort;
import cn.com.qytx.platform.base.query.Sort.Direction;
import cn.com.qytx.platform.log.service.ILog;
import cn.com.qytx.platform.log.service.LogType;
import cn.com.wh.safeaccident.util.Tool;
import cn.com.wh.support.SessionSupport;
import cn.com.wh.training.domain.PreserviceTraining;
import cn.com.wh.training.domain.SearchVo;
import cn.com.wh.training.service.IPreserviceTraining;
import cn.com.wh.util.DataInitUtil;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
/**
 * 
 * <br/>功能:岗前三级培训action
 * <br/>版本: 1.0
 * <br/>开发人员: 吴洲
 * <br/>创建日期: 2015年8月27日
 * <br/>修改日期: 2015年8月27日
 * <br/>修改列表:
 */
public class PreserviceTrainingAction extends BaseActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9071647943641438486L;

	@Autowired
	private IPreserviceTraining preserviceTrainingService;
	
	@Autowired
	private IDict dictService;
	
	@Resource(name="attachmentService")
	private IAttachment attachmentService;
	
    /**
     * 系统日志接口
     */
    @Resource
    private ILog logService;
	
	private SearchVo  vo;//查询vo
	
	private PreserviceTraining train;
	
	private Integer trainId;
	
	private String opt;
	
	private List<Attachment> fileList = new ArrayList<Attachment>();
	
	
	/**
	 * 
	 * 功能：岗前三级培训分页
	 * @return
	 */
	public String findPreserviceList(){
		try {
			int pageNum = (int) (Math.ceil((double) this.getIDisplayStart() / (double) this.getIDisplayLength())) + 1;
			Sort sort = new Sort(new Sort.Order(Direction.DESC, "groupId"),new Sort.Order(Direction.DESC, "id"));
			Page<PreserviceTraining> pageInfo = preserviceTrainingService.findByPage(this.getPageable(sort), vo);
			List<PreserviceTraining> list = pageInfo.getContent();
			List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
			
			int i = (pageNum - 1) * this.getIDisplayLength() + 1;
			if (null != list && list.size() > 0) {
				for (PreserviceTraining info : list) {
					Map<String, Object> map = new HashMap<String, Object>();
					//主键
					map.put("id", info.getId());
					//序号
					map.put("no", i);
					//姓名
					map.put("userName", info.getUserName()==null?"":info.getUserName());
					//公司名称
					if (DataInitUtil.companyMap!=null&&DataInitUtil.companyMap.get(info.getGroupId())!=null) {
						map.put("companyName", DataInitUtil.companyMap.get(info.getGroupId()));
					}else {
						map.put("companyName", "");
					}
					//性别
					if (info.getSex()!=null&&info.getSex()==1) {
						map.put("sex", "男");
					}else {
						map.put("sex", "女");
					}
					//年龄
					map.put("age", info.getAge());
					//工种
					map.put("workType", info.getWorkType()==null?"":info.getWorkType());
					//岗位
					map.put("post", info.getPost()==null?"":info.getPost());
					//成绩（企业级）
					map.put("oneScore", info.getOneScore()==null?"":info.getOneScore());
//					//地址
//					map.put("address", info.getNativePlace()==null?"":info.getNativePlace());
//					//个人态度
//					map.put("ownerattitude", info.getOwnerAttitude()==null?"":info.getOwnerAttitude());
//					//准上岗人意见
//					map.put("checkerattitude", info.getCheckAttitude()==null?"":info.getCheckAttitude());
					//批准人
					map.put("checker", info.getChecker()==null?"":info.getChecker());
					
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
     * 功能：添加或者修改岗前培训记录
     * @return
     */
	public String saveOrUpdatePreservice() {
		try {
			train = JSON.parseObject(opt, PreserviceTraining.class);
			boolean update = false;
			if(train != null && train.getId() != null){
				update = true;
			}
			train.setIsDelete(0);
			train.setIsForkGroup(getLoginUser().getIsForkGroup());
			train.setGroupId(getLoginUser().getIsForkGroup());
			train.setCompanyName(((SessionSupport)(this.getSessionSupport())).getCurrentCompanyName());
			train = preserviceTrainingService.saveOrUpdate(train);
			//记录日志
			logService.saveOrUpdate( Tool.generateLog(getLoginUser(), 
					this.getRequest().getRemoteAddr(), 
					update?"修改岗前三级培训成功":"新增岗前三级培训成功", 
					update?LogType.LOG_GQSJPX_UPDATE:LogType.LOG_GQSJPX_ADD, 
					train, 
					train.getId()) );
			ajax("1");
		} catch (Exception e) {
			e.printStackTrace();
			ajax("0");
		}
		return null;
	}
	
	/**
	 * 功能：删除岗前培训记录
	 * 
	 * @return
	 */
	public String deletePreservice() {
		try {
			preserviceTrainingService.delete(trainId, false);
			//记录日志
			logService.saveOrUpdate( Tool.generateLog(getLoginUser(), 
					this.getRequest().getRemoteAddr(), 
					"删除岗前三级培训成功", 
					LogType.LOG_GQSJPX_DELETE, 
					null, 
					trainId) );
			ajax("1");
		} catch (Exception e) {
			e.printStackTrace();
			ajax("0");
		}
		return null;
	}
	
	/**
	 * 获得岗前培训记录详情
	 * @return
	 */
	public String loadPreserviceDetail(){
        train = preserviceTrainingService.findOne(trainId);
        if (train!=null) {
        	//查询附件
        	fileList = attachmentService.getAttachmentsByIds(train.getAttachmentId());
		}
		return opt;
	}
	
	
	public SearchVo getVo() {
		return vo;
	}
	public void setVo(SearchVo vo) {
		this.vo = vo;
	}
	public PreserviceTraining getTrain() {
		return train;
	}
	public void setTrain(PreserviceTraining train) {
		this.train = train;
	}
	public Integer getTrainId() {
		return trainId;
	}
	public void setTrainId(Integer trainId) {
		this.trainId = trainId;
	}
	public String getOpt() {
		return opt;
	}
	public void setOpt(String opt) {
		this.opt = opt;
	}
	public List<Attachment> getFileList() {
		return fileList;
	}
	public void setFileList(List<Attachment> fileList) {
		this.fileList = fileList;
	}
	
	
}
