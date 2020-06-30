$("#form-post-edit").validate({
	rules:{
		unitName:{
			required:true,
			maxlength:15,
			remote: {
				url: "/systemResource/systemUnit/checkUnitUnique",
                type: "post",
                dataType: "json",
                data: {
					"unitName": function() {
					    return $("input[name='unitName']").val();
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
		unitNum:{
			required:true			
		},
		unitValue:{
			required:true			
		}
	},
	messages: {
	    "unitName": {
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
	_ajax_save("/systemResource/systemUnit/save", form);
}