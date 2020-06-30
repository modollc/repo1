$("#form-post-edit").validate({
	rules:{
		healthName:{
			required:true,
			maxlength:15,
			remote: {
				url: "/systemResource/systemHealth/checkHealthUnique",
                type: "post",
                dataType: "json",
                data: {
					"healthName": function() {
					    return $("input[name='healthName']").val();
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
		healthValue:{
			required:true			
		}
	},
	messages: {
	    "healthName": {
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
	_ajax_save("/systemResource/systemHealth/save", form);
}