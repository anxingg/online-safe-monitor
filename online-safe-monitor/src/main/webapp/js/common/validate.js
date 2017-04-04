/**
   js验证
 */
/***
 * 常用正则表达式
 * @constructor
 */
var RegExpMy = function(){};
RegExpMy.isEmail = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
RegExpMy.isTel = /^0(([1-9]\d)|([3-9]\d{2}))\d{8}$/;//格式区号+号码 037165711369
RegExpMy.isPhone = /^(13[0-9]|15[0-9]|18[0-9])\d{8}$/;
RegExpMy.isNaturalNumber = /^\d+(\.\d+)?$/;//非负浮点数
RegExpMy.isNumber = /^[-\+]?\d+(\.\d+)?$/;
RegExpMy.isIdCard = /(^\d{15}$)|(^\d{17}[0-9Xx]$)/;
RegExpMy.isMoney = /^\d+(\.\d+)?$/;
RegExpMy.isZip = /^[1-9]\d{5}$/;
RegExpMy.isQQ = /^[1-9]\d{4,10}$/;
RegExpMy.isInt = /^[-\+]?\d+$/;
RegExpMy.isEnglish = /^[A-Za-z]+$/;
RegExpMy.isChinese =  /^[\u0391-\uFFE5]+$/;
RegExpMy.isUrl =/^http(s)?:\/\/[A-Za-z0-9]+\.[A-Za-z0-9]+[\/=\?%\-&_~`@[\]\:+!]*([^<>])*$/;
RegExpMy.isDate = /^\d{4}-\d{1,2}-\d{1,2}$/;
RegExpMy.isTime = /^\d{4}-\d{1,2}-\d{1,2}\s\d{1,2}:\d{1,2}:\d{1,2}$/;
RegExpMy.isNaturalInt=/^[0-9]*[1-9][0-9]*$/;  //非负整数（正整数   +   0）;
RegExpMy.isLoginPass=/^[0-9a-zA-Z_]\w{5,17}$/;  //6-16位，区分大小写，只能使用字母、数字
RegExpMy.isLoginName= /^\w+$/;  //登录名格式验证 用户名必须为数字字母或者下划线！


/**
 * 验证密码格式
 * @param pass
 */
function isLoginName(loginName)
{
    return checkpatrn(RegExpMy.isLoginName,loginName);
}

/**
 * 验证密码格式
 * @param pass
 */
function isLoginPass(pass)
{
    return checkpatrn(RegExpMy.isLoginPass,pass);
}
/**
 * 验证是否日期格式
 * @param s
 * @return {*}
 */
function isDate(s)
{
    return checkpatrn(RegExpMy.isDate,s);
}
/**
 * 验证是否非负整数（正整数   +   0）
 * @param number
 * @return {*}
 */
function isNaturalInt(number)
{
    return checkpatrn(RegExpMy.isNaturalInt,number);
}

/**
 * 验证是否URL
 * @param s
 * @return {*}
 */
function isUrl(s)
{
    return checkpatrn(RegExpMy.isUrl,s);
}

/**
 * 验证是否中文
 * @param s
 * @return {*}
 */
function isChinese(s)
{
    return checkpatrn(RegExpMy.isChinese,s);
}
/**
 * 验证是否英文
 * @param s
 * @return {*}
 */
function isEnglish(s)
{
    return checkpatrn(RegExpMy.isEnglish,s);
}

/**
 * 验证是否整数
 * @param number
 * @return {*}
 */
function isInt(number)
{
    return checkpatrn(RegExpMy.isInt,number);
}
/**
 * 验证是否qq
 * @param qq
 * @return {*}
 */
function isQQ(qq)
{
    return checkpatrn(RegExpMy.isQQ,qq);
}
/**
 * 验证是否区号
 * @param zip
 * @return {*}
 */
function isZip(zip)
{
    return checkpatrn(RegExpMy.isZip,zip);
}
/**
 * 验证是否数字
 * @param number
 * @return {*}
 */
function isIdCard(idCard)
{
    return checkpatrn(RegExpMy.isIdCard,idCard);
}
/**
 * 验证是否数字
 * @param number
 * @return {*}
 */
function isNumber(number)
{
    return checkpatrn(RegExpMy.isNumber,number);
}
/**
 * 验证邮箱
 * @param email
 */
function isEmail(email)
{
    return checkpatrn(RegExpMy.isEmail,email);
}
/**
 * 验证固定电话号码
 * @param tel
 */
function  isTel(tel)
{
    return checkpatrn(RegExpMy.isTel,tel);
}

/**
 * 验证/非负浮点数
 * @param number
 * @return {*}
 */
function isNaturalNumber(number)
{
    return checkpatrn(RegExpMy.isNaturalNumber,number);
}

/**
 * 验证手机号码
 * @param phone
 * @return {*}
 */
function isPhone(phone)
{
    return checkpatrn(RegExpMy.isPhone,phone);
}

/**
 * 验证正则表达式
 * @param patrn 正则表达式
 * @param s   要检验的数据
 */
function checkpatrn(patrn,s)
{
    if (!patrn.exec(s)) return false
    return true
}

