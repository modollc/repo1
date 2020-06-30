$("#form-post-add").validate({
	rules:{
		unitName:{
			required:true,
			maxlength:15,
			remote: {
                url: "/systemResource/systemUnit/checkUnitUnique",
                type: "post",
                dataType: "json",
                data: {
					"unitName": function() {
					    return $("input[name='unitName']").val();
					}
                },
                dataFilter: function(data, type) {
                    if (data == "0") return true;
                    else return false;
                }
            }			
		},
		unitNum:{
			required:true			
		},
		unitValue:{
			required:true			
		}
	},
	messages: {
	    "unitName": {
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
	_ajax_save("/systemResource/systemUnit/save", form);
}