package cn.com.qytx.hotline.messagemanagement.dao;

import java.io.Serializable;
import java.sql.Timestamp;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import cn.com.qytx.hotline.messagemanagement.domain.VoxMail;
import cn.com.qytx.platform.base.dao.BaseDao;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;

/**
 * 功能:留言管理dao
 * 版本: 1.0
 * 开发人员: 张东领
 * 创建日期: 2014-12-3
 * 修改日期: 2014-12-3
 * 修改列表:
 */

@Component
public class VoxMailDao extends BaseDao<VoxMail,Integer> implements Serializable {

	/**
	 * 描述含义
	 */
	private static final long serialVersionUID = -4821739495496575855L;

	/**
	 * 功能：分页查询留言列表
	 * @param page：分页
	 * @param vm：留言表实体
	 * @return
	 */
	public Page<VoxMail> findByPage(Pageable page,VoxMail vm){
		StringBuilder hql = new StringBuilder();
		hql.append(" 1=1 ");
		if(vm!=null){
			Integer isForkGroup = vm.getIsForkGroup();//所属地市
			if(isForkGroup!=null&&isForkGroup!=2&&isForkGroup!=-1){
				hql.append(" and isForkGroup="+isForkGroup);
			}
			Integer state = vm.getState();//状态1未处理，2已处理，3无效
			if(state!=null&&state!=-1){
				hql.append(" and state="+state);
			}
			Timestamp beginTime = vm.getBeginTime();//留言开始时间
			if(beginTime!=null){
				hql.append(" and beginTime>= '"+beginTime+"'");
			}
			Timestamp endTime = vm.getEndTime();//留言结束时间
			if(endTime!=null){
				hql.append(" and endTime<= '"+endTime+"'");
			}
			String phone = vm.getPhone();//来电号码，支持模糊查询
			if(StringUtils.isNotBlank(phone)){
				hql.append(" and phone like '%"+phone+"%' ");
			}
		}
		return super.dataFilter().findAll(hql.toString(), page);
	}
	
//	public VoxMail updataVoxMail(VoxMail vm){
//		return super.saveOrUpdate(vm);
//	}
}
