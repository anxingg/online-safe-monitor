/**
 * 验证空值
 * @param id 验证的ID
 * @param errorMsg 错误提示语
 * @param nopassvalue 错误的值
 * @returns {Boolean} true 验证通过，false 验证不通过
 */
function verifyEmptyContent(id, errorMsg, nopassvalue){
	var pass=true;
	var obj=$("#"+id);
	var val=obj.val();
	if(val==nopassvalue){
		if(!obj.hasClass("inputError") ){
			obj.addClass("inputError");
			obj.after('<p class="requireField">'+errorMsg+'</p>');
			pass=false;
		}else{
			pass=false;
		}
	}else{
		obj.removeClass("inputError");
		obj.next('p').remove();
		pass=true;
	}
	obj.change(function(){
		if(obj.hasClass("inputError") ){
			obj.removeClass("inputError");
			obj.next('p').remove();
		}
	});
	return pass;
}