package com.blibli.demo.backend.controller.model.response;

import com.blibli.demo.backend.entity.Customer;
import com.blibli.demo.backend.entity.OrderItem;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder

public class OrderResponse {
    private int id;
    private double totalPrice;
    private List<OrderItem> orderItems;
    private Customer customer;
}
