package com.aig.product.repository;

import com.aig.product.entity.Product;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, String> {
    Product findByName(String name);
}

