var radios;

$(document).ready(function(){
	getCrmTable();
});

function getCrmTable(){
	var phone = art.dialog.data("phone");
	var crmId = art.dialog.data("crmId");
	$.ajax({
		url : basePath + "/jbpmworkorder/selectCrms.action",
		data : {
			phone : phone
		},
		type : "post",
		dataType : "text",
		success : function(data){
			var json = eval("("+data+")");
			$("#listTable").empty();
			var html = "";
			html += "<thead>";
			html += "<tr><th class='chk'></th>";
			html+= " <th>姓名</th>";
			html += "<th>性别</th>";
			html += "<th class='right_bdr0'>客户类别</th>";
			html+= "</tr></thead><tbody >";
			
			for(var i=0;i<json.length;i++){
				var id = json[i].crmId;
				var mobile = json[i].mobile;
				var backPhone = json[i].backPhone;
				var uname = json[i].uname;
				var uaddress = json[i].uaddress;
				var usex = json[i].usex;
				var personType = json[i].personType;
				var cardId = json[i].cardId;
				var hkAddress = json[i].hkAddress;
				var company = json[i].company;
				var job = json[i].job;
				var receiveMoney = json[i].receiveMoney;
				var note = json[i].note;
				if(i%2==0){
					html +="<tr class='even'>";
				}else{
					html +="<tr class='odd'>";
				}
				if(crmId == id){
					html += "<td><input type='radio' name='crm' value='"+id+"_"+mobile+"_"+backPhone+"_"+uname+"_"+uaddress+"_"+usex+"_"
					+personType+"_"+cardId+"_"+hkAddress+"_"+company+"_"+job+"_"+receiveMoney+"_"+note+"'/></td>";
				}else{
					html += "<td><input type='radio' name='crm' value='"+id+"_"+mobile+"_"+backPhone+"_"+uname+"_"+uaddress+"_"+usex+"_"
					+personType+"_"+cardId+"_"+hkAddress+"_"+company+"_"+job+"_"+receiveMoney+"_"+note+"'/></td>";
				}
				
				html += "<td>"+uname+"</td>";
				if(usex==1){
					html += "<td>男</td>";
				}else{
					html += "<td>女</td>";
				}
				if(personType==1){
					html += "<td class='right_bdr0'>一般客户</td>";
				}else if(personType==2){
					html += "<td class='right_bdr0'>VIP客户</td>";
				}else{
					html += "<td class='right_bdr0'>-</td>";
				}
				html += "</tr>";
				
			}
			html += "</tbody>";
			
			$("#listTable").append(html);
		}
	});
}

function getCheckedRadio(){
	 return $('input[name=crm]:checked').val();
}