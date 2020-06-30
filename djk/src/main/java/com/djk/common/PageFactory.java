package com.djk.common;



import javax.servlet.http.HttpServletRequest;

import com.baomidou.mybatisplus.plugins.Page;




/**
 * BootStrap Table默认的分页参数创建
 *
 * @author 邢广军
 */
public class PageFactory<T> {

    public Page<T> defaultPage() {
        HttpServletRequest request = HttpKit.getRequest();
        int limit = Integer.valueOf(request.getParameter("pageSize"));
        int offset = Integer.valueOf(request.getParameter("pageNum"));

        String sort = request.getParameter("orderByColumn");
        String order = request.getParameter("isAsc");
      
        if(ToolUtil.isEmpty(sort)){
            //Page<T> page = new Page<>((offset / limit + 1), limit);
            Page<T> page = new Page<>(offset, limit);
            page.setOpenSort(false);
            return page;
        }else{
        	//(offset / limit + 1)
        	Page<T> page = new Page<>(offset, limit, sort);
            if("asc".equals(order)){
                page.setAsc(true);
            }else{
                page.setAsc(false);
            }
            return page;
        }
    }
    
   
   
    
}
