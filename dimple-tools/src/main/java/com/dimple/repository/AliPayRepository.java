package com.dimple.repository;

import com.dimple.domain.AlipayConfig;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @className: AliPayRepository
 * @description:
 * @author: Dimple
 * @date: 06/17/20
 */
public interface AliPayRepository extends JpaRepository<AlipayConfig, Long> {
}
