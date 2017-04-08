$(document).ready(function(){
	
	initSelect("productType","sczk");
	//是否在 工业园区
	var hizyq = $("#hizyq").val();
	$("#zyq").val(hizyq);
	
	//企业规模
	var hiqygm = $("#hiqygm").val();
	$("#qygm").val(hiqygm);
	
	//生产状况
	var hisczk = $("#hisczk").val();
	$("#sczk").val(hisczk);
	
	//行业类型
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

	//经济类型
	$("#jjlx").change(function(){
		selectJJLX();
	});
	var jjlx = $("#jjlx").val();
	if(jjlx == -1){
		$("#jjlxfl").hide();
	}
	
	var economicType = $("#economicType").val().split(",");
	if(economicType[0]!=""){
		$("#jjlx").find("option[value='"+economicType[0]+"']").attr("selected",true);
		selectJJLX();
		if(economicType[0] == "私营经济" || economicType[0] == "个体经济" || economicType[0] == "股份制" || economicType[0] == "外商投资" || economicType[0] == "港澳台投资"){
			 $("#jjlxfl").find("option[value='"+economicType[1]+"']").attr("selected",true);
		 }
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
		art.dialog.confirm('确定要提交吗？', function() {
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
	var gszcdz = $.trim($("#gszcdz").val());
	var dwdm = $.trim($("#dwdm").val());
	var ssx = $.trim($("#ssx").val());
	ssx = $.trim($("#groupSel").val());
	var dwxz = $.trim($("#dwxz").val());//公司性质
	var clsj = $.trim($("#clsj").val());//成立时间
	clsj = clsj+" 00:00:00";
	var yyzzch = $.trim($("#yyzzch").val());
	var yyzzscfw = $.trim($("#yyzzscfw").val());
	var fdr = $.trim($("#fdr").val());
	var zzjgdm = $.trim($("#zzjgdm").val());
	//生成场所地址
	var sccsdz = $.trim($("#sccsdz").val());
	var qywz = $.trim($("#qywz").val());
	var yzbm = $.trim($("#yzbm").val());
	//经纬度
	var jd = $.trim($("#jd").val());
	var wd = $.trim($("#wd").val());
	//销售收入
	var xssr = $.trim($("#xssr").val());
	//职工人数
	var jgrs = $.trim($("#jgrs").val());
	//企业规模
	var qygm = $.trim($("#qygm").val());
	//是否在工业园区
	var zyq = $.trim($("#zyq").val());
	//企业分类
	var hylx = "";
	$('input[name="hylx"]:checked').each(function(){ 
		hylx+=$(this).val()+",";
	});
	
	//经济类型
	var jjlx = $("#jjlx").val();
	if(jjlx == "私营经济" || jjlx == "个体经济" || jjlx == "股份制" || jjlx == "外商投资" || jjlx == "港澳台投资"){
		jjlx = jjlx + "," + $("#jjlxfl").val();
	}
	//生产状况
	var sczk = $.trim($("#sczk").val());
	
	var parmas = {
			"parentId" : $('#parentId').val(),
			"groupId" : $('#groupId').val(),
			"companyName" : $('#companyName').val(),
			"cpy.registrationAddress" : gszcdz,
			"cpy.companyCode" : dwdm,
			"cpy.cityId" : ssx,
			"cpy.companyProperty" : dwxz,
			"cpy.businessLicence" : yyzzch,
			"cpy.productionScope" : yyzzscfw,
			"cpy.legalRepresentative" : fdr,
			"cpy.economicType" : jjlx,
			"cpy.unitCode" : zzjgdm,
			"cpy.productAddress" : sccsdz,
			"cpy.website" : qywz,
			"cpy.productType" : sczk,
			"cpy.postalcode" : yzbm,
			"cpy.precision" : jd,
			"cpy.dimension" : wd,
			"cpy.sales" : xssr,
			"cpy.enterpriseScale" : qygm,
			"cpy.isIn" : zyq,
			"cpy.workersNum" : jgrs,
			//"cpy.establishmentTime" : clsj,
			"establishmentTime":clsj
	};
	
	$(".formButton_green").attr("disabled", "disabled");
	$.ajax({
		url : basePath + "companywh/saveOrUpdateCpy.action",
		type : "post",
		data : parmas,
		success : function(data){
			if (data == 1) {
				//artDialog.alert("修改成功！");
				window.location.href = basePath+"logined/company/companyList.jsp?parentId="+$('#parentId').val();
			}else{
				artDialog.alert("保存失败！");
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



