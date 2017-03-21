function addPhone() {
	var aphone = $("#phone_Add").val();
	if(!checkNum($("#phone_Add"),aphone,"localAddPhone.phone_not_right")){
		return false;
	}
	var valid=validator($("#menu_phoneadd")[0]);
	if(valid){
		if(aphone == '' || aphone==null){
			showObjError($("#phone_Add"), 'localAddPhone.phone_not_null');
			return ;
		}else{
			hideError($("#phone_Add"));
		}
		var paramData={
			    'addphone': aphone
		}; 
	    $.ajax({
	        url : basePath+"outcall/phoneAdd.action",
	        type : "post",
	        dataType :'json',
	        data:paramData,
	        success : function(data) {
	            if(data==1){  
	            	var getList = art.dialog.data("getList");
	            	getList();
	            	art.dialog.close();
			    }else if(data==2) {
			    	art.dialog.alert("您添加的号段已经存在！");
			    }else{
	           		art.dialog.alert("号段添加失败！");
	            }
	        }
	    });
	}
}

function checkNum(obj,value,message){
	if(value!=null&&value!=""){
		var isFloat = /\D/;
		if(value.match(isFloat)){
			showObjError(obj,message);
			return false;
		}else{
			hideError(obj);
			return true;
		}
	}else{
		return true;
	}
}