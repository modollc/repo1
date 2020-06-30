$("#form-post-edit").validate({
	rules:{
		nutritionCategoryName:{
			required:true
		},
		name:{
			required:true,
			maxlength:15,
			remote: {
                url: "/nutrition/nutritionList/checkNameUnique",
                type: "post",
                dataType: "json",
                data: {
					"name": function() {
					    return $("input[name='name']").val();
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
		unit:{
			required:true			
		}
	}
,
messages: {
    "name": {
        remote: "营养名称已经存在"
    }
},
	submitHandler:function(form){
		edit();
	}
});

/** 岗位管理-修改岗位 */
function edit() {
	var form = $("#form-post-edit").serialize();
	_ajax_save("/nutrition/nutritionList/save", form);
}