$("#form-post-add").validate({
	rules:{
		supplierName:{
			required:true
		},
		cookingMethod:{
			required:true,
			maxlength:15,
			remote: {
                url: "/food/foodCooking/checkNameUnique",
                type: "post",
                dataType: "json",
                data: {
					"cookingMethod": function() {
					    return $("input[name='cookingMethod']").val();
					}
                },
                dataFilter: function(data, type) {
                    if (data == "0") return true;
                    else return false;
                }
            }
		},
		cookingValue:{
			required:true,
			number:true
		}
	}
,
messages: {
    "cookingMethod": {
        remote: "烹饪方式已经存在"
    }
},
	submitHandler:function(form){
		add();
	}
});

/** 岗位管理-新增岗位 */
function add() {

	var form = $("#form-post-add").serialize();
   
	_ajax_save("/food/foodCooking/save", form);
}