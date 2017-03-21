<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setAttribute("ctx", basePath);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>公告管理</title>
<link href="${ctx }platform/css/base.css" rel="stylesheet" type="text/css" />
<link href="${ctx }platform/css/public.css" rel="stylesheet" type="text/css" />
<link href="${ctx }platform/css/add_com.css" rel="stylesheet" type="text/css" />
<link href="${ctx }platform/plugins/datatable/table_style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx }platform/js/jquery-1.6.min.js"></script>
<script type="text/javascript" src="${ctx }platform/plugins/dialog/artDialog.js?skin=blue"></script>
<script type="text/javascript" src="${ctx }platform/js/base.js"></script>
<script type="text/javascript" src="${ctx }platform/plugins/dialog/iframeTools.js"></script>
                      
<script type="text/javascript" src="${ctx }platform/js/seatfun.js"></script>

<script type="text/javascript">
var basePath="${ctx }";
</script>
</head>

<body>

<div class="addcon">
		<div class="szlist">
				<p>
						<label class="radio" >
								<input type='checkbox' name='menuModule' value='threecall,threecall' 
	                   id='threecall_threecall' onClick='goSelect(this.id)' 
		                title="三方通话"  />
								三方通话 </label>
				</p>
				<div class="ml20" >
						<p>
								<label class="radio">
										<input type='checkbox' name='menuModule' 
		                 value='threecall,freeseat'
		                        id='threecall_freeseat' onClick='goSelect(this.id)' 
		                        title='空闲坐席' />
										空闲坐席 </label>
						</p>
						
						<p>
								<label class="radio">
										<input type='checkbox' name='menuModule' 
		                 value='threecall,addressbook'
		                        id='threecall_addressbook' onClick='goSelect(this.id)' 
		                        title='通讯录人员 '  />
										通讯录人员 </label>
						</p>
						
						<p>
								<label class="radio ">
										<input type='checkbox' name='menuModule' 
		                 value='threecall,definephone'
		                        id='threecall_definephone' onClick='goSelect(this.id)' 
		                        title='手动输入号码 '  >
										手动输入号码 </label>
						</p>
				</div>
		</div>
		<div class="szlist mt10">
				<label class="radio ">
						<input type='checkbox' name='menuModule' 
		                 value='transfer,transfer'
		                        id='transfer_transfer' onClick='goSelect(this.id)' 
		                        title='通话转接'  >
						通话转接</label>
				<div class="ml20 mt10">
						<label class="radio">
								<input type="radio" name="transferWay" value="1" id="way_1" checked />
								单步转移 </label>
						
						<label class="radio ">
								<input type="radio" name="transferWay" value="2" id="way_2"  />
								咨询转接 </label>
				</div>
				<div class="ml20">
						<p>
								<label class="radio">
										<input type='checkbox' name='menuModule' 
		                 value='transfer,freeseat'
		                        id='transfer_freeseat' onClick='goSelect(this.id)' 
		                        title='空闲坐席'  >
										空闲坐席 </label>
						</p>
						
						<p>
								<label class="radio">
										<input type='checkbox' name='menuModule' 
		                 value='transfer,addressbook'
		                        id='transfer_addressbook' onClick='goSelect(this.id)' 
		                        title='通讯录人员'  >
										通讯录人员 </label>
						</p>
						
						<p>
								<label class="radio ">
										<input type='checkbox' name='menuModule' 
		                 value='transfer,definephone'
		                        id='transfer_definephone' onClick='goSelect(this.id)' 
		                        title='手动输入号码'  >
										手动输入号码 </label>
						</p>
						<p>
								<label class="radio ">
										<input type='checkbox' name='menuModule' 
		                 value='transfer,trunqueue'
		                        id='transfer_trunqueue' onClick='goSelect(this.id)' 
		                        title='转队列'  >
										转队列</label>
						</p>
				</div>
		</div>
		<div class="szlist">
				<p>
						<label class="radio ">
								<input type="checkbox" name="outcallManaul" value="" id="outcallManaul"  />
								手动外呼 </label>
				</p>
				<p>
						<label class="radio ">
								<input type="checkbox" name="keepOrRecall" value="" id="keepOrRecall"  />
								保持/接回
 </label>
				</p>
				<p>
						<label class="radio ">
								<input type="checkbox" name="isHangup" value="" id="isHangup"  />
								挂断按钮
 </label>
				</p>
				<p>
						<label class="radio ">
								<input type="checkbox" name="isBusyOrIdle" value="" id="isBusyOrIdle"  />
								置忙/置闲
 </label>
				</p>
				<p>
						<label class="radio ">
								<input type="checkbox" name="isMonitor" value="" id="isMonitor"  />
								班长监听
 </label>
				</p>
				<p>
						<label class="radio ">
								<input type="checkbox" name="isAfterCallDeal" value="" id="isAfterCallDeal"  />
								话后处理 <em id="dealLength"><input name="dealLengthIn" id="dealLengthIn" type="text" onkeyup="value=value.replace(/[^\d]/g,'') " style="width:50px; margin:0px 5px" />秒</em>
 </label>
				</p>
				<p>
						<label class="radio ">
								<input type="checkbox" name="isAddressBook" value="" id="isAddressBook"  />
								显示通讯录
 </label>
				</p>
				<p>
						<label class="radio ">
								<input type="checkbox" name="isKnowledge" value="" id="isKnowledge"  />
								显示知识库
 </label>
				</p>
				<p>
						<label class="radio ">
								<input type="checkbox" name="isNotice" value="" id="isNotice"  />
								显示公告
 </label>
				</p>
				<p>
						<label class="radio ">
								<input type="checkbox" name="isNewWorkOrder" value="" id="isNewWorkOrder"  />
								显示新建工单
 </label>
				</p>
		</div>
		<div class="buttonArea">
    <input class="formButton_tab" id="saveSeatFunSet" type="submit" value="保 存">
    &nbsp; </div>
</div>

</body>
</html>
