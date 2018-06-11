package com.example.demo.repository;

import com.example.demo.dao.SysMenu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface SysMenuRepository extends Serializable,JpaRepository<SysMenu,String> {
}
