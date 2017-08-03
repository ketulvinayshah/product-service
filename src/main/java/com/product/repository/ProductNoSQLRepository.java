package com.product.repository;

/**
 * Created by kshah on 8/2/17.
 */

import com.product.domain.nosql.Product;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductNoSQLRepository extends PagingAndSortingRepository<Product, String> {

    @Query("select * from product where name=?0")
    List<Product> findByName(String name);
    @Query("select * from product where category=?0")
    List<Product> findByCategory(String category);
    @Query("select * from product where company=?0")
    List<Product> findByCompany(String company);

}