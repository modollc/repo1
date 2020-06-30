$("#form-post-edit").validate({
	rules:{
		sexName:{
			required:true,
			maxlength:15,
			remote: {
				url: "/systemResource/systemConstant/checkSexUnique",
                type: "post",
                dataType: "json",
                data: {
					"sexName": function() {
					    return $("input[name='sexName']").val();
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
		}
	},
	messages: {
	    "sexName": {
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
	_ajax_save("/systemResource/systemConstant/saveSex", form);
}