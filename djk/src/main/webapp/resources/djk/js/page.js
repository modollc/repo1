//var pno = 1; //从第几页开始
var psize =10; //每页显示
//var totalPage = 10; //总页数

function goPage(pno,psize){
    
	var itable = document.getElementById("PageData");
    var num = itable.rows.length;//表格所有行数(所有记录数)
    console.log(num);    
    var pageSize = psize;//每页显示行数
	
    //总共分几页 
    if(num/pageSize > parseInt(num/pageSize)){   
            totalPage=parseInt(num/pageSize)+1;   
       }else{   
           totalPage=parseInt(num/pageSize);   
       }   
    var currentPage = pno;//当前页数
    var startRow = (currentPage - 1) * pageSize+1;//开始显示的行  31 
       var endRow = currentPage * pageSize;//结束显示的行   40
       endRow = (endRow > num)? num : endRow;//   40
       console.log(endRow);
       //遍历显示数据实现分页
    for(var i=1;i<(num+1);i++){    
        var irow = itable.rows[i-1];
        if(i>=startRow && i<=endRow){
            irow.style.display = "";    
        }else{
            irow.style.display = "none";
        }
    }
    var pageEnd = document.getElementById("pageEnd");
    var tempStr = "共"+num+"条记录 分"+totalPage+"页 当前第"+currentPage+"页&nbsp;&nbsp;";
    if(currentPage>1){
        tempStr += "<a href=\"#page"+(1)+"\" onClick=\"goPage("+(1)+","+psize+")\">首页</a>";
        tempStr += "<a href=\"#page"+(currentPage-1)+"\" onClick=\"goPage("+(currentPage-1)+","+psize+")\"><上一页</a>"
    }else{
        tempStr += "<span>首页</span>";
        tempStr += "<span>&lt;上一页</span>";    
    }
	
	if (currentPage-1 <=0 ){
	}else if(currentPage-1 >=0 ){
		tempStr += "<a href=\"#page"+(currentPage-1)+"\" onClick=\"goPage("+(currentPage-1)+","+psize+")\">"+(currentPage-1)+"</a>";
	}
	//当前页数
	tempStr += "<a href=\"#page"+(currentPage)+"\" class=\"now\" onClick=\"goPage("+(currentPage)+","+psize+")\">"+(currentPage)+"</a>";
	
	if (currentPage+1 <= totalPage ){
		tempStr += "<a href=\"#page"+(currentPage+1)+"\" onClick=\"goPage("+(currentPage+1)+","+psize+")\">"+(currentPage+1)+"</a>";
	};	

    if(currentPage<totalPage){
        tempStr += "<a href=\"#page"+(currentPage+1)+"\" onClick=\"goPage("+(currentPage+1)+","+psize+")\">下一页></a>";
        tempStr += "<a href=\"#page"+(totalPage)+"\" onClick=\"goPage("+(totalPage)+","+psize+")\">尾页</a>";
    }else{
        tempStr += "<span>下一页&gt;</span>";
        tempStr += "<span>尾页</span>";  
    }
	tempStr += "&nbsp;&nbsp;每页显示<select class='page_select' id='page_select' onchange='getSltValue()'><option>选择</option><option value='5'>5</option><option value='10'>10</option><option value='50'>50</option><option value='100'>100</option></select>条";

    document.getElementById("showpage").innerHTML = tempStr;
}

	function getSltValue(){
		var size = document.getElementById("page_select").value;
		
		$("#page_select").each(function(){  
        	if($("#page_select").val()==size){  
         		//$(".page_select").attr("selected","selected");  
				//$(this).find("option:selected").attr("selected","selected");
				//alert("a");
            }  
        });  
  

/*
		if($("#page_select").val()==size){  
    		$("#page_select").attr("selected","selected"); 
			alert(size); 
		}; 
	*/
		goPage(1,size);
	};
	
	

	
	
