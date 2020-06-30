<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="UTF-8"  %>
<%@taglib prefix="shiro" uri="/WEB-INF/tld/shiro.tld" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%
String foodCategoryName = (String)request.getAttribute("foodCategoryName");
String pIds = (String)request.getAttribute("pIds");
Integer foodCategoryId = (Integer)request.getAttribute("foodCategoryId");
Integer foodCategoryPId = (Integer)request.getAttribute("foodCategoryPId");
System.out.println("foodCategoryName="+foodCategoryName);
%>
<jsp:include page="../head.jsp" />
 <style>
.navMenu-item:nth-child(1) .navMenu-title {
    background: #c38447;
    color: #fff;
}
.navMenu-item:nth-child(1) .navMenu-item-content li:nth-child(1) a{
    color: #622f00;
   font-weight: bold;
    
}
.increase_food .sxx{
    padding: 0;
}
html, body {
    height: 100%;
}
#modal-classification-public .uk-modal-body{
min-height: 500px;
    width: 800px;
}
iframe{
    height: 100%;
    width: 100%;
    position: absolute;
    bottom: 0;
    top: 0;
    left: 0;
    right: 0;
}
#modal-classification-public{
      opacity: 0;
      display: inline-block;
      transition: all .5s;
      pointer-events: none;
    }

    #modal-classification-public.uk-open{
      opacity: 1;
      pointer-events: initial;
    }
    .layui-layer{
width: 1200px !important;
}
</style>
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
	          field: 'codeValue',
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
	            field: 'publicCategoryNames',
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
	        },
	        {
	            title : '操作',
	            width : '20%',
	            align : "center",
	            formatter : function(row, index) {
	            	
	              var actions = [];
	              actions.push('<a class="btn btn-info btn-xs "  href="#" onclick="brower(\'' + index.id + '\')"><i class=""></i>详细信息</a> ');
	              return actions.join('');
	            }
	        }
	        ];
		
		var url = prefix;
		$.initTable(columns, url);
		//隐藏列
		$('.bootstrap-table').bootstrapTable('hideColumn', 'id');   
	});
	  
	function brower(id) {
		  var url = '/foodbrower?id=' + id;
		  layer_showAuto("食物详细信息", url);
	}
	
	function add() {
		window.location.href="/foodadd";
	}
	// 编辑食物
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
	    window.location.href='/foodedit?id=' + rows[0].id;
	}
	
	function addFood() {
		window.location.href='/foodadd?firstCategoryId=' +$("#firstCategoryId").val() +"&firstCategoryName="+$("#firstCategoryName").val();
		
	}
	
	// 复制食物
	function copyFood() {
	  //使用getSelections即可获得，row是json格式的数据
		var rows = $(".bootstrap-table").bootstrapTable('getSelections', function (row) {
		          return row;
		});
		//var rows = $.getSelections("roleId");
		if (rows.length == 0) {
			$.modalMsg("请选择要复制的数据", modal_status.WARNING);
			return;
		}
		//window.location.href='/foodcopy?foodId='+rows[0].id;
	    //_ajax('/foodcopy', { "foodId": rows[0].id }, "post");
		$.modalConfirm("复制食物【" + rows[0].name + "】", function() {
			_ajax('/foodcopy', { "foodId": rows[0].id }, "post");
		});
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
                onClick: zTreeOnClick,
                beforeRemove: beforeRemove,
                onAsyncSuccess:zTreeOnAsyncSuccess//树加载成功时的回调函数
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
        
        function zTreeOnNodeCreated(event, treeId, treeNode) {
       	  var param = $.trim($("input[name='param']").val());
       	  var treeObj = $.fn.zTree.getZTreeObj("tree");
       	  //只有搜索参数不为空且该节点为父节点时才进行异步加载
       	  if(param != "" && treeNode.isParent){
       	    treeObj.reAsyncChildNodes(treeNode, "refresh");
       	  } 
       	}
        
        function zTreeOnAsyncSuccess(){
        	
        	var zTreeObj = $.fn.zTree.getZTreeObj("treeDemo");
      	    // 这个方法是将标准 JSON 嵌套格式的数据转换为简单 Array 格式
      	    var nodes = zTreeObj.transformToArray(zTreeObj.getNodes()); 
      	    for (var i = 0; i < nodes.length; i++) {
      	        // 判断节点是否已经加载过，如果已经加载过则不需要再加载
      	        if (!nodes[i].zAsync) {
      	            zTreeObj.reAsyncChildNodes(nodes[i], '', true);
      	        }
      	    }   
        	
            var treeObj =  $.fn.zTree.getZTreeObj("treeDemo");
            var pIdArr = '<%=pIds%>'.split(",");
            //获取父节点为ROOT的节点，若节点没有加载子节点，则强制加载子节点
 
            var nodes = treeObj.getNodesByParamFuzzy("name", $.trim('<%=foodCategoryName%>'), null);
            if(nodes != null && undefined != nodes   && nodes != '') {
            	treeObj.expandNode(nodes[0]);//展开指定节点
            	treeObj.selectNode(nodes[0]);//选中指定节点
            	treeObj.setting.callback.onClick(null, nodes[0].id, nodes[0]);//触发函数 */
            }
          
        }
        
        
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
            
        	$("#firstCategoryId").val(treeNode.id);
    		$("#firstCategoryName").val(treeNode.name);
            
            $("#modal-classification-add .ssfl").val('');
            $("#modal-classification-add .lbmc").val('');
            $("#validatacategoryname").text('');
            //$("#list_box").attr('src',"/foodlist?foodCategoryId="+treeNode.id);
            if(treeNode.getParentNode() == null || treeNode.getParentNode() == 'null') {
            	$("#modal-classification-edit .ssfl").val(treeNode.name);
            }else{
	            $("#modal-classification-edit .ssfl").val(treeNode.getParentNode().name);
            }
	            $("#modal-classification-edit .lbmc").val(treeNode.name);
	            $("#updatevalidatacategoryname_lbmc_old").val(treeNode.name);
	            $("#updatevalidatacategoryname_value").val(treeNode.name);
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
            	$("#modal-classification-public-add .ssfl").val(treeNode.name);
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
            var oldName = $("#updatevalidatacategoryname_lbmc_old").val();
			var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
			type = e.data.type,
			nodes = zTree.getSelectedNodes();
			if (nodes.length == 0) {
				alert("请先选择一个节点");
			}
			
			if(nameCount == null || nameCount == '') {
				$("#updatevalidatacategoryname_value").text("不能为空");
				return;
			}
			
			if(oldName == nameCount) {
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
			}else{
				 $.ajax({
		            	type: "post",
						url : "/foodcategory/validatacategoryname",
						data: {name:nameCount},
			            dataType: "json",
						success : function(data) {
							if(data) {
								$("#updatevalidatacategoryname_value").text("不能重复");
								return;
							}else{
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
							
						},error : function(XMLHttpRequest, textStatus, errorThrown) {
							alert(errorThrown);
						}
					});
			}
			
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
								}else{
									alert("食物分类已被引用，不能删除");
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
            
            $("#validatacategoryname").text('');
            
            if(addname == null || addname == '') {
            	$("#validatacategoryname").text('不能为空');
            	return;
            }
            
            var publicCategoryCopy = $("input[type='checkbox']").is(':checked')

            $.ajax({
            	type: "post",
				url : "/foodcategory/validatacategoryname",
				data: {name:addname},
	            dataType: "json",
				success : function(data) {
					if(data) {
						$("#validatacategoryname").text('不能重复');
		            	return;
					}else{
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
					}
					
					
				},error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert(errorThrown);
				}
			});
           
        };
        
        var foodCategoryId = '';
        
        // 添加节点
		function addPublic(e) {
            var zTree = $.fn.zTree.getZTreeObj("treepublic"),
			isParent = e.data.isParent,
			nodes = zTree.getSelectedNodes(),
            treeNode = nodes[0];
            var addname = $("#modal-classification-public-add .lbmc").val();
           /*  if (nodes.length == 0) {
				alert("请先选择一个节点");
				return;
			} */
			var pid = '';
			if (nodes.length == 0) {
				pid =1;
			}else{
				pid=treeNode.id;
			}
            
            if(addname == null || addname == '') {
	            alert("请输入分类名称");
	            return ;
            }

            $.ajax({
            	type: "post",
				url : "/foodcategory/addpublic",
				data: {pid:pid, name:addname, foodCategoryId:foodCategoryId},
	            dataType: "json",
				success : function(data) {
					if (treeNode) {
						treeNode = zTree.addNodes(treeNode, {id:(data.id + newCount), pId:treeNode.id, isParent:isParent, name:addname });
					} else {
						treeNode = zTree.addNodes(null, {id:(data.id + newCount), pId:0, isParent:isParent, name:addname });
					}
					if (treeNode) {
						zTree.editName(treeNode[0]);
					} else {
						alert("叶子节点被锁定，无法增加子节点");
		            }
		            //$(".uk-modal").removeClass("uk-open");
		            $(".uk-modal").css("display","none");
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
			$("#copyvalidatacategoryname").text('');
			$.ajax({
            	type: "post",
				url : "/foodcategory/copy",
				data: {id:treeNode.id},
	            dataType: "json",
				success : function(data) {
					 $("#modal-classification-copy .ssfl").val(treeNode.getParentNode().name);
			         $("#modal-classification-copy .lbmc").val(data.name);
			         $("#copyvalidatacategoryname_lbmc_old").val(data.name);
			         $("#modal-classification-copy-node-id").val(data.id);
			  
			         var modal = UIkit.modal(".copyModalSelector");
			            modal.show();
			     	if (treeNode) {
						treeNode = zTree.addNodes(treeNode.getParentNode(), {id:(data.id), pId:treeNode.getParentNode().id, isParent:false,  name:data.name });
					} else {
						treeNode = zTree.addNodes(null, {id:(data.id), pId:0,  name:data.name });
					}
				},error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert(errorThrown);
				}
			});
			
		}
		
		function copySave() {
			var nameCount = $("#modal-classification-copy .lbmc").val();
			var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
			//type = e.data.type,
			nodes = zTree.getSelectedNodes();
			if (nodes.length == 0) {
				alert("请先选择一个节点");
			}
			
			var addname = $("#modal-classification-copy .lbmc").val();
            	$("#copyvalidatacategoryname").text('');
			if(addname == null || addname == '') {
            	$("#copyvalidatacategoryname").text('不能为空');
            	return;
	        }
			
			if($("#copyvalidatacategoryname_lbmc_old").val() == addname) {
				$.ajax({
	            	type: "post",
					url : "/foodcategory/update",
					data: {id:$("#modal-classification-copy-node-id").val(), name:nameCount},
		            dataType: "json",
					success : function(data) {
						for (var i=0, l=nodes.length; i<l; i++) {
							zTree.setting.view.fontCss = {};
							/* if (type == "rename") {
								nodes[i].name = nameCount;
							} */
							nodes[i].name = nameCount;
							var node = zTree.getNodeByParam("id",$("#modal-classification-copy-node-id").val() );
							zTree.removeNode(node);
						 	if (nodes[i]) {
						 		nodes[i] = zTree.addNodes(nodes[i].getParentNode(), {id:(data.id), pId:nodes[i].getParentNode().id, isParent:false,  name:data.name });
							} else {
								nodes[i] = zTree.addNodes(null, {id:(data.id), pId:0,  name:data.name });
							}
						}
			            $(".uk-modal").removeClass("uk-open");
			            $(".uk-modal").css("display","none");
							alert("保存成功");
					},error : function(XMLHttpRequest, textStatus, errorThrown) {
						alert(errorThrown);
					}
				});
			}else{
			 $.ajax({
	            	type: "post",
					url : "/foodcategory/validatacategoryname",
					data: {name:addname},
		            dataType: "json",
					success : function(data) {
						if(data) {
							$("#copyvalidatacategoryname").text('不能重复');
			            	return;
						}else{
							$.ajax({
				            	type: "post",
								url : "/foodcategory/update",
								data: {id:$("#modal-classification-copy-node-id").val(), name:nameCount},
					            dataType: "json",
								success : function(data) {
									for (var i=0, l=nodes.length; i<l; i++) {
										zTree.setting.view.fontCss = {};
										/* if (type == "rename") {
											nodes[i].name = nameCount;
										} */
										nodes[i].name = nameCount;
										var node = zTree.getNodeByParam("id",$("#modal-classification-copy-node-id").val() );
										zTree.removeNode(node);
									 	if (nodes[i]) {
									 		nodes[i] = zTree.addNodes(nodes[i].getParentNode(), {id:(data.id), pId:nodes[i].getParentNode().id, isParent:false,  name:data.name });
										} else {
											nodes[i] = zTree.addNodes(null, {id:(data.id), pId:0,  name:data.name });
										}
									}
						            $(".uk-modal").removeClass("uk-open");
						            $(".uk-modal").css("display","none");
										alert("保存成功");
								},error : function(XMLHttpRequest, textStatus, errorThrown) {
									alert(errorThrown);
								}
							});
						}
						
					},error : function(XMLHttpRequest, textStatus, errorThrown) {
						alert(errorThrown);
					}
				});
			}
			
		   
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
		
		function openPublicCategory(e) {
			　//$('#public_category_iframe').attr('src', $('#public_category_iframe').attr('src'));
			 document.getElementById('public_category_iframe').contentWindow.location.reload(true);
			//$("#modal-classification-public").text('')
			 var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
				//isParent = e.data.isParent,
				nodes = zTree.getSelectedNodes(),
	            treeNode = nodes[0];
	            if (nodes.length == 0) {
					alert("请先选择一个节点");
					return;
				}
	            //alert(document.getElementById('public_category').document.getElementById('treepublic'))
	            foodCategoryId = treeNode.id;
	           	
	           /*  foodCategoryId = treeNode.id;
	            var public_setting = {
	                    view: {
	                        showLine: true,
	                        showIcon: true,
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
	                        url : '/foodcategory/publiccategory/list?foodCategoryId='+foodCategoryId,
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
	            }); */
	           
	            var modal = UIkit.modal(".modalSelector");
	            modal.show();

	            
		}
		
		
          //执行函数
        $(document).ready(function(){
            var treeObj = $("#treeDemo");
            //var treePublic = $("#treepublic");
            var zTreeObj  = $.fn.zTree.init(treeObj, setting);
           
            console.log(zTree_Menu);
            
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
			$("#oBtnCopy").bind("click", copySave);
			
            $("#oBtnEditPublic").bind("click", {type:"rename"}, updateNodePublic);
            $("#modal-classification-public-remove").bind("click", removePublic);
            $("#oBtnAddPublic").bind("click", {isParent:false}, addPublic);
            
           // var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
            //debugger;
           // var node = treeObj.getNodeByParam("id", "113");
           // alert(zTree_Menu.getNodes()[0].children[0].children[0]);
            //treeObj.selectNode(node);
            //setting.callback.onClick = onClick;

            //要执行的方法
        }); 
           // function onClick() {alert();}
        
    </SCRIPT>     
  <my:token></my:token>
 <div class="container increase_food" >
            <div class="mbxue uk-width-1-1">
                <ul class="uk-breadcrumb">
                    <li>大健康管理系统</li>
                    <li><span> 食物维护</span></li>
                </ul>
                <div class="btn_cz">
                    <div class="operation_area">
                     	<input type="hidden"  name="firstCategoryName" id="firstCategoryName">
                        <input type="hidden" name="firstCategoryId" id="firstCategoryId">
                        <a href="#" onclick="addFood()" class="uk-button uk-button-primary">新增食物</a>
                       <!--  <button class="uk-button uk-button-default">复制食物</button> -->
                        <a href="#" onclick="copyFood()" class="uk-button uk-button-default">复制食物</a>
                        <a href="#" onclick="editFood()" class="uk-button uk-button-default">编辑食物</a>
                        <a href="#" onclick="deleteFood()" class="uk-button uk-button-default">删除食物</a>
                        <!-- <button class="uk-button uk-button-default" uk-toggle="target: #modal-deleting">删除食物</button> -->
                        <!-- <button class="uk-button uk-button-text" style="margin: 0 0 0 10px;"  uk-toggle="target: #modal-import">批量导入</button> -->
                    </div>
                </div>
            </div>
            
       
            
            <div class="uk-width-1-1 sxx" uk-grid>
                <div class="left uk-width-1-4">
                <div class="tree_operation">
                	<span>食物类别</span>
                    <a id="modal-classification-add" href="#modal-classification-add"  title="添加分类" uk-toggle><img src="../../resources/djk/images/home01_icon_01.jpg"></a>
                    <a onclick="copy()"  title="复制分类"><img src="../../resources/djk/images/home01_icon_02.jpg"></a>
                    <a href="#modal-classification-edit"  title="编辑分类" uk-toggle><img src="../../resources/djk/images/home01_icon_03.jpg"></a>
                    <a id="modal-classification-remove"  title="删除分类"><img src="../../resources/djk/images/home01_icon_04.jpg"></a>
                    <a   onclick="openPublicCategory()" ><img src="../../resources/djk/images/home01_icon_05.jpg"></a>
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
                        <button class="uk-button uk-width-2-1 uk-button-default" type="button" tabindex="-1">下载模板</button>
                    </div>
                </div>
                <div class="uk-margin">
                    <div class="uk-width-1-1" uk-form-custom>
                        <input type="file">
                        <button class="uk-button uk-width-2-1 uk-button-default" type="button" tabindex="-1">从文件导入</button>
                    </div>
                </div>
            </form>
            
            <p class="uk-text-right">
                <button class="uk-button uk-button-default uk-modal-close" type="button">关闭</button>
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
                        <input class="uk-input ssfl" id="form-horizontal-text" readonly type="text" value="">
                    </div>
                </div>
                <div class="uk-margin">
                    <label class="uk-form-label" for="form-horizontal-text">类别名称</label>
                    <div class="uk-form-controls">
                        <input class="uk-input lbmc" id="form-horizontal-text" type="text" value="">
                         <input  id="updatevalidatacategoryname_lbmc_old" type="hidden" >
                    </div>
                    <div id="updatevalidatacategoryname_value" style="color: red"></div>
                </div>
                
            </form>
            
            <p class="uk-text-right">
                <button class="uk-button uk-button-default uk-modal-close" type="button">关闭</button>
                <button id="oBtnEdit" class="uk-button uk-button-primary" type="button">确定</button>
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
                        <input class="uk-input ssfl" id="form-horizontal-text" readonly type="text" value="">
                    </div>
                </div>
                <div class="uk-margin">
                    <label class="uk-form-label" for="form-horizontal-text">类别名称</label>
                    <div class="uk-form-controls">
                        <input class="uk-input lbmc" id="form-horizontal-text" type="text" value="">
                    </div>
                </div>
            </form>
            
            <p class="uk-text-right">
                <button class="uk-button uk-button-default uk-modal-close" type="button">关闭</button>
                <button id="oBtnEditPublic" class="uk-button uk-button-primary" type="button">确定</button>
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
                        <input class="uk-input ssfl" id="form-horizontal-text" readonly type="text" value="">
                    </div>
                </div>
                <div class="uk-margin">
                    <label class="uk-form-label" for="form-horizontal-text">类别名称</label>
                    <div class="uk-form-controls">
                        <input class="uk-input lbmc" id="form-horizontal-text" type="text" value="" maxlength="15">
                    </div>
                    <div id='validatacategoryname' style="color: red"></div>
                </div>
                <div class="uk-margin">
                	<label>复制父类分类公共类别<input class="uk-checkbox" type="checkbox"  name="public_category" id="public_category"></label>
                </div>
            </form>
            
            <p class="uk-text-right">
                <button class="uk-button uk-button-default uk-modal-close" type="button">关闭</button>
                <button id="oBtnAdd" class="uk-button uk-button-primary" type="button">确定</button>
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
                        <input class="uk-input ssfl" id="form-horizontal-text" type="text" readonly="readonly" value="">
                    </div>
                </div>
                <div class="uk-margin">
                    <label class="uk-form-label" for="form-horizontal-text">类别名称</label>
                    <div class="uk-form-controls">
                        <input class="uk-input lbmc" id="form-horizontal-text" type="text" value="" maxlength="15">
                        <input  id="copyvalidatacategoryname_lbmc_old" type="hidden" >
                    </div>
                </div>
            </form>
            
            <p class="uk-text-right">
                <button class="uk-button uk-button-default uk-modal-close" type="button">关闭</button>
                <button id="oBtnAddPublic" class="uk-button uk-button-primary" type="button">确定</button>
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
                        <input class="uk-input ssfl" id="form-horizontal-text" type="text" value="" readonly="readonly">
                    </div>
                </div>
                <div class="uk-margin">
                    <label class="uk-form-label" for="form-horizontal-text">类别名称</label>
                    <div class="uk-form-controls">
                        <input class="uk-input lbmc" id="form-horizontal-text" type="text" value="" maxlength="15">
                        <input  id="modal-classification-copy-node-id" type="hidden" value="">
                    </div>
                    <div id="copyvalidatacategoryname" style="color: red"></div>
                </div>
            </form>
            
            <p class="uk-text-right">
                <button class="uk-button uk-button-default uk-modal-close" type="button">关闭</button>
                <button id="oBtnCopy" class="uk-button uk-button-primary" type="button">确定</button>
            </p>
        </div>
    </div>
    <!-- This is the public classification modal -->
    <div id="modal-classification-public" uk-modal class="modalSelector">
        <div class="uk-modal-dialog uk-modal-body">
            <iframe style="height:100%; width:100%" id="public_category_iframe" name="public_category_iframe" src="/foodcategory/publiccategory.html"></iframe>
        </div>
    </div>
    <!-- This is the deleting modal -->
    <div id="modal-deleting" uk-modal>
        <div class="uk-modal-dialog uk-modal-body">
            <h2 class="uk-modal-title">删除确认</h2>
            <p>删除后数据不能恢复，您确认要删除吗？</p>
            <p class="uk-text-right">
                <button class="uk-button uk-button-default uk-modal-close" type="button">取消</button>
                <button class="uk-button uk-button-primary" type="button">确定</button>
            </p>
        </div>
    </div>
<jsp:include page="../foot.jsp" />      
  