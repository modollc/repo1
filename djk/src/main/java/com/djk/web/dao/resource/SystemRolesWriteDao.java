package com.djk.web.dao.resource;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.djk.web.entity.resource.SystemRoles;

@Repository
public interface SystemRolesWriteDao {

    SystemRoles get(java.lang.Integer id);

    Integer save(SystemRoles systemRoles);

    Integer update(SystemRoles systemRoles);

    Integer getCount(@Param("queryMap") Map<String, String> queryMap);

    List<SystemRoles> page(@Param("queryMap") Map<String, String> queryMap,
                           @Param("start") Integer start, @Param("size") Integer size);

    Integer del(Integer id);

}
