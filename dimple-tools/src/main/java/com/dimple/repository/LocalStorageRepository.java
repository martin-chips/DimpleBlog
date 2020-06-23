package com.dimple.repository;

import com.dimple.domain.LocalStorage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @className: LocalStorageRepository
 * @description:
 * @author: Dimple
 * @date: 06/17/20
 */
public interface LocalStorageRepository extends JpaRepository<LocalStorage, Long>, JpaSpecificationExecutor<LocalStorage> {
}
