<%@page pageEncoding="UTF-8"%> 
<div class="logo">
	<img src="../img/logo.png"/>
	<span>${applicationScope.systemBasisSet.backstageName}</span>
</div>
<div class="headMenu">
	<span class="username">欢迎  ${session.adminUser.userName} 登陆系统</span>
	<ul>
		<a href="${ctx}wh/logined/osmIndex.jsp" >
			<li class="headMenu_liActive" id="menu_home">
				<img src="../img/home.png"/>
				<p>首页</p>
			</li>
		</a>
		<a href="${ctx}wh/logined/osmWelcome.jsp">
			<li id="menu_systemmanage">
				<img src="../img/set.png"/>
				<p>系统管理</p>
			</li>
		</a>
		<a href="###">
			<li id="menu_person">
				<img src="../img/person.png"/>
				<p>个人中心</p>
			</li>
		</a>
		<a href="javascript:void(0);" onclick="exit();">
			<li id="menu_logout">
				<img src="../img/exit.png"/>
				<p>安全退出</p>
			</li>
		</a>
	</ul>
</div>