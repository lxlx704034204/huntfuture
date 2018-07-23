package com.hante.repository;

import com.hante.domain.model.SysAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SysAccountRepository extends JpaRepository<SysAccount, String>, JpaSpecificationExecutor<SysAccount> {
}
