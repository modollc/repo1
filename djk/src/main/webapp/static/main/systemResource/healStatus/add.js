$("#form-post-add").validate({
	rules:{
		healthName:{
			required:true,
			maxlength:15,
			remote: {
                url: "/systemResource/systemHealth/checkHealthUnique",
                type: "post",
                dataType: "json",
                data: {
					"healthName": function() {
					    return $("input[name='healthName']").val();
					}
                },
                dataFilter: function(data, type) {
                    if (data == "0") return true;
                    else return false;
                }
            }			
		},
		healthValue:{
			required:true			
		}
	},
	messages: {
	    "healthName": {
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
	_ajax_save("/systemResource/systemHealth/save", form);
}