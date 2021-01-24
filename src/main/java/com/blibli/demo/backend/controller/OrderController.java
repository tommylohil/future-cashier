package com.blibli.demo.backend.controller;

import com.blibli.demo.backend.controller.model.request.InsertOrderRequest;
import com.blibli.demo.backend.controller.model.response.OrderResponse;
import com.blibli.demo.backend.controller.model.response.OrdersResponse;
import com.blibli.demo.backend.entity.Order;
import com.blibli.demo.backend.service.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {
    @Autowired
    private OrderServiceImpl orderService;

    // Get all
    @GetMapping(value = "/orders", produces = MediaType.APPLICATION_JSON_VALUE)
    public OrdersResponse getOrders (
            @RequestParam(value = "orderByCustomerName", defaultValue = "false") String orderByCustomerName,
            @RequestParam(value = "orderByTotalPrice", defaultValue = "false") String orderByTotalPrice
    ) {

        OrdersResponse response = OrdersResponse
                .builder()
                .data(orderService.getOrders(orderByCustomerName.equals("true"), orderByTotalPrice.equals("true")))
                .numberOfOrders(orderService.getNumberOfOrders())
                .build();

        return response;

    }

    // Get by id
    @GetMapping(value = "/orders/{orderId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Order getOrderById (
            @PathVariable int orderId
    ) {
        return orderService.getOrder(orderId);
    }

    // Post
    @PostMapping(value = "/orders")
    public OrderResponse submitOrder (
            @RequestBody InsertOrderRequest orderRequest) {
        Order order = orderService.insertOrder(orderRequest);
        return OrderResponse
                .builder()
                .totalPrice(order.getTotalPrice())
                .orderItems(order.getOrderItems())
                .customer(order.getCustomer())
                .id(order.getId())
                .build();
    }
}
