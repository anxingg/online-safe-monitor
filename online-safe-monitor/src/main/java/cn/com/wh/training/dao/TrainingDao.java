package cn.com.wh.training.dao;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import cn.com.qytx.platform.base.dao.BaseDao;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.wh.training.domain.SearchVo;
import cn.com.wh.training.domain.Training;

@Component
public class TrainingDao extends BaseDao<Training, Integer>{

	/**
	 * 
	 * 功能：培训列表
	 * @param page
	 * @param search
	 * @return
	 */
	public Page<Training> findByPage(Pageable page,SearchVo search){
		List<Object> param = new ArrayList<Object>();
		StringBuffer condition = new StringBuffer(" 1=1");
		if (search.getGroupId()!=null) {
			condition.append(" and groupId="+search.getGroupId());
		}
		if(search!=null){
			if(search.getTrainingType()==0){//计划管理
				    condition.append(" and trainType = 0");
				if(StringUtils.isNotEmpty(search.getTrainingContext())){
					condition.append(" and details like '%"+search.getTrainingContext()+"%'");
				}
				if(StringUtils.isNotEmpty(search.getCharge())){
					condition.append(" and charge like '%"+search.getCharge()+"%'");
				}
				if(StringUtils.isNotEmpty(search.getTrainingYear())){
					condition.append(" and trainYear = '"+search.getTrainingYear()+"'");
				}
			}else if(search.getTrainingType()==1){//记录
				condition.append(" and trainType = 1");
				if(StringUtils.isNotEmpty(search.getTrainingContext())){
					condition.append(" and details like '%"+search.getTrainingContext()+"%'");
				}
				if(StringUtils.isNotEmpty(search.getSpeaker())){
					condition.append(" and speaker like '%"+search.getSpeaker()+"%'");
				}if(StringUtils.isNotEmpty(search.getTrainingTime())){
					String trainingStr = search.getTrainingTime()+" 00:00:00";
					Date trainingTime = null;
					SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					try {
						trainingTime = sdf.parse(trainingStr);
					} catch (Exception e) {
						trainingTime = null;
					}
					if (trainingTime!=null) {
							condition.append(" and trainTime =?");
							param.add(trainingTime);
					}
				}
			}
		}
		if(param!=null&&param.size()>0){
			return unDeleted().findAll(condition.toString(), page,param.toArray());
		}else{
			return unDeleted().findAll(condition.toString(), page);
		}
	}
}
