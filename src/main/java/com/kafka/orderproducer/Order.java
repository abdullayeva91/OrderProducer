package com.kafka.orderproducer;

public record Order(int orderId, double amount, String status) {
}
