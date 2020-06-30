$("#form-post-edit").validate({
	rules:{
		ageName:{
			required:true,
			maxlength:15,
			remote: {
				url: "/systemResource/systemConstant/checkAgeUnique",
                type: "post",
                dataType: "json",
                data: {
					"ageName": function() {
					    return $("input[name='ageName']").val();
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
		}
	},
	messages: {
	    "ageName": {
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
	_ajax_save("/systemResource/systemConstant/saveAge", form);
}