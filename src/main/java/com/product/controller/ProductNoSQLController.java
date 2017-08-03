package com.product.controller;

import com.codahale.metrics.annotation.Timed;
import com.google.common.collect.Lists;
import com.product.domain.nosql.Product;
import com.product.service.ProductNoSQLService;
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
public class ProductNoSQLController {

    @Autowired
    ProductNoSQLService productNoSQLService;

    @RequestMapping(method = RequestMethod.GET, value = "/v2/products")
    @ApiOperation(value = "Get products")
    @Timed
    public ResponseEntity<List<Product>> getProducts(@ApiParam @RequestParam(required = false) String name, @ApiParam @RequestParam(required = false) String category, @ApiParam @RequestParam(required = false) String company){
        return ResponseEntity.ok(Lists.newArrayList(productNoSQLService.getProductsNoSql(name, category, company)));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/v2/product")
    @ApiOperation(value = "Add product")
    @Timed
    public ResponseEntity<Product> addProduct(@ApiParam(required = true) @RequestBody @Valid Product product){
        return ResponseEntity.ok(productNoSQLService.addProductNoSql(product));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/v2/product")
    @ApiOperation(value = "Update product")
    @Timed
    public ResponseEntity<Product> updateProduct(@ApiParam(required = true) @RequestBody @Valid Product product){
        return ResponseEntity.ok(productNoSQLService.addProductNoSql(product));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/v2/product")
    @ApiOperation(value = "Delete product")
    @Timed
    public ResponseEntity<?> deleteProduct(@ApiParam(required = true) @RequestBody @Valid Product product){
        productNoSQLService.deleteProductNoSql(product);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/v2/products")
    @ApiOperation(value = "Add/Update products")
    @Timed
    public ResponseEntity<?> addProduct(@ApiParam(required = true) @RequestBody @Valid List<Product> products){
        productNoSQLService.addProductsToQueueNoSql(products);
        return ResponseEntity.ok().build();
    }
}
