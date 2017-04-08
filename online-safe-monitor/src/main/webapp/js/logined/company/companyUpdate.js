$(document).ready(function(){
	
	initSelect("safeProductType","bzhdj");
	initSelect("importCertificate","zmmc");
	initSelect("productType","sczk");
	
	
	//处理下拉选
	var hibzhdj = $("#hibzhdj").val();
	$("#bzhdj").val(hibzhdj);
	
	var hizmmc = $("#hizmmc").val();
	$("#zmmc").val(hizmmc);
	
	var hizyq = $("#hizyq").val();
	$("#zyq").val(hizyq);
	
	var hiqygm = $("#hiqygm").val();
	$("#qygm").val(hiqygm);
	
	var hisczk = $("#hisczk").val();
	$("#sczk").val(hisczk);
	
	//处理复选框
	var hihylx = $("#hihylx").val();
	if(hihylx!=null && hihylx!=""){
		var check = hihylx.split(',');
		for (var i = 0; i < check.length; i++) {
			$("input[name='hylx']").each(function(){
				if($(this).val()==check[i]){
					$(this).attr("checked",true);
				}
			});
		}
	}
	
	var hidwsb = $("#hidwsb").val();
	if(hidwsb!=null && hidwsb!=""){
		var check = hidwsb.split(',');
		for (var i = 0; i < check.length; i++) {
			$("input[name='dwsb']").each(function(){
				if($(this).val()==check[i]){
					$(this).attr("checked",true);
				}
			});
		}
	}
	
	var jjlx = $("#jjlx").val();
	if(jjlx == -1){
		$("#jjlxfl").hide();
	}

	//保存企业信息
	$("#submit").click(function(){
		// 框架校验
		if (!validator(document.getElementById("form1"))) {
			return;
		}
		var sczk = $.trim($("#sczk").val());
		if(sczk==null || sczk=="" || sczk==-1){
			artDialog.alert("请选择生产状况！");
			return;
		}
		art.dialog.confirm('确定要保存修改吗？', function() {
			submit();
		}, function() {
			return;
		});
	});
	
});


//经济类型选择
function selectJJLX(){
	
	var jjlx = $("#jjlx").val();
	if(jjlx == -1){
		$("#jjlxfl").html("");
		$("#jjlxfl").hide();
	}
	if(jjlx == "国有经济"){
		$("#jjlxfl").html("");
		$("#jjlxfl").hide();
	}
	if(jjlx == "集体经济"){
		$("#jjlxfl").html("");
		$("#jjlxfl").hide();
	}
	if(jjlx == "私营经济"){
		$("#jjlxfl").show();
		var str ='<option value="私营独资企业">私营独资企业</option>';
		str +='<option value="私营合伙企业">私营合伙企业</option>';
		str +='<option value="私营有限责任公司">私营有限责任公司</option>';
		$("#jjlxfl").html(str);
	}
	if(jjlx == "个体经济"){
		$("#jjlxfl").show();
		var str ='<option value="个体工商户">个体工商户</option>';
		str +='<option value="个人合伙">个人合伙</option>';
		$("#jjlxfl").html(str);
	}
	if(jjlx == "联营经济"){
		$("#jjlxfl").html("");
		$("#jjlxfl").hide();
	}
	if(jjlx == "股份制"){
		$("#jjlxfl").show();
		var str ='<option value="股份有限公司">股份有限公司</option>';
		str +='<option value="有限责任公司">有限责任公司</option>';
		$("#jjlxfl").html(str);
	}
	if(jjlx == "外商投资"){
		$("#jjlxfl").show();
		var str ='<option value="中外合资经营企业">中外合资经营企业</option>';
		str +='<option value="中外合作经营企业">中外合作经营企业</option>';
		str +='<option value="外资企业">外资企业</option>';
		$("#jjlxfl").html(str);
	}
	if(jjlx == "港澳台投资"){
		$("#jjlxfl").show();
		var str ='<option value="合资经营企业">合资经营企业</option>';
		str +='<option value="合作经营企业">合作经营企业</option>';
		str +='<option value="独资企业">独资企业</option>';
		$("#jjlxfl").html(str);
	}
	if(jjlx == "其它经济"){
		$("#jjlxfl").html("");
		$("#jjlxfl").hide();
	}
	
	
}



/**
 * 保存企业信息
 */
function submit(){
	//单位代码
	var dwdm = $.trim($("#dwdm").val());

	//销售收入
	var xssr = $.trim($("#xssr").val());

	//职工人数
	var jgrs = $.trim($("#jgrs").val());
	
	//企业规模
	var qygm = $.trim($("#qygm").val());
	//安全生产管理人员人数
	var acscrs = $.trim($("#acscrs").val());

	//安全生产负责人
	var acscfzr = $.trim($("#acscfzr").val());

	//安全生产负责人移动电话
	var acscyddh = $.trim($("#acscyddh").val());

	//安全生产负责人办公电话
	var acscbgdh = $.trim($("#acscbgdh").val());

	//安全生产负责人电子邮箱
	var acscdzyx = $.trim($("#acscdzyx").val());
	
	//特种作业人员人数
	var tzzyrs = $.trim($("#tzzyrs").val());

	//安全生产标准等级
	var bzhdj = $.trim($("#bzhdj").val());

	//应急咨询服务号码
	var zxhm = $.trim($("#zxhm").val());

	//安全值班电话
	var zbdh = $.trim($("#zbdh").val());
	
	//进口企业资质证明名称
	var zmmc = $.trim($("#zmmc").val());
	//进口企业资质证明编号
	
	var zzbh = $.trim($("#zzbh").val());
	
	//主要产品及生产规模
	var zycp = $.trim($("#zycp").val());
	
	//企业简介
	var qyjj = $.trim($("#qyjj").val());
	
	//厂区边界外1000米范围内的单位或设备情况
	var dwsbStr="";
	$('input[name="dwsb"]:checked').each(function(){ 
		dwsbStr+=$(this).val()+",";
	});
	
	
	
	//生产状况
	var sczk = $.trim($("#sczk").val());
	
	//生产投入提取标准
	var extractDescription = $.trim($("#extractDescription").val());
	var parmas = {
			"cpy.companyCode" : dwdm,
			"cpy.productType" : sczk,
			"cpy.sales" : xssr,
			"cpy.enterpriseScale" : qygm,
			"cpy.safeManageUserNum" : acscrs,
			"cpy.safeManageUserName" : acscfzr,
			"cpy.safeManageUserPhone" : acscyddh,
			"cpy.safeManageUserTel" : acscbgdh,
			"cpy.safeManageUserEmail" : acscdzyx,
			"cpy.specialUserNum" : tzzyrs,
			"cpy.safeProductGrade" : bzhdj,
			"cpy.emergencyPhone" : zxhm,
			"cpy.safeDutyPhone" : zbdh,
			"cpy.importCompanyQualificationNum" : zzbh,
			"cpy.importCompanyQualification" : zmmc,
			"cpy.product" : zycp,
			"cpy.introduction" : qyjj,
			"cpy.workersNum" : jgrs,
			"cpy.outsideSituation" : dwsbStr,
			"cpy.extractDescription" : extractDescription
	};
	
	$(".formButton_green").attr("disabled", "disabled");
	$.ajax({
		url : basePath + "companywh/saveOrUpdateCpy.action",
		type : "post",
		data : parmas,
		success : function(data){
			if (data == 1) {
				//artDialog.alert("修改成功！");
				window.location.href = basePath+"companywh/toCompanyView.action";
			}else{
				artDialog.alert("修改失败！");
				$(".formButton_green").removeAttr("disabled");
			}
		}
	});
	
	
	
	
	
}

/**
 * 初始化数据字段下拉框
 * @return
 */
function initSelect(typeName,id) {
	var paramData = {
		'infoType' : typeName,
		'sysTag' : 1
	};
	qytx.app.ajax({
		url : basePath + "dict/getDicts.action",
		type : "post",
		dataType : "text",
		data : paramData,
		success : function(data) {
			//data是一个数组
			var jsonData = eval('(' + data + ')');
			$("#"+id).empty();
			$("#"+id).append('<option value="-1">请选择</option>');
			for (var i = 0; i < jsonData.length; i++) {
				$("#"+id).append("<option value='"
						+ jsonData[i].value + "'>" + jsonData[i].name
						+ "</option>");
			}
		}
	});
}



