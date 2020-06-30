<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="UTF-8"  %>
<%@taglib prefix="shiro" uri="/WEB-INF/tld/shiro.tld" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="my" %>
<jsp:include page="../head.jsp" />
<script type="text/javascript" src="../../../resources/djk/js/pick-pcc.min.1.0.1.js"></script>
 <script>
    function Dsy()
    {
    this.Items = {};
    }
    Dsy.prototype.add = function(id,iArray)
    {
    this.Items[id] = iArray;
    }
    Dsy.prototype.Exists = function(id)
    {
    if(typeof(this.Items[id]) == "undefined") return false;
    return true;
    } 
    function change(v){
    var str="0";
    for(i=0;i<v;i++){ str+=("_"+(document.getElementById(s[i]).selectedIndex-1));};
    var ss=document.getElementById(s[v]);
    with(ss){
      length = 0;
      options[0]=new Option(opt0[v],opt0[v]);
      if(v && document.getElementById(s[v-1]).selectedIndex>0 || !v)
      {
       if(dsy.Exists(str)){
        ar = dsy.Items[str];
        for(i=0;i<ar.length;i++)options[length]=new Option(ar[i],ar[i]);
        if(v)options[1].selected = true;
       }
      }
      if(++v<s.length){change(v);}
    }
    }
    
    var dsy = new Dsy();
    
    
    var s=["province","city","county"];
    var opt0 = ["国家","省/州","市"];
    $.ajax({
    	type: "post",
		url : "/findOriginList",
		data: {},
        dataType: "json",
		success : function(data) {
			for(var i=0; i<data.length; i++) {
				 var map = data[i]; // Map map = new HashMap();
				 // 遍历  
				 for(key in map){  
				     dsy.add(key, map[key]);
				 } 
				
			}
			 setup();
		},error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert(errorThrown);
		}
	});
    
    
	
    
    
	console.log(dsy);

    function setup()
    {
    for(i=0;i<s.length-1;i++)
       document.getElementById(s[i]).onchange=new Function("change("+(i+1)+")");
       change(0);
    }
    //console.log(s[0]);
    
    function validataLength() {
    	//alert($("#nutritionValue").val());
    	
    	/* var obj = document.getElementById("nutritionValue");
    	//先把非数字的都替换掉，除了数字和. 
   	     obj.value = obj.value.replace(/[^\d.]/g,""); 
   	     //必须保证第一个为数字而不是. 
   	     obj.value = obj.value.replace(/^\./g,""); 
   	     //保证只有出现一个.而没有多个. 
   	     obj.value = obj.value.replace(/\.{2,}/g,".");  */
   	     	
    	 //alert('1.1.1'.replace(/\.{2,}/g,"."));
   	    
    }
    </script>
   
<link rel="stylesheet" href="../../../resources/djk/css/pick-pcc.min.1.0.1.css">
<style>
#province, #city, #county{
width: calc((100% - 85px)/3);
}

</style>
<body>
            <form action="/foodcreate" id="food" name="food" method="post">
                <my:token></my:token>
    <div class="box uk-container">
       
        <div class="container increase_food" uk-grid>
            <div class="mbxue uk-width-1-1">
                <ul class="uk-breadcrumb">
                    <li>大健康管理系统</li>
                    <li><a href="foodmain.html">食物维护</a></li>
                    <li><span> 新增食物</span></li>
                </ul>
                <div class="btn_cz">
                    <input type="button" class="uk-button uk-button-default" onclick="window.location.href='/foodmain.html?foodCategoryName=${food.firstCategoryName }&foodCategoryId=${food.firstCategoryId }'" value="返回"/>
                    <input type="button" class="uk-button uk-button-primary" onclick="onCheckNodeSite()" value="保存"/>
                </div>
            </div>
            <div class="uk-width-1-1 sxx">
                    <div class="zTreeDemoBackground">
                        <ul class="list">
                            <!-- <li class="title"><input class="citySel" onclick="showMenu();" /></li> -->
                            <li>
                                <label><span>*</span>食物名称</label>
                                <input class="uk-input uk-form-width-medium" type="text" name="name" id="name" maxlength="15">
                            </li>
                            <li>
                                <label><span></span>食物别名</label>
                                <input class="uk-input uk-form-width-medium" type="text" name="alias" maxlength="15">
                            </li>
                            <li>
                                <label><span>*</span>第一分类</label>
                                <input class="uk-input uk-form-width-medium citySel in1" onclick="showMenu(1);" type="text" name="firstCategoryName"  id="firstCategoryName" value="${food.firstCategoryName }">
                                <input type="hidden" name="firstCategoryId" id="firstCategoryId" value="${food.firstCategoryId }">
                            </li>
                            <li>
                                <label><span></span>第二分类</label>
                                <input class="uk-input uk-form-width-medium citySel in2" onclick="showMenu(2);"type="text" name="secondCategoryName">
                                <input type="hidden" name="secondCategoryId" id="secondCategoryId">
                            </li>
                            <li>
                                <label><span></span>第三分类</label>
                                <input class="uk-input uk-form-width-medium citySel in3" onclick="showMenu(3);" type="text" name="threeCategoryName">
                                <input type="hidden" name="threeCategoryId" id="threeCategoryId">
                            </li>
                            <li style=" width: 39%; margin: 0 5px 0 0px;">
                                <label><span>*</span>产       地</label>
                                  <SELECT id="province" runat="server" name="firstOriginName">
						         </SELECT>
						         <SELECT id="city" runat="server" name="secondOriginName">
						         </SELECT>
						         <SELECT id="county" runat="server" name="threeOriginName">
						         </SELECT>
                            </li>
                            <li>
                                <label><span>*</span>时       令</label>
                                <select  class="uk-select" id="form-stacked-select" name="seasonName"  id="seasonName">
                                     <c:forEach items="${foodSeasonalList}" var="item">
	                                    <option value="${item.seasonal}">${item.seasonal}</option>
							        </c:forEach>
                                </select>
                            </li>
                            <li>
                                <label><span></span>供应商</label>
                                <select class="uk-select" id="form-stacked-select" name="supplierName">
                                    <option value="" selected="selected"></option>
                                 <c:forEach items="${foodSupplierList}" var="item">
                                    <option value="${item.supplierName}">${item.supplierName}</option>
						        </c:forEach>
                                </select>
                            </li>
                            <li>
                                <label><span>*</span>计算单位</label>
                                <div class="jldw">
                                    <input class="uk-input" type="text" name="unitNum"  id="unitNum" onkeyup="value=value.replace(/[^\d{1,}\.\d{1,}|\d{1,}]/g,'')" >
                                    <select class="uk-select" id="form-stacked-select" name="unitName">
                                         <c:forEach items="${systemWeightList}" var="item">
		                                    <option value="${item.weightName}"  <c:if test="${item.weightName == food.unitName}">selected</c:if>>${item.weightName}</option>
								         </c:forEach>
                                    </select>
                                </div>
                            </li>
                        </ul>
                    </div>
            </div>
            <div class="uk-width-1-1" uk-grid>
                <div class="left uk-width-1-3">
                    <ul id="treepublic" class="ztree"></ul>
                </div>
                <div class="right uk-width-2-3">
                        <table class="uk-table uk-table-middle uk-table-divider">
                            <thead>
                                <tr>
                                    <th class="uk-width-small">营养名称/th>
                                    <th>单位</th>
                                    <th>数量</th>
                                </tr>
                            </thead>
                            <tbody>
                             	<c:forEach items="${nutritionComponentList}" var="item">
	                                <tr>
	                                    <td>${item.name}<input type="hidden" name="nutritionName" value="${item.name}"/></td>
	                                    <td>${item.unit}<input type="hidden" name="nutritionUnit" value="${item.unit}"/></td>
	                                    <td><input class="uk-input uk-form-blank uk-form-width-medium" type="text" 
	                                    onkeyup="value=value.replace(/[^\d{1,}\.\d{1,}|\d{1,}]/g,'')" 
	                                    onchange="validataLength()"
	                                    placeholder="" name="nutritionValue" id="nutritionValue" value="0">
	                                    </td>
	                                </tr>
						        </c:forEach>
                            </tbody>
                        </table>
                </div>
            </div>
            
            <div id="menuContent" class="menuContent" style="display:none; position: absolute;">
                <ul id="treeDemo" class="ztree" style="margin-top:0; width:180px; height: 300px;"></ul>
            </div> 
        </div>
    </div>
    </form>
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
    
</body>
<script type="text/javascript">
    $(".pick-area1").pickArea({
        "format":"北京/哈尔市/请选择县", //格式
        "getVal":function(){
            console.log($(".pick-area-hidden").val())
        }
    }); 
</script>
<SCRIPT type="text/javascript">
    $(document).ready(function () {
        $(".citySel").click(function(){
            $(".citySel").removeClass("in");
            
            $(this).addClass("in");

           
        });
    });
    
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
        var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
        nodes = zTree.getCheckedNodes(true), v = "";
        
        for (var i=0, l=nodes.length; i<l; i++) {
            v += nodes[i].name + ",";
        }
        if (v.length > 0 ) v = v.substring(0, v.length-1);
        var cityObj = $(".citySel.in");
        cityObj.attr("value", v);
        alert($(".citySel.in").attr('name'));
        if($(".citySel.in").attr('name') == "firstCategoryName"){
            alert("当前是第一分类的选择框");
        }
        $("#menuContent").css("display","none");

        
    };
    //编辑节点
    function zTreePublicOnClick(event, treeId, treeNode) {
        console.log(treeNode);

    };
    
     /* var setting = {
            view: {
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
                onClick: zTreeOnClick,
                onCheck: zTreeonCheck
            },
            async: {  
                enable: true, // 设置 zTree是否开启异步加载模式  加载全部信息  
                type: "post",  
                url : '/foodcategory/list',
                autoParam : [ "id", "pid"],
                isSimpleData: true, // 简单数据模型，必须提供下面的两项
                treeNodeKey: "id", // 节点的id
                treeNodeParentKey: "pid"
                },

        };  */
    
    
    //数据过滤
    function filter(treeId, parentNode, childNodes,parentNodeJSON) {
        if (childNodes.length==0) return null;
        for (var i=0, l=childNodes.length; i<l; i++) {
            childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
        }
        return childNodes;
    }

     var setting = {
        check: {
            enable: true,
            chkStyle: "radio",
            radioType: "all"
        },
        view: {
            dblClickExpand: false,
            showLine: true,
            showIcon: false
        },
        data: {
            simpleData: {
                enable: true
            }
        },
        callback: {
            onClick: onClick,
            onCheck: onCheck
        },
        async: {  
            enable: true, // 设置 zTree是否开启异步加载模式  加载全部信息  
            type: "post",  
            url : '/foodcategory/list',
            autoParam : [ "id", "pid"],
            isSimpleData: true, // 简单数据模型，必须提供下面的两项
            treeNodeKey: "id", // 节点的id
            treeNodeParentKey: "pid"
            }
    }; 
     
     function onCheck(e, treeId, treeNode) {
    	 //debugger;
         var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
         nodes = zTree.getCheckedNodes(true),
         v = "",vid="";
         for (var i=0, l=nodes.length; i<l; i++) {
             v += nodes[i].name + ",";
             vid += nodes[i].id + ",";
         }
         if (v.length > 0 ) v = v.substring(0, v.length-1);
         if (vid.length > 0 ) vid = vid.substring(0, vid.length-1);
         var cityObj = $(".citySel.in");
         cityObj.attr("value", v);
         
         if($(".citySel.in").attr('name') == "firstCategoryName"){
             openPublicCategory(nodes[0].id);
             $("#firstCategoryId").val(nodes[0].id);
         }
         if($(".citySel.in").attr('name') == "secondCategoryName"){
        	 $("#secondCategoryId").val(nodes[0].id);
         }
         if($(".citySel.in").attr('name') == "threeCategoryName"){
        	 $("#threeCategoryId").val(nodes[0].id);
         }
         
 		$("#menuContent").css("display","none");
     }
     
     function openPublicCategory(categoryid) {
    	 
    	  var settingPublic = {
   		        view: {
   		            addDiyDom: addDiyDom,
   		         showLine: true,
                 showIcon: false
   		        },
   		        data: {
   		            simpleData: {
   		                enable: true
   		            }
   		        },
   		        async: {  
   		            enable: true, // 设置 zTree是否开启异步加载模式  加载全部信息  
   		            type: "post",  
   		            url : '/foodcategory/publiccategory/list?foodCategoryId='+categoryid,
   		            autoParam : [ "id", "pid"],
   		            isSimpleData: true, // 简单数据模型，必须提供下面的两项
   		            treeNodeKey: "id", // 节点的id
   		            treeNodeParentKey: "pid",  // 父节点的id
   		            dataFilter: filter  
   		            }
   		    };
   	        $.fn.zTree.init($("#treepublic"), settingPublic);
    	 
     }

    var zNodes =[
        { id:1, pId:0, name:"原生材料", open:true},
        { id:11, pId:1, name:"植物源", open:true},
        { id:111, pId:11, name:"菜类", open:true},
        { id:112, pId:111, name:"菜花"},
        { id:113, pId:111, name:"白菜"},
        { id:114, pId:111, name:"油菜"},
        { id:115, pId:11, name:"水果", open:true},
        { id:116, pId:115, name:"瓜类"},
        { id:117, pId:115, name:"浆果类"},
        { id:118, pId:115, name:"核果类"},
        { id:119, pId:118, name:"桃"},
        { id:119, pId:118, name:"李子"},
        { id:119, pId:118, name:"杏"},
        { id:119, pId:118, name:"大枣"},
        { id:120, pId:11, name:"谷物"},

        { id:12, pId:1, name:"动物源", open:true},
        { id:13, pId:1, name:"菌类"},
        { id:2, pId:0, name:"菜谱"},
        { id:31, pId:2, name:"菜谱一"},
        { id:32, pId:2, name:"菜谱二"},
        { id:3, pId:0, name:"多维多矿补剂"}
    ];
    function onClick(e, treeId, treeNode) {
        var zTree = $.fn.zTree.getZTreeObj("treeDemo");
        zTree.checkNode(treeNode, !treeNode.checked, null, true);
        return false;
    }

    function zTreeonCheck(e, treeId, treeNode) {
        var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
        nodes = zTree.getCheckedNodes(true),
        v = "";
        for (var i=0, l=nodes.length; i<l; i++) {
            v += nodes[i].name + ",";
        }
        if (v.length > 0 ) v = v.substring(0, v.length-1);
        var cityObj = $(".citySel.in");
        cityObj.attr("value", v);
        
        
        
        $("#menuContent").css("display","none");
    }

    function showMenu(e) {
        var cityObj = $(".citySel");
        var cityOffset = $(".citySel.in" + e).offset();
        $("#menuContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");

        $("body").bind("mousedown", onBodyDown);
    }
    
    function hideMenu() {
        $("#menuContent").fadeOut("fast");
        $("body").unbind("mousedown", onBodyDown);
    }
    function onBodyDown(event) {
        if (!(event.target.id == "menuBtn" || event.target.calssName == "citySel" || event.target.calssName == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
            hideMenu();
        }
    }
    //左侧公共类别部分
    var IDMark_A = "_a";
   

    var treepublic =[
        { id:1, pId:0, name:"肥瘦", open:true},
        { id:11, pId:1, name:"极瘦（>90%）"},
        { id:12, pId:1, name:"偏瘦（>70%）"},
        { id:13, pId:1, name:"正常（50%-70%）"},
        { id:14, pId:1, name:"偏肥（<50%）"},
        { id:2, pId:0, name:"内脏类"},
        { id:21, pId:2, name:"肝"},
        { id:22, pId:2, name:"肚"},
        { id:23, pId:2, name:"腰"},
        { id:3, pId:0, name:"肢体类"},
        { id:31, pId:3, name:"掌"},
        { id:32, pId:3, name:"头"},
        { id:33, pId:3, name:"爪"},
        { id:4, pId:0, name:"骨头"},
        { id:31, pId:4, name:"脊骨"},
        { id:32, pId:4, name:"长骨"},
        { id:33, pId:4, name:"肋骨"}
    ];

    function addDiyDom(treeId, treeNode) {
    	if(treeNode.getParentNode() != null && treeNode.getParentNode() != undefined) {
    		
	        var aObj = $("#" + treeNode.tId + IDMark_A);
	        if (treeNode.level == 0) {
	            var editStr = "";
	            aObj.before(editStr);
	            var btn = $("#checkbox_"+treeNode.id);
	        if (btn) btn.bind("change", function() {
	        	checkAccessories(treeNode, btn);});
	        } else if (treeNode.level == 1) {
	         
	        }
	        
	        if (treeNode.isParent) { //判断是否是父节点
	        	
	        } else {
	        	 var editStr = "<input type='radio' class='uk-radio' id='radio_" +treeNode.id+ "' name='radio_"+treeNode.getParentNode().id+"' value='"+treeNode.id+"' onfocus='this.blur();'></input>";
	             aObj.before(editStr);
	             var btn = $("#radio_"+treeNode.id);
	             if (btn) btn.bind("click", function() {checkBrand(treeNode, btn);});
	        }
    	}
    }
    var nodeJson = [];
	var treenode;
    function onCheckNodeSite(){
    	var name = $("#name").val();
    	var firstCategoryName = $("#firstCategoryName").val();
    	var firstOriginName = $("#firstOriginName").val();
    	//var seasonName = $("#seasonName").val();
    	var province = $("#province").val();
    	var unitNum = $("#unitNum").val();
    	
    	
    	if(name == null || name == '') {
    		alert("食物名称不能为空");
    		return;
    	}
    	
    	
    	
   	   $.ajax({
          	type: "post",
			url : "/validatefoodnameexist",
			data: {name:name},
            dataType: "json",
			success : function(data) {
				if(data) {
					alert("食物名称已经存在，请重新输入");
					return;
				}else{
					if(firstCategoryName == null || firstCategoryName == '') {
			    		alert("第一分类不能为空");
			    		return;
			    	}
			    	if(province == null || province == '') {
			    		alert("产地不能为空");
			    		return;
			    	}
			    	if(province == '国家') {
			    		alert("请选择产地");
			    		return;
			    	}
			    	/* if(seasonName == null || seasonName == '') {
			    		alert("时令不能为空");
			    		return;
			    	} */
			    	if(unitNum == null || unitNum == '') {
			    		alert("计算单位不能为空");
			    		return;
			    	}
			    	
			    	
			    	
			    	//获取所有被选中节点的回调函数
					treenode = $.fn.zTree.getZTreeObj("treepublic");
					var chkNodeArr = treenode.getCheckedNodes(true);    //true获取选中节点,false未选中节点,默认为true
					for (var i = 0; i < chkNodeArr.length; i++) {
						var channelStr = parentPathName(chkNodeArr[i].parentTId);//获取选中节点的所有父节点名称返回的字符串
						nodeJson[i] = { "id": chkNodeArr[i].id, "name": chkNodeArr[i].name};
						//alert( chkNodeArr[i].name);
					}
					//radio_
					
					$('input:radio:checked').each(function(){
						//alert(this.value+",");
					});
					
					$("#food").submit();
				}
			},error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert(errorThrown);
			}
		});
    	
    	
		/*var t = $('form').serialize();

		 $.ajax({
		    	type: "post",
				url : "/foodcreate",
				data: {t},
		        dataType: "json",
				success : function(data) {
					
				},error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert(errorThrown);
				}
			});
		 
		 return;
		*/
	};
	
	/* 递归查询选中节点的所有父节点 */
	function parentPathName(parentTId){
		    var treenode = $.fn.zTree.getZTreeObj("treepublic");
			var str="";
			if(parentTId!=null){
				var node = treenode.getNodeByTId(parentTId);
				if(node!=null && typeof(node)!='undefined'){
					str=parentPathName(node.parentTId);
					str+=node.name+"/";
				}
				return str;
			}
			return "";
	}


    function checkAccessories(treeNode, btn) {
        var checkedRadio = getCheckedRadio("radio_"+treeNode.id);
        if (btn.attr("checked")) {
            if (!checkedRadio) {
                $("#radio_" + treeNode.children[0].id).attr("checked", true);
            }
        } else {
            checkedRadio.attr("checked", false);
        }
    }

    function checkBrand(treeNode, btn) {
        if (btn.attr("checked")) {
            var pObj = $("#checkbox_" + treeNode.getParentNode().id);
            if (!pObj.attr("checked")) {
                pObj.attr("checked", true);
            }
        }
    }

    function getCheckedRadio(radioName) {
        var r = document.getElementsByName(radioName);
        for(var i=0; i<r.length; i++)    {
            if(r[i].checked)    {
                return $(r[i]);
            }
        }
        return null;
    }
    
    
    $(document).ready(function(){
        //$.fn.zTree.init($("#treepublic"), settingPublic, treepublic);
        var fcid = 0;
        if($('#firstCategoryId').val() != null && $('#firstCategoryId').val() != '' && $('#firstCategoryId').val() > 0) {
        	fcid = $('#firstCategoryId').val();
        }
    	openPublicCategory(fcid);
    });
    $(document).ready(function(){
    	//$.fn.zTree.init($("#treeDemo"), setting);
    	$.fn.zTree.init($("#treeDemo"), setting);
    });
</SCRIPT>
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
	
     $('body').on('click', 'tr', function() {
         parent.$(".operation_area .uk-button-default").addClass("in");
     });

</script>
  <!--js初始化函数-->
       <SCRIPT language="javascript">
      
       </SCRIPT>
<jsp:include page="../foot.jsp" />      