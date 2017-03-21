package cn.com.qytx.hotline.balck.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.qytx.hotline.balck.dao.BlacklistDao;
import cn.com.qytx.hotline.balck.domain.Blacklist;
import cn.com.qytx.hotline.balck.service.IBlacklist;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.qytx.platform.base.service.impl.BaseServiceImpl;

/**
 * 功能: 黑名单实现类
 * 版本: 1.0
 * 开发人员: 李华伟
 * 创建日期: 2013-8-19
 * 修改日期: 2013-8-19
 * 修改列表:
 */
@Service
@Transactional
public class BlacklistImpl extends BaseServiceImpl<Blacklist> implements IBlacklist, Serializable
{
    /**
     * 序列号
     */
    private static final long serialVersionUID = -605680261238864618L;
    
    /**
     * 注入黑名单dao
     */
    @Resource
    private BlacklistDao blacklistDao;
 

    /**
     * 功能：保存或者更新黑名单
     * @param Blacklist 黑名单实体类
     * @return Blacklist
     */
    public Blacklist saveOrUpdate(Blacklist arg0)
    {
        return blacklistDao.saveOrUpdate(arg0);
    }

    /**
     * 功能：分页查询
     * @param page 分页信息
     * @return Page
     */
    public Page<Blacklist> findAll(Pageable pageable, String phone)
    {
        return blacklistDao.findAll(pageable, phone);
    }

    /**
     * 批量删除黑名单
     * @param blacklistId blacklistId
     */
    public void deleteBatch(Integer[] blacklistIds)
    {
        if (null != blacklistIds)
        {
            for (Integer blacklistId : blacklistIds)
            {
                blacklistDao.delete(blacklistId, false);
            }
        }
    }

    /**
     * 功能：根据电话查询黑名单信息
     * @param phone 电话
     * @return Blacklist
     */
    public Blacklist findByPhone(String phone)
    {
        return blacklistDao.findByPhone(phone);
    }
}
