package com.example.demo.repository;

import com.example.demo.dao.Member;
import com.example.demo.dao.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.io.Serializable;

public interface MemberRepository extends Serializable,JpaRepository<Member,String>,
        JpaSpecificationExecutor<Member> {

    Member findMemberByNameAndPassword(String name ,String password);

}
