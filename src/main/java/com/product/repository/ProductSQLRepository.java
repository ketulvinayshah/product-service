package com.product.repository;

import com.product.domain.Product;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductSQLRepository extends PagingAndSortingRepository<Product, String> {

    List<Product> findByNameIgnoreCaseOrderByNameAsc(String name);
    List<Product> findByCategoryIgnoreCaseOrderByNameAsc(String category);
    List<Product> findByCompanyIgnoreCaseOrderByNameAsc(String company);
    List<Product> findByCategoryAndCompanyAllIgnoreCaseOrderByNameAsc(String category, String company);
    List<Product> findByPriceBetweenOrderByPriceAsc(float greaterThan, float lessThan);
    List<Product> findByCategoryIgnoreCaseAndPriceBetweenOrderByPriceAsc(String category, float greaterThan, float lessThan);

}

