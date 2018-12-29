package com.dimple.service;

import com.dimple.bean.User;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;

/**
 * @ClassName: UserService
 * @Description:
 * @Auther: Owenb
 * @Date: 11/19/18 17:20
 * @Version: 1.0
 */
@CacheConfig(cacheNames = "user")
public interface UserService {
    //    @Cacheable(key = "'loginId'.concat(#loginId)")
    User findByUserLoginId(String loginId);

    /**
     * 获取所有的用户的数据
     *
     * @param phone
     * @param startTime
     * @param endTime
     * @param loginId
     * @param pageable
     * @return
     */
    Page<User> getAllUsers(String phone, Boolean locked, Date startTime, Date endTime, String loginId, Pageable pageable);

    /**
     * 根据User的id删除User
     *
     * @param ids
     * @return
     */
    Integer deleteUser(Integer[] ids);

    /**
     * 重置密码
     *
     * @param id
     * @return
     */
    User resetPassword(Integer id, String newPassword);

    /**
     * 更新用户的信息
     *
     * @param user
     * @return
     */
    User updateUserInfo(User user);

    /**
     * 新建一个User
     *
     * @param user
     * @return
     */
    User insertUser(User user);

    /**
     * 根据Id获取User对象
     *
     * @param id
     * @return
     */
    User getUserById(Integer id);

    /**
     * 更改用户的状态
     *
     * @param id
     * @param locked
     * @return
     */
    User changeLocked(Integer id, Boolean locked);


}
