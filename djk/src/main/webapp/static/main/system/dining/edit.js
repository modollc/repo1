$("#form-post-edit").validate({
	rules:{
		mealQuantity:{
			required:true,
			maxlength:10,
			remote: {
                url: "/dige/digeDining/checkNameUnique",
                type: "post",
                dataType: "json",
                data: {
					"mealQuantity": function() {
					    return $("input[name='mealQuantity']").val();
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
		mealScope:{
			required:true,
			maxlength:15
		},
		mealValue:{
			required:true,
			number:true
		}
	}
,
messages: {
    "mealQuantity": {
        remote: "用餐量已经存在"
    }
},
	submitHandler:function(form){
		edit();
	}
});

/** 岗位管理-修改岗位 */
function edit() {
	var form = $("#form-post-edit").serialize();
	_ajax_save("/dige/digeDining/save", form);
}