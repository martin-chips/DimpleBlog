package com.dimple.modules.endModule.systemManager.repository;

import com.dimple.modules.endModule.systemManager.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author : Dimple
 * @version : 1.0
 * @class : UserRepository
 * @description :
 * @date : 12/26/18 19:14
 */
public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

    User findByUserLoginIdEquals(String loginId);

    User findByUserId(Integer id);

}
