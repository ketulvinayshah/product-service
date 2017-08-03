package com.product.service;

import com.product.domain.nosql.Product;
import com.product.repository.ProductNoSQLRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by kshah on 8/15/16.
 */
@Service
public class ProductNoSQLService {

    @Autowired
    ProductNoSQLRepository productNoSQLRepository;

    @Autowired
    private KafkaTemplate<String, Product> template;

    Logger logger = LoggerFactory.getLogger(ProductSQLService.class);

    @Cacheable("products")
    public Iterable<Product> getProductsNoSql(String name, String category, String company){
        Iterable<Product> products;

        if(name != null){
            products = productNoSQLRepository.findByName(name);
        }else if(category != null){
            products = productNoSQLRepository.findByCategory(category);
        }else if(company != null){
            products = productNoSQLRepository.findByCompany(company);
        }else{
            products = productNoSQLRepository.findAll();
        }
        
        logger.info("Got products : {}",  products);
        return products;
    }

    public void addProductsToQueueNoSql(List<Product> products){
        logger.info("Adding products to queue : {}" , products);
        products.forEach(product -> template.send("productTopic", product));
    }

    @CacheEvict(cacheNames="products", allEntries=true)
    public Product addProductNoSql(Product product){
        Product result = productNoSQLRepository.save(product);
        logger.info("Added product : {}", result.toString());
        return result;
    }

    @CacheEvict(cacheNames="products", allEntries=true)
    public void deleteProductNoSql(Product product){
        productNoSQLRepository.delete(product);
        logger.info("Deleted product : {}", product.toString());
    }
}
