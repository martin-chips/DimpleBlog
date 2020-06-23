package com.dimple.repository;

import com.dimple.domain.EmailConfig;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @className: EmailRepository
 * @description:
 * @author: Dimple
 * @date: 06/17/20
 */
public interface EmailRepository extends JpaRepository<EmailConfig, Long> {
}
