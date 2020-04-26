package com.ayit.demo.mapper;/**
 * Created by lipeifeng on 2020/4/23.
 **/


import com.ayit.demo.entity.Book;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Auther: lipeifeng
 * @Date: 2020/4/23 10:43
 * @Description:
 */
@Mapper
public interface BookMapper {
    /*@Select("select * from cib_user where id = #{id}")
    Book findUserById(@Param("id") int id);

    @Select("select * from cib_user")
    List<Book> findAllUsers();

    @Insert("insert into cib_user (user_name,create_time) values(#{userName},#{createTime})")
    void addUser(Book user);

    @Update("update cib_user set user_name=#{userName},create_time=#{createTime} where id = #{id}")
    void updateUser(Book user);*/

    @Insert("insert into book (id,name) values(#{id},#{name})")
    int save(Book book);

    @Select("select * from book")
    @Results({
            @Result(id = true, property = "id", column = "id", javaType = Long.class),
            @Result(property = "name", column = "name", javaType = String.class),
    })
    List<Book> findAll();

}