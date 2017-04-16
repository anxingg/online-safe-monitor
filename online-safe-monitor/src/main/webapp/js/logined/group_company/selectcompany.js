var userMap = new HashMap();
//ztree设置
var setting = {
    check: {
        enable: true,
        chkStyle: "radio",
        radioType: "all"
    },
    view: {
        showIcon: true //张东领修改，部门和人员前均应加图标
    },

    data: {
        simpleData: {
            enable: true
        },
        showTitle: true, //是否显示节点title信息提示 默认为true
        key: {
            title: "title" //设置title提示信息对应的属性名称 也就是节点相关的某个属性
        }
    },
    callback: {
        onCheck: zTreeOnCheck
    }
};


/**
 *单选按钮选择事件
 * @param event
 * @param treeId
 * @param node
 */
function zTreeOnCheck(event, treeId, node) {
    var showType = $("#showType").val();
    userMap.clear();
    //只显示部门
    if (node.id.substr(0, 4) == "gid_") {
        //如果是人员，则保存到map里面
        var treeNode = new TreeNode();
        var id = node.id.substr(4);
        treeNode.setId(id);
        treeNode.setData("");
        treeNode.setName(node.name);
        treeNode.setType("group");
        userMap.set(id, treeNode);
    }
    art.dialog.data("userMap", userMap);
}

/**
 * 根据类型列表选择群组
 * @param t
 */
function searchGroupbyTypeList() {
    var groupId = $('#groupId').val()
	var dataParam = {
        'groupId': groupId
    };
    var url=basePath + "companywh/getBindSelectTree.action";
    console.log("searchGroupbyTypeList url:"+url);
    $.ajax({
        url: url,
        type: 'post',
        dataType: 'json',
        data: dataParam,
        success: function(data) {
            var dataTemp = new Array();
            console.log("data.length:"+data.length);
            for (var i = 0; i < data.length; i++) {
                if (data[i].pId != "gid_-1") {
                    dataTemp.push(data[i]);
                    console.log("data[i]:"+data[i].id);
                }
            }
            var treeObj = $.fn.zTree.init($("#groupUserTree"), setting, dataTemp);

            // 默认展示第一个人员所在的组
            var rootNodesArr = treeObj.getNodes();
            for (var j = 0; j < rootNodesArr.length; j++) {
                var nodes = treeObj.transformToArray(rootNodesArr[j]);
                var isExpand = false;
                for (var i = 0; i < nodes.length; i++) {
                    if (nodes[i].id.indexOf("gid_") == 0) {
                        var nodesArr = treeObj.getNodesByParam("id", nodes[i].id, null);
                        var tempNode = nodesArr[0];
                        while (tempNode.getParentNode() != null) {
                            treeObj.expandNode(tempNode.getParentNode(), true);
                            tempNode = tempNode.getParentNode();
                        }
                        isExpand = true;
                        break;
                    }
                }
                if (isExpand) {
                    break;
                }
            }

            // 初始化后,同时初始化已经选择的人员
            if (first == true) {
                initDefaultSelected();
            } else {
                // 根据userMap初始化
                initSelected();
            }
        }
    });
}


/**
 * 初始化已经选择的人员
 */
function initDefaultSelected() {
    first = false;
    var defaultSelectId = $("#defaultSelectId").val();
    if ("" != defaultSelectId) {
        var ids = defaultSelectId.split(",");
        var treeObj = $.fn.zTree.getZTreeObj("groupUserTree");
        for (var i = 0; i < ids.length; i++) {
            var node = null;
            node = treeObj.getNodeByParam("id", "gid_" + ids[i], null);
            if (null != node) {
                treeObj.checkNode(node, true, false);
                var treeNode = new TreeNode();
                var id = node.id.substr(4);
                treeNode.setId(id);
                treeNode.setData("");
                treeNode.setName(node.name);
                treeNode.setType("group");
                userMap.set(id, treeNode);
            }
        }
        refreshSelectedUser();
    }
}

/**
 * 根据userMap初始化选择
 */
function initSelected() {
    userMap.forEach(function(value, key) {
        var treeObj = $.fn.zTree.getZTreeObj("groupUserTree");
        var node = null;
        node = treeObj.getNodeByParam("id", "gid_" + key, null);
        if (null != node) {
            treeObj.checkNode(node, true, false);
            var treeNode = new TreeNode();
            var id = node.id.substr(4);
            treeNode.setId(id);
            treeNode.setData("");
            treeNode.setName(node.name);
            treeNode.setType("group");
            userMap.set(id, treeNode);
        }
    });
}

var first = true;
$(document).ready(
    function() {
    	//选择部门
        $("#btnSelectGroup").bind("click", function() {
            searchGroupbyTypeList();
        });
        searchGroupbyTypeList();
    });