/**
 * @author REN
 */
$(document).ready(function() {
	//单击添加
	$("#addRoleModule").click(function(){
		submit_form();
		return false;
	});

	//单击复选框
	$("#moduleTable").delegate(":checkbox", "click", function(event){
		checkPrev(this);    
	   	checkSub(this);
	   	notCheckPrev(this);
		event.stopPropagation();
    });
});
/**
 * 选中祖先复选框
 */
function checkPrev(element){
    if(!element){
        return;
    }
	var isChecked=$(element).prop("checked");
	if(isChecked){
        var listCheckbox=$(element).parents("ul").parent().find(":checkbox:eq(0)");
        //选中父元素
        listCheckbox.prop("checked", true);
	}
}
/**
 * 后代复选框全部取消勾选时，取消勾选祖复选框
 * @param element
 */
function notCheckPrev(element){
	var listCheckbox = $(element).parent().parent().find(":checkbox");
	var allNotCheck = true;
	for(var i=0;i<listCheckbox.length;i++){
		var isCheck = $(listCheckbox).eq(i).prop("checked");
		if(isCheck){
			allNotCheck = false;
		}
	}
	var provCheckbox=$(element).parents("ul").parent().find(":checkbox:eq(0)");
	if(allNotCheck){
		provCheckbox.prop("checked",false);
	}
	
}
/**
 * 选中后代复选框
 */
function checkSub(element){
    if(!element){
        return;
    }
    var isChecked=$(element).prop("checked");
    var listCheckbox = null;
    if($(element).attr("id") == "oneCheckbox"){
    	listCheckbox=$(element).closest("td").find(":checkbox");
    }else{
    	listCheckbox=$(element).closest("li").find(":checkbox");
    }
	if(isChecked){
		listCheckbox.prop("checked", function( i, val ) {
			if (!$(this).prop("disabled")) {
				return true;
			}
        });
	}else{
		listCheckbox.prop("checked", false);
	}
}
/**
 * 提交信息
 * @return
 */
function submit_form() {
	//得到角色id
	var roleId=$("#roleId").val();
	if(!roleId){
		alert(roleId);
		return;
	}
	var moduleIds="";
	//得到所有选择模版
    var selectedCheckBox=$("#moduleTable").find(":checkbox");
    selectedCheckBox.each(function(i){
		 if($(this).prop("checked")){
		 	 moduleIds += '&moduleIds='+$(this).attr("value"); 
		 }
    });	
	$.ajax({
		url : basePath+"authority/addRoleModule.action",
		type : "post",
		dataType :'text',
		data:moduleIds+"&roleId="+roleId,
		success : function(data) {

			art.dialog({
				   title:"消息",
				   content:"修改权限成功。",
				   width : 317,
				   height : 109,
				   icon:"succeed",
				   opacity:0.3,
				   ok:function(){},
				   close:function(){

				   }
				});
	   }
	   });
}