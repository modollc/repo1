<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="UTF-8"  %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld" %>
<script src="../../../resources/djk/js/jquery-1.8.2.min.js"></script>
<script src="../../../resources/djk/easyui/jquery.easyui.min.js"></script>
<script src="../../../resources/djk/easyui/locale/easyui-lang-zh_CN.js"></script>
<script src="../../../resources/djk/js/func.js"></script>
<script src="../../../resources/djk/js/jquery.form.js"></script>
<script src="../../../resources/djk/js/jquery.ui.widget.js"></script>
<script src="../../../resources/djk/js/jquery.fileupload.js"></script>
<script src="../../../resources/djk/js/jquery.filedownload.js"></script>
<script src="../../../resources/djk/js/jquery.multifile.upload.js"></script>

<link rel="stylesheet" href="../../../resources/djk/easyui/themes/default/easyui.css" type="text/css"></link>
<link rel="stylesheet" href="../../../resources/djk/easyui/themes/icon.css" type="text/css"></link>
<link rel="stylesheet" href="../../../resources/djk/css/style.css" type="text/css"></link>

<script>
	
</script>
<body class="easyui-layout"  style="overflow-y: scroll">
<div class="formbox-a">
	<form id="addResourcesForm" method="post">
		<div class="form-contbox">
			<dl class="dl-group">

				<dd class="dd-group">
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>资源名称: </label>
							<span id="resourcesNameSpan"> <input
								class="txt w200 easyui-validatebox" type="text" id="content"
								name="content"
								data-options="required:true,validType:'length[1,40]'"
								class="txt w400" 
								value="${res.content}"/> 
							<span class="title_span">长度为1-40个字符</span>

							</span>
						</p>
					</div>
					<br />

					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>&nbsp;父&nbsp;资&nbsp;源:
							</label> 
							<input id="pid" class="txt w200 easyui-combotree" name="pid"
								value="${res.pid}"
								data-options="
								url:'/sys/resource/resTree',
								method:'get',
								onLoadSuccess:loadSuccess,
								required:true" />
						</p>
					</div>
					<br />

					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>资源链接: </label>
							<input class="txt w500 easyui-validatebox" type="text" id="url"
								name="url"
								value="${res.url}"
								data-options="required:true,validType:'length[1,255]'" />
							<span class="title_span">长度为1-255个字符</span>
						</p>
						<p class="p12 p-item">
							<label class="lab-item">&nbsp;</label>
							<label>
								<font style="color: #808080">
								填写完成该资源操作需要的所有链接地址，并用英文逗号分隔，不能有空格或其他特殊字符；
								</font>
							</label>
						</p>
						<p class="p12 p-item">
							<label class="lab-item">&nbsp;</label>
							<label>
								<font style="color: #808080">
								菜单类型的资源链接的第一个链接必须填写打开菜单所指向资源的链接，如下/admin/order为打开列表页的链接，后两个为获取数据的链接；
								</font>
							</label>
						</p>
						<p class="p12 p-item">
							<label class="lab-item">&nbsp;</label>
							<label>
								<font style="color: #808080">
								列表页面，如果显示列表数据需要3个链接，可如下填写[/admin/order,/admin/order/list,/admin/orderproduct/listbyorder]；
								</font>
							</label>
						</p>
						<p class="p12 p-item">
							<label class="lab-item">&nbsp;</label>
							<label>
								<font style="color: #808080">
								新增编辑数据页面，需要填写打开页面链接、保存数据的链接，[/admin/resource/add,/admin/resource/create]；
								</font>
							</label>
						</p>
						<p class="p12 p-item">
							<label class="lab-item">&nbsp;</label>
							<label>
								<font style="color: #808080">
								如果打开页面还会调用其他资源链接，请用逗号分隔添加在之后，如[/admin/resource/add,/admin/resource/create,/admin/resource/listparent]；
								</font>
							</label>
						</p>
						<p class="p12 p-item">
							<label class="lab-item">&nbsp;</label>
							<label>
								<font style="color: #808080">
								如果是一级菜单，平台：admin_menu_模块名，商家：seller_menu_模块名，如admin_menu_order
								</font>
							</label>
						</p>
					</div>
					<br />

					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>资源类型: </label>
							<select id="type" class="txt w200 easyui-combobox" 
								name="type"
								editable="false" data-options="required:true">
								<option value="">请选择</option>
								<option value="2" <c:if test="${res.type==2}"> selected </c:if> >按钮</option>
								<option value="1" <c:if test="${res.type==1}"> selected </c:if> >菜单</option>
							</select>
						</p>
					</div>

					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>应用范围: </label>
							<select id="scope" class="txt w200 easyui-combobox" name="scope"
								editable="false" data-options="required:true">
								<option value="2"  <c:if test="${res.scope==2}"> selected </c:if>>平台</option>
							</select>
						</p>
					</div>
					
					
					
				</dd>
			</dl>

			<p class="p-item p-btn">
				<a id="editBtn" onclick="editBtn()" class="easyui-linkbutton" iconCls="icon-save">保存</a>
				<a href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-delete" onclick="closeW()">关闭</a> <input
					type="hidden" id="rid" name="id" value="${res.id}">
			</p>
		</div>
	</form>
</div>
</body>