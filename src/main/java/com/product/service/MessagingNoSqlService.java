package com.product.service;

import com.product.domain.nosql.Product;
import com.product.repository.ProductNoSQLRepository;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * Created by kshah on 8/1/17.
 */
@Service
public class MessagingNoSqlService {

    @Autowired
    ProductNoSQLRepository productNoSQLRepository;

    Logger logger = LoggerFactory.getLogger(MessagingNoSqlService.class);

    @KafkaListener(topics = "productTopic")
    @CacheEvict(cacheNames="products", allEntries=true)
    public void listenNoSql(ConsumerRecord<String, Product> cr) throws Exception {
        productNoSQLRepository.save(cr.value());
    }

}