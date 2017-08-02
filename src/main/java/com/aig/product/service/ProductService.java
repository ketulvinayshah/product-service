package com.aig.product.service;

import com.aig.product.configuration.MessagingConfiguration;
import com.aig.product.entity.Product;
import com.aig.product.repository.ProductRepository;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by kshah on 8/15/16.
 */
@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    RabbitTemplate rabbitTemplate;

    Logger logger = LoggerFactory.getLogger(ProductService.class);

    private volatile boolean cacheMiss = false;

    public List<Product> getProducts(){
        List<Product> products = Lists.newArrayList(productRepository.findAll());
        logger.info("Getting a list of all products : {}",  products);
        return products;
    }

    public void addProductToQueue(Product product){
        logger.info("Adding product to queue : {}" , product.toString());
        rabbitTemplate.convertAndSend(MessagingConfiguration.queueName, product.toJsonString());
    }

    public void addProduct(Product product){
        logger.info("Adding a product : {}", product.toString());
        productRepository.save(product);
    }

    @Cacheable("Product")
    public Product getProduct(String name){
        logger.info("Getting product : {}", name);
        return productRepository.findByName(name);
    }
}
