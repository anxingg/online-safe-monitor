package cn.com.qytx.hotline.commoncard.action;

import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import cn.com.qytx.platform.base.action.BaseActionSupport;
import cn.com.qytx.webservice.impl.CardService;
import cn.com.qytx.webservice.impl.CardServiceService;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

/**
 * 一卡通的信息请求的Action
 * @author 李立泼
 * 创建时间：2014年12月10日
 *
 */
public class SelectMsgByCommonCard extends BaseActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5772835455664969062L;

	private String requestMsg;//传过来的参数信息(json格式)
	private List<Parameter> parameterList;//解析后的参数
	
	/**
	 * 根据传过来的信息，返回结果。
	 * @return json格式
	 */
	@SuppressWarnings("serial")
	public String selectMsg(){
		try{
			Gson gson = new Gson();
			ResponseMsg rm = null;
			if( StringUtils.isNotBlank(requestMsg) ){
				//当参数信息不是空是时，转成参数对象的集合。
				parameterList = gson.fromJson(requestMsg, new TypeToken<List<Parameter>>() {}.getType());
				rm = resolveParameter(parameterList) ;
			}else{
				rm = new ResponseMsg( null, 0 );
			}
			String jsonData = gson.toJson(rm);
//			System.out.println( "jsonData: " + jsonData);
			ajax( jsonData );
		}catch(Exception e){
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	
	/**
	 * 依参数集合，做不同的操作(模拟一个接口的实现)
	 * @param parameterList 传过来的参数
	 * @return 0表示失败，1表示成功
	 */
	private ResponseMsg resolveParameter( List<Parameter> parameterList ){
		int resultNum = 0;
		Object relayParameter = null;
		if( parameterList != null && !parameterList.isEmpty() ){
			for( int i = 0; i < parameterList.size(); i++ ){
				int paramIndex = parameterList.get(i).getIndex();//参数序号
				switch( paramIndex ){
					case 0://中转参数
						relayParameter = parameterList.get(i).getParametervalue();
						break;
					case 1://挂失
						resultNum = lossReport(parameterList.get(i).getParametervalue());
						break;
					default:
						break;
				}
			}
		}
		if( relayParameter == null ){
			resultNum = 0;
		}
		return new ResponseMsg(relayParameter, resultNum);
	}
	
	/**
	 * 挂失
	 * @param idCard
	 * @return
	 */
	private int lossReport( String idCard ){
		String result = null;
		CardServiceService css = new CardServiceService();
		CardService   cs = css.getCardService();
		String srcxml="<操作*>临时挂失</操作*><用户名*>12333X</用户名*><密码*>hn12333lsgs</密码*><城市代码*></城市代码*><社会保障卡卡号*></社会保障卡卡号*><社会保障号码*>"+ idCard +"</社会保障号码*><姓名*></姓名*><开户银行></开户银行><银行卡号></银行卡号>";
		//<ERR>身份证号不存在(或发卡城市不符)</ERR>
		//<ERR>00</ERR> ---成功
		//<ERR>01</ERR> ---失败
		String desxml=cs.allDsjk( srcxml );
		desxml = desxml.toUpperCase();
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		try {
			builder = dbf.newDocumentBuilder();
			Document doc = builder.parse(new java.io.ByteArrayInputStream(desxml.getBytes("UTF-8")));
			NodeList errList = doc.getElementsByTagName("ERR");
			Node errNode = (Node)errList.item(0);
			result = errNode.getTextContent();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		if( "00".equals(result) ){
			return 1;
		}else{
			return 0;
		}
	}
	
	/**
	 * 一卡通的参数实体类
	 * @author 李立泼
	 * 创建时间：2014年12月10日
	 * 
	 */
	static class ResponseMsg{
		
		/**
		 * 中转的参数
		 */
		private Object relayParameter;
		
		/**
		 * 返回值（1表示成功，0表示失败）
		 */
		private int resultNum;
		
		public ResponseMsg() {
			super();
		}

		public ResponseMsg(Object relayParameter, int resultNum) {
			super();
			this.relayParameter = relayParameter;
			this.resultNum = resultNum;
		}

		public Object getRelayParameter() {
			return relayParameter;
		}

		public void setRelayParameter(Object relayParameter) {
			this.relayParameter = relayParameter;
		}

		public int getResultNum() {
			return resultNum;
		}

		public void setResultNum(int resultNum) {
			this.resultNum = resultNum;
		}
		
	}
	
	
	/**
	 * 一卡通请求的参数实体类
	 * @author 李立泼
	 * 创建时间：2014年12月10日
	 *
	 */
	static class Parameter{
		/**
		 * 参数序号（用来表示第几个参数）
		 */
		private int index;
		
		/**
		 * 参数名
		 */
		private String parametername;
		
		/**
		 * 参数值
		 */
		private String parametervalue;
		
		public int getIndex() {
			return index;
		}
		public void setIndex(int index) {
			this.index = index;
		}
		public String getParametername() {
			return parametername;
		}
		public void setParametername(String parametername) {
			this.parametername = parametername;
		}
		public String getParametervalue() {
			return parametervalue;
		}
		public void setParametervalue(String parametervalue) {
			this.parametervalue = parametervalue;
		}
	}
	
	/**
	 * 验证输入的身份证号算法上是否正确(入参不做非空验证)
	 * @param idCode 身份证号码（仅支持18位的身份证号码）
	 * @return 返回true表示验证通过，返回false表示验证不通过。
	 */
	public static boolean verifyIdCode(String idCode){
		if(idCode.length()==18){
			/* 权数 */
			int[] weight = {7,9,10,5,8,4,2,1,6,3,7,9,
					10,5,8,4,2};
			int sum=0;
			for(int i=0; i<17; i++){
				char c = idCode.charAt(i);
				int w = weight[i];
				sum += (c-'0') * w; 
			}
			char[] code = {'1', '0', 'X', '9', '8',
					'7', '6', '5', '4', '3', '2'}; 
			char last = code[sum%11];
			return last==idCode.charAt(idCode.length()-1);
		}else{
			return false;
		}
	}

	public String getRequestMsg() {
		return requestMsg;
	}

	public void setRequestMsg(String requestMsg) {
		this.requestMsg = requestMsg;
	}

	public List<Parameter> getParameterList() {
		return parameterList;
	}

	public void setParameterList(List<Parameter> parameterList) {
		this.parameterList = parameterList;
	}
	
}
