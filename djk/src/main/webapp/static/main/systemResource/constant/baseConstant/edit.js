$("#form-post-edit").validate({
	rules:{
		constantName:{
			required:true,
			maxlength:15,
			remote: {
				url: "/systemResource/systemConstant/checkConstantUnique",
                type: "post",
                dataType: "json",
                data: {
					"constantName": function() {
					    return $("input[name='constantName']").val();
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
		minValue:{
			required:true			
		},
		maxValue:{
			required:true			
		},
		constantValue:{
			required:true			
		}
	},
	messages: {
	    "constantName": {
	        remote: "名称已经存在"
	    }
	},
	submitHandler:function(form){
		edit();
	}
});

/** 人需公共资源-修改岗位 */
function edit() {
	var form = $("#form-post-edit").serialize();
	_ajax_save("/systemResource/systemConstant/saveBase", form);
}