package com.roroldo.crm.dao;


import com.roroldo.crm.domain.SysLog;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ISysLogDao {

    @Insert("insert into syslog(visitTime, username, ip, url, executionTime, method)" +
            " values(#{visitTime}, #{username}, #{ip}, #{url}, #{executionTime}, #{method})")
    void save(SysLog sysLog);

    @Select("select * from syslog where username like #{searchUser}")
    List<SysLog> findAllByUsername(String searchUser);

    @Select("select * from syslog")
    List<SysLog> findAll();

    @Delete("delete from syslog where id = #{id}")
    void deleteById(Integer id);
}
