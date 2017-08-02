package com.product.controller;

import com.product.domain.Product;
import com.product.service.ProductService;
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
public class ProductController {

    @Autowired
    ProductService productService;

    @RequestMapping(method = RequestMethod.GET, value = "products")
    @ApiOperation(value = "Get products")
    @Timed
    public ResponseEntity<List<Product>> getProducts(@ApiParam @RequestParam(required = false) String name, @ApiParam @RequestParam(required = false) String category, @ApiParam @RequestParam(required = false) String company, @ApiParam @RequestParam(required = false) float priceGreaterThan, @ApiParam @RequestParam(required = false) float priceLessThan){
        return ResponseEntity.ok(Lists.newArrayList(productService.getProducts(name, category, company, priceGreaterThan, priceLessThan)));
    }

    @RequestMapping(method = RequestMethod.POST, value = "product")
    @ApiOperation(value = "Add product")
    @Timed
    public ResponseEntity<Product> addProduct(@ApiParam(required = true) @RequestBody @Valid Product product){
        return ResponseEntity.ok(productService.addProduct(product));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "product")
    @ApiOperation(value = "Update product")
    @Timed
    public ResponseEntity<Product> updateProduct(@ApiParam(required = true) @RequestBody @Valid Product product){
        return ResponseEntity.ok(productService.addProduct(product));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "product")
    @ApiOperation(value = "Delete product")
    @Timed
    public ResponseEntity<?> deleteProduct(@ApiParam(required = true) @RequestBody @Valid Product product){
        return ResponseEntity.ok().build();
    }
}
