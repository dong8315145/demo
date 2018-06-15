package com.example.demo.repository;

import com.example.demo.dao.SysMenu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;

public interface SysMenuRepository extends Serializable,JpaRepository<SysMenu,String> {

    List<SysMenu> findAllByPidAndFlag(String pid, String flag);




}
