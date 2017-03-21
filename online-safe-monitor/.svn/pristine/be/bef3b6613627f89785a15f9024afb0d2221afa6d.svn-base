package cn.com.qytx.hotline.phonetask.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.com.qytx.hotline.phonetask.dao.PhoneTaskDao;
import cn.com.qytx.hotline.phonetask.dao.QuestionDao;
import cn.com.qytx.hotline.phonetask.dao.QuestionItemDao;
import cn.com.qytx.hotline.phonetask.domain.Question;
import cn.com.qytx.hotline.phonetask.service.IQuestionService;
import cn.com.qytx.platform.base.service.impl.BaseServiceImpl;

/**
 * 功能:问题逻辑处理接口
 * 版本: 1.0
 * 开发人员: 彭小东
 * 创建日期: 2015-2-6
 * 修改日期: 2015-2-6
 * 修改列表:
 */
@Service("questionService")
@Transactional
public class QuestionServiceImpl extends BaseServiceImpl<Question> implements IQuestionService
{

    private static final long serialVersionUID = 1550338849343008377L;
    /**
     * 问题dao
     */
    @Resource(name = "questionDao")
    private transient QuestionDao questionDao;
    /**
     * 问题选项dao
     */
    @Resource(name = "questionItemDao")
    private transient QuestionItemDao questionItemDao;
    /**
     * 电话任务dao
     */
    @Autowired
    private PhoneTaskDao phoneTaskDao;

    /**
     * 功能：删除试题
     * @param id 试题id
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteAndItemById(Integer id)
    {
        questionItemDao.deleteItemByQuestionId(id);
        questionDao.delete(id);
    }

  





    @Override
    /**
     * 功能：保存和更新试卷问题
     * @param list  试题列表
     * @param questionnareId 试卷id
     * @param statue  外呼状态
     */
    public void save(List<Question> list, Integer questionnareId, Integer statue)
    {
        questionItemDao.deleteByQuestionnaireId(questionnareId);
        questionDao.deleteByQuestionnaireId(questionnareId);
        questionDao.saveOrUpdate(list);
        // if (statue == 2) {
        phoneTaskDao.updateState(questionnareId, statue);
        // }
    }

}
