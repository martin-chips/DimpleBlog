package com.dimple.repository;

import com.dimple.bean.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : Dimple
 * @version : 1.0
 * @class : VisitorRepository
 * @description :
 * @date : 12/26/18 19:15
 */
public interface VisitorRepository extends JpaRepository<Visitor, Integer> {
}
