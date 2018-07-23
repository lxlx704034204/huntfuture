package com.hante.repository;

import com.hante.domain.model.SysRoleMenuBO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SysRoleMenuRepository extends JpaRepository<SysRoleMenuBO, String>, JpaSpecificationExecutor<SysRoleMenuBO> {
}
