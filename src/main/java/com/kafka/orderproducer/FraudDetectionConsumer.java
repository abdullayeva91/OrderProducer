package com.kafka.orderproducer;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class FraudDetectionConsumer {
    private final ObjectMapper objectMapper=new ObjectMapper();



    @KafkaListener(topics = "ecommerce-orders", groupId = "fraud-detection-group")
    public void consume(String message, org.apache.kafka.clients.consumer.ConsumerRecord<String, String> record) {
        try {
            String userId = record.key();
            JsonNode orderJson = objectMapper.readTree(message);
            double amount = orderJson.get("amount").doubleValue();
            if (amount > 1000){
                System.out.println("Diqqet: Supheli sifaris askarlandi! User: "+ userId);
            }else {
                System.out.println("Sifaris tesdiqlendi. User: "+ userId);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
