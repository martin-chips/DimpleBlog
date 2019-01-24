package com.dimple.modules.endModule.visitorManager.repository;

import com.dimple.modules.endModule.visitorManager.bean.Blacklist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @ClassName: BlackListRepository
 * @Description:
 * @Auther: Dimple
 * @Date: 2019/1/20 13:44
 * @Version: 1.0
 */
public interface BlackListRepository extends JpaRepository<Blacklist, Integer>, JpaSpecificationExecutor<Blacklist> {

    Blacklist getById(Integer id);

}
