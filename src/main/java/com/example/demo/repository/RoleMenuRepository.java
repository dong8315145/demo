package com.example.demo.repository;

import com.example.demo.dao.RoleMenu;
import com.example.demo.dao.SysMenu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;

public interface RoleMenuRepository extends Serializable,JpaRepository<RoleMenu,String> {

    List<SysMenu> findAllByRoleId(String roleId);
}
