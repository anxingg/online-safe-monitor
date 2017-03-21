<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>人员添加</title>
    <jsp:include page="../../common/flatHead.jsp"/>
    <link href="${ctx }flat/css/reset.css" rel="stylesheet" type="text/css" />
    <link href="${ctx }flat/plugins/form/skins/form_default.css" rel="stylesheet" type="text/css" />
	<link href="${ctx }flat/plugins/datatable/skins/datatable_default.css" rel="stylesheet" type="text/css" />
		<style>
			img{cursor:pointer;}
		</style>
	<link href="${ctx }flat/plugins/annex/skins/annex_default.css" rel="stylesheet" type="text/css" />
	<link href="${ctx }flat/plugins/tree/skins/tree_default.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${ctx}js/user/selectuser.js"></script>
    <script type="text/javascript" src="${ctx}js/common/hashmap.js"></script>
    <script type="text/javascript" src="${ctx}js/common/treeNode.js"></script>
	<script type="text/javascript">
	$(document).ready(function(){
	   $(".myphoto dt").hover(function() {
			$(this).find(".close").fadeToggle();
		});
	});
	</script>
</head>
<body>
<input type="hidden" id="moduleClassName" value="<%=request.getParameter("moduleClassName") %>"/>
<div class="formPage">
  <div class="formbg">
    <div class="big_title">测试数据权限</div>
     
    <div class="content_form">
      <table width="100%" border="0" cellpadding="0" cellspacing="0"  class="inputTable">
		<tr>
		<th><label>选择人员：</label></th>
			<td  id="target" ><input type="hidden" id="selectedTargetId" /><input type="text" id="selectedTargetName" /><a class="icon_add" href="#" id="addTarget">添加</a>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</td>		
		</tr>
		<tr id="showInfo" style="display:none"><th ><label>人员信息：</label></th>
		<td>部门：<span id="showGroup"></span><br/>角色：<span id="showRoles"></span><br/>分支机构：<span id="showFork"></span></td>
		</tr>
		<tr>
			<th></th>
			<td><a class="icon_add" href="#" id="test">点击测试</a></td>
		</tr>
        <tr>
          <th><label>测试结果：</label></th>
           <td ><textarea class="formTextarea" id="condition" style="width:500px"></textarea></td>
        </tr>
        
      </table>
    </div>
  </div>
</div>
<script type="text/javascript">
	$("#addTarget").click(function(){
		openSelectUser(3,function(data){
			 data.forEach(function(value, key) {
               if(value.Type == "user"){
	               $("#selectedTargetId").val(value.Id);
	               $("#selectedTargetName").val(value.Name);
	               $("#showInfo").show();
					$.get(basePath+"/datafilter/getUserInfo.action?userId="+$("#selectedTargetId").val(),function(data){
						$("#showGroup").html(data.groupInfo);
						$("#showRoles").html(data.roles);
						$("#showFork").html(data.forkGroup);
					},"json");
               }
            });
		},null,$("#selectedTargetId").val());
		
	});
	$("#test").click(function(){
		$.post("${ctx}/datafilter/test.action?userId="+$("#selectedTargetId").val()+"&moduleClassName="+$("#moduleClassName").val(),function(data){
			$("#condition").val(data);
		});
	});	
</script>
</body>
</html>