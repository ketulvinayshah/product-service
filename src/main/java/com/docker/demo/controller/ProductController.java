package com.docker.demo.controller;

import com.docker.demo.entity.Product;
import com.docker.demo.service.ProductService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    public List<Product> getProducts(){
        return productService.getProducts();
    }


    @RequestMapping(method = RequestMethod.POST, value = "products")
    @ApiOperation(value = "Add a product")
    public void addProduct(@ApiParam @RequestBody Product product){
        productService.addProduct(product);
    }
}
