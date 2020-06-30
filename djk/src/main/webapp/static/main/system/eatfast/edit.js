$("#form-post-edit").validate({
	rules:{
		eatFast:{
			required:true,
			maxlength:10,
			remote: {
                url: "/dige/digeEatfast/checkEatNameUnique",
                type: "post",
                dataType: "json",
                data: {
					"eatFast": function() {
					    return $("input[name='eatFast']").val();
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
		eatTime:{
			required:true,
			maxlength:15
		},
		eatValue:{
			required:true,
			number:true			
		}
	}
,
messages: {
    "eatFast": {
        remote: "吃饭速度已经存在"
    }
},
	submitHandler:function(form){
		edit();
	}
});

/** 岗位管理-修改岗位 */
function edit() {
	var form = $("#form-post-edit").serialize();
	_ajax_save("/dige/digeEatfast/save", form);
}