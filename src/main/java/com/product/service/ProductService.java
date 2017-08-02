package com.product.service;

import com.product.domain.Product;
import com.product.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

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

    @Cacheable("products")
    public Iterable<Product> getProducts(String name, String category, String company, Float priceGreaterThan, Float priceLessThan){
        Iterable<Product> products;

        if(category != null && priceGreaterThan != null && priceLessThan != null){
            products = productRepository.findByCategoryIgnoreCaseAndPriceBetweenOrderByPriceAsc(category, priceGreaterThan, priceLessThan);
        }else if(priceGreaterThan != null && priceLessThan != null){
            products = productRepository.findByPriceBetweenOrderByPriceAsc(priceGreaterThan, priceLessThan);
        }else if(category != null && company != null){
            products = productRepository.findByCategoryAndCompanyAllIgnoreCaseOrderByNameAsc(category, company);
        }else if(name != null){
            products = productRepository.findByNameIgnoreCaseOrderByNameAsc(name);
        }else if(category != null){
            products = productRepository.findByCategoryIgnoreCaseOrderByNameAsc(category);
        }else if(company != null){
            products = productRepository.findByCompanyIgnoreCaseOrderByNameAsc(company);
        }else{
            products = productRepository.findAll();
        }

        logger.info("Got products : {}",  products);
        return products;
    }

//    public void addProductToQueue(Product product){
//        logger.info("Adding product to queue : {}" , product.toString());
//        rabbitTemplate.convertAndSend(MessagingConfiguration.queueName, product.toJsonString());
//    }

    public Product addProduct(Product product){
        Product result = productRepository.save(product);
        logger.info("Added product : {}", result.toString());
        return result;
    }

    public void deleteProduct(Product product){
        productRepository.delete(product);
        logger.info("Deleted product : {}", product.toString());
    }
}
