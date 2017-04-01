<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8" pageEncoding="utf-8"  %>
<%@ page import="cn.com.qytx.platform.utils.datetime.Lunar"%>
<%@ page import="cn.com.qytx.cbb.login.action.LogoConfig"%>
<%@ page import="cn.com.qytx.platform.utils.weather.Weather"%>
<%@ page import=" cn.com.qytx.platform.org.domain.ModuleInfo" %>
<jsp:include page="../../common/taglibs.jsp"/>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>${applicationScope.systemBasisSet.backstageName}</title>
	<jsp:include page="osmHead.jsp" />
	<script type="text/javascript" src="${ctx}wh/js/osmIndex.js"></script>
	<script src="${ctx }wh/js/calendar.js" type="text/javascript"></script>
	<script type="text/javascript">
		var basePath = "${ctx}";
  		function exit() {
  			art.dialog.confirm('确定关闭吗？', function() {
  				 //window.document.location = basePath + "logout.jsp";
  				 window.opener=null;
				 window.open('','_self');
				 window.close();
  			});
  		};
  	</script>
</head>
<body>
	<div class="wrap">
		<div class="mainhead">
			<div class="logo">
				<img src="../img/logo.png"/>
				<span>${applicationScope.systemBasisSet.backstageName}</span>
			</div>
			<div class="headMenu">
				<span class="username">欢迎  ${session.adminUser.userName} 登陆系统</span>
				<ul>
					<a href="${ctx}wh/logined/index.jsp"">
						<li>
							<img src="../img/home.png"/>
							<p>首页</p>
						</li>
					</a>
					<a href="###">
						<li class="headMenu_liActive">
							<img src="../img/set.png"/>
							<p>系统管理</p>
						</li>
					</a>
					<a href="###">
						<li>
							<img src="../img/person.png"/>
							<p>个人中心</p>
						</li>
					</a>
					<a href="javascript:void(0);" onclick="exit();">
						<li>
							<img src="../img/exit.png"/>
							<p>安全退出</p>
						</li>
					</a>
				</ul>
			</div>
		</div>			
		<div class="panel">
				<img src="img/list.png" alt="" style="width:100%;vertical-align: bottom;" />
			</div>
			<div class="check_right">
				<div class="bread-line">
					<label for="">位置：</label>
					<a href="###">安监在线</a>>
					<a href="###">设备管理</a>>
					<a href="###">添加采集设备</a>
				</div>
				<div class="list">
					<div class="collect_search">
						<span class="add_eq">+添加设备</span>
						<span class="search_eq">查询</span>
						<input type="text" class="search_inp" name="" placeholder="请输入设备名称 企业名称 设备地址" id="" />
					</div>
					<table id="" cellpadding="0" cellspacing="0" class="pretty " aria-describedby="myTable_info">
						<thead>
							<tr>
								<th style="width: 15%;">企业</th>
								<th style="width: 12%;">安装位置</th>
								<th style="width: 20%;">型号</th>
								<th style="width:10%;">通道数</th>
								<th style="width: 15%;">设备地址</th>
								<th style="width: 10%;">状态</th>
								<th style="width: 18%;">操作</th>
							</tr>
						</thead>

						<tbody role="alert" aria-live="polite" aria-relevant="all">
							<tr >
								<td>四灵</td>
								<td>1#电石炉</td>
								<td>华硕ADAM-6017</td>
								<td>8</td>
								<td>1.28.80.142:8080</td>
								<td>
									<span class="state_ena">启用</span>
								</td>
								<td>
									<span class="opera_span">启用</span>
									<span class="opera_span">启用</span>
									<span class="opera_span">启用</span>
								</td>
							</tr>
							<tr >
								<td>四灵</td>
								<td>1#电石炉</td>
								<td>华硕ADAM-6017</td>
								<td>8</td>
								<td>1.28.80.142:8080</td>
								<td>
									<span class="state_stop">停用</span>
								</td>
								<td>
									<span class="opera_span">启用</span>
									<span class="opera_span">启用</span>
									<span class="opera_span">启用</span>
								</td>
							</tr>
							<tr >
								<td>四灵</td>
								<td>1#电石炉</td>
								<td>华硕ADAM-6017</td>
								<td>8</td>
								<td>1.28.80.142:8080</td>
								<td>
									<span class="state_mainten">维修中</span>
								</td>
								<td>
									<span class="opera_span">启用</span>
									<span class="opera_span">启用</span>
									<span class="opera_span">启用</span>
								</td>
							</tr>
							<tr >
								<td>四灵</td>
								<td>1#电石炉</td>
								<td>华硕ADAM-6017</td>
								<td>8</td>
								<td>1.28.80.142:8080</td>
								<td>
									<span class="state_interup">网络中断</span>
								</td>
								<td>
									<span class="opera_span">启用</span>
									<span class="opera_span">启用</span>
									<span class="opera_span">启用</span>
								</td>
							</tr>
							<tr >
								<td>四灵</td>
								<td>1#电石炉</td>
								<td>华硕ADAM-6017</td>
								<td>8</td>
								<td>1.28.80.142:8080</td>
								<td>
									<span class="state_interup">网络中断</span>
								</td>
								<td>
									<span class="opera_span">启用</span>
									<span class="opera_span">启用</span>
									<span class="opera_span">启用</span>
								</td>
							</tr>
							<tr >
								<td>四灵</td>
								<td>1#电石炉</td>
								<td>华硕ADAM-6017</td>
								<td>8</td>
								<td>1.28.80.142:8080</td>
								<td>
									<span class="state_interup">网络中断</span>
								</td>
								<td>
									<span class="opera_span">启用</span>
									<span class="opera_span">启用</span>
									<span class="opera_span">启用</span>
								</td>
							</tr>
							<tr >
								<td>四灵</td>
								<td>1#电石炉</td>
								<td>华硕ADAM-6017</td>
								<td>8</td>
								<td>1.28.80.142:8080</td>
								<td>
									<span class="state_interup">网络中断</span>
								</td>
								<td>
									<span class="opera_span">启用</span>
									<span class="opera_span">启用</span>
									<span class="opera_span">启用</span>
								</td>
							</tr>
							<tr >
								<td>四灵</td>
								<td>1#电石炉</td>
								<td>华硕ADAM-6017</td>
								<td>8</td>
								<td>1.28.80.142:8080</td>
								<td>
									<span class="state_interup">网络中断</span>
								</td>
								<td>
									<span class="opera_span">启用</span>
									<span class="opera_span">启用</span>
									<span class="opera_span">启用</span>
								</td>
							</tr>
							<tr >
								<td>四灵</td>
								<td>1#电石炉</td>
								<td>华硕ADAM-6017</td>
								<td>8</td>
								<td>1.28.80.142:8080</td>
								<td>
									<span class="state_interup">网络中断</span>
								</td>
								<td>
									<span class="opera_span">启用</span>
									<span class="opera_span">启用</span>
									<span class="opera_span">启用</span>
								</td>
							</tr>
							<tr >
								<td>四灵</td>
								<td>1#电石炉</td>
								<td>华硕ADAM-6017</td>
								<td>8</td>
								<td>1.28.80.142:8080</td>
								<td>
									<span class="state_interup">网络中断</span>
								</td>
								<td>
									<span class="opera_span">启用</span>
									<span class="opera_span">启用</span>
									<span class="opera_span">启用</span>
								</td>
							</tr>
						</tbody>
					</table>
					<!--分页-->
					<img style="position: absolute;bottom: 0;" src="img/fenye.png" />
				</div>

			</div>

		<div class="foot border_top">
			<span>${applicationScope.systemBasisSet.unitInformation}   @2016</span>
		</div>
	</div>
</body>
<script src="${ctx}wh/js/safeOnline.js" type="text/javascript" charset="utf-8"></script>

</html>
