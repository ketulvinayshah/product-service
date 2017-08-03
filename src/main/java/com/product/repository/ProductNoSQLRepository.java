package com.product.repository;

/**
 * Created by kshah on 8/2/17.
 */

import com.product.domain.nosql.Product;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductNoSQLRepository extends CassandraRepository<Product> {

    @Query("select * from product where name=?0 ALLOW FILTERING")
    List<Product> findByName(String name);
    @Query("select * from product where category=?0 ALLOW FILTERING")
    List<Product> findByCategory(String category);
    @Query("select * from product where company=?0 ALLOW FILTERING")
    List<Product> findByCompany(String company);
    @Query("select * from product")
    List<Product> findAll();

}