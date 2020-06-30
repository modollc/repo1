$("#form-post-edit").validate({
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
		edit();
	}
});

/** 岗位管理-修改岗位 */
function edit() {
	var form = $("#form-post-edit").serialize();
	_ajax_save("/food/foodPhyState/save", form);
}