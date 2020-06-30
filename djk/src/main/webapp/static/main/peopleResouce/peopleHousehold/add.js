$("#form-post-add").validate({
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
		add();
	}
});

/** 人需公共资源--新增 */
function add() {
	var form = $("#form-post-add").serialize();
	_ajax_save("/peopleResouce/peopleHousehold/save", form);
}