$("#form-post-edit").validate({
	rules:{
		nutritionQuantity:{
			required:true,
			maxlength:10,
			remote: {
                url: "/dige/digeNutrition/checkNameUnique",
                type: "post",
                dataType: "json",
                data: {
					"nutritionQuantity": function() {
					    return $("input[name='nutritionQuantity']").val();
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
		nutritionNumber:{
			required:true,
			maxlength:15
		},
		nutritionValue:{
			required:true,
			number:true
		}
	}
,
messages: {
    "nutritionQuantity": {
        remote: "营养需要量已经存在"
    }
},
	submitHandler:function(form){
		edit();
	}
});

/** 岗位管理-修改岗位 */
function edit() {
	var form = $("#form-post-edit").serialize();
	_ajax_save("/dige/digeNutrition/save", form);
}