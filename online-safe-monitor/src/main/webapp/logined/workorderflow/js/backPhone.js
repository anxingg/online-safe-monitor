jQuery(document).ready(function() {

});

// 回访的电话
function callCustomer() {

	// showAllBackCallerInput();

	var callPhoneTwo = "";
	var vid = $("#vid").val();// 工单的id

	callPhoneTwo = $("#callPhoneTwo").html(); // 维修人员电话

	if (callPhoneTwo == null || callPhoneTwo == "" || callPhoneTwo == "-") {
		art.dialog.alert("号码不存在，不能回访！");
		return;
	} else {

		window.top.outCall(callPhoneTwo, null, vid);

	}

}
