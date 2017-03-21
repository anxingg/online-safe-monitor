

$(document).ready(function() {

	//确定按钮绑定事件
	$(".formButton_green").bind("click", function() {
		if(!verifyEmptyContent("catalogId", "请选择化学品编号！", "0")){
			$(".formButton_green").removeAttr("disabled");
			return;
		}
		
		// 框架校验
		if (validator(document.getElementById("form1"))) {
			addOne();
		}
		return false;
	});
	
	//侵入途径
	$(":checkbox[name='intrusiveWay']").live("click", function(){
		if($(this).attr("checked") == 'checked'){
			$("#hideIntrusiveWay").removeClass("inputError");
			$("#hideIntrusiveWay").next('p').remove();
		}else {
			//alert('没选 中');
		}
	});
	
	//危化品编号的变更事件
	$("#catalogId").live("change", function(){
		$("#pinming").html('');
		$("#bieming").html('');
		$("#cashao").html('');
		var array = $("#catalogId option:selected").eq(0).attr("name").split("@");
		if(array != null && array != undefined && array.length > 2){
			$("#pinming").html(array[0]);
			$("#bieming").html(array[1]);
			$("#cashao").html(array[2]);
		}
	});
	
});


/**
 * 新增或修改
 */
function addOne() {
	$(".formButton_green").attr("disabled", "disabled");
	
	//主键
	var vid = $("#vid").val();
	if(!vid){
		vid = -1;
	}
	
	//目录ID
	var catalogId = $("#catalogId").val();
	
	//公司ID
	var groupId = $("#group_id").val();
	
	//物质名称
	var materialName = $.trim( $("#materialName").val() );
	
	//分子式
	var molecularFormula = $.trim( $("#molecularFormula").val() );
	
	//熔点（℃）
	var meltingPoint = $("#meltingPoint").val();
	
	//沸点
	var boilingPoint = $("#boilingPoint").val();
	
	//比重（水=1）
	var gravity = $("#gravity").val();
	
	//饱和蒸气压（kPa）
	var saturatedVaporPressure = $("#saturatedVaporPressure").val();
	
	//蒸气密度（空气=1）
	var vaporDensity = $("#vaporDensity").val();
	
	//溶解性
	var solubility = $.trim( $("#solubility").val() );
	
	//外观与性状
	var appearance = $.trim( $("#appearance").val() );
	
	//危险特性
	var dangerousCharacteristic = $.trim( $("#dangerousCharacteristic").val() );
	
	//灭火方法
	var fireFightingMethods = $.trim( $("#fireFightingMethods").val() );
	
	//稳定性
	var stability = $(":radio[name='stability']:checked").first().val();
	
	//稳定性避免条件
	var stabilityAvoid = $.trim( $("#stabilityAvoid").val() );
	
	//聚合危险性
	var aggregateRisk = $(":radio[name='aggregateRisk']:checked").first().val();
	
	//聚合危险性避免条件
	var aggregateRiskAvoid = $.trim( $("#aggregateRiskAvoid").val() );
	
	//禁忌物
	var taboo = $.trim( $("#taboo").val() );
	
	//燃烧（分解）产物
	var breakdownProducts = $.trim( $("#breakdownProducts").val() );
	
	//侵入途径
	var intrusiveWay = ',';
	$(":checkbox[name='intrusiveWay']:checked").each(function(){ 
		intrusiveWay += $(this).val()+",";
	})
	if(intrusiveWay == ','){
		intrusiveWay = '';
		$("#hideIntrusiveWay").val("");
	}else {
		$("#hideIntrusiveWay").val(intrusiveWay);
	}
	
	if(!verifyEmptyContent("hideIntrusiveWay", "请选择侵入途径！", "")){
		$(".formButton_green").removeAttr("disabled");
		return;
	}
	
	//LD50
	var toxicityLD = $.trim( $("#toxicityLD").val() );
	
	//LC50
	var toxicityLC = $.trim( $("#toxicityLC").val() );
	
	//健康危害（急性和慢性）
	var healthRisk = $.trim( $("#healthRisk").val() );
	
	//泄漏紧急处理
	var leakageHandling = $.trim( $("#leakageHandling").val() );
	
	//储运注意事项
	var storageTransportationAttenti = $.trim( $("#storageTransportationAttenti").val() );
	
	//MAC
	var mac = $.trim( $("#mac").val() );
	
	//工程控制
	var engineeringControl = $.trim( $("#engineeringControl").val() );
	
	//呼吸系统防护
	var respiratoryProtection = $.trim( $("#respiratoryProtection").val() );
	
	//身体防护
	var bodyProtection = $.trim( $("#bodyProtection").val() );
	
	//手防护
	var handProtection = $.trim( $("#handProtection").val() );
	
	//眼防护
	var eyeProtection = $.trim( $("#eyeProtection").val() );
	
	//其它
	var other = $.trim( $("#other").val() );
	
	$.ajax({
		url : basePath + "dangerchemicals/dangerchemicals_Add_saveOrUpdate.action",
		type : "post",
		dataType : 'json',
		data : {
			'dangerChemicals.vid' : vid,
			'dangerChemicals.catalogId' : catalogId,
			'dangerChemicals.groupId' : groupId,
			'dangerChemicals.isForkGroup' : groupId,
			'dangerChemicals.materialName' : materialName,
			'dangerChemicals.molecularFormula' : molecularFormula,
			'dangerChemicals.meltingPoint' : meltingPoint,
			'dangerChemicals.boilingPoint' : boilingPoint,
			'dangerChemicals.gravity' : gravity,
			'dangerChemicals.saturatedVaporPressure' : saturatedVaporPressure,
			'dangerChemicals.vaporDensity' :vaporDensity,
			'dangerChemicals.solubility' :solubility,
			'dangerChemicals.appearance' :appearance,
			'dangerChemicals.dangerousCharacteristic' :dangerousCharacteristic,
			'dangerChemicals.fireFightingMethods' :fireFightingMethods,
			'dangerChemicals.stability' :stability,
			'dangerChemicals.stabilityAvoid' :stabilityAvoid,
			'dangerChemicals.aggregateRisk' :aggregateRisk,
			'dangerChemicals.aggregateRiskAvoid' :aggregateRiskAvoid,
			'dangerChemicals.taboo' :taboo,
			'dangerChemicals.breakdownProducts' :breakdownProducts,
			'dangerChemicals.intrusiveWay' :intrusiveWay,
			'dangerChemicals.toxicityLD' :toxicityLD,
			'dangerChemicals.toxicityLC' :toxicityLC,
			'dangerChemicals.healthRisk' :healthRisk,
			'dangerChemicals.leakageHandling' :leakageHandling,
			'dangerChemicals.storageTransportationAttenti' :storageTransportationAttenti,
			'dangerChemicals.mac' :mac,
			'dangerChemicals.engineeringControl' :engineeringControl,
			'dangerChemicals.respiratoryProtection' :respiratoryProtection,
			'dangerChemicals.bodyProtection' :bodyProtection,
			'dangerChemicals.handProtection' :handProtection,
			'dangerChemicals.eyeProtection' :eyeProtection,
			'dangerChemicals.other' : other
		},
		success : function(data) {
			if (data == 1) {
				/*
				if(vid == -1){
					artDialog.alert("新增成功！", function(){
						window.location.href = basePath + "wh/logined/dangerChemicals/dangerChemicalsList.jsp";
					});
				}else {
					artDialog.alert("修改成功！", function(){
						window.location.href = basePath + "wh/logined/dangerChemicals/dangerChemicalsList.jsp";
					});
				}
				*/
				window.location.href = basePath + "wh/logined/dangerChemicals/dangerChemicalsList.jsp";
			} else {
				artDialog.alert(tipStatement(vid, data));
				$(".formButton_green").removeAttr("disabled");
			}
		}
		
	});
	
}

function tipStatement(id, result){
	var oper = id == -1? '新增':'修改';
	var tip = result == 1?'成功':(result == -1?'时，物质名称已经使用':(result == -2?'时，分子式已经使用':'失败'));
	return oper + tip;
}


/**
 * 取消操作
 * @returns {Boolean}
 */
function goback(){
	var url = document.referrer;
	window.location.href = url;
	return false;
}