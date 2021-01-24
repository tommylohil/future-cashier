package com.blibli.demo.backend.service;

import com.blibli.demo.backend.controller.model.request.InsertOrderRequest;
import com.blibli.demo.backend.controller.model.response.OrdersResponse;
import com.blibli.demo.backend.entity.Order;

import java.util.List;


public interface OrderService {
    public Order getOrder(int id);
    public List<Order> getOrders(boolean orderbyCustomerName, boolean orderByTotalPrice);
    public Order insertOrder(InsertOrderRequest orderRequest);
}
