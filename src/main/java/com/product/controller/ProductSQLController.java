package com.product.controller;

import com.product.domain.sql.Product;
import com.product.service.ProductSQLService;
import com.codahale.metrics.annotation.Timed;
import com.google.common.collect.Lists;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by kshah on 8/15/16.
 */
@RestController
public class ProductSQLController {

    @Autowired
    ProductSQLService productSQLService;

    @RequestMapping(method = RequestMethod.GET, value = "v1/products")
    @ApiOperation(value = "Get products")
    @Timed
    public ResponseEntity<List<Product>> getProducts(@ApiParam @RequestParam(required = false) String name, @ApiParam @RequestParam(required = false) String category, @ApiParam @RequestParam(required = false) String company, @ApiParam @RequestParam(required = false) float priceGreaterThan, @ApiParam @RequestParam(required = false) float priceLessThan){
        return ResponseEntity.ok(Lists.newArrayList(productSQLService.getProducts(name, category, company, priceGreaterThan, priceLessThan)));
    }

    @RequestMapping(method = RequestMethod.POST, value = "v1/product")
    @ApiOperation(value = "Add product")
    @Timed
    public ResponseEntity<Product> addProduct(@ApiParam(required = true) @RequestBody @Valid Product product){
        return ResponseEntity.ok(productSQLService.addProduct(product));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "v1/product")
    @ApiOperation(value = "Update product")
    @Timed
    public ResponseEntity<Product> updateProduct(@ApiParam(required = true) @RequestBody @Valid Product product){
        return ResponseEntity.ok(productSQLService.addProduct(product));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "v1/product")
    @ApiOperation(value = "Delete product")
    @Timed
    public ResponseEntity<?> deleteProduct(@ApiParam(required = true) @RequestBody @Valid Product product){
        productSQLService.deleteProduct(product);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(method = RequestMethod.POST, value = "v1/products")
    @ApiOperation(value = "Add/Update products")
    @Timed
    public ResponseEntity<?> addProduct(@ApiParam(required = true) @RequestBody @Valid List<Product> products){
        productSQLService.addProductsToQueue(products);
        return ResponseEntity.ok().build();
    }
}
