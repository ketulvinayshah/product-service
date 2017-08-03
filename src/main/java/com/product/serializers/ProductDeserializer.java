package com.product.serializers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.product.domain.nosql.Product;
import org.apache.kafka.common.serialization.Deserializer;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by kshah on 8/3/17.
 */
@Component
public class ProductDeserializer implements Deserializer<Product>{

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public Product deserialize(String topic, byte[] data) {
        ObjectMapper mapper = new ObjectMapper();
        Product product = null;
        try {
            product = mapper.readValue(data, Product.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public void close() {

    }
}
