package com.example.demo.repository;

import com.example.demo.dao.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

/**
 * @program: demo
 * @description:
 * @author: Terdy.Zheng
 * @create: 2018-06-11 14:58
 **/
public interface SysUserRepository extends Serializable,JpaRepository<SysUser,String> {

    SysUser findByNameAndPasswordAndFlag(String name,String password,String flag);

    SysUser findByPhoneAndPasswordAndFlag(String name,String password,String flag);
}
