$("#form-post-add").validate({
	rules:{
		area:{
			required:true,
			maxlength:15,
			remote: {
                url: "/peopleResouce/peopleArea/checkAreaUnique",
                type: "post",
                dataType: "json",
                data: {
					"area": function() {
					    return $("input[name='area']").val();
					}
                },
                dataFilter: function(data, type) {
                    if (data == "0") return true;
                    else return false;
                }
            }			
		},
		areaValue:{
			required:true			
		}
	},
	messages: {
	    "area": {
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
	_ajax_save("/peopleResouce/peopleArea/save", form);
}