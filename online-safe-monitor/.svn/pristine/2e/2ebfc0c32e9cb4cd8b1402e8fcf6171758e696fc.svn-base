//var _productList = new Array(20);
//var _index = 0;

$(document).ready(
        function() {

	        if ($("#fromPage").val() != 1) {
		        $("#showTableCall").show();
		        $("#relateCustomerCallLog").show();
		        $("#relateCustomerCallLogTable").show();
	        }
	        var callPhone = $("#callPhone").val();
	        /*if (null != callPhone) {
		        if (callPhone.substr(0, 1) == 0) {
			        $("#umobile").val(callPhone);
		        } else {
			        $("#uphone").val(callPhone);
		        }
	        }*/

//	        $("#tabContentTwo").css("display", "none");
	        var cclId = $("#cclId").val();
//	        alert("============"+cclId);
	        if (cclId == 'undefined' || cclId == '' || typeof (cclId) == 'undefined') {	
		        getProductByPhone();
	        } else {
//		        getProductListById();
	        }

        });

/**
 * 根据来电号码得到用户档案信息
 */
function getProductByPhone() {
	/**
	 * 
	 * 标识id
	 */
	var phone = $("#uphone").val();//主叫手机号码
	//测试用
//	var phone="18615543067";
//	var phone="13523550628";
	if (null == phone || "" == phone){
//		phone = "-1";
		//如果用户的来电手机号为空，则获取其座机号
		phone=$("#umobile").val();// 主叫座机好
	}
	

	var paramData = {
		'phone' : phone

	};

	$
	        .ajax({
	            url : basePath + "customercalllog/getProductByPhone.action",
	            type : "post",
	            dataType : 'json',
	            data : paramData,
	            success : function(data) {

//		             alert(data);//判断data，不为空时赋值
	        	if(data!==null){
		            $("#uname").val(data.uname);
//		            $("#uphone").val(data.uphone);//手机号码，坐席绑定的手机号
//		            $("#umobile").val(data.umobile);//座机号码
		            var usex=data.usex;//用户性别
//		            alert(usex);
		            //将用户的性别填充
		            
		            var count=$("#gender option").length;
		            for(var i=0;i<count;i++){
		            	
//		            	alert("9999  "+$("#gender").get(0).options[i].value);
		            	if($("#gender").get(0).options[i].value==usex){
		            		$("#gender").get(0).options[i].selected = true;
		            		break;
		            	}
		            }
		            $("#uaddress").val(data.uaddress);//用户地址
	        	}
		         /*   if (typeof (data.uphone) == 'undefined') {
			            var callPhone = $("#callPhone").val();
			            if (callPhone.substr(0, 1) != 0) {
				            $("#uphone").val(callPhone);
			            }
		            }
		            if (typeof (data.umobile) == 'undefined') {
			            var callPhone = $("#callPhone").val();
			            if (callPhone.substr(0, 1) == 0) {
				            $("#umobile").val(callPhone);
			            }
		            }*/

		            
//		            $("#levelTypeValue").val(data.userLevel);

//		            $("#levelType").val(data.userLevel);
//		            levelType();// 用户类别
//		            if (data.haveOldProduct == 2) {
//			            $("#selectRepair").show();
//			            $("#showProduct").show();
//			            $("#nowRepair").show();
//			            $("#showRepair").show();
//			            $("#showTiJiaoButton").show();
//			            if (data.mapList.length > 0 && data.mapList.length != null && data.mapList.length != "") {
//				            for ( var i = 0; i < data.mapList.length; i++) {
//
//					            $("#selectRepair").append(
//					                    "<option value='" + data.mapList[i].id + "'>"
//					                            + data.mapList[i].productModelName + "&nbsp;&nbsp"
//					                            + data.mapList[i].hostID + "</option>");
//					            if (i == 0) {
//						            getProductById(data.mapList[i].id, 1);
//					            }
//
//				            }
//				            /*if ($("#productVid").val() != "") {
//					            $("#selectRepair").append(
//					                    "<option value='" + $("#productVid").val() + "'>" + $("#productM").val()
//					                            + "</option>");
//				            }*/
//			            }
//
//		            } else // 如果没有任何记录则只显示添加按钮
//		            {
//			            $("#inputResult").hide();
//			            $("#repairResultTitle").hide();
//			            $("#repairResultCaller").hide();
//			            $("#showRepairInputMsgTitle").hide();
//			            $("#showRepairInputMsg").hide();
//		            }

	            }

	        });

}

