$("#form-post-edit").validate({
	rules:{
		household:{
			required:true,
			maxlength:15,
			remote: {
                url: "/peopleResouce/peopleHousehold/checkHouseholdUnique",
                type: "post",
                dataType: "json",
                data: {
					"household": function() {
					    return $("input[name='household']").val();
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
		householdValue:{
			required:true			
		}
	},
	messages: {
	    "household": {
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
	_ajax_save("/peopleResouce/peopleHousehold/save", form);
}