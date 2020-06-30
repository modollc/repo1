<%@ page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="/WEB-INF/tld/shiro.tld"%>
<%@include file="../commons/head.jsp"%>
<jsp:include page="../head.jsp" />
<style>
.navMenu-item:nth-child(2) .navMenu-title {
    background: #c38447;
    color: #fff;
}
.navMenu-item:nth-child(2) .navMenu-item-content li:nth-child(1) a{
    color: #622f00;
   font-weight: bold;
    
}
.increase_food .sxx{
    padding: 0;
}
</style>

<SCRIPT type="text/javascript">
	    var fId="";
	    var fName="";
        var curMenu = null, zTree_Menu = null;
        var setting = {
            view: {
                showLine: true,
                showIcon: false,
                //showIcon: true,
                
                //selectedMulti: false,
                //dblClickExpand: false
            },
            data: {
                simpleData: {
                    enable: true,
                    idKey: "id",
                    pIdKey: "pid",
                    rootPId: 0
                }
            },
            callback: {
                beforeClick: beforeClick,
                onClick: zTreeOnClick,
                beforeRemove: beforeRemove
            },
            async: {  
                enable: true, // 设置 zTree是否开启异步加载模式  加载全部信息  
                type: "post",  
                url : '/nutrition/nutritionList/tree',
                autoParam : [ "id", "pid"],
                isSimpleData: true, // 简单数据模型，必须提供下面的两项
                treeNodeKey: "id", // 节点的id
                treeNodeParentKey: "pid",  // 父节点的id
                dataFilter: filter  
                },

        };
        
        
        //数据过滤
        function filter(treeId, parentNode, childNodes,parentNodeJSON) {
            if (childNodes.length==0) return null;
            for (var i=0, l=childNodes.length; i<l; i++) {
                childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
            }
            return childNodes;
        }

        var zNodes_food =[
            { id:1, pId:0, name:"原生材料", open:true},
            { id:2, pId:0, name:"菜谱", open:true},
            { id:3, pId:0, name:"多维多矿补剂"}
        ];
        var zNodes_public =[
            { id:1, pId:0, name:"公共类别", open:true},
            { id:11, pId:1, name:"肥瘦"},
            { id:111, pId:11, name:"极瘦（>90%）"},
            { id:112, pId:11, name:"偏瘦（>70%）"},
            { id:113, pId:11, name:"正常（50%-70%）"},
            { id:114, pId:11, name:"偏肥（<50%）"},
            { id:115, pId:11, name:"极肥（<20%）"},
            { id:2, pId:1, name:"内脏类"},
            { id:31, pId:2, name:"肝"},
            { id:32, pId:2, name:"肠"},
            { id:33, pId:2, name:"肚"},
            { id:34, pId:2, name:"腰"},
            { id:3, pId:1, name:"肢体类"},
            { id:4, pId:3, name:"掌"},
            { id:5, pId:3, name:"头"},
            { id:6, pId:3, name:"爪"},
            { id:7, pId:3, name:"尾"}
        ];
        function beforeClick(treeId, treeNode) {
            /* if (treeNode.level == 0 ) {
                var zTree = $.fn.zTree.getZTreeObj("treeDemo");
                zTree.expandNode(treeNode);
                return false;
            } */
            return true;
        }
       
        //编辑节点
        function zTreeOnClick(event, treeId, treeNode) {
            console.log(treeNode);
             fId=treeNode.id;
    	     fName=treeNode.name;
    	     searchTable(treeNode.id);
            $("#list_box").attr('src',"foodList.html?foodId="+treeNode.id);
            if(treeNode.getParentNode() == null || treeNode.getParentNode() == 'null') {
            	$("#modal-classification-edit .ssfl").val(treeNode.name);
            }else{
	            $("#modal-classification-edit .ssfl").val(treeNode.name);
            }
	            $("#modal-classification-edit .lbmc").val(treeNode.name);
            if(treeNode.getParentNode() == null || treeNode.getParentNode() == 'null') {
            	$("#modal-classification-add .ssfl").val(treeNode.name);
            }else{
            	$("#modal-classification-add .ssfl").val(treeNode.name);
            }

        };
        //编辑节点
        function zTreePublicOnClick(event, treeId, treeNode) {
            console.log(treeNode);
            if(treeNode.getParentNode() == null || treeNode.getParentNode() == 'null') {
            	$("#modal-classification-public-add .ssfl").val("公共分类");
            }else{
            	$("#modal-classification-public-add .ssfl").val(treeNode.getParentNode().name);
            }
            if(treeNode.getParentNode() == null || treeNode.getParentNode() == 'null') {
            	$("#modal-classification-public-edit .ssfl").val("公共分类");
            }else{
            	$("#modal-classification-public-edit .ssfl").val(treeNode.getParentNode().name);
            }
            $("#modal-classification-public-edit .lbmc").val(treeNode.name);

        };
        
        function updateNode(e) {
            var nameCount = $("#modal-classification-edit .lbmc").val();
			var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
			type = e.data.type,
			nodes = zTree.getSelectedNodes();
			if (nodes.length == 0) {
				alert("请先选择一个节点");
			}
			
		   $.ajax({
            	type: "post",
				url : "/nutrition/nutritionList/tree/edit",
				data: {id:nodes[0].id, name:nameCount},
	            dataType: "json",
				success : function(data) {
					for (var i=0, l=nodes.length; i<l; i++) {
						zTree.setting.view.fontCss = {};
						if (type == "rename") {
							nodes[i].name = nameCount;
						}
						zTree.updateNode(nodes[i]);
					}
		            $(".uk-modal").removeClass("uk-open");
		            $(".uk-modal").css("display","none");
						alert("修改成功");
				},error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert(errorThrown);
				}
			});
        }
        
        // 删除节点
        var log, className = "dark";
        function remove(e) {
			var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
			nodes = zTree.getSelectedNodes(),
			treeNode = nodes[0];
			if (nodes.length == 0) {
				alert("请先选择一个节点");
				return;
			}
			
			$.ajax({
            	type: "post",
				url : "/nutrition/nutritionList/tree/validateChild",
				data: {id:treeNode.id},
	            dataType: "json",
				success : function(data) {
					if(data == false) {

						$.ajax({
			            	type: "post",
							url : "/nutrition/nutritionList/tree/delete",
							data: {id:treeNode.id},
				            dataType: "json",
							success : function(data) {
								if(data == true) {
									zTree.removeNode(treeNode, true);
									alert("删除成功");
								}else{
									alert("删除失败，分类已经被应用！");
								}
							},error : function(XMLHttpRequest, textStatus, errorThrown) {
								alert(errorThrown);
							}
						});
					}else{
						alert(treeNode.name+"有子节点，不能删除");
						return;
					}
				},error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert(errorThrown);
				}
			});
        };
        
        
      
		function beforeRemove(treeId, treeNode) {
			className = (className === "dark" ? "":"dark");
			return confirm("确认删除 " + treeNode.name + " 分类吗？");
		}
        //添加节点
        var newCount = 1;
        
        // 添加节点
		function add(e) {
        	
            var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
			isParent = e.data.isParent,
			nodes = zTree.getSelectedNodes(),
            treeNode = nodes[0];
            console.log(treeNode);
            var addname = $("#modal-classification-add .lbmc").val();
            if (nodes.length == 0) {
				alert("请先选择一个节点");
				return;
			}

            $.ajax({
            	type: "post",
				url : "/nutrition/nutritionList/tree/add",
				data: {pid:treeNode.id, name:addname},
	            dataType: "json",
				success : function(data) {
					if (treeNode) {
						treeNode = zTree.addNodes(treeNode, {id:(data.id), pId:treeNode.id, isParent:isParent, name:addname });
					} else {
						treeNode = zTree.addNodes(null, {id:(data.id), pId:0, isParent:isParent, name:addname });
					}
					if (treeNode) {
						zTree.editName(treeNode[0]);
					} else {
						alert("叶子节点被锁定，无法增加子节点");
		            }
		            $(".uk-modal").removeClass("uk-open");
		            $(".uk-modal").css("display","none");
		            $("#modal-classification-add .lbmc").val('');
						alert("添加成功");
				},error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert(errorThrown);
				}
			});
        };
        
      
        
   
        
         //复制节点
         function fontCss(treeNode) {
			var aObj = $("#" + treeNode.tId + "_a");
			aObj.removeClass("copy").removeClass("cut");
			if (treeNode === curSrcNode) {
				if (curType == "copy") {
					aObj.addClass(curType);
				} else {
					aObj.addClass(curType);
				}			
			}
		}

		var curSrcNode, curType;
		function setCurSrcNode(treeNode) {
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			if (curSrcNode) {
				delete curSrcNode.isCur;
				var tmpNode = curSrcNode;
				curSrcNode = null;
				fontCss(tmpNode);
			}
			curSrcNode = treeNode;
			if (!treeNode) return;

			curSrcNode.isCur = true;			
			zTree.cancelSelectedNode();
			fontCss(curSrcNode);
		}
		function copy1(e) {
		
			var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
			nodes = zTree.getSelectedNodes();
			treeNode = nodes[0];
			if (nodes.length == 0) {
				alert("请先选择一个节点");
				return;
			}
			
			
			$.ajax({
            	type: "post",
				url : "/nutrition/nutritionList/tree/copy",
				data: {id:treeNode.id},
	            dataType: "json",
				success : function(data) {
					 if(data.code=="0"){
						 alert('复制成功！');
						 window.location.href="/nutrition/nutritionList/index";
					 }else{
						 alert(data.msg);
					 }
				},error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert(errorThrown);
				}
			});
			
		}
		
		
		function paste(e,treeNode) {
			var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
			nodes = zTree.getSelectedNodes(),
			targetNode = nodes.length>0? nodes[0]:null;
			if (curSrcNode === targetNode) {
				alert("不能移动，源节点与目标节点相同");
				return;
			}else if (curType === "copy") {
				targetNode = zTree.copyNode(targetNode, curSrcNode, "next");
                console.log(targetNode);
                console.log(curSrcNode);
			}
		}
		
		
		
		
          //执行函数
        $(document).ready(function(){
            var treeObj = $("#treeDemo");
            //var treePublic = $("#treepublic");
            $.fn.zTree.init(treeObj, setting);
            //$.fn.zTree.init(treeObj, setting, zNodes_food);
            //-$.fn.zTree.init(treePublic, setting, zNodes_public);
            
            zTree_Menu = $.fn.zTree.getZTreeObj("treeDemo");
            //-zTreePublic_Menu = $.fn.zTree.getZTreeObj("treepublic");
            
            //curMenu = zTree_Menu.getNodes()[0].children[0].children[0];
            //-curMenuPublic = zTreePublic_Menu.getNodes()[0].children[0].children[0];
            //zTree_Menu.selectNode(curMenu);
            //-zTreePublic_Menu.selectNode(curMenu);
    
            treeObj.hover(function () {
                if (!treeObj.hasClass("showIcon")) {
                    treeObj.addClass("showIcon");
                }
            }, function() {
                treeObj.removeClass("showIcon");
            });
            $("#oBtnEdit").bind("click", {type:"rename"}, updateNode);
            $("#modal-classification-remove").bind("click", remove);
            //$("#").bind("click", openPublicCategory);
            $("#oBtnAdd").bind("click", {isParent:false}, add);
            //$("#modal-classification-copy").bind("click", copy);
			
			
          
            
        }); 
    </SCRIPT>

<div class="container increase_food">
	<div class="mbxue uk-width-1-1">
		<ul class="uk-breadcrumb">
			<li>大健康管理系统</li>
			<li><span> 营养维护</span></li>
		</ul>
		<div class="btn_cz">
			<div class="operation_area">
				<input id="cateId" type="hidden" value="0"> <a
					class="uk-button uk-button-primary" onclick="javascript:add1()">新增营养</a>
				<button class="uk-button uk-button-default"
					onclick="javascript:copy()">复制营养</button>
				<button class="uk-button uk-button-default"
					onclick="javascript:edit()">编辑营养</button>
				<button class="uk-button uk-button-default"
					onclick="javascript:batchRemove()">删除营养</button>

			</div>
		</div>
	</div>

	<div class="uk-width-1-1 sxx" uk-grid>
		<div class="left uk-width-1-4">
			<div class="tree_operation">
				<span>营养维护</span>
				<a id="modal-classification-add" href="#modal-classification-add"
					 title="添加分类" uk-toggle><img src="../../resources/djk/images/home01_icon_01.jpg"></a> <a
					onclick="copy1()"  title="复制分类"><img src="../../resources/djk/images/home01_icon_02.jpg"></a> <a
					href="#modal-classification-edit" 
					title="编辑分类" uk-toggle><img src="../../resources/djk/images/home01_icon_03.jpg"></a> <a id="modal-classification-remove"
					 title="删除分类"><img src="../../resources/djk/images/home01_icon_04.jpg"></a>

			</div>
			<ul id="treeDemo" class="ztree"></ul>
		</div>
		<div class="right uk-width-3-4">
			<div class="form_list">
				<!-- <iframe id="list_box" width="100%" height="100%" src=""></iframe> -->
				<table class="bootstrap-table">
				</table>
			</div>
		</div>
	</div>

</div>


<!-- This is the modal -->
<div id="modal-import" uk-modal>
	<div class="uk-modal-dialog uk-modal-body">
		<h2 class="uk-modal-title">批量导入</h2>
		<form>
			<div class="uk-margin">
				<div class="uk-width-1-1" uk-form-custom>
					<input type="file">
					<button class="uk-button uk-width-2-1 uk-button-default"
						type="button" tabindex="-1">下载模板</button>
				</div>
			</div>
			<div class="uk-margin">
				<div class="uk-width-1-1" uk-form-custom>
					<input type="file">
					<button class="uk-button uk-width-2-1 uk-button-default"
						type="button" tabindex="-1">从文件导入</button>
				</div>
			</div>
		</form>

		<p class="uk-text-right">
			<button class="uk-button uk-button-default uk-modal-close"
				type="button">关闭</button>
		</p>
	</div>
</div>
<!-- This is the editing classification modal -->
<div id="modal-classification-edit" uk-modal>
	<div class="uk-modal-dialog uk-modal-body">
		<h2 class="uk-modal-title">编辑</h2>
		<form>
			<div class="uk-margin">
				<label class="uk-form-label" for="form-horizontal-text">所属分类</label>
				<div class="uk-form-controls">
					<input class="uk-input ssfl" id="form-horizontal-text" readonly
						type="text" value="">
				</div>
			</div>
			<div class="uk-margin">
				<label class="uk-form-label" for="form-horizontal-text">类别名称</label>
				<div class="uk-form-controls">
					<input class="uk-input lbmc" id="form-horizontal-text" type="text"
						value="">
				</div>
			</div>
		</form>

		<p class="uk-text-right">
			<button class="uk-button uk-button-default uk-modal-close"
				type="button">关闭</button>
			<button id="oBtnEdit" class="uk-button uk-button-primary"
				type="button">确定</button>
		</p>
	</div>
</div>
<!-- This is the editing classification modal -->
<div id="modal-classification-public-edit" uk-modal>
	<div class="uk-modal-dialog uk-modal-body">
		<h2 class="uk-modal-title">编辑</h2>
		<form>
			<div class="uk-margin">
				<label class="uk-form-label" for="form-horizontal-text">所属分类</label>
				<div class="uk-form-controls">
					<input class="uk-input ssfl" id="form-horizontal-text" readonly
						type="text" value="">
				</div>
			</div>
			<div class="uk-margin">
				<label class="uk-form-label" for="form-horizontal-text">类别名称</label>
				<div class="uk-form-controls">
					<input class="uk-input lbmc" id="form-horizontal-text" type="text"
						value="">
				</div>
			</div>
		</form>

		<p class="uk-text-right">
			<button class="uk-button uk-button-default uk-modal-close"
				type="button">关闭</button>
			<button id="oBtnEditPublic" class="uk-button uk-button-primary"
				type="button">确定</button>
		</p>
	</div>
</div>
<!-- This is the add classification modal -->
<div id="modal-classification-add" uk-modal>
	<div class="uk-modal-dialog uk-modal-body">
		<h2 class="uk-modal-title">添加分类</h2>
		<form>
			<div class="uk-margin">
				<label class="uk-form-label" for="form-horizontal-text">所属分类</label>
				<div class="uk-form-controls">
					<input class="uk-input ssfl" id="form-horizontal-text" readonly
						type="text" value="">
				</div>
			</div>
			<div class="uk-margin">
				<label class="uk-form-label" for="form-horizontal-text">类别名称</label>
				<div class="uk-form-controls">
					<input class="uk-input lbmc" id="form-horizontal-text" type="text"
						value="">
				</div>
			</div>

		</form>

		<p class="uk-text-right">
			<button class="uk-button uk-button-default uk-modal-close"
				type="button">关闭</button>
			<button id="oBtnAdd" class="uk-button uk-button-primary"
				type="button">确定</button>
		</p>
	</div>
</div>
<!-- This is the add classification public modal -->
<div id="modal-classification-public-add" uk-modal class="openAddPublic">
	<div class="uk-modal-dialog uk-modal-body">
		<h2 class="uk-modal-title">添加公共分类</h2>
		<form>
			<div class="uk-margin">
				<label class="uk-form-label" for="form-horizontal-text">所属分类</label>
				<div class="uk-form-controls">
					<input class="uk-input ssfl" id="form-horizontal-text" type="text"
						readolny value="">
				</div>
			</div>
			<div class="uk-margin">
				<label class="uk-form-label" for="form-horizontal-text">类别名称</label>
				<div class="uk-form-controls">
					<input class="uk-input lbmc" id="form-horizontal-text" type="text"
						value="">
				</div>
			</div>
		</form>

		<p class="uk-text-right">
			<button class="uk-button uk-button-default uk-modal-close"
				type="button">关闭</button>
			<button id="oBtnAddPublic" class="uk-button uk-button-primary"
				type="button">确定</button>
		</p>
	</div>
</div>
<!-- This is the copy classification modal -->
<div id="modal-classification-copy" uk-modal class="copyModalSelector">
	<div class="uk-modal-dialog uk-modal-body">
		<h2 class="uk-modal-title">复制分类</h2>
		<form>
			<div class="uk-margin">
				<label class="uk-form-label" for="form-horizontal-text">所属分类</label>
				<div class="uk-form-controls">
					<input class="uk-input ssfl" id="form-horizontal-text" type="text"
						value="">
				</div>
			</div>
			<div class="uk-margin">
				<label class="uk-form-label" for="form-horizontal-text">类别名称</label>
				<div class="uk-form-controls">
					<input class="uk-input lbmc" id="form-horizontal-text" type="text"
						value="">
				</div>
			</div>
		</form>

		<p class="uk-text-right">
			<button class="uk-button uk-button-default uk-modal-close"
				type="button">关闭</button>
			<button id="oBtnCopy" class="uk-button uk-button-primary"
				type="button">确定</button>
		</p>
	</div>
</div>
<!-- This is the public classification modal -->
<div id="modal-classification-public" uk-modal class="modalSelector">
	<div class="uk-modal-dialog uk-modal-body">
		<h2 class="uk-modal-title"></h2>
		<div class="tree_operation">
			<a id="modal-classification-public-add" onclick="openAddPublic()"
				uk-icon="icon: plus" title="添加分类"></a> <a
				href="#modal-classification-public-edit" uk-icon="icon: file-edit"
				title="编辑分类" uk-toggle></a> <a
				id="modal-classification-public-remove" uk-icon="icon: trash"
				title="删除分类"></a> <a class="uk-modal-close" uk-icon="icon: close"
				title="关闭"></a>
		</div>
		<ul id="treepublic" class="ztree"></ul>
	</div>
</div>
<!-- This is the deleting modal -->
<div id="modal-deleting" uk-modal>
	<div class="uk-modal-dialog uk-modal-body">
		<h2 class="uk-modal-title">删除确认</h2>
		<p>删除后数据不能恢复，您确认要删除吗？</p>
		<p class="uk-text-right">
			<button class="uk-button uk-button-default uk-modal-close"
				type="button">取消</button>
			<button class="uk-button uk-button-primary" type="button">确定</button>
		</p>
	</div>
</div>
<script src="../../static/main/js/ea-table.js?v=1.1.6"></script>
<script src="../../static/main/system/newnutrition/nutrition.js"></script>

<script type="text/javascript">
function searchTable(id){
	
	
	$("#cateId").val(id);
	
	
	$('.bootstrap-table').bootstrapTable('refresh', {
        url: '/nutrition/nutritionList/list?cateId='+$('#cateId').val(), 
    });
 
    
	
}

</script>


<jsp:include page="../foot.jsp" />



