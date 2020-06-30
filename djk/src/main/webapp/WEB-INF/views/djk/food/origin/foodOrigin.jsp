<%@ page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="/WEB-INF/tld/shiro.tld"%>
<%@include file="../../commons/head.jsp"%>
<jsp:include page="../../head.jsp" />
 <!-- zTree引用js -->
<script type="text/javascript" src="../../resources/djk/js/jquery.ztree.all.min.js"></script>


<style>
.navMenu-item:nth-child(4) .navMenu-title {
     background: #c38447;
    color: #fff;
}
.navMenu-item:nth-child(4) .navMenu-item-content li:nth-child(2) a{
    color: #622f00;
   font-weight: bold;
}
</style>

 <div class="container increase_food" uk-grid>
           
            <div class="mbxue uk-width-1-1">
                <ul class="uk-breadcrumb">
                    <li><a href="index.html">大健康管理系统</a></li>
                    <li><a href="PlaceOrigin.html">食物营养公共资源</a></li>
                    <li><span>产地</span></li>
                </ul>
                <div class="btn_cz">
                    <button class="uk-button uk-button-primary" uk-toggle="target: #modal-classification-add">新增</button>
                    <button class="uk-button uk-button-primary" uk-toggle="target: #modal-classification-edit">编辑</button>
                    <button class="uk-button uk-button-default" uk-toggle="target: #modal-deleting">删除</button>
                    
                </div>
            </div>
            
            <div class="uk-width-1-1" uk-grid>
                <div class="left uk-width-1-3">
                    <ul id="treeDemo" class="ztree"></ul>
                </div>
            </div>
            
            <div id="menuContent" class="menuContent" style="display:none; position: absolute;">
                <ul id="treeDemo" class="ztree" style="margin-top:0; width:180px; height: 300px;"></ul>
            </div> 
        </div>
    </div>
    <!-- This is the add classification modal -->
    <div id="modal-classification-add" uk-modal>
        <div class="uk-modal-dialog uk-modal-body">
            <h2 class="uk-modal-title">新增</h2>
            <form>
                <div class="uk-margin">
                    <label class="uk-form-label" for="form-horizontal-text">上级名称</label>
                    <div class="uk-form-controls">
                        <input class="uk-input ssfl" id="form-horizontal-text" name="addPName" id="addPName" type="text" >
                    </div>
                </div>
                <div class="uk-margin">
                    <label class="uk-form-label" for="form-horizontal-text">新增名称</label>
                    <div class="uk-form-controls">
                        <input class="uk-input lbmc" id="form-horizontal-text" name="addName" id="addName"  type="text">
                    </div>
                </div>
            </form>
            
            <p class="uk-text-right">
                <button class="uk-button uk-button-default uk-modal-close" type="button">取消</button>
                <button id="oBtnAdd" class="uk-button uk-button-primary" type="button" onclick="addName()">确定</button>
            </p>
        </div>
    </div>
    <!-- This is the editing classification modal -->
    <div id="modal-classification-edit" uk-modal>
        <div class="uk-modal-dialog uk-modal-body">
            <h2 class="uk-modal-title">编辑</h2>
            <form>
                <div class="uk-margin">
                    <label class="uk-form-label" for="form-horizontal-text">上级名称</label>
                    <div class="uk-form-controls">
                        <input class="uk-input ssfl" id="form-horizontal-text" name="editPName" id="editPName" type="text" >
                    </div>
                </div>
                <div class="uk-margin">
                    <label class="uk-form-label" for="form-horizontal-text">新增名称</label>
                    <div class="uk-form-controls">
                        <input class="uk-input lbmc" id="form-horizontal-text"  name="editName" id="editName"  type="text" value="">
                    </div>
                </div>
            </form>
            
            <p class="uk-text-right">
                <button class="uk-button uk-button-default uk-modal-close" type="button">取消</button>
                <button id="oBtnEdit" class="uk-button uk-button-primary" type="button" onclick="editName()">确定</button>
            </p>
        </div>
    </div>
     <!-- This is the deleting modal -->
     <div id="modal-deleting" uk-modal>
        <div class="uk-modal-dialog uk-modal-body">
            <h2 class="uk-modal-title">删除确认</h2>
            <p>删除后数据不能恢复，您确认要删除吗？</p>
            <p class="uk-text-right">
                <button class="uk-button uk-button-default uk-modal-close" type="button">取消</button>
                <button class="uk-button uk-button-primary" type="button" onclick="deletetree()">确定</button>
            </p>
        </div>
    </div>
    
    
    <SCRIPT type="text/javascript">
    var deleteId=0;
    function deletetree(){
    	 $.ajax({
       	  type: 'POST',
       	  url: "/food/foodOrigin/delete",
       	  data: {"id":deleteId},
       	  success: function(data){
       		  if(data.code=="0"){
       			alert('删除成功！');
       			deleteId=0;
       			window.location.href="/food/foodOrigin/index";
       		  }else{
       			alert(data.msg); 
       		  }
       		  
               
       	  },
       	  dataType: "json"
       });
    }
    function addName(){
    	var addname = $("#modal-classification-add .lbmc").val();
    	if(addname==""||addname==null){
    		alert('产地名称不能为空！');
    		return false;
    	}
    	$.ajax({
         	  type: 'POST',
         	  url: "/food/foodOrigin/add",
         	  data: {"originName":addname,"parentId":deleteId},
         	  success: function(data){
         		  if(data.code=="0"){
         			alert('添加成功！');
         			deleteId=0;
         			window.location.href="/food/foodOrigin/index";
         		  }else{
         			  alert(data.msg);
         		  }
                 
         	  },
         	  dataType: "json"
         });
    }
    function editName(){
    	var editname = $("#modal-classification-edit .lbmc").val();
    	if(editname==""||editname==null){
    		alert('产地名称不能为空！');
    		return;
    	}
    	$.ajax({
         	  type: 'POST',
         	  url: "/food/foodOrigin/edit",
         	  data: {id:deleteId,originName:editname},
         	  success: function(data){
         		  if(data.code=="0"){
         			alert('修改成功！');
         			deleteId=0;
         			window.location.href="/food/foodOrigin/index";
         		  }else{
         			  alert(data.msg);
         		  }
                 
         	  },
         	  dataType: "json"
         });
    }
    $(document).ready(function () {
        $(".citySel").click(function(){
            $(".citySel").removeClass("in");
            $(this).addClass("in");
        });
    });
    var curMenu = null, zTree_Menu = null;
        var setting = {
            view: {
                showLine: true,
                showIcon: false,
                selectedMulti: false,
                dblClickExpand: false
            },
            data: {
                simpleData: {
                    enable: true
                }
            },
            callback: {
                //beforeClick: beforeClick,
                onClick: zTreeOnClick,
                beforeRemove: beforeRemove
            }
        };
       
       // var zNodes_food = "${listAll}";
         var zNodes_food =[
            { id:1, pId:0, name:"中国", open:true},
            { id:11, pId:1, name:"黑龙江"},
            { id:111, pId:11, name:"哈尔滨"},
            { id:112, pId:111, name:"道里区"},
            { id:113, pId:111, name:"南岗区"},
            { id:114, pId:111, name:"道外区"},
            { id:115, pId:11, name:"齐齐哈尔"},
            { id:116, pId:115, name:"东区"},
            { id:117, pId:115, name:"西区"},
            { id:118, pId:115, name:"北区"},
            { id:120, pId:11, name:"佳木斯"},
            { id:12, pId:1, name:"北京"},
            { id:13, pId:1, name:"上海"},
            { id:2, pId:0, name:"美国"},
            { id:31, pId:2, name:"旧金山"},
            { id:32, pId:2, name:"拉斯维加斯"},
            { id:3, pId:0, name:"英国"}
        ]; 
        
        function beforeClick(treeId, treeNode) {
            if (treeNode.level == 0 ) {
                var zTree = $.fn.zTree.getZTreeObj("treeDemo");
                zTree.expandNode(treeNode);
                return false;
            }
            return true;
        }
        //编辑节点
        function zTreeOnClick(event, treeId, treeNode) {
        	deleteId=treeNode.id;
            console.log(treeNode);
           
            $("#modal-classification-add .ssfl").val(treeNode.name);
            $("#modal-classification-edit .lbmc").val(treeNode.name);
            if(treeNode.getParentNode()!=null){
            	 $("#modal-classification-edit .ssfl").val(treeNode.getParentNode().name);
            }else{
            	$("#modal-classification-edit .ssfl").val('顶级');
            }
           
        };
        function updateNode(e) {
            var nameCount = $("#modal-classification-edit .lbmc").val();
            if(nameCount==""||nameCount==null){
            	return;
            }
			var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
			type = e.data.type,
			nodes = zTree.getSelectedNodes();
			if (nodes.length == 0) {
				alert("请先选择一个节点");
			}
			for (var i=0, l=nodes.length; i<l; i++) {
				zTree.setting.view.fontCss = {};
				if (type == "rename") {
					nodes[i].name = nameCount;
				}
				zTree.updateNode(nodes[i]);
			}
            $(".uk-modal").removeClass("uk-open");
            $(".uk-modal").css("display","none");
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
			
			zTree.removeNode(treeNode, true);
        };
		function beforeRemove(treeId, treeNode) {

			className = (className === "dark" ? "":"dark");
			return confirm("确认删除 " + treeNode.name + " 分类吗？");
		}
        //添加节点
        var newCount = 1;
        
		function add(e) {
            var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
			isParent = e.data.isParent,
			nodes = zTree.getSelectedNodes(),
            treeNode = nodes[0];
            var addname = $("#modal-classification-add .lbmc").val();
            if(addname==""||addname==null){
            	return;
            }
            if (nodes.length == 0) {
				alert("请先选择一个节点");
				return;
			}
			if (treeNode) {
				treeNode = zTree.addNodes(treeNode, {id:(100 + newCount), pId:treeNode.id, isParent:isParent, name:addname });
			} else {
				treeNode = zTree.addNodes(null, {id:(100 + newCount), pId:0, isParent:isParent, name:addname });
			}
			if (treeNode) {
				zTree.editName(treeNode[0]);
			} else {
				alert("叶子节点被锁定，无法增加子节点");
            }
            $(".uk-modal").removeClass("uk-open");
            $(".uk-modal").css("display","none");
        };
         
         //执行函数
        $(document).ready(function(){
            var treeObj = $("#treeDemo");
            
            $.ajax({
            	  type: 'GET',
            	  url: "/food/foodOrigin/list",
            	  data: {},
            	  success: function(data){
            		  zNodes_food=data;
            		  $.fn.zTree.init(treeObj, setting, zNodes_food);
                      zTree_Menu = $.fn.zTree.getZTreeObj("treeDemo");
                      //curMenu = zTree_Menu.getNodes()[0].children[0].children[0];
                      zTree_Menu.selectNode(curMenu);
                      treeObj.hover(function () {
                          if (!treeObj.hasClass("showIcon")) {
                              treeObj.addClass("showIcon");
                          }
                      }, function() {
                          treeObj.removeClass("showIcon");
                      });
                      $("#oBtnEdit").bind("click", {type:"rename"}, updateNode);
                      $("#modal-classification-remove").bind("click", remove);
                      $("#oBtnAdd").bind("click", {isParent:false}, add);
                    
            	  },
            	  dataType: "json"
            });
           
           
            
            
        });
</SCRIPT>
	
<jsp:include page="../../foot.jsp" />



