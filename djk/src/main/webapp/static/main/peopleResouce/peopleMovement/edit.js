$("#form-post-edit").validate({
	rules:{
		movementName:{
			required:true,
			maxlength:15,
			remote: {
                url: "/peopleResouce/peopleMovement/checkMovementUnique",
                type: "post",
                dataType: "json",
                data: {
					"activeName": function() {
					    return $("input[name='movementName']").val();
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
		movementTime:{
			required:true			
		},
		movementNum:{
			required:true			
		}
	},
	messages: {
	    "movementName": {
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
	_ajax_save("/peopleResouce/peopleMovement/save", form);
}