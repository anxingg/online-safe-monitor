var loginIsForkGroup=null;
$(document).ready(function() {
	loginIsForkGroup = $("#loginIsForkGroup").val();
});

function getLoginIsFG(){
	return this.loginIsForkGroup;
}