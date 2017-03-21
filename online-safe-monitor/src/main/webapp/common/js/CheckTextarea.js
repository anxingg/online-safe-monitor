
/**
** 文本框的字符统计类
** 创建时间： 2015年01月14日
*/
function CheckTextarea() {

};
CheckTextarea.prototype = {
	fmaxlength : 500,/* 可输入的最大有效字数 */
	getStrLen: function(a) {/* 这个方法可以把半角的字符计算成0.5 */
		for (var b = 0, c = 0; c < a.length; c++) b += encodeURI(a.charAt(c)).length > 2 ? 1 : .5;
		return b
	},
	getStrLenOfSimple: function(a) {/* 这个方法不区分字符是全角还是半角 */
		return a.length;
	},
	checkCoKeyup: function(a) {
		var b = $(a).parent(),
			c = !1,
			e = ckta.fmaxlength;
			//f = $(a).parents(".form").attr("pid");
		//f >= 1e7 && 3e7 > f && (e = 2e3);
		var g = $(a).val(),
			h = parseInt(ckta.getStrLenOfSimple(g) + .5),
			i = "\u8fd8\u53ef\u4ee5\u8f93\u5165" + (e - h) + "\u5b57";
		return h > e ? i = '\u5df2\u8d85\u8fc7<font class="red">' + (h - e) + '</font>\u5b57' : h > 9 && (c = !0, b.find(".msg-error-01").hide()), b.children(".msg-text").html(i), c
	}
};
var cktaValue = '';
var ckta = new CheckTextarea();

function dynamicBinding( _textarea_DOM_Obj ){
	$(_textarea_DOM_Obj).unbind().bind("focus", function() {
		//$(this).removeClass("area01"), cktaValue = this.defaultValue || this.innerText, $(this).val() == cktaValue && $(this).val("")
		$(this).removeClass("area01"), cktaValue = this.innerText
	}).bind("blur", function() {
		//"" == $(this).val() && $(this).val(this.defaultValue || cktaValue).addClass("area01")
	}).bind("keyup", function() {
		ckta.fmaxlength = parseInt( $(this).attr("fmaxlength") , 10);//读取页面中 textarea 标签中的
		ckta.checkCoKeyup(this)
	})
}