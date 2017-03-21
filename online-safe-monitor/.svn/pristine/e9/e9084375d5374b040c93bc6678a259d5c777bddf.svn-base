// 实现replaceALL效果
String.prototype.replaceAll = function(s1, s2) {
	return this.replace(new RegExp(s1, "gm"), s2);
};

function checkTextarea(inputObj) {
	var length = inputObj.getAttribute('maxNumber');
	
	// 获取当前已经输入的字符数
	var realLength = inputObj.value.replaceAll("\r\n", "").length;
    
	if (realLength <= length){
		$(inputObj).siblings("span").html('还可以输入'+(length-realLength)+'字');
	}else if(realLength>length){
		$(inputObj).siblings("span").html('已超过<font class="red">'+(realLength-length)+'</font>字');
//		$(inputObj).siblings("span").html('<span style="color:red">已超过<font class="red">'+(realLength-length)+'</font>字</span>');
	}
}

var FormValid = function(frm) {
    this.frm = frm;
    this.errMsg = new Array();
	this.errName = new Array();

    this.textareaLength = function(inputObj) {
    	var length = inputObj.getAttribute('maxNumber');
    	var realLength = inputObj.value.replaceAll("\r\n", "").length;
        if (realLength>length) {
            return false;
        }
		return true;
    };
	
    this.required = function(inputObj) {
        if (typeof(inputObj) == "undefined" || inputObj.value.trim() == "") {
            return false;
        }
		return true;
    };

    this.eqaul = function(inputObj, formElements) {
		var fstObj = inputObj;
		var sndObj = formElements[inputObj.getAttribute('eqaulName')];

        if (fstObj != null && sndObj != null) {
            if (fstObj.value != sndObj.value) {
               return false;
            }
        }
		return true;
    };

    this.gt = function(inputObj, formElements) {
		var fstObj = inputObj;
		var sndObj = formElements[inputObj.getAttribute('eqaulName')];

        if (fstObj != null && sndObj != null && fstObj.value.trim()!='' && sndObj.value.trim()!='') {
            if (fstObj.value <= sndObj.value) {
                 return false;
            }
        }
		return true;
    };
    //objectName 判断与这个对象的值是否相同
	this.compare = function(inputObj, formElements) {
		var fstObj = inputObj;
		var sndObj = formElements[inputObj.getAttribute('objectName')];
        if (fstObj != null && sndObj != null && fstObj.value.trim()!='' && sndObj.value.trim()!='') {
            if (!eval('fstObj.value' + inputObj.getAttribute('operate') + 'sndObj.value')) {
                 return false;
            }
        }
		return true;
	};
	//判断文本的长度范围
	this.limit = function (inputObj) {
		var len = inputObj.value.length;
		if (len) {
			var minv = inputObj.getAttribute('min');
			var maxv = inputObj.getAttribute('max');
			minv = minv || 0;
			maxv = maxv || Number.MAX_VALUE;
			return minv <= len && len <= maxv;
		}
		return true;
	};


	//int值范围
	this.range = function (inputObj) {
		var val = parseInt(inputObj.value);
		if (inputObj.value) {
			var minv = inputObj.getAttribute('min');
			var maxv = inputObj.getAttribute('max');
			minv = minv || 0;
			maxv = maxv || Number.MAX_VALUE;

			return minv <= val && val <= maxv;
		}
		return true;
	};

	this.requireChecked = function (inputObj) {
		var minv = inputObj.getAttribute('min');
		var maxv = inputObj.getAttribute('max');
		minv = minv || 1;
		maxv = maxv || Number.MAX_VALUE;

		var checked = 0;
		var groups = document.getElementsByName(inputObj.name);

		for(var i=0;i<groups.length;i++) {
			if(groups[i].checked) checked++;

		}
		return minv <= checked && checked <= maxv;
	};

	//自定义过滤器
	this.filter = function (inputObj) {
		var value = inputObj.value;
		var allow = inputObj.getAttribute('allow');
		if (value.trim()) {
			return new RegExp("^.+\.(?=EXT)(EXT)$".replace(/EXT/g, allow.split(/\s*,\s*/).join("|")), "gi").test(value);
		}
		return true;
	};

	//不能填的内容
	this.isNo = function (inputObj) {
		var value = inputObj.value;
		var noValue = inputObj.getAttribute('noValue');
		return value!=noValue;
	};
    this.checkReg = function(inputObj, reg, msg) {
        inputObj.value = inputObj.value.trim();

        if (inputObj.value == '') {
            return;
        } else {
            if (!reg.test(inputObj.value)) {
				this.addErrorMsg(inputObj,msg);
			}
        }
    };


    this.passed = function() {
        if (this.errMsg.length > 0) {
            FormValid.showError(this.errMsg,this.errName);
             if(this.errName[0]==""){

             }else{
                //frt = document.getElementsByName(this.errName[0])[0];
            	 //frt =  errName[0];
			   // if (frt.type!='radio' && frt.type!='checkbox') {
				//frt.focus();
			  // }
             }
            return false;
        } else {
          return true;
        }
    };


    this.addErrorMsg = function(name,str) {
        this.errMsg.push(str);
		this.errName.push(name);
    };

    this.addAllName = function(name) {
		FormValid.allName.push(name);
    };

};
FormValid.allName = new Array();
FormValid.showError = function(errMsg) {
	var msg = "";
	for (var i = 0; i < errMsg.length; i++) {
		msg += "- " + errMsg[i] + "\n";
	}
};
function validator(frm) {
	var formElements = frm.elements;
	var fv = new FormValid(frm);

	for (var i=0; i<formElements.length;i++) {
		if ($(formElements[i]).html().indexOf("<param name") >= 0){
			continue;
		}
		var validType = formElements[i].getAttribute('valid');
		var errorMsg = formElements[i].getAttribute('errmsg');
		if (validType==null) continue;
		fv.addAllName(formElements[i].id);

		var vts = validType.split('|');
		var ems = errorMsg.split('|');
		for (var j=0; j<vts.length; j++) {
			var curValidType = vts[j];
			var curErrorMsg = ems[j];

			switch (curValidType) {
			case 'isNumber':
			case 'isEmail':
			case 'isPhone':
            case 'isPhoneSimple':
			case 'isMobile':
			case 'isIdCard':
			case 'isMoney':
			case 'isZip':
			case 'isQQ':
			case 'isInt':
			case 'isEnglish':
            case 'isEnglishName':
			case 'isChinese':
			case 'isUrl':
			case 'isDate':
			case 'isNaturalInt':
			case 'isNaturalNumber':
			case 'isTime':
			case 'isIp':
			case 'isLoginName':
			case 'isIntegerOrFloat':
			case 'isPositiveIntegerOrFloat':
				fv.checkReg(formElements[i],RegExps[curValidType],curErrorMsg);
				break;
			case 'regexp':
				fv.checkReg(formElements[i],new RegExp(formElements[i].getAttribute('regexp'),"g"),curErrorMsg);
				break;
			case 'custom':
				if (!eval(formElements[i].getAttribute('custom')+'(formElements[i],formElements)')) {
					fv.addErrorMsg(formElements[i],curErrorMsg);
				}
				break;
			default :
				if (!eval('fv.'+curValidType+'(formElements[i],formElements)')) {
					fv.addErrorMsg(formElements[i],curErrorMsg);
				}
				break;
			}
		}
	}
	return fv.passed();
}
String.prototype.trim = function() {
	return this.replace(/^\s*|\s*$/g, "");
};
var RegExps = function(){};
RegExps.isEmail = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
RegExps.isPhone = /(^(\d{2,4}[-_－—]?)?\d{3,8}([-_－—]?\d{3,8})?([-_－—]?\d{1,7})?$)|(^0?1[35]\d{9}$)/;
RegExps.isPhoneSimple=/^\d{7,12}$/;
RegExps.isMobile = /^(13|15|18)\d{9}$/;

RegExps.isNaturalNumber = /^\d+(\.\d+)?$/;//非负浮点数
RegExps.isNumber = /^[-\+]?\d+(\.\d+)?$/;
RegExps.isIntegerOrFloat = /^((-(?=0\.))?0|-?[1-9][0-9]{0,7})(\.[0-9]{1,2})?$/;//正负整数、正负浮点数(8位整数，2位小数)
RegExps.isPositiveIntegerOrFloat = /^(0|[1-9][0-9]{0,7})(\.[0-9]{1,2})?$/;//正整数、正浮点数(8位整数，2位小数)
RegExps.isIdCard = /(^\d{15}$)|(^\d{17}[0-9Xx]$)/;
RegExps.isMoney = /^\d+(\.\d+)?$/;
RegExps.isZip = /^[1-9]\d{5}$/;
RegExps.isQQ = /^[1-9]\d{4,10}$/;
RegExps.isInt = /^[-\+]?\d+$/;
RegExps.isEnglish = /^[A-Za-z]+$/;
RegExps.isEnglishName=/^[\sa-zA-Z]+$/;
RegExps.isChinese =  /^[\u0391-\uFFE5]+$/;
RegExps.isUrl = /^http:\/\/[A-Za-z0-9]+\.[A-Za-z0-9]+[\/=\?%\-&_~`@[\]\':+!]*([^<>\"\"])*$/;
RegExps.isDate = /^\d{4}-\d{1,2}-\d{1,2}$/;
RegExps.isTime = /^\d{4}-\d{1,2}-\d{1,2}\s\d{1,2}:\d{1,2}:\d{1,2}$/;
RegExps.isNaturalInt=/^[0-9]*[1-9][0-9]*$/;  //非负整数（正整数   +   0）;
RegExps.isLoginName=/^^[0-9a-zA-Z_]+$/;
RegExps.isIp=/^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/; //验证IP地址

function addLoadEvent(func) {
  var oldonload = window.onload;
  if (typeof window.onload != 'function') {
    window.onload = func;
  } else {
    window.onload = function() {
      oldonload();
      func();
    };
  }
}

//function prepareInputsForHints() {
//	var inputs = document.getElementsByTagName("input");
//	for (var i=0; i<inputs.length; i++){
//		if (inputs[i].parentNode.getElementsByTagName("span")[0]) {
//			inputs[i].onfocus = function () {
//				//this.parentNode.getElementsByTagName("span")[0].style.display = "inline";
//			}
//			inputs[i].onblur = function () {
//				//this.parentNode.getElementsByTagName("span")[0].style.display = "none";
//			}
//		}
//	}
//	var selects = document.getElementsByTagName("select");
//	for (var k=0; k<selects.length; k++){
//		if (selects[k].parentNode.getElementsByTagName("span")[0]) {
//			selects[k].onfocus = function () {
//				//this.parentNode.getElementsByTagName("span")[0].style.display = "inline";
//			}
//			selects[k].onblur = function () {
//				//this.parentNode.getElementsByTagName("span")[0].style.display = "none";
//			}
//		}
//	}
//}
//addLoadEvent(prepareInputsForHints);

FormValid.showError = function(errMsg,errName) {
	for (key in FormValid.allName) {
		//document.getElementById('errMsg_'+FormValid.allName[key]).innerHTML = '';
	}
	for (key in errMsg) {
		//document.getElementById('errMsg_'+errName[key]).innerHTML = errMsg[key];
	}

};