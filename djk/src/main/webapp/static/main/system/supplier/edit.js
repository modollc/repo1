$("#form-post-edit").validate({
	rules:{
		supplierName:{
			required:true,
			maxlength:25,
			remote: {
                url: "/food/foodSupplier/checkNameUnique",
                type: "post",
                dataType: "json",
                data: {
					"supplierName": function() {
					    return $("input[name='supplierName']").val();
					},
					"id": function() {
					    return $("input[name='id']").val();
					}
                },
                dataFilter: function(data, type) {
                    if (data == "0") return true;
                    else return false;
                }
            }
		},
		supplierValue:{
			required:true,
			number:true
			
		},
		supplierBrand:{
			required:true,
			maxlength:10
		}
	}
,
messages: {
    "supplierName": {
        remote: "供应商已经存在"
    }
},
	submitHandler:function(form){
		edit();
	}
});

/** 岗位管理-修改岗位 */
function edit() {
	var form = $("#form-post-edit").serialize();
	_ajax_save("/food/foodSupplier/save", form);
}