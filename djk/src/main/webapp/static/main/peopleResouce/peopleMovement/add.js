$("#form-post-add").validate({
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
		add();
	}
});

/** 人需公共资源--新增 */
function add() {
	var form = $("#form-post-add").serialize();
	_ajax_save("/peopleResouce/peopleMovement/save", form);
}
