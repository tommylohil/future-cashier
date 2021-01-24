package com.blibli.demo.backend.service.impl;

import com.blibli.demo.backend.controller.model.request.InsertOrderRequest;
import com.blibli.demo.backend.controller.model.response.OrdersResponse;
import com.blibli.demo.backend.entity.Customer;
import com.blibli.demo.backend.entity.Order;
import com.blibli.demo.backend.entity.OrderItem;
import com.blibli.demo.backend.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service

public class OrderServiceImpl implements OrderService {

    private String name;
    private int orderId = 0;
    private List<Order> orderList = new ArrayList<Order>();

    @Override
    public List<Order> getOrders(boolean orderbyCustomerName, boolean orderByTotalPrice) {

        List<Order> sortedOrderList = new ArrayList<Order>(orderList);

        if (orderbyCustomerName) {
            sortedOrderList.sort(Comparator.comparing(a -> a.getCustomer().getName()));
        } else if (orderByTotalPrice) {
            sortedOrderList.sort((a, b) -> (int) (b.getTotalPrice() - a.getTotalPrice()));
        }

        return sortedOrderList;

    }

    @Override
    public Order getOrder(int id) {

        try {
            Order selectedOrder = (Order) orderList.get(id);
            return selectedOrder;
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        return null;

    }

    public int getNumberOfOrders() {

        return orderList.size();

    }

    @Override
    public Order insertOrder(InsertOrderRequest orderRequest) {
        List<OrderItem> orderItemList = new ArrayList<>();
        int orderItemId = 0;
        double totalPrice = 0f;

        // Populate orderItems
        for (OrderItem orderItem : orderRequest.getOrderItems()) {
            totalPrice += orderItem.getQuantity() * orderItem.getPrice();

            OrderItem newOrderItem = OrderItem
                    .builder()
                    .name(orderItem.getName())
                    .price(orderItem.getPrice())
                    .quantity(orderItem.getQuantity())
                    .id(orderItemId++)
                    .build();

            orderItemList.add(newOrderItem);
        }

        // Create Order
        Order newOrder = Order
                .builder()
                .totalPrice(totalPrice)
                .orderItems(orderItemList)
                .customer(orderRequest.getCustomer())
                .id(orderId++)
                .build();

        // Add order to Order list
        orderList.add(newOrder);

        return newOrder;
    }
}
