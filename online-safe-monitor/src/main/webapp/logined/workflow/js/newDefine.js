jQuery(document).ready(function($){

	//加载下拉树的数据
	// var state = $("#processState").val();
	
	/*if(!$("#xmlDefine").val()){
		$.selectTree(basePath+"workflow/process!getFormCategoryTree.action?r="+Math.random(),"formCategory","menuContent","treeDemo");
	}*/


	//取消新增
	$("#cancel").click(function(){
	//	var tabName = $("#tabName").val();
	//	window.parent.parent.closeTab(tabName);
		window.location.href=basePath+"workflow/process!editProcess.action?processAttributeId="+ $("#pid").val();
	});
 
	//取消修改
	$("#cancelUpdate").click(function(){
		//window.location.href=basePath+"workflow/process!editProcess.action?processAttributeId="+ $("#pid").val();
		window.history.back();
	})

	//预览
	$("#prepView").click(function(){
		var formId = $("#formCategory_hidden").val();
		if(!formId){
			art.dialog.alert("请选择表单!");
			return ;
		}
		art.dialog.open(basePath+"workflowForm/viewForm.action?formId="+formId,{
			title:"预览表单",
			width:"100%",
			height:"100%",
			ok:true,
			lock : true,
		    opacity: 0.3
		});
	});

	$("#sure").click(function(){
		// editor.sync();
		var max=$("#directions").attr("maxlength");
		if($("#directions").val().length>max){
			art.dialog.alert("流程说明不能超过"+max+"个字符！");			
			return null;
		}
		
		if(!validator(document.getElementById("newForm"))){
			return;
		}
		if($("#categoryIdSelect").val() == "-1"){
			showObjError($("#categoryIdSelect"), 'process.process_type_not_null');
			return null;
		}
		var pid = "";
		if($("#pid").val()){
			pid = $("#pid").val();
		}
		$.post(basePath+"/workflow/process!checkProcessNameIsRepeat.action?processName="+encodeURI($("#processName").val())+"&processAttributeId="+pid,
		  function(result){
			 if(result == "success"){
				showObjError($("#processName"), 'process.process_name_not_repate'); 
				//art.dialog.alert("流程名称不能重复！");
				$("#processName").val("");
			  }else if(result == "false"){
				$("form").submit();
			}
		});
	});

});
function checklength(obj) {
   var max = obj.maxlength;
   if(max == null || max == "" || max == undefined) {
     return;
   }
   if(obj.value.length > max) {
    art.dialog.alert("流程说明不能超过"+max+"个字符！");
 //   obj.value=obj.value.substring(0,max);
     return;
   }
 }
