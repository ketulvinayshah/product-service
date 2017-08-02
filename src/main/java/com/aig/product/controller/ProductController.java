package com.aig.product.controller;

import com.aig.product.entity.Product;
import com.aig.product.service.ProductService;
import com.codahale.metrics.annotation.Timed;
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
    @ApiOperation(value = "Get list of products")
    @Timed
    public List<Product> getProducts(){
        return productService.getProducts();
    }


    @RequestMapping(method = RequestMethod.POST, value = "product")
    @ApiOperation(value = "Add a product")
    @Timed
    public ResponseEntity<String> addProduct(@ApiParam @RequestBody @Valid Product product){
        productService.addProductToQueue(product);
        return ResponseEntity.ok("Product Added");
    }

    @RequestMapping(method = RequestMethod.GET, value = "product")
    @ApiOperation(value = "Get a product")
    @Timed
    public Product getProduct(@ApiParam @RequestParam String name){
        return productService.getProduct(name);
    }
}
