jQuery(document).ready(function(){

	//添加人员
	$("#addUser").click(function(){
		openSelectUser(3,function(data){
			 $("#users").val("");
			 $("#userIds").val("");
			 data.forEach(function(value, key) {
               if(value.Type == "user"){
	               $("#users").append(value.Name+",");
	               var temp = $("#userIds").val();
	               temp+=value.Id+",";
	               $("#userIds").val(temp);
               }
            });
		},null,$("#userIds").val());
	});
	$("#removeUser").click(function(){
		$("#users").html("");
		$("#userIds").val("");
	});

	//添加部门
	$("#addGroup").click(function(){
		openSelectUser(1,function(data){
			$("#groups").html("");
			$("#groupIds").val("");
			data.forEach(function(value, key) {
               if(value.Type == "group"){
	               $("#groups").append(value.Name+",");
	               var temp = $("#groupIds").val();
	               temp+=value.Id+",";
	               $("#groupIds").val(temp);
               }
            });
		},null,$("#groupIds").val());
	});

	$("#removeGroup").click(function(){
		$("#groups").html("");
		$("#groupIds").val("");
	});

	//添加角色
	$("#addRole").click(function(){
		openSelectUser(2,function(data){
			$("#roles").html("");
			$("#roleIds").val("");
			data.forEach(function(value, key) {
               if(value.Type == "role"){
	               $("#roles").append(value.Name+",");
	               var temp = $("#roleIds").val();
	               temp+=value.Id+",";
	               $("#roleIds").val(temp);
               }
            });
		},null,$("#roleIds").val());
	});

	$("#removeRole").click(function(){
		$("#roles").html("");
		$("#roleIds").val("");
	});

});