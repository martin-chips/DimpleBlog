package com.dimple.service.impl;

import com.dimple.bean.User;
import com.dimple.bean.UserExample;
import com.dimple.dao.UserMapper;
import com.dimple.exception.user.UserAccountNotExistsException;
import com.dimple.service.UserService;
import com.dimple.utils.md5PasswordGenerator.Md5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @ClassName: UserServiceImpl
 * @Description:
 * @Auther: Owenb
 * @Date: 11/19/18 17:20
 * @Version: 1.0
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public User findByUserLoginId(String loginId) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUserLoginIdEqualTo(loginId);
        List<User> users = userMapper.selectByExample(userExample);
        if (users == null || users.size() == 0) {
            return null;
        }
        return users.get(0);
    }

    @Override
    public List<User> getAllUsers(String phone, Boolean locked, Date startTime, Date endTime, String loginId) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        if (StringUtils.isNotBlank(phone)) {
            criteria.andPhoneLike(phone);
        }
        if (StringUtils.isNotBlank(loginId)) {
            criteria.andUserLoginIdLike(loginId);
        }
        if (locked != null) {
            //为1表示已经被锁定
            if (locked == true) {
                criteria.andLockedEqualTo(true);
            } else if (locked == false) {//为0表示没有被锁定
                criteria.andLockedEqualTo(false);
            }
        }
        if (startTime != null && endTime != null) {
            criteria.andCreateTimeBetween(startTime, endTime);
        } else if (startTime == null && endTime != null) {
            criteria.andCreateTimeLessThanOrEqualTo(endTime);
        } else if (startTime != null && endTime == null) {
            criteria.andCreateTimeGreaterThanOrEqualTo(startTime);
        }
        List<User> users = userMapper.selectByExample(userExample);
        return users;
    }

    @Override
    public Integer deleteUser(Integer[] ids) {
        if (ids == null && ids.length == 0) {
            throw new UserAccountNotExistsException();
        }
        int count = 0;
        for (Integer id : ids) {
            count += userMapper.deleteByPrimaryKey(id);
        }
        return count;
    }

    @Override
    public Integer resetPassword(Integer id, String newPassword) {
        if (id == null) {
            return -1;
        }
        User user = userMapper.selectByPrimaryKey(id);
        if (user == null) {
            throw new UserAccountNotExistsException();
        }
        if (StringUtils.isBlank(newPassword)) {
            user.setPassword("12345");
        } else {
            user.setPassword(newPassword);
        }
        int i = userMapper.updateByPrimaryKeySelective(user);
        return i;
    }

    @Override
    public Integer updateUserInfo(User user) {
        if (user == null || user.getUserId() == null) {
            return -1;
        }
        User userDB = userMapper.selectByPrimaryKey(user.getUserId());
        if (userDB == null) {
            return -1;
        }
        if (StringUtils.isNotBlank(user.getPassword())) {
            user.setPassword(Md5Util.generatorMd5(user.getPassword(), userDB.getSalt()));
        }
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public Integer insertUser(User user) {
        if (user == null || StringUtils.isBlank(user.getPassword()) || StringUtils.isBlank(user.getUserLoginId()) || StringUtils.isBlank(user.getEmail()) || user.getSex() == null || StringUtils.isBlank(user.getUserName())) {
            return -1;
        }
        user.setCreateTime(new Date());
        user.setLocked(false);
        //设置盐
        user.setSalt(UUID.randomUUID().toString().substring(0, 8));
        user.setPassword(Md5Util.generatorMd5(user.getPassword(), user.getSalt()));
        return userMapper.insert(user);
    }

    @Override
    public User getUserById(Integer id) {
        if (id == null) {
            return null;
        }
        User user = userMapper.selectByPrimaryKey(id);
        return user;
    }

    @Override
    public Integer changeLocked(Integer id, Boolean locked) {
        User user = userMapper.selectByPrimaryKey(id);
        if (user == null) {
            return -1;
        }
        user.setLocked(!locked);
        int i = userMapper.updateByPrimaryKey(user);
        return i;
    }
}
