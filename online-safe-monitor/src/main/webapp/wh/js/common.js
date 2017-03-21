// 是否只有中文、英文、数字
function isChsEngNum(str){
	var patrn = /^[\u4e00-\u9fa5A-Za-z0-9]+$/;
	return patrn.test(str);
};

/**
 * 将汉字转换成ASCII码，然后在服务器端里再根据ASCII码转换成相应的字符
 * @param {Object} page_proc_name
 */
var characterToASCII = function(page_proc_name){
	var temp=page_proc_name;
	var page_prc_name="";
	//将汉字转换成ASCII码，然后在服务器端里再根据ASCII码转换成相应的字符
	for(i=0;i<temp.length;i++){
   		page_prc_name+=temp.charCodeAt(i)+" ";//将字符转换成相应的ASCII码并用空格隔开;
	}
	return page_prc_name;
}
//匹配由数字、英文字母、下划线组成
var isNumCharUnderline = function(str){
	var patrn = /^\w+$/;
	return patrn.test(str);
};
//是否由数字和英文字母组成
var isNumChar2 = function(str){
	var patrn = /^[A-Za-z0-9]+$/;
	var patrn1 =/[A-Z]/;
	var patrn2 =/[a-z]/;
	var patrn3 =/[0-9]/;
	if(patrn.test(str)){
		if(!patrn1.test(str)){
			return false;
		}
		if(!patrn2.test(str)){
			return false;
		}
		if(!patrn3.test(str)){
			return false;
		}
		return true;
	}
	return false;
};
//是否由数字和英文字母组成
var isNumChar = function(str){
        var patrn = /^[A-Za-z0-9]+$/;
        return patrn.test(str);
};
var isEmail= function(str){ 
	var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/; 
	return reg.test(str); 
};
var isOnlyChs = function(str){
	var patrn = /^[\u4e00-\u9fa5]+$/;
	return patrn.test(str);
};
/**
 * 检查是否是身份证号码
 * @param {Object} idcard
 */
var checkIdcard = function(idcard){
	if (idcard == null) {
		return false;
	}
	var Errors = new Array(true, "\u8eab\u4efd\u8bc1\u53f7\u7801\u4f4d\u6570\u4e0d\u5bf9!", "\u8eab\u4efd\u8bc1\u53f7\u7801\u51fa\u751f\u65e5\u671f\u8d85\u51fa\u8303\u56f4\u6216\u542b\u6709\u975e\u6cd5\u5b57\u7b26!", "\u8eab\u4efd\u8bc1\u53f7\u7801\u6821\u9a8c\u9519\u8bef!", "\u8eab\u4efd\u8bc1\u5730\u533a\u975e\u6cd5!");
	var area = {
		11: "\u5317\u4eac",
		12: "\u5929\u6d25",
		13: "\u6cb3\u5317",
		14: "\u5c71\u897f",
		15: "\u5185\u8499\u53e4",
		21: "\u8fbd\u5b81",
		22: "\u5409\u6797",
		23: "\u9ed1\u9f99\u6c5f",
		31: "\u4e0a\u6d77",
		32: "\u6c5f\u82cf",
		33: "\u6d59\u6c5f",
		34: "\u5b89\u5fbd",
		35: "\u798f\u5efa",
		36: "\u6c5f\u897f",
		37: "\u5c71\u4e1c",
		41: "\u6cb3\u5357",
		42: "\u6e56\u5317",
		43: "\u6e56\u5357",
		44: "\u5e7f\u4e1c",
		45: "\u5e7f\u897f",
		46: "\u6d77\u5357",
		50: "\u91cd\u5e86",
		51: "\u56db\u5ddd",
		52: "\u8d35\u5dde",
		53: "\u4e91\u5357",
		54: "\u897f\u85cf",
		61: "\u9655\u897f",
		62: "\u7518\u8083",
		63: "\u9752\u6d77",
		64: "\u5b81\u590f",
		65: "\u65b0\u7586",
		71: "\u53f0\u6e7e",
		81: "\u9999\u6e2f",
		82: "\u6fb3\u95e8",
		91: "\u56fd\u5916"
	};
	var idcard, Y, JYM;
	var S, M;
	var idcard_array = new Array();
	idcard_array = idcard.split("");
	//地区检验
	if (area[parseInt(idcard.substr(0, 2))] == null) {
		return Errors[4];
	}
	//身份号码位数及格式检验
	switch (idcard.length) {
		case 15:
			if ((parseInt(idcard.substr(6, 2)) + 1900) % 4 == 0 || ((parseInt(idcard.substr(6, 2)) + 1900) % 100 == 0 && (parseInt(idcard.substr(6, 2)) + 1900) % 4 == 0)) {
				ereg = /^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}$/;//测试出生日期的合法性
			} else {
				ereg = /^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}$/;//测试出生日期的合法性
			}
			if (ereg.test(idcard)) {
				return Errors[0];
			} else {
				return Errors[2];
			}
			break;
		case 18:
			//18位身份号码检测
			//出生日期的合法性检查
			//闰年月日:((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))
			//平年月日:((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))
			if (parseInt(idcard.substr(6, 4)) % 4 == 0 || (parseInt(idcard.substr(6, 4)) % 100 == 0 && parseInt(idcard.substr(6, 4)) % 4 == 0)) {
				ereg = /^[1-9][0-9]{5}19[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}[0-9Xx]$/;//闰年出生日期的合法性正则表达式
			} else {
				ereg = /^[1-9][0-9]{5}19[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}[0-9Xx]$/;//平年出生日期的合法性正则表达式
			}
			//测试出生日期的合法性
			if (ereg.test(idcard)) {
				//计算校验位
				S = (parseInt(idcard_array[0]) + parseInt(idcard_array[10])) * 7 + (parseInt(idcard_array[1]) + parseInt(idcard_array[11])) * 9 +
				(parseInt(idcard_array[2]) + parseInt(idcard_array[12])) * 10 +
				(parseInt(idcard_array[3]) + parseInt(idcard_array[13])) * 5 +
				(parseInt(idcard_array[4]) + parseInt(idcard_array[14])) * 8 +
				(parseInt(idcard_array[5]) + parseInt(idcard_array[15])) * 4 +
				(parseInt(idcard_array[6]) + parseInt(idcard_array[16])) * 2 +
				parseInt(idcard_array[7]) * 1 +
				parseInt(idcard_array[8]) * 6 +
				parseInt(idcard_array[9]) * 3;
				Y = S % 11;
				M = "F";
				JYM = "10X98765432";
				M = JYM.substr(Y, 1);//判断校验位
				if (M == idcard_array[17]) {
					return Errors[0]; //检测ID的校验位
				} else {
					return Errors[3];
				}
			} else {
				return Errors[2];
			}
			break;
		default:
			return Errors[1];
			break;
	}
}

//电话号码，是否是所有电话号码其中的一种: 电信、移动、 联通、 固话
function isTelMobUniNo(s){
	switch (true) {
		case this.isTelPhNo(s):// 电信
			return true;
			break;
		case this.isMbPhNo(s):// 移动
			return true;
			break;
		case this.isUniPhNo(s):// 联通
			return true;
			break;
		default:
			return false;
	}
}
//中国电信手机号
function isTelPhNo(s){
	if (!s) {
		return false;
	}
	if (s.length == 11) {
		return /^(133|153|180|189)\d*$/.test(s);
	}
	return false;
}
//中国移动手机号
function isMbPhNo(s){
	if (!s) {
		return false;
	}
	if (s.length == 11) {
		return /^(134|135|136|137|138|139|147|150|151|152|157|158|159|182|183|187|188|177)\d*$/.test(s);
	}
	return false;
}
//中国移动手机号码(支持诸位校验)
function isMbPhNoInd(s){
	if (!s) {
		return false;
	}
	var len = s.length;
	switch (true) {
		case len === 1:
			return /^(1)\d*$/.test(s);
			break;
		case len === 2:
			return /^(13|15|18|14)\d*$/.test(s);
			break;
		case len >= 3:
			return /^(134|135|136|137|138|139|150|151|152|157|158|159|187|188|147|182|183)\d*$/.test(s);
			break;
		default:
			return false;
			break;
	}
}
//中国联通手机号码
function isUniPhNo(s){
	if (!s) {
		return false;
	}
	if (s.length == 11) {
		return /^13[0-2]|15[56]|18[56]|145\d{8}$/.test(s);
	}
	return false;
}

//是否符合国内固话或者传真格式,格式例如：020-87110252
function isTelFax(s){
	if (!s) {
		return false;
	}
	return /^((0\d{2,3})-)?(\d{7,8})(-(\d{3,}))?$/.test(s);
}
//是否为email
function isEmail(s){
	if (!s) {
		return false;
	}
	return /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/.test(s);
}
//金额判断
function isMoney(s){
	if (!s) {
		return false;
	}
	// 是否金额,校验格式正整数,有效两位小数,类似：2，12.00，12，0.20
	if (this.isPosInt(s)) {
		return true;
	}
	return /^[1-9]\d+[\.]\d{0,2}$/.test(s) || /^\d[\.]\d{0,2}$/.test(s);
}

function isPosInt(s){
	// 是否正整数
	if (!s) {
		return false;
	}
	return /^[1-9](\d+)$/.test(String(s)) || /^(\d)$/.test(String(s));
}

function doing(message){
	var _info = (typeof arguments[0] === 'undefined') ? '正在处理，请稍候... ...' : arguments[0];
	jQuery.blockUI({
		message:
			'<div  class=\"modal-backdrop fade in\"></div>\
		    <div class=\"modal fade in\" style=\"display:block\">\
		       <div class=\"loadingBox loadingBox2\">\
		             <div class=\"progress\">\
		                  <div style=\"width:100%\" aria-valuemax=\"100\" aria-valuemin=\"0\" aria-valuenow=\"45\" role=\"progressbar\" class=\"progress-bar progress-bar-striped active\"><span class=\"sr-only\">45% Complete</span></div>\
		             </div>\
		             <p class=\"txt\">'+_info+'</p>\
		          </div>\
		    </div>',
		css: {
			border: 'none',
			backgroundColor: '',
			top: '50%',
			left: '50%',
			'text-align': 'left',
			'margin-left': '-586px',
			'margin-top': '-250px',
			cursor: null
		},
		fadeOut: 0,
		fadeIn: 0,
		overlayCSS: {
			backgroundColor: '',
			cursor: null
		}
	});
}

function undoing(){
	jQuery.unblockUI();
}


//fn,url不同时执行 msg显示的信息  second 多少秒后关闭  fn关闭后执行的操作  url如果fn不为函数，则跳转到url
var showdialog = function(msg,second,fn,url){
	var _info = (typeof arguments[0] === 'undefined') ? '正在处理，请稍候... ...' : arguments[0];
	var _sec = (typeof arguments[1] === 'undefined') ? 5 : arguments[1];
	var _url = (typeof arguments[3] === 'undefined') ? basePath+"reg/goReg.action" : arguments[3];
	artDialog({
	    content: _info,
	    time:_sec,
	    esc:true,
	    ok: function () {
	    	if(typeof fn==='function'){
	    		fn();
	    	}else{
	    		window.location.href=_url;
	    	}
	    },
	    close:function(){
	    	if(typeof fn==='function'){
	    		fn();
	    	}else{
	    		window.location.href=_url;
	    	}	
	    }
	});
}

