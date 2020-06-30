$("#form-post-edit").validate({
	rules:{
		seasonal:{
			required:true,
			maxlength:10,
			remote: {
                url: "/food/foodSeasonal/checkNameUnique",
                type: "post",
                dataType: "json",
                data: {
					"seasonalValue": function() {
					    return $("input[name='seasonalValue']").val();
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
		
		seasonalValue:{
			required:true,
			number:true
		}
	}
,
messages: {
    "seasonal": {
        remote: "时令已经存在"
    }
},
	submitHandler:function(form){
		edit();
	}
});

/** 岗位管理-修改岗位 */
function edit() {
	var form = $("#form-post-edit").serialize();
	_ajax_save("/food/foodSeasonal/save", form);
}