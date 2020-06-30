$("#form-post-add").validate({
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
		add();
	}
});

/** 岗位管理-新增岗位 */
function add() {

	var form = $("#form-post-add").serialize();
   
	_ajax_save("/food/foodSeasonal/save", form);
}