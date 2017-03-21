package cn.com.wh.reliefgoods.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.qytx.platform.base.query.Sort;
import cn.com.qytx.platform.base.query.Sort.Direction;
import cn.com.qytx.platform.base.service.impl.BaseServiceImpl;
import cn.com.wh.reliefgoods.dao.ReliefGoodsDao;
import cn.com.wh.reliefgoods.domain.ReliefGoods;
import cn.com.wh.reliefgoods.domain.SearchVo;
import cn.com.wh.reliefgoods.service.IReliefGoods;


@Service
@Transactional
public class ReliefGoodsImpl extends BaseServiceImpl<ReliefGoods> implements IReliefGoods{

	//救援物资dao
	@Autowired
	private ReliefGoodsDao reliefGoodsDao;

	@Override
	public Page<ReliefGoods> findByPage(Pageable page,
			SearchVo search) {
		return reliefGoodsDao.findByPage(page, search);
	}

	@Override
	public int saveGoods(ReliefGoods info) {
		int oldId = 0;
		if (info.getId()!=null) {
			oldId = info.getId();
		}
		reliefGoodsDao.saveOrUpdate(info);
		if (info.getIsShow() == 0 && oldId == 0) {//企业新增救援物资
			dealShowOfGroup(info.getGroupId());
		}
		return 0;
	}

	@Override
	public int deleteGoods(Integer goodsId) {
		ReliefGoods info = reliefGoodsDao.findOne(goodsId);
		reliefGoodsDao.delete(goodsId,false);
		if (info.getIsShow()==1) {
			dealShowOfGroup(info.getGroupId());
		}
		return 0;
	}

	public void dealShowOfGroup(Integer groupId){
		Sort sort = new Sort(new Sort.Order(Direction.DESC, "isShow"),new Sort.Order(Direction.ASC, "createTime"));
		SearchVo vo = new SearchVo(); 
		vo.setGroupId(groupId);
		List<ReliefGoods> list = reliefGoodsDao.findList(vo,sort);
		if (list!=null&&list.size()>0) {
			ReliefGoods info = list.get(0);
			if (info.getIsShow()==0) {
				reliefGoodsDao.updateShow(info.getId(), 1);
			}
		}
	}
}
