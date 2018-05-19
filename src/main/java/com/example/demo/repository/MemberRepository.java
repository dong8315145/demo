package com.example.demo.repository;

import com.example.demo.entity.Member;
import com.example.demo.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import sun.security.util.Password;

public interface MemberRepository extends JpaRepository<Member,String> {

    Member findMemberByNameAndPassword(String name ,String password);

}
