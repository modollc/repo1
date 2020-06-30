$("#form-post-edit").validate({
	rules:{
		temperature:{
			required:true,
			maxlength:15,
			remote: {
                url: "/peopleResouce/peopleTemperature/checkTemperatureUnique",
                type: "post",
                dataType: "json",
                data: {
					"temperature": function() {
					    return $("input[name='temperature']").val();
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
		temperatureValue:{
			required:true			
		}
	},
	messages: {
	    "temperature": {
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
	_ajax_save("/peopleResouce/peopleTemperature/save", form);
}