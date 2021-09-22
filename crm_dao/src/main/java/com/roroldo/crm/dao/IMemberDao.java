package com.roroldo.crm.dao;

import com.roroldo.crm.domain.Member;
import org.apache.ibatis.annotations.Select;

/**
 * 会员操作
 * @author 落霞不孤
 */
public interface IMemberDao {

    /**
     * 根据 id 查找会员
     * @param id 会员 id
     * @return 会员
     */
    @Select("select * from member where id = #{id}")
    Member findById(String id);
}
