$("#form-post-add").validate({
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
		add();
	}
});

/** 人需公共资源--新增 */
function add() {
	var form = $("#form-post-add").serialize();
	_ajax_save("/peopleResouce/peopleTemperature/save", form);
}