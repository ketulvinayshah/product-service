package com.docker.demo.service;

import com.docker.demo.entity.Product;
import com.docker.demo.repository.ProductRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by kshah on 8/15/16.
 */
@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public List<Product> getProducts(){
        return Lists.newArrayList(productRepository.findAll());
    }

    public void addProduct(Product product){
        productRepository.save(product);
    }
}
