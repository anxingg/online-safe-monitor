jQuery(document).ready(function($){

	//设置富文本编辑框
	// KindEditor.ready(function(K) {
	// 		editor = K.create('#directions', {
	// 			resizeType : 1,
	// 			allowPreviewEmoticons : false,
	// 			allowImageUpload : false,
	//             uploadJson : basePath+'plugins/kindeditor/upload_json.jsp',
	// 			items : [
	// 				'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
	// 				'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
	// 				'insertunorderedlist', '|', 'emoticons', 'link']
	// 		});
	//       });

	//取消复制，即删除该对象
	$("#cancelCopy").click(function(){
		var processId = $("#processAttributeId").val();
		$.get(basePath+"/workflow/manager!deleteProcess.action?processAttributeId="+processId,function(result){
			if(result == "success"){
				window.parent.parent.closeTab("copyProcess");
			}
		});
	});

	//预览
	$("#prepView").click(function(){
		var formId = $("#formCategory_hidden").val();
		
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
		if($("#directions").val().length>500){
			art.dialog.alert("流程说明不能超过500个字符！");
			return null;
		}
		if(!validator(document.getElementById("newForm"))){
			return;
		}
		var pid = "";
		if($("#pid").val()){
			pid = $("#pid").val();
		}
		$.post(basePath+"/workflow/manager!checkProcessNameIsRepeat.action?processName="+encodeURI($("#pname").val())+"&processAttributeId="+pid,function(result){
			if(result == "success"){
				art.dialog.alert("流程名称不能重复!");
				$("#pname").val("");
			}else if(result == "fail"){
				var processName = $("#pname").val();
				var oldJson = $("#jsonJpdl").val();
				if(oldJson){
					var newObj = eval("("+oldJson+")");
					newObj.props.props.name.value=processName;
					var newJson = JSON.stringify(newObj);
					$("#jsonJpdl").val(newJson);	
				}
				$("form").submit();
			}
		});
	});

});