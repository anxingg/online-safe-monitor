/**
 * 发送短信
 */
function sendMessage(data){
	var sendPhone = $("#callPhoneTwo").html();//接受电话
	var moduleName = "工单回访";
	if(data==1){
		moduleName = "来电弹屏";
		sendPhone = $("#uphone").val();
	}
	moduleName = encodeURI(encodeURI(moduleName));//转码
	var isMobile=/^((13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{8})$/;  
	if(sendPhone){
		if(!sendPhone.match(isMobile)){
			art.dialog.alert("联系电话不为手机，无法发送短信！");
			return false;
		}else{
			art.dialog.open(basePath +"/logined/workorderflow/jsp/sendMessage.jsp?sendPhone="+sendPhone+"&moduleName="+moduleName,{
				id : "sendMessage",
			    title : "发送短信",
			    width : 658,
			    minwitch : 658,
			    height :300,
			    lock : true,
			    close : function (){
			    },
			    button : [
			              {
							name : '发送',
							focus: true,//有了这个属性以后，这个按钮就有蓝色了。
							disabled: window.top.sumbitButton,//true时，不可用。false时，可用。
							callback : function() {
								if( !window.top.sumbitButton ){
									var iframe = this.iframe.contentWindow;
									iframe.subSendMessage(prompt);
								}
								return false;
							}
			              }, 
			              {
							name : '取消',
							callback : function() {
								window.top.sumbitButton=false;
								return true;
								art.dialog().close();
							}
			              }
			              ],
			    opacity: 0.1// 透明度
			});
		}
	}else{
		art.dialog.alert("接受手机不能为空！");
		return false;
	}
	
}
function prompt(data){
	if(data==0){
		art.dialog.tips("短信已发送!",3);
	}
}