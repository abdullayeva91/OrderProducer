package com.kafka.orderproducer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class OrderProducer {
    private final KafkaTemplate<String, Order> kafkaTemplate;

    public OrderProducer(KafkaTemplate<String, Order> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
  @Scheduled(fixedRate = 2000)
    public void sendOrder() {
        try {

            String userId = "user_" + new Random().nextInt(5)+1;
            double amount = 50 + new Random().nextDouble() * 2000;
            Order order = new Order(new Random().nextInt(1000), amount, "CREATED");

            kafkaTemplate.send("ecommerce-orders",userId, order);
            System.out.println("Sifaris gonderildi: " + userId +"mebleg "+ amount);



        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
