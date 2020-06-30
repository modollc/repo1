$("#form-post-add").validate({
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
		add();
	}
});

/** 岗位管理-新增岗位 */
function add() {

	var form = $("#form-post-add").serialize();
   
	_ajax_save("/food/foodSupplier/save", form);
}