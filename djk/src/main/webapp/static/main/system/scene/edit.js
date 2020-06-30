$("#form-post-edit").validate({
	rules:{
		sceneName:{
			required:true,
			maxlength:15,
			remote: {
                url: "/dige/digeScene/checkSceneNameUnique",
                type: "post",
                dataType: "json",
                data: {
					"sceneName": function() {
					    return $("input[name='sceneName']").val();
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
		reference:{
			required:true			
		},
		sceneValue:{
			required:true			
		}
	},
	messages: {
        "sceneName": {
            remote: "场景名称已经存在"
        }
    },
	submitHandler:function(form){
		edit();
	}
});

/** 岗位管理-修改岗位 */
function edit() {
	var form = $("#form-post-edit").serialize();
	_ajax_save("/dige/digeScene/save", form);
}