$("#form-post-edit").validate({
	rules:{
		appetiteName:{
			required:true,
			maxlength:15,
			remote: {
                url: "/peopleResouce/peopleAppetite/checkAppetiteUnique",
                type: "post",
                dataType: "json",
                data: {
					"activeName": function() {
					    return $("input[name='appetiteName']").val();
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
		appetiteValue:{
			required:true			
		}
	},
	messages: {
	    "appetiteName": {
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
	_ajax_save("/peopleResouce/peopleAppetite/save", form);
}