$("#form-post-add").validate({
	rules:{
		weightName:{
			required:true,
			maxlength:15,
			remote: {
                url: "/systemResource/systemWeight/checkWeightUnique",
                type: "post",
                dataType: "json",
                data: {
					"weightName": function() {
					    return $("input[name='weightName']").val();
					}
                },
                dataFilter: function(data, type) {
                    if (data == "0") return true;
                    else return false;
                }
            }			
		},
		weightNum:{
			required:true			
		},
		weightValue:{
			required:true			
		}
	},
	messages: {
	    "weightName": {
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
	_ajax_save("/systemResource/systemWeight/save", form);
}