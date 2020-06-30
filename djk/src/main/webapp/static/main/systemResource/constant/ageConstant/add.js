$("#form-post-add").validate({
	rules:{
		ageName:{
			required:true,
			maxlength:15,
			remote: {
                url: "/systemResource/systemConstant/checkAgeUnique",
                type: "post",
                dataType: "json",
                data: {
					"ageName": function() {
					    return $("input[name='ageName']").val();
					}
                },
                dataFilter: function(data, type) {
                    if (data == "0") return true;
                    else return false;
                }
            }			
		},
		minValue:{
			required:true			
		},
		maxValue:{
			required:true			
		}
	},
	messages: {
	    "ageName": {
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
	$.ajax({
		url: "/systemResource/systemConstant/saveAge",
        type: "post",
        dataType: "json",
        data: form,
        success: function(result) {
        	handleSuccess(result);
        }
	});
//	_ajax_save("/systemResource/systemConstant/saveAge", form);
	
}

/** 操作结果处理 */
function handleSuccess(result) {
    if (result.code == web_status.SUCCESS) {
    	parent.layer.msg("保存成功,正在刷新数据请稍后……",{icon:1,time: 500,shade: [0.1,'#fff']},function(){
    		$.parentReload();
		});
    } else {
    	$.modalAlert(result.msg, modal_status.FAIL);
    }
}