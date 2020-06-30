$("#form-post-add").validate({
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
		add();
	}
});

/** 岗位管理-新增岗位 */
function add() {

	var form = $("#form-post-add").serialize();
   
	_ajax_save("/dige/digeEatfast/save", form);
}