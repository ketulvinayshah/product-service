package com.product.service;

import com.product.domain.sql.Product;
import com.product.repository.ProductSQLRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by kshah on 8/15/16.
 */
@Service
public class ProductSQLService {

    @Autowired
    ProductSQLRepository productSQLRepository;

    @Autowired
    RabbitTemplate rabbitTemplate;

    Logger logger = LoggerFactory.getLogger(ProductSQLService.class);

    @Cacheable("products")
    public Iterable<Product> getProducts(String name, String category, String company, Float priceGreaterThan, Float priceLessThan){
        Iterable<Product> products;

        if(category != null && priceGreaterThan != null && priceLessThan != null){
            products = productSQLRepository.findByCategoryIgnoreCaseAndPriceBetweenOrderByPriceAsc(category, priceGreaterThan, priceLessThan);
        }else if(priceGreaterThan != null && priceLessThan != null){
            products = productSQLRepository.findByPriceBetweenOrderByPriceAsc(priceGreaterThan, priceLessThan);
        }else if(category != null && company != null){
            products = productSQLRepository.findByCategoryAndCompanyAllIgnoreCaseOrderByNameAsc(category, company);
        }else if(name != null){
            products = productSQLRepository.findByNameIgnoreCaseOrderByNameAsc(name);
        }else if(category != null){
            products = productSQLRepository.findByCategoryIgnoreCaseOrderByNameAsc(category);
        }else if(company != null){
            products = productSQLRepository.findByCompanyIgnoreCaseOrderByNameAsc(company);
        }else{
            products = productSQLRepository.findAll();
        }

        logger.info("Got products : {}",  products);
        return products;
    }

    public void addProductsToQueue(List<Product> products){
        logger.info("Adding products to queue : {}" , products);
        rabbitTemplate.convertAndSend("products.upsert", products);
    }

    @CacheEvict(cacheNames="products", allEntries=true)
    public Product addProduct(Product product){
        Product result = productSQLRepository.save(product);
        logger.info("Added product : {}", result.toString());
        return result;
    }

    @CacheEvict(cacheNames="products", allEntries=true)
    public void deleteProduct(Product product){
        productSQLRepository.delete(product);
        logger.info("Deleted product : {}", product.toString());
    }
}
