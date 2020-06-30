<%@ page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="/WEB-INF/tld/shiro.tld"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>九宏大健康管理系统</title>
<link rel="stylesheet" href="../../../resources/djk/css/uikit.min.css">
<link rel="stylesheet" href="../../../resources/djk/css/zTreeStyle.css"
	type="text/css">

<!-- Data Tables -->
<link rel="shortcut icon" href="favicon.ico">
<link href="../../../resources/djk/css/bootstrap.min.css?v=3.3.6"
	rel="stylesheet">
<!-- 自定义css -->
<link rel="stylesheet" href="../../../resources/djk/css/style.css"
	type="text/css">
<link href="../../../resources/djk/css/dataTables.bootstrap.css"
	rel="stylesheet">
<!-- jquery -->
<script type="text/javascript"
	src="../../../resources/djk/js/jquery.min.js?v=2.1.4"></script>
<!-- uikit框架引用js -->
<script src="../../../resources/djk/js/uikit.min.js"></script>
<script src="../../../resources/djk/js/uikit-icons.min.js"></script>
<!-- zTree引用js -->
<script type="text/javascript"
	src="../../../resources/djk/js/jquery.ztree.all.min.js"></script>
<!-- Data Tables -->
<script src="../../../resources/djk/js/jquery.jeditable.js"></script>
<script src="../../../resources/djk/js/jquery.dataTables.js"></script>
<script src="../../../resources/djk/js/dataTables.bootstrap.js"></script>


<link href="../../../static/css/bootstrap.min.css" rel="stylesheet" /> 
<link href="../../../static/css/font-awesome.css" rel="stylesheet" />
<!-- bootstrap-table 表格插件样式 -->
<link href="../../../static/ajax/libs/bootstrap-table/bootstrap-table.min.css" rel="stylesheet" />
<link href="../../../static/ajax/libs/jqTreeGrid/jquery.treegrid.css" rel="stylesheet" />
<link href="../../../static/css/animate.css" rel="stylesheet" />
<link href="../../../static/css/style.css" rel="stylesheet" />
<link href="../../../static/css/checkbox.css" rel="stylesheet" />



 <!-- <script src="../../static/js/jquery.min.js" ></script> -->
	<!-- <script src="../../static/js/bootstrap.min.js" ></script> -->
	
	<!-- bootstrap-table 表格插件 -->
	<script src="../../../static/ajax/libs/bootstrap-table/bootstrap-table.min.js" ></script>
	<script src="../../../static/ajax/libs/bootstrap-table/locale/bootstrap-table-zh-CN.min.js" ></script>
	<script src="../../../static/ajax/libs/bootstrap-table/extensions/mobile/bootstrap-table-mobile.min.js" ></script>
	<script src="../../../static/ajax/libs/bootstrap-table/extensions/toolbar/bootstrap-table-toolbar.min.js" ></script>
	<!-- jquery-validate 表单验证插件 -->
	<script src="../../../static/ajax/libs/validate/jquery.validate.min.js" ></script>
	<script src="../../../static/ajax/libs/validate/messages_zh.min.js" ></script>
	<script src="../../../static/ajax/libs/validate/jquery.validate.extend.js"></script>
	<!-- jquery-validate 表单树插件 -->
	<script src="../../../static/ajax/libs/jqTreeGrid/jquery.treegrid.min.js" ></script>
	<script src="../../../static/ajax/libs/jqTreeGrid/jquery.treegrid.extension.js" ></script>
	<!-- jquery-export 表格导出插件 -->
	<script src="../../../static/ajax/libs/bootstrap-table/extensions/export/bootstrap-table-export.js" ></script>
	<script src="../../../static/ajax/libs/bootstrap-table/extensions/export/tableExport.js" ></script>
	<script src="../../../static/ajax/libs/layer/layer.min.js" ></script>
	<script src="../../../static/main/js/common.js?v=1.1.6" ></script>
	<script src="../../../static/main/js/ea-ui.js?v=1.1.6" ></script>  
    <script src="../../../static/main/js/ea-table.js?v=1.1.6" ></script>  
  <SCRIPT type="text/javascript">
  
  //==============================食物维护===========================
	  var prefix = "/foodlistbygategoryid?foodCategoryId=<%=request.getParameter("foodCategoryId")%>"
	$(function() {
		
		  var  columns = [{
	          checkbox: true
	      },
	      {
	          field: 'id',
	          title: 'ID'
	      },
	        {
	            field: 'name',
	            title: '食物名称'
	        },
	        {
	            field: 'alias',
	            title: '别名'
	        },
	        {
	            field: 'firstCategoryName',
	            title: '食物类别'
	        },
	        {
	            field: 'firstCategoryName',
	            title: '公共分类'
	        },
	        {
	            field: 'unitName',
	            title: '单位'
	        },
	        {
	            field: 'firstOriginName',
	            title: '产地'
	        },
	        {
	            field: 'seasonName',
	            title: '时令'
	        },
	        {
	            field: 'supplierName',
	            title: '供应商'
	        }
	        ];
		
		
		var url = prefix;
		$.initTable(columns, url);
	});
	
	function add() {
		window.location.href="/foodadd";
	}
	
	function editFood() {
	  //使用getSelections即可获得，row是json格式的数据
		var rows = $(".bootstrap-table").bootstrapTable('getSelections', function (row) {
		          return row;
		});
		//var rows = $.getSelections("roleId");
		if (rows.length == 0) {
			$.modalMsg("请选择要修改的数据", modal_status.WARNING);
			return;
		}
		//alert(rows[0].id);
	    var url = prefix + '/edit?id=' + rows[0].id;
	  	  //foodedit
	}
	
	function deleteFood() {
		var rows = $(".bootstrap-table").bootstrapTable('getSelections', function (row) {
	        return row;
	    });
		
		if (rows.length == 0) {
			$.modalMsg("请选择要删除的数据", modal_status.WARNING);
			return;
		}
		$.modalConfirm("确认要删除选中的【" + rows[0].name + "】条数据吗?", function() {
			_ajax('/deletefood', { "id": rows[0].id }, "post");
		});
	}
  //===========================================================
  
        var curMenu = null, zTree_Menu = null;
        var setting = {
            view: {
                showLine: true,
                showIcon: false
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
                onClick: zTreePublicOnClick,
                beforeRemove: beforeRemove
            },
            async: {  
                enable: true, // 设置 zTree是否开启异步加载模式  加载全部信息  
                type: "post",  
                url : '/foodcategory/list',
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
            searchTable(treeNode.id);
            //$("#list_box").attr('src',"/foodlist?foodCategoryId="+treeNode.id);
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
        
        function searchTable(id){
        	
        	$('.bootstrap-table').bootstrapTable('refresh', {
                url: '/foodlistbygategoryid?foodCategoryId='+id, 
            });
        	
        }
        
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
            $("#modal-classification-public-add .lbmc").val('');

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
				url : "/foodcategory/update",
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
				url : "/foodcategory/validateChild",
				data: {id:treeNode.id},
	            dataType: "json",
				success : function(data) {
					if(data == false) {

						$.ajax({
			            	type: "post",
							url : "/foodcategory/delete",
							data: {id:treeNode.id},
				            dataType: "json",
							success : function(data) {
								if(data == true) {
									zTree.removeNode(treeNode, true);
									alert("删除成功");
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
        
        
        function removePublic(e) {
			var zTree = $.fn.zTree.getZTreeObj("treepublic"),
			nodes = zTree.getSelectedNodes(),
			treeNode = nodes[0];
			if (nodes.length == 0) {
				alert("请先选择一个节点");
				return;
			}
			if('公共分类' == treeNode.name) {
				alert("公共分类不能删除");
				return;
			}
			if(treeNode.isParent == true) {
				alert("分类有子分类，不能删除");
				return;
			}
			$.ajax({
            	type: "post",
				url : "/foodcategory/validatepublicchild",
				data: {id:treeNode.id},
	            dataType: "json",
				success : function(data) {
					if(data == false) {

						$.ajax({
			            	type: "post",
							url : "/foodcategory/deletepublic",
							data: {id:treeNode.id},
				            dataType: "json",
							success : function(data) {
								if(data.success == 'true') {
									zTree.removeNode(treeNode, true);
									alert(data.msg);
								}else{
									alert(data.msg);
									return;
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
            var addname = $("#modal-classification-add .lbmc").val();
            if (nodes.length == 0) {
				alert("请先选择一个节点");
				return;
			}
            
            var publicCategoryCopy = $("input[type='checkbox']").is(':checked')

            $.ajax({
            	type: "post",
				url : "/foodcategory/add",
				data: {pid:treeNode.id, name:addname, publicCategoryCopy:publicCategoryCopy},
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
						alert("添加成功");
				},error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert(errorThrown);
				}
			});
        };
        
        var foodCategoryId = '';
        
        // 添加节点
		function add_Public(e) {
            var zTree = $.fn.zTree.getZTreeObj("treepublic"),
			isParent = e.data.isParent,
			nodes = zTree.getSelectedNodes(),
            treeNode = nodes[0];
            var addname = $("#modal-classification-public-add .lbmc").val();
            if (nodes.length == 0) {
				alert("请先选择一个节点");
				return;
			} 
			var pid = '';
			if (nodes.length == 0) {
				pid =1;
			}else{
				pid=treeNode.id;
			}
            
            if(addname == null || addname == '') {
	            //alert("请输入分类名称");
	            $("#public_category_validate_value_add").text("不能为空");
	            return ;
            }

            $.ajax({
            	type: "post",
				url : "/foodcategory/addpublic",
				data: {pid:pid, name:addname, foodCategoryId:window.parent.foodCategoryId},
	            dataType: "json",
				success : function(data) {
					//debugger;
					if (treeNode) {
						treeNode = zTree.addNodes(treeNode, {id:(data.id + newCount), pid:data.pid, isParent:isParent, name:addname });
						zTree.reAsyncChildNodes(null, "refresh");
					} else {
						treeNode = zTree.addNodes(null, {id:(data.id + newCount), pId:0, isParent:isParent, name:addname });
					}
					if (treeNode) {
						zTree.editName(treeNode[0]);
					} else {
						alert("叶子节点被锁定，无法增加子节点");
		            }
		            $(".uk-modal").removeClass("uk-open");
					alert("添加成功");
				},error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert(errorThrown);
				}
			});
           
        };
        
        function updateNodePublic(e) {
            var nameCount = $("#modal-classification-public-edit .lbmc").val();
			var zTree = $.fn.zTree.getZTreeObj("treepublic"),
			type = e.data.type,
			nodes = zTree.getSelectedNodes();
			if (nodes.length == 0) {
				alert("请先选择一个节点");
			}
			if('公共分类' == nodes[0].name) {
				alert("公共分类不能修改");
				return;
			}
		   $.ajax({
            	type: "post",
				url : "/foodcategory/updatepublic",
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
        
        // 添加公共分类节点
		function openAddPublic() {
			 var zTree = $.fn.zTree.getZTreeObj("treepublic"),
				nodes = zTree.getSelectedNodes(),
	            treeNode = nodes[0];
	           
	           
	            var modal = UIkit.modal(".openAddPublic");
	            modal.show();

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
		function copy(e) {
			var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
			nodes = zTree.getSelectedNodes();
			treeNode = nodes[0];
			if (nodes.length == 0) {
				alert("请先选择一个节点");
				return;
			}
			
			$.ajax({
            	type: "post",
				url : "/foodcategory/copy",
				data: {id:treeNode.id},
	            dataType: "json",
				success : function(data) {
					 $("#modal-classification-copy .ssfl").val(treeNode.getParentNode().name);
			         $("#modal-classification-copy .lbmc").val(data.name);
			  
			         var modal = UIkit.modal(".copyModalSelector");
			            modal.show();
			     	if (treeNode) {
						treeNode = zTree.addNodes(treeNode.getParentNode(), {id:(data.id), pId:treeNode.getParentNode().id, isParent:true,  name:data.name });
					} else {
						treeNode = zTree.addNodes(null, {id:(data.id), pId:0,  name:data.name });
					}
				},error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert(errorThrown);
				}
			});
			
		}
		
		function copySave() {
			   var nameCount = $("#modal-classification-public-edit .lbmc").val();
				var zTree = $.fn.zTree.getZTreeObj("treepublic"),
				type = e.data.type,
				nodes = zTree.getSelectedNodes();
				if (nodes.length == 0) {
					alert("请先选择一个节点");
				}
				
			   $.ajax({
	            	type: "post",
					url : "/foodcategory/updatepublic",
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
    
            var public_setting = {
                    view: {
                        showLine: true,
                        showIcon: false,
                        selectedMulti: false,
                        dblClickExpand: false
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
                        onClick: zTreePublicOnClick,
                        beforeRemove: beforeRemove
                    },
                    async: {  
                        enable: true, // 设置 zTree是否开启异步加载模式  加载全部信息  
                        type: "post",  
                        url : '/foodcategory/publiccategory/list?foodCategoryId='+window.parent.foodCategoryId,
                        autoParam : [ "id", "pid"],
                        isSimpleData: true, // 简单数据模型，必须提供下面的两项
                        treeNodeKey: "id", // 节点的id
                        treeNodeParentKey: "pid",  // 父节点的id
                        dataFilter: filter  
                        },

                };
            
            var obj = $('#treepublic', parent.document);
            console.log(obj); 
            var treePublic = $("#treepublic");
            console.log(treePublic); 
            $.fn.zTree.init(treePublic, public_setting);
            zTreePublic_Menu = $.fn.zTree.getZTreeObj("treepublic");
            treePublic.hover(function () {
                if (!treePublic.hasClass("showIcon")) {
                	treePublic.addClass("showIcon");
                }
            }, function() {
            	treePublic.removeClass("showIcon");
            });
            $("#oBtnAddPublic").bind("click", {isParent:false}, add_Public);
            $("#oBtnEditPublic").bind("click", {type:"rename"}, updateNodePublic);
            
        }); 
        
    </SCRIPT>   
</head>
  	<style>
  	#treepublic{
  	    min-height: 380px;
  	    max-height:390px;
  	    overflow-y: auto;
  	}
  	body {
	    background: #fff;
	}
	.uk-modal {
    background: rgba(0,0,0,.2);
}
    .uk-modal{
      opacity: 0;
      display: inline-block;
      transition: all .5s;
      pointer-events: none;
    }

    .uk-modal.uk-open{
      opacity: 1;
      pointer-events: initial;
    }
  	</style>
    <body>
		 <h2 class="uk-modal-title"></h2>
		 <div class="tree_operation">
		     <a id="modal-classification-add" href="#modal-classification-public-add"   title="添加分类" uk-toggle><img src="../../resources/djk/images/home01_icon_01.jpg"></a>
		     <a href="#modal-classification-public-edit"   title="编辑分类" uk-toggle><img src="../../resources/djk/images/home01_icon_03.jpg"></a>
		     <a id="modal-classification-remove" onclick="removePublic()"  title="删除分类"><img src="../../resources/djk/images/home01_icon_04.jpg"></a>
		     <a class="uk-modal-close" onclick="successModal()"  title="关闭"><img src="../../resources/djk/images/home01_icon_06.jpg"></a>
		 </div>
		 <ul id="treepublic" class="ztree"></ul>
		 <!-- This is the add classification public modal -->
    <div id="modal-classification-public-add" uk-modal class="openAddPublic">
        <div class="uk-modal-dialog uk-modal-body">
            <h2 class="uk-modal-title">添加公共分类</h2>
            <form>
                <div class="uk-margin">
                    <label class="uk-form-label" for="form-horizontal-text">所属分类</label>
                    <div class="uk-form-controls">
                        <input class="uk-input ssfl" id="form-horizontal-text" type="text" readonly="readonly" value="">
                    </div>
                </div>
                <div class="uk-margin">
                    <label class="uk-form-label" for="form-horizontal-text">类别名称</label>
                    <div class="uk-form-controls">
                        <input class="uk-input lbmc" id="form-horizontal-text" type="text" value="" maxlength="15">
                    </div>
                    <div id="public_category_validate_value_add" style="color: red"></div>
                </div>
            </form>
            
            <p class="uk-text-right">
                <button class="uk-button uk-button-default uk-modal-close" type="button">关闭</button>
                <button id="oBtnAddPublic" class="uk-button uk-button-primary" type="button">确定</button>
            </p>
        </div>
    </div>
		    <!-- This is the editing classification modal -->
   <!-- This is the editing classification modal -->
    <div id="modal-classification-public-edit" uk-modal>
        <div class="uk-modal-dialog uk-modal-body">
            <h2 class="uk-modal-title">编辑</h2>
            <form>
                <div class="uk-margin">
                    <label class="uk-form-label" for="form-horizontal-text">所属分类</label>
                    <div class="uk-form-controls">
                        <input class="uk-input ssfl" id="form-horizontal-text" readonly="readonly" type="text" value="">
                    </div>
                </div>
                <div class="uk-margin">
                    <label class="uk-form-label" for="form-horizontal-text">类别名称</label>
                    <div class="uk-form-controls">
                        <input class="uk-input lbmc" id="form-horizontal-text" type="text" value="" maxlength="15">
                        <input type="hidden" id="public_category_value_old">
                    </div>
                    <div id="public_category_validate_value_edit" style="color: red"></div>
                </div>
            </form>
            
            <p class="uk-text-right">
                <button class="uk-button uk-button-default uk-modal-close" type="button">关闭</button>
                <button id="oBtnEditPublic" class="uk-button uk-button-primary" type="button">确定</button>
            </p>
        </div>
    </div>
        </body>
        <script>
		  function successModal(){
			  $(window.parent.document).find("#modal-classification-public").removeClass("uk-open");
		  }
		</script>
        <script type="text/javascript">
	$(document).ready(function(){
		$('.nav-menu-content-box').hover(function() {
			$(this).addClass('dow-hover');
			$(".nav-menu-content").show('slow');
		}, function() {
			$(this).removeClass('dow-hover');
		});

	});
	var maxHeight = 400;
	$(function(){
		$(".nav-menu-content-box > li").hover(function() {

			var $container = $(this),
				$list = $container.find("ul"),
				$anchor = $container.find("a"),
				height = $list.height() * 1.1,
				multiplier = height / maxHeight;

			$container.data("origHeight", $container.height());

			$anchor.addClass("hover");

			$list
				.show()
				.css({
					paddingTop: $container.data("origHeight")
				});

			if (multiplier > 1) {
				$container
					.css({
						height: maxHeight,
						overflow: "hidden"
					})
					.mousemove(function(e) {
						var offset = $container.offset();
						var relativeY = ((e.pageY - offset.top) * multiplier) - ($container.data("origHeight") * multiplier);
						if (relativeY > $container.data("origHeight")) {
							$list.css("top", -relativeY + $container.data("origHeight"));
						};
					});
			}

		}, function() {

			var $el = $(this);

			$el
				.height($(this).data("origHeight"))
				.find("ul")
				.css({ top: 0 })
				.hide()
				.end()
				.find("a")
				.removeClass("hover");

		});

	});
   

</script>
        </html>
        