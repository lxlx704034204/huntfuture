package com.hante.repository;

import com.hante.domain.model.SysRoot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SysRootRepository extends JpaRepository<SysRoot, String>, JpaSpecificationExecutor<SysRoot> {
}
