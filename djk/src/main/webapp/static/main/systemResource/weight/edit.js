$("#form-post-edit").validate({
	rules:{
		weightName:{
			required:true,
			maxlength:15,
			remote: {
				url: "/systemResource/systemWeight/checkWeightUnique",
                type: "post",
                dataType: "json",
                data: {
					"weightName": function() {
					    return $("input[name='weightName']").val();
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
		weightNum:{
			required:true			
		},
		weightValue:{
			required:true			
		}
	},
	messages: {
	    "weightName": {
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
	_ajax_save("/systemResource/systemWeight/save", form);
}