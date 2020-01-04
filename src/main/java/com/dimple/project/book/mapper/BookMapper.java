package com.dimple.project.book.mapper;

import com.dimple.project.book.domain.Book;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @className: BookMapper
 * @description:
 * @author: Dimple
 * @date: 2019-11-27
 */
public interface BookMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Book selectBookById(Long id);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param book 实例对象
     * @return 对象列表
     */
    List<Book> selectBookList(Book book);

    /**
     * 新增数据
     *
     * @param book 实例对象
     * @return 影响行数
     */
    int insertBook(Book book);

    /**
     * 修改数据
     *
     * @param book 实例对象
     * @return 影响行数
     */
    int updateBook(Book book);

    /**
     * 通过主键删除数据
     *
     * @param ids 主键
     * @return 影响行数
     */
    int deleteBookByIds(@Param("ids") Long[] ids, @Param("username") String username);

}
