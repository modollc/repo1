$("#form-post-add").validate({
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
					}
                },
                dataFilter: function(data, type) {
                    if (data == "0") return true;
                    else return false;
                }
            }
		},
		reference:{
			required:true,
			maxlength:15
		},
		sceneValue:{
			required:true,
			number:true
		}
	},
	messages: {
        "sceneName": {
            remote: "场景名称已经存在"
        }
    },
	submitHandler:function(form){
		add();
	}
});

/** 岗位管理-新增岗位 */
function add() {

	var form = $("#form-post-add").serialize();
   
	_ajax_save("/dige/digeScene/save", form);
}