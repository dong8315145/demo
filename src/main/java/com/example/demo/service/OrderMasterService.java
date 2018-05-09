package com.example.demo.service;

import com.example.demo.common.enums.PayStatusEnum;
import com.example.demo.dto.OrderMasterDTO;
import org.springframework.data.domain.Pageable;

public interface OrderMasterService {

    OrderMasterDTO create (OrderMasterDTO orderMasterDTO);
    OrderMasterDTO findOne (String orderMasterId);
    OrderMasterDTO findList (String buyerOpenid, Pageable pageable);
    OrderMasterDTO updateOne (String orderMasterId, PayStatusEnum payStatusEnum);

}
