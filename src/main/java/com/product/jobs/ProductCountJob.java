package com.product.jobs;

import com.product.repository.ProductSQLRepository;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by kshah on 8/3/17.
 */
@Component
public class ProductCountJob implements Job {

    Logger logger = LoggerFactory.getLogger(ProductCountJob.class);

    @Autowired
    ProductSQLRepository productSQLRepository;

    @Override
    public void execute(JobExecutionContext context){
        logger.info("Current Product Catalog: {}", productSQLRepository.findAll());
    }
}
