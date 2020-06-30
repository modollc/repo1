$("#form-post-add").validate({
	rules:{
		activeName:{
			required:true,
			maxlength:15,
			remote: {
                url: "/peopleResouce/peopleActive/checkActiveUnique",
                type: "post",
                dataType: "json",
                data: {
					"activeName": function() {
					    return $("input[name='activeName']").val();
					}
                },
                dataFilter: function(data, type) {
                    if (data == "0") return true;
                    else return false;
                }
            }			
		},
		activeValue:{
			required:true,
			number:true
		}
	},
	messages: {
	    "activeName": {
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
	_ajax_save("/peopleResouce/peopleActive/save", form);
}