$("#form-post-edit").validate({
	rules:{
		energyName:{
			required:true,
			maxlength:15,
			remote: {
				url: "/systemResource/systemEnergy/checkEnergyUnique",
                type: "post",
                dataType: "json",
                data: {
					"energyName": function() {
					    return $("input[name='energyName']").val();
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
		energyNum:{
			required:true			
		},
		energyValue:{
			required:true			
		}
	},
	messages: {
	    "energyName": {
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
	_ajax_save("/systemResource/systemEnergy/save", form);
}