package com.hante.repository;

import com.hante.domain.model.SysMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SysMenuRepository extends JpaRepository<SysMenu, String>, JpaSpecificationExecutor<SysMenu> {
}
