$("#form-post-add").validate({
	rules:{
		sexName:{
			required:true,
			maxlength:15,
			remote: {
                url: "/systemResource/systemConstant/checkSexUnique",
                type: "post",
                dataType: "json",
                data: {
					"sexName": function() {
					    return $("input[name='sexName']").val();
					}
                },
                dataFilter: function(data, type) {
                    if (data == "0") return true;
                    else return false;
                }
            }			
		}
	},
	messages: {
	    "sexName": {
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
	_ajax_save("/systemResource/systemConstant/saveSex", form);
}