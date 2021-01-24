package com.blibli.demo.backend.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class OrderItem {
    private int id;
    private double price;
    private double quantity;
    private String name;
}
