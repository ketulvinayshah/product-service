package com.product.service;

import com.product.domain.sql.Product;
import com.product.repository.ProductSQLRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * Created by kshah on 8/1/17.
 */
@Service
public class MessagingSQLService {

    @Autowired
    ProductSQLRepository productSQLRepository;

    Logger logger = LoggerFactory.getLogger(MessagingSQLService.class);

    @RabbitListener(queues={"snippet.upsert"})
    @CacheEvict(cacheNames="products", allEntries=true)
    public void rabbitListen(List<Product> products) throws IOException {
        products.forEach(product -> productSQLRepository.save(product));
    }

}