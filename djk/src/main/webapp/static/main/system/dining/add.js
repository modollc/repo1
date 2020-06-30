$("#form-post-add").validate({
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
		add();
	}
});

/** 岗位管理-新增岗位 */
function add() {

	var form = $("#form-post-add").serialize();
   
	_ajax_save("/dige/digeDining/save", form);
}