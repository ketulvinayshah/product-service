package com.product.service;

import com.product.domain.sql.Product;
import com.product.repository.ProductNoSQLRepository;
import com.product.repository.ProductSQLRepository;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * Created by kshah on 8/1/17.
 */
@Service
public class MessagingService {

    @Autowired
    ProductSQLRepository productSQLRepository;

    @Autowired
    ProductNoSQLRepository productNoSQLRepository;

    Logger logger = LoggerFactory.getLogger(MessagingService.class);

    @RabbitListener(queues={"snippet.upsert"})
    @CacheEvict(cacheNames="products", allEntries=true)
    public void rabbitListen(List<Product> products) throws IOException {
        products.forEach(product -> productSQLRepository.save(product));
    }

    @KafkaListener(topics = "productTopic")
    @CacheEvict(cacheNames="product", allEntries=true)
    public void listen(ConsumerRecord<String, List<Product>> cr) throws Exception {
        cr.value().forEach(product -> productNoSQLRepository.save(product));
    }

}