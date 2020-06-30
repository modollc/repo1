$("#form-post-add").validate({
	rules:{
		nutritionCategoryName:{
			required:true
		},
		name:{
			required:true,
			maxlength:15,
			remote: {
                url: "/nutrition/nutritionList/checkNameUnique",
                type: "post",
                dataType: "json",
                data: {
					"name": function() {
					    return $("input[name='name']").val();
					}
                },
                dataFilter: function(data, type) {
                    if (data == "0") return true;
                    else return false;
                }
            }
		},
		unit:{
			required:true			
		}
	}
,
messages: {
    "name": {
        remote: "营养名称已经存在"
    }
},
	submitHandler:function(form){
		add();
	}
});


function add() {

	var form = $("#form-post-add").serialize();
   
	_ajax_save("/nutrition/nutritionList/save", form);
}