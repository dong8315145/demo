package com.example.demo.repository;

import com.example.demo.entity.OrderDetail;
import com.example.demo.entity.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerInfoRepository extends JpaRepository<SellerInfo,String> {
}
