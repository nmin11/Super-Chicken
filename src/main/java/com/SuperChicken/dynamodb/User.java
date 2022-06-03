package com.SuperChicken.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@NoArgsConstructor
@DynamoDBTable(tableName = User.DYNAMO_TABLE_NAME)
public class User {
    public static final String DYNAMO_TABLE_NAME = "User";

    @Id
    @DynamoDBHashKey
    private String email;

    @DynamoDBAttribute
    private String name;

    @DynamoDBAttribute
    private String phone;

    @DynamoDBAttribute
    private boolean agreement;

    @Builder
    public User(String email, String name, String phone, boolean agreement) {
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.agreement = agreement;
    }
}
