/**
 * 初始化一个数据字典（请选择的值默认是0）
 * @param type 类型
 * @param id 页面中的SelectID
 * @param selectvalue 默认选中值
 * @param noselect 请选择的值
 */
function initDictType(type, id, selectvalue, noselect) {
	if(!noselect){
		noselect = 0;
	}
	var paramData = {
		'infoType' : type,
		'sysTag' : 1
	};
	qytx.app.ajax({
		url : basePath + "dict/getDicts.action",
		type : "post",
		dataType : "text",
		data : paramData,
		success : function(data) {
			//data是一个数组
			var jsonData = eval('(' + data + ')');
			$("#"+id).empty();
			$("#"+id).append('<option value="'+noselect+'">请选择</option>');
			for (var i = 0; i < jsonData.length; i++) {
				if(selectvalue && selectvalue == jsonData[i].value){
					$("#"+id).append('<option selected="selected" value="' + jsonData[i].value + '">' + jsonData[i].name + '</option>');
				}else {
					$("#"+id).append('<option value="' + jsonData[i].value + '">' + jsonData[i].name + '</option>');
				}
			}
		}
	});
}



/**
 * 加载企业下拉列表
 */
function getSelectCompany(){
	$.ajax({
		url : basePath + "companywh/getCompanyNameList.action",
		type : "post",
		dataType : 'json',
		data : {
		},
		success : function(data) {
			if (data != null && data!="") {
				var list = eval(data);
				var html = '';
				for (var i = 0; i < list.length; i++) {
					var companyName = list[i].companyName;
					var groupId = list[i].groupId;
					html += '<option value="'+groupId+'">'+companyName+'</option>';
				}
				$("#companName").html($("#companName").html()+html);
			}
		}
	});
}