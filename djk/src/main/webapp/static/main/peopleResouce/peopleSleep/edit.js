$("#form-post-edit").validate({
	rules:{
		sleepQuality:{
			required:true,
			maxlength:5,
			remote: {
                url: "/peopleResouce/peopleSleep/checkSleepUnique",
                type: "post",
                dataType: "json",
                data: {
					"sleepQuantity": function() {
					    return $("input[name='sleepQuantity']").val();
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
		sleepTime:{
			required:true			
		},
		sleepValue:{
			required:true			
		}
	},
	messages: {
	    "sleepQuality": {
	        remote: "睡眠质量已经存在"
	    }
	},
	submitHandler:function(form){
		edit();
	}
});

/** 人需公共资源-修改岗位 */
function edit() {
	var form = $("#form-post-edit").serialize();
	_ajax_save("/peopleResouce/peopleSleep/save", form);
}