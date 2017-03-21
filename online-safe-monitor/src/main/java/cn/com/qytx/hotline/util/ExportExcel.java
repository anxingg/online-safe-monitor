package cn.com.qytx.hotline.util;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.NumberFormats;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import cn.com.qytx.hotline.phonetask.domain.Question;
import cn.com.qytx.hotline.phonetask.domain.QuestionItem;
import cn.com.qytx.platform.base.action.BaseActionSupport;

/**
 * 导出工具类
 * 
 * @author Administrator
 */
@SuppressWarnings("serial")
public class ExportExcel extends BaseActionSupport

{
	/**
	 * 头的宽度
	 */
	private static int titleLength = 6;
	/**
	 * 当前所在的列
	 */
	private int currentCall = titleLength + 1;
	/**
	 * 数据信息
	 */
	private List<Map<String, Object>> mapList;

	/**
	 * 列名
	 */
	private List<String> headList;

	/**
	 * 列字段key
	 */
	private List<String> keyList;

	/**
	 * 自定义样式数据
	 */
	private List<List<CellBean>> cellData;

	/**
	 * 输出流
	 */
	private OutputStream os = null;

	/**
	 * WritableWorkbook
	 */
	private WritableWorkbook workbook = null;
	/**
	 * 选中标记
	 */
	private String right = "√";
	/**
	 * 所有的问题列表
	 */
	List<Question> questions = null;

	/**
	 * 用户对试题的答案
	 */
	Map<String, String> mapAnswer = null;
	/**
	 * 试卷标题
	 */
	private String title;
	/**
	 * 各个问题的起始位置
	 */
	Map<String, Integer> mapQuestion = new HashMap<String, Integer>();
	
	/**
	 * 表头名称
	 */
	private String innerName;
	/**
	 * 最后一行共计
	 */
	public List<String> lastKeyList;

	/**
	 * 构造函数
	 * 
	 * @param outputStream
	 *            输出流
	 * @param headList
	 *            列名
	 * @param mapList
	 *            内容信息
	 * @param keyList
	 *            列字段
	 */
	public ExportExcel(OutputStream outputStream, List<String> headList,
			List<Map<String, Object>> mapList, List<String> keyList,
			List<Question> questions, Map<String, String> mapAnswer,
			String title) {
		this.os = outputStream;
		this.headList = headList;
		this.mapList = mapList;
		this.keyList = keyList;
		this.questions = questions;
		this.mapAnswer = mapAnswer;
		this.title = title;
	}

	/**
	 * 有参构造方法
	 * 
	 * @param outputStream
	 *            输出流
	 * @param dataMapList
	 *            数据的map形式列表
	 * @param headKeyValueMap
	 *            列头和对应值的Key的Map集合，例如:{['name':'姓名'],['gender':'性别']}
	 */
	public ExportExcel(OutputStream outputStream,
			List<Map<String, Object>> dataMapList,
			Map<String, String> headKeyValueMap) {

		this.os = outputStream;
		this.mapList = dataMapList;
		this.headList = new LinkedList<String>();
		this.keyList = new LinkedList<String>();

		Set<Map.Entry<String, String>> set = headKeyValueMap.entrySet();
		Iterator<Map.Entry<String, String>> ite = set.iterator();
		while (ite.hasNext()) {
			Map.Entry<String, String> entry = ite.next();
			keyList.add(entry.getKey());
			headList.add(entry.getValue());
		}
		// for(String key:headKeyValueMap.keySet()){
		// keyList.add(key);
		// headList.add(headKeyValueMap.get(key));
		// }
	}

	/**
	 * 自定义单元格样式构造函数
	 * 
	 * @param response
	 *            HttpServletResponse
	 * @param headList
	 *            列名
	 * @param cellData
	 *            自定义样式数据
	 */
	public ExportExcel(OutputStream outputStream, List<String> headList,
			List<List<CellBean>> cellData) {
		this.os = outputStream;
		this.headList = headList;
		this.cellData = cellData;
	}
	
	/**--------李贺添加来电时段分布表导出----------*/
	/**
     * 构造函数
     * @param outputStream 输出流
     * @param headList 列名
     * @param mapList 内容信息
     * @param keyList 列字段
     */
    public ExportExcel(OutputStream outputStream, List<String> headList,
            List<Map<String, Object>> mapList, List<String> keyList)
    {
        this.os = outputStream;
        this.headList = headList;
        this.mapList = mapList;
        this.keyList = keyList;
    }
    /**
     * 导出来电时段分布
     */
    public void exportTimeFrame()
    {
        try
        {
            workbook = Workbook.createWorkbook(os);
            // 设置sheet页
            WritableSheet ws = workbook.createSheet("sheet 1", 0);
            writeSheetHeadTF(ws);
            writeSheetBodyTF(ws);
            workbook.write();
        }
        catch (Exception e)
        {
            LOGGER.error(e.getMessage());
        }
        finally
        {
            // 关闭流
            this.close();
        }
    }
    
    /**
     * 写标题
     * @param ws ws
     */
    private void writeSheetHeadTF(WritableSheet ws)
    {
        try
        {
            Label lb = null;
            if (null != headList && !headList.isEmpty())
            {
                for (int i = 0; i < headList.size(); i++)
                {
                    lb = new Label(i, 0, headList.get(i));

                    // 设置表格内容信息
                    ws.addCell(lb);

                    // 设置表格宽度
                    ws.setColumnView(i, 15);// 0
                }
            }
        }
        catch (Exception ex)
        {
        	LOGGER.error(ex.getMessage());
        }
    }
    /**
     * 写入导出文件体
     * @param ws WritableSheet
     * @throws WriteException
     * @throws RowsExceededException
     */
    private void writeSheetBodyTF(WritableSheet ws) throws RowsExceededException, WriteException
    {
        if (null != mapList)
        {
            writeDefaultBodyTF(ws);
        }
    }
    /**
     * 写格式单元格
     * 合计写入时占前两单元格
     * @param ws WritableSheet
     */
    private void writeDefaultBodyTF(WritableSheet ws)
    {
        try
        {
            // 设置单元格格式为text
            WritableCellFormat format = new WritableCellFormat(NumberFormats.TEXT);
            //设置字体为arial 字体大小为 10 
//            WritableFont wf = new WritableFont(WritableFont.ARIAL,11);
//            WritableCellFormat formatFont = new WritableCellFormat(wf);
            //单元格中文字为水平居中
//            formatFont.setAlignment(Alignment.CENTRE);
//            //单元格中文字为水平居中
//            formatFont.setVerticalAlignment(VerticalAlignment.CENTRE);
            
            // 自动换行
            format.setWrap(true);
            Label lb = null;
            for (int i = 0; i < mapList.size(); i++)
            {
            	Map<String, Object> map = mapList.get(i);
            	//判断传进来的list是否循环到最后一个，用于添加合并总计列
            	if(i == mapList.size()-1){
            		if (null != keyList && !keyList.isEmpty())
            		{
            			//合并最后一行，第一列和第二列
            			ws.mergeCells(0, mapList.size(), 1, mapList.size());
            			//设置行高
//            			ws.setRowView(mapList.size(), 500);
            			for (int j = 0; j < keyList.size(); j++)
            			{
            				lb = new Label(j, (i + 1), (null != map.get(keyList.get(j)) ? map.get(
            						keyList.get(j)).toString() : ""), format);
            				ws.addCell(lb);
            			}
            		}
            	}else{
            		if (null != keyList && !keyList.isEmpty())
            		{
            			for (int j = 0; j < keyList.size(); j++)
            			{
            				lb = new Label(j, (i + 1), (null != map.get(keyList.get(j)) ? map.get(
            						keyList.get(j)).toString() : ""), format);
            				ws.addCell(lb);
            			}
            		}
            	}
            }
        }
        catch (Exception ex)
        {
        	LOGGER.error(ex.getMessage());
        }
    }
    
	/**--------李贺添加来电时段分布导出--结束---------*/
    
    /**--------张东领添加坐席工作量复杂表头导出开始---------*/
    public ExportExcel(OutputStream outputStream,List<Map<String, Object>> mapList,List<String> keyList,String innerName){
    	this.os = outputStream;
        this.mapList = mapList;
        this.keyList = keyList;
        this.innerName = innerName;
    }
    public void exportSeatWork()
    {
        try
        {
            workbook = Workbook.createWorkbook(os);

            // 设置sheet页
            WritableSheet ws = workbook.createSheet("坐席工作量", 0);
            writeSheetNameSW(ws);
            writesheetheadSW(ws);
            writeSheetBodySW(ws);
            workbook.write();
        }
        catch (Exception e)
        {
        	LOGGER.error(e.getMessage());
        }
        finally
        {
            // 关闭流
            this.close();
        }
    }
    /**
     * 写excel表内部的表名（第一行，跨列，居中）
     * @param ws
     */
    private void writeSheetNameSW(WritableSheet ws){
    	try{
            Label lb = null;
            if (null != keyList && !keyList.isEmpty() && innerName!=null && !"".equals(innerName)){
            	//这里可以设置表头的字号，字体等
            	WritableFont wf = new WritableFont(WritableFont.ARIAL,14,WritableFont.BOLD,false,UnderlineStyle.NO_UNDERLINE,jxl.format.Colour.BLACK);
            	WritableCellFormat format = new WritableCellFormat(wf);
            	//WritableCellFormat format = new WritableCellFormat(NumberFormats.TEXT);
            	format.setAlignment(Alignment.CENTRE); //设置垂直对齐
            	//设置水平居中
            	format.setVerticalAlignment(VerticalAlignment.CENTRE);
            	//设置边框，和边框颜色
            	format.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK);
            	//设置背景色
            	format.setBackground(jxl.format.Colour.AQUA);
            	lb = new Label(0,0,innerName,format);
            	ws.mergeCells(0,0,keyList.size()-1,0);
            	ws.setRowView(0, 500, false); //设置行高
            	// 设置表格内容信息
            	ws.addCell(lb);
            }
            
        }catch (Exception ex){
        	LOGGER.error(ex.getMessage());
        }
    }
    
    String[] in={"呼入数","接听数","接听率（%）","平均振铃时长（秒）","平均通话时长（秒）","呼入总时长（分）"};
	String[] out = {"外呼数","成功数 ","成功率(%)","平均通话时长（秒）","通话总时长（分）"};
    public void writesheetheadSW(WritableSheet ws){
    	Label lb = null;
    	int currentRow=1;
    	int currentCol=0;
		try {
			// 构造格式：ARIAL字体、10号、粗体、非斜体、无下划线、黑色
			WritableFont wf = new WritableFont(WritableFont.ARIAL, 10,
					WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
					jxl.format.Colour.BLACK);
			WritableCellFormat totalx2Format = getSheetHeadForamt(wf);
			// 列 ，行，跨度，跨度
			ws.mergeCells(currentCol, currentRow, currentCol, currentRow+1);
			lb = new Label(currentCol, currentRow, "坐席工号",totalx2Format);
			//设置列宽度，参数1第几列，2宽度
			ws.addCell(lb);
			currentCol=currentCol+1;
			ws.mergeCells(currentCol, currentRow, currentCol, currentRow+1);
			lb = new Label(currentCol, currentRow, "坐席姓名",totalx2Format);
			ws.addCell(lb);
			currentCol=currentCol+1;
			ws.mergeCells(currentCol, currentRow, currentCol, currentRow+1);
			lb = new Label(currentCol, currentRow, "登录总时长（分）",totalx2Format);
			ws.setColumnView(currentCol, 20);
			ws.addCell(lb);
			currentCol=currentCol+1;
			ws.mergeCells(currentCol, currentRow, currentCol, currentRow+1);
			lb = new Label(currentCol, currentRow, "置忙总时长（分）",totalx2Format);
			ws.setColumnView(currentCol, 20);
			ws.addCell(lb);
			currentCol=currentCol+1;
			ws.mergeCells(currentCol, currentRow, currentCol, currentRow+1);
			lb = new Label(currentCol, currentRow, "空闲总时长（分）",totalx2Format);
			ws.setColumnView(currentCol, 20);
			ws.addCell(lb);
			currentCol=currentCol+1;
			ws.mergeCells(currentCol, currentRow, currentCol+5, currentRow);
			lb = new Label(currentCol, currentRow, "接电",totalx2Format);
			ws.addCell(lb);
			currentCol=currentCol+6;
			ws.mergeCells(currentCol, currentRow, currentCol+4, currentRow);
			lb = new Label(currentCol, currentRow, "外呼",totalx2Format);
			ws.addCell(lb);
			//第二行未合并的列
			for(int i=0;i<in.length;i++){
				lb = new Label(5+i, 2, in[i],totalx2Format);
				ws.setColumnView(5+i, 20);
				ws.addCell(lb);
			}
			for(int j=0;j<out.length;j++){
				lb = new Label(11+j, 2, out[j],totalx2Format);
				ws.setColumnView(11+j, 20);
				ws.addCell(lb);
			}
		} catch (RowsExceededException e) {
			LOGGER.error(e.getMessage());
		} catch (WriteException e) {
			LOGGER.error(e.getMessage());
		}
    }
    /**
     * 功能：得到表头样式
     * @param wf:字体样式
     * @return
     */
    private WritableCellFormat getSheetHeadForamt(WritableFont wf){
    	WritableCellFormat totalx2Format = new WritableCellFormat(wf);
		try {
			// 文字垂直居中对齐
			totalx2Format.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			// 文字水平居中对齐
			totalx2Format.setAlignment(jxl.format.Alignment.CENTRE);
			//设置边框，和边框颜色
			totalx2Format.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return totalx2Format;
    }
    
    /**
     * 写入导出文件体
     * @param ws WritableSheet
     * @throws WriteException
     * @throws RowsExceededException
     */
    private void writeSheetBodySW(WritableSheet ws) throws RowsExceededException, WriteException
    {
        if (null != mapList)
        {
            writeDefaultBodySW(ws);
        }
        else if (null != cellData)
        {
            writeUserDefinedBody(ws);
        }
    }

    /**
     * 写默认格式单元格
     * @param ws WritableSheet
     */
    private void writeDefaultBodySW(WritableSheet ws)
    {
        try
        {
            // 设置单元格格式为text
            WritableCellFormat format = new WritableCellFormat(NumberFormats.TEXT);
            // 文字垂直居中对齐
            format.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
         	// 文字水平居中对齐
            format.setAlignment(jxl.format.Alignment.CENTRE);
            //设置边框，和边框颜色
            format.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK);
            // 自动换行
            format.setWrap(true);
            Label lb = null;
            for (int i = 0; i < mapList.size(); i++)
            {
                Map<String, Object> map = mapList.get(i);
                if (null != keyList && !keyList.isEmpty())
                {
                    for (int j = 0; j < keyList.size(); j++)
                    {
                        lb = new Label(j, (i + 3), (null != map.get(keyList.get(j)) ? map.get(
                                keyList.get(j)).toString() : ""), format);
                        ws.addCell(lb);
                    }
                }
            }
        }
 catch (Exception ex)
        {
            LOGGER.error(ex.getMessage());
        }
    }
    
    /**--------张东领添加坐席工作量负责表头导出结束---------*/
	
	/**----------张东领添加开始-----------------*/
	 /**
     * 构造函数
     * @param outputStream 输出流
     * @param headList 列名
     * @param mapList 内容信息
     * @param keyList 列字段
     */
    public ExportExcel(OutputStream outputStream, List<String> headList,
            List<Map<String, Object>> mapList, List<String> keyList,String innerName,List<String> lastKeyList)
    {
        this.os = outputStream;
        this.headList = headList;
        this.mapList = mapList;
        this.keyList = keyList;
        this.innerName = innerName;
        this.lastKeyList = lastKeyList;
    }
    
    public void exportComplex()
    {
        try
        {
            workbook = Workbook.createWorkbook(os);

            // 设置sheet页
            WritableSheet ws = workbook.createSheet("sheet 1", 0);
            writeSheetNameWs(ws);
            writesheetheadWs(ws);
            writeSheetBodyWs(ws);
            workbook.write();
        }
        catch (Exception e)
        {
        	LOGGER.error(e.getMessage());
        }
        finally
        {
            // 关闭流
            this.close();
        }
    }
    
    
    public void writesheetheadWs(WritableSheet ws){
    	Label lb = null;
    	int currentRow=1;
    	int currentCol=0;
		try {
			String[] s={"电话咨询","语音留言","短信平台","合 计"};
			String[] ss={"数量","百分比"};
			// 构造格式：ARIAL字体、10号、粗体、非斜体、无下划线、黑色
			WritableFont wf = new WritableFont(WritableFont.ARIAL, 11,
					WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
					jxl.format.Colour.BLACK);
			WritableCellFormat totalx2Format = new WritableCellFormat(wf);
			// 文字垂直居中对齐
			totalx2Format.setVerticalAlignment(jxl.format.VerticalAlignment.BOTTOM);
			// 文字水平居中对齐
			totalx2Format.setAlignment(jxl.format.Alignment.CENTRE);
			// 列 ，行，跨度，跨度
			ws.mergeCells(currentCol, currentRow, currentCol, currentRow+1);
			lb = new Label(currentCol, currentRow, "序号",totalx2Format);
			ws.addCell(lb);
			currentCol=currentCol+1;
			ws.mergeCells(currentCol, currentRow, currentCol, currentRow+1);
			lb = new Label(currentCol, currentRow, "业务类别",totalx2Format);
			ws.addCell(lb);
			currentCol=currentCol+1;
			for(int i=0;i<s.length;i++){
				ws.mergeCells(currentCol+2*i, currentRow, currentCol+2*(i+1)-1, currentRow);
				lb = new Label(currentCol+2*i, currentRow, s[i],totalx2Format);
				ws.addCell(lb);
			}
			currentRow=currentRow+1;
			currentCol=2;
			for(int i=0;i<(s.length)*2;i++){
				int w=i%2;
				lb = new Label(currentCol+i, currentRow, ss[w],totalx2Format);
				ws.addCell(lb);
			}
		} catch (RowsExceededException e) {
			LOGGER.error(e.getMessage());
		} catch (WriteException e) {
			LOGGER.error(e.getMessage());
		}
    }
    
    
    /**
     * 写excel表内部的表名（第一行，跨列，居中）
     * @param ws
     */
    private void writeSheetNameWs(WritableSheet ws){
    	try{
            Label lb = null;
            if (null != headList && !headList.isEmpty() && innerName!=null && !"".equals(innerName)){
            	//这里可以设置表头的字号，字体等
            	WritableFont wf = new WritableFont(WritableFont.ARIAL,14,WritableFont.BOLD,false,UnderlineStyle.NO_UNDERLINE,jxl.format.Colour.BLACK);
            	WritableCellFormat format = new WritableCellFormat(wf);
            	//WritableCellFormat format = new WritableCellFormat(NumberFormats.TEXT);
            	format.setAlignment(Alignment.CENTRE); //设置垂直对齐
            	lb = new Label(0,0,innerName,format);
            	ws.mergeCells(0,0,headList.size()-1,0);
            	ws.setRowView(0, 500, false); //设置行高
            	// 设置表格内容信息
            	ws.addCell(lb);
            }
            
        }catch (Exception ex){
        	LOGGER.error(ex.getMessage());
        }
    }
    
    /**
     * 写入导出文件体
     * @param ws WritableSheet
     * @throws WriteException
     * @throws RowsExceededException
     */
    private void writeSheetBodyWs(WritableSheet ws) throws RowsExceededException, WriteException
    {
        if (null != mapList)
        {
            writeDefaultBodyWs(ws);
        }
    }
    /**
     * 功能：自定义单元格式
     * @param ws
     */
    private void writeDefaultBodyWs(WritableSheet ws){
    	try {
    		// 设置单元格格式为text
            WritableCellFormat format = new WritableCellFormat(NumberFormats.TEXT);
         // 自动换行
            format.setWrap(true);
            Label lb = null;
            if(mapList.size()>1){
            	for (int i = 0; i < mapList.size(); i++)
            	{
            		Map<String, Object> map = mapList.get(i);
            		if(i==(mapList.size()-1)){
            			WritableFont wf = new WritableFont(WritableFont.ARIAL,12,WritableFont.BOLD,false,UnderlineStyle.NO_UNDERLINE,jxl.format.Colour.BLACK);
            			WritableCellFormat formatLast = new WritableCellFormat(wf);
            			//WritableCellFormat format = new WritableCellFormat(NumberFormats.TEXT);
            			formatLast.setAlignment(Alignment.CENTRE); //设置垂直对齐
            			for(int l=0;l<5;l++){
            				ws.mergeCells((l*2), (i+3), (l*2)+1, (i+3));
            			}
            			lb = new Label(0, (i+3), "总计", formatLast);
            			ws.addCell(lb);
            			for(int k=0;k<lastKeyList.size();k++){
            				lb = new Label(((k*2)+2), (i+3), 
            						(null!=map.get(lastKeyList.get(k))?map.get(lastKeyList.get(k)).toString():""), formatLast);
            				ws.addCell(lb);
            			}
            		}else{
            			if (null != keyList && !keyList.isEmpty())
            			{
            				for (int j = 0; j < keyList.size(); j++)
            				{
            					//李立泼添加此 if 语句。（原来的语句在else中）
            					if(innerName!=null && !"".equals(innerName)){
            						lb = new Label(j, (i + 3), (null != map.get(keyList.get(j)) ? map.get(
            								keyList.get(j)).toString() : ""), format);
            					}
            					ws.addCell(lb);
            				}
            			}
            		}
            		
            	}
            }
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
    }
/**----------张东领添加结束-----------------*/
	/**
	 * 导出
	 */
	public void export() {
		try {
			workbook = Workbook.createWorkbook(os);

			// 设置sheet页
			WritableSheet ws = workbook.createSheet("sheet 1", 0);
			writeSheetTitle(ws, title + "任务-执行结果");
			writeSheetHead(ws);
			this.writeSheetHeadQuestion(ws, questions);
			writeSheetBody(ws);
			workbook.write();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			// 关闭流
			this.close();
		}
	}

	/**
	 * 功能：写标题的问题
	 * 
	 * @param ws
	 * @param questions2
	 */
	private void writeSheetHeadQuestion(WritableSheet ws,
			List<Question> questions2) {
		for (Question question : questions2) {
			String name = question.getName();
			int typeId = question.getTypeId();
			@SuppressWarnings("unused")
			int orderList = question.getOrderList();
			List<QuestionItem> items = question.getItems();
			Label lb = null;
			try {
				if (typeId == 3) {
					// 如果是问题
					ws.mergeCells(currentCall, 1, currentCall + 2, 1);
					lb = new Label(currentCall, 1, name,
							this.getCellFormate(Alignment.CENTRE));
					ws.addCell(lb);
					mapQuestion.put("_" + question.getId(), currentCall);
					currentCall=currentCall+3;
				} else {
					// 列 ，行，跨度，跨度
					ws.mergeCells(currentCall, 1, currentCall + items.size()
							- 1, 1);
					// +" "+typeId+" "+orderList
					lb = new Label(currentCall, 1, name,
							this.getCellFormate(Alignment.CENTRE));
					ws.addCell(lb);
					mapQuestion.put("_" + question.getId(), currentCall);
					for (QuestionItem it : items) {
						lb = new Label(currentCall, 2, it.getContent());
						ws.addCell(lb);
						currentCall++;
					}
				}
				
				

			} catch (Exception e) {
				LOGGER.error(e.getMessage());
			}
		}

	}

	/**
	 * 导出
	 */
	public void exportWithSheetName(String sheetName) {
		try {
			workbook = Workbook.createWorkbook(os);

			// 设置sheet页
			WritableSheet ws = workbook.createSheet(sheetName, 0);
			writeSheetHead(ws);
			writeSheetBody(ws);
			workbook.write();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			// 关闭流
			this.close();
		}
	}

	/**
	 * 写内容标题
	 * 
	 * @param ws
	 *            ws
	 */
	private void writeSheetTitle(WritableSheet ws, String title) {
		try {
			Label lb = null;
			// 列 ，行，跨度，跨度
			ws.mergeCells(0, 0, titleLength, 0);
			// 构造格式：ARIAL字体、10号、粗体、非斜体、无下划线、黑色

			WritableFont wf = new WritableFont(WritableFont.ARIAL, 14,
					WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
					jxl.format.Colour.BLACK);
			WritableCellFormat totalx2Format = new WritableCellFormat(wf);
			// 文字垂直居中对齐
			totalx2Format
					.setVerticalAlignment(jxl.format.VerticalAlignment.BOTTOM);
			// 文字水平居中对齐
			totalx2Format.setAlignment(jxl.format.Alignment.CENTRE);
			lb = new Label(0, 0, title, totalx2Format);

			ws.addCell(lb);
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage());
		}
	}

	/**
	 * 写字段标题
	 * 
	 * @param ws
	 *            ws
	 */
	private void writeSheetHead(WritableSheet ws) {
		try {
			Label lb = null;
			if (null != headList && !headList.isEmpty()) {
				for (int i = 0; i < headList.size(); i++) {
					ws.mergeCells(i, 1, i, 2);
					lb = new Label(i, 1, headList.get(i),
							this.getCellFormate(Alignment.CENTRE));
					// 设置表格内容信息
					ws.addCell(lb);
					// 设置表格宽度
					ws.setColumnView(i, 15);// 0
				}
			}
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage());
		}
	}

	/**
	 * 写入导出文件体
	 * 
	 * @param ws
	 *            WritableSheet
	 * @throws WriteException
	 * @throws RowsExceededException
	 */
	private void writeSheetBody(WritableSheet ws) throws RowsExceededException,
			WriteException {
		if (null != mapList) {
			writeDefaultBody(ws);
		} else if (null != cellData) {
			writeUserDefinedBody(ws);
		}
	}

	/**
	 * 写默认格式单元格
	 * 
	 * @param ws
	 *            WritableSheet
	 */
	private void writeDefaultBody(WritableSheet ws) {
		try {
			// 设置单元格格式为text
			WritableCellFormat format = new WritableCellFormat(
					NumberFormats.TEXT);

			// 自动换行
			format.setWrap(true);
			Label lb = null;
			for (int i = 0; i < mapList.size(); i++) {
				Map<String, Object> map = mapList.get(i);
				if (null != keyList && !keyList.isEmpty()) {
					for (int j = 0; j < keyList.size(); j++) {
						lb = new Label(j, (i + 3), (null != map.get(keyList
								.get(j)) ? map.get(keyList.get(j)).toString()
								: ""), format);
						ws.addCell(lb);
					}
					// 填充问题的答案
					String phoneTaskuserId = map.get("phoneUserid").toString();
					if (phoneTaskuserId != null && phoneTaskuserId.length() > 0) {
						Iterator<String> ite = mapQuestion.keySet().iterator();
						while (ite.hasNext()) {
							String key = ite.next();// 获取问题id
							String answer = mapAnswer
									.get(phoneTaskuserId + key);// 根据问题id+回访id查询回访结果
							int curr = mapQuestion.get(key);// 获取问题的起始列
							if (answer != null && !"".equals(answer)) {
								String[] ans = answer.split("=");
								int type = Integer.valueOf(ans[0]);
								if (type == 3) {
									ws.mergeCells(curr, i + 3, curr + 2, i + 3);
									lb = new Label(curr, i + 3, ans[1]);
									// 设置表格内容信息
									ws.addCell(lb);
								} else if (type == 2) {
									String[] item = ans[1].split("_");
									if (item.length > 0) {
										for (int it = 0; it < item.length; it++) {
											int span = Integer
													.valueOf(item[it]);
											if (span > 0) {
												lb = new Label(curr + span-1  ,
														i + 3, this.right);
												// 设置表格内容信息
												ws.addCell(lb);
											}
										}
									}

								} else {
									int span = Integer.valueOf(ans[1]);
									if (span >= 0) {
										lb = new Label(curr + span-1, i + 3,
												this.right);
										// 设置表格内容信息
										ws.addCell(lb);
									}

								}
							}

						}
					}

				}
			}
		} catch (RowsExceededException e) {
			LOGGER.error(e.getMessage());
		} catch (WriteException e) {
			LOGGER.error(e.getMessage());
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage());
		}
	}

	/**
	 * 输入用户自定单元格样式
	 * 
	 * @param ws
	 *            WritableSheet
	 * @throws WriteException
	 * @throws RowsExceededException
	 */
	private void writeUserDefinedBody(WritableSheet ws)
			throws RowsExceededException, WriteException {
		Label lb = null;
		for (int i = 0; i < cellData.size(); i++) {
			List<CellBean> cellList = cellData.get(i);
			if (null != cellList && !cellList.isEmpty()) {
				CellBean tempCellBean = null;
				for (int j = 0; j < cellList.size(); j++) {
					tempCellBean = cellList.get(j);
					if (null != tempCellBean) {
						lb = new Label(j, (i + 1),
								cellList.get(j).getContent(),
								getWritableCellFormat(tempCellBean));
						ws.addCell(lb);
					}
				}
			}
		}
	}

	private WritableCellFormat getWritableCellFormat(CellBean cellBean) {
		WritableCellFormat format = new WritableCellFormat();
		if (null != cellBean) {
			if (null != cellBean.getDisplayFormat()) {
				// 设置单元格格式
				format = new WritableCellFormat(cellBean.getDisplayFormat());
			}

			// 设置单元格背景色
			Colour backgroundColour = cellBean.getBackgroundColour();
			if (null != backgroundColour) {
				try {
					format.setBackground(backgroundColour);
				} catch (WriteException e) {
					LOGGER.error(e.getMessage());
				}
			}

		}
		return format;
	}

	/**
	 * 关闭工作流
	 */
	private void close() {
		if (null != workbook) {
			try {
				workbook.close();
			} catch (WriteException e) {
				LOGGER.error(e.getMessage());
			} catch (IOException e) {
				LOGGER.error(e.getMessage());
			}
		}
		if (null != os) {
			try {
				os.close();
			} catch (IOException e) {
				LOGGER.error(e.getMessage());
			}
		}
	}

	public List<Map<String, Object>> getMapList() {
		return mapList;
	}

	public void setMapList(List<Map<String, Object>> mapList) {
		this.mapList = mapList;
	}

	public List<String> getHeadList() {
		return headList;
	}

	public void setHeadList(List<String> headList) {
		this.headList = headList;
	}

	public List<String> getKeyList() {
		return keyList;
	}

	public void setKeyList(List<String> keyList) {
		this.keyList = keyList;
	}

	public WritableCellFormat getCellFormate(Alignment align) {
		WritableCellFormat wc = new WritableCellFormat();
		try {
			// 设置居中
			wc.setAlignment(align);
		} catch (WriteException e) {
			LOGGER.error(e.getMessage());
		}
		return wc;
	}

	/**
	 * @return the questions
	 */
	public List<Question> getQuestions() {
		return questions;
	}

	/**
	 * @param questions
	 *            the questions to set
	 */
	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public String getInnerName() {
		return innerName;
	}

	public void setInnerName(String innerName) {
		this.innerName = innerName;
	}

	public List<String> getLastKeyList() {
		return lastKeyList;
	}

	public void setLastKeyList(List<String> lastKeyList) {
		this.lastKeyList = lastKeyList;
	}

}
