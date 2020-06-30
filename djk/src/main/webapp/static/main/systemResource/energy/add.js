$("#form-post-add").validate({
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
		add();
	}
});

/** 人需公共资源--新增 */
function add() {
	var form = $("#form-post-add").serialize();
	_ajax_save("/systemResource/systemEnergy/save", form);
}