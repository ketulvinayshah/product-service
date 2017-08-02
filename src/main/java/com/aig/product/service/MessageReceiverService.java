package com.aig.product.service;

import com.aig.product.entity.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

/**
 * Created by kshah on 8/1/17.
 */
@Service
public class MessageReceiverService {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ProductService productService;

    Logger logger = LoggerFactory.getLogger(MessageReceiverService.class);

   @Transactional
    public void receiveMessage(String message) throws IOException {
        logger.info("Logging the message: ", message);
        Product product = objectMapper.readValue(message, Product.class);
        productService.addProduct(product);
    }
}
