$("#form-post-add").validate({
	rules:{
		foodStatus:{
			required:true,
			maxlength:15,
			remote: {
                url: "/food/foodPhyState/checkNameUnique",
                type: "post",
                dataType: "json",
                data: {
					"foodStatus": function() {
					    return $("input[name='foodStatus']").val();
					}
                },
                dataFilter: function(data, type) {
                    if (data == "0") return true;
                    else return false;
                }
            }
		},
		foodValue:{
			required:true,
			number:true
		}
	}
	,
	messages: {
	    "foodStatus": {
	        remote: "物理状态已经存在"
	    }
	},
	submitHandler:function(form){
		add();
	}
});

/** 岗位管理-新增岗位 */
function add() {

	var form = $("#form-post-add").serialize();
   
	_ajax_save("/food/foodPhyState/save", form);
}