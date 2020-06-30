$("#form-post-add").validate({
	rules:{
		appetiteName:{
			required:true,
			maxlength:15,
			remote: {
                url: "/peopleResouce/peopleAppetite/checkAppetiteUnique",
                type: "post",
                dataType: "json",
                data: {
					"activeName": function() {
					    return $("input[name='appetiteName']").val();
					}
                },
                dataFilter: function(data, type) {
                    if (data == "0") return true;
                    else return false;
                }
            }			
		},
		appetiteValue:{
			required:true			
		}
	},messages: {
	    "appetiteName": {
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
	_ajax_save("/peopleResouce/peopleAppetite/save", form);
}
