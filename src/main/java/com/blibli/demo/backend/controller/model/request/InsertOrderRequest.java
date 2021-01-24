package com.blibli.demo.backend.controller.model.request;

import com.blibli.demo.backend.entity.Customer;
import com.blibli.demo.backend.entity.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class InsertOrderRequest {
    private double totalPrice;
    private Customer customer;
    private List<OrderItem> orderItems;
}
