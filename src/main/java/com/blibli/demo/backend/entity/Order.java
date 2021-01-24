package com.blibli.demo.backend.entity;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder

public class Order {
    private int id;
    private List<OrderItem> orderItems;
    private double totalPrice;
    private Customer customer;
}
