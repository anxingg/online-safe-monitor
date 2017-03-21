package cn.com.wh.safeaccident.service;

import java.sql.Timestamp;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.wh.company.dao.WHCompanyDao;
import cn.com.wh.safeaccident.dao.SafeAccidentDao;
import cn.com.wh.safeaccident.domain.SafeAccident;
import cn.com.wh.safeaccident.domain.SafeAccidentStatisticsResult;

@Service
@Transactional
public class SafeAccidentImpl implements SafeAccidentService {

	@Resource
	WHCompanyDao wHCompanyDao;

	@Resource
	SafeAccidentDao safeAccidentDao;

	@Override
	public Page<SafeAccident> findByPage(Pageable pageable,
			String keyword, Integer accidentCharacter,
			String accidentName, Integer whroletype, Integer groupId,
			Date beginDate, Date endDate) {

		return safeAccidentDao.findByPage(pageable, keyword,
				accidentCharacter, accidentName, whroletype, groupId,
				beginDate, endDate);
	}

	@Override
	public SafeAccident saveOrUpdate(SafeAccident safeAccident) {
		return safeAccidentDao.saveOrUpdate(safeAccident);
	}

	@Override
	public SafeAccident findOne(Integer vid) {
		return safeAccidentDao.findOne(vid);
	}

	@Override
	public Page<SafeAccidentStatisticsResult> findStatisticsResultByPage(
			Pageable pageable, Timestamp occurredTimeBegin,
			Timestamp occurredTimeEnd, Integer accidentCharacter,
			Integer whroletype, Integer groupId) {
		return safeAccidentDao.findStatisticsResultByPage(pageable,
				occurredTimeBegin, occurredTimeEnd, accidentCharacter,
				whroletype, groupId);
	}

}
