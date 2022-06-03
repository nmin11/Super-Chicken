package com.SuperChicken;

import com.SuperChicken.dynamodb.User;
import com.SuperChicken.dynamodb.UserRepository;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.util.TableUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    private AmazonDynamoDB dynamoDB;

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setup() {
        CreateTableRequest createTableRequest = dynamoDBMapper.generateCreateTableRequest(User.class)
                .withProvisionedThroughput(new ProvisionedThroughput(1L, 1L));
        TableUtils.createTableIfNotExists(dynamoDB, createTableRequest);
    }

    @AfterEach
    void after() {
        TableUtils.deleteTableIfExists(dynamoDB, dynamoDBMapper.generateDeleteTableRequest(User.class));
    }

    @Test
    @DisplayName("User 를 DynamoDB에 저장")
    void save_test() {
        String email = "abcd@efgh.com";
        String name = "Loko";
        String phone = "+8212345678";
        boolean agreement = true;

        userRepository.save(User.builder()
                .email(email)
                .name(name)
                .phone(phone)
                .agreement(agreement)
                .build());

        User user = userRepository.findAll().iterator().next();
        Assertions.assertThat(user.getEmail()).isEqualTo(email);
    }
}
