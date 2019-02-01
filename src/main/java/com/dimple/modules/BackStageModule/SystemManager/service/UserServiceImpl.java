package com.dimple.modules.BackStageModule.SystemManager.service;

import com.dimple.modules.BackStageModule.SystemManager.bean.User;
import com.dimple.framework.exception.user.UserAccountNotExistsException;
import com.dimple.modules.BackStageModule.SystemManager.repository.UserRepository;
import com.dimple.modules.BackStageModule.SystemManager.repository.UserRoleRepository;
import com.dimple.utils.JpaUpdateUtil;
import com.dimple.utils.Md5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.Date;
import java.util.LinkedList;
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
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserRoleRepository userRoleRepository;

    @Override
    public User findByUserLoginId(String loginId) {
        return userRepository.findByUserLoginIdEquals(loginId);
    }

    @Override
    public Page<User> getAllUsers(String phone, Boolean locked, Date startTime, Date endTime, String loginId, Pageable pageable) {
        return userRepository.findAll((Specification<User>) (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> list = new LinkedList<>();
            if (StringUtils.isNotBlank(phone)) {
                list.add(criteriaBuilder.like(root.get("phone").as(String.class), "%" + phone + "%"));
            }
            if (startTime != null) {
                list.add(criteriaBuilder.greaterThanOrEqualTo(root.get("start_time").as(Date.class), startTime));
            }
            if (endTime != null) {
                list.add(criteriaBuilder.lessThanOrEqualTo(root.get("end_time").as(Date.class), endTime));
            }
            if (StringUtils.isNotBlank(loginId)) {
                list.add(criteriaBuilder.like(root.get("loginId").as(String.class), loginId));
            }
            if (locked != null) {
                list.add(criteriaBuilder.equal(root.get("locked").as(Boolean.class), locked));
            }
            Predicate[] predicates = new Predicate[list.size()];
            return criteriaBuilder.and(list.toArray(predicates));
        }, pageable);
    }

    @Override
    public Integer deleteUser(Integer[] ids) {
        if (ids == null && ids.length == 0) {
            throw new UserAccountNotExistsException();
        }
        int count = 0;
        for (Integer id : ids) {
            userRepository.deleteById(id);
            count++;
        }
        return count;
    }

    @Override
    public User resetPassword(Integer id, String newPassword) {
        if (id == null) {
            return null;
        }
        User user = userRepository.findByUserId(id);
        if (user == null) {
            throw new UserAccountNotExistsException();
        }
        if (StringUtils.isBlank(newPassword)) {
            user.setPassword("123456");
        } else {
            user.setPassword(newPassword);
        }
        return userRepository.save(user);
    }

    @Override
    public User updateUserInfo(User user) {
        if (user == null || user.getUserId() == null) {
            return null;
        }
        User UserDB = userRepository.findByUserId(user.getUserId());
        JpaUpdateUtil.copyProperties(UserDB, user);
        return userRepository.save(user);
    }

    @Override
    public User insertUser(User user) {
        if (user == null || StringUtils.isBlank(user.getPassword()) || StringUtils.isBlank(user.getUserLoginId()) || StringUtils.isBlank(user.getEmail()) || user.getSex() == null || StringUtils.isBlank(user.getUserName())) {
            return null;
        }
        user.setCreateTime(new Date());
        user.setLocked(false);
        //设置盐
        user.setSalt(UUID.randomUUID().toString().substring(0, 8));
        user.setPassword(Md5Util.generatorMd5(user.getPassword(), user.getSalt()));
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Integer id) {
        if (id == null) {
            return null;
        }
        User userDB = userRepository.findByUserId(id);
        if (userDB != null && userRoleRepository.findByUserId(id) != null) {
            userDB.setRoleId(userRoleRepository.findByUserId(id).getRoleId());
        }
        return userDB;
    }

    @Override
    public User changeLocked(Integer id, Boolean locked) {
        User user = userRepository.findByUserId(id);
        if (user == null) {
            return null;
        }
        user.setLocked(!locked);
        return userRepository.save(user);

    }
}
