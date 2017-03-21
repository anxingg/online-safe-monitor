/**
 * 验证格式
 */
function checkFileFormat() {
	var fileName = $("#fileToUpload").val();
	if (fileName == "") {
		$("#msg").html('请选择要验证的文件！');
		return false;
	}else{
		var rex = /.xls$/gi;
		if(!rex.test(fileName)){
			$("#msg").html('请选择电子表格！');
			return false;
		}
	}
	var radioType = 1;
	var url = basePath + 'phoneTask/checkBeforeImporting.action';
	$("#msg").html('');
	$.ajaxFileUpload({
				url : url,
				secureuri : false,
				fileElementId : 'fileToUpload',
				dataType : 'text',
				data : {
					uploadFileName : fileName,
					radioType : radioType
				},
				success : function(data, status) {
					if (data == "") {
						$("#msg").html("验证通过,可以导入！");

					} else {
						$("#msg").html(data);
					}
				},
				error : function(data, status, e) {

					$("#msg").html("对不起！验证文件时出错！");
				}
			});
	return false;

}

/**
 * 开始上传
 * 
 * @return {Boolean}
 */
function startAjaxFileUpload() {
	var fileName = $("#fileToUpload").val();
	if (fileName == "") {
		$("#msg").html('请选择要导入的文件！');
		return false;
	}else{
		var rex = /.xls$/gi;
		if(!rex.test(fileName)){
			$("#msg").html('请选择电子表格！');
			return false;
		}
	}
	var radioType = $("input:radio[name='radioType']:checked").val();
	var url = basePath + 'phoneTask/importPhoneTaskUser.action';
	//alert(url);
	$("#msg").html('<span class="gray_9 ml20 mr10">正在导入</span><img src="'+basePath+'images/jindu.gif" />');
	$.ajaxFileUpload({
				url : url,
				secureuri : false,
				fileElementId : 'fileToUpload',
				dataType : 'text', // 这里只能写成text，不能写成json。否则ajaxfileupload.js中103行会抛异常。不知道为什么。
				data : {
					uploadFileName : fileName
//					radioType : radioType
				},
				success : function(data, status) {
					//alert(data+"  "+status);
					var json = eval("("+data+")");
					$("#msg").html(json.result);
					art.dialog.data("successList",json.successList);
//					$("#page").attr("src",
//							basePath + 'logined/user/userList.jsp');
				},
				error : function(data, status, e) {
					//alert(data+"  "+status+"  "+e);
					$("#msg").html("对不起！导入文件时出错！");
				}
			});

	return false;

}