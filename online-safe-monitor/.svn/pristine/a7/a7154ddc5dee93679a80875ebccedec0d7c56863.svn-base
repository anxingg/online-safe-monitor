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
<input type="hidden" name="moduleClassName" value="<%=request.getParameter("moduleClassName") %>"/>
<div class="formPage">
  <div class="formbg">
    <div class="big_title">新增权限</div>
     
    <div class="content_form">
      <table width="100%" border="0" cellpadding="0" cellspacing="0"  class="inputTable">
        <tr>
          <th><label>范围：</label></th>
          <td><label class="radio"><input type="radio" value="all" name="relation"   />全部人员</label>
          	  <label class="radio"><input type="radio" value="user" name="relation"   />按人员</label>
          	  <label class="radio"><input type="radio" value="group" name="relation"  />按部门</label>
          	  <label class="radio"><input type="radio" value="role" name="relation"  />按角色</label>
          	  <label class="radio"><input type="radio" value="forkGroup" name="relation"  />按分支机构</label></td>
        </tr>
		
		<tr>
		<th></th>
			<td  id="target" style="display:none"><input type="hidden" id="selectedTargetId" /><input type="text" id="selectedTargetName" /><a class="icon_add" href="#" id="addTarget">添加</a></td>		
		</tr>
		
        <tr>
          <th><label>数据权限：</label></th>
           <td ><textarea class="formTextarea" id="condition" style="width:500px"></textarea></td>
        </tr>
        <tr>
          <th><label>描述：</label></th>
           <td ><textarea class="formTextarea" id="configName" style="width:500px"></textarea></td>
        </tr>
      </table>
    </div>
  </div>
</div>
<script type="text/javascript">
	$("input[name='relation']").click(function(){
		var v = $(this).val();
		if(v=="all"){
			$("#target").hide();
			clean();
		}else if(v =="user"){
			$("#target").show();
			clean();
			$("#addTarget").click(function(){
				openSelectUser(3,function(data){
					 data.forEach(function(value, key) {
		               if(value.Type == "user"){
			               $("#selectedTargetId").val(value.Id);
			               $("#selectedTargetName").val(value.Name);
		               }
		            });
				},null,$("#selectedTargetId").val());
			});
		}else if(v=="group"){
			$("#target").show();
			clean();
			$("#addTarget").click(function(){
				openSelectUser(1,function(data){
					data.forEach(function(value, key) {
		               if((value.Type == "group") && (value.Id!=0)){
		            	   $("#selectedTargetId").val(value.Id);
			               $("#selectedTargetName").val(value.Name);
		               }
		            });
				},null,$("#selectedTargetId").val());
			});
		}else if(v=="role"){
			$("#target").show();
			clean();
			$("#addTarget").click(function(){
				openSelectUser(2,function(data){
					data.forEach(function(value, key) {
		               if(value.Type == "role"){
		            	   $("#selectedTargetId").val(value.Id);
			               $("#selectedTargetName").val(value.Name);
		               }
		            });
				},null,$("#selectedTargetId").val());
			});
		}else if(v=="forkGroup"){
			$("#target").show();
			clean();
			$("#addTarget").click(function(){
				openSelectUser(8,function(data){
					data.forEach(function(value, key) {
		               if(value.Type == "group" && (value.Id!=0)){
		            	   $("#selectedTargetId").val(value.Id);
			               $("#selectedTargetName").val(value.Name);
		               }
		            });
				},null,$("#selectedTargetId").val());
			});
		}
	});
	
	function clean(){
		$("#selectedTargetName").val("");
		$("#selectedTargetId").val("");
		$("#addTarget").unbind();
	}
	
	function getData(){
		var relationType = $("input[type='radio']:checked").val();
		var id = $("#selectedTargetId").val();
		var condition = $("#condition").val();
		if(relationType=="all"){
			id="all";
		}
		if(!relationType || !id || !condition){
			return null;
		}else{
			return {
				"relationType":relationType,
				"id":id,
				"condition":condition,
				"configName":$("#configName").val()
			}			
		}
	}
</script>
</body>
</html>