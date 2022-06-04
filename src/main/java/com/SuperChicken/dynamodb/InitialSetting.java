package com.SuperChicken.dynamodb;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.util.TableUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
@Transactional
@RequiredArgsConstructor
public class InitialSetting {
    private final AmazonDynamoDB dynamoDB;
    private final DynamoDBMapper dynamoDBMapper;

    @PostConstruct
    void dynamodbSetup() {
        CreateTableRequest createTableRequest = dynamoDBMapper.generateCreateTableRequest(User.class)
                .withProvisionedThroughput(new ProvisionedThroughput(1L, 1L));
        TableUtils.createTableIfNotExists(dynamoDB, createTableRequest);
    }

    @PreDestroy
    void deleteTable() {
        TableUtils.deleteTableIfExists(dynamoDB, dynamoDBMapper.generateDeleteTableRequest(User.class));
    }
}
