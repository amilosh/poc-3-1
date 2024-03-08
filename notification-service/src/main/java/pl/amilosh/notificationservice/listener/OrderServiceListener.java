package pl.amilosh.notificationservice.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import pl.amilosh.notificationservice.event.OrderSavedEvent;

@Slf4j
@Component
public class OrderServiceListener {

    @KafkaListener(topics = "notificationTopic")
    public void handleNotification(OrderSavedEvent orderSavedEvent) {
        log.info("Message from notificationTopic: {}", orderSavedEvent.getOrderNumber());
    }
}
