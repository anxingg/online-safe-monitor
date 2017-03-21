
$(document).ready(function() {
	       
			getIndustryTree();

			$("#workName").click(function() {
						showIndustry();
						return false;
					});

			// 科室选择不能点击回车
			$("#workName").keydown(function(e) {
						if (e.which == 8) {
							return false;
						}
					});
			//初始化树的值
			
			
		});

/**
 * 群组树调用群组树
 */
var showOrHide = true;
function showIndustry() {
	$('#menuContent').toggle(showOrHide);
	// 相当于
	if (showOrHide) {
		showOrHide = false;
		var groupObj = $("#workName");
		var groupOffset = $("#workName").position();
		$("#menuContent").css({
					left : groupOffset.left + "px",
					top : groupOffset.top + groupObj.outerHeight() - 1 + "px"
				}).show();
		$("#treeContent").one("mouseleave", function() {
					$("#menuContent").hide();
					showOrHide = true;
					return false;
				});
	} else {
		$("#menuContent").hide();
		showOrHide = true;
	}
}

/**
 * 获取坐席的树结构
 */
function getIndustryTree() {
	// 设置树样式
	// 开始隐藏
	$("#menuContent").hide();
	// 坐席树参数设置
	var setting = {
			check: {
   				enable: true,
   				chkStyle: "checkbox",
   				radioType: "all"
   			},
		view : {
			dblClickExpand : false
		},
		data : {
			simpleData : {
				enable : true
			}
		},
		callback : {
		//	onClick : onTreeNodeClick,
			onCheck: zTreeOnCheck
		}
	};
	var dataParam = {
			    'type' : 6,
	            'showType' :3,
	            'parentId':5,
	            'groupType':1
	};
	$.ajax({
				url : basePath + "hotlineuser/selectUser.action",
				type : 'post',
				dataType : 'json',
				data : dataParam,
				success : function(data) {
						$.fn.zTree.init($("#industryTree"), setting, data);
					//初始化坐席
					initIndustry();
				}
			});

	/**
	 * 得到部门
	 */
	/*function getIndustry() {
		var parentId = $("#parentId").val();
		var zTree = $.fn.zTree.getZTreeObj("industryTree");
		var nodes = zTree.transformToArray(zTree.getNodes());
		for (var i = 0; i < nodes.length; i++) {
			var gName = nodes[i].name;
			var gId = nodes[i].id;
			if (gId == "uid_" + parentId) {
				zTree.selectNode(nodes[i]);
				$("#workName").val(gName);
			}
		};
	}*/
	/**
	 * 群组树调用点击事件
	 */
	/*function onTreeNodeClick(e, treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj("industryTree");
		var nodes = zTree.getSelectedNodes();

		if (nodes.length > 0) {
			var groupName = nodes[nodes.length - 1].name;
			var groupId = nodes[nodes.length - 1].id;
			$("#workIds").val(groupId.substring(4, groupId.length));
			$("#workName").val(groupName);
			$("#menuContent").hide();
		}
		// 根据部门名称得到当前节点
//		var groupId = $("#workIds").val();
//
//		var currentNode = zTree.getNodesByParam("id", "kid_" + groupId, null);
//		if (currentNode.length > 0) {
//			var childrenArr = getAllChildrenNodes(currentNode[0], []);
//			// 不能选择子节点
//			if ($.inArray(parentId, childrenArr) != -1) {
//				$("#parentId").val("");
//				$("#workName").val("");
//				art.dialog.alert("不能选择子行业作为上级行业！");
//				return;
//			}
//		}
	}*/
}

 function initIndustry(){ 
	 var zTree = $.fn.zTree.getZTreeObj("industryTree");
	  var workIds = $("#workIds").val();
	  if (null == zTree){
		  return;
	  }
	  var currentNode = zTree.getNodesByParam("id", "uid_" + workIds, null);
	  zTree.checkNode(currentNode, true, true);
	  zTree.updateNode(currentNode);
 //将选中的节点显示出来
	 
 }
 /**
  * 得到所有子节点
  */
/* function getAllChildrenNodes(treeNode,result) {
     if (treeNode.isParent) {
         var childrenNodes = treeNode.children;
         if (childrenNodes) {
             for (var i = 0; i < childrenNodes.length; i++) {
                 result.push((childrenNodes[i].id).substring(4));
                 result = getAllChildrenNodes(childrenNodes[i], result);
             }
         }
     }
     return result;
 }*/
 
 function zTreeOnCheck(){
	 var zTree = $.fn.zTree.getZTreeObj("industryTree");
	 //每次点击清空显示框
	 $("#workIds").val("");
	 $("#workName").val("");
		var nodes = zTree.getCheckedNodes(true);
		if (nodes.length > 0) {
			for(var i=0;i<nodes.length;i++){
				var tem=$("#workName").val();
				var tem1=$("#workIds").val();
				var groupName = nodes[i].name;
				var groupId = nodes[i].id;
				var heads=groupId.substring(0,4);
				
				if(heads=="uid_"){
					if(tem1){
						$("#workIds").val(tem1.trim()+","+groupId.substring(4, groupId.length).trim());
						$("#workName").val(tem+","+groupName);
					}else{
						$("#workIds").val(groupId.substring(4, groupId.length));
						$("#workName").val(groupName);
					}
				}
			}
			
			
			
		//	$("#menuContent").hide();
		}
	 
 }
