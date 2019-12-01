package order.repository;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.ConversionSchemas;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;

import static com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig.builder;

public class DbMapper {

    private static final String REGION = "us-east-1";

    public DynamoDBMapper getMapper() {
        DynamoDBMapperConfig mapperConfig = builder().withConversionSchema(ConversionSchemas.V2).build();
        AmazonDynamoDBClientBuilder clientBuilder = AmazonDynamoDBClientBuilder.standard();
        return new DynamoDBMapper(getClient(clientBuilder), mapperConfig);
    }

    private AmazonDynamoDB getClient(AmazonDynamoDBClientBuilder clientBuilder) {
        return clientBuilder.withRegion(REGION).build();
    }
}
