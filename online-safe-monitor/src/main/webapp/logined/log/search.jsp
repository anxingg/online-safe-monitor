<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>日志查询列表</title>
		<jsp:include page="../../wh/logined/head.jsp" />
		<!-- flat css js start-->
		<%-- <link href="${ctx }flat/css/reset.css" rel="stylesheet" type="text/css" /> --%>
		<%-- <link href="${ctx }flat/css/Reminder.css" rel="stylesheet" type="text/css" /> --%>
		<%-- <link href="${ctx }flat/plugins/datatable/skins/datatable_default.css" rel="stylesheet" type="text/css" /> --%>
		<link href="${ctx}wh/plugins/datatable/skins/datatable_default.css?version=${version}" rel="stylesheet" type="text/css"/>
		
		<script type="text/javascript" src="${ctx}plugins/datatable/jquery.dataTables.min.js"></script>
		<script type="text/javascript" src="${ctx }flat/js/placeholder.js"></script>
		<script type="text/javascript" src="${ctx}plugins/My97DatePicker/WdatePicker.js"></script>
		<!-- flat css js end-->
		<!-- table.css -->
		<!-- 人员选择  start-->
		<script type="text/javascript" src="${ctx}js/logined/user/selectuser.js"></script>
	    <script type="text/javascript" src="${ctx}js/common/hashmap.js"></script>
	    <script type="text/javascript" src="${ctx}js/common/treeNode.js"></script>
	    <script type="text/javascript" src="${ctx}js/user/selectuser.js"></script>
	    <!-- 人员选择  end-->
		<script type="text/javascript" src="${ctx}js/logined/log/search.js"></script>
	</head>
	<body>
		<input type="hidden" id="gobackType"/>
		<input type="hidden" id="max" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" class="formText" value="300"/>
		
		<div class="list">
			<!-- <div class="pageTitle"><em class="iconSearch">查询结果<i class="ml10 gray_9 f12 nor">（最多显示300条记录）</i></em></div>-->
			<%-- 
			   <div class="listbtn">
						<div class="tDiv2">
								
								<div class="fbutton">
									<div><span class="export"  id="exportLog">全部导出</span></div>
								</div>
								<div class="Search ml20" style="display:none">
										<label>&nbsp;&nbsp;&nbsp;&nbsp;查询的条数</label>
										<input type="text" id="max" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" class="formText" value="300"/>
		                                <input hideFocus="" name="searchNew" id="searchNew" type="button"  class="formButton_green"  value="确定"/>
								</div>
						</div>
						<div style="clear:both"></div>
				</div>
			 --%>
			<div class="searchArea">
				<ul>
					<li><label>日志类型：</label>
						<select id="logType">
							<option value="0">所有日志</option>
							<!-- <option value="1">登录日志</option> -->
							<!-- <option value="2">密码错误</option> -->
							<!-- <option value="3">用户名错误</option> -->
							<!-- <option value="4">admin清空密码</option> -->
							<!-- <option value="5">非法IP登录</option>  --> 
							<!-- <option value="6">退出系统</option> -->
							<option value="7">添加用户</option>
							<option value="8">编辑用户</option>
							<option value="9">删除用户</option>
							<!-- <option value="10">员工离职</option>   --> 
							<option value="11">修改登录密码</option>
							<!-- <option value="12">添加部门</option> -->
							<!-- <option value="13">编辑部门</option> -->
							<!-- <option value="14">删除部门</option> -->
							<!-- <option value="15">删除公告</option> -->
							<!-- <option value="16">发送邮件</option> -->
							<!-- <option value="17">删除邮件</option> -->
							<!-- <option value="18">按模块设置管理范围</option> --> 
							<option value="25">重置单位密码</option>
							<option value="26">事故新增</option>
							<option value="27">事故修改</option>
							<option value="28">事故删除</option>
							<option value="29">公司危险化学品新增</option>
							<option value="30">公司危险化学品修改</option>
							<option value="31">公司危险化学品删除</option>
							<option value="32">重大危险源新增</option>
							<option value="33">重大危险源修改</option>
							<option value="34">重大危险源删除</option>
							<option value="35">安全隐患排查新增</option>
							<option value="36">安全隐患排查修改</option>
							<option value="37">安全隐患排查删除</option>
							<option value="38">年度培训新增</option>
							<option value="39">年度培训修改</option>
							<option value="40">年度培训删除</option>
							<option value="41">安全培训记录新增</option>
							<option value="42">安全培训记录修改</option>
							<option value="43">安全培训记录删除</option>
							<option value="44">岗前三级培训新增</option>
							<option value="45">岗前三级培训修改</option>
							<option value="46">岗前三级培训删除</option>
							<option value="47">题库管理新增</option>
							<option value="48">题库管理修改</option>
							<option value="49">题库管理删除</option>
							<option value="50">题库管理生效</option>
							<option value="51">题库管理失效</option>
							<option value="52">试卷管理新增</option>
							<option value="53">试卷管理生效</option>
							<option value="54">试卷管理失效</option>
							<option value="55">试卷管理删除</option>
							<option value="56">应急演练新增</option>
							<option value="57">应急演练修改</option>
							<option value="58">应急演练删除</option>
							<option value="59">应急预案新增</option>
							<option value="60">应急预案修改</option>
							<option value="61">应急预案删除</option>
							<option value="62">企业注册</option>
							<option value="63">企业信息修改</option>
							<!-- <option value="64">企业法人新增</option> -->
							<option value="65">企业法人修改</option>
							<option value="66">企业证照修改</option>
							<option value="67">企业证照删除</option>
							<option value="68">特种作业人员新增</option>
							<option value="69">特种作业人员修改</option>
							<option value="70">特种作业人员删除</option>
							<option value="71">安全管理人员新增</option>
							<option value="72">安全管理人员修改</option>
							<option value="73">安全管理人员删除</option>
							<option value="74">企业产品新增</option>
							<option value="75">企业产品修改</option>
							<option value="76">企业产品删除</option>
							<option value="77">危险化学品新增</option>
							<option value="78">危险化学品修改</option>
							<option value="79">危险化学品删除</option>
							<option value="80">职业卫生专家新增</option>
							<option value="81">职业卫生专家修改</option>
							<option value="82">职业卫生专家删除</option>
							<option value="83">数据字典新增</option>
							<option value="84">数据字典修改</option>
							<option value="85">数据字典删除</option>
							<option value="86">公告发布</option>
							<option value="87">公告存草稿</option>
							<option value="88">公告修改</option>
							<option value="89">公告删除</option>
							<option value="90">公告置顶</option>
							<option value="91">公告取消置顶</option>
							<option value="92">公告终止</option>
							<option value="93">公告生效</option>
							<option value="94">政策法规发布</option>
							<option value="95">政策法规存草稿</option>
							<option value="96">政策法规修改</option>
							<option value="97">政策法规删除</option>
							<option value="98">政策法规置顶</option>
							<option value="99">政策法规取消置顶</option>
							<option value="100">政策法规终止</option>
							<option value="101">政策法规生效</option>
							<option value="102">危险化学品目录新增</option>
							<option value="103">危险化学品目录修改</option>
							<option value="104">危险化学品目录删除</option>
							<option value="105">应急机构管理新增</option>
							<option value="106">应急机构管理修改</option>
							<option value="107">应急机构管理删除</option>
							<option value="108">救援物资新增</option>
							<option value="109">救援物资修改</option>
							<option value="110">救援物资删除</option>
							<option value="111">安全管理机构修改</option>
							<option value="112">工艺流程新增</option>
							<option value="113">工艺流程修改</option>
							<option value="114">工艺流程删除</option>
							<option value="115">重大危险源危化品目录对象新增</option>
							<option value="116">重大危险源危化品目录对象修改</option>
							<option value="117">重大危险源危化品目录对象删除</option>
							<option value="118">安全生产费用提取新增</option>
							<option value="119">安全生产费用使用新增</option>
							<option value="120">非煤矿山专家新增</option>
							<option value="121">非煤矿山专家修改</option>
							<option value="122">非煤矿山专家删除</option>
							<option value="123">危险化学品专家新增</option>
							<option value="124">危险化学品专家修改</option>
							<option value="125">危险化学品专家删除</option>
						</select>
					</li>
					<li><label>起止时间：</label><input id="startTime" style="width:170px;" name="startTime" size="25"   onkeyup="value=value.replace(/(\s*$)/g,'')" onkeydown="FSubmit(event.keyCode||event.which);"  value=""  class="Wdate formText"  type="text" onFocus="WdatePicker({maxDate: '#F{$dp.$D(\'endTime\')}',skin:'default',dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>-<input id="endTime"  style="width:170px;" name="endTime"  size="25"   onkeyup="value=value.replace(/(\s*$)/g,'')" onkeydown="FSubmit(event.keyCode||event.which);"  value=""  class="Wdate formText"  type="text" onfocus="WdatePicker({minDate: '#F{$dp.$D(\'startTime\')}',skin:'default',dateFmt:'yyyy-MM-dd HH:mm:ss'})"/></li>
					<li><label>关键字：</label><input  type="text" id="loginUserName" class="formText Name" placeholder="用户姓名"/></li>
					<li><input class="searchButton" id="search" type="button" value="查询"/><div class="fButton blueBtn"><span class="export"  id="exportLog">全部导出</span></div></li>
				</ul>
			</div>
			<table class="pretty dataTable"  cellspacing="0" cellpadding="0" id="Table">
				<thead>
					<tr>
					<th style="width:90px;">时间</th>
					<th style="width:90px;">用户姓名</th>
					<th style="width:130px;">IP地址</th>
					<th style="width:120px;">日志类型</th>
					<th class="right_bdr0">备注</th>
					</tr>
				</thead>
			</table>
		</div>
	</body>
	<script>funPlaceholder(document.getElementById("loginUserName"));</script>
</html>