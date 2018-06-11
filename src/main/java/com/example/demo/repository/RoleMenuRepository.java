package com.example.demo.repository;

import com.example.demo.dao.RoleMenu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface RoleMenuRepository extends Serializable,JpaRepository<RoleMenu,String> {
}
