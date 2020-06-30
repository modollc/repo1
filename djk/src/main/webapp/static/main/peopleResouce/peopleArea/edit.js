$("#form-post-edit").validate({
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
		edit();
	}
});

/** 人需公共资源-修改岗位 */
function edit() {
	var form = $("#form-post-edit").serialize();
	_ajax_save("/peopleResouce/peopleArea/save", form);
}