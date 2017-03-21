package cn.com.qytx.hotline.phonetask.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Cell;
import jxl.Workbook;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.NumberFormats;
import jxl.write.WritableCellFeatures;
import jxl.write.WritableCellFormat;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.qytx.hotline.customercall.impl.CustomerCallLogImpl;
import cn.com.qytx.hotline.phonetask.domain.PhoneTaskUser;
import cn.com.qytx.hotline.phonetask.service.IPhoneTaskUser;
import cn.com.qytx.hotline.util.ImportBaseAction;
import cn.com.qytx.monitor.client.log.MonitorLogger;
import cn.com.qytx.monitor.client.logImpl.Log4jImpl;
import cn.com.qytx.platform.org.domain.UserInfo;

/**
 * 功能:外呼对象导入
 * 版本: 1.0 注意最后要关闭输出输入流
 * 开发人员: 马恺
 * 创建日期: 2014-9-22
 * 修改人: pxd
 * 修改日期: 2015-2-6
 * 修改列表:
 */
public class ImportPhoneTaskUserAction extends ImportBaseAction
{
    private static final long serialVersionUID = 1L;
    /**
     * log4j日志
     */
    private final static MonitorLogger logger =new Log4jImpl(ImportPhoneTaskUserAction.class);
//    private static final Logger logger = Logger.getLogger(ImportPhoneTaskUserAction.class);
    private static final String ID = "(ID:";
    /**
     * 电话任务用户接口
     */
    @Autowired
    private transient IPhoneTaskUser ptuimpl;
    /**
     * 把导入成功的数据返回到前台
     */
    private List<PhoneTaskUser> successList = new ArrayList<PhoneTaskUser>();
    /**
     * 检查导入的文件是否合法
     * 此文件一般不用动
     * @return
     */
    public String checkBeforeImporting()
    {
        if (uploadFile())
        {
            boolean flag = true;
            String result = initSheet();
            if (result == null)
            {
                // 第一行不判断
                for (int j = 1; j < sheet.getRows() && flag; j++)
                {
                    Cell[] cells = sheet.getRow(j); // 读取第j行,得到这一行的所有单元格。Cell
                    if (cells != null && cells.length > 0)
                    {
                        for (int k = 0; k < OldCols && flag; k++)
                        {
                            String val = "";
                            if (k < cells.length)
                            {
                                val = cells[k].getContents();// 内容
                            }
                            result = verify(val, j, k);// 检验单元格的返回结果
                            if (result != null && !"".equals(result))
                            {
                                flag = false;// 退出循环
                            }
                        }
                    }
                    if (!flag)
                    {
                        break;
                    }
                }
            }
            PrintWriter writer = null;
            this.getResponse().setHeader("ContentType", "text/json");
            this.getResponse().setCharacterEncoding("utf-8");
            this.getResponse().setContentType("text/html;charset=utf-8");
            try
            {
                writer = new PrintWriter(this.getResponse().getWriter());
            }
            catch (IOException e)
            {
                logger.error("检查导入的文件是否合法出错,当前操作人："+getLoginUser().getUserName()+ID+getLoginUser().getUserId()+")", e);
            }finally{
                colseAllStream();
            }
            writer.print(result);
            writer.close();
        }
        return null;
    }

    /**
     * 导入数据时调用的方法
     * 次方法一般不用修改
     * @return
     */
    public String importPhoneTaskUser()
    {
        try
        {
            if (uploadFile())
            {// 上传文件
                String result = initSheet();
                if (result == null)
                {
                    boolean lastRowPass = false;
                    for (int r = 1; r < OldRows; r++)
                    {
                        Cell[] cells = new Cell[OldCols];
                        boolean thisRowPass = true;
                        for (int c = 0; c < OldCols; c++)
                        {
                            Cell cell = sheet.getCell(c, r);
                            cells[c] = cell;// 把这一列中的每一单元格存入代表这一行单元格的数组中。方便下面的recordThisLineCalls(cells)方法调用。
                            boolean dynamicCellPass = maskExcelFile(cell, c, r);// 用来筛分每个单元格，过滤出不符合标准的excel信息，并另存入excel错误信息统计表中。并返回一个boolean值，true表示该行全部符合。
                            if (!dynamicCellPass)
                            {// 在这一行当中。只要有一列不符合标准。这一行就不符合标准，这一行就不通过。
                                thisRowPass = false;
                            }
                        }
                        if (thisRowPass)
                        {
                            recordThisLineCalls(cells);// 记录下这一行的数据。把这行的数据转换成一个对象，追加到记录成功的successList中。
                        }
                        else
                        {
                            errorRowsNum++;
                            errorImportNum++;
                        }
                        lastRowPass = thisRowPass;
                    }
                    // successImportNum = successList.size();// 总个数
                    if (errorWWB != null&&errorImportNum > 0)
                    {
                	   if (lastRowPass)
                       {// 当最后一行是正确的数据时，移除最后一行。
                           errorws = errorWWB.getSheet(0);
                           errorws.removeRow(errorRowsNum);
                       }
                       errorWWB.write();
                    }
                    /* ==== 后续操作，显示出导入的结果。==== */

                    result = getImportResult();
                    this.getResponse().setHeader("ContentType", "text/json");
                    this.getResponse().setCharacterEncoding("utf-8");
                    this.getResponse().setContentType("text/html;charset=utf-8");
                    Map<String, Object> jsonMap = new HashMap<String, Object>();
                    jsonMap.put("result", result);
                    jsonMap.put("successList", successList);
                    ajax(jsonMap);

                }
            }
        }
        catch (Exception e)
        {
        	logger.error("导入数据时出错,当前操作人："+getLoginUser().getUserName()+ID+getLoginUser().getUserId()+")", e);
        }
        finally
        {
            colseAllStream();
        }
        return null;
    }

    /**
     * 功能：把每一行的数据转换为外呼用户对象 并保存到list里面
     * case 对应的是表的列
     * @param cells
     */
    private void recordThisLineCalls(Cell[] cells)
    {
        UserInfo userInfo = this.getLoginUser();
        if (cells != null && cells.length > 0)
        {
            PhoneTaskUser ptu = new PhoneTaskUser();
            for (int k = 0; k < cells.length; k++)
            {
                String val = cells[k].getContents();// 内容
                if (val != null)
                {
                    val = val.trim();
                }
                switch (k)
                {
                case 0:
                    break;
                case 1:
                    ptu.setUserName(val);
                    break;// 姓名1
                case 2:
                    ptu.setSex("女".equals(val) ? "0" : "1");
                    break;// 性别2
                case 3:
                    ptu.setPhone(val);
                    break;// 联系电话3
                case 4:
                    ptu.setTelephone(val);
                    break;// 备用号码4
                case 5:
                    ptu.setAddress(val);
                    break;// 居住地5
                case 6:
                    if ("一般客户".equals(val))
                    {
                        ptu.setUserType(1);
                    }
                    else if ("VIP客户".equals(val))
                    {
                        ptu.setUserType(2);
                    }
                    else
                    {
                        ptu.setUserType(0);
                    }
                    break;// 客户类别6
                default:
                    ptu.setNotice(val);
                    ;// 备注7
                }
            }
            // 给crm对象赋值
            ptu.setIsForkGroup(getLoginUser().getIsForkGroup());
//            ptu.setCreateTime(new Timestamp(System.currentTimeMillis()));
//            ptu.setCreateUserId(userInfo.getUserId());
//            ptu.setLastUpdateTime(new Timestamp(System.currentTimeMillis()));
//            ptu.setLastUpdateUserId(userInfo.getUserId());
//            ptu.setIsDelete(0);
            successList.add(ptu);
            ptuimpl.saveOrUpdate(ptu);// 保存
            //添加日志
            logger.info("导入外呼用户对象成功，当前操作人："+getLoginUser().getUserName()+ID+getLoginUser().getUserId()+")");
            successNum++;
        }
    }

    /**
     * 功能：初始化输出的错误文件的头部文件
     */
    private boolean initErrorFileHead()
    {
        boolean flage = initErrorFile();
        if (flage && errorWWB == null)
        {
            try
            {
                errorWWB = Workbook.createWorkbook(new File(errorFileNamePath));
                errorws = errorWWB.createSheet("sheet1", 0);
                Label label00 = new Label(0, 0, "序号");// 添加错误信息表的表头。这几行是固定的。
                Label label01 = new Label(1, 0, "姓名");
                Label label02 = new Label(2, 0, "性别");
                Label label03 = new Label(3, 0, "联系电话");
                Label label04 = new Label(4, 0, "备用号码");
                Label label05 = new Label(5, 0, "居住地");
                Label label06 = new Label(6, 0, "客户类别");
                Label label07 = new Label(7, 0, "备注");
                errorws.addCell(label00);
                errorws.addCell(label01);
                errorws.addCell(label02);
                errorws.addCell(label03);
                errorws.addCell(label04);
                errorws.addCell(label05);
                errorws.addCell(label06);
                errorws.addCell(label07);
            }
            catch (Exception e)
            {
                logger.error("向文件中输入错误信息头部时出现异常！当前操作人："+getLoginUser().getUserName()+ID+getLoginUser().getUserId()+")", e);
                return false;
            }

        }
        return flage;

    }
    /**
     * 功能：验证非空
     * @return
     * @author:张东领
     */
    public String verifyNotEmpty(String thisCellContent, int r, int c){
    	String result = null;
    	if (thisCellContent == null || "".equals(thisCellContent))
        {// 所有字段全部不能为空。
            if (c == 1)
            {
                result = "第" + (r + 1) + "行第" + (c + 1) + "列姓名不能为空！";
                return result;
            }
            if (c == 2)
            {
                result = "第" + (r + 1) + "行第" + (c + 1) + "列性别不能为空！";
                return result;
            }
            if (c == 3)
            {
                result = "第" + (r + 1) + "行第" + (c + 1) + "列手机号码不能为空！";
                return result;
            }
        }
		return result;
    }

    /**
     * 功能：验证各个单元是否符合要求
     * @param thisCellContent 单元格让内容
     * @param r 行号
     * @param c 列号
     * @return
     */
    private String verify(String thisCellContent, int r, int c)
    {
        String regex3 = "^1\\d{10}$";// 第3列正则（手机号码）
        String regex4 = "^(\\d{3,4}[- ]?)?\\d{7,8}$";// 第4列正则（座机号码）
        String result = null;
        if (thisCellContent != null)
        {
            thisCellContent = thisCellContent.trim();
        }
        // 所有字段全部不能为空。
        result = verifyNotEmpty(thisCellContent, r, c);
        
        switch (c)
        {
        case 0:// 序号
            break;
        case 1:
            if (thisCellContent.length() > 20)
            {// 判断姓名
                result = "第" + (r + 1) + "行第" + (c + 1) + "列用户姓名长度不能大于10！";
            }
            break;
        case 2:
            if (thisCellContent.length() > 2)
            {// 判断性别
                result = "第" + (r + 1) + "行第" + (c + 1) + "列性别长度不能大于1！";
            }
            else
            {// 判断性别是否存在
                if (!"男".equals(thisCellContent) && !"女".equals(thisCellContent))
                {
                    result = "第" + (r + 1) + "行第" + (c + 1) + "列性别内容不符合!";
                }
            }
            break;
        case 3:
            if (!"".equals(thisCellContent) && (thisCellContent.matches(regex3) || thisCellContent.matches(regex4)))
            {// 判断联系电话
            	result = null;
            }
            else
            {
                result = "第" + (r + 1) + "行第" + (c + 1) + "列联系电话格式不正确！";
            }
            break;
        case 4:
            if (!"".equals(thisCellContent)&&!thisCellContent.matches(regex3) && !thisCellContent.matches(regex4))// 判断备用号码
            {
            	result = "第" + (r + 1) + "行第" + (c + 1) + "列备用号码格式不正确！";
            }
            break;
        case 5:
            if (!"".equals(thisCellContent) && (thisCellContent != null)&&thisCellContent.length() > 100)// 判断居住地
            {
            	result = "第" + (r + 1) + "行第" + (c + 1) + "列居住地长度不能大于50！";
            }
            break;

        case 6:
            if (!"".equals(thisCellContent) && (thisCellContent != null)&&!"一般客户".equals(thisCellContent) && !"VIP客户".equals(thisCellContent))// 判断客户类别
            {
            	result = "第" + (r + 1) + "行第" + (c + 1) + "列客户类别内容不符合!";
            }
            break;
        case 7:
            if (!"".equals(thisCellContent) && (thisCellContent != null)&&thisCellContent.length() > 1000)// 判断备注
            {
            	result = "第" + (r + 1) + "行第" + (c + 1) + "列备注长度不能大于500！";
            }
            break;
        default:
            break;
        }
        return result;
    }

    // ------------------------------------------------------------以下是通用代码 一般不用东
    /**
     * 功能：判断单元格是否符合要求 并记录到错误的sheet中
     * @param cell 单元格
     * @param c 列
     * @param r 行
     * @return
     */
    protected boolean maskExcelFile(Cell cell, int c, int r)
    {
        if (initErrorFileHead())
        {
            String thisCellContent = cell.getContents();
            String verifyResult = verify(thisCellContent, r, c);// 通过这个单元格的内容，和这个单元格所在的列号，判断出这个单元格中的信息是否符合标准。符合返回true。
            WritableCellFormat wcfDt = new WritableCellFormat(NumberFormats.TEXT);// 设置单元格为文本格式
            Label label0x = null;
            try
            {
                if (verifyResult == null)
                {// 根据这个单元格中内容的符合性来，判断是否给这一列添加底色。
                    label0x = new Label(c, errorRowsNum, thisCellContent, wcfDt);
                }
                else
                {
                    wcfDt.setBackground(Colour.RED);
                    label0x = new Label(c, errorRowsNum, thisCellContent, wcfDt);
                    /* 给错误的单元格添加批注信息 - 开始 */
                    WritableCellFeatures cellFeatures = new WritableCellFeatures();
                    cellFeatures.setComment(verifyResult);
                    label0x.setCellFeatures(cellFeatures);
                    /* 给错误的单元格添加批注信息 - 结束 */
                }
                errorws.addCell(label0x);// 把这个单元格添加到错误信息表中。若错误行数不变时，这个单元格中的数据会被覆盖掉。
            }
            catch (Exception e)
            {
                logger.error("添加错误记录单元发生错误！当前操作人："+getLoginUser().getUserName()+ID+getLoginUser().getUserId()+")", e);
            }
            return verifyResult == null;// 返回一个boolean值。true表示这个单元格中的数据符合标准。
        }
        else
        {
            return false;
        }
    }

}
