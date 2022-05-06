package br.com.alura.ecommerce;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class NewOrderMain {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        try(var kafkaDispatcher = new KafkaDispatcher()) {
            for (var i = 0; i < 10; i++) {
                var key = UUID.randomUUID().toString();
                var value = key + ",52000,789582585";
                kafkaDispatcher.send("ECOMMERCE_NEW_ORDER", key, value);

                var email = "Thank you for your order! We are processing your order!";
                kafkaDispatcher.send("ECOMMERCE_SEND_EMAIL", key, email);
            }
        }
    }

}
