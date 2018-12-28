package com.dimple.repository;

import com.dimple.bean.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author : Dimple
 * @version : 1.0
 * @class : UserRoleRepository
 * @description :
 * @date : 12/27/18 14:13
 */
public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {

    List<UserRole> findAllByUserId(Integer id);

}
