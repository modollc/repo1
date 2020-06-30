$("#form-post-add").validate({
	rules:{
		sleepQuality:{
			required:true,
			maxlength:15,
			remote: {
                url: "/peopleResouce/peopleSleep/checkSleepUnique",
                type: "post",
                dataType: "json",
                data: {
					"sleepQuantity": function() {
					    return $("input[name='sleepQuantity']").val();
					}
                },
                dataFilter: function(data, type) {
                    if (data == "0") return true;
                    else return false;
                }
            }
		},
		sleepTime:{
			required:true,
			maxlength:10
		},
		sleepValue:{
			required:true,
			number:true
		}
	},
	messages: {
	    "sleepQuality": {
	        remote: "睡眠质量已经存在"
	    }
	},
	submitHandler:function(form){
		add();
	}
});

/** 人需公共资源--新增 */
function add() {
	var form = $("#form-post-add").serialize();
	_ajax_save("/peopleResouce/peopleSleep/save", form);
}